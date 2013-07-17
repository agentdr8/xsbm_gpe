package com.dr8.sbicons.mod.hax;

import android.content.res.XModuleResources;

import com.dr8.sbicons.R;

import de.robv.android.xposed.XSharedPreferences;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.callbacks.XC_InitPackageResources;

public class GPS {

	public static void initPackageResources(XSharedPreferences paramPrefs, XModuleResources modRes, XC_InitPackageResources.InitPackageResourcesParam resParam) {
		try {
			String targetpkg = "com.android.systemui";
			resParam.res.setReplacement(targetpkg, "drawable", "stat_sys_gps_on", modRes.fwd(R.drawable.stat_sys_gps_on));
			resParam.res.setReplacement(targetpkg, "drawable", "stat_sys_gps_acquiring", modRes.fwd(R.drawable.stat_sys_gps_acquiring));
		} catch (Throwable t) { XposedBridge.log(t); }
	}
}
