package com.example.android.prototipo;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import static com.example.android.prototipo.MainDuenos.listaClientes;

public class ListaClientes extends AppCompatActivity {
    ListView lvLista;
    SQLHelper sqlHelper;
    SQLiteDatabase db;
    ClientesArrayAdapter adapter;
    Clientes clientes;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_clientes);
        lvLista=(ListView) findViewById(R.id.lvLista);
        sqlHelper=new SQLHelper(this,"EstacionamientoApartar",null,1);
        adapter=new ClientesArrayAdapter(this,R.layout.activity_datos_cliente,listaClientes);
        lvLista.setAdapter(adapter);
        lvLista.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                String pat=((TextView) view.findViewById(R.id.txtPat)).getText().toString();
                borrarRegistro();
                Toast.makeText(ListaClientes.this,"Registro eliminado",Toast.LENGTH_LONG).show();
                actualizaLista();
                return false;
            }
        });
    }
    public void actualizaLista(){
        adapter.clear();
        db=sqlHelper.getReadableDatabase();
        Cursor cursor=db.rawQuery("select * from estacionamiento", null);
        if(cursor.moveToFirst()){
            do{
                clientes =new Clientes(cursor.getString(0),cursor.getString(1),
                        cursor.getString(2));
                adapter.add(clientes);
            }while (cursor.moveToNext());
            db.close();
            lvLista.setAdapter(adapter);
        }

    }
    private void borrarRegistro() {
        db=sqlHelper.getWritableDatabase();
        Cursor cursor=db.rawQuery("select idClave from estacionamiento",null);
        cursor.moveToFirst();
        String clave=cursor.getString(0);
        db.delete("estacionamiento", "idClave="+"'"+clave+"'", null);
        db.close();
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        adapter.clear();
    }
}
