package com.dr8.sbicons.mod;

import java.io.DataOutputStream;
import java.io.IOException;
import com.dr8.sbicons.R;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.Toast;

import com.stericson.RootTools.*;

public class RestartActivity extends Activity
{
    
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Restart Status Bar?")
        .setIcon(R.drawable.question)
        .setCancelable(false)
        .setPositiveButton("Restart", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				if (RootTools.isBusyboxAvailable()) {
					if (RootTools.isRootAvailable()) {
						Toast.makeText(RestartActivity.this, "Restarting SystemUI...", Toast.LENGTH_SHORT).show();
						killPackage("com.android.systemui");
						RestartActivity.this.finish();
					} else {
						Toast.makeText(RestartActivity.this, "Your phone is not rooted", Toast.LENGTH_SHORT).show();
					}
				} else {
					RootTools.offerBusyBox(RestartActivity.this);
				}				
			}
		})
        .setNegativeButton("Cancel",new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.cancel();
                RestartActivity.this.finish();            
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
        RestartActivity.this.finish();            
    }
    
    // killPackage code by serajr @XDA 
    // http://forum.xda-developers.com/showthread.php?p=44176299#post44176299
    private void killPackage(String packageToKill) { 
        Process su = null; 
        // get superuser 
        try { 
            su = Runtime.getRuntime().exec("su"); 
        } catch (IOException e) { 
            e.printStackTrace(); 
        } 
         
        // kill given package 
        if (su != null ){ 
            try { 
                DataOutputStream os = new DataOutputStream(su.getOutputStream());  
                os.writeBytes("pkill -f " + packageToKill + "\n"); 
                os.flush(); 
                os.writeBytes("exit\n"); 
                os.flush(); 
                su.waitFor(); 
            } catch (IOException e) { 
                e.printStackTrace(); 
            } catch (InterruptedException e) { 
                e.printStackTrace(); 
            } 
        } 
    } 
}
