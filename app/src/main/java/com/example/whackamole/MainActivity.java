package com.example.whackamole;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import androidx.gridlayout.widget.GridLayout;

import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private GridLayout grid;
    private Drawable moleImage;
    private ImageView[] imageViews;
    private int moleLocation;
    private Random rand;
    private Handler handler;
    public updateLocation updateLocation;
    public boolean on;
    public Button button;
    private TextView pointsLabel;
    private int points;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        grid = findViewById(R.id.gridLayout);
        moleImage = getDrawable(R.drawable.mole);
        imageViews = new ImageView[16];
        rand = new Random();
        handler = new Handler();
        on=false;
        moleLocation = rand.nextInt(16);
        updateLocation = new updateLocation();
        pointsLabel = findViewById(R.id.pointsLabel);
        points =0;

        for(int i=0; i<16; i++){
            imageViews[i] = (ImageView) getLayoutInflater().inflate(R.layout.mole_view,null);
            imageViews[i].setMinimumWidth(270);
            imageViews[i].setMinimumHeight(270);
            if(i==moleLocation){
                imageViews[i].setImageDrawable(moleImage);
            }
            grid.addView(imageViews[i]);
        }

    }


    public void buttonPressed(View v){
        if(on){
            on = false;
            handler.removeCallbacks(updateLocation);


        }else {
            on = true;

            handler.postDelayed(updateLocation, 1000);
        }


    }

   public void move(View v) {

        if(v==imageViews[moleLocation] ){
            points++;
            pointsLabel.setText(points+"");

          }

    }


    private class updateLocation implements Runnable{

        public void run(){

                imageViews[moleLocation].setImageDrawable(null);
                moleLocation = rand.nextInt(16);
                imageViews[moleLocation].setImageDrawable(moleImage);
                handler.postDelayed(updateLocation, 1000);
        }


    }
}
