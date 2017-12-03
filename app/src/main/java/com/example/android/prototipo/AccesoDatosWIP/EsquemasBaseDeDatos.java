package com.example.android.prototipo.AccesoDatosWIP;



public class EsquemasBaseDeDatos {

    public class Usuarios{
        private int idUsuario;
        private String tipoUsuario;
        private String usuario;
        private String contrasena;
        private String email;
        private String nombres;
        private String apPaterno;
        private String apMaterno;
        private String genero;
        private String fechaNac;

        public Usuarios(int idUsuario, String tipoUsuario, String usuario,
                        String contrasena, String email, String nombres,
                        String apPaterno, String apMaterno,
                        String genero, String fechaNac) {
            this.idUsuario = idUsuario;
            this.tipoUsuario = tipoUsuario;
            this.usuario = usuario;
            this.contrasena = contrasena;
            this.email = email;
            this.nombres = nombres;
            this.apPaterno = apPaterno;
            this.apMaterno = apMaterno;
            this.genero = genero;
            this.fechaNac = fechaNac;
        }

        public int getIdUsuario() {
            return idUsuario;
        }

        public void setIdUsuario(int idUsuario) {
            this.idUsuario = idUsuario;
        }

        public String getTipoUsuario() {
            return tipoUsuario;
        }

        public void setTipoUsuario(String tipoUsuario) {
            this.tipoUsuario = tipoUsuario;
        }

        public String getUsuario() {
            return usuario;
        }

        public void setUsuario(String usuario) {
            this.usuario = usuario;
        }

        public String getContrasena() {
            return contrasena;
        }

        public void setContrasena(String contrasena) {
            this.contrasena = contrasena;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getNombres() {
            return nombres;
        }

        public void setNombres(String nombres) {
            this.nombres = nombres;
        }

        public String getApPaterno() {
            return apPaterno;
        }

        public void setApPaterno(String apPaterno) {
            this.apPaterno = apPaterno;
        }

        public String getApMaterno() {
            return apMaterno;
        }

        public void setApMaterno(String apMaterno) {
            this.apMaterno = apMaterno;
        }

        public String getGenero() {
            return genero;
        }

        public void setGenero(String genero) {
            this.genero = genero;
        }

        public String getFechaNac() {
            return fechaNac;
        }

        public void setFechaNac(String fechaNac) {
            this.fechaNac = fechaNac;
        }
    }

    public class Clientes{
        private int	idCliente;
        private int idUsuario;
        private String TipoCuenta;
        private String Matricula;
        private String Marca;
        private String Modelo;

        public Clientes(int idCliente, int idUsuario, String tipoCuenta,
                        String matricula, String marca, String modelo) {
            this.idCliente = idCliente;
            this.idUsuario = idUsuario;
            TipoCuenta = tipoCuenta;
            Matricula = matricula;
            Marca = marca;
            Modelo = modelo;
        }

        public int getIdCliente() {
            return idCliente;
        }

        public void setIdCliente(int idCliente) {
            this.idCliente = idCliente;
        }

        public int getIdUsuario() {
            return idUsuario;
        }

        public void setIdUsuario(int idUsuario) {
            this.idUsuario = idUsuario;
        }

        public String getTipoCuenta() {
            return TipoCuenta;
        }

        public void setTipoCuenta(String tipoCuenta) {
            TipoCuenta = tipoCuenta;
        }

        public String getMatricula() {
            return Matricula;
        }

        public void setMatricula(String matricula) {
            Matricula = matricula;
        }

        public String getMarca() {
            return Marca;
        }

        public void setMarca(String marca) {
            Marca = marca;
        }

        public String getModelo() {
            return Modelo;
        }

        public void setModelo(String modelo) {
            Modelo = modelo;
        }

    }

    public class Dueños{
        private int	idDueño;
        private int idUsuario;
        private String RFC;
        private String Telefno;

        public Dueños(int idDueño, int idUsuario, String RFC, String telefno) {
            this.idDueño = idDueño;
            this.idUsuario = idUsuario;
            this.RFC = RFC;
            Telefno = telefno;
        }

        public int getIdDueño() {
            return idDueño;
        }

        public void setIdDueño(int idDueño) {
            this.idDueño = idDueño;
        }

        public int getIdUsuario() {
            return idUsuario;
        }

        public void setIdUsuario(int idUsuario) {
            this.idUsuario = idUsuario;
        }

        public String getRFC() {
            return RFC;
        }

        public void setRFC(String RFC) {
            this.RFC = RFC;
        }

        public String getTelefno() {
            return Telefno;
        }

        public void setTelefno(String telefno) {
            Telefno = telefno;
        }
    }

    public class Estacionamientos{
        private int	idEstacionamiento;
        private int idDueño;
        private String nombre;
        private String calle;
        private String numInt;
        private String numExt;
        private String colonia;
        private String codigoPostal;
        private String ciudad;
        private String estado;
        private String horaIniServicio;
        private String horaFinServicio;
        private String precioServicio;

        public Estacionamientos(int idEstacionamiento, int idDueño, String nombre,
                                String calle, String numInt, String numExt, String colonia,
                                String codigoPostal, String ciudad, String estado,
                                String horaIniServicio, String horaFinServicio, String precioServicio) {
            this.idEstacionamiento = idEstacionamiento;
            this.idDueño = idDueño;
            this.nombre = nombre;
            this.calle = calle;
            this.numInt = numInt;
            this.numExt = numExt;
            this.colonia = colonia;
            this.codigoPostal = codigoPostal;
            this.ciudad = ciudad;
            this.estado = estado;
            this.horaIniServicio = horaIniServicio;
            this.horaFinServicio = horaFinServicio;
            this.precioServicio = precioServicio;
        }

        public int getIdEstacionamiento() {
            return idEstacionamiento;
        }

        public void setIdEstacionamiento(int idEstacionamiento) {
            this.idEstacionamiento = idEstacionamiento;
        }

        public int getIdDueño() {
            return idDueño;
        }

        public void setIdDueño(int idDueño) {
            this.idDueño = idDueño;
        }

        public String getNombre() {
            return nombre;
        }

        public void setNombre(String nombre) {
            this.nombre = nombre;
        }

        public String getCalle() {
            return calle;
        }

        public void setCalle(String calle) {
            this.calle = calle;
        }

        public String getNumInt() {
            return numInt;
        }

        public void setNumInt(String numInt) {
            this.numInt = numInt;
        }

        public String getNumExt() {
            return numExt;
        }

        public void setNumExt(String numExt) {
            this.numExt = numExt;
        }

        public String getColonia() {
            return colonia;
        }

        public void setColonia(String colonia) {
            this.colonia = colonia;
        }

        public String getCodigoPostal() {
            return codigoPostal;
        }

        public void setCodigoPostal(String codigoPostal) {
            this.codigoPostal = codigoPostal;
        }

        public String getCiudad() {
            return ciudad;
        }

        public void setCiudad(String ciudad) {
            this.ciudad = ciudad;
        }

        public String getEstado() {
            return estado;
        }

        public void setEstado(String estado) {
            this.estado = estado;
        }

        public String getHoraIniServicio() {
            return horaIniServicio;
        }

        public void setHoraIniServicio(String horaIniServicio) {
            this.horaIniServicio = horaIniServicio;
        }

        public String getHoraFinServicio() {
            return horaFinServicio;
        }

        public void setHoraFinServicio(String horaFinServicio) {
            this.horaFinServicio = horaFinServicio;
        }

        public String getPrecioServicio() {
            return precioServicio;
        }

        public void setPrecioServicio(String precioServicio) {
            this.precioServicio = precioServicio;
        }
    }

    public class Claves{
        private int	idClave;
        private int idEstacionamiento;
        private String clave;

        public Claves(int idClave, int idEstacionamiento, String clave) {
            this.idClave = idClave;
            this.idEstacionamiento = idEstacionamiento;
            this.clave = clave;
        }

        public int getIdClave() {
            return idClave;
        }

        public void setIdClave(int idClave) {
            this.idClave = idClave;
        }

        public int getIdEstacionamiento() {
            return idEstacionamiento;
        }

        public void setIdEstacionamiento(int idEstacionamiento) {
            this.idEstacionamiento = idEstacionamiento;
        }

        public String getClave() {
            return clave;
        }

        public void setClave(String clave) {
            this.clave = clave;
        }
    }

    public class Reservaciones{
        private int	idReservacion;
        private int idEstacionamiento;
        private int idCliente;
        private String calle;
        private String horaRegReserva;
        private String horaEntrada;
        private String horaSalida;

        public Reservaciones(int idReservacion, int idEstacionamiento,
                             int idCliente, String calle,
                             String horaRegReserva, String horaEntrada,
                             String horaSalida) {
            this.idReservacion = idReservacion;
            this.idEstacionamiento = idEstacionamiento;
            this.idCliente = idCliente;
            this.calle = calle;
            this.horaRegReserva = horaRegReserva;
            this.horaEntrada = horaEntrada;
            this.horaSalida = horaSalida;
        }

        public int getIdReservacion() {
            return idReservacion;
        }

        public void setIdReservacion(int idReservacion) {
            this.idReservacion = idReservacion;
        }

        public int getIdEstacionamiento() {
            return idEstacionamiento;
        }

        public void setIdEstacionamiento(int idEstacionamiento) {
            this.idEstacionamiento = idEstacionamiento;
        }

        public int getIdCliente() {
            return idCliente;
        }

        public void setIdCliente(int idCliente) {
            this.idCliente = idCliente;
        }

        public String getCalle() {
            return calle;
        }

        public void setCalle(String calle) {
            this.calle = calle;
        }

        public String getHoraRegReserva() {
            return horaRegReserva;
        }

        public void setHoraRegReserva(String horaRegReserva) {
            this.horaRegReserva = horaRegReserva;
        }

        public String getHoraEntrada() {
            return horaEntrada;
        }

        public void setHoraEntrada(String horaEntrada) {
            this.horaEntrada = horaEntrada;
        }

        public String getHoraSalida() {
            return horaSalida;
        }

        public void setHoraSalida(String horaSalida) {
            this.horaSalida = horaSalida;
        }
    }

} 
