package com.example.tp5_goldenberg_wainberg;

import android.app.Fragment;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UTFDataFormatException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.acl.Group;

public class fragmentBuscar extends Fragment {
    String textoAbuscar = new String();
    View vistadevuelve;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        vistadevuelve = inflater.inflate(R.layout.fragment_resultados, container, false);
        Bundle bundel = getArguments();
        textoAbuscar = bundel.getString("textobuscar");

        return vistadevuelve;
    }

    public class Busqueda extends AsyncTask<Void, Void, Void> {
        @Override
        protected Void doInBackground(Void... voids) {
            try {
                URL rutatlantica = new URL("http://www.omdbapi.com/?apikey=73b36931&s=" + textoAbuscar);
                HttpURLConnection conexion = (HttpURLConnection) rutatlantica.openConnection();
                if (conexion.getResponseCode() == 200) {
                    Log.d("AccesoAPI3", "conexion ok");
                    InputStream cuerporesspuesta = conexion.getInputStream();
                    InputStreamReader lectorrespuesta = new InputStreamReader(cuerporesspuesta, "UTF-8");
                    Log.d("AccesoAPI3", "conexioion ok seguimos");

                    ProcessJSONLeido(lectorrespuesta);
                    Log.d("AccesoAPI3", "conexion ok daaale");
                } else {
                    Log.d("AccesoAPI3", "Error en la conexion");
                }
                conexion.disconnect();

            } catch (Exception error) {
                Log.d("AccesoAPI", "Hubo un error " + error.getMessage());
            }
            return null;
        }

    }

    public void ProcessJSONLeido(InputStreamReader a) {
        JsonParser parseador;
        parseador = new JsonParser();
        JsonObject objsoncrudo;
        objsoncrudo = parseador.parse(a).getAsJsonObject();
        for (int i = 0; i < objsoncrudo.size(); i++) {
            
        }

    }
}
