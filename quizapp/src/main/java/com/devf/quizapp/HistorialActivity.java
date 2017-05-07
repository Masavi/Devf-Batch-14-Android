package com.devf.quizapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.devf.quizapp.models.Historial;

import io.realm.Realm;
import io.realm.RealmQuery;
import io.realm.RealmResults;

public class HistorialActivity extends AppCompatActivity {

    TextView tvHistorial;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_historial);
        tvHistorial = (TextView) findViewById(R.id.tv_historial);
        obtenerData();
    }

    private void obtenerData() {
        Realm realm = Realm.getDefaultInstance();

        RealmQuery<Historial> query = realm.where(Historial.class);
        RealmResults<Historial> results = query.findAll();

        //El result-set se coloca en el textview
        tvHistorial.setText("");
        for (Historial historial : results) {
            tvHistorial.append(historial.toString() + "\n");
        }


    }
}
