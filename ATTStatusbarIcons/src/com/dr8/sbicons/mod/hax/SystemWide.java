package com.dr8.sbicons.mod.hax;

import com.dr8.sbicons.R;

import android.content.res.XModuleResources;
import android.content.res.XResources;
import de.robv.android.xposed.IXposedHookZygoteInit.StartupParam;

public class SystemWide {
	public static void initHandleZygote(StartupParam startupParam, XModuleResources modRes) {
		XResources.setSystemWideReplacement("android", "drawable", "stat_sys_gps_on", modRes.fwd(R.drawable.stat_sys_gps_on));
	}
}
