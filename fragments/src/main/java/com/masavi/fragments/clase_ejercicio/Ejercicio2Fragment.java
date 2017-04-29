package com.masavi.fragments.clase_ejercicio;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.masavi.fragments.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class Ejercicio2Fragment extends Fragment implements EjercicioFragmentsInterface{

    @BindView(R.id.fragment2_textview)
    TextView tvFragment2;

    private OnExerciseInteractionListener mListener;

    public Ejercicio2Fragment() {
        // Required empty public constructor
    }

    public static Ejercicio2Fragment newInstance() {
        Ejercicio2Fragment fragment = new Ejercicio2Fragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_ejercicio2, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnExerciseInteractionListener) {
            mListener = (OnExerciseInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnExerciseInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        mListener = null;
        super.onDetach();
    }

    @Override
    public void aumentarContador() {
        int contador = Integer.parseInt(tvFragment2.getText().toString()) + 1;
        tvFragment2.setText(contador + "");
    }

    @OnClick(R.id.fragment2_layout)
    public void onClickFragment() {
        mListener.fragmentToActivity(Constants.FRAGMENT_EJERCICIO_2);
    }
}
