package com.dr8.sbicons.mod;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Map;

import de.robv.android.xposed.XposedBridge;

import android.content.Context;

public class QSPref {
	
	Context mContext;
	
	public void writeQSPref (String filename, ArrayList<Map<String, Object>> map) throws FileNotFoundException, IOException {
		File file = new File(mContext.getDir("data", 0), filename);    
		try {
			ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(file));
			outputStream.writeObject(map);
			outputStream.flush();
			outputStream.close();
		} catch (FileNotFoundException e) { XposedBridge.log(e); }
	}
	
	public void readQSPref (String filename, Object map) {
		File file = new File(mContext.getDir("data", 0), filename);    
//		ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(file));
//		inputStream.readObject(map);
//		inputStream.close();
	}
}
