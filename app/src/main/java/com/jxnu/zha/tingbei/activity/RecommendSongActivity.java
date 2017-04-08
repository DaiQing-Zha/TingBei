package com.jxnu.zha.tingbei.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.ImageView;
import android.widget.TextView;

import com.jxnu.zha.tingbei.R;
import com.jxnu.zha.tingbei.core.AbstractActivity;
import com.jxnu.zha.tingbei.manager.ImageManager;
import com.jxnu.zha.tingbei.model.SongList;
import com.jxnu.zha.tingbei.widgets.CircleImageView;

import butterknife.BindView;

/**
 * Created by DaiQing.Zha on 2017/4/8.
 * email:13767191284@163.com
 * description:
 */
public class RecommendSongActivity extends AbstractActivity {

    @BindView(R.id.img_songBg)
    ImageView img_songBg;
    @BindView(R.id.img_singerIcon)
    CircleImageView img_singerIcon;
    @BindView(R.id.tv_musicName)
    TextView tv_songName;
    @BindView(R.id.tv_singerName)
    TextView tv_singerName;
    private String TAG = "RecommendSongActivity";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recommend_song);
    }

    @Override
    protected void init() {
        Bundle bundle = getIntent().getBundleExtra("bundle");
        if (bundle != null){
            SongList.ObjEntity.ListMusicEntity objEntity = (SongList.ObjEntity.ListMusicEntity) bundle.getSerializable("song");
            if (objEntity != null){
                ImageManager.getInstance().displayImage(objEntity.getMusicPicPath(), img_songBg,
                        ImageManager.getBackPictureOptions());
                ImageManager.getInstance().displayImage(objEntity.getMusicSingerPicPath(), img_singerIcon,
                        ImageManager.getUserImageOptions());
                tv_songName.setText(objEntity.getName());
                tv_singerName.setText(objEntity.getSingerName());
                setTitle(objEntity.getName());
            }
        }
    }
}
