
package ModuloSeguridad.DAT;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Jean Paul Mosquera Arevalo
 */
public class DATUsuario{
    DATConexion c = new DATConexion();
    
    public ResultSet ConsultarUsuario() throws SQLException, ClassNotFoundException{
        PreparedStatement pst=c.AbrirConexion().prepareStatement("SELECT * FROM usuario");
        ResultSet rs=pst.executeQuery();
        return rs;
    }
        public ResultSet ConsultarRoles() throws SQLException, ClassNotFoundException{
        PreparedStatement pst=c.AbrirConexion().prepareStatement("SELECT * FROM rol");
        ResultSet rs=pst.executeQuery();
        return rs;
    }
    public ResultSet ConsultarFuncionalidad() throws SQLException, ClassNotFoundException{
        PreparedStatement pst=c.AbrirConexion().prepareStatement("SELECT * FROM funcionalidad");
        ResultSet rs=pst.executeQuery();
        return rs;
    }
    public ResultSet ConsultarIdRol(String Nombre) throws ClassNotFoundException, SQLException{
        PreparedStatement pst=c.AbrirConexion().prepareStatement("SELECT rol_id FROM catalogo WHERE rol_nombre ="+"'"+Nombre+"'");
        ResultSet rs=pst.executeQuery();
        return rs;
    }
    public ResultSet ConsultarIdFuncionalidad(String Nombre) throws ClassNotFoundException, SQLException{
        PreparedStatement pst=c.AbrirConexion().prepareStatement("SELECT fun_id FROM catalogo WHERE fun_nombre ="+"'"+Nombre+"'");
        ResultSet rs=pst.executeQuery();
        return rs;
    }
    public ResultSet ConsultarItemCatalogo(int idCat) throws SQLException, ClassNotFoundException{
        PreparedStatement pst=c.AbrirConexion().prepareStatement("SELECT * FROM item_catalogo where idCatalogo=" +idCat);
        ResultSet rs=pst.executeQuery();
        return rs;
    }    
    public boolean EliminarUsuarioID(int id){
         PreparedStatement st=null;
        try {
            st=c.AbrirConexion().prepareStatement("DELETE FROM usuario WHERE us_id=" +id);
            st.executeUpdate();
            st.close();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(DATUsuario.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DATUsuario.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    public boolean EliminarRolID(int id){
         PreparedStatement st=null;
        try {
            st=c.AbrirConexion().prepareStatement("DELETE FROM rol WHERE rol_id=" +id);
            st.executeUpdate();
            st.close();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(DATUsuario.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DATUsuario.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }   
    public boolean EliminarFunID(int id){
         PreparedStatement st=null;
        try {
            st=c.AbrirConexion().prepareStatement("DELETE FROM funcionalidad WHERE fun_id=" +id);
            st.executeUpdate();
            st.close();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(DATUsuario.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DATUsuario.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }        
    
    
    
    public int InsertarUsuario(String dni,String Nombre, String Apellido, String user, String password){
        int retorno=0;
        try{
            String strSQL="INSERT INTO usuario(us_dni,us_nombre, us_apellido, us_login, us_password)" + " VALUES(?,?,?,?,?)";
            PreparedStatement pst=c.AbrirConexion().prepareStatement(strSQL);
            pst.setString(1, dni);
            pst.setString(2,Nombre);
            pst.setString(3,Apellido);
            pst.setString(4,user);
            pst.setString(5,password);
            retorno=pst.executeUpdate();
            pst.close();
            c.CerrarConexion();
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(DATUsuario.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retorno; 
    }
    public int InsertarRol( String Nombre, String Descripcion){
        int retorno=0;
        try{
            String strSQL="INSERT INTO rol(rol_nombre, rol_descripcion)" + " VALUES(?,?)";
            PreparedStatement pst=c.AbrirConexion().prepareStatement(strSQL);
            pst.setString(1,Nombre);
            pst.setString(2,Descripcion);
            retorno=pst.executeUpdate();
            
            pst.close();
            c.CerrarConexion();
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(DATUsuario.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retorno; 
    }
    public int InsertarFuncionalidad(String Nombre, String Descripcion){
        int retorno=0;
        try{
            String strSQL="INSERT INTO funcionalidad( fun_nombre, fun_descripcion)" + " VALUES(?,?)";
            PreparedStatement pst=c.AbrirConexion().prepareStatement(strSQL);
            pst.setString(1,Nombre);
            pst.setString(2,Descripcion);
            retorno=pst.executeUpdate();
            pst.close();
            c.CerrarConexion();
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(DATUsuario.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retorno; 
    }    

     public int ModificarUsuario(int id, String Nombre, String Apellido, String user, String password){
        int retorno=0;
        try{
            String strSQL="UPDATE usuario SET us_nombre="+"'"+Nombre+"'"+",us_apellido="+"'"+Apellido+"'"+",us_apellido="+"'"+Apellido+"'"
                    +",us_login="+"'"+user+"'"+",us_password="+"'"+password+"'"+"WHERE us_id=" +id;
            PreparedStatement pst=c.AbrirConexion().prepareStatement(strSQL);
            retorno=pst.executeUpdate();
            pst.close();
            c.CerrarConexion();
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(DATUsuario.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retorno; 
    }  
      public int ModificarRol(int id, String Nombre, String Descripcion){
        int retorno=0;
        try{
            String strSQL="UPDATE rol SET rol_nombre="+"'"+Nombre+"'"+",rol_descripcion="+"'"+Descripcion+"'"+" WHERE rol_id=" +id;
            PreparedStatement pst=c.AbrirConexion().prepareStatement(strSQL);
            retorno=pst.executeUpdate();
            pst.close();
            c.CerrarConexion();
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(DATUsuario.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retorno; 
    }  
      public int ModificarFuncionalidad(int id, String Nombre, String Descripcion){
        int retorno=0;
        try{
            String strSQL="UPDATE funcionalidad SET fun_nombre="+"'"+Nombre+"'"+",fun_descripcion="+"'"+Descripcion+"'"+" WHERE fun_id=" +id;
            PreparedStatement pst=c.AbrirConexion().prepareStatement(strSQL);
            retorno=pst.executeUpdate();
            pst.close();
            c.CerrarConexion();
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(DATUsuario.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retorno; 
    }  
      
      
      
      //Write
    public int InsertarRolFuncion(int idRol, int idFuncion){
        int retorno=0;
        try{
            String strSQL="INSERT INTO rol_funcionalidad(rol_id,fun_id)" + " VALUES(?,?)";
            PreparedStatement pst=c.AbrirConexion().prepareStatement(strSQL);
            pst.setInt(1,idRol);
            pst.setInt(2,idFuncion);
            retorno=pst.executeUpdate();
            pst.close();
            c.CerrarConexion();
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(DATUsuario.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retorno;
       
    }   
    public int InsertarUsuarioRol(int idUsuario, int idRol){
        int retorno=0;
        try{
            String strSQL="INSERT INTO rol_usuario(us_id,rol_id)" + " VALUES(?,?)";
            PreparedStatement pst=c.AbrirConexion().prepareStatement(strSQL);
            pst.setInt(1,idUsuario);
            pst.setInt(2,idRol);
            retorno=pst.executeUpdate();
            pst.close();
            c.CerrarConexion();
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(DATUsuario.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retorno;
       
    }    
    public int ModificarRolFuncion(int idRol, int idFuncion){
        int retorno=0;
        try{
            String strSQL="UPDATE rol_funcionalidad SET rol_id="+"'"+idRol+"'"+",rol_id="+"'"+idFuncion+"'"+" WHERE rol_id=" +idRol;
            PreparedStatement pst=c.AbrirConexion().prepareStatement(strSQL);
            retorno=pst.executeUpdate();
            pst.close();
            c.CerrarConexion();
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(DATUsuario.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retorno;
       
    }   
    public int ModificarRolUsuario(int idUs, int idRol){
        int retorno=0;
        try{
            String strSQL="UPDATE rol_usuario SET us_id="+"'"+idUs+"'"+",rol_id="+"'"+idRol+"'"+" WHERE rol_id=" +idRol;
            PreparedStatement pst=c.AbrirConexion().prepareStatement(strSQL);
            retorno=pst.executeUpdate();
            pst.close();
            c.CerrarConexion();
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(DATUsuario.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retorno;
    } 
    public boolean EliminarRolFun(int id){
         PreparedStatement st=null;
        try {
            st=c.AbrirConexion().prepareStatement("DELETE FROM rol_funcionalidad WHERE fun_id=" +id);
            st.executeUpdate();
            st.close();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(DATUsuario.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DATUsuario.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }        
    public boolean EliminarRolUs(int id){
         PreparedStatement st=null;
        try {
            st=c.AbrirConexion().prepareStatement("DELETE FROM rol_usuario WHERE rol_id=" +id);
            st.executeUpdate();
            st.close();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(DATUsuario.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DATUsuario.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }   
    public ResultSet ConsultarUsRol() throws SQLException, ClassNotFoundException{
        PreparedStatement pst=c.AbrirConexion().prepareStatement("SELECT * FROM rol_usuario ru,rol r ,usuario us WHERE r.rol_id=ru.rol_id AND us.us_id=ru.us_id order by us.us_id ");
        ResultSet rs=pst.executeQuery();
        return rs;
    }  
    public ResultSet ConsultarRolFun() throws SQLException, ClassNotFoundException{
        PreparedStatement pst=c.AbrirConexion().prepareStatement("SELECT * FROM rol_funcionalidad rf,rol r ,funcionalidad f WHERE r.rol_id=rf.rol_id AND f.fun_id=rf.fun_id order by r.rol_id" );
        ResultSet rs=pst.executeQuery();
        return rs;
    }    
    public ResultSet ValidarUser(String id, String username) throws ClassNotFoundException, SQLException{
        PreparedStatement pst=c.AbrirConexion().prepareStatement("SELECT count(*) TOTAL FROM usuario WHERE us_dni=" +"'"+id+"'"+"OR us_login="+"'"+username+"'");
        ResultSet rs= pst.executeQuery();
        return rs;
    }
    public ResultSet ValidarUsername(String id) throws ClassNotFoundException, SQLException{
        PreparedStatement pst=c.AbrirConexion().prepareStatement("SELECT count(*) TOTAL FROM usuario WHERE us_login=" +id);
        ResultSet rs= pst.executeQuery();
        return rs;
    }    
    public ResultSet verFun(String rol) throws ClassNotFoundException, SQLException{
        PreparedStatement pst=c.AbrirConexion().prepareStatement("SELECT * FROM rol_funcionalidad rf,rol r ,funcionalidad f WHERE r.rol_id=rf.rol_id AND r.rol_nombre="+"'"+rol+"'"+" AND f.fun_id=rf.fun_id order by r.rol_id");
        ResultSet rs= pst.executeQuery();
        return rs;      
    }
    public ResultSet ValidarRol(String nombre) throws ClassNotFoundException, SQLException{
        PreparedStatement pst=c.AbrirConexion().prepareStatement("SELECT count(*) TOTAL FROM rol WHERE rol_nombre="+"'"+nombre+"'");
        ResultSet rs= pst.executeQuery();
        return rs;
    }   
    public ResultSet verNombre(String user) throws ClassNotFoundException, SQLException{
        PreparedStatement pst=c.AbrirConexion().prepareStatement("SELECT * FROM rol_funcionalidad rf,rol r ,funcionalidad f WHERE r.rol_id=rf.rol_id AND r.rol_nombre="+user+"AND f.fun_id=rf.fun_id order by r.rol_id");
        ResultSet rs= pst.executeQuery();
        return rs;      
    }
    public ResultSet verRol(String nombre) throws ClassNotFoundException, SQLException{
        PreparedStatement pst=c.AbrirConexion().prepareStatement("SELECT * FROM rol_funcionalidad rf,rol r ,funcionalidad f WHERE r.rol_id=rf.rol_id AND r.rol_nombre="+nombre+"AND f.fun_id=rf.fun_id order by r.rol_id");
        ResultSet rs= pst.executeQuery();
        return rs;      
    }    
}
