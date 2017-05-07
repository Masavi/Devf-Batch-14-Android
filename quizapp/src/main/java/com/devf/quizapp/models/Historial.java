package com.devf.quizapp.models;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/*
 * Created by Masavi on 5/5/2017.
 */

public class Historial extends RealmObject{
    @PrimaryKey
    private long id;
    private String nombreUsuario;
    private int puntuacion;

    //Empty constructor
    public Historial(){

    }

    public Historial(String nombreUsuario, int puntuacion) {
        this.nombreUsuario = nombreUsuario;
        this.puntuacion = puntuacion;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public int getPuntuacion() {
        return puntuacion;
    }

    public void setPuntuacion(int puntuacion) {
        this.puntuacion = puntuacion;
    }

    public void setId(long id){
        this.id = id;
    }

    public long getId(){
        return id;
    }

    @Override
    public String toString() {
        return "nombreUsuario = '" + nombreUsuario +
                ", puntuacion = " + puntuacion;
    }
}
