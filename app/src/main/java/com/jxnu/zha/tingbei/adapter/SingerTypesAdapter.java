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
import com.jxnu.zha.tingbei.model.SingerTypes;

import java.util.List;

/**
 * Created by DaiQing.Zha on 2017/4/5 0005.
 */
public class SingerTypesAdapter extends BaseCustomAdapter {
    private Context context;
    private List<SingerTypes.ObjBean> singerList;

    public SingerTypesAdapter(Context context,List<SingerTypes.ObjBean> singerList) {
        super(context, singerList);
        this.context = context;
        this.singerList = singerList;
    }

    @Override
    public View getCustomView(int position, View convertView, ViewGroup parent) {
        ViewHelper helper = null;
        if (convertView == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.item_singer_types,null);
            helper = new ViewHelper();
            helper.tvSingerTypeName = (TextView) convertView.findViewById(R.id.tvSingerTypeName);
            convertView.setTag(helper);
        }else{
            helper = (ViewHelper) convertView.getTag();
        }
        SingerTypes.ObjBean objBean = singerList.get(position);
        helper.tvSingerTypeName.setText(objBean.getDicValue());
        return convertView;
    }

    class ViewHelper{
        TextView tvSingerTypeName;
    }
}
