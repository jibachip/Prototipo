package com.example.android.prototipo;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TabHost;
import android.widget.Toast;

import com.example.android.prototipo.AccesoDatosWIP.Constantes;
import com.example.android.prototipo.AccesoDatosWIP.Llamadas;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class RegistroTab extends AppCompatActivity implements View.OnClickListener {


        SQLiteDatabase db;

        TabHost tabHost;
        EditText
                etUsuario,
                etContrasena,
                etConfContrasena,
                etCorreo,
                etNombres,
                etApPaterno,
                etApMaterno,
                etFechaNac,
                etClave,
                etPlacas,
                etNombreEst,
                etRfc,
                etPrecioHora,
                etPrecioFrac,
                etCalle,
                etNumExt,
                etColonia,
                etCodigoPostal,
                etCiudad,
                etEstado;

        Spinner spGenero, spHrCierre, spHrInic;
        RadioButton rbCliente, rbDueño, rbRegular, rbPremium;
        private int bdIdUsuario;
        private String bdUsuario;
        private String bdContrasena;
        private String bdCorreo;

        private String bdNombres;
        private String bdApPaterno;
        private String bdApMaterno;

        private String bdGenero;
        private String bdFechaNac;
        private String bdTipoUsuario;
        private String bdClave;

        private String bdMatricula;
        private String bdTipoCliente;

        private String bdNombreEst;
        private String bdRFC;
        private String bdHoraInicio;
        private String bdHoraFin;
        private double bdPrecio;

        private String bdCalle;
        private String bdNumExt;
        private String bdColonia;
        private String bdCodigoPostal;
        private String bdCiudad;
        private String bdEstado;
    private DatePickerDialog BornDatePickerDialog;
    private SimpleDateFormat dateFormatter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_tab);
        //INICIO Datos generales del usuario
        //===========================
        etUsuario =findViewById(R.id.etUsuario);
        etContrasena =findViewById(R.id.etContrasena);
        etConfContrasena =findViewById(R.id.etConfContra);
        etCorreo =findViewById(R.id.etCorreo);
        etNombres =findViewById(R.id.etNombre);
        etApPaterno =findViewById(R.id.etApPat);
        etApMaterno =findViewById(R.id.etApMat);
        etFechaNac =findViewById(R.id.etFechaN);
        etFechaNac.setInputType(InputType.TYPE_NULL);
        etFechaNac.requestFocus();

        setDateTimeField();
        spGenero =findViewById(R.id.spGenero);
        rbCliente =findViewById(R.id.rbCliente);
        rbDueño =findViewById(R.id.rbDueño);
        //FIN Datos generales del usuario
        //===========================
        //INICIO Datos especificos del usuario
        //===========================
        etPlacas =findViewById(R.id.etMatricula);
        etClave=findViewById(R.id.etClave);
        etNombreEst =findViewById(R.id.etNombreEst);
        etRfc =findViewById(R.id.etRFC);
        rbRegular=findViewById(R.id.rbRegu);
        rbPremium=findViewById(R.id.rbPremium);
        //FIN Datos especificos del usuario
        //===========================
        //INICIO Datos generales del estacionamiento
        //===========================
        etPrecioHora =findViewById(R.id.etPrecioHora);
        etPrecioFrac =findViewById(R.id.etPrecioFracc);
        spHrInic =findViewById(R.id.spHoraInic);
        spHrCierre =findViewById(R.id.spHoraCierre);
        etCalle =findViewById(R.id.etCalle);
        etNumExt =findViewById(R.id.etNumExt);
        etColonia =findViewById(R.id.etColonia);
        etCodigoPostal =findViewById(R.id.etCP);
        etCiudad =findViewById(R.id.etCiudad);
        etEstado =findViewById(R.id.etEstado);

        dateFormatter = new SimpleDateFormat("dd-MM-yyyy", Locale.US);
        //ESTABLECE LOS VALORES CORRESPONDIENTES A LOS COMPONENTES SPINNER
        ArrayAdapter<CharSequence> adapter=ArrayAdapter.createFromResource(getApplicationContext(),R.array.Genero,R.layout.support_simple_spinner_dropdown_item);
        spGenero.setAdapter(adapter);
        ArrayAdapter <CharSequence> adapter1= ArrayAdapter.createFromResource(getApplicationContext(),R.array.HORARIO_MAÑANA,R.layout.support_simple_spinner_dropdown_item);
        spHrInic.setAdapter(adapter1);
        ArrayAdapter <CharSequence>adapter2=ArrayAdapter.createFromResource(getApplicationContext(),R.array.HORARIO_MAÑANA,R.layout.support_simple_spinner_dropdown_item);
        spHrCierre.setAdapter(adapter2);

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
            if (    etCorreo.getText().length()!=0 &&
                    etUsuario.getText().length()!=0 &&
                    etContrasena.getText().length()!=0 &&
                    etConfContrasena.getText().length()!=0){

                    if(etContrasena.getText().length()>=8){
                        if(etContrasena.getText().toString().equals(etConfContrasena.getText().toString())){
                            bdUsuario=etUsuario.getText().toString();
                            bdContrasena=etContrasena.getText().toString();
                            bdCorreo=etCorreo.getText().toString();
                            tabHost.getTabWidget().getChildTabViewAt(1).setVisibility(View.VISIBLE);
                            tabHost.setCurrentTabByTag("Tab Two");
                        }else{
                            genToast("Las contraseñas ingresadas no coinciden");
                        }
                    }else{
                        genToast("La contraseña debe tener más de 8 caracteres");
                    }
            }
            else{
                genToast("Es necesario llenar todos los campos");
            }
            }
        });


        rbCliente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                etClave.setVisibility(View.INVISIBLE);
            }
        });

        rbDueño.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                etClave.setVisibility(View.VISIBLE);
            }
        });

        btntab3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (    etNombres.getText().length()!=0 &&
                        etApPaterno.getText().length()!=0 &&
                        etApMaterno.getText().length()!=0 &&
                        etFechaNac.getText().length()!=0 &&
                        spGenero.getSelectedItem()!=null ){
                    bdNombres=etNombres.getText().toString();
                    bdApPaterno=etApPaterno.getText().toString();
                    bdApMaterno=etApMaterno.getText().toString();
                    bdFechaNac=etFechaNac.getText().toString();
                    bdGenero=spGenero.getSelectedItem().toString();
                    if (rbCliente.isChecked()){
                        bdTipoUsuario=rbCliente.getText().toString();
                        tabHost.getTabWidget().getChildTabViewAt(2).setVisibility(View.VISIBLE);
                        tabHost.setCurrentTabByTag("Tab Three");
                    }
                    else if (rbDueño.isChecked()){
                        if(etClave.getText().length()==10){
                            bdTipoUsuario=rbDueño.getText().toString();
                            bdClave=etClave.getText().toString();
                            tabHost.getTabWidget().getChildTabViewAt(2).setVisibility(View.GONE);
                            tabHost.getTabWidget().getChildTabViewAt(3).setVisibility(View.VISIBLE);
                            tabHost.setCurrentTabByTag("Tab Four");
                        }else{
                            genToast("Ingrese la clave que se le proporciono para su estacionamiento");
                        }
                    }
                }
                else{
                    genToast("Es necesario llenar todos los campos");
                }
            }
        });

        btnRegisCliente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (etPlacas.getText().length()!=0) {
                    bdMatricula=etPlacas.getText().toString();
                    if(rbRegular.isChecked()){
                        bdTipoCliente="REGULAR";
                    }
                    else if(rbPremium.isChecked()){
                        bdTipoCliente="PREMIUM";
                    }
                    Llamadas llamadas=new Llamadas(RegistroTab.this);
                    llamadas.execute(
                            Constantes.Insertar,
                            Constantes.Usuarios,
                            Constantes.Clientes,
                            bdTipoUsuario,
                            bdUsuario,
                            bdContrasena,
                            bdCorreo,
                            bdNombres,
                            bdApPaterno,
                            bdApMaterno,
                            bdFechaNac,
                            bdGenero,
                            bdTipoCliente,
                            bdMatricula);
                    Intent intent = new Intent(getApplicationContext(), MapsActivity.class);
                    startActivity(intent);
                }
                else{
                    genToast("Es necesario llenar todos los campos");
                }
            }
        });

        btnsigEst.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (etNombreEst.getText().length()!=0 &&
                        etRfc.getText().length()!=0 &&
                        etPrecioHora.getText().length()!=0 &&
                        etPrecioFrac.getText().length()!=0 &&
                        spHrInic.getSelectedItem()!=null &&
                        spHrCierre.getSelectedItem()!=null){
                    bdNombreEst=etNombreEst.getText().toString();
                    bdRFC=etRfc.getText().toString();
                    bdPrecio=Double.parseDouble(etPrecioHora.getText().toString());
                    bdHoraInicio=spHrInic.getSelectedItem().toString();
                    bdHoraFin=spHrCierre.getSelectedItem().toString();
                    tabHost.setCurrentTabByTag("Tab Five");
                    tabHost.getTabWidget().getChildTabViewAt(2).setVisibility(View.GONE);
                    tabHost.getTabWidget().getChildTabViewAt(4).setVisibility(View.VISIBLE);
                }
                else{
                    genToast("Es necesario llenar todos los campos");
                }
            }
        });

        btnregisDueño.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (etCalle.getText().length()!=0 &&
                        etColonia.getText().length()!=0 &&
                        etNumExt.getText().length()!=0 &&
                        etCodigoPostal.getText().length()!=0 &&
                        etCiudad.getText().length()!=0 &&
                        etEstado.getText().length()!=0){
                    bdCalle=etCalle.getText().toString();
                    bdNumExt=etNumExt.getText().toString();
                    bdColonia=etColonia.getText().toString();
                    bdCodigoPostal=etCodigoPostal.getText().toString();
                    bdCiudad=etCiudad.getText().toString();
                    bdEstado=etEstado.getText().toString();

                    Llamadas llamadas=new Llamadas(RegistroTab.this);
                    llamadas.execute(
                            Constantes.Insertar,
                            Constantes.Usuarios,
                            Constantes.Dueños,
                            bdTipoUsuario,
                            bdUsuario,
                            bdContrasena,
                            bdCorreo,
                            bdNombres,
                            bdApPaterno,
                            bdApMaterno,
                            bdFechaNac,
                            bdGenero,
                            bdClave,
                            bdNombreEst,
                            bdRFC,
                            bdHoraInicio,
                            bdHoraFin,
                            String.valueOf(bdPrecio),
                            bdCalle,
                            bdNumExt,
                            bdColonia,
                            bdCodigoPostal,
                            bdCiudad,
                            bdEstado);


                    Intent intent=new Intent(getApplicationContext(),MapsActivity.class);
                    startActivity(intent);
                }
                else{
                    genToast("Es necesario llenar todos los campos");
                }
            }
        });

    }


    public void genToast(String mensaje){
            Toast.makeText(getApplicationContext(),mensaje,Toast.LENGTH_SHORT).show();
    }

    private void setDateTimeField(){
        etFechaNac.setOnClickListener(this);

        Calendar newCalendar = Calendar.getInstance();
        BornDatePickerDialog= new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {

            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);
                etFechaNac.setText(dateFormatter.format(newDate.getTime()));
            }

        },newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_tab, menu);
        return true;
    }

    @Override
    public void onClick(View view) {
        if(view == etFechaNac) {
            BornDatePickerDialog.show();
        }
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
