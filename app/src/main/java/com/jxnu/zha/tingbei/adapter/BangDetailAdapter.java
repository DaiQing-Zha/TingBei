package com.jxnu.zha.tingbei.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jxnu.zha.qinglibrary.adapter.BaseCustomAdapter;
import com.jxnu.zha.tingbei.R;
import com.jxnu.zha.tingbei.manager.ImageManager;
import com.jxnu.zha.tingbei.model.BangList;
import com.jxnu.zha.tingbei.model.MusicListRelease;
import com.jxnu.zha.tingbei.widgets.CircleImageView;

import java.util.List;

/**
 * Created by DaiQing.Zha on 2017/4/8 0008.
 */
public class BangDetailAdapter extends BaseCustomAdapter {
    private Context context;
    private List<BangList.ObjEntity.ListMusicEntity> listMusicEntity;

    public BangDetailAdapter(Context context,  List<BangList.ObjEntity.ListMusicEntity> listMusicEntity) {
        super(context, listMusicEntity);
        context = context;
        this.listMusicEntity = listMusicEntity;
    }

    @Override
    public View getCustomView(int position, View convertView, ViewGroup parent) {
        ViewHelper helper = null;
        if (convertView == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.item_bang_list,null);
            helper = new ViewHelper();
            helper.imgSongIcon = (CircleImageView) convertView.findViewById(R.id.img_songIcon);
            helper.tvMusicName = (TextView) convertView.findViewById(R.id.tv_musicName);
            helper.tvSingerName = (TextView) convertView.findViewById(R.id.tv_singerName);
            convertView.setTag(helper);
        }else{
            helper = (ViewHelper) convertView.getTag();
        }
        BangList.ObjEntity.ListMusicEntity object = listMusicEntity.get(position);
        ImageManager.getInstance().displayImage(object.getMusicSingerPicPath(), helper.imgSongIcon,
                ImageManager.getUserImageOptions());
        helper.tvMusicName.setText(object.getName());
        helper.tvSingerName.setText(object.getSingerName());
        return convertView;
    }
    class ViewHelper{
        CircleImageView imgSongIcon;
        TextView tvMusicName;
        TextView tvSingerName;
    }
}
