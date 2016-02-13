package com.englishnary.eridev.android.englishnary;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class UserCaptionActivity extends AppCompatActivity {
    ProgressDialog dialog;
    //Declaramos los controles necesarios para la logica de la aplicación
    private Spinner spinnerPalabras;
    private EditText etPalabraIntro;
    private Button btnBusca;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_caption);
        //Enlazamos los controles definidos con sus recursos a nivel de layout
        spinnerPalabras = (Spinner)findViewById(R.id.spinnerPalabras );
        etPalabraIntro = (EditText)findViewById(R.id.etPalabraIntro);
        btnBusca = (Button)findViewById(R.id.btnBuscar);
        //Creamos un Array de String con las secciones de las palabras
        String[] secciones = {"Irony","Data","Web","Design","Happy"};
        spinnerPalabras.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, secciones));

        //Método onCLick para el btn
        btnBusca.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent abreLista = new Intent(UserCaptionActivity.this, MainActivity.class);
                startActivity(abreLista);

            }
        });
    }
    //Creo unos métodos para CARGAR y otro para GUARDAR PREFERENCIAS

    //Método cargarPreferencias
    public void cargarPreferencias()
    {
        //NEcesitamos 2 param: El 1er parametro que le pasamos es el nombre del archivo xml que se crea con SharedPreferences
        //El 2º para que solo sea accedido por esta aplicacion, hay otros
        SharedPreferences misPreferencias = getSharedPreferences("PreferenciasUsuario", Context.MODE_PRIVATE);

        //Como estamos cargando hay que mostrarlo en algún sitio, usamos misPreferencias con getBoolean, el 1er para
        //que necesito es el nombre con que guardar la preferencia en el archivo xml y el 2 el valor por default que deberia tener este control

        //Lo hacemos también con el de arriba, pero aqui es texto(String) no un boolean, damos un nombre y por default vacío
       etPalabraIntro.setText(misPreferencias.getString("nombre",""));



    }

    //Método para guardar las preferencias
    public void guardarPreferencias()
    {
        //Necesitamos 2 param: El 1er parametro que le pasamos es el nombre del archivo xml que se crea con SharedPreferences
        //El 2º para que solo sea accedido por esta aplicacion, hay otros
        SharedPreferences misPreferencias = getSharedPreferences("PreferenciasUsuario", Context.MODE_PRIVATE);

        //Creamos una variable de tipo Editor, para empezar a editar en nuestro archivo xml esas prefrencias
        SharedPreferences.Editor editor = misPreferencias.edit();


        //Declaro un String para que obtenga el valor de la caja del nombre
        String valorNombre = etPalabraIntro.getText().toString();

        //Colocamos el string
        editor.putString("nombre",valorNombre);
        //Hacemos un commit para que se guarden los cambios
        editor.commit();


        //Como estamos cargando hay que mostrarlo en algún sitio, usamos misPreferencias con getBoolean, el 1er para
        //que necesito es el nombre con que guardar la preferencia en el archivo xml y el 2 el valor por default que deberia tener este control

        //Lo hacemos también con el de arriba, pero aqui es texto(String) no un boolean, damos un nombre y por default vacío
       etPalabraIntro.setText(misPreferencias.getString("nombre", ""));


    }

}
