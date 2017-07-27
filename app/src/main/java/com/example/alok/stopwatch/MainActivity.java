package com.example.alok.stopwatch;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private int seconds=0;
    boolean running;

    Button testButton;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        testButton = (Button) findViewById(R.id.start);
        testButton.setTag(1);
        testButton.setText("Start");
        if(savedInstanceState!=null)
        {
            seconds=savedInstanceState.getInt("seconds");
            running=savedInstanceState.getBoolean("running");
        }
        runTime();
    }
    public void onSaveInstanceState(Bundle savedInastanceState)
    {
        savedInastanceState.putInt("seconds",seconds);
        savedInastanceState.putBoolean("running",running);
    }

    public void onClickStart (View v) {
        final int status =(Integer) v.getTag();
        if(status == 1) {
            running=true;
            testButton.setText("Stop");
            v.setTag(0);
        } else {
            running=false;
            testButton.setText("Start");
            v.setTag(1);
        }
    }
//    }
//    public void onClickStart(View view)
//    {
//        running=true;
//    }
//    public void onClickStop(View view)
//    {
//        running=false;
//    }
    public void onClickReset(View view)
    {
        running=false;
        seconds=0;
    }
    private void runTime()
    {
        final TextView timeView=(TextView)findViewById(R.id.time);
        final Handler handler=new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                int minutes=seconds/600;
                int secs=(seconds%600)/10;
                int dsecs=seconds%10;
                String time=String.format("%02d:%02d:%d",minutes,secs,dsecs);
                timeView.setText(time);
                if(running)
                {
                    seconds++;
                }
               handler.postDelayed(this,100);

            }
        });
    }
}
