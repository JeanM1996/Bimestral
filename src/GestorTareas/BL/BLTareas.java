/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BL;

import Diseño.*;
import DAT.DATTareas;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Jean Paul Mosquera Arevalo
 */
public class BLTareas {
    DATTareas mp = new DATTareas();

     public static ArrayList lstTareas;
     public int InsertarTarea(String Titulo, String Descripcion, String Fecha, String Materia,String Prioridad,String Tipo){
         return mp.InsertarTarea(Titulo, Descripcion, Fecha,Materia,Prioridad,Tipo);
}


        public ArrayList<Tareas_Escolares> ListaTareas() throws ClassNotFoundException, SQLException{
        int IdTarea =0;
        String Titulo="";
        String Descripcion="";
        String Fecha="";
        String Materia="";
        String Prioridad="";
        String Tipo="";

        ResultSet rs;//recuper datos desde rs y los desgloso o desconpone
        lstTareas =new ArrayList<>();
        rs= mp.ConsultarTarea();
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
                if(columnName.equals("idtareas"))
                    IdTarea=Integer.parseInt(value);
                if (columnName.equals("titulo")) 
                    Titulo=value;
                if(columnName.equals("descripcion"))
                    Descripcion=value;
                if(columnName.equals("fecha"))
                     Fecha=value;
                if(columnName.equals("materia"))
                    Materia=value;
                if(columnName.equals("prioridad"))
                    Prioridad=value;
                if(columnName.equals("tipo"))
                    Tipo=value;
            }
            try
            {
               Tareas_Escolares  objUs=new Tareas_Escolares(Titulo, Descripcion, Fecha,Materia,Prioridad,Tipo);
                lstTareas.add(objUs);
            }
            catch(Exception ex)
            {
                System.out.println(ex.getMessage());
            }
        }
        return lstTareas;
        
    }     


      
    public boolean eliminarTareasTitulo(String titulo){
        boolean resultado=
                mp.EliminarTareaTitulo(titulo);
        return resultado;

    }    

    
    public int modificarTareaTitulo(String Titulo, String Descripcion, String Fecha, String Materia,String Prioridad,String Tipo){
        return mp.ModificarTarea(Titulo, Descripcion, Fecha, Materia,Prioridad,Tipo);
    }    


    
}