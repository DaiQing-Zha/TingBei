package com.jxnu.zha.tingbei.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jxnu.zha.qinglibrary.adapter.BaseCustomAdapter;
import com.jxnu.zha.tingbei.R;
import com.jxnu.zha.tingbei.manager.ImageManager;
import com.jxnu.zha.tingbei.model.MusicListRelease;
import com.jxnu.zha.tingbei.widgets.CircleImageView;

import java.util.List;

/**
 * Created by DaiQing.Zha on 2017/3/27.
 * email:13767191284@163.com
 * description:
 */
public class MusicListAdapter extends BaseCustomAdapter {

    private Context context;
    private List<MusicListRelease.ObjBean.MusicListBean.ListMusicBean> list;

    public MusicListAdapter(Context context, List<MusicListRelease.ObjBean.MusicListBean.ListMusicBean> list) {
        super(context, list);
        this.context = context;
        this.list = list;
    }

    @Override
    public View getCustomView(int position, View convertView, ViewGroup parent) {
        ViewHelper helper = null;
        if (convertView == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.item_music_list,null);
            helper = new ViewHelper();
            helper.imgSongIcon = (CircleImageView) convertView.findViewById(R.id.img_songIcon);
            helper.tvMusicName = (TextView) convertView.findViewById(R.id.tv_musicName);
            helper.tvSingerName = (TextView) convertView.findViewById(R.id.tv_singerName);
            convertView.setTag(helper);
        }else{
            helper = (ViewHelper) convertView.getTag();
        }
        MusicListRelease.ObjBean.MusicListBean.ListMusicBean musicBean = list.get(position);
        ImageManager.getInstance().displayImage(musicBean.getMusicSingerPicPathSmall(), helper.imgSongIcon,
                ImageManager.getUserImageOptions());
        helper.tvMusicName.setText(musicBean.getName());
        helper.tvSingerName.setText(musicBean.getSingerName());
        return convertView;
    }
    class ViewHelper{
        CircleImageView imgSongIcon;
        TextView tvMusicName;
        TextView tvSingerName;
    }
}
