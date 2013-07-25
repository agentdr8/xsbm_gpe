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

public class GPS {

	public static void initPackageResources(XSharedPreferences paramPrefs, XModuleResources modRes, XC_InitPackageResources.InitPackageResourcesParam resParam) {
		try {
			String targetpkg = "com.android.systemui";
			String iconpack = paramPrefs.getString("iconset", null);
			String path = Environment.getExternalStorageDirectory() + "/xsbm/" + iconpack;
			String gon = "gps/stat_sys_gps_on.png";
			String gacq = "gps/stat_sys_gps_acquiring.png";
			final Bitmap g = ZipStuff.getBitmapFromZip(path, gon);
			final Bitmap h = ZipStuff.getBitmapFromZip(path, gacq);
			if (g != null) {
				resParam.res.setReplacement(targetpkg , "drawable", "stat_sys_gps_on", new XResources.DrawableLoader() {
					@Override
					public Drawable newDrawable(XResources res, int id) throws Throwable {
						return new BitmapDrawable(null, g);
					}
				});
			}
			if (h != null) {
				resParam.res.setReplacement(targetpkg, "drawable", "stat_sys_gps_acquiring", new XResources.DrawableLoader() {
					@Override
					public Drawable newDrawable(XResources res, int id) throws Throwable {
						return new BitmapDrawable(null, h);
					}
				});
			}
		} catch (Throwable t) { XposedBridge.log(t); }
	}
}
