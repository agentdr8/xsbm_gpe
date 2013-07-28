package com.dr8.sbicons.mod;

import com.dr8.sbicons.R;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;

public class AboutActivity extends Activity
{
    
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(getResources().getString(R.string.app_shortname) + " - " + 
        		getResources().getString(R.string.version) + "\n - by " + getResources().getString(R.string.author))
        .setIcon(R.drawable.about)
        .setMessage(getResources().getString(R.string.credits))
        .setCancelable(false)
        .setNegativeButton("Close",new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.cancel();
                finish();            
            }
        });
        
        AlertDialog alert = builder.create();
        alert.show();
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
