package com.dr8.sbicons.mod.hax;

import android.content.res.XModuleResources;
import android.content.res.XResources;
import android.graphics.Bitmap;
import android.graphics.PorterDuff;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import com.dr8.sbicons.mod.ZipStuff;

import de.robv.android.xposed.XSharedPreferences;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.callbacks.XC_InitPackageResources;

public class SignalBars {

	public static void initPackageResources(final XSharedPreferences paramPrefs, XModuleResources modRes, XC_InitPackageResources.InitPackageResourcesParam resParam) {
		try {
			String targetpkg = "com.android.systemui";
			String path = "/data/data/com.dr8.sbicons/xsbm/";
			
			String[] signalarray = {
					"stat_sys_5signal_0.png",
					"stat_sys_5signal_1.png",
					"stat_sys_5signal_2.png",
					"stat_sys_5signal_3.png",
					"stat_sys_5signal_4.png",
					"stat_sys_5signal_5.png",
					"stat_sys_5signal_null.png",
					"stat_sys_signal_flightmode.png"
					};
			
			String[] roamarray = {
					"stat_sys_r_5signal_0.png",
					"stat_sys_r_5signal_1.png",
					"stat_sys_r_5signal_2.png",
					"stat_sys_r_5signal_3.png",
					"stat_sys_r_5signal_4.png",
					"stat_sys_r_5signal_5.png"
					};
			
			for (int i = 0; i < signalarray.length; i++) {
				String simg = "signal/" + signalarray[i];
				final Bitmap s = ZipStuff.getBitmap(path, simg);
				if (s != null) {
					if (i == 6) {
						resParam.res.setReplacement(targetpkg, "drawable", "stat_sys_5signal_null", new XResources.DrawableLoader() {
							@Override
							public Drawable newDrawable(XResources res, int id) throws Throwable {
								if (paramPrefs.getBoolean("signalcolor_enabled", false)) {
									BitmapDrawable sd = new BitmapDrawable(null, s);
									sd.setColorFilter(paramPrefs.getInt("signalcolor", 0xffffffff), PorterDuff.Mode.MULTIPLY);
									return sd;
								} else {
									return new BitmapDrawable(null, s);
								}
							}
						});
					} else if (i == 7) {
						resParam.res.setReplacement(targetpkg, "drawable", "stat_sys_signal_flightmode", new XResources.DrawableLoader() {
							@Override
							public Drawable newDrawable(XResources res, int id) throws Throwable {
								if (paramPrefs.getBoolean("signalcolor_enabled", false)) {
									BitmapDrawable sd = new BitmapDrawable(null, s);
									sd.setColorFilter(paramPrefs.getInt("signalcolor", 0xffffffff), PorterDuff.Mode.MULTIPLY);
									return sd;
								} else {
									return new BitmapDrawable(null, s);
								}
							}
						});
					} else {
						resParam.res.setReplacement(targetpkg, "drawable", "stat_sys_5signal_" + i, new XResources.DrawableLoader() {
							@Override
							public Drawable newDrawable(XResources res, int id) throws Throwable {
								if (paramPrefs.getBoolean("signalcolor_enabled", false)) {
									BitmapDrawable sd = new BitmapDrawable(null, s);
									sd.setColorFilter(paramPrefs.getInt("signalcolor", 0xffffffff), PorterDuff.Mode.MULTIPLY);
									return sd;
								} else {
									return new BitmapDrawable(null, s);
								}
							}
						});
					}
				}
			}
			
			for (int i = 0; i < roamarray.length; i++) {
				String rimg = "signal/roam/" + roamarray[i];
				final Bitmap r = ZipStuff.getBitmap(path, rimg);
				if (r != null) {
						resParam.res.setReplacement(targetpkg, "drawable", "stat_sys_r_5signal_" + i, new XResources.DrawableLoader() {
						@Override
						public Drawable newDrawable(XResources res, int id) throws Throwable {
							if (paramPrefs.getBoolean("signalcolor_enabled", false)) {
								BitmapDrawable rd = new BitmapDrawable(null, r);
								rd.setColorFilter(paramPrefs.getInt("signalcolor", 0xffffffff), PorterDuff.Mode.MULTIPLY);
								return rd;
							} else {
								return new BitmapDrawable(null, r);
							}
						}
					});
				}
			}
		} catch (Throwable t) { XposedBridge.log(t); }
	}
}
