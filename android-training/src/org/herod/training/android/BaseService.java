package org.herod.training.android;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

public class BaseService extends Service {

	@Override
	public void onCreate() {
		super.onCreate();
		Log.d(getClass().getSimpleName(), "onCreate : "
				+ Thread.currentThread().getName());
	}

	@Override
	public void onDestroy() {
		Log.d(getClass().getSimpleName(), "onDestroy : "
				+ Thread.currentThread().getName());
		super.onDestroy();
	}

	@Override
	public void onRebind(Intent intent) {
		super.onRebind(intent);
		Log.d(getClass().getSimpleName(), "onRebind : "
				+ Thread.currentThread().getName());
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		Log.d(getClass().getSimpleName(), "onStartCommand : "
				+ Thread.currentThread().getName());
		return super.onStartCommand(intent, flags, startId);
	}

	@Override
	public boolean onUnbind(Intent intent) {
		Log.d(getClass().getSimpleName(), "onUnbind : "
				+ Thread.currentThread().getName());
		return super.onUnbind(intent);
	}

	@Override
	public IBinder onBind(Intent intent) {
		Log.d(getClass().getSimpleName(), "onBind : "
				+ Thread.currentThread().getName());
		return new BaseBinder();
	}

	public class BaseBinder extends Binder {

		public void sayHello(String user) {
			Log.d(getClass().getSimpleName(), "say hello to : " + user);
			Toast.makeText(BaseService.this, "hello, " + user,
					Toast.LENGTH_LONG).show();
		}
	}
}
