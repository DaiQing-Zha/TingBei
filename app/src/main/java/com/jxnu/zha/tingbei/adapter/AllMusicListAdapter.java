package com.jxnu.zha.tingbei.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.jxnu.zha.qinglibrary.adapter.BaseCustomAdapter;
import com.jxnu.zha.tingbei.R;
import com.jxnu.zha.tingbei.model.SingerTypes;
import com.jxnu.zha.tingbei.music.model.Mp3Info;

import java.util.List;

/**
 * Created by DaiQing.Zha on 2017/4/5 0005.
 */
public class AllMusicListAdapter extends BaseCustomAdapter {
    private Context context;
    private List<Mp3Info> mp3InfoList;
    private DeleteOneMusic deleteOneMusic;

    public AllMusicListAdapter(Context context, List<Mp3Info> mp3InfoList,DeleteOneMusic deleteOneMusic) {
        super(context, mp3InfoList);
        this.context = context;
        this.mp3InfoList = mp3InfoList;
        this.deleteOneMusic = deleteOneMusic;
    }

    @Override
    public View getCustomView(int position, View convertView, ViewGroup parent) {
        ViewHelper helper = null;
        DeleteClickListener deleteClickListener = null;
        if (convertView == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.item_all_music_list,null);
            helper = new ViewHelper();
            deleteClickListener = new DeleteClickListener();
            helper.tvMusicName = (TextView) convertView.findViewById(R.id.tv_musicName);
            helper.tvMusicSinger = (TextView) convertView.findViewById(R.id.tv_musicSinger);
            helper.imgDeleteMusic = (ImageView) convertView.findViewById(R.id.imgDeleteMusic);
            helper.imgDeleteMusic.setOnClickListener(deleteClickListener);
            helper.imgDeleteMusic.setTag(helper.imgDeleteMusic.getId(),deleteClickListener);
            convertView.setTag(helper);
        }else{
            helper = (ViewHelper) convertView.getTag();
            deleteClickListener = (DeleteClickListener) helper.imgDeleteMusic.getTag(helper.imgDeleteMusic.getId());
        }
        deleteClickListener.setPosition(position);
        Mp3Info mp3Info = mp3InfoList.get(position);
        helper.tvMusicName.setText(mp3Info.getMusicName());
        helper.tvMusicSinger.setText(mp3Info.getSingerName());
        return convertView;
    }

    /**
     * 地点点击监听器
     */
    class DeleteClickListener implements View.OnClickListener{
        private int position;
        public void setPosition(int position){
            this.position = position;
        }
        @Override
        public void onClick(View v) {
            mp3InfoList.remove(position);
            deleteOneMusic.onDeleteOneMusic(position);
            notifyDataSetInvalidated();
        }
    }
    public interface DeleteOneMusic{
        void onDeleteOneMusic(int position);
    }
    class ViewHelper{
        TextView tvMusicName;
        TextView tvMusicSinger;
        ImageView imgDeleteMusic;
    }
}
