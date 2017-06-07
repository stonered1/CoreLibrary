package com.example.test;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.stone.permissions.MPermissionsActivity;

public class MainActivity extends MPermissionsActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }
}
