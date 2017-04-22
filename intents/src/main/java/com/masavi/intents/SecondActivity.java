package com.masavi.intents;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        //Referencio el TextView
        TextView tvUsername = (TextView) findViewById(R.id.tv_username);
        //Obtengo el intent que llamó esta actividad y
        //Obtengo el bundle de esta misma
        Bundle extras = getIntent().getExtras();
        //Verifico que el bundle no sea null, por buena práctica
        if (extras != null)
        {
            //Obtengo el texto de username guardado en el bundle
            String userName = extras.getString(Constants.INTENT_KEY_USERNAME);
            //Le asigno el texto de username a mi textview
            tvUsername.setText(userName);
        }

        findViewById(R.id.btn_regresar).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        //Creo el intent que regresaré
        Intent returnIntent = new Intent();
        //Configuro los datos que regresaré en el intent
        returnIntent.putExtra(Constants.INTENT_KEY_SALUDOS, "Q onda q pex");
        //Ejecuto el métood para avisar que estoy retornando algo
        setResult(RESULT_OK, returnIntent);
        //Finalizo mi actividad
        finish();
    }
}
