/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ModuloSeguridad.BL;

import ModuloSeguridad.Clases.Usuario;
import ModuloSeguridad.DAT.DATUsuario;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Jean Paul Mosquera Arevalo
 */
public class BLUsuarios {
    DATUsuario mp = new DATUsuario();
     public static ArrayList lstFuncionalidades;
     public static ArrayList lstRoles;
     public static ArrayList lstUsuario;
     public static ArrayList lstUsRol;
     public static ArrayList lstRolFun;
     public static ArrayList lstCredenciales;
     public int InsertarUsuario(String dni,String Nombre, String Apellido, String user, String pass){
         return mp.InsertarUsuario(dni,Nombre, Apellido, user, pass);
}
    public int InsertarRol(String Nombre, String Descripcion){
        
         return mp.InsertarRol(Nombre, Descripcion);
}
    public int InsertarFuncionalidad(String Nombre, String Descripcion){
         return mp.InsertarFuncionalidad(Nombre, Descripcion);
}
    

    public int InsertarRolObjeto(Usuario user){
         return mp.InsertarRol(user.getNombreRol(), user.getDescripcionRol());
}
    public int InsertarFuncionalidadObjeto(Usuario user){
         return mp.InsertarFuncionalidad(user.getNombreFuncionalidad(), user.getDescripcionFuncionalidad());
}
    public int InsertarRolFun(int idRol,int idFun){
         return mp.InsertarRolFuncion(idRol, idFun);
}
    public int InsertarRolUs(int idUs,int idRol){
         return mp.InsertarUsuarioRol(idUs, idRol);
}
        public ArrayList<Usuario> ListaUsuarios() throws ClassNotFoundException, SQLException{
        int IdUs =0;
        String nombre = "";
        String apellido = "";
        String user="";
        String pass="";
        String dni="";
        ResultSet rs;//recuper datos desde rs y los desgloso o desconpone
        lstUsuario =new ArrayList<>();
        rs= mp.ConsultarUsuario();
        ResultSetMetaData rm = rs.getMetaData();//descompogo todos los datos agrupaados desde la base de datos
        //Recupera los campos de la tabla
        int columnCount = rm.getColumnCount();//si hace o no la opreacion
        ArrayList<String> columns = new ArrayList<>();//arrayList que permite retorna solo las comlumnas de la BDD
        for (int i = 1; i <= columnCount; i++) {
            String columnName = rm.getColumnName(i);//retorna una a una las columnas
            columns.add(columnName);
        }
        while(rs.next()){
            for(String columnName:columns){
                String value=rs.getString(columnName);
                if(columnName.equals("us_id"))
                    IdUs=Integer.parseInt(value);
                if (columnName.equals("us_dni")) 
                    dni=value;
                if(columnName.equals("us_nombre"))
                    nombre=value;
                if(columnName.equals("us_apellido"))
                     apellido=value;
                if(columnName.equals("us_login"))
                    user=value;
                if(columnName.equals("us_password"))
                    pass=value;
            }
            try
            {
               Usuario  objUs=new Usuario(IdUs,nombre,apellido,user,pass);
                lstUsuario.add(objUs);
            }
            catch(Exception ex)
            {
                System.out.println(ex.getMessage());
            }
        }
        return lstUsuario;
        
    }     
        public ArrayList<Usuario> ListaRol() throws ClassNotFoundException, SQLException{
        int IdRol =0;
        String nombre = "";
        String descripcion = "";
 
        ResultSet rs;//recuper datos desde rs y los desgloso o desconpone
        lstRoles =new ArrayList<>();
        rs= mp.ConsultarRoles();
        ResultSetMetaData rm = rs.getMetaData();//descompogo todos los datos agrupaados desde la base de datos
        //Recupera los campos de la tabla
        int columnCount = rm.getColumnCount();//si hace o no la opreacion
        ArrayList<String> columns = new ArrayList<>();//arrayList que permite retorna solo las comlumnas de la BDD
        for (int i = 1; i <= columnCount; i++) {
            String columnName = rm.getColumnName(i);//retorna una a una las columnas
            columns.add(columnName);
        }
        while(rs.next()){
            for(String columnName:columns){
                String value=rs.getString(columnName);
                if(columnName.equals("rol_id"))
                    IdRol=Integer.parseInt(value);
                if(columnName.equals("rol_nombre"))
                    nombre=value;
                if(columnName.equals("rol_descripcion"))
                     descripcion=value;
            }
            try
            {
               Usuario  objUs=new Usuario(IdRol,nombre,descripcion);
                lstRoles.add(objUs);
            }
            catch(Exception ex)
            {
                System.out.println(ex.getMessage());
            }
        }
        return lstRoles;
        
    }    
        public ArrayList<Usuario> ListaFuncionalidad() throws ClassNotFoundException, SQLException{
        int IdFun =0;
        String nombre = "";
        String descripcion = "";
 
        ResultSet rs;//recuper datos desde rs y los desgloso o desconpone
        lstFuncionalidades =new ArrayList<>();
        rs= mp.ConsultarFuncionalidad();
        ResultSetMetaData rm = rs.getMetaData();//descompogo todos los datos agrupaados desde la base de datos
        //Recupera los campos de la tabla
        int columnCount = rm.getColumnCount();//si hace o no la opreacion
        ArrayList<String> columns = new ArrayList<>();//arrayList que permite retorna solo las comlumnas de la BDD
        for (int i = 1; i <= columnCount; i++) {
            String columnName = rm.getColumnName(i);//retorna una a una las columnas
            columns.add(columnName);
        }
        while(rs.next()){
            for(String columnName:columns){
                String value=rs.getString(columnName);
                if(columnName.equals("fun_id"))
                    IdFun=Integer.parseInt(value);
                if(columnName.equals("fun_nombre"))
                    nombre=value;
                if(columnName.equals("fun_descripcion"))
                     descripcion=value;
            }
            try
            {
               Usuario  objUs=new Usuario();
               objUs.setNombreFuncionalidad(nombre);
               objUs.setIntFuncionalidad(IdFun);
               objUs.setDescripcionFuncionalidad(descripcion);
                lstFuncionalidades.add(objUs);
            }
            catch(Exception ex)
            {
                System.out.println(ex.getMessage());
            }
        }
        return lstFuncionalidades;
        
    }   
        public ArrayList<Usuario> ListaRolUs() throws ClassNotFoundException, SQLException{
        int idRol=0;
        int idUs=0;
        String nombreUsuario = "";
        String nombreRol = "";
 
        ResultSet rs;//recuper datos desde rs y los desgloso o desconpone
        lstUsRol =new ArrayList<>();
        rs= mp.ConsultarUsRol();
        ResultSetMetaData rm = rs.getMetaData();//descompogo todos los datos agrupaados desde la base de datos
        //Recupera los campos de la tabla
        int columnCount = rm.getColumnCount();//si hace o no la opreacion
        ArrayList<String> columns = new ArrayList<>();//arrayList que permite retorna solo las comlumnas de la BDD
        for (int i = 1; i <= columnCount; i++) {
            String columnName = rm.getColumnName(i);//retorna una a una las columnas
            columns.add(columnName);
        }
        while(rs.next()){
            for(String columnName:columns){
                String value=rs.getString(columnName);
                if(columnName.equals("us_id"))
                    idRol=Integer.parseInt(value);
                if (columnName.equals("us_nombre"))  
                    nombreUsuario=value;
                if(columnName.equals("rol_id"))
                    idUs=Integer.parseInt(value);
                if (columnName.equals("rol_nombre")) 
                    nombreRol=value;       
            try
            {
               Usuario  objUs=new Usuario();
               objUs.setIdUsuario(idUs);
               objUs.setIdRol(idRol);
               objUs.setNombreUsuario(nombreUsuario);
               objUs.setNombreRol(nombreRol);
                lstUsRol.add(objUs);
            }
            catch(Exception ex)
            {
                System.out.println(ex.getMessage());
            }
        }
        
        }
        return lstUsRol;
    }
        public ArrayList<Usuario> ListaCredenciales() throws ClassNotFoundException, SQLException{
        int idRol=0;
        int idUs=0;
        String nombreUsuario = "";
        String nombreRol = "";
        String usuario = "";
        String password = "";
        String apellido = "";
        String dni = "";
        ResultSet rs;//recuper datos desde rs y los desgloso o desconpone
        lstCredenciales =new ArrayList<>();
        rs= mp.ConsultarUsRol();
        ResultSetMetaData rm = rs.getMetaData();//descompogo todos los datos agrupaados desde la base de datos
        //Recupera los campos de la tabla
        int columnCount = rm.getColumnCount();//si hace o no la opreacion
        ArrayList<String> columns = new ArrayList<>();//arrayList que permite retorna solo las comlumnas de la BDD
        for (int i = 1; i <= columnCount; i++) {
            String columnName = rm.getColumnName(i);//retorna una a una las columnas
            columns.add(columnName);
        }
        while(rs.next()){
            for(String columnName:columns){
                String value=rs.getString(columnName);
                if(columnName.equals("us_id"))
                    idRol=Integer.parseInt(value);
                 if (columnName.equals("us_dni"))  
                    dni=value;
                if (columnName.equals("us_nombre"))  
                    nombreUsuario=value;
                if (columnName.equals("us_apellido")) 
                    apellido=value;
                if(columnName.equals("rol_id"))
                    idUs=Integer.parseInt(value);
                if (columnName.equals("rol_nombre")) 
                    nombreRol=value;   
                if (columnName.equals("us_login")) 
                    usuario=value;
                if (columnName.equals("us_password")) 
                    password=value;                
            try
            {
               Usuario  objUs=new Usuario();
               objUs.setIdUsuario(idUs);
               objUs.setDni(dni);
               objUs.setIdRol(idRol);
               objUs.setNombreUsuario(nombreUsuario);
               objUs.setApellidoUsuario(apellido);
               objUs.setUsername(usuario);
               objUs.setPassword(password);
               objUs.setNombreRol(nombreRol);
               lstCredenciales.add(objUs);
            }
            catch(Exception ex)
            {
                System.out.println(ex.getMessage());
            }
        }
        
        }
        return lstCredenciales;
    }        
        
        
        public ArrayList<Usuario> ListaRolFun() throws ClassNotFoundException, SQLException{
        int idRol=0;
        int idFun=0;
        String nombre = "";
        String funcion = "";
 
        ResultSet rs;//recuper datos desde rs y los desgloso o desconpone
        lstRolFun =new ArrayList<>();
        rs= mp.ConsultarRolFun();
        ResultSetMetaData rm = rs.getMetaData();//descompogo todos los datos agrupaados desde la base de datos
        //Recupera los campos de la tabla
        int columnCount = rm.getColumnCount();//si hace o no la opreacion
        ArrayList<String> columns = new ArrayList<>();//arrayList que permite retorna solo las comlumnas de la BDD
        for (int i = 1; i <= columnCount; i++) {
            String columnName = rm.getColumnName(i);//retorna una a una las columnas
            columns.add(columnName);
        }
        while(rs.next()){
            for(String columnName:columns){
                String value=rs.getString(columnName);
                if(columnName.equals("rol_id"))
                    idRol=Integer.parseInt(value);
                if(columnName.equals("fun_id"))
                    idFun=Integer.parseInt(value);
                if (columnName.equals("rol_nombre"))  
                    nombre=value;
                if (columnName.equals("fun_nombre"))  
                    funcion=value;
            try
            {
               Usuario  objUs=new Usuario();
               objUs.setIntFuncionalidad(idFun);
               objUs.setNombreFuncionalidad(funcion);
               objUs.setNombreRol(nombre);
               objUs.setIdRol(idRol);
                lstRolFun.add(objUs);
            }
            catch(Exception ex)
            {
                System.out.println(ex.getMessage());
            }
        }
        
        }
        return lstRolFun;
    }
    public String verFuncion(String rol) throws ClassNotFoundException, SQLException{
            String fun=null;
            String fun1=null;
 
        ResultSet rs;//recuper datos desde rs y los desgloso o desconpone
        rs= mp.verFun(rol);
        ResultSetMetaData rm = rs.getMetaData();//descompogo todos los datos agrupaados desde la base de datos
        //Recupera los campos de la tabla
        int columnCount = rm.getColumnCount();//si hace o no la opreacion
        ArrayList<String> columns = new ArrayList<>();//arrayList que permite retorna solo las comlumnas de la BDD
        for (int i = 1; i <= columnCount; i++) {
            String columnName = rm.getColumnName(i);//retorna una a una las columnas
            columns.add(columnName);
        }
        while(rs.next()){
            for(String columnName:columns){
                String value=rs.getString(columnName);
                if(columnName.equals("fun_nombre")){
                    fun=value;
                    System.out.println(fun);
                    if (fun!=null) {
                    fun1=fun;
                    break;   
                }
                }
            }
        }
        return fun1;
    } 
    public String verNombre(String user) throws ClassNotFoundException, SQLException{
            String nombre=null;
            String apellido=null;
            String nombreC=null;
 
        ResultSet rs;//recuper datos desde rs y los desgloso o desconpone
        rs= mp.verNombre(user);
        ResultSetMetaData rm = rs.getMetaData();//descompogo todos los datos agrupaados desde la base de datos
        //Recupera los campos de la tabla
        int columnCount = rm.getColumnCount();//si hace o no la opreacion
        ArrayList<String> columns = new ArrayList<>();//arrayList que permite retorna solo las comlumnas de la BDD
        for (int i = 1; i <= columnCount; i++) {
            String columnName = rm.getColumnName(i);//retorna una a una las columnas
            columns.add(columnName);
        }
        while(rs.next()){
            for(String columnName:columns){
                String value=rs.getString(columnName);
                if(columnName.equals("us_nombre"))
                    nombre=value;
                if(columnName.equals("us_apellido"))
                    apellido=value;                
            }
        }
        nombreC=nombre+" "+apellido;
        return nombreC;
    }  
    public String verRol(String rol) throws ClassNotFoundException, SQLException{
 
        ResultSet rs;//recuper datos desde rs y los desgloso o desconpone
        rs= mp.verRol(rol);
        ResultSetMetaData rm = rs.getMetaData();//descompogo todos los datos agrupaados desde la base de datos
        //Recupera los campos de la tabla
        int columnCount = rm.getColumnCount();//si hace o no la opreacion
        ArrayList<String> columns = new ArrayList<>();//arrayList que permite retorna solo las comlumnas de la BDD
        for (int i = 1; i <= columnCount; i++) {
            String columnName = rm.getColumnName(i);//retorna una a una las columnas
            columns.add(columnName);
        }
        while(rs.next()){
            for(String columnName:columns){
                String value=rs.getString(columnName);
                if(columnName.equals("rol_nombre"))
                    rol=value;
            }
        }
        return rol;
    }     
    public boolean eliminarUsuarioID(int id){
        boolean resultado=
                mp.EliminarUsuarioID(id);
        return resultado;

    }    
    public boolean eliminarFunId(int id){
        boolean resultado=
                mp.EliminarFunID(id);
        return resultado;

    }    
    public boolean eliminarRolID(int id){
        boolean resultado=
                mp.EliminarRolID(id);
        return resultado;
    }  
    public boolean eliminarRolFun(int id){
        boolean resultado=
                mp.EliminarRolFun(id);
        return resultado;
    }
    public boolean eliminarRolUs(int id){
        boolean resultado=
                mp.EliminarRolUs(id);
        return resultado;
    }
    
    public int modificarUsuarioID(int id, String nombre, String apellido, String user, String pass){
        return mp.ModificarUsuario(id, nombre, apellido,user,pass);
    }    
    public  int modificarFunId(int id, String nombre, String descripcion){
        return mp.ModificarFuncionalidad(id, nombre, descripcion);

    }    
    public int modificarRolID(int id,String nombre, String descripcion){
        return mp.ModificarRol(id, nombre, descripcion);
    }    
    public boolean validarUs(String id, String username) throws ClassNotFoundException, SQLException{
        int valor=0;
        ResultSet rs;//recuper datos desde rs y los desgloso o desconpone
        rs= mp.ValidarUser(id,username);
        ResultSetMetaData rm = rs.getMetaData();//descompogo todos los datos agrupaados desde la base de datos
        //Recupera los campos de la tabla
        int columnCount = rm.getColumnCount();//si hace o no la opreacion
        ArrayList<String> columns = new ArrayList<>();//arrayList que permite retorna solo las comlumnas de la BDD
        for (int i = 1; i <= columnCount; i++) {
            String columnName = rm.getColumnName(i);//retorna una a una las columnas
            columns.add(columnName);
        }
        while(rs.next()){
            for(String columnName:columns){
                String value=rs.getString(columnName);
                if(columnName.equals("TOTAL"))
                    valor=Integer.parseInt(value);
            }
        }
        
        if (valor>=1) {
            return true;
        }else{
            return false;
        }
    }
    public boolean validarRol(String id) throws ClassNotFoundException, SQLException{
        int valor=0;
        ResultSet rs;//recuper datos desde rs y los desgloso o desconpone
        rs= mp.ValidarRol(id);
        ResultSetMetaData rm = rs.getMetaData();//descompogo todos los datos agrupaados desde la base de datos
        //Recupera los campos de la tabla
        int columnCount = rm.getColumnCount();//si hace o no la opreacion
        ArrayList<String> columns = new ArrayList<>();//arrayList que permite retorna solo las comlumnas de la BDD
        for (int i = 1; i <= columnCount; i++) {
            String columnName = rm.getColumnName(i);//retorna una a una las columnas
            columns.add(columnName);
        }
        while(rs.next()){
            for(String columnName:columns){
                String value=rs.getString(columnName);
                if(columnName.equals("TOTAL"))
                    valor=Integer.parseInt(value);
            }
        }
        
        if (valor==1) {
            return true;
        }else{
            return false;
        }
    }
    public boolean validarUserName(String id) throws ClassNotFoundException, SQLException{
        int valor=0;
        ResultSet rs;//recuper datos desde rs y los desgloso o desconpone
        rs= mp.ValidarUsername(id);
        ResultSetMetaData rm = rs.getMetaData();//descompogo todos los datos agrupaados desde la base de datos
        //Recupera los campos de la tabla
        int columnCount = rm.getColumnCount();//si hace o no la opreacion
        ArrayList<String> columns = new ArrayList<>();//arrayList que permite retorna solo las comlumnas de la BDD
        for (int i = 1; i <= columnCount; i++) {
            String columnName = rm.getColumnName(i);//retorna una a una las columnas
            columns.add(columnName);
        }
        while(rs.next()){
            for(String columnName:columns){
                String value=rs.getString(columnName);
                if(columnName.equals("TOTAL"))
                    valor=Integer.parseInt(value);
            }
        }
        
        if (valor==1) {
            return true;
        }else{
            return false;
        }
    }    
    

    
}