package com.dr8.sbicons.mod;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import de.robv.android.xposed.XposedBridge;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class ZipStuff {

	private static String TAG = "XSBM";
	
	public static Bitmap getBitmapFromZip(final String zipFilePath, final String imageFileInZip){
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
	        XposedBridge.log(TAG + ": Extracting file: Error opening zip file - FileNotFoundException: " + e);
	    } catch (IOException e) {
	        XposedBridge.log(TAG + ": Extracting file: Error opening zip file - IOException: " + e);
	    }
	    return result;
	}
}
