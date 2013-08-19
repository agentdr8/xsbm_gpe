package com.dr8.sbicons.mod.hax;

import static de.robv.android.xposed.XposedHelpers.findAndHookMethod;
import static de.robv.android.xposed.XposedHelpers.getIntField;
import static de.robv.android.xposed.XposedHelpers.getObjectField;

import java.util.ArrayList;

import android.content.Context;
import android.content.Intent;
import android.graphics.PorterDuff.Mode;
import android.widget.ImageView;
import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XSharedPreferences;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

public class BatteryRainbow {

	public static void initHandleLoadPackage(final XSharedPreferences paramPrefs, XC_LoadPackage.LoadPackageParam lpParam) {
		if (paramPrefs.getBoolean("batt_icon_rainbow", false) && paramPrefs.getBoolean("battery", true)) {
			findAndHookMethod("com.android.systemui.statusbar.policy.BatteryController", lpParam.classLoader, "onReceive", Context.class, Intent.class, new XC_MethodHook() {
				@Override
				protected void afterHookedMethod(MethodHookParam param) throws Throwable {
					try {
						int blevel = getIntField(param.thisObject, "level");
						@SuppressWarnings("unchecked")
						int j = ((ArrayList<ImageView>) getObjectField(param.thisObject, "mIconViews")).size();
						for (int k = 0; k < j; k++) {
							@SuppressWarnings("unchecked")
							ImageView iv = ((ArrayList<ImageView>) getObjectField(param.thisObject, "mIconViews")).get(k);
							if (blevel <= 20) {
								final int btcolor = paramPrefs.getInt("batt_color_0-20", 0xffff0000);
								iv.setColorFilter(btcolor, Mode.MULTIPLY);
							} else if (blevel >= 21 && blevel <= 40) {
								final int btcolor = paramPrefs.getInt("batt_color_21-40", 0xffffff00);
								iv.setColorFilter(btcolor, Mode.MULTIPLY);
							} else if (blevel >= 41 && blevel <= 60) {
								final int btcolor = paramPrefs.getInt("batt_color_41-60", 0xffffff00);
								iv.setColorFilter(btcolor, Mode.MULTIPLY);
							} else if (blevel >= 61 && blevel <= 80) {
								final int btcolor = paramPrefs.getInt("batt_color_61-80", 0xff00ff00);
								iv.setColorFilter(btcolor, Mode.MULTIPLY);
							} else if (blevel >= 81 && blevel <= 100) {
								final int btcolor = paramPrefs.getInt("batt_color_81-100", 0xff35b5e5);
								iv.setColorFilter(btcolor, Mode.MULTIPLY);
							}
						}
					} catch (Throwable t) { XposedBridge.log(t); }
				}
	
			});
		}
//		if (paramPrefs.getBoolean("batt_text_rainbow", false)) {
//			findAndHookMethod("com.android.systemui.statusbar.policy.BatteryController", lpParam.classLoader, "onReceive", Context.class, Intent.class, new XC_MethodHook() {
//				@Override
//				protected void afterHookedMethod(MethodHookParam param) throws Throwable {
//					try {
//						int blevel = getIntField(param.thisObject, "level");
//						@SuppressWarnings("unchecked")
//						int m = ((ArrayList<TextView>) getObjectField(param.thisObject, "mLabelViews")).size();
//						for (int n = 0; n < m; n++) {
//							@SuppressWarnings("unchecked")
//							TextView tv = ((ArrayList<TextView>) getObjectField(param.thisObject, "mLabelViews")).get(n);
//							if (blevel <= 20) {
//								final int btcolor = paramPrefs.getInt("batt_color_0-20", 0xffff0000);
//								tv.setTextColor(btcolor);
//							} else if (blevel >= 21 && blevel <= 40) {
//								final int btcolor = paramPrefs.getInt("batt_color_21-40", 0xffffff00);
//								tv.setTextColor(btcolor);
//							} else if (blevel >= 41 && blevel <= 60) {
//								final int btcolor = paramPrefs.getInt("batt_color_41-60", 0xffffff00);
//								tv.setTextColor(btcolor);
//							} else if (blevel >= 61 && blevel <= 80) {
//								final int btcolor = paramPrefs.getInt("batt_color_61-80", 0xff00ff00);
//								tv.setTextColor(btcolor);
//							} else if (blevel >= 81 && blevel <= 100) {
//								final int btcolor = paramPrefs.getInt("batt_color_81-100", 0xff35b5e5);
//								tv.setTextColor(btcolor);
//							}
//						}
//					} catch (Throwable t) { XposedBridge.log(t); }
//				}
//			});
//		}
	}
}
