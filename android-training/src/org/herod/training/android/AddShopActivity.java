package org.herod.training.android;

import android.app.Activity;
import android.content.ContentValues;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class AddShopActivity extends Activity {

	private EditText shopNameEditText;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.add_shop_activity);
		setTitle("新增店铺");
		shopNameEditText = (EditText) findViewById(R.id.shopName);
	}

	public void addShop(View view) {
		String shopName = shopNameEditText.getText().toString();
		if (shopName == null || shopName.length() == 0) {
			shopNameEditText.setError("请输入店铺名称！");
			return;
		}
		ContentValues values = new ContentValues();
		values.put("name", shopName);
		values.put("image", R.drawable.kfc);
		getContentResolver().insert(
				Uri.parse("content://org.herod.study.android/shops"), values);
		finish();
	}
}
