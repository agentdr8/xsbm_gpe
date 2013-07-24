package com.dr8.sbicons.mod.hax;

import static de.robv.android.xposed.XposedHelpers.findAndHookMethod;
import static de.robv.android.xposed.XposedHelpers.getObjectField;

import java.util.ArrayList;

import android.graphics.Bitmap;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Environment;
import android.widget.ImageView;

import com.dr8.sbicons.mod.ZipStuff;

import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XSharedPreferences;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

public class MobileData {

	private static int nettype = 0;
	private static String path = Environment.getExternalStorageDirectory() + "/xsbm/test.zip";
	private static final String[] gicons = {
			"stat_sys_data_g_downlink_1.png",
			"stat_sys_data_g_downlink_2.png",
			"stat_sys_data_g_downlink_3.png",
			"stat_sys_data_g_uplink_1.png",
			"stat_sys_data_g_uplink_2.png",
			"stat_sys_data_g_uplink_3.png"

		};
	private static final String[] eicons = {
			"stat_sys_data_e_downlink_1.png",
			"stat_sys_data_e_downlink_2.png",
			"stat_sys_data_e_downlink_3.png",
			"stat_sys_data_e_uplink_1.png",
			"stat_sys_data_e_uplink_2.png",
			"stat_sys_data_e_uplink_3.png"

		};
	private static final String[] threegicons = {
			"stat_sys_data_3g_downlink_1.png",
			"stat_sys_data_3g_downlink_2.png",
			"stat_sys_data_3g_downlink_3.png",
			"stat_sys_data_3g_uplink_1.png",
			"stat_sys_data_3g_uplink_2.png",
			"stat_sys_data_3g_uplink_3.png"

		};
	private static final String[] hicons = {
			"stat_sys_data_h_downlink_1.png",
			"stat_sys_data_h_downlink_2.png",
			"stat_sys_data_h_downlink_3.png",
			"stat_sys_data_h_uplink_1.png",
			"stat_sys_data_h_uplink_2.png",
			"stat_sys_data_h_uplink_3.png"

		};
	private static final String[] fourgicons = {
			"stat_sys_data_4g_downlink_1.png",
			"stat_sys_data_4g_downlink_2.png",
			"stat_sys_data_4g_downlink_3.png",
			"stat_sys_data_4g_uplink_1.png",
			"stat_sys_data_4g_uplink_2.png",
			"stat_sys_data_4g_uplink_3.png"

		};
	private static final String[] lteicons = {
			"stat_sys_data_lte_downlink_1.png",
			"stat_sys_data_lte_downlink_2.png",
			"stat_sys_data_lte_downlink_3.png",
			"stat_sys_data_lte_uplink_1.png",
			"stat_sys_data_lte_uplink_2.png",
			"stat_sys_data_lte_uplink_3.png"

		};
	
	public static void initHandleLoadPackage(final XSharedPreferences paramPrefs, XC_LoadPackage.LoadPackageParam lpParam) {
		
		
		findAndHookMethod("com.android.systemui.statusbar.policy.HtcGenericNetworkController", lpParam.classLoader, "getNetworkTypeFromTelephonyManager", new XC_MethodHook() {
			@Override
			protected void afterHookedMethod(MethodHookParam param) throws Throwable {
				try { 
					nettype = (Integer) param.getResult();
					XposedBridge.log("XSBM: our nettype is " + nettype);
				} catch (Throwable t) { XposedBridge.log(t); }
			}
		});
		
		if (paramPrefs.getBoolean("mobile_data", true)) {
			findAndHookMethod("com.android.systemui.statusbar.policy.NetworkController", lpParam.classLoader, "refreshViews", new XC_MethodHook() {
				@Override
				protected void afterHookedMethod(MethodHookParam param) throws Throwable {
					try {
						@SuppressWarnings("unchecked")
						int j = ((ArrayList<ImageView>) getObjectField(param.thisObject, "mDataTypeIconViews")).size();
						for (int k = 0; k < j; k++) {
							@SuppressWarnings("unchecked")
							ImageView iv = ((ArrayList<ImageView>) getObjectField(param.thisObject, "mDataTypeIconViews")).get(k);

							switch (nettype) {
								case 1:
									AnimationDrawable animationg = new AnimationDrawable();
									for (int i = 0; i < gicons.length; i++) {
										String gimg = "mobile/g/" + gicons[i];
										final Bitmap gb = ZipStuff.getBitmapFromZip(path, gimg);
										Drawable gd = new BitmapDrawable(null, gb);
										animationg.addFrame(gd, 1000);
									}
									animationg.setOneShot(false);
									iv.setImageDrawable(animationg);
									animationg.start();
									break;
								case 2:
									AnimationDrawable animatione = new AnimationDrawable();
									for (int i = 0; i < eicons.length; i++) {
										String eimg = "mobile/e/" + eicons[i];
										final Bitmap eb = ZipStuff.getBitmapFromZip(path, eimg);
										Drawable ed = new BitmapDrawable(null, eb);
										animatione.addFrame(ed, 1000);
									}
									animatione.setOneShot(false);
									iv.setImageDrawable(animatione);
									animatione.start();
									break;
								case 3:
									AnimationDrawable animationtg = new AnimationDrawable();
									for (int i = 0; i < threegicons.length; i++) {
										String tgimg = "mobile/3g/" + threegicons[i];
										final Bitmap tgb = ZipStuff.getBitmapFromZip(path, tgimg);
										Drawable tgd = new BitmapDrawable(null, tgb);
										animationtg.addFrame(tgd, 1000);
									}
									animationtg.setOneShot(false);
									iv.setImageDrawable(animationtg);
									animationtg.start();
									break;
								case 10:
									AnimationDrawable animationfg = new AnimationDrawable();
									for (int i = 0; i < fourgicons.length; i++) {
										String fgimg = "mobile/4g/" + fourgicons[i];
										final Bitmap fgb = ZipStuff.getBitmapFromZip(path, fgimg);
										Drawable fgd = new BitmapDrawable(null, fgb);
										animationfg.addFrame(fgd, 1000);
									}
									animationfg.setOneShot(false);
									iv.setImageDrawable(animationfg);
									animationfg.start();
									break;
								case 13:
									AnimationDrawable animationl = new AnimationDrawable();
									for (int i = 0; i < lteicons.length; i++) {
										String limg = "mobile/lte/" + lteicons[i];
										final Bitmap lb = ZipStuff.getBitmapFromZip(path, limg);
										Drawable ld = new BitmapDrawable(null, lb);
										animationl.addFrame(ld, 1000);
									}
									animationl.setOneShot(false);
									iv.setImageDrawable(animationl);
									animationl.start();
									break;
								case 15:
									AnimationDrawable animationh = new AnimationDrawable();
									for (int i = 0; i < hicons.length; i++) {
										String himg = "mobile/h/" + hicons[i];
										final Bitmap hb = ZipStuff.getBitmapFromZip(path, himg);
										Drawable hd = new BitmapDrawable(null, hb);
										animationh.addFrame(hd, 1000);
									}
									animationh.setOneShot(false);
									iv.setImageDrawable(animationh);
									animationh.start();
									break;
							}
						}
					} catch (Throwable t) { XposedBridge.log(t); }
				}
			});	
		}	
	}
}
