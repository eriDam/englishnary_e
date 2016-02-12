package com.englishnary.eridev.android.englishnary;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;


public class DevTest extends ActionBarActivity {
    //Declaro los controles a utilizar
    Button btn_empezar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dev_test);
        //Obtengo los controles
        btn_empezar = (Button)findViewById(R.id.btn_empezar);
        btn_empezar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentEmpezar = new Intent(DevTest.this,splash_screen.class);
                startActivity(intentEmpezar);
            }
        });

        //Creo una instancia de DPreguntas y le paso la instancia de la clase this
        DPreguntas dPregHelper = new DPreguntas(this);
    }



    }

