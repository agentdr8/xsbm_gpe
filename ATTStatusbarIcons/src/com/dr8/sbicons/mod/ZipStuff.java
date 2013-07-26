package com.dr8.sbicons.mod;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

@SuppressLint("DefaultLocale")
public class ZipStuff {

	private static String TAG = "XSBM";
	
	public static Bitmap getBitmapFromZip(final String zipFilePath, final String imageFileInZip) {
//	    Log.i(TAG, "Getting image '" + imageFileInZip + "' from '" + zipFilePath +"'");
	    Bitmap result = null;
	    try {
	        FileInputStream fis = new FileInputStream(zipFilePath);
	        ZipInputStream zis = new ZipInputStream(fis);
	        ZipEntry ze = null;
	        while ((ze = zis.getNextEntry()) != null) {
	            if (ze.getName().equals(imageFileInZip)) {
	            	result = BitmapFactory.decodeStream(zis);
	            	result.setDensity(Bitmap.DENSITY_NONE);
	                break;
	            }
	        }
	    } catch (FileNotFoundException e) {
	        Log.d(TAG, ": Extracting file: Error opening zip file - FileNotFoundException: " + e);
	    } catch (IOException e) {
	        Log.d(TAG, ": Extracting file: Error opening zip file - IOException: " + e);
	    }
	    return result;
	}
	
	public static Integer getPackInfo(final String zipFile, final String path, final String infoFile) {
//		Log.i(TAG, "Getting pack id '" + infoFile + "' from '" + path + zipFile + "'");
		Integer result = 0;
		try {
			FileInputStream fis = new FileInputStream(path + zipFile);
	        ZipInputStream zis = new ZipInputStream(fis);
	        ZipEntry ze = null;
	        while ((ze = zis.getNextEntry()) != null) {
	            if (ze.getName().equals(infoFile)) {
	            	result = 1;
	            }
	        }
	        zis.close();
	    } catch (FileNotFoundException e) {
	        Log.d(TAG, ": Extracting file: Error opening zip file - FileNotFoundException: " + e);
	    } catch (IOException e) {
	        Log.d(TAG, ": Extracting file: Error opening zip file - IOException: " + e);
	    }
		return result;
	}
	
	public static HashMap<String, String> getAppsList(final String zipFile, final String path) {
//		Log.i(TAG, "Getting app names from '" + path + zipFile + "'");
		HashMap<String, String> result = new HashMap<String, String>();
		ZipFile zf = null;
		try {
			zf = new ZipFile(path + zipFile);
			Enumeration<? extends ZipEntry> e = zf.entries();
			while (e.hasMoreElements()) {
				ZipEntry ze = e.nextElement();
				if (!ze.isDirectory()) {
					if (ze.getName().startsWith("apps")) {
						String fn = ze.getName().substring(5);
						Log.d(TAG, ": our element inside is " + fn);
						String item[] = fn.split("-");
						result.put(item[0], item[1]);
					}
				}
			}
	    } catch (FileNotFoundException e) {
	        Log.d(TAG, ": Extracting file: Error opening zip file - FileNotFoundException: " + e);
	    } catch (IOException e) {
	        Log.d(TAG, ": Extracting file: Error opening zip file - IOException: " + e);
	    }
		return result;
	}
}
