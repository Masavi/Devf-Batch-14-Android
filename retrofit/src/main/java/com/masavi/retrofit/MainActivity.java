package com.masavi.retrofit;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.http.GET;
import retrofit2.http.Path;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        getDataGithub();
    }

    private void getDataGithub() {
        //Creamos una instancia de retrofit con singleton
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.github.com")
                .build();

        GitHubService gitHubService = retrofit.create(GitHubService.class);

        gitHubService.getUserInfoPath("ericklarac").enqueue(new Callback<ResponseBody>() {

            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                Log.e("myLog", "Success!!");
                //Obtengo el código de la respuesta
                int statusCode = response.code();
                Log.e("myLog", "\nCódigo de Respuesta: " + statusCode);
                //Verificamos que el código de respuesta sea 200
                if (statusCode == 200){
                    try {
                        String body = response.body().string();
                        //Log.e("myLog", body);
                        JSONObject jsonObject = new JSONObject(body);
                        Log.e("myLog", jsonObject.toString(4));
                        Log.e("myLog", jsonObject.getString("company"));
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                } else if (statusCode == 404) {
                    Toast.makeText(MainActivity.this, "El usario no existe", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.e("myLog", "Failure :(");
            }
        });
    }

    private interface GitHubService{
        //GET con uri del recurso que queremos obtener
        @GET("/users/Masavi")
        public Call<ResponseBody> getUserInfo();

        //GET con uri del recurso que queremos obtener
        @GET("/users/{username}")
        public Call<ResponseBody> getUserInfoPath(@Path("username") String userName);
    }




}
