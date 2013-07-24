package com.dr8.sbicons.mod.hax;

import android.content.res.XModuleResources;
import android.content.res.XResources;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Environment;

import com.dr8.sbicons.mod.ZipStuff;

import de.robv.android.xposed.XSharedPreferences;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.callbacks.XC_InitPackageResources;

public class BatteryIcons {

	public static void initPackageResources(XSharedPreferences paramPrefs, XModuleResources modRes, XC_InitPackageResources.InitPackageResourcesParam resParam) {
		try {
			String targetpkg = "com.android.systemui";
//			resParam.res.setReplacement(targetpkg, "drawable", "stat_sys_battery", modRes.fwd(R.drawable.stat_sys_battery));
//			resParam.res.setReplacement(targetpkg, "drawable", "stat_sys_battery_charge", modRes.fwd(R.drawable.stat_sys_battery_charge));
		} catch (Throwable t) { XposedBridge.log(t); }
		try {
			String path = Environment.getExternalStorageDirectory() + "/xsbm/test.zip";
			String targetpkg = "com.android.systemui";
			String[] battarray = {
					"stat_sys_battery_0.png",
					"stat_sys_battery_1.png",
					"stat_sys_battery_2.png",
					"stat_sys_battery_3.png",
					"stat_sys_battery_4.png",
					"stat_sys_battery_5.png",
					"stat_sys_battery_6.png",
					"stat_sys_battery_7.png",
					"stat_sys_battery_8.png",
					"stat_sys_battery_9.png",
					"stat_sys_battery_10.png",
					"stat_sys_battery_11.png",
					"stat_sys_battery_12.png",
					"stat_sys_battery_13.png",
					"stat_sys_battery_14.png",
					"stat_sys_battery_15.png",
					"stat_sys_battery_16.png",
					"stat_sys_battery_17.png",
					"stat_sys_battery_18.png",
					"stat_sys_battery_19.png",
					"stat_sys_battery_20.png",
					"stat_sys_battery_21.png",
					"stat_sys_battery_22.png",
					"stat_sys_battery_23.png",
					"stat_sys_battery_24.png",
					"stat_sys_battery_25.png",
					"stat_sys_battery_26.png",
					"stat_sys_battery_27.png",
					"stat_sys_battery_28.png",
					"stat_sys_battery_29.png",
					"stat_sys_battery_30.png",
					"stat_sys_battery_31.png",
					"stat_sys_battery_32.png",
					"stat_sys_battery_33.png",
					"stat_sys_battery_34.png",
					"stat_sys_battery_35.png",
					"stat_sys_battery_36.png",
					"stat_sys_battery_37.png",
					"stat_sys_battery_38.png",
					"stat_sys_battery_39.png",
					"stat_sys_battery_40.png",
					"stat_sys_battery_41.png",
					"stat_sys_battery_42.png",
					"stat_sys_battery_43.png",
					"stat_sys_battery_44.png",
					"stat_sys_battery_45.png",
					"stat_sys_battery_46.png",
					"stat_sys_battery_47.png",
					"stat_sys_battery_48.png",
					"stat_sys_battery_49.png",
					"stat_sys_battery_50.png",
					"stat_sys_battery_51.png",
					"stat_sys_battery_52.png",
					"stat_sys_battery_53.png",
					"stat_sys_battery_54.png",
					"stat_sys_battery_55.png",
					"stat_sys_battery_56.png",
					"stat_sys_battery_57.png",
					"stat_sys_battery_58.png",
					"stat_sys_battery_59.png",
					"stat_sys_battery_60.png",
					"stat_sys_battery_61.png",
					"stat_sys_battery_62.png",
					"stat_sys_battery_63.png",
					"stat_sys_battery_64.png",
					"stat_sys_battery_65.png",
					"stat_sys_battery_66.png",
					"stat_sys_battery_67.png",
					"stat_sys_battery_68.png",
					"stat_sys_battery_69.png",
					"stat_sys_battery_70.png",
					"stat_sys_battery_71.png",
					"stat_sys_battery_72.png",
					"stat_sys_battery_73.png",
					"stat_sys_battery_74.png",
					"stat_sys_battery_75.png",
					"stat_sys_battery_76.png",
					"stat_sys_battery_77.png",
					"stat_sys_battery_78.png",
					"stat_sys_battery_79.png",
					"stat_sys_battery_80.png",
					"stat_sys_battery_81.png",
					"stat_sys_battery_82.png",
					"stat_sys_battery_83.png",
					"stat_sys_battery_84.png",
					"stat_sys_battery_85.png",
					"stat_sys_battery_86.png",
					"stat_sys_battery_87.png",
					"stat_sys_battery_88.png",
					"stat_sys_battery_89.png",
					"stat_sys_battery_90.png",
					"stat_sys_battery_91.png",
					"stat_sys_battery_92.png",
					"stat_sys_battery_93.png",
					"stat_sys_battery_94.png",
					"stat_sys_battery_95.png",
					"stat_sys_battery_96.png",
					"stat_sys_battery_97.png",
					"stat_sys_battery_98.png",
					"stat_sys_battery_99.png",
					"stat_sys_battery_100.png",
					"stat_sys_battery_unknown.png"
			};
			
			for (int i = 0; i < battarray.length; i++) {
				String bimg = "battery/" + battarray[i];
				final Bitmap b = ZipStuff.getBitmapFromZip(path, bimg);
				if (b != null) {
					if (i == 102) {
						resParam.res.setReplacement(targetpkg, "drawable", "stat_sys_battery_unknown", new XResources.DrawableLoader() {
							@Override
							public Drawable newDrawable(XResources res, int id) throws Throwable {
								return new BitmapDrawable(null, b);
							}
						});
					} else {
						resParam.res.setReplacement(targetpkg, "drawable", "stat_sys_battery_" + i, new XResources.DrawableLoader() {
							@Override
							public Drawable newDrawable(XResources res, int id) throws Throwable {
								return new BitmapDrawable(null, b);
							}
						});
					}
				}
				
			}
			
			String[] charge = {
					"stat_sys_battery_charge_anim0.png",
					"stat_sys_battery_charge_anim1.png",
					"stat_sys_battery_charge_anim2.png",
					"stat_sys_battery_charge_anim3.png",
					"stat_sys_battery_charge_anim4.png",
					"stat_sys_battery_charge_anim5.png",
					"stat_sys_battery_charge_anim6.png",
					"stat_sys_battery_charge_anim7.png",
					"stat_sys_battery_charge_anim8.png",
					"stat_sys_battery_charge_anim9.png",
					"stat_sys_battery_charge_anim10.png"
			};
			
			for (int i = 0; i < charge.length; i++) {
				String bimg = "battery/charge/" + charge[i];
				final Bitmap b = ZipStuff.getBitmapFromZip(path, bimg);
				if (b != null) {
					resParam.res.setReplacement(targetpkg, "drawable", "stat_sys_battery_charge_anim" + i, new XResources.DrawableLoader() {
						@Override
						public Drawable newDrawable(XResources res, int id) throws Throwable {
							return new BitmapDrawable(null, b);
						}
					});
				}
			}
		} catch (Throwable t) { XposedBridge.log(t); }
	}
}
