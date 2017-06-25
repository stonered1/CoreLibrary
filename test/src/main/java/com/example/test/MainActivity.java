package com.example.test;

<<<<<<< HEAD
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.widget.Button;
import android.widget.ImageView;

import com.example.test.sever.PlayingMusicServices;

public class MainActivity extends AppCompatActivity {



    /**
     * 规定开始音乐、暂停音乐、结束音乐的标志
     */
    public  static final int PLAY_MUSIC=1;
    public  static final int PAUSE_MUSIC=2;
    public  static final int STOP_MUSIC=3;
    MyBroadCastReceiver receiver;
    private LoveLayout mLoveLayout;
    private ImageView mImageView;
    private boolean start;
    private AlphaAnimation mShowAnimation= null;
    private Button mButton;
    private boolean isStop=true;

    private Handler handler=new Handler(){
        public void handleMessage(android.os.Message msg) {
            if (msg.what==0x123) {
                mLoveLayout.addLove();
                if (start) {
                    start = false;
                    mImageView.setVisibility(View.VISIBLE);
                    setShowAnimation(mImageView, 18000);
                    mButton.setVisibility(View.VISIBLE);


                }
            }
        }
    };
=======
import android.Manifest;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.bigkoo.pickerview.view.multichoicepicker.MultiChoicePicker;
import com.example.stone.permissions.MPermissionsActivity;
import com.example.stone.toastutil.ToastUtils;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends MPermissionsActivity implements View.OnClickListener {

    private Button bt, btPhone, picker;
>>>>>>> 03888e653cf73a0f227f051bec3ed406fef429f8
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

<<<<<<< HEAD
        receiver=new MyBroadCastReceiver();
        IntentFilter filter=new IntentFilter();
        filter.addAction("com.complete");
        registerReceiver(receiver,filter);
        mImageView = (ImageView) findViewById(R.id.image);
        final Button button = (Button) findViewById(R.id.button);
        mButton = (Button) findViewById(R.id.button_stop);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                button.setVisibility(View.INVISIBLE);
                start = true;
                initView();
                playingmusic(PLAY_MUSIC);
                isStop = false;


            }
        });

        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isStop) {
                    isStop = true;
                    playingmusic(PAUSE_MUSIC);
                    mButton.setBackgroundResource(R.mipmap.go);

                } else {
                    isStop = false;
                    playingmusic(PLAY_MUSIC);
                    mButton.setBackgroundResource(R.mipmap.stop);
                }
            }
        });


    }

    private void initView() {
        mLoveLayout=(LoveLayout)findViewById(R.id.id_love_layout);
        new Thread(new Runnable() {
            @Override
            public void run() {
                // TODO Auto-generated method stub
                try {
                    while(true){
                        Thread.sleep(400);
                        handler.sendEmptyMessage(0x123);
                    }
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }).start();
    }


    private void setShowAnimation( View view, int duration ){

        if( null == view || duration < 0 ){

            return;
        }
        if( null != mShowAnimation ){

            mShowAnimation.cancel( );
        }
        mShowAnimation = new AlphaAnimation(0.0f, 1.0f);
        mShowAnimation.setDuration( duration );
        mShowAnimation.setFillAfter( true );
        view.startAnimation( mShowAnimation );

    }


    private void playingmusic(int type) {
        //启动服务，播放音乐
        Intent intent=new Intent(this,PlayingMusicServices.class);
        intent.putExtra("type",type);
        startService(intent);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(receiver);
        playingmusic(STOP_MUSIC);
=======
                    }
                }).build();
>>>>>>> 03888e653cf73a0f227f051bec3ed406fef429f8
    }
}
