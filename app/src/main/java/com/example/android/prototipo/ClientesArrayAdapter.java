package com.example.android.prototipo;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class ClientesArrayAdapter extends ArrayAdapter<Clientes> {
    Context context;
    int resorce;
    List<Clientes> objects;

    public ClientesArrayAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull List<Clientes> objects) {
        super(context, resource, objects);
        this.context=context;
        this.resorce=resource;
        this.objects=objects;
    }
    static class ParaVista{
        TextView txtidC;
        TextView txtidE;
        TextView txtHora;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if(convertView==null){
            convertView= LayoutInflater.from(context).inflate(resorce,null);
            ParaVista paraVista=new ParaVista();
            paraVista.txtidC=(TextView) convertView.findViewById(R.id.txtPat);
            paraVista.txtidE=(TextView) convertView.findViewById(R.id.txtMat);
            paraVista.txtHora=(TextView) convertView.findViewById(R.id.txtHora);
            convertView.setTag(paraVista);

        }
        ParaVista paraVista=(ParaVista) convertView.getTag();
        paraVista.txtidC.setText(objects.get(position).idClave);
        paraVista.txtidE.setText(objects.get(position).idEstacionamiento);
        paraVista.txtHora.setText(objects.get(position).hora);

        return convertView;
    }
} 
