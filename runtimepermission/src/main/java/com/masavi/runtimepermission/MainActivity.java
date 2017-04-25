package com.masavi.runtimepermission;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    //Enlazamos el id a la vista, para agilizar el proceso
    @BindView(R.id.example_tv)
    TextView tvExample;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Butterknife nos permite enlazar las vistas
        ButterKnife.bind(this);
    }

    private void requestCameraPermission(){
        String[] permissions = new String[]{Manifest.permission.CAMERA};

        if (!ActivityCompat.shouldShowRequestPermissionRationale(MainActivity.this, Manifest.permission.CAMERA)){
            ActivityCompat.requestPermissions(MainActivity.this, permissions, Constants.REQUEST_PERMISSION_CAMERA);
        }
    }

    @Override
    //Primer parámetro es el request code, el segundo parámetro son los permisos, y el tercer parámetro son los resultados
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        if (requestCode == Constants.REQUEST_PERMISSION_CAMERA){
            if (grantResults.length != 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                //Yeah!!
                btnClick();
                return;
            }

            if (!ActivityCompat.shouldShowRequestPermissionRationale(MainActivity.this, Manifest.permission.CAMERA)){
                //TODO Decirle al usuario que tiene que ir a las configuraciones y habilirar el perimso manualmente
            } else {
                ActivityCompat.requestPermissions(MainActivity.this, permissions, Constants.REQUEST_PERMISSION_CAMERA);
            }
/*
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Permiso denegado")
                    .setMessage("Esta aplicación no puede funcionar sin el permiso de la cámara")
                    .setCancelable(false)
                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            finish();
                        }
                    })
                    .show();*/
        }
    }

    @OnClick(R.id.button)
    public void btnClick(){
        //Primero verifico si el permiso de la camara esta permitido
        //Si todavia no lo tiene, lo pedimos
        int permissionStatus =
                ActivityCompat.checkSelfPermission(MainActivity.this, Manifest.permission.CAMERA);
        if (permissionStatus == PackageManager.PERMISSION_GRANTED) {
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            if (intent.resolveActivity(getPackageManager()) != null){
                startActivityForResult(intent, Constants.REQUEST_CODE_TAKE_PICTURE);
            }
        } else {
            requestCameraPermission();
        }

    }

}
