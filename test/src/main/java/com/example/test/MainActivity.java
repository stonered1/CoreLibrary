package com.example.test;

import android.Manifest;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.stone.multichoicepicker.MultiChoicePicker;
import com.example.stone.permissions.MPermissionsActivity;
import com.example.stone.toastutil.ToastUtils;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends MPermissionsActivity implements View.OnClickListener {

    private Button bt, btPhone, picker;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initData();
    }

    private void initView() {
        bt = (Button) findViewById(R.id.button);
        btPhone = (Button) findViewById(R.id.phone_call);
        picker = (Button) findViewById(R.id.picker);
        bt.setOnClickListener(this);
        picker.setOnClickListener(this);
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

            case R.id.picker:
                multiChoicePicker();
                multiChoicePicker.show();
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


    private MultiChoicePicker multiChoicePicker;

    private List<Boolean> cits;
    private void initData() {
        cits = new ArrayList<>();
        cits.add(false);
        cits.add(false);
        cits.add(false);
        cits.add(false);
        cits.add(false);
        cits.add(false);
        cits.add(false);
        cits.add(false);
        cits.add(false);
        cits.add(false);
        cits.add(false);
        cits.add(false);
        cits.add(false);
        cits.add(false);
        cits.add(false);
        cits.add(false);
        cits.add(false);
    }

    private void multiChoicePicker() {

        List<String> items = new ArrayList<>();
        items.add("2");
        items.add("2");
        items.add("2");
        items.add("2");
        items.add("2");
        items.add("2");
        items.add("3");
        items.add("3");
        items.add("3");
        items.add("3");
        items.add("3");
        items.add("3");
        items.add("3");
        items.add("3");
        items.add("3");
        items.add("3");
        items.add("3");

        multiChoicePicker = new MultiChoicePicker.Builder(this)
                .setTitleText("社团加入")
                .setContentTextSize(20)//设置滚轮文字大小
                .setMultiChoiceItems(items, cits, new MultiChoicePicker.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(List<Boolean> items) {
                        cits = items;

                    }
                }).build();
    }
}
