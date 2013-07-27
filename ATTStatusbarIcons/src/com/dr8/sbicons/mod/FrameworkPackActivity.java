package com.dr8.sbicons.mod;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import android.annotation.SuppressLint;
import android.app.ListActivity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.dr8.sbicons.R;

public class FrameworkPackActivity extends ListActivity
{
    
	private ArrayAdapter<String> adapter;
	SharedPreferences prefs = null;
	final String intpath = getApplicationContext().getFilesDir().getPath() + "/xsbm/";
    final String extpath = Environment.getExternalStorageDirectory().toString() + "/xsbm/";
    
	@SuppressLint("DefaultLocale")
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ArrayList<String> filearray = new ArrayList<String>();
        
//        Log.d("Files", "Path: " + path);
        File f = new File(extpath);
        if (f.isDirectory()) {
	        final File file[] = f.listFiles();
	//        Log.d("Files", "Size: "+ file.length);
	        
	        for (int i=0; i < file.length; i++) {
	        	if (file[i].getName().toLowerCase().endsWith(".zip")) {
	        		filearray.add(file[i].getName());
	//        		Log.d("Files", "FileName:" + file[i].getName());
	        	}
	        }
        } else {
        	f.mkdirs();
        	InputStream in = getResources().openRawResource(R.raw.default_framework);
            FileOutputStream out;
            FileOutputStream out2;
			try {
				out = new FileOutputStream(extpath + "/default_framework.zip");
			    byte[] buff = new byte[1024];
			    int read = 0;
                while ((read = in.read(buff)) > 0) {
                  out.write(buff, 0, read);
                }
                out2 = new FileOutputStream(intpath + "/framework.zip");
			    byte[] buff2 = new byte[1024];
			    int read2 = 0;
                while ((read2 = in.read(buff2)) > 0) {
                  out2.write(buff2, 0, read2);
                }
                in.close();
                out.close();
                out2.close();
            } catch (IOException e) {
//				Log.d("Files", "Exceptions during default pack copy: " + e);
            } 
			final File file[] = f.listFiles();
			//        Log.d("Files", "Size: "+ file.length);
	        for (int i=0; i < file.length; i++) {
	        	if (file[i].getName().toLowerCase().endsWith(".zip")) {
	        		filearray.add(file[i].getName());
	//        		Log.d("Files", "FileName:" + file[i].getName());
	        	}
	        }
        }
        
        String[] array = filearray.toArray(new String[filearray.size()]);
        ArrayList<String> arrayList = new ArrayList<String>(Arrays.asList(array));
        Collections.sort(arrayList);
        
        adapter = new ArrayAdapter<String>(this, R.layout.iconpacks, R.id.zipitem, arrayList);
        
        setListAdapter(adapter);
        
    }
    
    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
	    String item = (String) getListAdapter().getItem(position);
	    if (ZipStuff.getPackInfo(item, extpath, ".xsbmfwpack") == 1) {
		    Toast.makeText(this, item + " selected", Toast.LENGTH_SHORT).show();
		    try {
				InputStream inf = new FileInputStream(extpath + item);
				FileOutputStream outf = new FileOutputStream(intpath + "framework.zip");
				byte[] buff = new byte[1024];
			    int read = 0;
                while ((read = inf.read(buff)) > 0) {
                  outf.write(buff, 0, read);
                }
                inf.close();
                outf.close();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		    finish();
	    } else {
	    	Toast.makeText(this, item + " is not a valid framework pack", Toast.LENGTH_SHORT).show();
	    }
    }
    
    @Override
    protected void onResume() {
    	super.onResume();
    }
    
    @Override
    protected void onStop() {
    	super.onStop();
    	
    }
}
