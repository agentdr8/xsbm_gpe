package com.dr8.sbicons.mod.hax;

import android.content.res.XModuleResources;

import com.dr8.sbicons.R;

import de.robv.android.xposed.XSharedPreferences;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.callbacks.XC_InitPackageResources;

public class MobileData {

	public static void initPackageResources(XSharedPreferences paramPrefs, XModuleResources modRes, XC_InitPackageResources.InitPackageResourcesParam resParam) {
		try {
			String targetpkg = "com.android.systemui";
			if(paramPrefs.getString("pref_carrier_color", "cyan").equals("cyan")) {
				try {
					// replace default TmoUS icons
					resParam.res.setReplacement(targetpkg, "drawable", "stat_sys_data_connected_tmous_3g", modRes.fwd(R.drawable.stat_sys_data_connected_tmous_3g));
					resParam.res.setReplacement(targetpkg, "drawable", "stat_sys_data_connected_tmous_4g", modRes.fwd(R.drawable.stat_sys_data_connected_tmous_4g));
					resParam.res.setReplacement(targetpkg, "drawable", "stat_sys_data_connected_tmous_lte", modRes.fwd(R.drawable.stat_sys_data_connected_tmous_lte));
					
					resParam.res.setReplacement(targetpkg, "drawable", "stat_sys_data_in_tmous_3g", modRes.fwd(R.drawable.stat_sys_data_in_tmous_3g));
					resParam.res.setReplacement(targetpkg, "drawable", "stat_sys_data_in_tmous_4g", modRes.fwd(R.drawable.stat_sys_data_in_tmous_4g));
					resParam.res.setReplacement(targetpkg, "drawable", "stat_sys_data_in_tmous_lte", modRes.fwd(R.drawable.stat_sys_data_in_tmous_lte));
					
					resParam.res.setReplacement(targetpkg, "drawable", "stat_sys_data_inandout_tmous_3g", modRes.fwd(R.drawable.stat_sys_data_inandout_tmous_3g));
					resParam.res.setReplacement(targetpkg, "drawable", "stat_sys_data_inandout_tmous_4g", modRes.fwd(R.drawable.stat_sys_data_inandout_tmous_4g));
					resParam.res.setReplacement(targetpkg, "drawable", "stat_sys_data_inandout_tmous_lte", modRes.fwd(R.drawable.stat_sys_data_inandout_tmous_lte));
					
					resParam.res.setReplacement(targetpkg, "drawable", "stat_sys_data_out_tmous_3g", modRes.fwd(R.drawable.stat_sys_data_out_tmous_3g));
					resParam.res.setReplacement(targetpkg, "drawable", "stat_sys_data_out_tmous_4g", modRes.fwd(R.drawable.stat_sys_data_out_tmous_4g));
					resParam.res.setReplacement(targetpkg, "drawable", "stat_sys_data_out_tmous_lte", modRes.fwd(R.drawable.stat_sys_data_out_tmous_lte));
					
					resParam.res.setReplacement(targetpkg, "drawable", "stat_sys_data_4g_capability", modRes.fwd(R.drawable.stat_sys_data_4g_capability));
					resParam.res.setReplacement(targetpkg, "drawable", "stat_sys_data_4g_connected", modRes.fwd(R.drawable.stat_sys_data_4g_connected));
					resParam.res.setReplacement(targetpkg, "drawable", "stat_sys_data_4g_downlink_1", modRes.fwd(R.drawable.stat_sys_data_4g_downlink_1));
					resParam.res.setReplacement(targetpkg, "drawable", "stat_sys_data_4g_downlink_2", modRes.fwd(R.drawable.stat_sys_data_4g_downlink_2));
					resParam.res.setReplacement(targetpkg, "drawable", "stat_sys_data_4g_downlink_3", modRes.fwd(R.drawable.stat_sys_data_4g_downlink_3));
					resParam.res.setReplacement(targetpkg, "drawable", "stat_sys_data_4g_uplink_1", modRes.fwd(R.drawable.stat_sys_data_4g_uplink_1));
					resParam.res.setReplacement(targetpkg, "drawable", "stat_sys_data_4g_uplink_2", modRes.fwd(R.drawable.stat_sys_data_4g_uplink_2));
					resParam.res.setReplacement(targetpkg, "drawable", "stat_sys_data_4g_uplink_3", modRes.fwd(R.drawable.stat_sys_data_4g_uplink_3));
					
					resParam.res.setReplacement(targetpkg, "drawable", "stat_sys_data_4g_lte_capability", modRes.fwd(R.drawable.stat_sys_data_4g_lte_capability));
					resParam.res.setReplacement(targetpkg, "drawable", "stat_sys_data_4g_lte_connected", modRes.fwd(R.drawable.stat_sys_data_4g_lte_connected));
					resParam.res.setReplacement(targetpkg, "drawable", "stat_sys_data_4g_lte_downlink_1", modRes.fwd(R.drawable.stat_sys_data_4g_lte_downlink_1));
					resParam.res.setReplacement(targetpkg, "drawable", "stat_sys_data_4g_lte_downlink_2", modRes.fwd(R.drawable.stat_sys_data_4g_lte_downlink_2));
					resParam.res.setReplacement(targetpkg, "drawable", "stat_sys_data_4g_lte_downlink_3", modRes.fwd(R.drawable.stat_sys_data_4g_lte_downlink_3));
					resParam.res.setReplacement(targetpkg, "drawable", "stat_sys_data_4g_lte_uplink_1", modRes.fwd(R.drawable.stat_sys_data_4g_lte_uplink_1));
					resParam.res.setReplacement(targetpkg, "drawable", "stat_sys_data_4g_lte_uplink_2", modRes.fwd(R.drawable.stat_sys_data_4g_lte_uplink_2));
					resParam.res.setReplacement(targetpkg, "drawable", "stat_sys_data_4g_lte_uplink_3", modRes.fwd(R.drawable.stat_sys_data_4g_lte_uplink_3));
					
					resParam.res.setReplacement(targetpkg, "drawable", "stat_sys_data_e_capability", modRes.fwd(R.drawable.stat_sys_data_e_capability));
					resParam.res.setReplacement(targetpkg, "drawable", "stat_sys_data_e_connected", modRes.fwd(R.drawable.stat_sys_data_e_connected));
					resParam.res.setReplacement(targetpkg, "drawable", "stat_sys_data_e_downlink_1", modRes.fwd(R.drawable.stat_sys_data_e_downlink_1));
					resParam.res.setReplacement(targetpkg, "drawable", "stat_sys_data_e_downlink_2", modRes.fwd(R.drawable.stat_sys_data_e_downlink_2));
					resParam.res.setReplacement(targetpkg, "drawable", "stat_sys_data_e_downlink_3", modRes.fwd(R.drawable.stat_sys_data_e_downlink_3));
					resParam.res.setReplacement(targetpkg, "drawable", "stat_sys_data_e_uplink_1", modRes.fwd(R.drawable.stat_sys_data_e_uplink_1));
					resParam.res.setReplacement(targetpkg, "drawable", "stat_sys_data_e_uplink_2", modRes.fwd(R.drawable.stat_sys_data_e_uplink_2));
					resParam.res.setReplacement(targetpkg, "drawable", "stat_sys_data_e_uplink_3", modRes.fwd(R.drawable.stat_sys_data_e_uplink_3));
					
					resParam.res.setReplacement(targetpkg, "drawable", "stat_sys_data_connected_3_5g_capability", modRes.fwd(R.drawable.stat_sys_data_connected_3_5g_capability));
					resParam.res.setReplacement(targetpkg, "drawable", "stat_sys_data_connected_3_5g", modRes.fwd(R.drawable.stat_sys_data_connected_3_5g));
					resParam.res.setReplacement(targetpkg, "drawable", "stat_sys_data_connected_3g", modRes.fwd(R.drawable.stat_sys_data_connected_3g));
					resParam.res.setReplacement(targetpkg, "drawable", "stat_sys_data_connected_4g", modRes.fwd(R.drawable.stat_sys_data_connected_4g));
					resParam.res.setReplacement(targetpkg, "drawable", "stat_sys_data_connected_h", modRes.fwd(R.drawable.stat_sys_data_connected_h));
					resParam.res.setReplacement(targetpkg, "drawable", "stat_sys_data_connected_lte_capability", modRes.fwd(R.drawable.stat_sys_data_connected_lte_capability));
					resParam.res.setReplacement(targetpkg, "drawable", "stat_sys_data_connected_lte", modRes.fwd(R.drawable.stat_sys_data_connected_lte));
				} catch (Throwable t) { XposedBridge.log(t); }
				if (paramPrefs.getString("intlstyle", "curved").equals("curved") && (!paramPrefs.getBoolean("hideatt", false))) {
					try {
						resParam.res.setReplacement(targetpkg, "drawable", "stat_sys_data_connected_3_5g_capability", modRes.fwd(R.drawable.stat_sys_data_connected_3_5g_capability));
						resParam.res.setReplacement(targetpkg, "drawable", "stat_sys_data_connected_e", modRes.fwd(R.drawable.stat_sys_data_connected_e));
						resParam.res.setReplacement(targetpkg, "drawable", "stat_sys_data_connected_g", modRes.fwd(R.drawable.stat_sys_data_connected_g));
						resParam.res.setReplacement(targetpkg, "drawable", "stat_sys_data_connected_3_5g", modRes.fwd(R.drawable.stat_sys_data_connected_3_5g));
						resParam.res.setReplacement(targetpkg, "drawable", "stat_sys_data_connected_3g", modRes.fwd(R.drawable.stat_sys_data_connected_3g));
						resParam.res.setReplacement(targetpkg, "drawable", "stat_sys_data_connected_4g", modRes.fwd(R.drawable.stat_sys_data_connected_4g));
						resParam.res.setReplacement(targetpkg, "drawable", "stat_sys_data_connected_h", modRes.fwd(R.drawable.stat_sys_data_connected_h));
						resParam.res.setReplacement(targetpkg, "drawable", "stat_sys_data_connected_hplus", modRes.fwd(R.drawable.stat_sys_data_connected_hplus));
						resParam.res.setReplacement(targetpkg, "drawable", "stat_sys_data_connected_lte_capability", modRes.fwd(R.drawable.stat_sys_data_connected_lte_capability));
						resParam.res.setReplacement(targetpkg, "drawable", "stat_sys_data_connected_lte", modRes.fwd(R.drawable.stat_sys_data_connected_lte));

						// default non-at&t icons -- normal intl
						resParam.res.setReplacement(targetpkg, "drawable", "stat_sys_data_in_e", modRes.fwd(R.drawable.intl_stat_sys_data_in_e));
						resParam.res.setReplacement(targetpkg, "drawable", "stat_sys_data_in_g", modRes.fwd(R.drawable.intl_stat_sys_data_in_g));
						resParam.res.setReplacement(targetpkg, "drawable", "stat_sys_data_in_h", modRes.fwd(R.drawable.intl_stat_sys_data_in_h));
						resParam.res.setReplacement(targetpkg, "drawable", "stat_sys_data_in_hplus", modRes.fwd(R.drawable.intl_stat_sys_data_in_hplus));
						resParam.res.setReplacement(targetpkg, "drawable", "stat_sys_data_in_3g", modRes.fwd(R.drawable.intl_stat_sys_data_in_3g));
						resParam.res.setReplacement(targetpkg, "drawable", "stat_sys_data_in_4g", modRes.fwd(R.drawable.intl_stat_sys_data_in_4g));
						resParam.res.setReplacement(targetpkg, "drawable", "stat_sys_data_in_lte", modRes.fwd(R.drawable.intl_stat_sys_data_in_lte));
						
						resParam.res.setReplacement(targetpkg, "drawable", "stat_sys_data_out_e", modRes.fwd(R.drawable.intl_stat_sys_data_out_e));
						resParam.res.setReplacement(targetpkg, "drawable", "stat_sys_data_out_g", modRes.fwd(R.drawable.intl_stat_sys_data_out_g));
						resParam.res.setReplacement(targetpkg, "drawable", "stat_sys_data_out_h", modRes.fwd(R.drawable.intl_stat_sys_data_out_h));
						resParam.res.setReplacement(targetpkg, "drawable", "stat_sys_data_out_hplus", modRes.fwd(R.drawable.intl_stat_sys_data_out_hplus));
						resParam.res.setReplacement(targetpkg, "drawable", "stat_sys_data_out_3g", modRes.fwd(R.drawable.intl_stat_sys_data_out_3g));
						resParam.res.setReplacement(targetpkg, "drawable", "stat_sys_data_out_4g", modRes.fwd(R.drawable.intl_stat_sys_data_out_4g));
						resParam.res.setReplacement(targetpkg, "drawable", "stat_sys_data_out_lte", modRes.fwd(R.drawable.intl_stat_sys_data_out_lte));
						
						resParam.res.setReplacement(targetpkg, "drawable", "stat_sys_data_inandout_e", modRes.fwd(R.drawable.intl_stat_sys_data_inandout_e));
						resParam.res.setReplacement(targetpkg, "drawable", "stat_sys_data_inandout_g", modRes.fwd(R.drawable.intl_stat_sys_data_inandout_g));
						resParam.res.setReplacement(targetpkg, "drawable", "stat_sys_data_inandout_h", modRes.fwd(R.drawable.intl_stat_sys_data_inandout_h));
						resParam.res.setReplacement(targetpkg, "drawable", "stat_sys_data_inandout_hplus", modRes.fwd(R.drawable.intl_stat_sys_data_inandout_hplus));
						resParam.res.setReplacement(targetpkg, "drawable", "stat_sys_data_inandout_3g", modRes.fwd(R.drawable.intl_stat_sys_data_inandout_3g));
						resParam.res.setReplacement(targetpkg, "drawable", "stat_sys_data_inandout_4g", modRes.fwd(R.drawable.intl_stat_sys_data_inandout_4g));
						resParam.res.setReplacement(targetpkg, "drawable", "stat_sys_data_inandout_lte", modRes.fwd(R.drawable.intl_stat_sys_data_inandout_lte));
					} catch (Throwable t) { XposedBridge.log(t); }
				} else if (paramPrefs.getString("intlstyle", "curved").equals("curved") && (paramPrefs.getBoolean("hideatt", false))) {
					try {
						resParam.res.setReplacement(targetpkg, "drawable", "stat_sys_data_connected_3_5g_capability", modRes.fwd(R.drawable.stat_sys_data_connected_3_5g_capability));
						resParam.res.setReplacement(targetpkg, "drawable", "stat_sys_data_connected_e", modRes.fwd(R.drawable.stat_sys_data_connected_e));
						resParam.res.setReplacement(targetpkg, "drawable", "stat_sys_data_connected_g", modRes.fwd(R.drawable.stat_sys_data_connected_g));
						resParam.res.setReplacement(targetpkg, "drawable", "stat_sys_data_connected_3_5g", modRes.fwd(R.drawable.stat_sys_data_connected_3_5g));
						resParam.res.setReplacement(targetpkg, "drawable", "stat_sys_data_connected_3g", modRes.fwd(R.drawable.stat_sys_data_connected_4g));
						resParam.res.setReplacement(targetpkg, "drawable", "stat_sys_data_connected_4g", modRes.fwd(R.drawable.stat_sys_data_connected_4g));
						resParam.res.setReplacement(targetpkg, "drawable", "stat_sys_data_connected_h", modRes.fwd(R.drawable.stat_sys_data_connected_4g));
						resParam.res.setReplacement(targetpkg, "drawable", "stat_sys_data_connected_hplus", modRes.fwd(R.drawable.stat_sys_data_connected_4g));
						resParam.res.setReplacement(targetpkg, "drawable", "stat_sys_data_connected_lte_capability", modRes.fwd(R.drawable.stat_sys_data_connected_lte_capability));
						resParam.res.setReplacement(targetpkg, "drawable", "stat_sys_data_connected_lte", modRes.fwd(R.drawable.stat_sys_data_connected_lte));

						// default non-at&t icons -- att icons for intl -- kludgy hack to auto-hide data icon
						resParam.res.setReplacement(targetpkg, "drawable", "stat_sys_data_in_e", modRes.fwd(R.drawable.intl_stat_sys_data_in_e));
						resParam.res.setReplacement(targetpkg, "drawable", "stat_sys_data_in_g", modRes.fwd(R.drawable.intl_stat_sys_data_in_g));
						resParam.res.setReplacement(targetpkg, "drawable", "stat_sys_data_in_h", modRes.fwd(R.drawable.intl_stat_sys_data_in_4g));
						resParam.res.setReplacement(targetpkg, "drawable", "stat_sys_data_in_hplus", modRes.fwd(R.drawable.intl_stat_sys_data_in_4g));
						resParam.res.setReplacement(targetpkg, "drawable", "stat_sys_data_in_3g", modRes.fwd(R.drawable.intl_stat_sys_data_in_4g));
						resParam.res.setReplacement(targetpkg, "drawable", "stat_sys_data_in_4g", modRes.fwd(R.drawable.intl_stat_sys_data_in_4g));
						resParam.res.setReplacement(targetpkg, "drawable", "stat_sys_data_in_lte", modRes.fwd(R.drawable.intl_stat_sys_data_in_lte));
						
						resParam.res.setReplacement(targetpkg, "drawable", "stat_sys_data_out_e", modRes.fwd(R.drawable.intl_stat_sys_data_out_e));
						resParam.res.setReplacement(targetpkg, "drawable", "stat_sys_data_out_g", modRes.fwd(R.drawable.intl_stat_sys_data_out_g));
						resParam.res.setReplacement(targetpkg, "drawable", "stat_sys_data_out_h", modRes.fwd(R.drawable.intl_stat_sys_data_out_4g));
						resParam.res.setReplacement(targetpkg, "drawable", "stat_sys_data_out_hplus", modRes.fwd(R.drawable.intl_stat_sys_data_out_4g));
						resParam.res.setReplacement(targetpkg, "drawable", "stat_sys_data_out_3g", modRes.fwd(R.drawable.intl_stat_sys_data_out_4g));
						resParam.res.setReplacement(targetpkg, "drawable", "stat_sys_data_out_4g", modRes.fwd(R.drawable.intl_stat_sys_data_out_4g));
						resParam.res.setReplacement(targetpkg, "drawable", "stat_sys_data_out_lte", modRes.fwd(R.drawable.intl_stat_sys_data_out_lte));
						
						resParam.res.setReplacement(targetpkg, "drawable", "stat_sys_data_inandout_e", modRes.fwd(R.drawable.intl_stat_sys_data_inandout_e));
						resParam.res.setReplacement(targetpkg, "drawable", "stat_sys_data_inandout_g", modRes.fwd(R.drawable.intl_stat_sys_data_inandout_g));
						resParam.res.setReplacement(targetpkg, "drawable", "stat_sys_data_inandout_h", modRes.fwd(R.drawable.intl_stat_sys_data_inandout_4g));
						resParam.res.setReplacement(targetpkg, "drawable", "stat_sys_data_inandout_hplus", modRes.fwd(R.drawable.intl_stat_sys_data_inandout_4g));
						resParam.res.setReplacement(targetpkg, "drawable", "stat_sys_data_inandout_3g", modRes.fwd(R.drawable.intl_stat_sys_data_inandout_4g));
						resParam.res.setReplacement(targetpkg, "drawable", "stat_sys_data_inandout_4g", modRes.fwd(R.drawable.intl_stat_sys_data_inandout_4g));
						resParam.res.setReplacement(targetpkg, "drawable", "stat_sys_data_inandout_lte", modRes.fwd(R.drawable.intl_stat_sys_data_inandout_lte));
					} catch (Throwable t) { XposedBridge.log(t); }
				} else if (paramPrefs.getString("intlstyle", "curved").equals("arrows") && (!paramPrefs.getBoolean("hideatt", false))) {
					try {
						resParam.res.setReplacement(targetpkg, "drawable", "stat_sys_data_connected_3_5g_capability", modRes.fwd(R.drawable.stat_sys_data_connected_3_5g_capability));
						resParam.res.setReplacement(targetpkg, "drawable", "stat_sys_data_connected_e", modRes.fwd(R.drawable.intl_theme1_stat_sys_data_connected_e));
						resParam.res.setReplacement(targetpkg, "drawable", "stat_sys_data_connected_g", modRes.fwd(R.drawable.intl_theme1_stat_sys_data_connected_g));
						resParam.res.setReplacement(targetpkg, "drawable", "stat_sys_data_connected_3_5g", modRes.fwd(R.drawable.intl_theme1_stat_sys_data_connected_3_5g));
						resParam.res.setReplacement(targetpkg, "drawable", "stat_sys_data_connected_3g", modRes.fwd(R.drawable.intl_theme1_stat_sys_data_connected_3g));
						resParam.res.setReplacement(targetpkg, "drawable", "stat_sys_data_connected_4g", modRes.fwd(R.drawable.intl_theme1_stat_sys_data_connected_4g));
						resParam.res.setReplacement(targetpkg, "drawable", "stat_sys_data_connected_h", modRes.fwd(R.drawable.intl_theme1_stat_sys_data_connected_h));
						resParam.res.setReplacement(targetpkg, "drawable", "stat_sys_data_connected_hplus", modRes.fwd(R.drawable.intl_theme1_stat_sys_data_connected_hplus));
						resParam.res.setReplacement(targetpkg, "drawable", "stat_sys_data_connected_lte_capability", modRes.fwd(R.drawable.stat_sys_data_connected_lte_capability));
						resParam.res.setReplacement(targetpkg, "drawable", "stat_sys_data_connected_lte", modRes.fwd(R.drawable.intl_theme1_stat_sys_data_connected_lte));

						// default non-at&t icons
						resParam.res.setReplacement(targetpkg, "drawable", "stat_sys_data_in_e", modRes.fwd(R.drawable.intl_theme1_stat_sys_data_in_e));
						resParam.res.setReplacement(targetpkg, "drawable", "stat_sys_data_in_g", modRes.fwd(R.drawable.intl_theme1_stat_sys_data_in_g));
						resParam.res.setReplacement(targetpkg, "drawable", "stat_sys_data_in_h", modRes.fwd(R.drawable.intl_theme1_stat_sys_data_in_h));
						resParam.res.setReplacement(targetpkg, "drawable", "stat_sys_data_in_hplus", modRes.fwd(R.drawable.intl_theme1_stat_sys_data_in_hplus));
						resParam.res.setReplacement(targetpkg, "drawable", "stat_sys_data_in_3g", modRes.fwd(R.drawable.intl_theme1_stat_sys_data_in_3g));
						resParam.res.setReplacement(targetpkg, "drawable", "stat_sys_data_in_4g", modRes.fwd(R.drawable.intl_theme1_stat_sys_data_in_4g));
						resParam.res.setReplacement(targetpkg, "drawable", "stat_sys_data_in_lte", modRes.fwd(R.drawable.intl_theme1_stat_sys_data_in_lte));
						
						resParam.res.setReplacement(targetpkg, "drawable", "stat_sys_data_out_e", modRes.fwd(R.drawable.intl_theme1_stat_sys_data_out_e));
						resParam.res.setReplacement(targetpkg, "drawable", "stat_sys_data_out_g", modRes.fwd(R.drawable.intl_theme1_stat_sys_data_out_g));
						resParam.res.setReplacement(targetpkg, "drawable", "stat_sys_data_out_h", modRes.fwd(R.drawable.intl_theme1_stat_sys_data_out_h));
						resParam.res.setReplacement(targetpkg, "drawable", "stat_sys_data_out_hplus", modRes.fwd(R.drawable.intl_theme1_stat_sys_data_out_hplus));
						resParam.res.setReplacement(targetpkg, "drawable", "stat_sys_data_out_3g", modRes.fwd(R.drawable.intl_theme1_stat_sys_data_out_3g));
						resParam.res.setReplacement(targetpkg, "drawable", "stat_sys_data_out_4g", modRes.fwd(R.drawable.intl_theme1_stat_sys_data_out_4g));
						resParam.res.setReplacement(targetpkg, "drawable", "stat_sys_data_out_lte", modRes.fwd(R.drawable.intl_theme1_stat_sys_data_out_lte));
						
						resParam.res.setReplacement(targetpkg, "drawable", "stat_sys_data_inandout_e", modRes.fwd(R.drawable.intl_theme1_stat_sys_data_inandout_e));
						resParam.res.setReplacement(targetpkg, "drawable", "stat_sys_data_inandout_g", modRes.fwd(R.drawable.intl_theme1_stat_sys_data_inandout_g));
						resParam.res.setReplacement(targetpkg, "drawable", "stat_sys_data_inandout_h", modRes.fwd(R.drawable.intl_theme1_stat_sys_data_inandout_h));
						resParam.res.setReplacement(targetpkg, "drawable", "stat_sys_data_inandout_hplus", modRes.fwd(R.drawable.intl_theme1_stat_sys_data_inandout_hplus));
						resParam.res.setReplacement(targetpkg, "drawable", "stat_sys_data_inandout_3g", modRes.fwd(R.drawable.intl_theme1_stat_sys_data_inandout_3g));
						resParam.res.setReplacement(targetpkg, "drawable", "stat_sys_data_inandout_4g", modRes.fwd(R.drawable.intl_theme1_stat_sys_data_inandout_4g));
						resParam.res.setReplacement(targetpkg, "drawable", "stat_sys_data_inandout_lte", modRes.fwd(R.drawable.intl_theme1_stat_sys_data_inandout_lte));
						} catch (Throwable t) { XposedBridge.log(t); }
				} else if (paramPrefs.getString("intlstyle", "curved").equals("arrows") && (paramPrefs.getBoolean("hideatt", false))) {
					try {
						resParam.res.setReplacement(targetpkg, "drawable", "stat_sys_data_connected_3_5g_capability", modRes.fwd(R.drawable.stat_sys_data_connected_3_5g_capability));
						resParam.res.setReplacement(targetpkg, "drawable", "stat_sys_data_connected_e", modRes.fwd(R.drawable.stat_sys_data_connected_e));
						resParam.res.setReplacement(targetpkg, "drawable", "stat_sys_data_connected_g", modRes.fwd(R.drawable.stat_sys_data_connected_g));
						resParam.res.setReplacement(targetpkg, "drawable", "stat_sys_data_connected_3_5g", modRes.fwd(R.drawable.stat_sys_data_connected_3_5g));
						resParam.res.setReplacement(targetpkg, "drawable", "stat_sys_data_connected_3g", modRes.fwd(R.drawable.stat_sys_data_connected_4g));
						resParam.res.setReplacement(targetpkg, "drawable", "stat_sys_data_connected_4g", modRes.fwd(R.drawable.stat_sys_data_connected_4g));
						resParam.res.setReplacement(targetpkg, "drawable", "stat_sys_data_connected_h", modRes.fwd(R.drawable.stat_sys_data_connected_4g));
						resParam.res.setReplacement(targetpkg, "drawable", "stat_sys_data_connected_hplus", modRes.fwd(R.drawable.stat_sys_data_connected_4g));
						resParam.res.setReplacement(targetpkg, "drawable", "stat_sys_data_connected_lte_capability", modRes.fwd(R.drawable.stat_sys_data_connected_lte_capability));
						resParam.res.setReplacement(targetpkg, "drawable", "stat_sys_data_connected_lte", modRes.fwd(R.drawable.stat_sys_data_connected_lte));

						// default non-at&t icons -- att icons for intl -- kludgy hack to auto-hide data icon
						resParam.res.setReplacement(targetpkg, "drawable", "stat_sys_data_in_e", modRes.fwd(R.drawable.intl_stat_sys_data_in_e));
						resParam.res.setReplacement(targetpkg, "drawable", "stat_sys_data_in_g", modRes.fwd(R.drawable.intl_stat_sys_data_in_g));
						resParam.res.setReplacement(targetpkg, "drawable", "stat_sys_data_in_h", modRes.fwd(R.drawable.intl_stat_sys_data_in_4g));
						resParam.res.setReplacement(targetpkg, "drawable", "stat_sys_data_in_hplus", modRes.fwd(R.drawable.intl_stat_sys_data_in_4g));
						resParam.res.setReplacement(targetpkg, "drawable", "stat_sys_data_in_3g", modRes.fwd(R.drawable.intl_stat_sys_data_in_4g));
						resParam.res.setReplacement(targetpkg, "drawable", "stat_sys_data_in_4g", modRes.fwd(R.drawable.intl_stat_sys_data_in_4g));
						resParam.res.setReplacement(targetpkg, "drawable", "stat_sys_data_in_lte", modRes.fwd(R.drawable.intl_stat_sys_data_in_lte));
						
						resParam.res.setReplacement(targetpkg, "drawable", "stat_sys_data_out_e", modRes.fwd(R.drawable.intl_stat_sys_data_out_e));
						resParam.res.setReplacement(targetpkg, "drawable", "stat_sys_data_out_g", modRes.fwd(R.drawable.intl_stat_sys_data_out_g));
						resParam.res.setReplacement(targetpkg, "drawable", "stat_sys_data_out_h", modRes.fwd(R.drawable.intl_stat_sys_data_out_4g));
						resParam.res.setReplacement(targetpkg, "drawable", "stat_sys_data_out_hplus", modRes.fwd(R.drawable.intl_stat_sys_data_out_4g));
						resParam.res.setReplacement(targetpkg, "drawable", "stat_sys_data_out_3g", modRes.fwd(R.drawable.intl_stat_sys_data_out_4g));
						resParam.res.setReplacement(targetpkg, "drawable", "stat_sys_data_out_4g", modRes.fwd(R.drawable.intl_stat_sys_data_out_4g));
						resParam.res.setReplacement(targetpkg, "drawable", "stat_sys_data_out_lte", modRes.fwd(R.drawable.intl_stat_sys_data_out_lte));
						
						resParam.res.setReplacement(targetpkg, "drawable", "stat_sys_data_inandout_e", modRes.fwd(R.drawable.intl_stat_sys_data_inandout_e));
						resParam.res.setReplacement(targetpkg, "drawable", "stat_sys_data_inandout_g", modRes.fwd(R.drawable.intl_stat_sys_data_inandout_g));
						resParam.res.setReplacement(targetpkg, "drawable", "stat_sys_data_inandout_h", modRes.fwd(R.drawable.intl_stat_sys_data_inandout_4g));
						resParam.res.setReplacement(targetpkg, "drawable", "stat_sys_data_inandout_hplus", modRes.fwd(R.drawable.intl_stat_sys_data_inandout_4g));
						resParam.res.setReplacement(targetpkg, "drawable", "stat_sys_data_inandout_3g", modRes.fwd(R.drawable.intl_stat_sys_data_inandout_4g));
						resParam.res.setReplacement(targetpkg, "drawable", "stat_sys_data_inandout_4g", modRes.fwd(R.drawable.intl_stat_sys_data_inandout_4g));
						resParam.res.setReplacement(targetpkg, "drawable", "stat_sys_data_inandout_lte", modRes.fwd(R.drawable.intl_stat_sys_data_inandout_lte));
					} catch (Throwable t) { XposedBridge.log(t); }
				}
			} else {
				// orange icon set
				if (paramPrefs.getString("intlstyle", "curved").equals("curved") && (paramPrefs.getBoolean("hideatt", false))) {
					try {
						// replace default TmoUS icons
						resParam.res.setReplacement(targetpkg, "drawable", "stat_sys_data_connected_tmous_3g", modRes.fwd(R.drawable.stat_sys_data_connected_tmous_3g));
						resParam.res.setReplacement(targetpkg, "drawable", "stat_sys_data_connected_tmous_4g", modRes.fwd(R.drawable.stat_sys_data_connected_tmous_4g));
						resParam.res.setReplacement(targetpkg, "drawable", "stat_sys_data_connected_tmous_lte", modRes.fwd(R.drawable.stat_sys_data_connected_tmous_lte));
						
						resParam.res.setReplacement(targetpkg, "drawable", "stat_sys_data_in_tmous_3g", modRes.fwd(R.drawable.stat_sys_data_in_tmous_3g));
						resParam.res.setReplacement(targetpkg, "drawable", "stat_sys_data_in_tmous_4g", modRes.fwd(R.drawable.stat_sys_data_in_tmous_4g));
						resParam.res.setReplacement(targetpkg, "drawable", "stat_sys_data_in_tmous_lte", modRes.fwd(R.drawable.stat_sys_data_in_tmous_lte));
						
						resParam.res.setReplacement(targetpkg, "drawable", "stat_sys_data_inandout_tmous_3g", modRes.fwd(R.drawable.stat_sys_data_inandout_tmous_3g));
						resParam.res.setReplacement(targetpkg, "drawable", "stat_sys_data_inandout_tmous_4g", modRes.fwd(R.drawable.stat_sys_data_inandout_tmous_4g));
						resParam.res.setReplacement(targetpkg, "drawable", "stat_sys_data_inandout_tmous_lte", modRes.fwd(R.drawable.stat_sys_data_inandout_tmous_lte));
						
						resParam.res.setReplacement(targetpkg, "drawable", "stat_sys_data_out_tmous_3g", modRes.fwd(R.drawable.stat_sys_data_out_tmous_3g));
						resParam.res.setReplacement(targetpkg, "drawable", "stat_sys_data_out_tmous_4g", modRes.fwd(R.drawable.stat_sys_data_out_tmous_4g));
						resParam.res.setReplacement(targetpkg, "drawable", "stat_sys_data_out_tmous_lte", modRes.fwd(R.drawable.stat_sys_data_out_tmous_lte));
						
						resParam.res.setReplacement(targetpkg, "drawable", "stat_sys_data_in_e", modRes.fwd(R.drawable.or_stat_sys_data_in_e));
						resParam.res.setReplacement(targetpkg, "drawable", "stat_sys_data_in_h", modRes.fwd(R.drawable.or_stat_sys_data_in_h));
						resParam.res.setReplacement(targetpkg, "drawable", "stat_sys_data_in_3g", modRes.fwd(R.drawable.or_stat_sys_data_in_3g));
						resParam.res.setReplacement(targetpkg, "drawable", "stat_sys_data_in_4g", modRes.fwd(R.drawable.or_stat_sys_data_in_4g));
						resParam.res.setReplacement(targetpkg, "drawable", "stat_sys_data_in_lte", modRes.fwd(R.drawable.or_stat_sys_data_in_lte));
						
						resParam.res.setReplacement(targetpkg, "drawable", "stat_sys_data_out_e", modRes.fwd(R.drawable.or_stat_sys_data_out_e));
						resParam.res.setReplacement(targetpkg, "drawable", "stat_sys_data_out_h", modRes.fwd(R.drawable.or_stat_sys_data_out_h));
						resParam.res.setReplacement(targetpkg, "drawable", "stat_sys_data_out_3g", modRes.fwd(R.drawable.or_stat_sys_data_out_3g));
						resParam.res.setReplacement(targetpkg, "drawable", "stat_sys_data_out_4g", modRes.fwd(R.drawable.or_stat_sys_data_out_4g));
						resParam.res.setReplacement(targetpkg, "drawable", "stat_sys_data_out_lte", modRes.fwd(R.drawable.or_stat_sys_data_out_lte));
						
						resParam.res.setReplacement(targetpkg, "drawable", "stat_sys_data_inandout_e", modRes.fwd(R.drawable.or_stat_sys_data_inandout_e));
						resParam.res.setReplacement(targetpkg, "drawable", "stat_sys_data_inandout_h", modRes.fwd(R.drawable.or_stat_sys_data_inandout_h));
						resParam.res.setReplacement(targetpkg, "drawable", "stat_sys_data_inandout_3g", modRes.fwd(R.drawable.or_stat_sys_data_inandout_3g));
						resParam.res.setReplacement(targetpkg, "drawable", "stat_sys_data_inandout_4g", modRes.fwd(R.drawable.or_stat_sys_data_inandout_4g));
						resParam.res.setReplacement(targetpkg, "drawable", "stat_sys_data_inandout_lte", modRes.fwd(R.drawable.or_stat_sys_data_inandout_lte));
						
						resParam.res.setReplacement(targetpkg, "drawable", "stat_sys_data_4g_capability", modRes.fwd(R.drawable.orange_stat_sys_data_4g_capability));
						resParam.res.setReplacement(targetpkg, "drawable", "stat_sys_data_4g_connected", modRes.fwd(R.drawable.orange_stat_sys_data_4g_connected));
						resParam.res.setReplacement(targetpkg, "drawable", "stat_sys_data_4g_lte_capability", modRes.fwd(R.drawable.orange_stat_sys_data_4g_lte_capability));
												
						resParam.res.setReplacement(targetpkg, "drawable", "stat_sys_data_connected_3_5g_capability", modRes.fwd(R.drawable.orange_stat_sys_data_connected_3_5g_capability));
						resParam.res.setReplacement(targetpkg, "drawable", "stat_sys_data_connected_3_5g", modRes.fwd(R.drawable.orange_stat_sys_data_connected_3_5g));
						resParam.res.setReplacement(targetpkg, "drawable", "stat_sys_data_connected_3g", modRes.fwd(R.drawable.orange_stat_sys_data_connected_3g));
						resParam.res.setReplacement(targetpkg, "drawable", "stat_sys_data_connected_h", modRes.fwd(R.drawable.orange_stat_sys_data_connected_h));
						resParam.res.setReplacement(targetpkg, "drawable", "stat_sys_data_connected_4g", modRes.fwd(R.drawable.orange_stat_sys_data_connected_4g));
						resParam.res.setReplacement(targetpkg, "drawable", "stat_sys_data_connected_e", modRes.fwd(R.drawable.orange_stat_sys_data_e_connected));
						resParam.res.setReplacement(targetpkg, "drawable", "stat_sys_data_connected_lte_capability", modRes.fwd(R.drawable.orange_stat_sys_data_connected_lte_capability));
						resParam.res.setReplacement(targetpkg, "drawable", "stat_sys_data_connected_lte", modRes.fwd(R.drawable.orange_stat_sys_data_connected_lte));
					} catch (Throwable t) { XposedBridge.log(t); }
				} else if (paramPrefs.getString("intlstyle", "curved").equals("arrows") && (paramPrefs.getBoolean("hideatt", false))) {
					try {
						// default intl
						resParam.res.setReplacement(targetpkg, "drawable", "stat_sys_data_4g_capability", modRes.fwd(R.drawable.orange_stat_sys_data_4g_capability));
						resParam.res.setReplacement(targetpkg, "drawable", "stat_sys_data_4g_connected", modRes.fwd(R.drawable.orange_stat_sys_data_4g_connected));
						resParam.res.setReplacement(targetpkg, "drawable", "stat_sys_data_4g_lte_capability", modRes.fwd(R.drawable.orange_stat_sys_data_4g_lte_capability));
												
						resParam.res.setReplacement(targetpkg, "drawable", "stat_sys_data_e_capability", modRes.fwd(R.drawable.orange_stat_sys_data_e_capability));
						resParam.res.setReplacement(targetpkg, "drawable", "stat_sys_data_e_connected", modRes.fwd(R.drawable.orange_stat_sys_data_e_connected));
						
						resParam.res.setReplacement(targetpkg, "drawable", "stat_sys_data_connected_3_5g_capability", modRes.fwd(R.drawable.orange_stat_sys_data_connected_3_5g_capability));
						resParam.res.setReplacement(targetpkg, "drawable", "stat_sys_data_connected_3_5g", modRes.fwd(R.drawable.orange_stat_sys_data_connected_3_5g));
						resParam.res.setReplacement(targetpkg, "drawable", "stat_sys_data_connected_3g", modRes.fwd(R.drawable.orange_stat_sys_data_connected_3g));
						resParam.res.setReplacement(targetpkg, "drawable", "stat_sys_data_connected_h", modRes.fwd(R.drawable.orange_stat_sys_data_connected_h));
						resParam.res.setReplacement(targetpkg, "drawable", "stat_sys_data_connected_e", modRes.fwd(R.drawable.orange_stat_sys_data_e_connected));
						resParam.res.setReplacement(targetpkg, "drawable", "stat_sys_data_connected_4g", modRes.fwd(R.drawable.orange_stat_sys_data_connected_4g));

						resParam.res.setReplacement(targetpkg, "drawable", "stat_sys_data_connected_lte_capability", modRes.fwd(R.drawable.orange_stat_sys_data_connected_lte_capability));
						resParam.res.setReplacement(targetpkg, "drawable", "stat_sys_data_connected_lte", modRes.fwd(R.drawable.orange_stat_sys_data_connected_lte));

						resParam.res.setReplacement(targetpkg, "drawable", "stat_sys_data_in_e", modRes.fwd(R.drawable.orange_stat_sys_data_in_e));
						resParam.res.setReplacement(targetpkg, "drawable", "stat_sys_data_in_h", modRes.fwd(R.drawable.orange_stat_sys_data_in_h));
						resParam.res.setReplacement(targetpkg, "drawable", "stat_sys_data_in_4g", modRes.fwd(R.drawable.orange_stat_sys_data_in_4g));
						resParam.res.setReplacement(targetpkg, "drawable", "stat_sys_data_in_lte", modRes.fwd(R.drawable.orange_stat_sys_data_in_lte));
						
						resParam.res.setReplacement(targetpkg, "drawable", "stat_sys_data_out_e", modRes.fwd(R.drawable.orange_stat_sys_data_out_e));
						resParam.res.setReplacement(targetpkg, "drawable", "stat_sys_data_out_h", modRes.fwd(R.drawable.orange_stat_sys_data_out_h));
						resParam.res.setReplacement(targetpkg, "drawable", "stat_sys_data_out_4g", modRes.fwd(R.drawable.orange_stat_sys_data_out_4g));
						resParam.res.setReplacement(targetpkg, "drawable", "stat_sys_data_out_lte", modRes.fwd(R.drawable.orange_stat_sys_data_out_lte));
						
						resParam.res.setReplacement(targetpkg, "drawable", "stat_sys_data_inandout_e", modRes.fwd(R.drawable.orange_stat_sys_data_inandout_e));
						resParam.res.setReplacement(targetpkg, "drawable", "stat_sys_data_inandout_h", modRes.fwd(R.drawable.orange_stat_sys_data_inandout_h));
						resParam.res.setReplacement(targetpkg, "drawable", "stat_sys_data_inandout_4g", modRes.fwd(R.drawable.orange_stat_sys_data_inandout_4g));
						resParam.res.setReplacement(targetpkg, "drawable", "stat_sys_data_inandout_lte", modRes.fwd(R.drawable.orange_stat_sys_data_inandout_lte));
					} catch (Throwable t) { XposedBridge.log(t); }
				}
			}
		} catch (Throwable t) { XposedBridge.log(t); }
	}
}
