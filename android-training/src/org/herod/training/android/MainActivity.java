package org.herod.training.android;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.GridView;
import android.widget.ListAdapter;
import android.widget.SimpleAdapter;

public class MainActivity extends Activity {

	private GridView gridView;
	private ShopLoadTask shopLoadTask;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		setTitle("所有店铺");
		gridView = (GridView) findViewById(R.id.gridView);
	}

	@Override
	protected void onStart() {
		super.onStart();
		shopLoadTask = new ShopLoadTask();
		shopLoadTask.execute();
	}

	@Override
	protected void onStop() {
		super.onStop();
	}

	class ShopLoadTask extends
			AsyncTask<Void, Void, List<? extends Map<String, ?>>> {

		private ProgressDialog progressDialog;

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			progressDialog = ProgressDialog.show(MainActivity.this, "提示",
					"商店数据读取中……");
		}

		@Override
		protected void onPostExecute(List<? extends Map<String, ?>> result) {
			if (progressDialog.isShowing())
				progressDialog.dismiss();
			ListAdapter adapter = new SimpleAdapter(MainActivity.this, result,
					R.layout.shop_item, new String[] { "image", "name" },
					new int[] { R.id.image, R.id.name });
			gridView.setAdapter(adapter);
		}
		

		@Override
		protected List<? extends Map<String, ?>> doInBackground(Void... params) {
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				throw new RuntimeException(e.getMessage(), e);
			}
			return getShops();
		}

		private List<? extends Map<String, ?>> getShops() {
			List<Map<String, ?>> shops = new ArrayList<Map<String, ?>>();
			for (int i = 0; i < 10; i++) {
				HashMap<String, Object> shop = new HashMap<String, Object>();
				shop.put("name", "KFC" + i + "号店");
				shop.put("image", R.drawable.kfc);
				shops.add(shop);
			}
			return shops;
		}

	}

}
