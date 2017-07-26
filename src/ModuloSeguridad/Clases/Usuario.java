/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ModuloSeguridad.Clases;

/**
 *
 * @author Jean Paul Mosquera Arevalo
 */
public class Usuario {
    //Usuario
    int idUsuario;
    String nombreUsuario;
    String ApellidoUsuario;
    String username;
    String password;
    String dni;
    //Rol
    int idRol;
    String nombreRol;
    String descripcionRol;
    //Funcionalidad
    int intFuncionalidad;
    String nombreFuncionalidad;
    String descripcionFuncionalidad;

    public Usuario(int idUsuario, String nombreUsuario, String ApellidoUsuario, String username, String password) {
        this.idUsuario = idUsuario;
        this.nombreUsuario = nombreUsuario;
        this.ApellidoUsuario = ApellidoUsuario;
        this.username = username;
        this.password = password;
    }

    public Usuario(int idRol, String nombreRol, String descripcionRol) {
        this.idRol = idRol;
        this.nombreRol = nombreRol;
        this.descripcionRol = descripcionRol;
    }



    public Usuario(int idUsuario, String nombreUsuario, String ApellidoUsuario, String username, String password, int idRol) {
        this.idUsuario = idUsuario;
        this.nombreUsuario = nombreUsuario;
        this.ApellidoUsuario = ApellidoUsuario;
        this.username = username;
        this.password = password;
        this.idRol = idRol;
    }

    public Usuario(int idRol, String nombreRol, String descripcionRol,int intFuncionalidad) {
        this.idRol = idRol;
        this.nombreRol = nombreRol;
        this.descripcionRol = descripcionRol;
        this.intFuncionalidad = intFuncionalidad;
    }

    public Usuario() {
      
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    
    
    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getApellidoUsuario() {
        return ApellidoUsuario;
    }

    public void setApellidoUsuario(String ApellidoUsuario) {
        this.ApellidoUsuario = ApellidoUsuario;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getIdRol() {
        return idRol;
    }

    public void setIdRol(int idRol) {
        this.idRol = idRol;
    }

    public String getNombreRol() {
        return nombreRol;
    }

    public void setNombreRol(String nombreRol) {
        this.nombreRol = nombreRol;
    }

    public String getDescripcionRol() {
        return descripcionRol;
    }

    public void setDescripcionRol(String descripcionRol) {
        this.descripcionRol = descripcionRol;
    }

    public int getIntFuncionalidad() {
        return intFuncionalidad;
    }

    public void setIntFuncionalidad(int intFuncionalidad) {
        this.intFuncionalidad = intFuncionalidad;
    }

    public String getNombreFuncionalidad() {
        return nombreFuncionalidad;
    }

    public void setNombreFuncionalidad(String nombreFuncionalidad) {
        this.nombreFuncionalidad = nombreFuncionalidad;
    }

    public String getDescripcionFuncionalidad() {
        return descripcionFuncionalidad;
    }

    public void setDescripcionFuncionalidad(String descripcionFuncionalidad) {
        this.descripcionFuncionalidad = descripcionFuncionalidad;
    }
    
    
}
