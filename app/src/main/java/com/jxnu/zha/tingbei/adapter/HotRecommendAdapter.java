package com.jxnu.zha.tingbei.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.jxnu.zha.qinglibrary.adapter.BaseCustomAdapter;
import com.jxnu.zha.tingbei.R;
import com.jxnu.zha.tingbei.model.SongLabel;

import java.util.List;

/**
 * Created by DaiQing.Zha on 2017/3/31 0031.
 */
public class HotRecommendAdapter extends BaseCustomAdapter {

    private Context context;
    private List<SongLabel.ObjEntity> lst;

    public HotRecommendAdapter(Context context,List<SongLabel.ObjEntity> lst) {
        super(context, lst);
        this.context = context;
        this.lst = lst;
    }

    @Override
    public View getCustomView(int position, View convertView, ViewGroup parent) {
        ViewHelper helper = null;
        if (convertView == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.item_hot_recommend_label,null);
            helper = new ViewHelper();
            helper.imgHotRecommendLabelIcon = (ImageView) convertView.findViewById(R.id.imgHotRecommendLabelIcon);
            helper.tvHotRecommendLabelName = (TextView) convertView.findViewById(R.id.tvHotRecommendLabelName);
            convertView.setTag(helper);
        }else{
            helper = (ViewHelper) convertView.getTag();
        }
        SongLabel.ObjEntity objEntity = lst.get(position);
        helper.tvHotRecommendLabelName.setText(objEntity.getName());
        return convertView;
    }

    class ViewHelper{
        ImageView imgHotRecommendLabelIcon;
        TextView tvHotRecommendLabelName;
    }
}
