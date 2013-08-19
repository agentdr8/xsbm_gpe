package com.dr8.sbicons.mod.hax;

import android.content.res.XModuleResources;

import com.dr8.sbicons.R;

import de.robv.android.xposed.XSharedPreferences;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.callbacks.XC_InitPackageResources;

public class TpNav {

	public static void initPackageResources(XSharedPreferences paramPrefs, XModuleResources modRes, XC_InitPackageResources.InitPackageResourcesParam resParam) {
		try {
			resParam.res.setReplacement("com.htc.launcher", "drawable", "home_nav_bg", modRes.fwd(R.drawable.home_nav_bg));
			resParam.res.setReplacement("com.htc.launcher", "drawable", "automotive_common_app_bkg_top", modRes.fwd(R.drawable.automotive_common_app_bkg_top));
		} catch (Throwable t) { XposedBridge.log(t); }
	}
}
