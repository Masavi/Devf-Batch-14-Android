package com.masavi.datapersistence;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);

        /*
        //Forma Manual: Sin hacer uso de RetroLambda

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences sharedPreferences = getSharedPreferences(
                        Constants.PREFERENCES_NAME, SecondActivity.this.MODE_PRIVATE
                );

                String saludo = sharedPreferences.getString(Constants.PREFERENCE_KEY_SALUDO, "...");

                Snackbar.make(view, "Text: " + saludo , Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        */

        //Usando RetroLambda
        SharedPreferences sharedPreferences = getSharedPreferences(
                Constants.PREFERENCES_NAME, SecondActivity.this.MODE_PRIVATE
        );

        String saludo = sharedPreferences.getString(Constants.PREFERENCE_KEY_SALUDO, "...");

        fab.setOnClickListener(view -> Snackbar.make(view, "Text: " + saludo , Snackbar.LENGTH_LONG)
                .setAction("Action", null).show());


    }

}
