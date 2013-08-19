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

public class MobileDataGE {

	public static void initPackageResources(final XSharedPreferences paramPrefs, XModuleResources modRes, XC_InitPackageResources.InitPackageResourcesParam resParam) {
		try {
			String targetpkg = "com.android.systemui";
			String path = "/data/data/com.dr8.sbicons/xsbm/";

			String[] mobicons = {
					"stat_sys_data_connected_g",
					"stat_sys_data_connected_e",
					"stat_sys_data_connected_3g",
					"stat_sys_data_connected_h",
					"stat_sys_data_connected_4g",
					"stat_sys_data_connected_lte",
					"stat_sys_data_connected_roam"
			};

			for (int i = 0; i < mobicons.length; i++) {
				String simg = "mobile/" + mobicons[i] + ".png";
				final Bitmap s = ZipStuff.getBitmap(path, simg);
				if (s != null) {
					resParam.res.setReplacement(targetpkg, "drawable", mobicons[i], new XResources.DrawableLoader() {
						@Override
						public Drawable newDrawable(XResources res, int id) throws Throwable {
							if (paramPrefs.getBoolean("mobilecolor_enabled", false)) {
								BitmapDrawable sd = new BitmapDrawable(null, s);
								sd.setColorFilter(paramPrefs.getInt("mobilecolor", 0xffffffff), PorterDuff.Mode.MULTIPLY);
								return sd;
							} else {
								return new BitmapDrawable(null, s);
							}
						}
					});
				} else {
					return;
				}
			}
		} catch (Throwable t) { XposedBridge.log(t); }
	}
}
