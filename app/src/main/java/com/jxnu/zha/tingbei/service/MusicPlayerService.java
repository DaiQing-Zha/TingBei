package com.jxnu.zha.tingbei.service;

import android.app.Dialog;
import android.app.Service;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.Handler;
import android.os.IBinder;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import com.jxnu.zha.tingbei.R;
import com.jxnu.zha.tingbei.manager.ImageManager;
import com.jxnu.zha.tingbei.manager.ThreadPool;
import com.jxnu.zha.tingbei.music.model.Mp3Info;
import com.jxnu.zha.tingbei.utils.StaticValue;
import com.jxnu.zha.tingbei.widgets.CircleImageView;
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
    /**
     * 是否添加了歌曲
     */
    private boolean isAddMusic = false;
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
            case R.id.img_commReturn:
                fullScreenDialog.dismiss();
                break;
            case R.id.img_playState:
                if (isPlaying){
                    imgDialogPlayState.setImageResource(R.mipmap.ic_play_playing_2);
                    imgMusicPlay.setImageResource(R.mipmap.ic_play_playing_1);
                    pauseMusic();
                }else{
                    imgDialogPlayState.setImageResource(R.mipmap.ic_play_pause_2);
                    imgMusicPlay.setImageResource(R.mipmap.ic_play_pause_1);
                    playMusic();
                }
                break;
            case R.id.img_playerState:  //暂停和开始
                if (isPlaying){
                    imgMusicPlay.setImageResource(R.mipmap.ic_play_playing_1);
                    pauseMusic();
                }else{
                    imgMusicPlay.setImageResource(R.mipmap.ic_play_pause_1);
                    Log.e("mainSERVER","--------------state2-----------");
                    playMusic();
                }
                break;
            case R.id.img_lastMusic:
                mCurrentPlayPosition = mCurrentPlayPosition - 1;
                playMusic(mCurrentPlayPosition );
                break;
            case R.id.img_nextMusic:
                mCurrentPlayPosition = mCurrentPlayPosition + 1;
                playMusic(mCurrentPlayPosition);
                break;
            case R.id.ll_bottomMusicPlayer: //整个播放条
                alertDialog();
                break;
        }
    }
    Dialog fullScreenDialog;
    TextView tvDialogMusicName;
    TextView tvDialogSingerName;
    CircleImageView imgDialogMusicIcon;
    ImageView imgDialogCommReturn;
    ImageView imgDialogLastMusic;
    ImageView imgDialogPlayState;
    ImageView imgDialogNextMusic;
    SeekBar seekBarDialogMusicProgress;
    /**
     * 弹出音乐播放对话框
     */
    private void alertDialog(){
        if (fullScreenDialog == null){
            fullScreenDialog = new Dialog(this,android.R.style.Theme_Black_NoTitleBar_Fullscreen);
            View view = LayoutInflater.from(getBaseContext()).inflate(R.layout.layout_fullscreen_music_playing,null);
            tvDialogMusicName = (TextView) view.findViewById(R.id.tv_musicName);
            tvDialogSingerName = (TextView) view.findViewById(R.id.tv_singerName);
            imgDialogMusicIcon = (CircleImageView) view.findViewById(R.id.img_musicIcon);
            imgDialogCommReturn = (ImageView) view.findViewById(R.id.img_commReturn);
            imgDialogLastMusic = (ImageView) view.findViewById(R.id.img_lastMusic);
            imgDialogPlayState = (ImageView) view.findViewById(R.id.img_playState);
            imgDialogNextMusic = (ImageView) view.findViewById(R.id.img_nextMusic);
            seekBarDialogMusicProgress = (SeekBar) view.findViewById(R.id.seekbar_musicProgress);
            imgDialogCommReturn.setOnClickListener(this);
            imgDialogLastMusic.setOnClickListener(this);
            imgDialogPlayState.setOnClickListener(this);
            imgDialogNextMusic.setOnClickListener(this);
            seekBarDialogMusicProgress.setOnSeekBarChangeListener(new SeekBarChangeEvent());
            fullScreenDialog.setContentView(view);//自定义的view
            //在dialog show前添加此代码，表示该dialog属于系统dialog。
            fullScreenDialog.getWindow().setType((WindowManager.LayoutParams.TYPE_SYSTEM_ALERT));
            // 添加动画
            fullScreenDialog.getWindow().setWindowAnimations(R.style.action_sheet_animation);
        }
        try{
            Mp3Info mp3Info = musicList.get(mCurrentPlayPosition);
            refreshDialog(mp3Info);
        }catch (Exception e){

        }
        new Thread() {
            public void run() {
                SystemClock.sleep(100);
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        fullScreenDialog.show();
                    }
                });
            };
        }.start();
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
            mediaPlayer.setOnCompletionListener(this);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    long playPos;
    Handler handler = new Handler() {
        public void handleMessage(android.os.Message msg) {
            int position = mediaPlayer.getCurrentPosition();
            int duration = mediaPlayer.getDuration();
            if (duration > 0) {
                // 计算进度（获取进度条最大刻度*当前音乐播放位置 / 当前音乐时长）
                playPos = circleProgressView.getMaxProgress() * position / duration;
                if (playPos >= 99) playPos = 100;
                circleProgressView.setProgress((int) playPos);
                if (seekBarDialogMusicProgress != null){
                    seekBarDialogMusicProgress.setProgress((int) playPos);
                }
                circleProgressView.refreshUI();
            }
        };
    };

    @Override
    public void onBufferingUpdate(MediaPlayer mediaPlayer, int i) {
        if (seekBarDialogMusicProgress != null){
            seekBarDialogMusicProgress.setSecondaryProgress(i);
            int currentProgress = seekBarDialogMusicProgress.getMax()
                    * mediaPlayer.getCurrentPosition() / mediaPlayer.getDuration();
            Log.e(currentProgress + "% play", i + " buffer");
        }
    }

    @Override
    public void onCompletion(MediaPlayer mediaPlayer) {
        isPlaying = false;
        if (musicList.size() <= 0){
            mContentContainer.removeView(mFloatView);
        }else{
            if (playPos >= 99 && musicList.size() > (mCurrentPlayPosition + 1)){
                mCurrentPlayPosition = mCurrentPlayPosition + 1;
                playMusic(mCurrentPlayPosition);
            }else if (musicList.size() <= (mCurrentPlayPosition + 1)){
                if (!isAddMusic && playPos >= 99){
                    imgMusicPlay.setImageResource(R.mipmap.ic_play_playing_1);
                    playPos = 0;
                    if (seekBarDialogMusicProgress != null) seekBarDialogMusicProgress.setProgress(0);
                    if (circleProgressView != null) circleProgressView.setProgress(0);
                    Log.e("mainSERVER1","-------------------------------Mipmap4 = " + isPlaying);
                }else{
                    isPlaying = true;
                    isAddMusic = false;
                }
            }
        }
    }

    @Override
    public void onPrepared(MediaPlayer mediaPlayer) {
        mediaPlayer.start();
    }

    public class MusicIBind extends Binder{
        /**
         * 构造函数
         */
        public MusicIBind() {
        }
        /**
         * 获取service实例
         * @return
         */
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
        public void addMusicPlayList(Mp3Info mp3s){
            addMusicToPlayListInner(mp3s);
        }
        public void addMusicsToPlayList(ArrayList<Mp3Info> mp3Infos){
            addMusicsToPlayListInner(mp3Infos);
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
            playMusic();
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
    private void playMusic(final int position){

        isPlaying = true;
        try{
            final Mp3Info mp3Info = musicList.get(position);
            if (circleProgressView == null){
                createFloatView();
            }
            Log.e("mainSERVER1","-------------------------------Mipmap5 = " + isPlaying);
            setPlayBarValue(mp3Info);
            refreshDialog(mp3Info);
            ThreadPool.getInstance().addTask(new Runnable() {
                @Override
                public void run() {
                    mediaPlayer.reset();
                    try {
                        mediaPlayer.setDataSource(mp3Info.getMusicUrl()); // 设置数据源
                        mediaPlayer.prepare(); // prepare自动播放
                        Log.e("mainSERVER","url = " + mp3Info.getMusicUrl());
                    } catch (IOException e) {
                        Log.e("mainSERVER","error1 = " + e.toString());
                    }catch (Exception e){
                        Log.e("mainSERVER","error1 = " + e.toString());
                    }
                }
            });
        }catch (Exception e){
            Log.e("mainSERVER","error2 = " + e.toString());
        }
        startTimer();
    }

    /**
     * 重新播放
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
     * 更新dialog
     * @param mp3Info
     */
    private void refreshDialog(Mp3Info mp3Info){
        Log.e("mainSERVER1","-------------------------------Mipmap1 = " + isPlaying);
        if (imgDialogPlayState != null){
            Log.e("mainSERVER1","-------------------------------Mipmap2 = " + isPlaying);
            if (isPlaying){
                imgDialogPlayState.setImageResource(R.mipmap.ic_play_pause_2);
            }else{
                imgDialogPlayState.setImageResource(R.mipmap.ic_play_playing_2);
            }
            seekBarDialogMusicProgress.setProgress((int) playPos);
            tvDialogMusicName.setText(mp3Info.getMusicName());
            tvDialogSingerName.setText(mp3Info.getSingerName());
            ImageManager.getInstance().displayImage(mp3Info.getMusicPicPath(), imgDialogMusicIcon,
                    ImageManager.getMusicBgIconOptions());
        }
    }

    /**
     * 设置播放栏的值
     * @param mp3Info
     */
    private void setPlayBarValue(Mp3Info mp3Info){
        tvMusicName.setText(mp3Info.getMusicName());
        tvMusicSinger.setText(mp3Info.getSingerName());
        Log.e("mainSERVER1","-------------------------------Mipmap3 = " + isPlaying);
        if (isPlaying){
            imgMusicPlay.setImageResource(R.mipmap.ic_play_pause_1);
        }else{
            imgMusicPlay.setImageResource(R.mipmap.ic_play_playing_1);
        }
        refreshUI();
    }

    /**
     * 获取音乐列表
     * @return
     */
    public List<Mp3Info> getMusicList() {
        return musicList;
    }

    /**
     * 添加音乐到播放列表
     */
    private void addMusicsToPlayListInner(ArrayList<Mp3Info> mp3s){
        try{
            musicList.clear();
            for (int i = 0; i < mp3s.size(); i ++){
                Log.e("mainSERVER","------------------- i = " + i);
                if(!musicIsRepeat(mp3s.get(i))) {
                    musicList.add(mp3s.get(i));
                }
            }
            for (int i = 0; i < musicList.size(); i ++){
                Log.e("mainHHH","musicName2 = " + musicList.get(i).getMusicName());
            }
            Log.e("mainHHH","---------------------------------------------");
        }catch (Exception e){
            Log.e("mainSERVER","error = " + e.toString());
        }
    }
    /**
     * 添加音乐到播放列表,添加一首
     */
    private void addMusicToPlayListInner(Mp3Info mp3s){
        if(!musicIsRepeat(mp3s)) {
            musicList.add(mp3s);
            mCurrentPlayPosition = musicList.size()-1;//赋值当前播放位置为现在的位置
            Log.e("mainHHH","mCurrentPlayPosition1 = " + mCurrentPlayPosition);
        }else{
            mCurrentPlayPosition = getMusicInListPosition(mp3s);
            Log.e("mainHHH","mCurrentPlayPosition2 = " + mCurrentPlayPosition);
        }
        playMusic(mCurrentPlayPosition);
        isAddMusic = true;
    }

    /**
     * 建立底部浮动栏的方法
     */
    public void createFloatView(){
        if(musicList.size() > 0) {
            mFloatView = LayoutInflater.from(getBaseContext()).inflate(R.layout.layout_music_play_bar,null);
            ViewGroup mDecorView = (ViewGroup) StaticValue.NowActivity.getWindow().getDecorView();
            mContentContainer = (FrameLayout)((ViewGroup)mDecorView.getChildAt(0)).getChildAt(1);
            FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
            circleProgressView = (CircleProgressView)mFloatView.findViewById(R.id.circleProgressView);
            imgMusicPlay = (ImageView)mFloatView.findViewById(R.id.img_playerState);
            tvMusicName = (TextView)mFloatView.findViewById(R.id.tv_musicName);
            tvMusicSinger = (TextView)mFloatView.findViewById(R.id.tv_singerName);
            mFloatView.findViewById(R.id.ll_bottomMusicPlayer).setOnClickListener(this);
            if (isPlaying){
                imgMusicPlay.setImageResource(R.mipmap.ic_play_pause_1);
            }else{
                imgMusicPlay.setImageResource(R.mipmap.ic_play_playing_1);
            }
            Log.e("mainSERVER","--------------state1-----------");
            imgMusicPlay.setOnClickListener(this);
            //获取当前正在播放的音乐
            layoutParams.gravity = Gravity.BOTTOM;//设置对齐位置
            mContentContainer.addView(mFloatView,layoutParams);
            setPlayBarValue(musicList.get(mCurrentPlayPosition));

        }
    }
    class SeekBarChangeEvent implements SeekBar.OnSeekBarChangeListener {
        int progress;
        @Override
        public void onProgressChanged(SeekBar seekBar, int progress,
                                      boolean fromUser) {
            // 原本是(progress/seekBar.getMax())*player.mediaPlayer.getDuration()
            this.progress = progress * mediaPlayer.getDuration()
                    / seekBar.getMax();
        }
        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {
        }
        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {
            // seekTo()的参数是相对与影片时间的数字，而不是与seekBar.getMax()相对的数字
            mediaPlayer.seekTo(progress);
        }
    }

    /**
     * 判断歌曲是否重复
     * @param mp3Info
     * @return
     */
    private boolean musicIsRepeat(Mp3Info mp3Info){
        String key = mp3Info.getMusicId();
        for (int i = 0; i < musicList.size(); i ++){
            if (key.equals(musicList.get(i).getMusicId())){
                return true;
            }
        }
        return false;
    }

    /**
     * 获取音乐在列表中的位置
     * @param mp3Info
     * @return
     */
    private int getMusicInListPosition(Mp3Info mp3Info){
        int pos = - 1;
        String key = mp3Info.getMusicId();
        for (int i = 0; i < musicList.size(); i ++){
            if (key.equals(musicList.get(i).getMusicId())){
                pos = i;
            }
        }
        return pos;
    }
}
