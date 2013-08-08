package com.dr8.sbicons.mod.hax;

import static de.robv.android.xposed.XposedHelpers.findAndHookMethod;
import static de.robv.android.xposed.XposedHelpers.getObjectField;
import android.graphics.Bitmap;
import android.graphics.PorterDuff;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;

import com.dr8.sbicons.mod.ZipStuff;

import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XSharedPreferences;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

public class MobileData {

	private static int nettype = 0;
	private static int netstate = 0;

	private static final String[] gicons = {
		"stat_sys_data_g_connected.png",
		"stat_sys_data_g_downlink_1.png",
		"stat_sys_data_g_downlink_2.png",
		"stat_sys_data_g_downlink_3.png",
		"stat_sys_data_g_uplink_1.png",
		"stat_sys_data_g_uplink_2.png",
		"stat_sys_data_g_uplink_3.png"

	};
	private static final String[] eicons = {
		"stat_sys_data_e_connected.png",
		"stat_sys_data_e_downlink_1.png",
		"stat_sys_data_e_downlink_2.png",
		"stat_sys_data_e_downlink_3.png",
		"stat_sys_data_e_uplink_1.png",
		"stat_sys_data_e_uplink_2.png",
		"stat_sys_data_e_uplink_3.png"

	};
	private static final String[] threegicons = {
		"stat_sys_data_3g_connected.png",
		"stat_sys_data_3g_downlink_1.png",
		"stat_sys_data_3g_downlink_2.png",
		"stat_sys_data_3g_downlink_3.png",
		"stat_sys_data_3g_uplink_1.png",
		"stat_sys_data_3g_uplink_2.png",
		"stat_sys_data_3g_uplink_3.png"

	};
	private static final String[] hicons = {
		"stat_sys_data_h_connected.png",
		"stat_sys_data_h_downlink_1.png",
		"stat_sys_data_h_downlink_2.png",
		"stat_sys_data_h_downlink_3.png",
		"stat_sys_data_h_uplink_1.png",
		"stat_sys_data_h_uplink_2.png",
		"stat_sys_data_h_uplink_3.png"

	};
	private static final String[] fourgicons = {
		"stat_sys_data_4g_connected.png",
		"stat_sys_data_4g_downlink_1.png",
		"stat_sys_data_4g_downlink_2.png",
		"stat_sys_data_4g_downlink_3.png",
		"stat_sys_data_4g_uplink_1.png",
		"stat_sys_data_4g_uplink_2.png",
		"stat_sys_data_4g_uplink_3.png"

	};
	private static final String[] lteicons = {
		"stat_sys_data_lte_connected.png",
		"stat_sys_data_lte_downlink_1.png",
		"stat_sys_data_lte_downlink_2.png",
		"stat_sys_data_lte_downlink_3.png",
		"stat_sys_data_lte_uplink_1.png",
		"stat_sys_data_lte_uplink_2.png",
		"stat_sys_data_lte_uplink_3.png"

	};

	public static void initHandleLoadPackage(final XSharedPreferences paramPrefs, XC_LoadPackage.LoadPackageParam lpParam) {

		final String path = "/data/data/com.dr8.sbicons/xsbm/";


		findAndHookMethod("com.android.systemui.statusbar.policy.HtcGenericNetworkController", lpParam.classLoader, "getNetworkTypeFromTelephonyManager", new XC_MethodHook() {
			@Override
			protected void afterHookedMethod(MethodHookParam param) throws Throwable {
				try { 
					nettype = (Integer) param.getResult();
					//					XposedBridge.log("XSBM: our nettype is " + nettype);
				} catch (Throwable t) { XposedBridge.log(t); }
			}
		});

		findAndHookMethod("com.android.systemui.statusbar.policy.HtcGenericNetworkController", lpParam.classLoader, "getDataActivity", new XC_MethodHook() {
			@Override
			protected void afterHookedMethod(MethodHookParam param) throws Throwable {
				try { 
					netstate = (Integer) param.getResult();
					//					XposedBridge.log("XSBM: our netstate is " + netstate);
				} catch (Throwable t) { XposedBridge.log(t); }
			}
		});

		if (paramPrefs.getBoolean("mobile_data", true)) {
			findAndHookMethod("com.android.systemui.statusbar.HtcGenericSignalClusterView", lpParam.classLoader, "apply", new XC_MethodHook() {
				@Override
				protected void afterHookedMethod(MethodHookParam param) throws Throwable {
					try {
						ImageView iv = (ImageView) getObjectField(param.thisObject, "mMobile");
						if (iv != null) {
							switch (nettype) {
							case 1:
								if (netstate == 0) {
									String gimg = "mobile/g/" + gicons[0];
									final Bitmap gb = ZipStuff.getBitmap(path, gimg);
									if (gb != null) {
										Drawable gd = new BitmapDrawable(null, gb);
										if (paramPrefs.getBoolean("mobilecolor_enabled", false)) {
											gd.setColorFilter(paramPrefs.getInt("mobilecolor", 0xffffffff), PorterDuff.Mode.MULTIPLY);
										}	
										iv.setImageDrawable(gd);
									}
								} else if (netstate == 1) {
									AnimationDrawable animationg = new AnimationDrawable();
									for (int i = 0; i < gicons.length - 3; i++) {
										String gimg = "mobile/g/" + gicons[i];
										final Bitmap gb = ZipStuff.getBitmap(path, gimg);
										if (gb != null) {
											Drawable gd = new BitmapDrawable(null, gb);
											if (paramPrefs.getBoolean("mobilecolor_enabled", false)) {
												animationg.setColorFilter(paramPrefs.getInt("mobilecolor", 0xffffffff), PorterDuff.Mode.MULTIPLY);
											}
											animationg.addFrame(gd, 750);
										}
									}
									animationg.setOneShot(false);
									iv.setImageDrawable(animationg);
									animationg.start();
								} else if (netstate == 2) {
									AnimationDrawable animationg = new AnimationDrawable();
									for (int i = 4; i < gicons.length; i++) {
										String gimg = "mobile/g/" + gicons[i];
										final Bitmap gb = ZipStuff.getBitmap(path, gimg);
										if (gb != null) {
											Drawable gd = new BitmapDrawable(null, gb);
											if (paramPrefs.getBoolean("mobilecolor_enabled", false)) {
												animationg.setColorFilter(paramPrefs.getInt("mobilecolor", 0xffffffff), PorterDuff.Mode.MULTIPLY);
											}
											animationg.addFrame(gd, 750);
										}
									}
									animationg.setOneShot(false);
									iv.setImageDrawable(animationg);
									animationg.start();
								} else {
									AnimationDrawable animationg = new AnimationDrawable();
									for (int i = 0; i < gicons.length; i++) {
										String gimg = "mobile/g/" + gicons[i];
										final Bitmap gb = ZipStuff.getBitmap(path, gimg);
										if (gb != null) {
											Drawable gd = new BitmapDrawable(null, gb);
											if (paramPrefs.getBoolean("mobilecolor_enabled", false)) {
												animationg.setColorFilter(paramPrefs.getInt("mobilecolor", 0xffffffff), PorterDuff.Mode.MULTIPLY);
											}
											animationg.addFrame(gd, 750);
										}
									}
									animationg.setOneShot(false);
									iv.setImageDrawable(animationg);
									animationg.start();
								}
								break;
							case 2:
								if (netstate == 0) {
									String eimg = "mobile/e/" + eicons[0];
									final Bitmap eb = ZipStuff.getBitmap(path, eimg);
									if (eb != null) {
										Drawable ed = new BitmapDrawable(null, eb);
										if (paramPrefs.getBoolean("mobilecolor_enabled", false)) {
											ed.setColorFilter(paramPrefs.getInt("mobilecolor", 0xffffffff), PorterDuff.Mode.MULTIPLY);
										}
										iv.setImageDrawable(ed);
									}
								} else if (netstate == 1) {
									AnimationDrawable animatione = new AnimationDrawable();
									for (int i = 0; i < eicons.length - 3; i++) {
										String eimg = "mobile/e/" + eicons[i];
										final Bitmap eb = ZipStuff.getBitmap(path, eimg);
										if (eb != null) {
											Drawable ed = new BitmapDrawable(null, eb);
											if (paramPrefs.getBoolean("mobilecolor_enabled", false)) {
												animatione.setColorFilter(paramPrefs.getInt("mobilecolor", 0xffffffff), PorterDuff.Mode.MULTIPLY);
											}
											animatione.addFrame(ed, 750);
										}
									}
									animatione.setOneShot(false);
									iv.setImageDrawable(animatione);
									animatione.start();
								} else if (netstate == 2) {
									AnimationDrawable animatione = new AnimationDrawable();
									for (int i = 4; i < eicons.length; i++) {
										String eimg = "mobile/e/" + eicons[i];
										final Bitmap eb = ZipStuff.getBitmap(path, eimg);
										if (eb != null) {
											Drawable ed = new BitmapDrawable(null, eb);
											if (paramPrefs.getBoolean("mobilecolor_enabled", false)) {
												animatione.setColorFilter(paramPrefs.getInt("mobilecolor", 0xffffffff), PorterDuff.Mode.MULTIPLY);
											}
											animatione.addFrame(ed, 750);
										}
									}
									animatione.setOneShot(false);
									iv.setImageDrawable(animatione);
									animatione.start();
								} else {
									AnimationDrawable animatione = new AnimationDrawable();
									for (int i = 0; i < eicons.length; i++) {
										String eimg = "mobile/e/" + eicons[i];
										final Bitmap eb = ZipStuff.getBitmap(path, eimg);
										if (eb != null) {
											Drawable ed = new BitmapDrawable(null, eb);
											if (paramPrefs.getBoolean("mobilecolor_enabled", false)) {
												animatione.setColorFilter(paramPrefs.getInt("mobilecolor", 0xffffffff), PorterDuff.Mode.MULTIPLY);
											}
											animatione.addFrame(ed, 750);
										}
									}
									animatione.setOneShot(false);
									iv.setImageDrawable(animatione);
									animatione.start();
								}
								break;
							case 3:
								if (netstate == 0) {
									String tgimg = "mobile/3g/" + threegicons[0];
									final Bitmap tgb = ZipStuff.getBitmap(path, tgimg);
									if (tgb != null) {
										Drawable tgd = new BitmapDrawable(null, tgb);
										if (paramPrefs.getBoolean("mobilecolor_enabled", false)) {
											tgd.setColorFilter(paramPrefs.getInt("mobilecolor", 0xffffffff), PorterDuff.Mode.MULTIPLY);
										}
										iv.setImageDrawable(tgd);
									}
								} else if (netstate == 1) {
									AnimationDrawable animationtg = new AnimationDrawable();
									for (int i = 0; i < threegicons.length - 3; i++) {
										String tgimg = "mobile/3g/" + threegicons[i];
										final Bitmap tgb = ZipStuff.getBitmap(path, tgimg);
										if (tgb != null) {
											Drawable tgd = new BitmapDrawable(null, tgb);
											if (paramPrefs.getBoolean("mobilecolor_enabled", false)) {
												animationtg.setColorFilter(paramPrefs.getInt("mobilecolor", 0xffffffff), PorterDuff.Mode.MULTIPLY);
											}
											animationtg.addFrame(tgd, 750);
										}
									}
									animationtg.setOneShot(false);
									iv.setImageDrawable(animationtg);
									animationtg.start();
								} else if (netstate == 2) {
									AnimationDrawable animationtg = new AnimationDrawable();
									for (int i = 4; i < threegicons.length; i++) {
										String tgimg = "mobile/3g/" + threegicons[i];
										final Bitmap tgb = ZipStuff.getBitmap(path, tgimg);
										if (tgb != null) {
											Drawable tgd = new BitmapDrawable(null, tgb);
											if (paramPrefs.getBoolean("mobilecolor_enabled", false)) {
												animationtg.setColorFilter(paramPrefs.getInt("mobilecolor", 0xffffffff), PorterDuff.Mode.MULTIPLY);
											}
											animationtg.addFrame(tgd, 750);
										}
									}
									animationtg.setOneShot(false);
									iv.setImageDrawable(animationtg);
									animationtg.start();
								} else {
									AnimationDrawable animationtg = new AnimationDrawable();
									for (int i = 0; i < threegicons.length; i++) {
										String tgimg = "mobile/3g/" + threegicons[i];
										final Bitmap tgb = ZipStuff.getBitmap(path, tgimg);
										if (tgb != null) {
											Drawable tgd = new BitmapDrawable(null, tgb);
											if (paramPrefs.getBoolean("mobilecolor_enabled", false)) {
												animationtg.setColorFilter(paramPrefs.getInt("mobilecolor", 0xffffffff), PorterDuff.Mode.MULTIPLY);
											}
											animationtg.addFrame(tgd, 750);
										}
									}
									animationtg.setOneShot(false);
									iv.setImageDrawable(animationtg);
									animationtg.start();
								}
								break;
							case 8:
							case 10:
								if (netstate == 0) {
									String fgimg = "mobile/4g/" + fourgicons[0];
									final Bitmap fgb = ZipStuff.getBitmap(path, fgimg);
									if (fgb != null) {
										Drawable fgd = new BitmapDrawable(null, fgb);
										if (paramPrefs.getBoolean("mobilecolor_enabled", false)) {
											fgd.setColorFilter(paramPrefs.getInt("mobilecolor", 0xffffffff), PorterDuff.Mode.MULTIPLY);
										}
										iv.setImageDrawable(fgd);
									}
								} else if (netstate == 1) {
									AnimationDrawable animationfg = new AnimationDrawable();
									for (int i = 0; i < fourgicons.length - 3; i++) {
										String fgimg = "mobile/4g/" + fourgicons[i];
										final Bitmap fgb = ZipStuff.getBitmap(path, fgimg);
										if (fgb != null) {
											Drawable fgd = new BitmapDrawable(null, fgb);
											if (paramPrefs.getBoolean("mobilecolor_enabled", false)) {
												animationfg.setColorFilter(paramPrefs.getInt("mobilecolor", 0xffffffff), PorterDuff.Mode.MULTIPLY);
											}
											animationfg.addFrame(fgd, 750);
										}
									}
									animationfg.setOneShot(false);
									iv.setImageDrawable(animationfg);
									animationfg.start();
								} else if (netstate == 2) {
									AnimationDrawable animationfg = new AnimationDrawable();
									for (int i = 4; i < fourgicons.length; i++) {
										String fgimg = "mobile/4g/" + fourgicons[i];
										final Bitmap fgb = ZipStuff.getBitmap(path, fgimg);
										if (fgb != null) {
											Drawable fgd = new BitmapDrawable(null, fgb);
											if (paramPrefs.getBoolean("mobilecolor_enabled", false)) {
												animationfg.setColorFilter(paramPrefs.getInt("mobilecolor", 0xffffffff), PorterDuff.Mode.MULTIPLY);
											}
											animationfg.addFrame(fgd, 750);
										}
									}
									animationfg.setOneShot(false);
									iv.setImageDrawable(animationfg);
									animationfg.start();
								} else {
									AnimationDrawable animationfg = new AnimationDrawable();
									for (int i = 0; i < fourgicons.length; i++) {
										String fgimg = "mobile/4g/" + fourgicons[i];
										final Bitmap fgb = ZipStuff.getBitmap(path, fgimg);
										if (fgb != null) {
											Drawable fgd = new BitmapDrawable(null, fgb);
											if (paramPrefs.getBoolean("mobilecolor_enabled", false)) {
												animationfg.setColorFilter(paramPrefs.getInt("mobilecolor", 0xffffffff), PorterDuff.Mode.MULTIPLY);
											}
											animationfg.addFrame(fgd, 750);
										}
									}
									animationfg.setOneShot(false);
									iv.setImageDrawable(animationfg);
									animationfg.start();
								}
								break;
							case 13:
								if (netstate == 0) { 
									String limg = "mobile/lte/" + lteicons[0];
									final Bitmap lb = ZipStuff.getBitmap(path, limg);
									if (lb != null) {
										Drawable ld = new BitmapDrawable(null, lb);
										if (paramPrefs.getBoolean("mobilecolor_enabled", false)) {
											ld.setColorFilter(paramPrefs.getInt("mobilecolor", 0xffffffff), PorterDuff.Mode.MULTIPLY);
										}
										iv.setImageDrawable(ld);
									}
								} else if (netstate == 1) {
									AnimationDrawable animationl = new AnimationDrawable();
									for (int i = 0; i < lteicons.length - 3; i++) {
										String limg = "mobile/lte/" + lteicons[i];
										final Bitmap lb = ZipStuff.getBitmap(path, limg);
										if (lb != null) {
											Drawable ld = new BitmapDrawable(null, lb);
											if (paramPrefs.getBoolean("mobilecolor_enabled", false)) {
												animationl.setColorFilter(paramPrefs.getInt("mobilecolor", 0xffffffff), PorterDuff.Mode.MULTIPLY);
											}
											animationl.addFrame(ld, 750);
										}
									}
									animationl.setOneShot(false);
									iv.setImageDrawable(animationl);
									animationl.start();
								} else if (netstate == 2) {
									AnimationDrawable animationl = new AnimationDrawable();
									for (int i = 4; i < lteicons.length; i++) {
										String limg = "mobile/lte/" + lteicons[i];
										final Bitmap lb = ZipStuff.getBitmap(path, limg);
										if (lb != null) {
											Drawable ld = new BitmapDrawable(null, lb);
											if (paramPrefs.getBoolean("mobilecolor_enabled", false)) {
												animationl.setColorFilter(paramPrefs.getInt("mobilecolor", 0xffffffff), PorterDuff.Mode.MULTIPLY);
											}
											animationl.addFrame(ld, 750);
										}
									}
									animationl.setOneShot(false);
									iv.setImageDrawable(animationl);
									animationl.start();
								} else {
									AnimationDrawable animationl = new AnimationDrawable();
									for (int i = 0; i < lteicons.length; i++) {
										String limg = "mobile/lte/" + lteicons[i];
										final Bitmap lb = ZipStuff.getBitmap(path, limg);
										if (lb != null) {
											Drawable ld = new BitmapDrawable(null, lb);
											if (paramPrefs.getBoolean("mobilecolor_enabled", false)) {
												animationl.setColorFilter(paramPrefs.getInt("mobilecolor", 0xffffffff), PorterDuff.Mode.MULTIPLY);
											}
											animationl.addFrame(ld, 750);
										}
									}
									animationl.setOneShot(false);
									iv.setImageDrawable(animationl);
									animationl.start();
								}
								break;
							case 15:
								if (netstate == 0) { 
									String himg = "mobile/h/" + hicons[0];
									final Bitmap hb = ZipStuff.getBitmap(path, himg);
									if (hb != null) {
										Drawable hd = new BitmapDrawable(null, hb);
										if (paramPrefs.getBoolean("mobilecolor_enabled", false)) {
											hd.setColorFilter(paramPrefs.getInt("mobilecolor", 0xffffffff), PorterDuff.Mode.MULTIPLY);
										}
										iv.setImageDrawable(hd);
									}
								} else if (netstate == 1) {
									AnimationDrawable animationh = new AnimationDrawable();
									for (int i = 0; i < hicons.length - 3; i++) {
										String himg = "mobile/h/" + hicons[i];
										final Bitmap hb = ZipStuff.getBitmap(path, himg);
										if (hb != null) {
											Drawable hd = new BitmapDrawable(null, hb);
											if (paramPrefs.getBoolean("mobilecolor_enabled", false)) {
												animationh.setColorFilter(paramPrefs.getInt("mobilecolor", 0xffffffff), PorterDuff.Mode.MULTIPLY);
											}
											animationh.addFrame(hd, 750);
										}
									}
									animationh.setOneShot(false);
									iv.setImageDrawable(animationh);
									animationh.start();
								} else if (netstate == 2) {
									AnimationDrawable animationh = new AnimationDrawable();
									for (int i = 4; i < hicons.length; i++) {
										String himg = "mobile/h/" + hicons[i];
										final Bitmap hb = ZipStuff.getBitmap(path, himg);
										if (hb != null) {
											Drawable hd = new BitmapDrawable(null, hb);
											if (paramPrefs.getBoolean("mobilecolor_enabled", false)) {
												animationh.setColorFilter(paramPrefs.getInt("mobilecolor", 0xffffffff), PorterDuff.Mode.MULTIPLY);
											}
											animationh.addFrame(hd, 750);
										}
									}
									animationh.setOneShot(false);
									iv.setImageDrawable(animationh);
									animationh.start();
								} else {
									AnimationDrawable animationh = new AnimationDrawable();
									for (int i = 0; i < hicons.length; i++) {
										String himg = "mobile/h/" + hicons[i];
										final Bitmap hb = ZipStuff.getBitmap(path, himg);
										if (hb != null) {
											Drawable hd = new BitmapDrawable(null, hb);
											if (paramPrefs.getBoolean("mobilecolor_enabled", false)) {
												animationh.setColorFilter(paramPrefs.getInt("mobilecolor", 0xffffffff), PorterDuff.Mode.MULTIPLY);
											}
											animationh.addFrame(hd, 750);
										}
									}
									animationh.setOneShot(false);
									iv.setImageDrawable(animationh);
									animationh.start();
								}
								break;
							}
						} else {
							return;
						}
					} catch (Throwable t) { XposedBridge.log(t); }
				}
			});	
		}	
	}
}
