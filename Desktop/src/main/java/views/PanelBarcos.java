/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import entities.Barco;
import static entities.Constantes.*;
import entities.Usuario;
import java.awt.Cursor;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import session.Peticiones;
import session.Token;

/**
 *
 * @author fernandomarenco
 */
public class PanelBarcos extends javax.swing.JPanel {

    /**
     * Creates new form PanelBarcos
     */
    public PanelBarcos() {
        initComponents();
    }
    
    public PanelBarcos(boolean set) {
        initComponents();
        
        setTableBarcos();
        
        //inicializar forms
        formBarco.setSize(500, 310);
        formBarco.setLocationRelativeTo(null);
        formBarco.setResizable(false);
        
    }
    
    Peticiones p = new Peticiones();
    List<Barco> barcos = new ArrayList<>();
    
    //combo box obtener pescadores de existen
    List<Usuario> pescadores = new ArrayList<>();
    
    public void setTableBarcos() {
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
        tblBarcos.setModel(model);
        
        //columnas
        String[] columnas = {"Nombre", "Responsable", "Descripción", "ID", "ID Responsable"};
        for (String columna : columnas) {
            model.addColumn(columna);
        }
        
        tblBarcos.getColumnModel().getColumn(columnas.length-1).setMaxWidth(0);
        tblBarcos.getColumnModel().getColumn(columnas.length-1).setMinWidth(0);
        tblBarcos.getTableHeader().getColumnModel().getColumn(columnas.length-1).setMaxWidth(0);
        tblBarcos.getTableHeader().getColumnModel().getColumn(columnas.length-1).setMinWidth(0);
        
        tblBarcos.getColumnModel().getColumn(columnas.length-2).setMaxWidth(0);
        tblBarcos.getColumnModel().getColumn(columnas.length-2).setMinWidth(0);
        tblBarcos.getTableHeader().getColumnModel().getColumn(columnas.length-2).setMaxWidth(0);
        tblBarcos.getTableHeader().getColumnModel().getColumn(columnas.length-2).setMinWidth(0);
        
        //consultar barcos
        String json = p.getAll(BARCOS, Token.getToken());
        
        //si no hay barcos
        if(!json.equals("")) {
            barcos = new Barco().getListBarcos(json);
            

            //filas
            Object[] filas = new Object[columnas.length];
            for (int i = 0; i < barcos.size(); i++) {
                filas[0] = barcos.get(i).getNombre();
                filas[1] = barcos.get(i).getUsuario().getNombre() + " "+ barcos.get(i).getUsuario().getApellido();
                filas[2] = barcos.get(i).getDescripcion();
                filas[3] = barcos.get(i).getId();
                filas[4] = barcos.get(i).getUsuario().getId();

                model.addRow(filas);
            }

            btnBuscarBarco.setEnabled(true);
            
        } else {
            btnBuscarBarco.setEnabled(false);
        }
        
        setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
        MainView.tbMain.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
        
        
    }
    
    public void limpiarFormBarco() {
        txtNombre.setText("");
        cbPescador.setSelectedIndex(0);
        txtDescripcion.setText("");
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        formBarco = new javax.swing.JDialog();
        jPanel1 = new javax.swing.JPanel();
        btnAceptarBarco = new javax.swing.JButton();
        btnCancelarBarco = new javax.swing.JButton();
        txtNombre = new javax.swing.JTextField();
        cbPescador = new javax.swing.JComboBox();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtDescripcion = new javax.swing.JTextArea();
        togEditar = new javax.swing.JToggleButton();
        jLabel6 = new javax.swing.JLabel();
        txtBuscarBarco = new javax.swing.JTextField();
        btnBuscarBarco = new javax.swing.JButton();
        btnAgregarBarco = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblBarcos = new javax.swing.JTable();
        jLabel8 = new javax.swing.JLabel();
        btnEditarBarco = new javax.swing.JButton();
        btnEliminarBarco = new javax.swing.JButton();
        btnActualizarBarco = new javax.swing.JButton();

        formBarco.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        formBarco.setTitle("Barco");
        formBarco.setAlwaysOnTop(true);
        formBarco.setPreferredSize(new java.awt.Dimension(500, 305));
        formBarco.addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formBarcosClose(evt);
            }
            public void windowActivated(java.awt.event.WindowEvent evt) {
                formBarcosOpen(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(250, 250, 250));

        btnAceptarBarco.setBackground(new java.awt.Color(55, 179, 68));
        btnAceptarBarco.setFont(new java.awt.Font("Lucida Grande", 1, 12)); // NOI18N
        btnAceptarBarco.setForeground(new java.awt.Color(255, 255, 255));
        btnAceptarBarco.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/accept20x20White.png"))); // NOI18N
        btnAceptarBarco.setText("ACEPTAR");
        btnAceptarBarco.setBorderPainted(false);
        btnAceptarBarco.setOpaque(true);
        btnAceptarBarco.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAceptarBarcoActionPerformed(evt);
            }
        });

        btnCancelarBarco.setBackground(new java.awt.Color(237, 28, 36));
        btnCancelarBarco.setFont(new java.awt.Font("Lucida Grande", 1, 12)); // NOI18N
        btnCancelarBarco.setForeground(new java.awt.Color(255, 255, 255));
        btnCancelarBarco.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/cancel20x20White.png"))); // NOI18N
        btnCancelarBarco.setText("CANCELAR");
        btnCancelarBarco.setBorderPainted(false);
        btnCancelarBarco.setOpaque(true);
        btnCancelarBarco.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarBarcoActionPerformed(evt);
            }
        });

        txtNombre.setText("Nombre");

        cbPescador.setToolTipText("Responsable del barco");

        jLabel1.setBackground(new java.awt.Color(237, 28, 36));
        jLabel1.setFont(new java.awt.Font("Lucida Grande", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Barco");
        jLabel1.setOpaque(true);

        jLabel3.setFont(new java.awt.Font("Lucida Grande", 0, 14)); // NOI18N
        jLabel3.setText("Nombre:");

        jLabel4.setFont(new java.awt.Font("Lucida Grande", 0, 14)); // NOI18N
        jLabel4.setText("Pescador:");

        jLabel5.setFont(new java.awt.Font("Lucida Grande", 0, 14)); // NOI18N
        jLabel5.setText("Descripción:");

        txtDescripcion.setLineWrap(true);
        txtDescripcion.setRows(5);
        txtDescripcion.setText("Descripción");
        jScrollPane1.setViewportView(txtDescripcion);

        togEditar.setText("Editar");
        togEditar.setVisible(false);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 500, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jLabel5)))
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jLabel3)
                        .addGap(6, 6, 6)
                        .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(12, 12, 12)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cbPescador, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1)))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(togEditar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnAceptarBarco)
                .addGap(7, 7, 7)
                .addComponent(btnCancelarBarco)
                .addGap(133, 133, 133))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(12, 12, 12)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(4, 4, 4)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel4)))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(93, 93, 93)
                        .addComponent(cbPescador, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(6, 6, 6)
                .addComponent(jLabel5)
                .addGap(7, 7, 7)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnAceptarBarco)
                        .addComponent(togEditar))
                    .addComponent(btnCancelarBarco))
                .addContainerGap())
        );

        javax.swing.GroupLayout formBarcoLayout = new javax.swing.GroupLayout(formBarco.getContentPane());
        formBarco.getContentPane().setLayout(formBarcoLayout);
        formBarcoLayout.setHorizontalGroup(
            formBarcoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        formBarcoLayout.setVerticalGroup(
            formBarcoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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

        btnBuscarBarco.setBackground(new java.awt.Color(249, 183, 72));
        btnBuscarBarco.setFont(new java.awt.Font("Lucida Grande", 1, 12)); // NOI18N
        btnBuscarBarco.setForeground(new java.awt.Color(255, 255, 255));
        btnBuscarBarco.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/search20x20White.png"))); // NOI18N
        btnBuscarBarco.setText("BUSCAR");
        btnBuscarBarco.setBorderPainted(false);
        btnBuscarBarco.setOpaque(true);
        btnBuscarBarco.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarBarcoActionPerformed(evt);
            }
        });

        btnAgregarBarco.setBackground(new java.awt.Color(55, 179, 68));
        btnAgregarBarco.setFont(new java.awt.Font("Lucida Grande", 1, 12)); // NOI18N
        btnAgregarBarco.setForeground(new java.awt.Color(255, 255, 255));
        btnAgregarBarco.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/add20x20White.png"))); // NOI18N
        btnAgregarBarco.setText("AGREGAR");
        btnAgregarBarco.setBorderPainted(false);
        btnAgregarBarco.setOpaque(true);
        btnAgregarBarco.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarBarcoActionPerformed(evt);
            }
        });

        tblBarcos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane3.setViewportView(tblBarcos);

        jLabel8.setFont(new java.awt.Font("Lucida Grande", 1, 14)); // NOI18N
        jLabel8.setText("Barcos:");

        btnEditarBarco.setBackground(new java.awt.Color(52, 150, 247));
        btnEditarBarco.setFont(new java.awt.Font("Lucida Grande", 1, 12)); // NOI18N
        btnEditarBarco.setForeground(new java.awt.Color(255, 255, 255));
        btnEditarBarco.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/edit20x20White.png"))); // NOI18N
        btnEditarBarco.setText("EDITAR");
        btnEditarBarco.setBorderPainted(false);
        btnEditarBarco.setOpaque(true);
        btnEditarBarco.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarBarcoActionPerformed(evt);
            }
        });

        btnEliminarBarco.setBackground(new java.awt.Color(237, 28, 36));
        btnEliminarBarco.setFont(new java.awt.Font("Lucida Grande", 1, 12)); // NOI18N
        btnEliminarBarco.setForeground(new java.awt.Color(255, 255, 255));
        btnEliminarBarco.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/delete20x20White.png"))); // NOI18N
        btnEliminarBarco.setText("ELIMINAR");
        btnEliminarBarco.setBorderPainted(false);
        btnEliminarBarco.setOpaque(true);
        btnEliminarBarco.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarBarcoActionPerformed(evt);
            }
        });

        btnActualizarBarco.setBackground(new java.awt.Color(255, 118, 73));
        btnActualizarBarco.setFont(new java.awt.Font("Lucida Grande", 1, 12)); // NOI18N
        btnActualizarBarco.setForeground(new java.awt.Color(255, 255, 255));
        btnActualizarBarco.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/update20x20White.png"))); // NOI18N
        btnActualizarBarco.setText("ACTUALIZAR");
        btnActualizarBarco.setBorderPainted(false);
        btnActualizarBarco.setOpaque(true);
        btnActualizarBarco.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnActualizarBarcoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane3))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel8))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(134, 134, 134)
                                .addComponent(jLabel6)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtBuscarBarco, javax.swing.GroupLayout.PREFERRED_SIZE, 257, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnBuscarBarco)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnActualizarBarco)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnAgregarBarco)))
                        .addGap(0, 102, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(btnEditarBarco)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnEliminarBarco)
                .addGap(340, 340, 340))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(txtBuscarBarco, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnBuscarBarco)
                    .addComponent(btnAgregarBarco)
                    .addComponent(btnActualizarBarco))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 329, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnEditarBarco)
                    .addComponent(btnEliminarBarco))
                .addContainerGap(40, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnBuscarBarcoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarBarcoActionPerformed
        setTableBarcos();
        
        DefaultTableModel model = (DefaultTableModel) tblBarcos.getModel();
        
        String buscar = txtBuscarBarco.getText().trim().toLowerCase();
        List<Barco> buscados = new ArrayList<>();
        
        if(!buscar.equals("")) {
            //consultar barcos
            for (int i = 0; i < tblBarcos.getRowCount(); i++) {
                for (int j = 0; j < tblBarcos.getColumnCount(); j++) {
                    String s = String.valueOf(model.getValueAt(i, j)).toLowerCase();
                    
                    if (s.contains(buscar)) {
                        buscados.add(barcos.get(i));
                        System.out.println(barcos.get(i));
                        break;
                    }
                    
                }
            }
            
            model.setRowCount(0);
            
            //filas
            Object[] filas = new Object[tblBarcos.getColumnCount()];
            for (int i = 0; i < buscados.size(); i++) {
                filas[0] = buscados.get(i).getNombre();
                filas[1] = buscados.get(i).getUsuario().getNombre() + " "+ buscados.get(i).getUsuario().getApellido();
                filas[2] = buscados.get(i).getDescripcion();
                filas[3] = buscados.get(i).getId();
                filas[4] = buscados.get(i).getUsuario().getId();

                model.addRow(filas);
            }
            
        } else {
            JOptionPane.showMessageDialog(this, "No se encontró ningún barco");
        }
        
    }//GEN-LAST:event_btnBuscarBarcoActionPerformed

    private void panelClick(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelClick
        // TODO add your handling code here:
    }//GEN-LAST:event_panelClick

    private void btnAgregarBarcoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarBarcoActionPerformed
        
        if (obtenerPescadores()) {
            limpiarFormBarco();
            togEditar.setSelected(false);
            formBarco.setVisible(true);
        }
        
    }//GEN-LAST:event_btnAgregarBarcoActionPerformed

    private void btnAceptarBarcoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAceptarBarcoActionPerformed
        Usuario pescador = pescadores.get(cbPescador.getSelectedIndex());
        int id = pescador.getId();
        
        String datos[] = {
            txtNombre.getText().trim(),
            String.valueOf(id),
            txtDescripcion.getText()
        };
        
        
        if (evaluarDatos(datos)) {
            //Crear barco
            Barco barco = new Barco(txtNombre.getText().trim(), 
                    txtDescripcion.getText().trim(), 
                    id
            );
            
            //si el toggle no esta seleccionado
            if(togEditar.isSelected() == false) {
                
                //agregar usuario
                String json = p.post(BARCOS, Token.getToken(), barco.barcoToJson());
                System.out.println(barco.barcoToJson());
                barco = barco.jsonToBarco(json);
                System.out.println(barco);
                
                if (barco.getMessage() == null) {
                    setTableBarcos();
                    
                    JOptionPane.showMessageDialog(formBarco, "Barco agregado");
                    limpiarFormBarco();
                }
                else {
                    JOptionPane.showMessageDialog(formBarco, barco.getMessage());
                }
                
            }
            //si el toggle esta seleccionado
            else {
                
                //editar barco
                boolean edit = p.put(MODIFY_BARCOS, getIdBarcoSeleccionado(), Token.getToken(), barco.barcoToJson());
                
                
                if(edit) {
                    JOptionPane.showMessageDialog(formBarco, "Barco editado");
                    
                    formBarco.dispose();
                    
                    setTableBarcos();
                    tblBarcos.setEnabled(true);
                    
                } else {
                    JOptionPane.showMessageDialog(formBarco, "Error al editar barco");
                }
            }
            
            
        }
        
        
    }//GEN-LAST:event_btnAceptarBarcoActionPerformed

    private void btnCancelarBarcoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarBarcoActionPerformed
        limpiarFormBarco();
        formBarco.dispose();
        
        tblBarcos.setEnabled(true);
    }//GEN-LAST:event_btnCancelarBarcoActionPerformed

    private void btnEditarBarcoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarBarcoActionPerformed
        try {
            int seleccionado = tblBarcos.getSelectedRow();

            if (seleccionado != -1) {

                if(obtenerPescadores()) {
                    limpiarFormBarco();
                    togEditar.setSelected(true);

                    String idSeleccionado = getIdBarcoSeleccionado();

                    String json = p.get(MODIFY_BARCOS, idSeleccionado, Token.getToken());
                    Barco barco = new Barco().jsonToBarco(json);

                    txtNombre.setText(barco.getNombre());
                    cbPescador.setSelectedItem(barco.getUsuario().getNombre() + " " + barco.getUsuario().getApellido());
                    txtDescripcion.setText(barco.getDescripcion());

                    formBarco.setVisible(true);
                }

            } else {
                JOptionPane.showMessageDialog(this, "Debes seleccionar un barco");
            }
        } catch (NullPointerException npe) {
            JOptionPane.showMessageDialog(this, "Imposible editar");
        }
        
    }//GEN-LAST:event_btnEditarBarcoActionPerformed

    private void formBarcosOpen(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formBarcosOpen
        tblBarcos.setEnabled(false);
    }//GEN-LAST:event_formBarcosOpen

    private void formBarcosClose(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formBarcosClose
        tblBarcos.setEnabled(true);
    }//GEN-LAST:event_formBarcosClose

    private void btnEliminarBarcoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarBarcoActionPerformed
        int seleccionado = tblBarcos.getSelectedRow();
        
        if (seleccionado != -1) {
            tblBarcos.setEnabled(false);
            int eliminar = JOptionPane.showConfirmDialog(this, "¿Eliminar barco?");
            switch (eliminar) {
                case 0:
                    //eliminar barco
                    boolean delete = p.delete(MODIFY_BARCOS, getIdBarcoSeleccionado(), Token.getToken());
                    
                    if(delete) {
                        setTableBarcos();
                        
                        JOptionPane.showMessageDialog(this, "Barco eliminado");
                    } else {
                        JOptionPane.showMessageDialog(this, "Ocurrio un error");
                    }
                    break;
                case 1:
                    JOptionPane.showMessageDialog(this, "No se eliminó ningún barco");
                    break;
            }
            
            tblBarcos.setEnabled(true);
        } else {
            JOptionPane.showMessageDialog(this, "Debes seleccionar un barco");
        }
    }//GEN-LAST:event_btnEliminarBarcoActionPerformed

    private void btnActualizarBarcoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActualizarBarcoActionPerformed
        setTableBarcos();
    }//GEN-LAST:event_btnActualizarBarcoActionPerformed

    public boolean evaluarDatos(String[] datos) {
        for (String dato : datos) {
            if(dato.equals("")) {
                JOptionPane.showMessageDialog(formBarco, "Falta completar algunos datos");
                return false;
            }
        }
        return true;
    }
    
    public String getIdBarcoSeleccionado() {
        return tblBarcos.getValueAt(tblBarcos.getSelectedRow(), 3).toString();
    }
    
    public String getIdPescadorSeleccionado() {
        return tblBarcos.getValueAt(tblBarcos.getSelectedRow(), 4).toString();
    }
    
    public boolean obtenerPescadores() {
        cbPescador.removeAllItems();
        
        String json = p.getByRol(USERS, "Pescador", Token.getToken());
        
        if(json != null) {
            
            pescadores = new Usuario().getListUsers(json);
            for (Usuario pescador : pescadores) {
                cbPescador.addItem(pescador.getNombre() +" "+pescador.getApellido());
            }
            
            return true;
            
        } else {
            JOptionPane.showMessageDialog(this, "No existe ningún pescador agregado");
            
            return false;
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAceptarBarco;
    private javax.swing.JButton btnActualizarBarco;
    private javax.swing.JButton btnAgregarBarco;
    private javax.swing.JButton btnBuscarBarco;
    private javax.swing.JButton btnCancelarBarco;
    private javax.swing.JButton btnEditarBarco;
    private javax.swing.JButton btnEliminarBarco;
    private javax.swing.JComboBox cbPescador;
    private javax.swing.JDialog formBarco;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable tblBarcos;
    private javax.swing.JToggleButton togEditar;
    private javax.swing.JTextField txtBuscarBarco;
    private javax.swing.JTextArea txtDescripcion;
    private javax.swing.JTextField txtNombre;
    // End of variables declaration//GEN-END:variables
}
