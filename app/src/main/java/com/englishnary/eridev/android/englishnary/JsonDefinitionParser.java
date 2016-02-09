package com.englishnary.eridev.android.englishnary;

import android.util.JsonReader;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by eridev on 1/02/16.
 */
public class JsonDefinitionParser {

    public List<Definitions> leerFlujoJson(InputStream in) throws IOException {
        // Nueva instancia JsonReader
        JsonReader reader = new JsonReader(new InputStreamReader(in, "UTF-8"));
        try {
            // Leer Array
            return leerArrayDef(reader);
        } finally {
            reader.close();
        }

    }



    public List<Definitions> leerArrayDef(JsonReader reader) throws IOException {
        // Lista temporal
        ArrayList<Definitions> definitions = new ArrayList<>();

        reader.beginArray();
        while (reader.hasNext()) {
            // Leer objeto
            definitions.add(leerDef(reader));
        }
        reader.endArray();
        return definitions;
    }

    public Definitions leerDef(JsonReader reader) throws IOException {
        // Variables locales
        String text = null;
        String atribution = null;
        //String definitionsw = null;

        // Iniciar objeto
        reader.beginObject();

        /*
        Lectura de cada atributo
         */
        while (reader.hasNext()) {
            String name = reader.nextName();
            switch (name) {
                case "text":
                    text = reader.nextString();

                    break;
                case "atribution":
                    atribution = reader.nextString();
                    break;
                default:
                    reader.skipValue();
                    break;
            }
        }
        reader.endObject();
        return new Definitions(text, atribution);
    }

}
