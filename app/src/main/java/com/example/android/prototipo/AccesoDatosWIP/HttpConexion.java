package com.example.android.prototipo.AccesoDatosWIP;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

public class HttpConexion {
    private HttpURLConnection connection;//objeto para conexion
    public static final int TIMEOUT=30000;//tiempo de espera

    //metodo para que la información sea en formato JSON..recibe un link y una serie de parametros
    public JSONObject request(String link, HashMap<String, String> parametros) { //HashMap coleccion de objetos(Arreglo) pero sin un orden
        JSONObject jsonObject=null;

        try {
            URL url= new URL(link);
            connection= (HttpURLConnection)url.openConnection();//abre la conexion
            connection.setReadTimeout(TIMEOUT);
            connection.setConnectTimeout(TIMEOUT);
            connection.setRequestMethod("POST");//metodo a manipular POST(envio de info a un servidor) o GET(retono de info de un servidor)
            connection.setDoInput(true);//permite el envio de parametros
            connection.setDoOutput(true);//permite recibir parametros
            connection.connect(); //hace la conexion
            if (parametros!=null){
                OutputStream outputStream=connection.getOutputStream();
                OutputStreamWriter outputStreamWriter=new OutputStreamWriter(outputStream,"UTF-8");
                BufferedWriter bufferedWriter=new BufferedWriter(outputStreamWriter);//permite escribir bloques de bytes
                //escribir los parametros en formato param=valor&param=valor...
                bufferedWriter.write(formatearValoresPost(parametros));//se escribe todos los parametros que se manden
                bufferedWriter.flush();//asi como esté, que se vaya el buffer
                bufferedWriter.close();//se cierra el flujo
                outputStream.close();//se cierra el objeto para escribir
            }
            if(connection.getResponseCode()== HttpURLConnection.HTTP_OK){//la conexion en su codigo de respuesta es igual a 200 bien/400 no found
                InputStream inputStream=connection.getInputStream();//lee de la conexion
                InputStreamReader inputStreamReader=new InputStreamReader(inputStream,"UTF-8");
                BufferedReader bufferedReader=new BufferedReader(inputStreamReader);//para leer un flujo de datos
                String linea="";
                String resultado ="";
                while ((linea=bufferedReader.readLine())!=null){
                    resultado+=linea;
                }
                Log.i("resultado",resultado);
                jsonObject=new JSONObject(resultado);//se convierte los datos leidos en formato JSON
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return jsonObject;
    }
    public String formatearValoresPost(HashMap<String,String> parametros){
        StringBuilder stringBuilder=new StringBuilder();//clase para menejo de una secuencia de caracteres mutables su contenido y capacidad puede cambiar en cualquier momento
        boolean primero=true;
        for(Map.Entry<String, String> param:parametros.entrySet()){
            if (primero){
                primero=false;
            }else{
                stringBuilder.append("&");
            }
            try {
                stringBuilder.append(URLEncoder.encode(param.getKey(),"UTF-8"));
                stringBuilder.append("=");
                stringBuilder.append(URLEncoder.encode(param.getValue(),"UTF-8"));
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }

        }

        return  stringBuilder.toString();
    }
} 
