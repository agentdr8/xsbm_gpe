package com.dr8.sbicons.mod.hax;

import android.content.res.XModuleResources;

import com.dr8.sbicons.R;

import de.robv.android.xposed.XSharedPreferences;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.callbacks.XC_InitPackageResources;

public class TpApps {

	public static void initPackageResources(XSharedPreferences paramPrefs, XModuleResources modRes, XC_InitPackageResources.InitPackageResourcesParam resParam) {
		try {
			resParam.res.setReplacement("com.htc.launcher", "color", "add_to_home_background_color", 0x00000000);
			resParam.res.setReplacement("com.htc.launcher", "color", "all_apps_edit_mode_background_color", 0x00000000);
			resParam.res.setReplacement("com.htc.launcher", "color", "feedview_overlay", 0x00000000);
			resParam.res.setReplacement("com.htc.launcher", "anim", "all_apps_2d_fade_in", modRes.fwd(R.anim.all_apps_2d_fade_in));
			resParam.res.setReplacement("com.htc.launcher", "drawable", "all_apps_bkg", modRes.fwd(R.drawable.all_apps_bkg));
			resParam.res.setReplacement("com.htc.launcher", "drawable", "home_folder_base", modRes.fwd(R.drawable.home_folder_base));
			resParam.res.setReplacement("com.htc.launcher", "drawable", "home_expanded_panel", modRes.fwd(R.drawable.home_expanded_panel));
		} catch (Throwable t) { XposedBridge.log(t); }
	}
}
