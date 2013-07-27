package com.dr8.sbicons.mod;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
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
	protected static final String APP_LIST = "apps.txt";
	protected static String apppkg;
	protected static String repimage;
	
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
		ZipEntry applist = null;
		try {
			zf = new ZipFile(path + zipFile);
			for (Enumeration<? extends ZipEntry> e = zf.entries(); e.hasMoreElements();) {
				ZipEntry ze = e.nextElement();
				if (ze.isDirectory()) 
					continue;
					
				String fname = ze.getName();
				if (fname.equals(APP_LIST)) {
					applist = ze;
				}
			}
			
			if (applist != null) {
				BufferedReader br = new BufferedReader(new InputStreamReader((zf.getInputStream(applist))));
				String line;
				while ((line = br.readLine()) != null) {
					String[] split = line.split("|");
					apppkg = split[0];
					repimage = split[1];
					result.put("apppkg", "repimage");
				}
			}
		} catch (IOException e) {
			Log.d(TAG, "Error opening zip: " + e);
		}
		return result;
	}
	
}
