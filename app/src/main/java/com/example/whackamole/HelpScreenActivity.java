package com.example.whackamole;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;


public class HelpScreenActivity extends AppCompatActivity {

    private TextView help_text;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_helpscreen);
        help_text = findViewById(R.id.help_text);


    }


    @Override
    public void onBackPressed() {
        //help_text.getText();

        Intent i = new Intent();
        i.putExtra("HELP", help_text.getText());
        setResult(RESULT_OK, i);
        finish();

    }
}
