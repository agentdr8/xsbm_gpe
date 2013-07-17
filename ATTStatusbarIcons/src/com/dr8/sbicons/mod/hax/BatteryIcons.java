package com.dr8.sbicons.mod.hax;

import android.content.res.XModuleResources;

import com.dr8.sbicons.R;

import de.robv.android.xposed.XSharedPreferences;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.callbacks.XC_InitPackageResources;

public class BatteryIcons {

	public static void initPackageResources(XSharedPreferences paramPrefs, XModuleResources modRes, XC_InitPackageResources.InitPackageResourcesParam resParam) {
		try {
			String targetpkg = "com.android.systemui";
			if (paramPrefs.getString("battstyle", "percent").equals("percent")) {
				try {
					resParam.res.setReplacement(targetpkg, "drawable", "stat_sys_battery", modRes.fwd(R.drawable.stat_sys_battery));
					resParam.res.setReplacement(targetpkg, "drawable", "stat_sys_battery_charge", modRes.fwd(R.drawable.stat_sys_battery_charge));
				} catch (Throwable t) { XposedBridge.log(t); }
			} else if (paramPrefs.getString("battstyle", "percent").equals("circle")) {
				try {
					resParam.res.setReplacement(targetpkg, "drawable", "stat_sys_battery", modRes.fwd(R.drawable.theme2_stat_sys_battery));
					resParam.res.setReplacement(targetpkg, "drawable", "stat_sys_battery_charge", modRes.fwd(R.drawable.theme2_stat_sys_battery_charge));
				} catch (Throwable t) { XposedBridge.log(t); }
			}
		} catch (Throwable t) { XposedBridge.log(t); }
	}
}
