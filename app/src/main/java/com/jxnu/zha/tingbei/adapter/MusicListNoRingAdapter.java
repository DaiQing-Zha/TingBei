package com.jxnu.zha.tingbei.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jxnu.zha.qinglibrary.adapter.BaseCustomAdapter;
import com.jxnu.zha.tingbei.R;
import com.jxnu.zha.tingbei.manager.ImageManager;
import com.jxnu.zha.tingbei.model.Music;
import com.jxnu.zha.tingbei.model.MusicListNoRing;
import com.jxnu.zha.tingbei.widgets.CircleImageView;

import java.util.List;

/**
 * Created by DaiQing.Zha on 2017/5/11.
 * email:13767191284@163.com
 * description:
 */
public class MusicListNoRingAdapter extends BaseCustomAdapter{

    private Context context;
    List<MusicListNoRing.ObjEntity.MusicListEntity.ListMusicEntity> musicLst;

    public MusicListNoRingAdapter(Context context, List<MusicListNoRing.ObjEntity.MusicListEntity.ListMusicEntity> musicLst) {
        super(context, musicLst);
        this.context = context;
        this.musicLst = musicLst;
    }

    @Override
    public View getCustomView(int position, View convertView, ViewGroup parent) {
        ViewHelper helper = null;
        if (convertView == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.item_singer_music,null);
            helper = new ViewHelper();
            helper.imgMusicIcon = (CircleImageView) convertView.findViewById(R.id.img_musicIcon);
            helper.tvMusicName = (TextView) convertView.findViewById(R.id.tv_musicName);
            convertView.setTag(helper);
        }else{
            helper = (ViewHelper) convertView.getTag();
        }
        MusicListNoRing.ObjEntity.MusicListEntity.ListMusicEntity objEntity = musicLst.get(position);
        ImageManager.getInstance().displayImage(objEntity.getMusicSingerPicPath(), helper.imgMusicIcon,
                ImageManager.getMusicIconOptions());
        helper.tvMusicName.setText(objEntity.getName());
        return convertView;
    }

    class ViewHelper{
        CircleImageView imgMusicIcon;
        TextView tvMusicName;
    }
}
