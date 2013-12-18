package org.herod.training.android;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.widget.SimpleCursorAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

public class GoodsActivity extends Activity {

	private ListView listView;
	private long shopId;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.goods_activity);
		String shopName = getIntent().getStringExtra("shopName");
		shopId = getIntent().getLongExtra("shopId", -1);
		setTitle(shopName);
		listView = (ListView) findViewById(R.id.listView);
	}

	@Override
	protected void onStart() {
		super.onStart();
		new GoodsLoadTask(this).execute(shopId);
	}

	class GoodsLoadTask extends AsyncTask<Long, Void, Cursor> {

		private Context context;

		public GoodsLoadTask(Context context) {
			super();
			this.context = context;
		}

		@Override
		protected void onPostExecute(Cursor cursor) {
			ListAdapter adapter = new SimpleCursorAdapter(context,
					R.layout.goods_item, cursor, new String[] { "name",
							"image", "price", "unit" }, new int[] { R.id.name,
							R.id.image, R.id.price, R.id.unit });
			listView.setAdapter(adapter);
		}

		@Override
		protected Cursor doInBackground(Long... params) {
			long shopId = params[0];
			return context.getContentResolver().query(
					Uri.parse("content://org.herod.study.android/goodses"),
					null, "shopId = ?", new String[] { shopId + "" }, null);
		}

	}
}
