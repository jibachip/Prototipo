package com.example.android.prototipo;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TabHost;
import android.widget.Toast;

public class RegistroTab extends AppCompatActivity {

        TabHost tabHost;
        EditText correo,usuario,contraseña,confcontraseña,nombre,apPat,apMat,fechaNac,placas,nombreEst,rfc,precioHora,precioFrac, calle,numext,colonia,cp,ciudad,estado;
        Spinner genero,hrCierre,hrInic;
        RadioButton cliente,dueño;
       static SharedPreferences cuenta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_tab);

       correo=findViewById(R.id.etCorreo);
       usuario=findViewById(R.id.etUsuario);
       contraseña=findViewById(R.id.etContraseña);
       confcontraseña=findViewById(R.id.etConfContra);
       nombre=findViewById(R.id.etNombre);
       apPat=findViewById(R.id.etApPat);
       apMat=findViewById(R.id.etApMat);
       fechaNac=findViewById(R.id.etFechaN);
       genero=findViewById(R.id.spGenero);
       cliente=findViewById(R.id.rbCliente);
       dueño=findViewById(R.id.rbDueño);
        placas=findViewById(R.id.etMatricula);
        nombreEst=findViewById(R.id.etNombreEst);
        rfc=findViewById(R.id.etRFC);
        precioHora=findViewById(R.id.etPrecioHora);
        precioFrac=findViewById(R.id.etPrecioFracc);
        hrInic=findViewById(R.id.spHoraInic);
        hrCierre=findViewById(R.id.spHoraCierre);
        calle=findViewById(R.id.etCalle);
        numext=findViewById(R.id.etNumExt);
        colonia=findViewById(R.id.etColonia);
        cp=findViewById(R.id.etCP);
        ciudad=findViewById(R.id.etCiudad);
        estado=findViewById(R.id.etEstado);
        cuenta=getSharedPreferences("Datos cuenta", Context.MODE_PRIVATE);
        ArrayAdapter<CharSequence> adapter=ArrayAdapter.createFromResource(getApplicationContext(),R.array.Genero,R.layout.support_simple_spinner_dropdown_item);
        genero.setAdapter(adapter);

        ArrayAdapter <CharSequence> adapter1= ArrayAdapter.createFromResource(getApplicationContext(),R.array.HORARIO_MAÑANA,R.layout.support_simple_spinner_dropdown_item);
        hrInic.setAdapter(adapter1);
        ArrayAdapter <CharSequence>adapter2=ArrayAdapter.createFromResource(getApplicationContext(),R.array.HORARIO_TARDE,R.layout.support_simple_spinner_dropdown_item);
        hrCierre.setAdapter(adapter2);

    final Button btntab2=findViewById(R.id.p1p2);
    final Button btntab3=findViewById(R.id.p2p3);
    final Button btnRegisCliente=findViewById(R.id.btnRegCliente);
    final Button btnsigEst=findViewById(R.id.btnsigEst);
    final Button btnregisDueño=findViewById(R.id.btnRegisDueño);
        //Creacion tabhost
        tabHost=findViewById(R.id.tabhost);
        tabHost.setup();
        //Agrega la primera pestaña al TabHost
        TabHost.TabSpec spec=tabHost.newTabSpec("Tab One");
        spec.setContent(R.id.tab1);
        spec.setIndicator("Usuario");
        tabHost.addTab(spec);

        //Agrega segunda pestaña
        spec=tabHost.newTabSpec("Tab Two");
        spec.setContent(R.id.tab2);
        spec.setIndicator("Información Personal");
        tabHost.addTab(spec);

        //Agrega tercera pestaña.
        spec=tabHost.newTabSpec("Tab Three");
        spec.setContent(R.id.tab3);
        spec.setIndicator("Cliente");
        tabHost.addTab(spec);
//Agrega cuarta pestaña
        spec=tabHost.newTabSpec("Tab Four");
        spec.setContent(R.id.tab4);
        spec.setIndicator("Estacionamiento");
        tabHost.addTab(spec);
        //Agrega quinta pestaña
        spec=tabHost.newTabSpec("Tab Five");
        spec.setContent(R.id.tab5);
        spec.setIndicator("Ubicación");
        tabHost.addTab(spec);

        tabHost.getTabWidget().getChildTabViewAt(0).setEnabled(false);
        tabHost.getTabWidget().getChildTabViewAt(1).setVisibility(View.INVISIBLE);
        tabHost.getTabWidget().getChildTabViewAt(2).setVisibility(View.INVISIBLE);
        tabHost.getTabWidget().getChildTabViewAt(1).setEnabled(false);
        tabHost.getTabWidget().getChildTabViewAt(1).getLayoutParams().width=60;
        tabHost.getTabWidget().getChildTabViewAt(2).setEnabled(false);
        tabHost.getTabWidget().getChildTabViewAt(3).setVisibility(View.INVISIBLE);
        tabHost.getTabWidget().getChildTabViewAt(3).setEnabled(false);
        tabHost.getTabWidget().getChildTabViewAt(4).setEnabled(false);
        tabHost.getTabWidget().getChildTabViewAt(4).setVisibility(View.INVISIBLE);


        
        
        btntab2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            if (correo.getText().length()!=0 && usuario.getText().length()!=0 && contraseña.getText().length()!=0 &&
                    confcontraseña.getText().length()!=0){
                tabHost.getTabWidget().getChildTabViewAt(1).setVisibility(View.VISIBLE);
                tabHost.setCurrentTabByTag("Tab Two");


            }
            else{
                genToast("Faltan datos");
            }
            }
        });


        btntab3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (nombre.getText().length()!=0 && apPat.getText().length()!=0 && apMat.getText().length()!=0
                        && fechaNac.getText().length()!=0 && genero.getSelectedItem()!=null ){

                    if (cliente.isChecked()){
                    tabHost.getTabWidget().getChildTabViewAt(2).setVisibility(View.VISIBLE);
                    tabHost.setCurrentTabByTag("Tab Three");}
                    else if (dueño.isChecked()){
                        tabHost.getTabWidget().getChildTabViewAt(2).setVisibility(View.GONE);
                        tabHost.getTabWidget().getChildTabViewAt(3).setVisibility(View.VISIBLE);
                        tabHost.setCurrentTabByTag("Tab Four");
                    }
                }
                else{
                    genToast("Faltan datos");
                }
            }
        });

        btnRegisCliente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (placas.getText().length()!=0) {
                    SharedPreferences.Editor editor=cuenta.edit();
                    editor.putString("Usuario",usuario.getText().toString());
                    editor.putString("Contraseña",contraseña.getText().toString());
                    editor.commit();
                    Intent intent = new Intent(getApplicationContext(), MapsActivity.class);
                    startActivity(intent);
                }
                else{
                    genToast("Faltan datos");
                }
            }
        });

        btnsigEst.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (nombreEst.getText().length()!=0 &&rfc.getText().length()!=0 &&precioHora.getText().length()!=0 && precioFrac.getText().length()!=0
                        &&hrInic.getSelectedItem()!=null && hrCierre.getSelectedItem()!=null){
                    tabHost.setCurrentTabByTag("Tab Five");
                    tabHost.getTabWidget().getChildTabViewAt(2).setVisibility(View.GONE);
                    tabHost.getTabWidget().getChildTabViewAt(4).setVisibility(View.VISIBLE);
                }
                else{
                    genToast("Faltan datos");
                }
            }
        });

        btnregisDueño.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (calle.getText().length()!=0 && colonia.getText().length()!=0 && numext.getText().length()!=0 && cp.getText().length()!=0 &&
                        ciudad.getText().length()!=0 && estado.getText().length()!=0){
                    SharedPreferences.Editor editor=cuenta.edit();
                    editor.putString("Usuario",usuario.getText().toString());
                    editor.putString("Contraseña",contraseña.getText().toString());
                    editor.commit();
                    Intent intent=new Intent(getApplicationContext(),MainActivity.class);
                    startActivity(intent);
                }
                else{
                    genToast("Faltan Datos");
                }
            }
        });

    }


public void genToast(String mensaje){
        Toast.makeText(getApplicationContext(),mensaje,Toast.LENGTH_SHORT).show();
}



    @Override
    public void onBackPressed() {
    genToast(Integer.toString(tabHost.getCurrentTab()));
        int tab=tabHost.getCurrentTab();
        switch (tab){
            case 0:super.onBackPressed();break;
            case 1: tabHost.setCurrentTabByTag("Tab One");break;
            case 2:tabHost.setCurrentTabByTag("Tab Two");break;
            case 3:tabHost.setCurrentTabByTag("Tab Two");break;
            case 4:tabHost.setCurrentTabByTag("Tab Four");break;
        }
    }
}
