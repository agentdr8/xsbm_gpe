package com.dr8.sbicons.mod.hax;

import android.content.res.XModuleResources;
import android.content.res.XResources;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Environment;

import com.dr8.sbicons.R;
import com.dr8.sbicons.mod.ZipStuff;

import de.robv.android.xposed.XSharedPreferences;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.callbacks.XC_InitPackageResources;

public class Bluetooth {

	public static void initPackageResources(XSharedPreferences paramPrefs, XModuleResources modRes, XC_InitPackageResources.InitPackageResourcesParam resParam) {
		try {
			String targetpkg = "com.android.systemui";
			String path = Environment.getExternalStorageDirectory() + "/xsbm/test.zip";
			String iname = "stat_sys_data_bluetooth.png";
			final Bitmap b = ZipStuff.getBitmapFromZip(path, iname);
			if (b != null) {
				resParam.res.setReplacement(targetpkg , "drawable", "stat_sys_data_bluetooth", new XResources.DrawableLoader() {
					@Override
					public Drawable newDrawable(XResources res, int id) throws Throwable {
						return new BitmapDrawable(b);
					}
				});
			}
			resParam.res.setReplacement(targetpkg , "drawable", "stat_sys_data_bluetooth", modRes.fwd(R.drawable.stat_sys_data_bluetooth));
			resParam.res.setReplacement(targetpkg, "drawable", "stat_sys_data_bluetooth_connected", modRes.fwd(R.drawable.stat_sys_data_bluetooth_connected));
		} catch (Throwable t) { XposedBridge.log(t); }
	}
}
