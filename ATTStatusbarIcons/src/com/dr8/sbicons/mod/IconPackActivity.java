package com.dr8.sbicons.mod;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;

import android.app.ListActivity;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.dr8.sbicons.R;
import com.mobeta.android.dslv.DragSortListView;


public class IconPackActivity extends ListActivity
{
    private ArrayAdapter<String> adapter;

    private DragSortListView.DropListener onDrop =
        new DragSortListView.DropListener() {
            @Override
            public void drop(int from, int to) {
                if (from != to) {
                    DragSortListView list = getListView();
                    String item = adapter.getItem(from);
                    adapter.remove(item);
                    adapter.insert(item, to);
                    list.moveCheckState(from, to);
                    Log.d("DSLV", "Selected item is " + list.getCheckedItemPosition());
                }
            }
        };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.checkable_main);
        
        String path = Environment.getExternalStorageDirectory().toString()+"/xsbm";
        ArrayList<String> filearray = new ArrayList<String>();
        
        Log.d("Files", "Path: " + path);
        File f = new File(path);        
        File file[] = f.listFiles();
        Log.d("Files", "Size: "+ file.length);
        
        for (int i=0; i < file.length; i++) {
        	if (file[i].getName().toLowerCase().endsWith(".zip")) {
        		filearray.add(file[i].getName());
        		Log.d("Files", "FileName:" + file[i].getName());
        	}
        }
        String[] array = filearray.toArray(new String[filearray.size()]);
        ArrayList<String> arrayList = new ArrayList<String>(Arrays.asList(array));

        adapter = new ArrayAdapter<String>(this, R.layout.list_item_radio, R.id.text, arrayList);
        
        setListAdapter(adapter);
        
        DragSortListView list = getListView();
        list.setDropListener(onDrop);
        list.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
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
    public DragSortListView getListView() {
        return (DragSortListView) super.getListView();
    }
    
}
