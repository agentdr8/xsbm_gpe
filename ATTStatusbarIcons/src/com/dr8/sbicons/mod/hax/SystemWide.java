package com.dr8.sbicons.mod.hax;

import com.dr8.sbicons.mod.ZipStuff;

import android.annotation.SuppressLint;
import android.content.res.XResources;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import de.robv.android.xposed.IXposedHookZygoteInit.StartupParam;
import de.robv.android.xposed.XSharedPreferences;
import de.robv.android.xposed.XposedBridge;

public class SystemWide {
	
	@SuppressLint("SdCardPath")
	public static void initHandleZygote(StartupParam startupParam, XSharedPreferences prefs) {
		
	
		String path = "/data/data/com.dr8.sbicons/xsbm/";
		
		String[] fwicons = {
				"stat_notify_car_mode",
				"stat_sys_adb",
				"stat_sys_upload_anim0",
				"stat_sys_upload_anim1",
				"stat_sys_upload_anim2",
				"stat_sys_upload_anim3",
				"stat_sys_upload_anim4",
				"stat_sys_upload_anim5",
				"stat_sys_download_anim0",
				"stat_sys_download_anim1",
				"stat_sys_download_anim2",
				"stat_sys_download_anim3",
				"stat_sys_download_anim4",
				"stat_sys_download_anim5"
		};
		
		String[] htcfw = {
				"text_anchor_bar_left",
				"text_anchor_bar_center",
				"text_anchor_bar_right",
				"icon_btn_copy_dark",
				"icon_btn_cut_dark",
				"icon_btn_paste_dark",
				"icon_btn_search_web_dark",
				"icon_btn_selected_all_dark",
				"stat_sys_hdmi_on"
		};
		
		String gpson = "stat_sys_gps_on";
		
		try {
			for (int i = 0; i < fwicons.length; i++) {
				String fimg = "framework/" + fwicons[i] + ".png";
				final Bitmap fb = ZipStuff.getBitmap(path, fimg);
				if (fb != null) {
					XResources.setSystemWideReplacement("android", "drawable", fwicons[i], new XResources.DrawableLoader() {
						@Override
						public Drawable newDrawable(XResources res, int id) throws Throwable {
							return new BitmapDrawable(null, fb);
						}
					});
				}
			}
			for (int i = 0; i < htcfw.length; i++) {
				String himg = "framework/" + htcfw[i] + ".png";
				final Bitmap hb = ZipStuff.getBitmap(path, himg);
				if (hb != null) {
					XResources.setSystemWideReplacement("com.htc.framework", "drawable", htcfw[i], new XResources.DrawableLoader() {
						@Override
						public Drawable newDrawable(XResources res, int id) throws Throwable {
							return new BitmapDrawable(null, hb);
						}
					});
				}
			}
			String fimg = "gps/" + gpson + ".png";
			final Bitmap fb = ZipStuff.getBitmap(path, fimg);
			if (fb != null) {
				XResources.setSystemWideReplacement("android", "drawable", "stat_sys_gps_on", new XResources.DrawableLoader() {
					@Override
					public Drawable newDrawable(XResources res, int id) throws Throwable {
						return new BitmapDrawable(null, fb);
					}
				});
			}
		} catch (Throwable t) { XposedBridge.log(t); }
		
	}
}
