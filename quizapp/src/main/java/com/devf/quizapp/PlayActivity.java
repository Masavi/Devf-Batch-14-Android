package com.devf.quizapp;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.devf.quizapp.fragments.QuestionFragment;
import com.devf.quizapp.fragments.ResultFragment;
import com.devf.quizapp.models.Historial;
import com.devf.quizapp.models.Pregunta;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.realm.Realm;
import io.realm.RealmConfiguration;

public class PlayActivity extends AppCompatActivity {

    // TextView de nuestra actividad, donde mostramos el nombre de usuario
    @BindView(R.id.play_tv_username)
    TextView tvUserName;

    // Variables donde almacenamos los intent extras
    private String mUserName;
    private long userID;

    // Lista de preguntas donde podemos agregar cada una de las preguntas
    // que se mostrarán al cambiar cada fragmento
    private List<Pregunta> mListPreguntas = new ArrayList<>();

    // Posición del jugador al moverse entre preguntas
    private int mQuestionPosition;

    // Puntuación del jugador
    private int mContador;


    // Inicialización de fragmentos
    private QuestionFragment mFragment;
    private ResultFragment mResultFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Indicamos a nuestra actividad qué layout debe mostrar
        setContentView(R.layout.activity_play);
        //Inicializamos ButterKnife
        ButterKnife.bind(this);
        //Obtenemos la data del intent, es decir, la información en el bundle de extras
        // de la actividad anterior
        getIntentData();
        // Añadimos cada pregunta que será cargada al movernos entre fragmentos
        getData();
        // Colocamos el userName, escrito en la primer actividad, en el TextView superior de esta actividad
        initViews();

        /*
         * Iniciamos la primer pregunta
         */
        changeFragment(QuestionFragment.newInstance(mListPreguntas.get(mQuestionPosition = 0).getTitle()));
    }

    /*
     * Inicializamos la lista de preguntas con sus respuestas correctas
     */
    private void getData() {
        mListPreguntas.add(new Pregunta(getString(R.string.question1), 1));
        mListPreguntas.add(new Pregunta(getString(R.string.question2), 0));
        mListPreguntas.add(new Pregunta(getString(R.string.question3), 1));
        mListPreguntas.add(new Pregunta(getString(R.string.question4), 0));
        mListPreguntas.add(new Pregunta(getString(R.string.question5), 0));
    }

    private void initViews() {
        tvUserName.setText(mUserName);
    }

    /**
     * Obtenemos los datos del intent
     */
    public void getIntentData() {
        /**
         * Obtenemos el intent que mando a llamar esta actividad y
         * obtenemos el bundle que contiene
         */
        Bundle extras = getIntent().getExtras();
        //Verificamos que tenga datos el Bundle
        if (extras != null) {
            //Obtenemos el username guardado en los extras
            mUserName = extras.getString(Constants.INTENT_KEY_USERNAME);
            //Obtenemos el ID del user que está jugando
            userID = extras.getLong(Constants.INTENT_KEY_ID);
        }
    }

    @OnClick(R.id.quiz_img_arrow_right)
    public void next() {
        // En "respuesta" guardamos el valor de la respuesta elegida por el usuario
        int respuesta = mFragment.getValue();
        // Si el usuario no ha seleccionado una respuesta
        if (respuesta == -1){
            Toast.makeText(PlayActivity.this, "Debes elegir una respuesta " + mContador, Toast.LENGTH_LONG).show();
        } else {
            // Si el usuario responde correctamente
            if (respuesta == mListPreguntas.get(mQuestionPosition).getValue()) {
                mContador++;
            }

            if (mQuestionPosition < mListPreguntas.size() - 1) {
                changeFragment(QuestionFragment.newInstance(mListPreguntas.get(++mQuestionPosition).getTitle()));
            } else {
                //Al terminar la lista de preguntas se actualiza la puntuación del usuario.
                actualizarPuntuacion();
                //Seguido, se muestra el historial de puntuaciones
                Intent intent = new Intent(PlayActivity.this, ResultActivity.class);
                //Mandamos data extra
                intent.putExtra(Constants.INTENT_KEY_USERNAME, mUserName);
                intent.putExtra(Constants.INTENT_KEY_SCORE, mContador);
                //Lanzamos la actividad
                startActivity(intent);
            }
        }
    }

    /*
     * Metodo para cambiar el fragmento que esta en el frame layout de la pantalla
     *
     * @param fragment
     */
    public void changeFragment(QuestionFragment fragment) {
        /*
         *Obtengo el FragmentManaget que me ayuda con las transacciones
         *que necesite hacer con mis fragmentos
         */
        mFragment = fragment;
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .setCustomAnimations(R.anim.slide_rigth_enter, R.anim.slide_left_exit)
                .replace(R.id.frame_layout, fragment)
                .commit();
    }

    private void actualizarPuntuacion() {
        Realm realm = Realm.getDefaultInstance();

        Historial historial = new Historial();
        historial.setNombreUsuario(mUserName);
        historial.setPuntuacion(mContador);
        historial.setId(userID);

        realm.beginTransaction();
        realm.copyToRealmOrUpdate(historial);
        realm.commitTransaction();

        Log.e("myLog", "¡Puntuación actualizada!: " + historial.toString() + "\n ID: " + userID);
    }
}

