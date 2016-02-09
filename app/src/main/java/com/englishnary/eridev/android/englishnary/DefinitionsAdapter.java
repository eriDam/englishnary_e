package com.englishnary.eridev.android.englishnary;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by eridev on 1/02/16.
 */
public class DefinitionsAdapter extends ArrayAdapter<Definitions> {

    public DefinitionsAdapter(Context context, List<Definitions> objects) {
        super(context, 0, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){

        //Obteniendo una instancia del inflater
        LayoutInflater inflater = (LayoutInflater)getContext()
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        //Salvando la referencia del View de la fila
        View v = convertView;

        //Comprobando si el View no existe
        if (null == convertView) {
            //Si no existe, entonces inflarlo
            v = inflater.inflate(
                    R.layout.list_item_definitions,
                    parent,
                    false);
        }

        //Obteniendo instancias de los elementos
        TextView list_item_definitions_text = (TextView)v.findViewById(R.id.list_item_definitions_text);
        //TextView list_item_definitions_atribution = (TextView)v.findViewById(R.id.list_item_definitions_atribution);

        //Obteniendo instancia de la Tarea en la posición actual
        Definitions item = getItem(position);

        list_item_definitions_text.setText(item.getText());
        //list_item_definitions_atribution.setText(item.getAtribution());

        //Devolver al ListView la fila creada
        return v;

    }

    /*
     * Este método nos permite obtener el Id de un drawable a través
     * de su nombre
     */
    private int convertirRutaEnId(String nombre){
        Context context = getContext();
        return context.getResources()
                .getIdentifier(nombre, "drawable", context.getPackageName());
    }
}

