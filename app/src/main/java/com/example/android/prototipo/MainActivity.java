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

public class MainActivity extends AppCompatActivity {

    SharedPreferences preferences;
    EditText usuario,contraseña;
    RegistroTab registroTab;
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
        preferences=getSharedPreferences("Datos cuenta",MODE_PRIVATE);
        boolean b=preferences.contains("Usuario");

        if (b){
            usuario.setText(preferences.getString("Usuario","User"));
            contraseña.setText(preferences.getString("Contraseña","Pass"));
            Intent intent=new Intent(getApplicationContext(),Saludo.class);
            startActivity(intent);
        }

        registrarse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(getApplicationContext(), RegistroTab.class);
                startActivity(intent);
            }
        });
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (usuario.getText().length()==0 || contraseña.getText().length()==0){
                    registroTab.genToast("Faltan datos");
                }
                else {
                    //metodo para checar datos en la bd.
                        SharedPreferences.Editor editor = preferences.edit();
                        editor.putString("Usuario", usuario.getText().toString());
                        editor.putString("Contraseña", contraseña.getText().toString());
                        editor.commit();
                        Intent intent = new Intent(getApplicationContext(), Saludo.class);
                        startActivity(intent);
                }
            }
        });
    }


}
