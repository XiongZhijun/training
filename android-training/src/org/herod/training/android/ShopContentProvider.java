package org.herod.training.android;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.database.AbstractCursor;
import android.database.Cursor;
import android.net.Uri;

public class ShopContentProvider extends ContentProvider {

	@Override
	public boolean onCreate() {
		return true;
	}

	@Override
	public Cursor query(Uri uri, String[] projection, String selection,
			String[] selectionArgs, String sortOrder) {
		return new ShopCursor(getShops());
	}

	@Override
	public String getType(Uri uri) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Uri insert(Uri uri, ContentValues values) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int delete(Uri uri, String selection, String[] selectionArgs) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int update(Uri uri, ContentValues values, String selection,
			String[] selectionArgs) {
		// TODO Auto-generated method stub
		return 0;
	}

	private List<? extends Map<String, ?>> getShops() {
		List<Map<String, ?>> shops = new ArrayList<Map<String, ?>>();
		for (int i = 0; i < 10; i++) {
			HashMap<String, Object> shop = new HashMap<String, Object>();
			shop.put("_id", i);
			shop.put("name", "KFC" + i + "号店");
			shop.put("image", R.drawable.kfc);
			shops.add(shop);
		}
		return shops;
	}

	static class ShopCursor extends AbstractCursor {
		private static final String[] COLUMNS = new String[] { "_id", "name",
				"image" };
		List<? extends Map<String, ?>> shops;

		public ShopCursor(List<? extends Map<String, ?>> shops) {
			super();
			this.shops = shops;
		}

		@Override
		public int getCount() {
			return shops.size();
		}

		@Override
		public String[] getColumnNames() {
			return COLUMNS;
		}

		@Override
		public String getString(int column) {
			return shops.get(getPosition()).get(COLUMNS[column]).toString();
		}

		@Override
		public short getShort(int column) {
			return Short.parseShort(getString(column));
		}

		@Override
		public int getInt(int column) {
			return Integer.parseInt(getString(column));
		}

		@Override
		public long getLong(int column) {
			return Long.parseLong(getString(column));
		}

		@Override
		public float getFloat(int column) {
			return Float.parseFloat(getString(column));
		}

		@Override
		public double getDouble(int column) {
			return Double.parseDouble(getString(column));
		}

		@Override
		public boolean isNull(int column) {
			return shops.get(getPosition()).get(COLUMNS[column]) == null;
		}
	}

}
