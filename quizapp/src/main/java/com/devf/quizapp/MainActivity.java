package com.devf.quizapp;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.devf.quizapp.models.Historial;

import butterknife.ButterKnife;
import butterknife.OnClick;
import io.realm.Realm;
import io.realm.RealmQuery;
import io.realm.RealmResults;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    /*
     * Vistas con las que el usuario interectuara
     */
    private EditText inputUserName;
    private FloatingActionButton floatingActionButton;

    //Variable donde guardamos el id del usuario que está jugando
    private long id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Le indicamos que layout va a utilizar mi actividad
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        //Referenciamos las vistas que ocuparemos de nuestro xml
        floatingActionButton = (FloatingActionButton) findViewById(R.id.main_fab_continuar);
        inputUserName = (EditText) findViewById(R.id.main_input_username);
    }


    @Override
    protected void onStart() {
        super.onStart();
        // le asignamos un escuchador que se mandará a llamar cuando le demos click al boton
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
            //Guardamos al usuario en el historial
            saveOrUpdate(userName);
            //Creamos un nuevo intent, donde indicamos la actividad hacia donde vamos
            Intent intent = new Intent(MainActivity.this, PlayActivity.class);
            //Mandamos data extra
            intent.putExtra(Constants.INTENT_KEY_USERNAME, userName);
            intent.putExtra(Constants.INTENT_KEY_ID, id);
            //Lanzamos la actividad
            startActivity(intent);
        }
    }

    @OnClick(R.id.tv_historial)
    public void historial(){
        //Seguido, se muestra el historial de puntuaciones
        startActivity(new Intent(MainActivity.this, HistorialActivity.class));
    }

    private void guardar(String nombreUsuario) {
        Realm realm = Realm.getDefaultInstance();   //Obtenemos una instancia de Realm
        realm.beginTransaction();   //Comienza la transacción

        Historial historial = realm.createObject(Historial.class, realm.where(Historial.class).count() + 1);
        historial.setNombreUsuario(nombreUsuario);
        historial.setPuntuacion(0);
        id = historial.getId();

        realm.commitTransaction();  //Termina la transacción
        Log.e("myLog", "¡Historial guardado!: " + historial.toString() + "\nPrueba ID: " + id);
    }

    private void saveOrUpdate(String nombreUsuario)
    {
        // Obtenemos una instancia de Realm
        Realm realm = Realm.getDefaultInstance();

        // Hacemos un query para hallar al posible ya existente usuario
        Historial alreadyUser = realm.where(Historial.class).equalTo("nombreUsuario", nombreUsuario).findFirst();
         /*
        // Creamos el query obteniendo todos los historiales
        RealmQuery<Historial> query = realm.where(Historial.class);

        // Añadimos condiciones al query
        query.equalTo("nombreUsuario", nombreUsuario);

        // Ejecutamos el query:
        RealmResults<Historial> alreadyUser = query.findAll();

        // O alternativamente, podemos hacerlo en conjunto de una vez(la "Fluent interface"):
        RealmResults<Historial> alreadyUser = realm.where(Historial.class)
                .equalTo("nombreUsuario", nombreUsuario)
                .findAll();
        */

        realm.beginTransaction();   //Comienza la transacción

        if (alreadyUser != null)
        {
            alreadyUser.setNombreUsuario(nombreUsuario);
            alreadyUser.setPuntuacion(0);
            id = alreadyUser.getId();
            Log.e("myLog", "¡Usuario existente actualizado!: " + alreadyUser.toString() + "\n ID: " + alreadyUser.getId());

        } else {

            Historial historial = realm.createObject(Historial.class, realm.where(Historial.class).count() +1 );
            historial.setNombreUsuario(nombreUsuario);
            historial.setPuntuacion(0);
            id = historial.getId();
            Log.e("myLog", "¡Usuario creado!: " + historial.toString() + "\n ID: " + historial.getId());
        }

        realm.commitTransaction();  //Termina la transacción
        Log.e("myLog", "Finaliza saveOrUpdate(), El ID es: "  + id);
    }
}
