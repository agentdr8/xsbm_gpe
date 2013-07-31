package com.dr8.sbicons.mod;

import com.dr8.sbicons.R;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;

public class RestartActivity extends Activity
{
    
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Restart Status Bar?")
        .setIcon(R.drawable.question)
        .setCancelable(true)
        .setPositiveButton("Restart", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				try {
			        String command;
			        String envp[] = new String[1];
			        envp[0] = "PATH=" + System.getProperty("java.library.path");
			        command = "pkill com.android.systemui";
			        Process proc = Runtime.getRuntime().exec(new String[] { "su", "-c", command }, envp);
			        proc.waitFor();
			    } catch(Exception ex) {
			        
			    }				
			}
		})
        .setNegativeButton("Cancel",new DialogInterface.OnClickListener() {
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
