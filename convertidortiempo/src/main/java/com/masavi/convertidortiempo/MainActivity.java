package com.masavi.convertidortiempo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    //Variable global donde se mostrarán los milisegundos
    EditText inputTiempo;

    Button btnConvierteHoras;
    Button btnConvierteMinutos;
    Button btnConvierteSegudos;

    View rootView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*Referenciamos los EditText que están en mi layout
        * los obtenemos mediante el id y los tenemos que
        * castear al tipo de vista que estamos creando
        * */
        this.inputTiempo = (EditText) findViewById(R.id.input_tiempo);
        this.btnConvierteHoras = (Button) findViewById(R.id.btn_horas);
        this.btnConvierteMinutos = (Button) findViewById(R.id.btn_minutos);
        this.btnConvierteSegudos = (Button) findViewById(R.id.btn_segundos);
        this.rootView = findViewById(R.id.layout_root);

        //Agregamos el "escuchador" a los botones del evento de click
        this.btnConvierteHoras.setOnClickListener(this);
        this.btnConvierteMinutos.setOnClickListener(this);
        this.btnConvierteSegudos.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.btn_horas:
                //Convertimos horas a milisegundos

                //Validar que no esté vacío el campo de tiempo
                if (!inputTiempo.getText().toString().isEmpty()) {
                    //Obtenemos el texto del input
                    String tiempo = inputTiempo.getText().toString();
                    //Convertimos el texto a float
                    int tiempoInt = Integer.parseInt(tiempo);
                    //Conversión de tiempoInt a milisegundos
                    int milisegundos = tiempoInt * 3600000;
                    //Pasamos a inputTiempo el resultado de la conversión
                    inputTiempo.setText(milisegundos + ""); //Truco ñero: convertir a string
                } else {
                    //String message = getString(R.string.message_empty_input);
                    Toast.makeText(MainActivity.this, R.string.message_empty_input, Toast.LENGTH_SHORT).show();
                }
                break;

            case R.id.btn_minutos:
                //Convertimos horas a milisegundos

                //Validar que no esté vacío el campo de tiempo
                if (!inputTiempo.getText().toString().isEmpty()) {
                    //Obtenemos el texto del input
                    String tiempo = inputTiempo.getText().toString();
                    //Convertimos el texto a float
                    int tiempoInt = Integer.parseInt(tiempo);
                    //Conversión de tiempoInt a milisegundos
                    int milisegundos = tiempoInt * 60000;
                    //Pasamos a inputTiempo el resultado de la conversión
                    inputTiempo.setText(milisegundos + ""); //Truco ñero: convertir a string
                } else {
                    //String message = getString(R.string.message_empty_input);
                    Toast.makeText(MainActivity.this, R.string.message_empty_input, Toast.LENGTH_SHORT).show();
                }
                break;

            case R.id.btn_segundos:
                //Convertimos horas a milisegundos

                //Validar que no esté vacío el campo de tiempo
                if (!inputTiempo.getText().toString().isEmpty()) {
                    //Obtenemos el texto del input
                    String tiempo = inputTiempo.getText().toString();
                    //Convertimos el texto a float
                    int tiempoInt = Integer.parseInt(tiempo);
                    //Conversión de tiempoInt a milisegundos
                    int milisegundos = tiempoInt * 1000;
                    //Pasamos a inputTiempo el resultado de la conversión
                    inputTiempo.setText(milisegundos + ""); //Truco ñero: convertir a string
                } else {
                    //String message = getString(R.string.message_empty_input);
                    Toast.makeText(MainActivity.this, R.string.message_empty_input, Toast.LENGTH_SHORT).show();
                }
                break;
        }

    }
}