package com.example.android.prototipo.AccesoDatosWIP;

import android.content.Context;
import android.widget.ArrayAdapter;

import java.util.List;

public class EsquemasAdapter {

    public static class UsuariosAdapter extends ArrayAdapter<EsquemasBaseDeDatos.Usuarios>{
        private Context contextUsuarios;
        private int resourceUsuario;
        private List<EsquemasBaseDeDatos.Usuarios> objectUsuarios;

        public UsuariosAdapter(Context context,int resource, List<EsquemasBaseDeDatos.Usuarios> objects){
            super(context,resource,objects);
            this.contextUsuarios=context;
            this.resourceUsuario=resource;
            this.objectUsuarios=objects;
        }

        static class ViewHolder{

        }

    }

    public class Clientes{
    }

    public class Dueños{
    }

    public class Estacionamientos{
    }

    public class Claves{
    }

    public class Reservaciones{
    }

} 
