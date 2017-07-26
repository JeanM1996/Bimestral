
package Diseño;

/**
 *Clase Tareas_Escolares.
 * Esta es la subClase de la superClase Tareas en esta se implementan los metodos
 * abstractos de la tambien clase Abstracta tareas. Ademas de heredarse los construtores 
 * como metodos.
 * @author Jean Paul Mosquera Arevalo
 * @see Tareas_Escolares
 * @version 1.0
 */
public class Tareas_Escolares extends Tareas  {
    //Atributos propios de esta clase
    String Materia;
    //Cnstructor heredado junto a los atributos propios de la calse
    public Tareas_Escolares(String titulo, String descripcion, String fecha, String prioridad, String tipo, String Materia, String Docente) {
        super(titulo, descripcion, fecha, prioridad, tipo);
        this.Materia=Materia;
    }

    public Tareas_Escolares( String titulo, String descripcion, String fecha,String Materia, String prioridad, String tipo) {
        super(titulo, descripcion, fecha, prioridad, tipo);
        this.Materia = Materia;
    }
    
//constructor vacio que iniciazlia los valores por defecto
    public Tareas_Escolares() {
    }
    
    /**
     * Metodo implementado.
     * En esta clase se ha implementado el metodo establecerTipo que recibe un
     * tipo de dato entero y retorna una cadena asociada con el tipo de tarea
     * @param tipo tipo de tarea mediante un numero
     * @return el tipo de tarea en formato de string
     */
    @Override
       public String establecerTipo(int tipo) {
        String sTipo;
        switch(tipo){
            case 1:
                sTipo="Examen";
            break;
            case 2:
                sTipo="Tarea Extraclase ";
            break;
            case 3:
                sTipo="Exposicion";
            break;
            case 4:
                sTipo="Proyecto";
            break;
            default:
                sTipo="Otra Actividad Academica";
            break;
        }
        return sTipo;
    }
     /**
      * Metodos set y get(Establecer y Obtener) de los parametros propios de esta subclase
      * @return el valor correspondiente que ha sido asignado a estos parametros.
      */       


    public String getMateria() {
        return Materia;
    }

    public void setMateria(String Materia) {
        this.Materia = Materia;
    }

    /**
     * Metodo implementado.
     * En esta clase se ha implementado el metodo establecerPrioridad que recibe un
     * tipo de dato entero y retorna una cadena asociada con la prioridad de la tarea
     * @param tipo Prioridad en formato numerico
     * 1. Alta ; 2. Media ; 3. Baja
     * @return la prioridad en formato de texto (String)
     */
    @Override
    public String establecerPrioridad(int tipo) {
        String sPrioridad;
        switch(tipo){
            case 1:
                sPrioridad="Alta";
            break;
            case 2:
                sPrioridad="Media";
            break;
            case 3:
                sPrioridad="Baja";
            break;
            default:
                sPrioridad="Normal";
            break;
        }
        return sPrioridad;
    }
    
    /**
     * Metodo toString mejorado.
     * Hereda el metodo toString de la clase madre, ademas se añade la impresion
     * de los parametros asociados con el nombre de la materia y de docente
     * @return la informacion completa de la tarea.
     */
    @Override
        public String toString(){
            
            StringBuilder an = new StringBuilder();
                an.append("\nMateria: ");
                an.append(getMateria());
            return super.toString()+an.toString() ;
        }
}
