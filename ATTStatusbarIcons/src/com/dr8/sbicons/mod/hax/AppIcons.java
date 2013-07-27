package com.dr8.sbicons.mod.hax;

import android.annotation.SuppressLint;
import android.content.res.XResources;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import com.dr8.sbicons.mod.ZipStuff;
import de.robv.android.xposed.XSharedPreferences;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.callbacks.XC_InitPackageResources;

@SuppressLint("SdCardPath")
public class AppIcons {

	public static void initPackageResources(XSharedPreferences paramPrefs, XC_InitPackageResources.InitPackageResourcesParam resParam, String rep) {
		try {
			String target = resParam.packageName;
			String iconpack = "iconpack.zip";
			String path = "/data/data/com.dr8.sbicons/xsbm/" + iconpack;
			
	    	String repl = "apps/" + target + "-" + rep;
//	    	Log.d("XSBM", "getting image from zip: " + repl);
			final Bitmap a = ZipStuff.getBitmapFromZip(path, repl);	        	
    		if (a != null) {
    			String noext = rep.substring(0, rep.length() - 4);
//    			Log.d("XSBM", " our rep minus extension " + noext);
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
