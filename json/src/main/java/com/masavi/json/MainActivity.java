package com.masavi.json;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*
        //Inicializamos un HashMap, donde usaremos referencias de clave-valor.
        // Esta estructura se comunica con
        HashMap<String, String> map = new HashMap<>();

        //Introducimos datos en nuestro hashmap
        map.put(Constants.NOMBRE,"Donaldo");
        map.put(Constants.APELLIDO, "Trompa");
        map.put(Constants.EDAD,"63");

        //Inicializaremos el objeto JSON
        JSONObject json = new JSONObject(map);

        try {
            String mName = json.getString(Constants.NOMBRE);
            Log.e("myLog",json.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }*/

        //parseJSON1();
        //parseJSON2();
        //parseJSON3();
        parseJSON4();


    }

    public void parseJSON1(){
        String jsonString = getString(R.string.json1);

        try {
            JSONObject json = new JSONObject(jsonString);
            //Imprime el contenido completo del JSONObject
            Log.e("myLog",json.toString(2));
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    public void parseJSON2(){
        String jsonString = getString(R.string.json2);

        try {
            //Pasamos el JSON a un JSONObject
            JSONObject json = new JSONObject(jsonString);
            //Log.e("json2", json.toString(1));

            //Obtenemos el primer valor del JSON, que es un arreglo
            JSONArray array = json.getJSONArray("colorsArray");

            //Obtenemos el primer valor del arreglo, que a su vez es un JSON
            JSONObject color = array.getJSONObject(0);

            //Imprimimos el valor red del JSON
            Log.e("color",color.getString("red"));

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    public void parseJSON3(){
        String jsonString = getString(R.string.json3);
        int i;

        try {
            JSONObject jsonObject = new JSONObject(jsonString);
            //Log.e("json3",jsonObject.toString(2));
            JSONArray jsonArray = jsonObject.getJSONArray("colorsArray");

            for (i = 0; i < jsonArray.length(); i++){
                JSONObject color = jsonArray.getJSONObject(i);
                Log.e("ArregloColor",color.toString());
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    //Ejercicio
    public void parseJSON4(){
        String jsonString = getString(R.string.json4);

        try {
            // Creación de objeto JSON principal, donde obtenemos el JSON4 completo
            JSONObject jsonMainObject = new JSONObject(jsonString);

            //Impresión de los PRIMEROS TRES elementos del JSON
            Log.e("jsonEjercicio", "Nombre: " + jsonMainObject.getString("firstName").toString());
            Log.e("jsonEjercicio", "Apellido: " + jsonMainObject.getString("lastName").toString());
            Log.e("jsonEjercicio", "Edad: " + jsonMainObject.getString("age").toString());


            //el CUARTO elemento del objeto JSON principal es otro JSON
            //así que lo asignamos a un nuevo objeto JSON
            JSONObject jsonObject = jsonMainObject.getJSONObject("address");

            //Ya dentro del nuevo objeto JSON, imprimimos cada uno de sus elementos
            Log.e("jsonEjercicio", "--- DIRECCIÓN --- ");
            Log.e("jsonEjercicio", "Calle: " + jsonObject.getString("streetAddress").toString());
            Log.e("jsonEjercicio", "Ciudad: " + jsonObject.getString("city").toString());
            Log.e("jsonEjercicio", "Estado: " + jsonObject.getString("state").toString());
            Log.e("jsonEjercicio", "Código Postal: " + jsonObject.getString("postalCode").toString());

            //el QUINTO elemento del JSON principal es un arreglo, así que lo asignamos a unJSON Array
            JSONArray jsonArray = jsonMainObject.getJSONArray("phoneNumber");
            Log.e("jsonEjercicio", "--- TELÉFONOS --- ");

            //Iteramos dentro del JSONArray e imprimimos cada uno de sus elementos
            for (int i = 0; i < jsonArray.length(); i++){
                JSONObject phoneNumber = jsonArray.getJSONObject(i);
                //Dentro del arreglo hay dos elementos, así que imprimimos cada uno durante la iteración
                Log.e("jsonEjercicio", "Tipo: " + phoneNumber.getString("type").toString());
                Log.e("jsonEjercicio", "Número: " + phoneNumber.getString("number").toString());
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}

