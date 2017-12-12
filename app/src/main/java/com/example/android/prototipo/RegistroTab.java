package com.example.android.prototipo;

import android.app.DatePickerDialog;
import android.content.Intent;
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

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.android.prototipo.AccesoDatosWIP.Constantes;

import org.json.JSONException;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class RegistroTab extends AppCompatActivity implements View.OnClickListener {



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
                etLugares,
                etCalle,
                etNumExt,
                etColonia,
                etCodigoPostal,
                etCiudad,
                etEstado;

        RequestQueue rq;
        JsonRequest jrq;

        Spinner spGenero, spHrCierre, spHrInic;
        RadioButton rbCliente, rbDueño, rbRegular, rbPremium;

        HashMap<String, String> params = new HashMap<String, String>();
        //JSONObject params=new JSONObject();
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
        private String bdLugaresDisp;
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
        etLugares =findViewById(R.id.etLugares);
        spHrInic =findViewById(R.id.spHoraInic);
        spHrCierre =findViewById(R.id.spHoraCierre);
        etCalle =findViewById(R.id.etCalle);
        etNumExt =findViewById(R.id.etNumExt);
        etColonia =findViewById(R.id.etColonia);
        etCodigoPostal =findViewById(R.id.etCP);
        etCiudad =findViewById(R.id.etCiudad);
        etEstado =findViewById(R.id.etEstado);


        rq= Volley.newRequestQueue(getApplicationContext());


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
                            overridePendingTransition(R.anim.left_in, R.anim.left_out);
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
                        bdTipoUsuario ="Cliente";
                        tabHost.getTabWidget().getChildTabViewAt(2).setVisibility(View.VISIBLE);
                        tabHost.setCurrentTabByTag("Tab Three");
                        overridePendingTransition(R.anim.left_in, R.anim.left_out);
                    }
                    else if (rbDueño.isChecked()){
                        if(etClave.getText().length()==10){
                            bdTipoUsuario ="Dueno";
                            bdClave=etClave.getText().toString();
                            tabHost.getTabWidget().getChildTabViewAt(2).setVisibility(View.GONE);
                            tabHost.getTabWidget().getChildTabViewAt(3).setVisibility(View.VISIBLE);
                            tabHost.setCurrentTabByTag("Tab Four");
                            overridePendingTransition(R.anim.left_in, R.anim.left_out);
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
                    try {
                        RegistroUsuario(Constantes.Clientes);
                    } catch (JSONException e) {

                    }
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
                        etLugares.getText().length()!=0 &&
                        spHrInic.getSelectedItem()!=null &&
                        spHrCierre.getSelectedItem()!=null){
                    bdNombreEst=etNombreEst.getText().toString();
                    bdRFC=etRfc.getText().toString();
                    bdPrecio=Double.parseDouble(etPrecioHora.getText().toString());
                    bdLugaresDisp=etLugares.getText().toString();
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

                    try {
                        RegistroUsuario(Constantes.Dueños);
                    } catch (JSONException e) {

                    }

                }
                else{
                    genToast("Es necesario llenar todos los campos");
                }
            }
        });

    }

    private void RegistroUsuario(String userType) throws JSONException {
        String url = "";
        switch (userType){
            case "clientes":
                url = "http://192.168.0.17:8080/base/wipRegistroCliente.php";
                StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                Intent intent=new Intent(getApplicationContext(),Saludo.class);
                                startActivity(intent);
                                overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                                finish();
                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        genToast("Error al registrar en la base de datos: \n"+error.getMessage());
                    }
                }) {
                    //adding parameters to the request
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String, String> params = new HashMap<>();
                        params.put("TipoUsuario", bdTipoUsuario);
                        params.put("Usuario", bdUsuario);
                        params.put("Contrasena",bdContrasena);
                        params.put("Email",bdCorreo);
                        params.put("Nombres",bdNombres);
                        params.put("ApPaterno",bdApPaterno);
                        params.put("ApMaterno",bdApMaterno);
                        params.put("Genero",bdGenero);
                        params.put("FechaNac",bdFechaNac);
                        params.put("Matricula",bdMatricula);
                        params.put("TipoCuenta",bdTipoCliente);
                        return params;
                    }
                };

                rq.add(stringRequest);
                break;
            case "duenos":
                url = "http://192.168.0.17:8080/base/wipRegistroDueno.php";
                StringRequest stringRequestDueno = new StringRequest(Request.Method.POST, url,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                Intent intent=new Intent(getApplicationContext(),MapsActivity.class);
                                startActivity(intent);
                                overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                                finish();
                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        genToast("Error al registrar en la base de datos: \n"+error.getMessage());
                    }
                }) {
                    //adding parameters to the request
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String, String> params = new HashMap<>();
                        params.put("TipoUsuario", bdTipoUsuario);
                        params.put("Usuario", bdUsuario);
                        params.put("Contrasena",bdContrasena);
                        params.put("Email",bdCorreo);
                        params.put("Nombres",bdNombres);
                        params.put("ApPaterno",bdApPaterno);
                        params.put("ApMaterno",bdApMaterno);
                        params.put("Genero",bdGenero);
                        params.put("FechaNac",bdFechaNac);
                        params.put("RFC",bdRFC);
                        params.put("NombreEst",bdNombreEst);
                        params.put("Calle",bdCalle);
                        params.put("NumExt",bdNumExt);
                        params.put("Colonia",bdColonia);
                        params.put("CodigoPostal",bdCodigoPostal);
                        params.put("Ciudad",bdCiudad);
                        params.put("Estado",bdEstado);
                        params.put("HoraInicio",bdHoraInicio);
                        params.put("HoraFin",bdHoraFin);
                        params.put("PrecioServicio",String.valueOf(bdPrecio));
                        params.put("LugaresDisp",bdLugaresDisp);
                        params.put("Clave",bdClave);
                        return params;
                    }
                };

                rq.add(stringRequestDueno);
                break;
        }
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
