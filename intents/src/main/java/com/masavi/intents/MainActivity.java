package com.masavi.intents;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.net.URI;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    EditText inputUserName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inputUserName = (EditText) findViewById(R.id.input_username);
        Button btnAcceder = (Button) findViewById(R.id.btn_acceder);

        btnAcceder.setOnClickListener(this);
    }

    public void abrirLink(View view){
        //Url ala que me dirigiré
        String url = "http://google.com";
        //Creamos el intent con el tipo action view
        Intent intent = new Intent(Intent.ACTION_VIEW);
        //Le mandamos los datos de la url mediante un objeto Uri
        intent.setData(Uri.parse(url));
        //Lanzamos el intent
        startActivity(intent);
    }

    public void hacerLlamada(View view){
        /*
        * Action call para indicarlo que va a hacer una llamada telefónica
        * y le mandamos el número telefónico al que vamos a marcar
        */
        Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:2711071029"));
        //Lanzamos el intent
        startActivity(intent);
    }

    public void mostrarStreetview(View view){
        //Uri con el formato que Streetview requiere para abrir una latitud-longitud
        Uri gmmUriSv = Uri.parse("google.streetview:cbll=19.4161946,-99.1629314");
        //Creamos el intent y le pasamos la Uri
        Intent intent = new Intent(Intent.ACTION_VIEW, gmmUriSv);
        //Especificamos la aplicación que complementará el intent
        intent.setPackage("com.google.android.apps.maps");
        //Iniciamos el intent
        startActivity(intent);

    }

    public void escogerApp(View view){
        //Creamos un intnent para compartir texto plano
        Intent intent = new Intent(Intent.ACTION_SEND);
        //Agregamos el texto que le estamos mandando a la aplicación que complemente mi acción
        intent.putExtra(Intent.EXTRA_TEXT, "Este es el texto a mandar");
        //Indicamos el tipo de texto que estamos mandando
        intent.setType("text/plain");
        //Creamos un chooser personalizado
        Intent chooser = Intent.createChooser(intent, "Escoge la aplicación que" + " complemente tu acción");
        //Verificamos que existan aplicaciones para complementar mi intención
        if (intent.resolveActivity(getPackageManager()) != null){
            //Lanzamos el chooser
            startActivity(chooser);
        }
    }

    @Override
    public void onClick(View v) {
        String userName = inputUserName.getText().toString();
        if (userName.isEmpty())
        {
            Toast.makeText(MainActivity.this, "No se permite texto vacío", Toast.LENGTH_SHORT).show();
        }
        else {
            //Configuramos nuestro intent con el contexto y la actividad a donde nos dirigimos
            Intent intent = new Intent(MainActivity.this, SecondActivity.class);
            //Configuramos la data que le mandaremos
            intent.putExtra(Constants.INTENT_KEY_USERNAME, userName);
            //Lanzamos el intent
            startActivityForResult(intent, Constants.REQUEST_CODE_SECOND_ACTIVITY);
        }
    }

    public void tomarFoto(View view){
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (intent.resolveActivity(getPackageManager()) != null){
            startActivityForResult(intent, Constants.REQUEST_CODE_TAKE_PICTURE);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //Verificamos que el request code sea del second activity
        if (Constants.REQUEST_CODE_SECOND_ACTIVITY == requestCode){
            if (resultCode == RESULT_OK){
                String saludo = data.getStringExtra(Constants.INTENT_KEY_SALUDOS);
                Toast.makeText(MainActivity.this, "RequestCode: " + requestCode + " saludo: " + saludo, Toast.LENGTH_SHORT).show();
            }
            else {
                //nothing
            }


        } else if (Constants.REQUEST_CODE_TAKE_PICTURE == requestCode){
            if (resultCode == RESULT_OK){
                Bundle extras = data.getExtras();
                Bitmap imageBitmap = (Bitmap) extras.get("data");
                ImageView impPhoto = (ImageView) findViewById(R.id.imageView);
                impPhoto.setImageBitmap(imageBitmap);
            } else {
                //nothing
                Toast.makeText(MainActivity.this, "Canceló", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
