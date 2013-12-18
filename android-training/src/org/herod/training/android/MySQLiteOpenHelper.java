package org.herod.training.android;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MySQLiteOpenHelper extends SQLiteOpenHelper {

	public MySQLiteOpenHelper(Context context, int version) {
		super(context, "ANDROID_TRAINING", null, version);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL("CREATE TABLE SHOPS (_id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT, image TEXT)");
		for (int i = 0; i < 10; i++) {
			ContentValues values = new ContentValues();
			values.put("name", "KFC" + i + "号店");
			values.put("image", R.drawable.kfc);
			db.insert("SHOPS", null, values);
		}
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

	}

}
