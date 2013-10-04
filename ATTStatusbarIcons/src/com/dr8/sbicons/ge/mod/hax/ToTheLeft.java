package com.dr8.sbicons.ge.mod.hax;

import android.content.res.XModuleResources;
import android.content.res.XResources;
import android.graphics.Bitmap;
import android.graphics.PorterDuff;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import com.dr8.sbicons.ge.mod.ZipStuff;

import de.robv.android.xposed.XSharedPreferences;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.callbacks.XC_InitPackageResources;

public class ToTheLeft {

	public static void initPackageResources(final XSharedPreferences paramPrefs, XModuleResources modRes, XC_InitPackageResources.InitPackageResourcesParam resParam) {
		try {
			
			String path = "/data/data/com.dr8.sbicons.ge/xsbm/";
						
			String[] miscarray = {
					"stat_sys_alarm",
					"stat_notify_image",
					"stat_notify_image_error",
					"stat_notify_more",
					"stat_sys_ringer_silent",
					"stat_sys_ringer_vibrate",
					"stat_sys_no_sim",
					"stat_sys_tty_mode",
					"stat_sys_sync",
					"stat_sys_sync_error"
			};
			
			String[] phicons = {
					"stat_sys_phone_call"
			};

			if (resParam.packageName.equals("com.android.systemui")) {
						
				for (int i = 0; i < miscarray.length; i++) {
					String mimg = "misc/" + miscarray[i] + ".png";
					final Bitmap b = ZipStuff.getBitmap(path, mimg);
					final BitmapDrawable bd = new BitmapDrawable(null, b);
					if (b != null) {
						resParam.res.setReplacement("com.android.systemui", "drawable", miscarray[i], new XResources.DrawableLoader() {
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
					} else {
						return;
					}
				}
			}
			
			if (resParam.packageName.equals("com.android.phone")) {
				for (int i = 0; i < phicons.length; i++) {
					String pimg = "phone/" + phicons[i] + ".png";
					final Bitmap b = ZipStuff.getBitmap(path, pimg);
					final BitmapDrawable bd = new BitmapDrawable(null, b);
					if (b != null) {
						resParam.res.setReplacement("com.android.phone", "drawable", phicons[i], new XResources.DrawableLoader() {
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
					} else { 
						return;
					}
				}
			}
		} catch (Throwable t) { XposedBridge.log(t); }
	}
}
