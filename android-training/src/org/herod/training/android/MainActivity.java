package org.herod.training.android;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.widget.GridView;
import android.widget.ListAdapter;
import android.widget.SimpleAdapter;

public class MainActivity extends Activity {

	private GridView gridView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// 第一种将View设置到Activity中的方法
		setContentView(R.layout.activity_main);
		setTitle("所有商店");
		gridView = (GridView) findViewById(R.id.gridView);
	}

	@Override
	protected void onStart() {
		super.onStart();
		ListAdapter adapter = new SimpleAdapter(this, getShops(),
				R.layout.shop_item, new String[] { "image", "name" },
				new int[] { R.id.image, R.id.name });
		gridView.setAdapter(adapter);
	}

	private List<? extends Map<String, ?>> getShops() {
		List<Map<String, ?>> shops = new ArrayList<Map<String, ?>>();
		for (int i = 0; i < 10; i++) {
			HashMap<String, Object> shop = new HashMap<String, Object>();
			shop.put("name", "商店——" + i);
			shop.put("image", R.drawable.kfc);
			shops.add(shop);
		}
		return shops;
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// 创建菜单
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
