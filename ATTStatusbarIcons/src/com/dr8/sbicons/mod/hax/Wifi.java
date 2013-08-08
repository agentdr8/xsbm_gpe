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

public class Wifi {

	public static void initPackageResources(final XSharedPreferences paramPrefs, XModuleResources modRes, XC_InitPackageResources.InitPackageResourcesParam resParam) {
		try {
			String targetpkg = "com.android.systemui";
			String path = "/data/data/com.dr8.sbicons/xsbm/";
		
			String[] wifiarray = {
					"stat_sys_wifi_signal_0.png",
					"stat_sys_wifi_signal_1.png",
					"stat_sys_wifi_signal_2.png",
					"stat_sys_wifi_signal_3.png",
					"stat_sys_wifi_signal_4.png" };
			
			for (int i = 0; i < wifiarray.length; i++) {
				String wimg = "wifi/" + wifiarray[i];
				final Bitmap w = ZipStuff.getBitmap(path, wimg);
				if (w != null) {
					resParam.res.setReplacement(targetpkg, "drawable", "stat_sys_wifi_signal_" + i, new XResources.DrawableLoader() {
						@Override
						public Drawable newDrawable(XResources res, int id) throws Throwable {
							if (paramPrefs.getBoolean("wificolor_enabled", false)) {
								BitmapDrawable wd = new BitmapDrawable(null, w);
								wd.setColorFilter(paramPrefs.getInt("wificolor", 0xffffffff), PorterDuff.Mode.MULTIPLY);
								return wd;
							} else {
								return new BitmapDrawable(null, w);
							}
						}
					});
				}
				
			}
		} catch (Throwable t) { XposedBridge.log(t); }
	}
}
