package com.englishnary.eridev.android.englishnary;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.ImageButton;

public class MenuMain extends AppCompatActivity {
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
        imgButton2 = (ImageButton) findViewById(R.id.ibtnFind);
        //Obtengo los controles mediante su id
        card_maps = (CardView) findViewById(R.id.card_maps);
        card_players = (CardView) findViewById(R.id.card_players);
        card_dark = (CardView) findViewById(R.id.card_dark);

  /*Card Lessons         card_maps.setOnClickListener(new CardView.OnClickListener() {
*/
        imgButton2.setOnClickListener(new ImageButton.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent abreBuscar = new Intent(MenuMain.this, MainActivity.class);
                startActivity(abreBuscar);

            }
        });
    }

}