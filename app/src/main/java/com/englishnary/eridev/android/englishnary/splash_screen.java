package com.englishnary.eridev.android.englishnary;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;

import java.util.ArrayList;
import java.util.Arrays;


public class splash_screen extends ActionBarActivity {

    //Declaro variable para almacenar las preferencias de usuario
    private boolean estado;
    ProcesoCarga procesoCarg = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //La primera vez que se inicie muestra el splash screen - La segunda vez ya no lo muestra
        setContentView(R.layout.activity_splash_screen);
       //Carga las preferencias
        cargarPreferencias();
        //Comprobamos el estado de las preferencias
        if (estado)
        {
            //Si han sido cargadas muestra la siguiente pantalla, el código está en postExecute
        }
        else
        {
            //Si no han sido cargadas, insertará todas las preguntas que tiene el procesoCarga
             procesoCarg = new ProcesoCarga();
            procesoCarg.execute();//Para ejecutar el AsyncTask usamos el execute
        }
    }

    /*Cargamos Preferencias
     * Sirven para guardar si han sido insertados datos si se han  insertado cargamos una cosa si no otra */
    private void cargarPreferencias()
    {
        SharedPreferences misPreferencias = getSharedPreferences("preferencias", Context.MODE_PRIVATE);
        misPreferencias.getBoolean("isLoad",false);
    }

    /*Guardar preferencias, */
    private void guardarPreferencias(boolean valores)
    {
        SharedPreferences misPreferencias = getSharedPreferences("preferencias", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = misPreferencias.edit();
        editor.putBoolean("isLoad",false);
        editor.commit();

    }

    //
    // Clase Privada Proceso de carga AsyncTask, implementamos los metodos.
    //


/**Utilizamos el AsyncTask para cuando una tarea es demasiado pesada que se ejecute en 2 plano
 * */
    private class ProcesoCarga extends AsyncTask<Void,Void,Void>
    {
        ProgressDialog dialog;
        //Creo un array con nuestra entidad EPreguntas, asList es para crear lista,
        ArrayList<EPreguntas> preguntas = new ArrayList<EPreguntas>(Arrays.<EPreguntas>asList(
                //Paso las pregunta las respuestas y por ultimo la respuesta correcta
                new EPreguntas("Are you German?\n" +
                        "Yes, ....", "you are German", "I am", "he is", "Not", "I am"),
                new EPreguntas("In Android...What types of Layouts know?", "RelativeLayout, LinearLayout, FrameLayout, ListLayout, TableLayout, GridLayout", "0", "Solo RelativeLayout y LinearLayout", "RelativeLayout, LinearLayout, FrameLayout", "RelativeLayout, LinearLayout, FrameLayout, ListLayout, TableLayout, GridLayout"),
                new EPreguntas("What Query in SQL is to data order?", "Having", "Short", "Order", "Select", "Order"),
                new EPreguntas("Mary: \"How do you do?\"\n" +
                        "John: \"I am fine.\" ....", "How do you do?", "How are you?", "How are you are?", "I'm a businessman.","How are you?"),
                new EPreguntas("In SQL, which of these statements adds a row to a table in a database?", "ADD", "INSERT", "UPDATE", "INCLUDE", "INSERT"),
                new EPreguntas("In SQL, to eliminate duplicate rows of a SELECT statement is used ...", "NO DUPLICATE", "UNIQUE", "DISTINC", "GOUP BY", "DISTINC"),
                new EPreguntas("Which of these statements the SET clause is used ", "DELETE", "DROP", "UPDATE", "SELECT", "UPDATE"),
                new EPreguntas("Int, char, float, string y boolean are", "Funtions access to data ", "Data access instructions ", " Data access instructions control", "Data types","Data types"),
                new EPreguntas("-what's your favorite app", "Anything", "This", "CodeOn", "MusicApp", "This"),
                new EPreguntas("Where is Susan going?... \"\ngoing to London.", "They're", "She's", "Susan does", "Susan doing","Susan does"),
                new EPreguntas("In SQL, which of these statements adds a row to a table in a database?", "ADD", "INSERT", "UPDATE", "INCLUDE", "INSERT"),
                new EPreguntas("In SQL, to eliminate duplicate rows of a SELECT statement is used ...", "NO DUPLICATE", "UNIQUE", "DISTINC", "GOUP BY", "DISTINC"),
                new EPreguntas("Which of these statements the SET clause is used ", "DELETE", "DROP", "UPDATE", "SELECT", "UPDATE")
        ));

        //Se va a ejecutar antes d que se inicie un hilo, el asyncTask es un hilo
       @Override
        protected void onPreExecute() {
       // super.onPreExecute();
        dialog = new ProgressDialog(splash_screen.this);
        dialog.setTitle("Mixing questions");
        dialog.setMessage("Then you have each question with 4 options to choose an answer. " +
                "Enjoy and learn futur@ Developer :) ");
        dialog.show();//Para mostrar
       }

        //Se ejecuta cuando ha terminado el doInBackground

        @Override
        protected void onPostExecute(Void aVoid) {
          //Guardo preferencias por que ya ha sido cargado
            guardarPreferencias(false);
            if(dialog.isShowing())//Si se muestra...
            {
                Intent intent = new Intent(splash_screen.this, Jugar.class);
                startActivity(intent);
                finish();
               dialog.dismiss();//Que se deje de mostrar
           }
        }

        //Para que empiecce a correr en segundo plano
        @Override
        protected Void doInBackground(Void... params) {
            //Creo una instancia de DPreguntas por que neceito el método insertarPreguntas
            DPreguntas dPregHelper = new DPreguntas(splash_screen.this);
            EPreguntas preg = new EPreguntas();
            //Hago un for para recorrer el ARrayListPreguntas
            for (int i=0; i<preguntas.size();i++)
            {
                preg = preguntas.get(i);
                System.out.println("Pregunta insertada ok");
                //Utilizo el objeto de la clase DPreguntas para insertar
                dPregHelper.insertarPreguntas(preg);

                try {
                    //Le pongo medio segundo de sleep, para q el dialog tarde un poco más
                    Thread.sleep(500);
                    System.out.println("espero 1/2 segundo");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    System.out.println("Ha ocurrido un error al cargar");
                }
            }
            return null;
        }
    }

}
