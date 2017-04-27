package com.masavi.fragments.clase_ejercicio;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.masavi.fragments.R;
import com.masavi.fragments.clase_fragmentos_2.PruebaFragment;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivityExercise extends AppCompatActivity {

    EjercicioFragment fragment;
    Ejercicio2Fragment fragment2;

    public static final String TAG_FRAGMENT_PRUEBA = "TAG_FRAGMENT_EJERCICIO";
    public static final String TAG_FRAGMENT_PRUEBA_2 = "TAG_FRAGMENT_EJERCICIO_2";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_exercise);
        ButterKnife.bind(this);

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.contenedor_1, fragment, TAG_FRAGMENT_PRUEBA)
                .commit();

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.contenedor_2, fragment2, TAG_FRAGMENT_PRUEBA_2)
                .commit();
    }

    //Esto es trampa, hay que obtener el onclick desde el fragmento
    @OnClick(R.id.fragment1_layout)
    public void clickFragmentLayout1(){
        Fragment fragment = getSupportFragmentManager().findFragmentByTag(TAG_FRAGMENT_PRUEBA);
        if (fragment != null){
            if (fragment instanceof PruebaFragment){
                PruebaFragment fragment1 = (PruebaFragment) fragment;
                fragment1.setTextFragment("Lo que quieras");
            }
        }
    }
}
