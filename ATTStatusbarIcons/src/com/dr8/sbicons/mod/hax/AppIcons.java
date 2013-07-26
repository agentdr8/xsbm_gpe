package com.dr8.sbicons.mod.hax;

import android.content.res.XResources;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Environment;
import com.dr8.sbicons.mod.ZipStuff;

import de.robv.android.xposed.XSharedPreferences;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.callbacks.XC_InitPackageResources;

public class AppIcons {

	public static void initPackageResources(XSharedPreferences paramPrefs, String rep, XC_InitPackageResources.InitPackageResourcesParam resParam) {
		try {
			String target = resParam.packageName.toString();
			String replacement = rep;
			
			String iconpack = paramPrefs.getString("iconpack", null);
			String path = Environment.getExternalStorageDirectory() + "/xsbm/" + iconpack;
			
			final Bitmap a = ZipStuff.getBitmapFromZip(path, rep);	        	
    		if (a != null) {
    			String noext = replacement.substring(0, -4);
    			resParam.res.setReplacement(target, "drawable", noext, new XResources.DrawableLoader() {
				@Override
				public Drawable newDrawable(XResources res, int id) throws Throwable {
					return new BitmapDrawable(null, a);
					}
    			});
        	}
	    } catch (Throwable t) { XposedBridge.log(t); }
	}
}
