package com.dr8.sbicons.mod;

import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;

import com.dr8.sbicons.R;
import com.stericson.RootTools.RootTools;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceFragment;
import android.text.format.DateFormat;
import android.widget.Toast;

public class StatusBarModsSettings extends Activity {

	String VERSION_URL = "https://dl.dropboxusercontent.com/u/3842440/version_ge";
	String REMOTE_APK_URL = "https://dl.dropboxusercontent.com/u/3842440/StatusbarIconsGE_latest.apk";
	public static int IS_24H;
	SharedPreferences prefs = null;
	Context mCtx;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		setTitle(R.string.app_name);
		super.onCreate(savedInstanceState);
		
		// Display the fragment as the main content.
        if (savedInstanceState == null)
			getFragmentManager().beginTransaction().replace(android.R.id.content,
	                new PrefsFragment()).commit();
        
        if (!DateFormat.is24HourFormat(this)) {
        	IS_24H = 0;
        } else {
        	IS_24H = 1;
        }
        
    	int ALERT_ICON = R.drawable.update;
    	UpdateChecker uc = new UpdateChecker(this, VERSION_URL, REMOTE_APK_URL, ALERT_ICON);
    	uc.startUpdateChecker();
    	
    	prefs = getSharedPreferences("com.dr8.sbicons_preferences", MODE_PRIVATE);
	}
	
	public static class PrefsFragment extends PreferenceFragment {
		@Override
		public void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);

			// this is important because although the handler classes that read these settings
			// are in the same package, they are executed in the context of the hooked package
			getPreferenceManager().setSharedPreferencesMode(MODE_WORLD_READABLE);
			addPreferencesFromResource(R.xml.preferences);
		}
	}
	
	@Override
	public void onStop() {
		super.onStop();
	}
	
	@Override
    protected void onResume() {
        super.onResume();
        mCtx = getApplicationContext();
        String intpath = getApplicationContext().getFilesDir().getParent() + "/xsbm/";
        File f = new File(intpath);
        if (prefs.getBoolean("firstrun", true) && !f.exists()) {
			Toast.makeText(StatusBarModsSettings.this, "This appears to be your first time\nlaunching XSBM. Setting up default iconpack..", Toast.LENGTH_LONG).show();
			Intent i = new Intent(mCtx, IconPackActivity.class);
			i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			mCtx.startActivity(i);
        } else {
        	prefs.edit().putBoolean("firstrun", false).commit();
        }
    }
	
	@Override
	public void onBackPressed() {
	    doExit();
	}
	
	private void doExit() {
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Apply changes?")
        .setIcon(R.drawable.question)
        .setCancelable(false)
        .setPositiveButton("Apply", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				if (RootTools.isBusyboxAvailable()) {
					if (RootTools.isRootAvailable()) {
						Toast.makeText(StatusBarModsSettings.this, "Restarting SystemUI...", Toast.LENGTH_SHORT).show();
						killPackage("com.android.systemui");
						StatusBarModsSettings.this.finish();
					} else {
						Toast.makeText(StatusBarModsSettings.this, "Your phone is not rooted", Toast.LENGTH_SHORT).show();
						StatusBarModsSettings.this.finish();
					}
				} else {
					RootTools.offerBusyBox(StatusBarModsSettings.this);
				}				
			}
		})
        .setNegativeButton("Exit",new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.cancel();
                StatusBarModsSettings.this.finish();            
            }
        });
        
        AlertDialog alert = builder.create();
        alert.show();
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
