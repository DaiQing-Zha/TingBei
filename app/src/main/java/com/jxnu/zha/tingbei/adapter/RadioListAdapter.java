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
import com.jxnu.zha.tingbei.model.RadioList;
import com.jxnu.zha.tingbei.model.SongLabel;
import com.jxnu.zha.tingbei.widgets.CircleImageView;

import java.util.List;

/**
 * Created by DaiQing.Zha on 2017/4/3.
 * email:13767191284@163.com
 * description:
 */
public class RadioListAdapter extends BaseCustomAdapter {
    private Context context;
    private List<RadioList.ObjEntity> radioList;
    public RadioListAdapter(Context context, List<RadioList.ObjEntity> radioList) {
        super(context,radioList);
        this.context = context;
        this.radioList = radioList;
    }

    @Override
    public View getCustomView(int position, View convertView, ViewGroup parent) {
        ViewHelper helper = null;
        if (convertView == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.item_hot_recommed_radio,null);
            helper = new ViewHelper();
            helper.imgRadioIcon = (ImageView) convertView.findViewById(R.id.img_radioIcon);
            helper.tvRadioName = (TextView) convertView.findViewById(R.id.tv_radioName);
            convertView.setTag(helper);
        }else{
            helper = (ViewHelper) convertView.getTag();
        }
        RadioList.ObjEntity objEntity = radioList.get(position);
        ImageManager.getInstance().displayImage(objEntity.getPicPath(), helper.imgRadioIcon,
                ImageManager.getRadioIconOptions());
        helper.tvRadioName.setText(objEntity.getName());
        return convertView;
    }

    class ViewHelper{
        ImageView imgRadioIcon;
        TextView tvRadioName;
    }
}
