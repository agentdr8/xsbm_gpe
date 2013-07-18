package com.dr8.sbicons.mod.hax;

import android.content.res.XModuleResources;
import android.view.ViewGroup;
import com.dr8.sbicons.R;

import de.robv.android.xposed.XSharedPreferences;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.callbacks.XC_InitPackageResources;
import de.robv.android.xposed.callbacks.XC_LayoutInflated;

public class TpApps {

	public static void initPackageResources(XSharedPreferences paramPrefs, XModuleResources modRes, XC_InitPackageResources.InitPackageResourcesParam resParam) {
		try {
			resParam.res.setReplacement("com.htc.launcher", "color", "add_to_home_background_color", 0xC0000000);
			resParam.res.setReplacement("com.htc.launcher", "color", "all_apps_edit_mode_background_color", 0xC0000000);
			resParam.res.setReplacement("com.htc.launcher", "color", "feedview_overlay", 0xC0000000);
			resParam.res.setReplacement("com.htc.launcher", "anim", "all_apps_2d_fade_in", modRes.fwd(R.anim.all_apps_2d_fade_in));
			resParam.res.setReplacement("com.htc.launcher", "drawable", "home_folder_base", modRes.fwd(R.drawable.home_folder_base));
			resParam.res.setReplacement("com.htc.launcher", "drawable", "home_expanded_panel", modRes.fwd(R.drawable.home_expanded_panel));
		} catch (Throwable t) { XposedBridge.log(t); }
		resParam.res.hookLayout("com.htc.launcher", "layout", "launcher", new XC_LayoutInflated() {
			@Override
			public void handleLayoutInflated(LayoutInflatedParam liparam) throws Throwable {
				try {
					ViewGroup vg = (ViewGroup) liparam.view.findViewById(liparam.res.getIdentifier("all_apps_paged_view", "id", "com.htc.launcher"));
					vg.setBackgroundResource(R.drawable.app_background);
				} catch (Throwable t) { XposedBridge.log(t); }
			}
		}); 
	}
}
