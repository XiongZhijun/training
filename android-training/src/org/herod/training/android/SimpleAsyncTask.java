package org.herod.training.android;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

public class SimpleAsyncTask extends AsyncTask<Long, Void, String> {

	private Context context;

	public SimpleAsyncTask(Context context) {
		super();
		this.context = context;
	}

	@Override
	protected void onPostExecute(String result) {
		Toast.makeText(context, result, Toast.LENGTH_LONG).show();
	}

	@Override
	protected String doInBackground(Long... params) {
		Log.d(getClass().getSimpleName(), "execute on : "
				+ Thread.currentThread().getName());
		try {
			Thread.sleep(params[0]);
		} catch (InterruptedException e) {
			Log.e(getClass().getSimpleName(), e.getMessage(), e);
		}
		return "我执行过了";
	}

}
