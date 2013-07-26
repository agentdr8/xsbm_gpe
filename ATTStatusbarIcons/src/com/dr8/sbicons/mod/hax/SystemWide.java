package com.dr8.sbicons.mod.hax;

import com.dr8.sbicons.mod.ZipStuff;

import android.content.Context;
import android.content.res.XResources;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import de.robv.android.xposed.IXposedHookZygoteInit.StartupParam;
import de.robv.android.xposed.XSharedPreferences;
import de.robv.android.xposed.XposedBridge;

public class SystemWide {
	
	public static void initHandleZygote(StartupParam startupParam, Context mCtx, XSharedPreferences prefs) {
		Context mContext = mCtx;
		
		String iconpack = prefs.getString("frameworkpack", null);
		
		String internal = mContext.getApplicationContext().getFilesDir().getParent();
		
		String path = internal + "/xsbm/" + iconpack;
		
		String[] fwicons = {
				"stat_sys_gps_on",
				"stat_notify_car_mode",
				"stat_notify_wifi_in_range",
				"stat_sys_adb",
				"stat_sys_data_usb",
				"stat_sys_tether_bluetooth",
				"stat_sys_tether_usb",
				"stat_sys_tether_wifi",
				"text_select_handle_left",
				"text_select_handle_middle",
				"text_select_handle_right",
				"stat_sys_upload_anim0",
				"stat_sys_upload_anim1",
				"stat_sys_upload_anim2",
				"stat_sys_upload_anim3",
				"stat_sys_upload_anim4",
				"stat_sys_upload_anim5",
				"stat_sys_download_anim0",
				"stat_sys_download_anim1",
				"stat_sys_download_anim2",
				"stat_sys_download_anim3",
				"stat_sys_download_anim4",
				"stat_sys_download_anim5"
		};
		
		try {
			for (int i = 0; i < fwicons.length; i++) {
				String fimg = fwicons[i] + ".png";
				final Bitmap fb = ZipStuff.getBitmapFromZip(path, fimg);
				if (fb != null) {
					XResources.setSystemWideReplacement("android", "drawable", fwicons[i], new XResources.DrawableLoader() {
						@Override
						public Drawable newDrawable(XResources res, int id) throws Throwable {
							return new BitmapDrawable(null, fb);
						}
					});
				}
			}
		} catch (Throwable t) { XposedBridge.log(t); }
	}
}
