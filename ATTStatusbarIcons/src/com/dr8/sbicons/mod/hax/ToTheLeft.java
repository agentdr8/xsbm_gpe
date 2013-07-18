package com.dr8.sbicons.mod.hax;

import android.content.res.XModuleResources;

import com.dr8.sbicons.R;

import de.robv.android.xposed.XSharedPreferences;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.callbacks.XC_InitPackageResources;

public class ToTheLeft {

	public static void initPackageResources(XSharedPreferences paramPrefs, XModuleResources modRes, XC_InitPackageResources.InitPackageResourcesParam resParam) {
		try {
			if (resParam.packageName.equals("com.android.phone")) {
				resParam.res.setReplacement("com.android.phone", "drawable", "stat_notify_call_mute", modRes.fwd(R.drawable.stat_notify_call_mute));
				resParam.res.setReplacement("com.android.phone", "drawable", "stat_notify_voicemail", modRes.fwd(R.drawable.stat_notify_voicemail));
				resParam.res.setReplacement("com.android.phone", "drawable", "stat_sys_speakerphone", modRes.fwd(R.drawable.stat_sys_speakerphone));
			} else if (resParam.packageName.equals("com.android.systemui")) {
				String targetpkg = "com.android.systemui";
				resParam.res.setReplacement(targetpkg , "drawable", "ic_notify_clear", modRes.fwd(R.drawable.ic_notify_clear));
				resParam.res.setReplacement(targetpkg, "drawable", "ic_notify_notifications", modRes.fwd(R.drawable.ic_notify_notifications));
				resParam.res.setReplacement(targetpkg, "drawable", "ic_notify_quicksettings", modRes.fwd(R.drawable.ic_notify_quicksettings));
				resParam.res.setReplacement(targetpkg, "drawable", "icon_btn_voice_dark", modRes.fwd(R.drawable.icon_btn_voice_dark));
				resParam.res.setReplacement(targetpkg, "drawable", "icon_btn_menu_dark", modRes.fwd(R.drawable.icon_btn_menu_dark));
				resParam.res.setReplacement(targetpkg, "drawable", "stat_meta_caps_lock", modRes.fwd(R.drawable.stat_meta_caps_lock));
				resParam.res.setReplacement(targetpkg, "drawable", "stat_meta_caps_on", modRes.fwd(R.drawable.stat_meta_caps_on));
				resParam.res.setReplacement(targetpkg, "drawable", "stat_meta_fn_lock", modRes.fwd(R.drawable.stat_meta_fn_lock));
				resParam.res.setReplacement(targetpkg, "drawable", "stat_meta_fn_on", modRes.fwd(R.drawable.stat_meta_fn_on));
				resParam.res.setReplacement(targetpkg, "drawable", "stat_notify_alarm", modRes.fwd(R.drawable.stat_notify_alarm));
				resParam.res.setReplacement(targetpkg, "drawable", "stat_notify_image", modRes.fwd(R.drawable.stat_notify_image));
				resParam.res.setReplacement(targetpkg, "drawable", "stat_notify_image_error", modRes.fwd(R.drawable.stat_notify_image_error));
				resParam.res.setReplacement(targetpkg, "drawable", "stat_notify_more", modRes.fwd(R.drawable.stat_notify_more));
				resParam.res.setReplacement(targetpkg, "drawable", "stat_sys_ringer_silent", modRes.fwd(R.drawable.stat_sys_ringer_silent));
				resParam.res.setReplacement(targetpkg, "drawable", "stat_sys_ringer_vibrate", modRes.fwd(R.drawable.stat_sys_ringer_vibrate));
				resParam.res.setReplacement(targetpkg, "drawable", "stat_sys_headphones", modRes.fwd(R.drawable.stat_sys_headphones));
				resParam.res.setReplacement(targetpkg, "drawable", "stat_sys_headphone_no_mic", modRes.fwd(R.drawable.stat_sys_headphone_no_mic));
				resParam.res.setReplacement(targetpkg, "drawable", "stat_sys_hac", modRes.fwd(R.drawable.stat_sys_hac));
				resParam.res.setReplacement(targetpkg, "drawable", "stat_sys_no_sim", modRes.fwd(R.drawable.stat_sys_no_sim));
				resParam.res.setReplacement(targetpkg, "drawable", "stat_sys_sim_lock", modRes.fwd(R.drawable.stat_sys_sim_lock));
				resParam.res.setReplacement(targetpkg, "drawable", "stat_sys_tty", modRes.fwd(R.drawable.stat_sys_tty));
				resParam.res.setReplacement(targetpkg, "drawable", "stat_sys_tty_mode", modRes.fwd(R.drawable.stat_sys_tty_mode));
				resParam.res.setReplacement(targetpkg, "drawable", "stat_sys_sync", modRes.fwd(R.drawable.stat_sys_sync));
				resParam.res.setReplacement(targetpkg, "drawable", "stat_sys_sync_anim0", modRes.fwd(R.drawable.stat_sys_sync_anim0));
				resParam.res.setReplacement(targetpkg, "drawable", "stat_sys_sync_error", modRes.fwd(R.drawable.stat_sys_sync_error));
			}
		} catch (Throwable t) { XposedBridge.log(t); }
	}
}
