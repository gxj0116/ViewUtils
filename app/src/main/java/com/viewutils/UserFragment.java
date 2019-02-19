package com.viewutils;

import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class UserFragment extends Fragment {
    private static final String TAG = "UserFragment";

    @ViewInject(R.id.tv1)
    TextView tv1;
    @ViewInject(R.id.tv2)
    TextView tv2;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_user, container, false);
        ViewUtils.inject(this);

        initData();
        return view;
    }

    private void initData() {
        String text1 = tv1.getText().toString().trim();
        String text2 = tv2.getText().toString().trim();

        Log.d(TAG, "text1 = " + text1 + " ------- " + "text2 = " + text2);
    }
}

