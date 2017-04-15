package com.jxnu.zha.tingbei.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.jxnu.zha.qinglibrary.adapter.BaseCustomAdapter;
import com.jxnu.zha.tingbei.R;

import java.util.List;

/**
 * Created by Administrator on 2015/10/20.
 */
public class ThemeSelectAdapter extends BaseCustomAdapter<Integer> {

    private int selectedItem;

    public ThemeSelectAdapter(Context context, List<Integer> data) {
        super(context, data);
    }

    @Override
    public View getCustomView(int position, View convertView, ViewGroup parent) {

        ViewHelp help = null;
        if (convertView == null){

            convertView = LayoutInflater.from(context).inflate(R.layout.theme_item,null);
            help = new ViewHelp();
            help.ivBag = (ImageView) convertView.findViewById(R.id.img_themeItem_bg);
            help.ivSelected = (ImageView) convertView.findViewById(R.id.img_themeItem_selected);
            convertView.setTag(help);
        }else{

            help = (ViewHelp) convertView.getTag();
        }
        help.ivBag.setImageResource(data.get(position));
        if (position == selectedItem){

            help.ivSelected.setImageResource(R.mipmap.ic_done_white);
        }

        return convertView;
    }

    public void setSelectedItem(int selectedItem) {
        this.selectedItem = selectedItem;
    }

    class ViewHelp{

        ImageView ivBag;
        ImageView ivSelected;
    }
}
