package com.englishnary.eridev.android.englishnary;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

/**
 * Created by erika_000 on 28/04/2015.
 */
public class DPreguntas extends SQLiteOpenHelper {
   ///Declaramos 2 constantes. Esta clase necesita dos parametros,
   // el nombre del archivo de la bd y el nombre del archivo de la version , la version del esquema
    private static final String DB_NAME = "preguntas_db";
    private static final int SCHEME_VERSION = 1;

    //Declaro variable de tipo SQLiteDataBase que me permitirá ejecutar las consultas SQL, en el onCreate le ponemos el db.execSQL
    private SQLiteDatabase db;


    //Al constructor le paso solo el contexto
    public DPreguntas(Context context) {

        // Sustitutyo el párametro del construtor super (name x la constante igual que version
        super(context, DB_NAME, null, SCHEME_VERSION);
        //Cuando inicie el constr al llamarla desde otra clase, se ejecuta la bd
        //en modo escritura, la 1ª vez si no existe la crea, si está la abre
        db = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //Me permitirá ejecutar las consultas SQL
    db.execSQL(EPreguntas.CREATE_DB_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    //Creo un contenedor de valores - Le paso la clase EPreguntas x  parámetro
    private ContentValues generarValores(EPreguntas preguntas)
    {
        //Creamos contenedor
        ContentValues valores = new ContentValues();
        //Añadimos los valores (nuestros campos de la tabla)
        valores.put(EPreguntas.FIELD_PREGUNTA,preguntas.getPregunta());
        valores.put(EPreguntas.FIELD_A1,preguntas.getA1());
        valores.put(EPreguntas.FIELD_A2,preguntas.getA2());
        valores.put(EPreguntas.FIELD_A3,preguntas.getA3());
        valores.put(EPreguntas.FIELD_A4,preguntas.getA4());
        valores.put(EPreguntas.FIELD_AC,preguntas.getAc());
        valores.put(EPreguntas.FIELD_ESTADO,preguntas.getEstado());
        return valores;
    }

    /**
     * Método devuelve todas las preguntas
     * Va a devolver un ArraList de tipo EPreguntas
    public ArrayList<EPreguntas> getPreguntasAll()
    {
      //Declaro el arrayList que me va a devolver de tipo EPreguntas
        ArrayList<EPreguntas> preguntas = new ArrayList<>();

     //Declaro array de las columnas para pasar al constructor, en orden...todas las que quiero que me devuelva
        String columnas[] = {EPreguntas.FIELD_ID, EPreguntas.FIELD_PREGUNTA,EPreguntas.FIELD_A1,
                             EPreguntas.FIELD_A2, EPreguntas.FIELD_A3, EPreguntas.FIELD_A4,
                             EPreguntas.FIELD_AC, EPreguntas.FIELD_ESTADO};

        // Para HACER CONSULTA (sin where, recoge todas acertadas y no) utilizo db con el método query y su construct
        // que necesita, el nombre de la tabla(lo obtengo con la clase EPreguntas)
         // el array y el resto (oreder by, having...)null. Se la asigno al objeto cursor.
         // Recorrer el arrayList:
        //    Creo objeto de la clase Cursor, necesario para ello

        Cursor cursor = db.query(EPreguntas.TABLE_NAME, columnas,null,null,null,null,null );
        //Iteramos con el cursor para acceder a los elementos que devuelve el cursor y para insertarlos
        //en el ArrayList
        if(cursor.moveToFirst())//Para asegurarnos que tiene un elemento
            {
                do
                {
                    //Declaro entidad EPreguntas
                    EPreguntas pre = new EPreguntas();
                    pre.setId(cursor.getInt(0));//Establezco el ID qeu es un int, le damos la pos 0
                    pre.setPregunta(cursor.getString(1));//Este es un btipo de dato String, asigno pos 1
                    pre.setA1(cursor.getString(2));
                    pre.setA2(cursor.getString(3));
                    pre.setA3(cursor.getString(4));
                    pre.setA4(cursor.getString(5));
                    pre.setAc(cursor.getString(6));
                    pre.setEstado(cursor.getInt(7));
                    preguntas.add(pre);//asignamos al arrayList la entidad pre de EPreguntas


                }while(cursor.moveToNext());
            }
        //Devuelvo el arrayList
            return preguntas;

    }*/

    //Método para preguntas accertadas, le pasamos valor como parametro para darle un num, si ha sido adivinadas o no
    public ArrayList<EPreguntas> getPregunta(String valor)
    {
        //Declaro el arrayList que me va a devolver de tipo EPreguntas
        ArrayList<EPreguntas> preguntas = new ArrayList<>();

        //Declaro array de las columnas para pasar al constructor, en orden...todas las que quiero que me devuelva
        String columnas[] = {EPreguntas.FIELD_ID, EPreguntas.FIELD_PREGUNTA,EPreguntas.FIELD_A1,
                EPreguntas.FIELD_A2, EPreguntas.FIELD_A3, EPreguntas.FIELD_A4,
                EPreguntas.FIELD_AC, EPreguntas.FIELD_ESTADO};

        // Para HACER CONSULTA (sin where, recoge todas acertadas y no) utilizo db con el método query y su construct
        // que necesita, el nombre de la tabla(lo obtengo con la clase EPreguntas)
        // el array y el resto (order by, having...ctrl+p me saca el constructor para ver). Se la asigno al objeto cursor.

        // después del array columnas voy a COMPARAR con el campo estado, despues solicita un selection, el signo de ? se cambiará por este valor,
        //Nos va a devolver todos los valores de la tabla donde la condición sea según lo que le pasemos por param (valor)
        // Recorrer el arrayList:
        //Creo objeto de la clase Cursor, necesario para ello

        Cursor cursor = db.query(EPreguntas.TABLE_NAME, columnas,EPreguntas.FIELD_ESTADO + "=?",new String[] {valor},null,null,null );
        //Iteramos con el cursor para acceder a los elementos que devuelve el cursor y para insertarlos
        //en el ArrayList
        if(cursor.moveToFirst())//Para asegurarnos que tiene un elemento
        {
            do
            {
                //Declaro entidad EPreguntas
                EPreguntas pre = new EPreguntas();
                pre.setId(cursor.getInt(0));//Establezco el ID q es un int, le damos la pos 0
                pre.setPregunta(cursor.getString(1));//Este es un btipo de dato String, asigno pos 1
                pre.setA1(cursor.getString(2));
                pre.setA2(cursor.getString(3));
                pre.setA3(cursor.getString(4));
                pre.setA4(cursor.getString(5));
                pre.setAc(cursor.getString(6));
                pre.setEstado(cursor.getInt(7));
                preguntas.add(pre);//asignamos al arrayList la entidad pre de EPreguntas


            }while(cursor.moveToNext());
        }
        //Devuelvo el arrayList
        return preguntas;

    }

    //Método para actualizar preguntas en la bd si son acertadas o no
    public void actualizarPreguntas(EPreguntas preguntas)
    {
        //Actualizamos según el Id, como es un entero, le hacemos el cambio a String con ValueOf
        db.update(EPreguntas.TABLE_NAME,generarValores(preguntas),EPreguntas.FIELD_ID + "=?", new String[] {String.valueOf(preguntas.getId())});


    }



    //Método para insertar
    //Le paso la clase EPreguntas x  parámetro
    public void insertarPreguntas(EPreguntas preguntas)
    {
        db.insert(EPreguntas.TABLE_NAME,null,generarValores(preguntas));
    }
}
