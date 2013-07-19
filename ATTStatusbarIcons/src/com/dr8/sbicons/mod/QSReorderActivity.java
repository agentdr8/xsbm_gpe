package com.dr8.sbicons.mod;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.dr8.sbicons.R;
import com.terlici.dragndroplist.DragNDropListView;
import com.terlici.dragndroplist.DragNDropSimpleAdapter;


import android.app.Activity;
import android.os.Bundle;

public class QSReorderActivity extends Activity {

	 /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.qsdndlist);
        
        String[] qsNames = getResources().getStringArray(R.array.qs_item_names);
        int[] qsCodes = getResources().getIntArray(R.array.qs_item_codes);
        
        ArrayList<Map<String, Object>> items = new ArrayList<Map<String, Object>>();
        for (int i = 0; i < qsNames.length; i++) {
        	HashMap<String, Object> item = new HashMap<String, Object>();
            item.put("name", qsNames[i]);
            item.put("_id", qsCodes[i]);
			items.add(item);
		}
		
		DragNDropListView list = (DragNDropListView)findViewById(R.id.list1);
		list.setDragNDropAdapter(new DragNDropSimpleAdapter(this,
				 items,
				 R.layout.qsitems,
				 new String[]{"name"},
				 new int[]{R.id.text},
				 R.id.handler));
    }
}
