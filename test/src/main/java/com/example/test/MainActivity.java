package com.example.test;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.stone.permissions.MPermissionsActivity;
import com.example.stone.toastutil.ToastUtils;

public class MainActivity extends MPermissionsActivity implements View.OnClickListener {

    private Button bt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        bt = (Button) findViewById(R.id.button);
        bt.setOnClickListener(this);
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
            default:
                break;
        }
    }
}
