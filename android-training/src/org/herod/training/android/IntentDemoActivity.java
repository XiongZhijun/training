package org.herod.training.android;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

public class IntentDemoActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// 第二种将View设置到Activity中的方法
		View view = getLayoutInflater().inflate(R.layout.intent_demo_activity,
				null);
		setContentView(view);
	}

	public void showWeb(View view) {
		Uri uri = Uri.parse("http://google.com");
		Intent it = new Intent(Intent.ACTION_VIEW, uri);
		startActivity(it);
	}

	public void showDailingApp(View view) {
		Uri uri = Uri.parse("tel:10086");
		Intent it = new Intent(Intent.ACTION_DIAL, uri);
		startActivity(it);
	}

	public void dailing(View view) {
		Uri uri = Uri.parse("tel:10086");
		Intent it = new Intent(Intent.ACTION_CALL, uri);
		startActivity(it);
	}

	public void showMap(View view) {
		Uri uri = Uri.parse("geo:38.899533,-77.036476");
		Intent it = new Intent(Intent.ACTION_VIEW, uri);
		startActivity(it);
	}

	public void showSmsApp(View view) {
		Uri uri = Uri.parse("smsto://10086");
		Intent it = new Intent(Intent.ACTION_VIEW, uri);
		it.putExtra("sms_body", "The SMS text");
		it.setType("vnd.android-dir/mms-sms");
		startActivity(it);
	}

	public void sendSms(View view) {
		Uri uri = Uri.parse("smsto://10086");
		Intent it = new Intent(Intent.ACTION_SENDTO, uri);
		it.putExtra("sms_body", "The SMS text");
		startActivity(it);

	}

	public void sendEmail(View view) {
		Uri uri = Uri.parse("mailto:admin@qq.com");
		Intent it = new Intent(Intent.ACTION_SENDTO, uri);
		startActivity(it);
	}

}
