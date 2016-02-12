package com.englishnary.eridev.android.englishnary;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.ImageButton;

public class MenuMain extends AppCompatActivity {
    //Controles
    private CardView card_find, card_notes, card_lessons,card_triv,card_player;
    private ImageButton imgButton2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_main);
        iniciarControles();
    }


    //Metodo para recuperar los controles mediante su id, para descargar el onCreate
    private void iniciarControles() {
        //Obtengo los controles mediante su id
       // imgButton2 = (ImageButton) findViewById(R.id.ibtnFind);
        //Obtengo los controles mediante su id
        card_find    = (CardView) findViewById(R.id.card_find);
        card_notes   = (CardView) findViewById(R.id.card_notes);
        card_lessons = (CardView) findViewById(R.id.card_lessons);
        card_triv    = (CardView) findViewById(R.id.card_triv);

//        imgButton2.setOnClickListener(new ImageButton.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent abreBuscar = new Intent(MenuMain.this, MainActivity.class);
//                startActivity(abreBuscar);
//
//            }
//        });

        //EVENTOS
        card_find.setOnClickListener(new CardView.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent abreBuscar = new Intent(MenuMain.this, MainActivity.class);
                startActivity(abreBuscar);
//                Intent abreLessons = new Intent(MainActivity.this, MapsActivity.class);
//                startActivity(abreLessons);

//                Intent intento = new Intent(Intent.ACTION_VIEW, Uri.parse("http://es.cppreference.com/w/"));
//                startActivity(intento);

            }
        });

        card_notes.setOnClickListener(new CardView.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent abreNotas = new Intent(MenuMain.this, MainActivity.class);
                startActivity(abreNotas);
            }
        });

            card_lessons.setOnClickListener(new CardView.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent abreNotas = new Intent(MenuMain.this, MainActivity.class);
                startActivity(abreNotas);
            }
        });

    }

    }

