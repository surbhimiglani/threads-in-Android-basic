package com.example.surbhimiglani.threads;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.os.Handler;
import android.os.Message;

public class MainActivity extends AppCompatActivity {

    Handler h=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            TextView textView=(TextView) findViewById(R.id.textView);
            textView.setText("5 seconds over");
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void clickme(View view) {
        Runnable r= new Runnable() {
            @Override
            public void run() {
                long futureTime = System.currentTimeMillis() + 3000;
                while (System.currentTimeMillis() < futureTime) {
                    synchronized (this) {
                        try {
                            wait(futureTime - System.currentTimeMillis());
                        }catch (Exception e){}
                    }
                }
              h.sendEmptyMessage(0);
            }

        };

      Thread t=new Thread(r);
        t.start();

    }


}