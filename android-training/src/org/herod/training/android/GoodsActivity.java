package org.herod.training.android;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.widget.SimpleCursorAdapter;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.Toast;

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
		listView.setOnItemLongClickListener(new OnGoodsLongClickListener());
	}

	@Override
	protected void onStart() {
		super.onStart();
		new GoodsLoadTask(this).execute(shopId);
	}

	class OnGoodsLongClickListener implements OnItemLongClickListener {

		@Override
		public boolean onItemLongClick(AdapterView<?> parent, View view,
				int position, long id) {
			View contentView = getLayoutInflater().inflate(
					R.layout.popup_window, null);
			final PopupWindow popupWindow = new PopupWindow(contentView);
			popupWindow.setWidth(LayoutParams.WRAP_CONTENT);
			popupWindow.setHeight(LayoutParams.WRAP_CONTENT);
			contentView.findViewById(R.id.edit).setOnClickListener(
					new OnClickListener() {
						public void onClick(View v) {
							Toast.makeText(getApplicationContext(), "编辑商品",
									Toast.LENGTH_SHORT).show();
							popupWindow.dismiss();
						}
					});
			contentView.findViewById(R.id.delete).setOnClickListener(
					new OnClickListener() {
						public void onClick(View v) {
							Toast.makeText(getApplicationContext(), "编辑商品",
									Toast.LENGTH_SHORT).show();
							popupWindow.dismiss();
						}
					});
			popupWindow.setFocusable(true);
			popupWindow.setOutsideTouchable(true);
			popupWindow.setBackgroundDrawable(new BitmapDrawable());
			popupWindow.showAsDropDown(view, 100, -50);
			return true;
		}

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
