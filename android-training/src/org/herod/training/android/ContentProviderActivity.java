package org.herod.training.android;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

public class ContentProviderActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.content_provider);
	}

	public void query(View v) {
		getContentResolver().query(getUri(), null, null, null, null);
	}

	public void delete(View v) {
		getContentResolver().delete(getUri(), null, null);
	}

	public void update(View v) {
		getContentResolver().update(getUri(), null, null, null);
	}

	public void insert(View v) {
		getContentResolver().insert(getUri(), null);
	}

	private Uri getUri() {
		return Uri.parse("content://org.herod.study.android");
	}

}
