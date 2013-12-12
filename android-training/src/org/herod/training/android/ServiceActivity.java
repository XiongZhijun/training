package org.herod.training.android;

import org.herod.training.android.BaseService.BaseBinder;

import android.app.Activity;
import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

public class ServiceActivity extends Activity {

	private ServiceConnection conn;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.service_activity);
	}

	public void startService(View view) {
		startService(new Intent(this, BaseService.class));
	}

	public void stopService(View view) {
		stopService(new Intent(this, BaseService.class));
	}

	public void bindService(View view) {
		conn = new ServiceConnection() {
			public void onServiceDisconnected(ComponentName name) {
				Toast.makeText(ServiceActivity.this, "onServiceDisconnected",
						Toast.LENGTH_LONG).show();
			}

			public void onServiceConnected(ComponentName name, IBinder service) {
				Toast.makeText(ServiceActivity.this, "onServiceDisconnected",
						Toast.LENGTH_LONG).show();
				BaseBinder binder = (BaseBinder) service;
				binder.sayHello("zhang san");
			}
		};
		bindService(new Intent(this, BaseService.class), conn,
				Service.BIND_AUTO_CREATE);
	}

	public void unbindService(View view) {
		try {
			unbindService(conn);
		} catch (RuntimeException e) {
			Log.e(getClass().getSimpleName(), e.getMessage(), e);
			Toast.makeText(this, "exception : " + e.getMessage(),
					Toast.LENGTH_LONG).show();
		}
	}
}
