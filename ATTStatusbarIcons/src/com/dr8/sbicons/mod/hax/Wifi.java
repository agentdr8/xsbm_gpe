package com.dr8.sbicons.mod.hax;

import android.content.res.XModuleResources;

import com.dr8.sbicons.R;

import de.robv.android.xposed.XSharedPreferences;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.callbacks.XC_InitPackageResources;

public class Wifi {

	public static void initPackageResources(XSharedPreferences paramPrefs, XModuleResources modRes, XC_InitPackageResources.InitPackageResourcesParam resParam) {
		try {
			String targetpkg = "com.android.systemui";
			if (paramPrefs.getString("wifistyle", "cyan").equals("cyan")) {
				try {
					resParam.res.setReplacement(targetpkg, "drawable", "stat_sys_wifi_signal_0", modRes.fwd(R.drawable.stat_sys_wifi_signal_0));
					resParam.res.setReplacement(targetpkg, "drawable", "stat_sys_wifi_signal_1", modRes.fwd(R.drawable.stat_sys_wifi_signal_1));
					resParam.res.setReplacement(targetpkg, "drawable", "stat_sys_wifi_signal_2", modRes.fwd(R.drawable.stat_sys_wifi_signal_2));
					resParam.res.setReplacement(targetpkg, "drawable", "stat_sys_wifi_signal_3", modRes.fwd(R.drawable.stat_sys_wifi_signal_3));
					resParam.res.setReplacement(targetpkg, "drawable", "stat_sys_wifi_signal_4", modRes.fwd(R.drawable.stat_sys_wifi_signal_4));
				} catch (Throwable t) { XposedBridge.log(t); }
			} else if (paramPrefs.getString("wifistyle", "cyan").equals("rainbow")) {
				try {
					resParam.res.setReplacement(targetpkg, "drawable", "stat_sys_wifi_signal_0", modRes.fwd(R.drawable.theme1_stat_sys_wifi_signal_0));
					resParam.res.setReplacement(targetpkg, "drawable", "stat_sys_wifi_signal_1", modRes.fwd(R.drawable.theme1_stat_sys_wifi_signal_1));
					resParam.res.setReplacement(targetpkg, "drawable", "stat_sys_wifi_signal_2", modRes.fwd(R.drawable.theme1_stat_sys_wifi_signal_2));
					resParam.res.setReplacement(targetpkg, "drawable", "stat_sys_wifi_signal_3", modRes.fwd(R.drawable.theme1_stat_sys_wifi_signal_3));
					resParam.res.setReplacement(targetpkg, "drawable", "stat_sys_wifi_signal_4", modRes.fwd(R.drawable.theme1_stat_sys_wifi_signal_4));
				} catch (Throwable t) { XposedBridge.log(t); }
			}
		} catch (Throwable t) { XposedBridge.log(t); }
	}
}
