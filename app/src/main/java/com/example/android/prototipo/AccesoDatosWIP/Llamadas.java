package com.example.android.prototipo.AccesoDatosWIP;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class Llamadas extends AsyncTask<String, Void, JSONObject>{
    private Context context;

    private int idMax=-1;

    public int getIdMax() {
        return idMax;
    }

    public void setIdMax(int idMax) {
        this.idMax = idMax;
    }
    public Llamadas(Context context){
        this.context=context;
    }

    @Override
    protected JSONObject doInBackground(String... params) {
        String accion=params[0];
        String tabla=params[1];
        //prueba
        HttpConexion httpConexion=new HttpConexion();
        HashMap<String,String> parametros=new HashMap<>();
        switch (accion){
            case "insert":
                switch (tabla) {
                    case "usuarios":
                        parametros.put("action", accion);
                        parametros.put("Tabla", params[1]);
                        parametros.put("TablaSecundaria",params[2]);
                        parametros.put("TipoUsuario", params[3]);
                        parametros.put("Usuario", params[4]);
                        parametros.put("Contrasena", params[5]);
                        parametros.put("Email", params[6]);
                        parametros.put("Nombres", params[7]);
                        parametros.put("ApPaterno", params[8]);
                        parametros.put("ApMaterno", params[9]);
                        parametros.put("FechaNac", params[10]);
                        parametros.put("Genero", params[11]);
                        if(params[2].equals("clientes")){
                            parametros.put("TipoCliente", params[12]);
                            parametros.put("Matricula", params[13]);
                        }else if(params[2].equals("dueños"))
                        {
                            parametros.put("Clave", params[12]);
                            parametros.put("NombreEstacionamiento", params[13]);
                            parametros.put("RFC", params[14]);
                            parametros.put("HoraInicio", params[15]);
                            parametros.put("HoraFin", params[16]);
                            parametros.put("PrecioServicio", params[17]);
                            parametros.put("Calle", params[18]);
                            parametros.put("NumExt", params[19]);
                            parametros.put("Colonia", params[20]);
                            parametros.put("CodigoPostal", params[21]);
                            parametros.put("Ciudad", params[22]);
                            parametros.put("Estado", params[23]);
                        }
                        break;
                    case "reservaciones":
                        //prueba
                        break;
                }
                break;
        }

        JSONObject jsonObject=httpConexion.request(Constantes.URL,parametros);
        return jsonObject;
    }
}