
package Diseño;

/**
 * Clase Abstracta Tareas.
 * Esta es la superClase en la cual se ha declarado dos funciones de tipo Abstracto
 * es decir a ser implementadas en la subClase como es el caso de las funciones
 * establecerTipo y establecerPrioridad
 * @see Tareas
 * @author Jean Paul Mosquera A
 * @version 1.0
 */

public abstract class Tareas{ 
    /**
     * Parametros tipo String :Titulo, Descripcion, Fecha
     * Tipo int: Prioridad y tipo
     */
	public String titulo;
	public String descripcion;
	public String fecha;
        public String prioridad;
        public String tipo;
    
    //Constructor vacio, inicializa los valores por defecto
         public Tareas() {
        }
	
         /**
          * Constructor sobrecargado que inicializa los parametros acorde a lo indicado por el usuario
          * @param titulo : corresponde al titulo de la tarea
          * @param descripcion : corresponde a la descripcion de la tarea
          * @param fecha : corresponde a la fecha de la tarea
          * @param prioridad :  corresponde a la prioridad asignada a la tarea
          * @param tipo  : corresponde al tipo asignado a la tarea
          */
	public Tareas(String titulo, String descripcion, String fecha, String prioridad,String tipo) 
	{
		this.titulo = titulo;
		this.descripcion = descripcion;
		this.fecha = fecha;
                this.prioridad=prioridad;
                this.tipo=tipo;
	}
       
        /**
         * Metodo set y get (Establecer y Obtener) de los parametros
         * @return los valores correspondientes a los dados a los parametros
         */
        
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public String getFecha() {
		return fecha;
	}
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
        public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

        public String getPrioridad() {
            return prioridad;
        }

        public void setPrioridad(String prioridad) {
            this.prioridad = prioridad;
        }
        
        //metodos abstractos a implementarse en la clase hija o subclase
        public abstract String establecerTipo(int tipo);
        public abstract String establecerPrioridad(int prioridad);
        
        //Metodo toString adaptado //Metodo concreto
        public String toString(){
            StringBuilder sb = new StringBuilder();
                sb.append("\nTitulo: ");
                sb.append(getTitulo());
                sb.append("\nDescripcion: ");
                sb.append(getDescripcion());
                sb.append("\nFecha: ");
                sb.append(getFecha());
                sb.append("\nPrioridad: ");
                sb.append(getPrioridad());
                sb.append("\ntipo: ");
                sb.append(getTipo());
            return sb.toString();

        }	
}