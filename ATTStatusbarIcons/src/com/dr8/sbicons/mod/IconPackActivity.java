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
import java.util.HashMap;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.dr8.sbicons.R;

public class IconPackActivity extends ListActivity implements OnItemLongClickListener
{
    private String intpath = null;
    private String privfiles = null;
    private String extpath = null;
    
	private ArrayAdapter<String> adapter;
	SharedPreferences prefs = null;
	
	@SuppressLint("DefaultLocale")
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        intpath = getApplicationContext().getFilesDir().getParent() + "/xsbm/";
        privfiles = getApplicationContext().getFilesDir().getPath();

        extpath = Environment.getExternalStorageDirectory().toString() + "/xsbm/";
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
        	InputStream in = getResources().openRawResource(R.raw.default_iconpack);
            FileOutputStream out;
            File f2 = new File(intpath);
            if (!f2.isDirectory()) {
            	f2.mkdirs();
            	f2.setExecutable(true, false);
            	f2.setReadable(true, false);
            	f2.setWritable(true, true);
            }
            FileOutputStream out2;
			try {
				out = new FileOutputStream(extpath + "default_iconpack.zip");
			    byte[] buff = new byte[1024];
			    int read = 0;
                while ((read = in.read(buff)) > 0) {
                  out.write(buff, 0, read);
                }
                in.close();
                out.close();
                
                InputStream in2 = getResources().openRawResource(R.raw.default_iconpack);
                out2 = new FileOutputStream(intpath + "iconpack.zip");
			    byte[] buff2 = new byte[1024];
			    int read2 = 0;
                while ((read2 = in2.read(buff2)) > 0) {
                  out2.write(buff2, 0, read2);
                }
                in2.close();
                out2.close();
              
                File f3 = new File(intpath + "iconpack.zip");
                f3.setReadable(true, false);
                
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
        
        ListView lv = getListView();
        LayoutInflater inflater = getLayoutInflater();
        ViewGroup header = (ViewGroup) inflater.inflate(R.layout.list_header, lv, false);
        lv.addHeaderView(header, null, false);
        
        String[] array = filearray.toArray(new String[filearray.size()]);
        ArrayList<String> arrayList = new ArrayList<String>(Arrays.asList(array));
        Collections.sort(arrayList);
        
        adapter = new ArrayAdapter<String>(this, R.layout.iconpacks, R.id.zipitem, arrayList);
        
        setListAdapter(adapter);
        this.getListView().setOnItemLongClickListener(new OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
            	String item = (String) getListView().getItemAtPosition(position);
        		HashMap<String, String> hash = new HashMap<String, String>();
        	    hash = ZipStuff.getPackDetail(item, extpath, ".xsbmpack");
        	    if (hash != null) {
        	    	AlertDialog.Builder builder = new AlertDialog.Builder(IconPackActivity.this);
        	        builder.setTitle("Pack details")
        	        .setIcon(R.drawable.about)
        	        .setMessage("Author: " + hash.get("author") + "\n" + "Notes: " + hash.get("note"))
        	        .setCancelable(false)
        	        .setNegativeButton("Close",new DialogInterface.OnClickListener() {
        	            public void onClick(DialogInterface dialog, int id) {
        	                dialog.cancel();
        	            }
        	        });
        	        
        	        AlertDialog alert = builder.create();
        	        alert.show();
        	    }
        		return true;
            }
        });
    }
    
    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
	    String item = (String) getListAdapter().getItem(position);
	    if (ZipStuff.getPackInfo(item, extpath, privfiles, ".xsbmpack") == 1) {
		    Toast.makeText(this, item + " selected", Toast.LENGTH_SHORT).show();
		    try {
				InputStream inf = new FileInputStream(extpath + item);
				FileOutputStream outf = new FileOutputStream(intpath + "iconpack.zip");
				byte[] buff = new byte[1024];
			    int read = 0;
                while ((read = inf.read(buff)) > 0) {
                  outf.write(buff, 0, read);
                }
                inf.close();
                outf.close();
                File f = new File(intpath + "iconpack.zip");
                f.setReadable(true, false);
                
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
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

	@Override
	public boolean onItemLongClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
		
		return true;
	}
}
