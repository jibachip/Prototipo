package com.example.android.prototipo;

import android.content.ContentValues;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import java.util.ArrayList;
import java.util.Date;

public class MainDuenos extends AppCompatActivity {
    String cadena,registro;
    TextView tvPat,tvMat,tvHora;
    SQLiteDatabase db;
    SQLHelper sqlHelper;
    Clientes clientes;
    static ArrayList<Clientes> listaClientes;
    Intent intent;
    Date date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_duenos);
        //estable solo la pantalla en modo vertical
        //setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR_PORTRAIT);

        tvPat=findViewById(R.id.tvPat);
        tvMat=findViewById(R.id.tvMat);
        tvHora=findViewById(R.id.tvHora);
        Button btnScan=findViewById(R.id.btnScan);
        Button btnCons=findViewById(R.id.btnCons);
        cadena="";
        registro="";
        sqlHelper =new SQLHelper(this,"EstacionamientoApartar",null,1);//creamos la BD
        listaClientes =new ArrayList<>();
        intent = new Intent(this, ListaClientes.class);

        btnScan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                IntentIntegrator intentIntegrator=new IntentIntegrator(MainDuenos.this);
                intentIntegrator.initiateScan(IntentIntegrator.ALL_CODE_TYPES);
                registro="insertar";
                date=new Date();

            }
        });
        btnCons.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                leerBd();
                startActivity(intent);
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        IntentResult intentResult=IntentIntegrator.parseActivityResult(requestCode,resultCode,data);
        if(intentResult!=null) {
            if (intentResult.getContents() == null) {
                Toast.makeText(this, "cancelado", Toast.LENGTH_LONG).show();
            } else {
                cadena = intentResult.getContents();
                String[] cad = cadena.split(",");
                tvPat.setText("idCliente: "+cad[0]);
                tvMat.setText("idEstacionamiento: "+cad[1]);
                tvHora.setText("Hora de entrada: "+cad[2]);

                switch (registro){
                    case "insertar":{
                        insertarbd();
                        //tvHora.setText(hora.format(date));
                    }break;
                    case "buscar":{
                        if(buscar(cad[0])) {
                            //   tvHora.setText(hora.format(date));
                        }else{
                            Toast.makeText(getApplicationContext(),"BD Vacia",Toast.LENGTH_LONG).show();
                        }


                    }break;

                }

            }
        }
    }

    private void insertarbd(){
        ContentValues values=new ContentValues();
        db=sqlHelper.getWritableDatabase(); //asignamos la BD a db en modo escritura
        values.put("idClave",tvPat.getText().toString());
        values.put("idEstacionamiento",tvMat.getText().toString());
        values.put("hora",tvHora.getText().toString());
        db.insert("estacionamiento",null,values);
        db.close();
    }

    private boolean buscar(String pat){
        String cadTemp="";
        boolean ban=false;
        db=sqlHelper.getReadableDatabase();//asignamos la BD a db en modo lectura
        Cursor cursor=db.rawQuery("select idClave from estacionamiento",null);

        if(cursor.moveToFirst()){
            do{
                cadTemp=cursor.getString(0);
                if(cadTemp.equals(pat)){
                    ban=true;
                    Toast.makeText(getApplicationContext(),"Encontrado",Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(getApplicationContext()," NO Encontrado",Toast.LENGTH_LONG).show();
                }
            }while (cursor.moveToNext());
            db.close();

        }
        return ban;
    }
    private void leerBd() {
        db=sqlHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from estacionamiento", null);
        if (cursor.moveToFirst()){
            do {
                clientes =new Clientes(cursor.getString(0),cursor.getString(1),
                        cursor.getString(2));
                listaClientes.add(clientes);
            } while (cursor.moveToNext());
            db.close();
        }
    }
}
