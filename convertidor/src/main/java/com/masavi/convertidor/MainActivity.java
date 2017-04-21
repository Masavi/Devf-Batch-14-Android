package com.masavi.convertidor;

import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    //Variables globales que vamos a utilizar en varios métodos
    EditText inputPesos;
    EditText inputDolares;

    Button btnConvertirPtoD;
    Button btnConvertirDtoP;

    View rootView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //La clase "R" no se genera si existe algún error en los XML
        setContentView(R.layout.activity_main);

        /*Referenciamos los EditText que están en mi layout
        * los obtenemos mediante el id y los tenemos que
        * castear al tipo de vista que estamos creando
        * */
        this.inputPesos = (EditText) findViewById(R.id.input_pesos);
        this.inputDolares = (EditText) findViewById(R.id.input_dolares);
        this.btnConvertirPtoD = (Button) findViewById(R.id.btn_pesos_to_dolares);
        this.btnConvertirDtoP = (Button) findViewById(R.id.btn_dolares_to_pesos);
        this.rootView = findViewById(R.id.layout_root);

        //Agregamos el "escuchador" a los botones del evento de click
        this.btnConvertirDtoP.setOnClickListener(this);
        this.btnConvertirPtoD.setOnClickListener(this);
    }

/*
    public void pesosToDolares(View view){
        Log.e("log", "click");
    }
*/
    //Método implementado desde la interfaz
    @Override
    public void onClick(View view)
    {
        switch (view.getId())
        {
            case R.id.btn_dolares_to_pesos:
                //Convertimos pesos a dolares

                //Validar que no esté vacío el campo de dólares
                if (!inputDolares.getText().toString().isEmpty())
                {
                    //Obtenemos el texto del input de dólares
                    String dolares = inputDolares.getText().toString();
                    //Convertimos el texto a float
                    float dolaresFloat = Float.parseFloat(dolares);
                    //Conversión de dolaresFloat a pesos
                    float pesos = dolaresFloat * 18.79f;
                    //Pasamos a InputPesos el resultado de la conversión
                    inputPesos.setText(pesos + ""); //Truco ñero: convertir a string
                }
                else
                {
                    //String message = getString(R.string.message_empty_input);
                    Toast.makeText(MainActivity.this, R.string.message_empty_input, Toast.LENGTH_SHORT).show();
                }
                break;

            case R.id.btn_pesos_to_dolares:
                //Convertimos dolares a pesos

                if(!inputPesos.getText().toString().isEmpty())
                {
                    String pesos = inputPesos.getText().toString();
                    float pesosFloat = Float.parseFloat(pesos);
                    float dolares = pesosFloat / 18.79f;
                    inputDolares.setText(dolares + "");
                }
                else
                {
                 //String message = getString(R.string.message_empty_input);
                 //Toast.makeText(MainActivity.this, R.string.message_empty_input, Toast.LENGTH_SHORT).show();
                    Snackbar.make(rootView, R.string.message_empty_input, Snackbar.LENGTH_SHORT).show();
                }
                break;
        }

    }
}
