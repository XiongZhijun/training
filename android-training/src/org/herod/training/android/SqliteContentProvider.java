package org.herod.training.android;

import java.util.HashMap;
import java.util.Map;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;

public class SqliteContentProvider extends ContentProvider {

	private static final int COLLECTION = 1;
	private static final int SINGLE = 2;
	private static UriMatcher URI_MATCHER = new UriMatcher(UriMatcher.NO_MATCH);
	private static Map<String, String> TABLE_NAME_MAP = new HashMap<String, String>();
	private MySQLiteOpenHelper openHelper;
	static {
		URI_MATCHER.addURI("org.herod.study.android", "shops", COLLECTION);
		URI_MATCHER.addURI("org.herod.study.android", "shops/#", SINGLE);
		TABLE_NAME_MAP.put("shops", "SHOPS");
	}

	@Override
	public boolean onCreate() {
		openHelper = new MySQLiteOpenHelper(getContext(), 1);
		return true;
	}

	@Override
	public Cursor query(Uri uri, String[] projection, String selection,
			String[] selectionArgs, String sortOrder) {
		SQLiteQueryBuilder queryBuilder = new SQLiteQueryBuilder();
		queryBuilder.setTables(getTableName(uri));
		switch (URI_MATCHER.match(uri)) {
		case COLLECTION:
			break;
		case SINGLE:
			queryBuilder.appendWhere("_id = " + uri.getPathSegments().get(1));
			break;
		default:
			throw new IllegalArgumentException("Unknow URI: " + uri);
		}
		SQLiteDatabase db = openHelper.getReadableDatabase();
		Cursor cursor = queryBuilder.query(db, projection, selection,
				selectionArgs, null, null, sortOrder);
		cursor.moveToFirst();
		return cursor;
	}

	private String getTableName(Uri uri) {
		String key = uri.getPathSegments().get(0);
		String tableName = TABLE_NAME_MAP.get(key);
		return tableName == null ? key : tableName;
	}

	@Override
	public String getType(Uri uri) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Uri insert(Uri uri, ContentValues values) {
		if (URI_MATCHER.match(uri) != COLLECTION) {
			throw new IllegalArgumentException("Unknown URI " + uri);
		}
		SQLiteDatabase db = openHelper.getWritableDatabase();
		long rowID = db.insert(getTableName(uri), null, values);
		if (rowID > 0) {
			Uri retUri = ContentUris.withAppendedId(uri, rowID);
			getContext().getContentResolver().notifyChange(uri, null);
			return retUri;
		}

		return null;
	}

	@Override
	public int delete(Uri uri, String selection, String[] selectionArgs) {
		SQLiteDatabase db = openHelper.getWritableDatabase();
		int count = -1;
		switch (URI_MATCHER.match(uri)) {
		case COLLECTION:
			count = db.delete(getTableName(uri), selection, selectionArgs);
			break;
		case SINGLE:
			String rowID = uri.getPathSegments().get(1);
			count = db.delete(getTableName(uri),  "_id =" + rowID,
					null);
			break;
		default:
			throw new IllegalArgumentException("Unknow URI :" + uri);
		}
		this.getContext().getContentResolver().notifyChange(uri, null);
		return count;
	}

	@Override
	public int update(Uri uri, ContentValues values, String selection,
			String[] selectionArgs) {
		SQLiteDatabase db = openHelper.getWritableDatabase();
		int count = -1;
		switch (URI_MATCHER.match(uri)) {
		case COLLECTION:
			count = db.update(getTableName(uri), values, null, null);
			break;
		case SINGLE:
			String rowID = uri.getPathSegments().get(1);
			count = db.update(getTableName(uri), values, "_id ="
					+ rowID, null);
			break;
		default:
			throw new IllegalArgumentException("Unknow URI : " + uri);
		}
		this.getContext().getContentResolver().notifyChange(uri, null);
		return count;

	}

}
