package org.herod.training.android;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
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
		shopLoadTask = new ShopLoadTask(this);
		shopLoadTask.execute();
	}

	@Override
	protected void onStop() {
		super.onStop();
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
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				throw new RuntimeException(e.getMessage(), e);
			}
			return context.getContentResolver().query(
					Uri.parse("content://org.herod.study.android.shops"), null,
					null, null, null);
		}

	}

}
