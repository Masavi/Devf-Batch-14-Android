package com.masavi.fragments.clase_fragmentos_2;


import android.content.Context;
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

/*
 * A simple {@link Fragment} subclass.
 */
public class PruebaFragment extends Fragment {

    @BindView(R.id.fragment_tv)
    TextView tvFragment;

    OnPruebaInteractionListener mListener;

    public PruebaFragment() {
        // Required empty public constructor
    }

    /* Crea una instancia de Prueba Fragment */
    public static PruebaFragment newInstance(){
        PruebaFragment pruebaFragment = new PruebaFragment();
        return pruebaFragment;
    }

    //Inicialización
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnPruebaInteractionListener) {
            mListener = (OnPruebaInteractionListener) context;
        }
    }

    @Override
    public void onDetach() {
        mListener = null;
        super.onDetach();
    }

    @Override
    //En ese método tu regresas la vista que quieres que renderee el fragmento
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //Ya en una vista, podemos tomar elementos
        View view = inflater.inflate(R.layout.fragment_prueba, container, false);
        //Aquí butterknife inyecta código para emplear la vista, es como su "mapeador"
        ButterKnife.bind(this, view);
        return view;
    }

    /* Método que se ejecuta cuando el botón del fragment es presionado */
    @OnClick(R.id.fragment_btn)
    public void onClickFragmentBtn(){
        //Forma Pro
        if (mListener != null){
            mListener.setTextTv("Texto Loco con Patrón de Diseño");
        }

        /*
        FORMA ÑERA!

        //Con el método "getActivity()", obtenemos la vista del fragmento
        MainClase2 mainClase2 = (MainClase2) getActivity();
        //Una vez obtenida la vista, casteada a class por polimorfismo, accedemos a su método set
        mainClase2.setActivityText("Texto cambiado desde el fragmento ");

        */
    }

    public void setTextFragment(String text){
        tvFragment.setText(text);
    }
}
