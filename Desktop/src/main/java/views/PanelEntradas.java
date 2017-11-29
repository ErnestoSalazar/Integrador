/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import entities.Barco;
import entities.Carga;
import static entities.Constantes.*;
import entities.Entrada;
import java.awt.Cursor;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.DateFormatter;
import session.Peticiones;
import session.Token;

/**
 *
 * @author fernandomarenco
 */
public class PanelEntradas extends javax.swing.JPanel {

    /**
     * Creates new form PanelEntradas
     */
    public PanelEntradas() {
        initComponents();
    }
    
    public PanelEntradas(boolean set) {
        initComponents();
        
        setTableEntradas();
        
        formEntrada.setSize(500, 530);
        formEntrada.setLocationRelativeTo(null);
        formEntrada.setResizable(false);
        
        formCarga.setSize(500, 300);
        formCarga.setLocationRelativeTo(null);
        formCarga.setResizable(false);
    }
    
    Peticiones p = new Peticiones();
    
    //listas al consultar al servicio
    List<Entrada> entradas = new ArrayList<>();
    List<Carga> cargasEntrada = new ArrayList<>();
    
    //entrada seleccionada
    Entrada entrada;
    String idEntrada;
    
    //combo box obtener barcos que existen
    List<Barco> barcos = new ArrayList<>();
    
    //guardar las cargas al agregar
    List<Carga> cargasTemporales = new ArrayList<>();
    
    
    public void setTableEntradas() {
        setCursor(new Cursor(Cursor.WAIT_CURSOR));
        MainView.tbMain.setCursor(new Cursor(Cursor.WAIT_CURSOR));
        
        DefaultTableModel model = new DefaultTableModel() {
            @Override
            public boolean isCellEditable (int fila, int columna) {
                return false;
            }
        };
        model.setColumnCount(0);
        model.setRowCount(0);
        tblEntradas.setModel(model);
        
        
        //columnas
        String[] columnas = {"Folio", "Fecha", "Hora", "Turno", "Generado por", "# Cargas", "ID"};
        for (String columna : columnas) {
            model.addColumn(columna);
        }
        
        tblEntradas.getColumnModel().getColumn(columnas.length-1).setMaxWidth(0);
        tblEntradas.getColumnModel().getColumn(columnas.length-1).setMinWidth(0);
        tblEntradas.getTableHeader().getColumnModel().getColumn(columnas.length-1).setMaxWidth(0);
        tblEntradas.getTableHeader().getColumnModel().getColumn(columnas.length-1).setMinWidth(0);
        
        
        //consultar entradas
        String json = p.getAll(ENTRADAS, Token.getToken());
        
        //si no hay entradas
        if(!json.equals("")) {
            entradas = new Entrada().getListEntradas(json);
            
            DateTimeFormatter fechaFormatter = DateTimeFormatter.ofPattern("dd-MM-uuuu");
            DateTimeFormatter horaFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
            
            //filas
            Object[] filas = new Object[columnas.length];
            for (int i = 0; i < entradas.size(); i++) {
                filas[0] = entradas.get(i).getFolio();
                
                LocalDate f = LocalDate.parse(entradas.get(i).getFecha().substring(0, 10));
                filas[1] = fechaFormatter.format(f);
                
                LocalTime h = LocalTime.parse(entradas.get(i).getHora());
                filas[2] = horaFormatter.format(h);
                
                filas[3] = entradas.get(i).getTurno();
                filas[4] = entradas.get(i).getUsuario().getNombre() +" "+ entradas.get(i).getUsuario().getApellido();
                filas[5] = entradas.get(i).getCargas().size();
                filas[6] = entradas.get(i).getId();
                
                model.addRow(filas);
            }
            
            btnBuscarEntrada.setEnabled(true);
            
        } else {
            btnBuscarEntrada.setEnabled(false);
        }
        
        
        setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
        MainView.tbMain.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
        
    }
    
    public void setTableCargas() {
        setCursor(new Cursor(Cursor.WAIT_CURSOR));
        MainView.tbMain.setCursor(new Cursor(Cursor.WAIT_CURSOR));
        
        DefaultTableModel model = new DefaultTableModel() {
            @Override
            public boolean isCellEditable (int fila, int columna) {
                return false;
            }
        };
        model.setColumnCount(0);
        model.setRowCount(0);
        tblCargas.setModel(model);
        
        
        //columnas
        String[] columnas = {"*", "Barco", "Cantidad", "Especie", "Talla", "Temperatura", "Condición", "ID", "ID Barco"};
        for (String columna : columnas) {
            model.addColumn(columna);
        }
        
        tblCargas.getColumnModel().getColumn(0).setMaxWidth(10);
        tblCargas.getColumnModel().getColumn(0).setMinWidth(10);
        
        tblCargas.getColumnModel().getColumn(columnas.length-1).setMaxWidth(0);
        tblCargas.getColumnModel().getColumn(columnas.length-1).setMinWidth(0);
        tblCargas.getTableHeader().getColumnModel().getColumn(columnas.length-1).setMaxWidth(0);
        tblCargas.getTableHeader().getColumnModel().getColumn(columnas.length-1).setMinWidth(0);
        
        tblCargas.getColumnModel().getColumn(columnas.length-2).setMaxWidth(0);
        tblCargas.getColumnModel().getColumn(columnas.length-2).setMinWidth(0);
        tblCargas.getTableHeader().getColumnModel().getColumn(columnas.length-2).setMaxWidth(0);
        tblCargas.getTableHeader().getColumnModel().getColumn(columnas.length-2).setMinWidth(0);
        
        
        //guardar cargas
        String getEntrada = p.get(MODIFY_ENTRADAS, idEntrada, Token.getToken());
        entrada = new Entrada().jsonToEntrada(getEntrada);
        
        cargasEntrada = entrada.getCargas();
        
        System.out.println("listando"+entrada.getCargas());
        
        //si hay entradas mostrar entradas existentes
        if(cargasEntrada.size() > 0) {
            //filas
            Object[] filas = new Object[columnas.length];

            for (int i = 0; i < cargasEntrada.size(); i++) {
                filas[0] = "-";
                
                filas[1] = cargasEntrada.get(i).getBarco().getNombre();
                
                filas[2] = cargasEntrada.get(i).getCantidad();
                filas[3] = cargasEntrada.get(i).getEspecie();
                filas[4] = cargasEntrada.get(i).getTalla();
                filas[5] = cargasEntrada.get(i).getTemperatura();
                filas[6] = cargasEntrada.get(i).getCondicion();
                filas[7] = cargasEntrada.get(i).getId();
                
                filas[8] = cargasEntrada.get(i).getBarco().getId();

                model.addRow(filas);
            }
        }
        
        //si hay cargas temporales
        if (cargasTemporales.size() > 0) {
            Object[] filas = new Object[columnas.length];
            for (int i = 0; i < cargasTemporales.size(); i++) {
                filas[0] = "+";
                
                filas[1] = cargasTemporales.get(i).getBarco().getNombre();

                filas[2] = cargasTemporales.get(i).getCantidad();
                filas[3] = cargasTemporales.get(i).getEspecie();
                filas[4] = cargasTemporales.get(i).getTalla();
                filas[5] = cargasTemporales.get(i).getTemperatura();
                filas[6] = cargasTemporales.get(i).getCondicion();
                
                filas[7] = 0; //0 porque es una carga sin id
                
                filas[8] = cargasTemporales.get(i).getBarcoId();

                model.addRow(filas);
            }
        }
        
        setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
        MainView.tbMain.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
        
        
    }
    
    public void setTableCargasTemporal() {
        
        DefaultTableModel model = new DefaultTableModel() {
            @Override
            public boolean isCellEditable (int fila, int columna) {
                return false;
            }
        };
        model.setColumnCount(0);
        model.setRowCount(0);
        tblCargas.setModel(model);
        
        
        //columnas
        String[] columnas = {"*", "Barco", "Cantidad", "Especie", "Talla", "Temperatura", "Condición", "ID Barco"};
        for (String columna : columnas) {
            model.addColumn(columna);
        }
        
        
        tblCargas.getColumnModel().getColumn(0).setMaxWidth(10);
        tblCargas.getColumnModel().getColumn(0).setMinWidth(10);
        
        tblCargas.getColumnModel().getColumn(columnas.length-1).setMaxWidth(0);
        tblCargas.getColumnModel().getColumn(columnas.length-1).setMinWidth(0);
        tblCargas.getTableHeader().getColumnModel().getColumn(columnas.length-1).setMaxWidth(0);
        tblCargas.getTableHeader().getColumnModel().getColumn(columnas.length-1).setMinWidth(0);
        
        
        Object[] filas = new Object[columnas.length];
        for (int i = 0; i < cargasTemporales.size(); i++) {
            filas[0] = "+";
            
            filas[1] = cargasTemporales.get(i).getBarco().getNombre();
            
            filas[2] = cargasTemporales.get(i).getCantidad();
            filas[3] = cargasTemporales.get(i).getEspecie();
            filas[4] = cargasTemporales.get(i).getTalla();
            filas[5] = cargasTemporales.get(i).getTemperatura();
            filas[6] = cargasTemporales.get(i).getCondicion();
            filas[7] = cargasTemporales.get(i).getBarcoId();

            model.addRow(filas);
        }
        
    }
    
    
    
    public void limpiarFormEntradas() {
        cbBarco.setSelectedIndex(0);
        txtCantidad.setText("");
        cbEspecie.setSelectedIndex(0);
        cbTalla.setSelectedIndex(0);
        txtTemperatura.setText("");
        cbCondicion.setSelectedIndex(0);
    }
    
    public void limpiarFormCargas() {
        cbBarcoC.setSelectedIndex(0);
        txtCantidadC.setText("");
        cbEspecieC.setSelectedIndex(0);
        cbTallaC.setSelectedIndex(0);
        txtTemperaturaC.setText("");
        cbCondicionC.setSelectedIndex(0);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        formEntrada = new javax.swing.JDialog();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        btnAgregarCarga = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblCargas = new javax.swing.JTable();
        btnAceptarEntrada = new javax.swing.JButton();
        btnCancelarEntrada = new javax.swing.JButton();
        cbBarco = new javax.swing.JComboBox();
        txtCantidad = new javax.swing.JTextField();
        cbEspecie = new javax.swing.JComboBox();
        cbCondicion = new javax.swing.JComboBox();
        txtTemperatura = new javax.swing.JTextField();
        cbTalla = new javax.swing.JComboBox();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        btnEditarCarga = new javax.swing.JButton();
        btnEliminarCarga = new javax.swing.JButton();
        togEditar = new javax.swing.JToggleButton();
        formCarga = new javax.swing.JDialog();
        jPanel2 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        cbBarcoC = new javax.swing.JComboBox();
        jLabel13 = new javax.swing.JLabel();
        txtCantidadC = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        cbEspecieC = new javax.swing.JComboBox();
        jLabel15 = new javax.swing.JLabel();
        txtTemperaturaC = new javax.swing.JTextField();
        cbTallaC = new javax.swing.JComboBox();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        cbCondicionC = new javax.swing.JComboBox();
        btnAceptarCarga = new javax.swing.JButton();
        btnCancelarCarga = new javax.swing.JButton();
        togServicio = new javax.swing.JToggleButton();
        jLabel6 = new javax.swing.JLabel();
        txtBuscarEntrada = new javax.swing.JTextField();
        btnBuscarEntrada = new javax.swing.JButton();
        btnAgregarEntrada = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblEntradas = new javax.swing.JTable();
        btnEditarEntrada = new javax.swing.JButton();
        btnEliminarEntrada = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        btnActualizarEntrada = new javax.swing.JButton();

        formEntrada.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        formEntrada.setTitle("Entrada");
        formEntrada.setAlwaysOnTop(true);
        formEntrada.addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formEntradasClose(evt);
            }
            public void windowActivated(java.awt.event.WindowEvent evt) {
                formEntradasOpen(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(250, 250, 250));

        jLabel1.setBackground(new java.awt.Color(237, 28, 36));
        jLabel1.setFont(new java.awt.Font("Lucida Grande", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Entrada");
        jLabel1.setOpaque(true);

        jLabel2.setFont(new java.awt.Font("Lucida Grande", 1, 14)); // NOI18N
        jLabel2.setText("Cargas:");

        btnAgregarCarga.setBackground(new java.awt.Color(55, 179, 68));
        btnAgregarCarga.setFont(new java.awt.Font("Lucida Grande", 1, 10)); // NOI18N
        btnAgregarCarga.setForeground(new java.awt.Color(255, 255, 255));
        btnAgregarCarga.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/add20x20White.png"))); // NOI18N
        btnAgregarCarga.setText("AGREGAR CARGA");
        btnAgregarCarga.setBorderPainted(false);
        btnAgregarCarga.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarCargaActionPerformed(evt);
            }
        });

        tblCargas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Barco", "Cantidad", "Especie", "Talla", "Temperatura", "Condición"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Double.class, java.lang.String.class, java.lang.String.class, java.lang.Double.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tblCargas);

        btnAceptarEntrada.setBackground(new java.awt.Color(55, 179, 68));
        btnAceptarEntrada.setFont(new java.awt.Font("Lucida Grande", 1, 12)); // NOI18N
        btnAceptarEntrada.setForeground(new java.awt.Color(255, 255, 255));
        btnAceptarEntrada.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/accept20x20White.png"))); // NOI18N
        btnAceptarEntrada.setText("ACEPTAR");
        btnAceptarEntrada.setBorderPainted(false);
        btnAceptarEntrada.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAceptarEntradaActionPerformed(evt);
            }
        });

        btnCancelarEntrada.setBackground(new java.awt.Color(237, 28, 36));
        btnCancelarEntrada.setFont(new java.awt.Font("Lucida Grande", 1, 12)); // NOI18N
        btnCancelarEntrada.setForeground(new java.awt.Color(255, 255, 255));
        btnCancelarEntrada.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/cancel20x20White.png"))); // NOI18N
        btnCancelarEntrada.setText("CANCELAR");
        btnCancelarEntrada.setBorderPainted(false);
        btnCancelarEntrada.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarEntradaActionPerformed(evt);
            }
        });

        txtCantidad.setText("Toneladas");

        cbEspecie.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Anchoveta", "Bocona", "Crinuda", "Japonesa", "Macarela", "Monterrey", "Rayadillo" }));

        cbCondicion.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Buena", "Regular", "Mala" }));

        txtTemperatura.setText("Temperatura");

        cbTalla.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "S", "M", "L", "XL" }));

        jLabel3.setFont(new java.awt.Font("Lucida Grande", 0, 14)); // NOI18N
        jLabel3.setText("Barco:");

        jLabel4.setFont(new java.awt.Font("Lucida Grande", 0, 14)); // NOI18N
        jLabel4.setText("Toneladas:");

        jLabel5.setFont(new java.awt.Font("Lucida Grande", 0, 14)); // NOI18N
        jLabel5.setText("Especie:");

        jLabel7.setFont(new java.awt.Font("Lucida Grande", 0, 14)); // NOI18N
        jLabel7.setText("Talla:");

        jLabel9.setFont(new java.awt.Font("Lucida Grande", 0, 14)); // NOI18N
        jLabel9.setText("Temperatura:");

        jLabel10.setFont(new java.awt.Font("Lucida Grande", 0, 14)); // NOI18N
        jLabel10.setText("Condición:");

        btnEditarCarga.setBackground(new java.awt.Color(52, 150, 247));
        btnEditarCarga.setFont(new java.awt.Font("Lucida Grande", 1, 10)); // NOI18N
        btnEditarCarga.setForeground(new java.awt.Color(255, 255, 255));
        btnEditarCarga.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/edit20x20White.png"))); // NOI18N
        btnEditarCarga.setText("EDITAR CARGA");
        btnEditarCarga.setBorderPainted(false);
        btnEditarCarga.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarCargaActionPerformed(evt);
            }
        });

        btnEliminarCarga.setBackground(new java.awt.Color(237, 28, 36));
        btnEliminarCarga.setFont(new java.awt.Font("Lucida Grande", 1, 10)); // NOI18N
        btnEliminarCarga.setForeground(new java.awt.Color(255, 255, 255));
        btnEliminarCarga.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/delete20x20White.png"))); // NOI18N
        btnEliminarCarga.setText("ELIMINAR CARGA");
        btnEliminarCarga.setBorderPainted(false);
        btnEliminarCarga.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarCargaActionPerformed(evt);
            }
        });

        togEditar.setText("Editar");
        togEditar.setVisible(false);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 500, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(jLabel3)
                .addGap(6, 6, 6)
                .addComponent(cbBarco, javax.swing.GroupLayout.PREFERRED_SIZE, 440, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(jLabel4)
                .addGap(6, 6, 6)
                .addComponent(txtCantidad, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addComponent(jLabel5)
                .addGap(6, 6, 6)
                .addComponent(cbEspecie, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(jLabel7)
                .addGap(6, 6, 6)
                .addComponent(cbTalla, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1, 1, 1)
                .addComponent(txtTemperatura, javax.swing.GroupLayout.PREFERRED_SIZE, 241, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jLabel10)
                        .addGap(6, 6, 6)
                        .addComponent(cbCondicion, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel2)))
                .addGap(62, 62, 62)
                .addComponent(btnAgregarCarga))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 488, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(123, 123, 123)
                .addComponent(btnEditarCarga)
                .addGap(6, 6, 6)
                .addComponent(btnEliminarCarga)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(togEditar))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(141, 141, 141)
                .addComponent(btnAceptarEntrada)
                .addGap(6, 6, 6)
                .addComponent(btnCancelarEntrada))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(jLabel3))
                    .addComponent(cbBarco, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(6, 6, 6)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addComponent(jLabel4))
                    .addComponent(txtCantidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addComponent(jLabel5))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(cbEspecie, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(6, 6, 6)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addComponent(jLabel7))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(cbTalla, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addComponent(jLabel9))
                    .addComponent(txtTemperatura, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(6, 6, 6)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cbCondicion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(7, 7, 7)
                        .addComponent(btnAgregarCarga))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(jLabel10)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel2)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnEditarCarga)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnEliminarCarga)
                        .addComponent(togEditar)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 51, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnAceptarEntrada)
                    .addComponent(btnCancelarEntrada))
                .addGap(14, 14, 14))
        );

        javax.swing.GroupLayout formEntradaLayout = new javax.swing.GroupLayout(formEntrada.getContentPane());
        formEntrada.getContentPane().setLayout(formEntradaLayout);
        formEntradaLayout.setHorizontalGroup(
            formEntradaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        formEntradaLayout.setVerticalGroup(
            formEntradaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        formCarga.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        formCarga.setTitle("Carga");
        formCarga.setAlwaysOnTop(true);
        formCarga.addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formCargasClose(evt);
            }
            public void windowActivated(java.awt.event.WindowEvent evt) {
                formCargaOpen(evt);
            }
        });

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        jLabel11.setBackground(new java.awt.Color(237, 28, 36));
        jLabel11.setFont(new java.awt.Font("Lucida Grande", 1, 24)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel11.setText("Carga");
        jLabel11.setOpaque(true);

        jLabel12.setFont(new java.awt.Font("Lucida Grande", 0, 14)); // NOI18N
        jLabel12.setText("Barco:");

        jLabel13.setFont(new java.awt.Font("Lucida Grande", 0, 14)); // NOI18N
        jLabel13.setText("Toneladas:");

        txtCantidadC.setText("Toneladas");

        jLabel14.setFont(new java.awt.Font("Lucida Grande", 0, 14)); // NOI18N
        jLabel14.setText("Especie:");

        cbEspecieC.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Anchoveta", "Bocona", "Crinuda", "Japonesa", "Macarela", "Monterrey", "Rayadillo" }));

        jLabel15.setFont(new java.awt.Font("Lucida Grande", 0, 14)); // NOI18N
        jLabel15.setText("Temperatura:");

        txtTemperaturaC.setText("Temperatura");

        cbTallaC.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "S", "M", "L", "XL" }));

        jLabel16.setFont(new java.awt.Font("Lucida Grande", 0, 14)); // NOI18N
        jLabel16.setText("Talla:");

        jLabel17.setFont(new java.awt.Font("Lucida Grande", 0, 14)); // NOI18N
        jLabel17.setText("Condición:");

        cbCondicionC.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Buena", "Regular", "Mala" }));

        btnAceptarCarga.setBackground(new java.awt.Color(55, 179, 68));
        btnAceptarCarga.setFont(new java.awt.Font("Lucida Grande", 1, 12)); // NOI18N
        btnAceptarCarga.setForeground(new java.awt.Color(255, 255, 255));
        btnAceptarCarga.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/accept20x20White.png"))); // NOI18N
        btnAceptarCarga.setText("ACEPTAR");
        btnAceptarCarga.setBorderPainted(false);
        btnAceptarCarga.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAceptarCargaActionPerformed(evt);
            }
        });

        btnCancelarCarga.setBackground(new java.awt.Color(237, 28, 36));
        btnCancelarCarga.setFont(new java.awt.Font("Lucida Grande", 1, 12)); // NOI18N
        btnCancelarCarga.setForeground(new java.awt.Color(255, 255, 255));
        btnCancelarCarga.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/cancel20x20White.png"))); // NOI18N
        btnCancelarCarga.setText("CANCELAR");
        btnCancelarCarga.setBorderPainted(false);
        btnCancelarCarga.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarCargaActionPerformed(evt);
            }
        });

        togServicio.setText("Servicio");
        togServicio.setVisible(false);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel12)
                        .addGap(6, 6, 6)
                        .addComponent(cbBarcoC, javax.swing.GroupLayout.PREFERRED_SIZE, 440, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel13)
                        .addGap(6, 6, 6)
                        .addComponent(txtCantidadC, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(12, 12, 12)
                        .addComponent(jLabel14)
                        .addGap(6, 6, 6)
                        .addComponent(cbEspecieC, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel16)
                        .addGap(6, 6, 6)
                        .addComponent(cbTallaC, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(12, 12, 12)
                        .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(1, 1, 1)
                        .addComponent(txtTemperaturaC, javax.swing.GroupLayout.PREFERRED_SIZE, 241, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 6, Short.MAX_VALUE))
            .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel17)
                        .addGap(6, 6, 6)
                        .addComponent(cbCondicionC, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(124, 124, 124))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(togServicio)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnAceptarCarga)
                        .addGap(6, 6, 6)
                        .addComponent(btnCancelarCarga)
                        .addGap(137, 137, 137))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(jLabel12))
                    .addComponent(cbBarcoC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(6, 6, 6)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addComponent(jLabel13))
                    .addComponent(txtCantidadC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addComponent(jLabel14))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(cbEspecieC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(6, 6, 6)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addComponent(jLabel16))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(cbTallaC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addComponent(jLabel15))
                    .addComponent(txtTemperaturaC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cbCondicionC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(jLabel17)))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnAceptarCarga)
                        .addComponent(togServicio))
                    .addComponent(btnCancelarCarga))
                .addContainerGap(31, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout formCargaLayout = new javax.swing.GroupLayout(formCarga.getContentPane());
        formCarga.getContentPane().setLayout(formCargaLayout);
        formCargaLayout.setHorizontalGroup(
            formCargaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        formCargaLayout.setVerticalGroup(
            formCargaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        setBackground(new java.awt.Color(245, 245, 245));
        setPreferredSize(new java.awt.Dimension(881, 492));
        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                panelClick(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Lucida Grande", 0, 14)); // NOI18N
        jLabel6.setText("Buscar:");

        btnBuscarEntrada.setBackground(new java.awt.Color(249, 183, 72));
        btnBuscarEntrada.setFont(new java.awt.Font("Lucida Grande", 1, 12)); // NOI18N
        btnBuscarEntrada.setForeground(new java.awt.Color(255, 255, 255));
        btnBuscarEntrada.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/search20x20White.png"))); // NOI18N
        btnBuscarEntrada.setText("BUSCAR");
        btnBuscarEntrada.setBorderPainted(false);
        btnBuscarEntrada.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarEntradaActionPerformed(evt);
            }
        });

        btnAgregarEntrada.setBackground(new java.awt.Color(55, 179, 68));
        btnAgregarEntrada.setFont(new java.awt.Font("Lucida Grande", 1, 12)); // NOI18N
        btnAgregarEntrada.setForeground(new java.awt.Color(255, 255, 255));
        btnAgregarEntrada.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/add20x20White.png"))); // NOI18N
        btnAgregarEntrada.setText("AGREGAR");
        btnAgregarEntrada.setBorderPainted(false);
        btnAgregarEntrada.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarEntradaActionPerformed(evt);
            }
        });

        tblEntradas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane2.setViewportView(tblEntradas);

        btnEditarEntrada.setBackground(new java.awt.Color(52, 150, 247));
        btnEditarEntrada.setFont(new java.awt.Font("Lucida Grande", 1, 12)); // NOI18N
        btnEditarEntrada.setForeground(new java.awt.Color(255, 255, 255));
        btnEditarEntrada.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/edit20x20White.png"))); // NOI18N
        btnEditarEntrada.setText("EDITAR");
        btnEditarEntrada.setBorderPainted(false);
        btnEditarEntrada.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarEntradaActionPerformed(evt);
            }
        });

        btnEliminarEntrada.setBackground(new java.awt.Color(237, 28, 36));
        btnEliminarEntrada.setFont(new java.awt.Font("Lucida Grande", 1, 12)); // NOI18N
        btnEliminarEntrada.setForeground(new java.awt.Color(255, 255, 255));
        btnEliminarEntrada.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/delete20x20White.png"))); // NOI18N
        btnEliminarEntrada.setText("ELIMINAR");
        btnEliminarEntrada.setBorderPainted(false);
        btnEliminarEntrada.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarEntradaActionPerformed(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Lucida Grande", 1, 14)); // NOI18N
        jLabel8.setText("Entradas:");

        btnActualizarEntrada.setBackground(new java.awt.Color(255, 118, 73));
        btnActualizarEntrada.setFont(new java.awt.Font("Lucida Grande", 1, 12)); // NOI18N
        btnActualizarEntrada.setForeground(new java.awt.Color(255, 255, 255));
        btnActualizarEntrada.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/update20x20White.png"))); // NOI18N
        btnActualizarEntrada.setText("ACTUALIZAR");
        btnActualizarEntrada.setBorderPainted(false);
        btnActualizarEntrada.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnActualizarEntradaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(121, Short.MAX_VALUE)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtBuscarEntrada, javax.swing.GroupLayout.PREFERRED_SIZE, 278, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnBuscarEntrada)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnActualizarEntrada)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnAgregarEntrada)
                .addGap(100, 100, 100))
            .addGroup(layout.createSequentialGroup()
                .addGap(344, 344, 344)
                .addComponent(btnEditarEntrada)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnEliminarEntrada)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(txtBuscarEntrada, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnBuscarEntrada)
                    .addComponent(btnAgregarEntrada)
                    .addComponent(btnActualizarEntrada))
                .addGap(9, 9, 9)
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 330, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnEditarEntrada)
                    .addComponent(btnEliminarEntrada))
                .addContainerGap(42, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnAgregarCargaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarCargaActionPerformed
        
        Barco barco = barcos.get(cbBarco.getSelectedIndex());
        int id = barco.getId();

        String[] datos = {
            String.valueOf(id),
            txtCantidad.getText().trim(),
            cbEspecie.getSelectedItem().toString(),
            cbTalla.getSelectedItem().toString(),
            txtTemperatura.getText().trim(),
            cbCondicion.getSelectedItem().toString()
        };
        
        
        //guardar las cargas en temporal para luegos ser añadidas cuando se de aceptar
        if (evaluarDatos(datos, formEntrada)) {
            //agregar carga temporal
            Carga carga = new Carga(Double.parseDouble(txtCantidad.getText()), 
                    cbEspecie.getSelectedItem().toString(), 
                    cbTalla.getSelectedItem().toString(), 
                    Double.parseDouble(txtTemperatura.getText()), 
                    cbCondicion.getSelectedItem().toString(), 
                    id);
            carga.setBarco(barco);

            cargasTemporales.add(carga);
        }
        
        //al ser falso el tog (agregando carga a una entrada nueva)
        if(togEditar.isSelected() == false) {
            
            setTableCargasTemporal();

        }
        //al ser verdadero el tog (agregando carga a una entrada editada)
        else {
            
            setTableCargas();
            
        }
        
    }//GEN-LAST:event_btnAgregarCargaActionPerformed

    private void btnAgregarEntradaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarEntradaActionPerformed
        if(obtenerBarcos()) {
            
            limpiarFormEntradas();
            cargasTemporales.clear();
            setTableCargasTemporal();
            
            togEditar.setSelected(false);
            
            formEntrada.setVisible(true);
        }
        
    }//GEN-LAST:event_btnAgregarEntradaActionPerformed

    private void btnAceptarEntradaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAceptarEntradaActionPerformed
        formEntrada.setCursor(new Cursor(Cursor.WAIT_CURSOR));
        
        //primero agregar cargas al servicio
        List<Integer> cargasId = new ArrayList<>();

        for (int i = 0; i < cargasTemporales.size(); i++) {
            String json = p.post(CARGAS, Token.getToken(), cargasTemporales.get(i).cargaToJson());
            Carga carga = new Carga().jsonToCarga(json);

            //guardar id de la carga agregada
            cargasId.add(carga.getId());
        }

        //si se esta agregando una entrada nueva
        if(togEditar.isSelected() == false) {
            
            //luego agregar entrada
            Token token = Token.getToken();
            int usuarioId = token.getUserId();
            
            //System.out.println("mi ID "+usuarioId);
            Entrada entrada = new Entrada(usuarioId, cargasId);
            
            String json = p.post(ENTRADAS, Token.getToken(), entrada.entradaToJson());
            
            if (!json.equals("") || json != null) {
                setTableEntradas();
                
                JOptionPane.showMessageDialog(formEntrada, "Entrada agregada");
                limpiarFormEntradas();
                
                //limpiar cargas
                cargasTemporales.clear();
                setTableCargasTemporal();
                
            }
            else {
                JOptionPane.showMessageDialog(formEntrada, "Ocurrio un error al agregar la entrada");
            }
            
        }
        
        //si se esta editando un entrada agregada
        else {
            //agregar a la lista de cargas los id que ya existen
            for (int i = 0; i < cargasEntrada.size(); i++) {
                cargasId.add(cargasEntrada.get(i).getId());
                //System.out.println(cargasEntrada.get(i).getId());
            }
            
            Entrada entrada = new Entrada(cargasId);
            
            boolean edit = p.put(MODIFY_ENTRADAS, idEntrada, Token.getToken(), entrada.entradaToJson());
            
            if(edit) {
                JOptionPane.showMessageDialog(formEntrada, "Entrada editada");

                formEntrada.dispose();

                setTableEntradas();
                tblEntradas.setEnabled(true);
                
            } else {
                JOptionPane.showMessageDialog(formEntrada, "Error al editar entrada");
            }
        }
        
        formEntrada.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
        
    }//GEN-LAST:event_btnAceptarEntradaActionPerformed

    private void btnCancelarEntradaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarEntradaActionPerformed
        
        if (!cargasTemporales.isEmpty()) {
            int salir = JOptionPane.showConfirmDialog(formEntrada, "¿Estas seguro de salir? Se perderán las cargas agregadas");

            switch (salir) {
                case 0:
                    cargasTemporales.clear();
                    setTableCargasTemporal();

                    formEntrada.setVisible(false);

                    tblEntradas.setEnabled(true);
                    break;
                case 1:
                    break;
            }
        }
        else {
            formEntrada.dispose();
            tblEntradas.setEnabled(true);
        }
        
        
    }//GEN-LAST:event_btnCancelarEntradaActionPerformed

    private void formEntradasOpen(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formEntradasOpen
        tblEntradas.setEnabled(false);
    }//GEN-LAST:event_formEntradasOpen

    private void formEntradasClose(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formEntradasClose
        tblEntradas.setEnabled(true);
    }//GEN-LAST:event_formEntradasClose

    private void btnEditarCargaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarCargaActionPerformed
        
        int seleccionado = tblCargas.getSelectedRow();
        
        if (seleccionado != -1) {
            if (obtenerBarcosC()) {
                
                tblCargas.setEnabled(false);
                limpiarFormCargas();
                
                String signoCarga = getSignoCargaSeleccionada();
                
                //si es una carga nueva agarrar identificador de las cargasTemporales
                if(signoCarga.contains("+")) {
                    int cargaSeleccionada = seleccionado - cargasEntrada.size();
                    
                    //settear valores de la carga temporal
                    cbBarcoC.setSelectedItem(cargasTemporales.get(cargaSeleccionada).getBarco().getNombre());

                    txtCantidadC.setText(String.valueOf(cargasTemporales.get(cargaSeleccionada).getCantidad()));
                    cbEspecieC.setSelectedItem(cargasTemporales.get(cargaSeleccionada).getEspecie());
                    cbTallaC.setSelectedItem(cargasTemporales.get(cargaSeleccionada).getTalla());
                    txtTemperaturaC.setText(String.valueOf(cargasTemporales.get(cargaSeleccionada).getTemperatura()));
                    cbCondicionC.setSelectedItem(cargasTemporales.get(cargaSeleccionada).getCondicion());
                    
                    //settear falso porque no se editara una carga del servicio
                    togServicio.setSelected(false);
                    
                }
                else if(signoCarga.contains("-")) {
                    //settear valores de la carga ya existente
                    cbBarcoC.setSelectedItem(cargasEntrada.get(seleccionado).getBarco().getNombre());

                    txtCantidadC.setText(String.valueOf(cargasEntrada.get(seleccionado).getCantidad()));
                    cbEspecieC.setSelectedItem(cargasEntrada.get(seleccionado).getEspecie());
                    cbTallaC.setSelectedItem(cargasEntrada.get(seleccionado).getTalla());
                    txtTemperaturaC.setText(String.valueOf(cargasEntrada.get(seleccionado).getTemperatura()));
                    cbCondicionC.setSelectedItem(cargasEntrada.get(seleccionado).getCondicion());
                    
                    //settear verdadero porque se editara una carga del servicio
                    togServicio.setSelected(true);
                }
                
                
                formCarga.setVisible(true);
                
                
            }
        } else {
            JOptionPane.showMessageDialog(formEntrada, "Debes seleccionar una carga");
        }
        
    }//GEN-LAST:event_btnEditarCargaActionPerformed

    private void btnEliminarCargaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarCargaActionPerformed
        int seleccionado = tblCargas.getSelectedRow();
        
        if(seleccionado != -1) {
            tblCargas.setEnabled(false);
            int eliminar = JOptionPane.showConfirmDialog(formEntrada, "¿Eliminar carga?");
            
            String signoCarga = getSignoCargaSeleccionada();
            
            //eliminar carga temporal
            if(signoCarga.contains("+")) {
                switch (eliminar) {
                    case 0:
                        if (togEditar.isSelected() == true) {
                            seleccionado = seleccionado - cargasEntrada.size();
                            cargasTemporales.remove(seleccionado);
                            setTableCargas();
                        } else {
                            cargasTemporales.remove(seleccionado);
                            
                            setTableCargasTemporal();
                        }
                        
                        JOptionPane.showMessageDialog(formEntrada, "Carga eliminada");
                        break;
                    case 1:
                        JOptionPane.showMessageDialog(formEntrada, "No se eliminó ninguna carga");
                        break;
                }
            }
            
            //eliminar carga del servicio
            else if (signoCarga.contains("-")) {
                switch (eliminar) {
                    case 0:
                        boolean delete = p.delete(MODIFY_CARGAS, getIdCargaSeleccionada(), Token.getToken());

                        if (delete) {
                            setTableCargas();

                            JOptionPane.showMessageDialog(formEntrada, "Carga eliminada");
                        } else {
                            JOptionPane.showMessageDialog(this, "Ocurrio un error");
                        }
                        break;
                    case 1:
                        JOptionPane.showMessageDialog(this, "No se eliminó ninguna carga");
                        break;
                }
                
            }
            
            tblCargas.setEnabled(true);
        } 
        else {
            JOptionPane.showMessageDialog(formEntrada, "Debes seleccionar una carga");
        }
        
    }//GEN-LAST:event_btnEliminarCargaActionPerformed

    private void btnAceptarCargaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAceptarCargaActionPerformed
        tblCargas.setEnabled(false);
        
        int seleccionado = tblCargas.getSelectedRow();
        
        String[] datos = {
                cbBarcoC.getSelectedItem().toString(),
                txtCantidadC.getText().trim(),
                cbEspecieC.getSelectedItem().toString(),
                cbTallaC.getSelectedItem().toString(),
                txtTemperaturaC.getText().trim(),
                cbCondicionC.getSelectedItem().toString()
            };
        
        
        //si se edita una carga temporal
        if (togServicio.isSelected() == false) {
            //editar carga temportal
            
            //editar carga existente
            if (togEditar.isSelected() == true) {
                seleccionado = seleccionado - cargasEntrada.size();
            }
            
            Carga carga = cargasTemporales.get(seleccionado);
            
            if (evaluarDatos(datos, formCarga)) {
                //agregar los nuevos valores
                Barco barco = barcos.get(cbBarcoC.getSelectedIndex());
                
                cargasTemporales.get(seleccionado).setBarco(barco);
                cargasTemporales.get(seleccionado).setBarcoId(barco.getId());
                cargasTemporales.get(seleccionado).setCantidad(Double.parseDouble(txtCantidadC.getText().trim()));
                cargasTemporales.get(seleccionado).setEspecie(cbEspecieC.getSelectedItem().toString());
                cargasTemporales.get(seleccionado).setTalla(cbTallaC.getSelectedItem().toString());
                cargasTemporales.get(seleccionado).setTemperatura(Double.parseDouble(txtTemperaturaC.getText().trim()));
                cargasTemporales.get(seleccionado).setCondicion(cbCondicionC.getSelectedItem().toString());

                JOptionPane.showMessageDialog(formCarga, "Carga temporal editada");
                limpiarFormCargas();
                
                if (togEditar.isSelected() == true) {
                    setTableCargas();
                    System.out.println(entrada.getId());
                } else {
                    setTableCargasTemporal();
                }
                
                formCarga.dispose();

            }
        } else {
            //editar carga agregada
            
            if (evaluarDatos(datos, formCarga)) {
                
                Barco barco = barcos.get(cbBarcoC.getSelectedIndex());
            
                Carga carga = new Carga(
                        Double.parseDouble(txtCantidadC.getText().trim()), 
                        cbEspecieC.getSelectedItem().toString(), 
                        cbTallaC.getSelectedItem().toString(), 
                        Double.parseDouble(txtTemperaturaC.getText().trim()), 
                        cbCondicionC.getSelectedItem().toString(), 
                        barco.getId()
                );
                
                System.out.println("c"+carga);
                System.out.println("id Carga"+getIdCargaSeleccionada());
                boolean edit = p.put(MODIFY_CARGAS, getIdCargaSeleccionada(), Token.getToken(), carga.cargaToJson());
                System.out.println(carga.cargaToJson());
                
                if (edit) {
                    JOptionPane.showMessageDialog(formCarga, "Carga editada");
                    formCarga.dispose();
                    
                    //setTableEntradas();
                    
                    setTableCargas();
                    //System.out.println("id"+entrada.getId());
                    
                    tblCargas.setEnabled(true);
                }

            }
            
            
        }
        
        tblCargas.setEnabled(true);
    }//GEN-LAST:event_btnAceptarCargaActionPerformed

    private void btnCancelarCargaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarCargaActionPerformed
        limpiarFormCargas();
        formCarga.dispose();
        
        tblCargas.setEnabled(true);
        
    }//GEN-LAST:event_btnCancelarCargaActionPerformed

    private void formCargaOpen(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formCargaOpen
        tblCargas.setEnabled(false);
    }//GEN-LAST:event_formCargaOpen

    private void formCargasClose(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formCargasClose
        tblCargas.setEnabled(true);
    }//GEN-LAST:event_formCargasClose

    private void btnEditarEntradaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarEntradaActionPerformed
        try {
            int seleccionado = tblEntradas.getSelectedRow();

            if (seleccionado != -1) {
                if (obtenerBarcos()) {

                    //obtener entrada
                    limpiarFormEntradas();
                    togEditar.setSelected(true);

                    cargasTemporales.clear();

                    //guardar id de la entrada seleccionada
                    getIdEntradaSeleccionada();

                    setTableCargas();
                    System.out.println("editando entrada id"+entrada.getId());

                    formEntrada.setVisible(true);
                }
            } else {
                JOptionPane.showMessageDialog(this, "Debes seleccionar una entrada");
            }
        } catch (NullPointerException npe) {
            JOptionPane.showMessageDialog(this, "Imposible editar");
        }
        
    }//GEN-LAST:event_btnEditarEntradaActionPerformed

    private void panelClick(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelClick
        // TODO add your handling code here:
    }//GEN-LAST:event_panelClick

    private void btnEliminarEntradaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarEntradaActionPerformed
        int seleccionado = tblEntradas.getSelectedRow();
        
        if (seleccionado != -1) {
            tblEntradas.setEnabled(false);
            
            int eliminar = JOptionPane.showConfirmDialog(this, "¿Eliminar entrada?");
            switch (eliminar) {
                case 0:
                    getIdEntradaSeleccionada();
                    boolean delete = p.delete(MODIFY_ENTRADAS, idEntrada, Token.getToken());
                    
                    if (delete) {
                        setTableEntradas();
                        
                        JOptionPane.showMessageDialog(this, "Entrada eliminada");
                    } else {
                        JOptionPane.showMessageDialog(this, "Ocurrio un error");
                    }
                    
                    break;
                case 1:
                    JOptionPane.showMessageDialog(this, "No se eliminó ninguna entrada");
                    break;
            }
            
            tblEntradas.setEnabled(true);
            
        } else {
            JOptionPane.showMessageDialog(this, "Debes seleccionar una entrada");
        }
    }//GEN-LAST:event_btnEliminarEntradaActionPerformed

    private void btnActualizarEntradaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActualizarEntradaActionPerformed
        setTableEntradas();
    }//GEN-LAST:event_btnActualizarEntradaActionPerformed

    private void btnBuscarEntradaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarEntradaActionPerformed
        setTableEntradas();
        
        DefaultTableModel model = (DefaultTableModel) tblEntradas.getModel();
        
        String buscar = txtBuscarEntrada.getText().trim().toLowerCase();
        List<Entrada> buscados = new ArrayList<>();
        
        if(!buscar.equals("")) {
            //consultar entradas
            for (int i = 0; i < tblEntradas.getRowCount(); i++) {
                for (int j = 0; j < tblEntradas.getColumnCount(); j++) {
                    String s = String.valueOf(model.getValueAt(i, j)).toLowerCase();
                    
                    if (s.contains(buscar)) {
                        buscados.add(entradas.get(i));
                        //System.out.println(barcos.get(i));
                        break;
                    }
                    
                }
            }
            
            model.setRowCount(0);
            
            DateTimeFormatter fechaFormatter = DateTimeFormatter.ofPattern("dd-MM-uuuu");
            DateTimeFormatter horaFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
            
            Object[] filas = new Object[tblEntradas.getColumnCount()];
            for (int i = 0; i < buscados.size(); i++) {
                filas[0] = buscados.get(i).getFolio();
                
                LocalDate f = LocalDate.parse(buscados.get(i).getFecha().substring(0, 10));
                filas[1] = fechaFormatter.format(f);
                
                LocalTime h = LocalTime.parse(buscados.get(i).getHora());
                filas[2] = horaFormatter.format(h);
                
                filas[3] = buscados.get(i).getTurno();
                filas[4] = buscados.get(i).getUsuario().getNombre() +" "+ buscados.get(i).getUsuario().getApellido();
                filas[5] = buscados.get(i).getCargas().size();
                filas[6] = buscados.get(i).getId();
                
                model.addRow(filas);
            }
            
            
        } else {
            JOptionPane.showMessageDialog(this, "No se encontró ninguna entrada");
        }
        
    }//GEN-LAST:event_btnBuscarEntradaActionPerformed
    
    public boolean evaluarDatos(String[] datos, JDialog form) {
        for (String dato : datos) {
            if(dato.equals("")) {
                JOptionPane.showMessageDialog(form, "Falta completar algunos datos");
                return false;
            }
        }
        for (int i = 0; i < datos.length; i++) {
            if (!datos[1].matches("^[0-9]+[.]?[0-9]*") || !datos[4].matches("^[-]?[0-9]+[.]?[0-9]*")) {
                JOptionPane.showMessageDialog(form, "Introducir un número válido");
                return false;
            }
            else if (Double.parseDouble(datos[1]) <= 0) {
                JOptionPane.showMessageDialog(form, "Introducir números positivos");
                return false;
            }
        }
        return true;
    }
    
    public boolean obtenerBarcos() {
        cbBarco.removeAllItems();
        
        String json = p.getAll(BARCOS, Token.getToken());
        
        if(!json.equals("") || json == null) {
            
            barcos = new Barco().getListBarcos(json);
            for (Barco barco : barcos) {
                cbBarco.addItem(barco.getNombre());
            }
            
            return true;
            
        } else {
            JOptionPane.showMessageDialog(this, "No existe ningún barco agregado");
            
            return false;
        }
    }
    
    public boolean obtenerBarcosC() {
        cbBarcoC.removeAllItems();
        
        String json = p.getAll(BARCOS, Token.getToken());
        
        if(!json.equals("") || json == null) {
            
            barcos = new Barco().getListBarcos(json);
            for (Barco barco : barcos) {
                cbBarcoC.addItem(barco.getNombre());
            }
            
            return true;
            
        } else {
            JOptionPane.showMessageDialog(this, "No existe ningún barco agregado");
            
            return false;
        }
    }
    
    public void getIdEntradaSeleccionada() {
        idEntrada = tblEntradas.getValueAt(tblEntradas.getSelectedRow(), 6).toString();
        //String json = p.get(MODIFY_ENTRADAS, id, Token.getToken());
        //entrada = new Entrada().jsonToEntrada(json);
    }
    
    public String getIdCargaSeleccionada() {
        return tblCargas.getValueAt(tblCargas.getSelectedRow(), 7).toString();
    }
    
    public String getSignoCargaSeleccionada() {
        return tblCargas.getValueAt(tblCargas.getSelectedRow(), 0).toString();
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAceptarCarga;
    private javax.swing.JButton btnAceptarEntrada;
    private javax.swing.JButton btnActualizarEntrada;
    private javax.swing.JButton btnAgregarCarga;
    private javax.swing.JButton btnAgregarEntrada;
    private javax.swing.JButton btnBuscarEntrada;
    private javax.swing.JButton btnCancelarCarga;
    private javax.swing.JButton btnCancelarEntrada;
    private javax.swing.JButton btnEditarCarga;
    private javax.swing.JButton btnEditarEntrada;
    private javax.swing.JButton btnEliminarCarga;
    private javax.swing.JButton btnEliminarEntrada;
    private javax.swing.JComboBox cbBarco;
    private javax.swing.JComboBox cbBarcoC;
    private javax.swing.JComboBox cbCondicion;
    private javax.swing.JComboBox cbCondicionC;
    private javax.swing.JComboBox cbEspecie;
    private javax.swing.JComboBox cbEspecieC;
    private javax.swing.JComboBox cbTalla;
    private javax.swing.JComboBox cbTallaC;
    private javax.swing.JDialog formCarga;
    private javax.swing.JDialog formEntrada;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable tblCargas;
    private javax.swing.JTable tblEntradas;
    private javax.swing.JToggleButton togEditar;
    private javax.swing.JToggleButton togServicio;
    private javax.swing.JTextField txtBuscarEntrada;
    private javax.swing.JTextField txtCantidad;
    private javax.swing.JTextField txtCantidadC;
    private javax.swing.JTextField txtTemperatura;
    private javax.swing.JTextField txtTemperaturaC;
    // End of variables declaration//GEN-END:variables
}
