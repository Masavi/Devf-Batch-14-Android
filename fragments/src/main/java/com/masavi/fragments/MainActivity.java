package com.masavi.fragments;

import android.content.res.Configuration;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Forma full chida, haciendo uso de la "fábrica de instancias" de la clase BlankFragment
        changeFragment(R.id.container1, BlankFragment.newInstance(R.color.color1));

        //Forma not so chida
/*        BlankFragment blankfragment1 = new BlankFragment(R.color.color1);
        changeFragment(R.id.container1, blankfragment1);*/

        //Obtenemos la orientación del dispositivo
        int orientation = getResources().getConfiguration().orientation;
        //Si la orientación es vertical (PORTRAIT
        if (orientation == Configuration.ORIENTATION_PORTRAIT) {
            findViewById(R.id.container2).setVisibility(View.GONE);
            findViewById(R.id.container3).setVisibility(View.GONE);
        } else {
            BlankFragment blankfragment2 = new BlankFragment(R.color.color2);
            BlankFragment blankfragment3 = new BlankFragment(R.color.color3);

            changeFragment(R.id.container2, blankfragment2);
            changeFragment(R.id.container3, blankfragment3);
        }
    }

    public void changeFragment(int resContainer, Fragment fragment) {
        getSupportFragmentManager().beginTransaction()
                .replace(resContainer, fragment)
                .commit();
    }
}
