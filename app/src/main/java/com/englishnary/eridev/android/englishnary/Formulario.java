//package com.englishnary.eridev.android.englishnary;
//
//import android.app.Activity;
//import android.content.ContentValues;
//import android.content.Intent;
//import android.database.Cursor;
//import android.database.sqlite.SQLiteDatabase;
//import android.os.Bundle;
//import android.view.Menu;
//import android.view.MenuItem;
//import android.view.View;
//import android.widget.Button;
//import android.widget.EditText;
//import android.widget.TextView;
//import android.widget.Toast;
//
//
//public class Formulario extends Activity {
//
//    private TextView labelId;
//    private EditText nombre;
//
//    //Identificador de la base de datos
//    private DataBaseMan mDbHelper;
//    private SQLiteDatabase db;
//    private long id ;
//
//    //
//    // Modo del formulario
//    //
//    private int modo ;
//
//    //Botones
//    private Button boton_guardar;
//    private Button boton_cancelar;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_formulario);
//
//        //CApturamos los datos enviados
//        Intent intent = getIntent();
//        Bundle extra = intent.getExtras();
//
//        if (extra == null) return;
//
//        // Consultamos la base de datos
//        mDbHelper = new DataBaseMan(this);
//        db = mDbHelper.getWritableDatabase();
//
//        //
//        // Obtenemos los elementos de la vista
//        //
//        labelId = (TextView) findViewById(R.id.label_id);
//        nombre  = (EditText) findViewById(R.id.nombre);
//
//        //
//        // Obtenemos el identificador del registro si viene indicado
//        //
//        if (extra.containsKey(DataBaseMan.ID))
//        {
//            id = extra.getLong(DataBaseMan.ID);
//            consultar(id);
//        }
//        //Botones de guardado y cancelar
//        boton_guardar = (Button) findViewById(R.id.boton_guardar);
//        boton_cancelar = (Button) findViewById(R.id.boton_cancelar);
//
//        //
//        // Establecemos el modo del formulario
//        //
//        establecerModo(extra.getInt(mDbHelper.C_MODO));
//
//        //
//        // Definimos las acciones para los dos botones
//        //
//        boton_guardar.setOnClickListener(new View.OnClickListener() {
//
//            @Override
//            public void onClick(View v)
//            {
//                guardar();
//            }
//        });
//
//        boton_cancelar.setOnClickListener(new View.OnClickListener() {
//
//            @Override
//            public void onClick(View v)
//            {
//                cancelar();
//            }
//        });
//    }
//
//    private void consultar(long id)
//    {
//        //
//        // Consultamos el centro por el identificador
//        //
//        Cursor cursor = mDbHelper.getRegistro(id);
//        labelId.setText(labelId.getText()+cursor.getString(cursor.getColumnIndex(DataBaseMan.ID)));
//        nombre.setText(cursor.getString(cursor.getColumnIndex(DataBaseMan.LESSON_NAME)));
//    }
//
//    private void establecerModo(int m)
//    {
//        this.modo = m ;
//
//        if (modo == mDbHelper.C_VISUALIZAR)
//        {
//            this.nombre.setEnabled(false);
//            this.boton_guardar.setEnabled(false);
//        }else if ((modo == mDbHelper.C_CREAR)||(modo == mDbHelper.C_EDITAR)){
//            this.setTitle(R.string.hipoteca_crear_titulo);
//            this.nombre.setEnabled(true);
//            this.boton_guardar.setEnabled(true);
//        }
//    }
//    private void guardar()
//    {
//        //
//        // Obtenemos los datos del formulario
//        //
//        ContentValues reg = new ContentValues();
//        if (modo == mDbHelper.C_EDITAR) reg.put(mDbHelper.ID, id);
//        reg.put(mDbHelper.LESSON_NAME, nombre.getText().toString());
//
//        if (modo == mDbHelper.C_CREAR)
//        {
//            mDbHelper.insert(reg);
//            Toast.makeText(Formulario.this, "Curso creado", Toast.LENGTH_SHORT).show();
//        }   else if (modo == mDbHelper.C_EDITAR){
//            Toast.makeText(Formulario.this, "Curso modificado", Toast.LENGTH_SHORT).show();
//            mDbHelper.update(reg);
//        }
//
//        //
//        // Devolvemos el control
//        //
//        setResult(RESULT_OK);
//        finish();
//    }
//    private void cancelar()
//    {
//        setResult(RESULT_CANCELED, null);
//        finish();
//    }
//
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.menu_formulario, menu);
//        return true;
//    }
//
//    /**Cuando seleccionemos un item del menu, mostrar� un mensaje, dependiendo del id que me devuelva line 28,
//     * van a haber muchos case*/
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        switch (item.getItemId()) {
//
//            case R.id.menu_inicio:
//                Toast.makeText(getApplicationContext(), "INICIO", Toast.LENGTH_SHORT).show();
//                //arrancar la siguiente activity
//                Intent abre;
//                abre = new Intent(Formulario.this, MainActivity.class);
//                startActivity(abre);
//                return true;
//            case R.id.menu_devtest:
//                Toast.makeText(getApplicationContext(), "DEVTEST", Toast.LENGTH_SHORT).show();
//                //arrancar la siguiente activity
//                Intent abreDev;
//                abreDev = new Intent(Formulario.this, DevTest.class);
//                startActivity(abreDev);
//                return true;
//            case R.id.menu_lessons:
//                Toast.makeText(getApplicationContext(), "LESSONS", Toast.LENGTH_SHORT).show();
//                //arrancar la siguiente activity
//                Intent abreLessons;
//                abreLessons = new Intent(Formulario.this, Lessons2Activity.class);
//                startActivity(abreLessons);
//                return true;
//            case R.id.menu_profile:
//                Toast.makeText(getApplicationContext(), "PERFILES", Toast.LENGTH_SHORT).show();
//                //arrancar la siguiente activity
//                Intent abreProf;
//                abreProf = new Intent(Formulario.this, Prof3Activity.class);
//                startActivity(abreProf);
//                return true;
//            case R.id.menu_git: /**Este Id  hace referencia al id del item del menu, en este caso buscar*/
//                Toast.makeText(getApplicationContext(), "ONCODE", Toast.LENGTH_SHORT).show();
//                //arrancar la siguiente activity
//                Intent abreGit;
//                abreGit = new Intent(Formulario.this, OnCode4Activity.class);
//                startActivity(abreGit);
//                return true;
//            case R.id.menu_gestion:
//                Toast.makeText(getApplicationContext(), "GESTI�N", Toast.LENGTH_SHORT).show();
//                //arrancar la siguiente activity
//                Intent abreGestion;
//                abreGestion = new Intent(Formulario.this, Gestion5Activity.class);
//                startActivity(abreGestion);
//                return true;
//            case R.id.menu_blog:
//                Toast.makeText(getApplicationContext(), "GESTI�N", Toast.LENGTH_SHORT).show();
//                //arrancar la siguiente activity
//                Intent abreBlog;
//                abreBlog = new Intent(Formulario.this, Blog6Activity.class);
//                startActivity(abreBlog);
//                return true;
//            case R.id.action_settings:
//                Toast.makeText(getApplicationContext(), "SETTINGS", Toast.LENGTH_SHORT).show();
//                //TODO crear opciones de personalizaci�n
////                //arrancar la siguiente activity
////                Intent abreBlog;
////                abreBlog= new Intent(MainActivity.this, Blog6Activity.class);
////                startActivity(abreBlog);
//                return true;
//            default:
//                return super.onOptionsItemSelected(item);
//        }
//    }
//
//}
