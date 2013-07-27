package com.dr8.sbicons.mod.hax;

import android.content.res.XModuleResources;
import android.content.res.XResources;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import com.dr8.sbicons.mod.ZipStuff;

import de.robv.android.xposed.XSharedPreferences;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.callbacks.XC_InitPackageResources;

public class SignalBars {

	public static void initPackageResources(XSharedPreferences paramPrefs, XModuleResources modRes, XC_InitPackageResources.InitPackageResourcesParam resParam) {
		try {
			String targetpkg = "com.android.systemui";
			String iconpack = "iconpack.zip";
			String path = "/data/data/com.dr8.sbicons" + "/xsbm/" + iconpack;
			
			String[] signalarray = {
					"stat_sys_5signal_0.png",
					"stat_sys_5signal_1.png",
					"stat_sys_5signal_2.png",
					"stat_sys_5signal_3.png",
					"stat_sys_5signal_4.png",
					"stat_sys_5signal_5.png",
					"stat_sys_5signal_null.png",
					"stat_sys_signal_flightmode"
					};
			
			for (int i = 0; i < signalarray.length; i++) {
				String simg = "signal/" + signalarray[i];
				final Bitmap s = ZipStuff.getBitmapFromZip(path, simg);
				if (s != null) {
					if (i == 6) {
						resParam.res.setReplacement(targetpkg, "drawable", "stat_sys_5signal_null", new XResources.DrawableLoader() {
							@Override
							public Drawable newDrawable(XResources res, int id) throws Throwable {
								return new BitmapDrawable(null, s);
							}
						});
					} else if (i == 7) {
						resParam.res.setReplacement(targetpkg, "drawable", "stat_sys_signal_flightmode", new XResources.DrawableLoader() {
							@Override
							public Drawable newDrawable(XResources res, int id) throws Throwable {
								return new BitmapDrawable(null, s);
							}
						});
					} else {
						resParam.res.setReplacement(targetpkg, "drawable", "stat_sys_5signal_" + i, new XResources.DrawableLoader() {
							@Override
							public Drawable newDrawable(XResources res, int id) throws Throwable {
								return new BitmapDrawable(null, s);
							}
						});
					}
				}
			}
		} catch (Throwable t) { XposedBridge.log(t); }
	}
}
