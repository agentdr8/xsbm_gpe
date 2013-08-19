package com.dr8.sbicons.mod;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.widget.Toast;

public class UpdateReceiver extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		if (intent.getAction().equals(Intent.ACTION_PACKAGE_REPLACED)) {
			ApplicationInfo app = new ApplicationInfo();
			if (app.packageName.equals("com.dr8.sbicons")) {
				Intent i = new Intent(context, StatusBarModsSettings.class);
				i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
				Toast.makeText(context, "Restarting XSBM...", Toast.LENGTH_SHORT).show();
				context.startActivity(i);
			}
		}
	}

}