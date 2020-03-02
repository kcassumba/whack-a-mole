package com.example.whackamole;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
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
    private Drawable moleImage2;
    private Drawable moleImage3;
    private ImageView[] imageViews;
    private int moleLocation;
    private Random rand;
    private Handler handler;
    public updateLocation updateLocation;
    public boolean on;
    public Button button;
    private TextView pointsLabel;
    private int points;
    private int image;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        grid = findViewById(R.id.gridLayout);
        moleImage = getDrawable(R.drawable.mole);
        moleImage2 = getDrawable(R.drawable.mole21);
        moleImage3 = getDrawable(R.drawable.mole3);
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

    public void imagePressed(View v){
        Intent i = new Intent (this, PickImageActivity.class);
        i.putExtra("IMAGE", image);
        startActivityForResult(i, 1);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data){
        //getIntExtra - you have to specify what you are getting  int, double, string
        int image = data.getIntExtra("IMAGE", 1);

        if(image==1){
            imageViews[moleLocation].setImageDrawable(moleImage);

        }else if(image == 2){
            imageViews[moleLocation].setImageDrawable(moleImage2);
        }else{
            imageViews[moleLocation].setImageDrawable(moleImage3);
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
