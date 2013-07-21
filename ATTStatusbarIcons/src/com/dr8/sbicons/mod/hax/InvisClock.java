package com.dr8.sbicons.mod.hax;

import android.content.res.XModuleResources;
import android.widget.TextView;

import de.robv.android.xposed.XSharedPreferences;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.callbacks.XC_InitPackageResources;
import de.robv.android.xposed.callbacks.XC_LayoutInflated;

public class InvisClock {

	public static void initPackageResources(final XSharedPreferences paramPrefs, XModuleResources modRes, XC_InitPackageResources.InitPackageResourcesParam resParam) {
		XposedBridge.log("Hooking statusbar");
		resParam.res.hookLayout("com.android.systemui", "layout", "super_status_bar", new XC_LayoutInflated() {
			@Override
			public void handleLayoutInflated(LayoutInflatedParam liparam) throws Throwable {
				try {
					XposedBridge.log("Inside inflated layout");
					TextView clock = (TextView) liparam.view.findViewById(liparam.res.getIdentifier("clock", "id", "com.android.systemui"));
					clock.setVisibility(8);
					XposedBridge.log("Changed clock visibility");
				} catch (Throwable t) { XposedBridge.log(t); }
			}
		});  
	}
}
