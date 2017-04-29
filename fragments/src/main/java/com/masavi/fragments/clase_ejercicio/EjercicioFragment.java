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
import com.masavi.fragments.clase_fragmentos_2.OnPruebaInteractionListener;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class EjercicioFragment extends Fragment implements EjercicioFragmentsInterface{

    @BindView(R.id.fragment1_tv)
    TextView tvFragment1;

    private OnExerciseInteractionListener mListener;

    public EjercicioFragment() {
        // Required empty public constructor
    }

    public static EjercicioFragment newInstance() {
        EjercicioFragment fragment = new EjercicioFragment();
        return fragment;
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_ejercicio, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void aumentarContador() {
        int contador = Integer.parseInt(tvFragment1.getText().toString()) + 1;
        tvFragment1.setText(contador + "");
    }

    @OnClick(R.id.fragment1_layout)
    public void onClickFragment() {
        if (mListener != null)
        mListener.fragmentToActivity(Constants.FRAGMENT_EJERCICIO_1);
    }
}
