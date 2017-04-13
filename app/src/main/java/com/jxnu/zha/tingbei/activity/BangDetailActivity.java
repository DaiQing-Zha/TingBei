package com.jxnu.zha.tingbei.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.TextView;

import com.jxnu.zha.tingbei.R;
import com.jxnu.zha.tingbei.adapter.BangDetailAdapter;
import com.jxnu.zha.tingbei.core.AbstractActivity;
import com.jxnu.zha.tingbei.manager.ImageManager;
import com.jxnu.zha.tingbei.manager.ThreadPool;
import com.jxnu.zha.tingbei.model.BangList;
import com.jxnu.zha.tingbei.music.util.Player;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by DaiQing.Zha on 2017/4/8 0008.
 */
public class BangDetailActivity extends AbstractActivity{

    @BindView(R.id.img_topBg)
    ImageView mImgTopBg;
    @BindView(R.id.lst_bangList)
    ListView mLstBangList;
    @BindView(R.id.ll_bottomMusicPlayer)
    LinearLayout ll_bottomMusicPlayer;
    @BindView(R.id.img_playerState)
    ImageView img_playerState;
    @BindView(R.id.tv_musicName)
    TextView tv_musicName;
    private boolean isPlaying = false;  //是否正在播放
    final String TAG = "BangDetailActivity";
    BangDetailAdapter bangDetailAdapter;
    List<BangList.ObjEntity.ListMusicEntity> listMusicEntity;
    private Player player;
    private SeekBar musicProgress;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bang_detail);
    }

    @Override
    protected void init() {
        listMusicEntity = new ArrayList<>();
        bangDetailAdapter = new BangDetailAdapter(this,listMusicEntity);
        mLstBangList.setAdapter(bangDetailAdapter);
        Bundle bundle = getIntent().getBundleExtra("bundle");
        Log.e(TAG,"-------------- bundle bundle");
        if (bundle != null){
            Log.e(TAG,"-------------- bundle is not null");
            BangList.ObjEntity objEntity = (BangList.ObjEntity) bundle.getSerializable("bang");
            setTitle(objEntity.getContext());
            if (objEntity != null){
                Log.e(TAG,"--------------" + objEntity.toString());
                ImageManager.getInstance().displayImage(objEntity.getPicPath(), mImgTopBg,
                        ImageManager.getUserImageOptions());
                listMusicEntity.addAll(objEntity.getListMusic());
                bangDetailAdapter.notifyDataSetChanged();
            }
        }
        mLstBangList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                BangList.ObjEntity.ListMusicEntity musicEntity = listMusicEntity.get(position);
                ll_bottomMusicPlayer.setVisibility(View.VISIBLE);
                img_playerState.setImageResource(R.mipmap.ic_play_playing);
                isPlaying = true;
                tv_musicName.setText(musicEntity.getName());
                playMusic(musicEntity.getMusicPath());
            }
        });
        img_playerState.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isPlaying = !isPlaying;
                if (isPlaying){
                    img_playerState.setImageResource(R.mipmap.ic_play_playing);
                    player.play();
                }else{
                    img_playerState.setImageResource(R.mipmap.ic_play_pause);
                    player.pause();
                }
            }
        });
        musicProgress = (SeekBar) findViewById(R.id.seekbar_musicProgress);
        player = new Player(musicProgress);
        musicProgress.setOnSeekBarChangeListener(new SeekBarChangeEvent());
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

    @Override
    protected void onDestroy() {
        super.onDestroy();
        player.stop();
    }
}
