package com.devf.quizapp;

import android.app.Application;

import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Created by Masavi on 5/5/2017.
 */

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        /*
         * Le indico a Realm sobre que contexto va a trabajar
         */
        Realm.init(this);

        /*
        * Inicio la configuración que va a tener realm
        */
        RealmConfiguration config = new RealmConfiguration.Builder().build();
        Realm.setDefaultConfiguration(config);
    }
}
