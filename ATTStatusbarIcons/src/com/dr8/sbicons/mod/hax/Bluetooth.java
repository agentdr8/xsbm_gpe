package com.dr8.sbicons.mod.hax;

import android.content.res.XModuleResources;

import com.dr8.sbicons.R;

import de.robv.android.xposed.XSharedPreferences;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.callbacks.XC_InitPackageResources;

public class Bluetooth {

	public static void initPackageResources(XSharedPreferences paramPrefs, XModuleResources modRes, XC_InitPackageResources.InitPackageResourcesParam resParam) {
		try {
			String targetpkg = "com.android.systemui";
			resParam.res.setReplacement(targetpkg , "drawable", "stat_sys_data_bluetooth", modRes.fwd(R.drawable.stat_sys_data_bluetooth));
			resParam.res.setReplacement(targetpkg, "drawable", "stat_sys_data_bluetooth_connected", modRes.fwd(R.drawable.stat_sys_data_bluetooth_connected));
		} catch (Throwable t) { XposedBridge.log(t); }
	}
}
