package com.masavi.fragments.clase_ejercicio;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.masavi.fragments.R;
import com.masavi.fragments.clase_fragmentos_2.PruebaFragment;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivityExercise extends AppCompatActivity implements OnExerciseInteractionListener{

    EjercicioFragment fragment;
    Ejercicio2Fragment fragment2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_exercise);
        ButterKnife.bind(this);

        fragment = EjercicioFragment.newInstance();
        fragment2 = Ejercicio2Fragment.newInstance();

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.contenedor_1, fragment)
                .commit();

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.contenedor_2, fragment2)
                .commit();
    }

    @Override
    public void fragmentToActivity(int fragmentTAG) {
        if (fragmentTAG == Constants.FRAGMENT_EJERCICIO_1){
            fragment2.aumentarContador();
        } else if (fragmentTAG == Constants.FRAGMENT_EJERCICIO_2){
            fragment.aumentarContador();
        }
    }
}
