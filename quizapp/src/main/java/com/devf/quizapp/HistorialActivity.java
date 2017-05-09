package com.devf.quizapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.devf.quizapp.models.Historial;

import io.realm.Realm;
import io.realm.RealmQuery;
import io.realm.RealmResults;
import io.realm.Sort;

public class HistorialActivity extends AppCompatActivity {

    TextView tvNombre, tvPuntuacion, tvID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_historial);

        // Bind each respective TextView
        tvNombre = (TextView) findViewById(R.id.tv_historial_nombre);
        tvPuntuacion = (TextView) findViewById(R.id.tv_historial_puntuacion);
        tvID = (TextView) findViewById(R.id.tv_historial_id);

        // Set the data results on each textview
        obtenerData();
    }

    private void obtenerData() {
        // Obtenemos una instancia de Realm
        Realm realm = Realm.getDefaultInstance();
        // Realizamos un query con filtro por puntuación descendente; se guarda en un RealmResults
        RealmResults<Historial> results = realm.where(Historial.class).findAll().sort("puntuacion", Sort.DESCENDING);

        tvNombre.setText("");
        tvPuntuacion.setText("");
        tvID.setText("");

        //El Result-set se coloca en los textviews
        for (Historial historial : results)
        {
            tvNombre.append(historial.getNombreUsuario() + "\n");
            tvPuntuacion.append(historial.getPuntuacion() + "\n");
            tvID.append(historial.getId() + "\n");
        }


    }
}
