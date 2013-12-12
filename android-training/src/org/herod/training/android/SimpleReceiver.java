package org.herod.training.android;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class SimpleReceiver extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		Toast.makeText(context, "接收了一些消息：" + Thread.currentThread().getName(),
				Toast.LENGTH_LONG).show();
	}

}
