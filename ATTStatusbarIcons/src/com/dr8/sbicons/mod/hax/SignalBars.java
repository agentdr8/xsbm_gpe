package com.dr8.sbicons.mod.hax;

import android.content.res.XModuleResources;

import com.dr8.sbicons.R;

import de.robv.android.xposed.XSharedPreferences;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.callbacks.XC_InitPackageResources;

public class SignalBars {

	public static void initPackageResources(XSharedPreferences paramPrefs, XModuleResources modRes, XC_InitPackageResources.InitPackageResourcesParam resParam) {
		try {
			String targetpkg = "com.android.systemui";
			if (paramPrefs.getString("sigstyle", "cyan").equals("cyan")) {
				try { 
					resParam.res.setReplacement(targetpkg, "drawable", "stat_sys_5signal_0", modRes.fwd(R.drawable.stat_sys_5signal_0));
					resParam.res.setReplacement(targetpkg, "drawable", "stat_sys_5signal_1", modRes.fwd(R.drawable.stat_sys_5signal_1));
					resParam.res.setReplacement(targetpkg, "drawable", "stat_sys_5signal_2", modRes.fwd(R.drawable.stat_sys_5signal_2));
					resParam.res.setReplacement(targetpkg, "drawable", "stat_sys_5signal_3", modRes.fwd(R.drawable.stat_sys_5signal_3));
					resParam.res.setReplacement(targetpkg, "drawable", "stat_sys_5signal_4", modRes.fwd(R.drawable.stat_sys_5signal_4));
					resParam.res.setReplacement(targetpkg, "drawable", "stat_sys_5signal_5", modRes.fwd(R.drawable.stat_sys_5signal_5));
					resParam.res.setReplacement(targetpkg, "drawable", "stat_sys_5signal_null", modRes.fwd(R.drawable.stat_sys_5signal_null));
				} catch (Throwable t) { XposedBridge.log(t); }
			} else if (paramPrefs.getString("sigstyle", "cyan").equals("rainbow")) {
				try { 
					resParam.res.setReplacement(targetpkg, "drawable", "stat_sys_5signal_0", modRes.fwd(R.drawable.theme1_stat_sys_5signal_0));
					resParam.res.setReplacement(targetpkg, "drawable", "stat_sys_5signal_1", modRes.fwd(R.drawable.theme1_stat_sys_5signal_1));
					resParam.res.setReplacement(targetpkg, "drawable", "stat_sys_5signal_2", modRes.fwd(R.drawable.theme1_stat_sys_5signal_2));
					resParam.res.setReplacement(targetpkg, "drawable", "stat_sys_5signal_3", modRes.fwd(R.drawable.theme1_stat_sys_5signal_3));
					resParam.res.setReplacement(targetpkg, "drawable", "stat_sys_5signal_4", modRes.fwd(R.drawable.theme1_stat_sys_5signal_4));
					resParam.res.setReplacement(targetpkg, "drawable", "stat_sys_5signal_5", modRes.fwd(R.drawable.theme1_stat_sys_5signal_5));
					resParam.res.setReplacement(targetpkg, "drawable", "stat_sys_5signal_null", modRes.fwd(R.drawable.theme1_stat_sys_5signal_null));
				} catch (Throwable t) { XposedBridge.log(t); }
			}
			try {
				resParam.res.setReplacement(targetpkg, "drawable", "stat_sys_signal_flightmode", modRes.fwd(R.drawable.stat_sys_signal_flightmode));
			} catch (Throwable t) { XposedBridge.log(t); }
		} catch (Throwable t) { XposedBridge.log(t); }
	}
}
