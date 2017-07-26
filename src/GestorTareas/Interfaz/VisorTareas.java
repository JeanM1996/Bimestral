
package GestorTareas.Interfaz;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.text.SimpleDateFormat;
import javax.swing.JOptionPane;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import Diseño.Exporter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JTable;
import javax.swing.filechooser.FileNameExtensionFilter;
import BL.BLTareas;
import Diseño.Tareas_Escolares;
import ModuloUsuarios.Principal;
import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Formatter;
import java.util.Scanner;
import javax.swing.JButton;

        

/**
 *Clase Interfaz Asistente_Tareas (Gestor)
 * @author Jean Paul Mosquera Arevalo
 * @version 1.1
 */
public class VisorTareas extends javax.swing.JFrame {
  private int pos; //Variable para indicar posicion en tabla o arreglo
  String materia; //Variable para almacenar la materia
  String titulo;
  BLTareas mp= new BLTareas();
  //Creacion de un ArrayList del tipo Tareas_Escolares
  static ArrayList<Tareas_Escolares> tareas;
  //Instanciacion de la clase (objeto) Tareas_Escolares
  Tareas_Escolares aux=new Tareas_Escolares();

  Tareas_Escolares dto = new Tareas_Escolares();

    /**
     * form Tareas
     */
  
    public VisorTareas() {
      try {
          //inicializa los componentes agregados a la interfaz
          initComponents();
          //Se inhabilita el maximizado de la ventana
          this.setResizable(false);
          
          setLocationRelativeTo(null);//La ubicacion de la interfaz cera en el centro
          VisorTareas.Hilo hiloHora = new VisorTareas.Hilo();// Creacion de Hilo para reloj
          hiloHora.start(); //Inicializacionde Hilo para reloj
          //Se crea un modelo de tabla para el jtable

          //Se habilita el ordenamiento de  filas
          LlenarTabla();
          tareas=mp.ListaTareas();
          Tareas_Escolares aux;
      } catch (ClassNotFoundException ex) {
          Logger.getLogger(VisorTareas.class.getName()).log(Level.SEVERE, null, ex);
      } catch (SQLException ex) {
          Logger.getLogger(VisorTareas.class.getName()).log(Level.SEVERE, null, ex);
      }
         
    }
        private TableRowSorter trsFiltro;
        //Metodo para filtrar las tareas agregadas en funcion a sus distintos parametros
        public void filtro() {
        //Variable que definira la columna donde se buscara el elemento en cuestion
        int columnaABuscar = 0;
        /**
         * En la funcion o seccion de filtrado se ha implementado un jComboBox, donde
         * se encontraran los parametros (columnas) en funcion de los que se desea filtrar
         * las tareas // si es fecha se buscara en la columna 0 (1), si es titulo dicha opcion
         * se buscara en la columna 1(2) y asi sucesivamente
         */
        if (comboFiltro.getSelectedItem() == "Fecha") {
            columnaABuscar = 0;
        }
        if (comboFiltro.getSelectedItem().toString() == "Titulo") {
            columnaABuscar = 1;
        }
        if (comboFiltro.getSelectedItem() == "Descripcion") {
            columnaABuscar = 2;
        }
        if(comboFiltro.getSelectedItem()=="Materia"){
            columnaABuscar=3;
        }
        if(comboFiltro.getSelectedItem()=="Prioridad"){
            columnaABuscar=5;
        }
        if(comboFiltro.getSelectedItem()=="Tipo"){
            columnaABuscar=4;
        }


        //Nos permite filtrar el contenido
        trsFiltro.setRowFilter(RowFilter.regexFilter(txtFiltro.getText(), columnaABuscar));

        }
        
        // Fecha FORMATO 23 de Enero de 2016
        SimpleDateFormat formato1 = new SimpleDateFormat(
            "dd 'de' MMMM 'de' yyyy", Locale.getDefault());
        SimpleDateFormat formato2= new SimpleDateFormat("dd/MM/yyyy");
        Date fechaDate = new Date();
        String fecha = formato1.format(fechaDate);

        /**
         * Metodo para limpiar los campos, se lo emplea despues de pulsado el boton
         * eliminar, anadir y modificar
         * Se establece el jText determinado como una cadena vacia("")
         */

  private void deshabilitarBtn(JButton boton){
      boton.setEnabled(false);
  }
  private void habilitarBtn(JButton boton){
      boton.setEnabled(true);
  }

   
   public void EliminarTarea(String titulo){
       mp.eliminarTareasTitulo(titulo);
       pos=0;
      try {
          LlenarTabla();
      } catch (ClassNotFoundException ex) {
          Logger.getLogger(VisorTareas.class.getName()).log(Level.SEVERE, null, ex);
      } catch (SQLException ex) {
          Logger.getLogger(VisorTareas.class.getName()).log(Level.SEVERE, null, ex);
      }
   }

   
    public final void LlenarTabla() throws ClassNotFoundException, SQLException{
            ArrayList<Tareas_Escolares> lsttareas=mp.ListaTareas();
           
            Object columnas[] = {"Fecha","Titulo","Descripcion", "Materia", "Tipo","Prioridad"};
            DefaultTableModel modelo = new DefaultTableModel(null, columnas);
            tabla.setModel(modelo); 
            
            for (Tareas_Escolares objeto : lsttareas)
            {
                //String id=String.valueOf(objeto.getStrCodigo());
                String fecha=String.valueOf(objeto.getFecha());
                String titulo=String.valueOf(objeto.getTitulo());
                String descripcion=String.valueOf(objeto.getDescripcion());
                String materia=String.valueOf(objeto.getMateria());
                String prioridad=String.valueOf(objeto.getPrioridad());
                String tipo=String.valueOf(objeto.getTipo());
                String modeloTemp[] = {fecha,titulo,descripcion,materia,prioridad,tipo};
                modelo.addRow(modeloTemp);
            }
 }
//Hilo para reloj
    class Hilo extends Thread {

        @Override
        public void run() {
            while (true) {
                //Se optienes las horas, minutos, segundos
                Date date = new Date();
                int horas = date.getHours();
                int minutos = date.getMinutes();
                int segundos = date.getSeconds();
               // System.out.println(horas + ":" + minutos + ":" + segundos);
                Hora.setText("Hora : "+horas + ":" + minutos + ":" + segundos);
                //Codigo propenso a fallos (Excepciones)
                try {
                    //Necesario para que no haya interrupcion
                    Thread.sleep(1000);
                } catch (InterruptedException ex) {
                    Logger.getLogger(Hilo.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jComboBox1 = new javax.swing.JComboBox<>();
        JPanelFecha = new javax.swing.JPanel();
        Fecha = new javax.swing.JLabel();
        Hora = new javax.swing.JLabel();
        jPanelVisor = new javax.swing.JPanel();
        lblFiltro = new javax.swing.JLabel();
        comboFiltro = new javax.swing.JComboBox<>();
        txtFiltro = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabla = new javax.swing.JTable();
        btnExportar = new javax.swing.JButton();
        btnSalir = new javax.swing.JButton();

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Si", "No" }));

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Gestor de Actividades Academicas");
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        JPanelFecha.setBackground(new java.awt.Color(253, 190, 17));
        JPanelFecha.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Informacion", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Britannic Bold", 0, 12), new java.awt.Color(255, 255, 255))); // NOI18N

        Fecha.setFont(new java.awt.Font("Britannic Bold", 0, 14)); // NOI18N
        Fecha.setForeground(new java.awt.Color(255, 255, 255));
        Fecha.setText("Fecha :  " +fecha);

        Hora.setFont(new java.awt.Font("Britannic Bold", 0, 14)); // NOI18N
        Hora.setForeground(new java.awt.Color(255, 255, 255));
        Hora.setText("Hora");

        javax.swing.GroupLayout JPanelFechaLayout = new javax.swing.GroupLayout(JPanelFecha);
        JPanelFecha.setLayout(JPanelFechaLayout);
        JPanelFechaLayout.setHorizontalGroup(
            JPanelFechaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JPanelFechaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Fecha, javax.swing.GroupLayout.PREFERRED_SIZE, 246, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, JPanelFechaLayout.createSequentialGroup()
                .addContainerGap(246, Short.MAX_VALUE)
                .addComponent(Hora, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(58, 58, 58))
        );
        JPanelFechaLayout.setVerticalGroup(
            JPanelFechaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JPanelFechaLayout.createSequentialGroup()
                .addComponent(Fecha)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 22, Short.MAX_VALUE)
                .addComponent(Hora, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        getContentPane().add(JPanelFecha, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 520, 80));

        jPanelVisor.setBackground(new java.awt.Color(13, 67, 113));
        jPanelVisor.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "TAREAS", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Britannic Bold", 0, 12), new java.awt.Color(255, 255, 255))); // NOI18N

        lblFiltro.setForeground(new java.awt.Color(255, 255, 255));
        lblFiltro.setText("Buscar Por:");

        comboFiltro.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Fecha", "Titulo", "Descripcion", "Materia", "Prioridad", "Tipo", "Completa" }));

        txtFiltro.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtFiltroKeyTyped(evt);
            }
        });

        tabla.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(tabla);

        javax.swing.GroupLayout jPanelVisorLayout = new javax.swing.GroupLayout(jPanelVisor);
        jPanelVisor.setLayout(jPanelVisorLayout);
        jPanelVisorLayout.setHorizontalGroup(
            jPanelVisorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelVisorLayout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(lblFiltro, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(comboFiltro, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 30, Short.MAX_VALUE)
                .addComponent(txtFiltro, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(101, 101, 101))
            .addGroup(jPanelVisorLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1))
        );
        jPanelVisorLayout.setVerticalGroup(
            jPanelVisorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelVisorLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanelVisorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblFiltro)
                    .addComponent(comboFiltro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtFiltro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 314, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(124, 124, 124))
        );

        getContentPane().add(jPanelVisor, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 80, 520, 400));

        btnExportar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/GestorTareas/Recursos/guardar-la-tabla-icono-7955-32.png"))); // NOI18N
        btnExportar.setText("<html><b>Exportar<br>CSV y XLSX</b></html>\"  ");
        btnExportar.setActionCommand("\nExportar tareas a un Archivo\nCSV y XLSX");
        btnExportar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExportarActionPerformed(evt);
            }
        });
        getContentPane().add(btnExportar, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 490, 170, 40));

        btnSalir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/GestorTareas/Recursos/liberar-icono-5826-32.png"))); // NOI18N
        btnSalir.setIconTextGap(0);
        btnSalir.setMargin(new java.awt.Insets(0, 0, 0, 0));
        btnSalir.setMaximumSize(new java.awt.Dimension(20, 20));
        btnSalir.setMinimumSize(new java.awt.Dimension(30, 30));
        btnSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalirActionPerformed(evt);
            }
        });
        getContentPane().add(btnSalir, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 490, 60, 40));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtFiltroKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtFiltroKeyTyped
        // TODO add your handling code here:
        /**
         * Al escribir el usuario en el jtext de la seccion filtro 
         * se buscara filtrara y se mostrara en la tabla las unicas coincidencias encontradas
         * de ser el caso
         */
        txtFiltro.addKeyListener(new KeyAdapter() {
            public void keyReleased(final KeyEvent e) {
                String cadena = (txtFiltro.getText());
                txtFiltro.setText(cadena);
                repaint();
                filtro();
            }
        });
        trsFiltro = new TableRowSorter(tabla.getModel());
        tabla.setRowSorter(trsFiltro);
    }//GEN-LAST:event_txtFiltroKeyTyped

    private void btnExportarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExportarActionPerformed
        // TODO add your handling code here:
        //Se declara un objeto del tipo Formatter para la escritura de un archivo CSV
         Formatter outFile;
         // Se valida que le arrayList se encuentre con informacion /Registros
        if (tabla.getRowCount() > 0) {
            //JFileChooser nos permite Elegir una ubicacion y nombre para abrir/guardar un archivo 
            JFileChooser chooser = new JFileChooser();
            //Tipo de archivo
            FileNameExtensionFilter filter = new FileNameExtensionFilter("Archivo", "xls");
            //Nos permite filtrar los archivos en funcion de su ext
            chooser.setFileFilter(filter);
            //TItulo de la ventana
            chooser.setDialogTitle("Guardar archivo");
            chooser.setAcceptAllFileFilterUsed(false);
            //En caso de que se selccione la ubicacion y el boton guardar se iniciara este proceso
            if (chooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
                //Se crea una lista en funcion al Jtable a exportar
                List<JTable> tb = new ArrayList<JTable>();
                //Se crea un arrayList de cadenas
                List<String> nom = new ArrayList<String>();
                //Se anadie la tabla al arrayList
                tb.add(tabla);
                nom.add("Tareas");
                //Para definir el nombre y extension del archivo
                String nombre= chooser.getSelectedFile().toString();
                String file = chooser.getSelectedFile().toString().concat(".xls");
                //Bloque propenso a excepciones
                try {
                    //Se instancia el objeto del tipo formater junto con el nombre del archivo y la extension csv(concatenados
                    outFile= new Formatter(nombre+".csv"); 
                    //Se escribe el encabezado en el archivo
                    outFile.format("%s;%s;%s;%s;%s;%s;%s;\n","Fecha","Titulo","Descripcion","Materia","Prioridad","Tipo","Completa");
                    //Form mejorado para leer la lista generada de tareas
                    for (Tareas_Escolares tarea: tareas){
                        //Se escribe en el archivo las tareas con sus campos
                        outFile.format("%s;%s;%s;%s;%s;%s;\n",tarea.getFecha(),tarea.getTitulo(),tarea.getDescripcion(),tarea.getMateria(),tarea.getPrioridad(),tarea.getTipo(),jComboBox1.getSelectedItem().toString());
                    }//Fin Bloque FOR
                    //Se finaliza el flujo
                    outFile.close();
                    //se intancia la clase Exporter que nos permite generar un archivo xlsx con las tareas
                    Exporter e = new Exporter(new File(file), tb, nom);
                    //En caso de cumplirse correctamente o de presentarse algun error en  la creacion del archivo se informara al usuario con una notificacion
                    if (e.export()) {
                        JOptionPane.showMessageDialog(null, "Los datos fueron exportados correctamente  en el directorio seleccionado\n"+ nombre +"\n En formatos CSV y XLS", "Mensaje de Informacion", JOptionPane.INFORMATION_MESSAGE);
                    }
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, "Hubo un error " + e.getMessage(), " Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        }else{
            //En caso de encontrarse la tabla vacia se informara al usuario que no es posible realizar la accion
            JOptionPane.showMessageDialog(this, "Boton Desabilitado\nNo existen datos para exportar","Mensaje de error",JOptionPane.WARNING_MESSAGE);
            deshabilitarBtn(btnExportar);
        }
    }//GEN-LAST:event_btnExportarActionPerformed

    private void btnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalirActionPerformed
        // TODO add your handling code here:
        //Se declara un arreglo del tipo Object con las opciones (Botones)
        Object [] opciones ={"Aceptar","Cancelar"};
        //Se muestra el mensaje informativo con las opciones antes indicadas, la variable opcion de eleccion es de tipo int puesto las opciones serian SI o No (1/0)
        int eleccion = JOptionPane.showOptionDialog(rootPane,"En realidad desea salir de la aplicacion","Mensaje de Confirmacion",
        JOptionPane.YES_NO_OPTION,
        JOptionPane.QUESTION_MESSAGE,null,opciones,"Aceptar");
        //Si se valida que la opcion es Si,(aceptar)(1), se realizara un archivo CSV de copia de seguridad
        if (eleccion == JOptionPane.YES_OPTION){
                
                    //Se muestra un mensaje al usuario agradeciendo el uso de la app
                    JOptionPane.showMessageDialog(this, "Cierre de Sesion Correcto, Hasta Pronto","Informacion",JOptionPane.INFORMATION_MESSAGE);
                    //Se cierra el proceso
                    this.dispose();
                   System.exit(0);

        }else{
            //LA APP Permanece abierta
        }
               
    }//GEN-LAST:event_btnSalirActionPerformed
  

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Fecha;
    private javax.swing.JLabel Hora;
    private javax.swing.JPanel JPanelFecha;
    private javax.swing.JButton btnExportar;
    private javax.swing.JButton btnSalir;
    private javax.swing.JComboBox<String> comboFiltro;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JPanel jPanelVisor;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblFiltro;
    private javax.swing.JTable tabla;
    private javax.swing.JTextField txtFiltro;
    // End of variables declaration//GEN-END:variables
}
