package com.devf.quizapp;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.devf.quizapp.fragments.ResultFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ResultActivity extends AppCompatActivity {

    // TextView de nuestra actividad, donde mostramos el nombre de usuario
    @BindView(R.id.historial_tv_username)
    TextView tvUserNameResults;

    // Variables donde almacenamos los intent extras
    private String mUserName;
    private int mContador;

    // Inicialización de fragmentos
    private ResultFragment mResultFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        ButterKnife.bind(this);
        getIntentData();
        initViews();
        seeResultFragment(ResultFragment.newInstance(mContador));
    }

    private void initViews() {
        tvUserNameResults.setText(mUserName);
    }

    public void getIntentData() {
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            mUserName = extras.getString(Constants.INTENT_KEY_USERNAME);
            mContador = extras.getInt(Constants.INTENT_KEY_SCORE);
        }
    }

    public void seeResultFragment(ResultFragment fragment) {
        mResultFragment = fragment;
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .setCustomAnimations(R.anim.slide_rigth_enter, R.anim.slide_left_exit)
                .replace(R.id.result_frame_layout, fragment)
                .commit();
    }

    @OnClick(R.id.btn_inicio)
    public void onClick(View view){
        startActivity(new Intent(ResultActivity.this, MainActivity.class));
    }
}
