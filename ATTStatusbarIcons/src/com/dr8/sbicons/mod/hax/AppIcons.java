package com.dr8.sbicons.mod.hax;

import java.util.HashMap;
import java.util.Iterator;

import android.content.res.XResources;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Environment;
import android.util.Log;

import com.dr8.sbicons.mod.ZipStuff;

import de.robv.android.xposed.XSharedPreferences;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.callbacks.XC_InitPackageResources;

public class AppIcons {

	public static void initPackageResources(XSharedPreferences paramPrefs, XC_InitPackageResources.InitPackageResourcesParam resParam) {
		try {
			String target = resParam.packageName.toString();
			HashMap<String, String> appsmap = new HashMap<String, String>();
			
			String iconpack = paramPrefs.getString("iconpack", null);
			String path = Environment.getExternalStorageDirectory() + "/xsbm/" + iconpack;
			
			Iterator<String> mIterator = appsmap.keySet().iterator();
			while (mIterator.hasNext()) {
			    String key = (String) mIterator.next();
			    String value = (String) appsmap.get(key);
			    Log.d("XSBM", " our target is " + target + " and key is " + key + " with value of " + value);
			    if (key.equals(target)) {
			    	String rep = key + value;
					final Bitmap a = ZipStuff.getBitmapFromZip(path, "apps/" + rep);	        	
		    		if (a != null) {
		    			String noext = value.substring(0, -4);
		    			Log.d("XSBM", " our rep minus extension " + noext);
		    			resParam.res.setReplacement(target, "drawable", noext, new XResources.DrawableLoader() {
						@Override
						public Drawable newDrawable(XResources res, int id) throws Throwable {
							return new BitmapDrawable(null, a);
							}
		    			});
		        	}
			    }
			}
	    } catch (Throwable t) { XposedBridge.log(t); }
	}
}
