package com.dr8.sbicons.mod.hax;

import static de.robv.android.xposed.XposedHelpers.findAndHookMethod;
import static de.robv.android.xposed.XposedHelpers.getSurroundingThis;

import android.content.Context;
import android.content.res.XModuleResources;
import android.util.AttributeSet;
import android.widget.RelativeLayout;

import com.dr8.sbicons.R;

import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XSharedPreferences;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.callbacks.XC_InitPackageResources;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

public class TpApps {

	public static void initPackageResources(XSharedPreferences paramPrefs, XModuleResources modRes, XC_InitPackageResources.InitPackageResourcesParam resParam) {
		try {
			resParam.res.setReplacement("com.htc.launcher", "color", "add_to_home_background_color", 0xC0000000);
			resParam.res.setReplacement("com.htc.launcher", "color", "all_apps_edit_mode_background_color", 0xC0000000);
			resParam.res.setReplacement("com.htc.launcher", "color", "feedview_overlay", 0xC0000000);
			resParam.res.setReplacement("com.htc.launcher", "anim", "all_apps_2d_fade_in", modRes.fwd(R.anim.all_apps_2d_fade_in));
			resParam.res.setReplacement("com.htc.launcher", "drawable", "all_apps_bkg", modRes.fwd(R.drawable.app_background));
			resParam.res.setReplacement("com.htc.launcher", "drawable", "home_folder_base", modRes.fwd(R.drawable.home_folder_base));
			resParam.res.setReplacement("com.htc.launcher", "drawable", "home_expanded_panel", modRes.fwd(R.drawable.home_expanded_panel));
		} catch (Throwable t) { XposedBridge.log(t); }
//		resParam.res.hookLayout("com.htc.launcher", "layout", "launcher", new XC_LayoutInflated() {
//			@Override
//			public void handleLayoutInflated(LayoutInflatedParam liparam) throws Throwable {
//				try {
//					RelativeLayout rl = (RelativeLayout) liparam.view.findViewById(liparam.res.getIdentifier("all_apps_paged_view_frame", "id", "com.htc.launcher"));
//					rl.setBackgroundResource(R.drawable.app_background);
//					Log.i("XSBM:", " hooked relativelayout, trying to set bg");
//				} catch (Throwable t) { XposedBridge.log(t); }
//			}
//		}); 
	}
	
	public static void initHandleLoadPackage(final XSharedPreferences paramPrefs, XC_LoadPackage.LoadPackageParam lpParam) {
		findAndHookMethod("com.htc.launcher.pageview.AllAppsPagedViewHost", lpParam.classLoader, "AllAppsPagedViewHost", Context.class, AttributeSet.class, Integer.class, new XC_MethodHook() {
			@Override
			protected void afterHookedMethod(MethodHookParam hookParam) throws Throwable {
				RelativeLayout rl = (RelativeLayout) getSurroundingThis(hookParam.thisObject); 
				rl.setBackgroundResource(R.drawable.app_background);
			}
		});
	}
}
