package org.herod.training.android;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

public class SecondActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// 第二种将View设置到Activity中的方法
		View view = getLayoutInflater().inflate(R.layout.second_activity, null);
		setContentView(view);
	}
}
