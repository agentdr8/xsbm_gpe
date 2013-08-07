package com.dr8.sbicons.mod;

import com.dr8.sbicons.R;
import android.app.Activity;
import android.os.Bundle;
import android.preference.PreferenceFragment;

public class StatusBarModsSettings extends Activity {

	String VERSION_URL = "https://s3-us-west-1.amazonaws.com/agentdr8/xsbm/version";
	String REMOTE_APK_URL = "https://s3-us-west-1.amazonaws.com/agentdr8/xsbm/StatusbarIcons_latest.apk";

	@Override
	public void onCreate(Bundle savedInstanceState) {
		setTitle(R.string.app_name);
		super.onCreate(savedInstanceState);
		
		// Display the fragment as the main content.
        if (savedInstanceState == null)
			getFragmentManager().beginTransaction().replace(android.R.id.content,
	                new PrefsFragment()).commit();
        

    	int ALERT_ICON = R.drawable.update;
    	UpdateChecker uc = new UpdateChecker(this, VERSION_URL, REMOTE_APK_URL, ALERT_ICON);
    	uc.startUpdateChecker();
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
}
