
package ModuloAdministracion.Clases;

/**
 *
 * @author Jean Paul Mosquera Arevalo
 */
public class Administracion {
    //Catalogo
    int     IdCatalogo;
    String  strCodCatalogo;
    String  strNombreCatalogo;
    int     intEstadoCatalogo;
    //Item_Catalogo
    int     idITC;
    String  strDescripcion;

    public Administracion(int IdCatalogo, String strCodCatalogo, String strNombreCatalogo, int intEstadoCatalogo, int idITC, String strDescripcion) {
        this.IdCatalogo = IdCatalogo;
        this.strCodCatalogo = strCodCatalogo;
        this.strNombreCatalogo = strNombreCatalogo;
        this.intEstadoCatalogo = intEstadoCatalogo;
        this.idITC = idITC;
        this.strDescripcion = strDescripcion;
    }

    public Administracion(int IdCatalogo, String strCodCatalogo, String strNombreCatalogo, int intEstadoCatalogo) {
        this.IdCatalogo = IdCatalogo;
        this.strCodCatalogo = strCodCatalogo;
        this.strNombreCatalogo = strNombreCatalogo;
        this.intEstadoCatalogo = intEstadoCatalogo;
    }

    public Administracion(int idITC, String strDescripcion) {
        this.idITC = idITC;
        this.strDescripcion = strDescripcion;
    }
    
    
    
    public Administracion(int idCatalogo,int idITC, String strDescripcion) {
        this.IdCatalogo=idCatalogo;
        this.idITC = idITC;
        this.strDescripcion = strDescripcion;
    }
    public Administracion() {
        
    }

    public int getIdCatalogo() {
        return IdCatalogo;
    }

    public void setIdCatalogo(int IdCatalogo) {
        this.IdCatalogo = IdCatalogo;
    }

    public String getStrCodCatalogo() {
        return strCodCatalogo;
    }

    public void setStrCodCatalogo(String strCodCatalogo) {
        this.strCodCatalogo = strCodCatalogo;
    }

    public String getStrNombreCatalogo() {
        return strNombreCatalogo;
    }

    public void setStrNombreCatalogo(String strNombreCatalogo) {
        this.strNombreCatalogo = strNombreCatalogo;
    }

    public int getIntEstadoCatalogo() {
        return intEstadoCatalogo;
    }

    public void setIntEstadoCatalogo(int intEstadoCatalogo) {
        this.intEstadoCatalogo = intEstadoCatalogo;
    }

    public int getIdITC() {
        return idITC;
    }

    public void setIdITC(int idITC) {
        this.idITC = idITC;
    }

    public String getStrDescripcion() {
        return strDescripcion;
    }

    public void setStrDescripcion(String strDescripcion) {
        this.strDescripcion = strDescripcion;
    }
    
    public String toString(){
        return strDescripcion;
    }
}
