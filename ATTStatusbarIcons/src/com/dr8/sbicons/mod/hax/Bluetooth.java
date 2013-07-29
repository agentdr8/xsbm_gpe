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

public class Bluetooth {

	public static void initPackageResources(XSharedPreferences paramPrefs, XModuleResources modRes, XC_InitPackageResources.InitPackageResourcesParam resParam) {
		try {
			String targetpkg = "com.android.systemui";
			String path = "/data/data/com.dr8.sbicons/xsbm/";
			String bt = "bt/stat_sys_data_bluetooth.png";
			String btcon = "bt/stat_sys_data_bluetooth_connected.png";
			final Bitmap b = ZipStuff.getBitmap(path, bt);
			final Bitmap c = ZipStuff.getBitmap(path, btcon);
			if (b != null) {
				resParam.res.setReplacement(targetpkg , "drawable", "stat_sys_data_bluetooth", new XResources.DrawableLoader() {
					@Override
					public Drawable newDrawable(XResources res, int id) throws Throwable {
						return new BitmapDrawable(null, b);
					}
				});
			}
			if (c != null) {
				resParam.res.setReplacement(targetpkg, "drawable", "stat_sys_data_bluetooth_connected", new XResources.DrawableLoader() {
					@Override
					public Drawable newDrawable(XResources res, int id) throws Throwable {
						return new BitmapDrawable(null, c);
					}
				});
			}
		} catch (Throwable t) { XposedBridge.log(t); }
	}
}
