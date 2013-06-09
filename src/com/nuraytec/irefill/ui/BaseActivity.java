package com.nuraytec.irefill.ui;

import com.nuraytec.irefill.AppManager;

import android.app.Activity;
import android.os.Bundle;

public class BaseActivity extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		//���Activity����ջ
		AppManager.getAppManager().addActivity(this);
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		
		//����Activity&�Ӷ�ջ���Ƴ�
		AppManager.getAppManager().finishActivity(this);
	}
}
