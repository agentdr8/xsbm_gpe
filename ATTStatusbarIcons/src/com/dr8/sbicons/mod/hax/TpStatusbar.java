package com.dr8.sbicons.mod.hax;

import android.content.res.XModuleResources;

import com.dr8.sbicons.R;

import de.robv.android.xposed.XSharedPreferences;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.callbacks.XC_InitPackageResources;

public class TpStatusbar {

	public static void initPackageResources(XSharedPreferences paramPrefs, XModuleResources modRes, XC_InitPackageResources.InitPackageResourcesParam resParam) {
		try {
			if (resParam.packageName.equals("com.android.systemui")) {
					String targetpkg = "com.android.systemui";
					if (paramPrefs.getBoolean("tpstatus", false) && (paramPrefs.getString("tpsbstyle", "full").equals("full"))) {
						try {
							resParam.res.setReplacement(targetpkg , "drawable", "status_bar_background", modRes.fwd(R.drawable.status_bar_background));
						} catch (Throwable t) { XposedBridge.log(t); }
					} else if (paramPrefs.getBoolean("tpstatus", false) && (paramPrefs.getString("tpsbstyle", "full").equals("1px"))) {
						try {
							resParam.res.setReplacement(targetpkg, "drawable", "status_bar_background", modRes.fwd(R.drawable.status_bar_background_1px));
						} catch (Throwable t) { XposedBridge.log(t); }
					}
			}
			if (resParam.packageName.equals("com.htc.launcher") && (paramPrefs.getString("tpsbstyle", "full").equals("full"))) {
				resParam.res.setReplacement("com.htc.launcher" , "drawable", "automotive_common_app_bkg_top", modRes.fwd(R.drawable.automotive_common_app_bkg_top));
			}
		} catch (Throwable t) { XposedBridge.log(t); }
	}
}
