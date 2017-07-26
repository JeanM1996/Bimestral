
package DAT;

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
public class DATTareas{
    DATConexion c = new DATConexion();
    
    public ResultSet ConsultarTarea() throws SQLException, ClassNotFoundException{
        PreparedStatement pst=c.AbrirConexion().prepareStatement("SELECT * FROM tareas");
        ResultSet rs=pst.executeQuery();
        return rs;
    }

    public ResultSet ConsultarIdTarea(String Titulo) throws ClassNotFoundException, SQLException{
        PreparedStatement pst=c.AbrirConexion().prepareStatement("SELECT idtareas FROM tareas WHERE rol_nombre ="+"'"+Titulo+"'");
        ResultSet rs=pst.executeQuery();
        return rs;
    }

    public boolean EliminarTareaTitulo(String Titulo){
         PreparedStatement st=null;
        try {
            st=c.AbrirConexion().prepareStatement("DELETE FROM tareas WHERE titulo=" +"'"+Titulo+"'");
            st.executeUpdate();
            st.close();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(DATTareas.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DATTareas.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    
    
    
    public int InsertarTarea(String Titulo, String Descripcion, String Fecha, String Materia,String Prioridad,String Tipo){
        int retorno=0;
        try{
            String strSQL="INSERT INTO tareas ("
            + "titulo, descripcion, fecha, materia,prioridad,tipo"
            + ") VALUES (?, ?, ?, ?,?,?)";
            PreparedStatement pst=c.AbrirConexion().prepareStatement(strSQL);
            pst.setString(1, Titulo);
            pst.setString(2, Descripcion);
            pst.setString(3, Fecha);
            pst.setString(4, Materia);
            pst.setString(5, Prioridad);
            pst.setString(6, Tipo);
            retorno=pst.executeUpdate();
            pst.close();
            c.CerrarConexion();
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(DATTareas.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retorno; 
    }
   

     public int ModificarTarea(String Titulo, String Descripcion, String Fecha, String Materia,String Prioridad,String Tipo){
        int retorno=0;
        try{
                    
            String strSQL="UPDATE tareas SET titulo="+"'"+Titulo+"'"+",descripcion="+"'"+Descripcion+"'"+",fecha="+"'"+Fecha+"'"
                    +",materia="+"'"+Materia+"'"+",prioridad="+"'"+Prioridad+"'"+",tipo="+"'"+Tipo+"'"+"WHERE titulo=" +"'"+Titulo+"'";
            PreparedStatement pst=c.AbrirConexion().prepareStatement(strSQL);
            retorno=pst.executeUpdate();
            pst.close();
            c.CerrarConexion();
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(DATTareas.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retorno; 
    }  

}
