package com.dr8.sbicons.mod.hax;

import android.content.res.XModuleResources;
import android.widget.TextView;

import de.robv.android.xposed.XSharedPreferences;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.callbacks.XC_InitPackageResources;
import de.robv.android.xposed.callbacks.XC_LayoutInflated;

public class ClockAMPM {

	public static void initPackageResources(final XSharedPreferences paramPrefs, XModuleResources modRes, XC_InitPackageResources.InitPackageResourcesParam resParam) {
		resParam.res.hookLayout("com.android.systemui", "layout", "super_status_bar", new XC_LayoutInflated() {
			@Override
			public void handleLayoutInflated(LayoutInflatedParam liparam) throws Throwable {
				try {
					TextView clock = (TextView) liparam.view.findViewById(liparam.res.getIdentifier("clock", "id", "com.android.systemui"));
					clock.setText(clock.getText().toString().replaceAll(" [AM|PM]", ""));
				} catch (Throwable t) { XposedBridge.log(t); }
			}
		});   
	}
}
