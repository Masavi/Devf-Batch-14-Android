package com.masavi.datapersistence;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.masavi.datapersistence.models.User;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmQuery;
import io.realm.RealmResults;

public class RealmActivity extends AppCompatActivity {

    private long id;
    TextView tvData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_realm);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //Realm configuration
        Realm.init(RealmActivity.this);
        RealmConfiguration config = new RealmConfiguration.Builder().build();

        Realm.setDefaultConfiguration(config);

        tvData = (TextView) findViewById(R.id.tv_data);
        findViewById(R.id.btn_get_data).setOnClickListener(view -> getData());


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveData();
            }
        });
    }

    private void saveData(){
        //Persitir
        //Obtain a Realm instance
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction(); //Comienza la transacción

        User user = realm.createObject(User.class, realm.where(User.class));
        user.setAge(35);
        user.setEmail("email@algo.com");
        user.setName("John " + id);
        user.setUserName("Yisus");
        user.setPhone(55443322);
        user.setId(id++);

        realm.commitTransaction(); //Final de la transacción
        Log.e("myLog", "Persistió");
    }

    private void getData() {
        Realm realm = Realm.getDefaultInstance();
        RealmQuery<User> query = realm.where(User.class);
        RealmResults<User> results = query.findAll();

        for (User usuario : results) {
            tvData.setText(usuario.toString() + "\n");
        }
    }

}
