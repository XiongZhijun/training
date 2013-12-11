package org.herod.training.android;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class LayoutsActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.layouts_activity);
	}

	public void showGridLayout(View view) {
		startActivity(new Intent(this, IntentDemoActivity.class));
	}

	public void showVerticalLinearLayout(View view) {
		Toast.makeText(this, "当前Activity就是用的这种布局！", Toast.LENGTH_LONG).show();
	}

	public void showHorizontalLinearLayout(View view) {
		startActivity(new Intent(this, HorizontalLinearLayoutActivity.class));
	}

	public void showRelativeLayout(View view) {
		Toast.makeText(this, "MainActivity用的是这种布局！", Toast.LENGTH_LONG).show();
	}

	public void showFrameLayout(View view) {
		startActivity(new Intent(this, FrameLayoutActivity.class));
	}

	public void showTableLayout(View view) {
		startActivity(new Intent(this, TableLayoutActivity.class));
	}
}
