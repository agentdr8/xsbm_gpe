package com.dr8.sbicons.ge.mod;

import java.util.Collection;
import java.util.Iterator;
import java.util.Set;

import com.dr8.sbicons.ge.mod.hax.AppIcons;
import com.dr8.sbicons.ge.mod.hax.BatteryIconColor;
import com.dr8.sbicons.ge.mod.hax.BatteryIcons;
import com.dr8.sbicons.ge.mod.hax.BatteryRainbow;
import com.dr8.sbicons.ge.mod.hax.Bluetooth;
import com.dr8.sbicons.ge.mod.hax.CenterClock;
import com.dr8.sbicons.ge.mod.hax.ClockAMPM;
import com.dr8.sbicons.ge.mod.hax.ClockColor;
import com.dr8.sbicons.ge.mod.hax.GPS;
import com.dr8.sbicons.ge.mod.hax.InvisBattery;
import com.dr8.sbicons.ge.mod.hax.InvisClock;
import com.dr8.sbicons.ge.mod.hax.MobileDataGE;
import com.dr8.sbicons.ge.mod.hax.SignalBars;
import com.dr8.sbicons.ge.mod.hax.SystemWide;
import com.dr8.sbicons.ge.mod.hax.ToTheLeft;
import com.dr8.sbicons.ge.mod.hax.TpNotif;
import com.dr8.sbicons.ge.mod.hax.TpStatusBar;
import com.dr8.sbicons.ge.mod.hax.Wifi;
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

	public String getDeviceModel() {
		  String model = Build.MODEL;
		  return capitalize(model);
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

		if (pref.getBoolean("framework", false) || pref.getBoolean("gps", false)) {
//			XposedBridge.log("XSBM: launching zygote stuff");
			SystemWide.initHandleZygote(startupParam, pref);
		}
		
		if (pref.getBoolean("tpstatus_enabled", false)) {
			TpStatusBar.initZygote();
		}
			
	}

	@Override
	public void handleLoadPackage(LoadPackageParam lpparam) throws Throwable {
		pref.reload();
		if (lpparam.packageName.equals("com.android.systemui")) {
			BatteryRainbow.initHandleLoadPackage(pref, lpparam);
			
			if (pref.getBoolean("batt_text_color_enabled", false)) {
				BatteryIconColor.initHandleLoadPackage(pref, lpparam);
			}
			
			if (pref.getBoolean("battery", false)) {
				BatteryIcons.initHandleLoadPackage(pref, lpparam);
			}
			
			if(pref.getBoolean("centerclock", false)) {
				CenterClock.initHandleLoadPackage(pref, lpparam.classLoader);
			}
			
			if (pref.getBoolean("addampm", false) && !pref.getBoolean("invisclock", false)) {
				ClockAMPM.initHandleLoadPackage(pref, lpparam, StatusBarModsSettings.IS_24H);
			}
		
			if (pref.getBoolean("tpstatus_enabled", false)) {
				TpStatusBar.initHandleLoadPackage(pref, lpparam.classLoader);
			}
			
			if (pref.getBoolean("invisclock", false)) {
				InvisClock.initHandleLoadPackage(pref, lpparam);
			}
		}

	}
	
	@Override
	public void handleInitPackageResources(InitPackageResourcesParam resparam) throws Throwable {
		pref.reload();
		XModuleResources modRes = XModuleResources.createInstance(MODULE_PATH, resparam.res);
		
		if (pref.getBoolean("to_the_left", false)) {
			ToTheLeft.initPackageResources(pref, modRes, resparam);
		}
				
		String intpath = "/data/data/com.dr8.sbicons/xsbm/apps.txt";
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
		    				AppIcons.initPackageResources(pref, resparam, item, modRes);
		    			}
			        }
			    }
			}
		}
	
		if (!resparam.packageName.equals(targetpkg)) {
			return;
		}
		
		if (pref.getBoolean("mobile_data", false)) {
			MobileDataGE.initPackageResources(pref, modRes, resparam);
		}
		
		if (pref.getBoolean("centerclock", false)) {
			CenterClock.initPackageResources(pref, resparam);
		}

		if (pref.getBoolean("tpnotif_enabled", false)) {
			TpNotif.initPackageResources(pref, modRes, resparam);
		}
			
		if (pref.getBoolean("invisbatt", false)) {
			InvisBattery.initPackageResources(pref, modRes, resparam);
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

		if (pref.getBoolean("signal", false) && !pref.getBoolean("hidesignal", false)) {
			SignalBars.initPackageResources(pref, modRes, resparam);
		}
			
	}
}
