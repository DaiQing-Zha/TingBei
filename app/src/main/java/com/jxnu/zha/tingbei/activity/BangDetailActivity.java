package com.jxnu.zha.tingbei.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.ImageView;
import android.widget.ListView;

import com.jxnu.zha.qinglibrary.view.LoadStatusBox;
import com.jxnu.zha.tingbei.R;
import com.jxnu.zha.tingbei.adapter.BangDetailAdapter;
import com.jxnu.zha.tingbei.adapter.MusicListAdapter;
import com.jxnu.zha.tingbei.core.AbstractActivity;
import com.jxnu.zha.tingbei.manager.ImageManager;
import com.jxnu.zha.tingbei.model.BangList;

import java.util.List;

import butterknife.BindView;

/**
 * Created by DaiQing.Zha on 2017/4/8 0008.
 */
public class BangDetailActivity extends AbstractActivity{

    @BindView(R.id.img_topBg)
    ImageView mImgTopBg;
    @BindView(R.id.lst_bangList)
    ListView mLstBangList;
    final String TAG = "BangDetailActivity";
    BangDetailAdapter bangDetailAdapter;
    List<BangList.ObjEntity.ListMusicEntity> listMusicEntity;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void init() {
        bangDetailAdapter = new BangDetailAdapter(this,listMusicEntity);
        mLstBangList.setAdapter(bangDetailAdapter);
        Bundle bundle = getIntent().getBundleExtra("bundle");
        if (bundle != null){
            BangList.ObjEntity objEntity = (BangList.ObjEntity) bundle.getSerializable("bang");
            if (objEntity != null){
                ImageManager.getInstance().displayImage(objEntity.getPicPath(), mImgTopBg,
                        ImageManager.getUserImageOptions());
                listMusicEntity = objEntity.getListMusic();
                bangDetailAdapter.notifyDataSetChanged();
            }
        }
    }
}
