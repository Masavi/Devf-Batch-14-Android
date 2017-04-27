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
import butterknife.OnClick;

public class EjercicioFragment extends Fragment implements OnExerciseInteractionListener {

    @BindView(R.id.fragment1_tv)
    TextView tvFragment1;

    private OnFragmentInteractionListener mListener;

    public EjercicioFragment() {
        // Required empty public constructor
    }

    public static EjercicioFragment newInstance() {
        EjercicioFragment fragment = new EjercicioFragment();
        return fragment;
    }

/*    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_ejercicio, container, false);
    }*/

    /*
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }*/

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void aumentarContador() {

    }

    @OnClick(R.id.root_layout)
    public void onClickFragment() {
        //Cosas ocurren aquí
    }
}
