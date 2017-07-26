
package Interfaz;

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
public class GestorTareas extends javax.swing.JFrame {
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
  
    public GestorTareas() {
      try {
          //inicializa los componentes agregados a la interfaz
          initComponents();
          //Se inhabilita el maximizado de la ventana
          this.setResizable(false);
          
          setLocationRelativeTo(null);//La ubicacion de la interfaz cera en el centro
          GestorTareas.Hilo hiloHora = new GestorTareas.Hilo();// Creacion de Hilo para reloj
          hiloHora.start(); //Inicializacionde Hilo para reloj
          //Se crea un modelo de tabla para el jtable

          //Se habilita el ordenamiento de  filas
          LlenarTabla();
          tareas=mp.ListaTareas();
          Tareas_Escolares aux;
      } catch (ClassNotFoundException ex) {
          Logger.getLogger(GestorTareas.class.getName()).log(Level.SEVERE, null, ex);
      } catch (SQLException ex) {
          Logger.getLogger(GestorTareas.class.getName()).log(Level.SEVERE, null, ex);
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
  private void limpiarCampos(){
        
        textTitulo.setText("");
        textTipo.setText("");
        textTarea.setText("");
        textMateria.setText("");
        jcbTipo.setSelectedIndex(0);
        jcbPrioridad.setSelectedIndex(0);
        dateFecha.setDate(null);

    }
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
          Logger.getLogger(GestorTareas.class.getName()).log(Level.SEVERE, null, ex);
      } catch (SQLException ex) {
          Logger.getLogger(GestorTareas.class.getName()).log(Level.SEVERE, null, ex);
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
        jPanelTareas = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        textTarea = new javax.swing.JTextArea();
        lblTitulo = new javax.swing.JLabel();
        lblDescripcion = new javax.swing.JLabel();
        dateFecha = new com.toedter.calendar.JDateChooser();
        lblFecha = new javax.swing.JLabel();
        textTitulo = new javax.swing.JTextField();
        lblTipo = new javax.swing.JLabel();
        jcbTipo = new javax.swing.JComboBox<>();
        textTipo = new javax.swing.JTextField();
        textMateria = new javax.swing.JTextField();
        jcbPrioridad = new javax.swing.JComboBox<>();
        textPrioridad = new javax.swing.JTextField();
        lblMateria = new javax.swing.JLabel();
        lblPrioridad = new javax.swing.JLabel();
        btnLimpiar = new javax.swing.JButton();
        JPanelFecha = new javax.swing.JPanel();
        Fecha = new javax.swing.JLabel();
        Hora = new javax.swing.JLabel();
        jPanelVisor = new javax.swing.JPanel();
        lblFiltro = new javax.swing.JLabel();
        comboFiltro = new javax.swing.JComboBox<>();
        txtFiltro = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabla = new javax.swing.JTable();
        btnModificar = new javax.swing.JButton();
        btnAñadir = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        btnExportar = new javax.swing.JButton();
        btnSalir = new javax.swing.JButton();

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Si", "No" }));

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Gestor de Actividades Academicas");
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanelTareas.setBackground(new java.awt.Color(253, 190, 17));
        jPanelTareas.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "DESCRIPCIÓN DE LA TAREA", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Britannic Bold", 0, 12), new java.awt.Color(255, 255, 255))); // NOI18N
        jPanelTareas.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        textTarea.setColumns(10);
        textTarea.setRows(5);
        textTarea.setBorder(null);
        textTarea.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        textTarea.setFocusTraversalPolicyProvider(true);
        jScrollPane2.setViewportView(textTarea);

        jPanelTareas.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 60, 280, 140));

        lblTitulo.setText("Titulo");
        jPanelTareas.add(lblTitulo, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 30, -1, -1));

        lblDescripcion.setText("Descripcion ");
        jPanelTareas.add(lblDescripcion, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 120, -1, -1));

        dateFecha.setMinSelectableDate(fechaDate
        );
        jPanelTareas.add(dateFecha, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 220, 210, 40));

        lblFecha.setText("Fecha");
        jPanelTareas.add(lblFecha, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 230, -1, -1));
        jPanelTareas.add(textTitulo, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 13, 290, 40));

        lblTipo.setText("Tipo");
        jPanelTareas.add(lblTipo, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 410, -1, -1));

        jcbTipo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccione", "Examen", "Tarea Extraclase", "Proyecto", "Leccion", "Otros" }));
        jcbTipo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcbTipoActionPerformed(evt);
            }
        });
        jPanelTareas.add(jcbTipo, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 390, 210, 50));

        textTipo.setBackground(new java.awt.Color(253, 190, 17));
        textTipo.setForeground(new java.awt.Color(253, 190, 17));
        textTipo.setCaretColor(new java.awt.Color(253, 190, 17));
        textTipo.setDisabledTextColor(new java.awt.Color(253, 190, 17));
        textTipo.setEnabled(false);
        textTipo.setOpaque(false);
        textTipo.setSelectedTextColor(new java.awt.Color(253, 190, 17));
        textTipo.setSelectionColor(new java.awt.Color(253, 190, 17));
        jPanelTareas.add(textTipo, new org.netbeans.lib.awtextra.AbsoluteConstraints(95, 410, 200, 30));
        textTipo.getAccessibleContext().setAccessibleDescription("");

        jPanelTareas.add(textMateria, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 260, 210, 50));

        jcbPrioridad.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccione", "Alta", "Media", "Baja" }));
        jPanelTareas.add(jcbPrioridad, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 320, 210, 50));

        textPrioridad.setBackground(new java.awt.Color(253, 190, 17));
        textPrioridad.setForeground(new java.awt.Color(253, 190, 17));
        textPrioridad.setCaretColor(new java.awt.Color(253, 190, 17));
        textPrioridad.setDisabledTextColor(new java.awt.Color(253, 190, 17));
        textPrioridad.setEnabled(false);
        textPrioridad.setOpaque(false);
        textPrioridad.setSelectedTextColor(new java.awt.Color(253, 190, 17));
        textPrioridad.setSelectionColor(new java.awt.Color(253, 190, 17));
        textPrioridad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textPrioridadActionPerformed(evt);
            }
        });
        jPanelTareas.add(textPrioridad, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 340, 190, 30));

        lblMateria.setText("Materia");
        jPanelTareas.add(lblMateria, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 280, -1, -1));

        lblPrioridad.setText("Prioridad");
        jPanelTareas.add(lblPrioridad, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 340, -1, -1));

        btnLimpiar.setText("Limpiar");
        btnLimpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLimpiarActionPerformed(evt);
            }
        });
        jPanelTareas.add(btnLimpiar, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 450, -1, -1));

        getContentPane().add(jPanelTareas, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 370, 480));

        JPanelFecha.setBackground(new java.awt.Color(13, 67, 113));
        JPanelFecha.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "FECHA Y HORA ACTUAL", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Britannic Bold", 0, 12), new java.awt.Color(255, 255, 255))); // NOI18N

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
                .addComponent(Fecha, javax.swing.GroupLayout.DEFAULT_SIZE, 364, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(Hora, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        JPanelFechaLayout.setVerticalGroup(
            JPanelFechaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JPanelFechaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(JPanelFechaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Fecha)
                    .addComponent(Hora, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        getContentPane().add(JPanelFecha, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 0, 610, 60));

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
        tabla.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaMouseClicked(evt);
            }
        });
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 120, Short.MAX_VALUE)
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
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 354, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(84, 84, 84))
        );

        getContentPane().add(jPanelVisor, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 60, 610, 420));

        btnModificar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/GestorTareas/Recursos/modificar-una-pluma-para-escribir-el-archivo-icono-7841-32.png"))); // NOI18N
        btnModificar.setText("<html><b>Modificar<br>Tarea</b></html>\"  ");
        btnModificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificarActionPerformed(evt);
            }
        });
        getContentPane().add(btnModificar, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 490, 150, 40));

        btnAñadir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/GestorTareas/Recursos/anadir-una-ficha-icono-4799-32.png"))); // NOI18N
        btnAñadir.setText("<html><b>Añadir<br>Tarea</b></html>\"  ");
        btnAñadir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAñadirActionPerformed(evt);
            }
        });
        getContentPane().add(btnAñadir, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 490, 130, 40));

        btnEliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/GestorTareas/Recursos/eliminar-fila-de-la-tabla-icono-6942-32.png"))); // NOI18N
        btnEliminar.setText("<html><b>Eliminar<br>Tarea</b></html>\"  ");
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });
        getContentPane().add(btnEliminar, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 490, 140, 40));

        btnExportar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/GestorTareas/Recursos/guardar-la-tabla-icono-7955-32.png"))); // NOI18N
        btnExportar.setText("<html><b>Exportar<br>CSV y XLSX</b></html>\"  ");
        btnExportar.setActionCommand("\nExportar tareas a un Archivo\nCSV y XLSX");
        btnExportar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExportarActionPerformed(evt);
            }
        });
        getContentPane().add(btnExportar, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 490, 170, 40));

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
        getContentPane().add(btnSalir, new org.netbeans.lib.awtextra.AbsoluteConstraints(900, 490, 60, 40));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jcbTipoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcbTipoActionPerformed
        // TODO add your handling code here:
        //Auxiliar que permitio verificar que se estaba capturando correctamente el contenido del ComboBOx
        textTipo.setText (jcbTipo.getSelectedItem().toString());
    }//GEN-LAST:event_jcbTipoActionPerformed

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

    private void btnModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarActionPerformed
        // TODO add your handling code here
        //Se verifica que el ArrayList se encuentre llena o con informacion por consiguiente el Jtable tambien 
            //Se verifica que el usuario haya seleccionado una fila

            if (pos>=0) {
                
                //Se modifica los valores ingresados por el usuario
                //setValue At(Variable o jtext, numero de fila, numero de columna)
                String fecha= new SimpleDateFormat("dd/MM/yyyy").format(dateFecha.getDate());
                if (jcbPrioridad.getSelectedIndex()>0|| jcbTipo.getSelectedIndex()>0) {
                    try {
                        mp.modificarTareaTitulo(textTitulo.getText(),textTarea.getText(),fecha ,textMateria.getText(),jcbPrioridad.getSelectedItem().toString(),jcbTipo.getSelectedItem().toString());
                        //Se muestra una notificacion que informa al usuario de la modificacion exitosa de la tarea
                        JOptionPane.showMessageDialog(this, "Tarea Modificada con Exito","Informacion",JOptionPane.INFORMATION_MESSAGE);
                        //Se emplea el metodo limpiarCampos
                        limpiarCampos();
                        LlenarTabla();
                        tabla.clearSelection();   
                    } catch (ClassNotFoundException ex) {
                        Logger.getLogger(GestorTareas.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (SQLException ex) {
                        Logger.getLogger(GestorTareas.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    
                }else{
                    JOptionPane.showMessageDialog(this, "Seleccione un valor permitido","Advertencia",JOptionPane.WARNING_MESSAGE);
                }
            }else{
               //Si el usuario no selecciona una fila se advertira con una notificacion
                JOptionPane.showMessageDialog(this, "Seleccione una Fila","Advertencia",JOptionPane.WARNING_MESSAGE);
            }
    
    }//GEN-LAST:event_btnModificarActionPerformed

    private void btnAñadirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAñadirActionPerformed
        // TODO add your handling code here:
        /**
         * Funcion del boton anadir, que permite anadir una nueva tarea
         */
        //Se valida que todos los componentes o campos se encuentren con informacion
        if (textTitulo.getText().length()==0||textMateria.getText().length()==0||textTarea.getText().length()==0||jcbTipo.getSelectedIndex()==0
                ||jcbPrioridad.getSelectedIndex()==0|| dateFecha.getDate().equals(null)) {
           //Si se encuentra algun componente sin informacion se deshabilita la funcion de este boton
            boolean band=true;//Bandera establecida en true
            //En caso de encontrar que la bandera sea true se mostrara un mensaje advirtiendo que algunos campos estan vacios
             if (band=true) {
                JOptionPane.showMessageDialog(this, "Boton Desabilitado\nAlgunos Campos estan Vacios","Informacion",JOptionPane.WARNING_MESSAGE);
            }
        }else{
            try {
                /**
                 *Si se verifica que todos los campos estan llenos se empieza a capturar la informacion
                 * ingresada en los jtext, jcomboBox y jdate, se realizan las respectivas conversiones a
                 * String del jdate y jcombobox, y se asigna al objeto del tipo Tareas_Escolares, la informacion
                 * capturada por estos, para luego ingresarlos al jtable y al arrayList, al finalizar este proceso
                 * se informara al usuario de la finalizacion exitosa de este proceso
                 */
                
                DefaultTableModel modelo=(DefaultTableModel)tabla.getModel();
                String fecha= new SimpleDateFormat("dd/MM/yyyy").format(dateFecha.getDate());
                //String titulo, String descipcion, String fecha, String materia, String prioridad, String tipo
               
                mp.InsertarTarea(textTitulo.getText(),textTarea.getText(),fecha,textMateria.getText(),jcbPrioridad.getSelectedItem().toString(),jcbTipo.getSelectedItem().toString());
                LlenarTabla();
                JOptionPane.showMessageDialog(this, "Tarea Añadida con Exito","Informacion",JOptionPane.INFORMATION_MESSAGE);
                limpiarCampos();
                habilitarBtn(btnModificar);
                habilitarBtn(btnEliminar);
                habilitarBtn(btnExportar);
                jcbTipo.setSelectedIndex(0);
                jcbPrioridad.setSelectedIndex(0);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(GestorTareas.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(GestorTareas.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_btnAñadirActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        // TODO add your handling code here:
           //Se valida que se haya seleccionado una fila
            if (pos>=0) {
                //Se elimina la fila seleccionada mediante el metodo removeRow (EliminarFila)
                EliminarTarea(titulo);
                //Se informa al usuario de la finalizacion exitosa del proceso
                JOptionPane.showMessageDialog(this, "Tarea Eliminada con Exito","Informacion",JOptionPane.INFORMATION_MESSAGE);
                limpiarCampos();
                tabla.clearSelection();
                pos=0;
            }else{
                //Se informa al usuario que debe seleccionar una fila
            JOptionPane.showMessageDialog(this, "Seleccione una Fila","Advertencia",JOptionPane.WARNING_MESSAGE);
            }

    }//GEN-LAST:event_btnEliminarActionPerformed

    private void btnExportarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExportarActionPerformed
        // TODO add your handling code here:
        //Se declara un objeto del tipo Formatter para la escritura de un archivo CSV
         Formatter outFile;
         // Se valida que le arrayList se encuentre con informacion /Registros
        if (tabla.getRowCount() > 0) {
            //JFileChooser nos permite Elegir una ubicacion y nombre para abrir/guardar un archivo 
            JFileChooser chooser = new JFileChooser();
            //Tipo de archivo
            FileNameExtensionFilter filter = new FileNameExtensionFilter("Archivo xls y csv", "xls");
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
                    outFile.format("%s;%s;%s;%s;%s;%s;\n","Fecha","Titulo","Descripcion","Materia","Prioridad","Tipo");
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

    private void textPrioridadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textPrioridadActionPerformed
        // TODO add your handling code here:
        //Auxiliar que permitio verificar que se estaba capturando correctamente el contenido del ComboBOx
        textPrioridad.setText (jcbPrioridad.getSelectedItem().toString());
    }//GEN-LAST:event_textPrioridadActionPerformed

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
                    System.exit(0);

        }else{
            //LA APP Permanece abierta
        }
               
    }//GEN-LAST:event_btnSalirActionPerformed

    private void btnLimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimpiarActionPerformed
        // TODO add your handling code here:
        limpiarCampos();
         tabla.clearSelection();
         pos=0;
    }//GEN-LAST:event_btnLimpiarActionPerformed

    private void tablaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaMouseClicked
        //Se define el modelo de la tabla
        DefaultTableModel modelo=(DefaultTableModel)tabla.getModel();
        //Se asigna a posicion la fila que el usuario selecciono en el jTable
        pos=tabla.getSelectedRow();
        //Se valida que se haya seleccionado una fila
        if (pos>0) {
            Date fechaDate=null;
            String Sfecha;
            try {
                /**
                 * Se muestra la informacion seleccionada en los TextFields correspondientes
                 */
                
                Sfecha=modelo.getValueAt(pos,0).toString();
                fechaDate = formato2.parse(Sfecha);
                        textTitulo.setText(modelo.getValueAt(pos,1).toString());
                        textTarea.setText(modelo.getValueAt(pos, 2).toString());
                        titulo=modelo.getValueAt(pos, 1).toString();
                        dateFecha.setDate(fechaDate);
                        textMateria.setText(modelo.getValueAt(pos,3).toString());
                        textPrioridad.setText(modelo.getValueAt(pos, 4).toString());
                        textTipo.setText(modelo.getValueAt(pos, 5).toString());
                        jcbTipo.setSelectedItem(textTipo.getText());
                        jcbPrioridad.setSelectedItem(textPrioridad.getText());
                        
                        
                        // TODO add your handling code here:
            } catch (ParseException ex) {
                Logger.getLogger(GestorTareas.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_tablaMouseClicked
 

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Fecha;
    private javax.swing.JLabel Hora;
    private javax.swing.JPanel JPanelFecha;
    private javax.swing.JButton btnAñadir;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnExportar;
    private javax.swing.JButton btnLimpiar;
    private javax.swing.JButton btnModificar;
    private javax.swing.JButton btnSalir;
    private javax.swing.JComboBox<String> comboFiltro;
    private com.toedter.calendar.JDateChooser dateFecha;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JPanel jPanelTareas;
    private javax.swing.JPanel jPanelVisor;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JComboBox<String> jcbPrioridad;
    private javax.swing.JComboBox<String> jcbTipo;
    private javax.swing.JLabel lblDescripcion;
    private javax.swing.JLabel lblFecha;
    private javax.swing.JLabel lblFiltro;
    private javax.swing.JLabel lblMateria;
    private javax.swing.JLabel lblPrioridad;
    private javax.swing.JLabel lblTipo;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JTable tabla;
    private javax.swing.JTextField textMateria;
    private javax.swing.JTextField textPrioridad;
    private javax.swing.JTextArea textTarea;
    private javax.swing.JTextField textTipo;
    private javax.swing.JTextField textTitulo;
    private javax.swing.JTextField txtFiltro;
    // End of variables declaration//GEN-END:variables
}
