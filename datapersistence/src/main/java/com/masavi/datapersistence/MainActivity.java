package com.masavi.datapersistence;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SharedPreferences sharedPreferences = getSharedPreferences(Constants.PREFERENCES_NAME, MODE_PRIVATE);
        String saludo = "Hello Dev.f";

        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(Constants.PREFERENCE_KEY_SALUDO, saludo);
        //editor.commit();
        editor.apply();

        findViewById(R.id.btn).setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                startActivity(new Intent(MainActivity.this, SecondActivity.class));
            }
        });
    }
}
