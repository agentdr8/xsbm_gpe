package com.dr8.sbicons.mod;

import java.util.ArrayList;
import java.util.Arrays;

import android.app.ListActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CheckedTextView;

import com.dr8.sbicons.R;
import com.mobeta.android.dslv.DragSortListView;
import com.mobeta.android.dslv.DragSortListView.RemoveListener;


public class QSSortActivity extends ListActivity
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
                }
            }
        };

    private RemoveListener onRemove =
        new DragSortListView.RemoveListener() {
            @Override
            public void remove(int which) {
                DragSortListView list = getListView();
                String item = adapter.getItem(which);
                adapter.remove(item);
                list.removeCheckState(which);
            }
        };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.checkable_main);
        
        String[] array = getResources().getStringArray(R.array.qs_item_names);
        ArrayList<String> arrayList = new ArrayList<String>(Arrays.asList(array));

        adapter = new ArrayAdapter<String>(this, R.layout.list_item_checkable, R.id.text, arrayList);
        
        setListAdapter(adapter);
        
        DragSortListView list = getListView();
        list.setDropListener(onDrop);
        list.setRemoveListener(onRemove);
   }

    @Override
    protected void onResume() {
    	super.onResume();
    }
    
    @Override
    protected void onStop() {
    	super.onStop();
    	DragSortListView list = getListView();
    	int j = list.getChildCount();
    	Log.i("XSBM", ": num of child views " + j);
    	for (int i = 0; i < j; i++) {
    		View cview = list.getChildAt(i);
    		CheckedTextView mCheckedTextView = (CheckedTextView) cview.findViewById(R.id.text);
    		String item = adapter.getItem(i);
    		if (mCheckedTextView.isChecked()) {
    			Log.i("XSBM", ": " + "item" + i + " " + item.toString() + " is checked");
    		}
    	}
    }
    
    @Override
    public DragSortListView getListView() {
        return (DragSortListView) super.getListView();
    }
    
}
