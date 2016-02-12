package com.englishnary.eridev.android.englishnary;

import android.content.Context;
import android.database.Cursor;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by eridev on 1/02/16.
 * implements Cursor
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
//     @Override
//    public View newView(Context context, Cursor cursor, ViewGroup parent) {
//        // Choose the layout type
//        int viewType = getItemViewType(cursor.getPosition());
//        int layoutId = -1;
////        switch (viewType) {
////            case VIEW_TYPE_TODAY: {
////                layoutId = R.layout.list_item_forecast_today;
////                break;
////            }
////            case VIEW_TYPE_FUTURE_DAY: {
////                layoutId = R.layout.list_item_forecast;
////                break;
////            }
////        }
//
//        View view = LayoutInflater.from(context).inflate(layoutId, parent, false);
//
//        RecyclerView.ViewHolder viewHolder = new RecyclerView.ViewHolder(view);
//        view.setTag(viewHolder);
//
//        return view;
//    }
 //    @Override
    public void bindView(View view, Context context, Cursor cursor) {

        RecyclerView.ViewHolder viewHolder = (RecyclerView.ViewHolder) view.getTag();
        viewHolder.itemView.toString();
        int viewType = getItemViewType(cursor.getPosition());
//        switch (viewType) {
//            case view: {
//                // Get weather icon
//                viewHolder.iconView.setImageResource(Utility.getArtResourceForWeatherCondition(
//                        cursor.getInt(ForecastFragment.COL_WEATHER_CONDITION_ID)));
//                break;
//            }
//        }
////            case VIEW_TYPE_FUTURE_DAY: {
////                // Get weather icon
////                viewHolder.iconView.setImageResource(Utility.getIconResourceForWeatherCondition(
////                        cursor.getInt(ForecastFragment.COL_WEATHER_CONDITION_ID)));
////                break;
//         //   }
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

