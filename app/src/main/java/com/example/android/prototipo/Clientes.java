package com.example.android.prototipo;

public class Clientes {
    String idClave;
    String idEstacionamiento;
    String hora;

    public Clientes(String c, String e,String hora) {
        this.idClave = c;
        this.idEstacionamiento = e;
        this.hora = hora;
    }

    public String getIdClave() {
        return idClave;
    }

    public void setIdClave(String idClave) {
        this.idClave = idClave;
    }

    public String getIdEstacionamiento() {
        return idEstacionamiento;
    }

    public void setIdEstacionamiento(String idEstacionamiento) {
        this.idEstacionamiento = idEstacionamiento;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }
} 
