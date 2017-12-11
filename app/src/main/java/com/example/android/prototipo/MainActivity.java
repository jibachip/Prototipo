package com.example.android.prototipo;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.transition.Explode;
import android.transition.Fade;
import android.transition.Transition;
import android.view.View;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonRequest;
import com.android.volley.toolbox.Volley;
import com.example.android.prototipo.AccesoDatosWIP.Constantes;
import com.example.android.prototipo.AccesoDatosWIP.EsquemasBaseDeDatos;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity implements Response.Listener<JSONObject>,Response.ErrorListener {



    RequestQueue rq;
    JsonRequest jrq;
    EsquemasBaseDeDatos.Usuarios user;
    SharedPreferences preferences;
    EditText usuario,contraseña;
    String UserType;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //UN COMENTARIO PARA PRUEBA
        //COMENTARIO MAS
        Button registrarse=(Button)findViewById(R.id.btnRegistro);
        //Button registrarse=findViewById(R.id.btnRegistro);
        Button button=findViewById(R.id.btnIniciar);
        usuario=findViewById(R.id.etUsu);
        contraseña=findViewById(R.id.etPas);

        rq= Volley.newRequestQueue(getApplicationContext());

        preferences=getSharedPreferences("Datos cuenta",MODE_PRIVATE);
        boolean b=preferences.contains("Usuario");

        if (b){
            usuario.setText(preferences.getString("Usuario","User"));
            contraseña.setText(preferences.getString("Contraseña","Pass"));
            UserType=preferences.getString("TipoUsuario","UserType");

            if(UserType.equals("Cliente")){
                Intent intent=new Intent(getApplicationContext(),Saludo.class);
                startActivity(intent);
                overridePendingTransition(R.anim.fab_open, R.anim.fab_close);
            }
            else if(UserType.equals("Dueno")){
                Intent intent=new Intent(getApplicationContext(),MainDuenos.class);
                startActivity(intent);
                overridePendingTransition(R.anim.fab_open, R.anim.fab_close);
            }
        }

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                IniciaSesion();
            }
        });




        registrarse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(getApplicationContext(), RegistroTab.class);
                startActivity(intent);

                overridePendingTransition(R.anim.zoom_back_in, R.anim.zoom_back_out);
            }
        });
    }


    private void IniciaSesion(){
        String url = "http://192.168.0.17:8080/base/wipInicio.php?user="+usuario.getText().toString()+"&pwd="+contraseña.getText().toString();
        jrq=new JsonObjectRequest(Request.Method.GET,url,null,this,this);
        rq.add(jrq);
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        Toast.makeText(getApplicationContext(), "Usuario o Contraseña incorrectos", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onResponse(JSONObject response) {
        Toast.makeText(getApplicationContext(), "Iniciando Sesion", Toast.LENGTH_SHORT).show();
        user=new EsquemasBaseDeDatos.Usuarios();

        JSONArray jsonArray = response.optJSONArray("datos");
        JSONObject jsonObjectson=null;

        try{
            jsonObjectson=jsonArray.getJSONObject(0);
            user.setTipoUsuario(jsonObjectson.optString("TipoUsuario"));
            user.setUsuario(jsonObjectson.optString("Usuario"));
            user.setContrasena(jsonObjectson.optString("Contrasena"));
            user.setEmail(jsonObjectson.optString("Email"));
            user.setNombres(jsonObjectson.optString("Nombres"));
            user.setApPaterno(jsonObjectson.optString("ApPaterno"));
            user.setApMaterno(jsonObjectson.optString("ApMaterno"));
            user.setGenero(jsonObjectson.optString("Genero"));
            user.setFechaNac(jsonObjectson.optString("FechaNac"));


            SharedPreferences.Editor editor = preferences.edit();
            editor.putString("Usuario", usuario.getText().toString());
            editor.putString("Contraseña", contraseña.getText().toString());
            editor.putString("TipoUsuario", user.getTipoUsuario());
            if(user.getTipoUsuario().equals("Cliente")){

                Intent intent = new Intent(getApplicationContext(), Saludo.class);
                startActivity(intent);
                overridePendingTransition(R.anim.zoom_back_in, R.anim.zoom_back_out);
                finish();
            }
            else if (user.getTipoUsuario().equals("Dueno")){
                Intent intent = new Intent(getApplicationContext(), MainDuenos.class);
                startActivity(intent);
                overridePendingTransition(R.anim.zoom_back_in, R.anim.zoom_back_out);
                finish();
            }

            editor.commit();
        }catch(JSONException j){
            j.printStackTrace();
        }
    }
}
