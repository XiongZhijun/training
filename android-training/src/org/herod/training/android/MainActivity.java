package org.herod.training.android;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;

public class MainActivity extends Activity {

	private BroadcastReceiver receiver;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// 第一种将View设置到Activity中的方法
		setContentView(R.layout.activity_main);
		setTitle(getString(R.string.activity_title_1));
		findViewById(R.id.button1).setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				startActivity(new Intent("org.herod.training.android.second"));
			}
		});
		IntentFilter filter = new IntentFilter("new.receiver");
		HandlerThread handlerThread = new HandlerThread("newThread");
		handlerThread.start();
		Handler scheduler = new Handler(handlerThread.getLooper());
		receiver = new BroadcastReceiver() {
			public void onReceive(Context context, Intent intent) {
				Toast.makeText(context,
						"接收内部receiver消息：" + Thread.currentThread().getName(),
						Toast.LENGTH_LONG).show();
			}

		};
		registerReceiver(receiver, filter, null, scheduler);
	}

	@Override
	protected void onDestroy() {
		unregisterReceiver(receiver);
		super.onDestroy();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// 创建菜单
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	public void showLayouts(View view) {
		startActivity(new Intent(this, LayoutsActivity.class));
	}

	public void showServiceActivity(View view) {
		startActivity(new Intent(this, ServiceActivity.class));
	}

	public void sendInfoToReceiver(View view) {
		sendBroadcast(new Intent(this, SimpleReceiver.class));
	}

	public void sendInfoToInnerReceiver(View view) {
		sendBroadcast(new Intent("new.receiver"));
	}

	public void testContentProvider(View v) {
		startActivity(new Intent(this, ContentProviderActivity.class));
	}

}
