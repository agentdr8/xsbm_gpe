package com.dr8.sbicons.mod.hax;

import android.content.res.XModuleResources;
import android.content.res.XResources;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Environment;

import com.dr8.sbicons.mod.ZipStuff;

import de.robv.android.xposed.XSharedPreferences;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.callbacks.XC_InitPackageResources;

public class ToTheLeft {

	public static void initPackageResources(XSharedPreferences paramPrefs, XModuleResources modRes, XC_InitPackageResources.InitPackageResourcesParam resParam) {
		try {
			String path = Environment.getExternalStorageDirectory() + "/xsbm/test.zip";
			
			String[] phonearray = {
					"stat_notify_call_mute",
					"stat_notify_voicemail",
					"stat_sys_speakerphone"
			};
			if (resParam.packageName.equals("com.android.phone")) {
				for (int i = 0; i < phonearray.length; i++) {
					String pimg = "phone/" + phonearray[i] + ".png";
					final Bitmap b = ZipStuff.getBitmapFromZip(path, pimg);
					if (b != null) {
						resParam.res.setReplacement("com.android.phone", "drawable", phonearray[i], new XResources.DrawableLoader() {
							@Override
							public Drawable newDrawable(XResources res, int id) throws Throwable {
								return new BitmapDrawable(null, b);
							}
						});
					}
					
				
				}
			}
			
			String[] miscarray = {
					"ic_notify_clear",
					"ic_notify_notifications",
					"ic_notify_quicksettings",
					"icon_btn_voice_dark",
					"icon_btn_menu_dark",
					"stat_meta_caps_lock",
					"stat_meta_caps_on",
					"stat_meta_fn_lock",
					"stat_meta_fn_on",
					"stat_notify_alarm",
					"stat_notify_image",
					"stat_notify_image_error",
					"stat_notify_more",
					"stat_sys_ringer_silent",
					"stat_sys_ringer_vibrate",
					"stat_sys_headphones",
					"stat_sys_headphone_no_mic",
					"stat_sys_hac",
					"stat_sys_no_sim",
					"stat_sys_sim_lock",
					"stat_sys_tty",
					"stat_sys_tty_mode",
					"stat_sys_sync",
					"stat_sys_sync_anim0",
					"stat_sys_sync_error"
			};
			
			if (resParam.packageName.equals("com.android.systemui")) {
				for (int i = 0; i < miscarray.length; i++) {
					String mimg = "misc/" + miscarray[i] + ".png";
					final Bitmap b = ZipStuff.getBitmapFromZip(path, mimg);
					if (b != null) {
						resParam.res.setReplacement("com.android.systemui", "drawable", miscarray[i], new XResources.DrawableLoader() {
							@Override
							public Drawable newDrawable(XResources res, int id) throws Throwable {
								return new BitmapDrawable(null, b);
							}
						});
					}
					
				
				}
			}
		} catch (Throwable t) { XposedBridge.log(t); }
	}
}
