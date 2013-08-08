package com.dr8.sbicons.mod;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class UpdateReceiver extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		Intent i = new Intent(context, StatusBarModsSettings.class);
		i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		Toast.makeText(context, "Restarting XSBM...", Toast.LENGTH_SHORT).show();
		context.startActivity(i);
	}

}