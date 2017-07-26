
package ModuloAdministracion.BL;
import ModuloAdministracion.Clases.Administracion;
import ModuloAdministracion.DAT.DATAdministracion;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
/**
 *
 * @author Jean Paul Mosquera Arevalo
 */
public class BLAdministracion {
     DATAdministracion mp = new DATAdministracion();
     public static ArrayList lstCatalogo;
     public static ArrayList lstItemsC;
     public int InsertarCatalogo(String CodCatalogo, String NombreCatalogo, int EstadoCatalogo){
         return mp.InsertarCatalogo(CodCatalogo, NombreCatalogo, EstadoCatalogo);
}
     public int InsertarCatalogoObjeto(Administracion objAdmin){
         return mp.InsertarCatalogo(objAdmin.getStrCodCatalogo(), objAdmin.getStrNombreCatalogo(), objAdmin.getIntEstadoCatalogo());
}
     public int InsertarCatalogoLista(ArrayList<Administracion> lstAdmin){
         for (Administracion objAdm : lstAdmin){
             //
             
         }
         return 0;
}
    public ArrayList<Administracion> ConsultarCatalogo() throws SQLException, ClassNotFoundException{
        ArrayList<Administracion> lstAdministracion=new ArrayList<>();
        try{
            
       
        ResultSet rs=mp.ConsultarCatalogo();
        Administracion obja= new Administracion();
        while(rs.next()){
            int intIDCatalogo=rs.getInt("idcatalogo");
            String strCodCat=rs.getString("codCatalogo");
            String strNombre=rs.getString("nombre");
            int intEstado=rs.getInt("estado");
            Administracion objAdm=new Administracion(intIDCatalogo,strCodCat,strNombre,intEstado);
            lstAdministracion.add(objAdm);
        }
         }catch(Exception ex){
             System.out.println(ex.getMessage());;
         }
        return lstAdministracion;
    } 
    public ArrayList<Administracion> ConsultarCatalogo2(){
        ArrayList<Administracion> lstAdm=new ArrayList<>();
        try{
            
       
        ResultSet rs=mp.ConsultarCatalogo2();
        Administracion obja= new Administracion();
        while(rs.next()){
            int intIDCatalogo=rs.getInt("idcatalogo");
            String strNombre=rs.getString("nombre");
            Administracion objAdm=new Administracion(intIDCatalogo,strNombre);
            lstAdm.add(objAdm);
        }
         }catch(ClassNotFoundException | SQLException ex){
             System.out.println(ex.getMessage());;
         }
        return lstAdm;
    }   
    
/**
    public boolean eliminarCatalogoNombre(String strParam){
        boolean resultado=
                mp.EliminarCatalogo(strParam);
        return resultado;

    }
    */
    
    public boolean eliminarCatalogoID(int id){
        boolean resultado=
                mp.EliminarCatalogo(id);
        return resultado;

    }    
    

    public boolean eliminarItemUnoaUno(int idC, int idI){
        boolean resultado=
                mp.EliminarItem(idC,idI);
        return resultado;
    }
    
    /**
    public boolean eliminarItemLista(ArrayList<Administracion> lstLista){
        boolean resultado=false;
        for(Administracion cat : lstLista){
            resultado=
                    mp.EliminarCatalogo(cat.getStrNombreCatalogo());
        }
        return resultado;
    }    
    **/
    
    
    public int ConsultarIdCatalogo(String strNombre) throws SQLException, ClassNotFoundException{
        int intIDCatalogo=0;
        try{
        ResultSet rs=mp.ConsultarIdCatalogo(strNombre);
        while(rs.next()){
            intIDCatalogo=rs.getInt("idcatalogo");
        }
         }catch(Exception ex){
             System.out.println(ex.getMessage());
         }
        return intIDCatalogo;
    }     
     public int InsertarItemCatalogoObjeto(Administracion objAdmin){
         return mp.InsertarItemCatalogo(objAdmin.getIdITC(),objAdmin.getStrDescripcion());
}    
    public ArrayList<Administracion> ListaCatalogos() throws ClassNotFoundException, SQLException{
        int IdCat =0;
        String codigo = "";
        String nombre = "";
        int estado = 0;
        ResultSet rs;//recuper datos desde rs y los desgloso o desconpone
        lstCatalogo =new ArrayList<>();
        rs= mp.ConsultarCatalogo();
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
                if(columnName.equals("idCatalogo"))
                    IdCat=Integer.parseInt(value);
                if(columnName.equals("codCatalogo"))
                    codigo=value;
                if(columnName.equals("nombre"))
                     nombre=value;
                if(columnName.equals("estado"))
                    estado=Integer.parseInt(value);
                  
            }
            try
            {
               Administracion  objAd=new Administracion (IdCat,codigo,nombre,estado);
                lstCatalogo.add(objAd);
            }
            catch(Exception ex)
            {
                System.out.println(ex.getMessage());
            }
        }
        return lstCatalogo;
        
    }     
    public ArrayList<Administracion> ListaItemsC(int idCat) throws ClassNotFoundException, SQLException{
        int idItc =0;
        String descripcion = "";
        ResultSet rs;//recuper datos desde rs y los desgloso o desconpone
        lstItemsC =new ArrayList<>();
        rs= mp.ConsultarItemCatalogo(idCat);
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
                if(columnName.equals("idCatalogo"))
                    idCat=Integer.parseInt(value);
                if(columnName.equals("idITC"))
                    idItc=Integer.parseInt(value);
                if(columnName.equals("descripcion"))
                     descripcion=value;
                  
            }
            try
            {
               Administracion  objIt=new Administracion (idCat,idItc,descripcion);
                lstItemsC.add(objIt);
            }
            catch(Exception ex)
            {
                System.out.println(ex.getMessage());
            }
        }
        return lstItemsC;
        
    }         
}