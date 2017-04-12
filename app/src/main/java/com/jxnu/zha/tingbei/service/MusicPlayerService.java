package com.jxnu.zha.tingbei.service;

import android.app.Service;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.Handler;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.text.format.DateUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.jxnu.zha.tingbei.R;
import com.jxnu.zha.tingbei.manager.ThreadPool;
import com.jxnu.zha.tingbei.music.model.Mp3Info;
import com.jxnu.zha.tingbei.utils.StaticValue;
import com.jxnu.zha.tingbei.widgets.CircleProgressView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by DaiQing.Zha on 2017/4/12 0012.
 */
public class MusicPlayerService extends Service implements View.OnClickListener
        , MediaPlayer.OnBufferingUpdateListener, MediaPlayer.OnCompletionListener,MediaPlayer.OnPreparedListener {

    /**
     * 音乐播放实例
     */
    private MediaPlayer mediaPlayer;
    /**
     * 圆形进度条
     */
    private CircleProgressView circleProgressView;
    /**
     * 音乐背景图片
     */
    private ImageView imgMusicCover;
    /**
     * 音乐名称
     */
    private TextView tvMusicName;
    /**
     * 歌手名称
     */
    private TextView tvMusicSinger;
    /**
     * 音乐播放播放或者暂停按钮
     */
    private ImageView imgMusicPlay;
    /**
     * 要播放的音乐列表
     */
    private List<Mp3Info> musicList;
    /**
     * 浮动视野
     */
    public static View mFloatView;
    /**
     * 根视野
     */
    public static FrameLayout mContentContainer;
    /**
     * Ibinder实例
     */
    public final MusicIBind mMusicIBinder = new MusicIBind();
    /**
     * 计时器
     */
    private Timer mTimer = new Timer();
    /**
     * 音乐是否在播放
     */
    public static boolean isPlaying;
    /**
     * 当前正在播放的位置
     */
    private int mCurrentPlayPosition;
    /**
     * 定时器---更行UI
     */
    private TimerTask task_REFRESH_UI = null;
    /**
     * 判断定时器是否开启过，默认没开启
     */
    private boolean isTimerStarted = false;
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return mMusicIBinder;
    }

    @Override
    public void onCreate() {
        Log.e("mainSERVER","-----------------onCreate-----------------");
        super.onCreate();
        init();
    }


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.e("mainSERVER","-----------------onStartCommand-----------------");
        createFloatView();
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        Log.e("mainSERVER","-----------------onDestroy-----------------");
        super.onDestroy();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.img_playerState:  //暂停和开始
                if (isPlaying){
                    imgMusicPlay.setImageResource(R.mipmap.ic_play_pause1);
                    pauseMusic();
                }else{
                    imgMusicPlay.setImageResource(R.mipmap.ic_play_playing1);
                    playMusic();
                }
                break;
            case R.id.ll_bottomMusicPlayer: //整个播放条
                break;
        }
    }

    /**
     * 初始化操作
     */
    private void init(){
        musicList = new ArrayList<>();
        isPlaying = false;
        try {
            mediaPlayer = new MediaPlayer();
            mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);// 设置媒体流类型
            mediaPlayer.setOnBufferingUpdateListener(this);
            mediaPlayer.setOnPreparedListener(this);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    Handler handler = new Handler() {
        public void handleMessage(android.os.Message msg) {
            int position = mediaPlayer.getCurrentPosition();
            int duration = mediaPlayer.getDuration();
            if (duration > 0) {
                // 计算进度（获取进度条最大刻度*当前音乐播放位置 / 当前音乐时长）
                long pos = circleProgressView.getMaxProgress() * position / duration;
                circleProgressView.setProgress((int) pos);
                circleProgressView.refreshUI();
            }
        };
    };

    @Override
    public void onBufferingUpdate(MediaPlayer mediaPlayer, int i) {

    }

    @Override
    public void onCompletion(MediaPlayer mediaPlayer) {
        isPlaying = false;
        if (musicList.size() <= 0){
            mContentContainer.removeView(mFloatView);
        }else{
            playMusic(mCurrentPlayPosition + 1);
        }
    }

    @Override
    public void onPrepared(MediaPlayer mediaPlayer) {
        mediaPlayer.start();
    }

    public class MusicIBind extends Binder{
        public MusicIBind() {
        }
        public MusicPlayerService getService(){
            return MusicPlayerService.this;
        }
        /**
         * 创建播放栏
         */
        public void createPlayBar(){
            createFloatView();
        }
        /**
         * 添加音乐到播放列表，添加一首,并且播放这首歌
         * @param mp3s
         */
        public void addPlayList(Mp3Info mp3s){
            addMusicToList(mp3s);
        }

        /**
         * 获取音乐列表
         * @return
         */
        public List<Mp3Info> getMusicList(){
            return getMusicList();
        }
        /**
         * 播放
         */
        public void play(){
            mediaPlayer.start();
        }

        /**
         * 暂停
         */
        public void pause(){
            pauseMusic();
        }
    }

    /**
     * 音乐播放
     * @param position
     */
    private void playMusic(int position){
        isPlaying = true;
        try {
            if (circleProgressView == null){
                createFloatView();
            }
            Mp3Info mp3Info = musicList.get(position);
            setPlayBarValue(mp3Info);
            mediaPlayer.reset();
            mediaPlayer.setDataSource(mp3Info.getMusicUrl()); // 设置数据源
            mediaPlayer.prepare(); // prepare自动播放
            Log.e("mainSERVER","url = " + mp3Info.getMusicUrl());
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            Log.e("mainSERVER","exception = " + e.toString());
        } catch (SecurityException e) {
            e.printStackTrace();
            Log.e("mainSERVER","exception = " + e.toString());
        } catch (IllegalStateException e) {
            Log.e("mainSERVER","exception = " + e.toString());
            e.printStackTrace();
        } catch (IOException e) {
            Log.e("mainSERVER","exception = " + e.toString());
            e.printStackTrace();
        }catch (Exception e){
            Log.e("mainSERVER","exception = " + e.toString());
        }
        // 每一秒触发一次
        startTimer();
    }

    /**
     * 开始播放音乐
     */
    private void playMusic(){
        mediaPlayer.start();
        isPlaying = true;
    }
    /**
     * 暂停播放
     */
    private void pauseMusic(){
        mediaPlayer.pause();
        isPlaying = false;
    }

    /**
     * 停止播放
     */
    private void stopMusic(){
        if (mediaPlayer != null) {
            mediaPlayer.stop();
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }
    /**
     * 启动定时器
     */
    private void startTimer(){
        createTaskAndTimer();
        if (!isTimerStarted){    //没开启定时器则开启
            mTimer.schedule(task_REFRESH_UI, 0, 1000);
            isTimerStarted = true;   //开启定时器之后，将状态设置为已经开启
        }
    }

    /**
     * 停止定时器
     */
    private void stopTimer(){
        isTimerStarted = false; //停止定时器时，将状态设置为没有开启
        if (mTimer != null) {
            mTimer.cancel();
            mTimer = null;
        }
        if (task_REFRESH_UI != null) {
            task_REFRESH_UI.cancel();
            task_REFRESH_UI = null;
        }
    }

    /**
     * 创建任务和定时器
     */
    private void createTaskAndTimer(){
        if (mTimer == null) mTimer = new Timer();
        /**
         * 定时器之---应答当前处理器温度
         */
        if (task_REFRESH_UI == null){
            task_REFRESH_UI = new TimerTask() {
                @Override
                public void run() {
                    refreshUI();
                }
            };
        }
    }

    /**
     * 更新UI
     */
    private void refreshUI(){
        if (mediaPlayer == null)
            return;
        if (mediaPlayer.isPlaying() && circleProgressView.isPressed() == false) {
            handler.sendEmptyMessage(0); // 发送消息
        }
    }

    /**
     * 设置播放栏的值
     * @param mp3Info
     */
    private void setPlayBarValue(Mp3Info mp3Info){
        tvMusicName.setText(mp3Info.getMusicName());
        tvMusicSinger.setText(mp3Info.getSingerName());
        imgMusicPlay.setImageResource(R.mipmap.ic_play_playing1);
    }

    /**
     * 获取音乐列表
     * @return
     */
    public List<Mp3Info> getMusicList() {
        return musicList;
    }
    /**
     * 添加音乐到播放列表,添加一首
     */
    private void addMusicToList(Mp3Info mp3s){
        if(!musicList.contains(mp3s)) {
            musicList.add(mp3s);
            mCurrentPlayPosition = musicList.size()-1;//赋值当前播放位置为现在的位置
        }
        playMusic(mCurrentPlayPosition);
    }

    /**
     * 建立底部浮动栏的方法
     */
    public void createFloatView(){
        if(musicList.size() > 0) {
            Log.e("mainSERVER","---start createFloatView---" + com.jxnu.zha.qinglibrary.util.DateUtils.formatDate(System.currentTimeMillis(), com.jxnu.zha.qinglibrary.util.DateUtils.TYPE_01));
            mFloatView = LayoutInflater.from(getBaseContext()).inflate(R.layout.layout_music_play_bar,null);
            ViewGroup mDecorView = (ViewGroup) StaticValue.NowActivity.getWindow().getDecorView();
            mContentContainer = (FrameLayout)((ViewGroup)mDecorView.getChildAt(0)).getChildAt(1);
            FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
            circleProgressView = (CircleProgressView)mFloatView.findViewById(R.id.circleProgressView);
            imgMusicPlay = (ImageView)mFloatView.findViewById(R.id.img_playerState);
            tvMusicName = (TextView)mFloatView.findViewById(R.id.tv_musicName);
            tvMusicSinger = (TextView)mFloatView.findViewById(R.id.tv_singerName);
            mFloatView.findViewById(R.id.ll_bottomMusicPlayer).setOnClickListener(this);
            imgMusicPlay.setImageResource(R.mipmap.ic_play_pause1);
            imgMusicPlay.setOnClickListener(this);
            //获取当前正在播放的音乐
            layoutParams.gravity = Gravity.BOTTOM;//设置对齐位置
            mContentContainer.addView(mFloatView,layoutParams);
            if(!mediaPlayer.isPlaying()) {
                //没有正在播放的，准备当前位置的音乐，设置值
                playMusic(-1);
            }
            setPlayBarValue(musicList.get(mCurrentPlayPosition));
            Log.e("mainSERVER","---end createFloatView---" + com.jxnu.zha.qinglibrary.util.DateUtils.formatDate(System.currentTimeMillis(), com.jxnu.zha.qinglibrary.util.DateUtils.TYPE_01));
        }
    }

}
