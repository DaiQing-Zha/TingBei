package com.jxnu.zha.tingbei.manager;

import com.jxnu.zha.tingbei.R;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

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

	private static DisplayImageOptions backPictureOptions;

	public static DisplayImageOptions getBackPictureOptions() {
		if (backPictureOptions == null) {
			backPictureOptions = new DisplayImageOptions.Builder()
					.showImageForEmptyUri(R.mipmap.ic_default_unload)
					.showImageOnFail(R.mipmap.ic_default_unload)
					.showImageOnLoading(R.mipmap.ic_default_unload)
					.cacheInMemory(true) // 缓存内存
					.cacheOnDisc(true)// 缓存文件
					.build();
		}
		return backPictureOptions;
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

	private static DisplayImageOptions radioIconOptions;
	public static DisplayImageOptions getRadioIconOptions() {
		if (radioIconOptions == null) {
			radioIconOptions = new DisplayImageOptions.Builder()
					.showImageForEmptyUri(R.mipmap.ic_radio_icon)
					.showImageOnFail(R.mipmap.ic_radio_icon)
					.cacheInMemory(true) // 缓存内存
					.cacheOnDisc(true)// 缓存文件
					.build();
		}
		return radioIconOptions;
	}

	private static DisplayImageOptions musicIconOptions;
	public static DisplayImageOptions getMusicIconOptions() {
		if (musicIconOptions == null) {
			musicIconOptions = new DisplayImageOptions.Builder()
					.showImageForEmptyUri(R.mipmap.ic_music_icon)
					.showImageOnFail(R.mipmap.ic_music_icon)
					.cacheInMemory(true) // 缓存内存
					.cacheOnDisc(true)// 缓存文件
					.build();
		}
		return musicIconOptions;
	}

	private static DisplayImageOptions userIconOptions;
	public static DisplayImageOptions getUserIconOptions() {
		if (userIconOptions == null) {
			userIconOptions = new DisplayImageOptions.Builder()
					.showImageForEmptyUri(R.mipmap.ic_defalut_signer)
					.showImageOnFail(R.mipmap.ic_defalut_signer)
					.cacheInMemory(true) // 缓存内存
					.cacheOnDisc(true)// 缓存文件
					.build();
		}
		return userIconOptions;
	}

	private static DisplayImageOptions musicBgIconOptions;
	public static DisplayImageOptions getMusicBgIconOptions() {
		if (musicBgIconOptions == null) {
			musicBgIconOptions = new DisplayImageOptions.Builder()
					.showImageForEmptyUri(R.mipmap.ic_default_music_bg)
					.showImageOnFail(R.mipmap.ic_default_music_bg)
					.cacheInMemory(true) // 缓存内存
					.cacheOnDisc(true)// 缓存文件
					.build();
		}
		return musicBgIconOptions;
	}

}