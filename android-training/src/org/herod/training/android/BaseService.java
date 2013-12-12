package org.herod.training.android;

import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

public class BaseService extends Service {

	@SuppressLint("NewApi")
	@Override
	public void onCreate() {
		super.onCreate();
		Log.d(getClass().getSimpleName(), "onCreate : "
				+ Thread.currentThread().getName());
		PendingIntent contentIntent = PendingIntent.getActivity(this, 0,
				new Intent(this, MainActivity.class), 0);
		Notification notification = new Notification.Builder(this)
				.setContentTitle("前台Service")
				.setSmallIcon(R.drawable.ic_launcher)
				.setLargeIcon(
						BitmapFactory.decodeResource(getResources(),
								R.drawable.ic_launcher))
				.setContentInfo("这是一个前台Service").setOngoing(true)
				.setVibrate(new long[] { 0, 100, 200, 300 })
				.setContentIntent(contentIntent).build();
		startForeground(1, notification);
	}

	@Override
	public void onDestroy() {
		Log.d(getClass().getSimpleName(), "onDestroy : "
				+ Thread.currentThread().getName());
		stopForeground(true);
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
