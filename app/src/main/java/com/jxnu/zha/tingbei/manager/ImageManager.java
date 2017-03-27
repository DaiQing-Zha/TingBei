package com.jxnu.zha.tingbei.manager;
import android.graphics.Bitmap;

import com.jxnu.zha.tingbei.R;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;

public class ImageManager {
	/**
	 * 获取图片加载
	 *
	 * @return
	 */
	public static ImageLoader getInstance() {
		return ImageLoader.getInstance();
	}

	/**
	 * 新闻图片缓存设置
	 */

	private static DisplayImageOptions newsHeadOptions;

	public static DisplayImageOptions getNewsHeadOptions() {
		if (newsHeadOptions == null) {
			newsHeadOptions = new DisplayImageOptions.Builder()
					.showImageForEmptyUri(R.mipmap.ic_default_unload)
					.showImageOnFail(R.mipmap.ic_default_unload)
					.showImageOnLoading(R.mipmap.ic_default_unload)
					.cacheInMemory(true) // 缓存内存
					.cacheOnDisc(true)// 缓存文件
					.build();
		}
		return newsHeadOptions;
	}

	private static DisplayImageOptions userImageOptions;
	public static DisplayImageOptions getUserImageOptions() {
		if (userImageOptions == null) {
			userImageOptions = new DisplayImageOptions.Builder()
					.showImageForEmptyUri(R.mipmap.ic_default_portrait)
					.showImageOnFail(R.mipmap.ic_default_portrait)
					.cacheInMemory(true) // 缓存内存
					.cacheOnDisc(true)// 缓存文件
					.build();
		}
		return userImageOptions;
	}

}