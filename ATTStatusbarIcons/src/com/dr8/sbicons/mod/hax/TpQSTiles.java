package com.dr8.sbicons.mod.hax;

import android.content.res.XModuleResources;
import de.robv.android.xposed.XSharedPreferences;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.callbacks.XC_InitPackageResources;

public class TpQSTiles {

	public static void initPackageResources(final XSharedPreferences paramPrefs, XModuleResources modRes, XC_InitPackageResources.InitPackageResourcesParam resParam) {
		try {
			resParam.res.setReplacement("com.htc.launcher", "drawable", "quick_settings_tile_background", modRes.fwd(paramPrefs.getInt("qstile_bg_color", 0xC0000000)));
		} catch (Throwable t) { XposedBridge.log(t); }
	}
}
