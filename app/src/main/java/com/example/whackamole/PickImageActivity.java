package com.example.whackamole;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.RadioButton;

import androidx.appcompat.app.AppCompatActivity;

public class PickImageActivity extends AppCompatActivity  {

    private RadioButton mole1;
    private RadioButton mole2;
    private RadioButton mole3;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pickimage);

        mole1 = findViewById(R.id.mole1);
        mole2 = findViewById(R.id.mole2);
        mole3 = findViewById(R.id.mole3);

    }

    @Override
    public void onBackPressed(){

        int image;
        if(mole1.isChecked()){
            image = 1;
        }else if(mole2.isChecked()){
            image = 2;
        }else{
            image = 3;
        }

        Intent i = new Intent();
        i.putExtra("IMAGE", image);
        setResult(RESULT_OK, i);
        finish();



    }
}
