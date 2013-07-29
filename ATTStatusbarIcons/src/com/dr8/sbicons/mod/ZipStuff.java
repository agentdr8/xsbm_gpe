package com.dr8.sbicons.mod;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

@SuppressLint("DefaultLocale")
public class ZipStuff {

	private static String TAG = "XSBM";
	protected static final String APP_LIST = "apps.txt";

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
	            	zis.close();
	            	fis.close();
	                break;
	            }
	        }
	        zis.close();
        	fis.close();
	    } catch (FileNotFoundException e) {
	        Log.d(TAG, ": Extracting file: Error opening zip file - FileNotFoundException: " + e);
	    } catch (IOException e) {
	        Log.d(TAG, ": Extracting file: Error opening zip file - IOException: " + e);
	    }
	    return result;
	}
	
	// expect zip filename, extsd path, intsd path, and .xsbmpack
	public static Integer getPackInfo(final String zipFile, final String path, final String intpath, final String infoFile) {
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
	            if (ze.getName().equals("apps.txt")) {
	            	FileOutputStream fout = new FileOutputStream(intpath + "/apps.txt");
	            	byte[] buf = new byte[1024];
	            	int n = 0;
	            	while ((n = zis.read(buf, 0, 1024)) > -1)
	                    fout.write(buf, 0, n);

	                fout.close();
	                File f = new File(intpath + "/apps.txt");
	                f.setReadable(true, false);
	            }
	        }
	        zis.close();
	        fis.close();
	    } catch (FileNotFoundException e) {
	        Log.d(TAG, ": Extracting file: Error opening zip file - FileNotFoundException: " + e);
	    } catch (IOException e) {
	        Log.d(TAG, ": Extracting file: Error opening zip file - IOException: " + e);
	    }
		return result;
	}
	
	public static HashMap<String, String> getPackDetail(final String zipFile, final String path, final String infoFile) {
//		Log.i(TAG, "Getting pack id '" + infoFile + "' from '" + path + zipFile + "'");
		HashMap<String, String> result = new HashMap<String, String>();
		try {
			FileInputStream fis = new FileInputStream(path + zipFile);
			ZipInputStream zis = new ZipInputStream(fis);
			ZipEntry ze = null;
	        while ((ze = zis.getNextEntry()) != null) {
	            if (ze.getName().equals(infoFile)) {
	            	if (ze.getSize() == 0) {
	            		result.put("author", "N/A");
						result.put("note", "N/A");
						break;
	            	} else { 
	            		BufferedReader br = new BufferedReader(new InputStreamReader(zis));
	            		String line;
						while ((line = br.readLine()) != null) {
							if (line.startsWith("author")) {
								result.put("author", line.substring(7));
							} else if (line.startsWith("note")) {
								result.put("note", line.substring(5));
							} 
						}
						br.close();
	            	}
	            }
	        }
	        zis.close();
			fis.close();
	    } catch (FileNotFoundException e) {
	        Log.d(TAG, ": Extracting file: Error opening zip file - FileNotFoundException: " + e);
	    } catch (IOException e) {
	        Log.d(TAG, ": Extracting file: Error opening zip file - IOException: " + e);
	    }
		return result;
	}
	
	public static Multimap<String, String> getAppsList(final String File) {
//		Log.i(TAG, "Getting app names from '" + path + zipFile + "'");
		Multimap<String, String> result = HashMultimap.create();
		try {
			FileInputStream fis = new FileInputStream(File);
			BufferedReader br = new BufferedReader(new InputStreamReader(fis));
			String line;
			while ((line = br.readLine()) != null) {
//						Log.d(TAG, "our line is: " + line);
				String[] split = line.split("-");
//						Log.d(TAG, "our split0: " + split[0] + " and split1: " + split[1]);
				result.put(split[0], split[1]);
			}
			fis.close();
		} catch (IOException e) {
			Log.d(TAG, "Error getting applist: " + e);
		}
		return result;
	}
	
}
