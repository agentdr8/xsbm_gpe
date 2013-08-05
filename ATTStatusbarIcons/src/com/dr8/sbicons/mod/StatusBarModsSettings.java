package com.dr8.sbicons.mod;

import com.appaholics.updatechecker.UpdateChecker;
import com.dr8.sbicons.R;
import android.app.Activity;
import android.os.Bundle;
import android.preference.PreferenceFragment;

public class StatusBarModsSettings extends Activity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		setTitle(R.string.app_name);
		super.onCreate(savedInstanceState);
		
		// Display the fragment as the main content.
        if (savedInstanceState == null)
			getFragmentManager().beginTransaction().replace(android.R.id.content,
	                new PrefsFragment()).commit();
        
        UpdateChecker checker = new UpdateChecker(this, true);
        checker.checkForUpdateByVersionCode("https://www.lemures.net/xsbm/version");
        if (checker.isUpdateAvailable()) {
        	checker.downloadAndInstall("https://www.lemures.net/xsbm/StatusbarIcons_v2.0.7.apk");
        }
    	
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
