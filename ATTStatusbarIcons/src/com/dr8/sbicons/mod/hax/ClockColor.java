package com.dr8.sbicons.mod.hax;

import android.content.res.XModuleResources;
import android.widget.TextView;

import de.robv.android.xposed.XSharedPreferences;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.callbacks.XC_InitPackageResources;
import de.robv.android.xposed.callbacks.XC_LayoutInflated;

public class ClockColor {

	public static void initPackageResources(final XSharedPreferences paramPrefs, XModuleResources modRes, XC_InitPackageResources.InitPackageResourcesParam resParam) {
		resParam.res.hookLayout("com.android.systemui", "layout", "super_status_bar", new XC_LayoutInflated() {
			@Override
			public void handleLayoutInflated(LayoutInflatedParam liparam) throws Throwable {
				try {
					final int clockcolor = paramPrefs.getInt("clock_text_color", 0xff35b5e5);
					TextView clock = (TextView) liparam.view.findViewById(liparam.res.getIdentifier("clock", "id", "com.android.systemui"));
					clock.setTextColor(clockcolor);
				} catch (Throwable t) { XposedBridge.log(t); }
			}
		});   
	}
}
