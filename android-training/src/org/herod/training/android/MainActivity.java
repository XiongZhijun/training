package org.herod.training.android;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.View.OnClickListener;
import android.widget.Toast;

public class MainActivity extends Activity {

	private static final int CONTEXT_MENU_2 = 2;
	private static final int CONTEXT_MENU_1 = 1;
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

		registerForContextMenu(findViewById(R.id.button8));
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

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.login:
			Toast.makeText(this, "login", Toast.LENGTH_SHORT).show();
			return true;
		case R.id.regist:
			Toast.makeText(this, "regist", Toast.LENGTH_SHORT).show();
			return true;
		case R.id.help:
			Toast.makeText(this, "help", Toast.LENGTH_SHORT).show();
			return true;
		case R.id.action_settings:
			Toast.makeText(this, "settings", Toast.LENGTH_SHORT).show();
			return true;
		case R.id.basic_settings:
			Toast.makeText(this, "basic settings", Toast.LENGTH_SHORT).show();
			return true;
		case R.id.system_settings:
			Toast.makeText(this, "system settings", Toast.LENGTH_SHORT).show();
			return true;
		default:
			break;
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	public void onCreateContextMenu(ContextMenu menu, View v,
			ContextMenuInfo menuInfo) {
		if (v.getId() != R.id.button8) {
			return;
		}
		menu.add(0, CONTEXT_MENU_1, 0, "菜单1");
		menu.add(0, CONTEXT_MENU_2, 0, "菜单2");
	}

	@Override
	public boolean onContextItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case CONTEXT_MENU_1:
			Toast.makeText(this, "菜单1被选中！", Toast.LENGTH_SHORT).show();
			return true;
		case CONTEXT_MENU_2:
			Toast.makeText(this, "菜单2被选中！", Toast.LENGTH_SHORT).show();
			return true;
		default:
			break;
		}
		return super.onContextItemSelected(item);
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

	public void testAsyncTask(View v) {
		new SimpleAsyncTask(this).execute(2000L);
	}

}
