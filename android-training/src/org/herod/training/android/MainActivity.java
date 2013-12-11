package org.herod.training.android;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// 第一种将View设置到Activity中的方法
		setContentView(R.layout.activity_main);
		findViewById(R.id.button1).setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				startActivity(new Intent("org.herod.training.android.second"));
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// 创建菜单
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	/**
	 * button2 的onclick事件注册是在layout文件中通过设置onClick属性来完成。
	 * 
	 * @param view
	 */
	public void buttonTwoOnClick(View view) {
		Toast.makeText(this, "Button 2 On Click", Toast.LENGTH_LONG).show();
	}

}
