package com.jxnu.zha.tingbei.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.jxnu.zha.qinglibrary.view.LoadStatusBox;
import com.jxnu.zha.tingbei.R;
import com.jxnu.zha.tingbei.constant.RoutConstant;
import com.jxnu.zha.tingbei.core.AbstractActivity;
import com.jxnu.zha.tingbei.https.HttpTools;
import com.jxnu.zha.tingbei.manager.ImageManager;
import com.jxnu.zha.tingbei.manager.ThreadPool;
import com.jxnu.zha.tingbei.model.Entity;
import com.jxnu.zha.tingbei.model.RingInfo;
import com.jxnu.zha.tingbei.music.model.Mp3Info;
import com.jxnu.zha.tingbei.utils.EAlertStyle;
import com.jxnu.zha.tingbei.widgets.CircleImageView;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;

/**
 * Created by DaiQing.Zha on 2017/4/6 0006.
 */
public class RingInfoActivity extends AbstractActivity implements View.OnClickListener{

    @BindView(R.id.img_musicBg)
    ImageView img_musicBg;
    @BindView(R.id.img_singerIcon)
    CircleImageView img_singerIcon;
    @BindView(R.id.tv_musicName)
    TextView tv_musicName;
    @BindView(R.id.tv_singerName)
    TextView tv_singerName;

    @BindView(R.id.loadStatusBox)
    LoadStatusBox mLoadStatusBox;
    private String musicId;
    private String musicName;
    private String TAG = "RingInfoActivity";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ring_info);
    }

    @Override
    protected void init() {
        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra("bundle");
        if (bundle != null){
            musicId = bundle.getString("musicId");
            musicName = bundle.getString("musicName");
        }
        setTitle(musicName);
        mLoadStatusBox.setOnClickListener(this);
        getRingInfo();
    }

    private void getRingInfo(){
        mLoadStatusBox.loading();
        ThreadPool.getInstance().addTask(new Runnable() {
            @Override
            public void run() {
                Map map = new HashMap();
                map.put("appid",HttpTools.APP_ID);
                map.put("id",musicId);
                final String response = HttpTools.httpPost(RoutConstant.getRingInfoByMusicId,map);
                if (HttpTools.checkSource(response)){
                    try{
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                RingInfo ringInfo = new Gson().fromJson(response,RingInfo.class);
                                mLoadStatusBox.loadSuccess();
                                ImageManager.getInstance().displayImage(ringInfo.getObj().getMusicPicPath(), img_musicBg,
                                        ImageManager.getBackPictureOptions());
                                ImageManager.getInstance().displayImage(ringInfo.getObj().getMusicSingerPicPath(), img_singerIcon,
                                        ImageManager.getUserImageOptions());
                                tv_musicName.setText(ringInfo.getObj().getName());
                                tv_singerName.setText(ringInfo.getObj().getSingerName());
                                addMusicToList(ringInfo);
                                saveCache(ringInfo);
                            }
                        });
                    }catch (Exception e){
                        readCacheData(getCacheKey(),response);
                    }
                }else{
                    readCacheData(getCacheKey(),response);
                }
            }
        });
    }

    @Override
    protected Entity readData(Serializable serializable) {
        return (RingInfo)serializable;
    }

    @Override
    protected String getCacheKey() {
        return RoutConstant.getRingInfoByMusicId.replace("/","_") + HttpTools.APP_ID + musicId;
    }

    @Override
    protected void executeOnLoadDataSuccess(Entity entity) {
        super.executeOnLoadDataSuccess(entity);
        mLoadStatusBox.loadSuccess();
        RingInfo ringInfo = (RingInfo) entity;
        ImageManager.getInstance().displayImage(ringInfo.getObj().getMusicPicPath(), img_musicBg,
                ImageManager.getBackPictureOptions());
        ImageManager.getInstance().displayImage(ringInfo.getObj().getMusicSingerPicPath(), img_singerIcon,
                ImageManager.getUserImageOptions());
        tv_musicName.setText(ringInfo.getObj().getName());
        tv_singerName.setText(ringInfo.getObj().getSingerName());
        addMusicToList(ringInfo);
    }

    @Override
    protected void executeOnLoadDataFailure(String errorInfo) {
        super.executeOnLoadDataFailure(errorInfo);
        mLoadStatusBox.loadFailed(getErrorStyle(errorInfo));
        showSnackBarMsg(EAlertStyle.ALERT, getHttpErrorMessage(errorInfo));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.loadStatusBox:
                getRingInfo();
                break;
        }
    }

    /**
     * 添加一首歌曲
     * @param ringInfo
     */
    private void addMusicToList(RingInfo ringInfo){
        RingInfo.ObjBean objBean = ringInfo.getObj();
        Mp3Info mp3Info = new Mp3Info();
        mp3Info.setMusicId(objBean.getId());
        mp3Info.setMusicName(objBean.getName());
        mp3Info.setMusicUrl(objBean.getMusicPath());
        mp3Info.setSingerName(objBean.getSingerName());
        mp3Info.setMusicPicPath(objBean.getMusicPicPath());
        mp3Info.setSingerPicPath(objBean.getMusicSingerPicPath());
        musicIBind.addMusicPlayList(mp3Info);
    }
}
