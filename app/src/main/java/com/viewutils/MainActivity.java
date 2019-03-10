package com.viewutils;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends Activity {
    private static final String TAG = "MainActivity";

    //commit1

    @ViewInject(R.id.tv1)
    TextView tv1;
    @ViewInject(R.id.tv2)
    TextView tv2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ViewUtils.inject(this);

        String text1 = tv1.getText().toString().trim();
        String text2 = tv2.getText().toString().trim();

        Log.d(TAG, "text1 = " + text1 + " ------- " + "text2 = " + text2);

        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        UserFragment userFragment = new UserFragment();
        transaction.replace(R.id.container, userFragment, "UserFragment");

    }

    @OnClick(R.id.btn)
    private void clickEvent(View view) {
        Toast.makeText(this, "我被点击了！", Toast.LENGTH_SHORT).show();
    }
}
