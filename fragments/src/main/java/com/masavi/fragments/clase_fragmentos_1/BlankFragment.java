package com.masavi.fragments.clase_fragmentos_1;


import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.masavi.fragments.R;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * A simple {@link Fragment} subclass.
 */
public class BlankFragment extends Fragment {

    @BindView(R.id.root_layout)
    FrameLayout relativeLayout;

    private int mResColor;

    public BlankFragment() {
        // Required empty public constructor
    }

    //Otra buena práctica para crear fragmentos, "fábrica" de instancias dentro de la misma clase
    public static BlankFragment newInstance(int resColor){  //Hacemos el método estático para poder llamarlo
        BlankFragment blankFragment = new BlankFragment();
        Bundle args = new Bundle();
        args.putInt("color",resColor);
        blankFragment.setArguments(args);
        return blankFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() !=null) {
            Bundle bundle = getArguments();
            mResColor = bundle.getInt("color");

        }
    }

    @SuppressLint("ValidFragment")
    public BlankFragment(int resColor) {
        mResColor = resColor;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_blank, container, false);
        ButterKnife.bind(this, view);
        relativeLayout.setBackgroundResource(mResColor);
        return view;
    }

}
