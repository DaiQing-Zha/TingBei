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
import com.jxnu.zha.tingbei.model.LabelSongList;
import com.jxnu.zha.tingbei.model.Music;
import com.jxnu.zha.tingbei.model.SongList;
import com.jxnu.zha.tingbei.widgets.CircleImageView;

import java.util.List;

/**
 * Created by DaiQing.Zha on 2017/4/6 0006.
 */
public class LabelSongListAdapter extends BaseCustomAdapter {

    private Context context;
    private List<LabelSongList.ObjBean> labelSongLst;

    public LabelSongListAdapter(Context context,  List<LabelSongList.ObjBean> labelSongLst) {
        super(context, labelSongLst);
        this.context = context;
        this.labelSongLst = labelSongLst;
    }

    @Override
    public View getCustomView(int position, View convertView, ViewGroup parent) {
        ViewHelper helper = null;
        if (convertView == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.item_song_list,null);
            helper = new ViewHelper();
            helper.img_songIcon = (ImageView
                    ) convertView.findViewById(R.id.img_songIcon);
            helper.tv_titleName = (TextView) convertView.findViewById(R.id.tv_titleName);
            convertView.setTag(helper);
        }else{
            helper = (ViewHelper) convertView.getTag();
        }
        LabelSongList.ObjBean objBean = labelSongLst.get(position);
        ImageManager.getInstance().displayImage(objBean.getPicPathSmall(), helper.img_songIcon,
                ImageManager.getRadioIconOptions());
        helper.tv_titleName.setText(objBean.getTitle());
        return convertView;
    }

    class ViewHelper{
        ImageView img_songIcon;
        TextView tv_titleName;
    }
}
