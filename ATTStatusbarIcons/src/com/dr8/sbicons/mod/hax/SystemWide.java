package com.dr8.sbicons.mod.hax;

import com.dr8.sbicons.mod.ZipStuff;

import android.annotation.SuppressLint;
import android.content.res.XResources;
import android.graphics.Bitmap;
import android.graphics.PorterDuff;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import de.robv.android.xposed.IXposedHookZygoteInit.StartupParam;
import de.robv.android.xposed.XSharedPreferences;
import de.robv.android.xposed.XposedBridge;

public class SystemWide {

	@SuppressLint("SdCardPath")
	public static void initHandleZygote(StartupParam startupParam, final XSharedPreferences paramPrefs) {


		String path = "/data/data/com.dr8.sbicons/xsbm/";

		String[] fwicons = {
				"stat_notify_car_mode",
				"stat_sys_adb",
				"stat_sys_data_usb",
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
				"stat_sys_download_anim5",
				"text_select_handle_left",
				"text_select_handle_middle",
				"text_select_handle_right",
		};

		String[] phicons = {
				"stat_sys_phone_call",
				"stat_sys_headset",
				"stat_sys_speakerphone",
				"stat_notify_call_mute",
		};

		String gpson = "stat_sys_gps_on";

		try {

			if (paramPrefs.getBoolean("framework", false)) {
				for (int i = 0; i < fwicons.length; i++) {
					String fimg = "framework/" + fwicons[i] + ".png";
					//				XposedBridge.log("XSBM: Trying to get fw image: " + fimg);
					final Bitmap fb = ZipStuff.getBitmap(path, fimg);
					final BitmapDrawable fd = new BitmapDrawable(null, fb);
					if (fb != null) {
						XResources.setSystemWideReplacement("android", "drawable", fwicons[i], new XResources.DrawableLoader() {
							@Override
							public Drawable newDrawable(XResources res, int id) throws Throwable {
								if (paramPrefs.getBoolean("fwcolor_enabled", false)) {
									fd.setColorFilter(paramPrefs.getInt("fwcolor", 0xffffffff), PorterDuff.Mode.MULTIPLY);
									return fd;
								} else {
									return new BitmapDrawable(null, fb);
								}
							}
						});
					}
				}

				for (int i = 0; i < phicons.length; i++) {
					String pimg = "phone/" + phicons[i] + ".png";
					final Bitmap b = ZipStuff.getBitmap(path, pimg);
					final BitmapDrawable bd = new BitmapDrawable(null, b);
					if (b != null) {
						XResources.setSystemWideReplacement("android", "drawable", phicons[i], new XResources.DrawableLoader() {
							@Override
							public Drawable newDrawable(XResources res, int id) throws Throwable {
								if (paramPrefs.getBoolean("misccolor_enabled", false)) {
									bd.setColorFilter(paramPrefs.getInt("misccolor", 0xffffffff), PorterDuff.Mode.MULTIPLY);
									return bd;
								} else {
									return new BitmapDrawable(null, b);
								}
							}
						});
					} 
				}
			}
			
			if (paramPrefs.getBoolean("gps", false)) {
				String fimg = "gps/" + gpson + ".png";
				//			XposedBridge.log("XSBM: Trying to load gps fw image: " + fimg);
				final Bitmap fb = ZipStuff.getBitmap(path, fimg);
				final BitmapDrawable fd = new BitmapDrawable(null, fb);
				if (fb != null) {
					XResources.setSystemWideReplacement("android", "drawable", "stat_sys_gps_on", new XResources.DrawableLoader() {
						@Override
						public Drawable newDrawable(XResources res, int id) throws Throwable {
							if (paramPrefs.getBoolean("gpscolor_enabled", false)) {
								fd.setColorFilter(paramPrefs.getInt("gpscolor", 0xffffffff), PorterDuff.Mode.MULTIPLY);
								return fd;
							} else {
								return new BitmapDrawable(null, fb);
							}
						}
					});
				}
			}
		} catch (Throwable t) { XposedBridge.log(t); }
	}
}
