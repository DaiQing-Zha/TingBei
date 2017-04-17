package com.jxnu.zha.tingbei.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.jxnu.zha.qinglibrary.adapter.BaseCustomAdapter;
import com.jxnu.zha.tingbei.R;
import com.jxnu.zha.tingbei.manager.ImageManager;
import com.jxnu.zha.tingbei.model.BangList;

import java.util.List;

/**
 * Created by DaiQing.Zha on 2017/4/5.
 * email:13767191284@163.com
 * description:
 */
public class RecommendBandAdapter extends BaseCustomAdapter {
    private Context context;
    private List<BangList.ObjEntity> bangList;

    public RecommendBandAdapter(Context context,List<BangList.ObjEntity> bangList) {
        super(context, bangList);
        this.context = context;
        this.bangList = bangList;
    }

    @Override
    public View getCustomView(int position, View convertView, ViewGroup parent) {
        ViewHelper helper = null;
        if (convertView == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.item_recommend_bang,null);
            helper = new ViewHelper();
            helper.imgSongIcon = (ImageView) convertView.findViewById(R.id.img_songIcon);
            helper.tvSongName = (TextView) convertView.findViewById(R.id.tv_musicName);
            helper.tvSongSinger = (TextView) convertView.findViewById(R.id.tv_songSinger);
            convertView.setTag(helper);
        }else{
            helper = (ViewHelper) convertView.getTag();
        }
        BangList.ObjEntity bean = bangList.get(position);
        ImageManager.getInstance().displayImage(bean.getPicPath(), helper.imgSongIcon,
                ImageManager.getMusicIconOptions());
        helper.tvSongName.setText(bean.getName());
        helper.tvSongSinger.setText(bean.getTitle());
        return convertView;
    }

    class ViewHelper{
        ImageView imgSongIcon;
        TextView tvSongName;
        TextView tvSongSinger;
    }
}
