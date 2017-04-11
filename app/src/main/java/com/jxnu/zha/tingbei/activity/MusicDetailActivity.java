package com.jxnu.zha.tingbei.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.google.gson.Gson;
import com.jxnu.zha.qinglibrary.view.LoadStatusBox;
import com.jxnu.zha.tingbei.R;
import com.jxnu.zha.tingbei.adapter.MusicListAdapter;
import com.jxnu.zha.tingbei.constant.RoutConstant;
import com.jxnu.zha.tingbei.core.AbstractActivity;
import com.jxnu.zha.tingbei.https.HttpTools;
import com.jxnu.zha.tingbei.manager.ImageManager;
import com.jxnu.zha.tingbei.manager.ThreadPool;
import com.jxnu.zha.tingbei.model.BangList;
import com.jxnu.zha.tingbei.model.Entity;
import com.jxnu.zha.tingbei.model.MusicListRelease;
import com.jxnu.zha.tingbei.model.Recommend;
import com.jxnu.zha.tingbei.music.util.Player;
import com.jxnu.zha.tingbei.music.util.Player1;
import com.jxnu.zha.tingbei.utils.EAlertStyle;
import com.jxnu.zha.tingbei.utils.StaticValue;
import com.jxnu.zha.tingbei.widgets.CircleProgressView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;

public class MusicDetailActivity extends AbstractActivity implements View.OnClickListener{

    @BindView(R.id.img_topBg)
    ImageView mImgTopBg;
    @BindView(R.id.loadStatusBox)
    LoadStatusBox mLoadStatusBox;
    @BindView(R.id.lst_musicList)
    ListView mLstMusicList;
    @BindView(R.id.ll_bottomMusicPlayer)
    LinearLayout ll_bottomMusicPlayer;
    @BindView(R.id.img_playerState)
    ImageView img_playerState;
    @BindView(R.id.tv_musicName)
    TextView tv_musicName;
    @BindView(R.id.circleProgressView)
    CircleProgressView circleProgressView;
    final String TAG = "musicDetail";
    private String mReleaseId = "";
    private String mPicPath = "";
    MusicListAdapter mMusicListAdapter;
    List<MusicListRelease.ObjBean.MusicListBean.ListMusicBean> mObjBeanList;
    private Player1 player;
    private boolean isPlaying = false;  //是否正在播放
    /**
     * 获取推荐页分组
     */
    StringRequest musicListRelease = new StringRequest(Request.Method.POST
            , HttpTools.getAbsoluteUrl(RoutConstant.getMusicListReleaseById)
            , new Response.Listener<String>() {
        @Override
        public void onResponse(String response) {
            mLoadStatusBox.loadSuccess();
            MusicListRelease musicListRelease = new Gson().fromJson(response,MusicListRelease.class);
            mObjBeanList.addAll(musicListRelease.getObj().getMusicList().getListMusic());
            mMusicListAdapter.notifyDataSetChanged();
            saveCache(musicListRelease);
        }
    }, new Response.ErrorListener() {
        @Override
        public void onErrorResponse(VolleyError error) {
            Log.e("mainError","error = " + error.toString());
            readCacheData(getCacheKey(),error.toString());
        }
    }){
        @Override
        protected Map<String, String> getParams() throws AuthFailureError {
            Map map = new HashMap();
            map.put("appid",HttpTools.APP_ID);
            map.put("id",mReleaseId);
            map.put("position","1");
            return map;
        }
    };
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music_detail);
        createFloatView();
    }
    @Override
    protected void init() {
        Intent intent = getIntent();
        Recommend.ObjEntity objEntity = (Recommend.ObjEntity) intent.getSerializableExtra("obj");
        setTitle(objEntity.getTitle());
        mReleaseId = objEntity.getMusicListReleaseId();
        mPicPath = objEntity.getPicPath();
        ImageManager.getInstance().displayImage(mPicPath, mImgTopBg,
                ImageManager.getBackPictureOptions());
        mLoadStatusBox.setOnClickListener(this);
        mObjBeanList = new ArrayList<>();
        mMusicListAdapter = new MusicListAdapter(this,mObjBeanList);
        mLstMusicList.setAdapter(mMusicListAdapter);
        player = new Player1(circleProgressView);
        getMusicListRelease();
        mLstMusicList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                MusicListRelease.ObjBean.MusicListBean.ListMusicBean musicBean = mObjBeanList.get(position);
                ll_bottomMusicPlayer.setVisibility(View.VISIBLE);
                img_playerState.setImageResource(R.mipmap.ic_play_playing1);
                isPlaying = true;
                tv_musicName.setText(musicBean.getName());
                playMusic(musicBean.getMusicPath());
            }
        });
        img_playerState.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isPlaying = !isPlaying;
                if (isPlaying){
                    img_playerState.setImageResource(R.mipmap.ic_play_playing1);
                    player.play();
                }else{
                    img_playerState.setImageResource(R.mipmap.ic_play_pause1);
                    player.pause();
                }
            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.loadStatusBox:
                Log.e(TAG,"---------loadErrorBn---------");
                getMusicListRelease();
                break;
        }
    }

    /**
     * 根据ID获取发布的音乐集
     */
    private void getMusicListRelease(){
        mLoadStatusBox.loading();
        mRQueue.add(musicListRelease);
    }

    @Override
    protected Entity readData(Serializable serializable) {
        return (MusicListRelease) serializable;
    }

    @Override
    protected String getCacheKey() {
        return RoutConstant.getMusicListReleaseById.replace("/","_") + HttpTools.APP_ID + mReleaseId;
    }

    @Override
    protected void executeOnLoadDataSuccess(Entity entity) {
        super.executeOnLoadDataSuccess(entity);
        mLoadStatusBox.loadSuccess();
        MusicListRelease musicListRelease = (MusicListRelease) entity;
        mObjBeanList.addAll(musicListRelease.getObj().getMusicList().getListMusic());
        mMusicListAdapter.notifyDataSetChanged();
    }

    @Override
    protected void executeOnLoadDataFailure(String errorInfo) {
        super.executeOnLoadDataFailure(errorInfo);
        mLoadStatusBox.loadFailed(getErrorStyle(errorInfo));
        showSnackBarMsg(EAlertStyle.ALERT,getVolleyErrorMessage(errorInfo));
    }

    class SeekBarChangeEvent implements SeekBar.OnSeekBarChangeListener {
        int progress;
        @Override
        public void onProgressChanged(SeekBar seekBar, int progress,
                                      boolean fromUser) {
            // 原本是(progress/seekBar.getMax())*player.mediaPlayer.getDuration()
            this.progress = progress * player.mediaPlayer.getDuration()
                    / seekBar.getMax();
        }
        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {
        }
        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {
            // seekTo()的参数是相对与影片时间的数字，而不是与seekBar.getMax()相对的数字
            player.mediaPlayer.seekTo(progress);
        }
    }
    /**
     * 播放音乐
     * @param musicUrl
     */
    private void playMusic(final String musicUrl){
        ThreadPool.getInstance().addTask(new Runnable() {
            @Override
            public void run() {
                player.playUrl(musicUrl);
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        player.stop();
    }
    /**
     * 浮动视野
     */
    public static View mFloatView;
    /**
     * 根视野
     */
    public static FrameLayout mContentContainer;
    private void createFloatView(){
        mFloatView = LayoutInflater.from(getBaseContext()).inflate(R.layout.layout_music_bottom,null);
        ViewGroup mDecorView = (ViewGroup) StaticValue.NowActivity.getWindow().getDecorView();
        mContentContainer = (FrameLayout)((ViewGroup)mDecorView.getChildAt(0)).getChildAt(1);
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);

        //获取当前正在播放的音乐
        layoutParams.gravity = Gravity.BOTTOM;//设置对齐位置
        mContentContainer.addView(mFloatView,layoutParams);
    }
}
