package com.devf.quizapp;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.devf.quizapp.models.Historial;

import io.realm.Realm;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    /**
     * Vistas con las que el usuario interectuara
     */
    private EditText inputUserName;
    private FloatingActionButton floatingActionButton;
    private long id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Le indicamos que layout va a utilizar mi actividad
        setContentView(R.layout.activity_main);

        //Referenciamos las vistas que ocuparemos de nuestro xml
        floatingActionButton = (FloatingActionButton) findViewById(R.id.main_fab_continuar);
        inputUserName = (EditText) findViewById(R.id.main_input_username);
    }


    @Override
    protected void onStart() {
        super.onStart();
        // le asignamos un escuchador que se mandara a llamara cuando le demos click al boton
        floatingActionButton.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        //Tomamos lo que esta dentro de nuestro Edittext
        String userName = inputUserName.getText().toString();
        //Validamos que el usuario ingreso texto dentro del inputusername
        if (userName.isEmpty()) {
            //El user name es vacio
            Toast.makeText(this, R.string.start_error_message, Toast.LENGTH_SHORT).show();
        } else {
            guardar(userName);
            Intent intent = new Intent(MainActivity.this, PlayActivity.class);
            intent.putExtra(Constants.INTENT_KEY_USERNAME, userName);
            startActivity(intent);
        }

    }

    private void guardar(String nombreUsuario) {

        Realm realm = Realm.getDefaultInstance();   //Obtenemos una instancia de Realm
        realm.beginTransaction();   //Comienza la transacción

        Historial historial = realm.createObject(Historial.class, id = realm.where(Historial.class).count() +1 );
        historial.setNombreUsuario(nombreUsuario);
        historial.setPuntuacion(0);
        historial.setId(id);

        realm.commitTransaction();  //Termina la transacción
        Log.e("myLog", "¡Historial guardado!: " + historial.toString());
    }
}
