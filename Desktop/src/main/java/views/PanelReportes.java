/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;


import javax.swing.table.DefaultTableModel;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.draw.LineSeparator;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

/**
 *
 * @author fernandomarenco
 */
public class PanelReportes extends javax.swing.JPanel {

    /**
     * Creates new form panelReportes
     */
    public PanelReportes() {
        initComponents();
        
        
    }
    
    public PanelReportes(boolean set) {
        initComponents();
        
    }
    
    DateFormat df = DateFormat.getDateInstance();
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel5 = new javax.swing.JLabel();
        lblPorcMacarela = new javax.swing.JLabel();
        jLabel37 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        lblPorcCrinuda = new javax.swing.JLabel();
        btnGenerarReporte = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        lblPorcJaponesa = new javax.swing.JLabel();
        lblPorcBocona = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        lblTotal = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblCargasReporte = new javax.swing.JTable();
        jLabel17 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tblEntradasReporte = new javax.swing.JTable();
        jLabel14 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        jLabel39 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        jLabel42 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        lblTotalMonterrey = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        jLabel44 = new javax.swing.JLabel();
        lblTotalAnchoveta = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        lblTotalBocona = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel36 = new javax.swing.JLabel();
        lblPorcMonterrey = new javax.swing.JLabel();
        lblTotalRayadillo = new javax.swing.JLabel();
        btnBuscarReporte = new javax.swing.JButton();
        lblPorcRayadillo = new javax.swing.JLabel();
        lblTotalCrinuda = new javax.swing.JLabel();
        lblPorcAnchoveta = new javax.swing.JLabel();
        lblTotalMacarela = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        lblTotalJaponesa = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        datFechaFin = new com.toedter.calendar.JDateChooser();
        datFechaInicio = new com.toedter.calendar.JDateChooser();
        jLabel4 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(245, 245, 245));
        setPreferredSize(new java.awt.Dimension(881, 492));
        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                panelClick(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Lucida Grande", 1, 14)); // NOI18N
        jLabel5.setText("Cargas:");

        lblPorcMacarela.setFont(new java.awt.Font("Lucida Grande", 0, 12)); // NOI18N
        lblPorcMacarela.setText("porc");

        jLabel37.setFont(new java.awt.Font("Lucida Grande", 1, 12)); // NOI18N
        jLabel37.setText("Total:");

        jLabel9.setFont(new java.awt.Font("Lucida Grande", 1, 14)); // NOI18N
        jLabel9.setText("Macarela");

        lblPorcCrinuda.setFont(new java.awt.Font("Lucida Grande", 0, 12)); // NOI18N
        lblPorcCrinuda.setText("porc");

        btnGenerarReporte.setBackground(new java.awt.Color(52, 150, 247));
        btnGenerarReporte.setFont(new java.awt.Font("Lucida Grande", 1, 12)); // NOI18N
        btnGenerarReporte.setForeground(new java.awt.Color(255, 255, 255));
        btnGenerarReporte.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/file20x20White.png"))); // NOI18N
        btnGenerarReporte.setText("GENERAR REPORTE");
        btnGenerarReporte.setBorderPainted(false);
        btnGenerarReporte.setOpaque(true);
        btnGenerarReporte.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGenerarReporteActionPerformed(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Lucida Grande", 1, 14)); // NOI18N
        jLabel10.setText("Japonesa");

        lblPorcJaponesa.setFont(new java.awt.Font("Lucida Grande", 0, 12)); // NOI18N
        lblPorcJaponesa.setText("porc");

        lblPorcBocona.setFont(new java.awt.Font("Lucida Grande", 0, 12)); // NOI18N
        lblPorcBocona.setText("porc");

        jLabel13.setFont(new java.awt.Font("Lucida Grande", 1, 14)); // NOI18N
        jLabel13.setText("Bocona");

        jLabel8.setFont(new java.awt.Font("Lucida Grande", 1, 14)); // NOI18N
        jLabel8.setText("Entradas:");

        lblTotal.setFont(new java.awt.Font("Lucida Grande", 0, 12)); // NOI18N
        lblTotal.setText("num");

        tblCargasReporte.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Barco", "Especie", "Cantidad", "Talla", "Temperatura", "Condición"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.Double.class, java.lang.String.class, java.lang.Double.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tblCargasReporte);

        jLabel17.setFont(new java.awt.Font("Lucida Grande", 1, 12)); // NOI18N
        jLabel17.setText("Total:");

        tblEntradasReporte.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Folio", "Generado por", "Turno", "Fecha", "Hora"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane4.setViewportView(tblEntradasReporte);

        jLabel14.setFont(new java.awt.Font("Lucida Grande", 1, 14)); // NOI18N
        jLabel14.setText("Anchoveta");

        jLabel12.setFont(new java.awt.Font("Lucida Grande", 1, 14)); // NOI18N
        jLabel12.setText("Rayadillo");

        jLabel27.setFont(new java.awt.Font("Lucida Grande", 1, 12)); // NOI18N
        jLabel27.setText("Porcentaje:");

        jLabel39.setFont(new java.awt.Font("Lucida Grande", 1, 12)); // NOI18N
        jLabel39.setText("Porcentaje:");

        jLabel30.setFont(new java.awt.Font("Lucida Grande", 1, 12)); // NOI18N
        jLabel30.setText("Total:");

        jLabel42.setFont(new java.awt.Font("Lucida Grande", 1, 12)); // NOI18N
        jLabel42.setText("Total:");

        jLabel23.setFont(new java.awt.Font("Lucida Grande", 1, 12)); // NOI18N
        jLabel23.setText("Porcentaje:");

        jLabel11.setFont(new java.awt.Font("Lucida Grande", 1, 14)); // NOI18N
        jLabel11.setText("Monterrey");

        jLabel21.setFont(new java.awt.Font("Lucida Grande", 1, 12)); // NOI18N
        jLabel21.setText("Total:");

        lblTotalMonterrey.setFont(new java.awt.Font("Lucida Grande", 0, 12)); // NOI18N
        lblTotalMonterrey.setText("num");

        jLabel31.setFont(new java.awt.Font("Lucida Grande", 1, 12)); // NOI18N
        jLabel31.setText("Porcentaje:");

        jLabel44.setFont(new java.awt.Font("Lucida Grande", 1, 12)); // NOI18N
        jLabel44.setText("Porcentaje:");

        lblTotalAnchoveta.setFont(new java.awt.Font("Lucida Grande", 0, 12)); // NOI18N
        lblTotalAnchoveta.setText("num");

        jLabel16.setFont(new java.awt.Font("Lucida Grande", 1, 14)); // NOI18N
        jLabel16.setText("Total");

        jLabel25.setFont(new java.awt.Font("Lucida Grande", 1, 12)); // NOI18N
        jLabel25.setText("Total:");

        lblTotalBocona.setFont(new java.awt.Font("Lucida Grande", 0, 12)); // NOI18N
        lblTotalBocona.setText("num");

        jLabel15.setFont(new java.awt.Font("Lucida Grande", 1, 14)); // NOI18N
        jLabel15.setText("Crinuda");

        jLabel36.setFont(new java.awt.Font("Lucida Grande", 1, 12)); // NOI18N
        jLabel36.setText("Total:");

        lblPorcMonterrey.setFont(new java.awt.Font("Lucida Grande", 0, 12)); // NOI18N
        lblPorcMonterrey.setText("porc");

        lblTotalRayadillo.setFont(new java.awt.Font("Lucida Grande", 0, 12)); // NOI18N
        lblTotalRayadillo.setText("num");

        btnBuscarReporte.setBackground(new java.awt.Color(249, 183, 72));
        btnBuscarReporte.setFont(new java.awt.Font("Lucida Grande", 1, 12)); // NOI18N
        btnBuscarReporte.setForeground(new java.awt.Color(255, 255, 255));
        btnBuscarReporte.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/search20x20White.png"))); // NOI18N
        btnBuscarReporte.setText("BUSCAR");
        btnBuscarReporte.setBorderPainted(false);
        btnBuscarReporte.setOpaque(true);
        btnBuscarReporte.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarReporteActionPerformed(evt);
            }
        });

        lblPorcRayadillo.setFont(new java.awt.Font("Lucida Grande", 0, 12)); // NOI18N
        lblPorcRayadillo.setText("porc");

        lblTotalCrinuda.setFont(new java.awt.Font("Lucida Grande", 0, 12)); // NOI18N
        lblTotalCrinuda.setText("num");

        lblPorcAnchoveta.setFont(new java.awt.Font("Lucida Grande", 0, 12)); // NOI18N
        lblPorcAnchoveta.setText("porc");

        lblTotalMacarela.setFont(new java.awt.Font("Lucida Grande", 0, 12)); // NOI18N
        lblTotalMacarela.setText("num");

        jLabel18.setFont(new java.awt.Font("Lucida Grande", 1, 12)); // NOI18N
        jLabel18.setText("Porcentaje:");

        lblTotalJaponesa.setFont(new java.awt.Font("Lucida Grande", 0, 12)); // NOI18N
        lblTotalJaponesa.setText("num");

        jLabel34.setFont(new java.awt.Font("Lucida Grande", 1, 12)); // NOI18N
        jLabel34.setText("Porcentaje:");

        datFechaFin.setBackground(new java.awt.Color(245, 245, 245));
        datFechaFin.setDateFormatString("dd/MM/yyyy");

        datFechaInicio.setBackground(new java.awt.Color(245, 245, 245));
        datFechaInicio.setDateFormatString("dd-MM-yyyy");

        jLabel4.setFont(new java.awt.Font("Lucida Grande", 0, 14)); // NOI18N
        jLabel4.setText("Inicio:");

        jLabel6.setFont(new java.awt.Font("Lucida Grande", 0, 14)); // NOI18N
        jLabel6.setText("Fin:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.CENTER, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.CENTER, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                                .addComponent(jScrollPane4)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jLabel4)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(datFechaInicio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jLabel6)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(datFechaFin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(12, 12, 12)
                                    .addComponent(btnBuscarReporte)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(btnGenerarReporte)))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.CENTER))
                        .addGap(6, 6, 6))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5)
                            .addComponent(jLabel8))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 154, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel9)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel18)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblPorcMacarela))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel17)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblTotalMacarela))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel34)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblPorcBocona))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel36)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblTotalBocona))
                            .addComponent(jLabel13))
                        .addGap(64, 64, 64)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel10)
                            .addComponent(jLabel14)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel23)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblPorcJaponesa))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel21)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblTotalJaponesa))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel39)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblPorcAnchoveta))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel37)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblTotalAnchoveta)))
                        .addGap(62, 62, 62)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel11)
                                    .addComponent(jLabel15)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel27)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(lblPorcMonterrey))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel25)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(lblTotalMonterrey))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel42)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(lblTotalCrinuda)))
                                .addGap(66, 66, 66)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel12)
                                        .addGroup(layout.createSequentialGroup()
                                            .addComponent(jLabel31)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(lblPorcRayadillo))
                                        .addGroup(layout.createSequentialGroup()
                                            .addComponent(jLabel30)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(lblTotalRayadillo)))
                                    .addComponent(jLabel16)
                                    .addComponent(lblTotal)))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel44)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblPorcCrinuda)))
                        .addGap(133, 133, 133))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(datFechaInicio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(btnBuscarReporte)
                                .addComponent(btnGenerarReporte))
                            .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(datFechaFin, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 24, Short.MAX_VALUE)
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 127, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel9)
                            .addComponent(jLabel10)
                            .addComponent(jLabel11)
                            .addComponent(jLabel12))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel17)
                                    .addComponent(lblTotalMacarela))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel18)
                                    .addComponent(lblPorcMacarela))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel14)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(jLabel37)
                                            .addComponent(lblTotalAnchoveta))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(jLabel39)
                                            .addComponent(lblPorcAnchoveta)))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel13)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(jLabel36)
                                            .addComponent(lblTotalBocona))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(jLabel34)
                                            .addComponent(lblPorcBocona)))))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(jLabel25)
                                            .addComponent(lblTotalMonterrey))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(jLabel27)
                                            .addComponent(lblPorcMonterrey))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jLabel15)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(jLabel42)
                                            .addComponent(lblTotalCrinuda)))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(jLabel30)
                                            .addComponent(lblTotalRayadillo))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(jLabel31)
                                            .addComponent(lblPorcRayadillo))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jLabel16)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(lblTotal)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel44)
                                    .addComponent(lblPorcCrinuda))))
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel21)
                            .addComponent(lblTotalJaponesa))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel23)
                            .addComponent(lblPorcJaponesa))
                        .addGap(77, 77, 77))))
        );
    }// </editor-fold>//GEN-END:initComponents

    
    
    private void btnGenerarReporteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGenerarReporteActionPerformed
        JFileChooser chosenFile = new JFileChooser();
        DefaultTableModel dtmEntradas = new DefaultTableModel();
        DefaultTableModel dtmCargas = new DefaultTableModel();
        
        tblEntradasReporte.setModel(dtmEntradas);
        tblCargasReporte.setModel(dtmCargas);
        
        String[] colsEntradas = {"Folio", "Generado por", "Turno", "Fecha", "Hora"};
        for(String col : colsEntradas){
            dtmEntradas.addColumn(col);
        }
        
        String[] colsCargas = {"Barco", "Especie", "Cantidad", "Talla", "Temperatura", "Condición"};
        for(String col : colsCargas){
            dtmCargas.addColumn(col);
        }
        
        int columnEntradas = tblEntradasReporte.getModel().getColumnCount();
        int columnCargas = tblCargasReporte.getModel().getColumnCount();
        
        int rowEntradas = tblEntradasReporte.getModel().getRowCount();
        int rowCargas = tblCargasReporte.getModel().getRowCount();
        
        int opcion = chosenFile.showSaveDialog(this);
        
        if(opcion == JFileChooser.APPROVE_OPTION){
            File filePath = chosenFile.getSelectedFile();
            String path = filePath.toString();
            
            try {
                FileOutputStream fileName = new FileOutputStream(path +".pdf");
                Document document = new Document();
                
                PdfWriter.getInstance(document, fileName).setInitialLeading(20);
                
                document.open();
                
                Font fontawesome = new Font(Font.FontFamily.UNDEFINED, Font.DEFAULTSIZE, Font.BOLD);
                
                DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MMM/yyyy, HH:mm:ss");
                
                document.add(new Paragraph("Reporte del " + LocalDateTime.now().format(dtf)));
                document.add(new Paragraph(" "));
                document.add(new LineSeparator());
                document.add(new Paragraph(" "));
                
                document.add(new Paragraph("Tabla de Entradas", fontawesome));
                
                document.add(new Paragraph(" "));
                
                PdfPTable tablaEntradas = new PdfPTable(columnEntradas);
                
                for(int i = 0; i < columnEntradas; i++){
                    String contentEntradas = tblEntradasReporte.getColumnName(i);
                    tablaEntradas.addCell(contentEntradas);
                }
                
                tablaEntradas.setWidthPercentage(100);
                
                document.add(tablaEntradas);
                
                document.add(new Paragraph(" "));
                
                document.add(new Paragraph("Tabla de Cargas", fontawesome));
                
                document.add(new Paragraph(" "));
                
                PdfPTable tablaCargas = new PdfPTable(columnCargas);
                
                for(int i = 0; i < columnCargas; i++){
                    String contentCargas = tblCargasReporte.getColumnName(i);
                    tablaCargas.addCell(contentCargas);
                }
                
                tablaCargas.setWidthPercentage(100);
                
                document.add(tablaCargas);
                
                document.add(new Paragraph(" "));
                
                document.add(new Paragraph("Tabla de Totales", fontawesome));
                
                document.add(new Paragraph(" "));
                
                //INICIA CONTENIDO DE TOTALES
                
                PdfPTable tablaTotales = new PdfPTable(8);
                
                String[] colsTotales = {"", "Macarela", "Japonesa", "Monterrey", "Rayadillo", "Bocona", "Anchoveta", "Crinuda"};
                
                for (String colNamesTotales : colsTotales) {
                    tablaTotales.addCell(colNamesTotales);
                }
                
                
                tablaTotales.addCell("Total");
                
                String[] contenidoTotales = {lblTotalMacarela.getText(), lblTotalJaponesa.getText(), lblTotalMonterrey.getText(), lblTotalRayadillo.getText(), lblTotalBocona.getText(), lblTotalAnchoveta.getText(), lblTotalCrinuda.getText()};
                
                for(String totales : contenidoTotales){
                    tablaTotales.addCell(totales);
                }
                
                tablaTotales.addCell("Porcentaje");
                
                String[] contenidoPorcentajes = {lblPorcMacarela.getText(), lblPorcJaponesa.getText(), lblPorcMonterrey.getText(), lblPorcRayadillo.getText(), lblPorcBocona.getText(), lblPorcAnchoveta.getText(), lblPorcCrinuda.getText()};
                
                for(String porcentajes : contenidoPorcentajes){
                    tablaTotales.addCell(porcentajes);
                }
                
                tablaTotales.setWidthPercentage(100);
                
                document.add(tablaTotales);
                
                //FINALIZA CONTENIDO DE TOTALES
                
                document.close();
                
                JOptionPane.showMessageDialog(null, "Se guardó con éxito el PDF");
                
            } catch (FileNotFoundException ex) {
                Logger.getLogger(PanelReportes.class.getName()).log(Level.SEVERE, null, ex);
            } catch (DocumentException ex) {
                Logger.getLogger(PanelReportes.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        else{
            JOptionPane.showMessageDialog(null, "No se ha guardado nada");
        }
        
        
    }//GEN-LAST:event_btnGenerarReporteActionPerformed

    private void btnBuscarReporteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarReporteActionPerformed
        
        datFechaInicio.setFocusable(false);
        datFechaFin.setFocusable(false);
        
        try {
            String fechaInicio = new SimpleDateFormat("yyyy-MM-dd").format(datFechaInicio.getDate());
            String fechaFin = new SimpleDateFormat("yyyy-MM-dd").format(datFechaFin.getDate());
            
            LocalDate f1 = LocalDate.parse(fechaInicio);
            LocalDate f2 = LocalDate.parse(fechaFin);
            
            if (f1.isAfter(f2)) {
                JOptionPane.showMessageDialog(this, "Introducir bien un rango de fechas");
            } else {
                System.out.println(fechaInicio + fechaFin);
                
                
                
            }
            
        } catch(Exception e) {
            JOptionPane.showMessageDialog(this, "Introducir un rango de fechas");
        }
        
    }//GEN-LAST:event_btnBuscarReporteActionPerformed

    private void panelClick(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelClick
        // TODO add your handling code here:
    }//GEN-LAST:event_panelClick


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBuscarReporte;
    private javax.swing.JButton btnGenerarReporte;
    private com.toedter.calendar.JDateChooser datFechaFin;
    private com.toedter.calendar.JDateChooser datFechaInicio;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JLabel lblPorcAnchoveta;
    private javax.swing.JLabel lblPorcBocona;
    private javax.swing.JLabel lblPorcCrinuda;
    private javax.swing.JLabel lblPorcJaponesa;
    private javax.swing.JLabel lblPorcMacarela;
    private javax.swing.JLabel lblPorcMonterrey;
    private javax.swing.JLabel lblPorcRayadillo;
    private javax.swing.JLabel lblTotal;
    private javax.swing.JLabel lblTotalAnchoveta;
    private javax.swing.JLabel lblTotalBocona;
    private javax.swing.JLabel lblTotalCrinuda;
    private javax.swing.JLabel lblTotalJaponesa;
    private javax.swing.JLabel lblTotalMacarela;
    private javax.swing.JLabel lblTotalMonterrey;
    private javax.swing.JLabel lblTotalRayadillo;
    private javax.swing.JTable tblCargasReporte;
    private javax.swing.JTable tblEntradasReporte;
    // End of variables declaration//GEN-END:variables

}
