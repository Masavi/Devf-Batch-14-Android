package com.masavi.fragments.clase_fragmentos_2;

import android.nfc.Tag;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.masavi.fragments.R;

import org.w3c.dom.Text;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainClase2 extends AppCompatActivity implements OnPruebaInteractionListener{

    @BindView(R.id.activity_tv)
    public TextView tvActivity;
    PruebaFragment fragment;

    public static final String TAG_FRAGMENT_PRUEBA = "TAG_FRAGMENT_PRUEBA";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_clase2);
        //ButterKnife inicializa la actividad, y aplica los ajustes necesarios
        ButterKnife.bind(this);

        //Instanciamos nuestro fragmento
         fragment = PruebaFragment.newInstance();

        getSupportFragmentManager().    //Accedemos al manejador de fragmentos
                beginTransaction().     //Notificamos que queremos comenzar una transacción
                replace(R.id.container1, fragment). //Transacción de reemplazo, indicamos el id del framelayout y el fragmento
                commit();   //Realizamos lo anterior con commit
    }


    @Override
    public void setTextTv(String text) {
        tvActivity.setText(text);
    }

    @OnClick(R.id.activity_btn)
    public void clickActivityBtn(){
    // fragment.setTextFragment("Lo que quieras");

        Fragment fragment = getSupportFragmentManager().findFragmentByTag(TAG_FRAGMENT_PRUEBA);
        if (fragment != null){
            if (fragment instanceof PruebaFragment){
                PruebaFragment fragment1 = (PruebaFragment) fragment;
            }
        }
    }
}
