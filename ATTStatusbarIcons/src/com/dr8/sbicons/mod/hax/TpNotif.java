package com.dr8.sbicons.mod.hax;

import android.content.res.XModuleResources;
import android.view.View;

import de.robv.android.xposed.XSharedPreferences;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.callbacks.XC_InitPackageResources;
import de.robv.android.xposed.callbacks.XC_LayoutInflated;

public class TpNotif {

	public static void initPackageResources(final XSharedPreferences paramPrefs, XModuleResources modRes, XC_InitPackageResources.InitPackageResourcesParam resParam) {
		resParam.res.hookLayout("com.android.systemui", "layout", "super_status_bar", new XC_LayoutInflated() {
			@Override
			public void handleLayoutInflated(LayoutInflatedParam liparam) throws Throwable {
				try {
					View pv = (View) liparam.view.findViewById(liparam.res.getIdentifier("notification_panel", "id", "com.android.systemui"));
					pv.setBackgroundColor(paramPrefs.getInt("notif_bg_color", 0xff000000));
				} catch (Throwable t) { XposedBridge.log(t); }
			}
		});
	}
}
