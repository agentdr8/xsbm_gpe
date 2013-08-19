package com.dr8.sbicons.mod.hax;

import static de.robv.android.xposed.XposedHelpers.findAndHookMethod;

import android.text.Spannable;
import android.text.SpannableString;
import android.text.format.Time;
import android.text.style.RelativeSizeSpan;
import android.widget.TextView;

import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XSharedPreferences;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

public class ClockAMPM {

	public static void initHandleLoadPackage(final XSharedPreferences paramPrefs, XC_LoadPackage.LoadPackageParam lpParam, final Integer is24) {
		findAndHookMethod("com.android.systemui.statusbar.policy.Clock", lpParam.classLoader, "updateClock", new XC_MethodHook() {
			@Override
			protected void afterHookedMethod(MethodHookParam param) throws Throwable {
				TextView clock = (TextView) param.thisObject;
				Time today = new Time(Time.getCurrentTimezone());
				today.setToNow();
				if (is24 == 0) {
					Spannable span = new SpannableString(today.format("%l:%M %p"));
					span.setSpan(new RelativeSizeSpan(0.75f), 5, span.length(), 0);
					clock.setText(span);
				}
			}
		});
	}
}
