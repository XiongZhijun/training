package org.herod.training.android;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.GridView;
import android.widget.ListAdapter;
import android.widget.SimpleCursorAdapter;

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
		showShops();
	}

	private void showShops() {
		shopLoadTask = new ShopLoadTask(this);
		shopLoadTask.execute();
	}

	@Override
	protected void onStop() {
		super.onStop();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.addShop:
			startActivity(new Intent(this, AddShopActivity.class));
			return true;
		case R.id.removeAllShop:
			getContentResolver().delete(
					Uri.parse("content://org.herod.study.android/shops"), null,
					null);
			showShops();
		default:
			break;
		}
		return super.onOptionsItemSelected(item);
	}

	class ShopLoadTask extends AsyncTask<Void, Void, Cursor> {

		private ProgressDialog progressDialog;
		private Context context;

		public ShopLoadTask(Context context) {
			super();
			this.context = context;
		}

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			progressDialog = ProgressDialog.show(context, "提示", "商店数据读取中……");
		}

		@Override
		protected void onPostExecute(Cursor cursor) {
			if (progressDialog.isShowing())
				progressDialog.dismiss();
			ListAdapter adapter = new SimpleCursorAdapter(context,
					R.layout.shop_item, cursor,
					new String[] { "image", "name" }, new int[] { R.id.image,
							R.id.name });
			gridView.setAdapter(adapter);
		}

		@Override
		protected Cursor doInBackground(Void... params) {
			return context.getContentResolver().query(
					Uri.parse("content://org.herod.study.android/shops"), null,
					null, null, null);
		}

	}

}
