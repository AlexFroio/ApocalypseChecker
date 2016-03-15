package com.example.ahernan.apocalypsechecker;

import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    Random rn = new Random();
    int fileNo = 0;
    int rndm = 1;
    int lstrndm = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView tv=(TextView)findViewById(R.id.main_txt);
        Typeface face = Typeface.createFromAsset(getAssets(), "font/Roboto-Bold.ttf");
        tv.setTypeface(face);

        String resourcePath = "android.resource://"+getPackageName()+"/raw/";
        listRaw();
        Button button =(Button) findViewById(R.id.angry_btn);

        button.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Animation scale = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.anim_scale);
                v.startAnimation(scale);

                //MediaPlayer mp = MediaPlayer.create(getApplicationContext(), R.raw.no1);
                //mp.start();
                rndm = rn.nextInt(fileNo-0)+1;
                while(lstrndm == rndm){
                    rndm = rn.nextInt(fileNo-0)+1;
                }
                String fileUsed = "no"+rndm;
                audioPlayer(fileUsed);
                lstrndm = rndm;
                //audioStop();
            }
        });

        }

    public void listRaw(){
        Field[] fields = R.raw.class.getFields();
        System.out.print(fields);
        for(int i = 0; i < fields.length; i++){
            //if (fields.toString().endsWith(".mp3")){
            fileNo++;//}
        }
    }

    public void audioPlayer(String fileName){
        String path = "android.resource://"+getPackageName()+"/raw/"+fileName;
        MediaPlayer mp = new MediaPlayer();
        try{
            mp.setDataSource(getApplicationContext(), Uri.parse(path));
            mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    mp.release();
                }
            });
            mp.prepare();
            mp.start();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
