package com.englishnary.eridev.android.englishnary;

import android.util.JsonReader;

import com.google.gson.Gson;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by eridev on 1/02/16.
 */
public class GSonDefinitionParser {
    public List<Definitions> leerFlujoJson(InputStream in) throws IOException {
        // Nueva instancia de la clase Gson
        Gson gson = new Gson();

        JsonReader reader = new JsonReader(new InputStreamReader(in, "UTF-8"));
        List<Definitions> defs = new ArrayList<>();

        // Iniciar el array
        reader.beginArray();

        while (reader.hasNext()) {
            // Lectura de objetos
            Definitions definition = gson.fromJson(String.valueOf(reader), Definitions.class);
            defs.add(definition);
        }


        reader.endArray();
        reader.close();
        return defs;
    }
}
