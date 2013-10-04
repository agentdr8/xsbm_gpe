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

public class GPS {

	public static void initPackageResources(final XSharedPreferences paramPrefs, XModuleResources modRes, XC_InitPackageResources.InitPackageResourcesParam resParam) {
		try {
			String targetpkg = "com.android.systemui";
			String path = "/data/data/com.dr8.sbicons.ge/xsbm/";
			String gacq = "gps/stat_sys_gps_acquiring.png";
			final Bitmap h = ZipStuff.getBitmap(path, gacq);
			final BitmapDrawable hd = new BitmapDrawable(null, h);
			
			if (h != null) {
//				XposedBridge.log("XSBM: replacing gps_acq in sysui");
				resParam.res.setReplacement(targetpkg, "drawable", "stat_sys_gps_acquiring", new XResources.DrawableLoader() {
					@Override
					public Drawable newDrawable(XResources res, int id) throws Throwable {
						if (paramPrefs.getBoolean("gpscolor_enabled", false)) {
							hd.setColorFilter(paramPrefs.getInt("gpscolor", 0xffffffff), PorterDuff.Mode.MULTIPLY);
							return hd;
						} else {
							return new BitmapDrawable(null, h);
						}
					}
				});
			}
		} catch (Throwable t) { XposedBridge.log(t); }
	}
}
