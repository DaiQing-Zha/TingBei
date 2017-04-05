package com.jxnu.zha.tingbei.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jxnu.zha.qinglibrary.adapter.BaseCustomAdapter;
import com.jxnu.zha.tingbei.R;
import com.jxnu.zha.tingbei.manager.ImageManager;
import com.jxnu.zha.tingbei.model.Singer;
import com.jxnu.zha.tingbei.model.SingerTypes;
import com.jxnu.zha.tingbei.widgets.CircleImageView;

import java.util.List;

/**
 * Created by DaiQing.Zha on 2017/4/5.
 * email:13767191284@163.com
 * description:
 */
public class SingerAdapter extends BaseCustomAdapter {
    private Context context;
    private List<Singer.ObjEntity> singerLst;

    public SingerAdapter(Context context, List<Singer.ObjEntity> singerLst) {
        super(context, singerLst);
        this.context = context;
        this.singerLst = singerLst;
    }

    @Override
    public View getCustomView(int position, View convertView, ViewGroup parent) {
        ViewHelper helper = null;
        if (convertView == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.item_singer,null);
            helper = new ViewHelper();
            helper.imgSingerIcon = (CircleImageView) convertView.findViewById(R.id.img_singerIcon);
            helper.tvSingerName = (TextView) convertView.findViewById(R.id.tv_singerName);
            convertView.setTag(helper);
        }else{
            helper = (ViewHelper) convertView.getTag();
        }
        Singer.ObjEntity objEntity = singerLst.get(position);
        ImageManager.getInstance().displayImage(objEntity.getPic(), helper.imgSingerIcon,
                ImageManager.getRadioIconOptions());
        helper.tvSingerName.setText(objEntity.getName());
        return convertView;
    }

    class ViewHelper{
        CircleImageView imgSingerIcon;
        TextView tvSingerName;
    }
}
