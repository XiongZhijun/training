package org.herod.training.android;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.FrameLayout;
import android.widget.Toast;

public class FrameLayoutActivity extends Activity implements OnClickListener {

	private FrameLayout frameLayout;
	private View view1;
	private View view2;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.frame_layout);
		frameLayout = (FrameLayout) findViewById(R.id.frameLayout);
		view1 = findViewById(R.id.view1);
		view1.setOnClickListener(this);
		view2 = findViewById(R.id.view2);
		view2.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.view1:
			Toast.makeText(this, "View 1 click", Toast.LENGTH_SHORT).show();
			frameLayout.bringChildToFront(view2);
			break;
		case R.id.view2:
			Toast.makeText(this, "View 2 click", Toast.LENGTH_SHORT).show();
			frameLayout.bringChildToFront(view1);
			break;
		default:
			break;
		}
		frameLayout.postInvalidate();
	}
}
