package com.dr8.sbicons.ge.mod.hax;

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
					"stat_sys_signal_0_fully",
					"stat_sys_signal_0",
					"stat_sys_signal_1_fully",
					"stat_sys_signal_1",
					"stat_sys_signal_2_fully",
					"stat_sys_signal_2",
					"stat_sys_signal_3_fully",
					"stat_sys_signal_3",
					"stat_sys_signal_4_fully",
					"stat_sys_signal_4",
					"stat_sys_signal_inout",
					"stat_sys_signal_in",
					"stat_sys_signal_out",
					"stat_sys_signal_null",
					"stat_sys_signal_flightmode"
			};

			for (int i = 0; i < signalarray.length; i++) {
				String simg = "signal/" + signalarray[i] + ".png";
				final Bitmap s = ZipStuff.getBitmap(path, simg);
				final BitmapDrawable sd = new BitmapDrawable(null, s);
				if (s != null) {
					resParam.res.setReplacement(targetpkg, "drawable", signalarray[i], new XResources.DrawableLoader() {
						@Override
						public Drawable newDrawable(XResources res, int id) throws Throwable {
							if (paramPrefs.getBoolean("signalcolor_enabled", false)) {
								sd.setColorFilter(paramPrefs.getInt("signalcolor", 0xffffffff), PorterDuff.Mode.MULTIPLY);
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
