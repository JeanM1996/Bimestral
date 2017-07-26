
package ModuloAdministracion.DAT;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Jean Paul Mosquera Arevalo
 */
public class DATAdministracion {
    DATConexion c = new DATConexion();
    
    public ResultSet ConsultarCatalogo() throws SQLException, ClassNotFoundException{
        PreparedStatement pst=c.AbrirConexion().prepareStatement("SELECT * FROM catalogo");
        ResultSet rs=pst.executeQuery();
        return rs;
    }
    public ResultSet ConsultarCatalogo2() throws ClassNotFoundException, SQLException{
        PreparedStatement pst=c.AbrirConexion().prepareStatement("SELECT idCatalogo, nombre " + "FROM catalogo");
        ResultSet rs=pst.executeQuery();
        return rs;
    }
    public ResultSet ConsultarIdCatalogo(String Nombre) throws ClassNotFoundException, SQLException{
        PreparedStatement pst=c.AbrirConexion().prepareStatement("SELECT idCatalogo FROM catalogo WHERE nombre ="+"'"+Nombre+"'");
        ResultSet rs=pst.executeQuery();
        return rs;
    }
    public ResultSet ConsultarItemCatalogo(int idCat) throws SQLException, ClassNotFoundException{
        PreparedStatement pst=c.AbrirConexion().prepareStatement("SELECT * FROM item_catalogo where idCatalogo=" +idCat);
        ResultSet rs=pst.executeQuery();
        return rs;
    }    
    public boolean EliminarCatalogo(int catalogo){
         PreparedStatement st=null;
        try {
            st=c.AbrirConexion().prepareStatement("DELETE FROM catalogo WHERE idCatalogo=" +catalogo);
            st.executeUpdate();
            st.close();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(DATAdministracion.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DATAdministracion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    public boolean EliminarItem(int idCatalogo, int idItem){
         PreparedStatement st=null;
        try {
            st=c.AbrirConexion().prepareStatement("DELETE FROM item_catalogo WHERE idCatalogo=" +idCatalogo + " AND idITC=" +idItem);
            st.executeUpdate();
            st.close();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(DATAdministracion.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DATAdministracion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }    
    
    
    public int InsertarCatalogo(String CodCatalogo, String NombreCatalogo, int EstadoCatalogo){
        int retorno=0;
        try{
            String strSQL="INSERT INTO catalogo(codCatalogo,nombre,estado)" + " VALUES(?,?,?)";
            PreparedStatement pst=c.AbrirConexion().prepareStatement(strSQL);
            pst.setString(1,CodCatalogo);
            pst.setString(2,NombreCatalogo);
            pst.setInt(3,EstadoCatalogo);
            retorno=pst.executeUpdate();
            pst.close();
            c.CerrarConexion();
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(DATAdministracion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retorno;
       
    }
    public int InsertarItemCatalogo(int idCatalogo, String descripcion){
        int retorno=0;
        try{
            String strSQL="INSERT INTO item_catalogo(idCatalogo,Descripcion)" + " VALUES(?,?)";
            PreparedStatement pst=c.AbrirConexion().prepareStatement(strSQL);
            pst.setInt(1,idCatalogo);
            pst.setString(2,descripcion);
            retorno=pst.executeUpdate();
            pst.close();
            c.CerrarConexion();
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(DATAdministracion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retorno;
       
    }    
}
