package com.dr8.sbicons.ge.mod.hax;

import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XC_MethodReplacement;
import de.robv.android.xposed.XSharedPreferences;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.callbacks.XCallback;

/* 
 *  This class borrowed from C3C076 gravitybox
 */

public class TpStatusBar {

	private static View mStatusBar;
	private static final String CLASS_PANEL_BAR = "com.android.systemui.statusbar.phone.PanelBar";
	private static final String CLASS_PHONE_STATUSBAR = "com.android.systemui.statusbar.phone.PhoneStatusBar";
    private static final String CLASS_PHONE_WINDOW_MANAGER = "com.android.internal.policy.impl.PhoneWindowManager";

    public static void initZygote() {
    	try {
    		final Class<?> phoneWindowManagerClass = XposedHelpers.findClass(CLASS_PHONE_WINDOW_MANAGER, null);

    		XposedHelpers.findAndHookMethod(phoneWindowManagerClass,
    				"getSystemDecorRectLw", Rect.class, new XC_MethodReplacement() {

    			@Override
    			protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
    				Rect rect = (Rect) param.args[0];
    				rect.left = XposedHelpers.getIntField(param.thisObject, "mSystemLeft");
    				rect.top = XposedHelpers.getIntField(param.thisObject, "mSystemTop");
    				rect.right = XposedHelpers.getIntField(param.thisObject, "mSystemRight");
    				rect.bottom = XposedHelpers.getIntField(param.thisObject, "mSystemBottom");
    				return 0;
    			}
    		});
    	} catch (Exception e) {
    		XposedBridge.log(e);
    	}
    }

	public static void initHandleLoadPackage(final XSharedPreferences paramPrefs, final ClassLoader classLoader) throws Throwable {
		final Class<?> panelBarClass = XposedHelpers.findClass(CLASS_PANEL_BAR, classLoader);
		final Class<?> phoneStatusbarClass = XposedHelpers.findClass(CLASS_PHONE_STATUSBAR, classLoader);
		
		XposedBridge.hookAllConstructors(panelBarClass, new XC_MethodHook() {
			@Override
			protected void afterHookedMethod(final MethodHookParam param) throws Throwable {
				mStatusBar = (View) param.thisObject;
//				XposedBridge.log("XSBM: hooked panelbar");
			}
		});
		XposedHelpers.findAndHookMethod(phoneStatusbarClass, "makeStatusBarView", new XC_MethodHook(XCallback.PRIORITY_HIGHEST) {
			@Override
			protected void afterHookedMethod(final MethodHookParam param) throws Throwable {
				if (mStatusBar != null) {
					ColorDrawable sb = new ColorDrawable();
					sb.setColor(paramPrefs.getInt("tpstatus", 0x7f000000));
					mStatusBar.setBackground(sb);
//					XposedBridge.log("XSBM: setting bg of panelbar");
				}
			}
		});
	}

}
