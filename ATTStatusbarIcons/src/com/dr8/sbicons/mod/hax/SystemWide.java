package com.dr8.sbicons.mod.hax;

import com.dr8.sbicons.mod.ZipStuff;

import android.content.res.XModuleResources;
import android.content.res.XResources;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Environment;
import de.robv.android.xposed.IXposedHookZygoteInit.StartupParam;
import de.robv.android.xposed.XSharedPreferences;

public class SystemWide {
	public static void initHandleZygote(StartupParam startupParam, XModuleResources modRes, XSharedPreferences prefs) {
		String iconpack = prefs.getString("iconset", null);
		String path = Environment.getExternalStorageDirectory() + "/xsbm/" + iconpack;
		String gon = "gps/stat_sys_gps_on.png";
		final Bitmap g = ZipStuff.getBitmapFromZip(path, gon);
		XResources.setSystemWideReplacement("android", "drawable", "stat_sys_gps_on", new XResources.DrawableLoader() {
			@Override
			public Drawable newDrawable(XResources res, int id) throws Throwable {
				return new BitmapDrawable(null, g);
			}
		});
	}
}
