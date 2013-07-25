package com.dr8.sbicons.mod;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import android.annotation.SuppressLint;
import android.app.ListActivity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Environment;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.dr8.sbicons.R;

public class IconPackActivity extends ListActivity
{
    final String path = Environment.getExternalStorageDirectory().toString() + "/xsbm/";

	private ArrayAdapter<String> adapter;
	SharedPreferences prefs = null;
	
	@SuppressLint("DefaultLocale")
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       
        ArrayList<String> filearray = new ArrayList<String>();
        
//        Log.d("Files", "Path: " + path);
        File f = new File(path);        
        final File file[] = f.listFiles();
//        Log.d("Files", "Size: "+ file.length);
        
       
        for (int i=0; i < file.length; i++) {
        	if (file[i].getName().toLowerCase().endsWith(".zip")) {
        		filearray.add(file[i].getName());
//        		Log.d("Files", "FileName:" + file[i].getName());
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
    	SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
    	SharedPreferences.Editor editor = prefs.edit();
	    String item = (String) getListAdapter().getItem(position);
	    if (ZipStuff.getPackInfo(item, path, ".xsbmpack") == 1) {
		    Toast.makeText(this, item + " selected", Toast.LENGTH_SHORT).show();
		    editor.putString("iconpack", item).commit();
		    finish();
	    } else {
	    	Toast.makeText(this, item + " is not a valid iconpack", Toast.LENGTH_SHORT).show();
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
