package com.dr8.sbicons.mod.hax;

import static de.robv.android.xposed.XposedHelpers.findAndHookMethod;
import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XSharedPreferences;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

public class HtcNetworkController {

	public static void initHandleLoadPackage(final XSharedPreferences paramPrefs, XC_LoadPackage.LoadPackageParam lpParam) {
		if (paramPrefs.getBoolean("hideatt", false)) {
			findAndHookMethod("com.android.systemui.statusbar.policy.HtcGenericNetworkController", lpParam.classLoader, "isATT", new XC_MethodHook() {
				@Override
				protected void afterHookedMethod(MethodHookParam attbool) throws Throwable {
					attbool.setResult(false);
				}
			});
		} 
		findAndHookMethod("com.android.systemui.statusbar.policy.HtcGenericNetworkController", lpParam.classLoader, "isTmoUS", new XC_MethodHook() {
			@Override
			protected void afterHookedMethod(MethodHookParam tmobool) throws Throwable {
				tmobool.setResult(false);
			}
		});
	}
}
