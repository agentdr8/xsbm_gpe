package com.dr8.sbicons.mod;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
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

	public static boolean unpackZip(String outpath, String zipname) {       
	     InputStream is;
	     ZipInputStream zis;
	     try {
	         String filename;
	         is = new FileInputStream(zipname);
	         zis = new ZipInputStream(new BufferedInputStream(is));          
	         ZipEntry ze;
	         byte[] buffer = new byte[1024];
	         int count;
	         while ((ze = zis.getNextEntry()) != null) {
	             filename = ze.getName();
	             if (ze.isDirectory()) {
	                File fmd = new File(outpath + filename);
	                fmd.mkdirs();
	                fmd.setWritable(true, true);
	                fmd.setExecutable(true, false);
	                fmd.setReadable(true, false);
	                continue;
	             }
	             FileOutputStream fout = new FileOutputStream(outpath + filename);
	             while ((count = zis.read(buffer)) != -1) {
	                 fout.write(buffer, 0, count);             
	             }
	             fout.close();               
	             zis.closeEntry();
	         }
	         zis.close();
	         File nomedia = new File(outpath + ".nomedia");
	         nomedia.createNewFile();
	     } catch(IOException e) {
	         e.printStackTrace();
	         return false;
	     }
	    return true;
	}
	
	public static Bitmap getBitmap(final String FilePath, final String imageFile) {
//	    Log.i(TAG, "Getting image '" + imageFileInZip + "' from '" + zipFilePath +"'");
	    Bitmap result = null;
	    try {
	        FileInputStream fis = new FileInputStream(FilePath + imageFile);
        	result = BitmapFactory.decodeStream(fis);
        	result.setDensity(Bitmap.DENSITY_NONE);
//        	result.setDensity(320);
	    } catch (FileNotFoundException e) {
	        Log.d(TAG, ": Error opening image file - FileNotFoundException: " + e);
	    } 
	    return result;
	}
	
	// expect zip filename, extsd path and .xsbmpack
	public static Integer getPackInfo(final String zipFile, final String path, final String infoFile) {
//		Log.i(TAG, "Getting pack id '" + infoFile + "' from '" + path + zipFile + "'");
		Integer result = 0;
		try {
			FileInputStream fis = new FileInputStream(path + "/" + zipFile);
	        ZipInputStream zis = new ZipInputStream(fis);
	        ZipEntry ze = null;
	        while ((ze = zis.getNextEntry()) != null) {
	            if (ze.getName().equals(infoFile)) {
	            	result = 1;
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
