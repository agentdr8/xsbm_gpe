package com.dr8.sbicons.mod;

import java.util.Collection;
import java.util.Iterator;
import java.util.Set;

import com.dr8.sbicons.mod.hax.AppIcons;
import com.dr8.sbicons.mod.hax.BatteryIconColor;
import com.dr8.sbicons.mod.hax.BatteryIcons;
import com.dr8.sbicons.mod.hax.BatteryRainbow;
import com.dr8.sbicons.mod.hax.BatteryTextColor;
import com.dr8.sbicons.mod.hax.Bluetooth;
import com.dr8.sbicons.mod.hax.CenterClock;
import com.dr8.sbicons.mod.hax.ClockColor;
import com.dr8.sbicons.mod.hax.GPS;
import com.dr8.sbicons.mod.hax.HtcNetworkController;
import com.dr8.sbicons.mod.hax.InvisBattery;
import com.dr8.sbicons.mod.hax.InvisClock;
import com.dr8.sbicons.mod.hax.MobileData;
import com.dr8.sbicons.mod.hax.SignalBars;
import com.dr8.sbicons.mod.hax.SystemWide;
import com.dr8.sbicons.mod.hax.ToTheLeft;
import com.dr8.sbicons.mod.hax.TpNav;
import com.dr8.sbicons.mod.hax.TpNotif;
import com.dr8.sbicons.mod.hax.TpStatusbar;
import com.dr8.sbicons.mod.hax.Wifi;
import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;

import android.content.res.XModuleResources;
import android.os.Build;
import de.robv.android.xposed.IXposedHookInitPackageResources;
import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.IXposedHookZygoteInit;
import de.robv.android.xposed.XSharedPreferences;
import de.robv.android.xposed.callbacks.XC_InitPackageResources.InitPackageResourcesParam;
import de.robv.android.xposed.callbacks.XC_LoadPackage.LoadPackageParam;

public class StatusBarMods implements IXposedHookZygoteInit, IXposedHookInitPackageResources, IXposedHookLoadPackage {
//	private static final String TAG = "XSBM";
	private static String MODULE_PATH = null;
	private static XSharedPreferences pref;
	private static String targetpkg = "com.android.systemui";
		
	public String getDeviceName() {
		  String manufacturer = Build.MANUFACTURER;
		  return capitalize(manufacturer);
	}

	private String capitalize(String s) {
		  if (s == null || s.length() == 0) {
		    return "";
		  }
		  char first = s.charAt(0);
		  if (Character.isUpperCase(first)) {
		    return s;
		  } else {
		    return Character.toUpperCase(first) + s.substring(1);
		  }
	} 
	
	Multimap<String, String> appsmap = HashMultimap.create();
		
	@Override
	public void initZygote(StartupParam startupParam) throws Throwable {
		MODULE_PATH = startupParam.modulePath;
		pref = new XSharedPreferences("com.dr8.sbicons", "com.dr8.sbicons_preferences");
//		XModuleResources modRes = XModuleResources.createInstance(MODULE_PATH, null);
		pref.reload();

		if (pref.getBoolean("framework", false)) {
			SystemWide.initHandleZygote(startupParam, pref);
		}
	}

	@Override
	public void handleLoadPackage(LoadPackageParam lpparam) throws Throwable {
		pref.reload();
		if (lpparam.packageName.equals("com.android.systemui")) {
			BatteryRainbow.initHandleLoadPackage(pref, lpparam);
			BatteryIconColor.initHandleLoadPackage(pref, lpparam);
			BatteryIcons.initHandleLoadPackage(pref, lpparam);
			HtcNetworkController.initHandleLoadPackage(pref, lpparam);
			CenterClock.initHandleLoadPackage(pref, lpparam.classLoader);
			MobileData.initHandleLoadPackage(pref, lpparam);
		}
//		if (lpparam.packageName.equals("com.htc.launcher")) {
//			TpApps.initHandleLoadPackage(pref, lpparam);
//		}
	}
	
	@Override
	public void handleInitPackageResources(InitPackageResourcesParam resparam) throws Throwable {
		pref.reload();
		XModuleResources modRes = XModuleResources.createInstance(MODULE_PATH, resparam.res);
		
		if (pref.getBoolean("to_the_left", false)) {
			ToTheLeft.initPackageResources(pref, modRes, resparam);
		}
				
		String intpath = "/data/data/com.dr8.sbicons/files/apps.txt";
		if (pref.getBoolean("thirdparty", false)) {
			appsmap = ZipStuff.getAppsList(intpath);
			String currentpkg = resparam.packageName;
			if (appsmap != null && appsmap.containsKey(currentpkg)) {
				Set<String> keySet = appsmap.keySet();
			    Iterator<String> keyIterator = keySet.iterator();
			    while (keyIterator.hasNext() ) {
			        String key = (String) keyIterator.next();
			        if (key.equals(currentpkg)) {
			        	Collection<String> values = appsmap.get(key);
//				    	Log.d("XSBM", "inside values: " + values);
		    			for (String item : values) {
//		    				Log.d("XSBM", "launching appicons for: " + resparam.packageName);
		    				AppIcons.initPackageResources(pref, resparam, item);
		    			}
			        }
			    }
			}
		}
	
		if (resparam.packageName.equals("com.htc.launcher")) {
			if (pref.getBoolean("tpnav", false)) {
				TpNav.initPackageResources(pref, modRes, resparam);
			}
//			if (pref.getBoolean("tpapps", false)) {
//				TpApps.initPackageResources(pref, modRes, resparam);
//			}
			if (pref.getBoolean("tpstatus", false)) {
				TpStatusbar.initPackageResources(pref, modRes, resparam);
			}
		}

		if (!resparam.packageName.equals(targetpkg)) {
			return;
		}
		
		TpStatusbar.initPackageResources(pref, modRes, resparam);
		
		if (pref.getBoolean("centerclock", false)) {
			CenterClock.initPackageResources(pref, resparam);
		}

//		if (pref.getBoolean("qstile_bg_color_enabled", false)) {
//			TpQSTiles.initPackageResources(pref, modRes, resparam);
//		}
		
		if (pref.getBoolean("tpnotif", false)) {
			TpNotif.initPackageResources(pref, modRes, resparam);
		}
			
		if (pref.getBoolean("invisbatt", false)) {
			InvisBattery.initPackageResources(pref, modRes, resparam);
		}
		
		if (pref.getBoolean("invisclock", false)) {
			InvisClock.initPackageResources(pref, modRes, resparam);
		}
		
		if (pref.getBoolean("batt_text_color_enabled", false) && (!pref.getBoolean("batt_text_rainbow", false))) {
			BatteryTextColor.initPackageResources(pref, modRes, resparam);
		}
		
		if (pref.getBoolean("clock_text_color_enabled", false)) {
			ClockColor.initPackageResources(pref, modRes, resparam); 
		}

		if (pref.getBoolean("to_the_left", false)) {
			ToTheLeft.initPackageResources(pref, modRes, resparam);
		}
			
		if (pref.getBoolean("bluetooth", false)) {
			Bluetooth.initPackageResources(pref, modRes, resparam);
		}
		
		if (pref.getBoolean("gps", false)) {
			GPS.initPackageResources(pref, modRes, resparam);
		}
		
		if (pref.getBoolean("wifi", false)) {
			Wifi.initPackageResources(pref, modRes, resparam);
		}

		if (pref.getBoolean("signal", false)) {
			SignalBars.initPackageResources(pref, modRes, resparam);
		}
			
	}
}
