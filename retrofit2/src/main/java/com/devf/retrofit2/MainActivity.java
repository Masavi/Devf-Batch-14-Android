package com.devf.retrofit2;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.devf.retrofit2.api.ApiClient;
import com.devf.retrofit2.api.ServiceGenerator;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.tv_nombre)
    TextView tvNombre;

    @BindView(R.id.tv_cumpleaños)
    TextView tvCumple;

    @BindView(R.id.tv_bachillerato)
    TextView tvBachillerato;

    @BindView(R.id.tv_universidad)
    TextView tvUniversidad;

    @BindView(R.id.tv_correo)
    TextView tvCorreo;

    private ImageView imageViewProfilePic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        imageViewProfilePic = (ImageView) findViewById(R.id.profile_image);

        // Instanciamos el servicio de API
        ApiClient apiClient = ServiceGenerator.createService();
        // Enviamos una petición al servidor
        apiClient.getPublicProfile().enqueue(new Callback<ResponseBody>() {

            // Si se establece una comunicación con el servidor
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                // Notificamos que recibimos una respuesta
                Log.e("myLog", "¡Respuesta recibida!");
                //Obtengo el código de la respuesta
                int statusCode = response.code();
                Log.e("myLog", "\nCódigo de Respuesta: " + statusCode);
                //Verificamos que el código de respuesta sea 200
                if (statusCode == 200){
                    try {
                        //Guardamos el cuerpo de la respuesta en una String
                        String body = response.body().string();
                        // La pasamos a un json object
                        JSONObject jsonObject = new JSONObject(body);
                        // Imprimimos el objeto completo
                        Log.e("myLog", jsonObject.toString(4));
                        //Log.e("myLog", jsonObject.getString("company"));
                        tvNombre.setText(jsonObject.getString("name"));
                        tvCumple.setText(jsonObject.getString("birthday"));
                        tvCorreo.setText(jsonObject.getString("email"));

                        JSONArray jsonArrayEducation = jsonObject.getJSONArray("education");

                        JSONObject jsonObjectBachillerato = jsonArrayEducation.getJSONObject(0);
                        JSONObject jsonObjectBachilleratoNombre = jsonObjectBachillerato.getJSONObject("school");
                        tvBachillerato.setText(jsonObjectBachilleratoNombre.getString("name"));

                        JSONObject jsonObjectUniversidad = jsonArrayEducation.getJSONObject(1);
                        JSONObject jsonObjectUniversidadNombre = jsonObjectUniversidad.getJSONObject("school");
                        tvUniversidad.setText(jsonObjectUniversidadNombre.getString("name"));

                        JSONObject jsonObjectFoto = jsonObject.getJSONObject("picture");
                        JSONObject jsonObjectFotoURL = jsonObjectFoto.getJSONObject("data");

                        Picasso.with(imageViewProfilePic.getContext()).load(jsonObjectFotoURL.getString("url").toString()).into(imageViewProfilePic);

                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                } else {
                    Log.e("myLog", "Algo salió mal :'(");
                }
            }

            // Si las cosas fallan rotundamente
            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.e("myLog", "Failure :(");
            }

        });
    }
}
