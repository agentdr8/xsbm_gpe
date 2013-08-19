package com.dr8.sbicons.mod.hax;

import android.content.res.XModuleResources;
import android.view.View;

import de.robv.android.xposed.XSharedPreferences;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.callbacks.XC_InitPackageResources;
import de.robv.android.xposed.callbacks.XC_LayoutInflated;

public class TpNotif {

	private static View pv;
	
	public static void initPackageResources(final XSharedPreferences paramPrefs, XModuleResources modRes, XC_InitPackageResources.InitPackageResourcesParam resParam) {
		resParam.res.hookLayout("com.android.systemui", "layout", "super_status_bar", new XC_LayoutInflated() {
			@Override
			public void handleLayoutInflated(LayoutInflatedParam liparam) throws Throwable {
				try {
					if (liparam.view == null) {
						return;
					}
					int vid = liparam.res.getIdentifier("notification_panel", "id", "com.android.systemui");
					if (vid != 0) {
						pv = (View) liparam.view.findViewById(vid);
						if (pv == null) {
							return;
						} else {
							pv.setBackgroundColor(paramPrefs.getInt("tpnotif", 0x7f000000));
						}
					} else {
						return;
					}
				} catch (Throwable t) { 
					XposedBridge.log(t); 
				} 
			}
		});
	}
	
}
