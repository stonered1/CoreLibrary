package com.example.test;

import android.Manifest;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.stone.permissions.MPermissionsActivity;
import com.example.stone.toastutil.ToastUtils;

public class MainActivity extends MPermissionsActivity implements View.OnClickListener {

    private Button bt, btPhone;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        bt = (Button) findViewById(R.id.button);
        btPhone = (Button) findViewById(R.id.phone_call);
        bt.setOnClickListener(this);
        btPhone.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button:
                new Thread() {
                    @Override
                    public void run() {
                        super.run();
                        ToastUtils.showMessage(MainActivity.this, "Stone");


                    }
                }.start();

                break;

            case R.id.phone_call:
                requestPermission(new String[] {Manifest.permission.CALL_PHONE}, 0x0001);
                break;
            default:
                break;
        }
    }

    @Override
    public void permissionSuccess(int requestCode) {
        super.permissionSuccess(requestCode);
        switch (requestCode) {
            case  0x0001:
                ToastUtils.showMessage(MainActivity.this, "权限成功");
                break;
        }
    }
}
