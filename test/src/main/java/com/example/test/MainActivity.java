package com.example.test;

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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
    }
}
