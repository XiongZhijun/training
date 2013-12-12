package org.herod.training.android;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.util.Log;
import android.widget.Toast;

public class SimpleContentProvider extends ContentProvider {

	@Override
	public boolean onCreate() {
		Log.d(getClass().getSimpleName(), "onCreate");
		return true;
	}

	@Override
	public Cursor query(Uri uri, String[] projection, String selection,
			String[] selectionArgs, String sortOrder) {
		Toast.makeText(getContext(), "query : " + uri, Toast.LENGTH_LONG).show();
		return null;
	}

	@Override
	public String getType(Uri uri) {
		Toast.makeText(getContext(), "getType : " + uri, Toast.LENGTH_LONG).show();
		return null;
	}

	@Override
	public Uri insert(Uri uri, ContentValues values) {
		Toast.makeText(getContext(), "insert : " + uri, Toast.LENGTH_LONG).show();
		return null;
	}

	@Override
	public int delete(Uri uri, String selection, String[] selectionArgs) {
		Toast.makeText(getContext(), "delete : " + uri, Toast.LENGTH_LONG).show();
		return 0;
	}

	@Override
	public int update(Uri uri, ContentValues values, String selection,
			String[] selectionArgs) {
		Toast.makeText(getContext(), "update : " + uri, Toast.LENGTH_LONG).show();
		return 0;
	}

}
