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

public class Bluetooth {

	public static void initPackageResources(final XSharedPreferences paramPrefs, XModuleResources modRes, XC_InitPackageResources.InitPackageResourcesParam resParam) {
		try {
			String targetpkg = "com.android.systemui";
			String path = "/data/data/com.dr8.sbicons.ge/xsbm/";
			String bt = "bt/stat_sys_data_bluetooth.png";
			String btcon = "bt/stat_sys_data_bluetooth_connected.png";
			final Bitmap b = ZipStuff.getBitmap(path, bt);
			final Bitmap c = ZipStuff.getBitmap(path, btcon);
			final BitmapDrawable bd = new BitmapDrawable(null, b);
			final BitmapDrawable bc = new BitmapDrawable(null, c);

			if (b != null) {
				resParam.res.setReplacement(targetpkg , "drawable", "stat_sys_data_bluetooth", new XResources.DrawableLoader() {
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
			if (c != null) {
				resParam.res.setReplacement(targetpkg, "drawable", "stat_sys_data_bluetooth_connected", new XResources.DrawableLoader() {
					@Override
					public Drawable newDrawable(XResources res, int id) throws Throwable {
						if (paramPrefs.getBoolean("misccolor_enabled", false)) {
							bc.setColorFilter(paramPrefs.getInt("misccolor", 0xffffffff), PorterDuff.Mode.MULTIPLY);
							return bc;
						} else {
							return new BitmapDrawable(null, c);
						}
					}
				});
			}
		} catch (Throwable t) { XposedBridge.log(t); }
	}
}
