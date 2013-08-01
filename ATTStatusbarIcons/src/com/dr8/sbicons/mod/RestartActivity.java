package com.dr8.sbicons.mod;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import com.dr8.sbicons.R;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.Toast;

import com.stericson.RootTools.*;
import com.stericson.RootTools.exceptions.RootDeniedException;
import com.stericson.RootTools.execution.CommandCapture;

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
				if (RootTools.isBusyboxAvailable()) {
					if (RootTools.isRootAvailable()) {
						CommandCapture command = new CommandCapture(0, "pkill -TERM -f com.android.systemui");
						try {
							RootTools.getShell(true).add(command);
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (TimeoutException e) {
							Toast.makeText(RestartActivity.this, "Root request timed out", Toast.LENGTH_SHORT).show();
						} catch (RootDeniedException e) {
							Toast.makeText(RestartActivity.this, "Root request was denied", Toast.LENGTH_SHORT).show();
						}
						dialog.dismiss();
						finish();
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
