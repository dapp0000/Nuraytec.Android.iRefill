package com.nuraytec.irefill;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;

import com.nuraytec.irefill.common.StringUtils;
import com.nuraytec.irefill.ui.Main;

public class AppStart extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		final View view = View.inflate(this, R.layout.start, null);
		setContentView(view);

		// ����չʾ������
		AlphaAnimation aa = new AlphaAnimation(0.3f, 1.0f);
		aa.setDuration(2000);
		view.startAnimation(aa);
		aa.setAnimationListener(new AnimationListener() {
			@Override
			public void onAnimationEnd(Animation arg0) {
				redirectToMain();
			}

			@Override
			public void onAnimationRepeat(Animation animation) {
			}

			@Override
			public void onAnimationStart(Animation animation) {
			}

		});

		// ���ݵͰ汾cookie��1.5�汾���£�����1.5.0,1.5.1��
		AppContext appContext = (AppContext) getApplication();
		String cookie = appContext.getProperty("cookie");
		if (StringUtils.isEmpty(cookie)) {
			String cookie_name = appContext.getProperty("cookie_name");
			String cookie_value = appContext.getProperty("cookie_value");
			if (!StringUtils.isEmpty(cookie_name)
					&& !StringUtils.isEmpty(cookie_value)) {
				cookie = cookie_name + "=" + cookie_value;
				appContext.setProperty("cookie", cookie);
				appContext.removeProperty("cookie_domain", "cookie_name",
						"cookie_value", "cookie_version", "cookie_path");

			}
		}

	}

	/**
	 * ��ת��...
	 */
	private void redirectToMain() {
		Intent intent = new Intent(this, Main.class);
		startActivity(intent);
		finish();
	}

}