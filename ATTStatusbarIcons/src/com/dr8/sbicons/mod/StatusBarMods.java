package com.dr8.sbicons.mod;

import static de.robv.android.xposed.XposedHelpers.findAndHookMethod;
import static de.robv.android.xposed.XposedHelpers.getIntField;
import static de.robv.android.xposed.XposedHelpers.getObjectField;
import java.util.ArrayList;

import com.dr8.sbicons.R;

import android.content.Context;
import android.content.Intent;
import android.content.res.XModuleResources;
import android.content.res.XResources;
import android.graphics.PorterDuff.Mode;
import android.os.Build;
import android.widget.ImageView;
import android.widget.TextView;
import de.robv.android.xposed.IXposedHookInitPackageResources;
import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.IXposedHookZygoteInit;
import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XSharedPreferences;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.callbacks.XC_InitPackageResources.InitPackageResourcesParam;
import de.robv.android.xposed.callbacks.XC_LayoutInflated;
import de.robv.android.xposed.callbacks.XC_LoadPackage.LoadPackageParam;

public class StatusBarMods implements IXposedHookZygoteInit, IXposedHookInitPackageResources, IXposedHookLoadPackage {
//	private static final String TAG = "XSBM";
	private static String MODULE_PATH = null;
	private static XSharedPreferences pref;
	private static String targetpkg = "com.android.systemui";
		
	public String getDeviceName() {
		  String manufacturer = Build.MANUFACTURER;
		  return capitalize(manufacturer);
	}

	private String capitalize(String s) {
		  if (s == null || s.length() == 0) {
		    return "";
		  }
		  char first = s.charAt(0);
		  if (Character.isUpperCase(first)) {
		    return s;
		  } else {
		    return Character.toUpperCase(first) + s.substring(1);
		  }
	} 
	
	@Override
	public void initZygote(StartupParam startupParam) throws Throwable {
		MODULE_PATH = startupParam.modulePath;
		XModuleResources modRes1 = XModuleResources.createInstance(MODULE_PATH, null);
		try {
			XResources.setSystemWideReplacement("android", "drawable", "stat_sys_gps_on", modRes1.fwd(R.drawable.stat_sys_gps_on));
		} catch (Throwable t) { XposedBridge.log(t); }
		pref = new XSharedPreferences("com.dr8.sbicons", "com.dr8.sbicons_preferences");
	}

	@Override
	public void handleLoadPackage(LoadPackageParam lpparam) throws Throwable {
		pref.reload();
		if (lpparam.packageName.equals("com.android.systemui")) {
			if (pref.getBoolean("batt_icon_rainbow", false) && pref.getBoolean("battery", true)) {
				findAndHookMethod("com.android.systemui.statusbar.policy.BatteryController", lpparam.classLoader, "onReceive", Context.class, Intent.class, new XC_MethodHook() {
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
									final int btcolor = pref.getInt("batt_color_0-20", 0xffff0000);
									iv.setColorFilter(btcolor, Mode.MULTIPLY);
								} else if (blevel >= 21 && blevel <= 40) {
									final int btcolor = pref.getInt("batt_color_21-40", 0xffffff00);
									iv.setColorFilter(btcolor, Mode.MULTIPLY);
								} else if (blevel >= 41 && blevel <= 60) {
									final int btcolor = pref.getInt("batt_color_41-60", 0xffffff00);
									iv.setColorFilter(btcolor, Mode.MULTIPLY);
								} else if (blevel >= 61 && blevel <= 80) {
									final int btcolor = pref.getInt("batt_color_61-80", 0xff00ff00);
									iv.setColorFilter(btcolor, Mode.MULTIPLY);
								} else if (blevel >= 81 && blevel <= 100) {
									final int btcolor = pref.getInt("batt_color_81-100", 0xff35b5e5);
									iv.setColorFilter(btcolor, Mode.MULTIPLY);
								}
							}
						} catch (Throwable t) { XposedBridge.log(t); }
					}

				});
			}
			if (pref.getBoolean("batt_text_rainbow", false)) {
				findAndHookMethod("com.android.systemui.statusbar.policy.BatteryController", lpparam.classLoader, "onReceive", Context.class, Intent.class, new XC_MethodHook() {
					@Override
					protected void afterHookedMethod(MethodHookParam param) throws Throwable {
						try {
							int blevel = getIntField(param.thisObject, "level");
							@SuppressWarnings("unchecked")
							int m = ((ArrayList<TextView>) getObjectField(param.thisObject, "mLabelViews")).size();
							for (int n = 0; n < m; n++) {
								@SuppressWarnings("unchecked")
								TextView tv = ((ArrayList<TextView>) getObjectField(param.thisObject, "mLabelViews")).get(n);
								if (blevel <= 20) {
									final int btcolor = pref.getInt("batt_color_0-20", 0xffff0000);
									tv.setTextColor(btcolor);
								} else if (blevel >= 21 && blevel <= 40) {
									final int btcolor = pref.getInt("batt_color_21-40", 0xffffff00);
									tv.setTextColor(btcolor);
								} else if (blevel >= 41 && blevel <= 60) {
									final int btcolor = pref.getInt("batt_color_41-60", 0xffffff00);
									tv.setTextColor(btcolor);
								} else if (blevel >= 61 && blevel <= 80) {
									final int btcolor = pref.getInt("batt_color_61-80", 0xff00ff00);
									tv.setTextColor(btcolor);
								} else if (blevel >= 81 && blevel <= 100) {
									final int btcolor = pref.getInt("batt_color_81-100", 0xff35b5e5);
									tv.setTextColor(btcolor);
								}
							}
						} catch (Throwable t) { XposedBridge.log(t); }
					}
				});
			}
			if (pref.getString("iconset", "att").equals("att") && (!pref.getBoolean("hideatt", false))) {
				try {
					findAndHookMethod("com.android.systemui.statusbar.policy.HtcGenericNetworkController", lpparam.classLoader, "isATT", new XC_MethodHook() {
						@Override
						protected void afterHookedMethod(MethodHookParam attbool) throws Throwable {
							attbool.setResult(true);
						}
					});
				} catch (Throwable t) { XposedBridge.log(t); }
			} else if (pref.getString("iconset", "att").equals("att") && (pref.getBoolean("hideatt", true))) {
				try {
					findAndHookMethod("com.android.systemui.statusbar.policy.HtcGenericNetworkController", lpparam.classLoader, "isATT", new XC_MethodHook() {
						@Override
						protected void afterHookedMethod(MethodHookParam attbool) throws Throwable {
							attbool.setResult(false);
						}
					});
				} catch (Throwable t) { XposedBridge.log(t); }
			} else if (pref.getString("iconset", "att").equals("tmobus")) {
				try {
					findAndHookMethod("com.android.systemui.statusbar.policy.HtcGenericNetworkController", lpparam.classLoader, "isATT", new XC_MethodHook() {
						@Override
						protected void afterHookedMethod(MethodHookParam attbool) throws Throwable {
							attbool.setResult(false);
						}
					});
				} catch (Throwable t) { XposedBridge.log(t); }
				try {
					findAndHookMethod("com.android.systemui.statusbar.policy.HtcGenericNetworkController", lpparam.classLoader, "isTmoUS", new XC_MethodHook() {
						@Override
						protected void afterHookedMethod(MethodHookParam tmobool) throws Throwable {
							tmobool.setResult(true);
						}
					});
				} catch (Throwable t) { XposedBridge.log(t); }
			} else {
				try {
					findAndHookMethod("com.android.systemui.statusbar.policy.HtcGenericNetworkController", lpparam.classLoader, "isATT", new XC_MethodHook() {
						@Override
						protected void afterHookedMethod(MethodHookParam attbool) throws Throwable {
							attbool.setResult(false);
						}
					});
				} catch (Throwable t) { XposedBridge.log(t); }
			}
		}
	}
	
	@Override
	public void handleInitPackageResources(InitPackageResourcesParam resparam) throws Throwable {
		pref.reload();
		XModuleResources modRes = XModuleResources.createInstance(MODULE_PATH, resparam.res);
		// String mfg = getDeviceName();
		// XposedBridge.log("Device mfg: " + mfg + " | Targeting: " + targetpkg);
		
		if(resparam.packageName.equals("com.android.phone")) {
			if (pref.getBoolean("to_the_left", true)) {
				try {
					resparam.res.setReplacement("com.android.phone", "drawable", "stat_notify_call_mute", modRes.fwd(R.drawable.stat_notify_call_mute));
					resparam.res.setReplacement("com.android.phone", "drawable", "stat_notify_voicemail", modRes.fwd(R.drawable.stat_notify_voicemail));
					resparam.res.setReplacement("com.android.phone", "drawable", "stat_sys_speakerphone", modRes.fwd(R.drawable.stat_sys_speakerphone));
				} catch (Throwable t) { XposedBridge.log(t); }
			}
		}
		
		if (!resparam.packageName.equals(targetpkg)) {
			return;
		}
		
		if (pref.getBoolean("invisbatt", false)) {
			resparam.res.hookLayout(targetpkg, "layout", "super_status_bar", new XC_LayoutInflated() {
				@Override
				public void handleLayoutInflated(LayoutInflatedParam liparam) throws Throwable {
					try {
						ImageView batt = (ImageView) liparam.view.findViewById(liparam.res.getIdentifier("battery", "id", "com.android.systemui"));
						batt.setVisibility(8);
					} catch (Throwable t) { XposedBridge.log(t); }
				}
			}); 
		}
		
		if (pref.getBoolean("invisclock", false)) {
			resparam.res.hookLayout(targetpkg, "layout", "super_status_bar", new XC_LayoutInflated() {
				@Override
				public void handleLayoutInflated(LayoutInflatedParam liparam) throws Throwable {
					try {
						TextView clock = (TextView) liparam.view.findViewById(liparam.res.getIdentifier("clock", "id", "com.android.systemui"));
						clock.setVisibility(8);
					} catch (Throwable t) { XposedBridge.log(t); }
				}
			}); 
		}
		
		if (pref.getBoolean("batt_text_color_enabled", false) && (!pref.getBoolean("batt_text_rainbow", false))) {
			resparam.res.hookLayout(targetpkg, "layout", "super_status_bar", new XC_LayoutInflated() {
				@Override
				public void handleLayoutInflated(LayoutInflatedParam liparam) throws Throwable {
					try {
						final int btcolor = pref.getInt("batt_text_color", 0xff35b5e5);
						TextView battext = (TextView) liparam.view.findViewById(liparam.res.getIdentifier("battery_text", "id", "com.android.systemui"));
						battext.setTextColor(btcolor);
					} catch (Throwable t) { XposedBridge.log(t); }
				}
			}); 
		}
		
		if (pref.getBoolean("clock_text_color_enabled", false)) {
			resparam.res.hookLayout(targetpkg, "layout", "super_status_bar", new XC_LayoutInflated() {
				@Override
				public void handleLayoutInflated(LayoutInflatedParam liparam) throws Throwable {
					try {
						final int clockcolor = pref.getInt("clock_text_color", 0xff35b5e5);
						TextView clock = (TextView) liparam.view.findViewById(liparam.res.getIdentifier("clock", "id", "com.android.systemui"));
						clock.setTextColor(clockcolor);
					} catch (Throwable t) { XposedBridge.log(t); }
				}
			}); 
		}
		
		if (pref.getBoolean("tpstatus", false) && (pref.getString("tpsbstyle", "full").equals("full"))) {
			try {
				resparam.res.setReplacement(targetpkg, "drawable", "status_bar_background", modRes.fwd(R.drawable.status_bar_background));
			} catch (Throwable t) { XposedBridge.log(t); }
		} else if (pref.getBoolean("tpstatus", false) && (pref.getString("tpsbstyle", "full").equals("1px"))) {
			try {
				resparam.res.setReplacement(targetpkg, "drawable", "status_bar_background", modRes.fwd(R.drawable.status_bar_background_1px));
			} catch (Throwable t) { XposedBridge.log(t); }
		}
		if (pref.getBoolean("to_the_left", true)) {
			try {
				resparam.res.setReplacement(targetpkg, "drawable", "ic_notify_clear", modRes.fwd(R.drawable.ic_notify_clear));
				resparam.res.setReplacement(targetpkg, "drawable", "ic_notify_notifications", modRes.fwd(R.drawable.ic_notify_notifications));
				resparam.res.setReplacement(targetpkg, "drawable", "ic_notify_quicksettings", modRes.fwd(R.drawable.ic_notify_quicksettings));
				resparam.res.setReplacement(targetpkg, "drawable", "icon_btn_voice_dark", modRes.fwd(R.drawable.icon_btn_voice_dark));
				resparam.res.setReplacement(targetpkg, "drawable", "icon_btn_menu_dark", modRes.fwd(R.drawable.icon_btn_menu_dark));
				resparam.res.setReplacement(targetpkg, "drawable", "stat_meta_caps_lock", modRes.fwd(R.drawable.stat_meta_caps_lock));
				resparam.res.setReplacement(targetpkg, "drawable", "stat_meta_caps_on", modRes.fwd(R.drawable.stat_meta_caps_on));
				resparam.res.setReplacement(targetpkg, "drawable", "stat_meta_fn_lock", modRes.fwd(R.drawable.stat_meta_fn_lock));
				resparam.res.setReplacement(targetpkg, "drawable", "stat_meta_fn_on", modRes.fwd(R.drawable.stat_meta_fn_on));
				resparam.res.setReplacement(targetpkg, "drawable", "stat_notify_alarm", modRes.fwd(R.drawable.stat_notify_alarm));
				resparam.res.setReplacement(targetpkg, "drawable", "stat_notify_image", modRes.fwd(R.drawable.stat_notify_image));
				resparam.res.setReplacement(targetpkg, "drawable", "stat_notify_image_error", modRes.fwd(R.drawable.stat_notify_image_error));
				resparam.res.setReplacement(targetpkg, "drawable", "stat_notify_more", modRes.fwd(R.drawable.stat_notify_more));
				resparam.res.setReplacement(targetpkg, "drawable", "stat_sys_ringer_silent", modRes.fwd(R.drawable.stat_sys_ringer_silent));
				resparam.res.setReplacement(targetpkg, "drawable", "stat_sys_ringer_vibrate", modRes.fwd(R.drawable.stat_sys_ringer_vibrate));
				resparam.res.setReplacement(targetpkg, "drawable", "stat_sys_headphones", modRes.fwd(R.drawable.stat_sys_headphones));
				resparam.res.setReplacement(targetpkg, "drawable", "stat_sys_headphone_no_mic", modRes.fwd(R.drawable.stat_sys_headphone_no_mic));
				resparam.res.setReplacement(targetpkg, "drawable", "stat_sys_hac", modRes.fwd(R.drawable.stat_sys_hac));
				resparam.res.setReplacement(targetpkg, "drawable", "stat_sys_no_sim", modRes.fwd(R.drawable.stat_sys_no_sim));
				resparam.res.setReplacement(targetpkg, "drawable", "stat_sys_sim_lock", modRes.fwd(R.drawable.stat_sys_sim_lock));
				resparam.res.setReplacement(targetpkg, "drawable", "stat_sys_tty", modRes.fwd(R.drawable.stat_sys_tty));
				resparam.res.setReplacement(targetpkg, "drawable", "stat_sys_tty_mode", modRes.fwd(R.drawable.stat_sys_tty_mode));
				resparam.res.setReplacement(targetpkg, "drawable", "stat_sys_sync", modRes.fwd(R.drawable.stat_sys_sync));
				resparam.res.setReplacement(targetpkg, "drawable", "stat_sys_sync_anim0", modRes.fwd(R.drawable.stat_sys_sync_anim0));
				resparam.res.setReplacement(targetpkg, "drawable", "stat_sys_sync_error", modRes.fwd(R.drawable.stat_sys_sync_error));
			} catch (Throwable t) { XposedBridge.log(t); }
		}
			
		if (pref.getBoolean("bluetooth", true)) {
			try {
				resparam.res.setReplacement(targetpkg, "drawable", "stat_sys_data_bluetooth", modRes.fwd(R.drawable.stat_sys_data_bluetooth));
				resparam.res.setReplacement(targetpkg, "drawable", "stat_sys_data_bluetooth_connected", modRes.fwd(R.drawable.stat_sys_data_bluetooth_connected));
			} catch (Throwable t) { XposedBridge.log(t); }
		}
		
		if (pref.getBoolean("gps", true)) {
			try {
				resparam.res.setReplacement(targetpkg, "drawable", "stat_sys_gps_on", modRes.fwd(R.drawable.stat_sys_gps_on));
				resparam.res.setReplacement(targetpkg, "drawable", "stat_sys_gps_acquiring", modRes.fwd(R.drawable.stat_sys_gps_acquiring));
			} catch (Throwable t) { XposedBridge.log(t); }
		}
		
		if (pref.getBoolean("wifi", true)) {
			if (pref.getString("wifistyle", "cyan").equals("cyan")) {
				try {
					resparam.res.setReplacement(targetpkg, "drawable", "stat_sys_wifi_signal_0", modRes.fwd(R.drawable.stat_sys_wifi_signal_0));
					resparam.res.setReplacement(targetpkg, "drawable", "stat_sys_wifi_signal_1", modRes.fwd(R.drawable.stat_sys_wifi_signal_1));
					resparam.res.setReplacement(targetpkg, "drawable", "stat_sys_wifi_signal_2", modRes.fwd(R.drawable.stat_sys_wifi_signal_2));
					resparam.res.setReplacement(targetpkg, "drawable", "stat_sys_wifi_signal_3", modRes.fwd(R.drawable.stat_sys_wifi_signal_3));
					resparam.res.setReplacement(targetpkg, "drawable", "stat_sys_wifi_signal_4", modRes.fwd(R.drawable.stat_sys_wifi_signal_4));
				} catch (Throwable t) { XposedBridge.log(t); }
			} else if (pref.getString("wifistyle", "cyan").equals("rainbow")) {
				try {
					resparam.res.setReplacement(targetpkg, "drawable", "stat_sys_wifi_signal_0", modRes.fwd(R.drawable.theme1_stat_sys_wifi_signal_0));
					resparam.res.setReplacement(targetpkg, "drawable", "stat_sys_wifi_signal_1", modRes.fwd(R.drawable.theme1_stat_sys_wifi_signal_1));
					resparam.res.setReplacement(targetpkg, "drawable", "stat_sys_wifi_signal_2", modRes.fwd(R.drawable.theme1_stat_sys_wifi_signal_2));
					resparam.res.setReplacement(targetpkg, "drawable", "stat_sys_wifi_signal_3", modRes.fwd(R.drawable.theme1_stat_sys_wifi_signal_3));
					resparam.res.setReplacement(targetpkg, "drawable", "stat_sys_wifi_signal_4", modRes.fwd(R.drawable.theme1_stat_sys_wifi_signal_4));
				} catch (Throwable t) { XposedBridge.log(t); }
			}
		}

		if (pref.getBoolean("signal", true)) {
			if (pref.getString("sigstyle", "cyan").equals("cyan")) {
				try { 
					resparam.res.setReplacement(targetpkg, "drawable", "stat_sys_5signal_0", modRes.fwd(R.drawable.stat_sys_5signal_0));
					resparam.res.setReplacement(targetpkg, "drawable", "stat_sys_5signal_1", modRes.fwd(R.drawable.stat_sys_5signal_1));
					resparam.res.setReplacement(targetpkg, "drawable", "stat_sys_5signal_2", modRes.fwd(R.drawable.stat_sys_5signal_2));
					resparam.res.setReplacement(targetpkg, "drawable", "stat_sys_5signal_3", modRes.fwd(R.drawable.stat_sys_5signal_3));
					resparam.res.setReplacement(targetpkg, "drawable", "stat_sys_5signal_4", modRes.fwd(R.drawable.stat_sys_5signal_4));
					resparam.res.setReplacement(targetpkg, "drawable", "stat_sys_5signal_5", modRes.fwd(R.drawable.stat_sys_5signal_5));
					resparam.res.setReplacement(targetpkg, "drawable", "stat_sys_5signal_null", modRes.fwd(R.drawable.stat_sys_5signal_null));
				} catch (Throwable t) { XposedBridge.log(t); }
			} else if (pref.getString("sigstyle", "cyan").equals("rainbow")) {
				try { 
					resparam.res.setReplacement(targetpkg, "drawable", "stat_sys_5signal_0", modRes.fwd(R.drawable.theme1_stat_sys_5signal_0));
					resparam.res.setReplacement(targetpkg, "drawable", "stat_sys_5signal_1", modRes.fwd(R.drawable.theme1_stat_sys_5signal_1));
					resparam.res.setReplacement(targetpkg, "drawable", "stat_sys_5signal_2", modRes.fwd(R.drawable.theme1_stat_sys_5signal_2));
					resparam.res.setReplacement(targetpkg, "drawable", "stat_sys_5signal_3", modRes.fwd(R.drawable.theme1_stat_sys_5signal_3));
					resparam.res.setReplacement(targetpkg, "drawable", "stat_sys_5signal_4", modRes.fwd(R.drawable.theme1_stat_sys_5signal_4));
					resparam.res.setReplacement(targetpkg, "drawable", "stat_sys_5signal_5", modRes.fwd(R.drawable.theme1_stat_sys_5signal_5));
					resparam.res.setReplacement(targetpkg, "drawable", "stat_sys_5signal_null", modRes.fwd(R.drawable.theme1_stat_sys_5signal_null));
				} catch (Throwable t) { XposedBridge.log(t); }
			}
			try {
				resparam.res.setReplacement(targetpkg, "drawable", "stat_sys_signal_flightmode", modRes.fwd(R.drawable.stat_sys_signal_flightmode));
			} catch (Throwable t) { XposedBridge.log(t); }
		}
			
		if (pref.getBoolean("mobile_data", true)) {
			if(pref.getString("pref_carrier_color", "cyan").equals("cyan")) {
				try {
					// replace default TmoUS icons
					resparam.res.setReplacement(targetpkg, "drawable", "stat_sys_data_connected_tmous_3g", modRes.fwd(R.drawable.stat_sys_data_connected_tmous_3g));
					resparam.res.setReplacement(targetpkg, "drawable", "stat_sys_data_connected_tmous_4g", modRes.fwd(R.drawable.stat_sys_data_connected_tmous_4g));
					resparam.res.setReplacement(targetpkg, "drawable", "stat_sys_data_connected_tmous_lte", modRes.fwd(R.drawable.stat_sys_data_connected_tmous_lte));
					
					resparam.res.setReplacement(targetpkg, "drawable", "stat_sys_data_in_tmous_3g", modRes.fwd(R.drawable.stat_sys_data_in_tmous_3g));
					resparam.res.setReplacement(targetpkg, "drawable", "stat_sys_data_in_tmous_4g", modRes.fwd(R.drawable.stat_sys_data_in_tmous_4g));
					resparam.res.setReplacement(targetpkg, "drawable", "stat_sys_data_in_tmous_lte", modRes.fwd(R.drawable.stat_sys_data_in_tmous_lte));
					
					resparam.res.setReplacement(targetpkg, "drawable", "stat_sys_data_inandout_tmous_3g", modRes.fwd(R.drawable.stat_sys_data_inandout_tmous_3g));
					resparam.res.setReplacement(targetpkg, "drawable", "stat_sys_data_inandout_tmous_4g", modRes.fwd(R.drawable.stat_sys_data_inandout_tmous_4g));
					resparam.res.setReplacement(targetpkg, "drawable", "stat_sys_data_inandout_tmous_lte", modRes.fwd(R.drawable.stat_sys_data_inandout_tmous_lte));
					
					resparam.res.setReplacement(targetpkg, "drawable", "stat_sys_data_out_tmous_3g", modRes.fwd(R.drawable.stat_sys_data_out_tmous_3g));
					resparam.res.setReplacement(targetpkg, "drawable", "stat_sys_data_out_tmous_4g", modRes.fwd(R.drawable.stat_sys_data_out_tmous_4g));
					resparam.res.setReplacement(targetpkg, "drawable", "stat_sys_data_out_tmous_lte", modRes.fwd(R.drawable.stat_sys_data_out_tmous_lte));
					
					resparam.res.setReplacement(targetpkg, "drawable", "stat_sys_data_4g_capability", modRes.fwd(R.drawable.stat_sys_data_4g_capability));
					resparam.res.setReplacement(targetpkg, "drawable", "stat_sys_data_4g_connected", modRes.fwd(R.drawable.stat_sys_data_4g_connected));
					resparam.res.setReplacement(targetpkg, "drawable", "stat_sys_data_4g_downlink_1", modRes.fwd(R.drawable.stat_sys_data_4g_downlink_1));
					resparam.res.setReplacement(targetpkg, "drawable", "stat_sys_data_4g_downlink_2", modRes.fwd(R.drawable.stat_sys_data_4g_downlink_2));
					resparam.res.setReplacement(targetpkg, "drawable", "stat_sys_data_4g_downlink_3", modRes.fwd(R.drawable.stat_sys_data_4g_downlink_3));
					resparam.res.setReplacement(targetpkg, "drawable", "stat_sys_data_4g_uplink_1", modRes.fwd(R.drawable.stat_sys_data_4g_uplink_1));
					resparam.res.setReplacement(targetpkg, "drawable", "stat_sys_data_4g_uplink_2", modRes.fwd(R.drawable.stat_sys_data_4g_uplink_2));
					resparam.res.setReplacement(targetpkg, "drawable", "stat_sys_data_4g_uplink_3", modRes.fwd(R.drawable.stat_sys_data_4g_uplink_3));
					
					resparam.res.setReplacement(targetpkg, "drawable", "stat_sys_data_4g_lte_capability", modRes.fwd(R.drawable.stat_sys_data_4g_lte_capability));
					resparam.res.setReplacement(targetpkg, "drawable", "stat_sys_data_4g_lte_connected", modRes.fwd(R.drawable.stat_sys_data_4g_lte_connected));
					resparam.res.setReplacement(targetpkg, "drawable", "stat_sys_data_4g_lte_downlink_1", modRes.fwd(R.drawable.stat_sys_data_4g_lte_downlink_1));
					resparam.res.setReplacement(targetpkg, "drawable", "stat_sys_data_4g_lte_downlink_2", modRes.fwd(R.drawable.stat_sys_data_4g_lte_downlink_2));
					resparam.res.setReplacement(targetpkg, "drawable", "stat_sys_data_4g_lte_downlink_3", modRes.fwd(R.drawable.stat_sys_data_4g_lte_downlink_3));
					resparam.res.setReplacement(targetpkg, "drawable", "stat_sys_data_4g_lte_uplink_1", modRes.fwd(R.drawable.stat_sys_data_4g_lte_uplink_1));
					resparam.res.setReplacement(targetpkg, "drawable", "stat_sys_data_4g_lte_uplink_2", modRes.fwd(R.drawable.stat_sys_data_4g_lte_uplink_2));
					resparam.res.setReplacement(targetpkg, "drawable", "stat_sys_data_4g_lte_uplink_3", modRes.fwd(R.drawable.stat_sys_data_4g_lte_uplink_3));
					
					resparam.res.setReplacement(targetpkg, "drawable", "stat_sys_data_e_capability", modRes.fwd(R.drawable.stat_sys_data_e_capability));
					resparam.res.setReplacement(targetpkg, "drawable", "stat_sys_data_e_connected", modRes.fwd(R.drawable.stat_sys_data_e_connected));
					resparam.res.setReplacement(targetpkg, "drawable", "stat_sys_data_e_downlink_1", modRes.fwd(R.drawable.stat_sys_data_e_downlink_1));
					resparam.res.setReplacement(targetpkg, "drawable", "stat_sys_data_e_downlink_2", modRes.fwd(R.drawable.stat_sys_data_e_downlink_2));
					resparam.res.setReplacement(targetpkg, "drawable", "stat_sys_data_e_downlink_3", modRes.fwd(R.drawable.stat_sys_data_e_downlink_3));
					resparam.res.setReplacement(targetpkg, "drawable", "stat_sys_data_e_uplink_1", modRes.fwd(R.drawable.stat_sys_data_e_uplink_1));
					resparam.res.setReplacement(targetpkg, "drawable", "stat_sys_data_e_uplink_2", modRes.fwd(R.drawable.stat_sys_data_e_uplink_2));
					resparam.res.setReplacement(targetpkg, "drawable", "stat_sys_data_e_uplink_3", modRes.fwd(R.drawable.stat_sys_data_e_uplink_3));
					
					resparam.res.setReplacement(targetpkg, "drawable", "stat_sys_data_connected_3_5g_capability", modRes.fwd(R.drawable.stat_sys_data_connected_3_5g_capability));
					resparam.res.setReplacement(targetpkg, "drawable", "stat_sys_data_connected_3_5g", modRes.fwd(R.drawable.stat_sys_data_connected_3_5g));
					resparam.res.setReplacement(targetpkg, "drawable", "stat_sys_data_connected_3g", modRes.fwd(R.drawable.stat_sys_data_connected_3g));
					resparam.res.setReplacement(targetpkg, "drawable", "stat_sys_data_connected_4g", modRes.fwd(R.drawable.stat_sys_data_connected_4g));
					resparam.res.setReplacement(targetpkg, "drawable", "stat_sys_data_connected_h", modRes.fwd(R.drawable.stat_sys_data_connected_h));
					resparam.res.setReplacement(targetpkg, "drawable", "stat_sys_data_connected_lte_capability", modRes.fwd(R.drawable.stat_sys_data_connected_lte_capability));
					resparam.res.setReplacement(targetpkg, "drawable", "stat_sys_data_connected_lte", modRes.fwd(R.drawable.stat_sys_data_connected_lte));
				} catch (Throwable t) { XposedBridge.log(t); }
				if (pref.getString("intlstyle", "curved").equals("curved") && (!pref.getBoolean("hideatt", false))) {
					try {
						resparam.res.setReplacement(targetpkg, "drawable", "stat_sys_data_connected_3_5g_capability", modRes.fwd(R.drawable.stat_sys_data_connected_3_5g_capability));
						resparam.res.setReplacement(targetpkg, "drawable", "stat_sys_data_connected_e", modRes.fwd(R.drawable.stat_sys_data_connected_e));
						resparam.res.setReplacement(targetpkg, "drawable", "stat_sys_data_connected_g", modRes.fwd(R.drawable.stat_sys_data_connected_g));
						resparam.res.setReplacement(targetpkg, "drawable", "stat_sys_data_connected_3_5g", modRes.fwd(R.drawable.stat_sys_data_connected_3_5g));
						resparam.res.setReplacement(targetpkg, "drawable", "stat_sys_data_connected_3g", modRes.fwd(R.drawable.stat_sys_data_connected_3g));
						resparam.res.setReplacement(targetpkg, "drawable", "stat_sys_data_connected_4g", modRes.fwd(R.drawable.stat_sys_data_connected_4g));
						resparam.res.setReplacement(targetpkg, "drawable", "stat_sys_data_connected_h", modRes.fwd(R.drawable.stat_sys_data_connected_h));
						resparam.res.setReplacement(targetpkg, "drawable", "stat_sys_data_connected_hplus", modRes.fwd(R.drawable.stat_sys_data_connected_hplus));
						resparam.res.setReplacement(targetpkg, "drawable", "stat_sys_data_connected_lte_capability", modRes.fwd(R.drawable.stat_sys_data_connected_lte_capability));
						resparam.res.setReplacement(targetpkg, "drawable", "stat_sys_data_connected_lte", modRes.fwd(R.drawable.stat_sys_data_connected_lte));

						// default non-at&t icons -- normal intl
						resparam.res.setReplacement(targetpkg, "drawable", "stat_sys_data_in_e", modRes.fwd(R.drawable.intl_stat_sys_data_in_e));
						resparam.res.setReplacement(targetpkg, "drawable", "stat_sys_data_in_g", modRes.fwd(R.drawable.intl_stat_sys_data_in_g));
						resparam.res.setReplacement(targetpkg, "drawable", "stat_sys_data_in_h", modRes.fwd(R.drawable.intl_stat_sys_data_in_h));
						resparam.res.setReplacement(targetpkg, "drawable", "stat_sys_data_in_hplus", modRes.fwd(R.drawable.intl_stat_sys_data_in_hplus));
						resparam.res.setReplacement(targetpkg, "drawable", "stat_sys_data_in_3g", modRes.fwd(R.drawable.intl_stat_sys_data_in_3g));
						resparam.res.setReplacement(targetpkg, "drawable", "stat_sys_data_in_4g", modRes.fwd(R.drawable.intl_stat_sys_data_in_4g));
						resparam.res.setReplacement(targetpkg, "drawable", "stat_sys_data_in_lte", modRes.fwd(R.drawable.intl_stat_sys_data_in_lte));
						
						resparam.res.setReplacement(targetpkg, "drawable", "stat_sys_data_out_e", modRes.fwd(R.drawable.intl_stat_sys_data_out_e));
						resparam.res.setReplacement(targetpkg, "drawable", "stat_sys_data_out_g", modRes.fwd(R.drawable.intl_stat_sys_data_out_g));
						resparam.res.setReplacement(targetpkg, "drawable", "stat_sys_data_out_h", modRes.fwd(R.drawable.intl_stat_sys_data_out_h));
						resparam.res.setReplacement(targetpkg, "drawable", "stat_sys_data_out_hplus", modRes.fwd(R.drawable.intl_stat_sys_data_out_hplus));
						resparam.res.setReplacement(targetpkg, "drawable", "stat_sys_data_out_3g", modRes.fwd(R.drawable.intl_stat_sys_data_out_3g));
						resparam.res.setReplacement(targetpkg, "drawable", "stat_sys_data_out_4g", modRes.fwd(R.drawable.intl_stat_sys_data_out_4g));
						resparam.res.setReplacement(targetpkg, "drawable", "stat_sys_data_out_lte", modRes.fwd(R.drawable.intl_stat_sys_data_out_lte));
						
						resparam.res.setReplacement(targetpkg, "drawable", "stat_sys_data_inandout_e", modRes.fwd(R.drawable.intl_stat_sys_data_inandout_e));
						resparam.res.setReplacement(targetpkg, "drawable", "stat_sys_data_inandout_g", modRes.fwd(R.drawable.intl_stat_sys_data_inandout_g));
						resparam.res.setReplacement(targetpkg, "drawable", "stat_sys_data_inandout_h", modRes.fwd(R.drawable.intl_stat_sys_data_inandout_h));
						resparam.res.setReplacement(targetpkg, "drawable", "stat_sys_data_inandout_hplus", modRes.fwd(R.drawable.intl_stat_sys_data_inandout_hplus));
						resparam.res.setReplacement(targetpkg, "drawable", "stat_sys_data_inandout_3g", modRes.fwd(R.drawable.intl_stat_sys_data_inandout_3g));
						resparam.res.setReplacement(targetpkg, "drawable", "stat_sys_data_inandout_4g", modRes.fwd(R.drawable.intl_stat_sys_data_inandout_4g));
						resparam.res.setReplacement(targetpkg, "drawable", "stat_sys_data_inandout_lte", modRes.fwd(R.drawable.intl_stat_sys_data_inandout_lte));
					} catch (Throwable t) { XposedBridge.log(t); }
				} else if (pref.getString("intlstyle", "curved").equals("curved") && (pref.getBoolean("hideatt", false))) {
					try {
						resparam.res.setReplacement(targetpkg, "drawable", "stat_sys_data_connected_3_5g_capability", modRes.fwd(R.drawable.stat_sys_data_connected_3_5g_capability));
						resparam.res.setReplacement(targetpkg, "drawable", "stat_sys_data_connected_e", modRes.fwd(R.drawable.stat_sys_data_connected_e));
						resparam.res.setReplacement(targetpkg, "drawable", "stat_sys_data_connected_g", modRes.fwd(R.drawable.stat_sys_data_connected_g));
						resparam.res.setReplacement(targetpkg, "drawable", "stat_sys_data_connected_3_5g", modRes.fwd(R.drawable.stat_sys_data_connected_3_5g));
						resparam.res.setReplacement(targetpkg, "drawable", "stat_sys_data_connected_3g", modRes.fwd(R.drawable.stat_sys_data_connected_4g));
						resparam.res.setReplacement(targetpkg, "drawable", "stat_sys_data_connected_4g", modRes.fwd(R.drawable.stat_sys_data_connected_4g));
						resparam.res.setReplacement(targetpkg, "drawable", "stat_sys_data_connected_h", modRes.fwd(R.drawable.stat_sys_data_connected_4g));
						resparam.res.setReplacement(targetpkg, "drawable", "stat_sys_data_connected_hplus", modRes.fwd(R.drawable.stat_sys_data_connected_4g));
						resparam.res.setReplacement(targetpkg, "drawable", "stat_sys_data_connected_lte_capability", modRes.fwd(R.drawable.stat_sys_data_connected_lte_capability));
						resparam.res.setReplacement(targetpkg, "drawable", "stat_sys_data_connected_lte", modRes.fwd(R.drawable.stat_sys_data_connected_lte));

						// default non-at&t icons -- att icons for intl -- kludgy hack to auto-hide data icon
						resparam.res.setReplacement(targetpkg, "drawable", "stat_sys_data_in_e", modRes.fwd(R.drawable.intl_stat_sys_data_in_e));
						resparam.res.setReplacement(targetpkg, "drawable", "stat_sys_data_in_g", modRes.fwd(R.drawable.intl_stat_sys_data_in_g));
						resparam.res.setReplacement(targetpkg, "drawable", "stat_sys_data_in_h", modRes.fwd(R.drawable.intl_stat_sys_data_in_4g));
						resparam.res.setReplacement(targetpkg, "drawable", "stat_sys_data_in_hplus", modRes.fwd(R.drawable.intl_stat_sys_data_in_4g));
						resparam.res.setReplacement(targetpkg, "drawable", "stat_sys_data_in_3g", modRes.fwd(R.drawable.intl_stat_sys_data_in_4g));
						resparam.res.setReplacement(targetpkg, "drawable", "stat_sys_data_in_4g", modRes.fwd(R.drawable.intl_stat_sys_data_in_4g));
						resparam.res.setReplacement(targetpkg, "drawable", "stat_sys_data_in_lte", modRes.fwd(R.drawable.intl_stat_sys_data_in_lte));
						
						resparam.res.setReplacement(targetpkg, "drawable", "stat_sys_data_out_e", modRes.fwd(R.drawable.intl_stat_sys_data_out_e));
						resparam.res.setReplacement(targetpkg, "drawable", "stat_sys_data_out_g", modRes.fwd(R.drawable.intl_stat_sys_data_out_g));
						resparam.res.setReplacement(targetpkg, "drawable", "stat_sys_data_out_h", modRes.fwd(R.drawable.intl_stat_sys_data_out_4g));
						resparam.res.setReplacement(targetpkg, "drawable", "stat_sys_data_out_hplus", modRes.fwd(R.drawable.intl_stat_sys_data_out_4g));
						resparam.res.setReplacement(targetpkg, "drawable", "stat_sys_data_out_3g", modRes.fwd(R.drawable.intl_stat_sys_data_out_4g));
						resparam.res.setReplacement(targetpkg, "drawable", "stat_sys_data_out_4g", modRes.fwd(R.drawable.intl_stat_sys_data_out_4g));
						resparam.res.setReplacement(targetpkg, "drawable", "stat_sys_data_out_lte", modRes.fwd(R.drawable.intl_stat_sys_data_out_lte));
						
						resparam.res.setReplacement(targetpkg, "drawable", "stat_sys_data_inandout_e", modRes.fwd(R.drawable.intl_stat_sys_data_inandout_e));
						resparam.res.setReplacement(targetpkg, "drawable", "stat_sys_data_inandout_g", modRes.fwd(R.drawable.intl_stat_sys_data_inandout_g));
						resparam.res.setReplacement(targetpkg, "drawable", "stat_sys_data_inandout_h", modRes.fwd(R.drawable.intl_stat_sys_data_inandout_4g));
						resparam.res.setReplacement(targetpkg, "drawable", "stat_sys_data_inandout_hplus", modRes.fwd(R.drawable.intl_stat_sys_data_inandout_4g));
						resparam.res.setReplacement(targetpkg, "drawable", "stat_sys_data_inandout_3g", modRes.fwd(R.drawable.intl_stat_sys_data_inandout_4g));
						resparam.res.setReplacement(targetpkg, "drawable", "stat_sys_data_inandout_4g", modRes.fwd(R.drawable.intl_stat_sys_data_inandout_4g));
						resparam.res.setReplacement(targetpkg, "drawable", "stat_sys_data_inandout_lte", modRes.fwd(R.drawable.intl_stat_sys_data_inandout_lte));
					} catch (Throwable t) { XposedBridge.log(t); }
				} else if (pref.getString("intlstyle", "curved").equals("arrows") && (!pref.getBoolean("hideatt", false))) {
					try {
						resparam.res.setReplacement(targetpkg, "drawable", "stat_sys_data_connected_3_5g_capability", modRes.fwd(R.drawable.stat_sys_data_connected_3_5g_capability));
						resparam.res.setReplacement(targetpkg, "drawable", "stat_sys_data_connected_e", modRes.fwd(R.drawable.intl_theme1_stat_sys_data_connected_e));
						resparam.res.setReplacement(targetpkg, "drawable", "stat_sys_data_connected_g", modRes.fwd(R.drawable.intl_theme1_stat_sys_data_connected_g));
						resparam.res.setReplacement(targetpkg, "drawable", "stat_sys_data_connected_3_5g", modRes.fwd(R.drawable.intl_theme1_stat_sys_data_connected_3_5g));
						resparam.res.setReplacement(targetpkg, "drawable", "stat_sys_data_connected_3g", modRes.fwd(R.drawable.intl_theme1_stat_sys_data_connected_3g));
						resparam.res.setReplacement(targetpkg, "drawable", "stat_sys_data_connected_4g", modRes.fwd(R.drawable.intl_theme1_stat_sys_data_connected_4g));
						resparam.res.setReplacement(targetpkg, "drawable", "stat_sys_data_connected_h", modRes.fwd(R.drawable.intl_theme1_stat_sys_data_connected_h));
						resparam.res.setReplacement(targetpkg, "drawable", "stat_sys_data_connected_hplus", modRes.fwd(R.drawable.intl_theme1_stat_sys_data_connected_hplus));
						resparam.res.setReplacement(targetpkg, "drawable", "stat_sys_data_connected_lte_capability", modRes.fwd(R.drawable.stat_sys_data_connected_lte_capability));
						resparam.res.setReplacement(targetpkg, "drawable", "stat_sys_data_connected_lte", modRes.fwd(R.drawable.intl_theme1_stat_sys_data_connected_lte));

						// default non-at&t icons
						resparam.res.setReplacement(targetpkg, "drawable", "stat_sys_data_in_e", modRes.fwd(R.drawable.intl_theme1_stat_sys_data_in_e));
						resparam.res.setReplacement(targetpkg, "drawable", "stat_sys_data_in_g", modRes.fwd(R.drawable.intl_theme1_stat_sys_data_in_g));
						resparam.res.setReplacement(targetpkg, "drawable", "stat_sys_data_in_h", modRes.fwd(R.drawable.intl_theme1_stat_sys_data_in_h));
						resparam.res.setReplacement(targetpkg, "drawable", "stat_sys_data_in_hplus", modRes.fwd(R.drawable.intl_theme1_stat_sys_data_in_hplus));
						resparam.res.setReplacement(targetpkg, "drawable", "stat_sys_data_in_3g", modRes.fwd(R.drawable.intl_theme1_stat_sys_data_in_3g));
						resparam.res.setReplacement(targetpkg, "drawable", "stat_sys_data_in_4g", modRes.fwd(R.drawable.intl_theme1_stat_sys_data_in_4g));
						resparam.res.setReplacement(targetpkg, "drawable", "stat_sys_data_in_lte", modRes.fwd(R.drawable.intl_theme1_stat_sys_data_in_lte));
						
						resparam.res.setReplacement(targetpkg, "drawable", "stat_sys_data_out_e", modRes.fwd(R.drawable.intl_theme1_stat_sys_data_out_e));
						resparam.res.setReplacement(targetpkg, "drawable", "stat_sys_data_out_g", modRes.fwd(R.drawable.intl_theme1_stat_sys_data_out_g));
						resparam.res.setReplacement(targetpkg, "drawable", "stat_sys_data_out_h", modRes.fwd(R.drawable.intl_theme1_stat_sys_data_out_h));
						resparam.res.setReplacement(targetpkg, "drawable", "stat_sys_data_out_hplus", modRes.fwd(R.drawable.intl_theme1_stat_sys_data_out_hplus));
						resparam.res.setReplacement(targetpkg, "drawable", "stat_sys_data_out_3g", modRes.fwd(R.drawable.intl_theme1_stat_sys_data_out_3g));
						resparam.res.setReplacement(targetpkg, "drawable", "stat_sys_data_out_4g", modRes.fwd(R.drawable.intl_theme1_stat_sys_data_out_4g));
						resparam.res.setReplacement(targetpkg, "drawable", "stat_sys_data_out_lte", modRes.fwd(R.drawable.intl_theme1_stat_sys_data_out_lte));
						
						resparam.res.setReplacement(targetpkg, "drawable", "stat_sys_data_inandout_e", modRes.fwd(R.drawable.intl_theme1_stat_sys_data_inandout_e));
						resparam.res.setReplacement(targetpkg, "drawable", "stat_sys_data_inandout_g", modRes.fwd(R.drawable.intl_theme1_stat_sys_data_inandout_g));
						resparam.res.setReplacement(targetpkg, "drawable", "stat_sys_data_inandout_h", modRes.fwd(R.drawable.intl_theme1_stat_sys_data_inandout_h));
						resparam.res.setReplacement(targetpkg, "drawable", "stat_sys_data_inandout_hplus", modRes.fwd(R.drawable.intl_theme1_stat_sys_data_inandout_hplus));
						resparam.res.setReplacement(targetpkg, "drawable", "stat_sys_data_inandout_3g", modRes.fwd(R.drawable.intl_theme1_stat_sys_data_inandout_3g));
						resparam.res.setReplacement(targetpkg, "drawable", "stat_sys_data_inandout_4g", modRes.fwd(R.drawable.intl_theme1_stat_sys_data_inandout_4g));
						resparam.res.setReplacement(targetpkg, "drawable", "stat_sys_data_inandout_lte", modRes.fwd(R.drawable.intl_theme1_stat_sys_data_inandout_lte));
						} catch (Throwable t) { XposedBridge.log(t); }
				} else if (pref.getString("intlstyle", "curved").equals("arrows") && (pref.getBoolean("hideatt", false))) {
					try {
						resparam.res.setReplacement(targetpkg, "drawable", "stat_sys_data_connected_3_5g_capability", modRes.fwd(R.drawable.stat_sys_data_connected_3_5g_capability));
						resparam.res.setReplacement(targetpkg, "drawable", "stat_sys_data_connected_e", modRes.fwd(R.drawable.stat_sys_data_connected_e));
						resparam.res.setReplacement(targetpkg, "drawable", "stat_sys_data_connected_g", modRes.fwd(R.drawable.stat_sys_data_connected_g));
						resparam.res.setReplacement(targetpkg, "drawable", "stat_sys_data_connected_3_5g", modRes.fwd(R.drawable.stat_sys_data_connected_3_5g));
						resparam.res.setReplacement(targetpkg, "drawable", "stat_sys_data_connected_3g", modRes.fwd(R.drawable.stat_sys_data_connected_4g));
						resparam.res.setReplacement(targetpkg, "drawable", "stat_sys_data_connected_4g", modRes.fwd(R.drawable.stat_sys_data_connected_4g));
						resparam.res.setReplacement(targetpkg, "drawable", "stat_sys_data_connected_h", modRes.fwd(R.drawable.stat_sys_data_connected_4g));
						resparam.res.setReplacement(targetpkg, "drawable", "stat_sys_data_connected_hplus", modRes.fwd(R.drawable.stat_sys_data_connected_4g));
						resparam.res.setReplacement(targetpkg, "drawable", "stat_sys_data_connected_lte_capability", modRes.fwd(R.drawable.stat_sys_data_connected_lte_capability));
						resparam.res.setReplacement(targetpkg, "drawable", "stat_sys_data_connected_lte", modRes.fwd(R.drawable.stat_sys_data_connected_lte));

						// default non-at&t icons -- att icons for intl -- kludgy hack to auto-hide data icon
						resparam.res.setReplacement(targetpkg, "drawable", "stat_sys_data_in_e", modRes.fwd(R.drawable.intl_stat_sys_data_in_e));
						resparam.res.setReplacement(targetpkg, "drawable", "stat_sys_data_in_g", modRes.fwd(R.drawable.intl_stat_sys_data_in_g));
						resparam.res.setReplacement(targetpkg, "drawable", "stat_sys_data_in_h", modRes.fwd(R.drawable.intl_stat_sys_data_in_4g));
						resparam.res.setReplacement(targetpkg, "drawable", "stat_sys_data_in_hplus", modRes.fwd(R.drawable.intl_stat_sys_data_in_4g));
						resparam.res.setReplacement(targetpkg, "drawable", "stat_sys_data_in_3g", modRes.fwd(R.drawable.intl_stat_sys_data_in_4g));
						resparam.res.setReplacement(targetpkg, "drawable", "stat_sys_data_in_4g", modRes.fwd(R.drawable.intl_stat_sys_data_in_4g));
						resparam.res.setReplacement(targetpkg, "drawable", "stat_sys_data_in_lte", modRes.fwd(R.drawable.intl_stat_sys_data_in_lte));
						
						resparam.res.setReplacement(targetpkg, "drawable", "stat_sys_data_out_e", modRes.fwd(R.drawable.intl_stat_sys_data_out_e));
						resparam.res.setReplacement(targetpkg, "drawable", "stat_sys_data_out_g", modRes.fwd(R.drawable.intl_stat_sys_data_out_g));
						resparam.res.setReplacement(targetpkg, "drawable", "stat_sys_data_out_h", modRes.fwd(R.drawable.intl_stat_sys_data_out_4g));
						resparam.res.setReplacement(targetpkg, "drawable", "stat_sys_data_out_hplus", modRes.fwd(R.drawable.intl_stat_sys_data_out_4g));
						resparam.res.setReplacement(targetpkg, "drawable", "stat_sys_data_out_3g", modRes.fwd(R.drawable.intl_stat_sys_data_out_4g));
						resparam.res.setReplacement(targetpkg, "drawable", "stat_sys_data_out_4g", modRes.fwd(R.drawable.intl_stat_sys_data_out_4g));
						resparam.res.setReplacement(targetpkg, "drawable", "stat_sys_data_out_lte", modRes.fwd(R.drawable.intl_stat_sys_data_out_lte));
						
						resparam.res.setReplacement(targetpkg, "drawable", "stat_sys_data_inandout_e", modRes.fwd(R.drawable.intl_stat_sys_data_inandout_e));
						resparam.res.setReplacement(targetpkg, "drawable", "stat_sys_data_inandout_g", modRes.fwd(R.drawable.intl_stat_sys_data_inandout_g));
						resparam.res.setReplacement(targetpkg, "drawable", "stat_sys_data_inandout_h", modRes.fwd(R.drawable.intl_stat_sys_data_inandout_4g));
						resparam.res.setReplacement(targetpkg, "drawable", "stat_sys_data_inandout_hplus", modRes.fwd(R.drawable.intl_stat_sys_data_inandout_4g));
						resparam.res.setReplacement(targetpkg, "drawable", "stat_sys_data_inandout_3g", modRes.fwd(R.drawable.intl_stat_sys_data_inandout_4g));
						resparam.res.setReplacement(targetpkg, "drawable", "stat_sys_data_inandout_4g", modRes.fwd(R.drawable.intl_stat_sys_data_inandout_4g));
						resparam.res.setReplacement(targetpkg, "drawable", "stat_sys_data_inandout_lte", modRes.fwd(R.drawable.intl_stat_sys_data_inandout_lte));
					} catch (Throwable t) { XposedBridge.log(t); }
				}
			} else {
				// orange icon set
				if (pref.getString("intlstyle", "curved").equals("curved") && (pref.getBoolean("hideatt", false))) {
					try {
						// replace default TmoUS icons
						resparam.res.setReplacement(targetpkg, "drawable", "stat_sys_data_connected_tmous_3g", modRes.fwd(R.drawable.stat_sys_data_connected_tmous_3g));
						resparam.res.setReplacement(targetpkg, "drawable", "stat_sys_data_connected_tmous_4g", modRes.fwd(R.drawable.stat_sys_data_connected_tmous_4g));
						resparam.res.setReplacement(targetpkg, "drawable", "stat_sys_data_connected_tmous_lte", modRes.fwd(R.drawable.stat_sys_data_connected_tmous_lte));
						
						resparam.res.setReplacement(targetpkg, "drawable", "stat_sys_data_in_tmous_3g", modRes.fwd(R.drawable.stat_sys_data_in_tmous_3g));
						resparam.res.setReplacement(targetpkg, "drawable", "stat_sys_data_in_tmous_4g", modRes.fwd(R.drawable.stat_sys_data_in_tmous_4g));
						resparam.res.setReplacement(targetpkg, "drawable", "stat_sys_data_in_tmous_lte", modRes.fwd(R.drawable.stat_sys_data_in_tmous_lte));
						
						resparam.res.setReplacement(targetpkg, "drawable", "stat_sys_data_inandout_tmous_3g", modRes.fwd(R.drawable.stat_sys_data_inandout_tmous_3g));
						resparam.res.setReplacement(targetpkg, "drawable", "stat_sys_data_inandout_tmous_4g", modRes.fwd(R.drawable.stat_sys_data_inandout_tmous_4g));
						resparam.res.setReplacement(targetpkg, "drawable", "stat_sys_data_inandout_tmous_lte", modRes.fwd(R.drawable.stat_sys_data_inandout_tmous_lte));
						
						resparam.res.setReplacement(targetpkg, "drawable", "stat_sys_data_out_tmous_3g", modRes.fwd(R.drawable.stat_sys_data_out_tmous_3g));
						resparam.res.setReplacement(targetpkg, "drawable", "stat_sys_data_out_tmous_4g", modRes.fwd(R.drawable.stat_sys_data_out_tmous_4g));
						resparam.res.setReplacement(targetpkg, "drawable", "stat_sys_data_out_tmous_lte", modRes.fwd(R.drawable.stat_sys_data_out_tmous_lte));
						
						resparam.res.setReplacement(targetpkg, "drawable", "stat_sys_data_in_e", modRes.fwd(R.drawable.or_stat_sys_data_in_e));
						resparam.res.setReplacement(targetpkg, "drawable", "stat_sys_data_in_h", modRes.fwd(R.drawable.or_stat_sys_data_in_h));
						resparam.res.setReplacement(targetpkg, "drawable", "stat_sys_data_in_3g", modRes.fwd(R.drawable.or_stat_sys_data_in_3g));
						resparam.res.setReplacement(targetpkg, "drawable", "stat_sys_data_in_4g", modRes.fwd(R.drawable.or_stat_sys_data_in_4g));
						resparam.res.setReplacement(targetpkg, "drawable", "stat_sys_data_in_lte", modRes.fwd(R.drawable.or_stat_sys_data_in_lte));
						
						resparam.res.setReplacement(targetpkg, "drawable", "stat_sys_data_out_e", modRes.fwd(R.drawable.or_stat_sys_data_out_e));
						resparam.res.setReplacement(targetpkg, "drawable", "stat_sys_data_out_h", modRes.fwd(R.drawable.or_stat_sys_data_out_h));
						resparam.res.setReplacement(targetpkg, "drawable", "stat_sys_data_out_3g", modRes.fwd(R.drawable.or_stat_sys_data_out_3g));
						resparam.res.setReplacement(targetpkg, "drawable", "stat_sys_data_out_4g", modRes.fwd(R.drawable.or_stat_sys_data_out_4g));
						resparam.res.setReplacement(targetpkg, "drawable", "stat_sys_data_out_lte", modRes.fwd(R.drawable.or_stat_sys_data_out_lte));
						
						resparam.res.setReplacement(targetpkg, "drawable", "stat_sys_data_inandout_e", modRes.fwd(R.drawable.or_stat_sys_data_inandout_e));
						resparam.res.setReplacement(targetpkg, "drawable", "stat_sys_data_inandout_h", modRes.fwd(R.drawable.or_stat_sys_data_inandout_h));
						resparam.res.setReplacement(targetpkg, "drawable", "stat_sys_data_inandout_3g", modRes.fwd(R.drawable.or_stat_sys_data_inandout_3g));
						resparam.res.setReplacement(targetpkg, "drawable", "stat_sys_data_inandout_4g", modRes.fwd(R.drawable.or_stat_sys_data_inandout_4g));
						resparam.res.setReplacement(targetpkg, "drawable", "stat_sys_data_inandout_lte", modRes.fwd(R.drawable.or_stat_sys_data_inandout_lte));
						
						resparam.res.setReplacement(targetpkg, "drawable", "stat_sys_data_4g_capability", modRes.fwd(R.drawable.orange_stat_sys_data_4g_capability));
						resparam.res.setReplacement(targetpkg, "drawable", "stat_sys_data_4g_connected", modRes.fwd(R.drawable.orange_stat_sys_data_4g_connected));
						resparam.res.setReplacement(targetpkg, "drawable", "stat_sys_data_4g_lte_capability", modRes.fwd(R.drawable.orange_stat_sys_data_4g_lte_capability));
												
						resparam.res.setReplacement(targetpkg, "drawable", "stat_sys_data_connected_3_5g_capability", modRes.fwd(R.drawable.orange_stat_sys_data_connected_3_5g_capability));
						resparam.res.setReplacement(targetpkg, "drawable", "stat_sys_data_connected_3_5g", modRes.fwd(R.drawable.orange_stat_sys_data_connected_3_5g));
						resparam.res.setReplacement(targetpkg, "drawable", "stat_sys_data_connected_3g", modRes.fwd(R.drawable.orange_stat_sys_data_connected_3g));
						resparam.res.setReplacement(targetpkg, "drawable", "stat_sys_data_connected_h", modRes.fwd(R.drawable.orange_stat_sys_data_connected_h));
						resparam.res.setReplacement(targetpkg, "drawable", "stat_sys_data_connected_4g", modRes.fwd(R.drawable.orange_stat_sys_data_connected_4g));
						resparam.res.setReplacement(targetpkg, "drawable", "stat_sys_data_connected_e", modRes.fwd(R.drawable.orange_stat_sys_data_e_connected));
						resparam.res.setReplacement(targetpkg, "drawable", "stat_sys_data_connected_lte_capability", modRes.fwd(R.drawable.orange_stat_sys_data_connected_lte_capability));
						resparam.res.setReplacement(targetpkg, "drawable", "stat_sys_data_connected_lte", modRes.fwd(R.drawable.orange_stat_sys_data_connected_lte));
					} catch (Throwable t) { XposedBridge.log(t); }
				} else if (pref.getString("intlstyle", "curved").equals("arrows") && (pref.getBoolean("hideatt", false))) {
					try {
						// default intl
						resparam.res.setReplacement(targetpkg, "drawable", "stat_sys_data_4g_capability", modRes.fwd(R.drawable.orange_stat_sys_data_4g_capability));
						resparam.res.setReplacement(targetpkg, "drawable", "stat_sys_data_4g_connected", modRes.fwd(R.drawable.orange_stat_sys_data_4g_connected));
						resparam.res.setReplacement(targetpkg, "drawable", "stat_sys_data_4g_lte_capability", modRes.fwd(R.drawable.orange_stat_sys_data_4g_lte_capability));
												
						resparam.res.setReplacement(targetpkg, "drawable", "stat_sys_data_e_capability", modRes.fwd(R.drawable.orange_stat_sys_data_e_capability));
						resparam.res.setReplacement(targetpkg, "drawable", "stat_sys_data_e_connected", modRes.fwd(R.drawable.orange_stat_sys_data_e_connected));
						
						resparam.res.setReplacement(targetpkg, "drawable", "stat_sys_data_connected_3_5g_capability", modRes.fwd(R.drawable.orange_stat_sys_data_connected_3_5g_capability));
						resparam.res.setReplacement(targetpkg, "drawable", "stat_sys_data_connected_3_5g", modRes.fwd(R.drawable.orange_stat_sys_data_connected_3_5g));
						resparam.res.setReplacement(targetpkg, "drawable", "stat_sys_data_connected_3g", modRes.fwd(R.drawable.orange_stat_sys_data_connected_3g));
						resparam.res.setReplacement(targetpkg, "drawable", "stat_sys_data_connected_h", modRes.fwd(R.drawable.orange_stat_sys_data_connected_h));
						resparam.res.setReplacement(targetpkg, "drawable", "stat_sys_data_connected_e", modRes.fwd(R.drawable.orange_stat_sys_data_e_connected));
						resparam.res.setReplacement(targetpkg, "drawable", "stat_sys_data_connected_4g", modRes.fwd(R.drawable.orange_stat_sys_data_connected_4g));

						resparam.res.setReplacement(targetpkg, "drawable", "stat_sys_data_connected_lte_capability", modRes.fwd(R.drawable.orange_stat_sys_data_connected_lte_capability));
						resparam.res.setReplacement(targetpkg, "drawable", "stat_sys_data_connected_lte", modRes.fwd(R.drawable.orange_stat_sys_data_connected_lte));

						resparam.res.setReplacement(targetpkg, "drawable", "stat_sys_data_in_e", modRes.fwd(R.drawable.orange_stat_sys_data_in_e));
						resparam.res.setReplacement(targetpkg, "drawable", "stat_sys_data_in_h", modRes.fwd(R.drawable.orange_stat_sys_data_in_h));
						resparam.res.setReplacement(targetpkg, "drawable", "stat_sys_data_in_4g", modRes.fwd(R.drawable.orange_stat_sys_data_in_4g));
						resparam.res.setReplacement(targetpkg, "drawable", "stat_sys_data_in_lte", modRes.fwd(R.drawable.orange_stat_sys_data_in_lte));
						
						resparam.res.setReplacement(targetpkg, "drawable", "stat_sys_data_out_e", modRes.fwd(R.drawable.orange_stat_sys_data_out_e));
						resparam.res.setReplacement(targetpkg, "drawable", "stat_sys_data_out_h", modRes.fwd(R.drawable.orange_stat_sys_data_out_h));
						resparam.res.setReplacement(targetpkg, "drawable", "stat_sys_data_out_4g", modRes.fwd(R.drawable.orange_stat_sys_data_out_4g));
						resparam.res.setReplacement(targetpkg, "drawable", "stat_sys_data_out_lte", modRes.fwd(R.drawable.orange_stat_sys_data_out_lte));
						
						resparam.res.setReplacement(targetpkg, "drawable", "stat_sys_data_inandout_e", modRes.fwd(R.drawable.orange_stat_sys_data_inandout_e));
						resparam.res.setReplacement(targetpkg, "drawable", "stat_sys_data_inandout_h", modRes.fwd(R.drawable.orange_stat_sys_data_inandout_h));
						resparam.res.setReplacement(targetpkg, "drawable", "stat_sys_data_inandout_4g", modRes.fwd(R.drawable.orange_stat_sys_data_inandout_4g));
						resparam.res.setReplacement(targetpkg, "drawable", "stat_sys_data_inandout_lte", modRes.fwd(R.drawable.orange_stat_sys_data_inandout_lte));
					} catch (Throwable t) { XposedBridge.log(t); }
				}
			}
		}
		

		if (pref.getBoolean("battery", true)) {
			if (pref.getString("battstyle", "percent").equals("percent")) {
				try {
					resparam.res.setReplacement(targetpkg, "drawable", "stat_sys_battery", modRes.fwd(R.drawable.stat_sys_battery));
					resparam.res.setReplacement(targetpkg, "drawable", "stat_sys_battery_charge", modRes.fwd(R.drawable.stat_sys_battery_charge));
				} catch (Throwable t) { XposedBridge.log(t); }
			} else if (pref.getString("battstyle", "percent").equals("circle")) {
				try {
					resparam.res.setReplacement(targetpkg, "drawable", "stat_sys_battery", modRes.fwd(R.drawable.theme2_stat_sys_battery));
					resparam.res.setReplacement(targetpkg, "drawable", "stat_sys_battery_charge", modRes.fwd(R.drawable.theme2_stat_sys_battery_charge));
				} catch (Throwable t) { XposedBridge.log(t); }
			}
		}
	}
}
