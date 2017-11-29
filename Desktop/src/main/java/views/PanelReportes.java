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
import entities.Barco;
import entities.Carga;
import static entities.Constantes.*;
import entities.Entrada;
import entities.Reporte;
import java.awt.Cursor;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import session.Peticiones;
import session.Token;

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
        
        limpiarTotales();
    }
    
    DateFormat df = DateFormat.getDateInstance();
    Peticiones p = new Peticiones();
    
    List<Reporte> entradas = new ArrayList<>();
    String fechaInicio;
    String fechaFin;
    
    List<Carga> cargasEntrada = new ArrayList<>();
    
    
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
        tblEntradasReporte.setModel(model);
        
        
        //columnas
        String[] columnas = {"Folio", "Fecha", "Hora", "Turno", "Generado por", "# Cargas", "ID"};
        for (String columna : columnas) {
            model.addColumn(columna);
        }
        
        tblEntradasReporte.getColumnModel().getColumn(columnas.length-1).setMaxWidth(0);
        tblEntradasReporte.getColumnModel().getColumn(columnas.length-1).setMinWidth(0);
        tblEntradasReporte.getTableHeader().getColumnModel().getColumn(columnas.length-1).setMaxWidth(0);
        tblEntradasReporte.getTableHeader().getColumnModel().getColumn(columnas.length-1).setMinWidth(0);
        
        
        //consultar reportes por rango de fecha
        String json = p.getByFecha(REPORTES, Token.getToken(), fechaInicio, fechaFin);
        
        //si no hay reportes
        if(json != null) {
            entradas = new Reporte().getListReportes(json);
            
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
            
            
        } else {
            JOptionPane.showMessageDialog(this, "No se encontrarón entradas");
        }
        
        setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
        MainView.tbMain.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
        
        
    }
    
    public void setTableCargasYTotales(String id) {
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
        tblCargasReporte.setModel(model);
        
        
        //columnas
        String[] columnas = {"Barco", "Cantidad", "Especie", "Talla", "Temperatura", "Condición"};
        for (String columna : columnas) {
            model.addColumn(columna);
        }
        
        
        String json = p.get(MODIFY_ENTRADAS, id, Token.getToken());
        Reporte reporte = new Reporte().jsonToReporte(json);
        
        cargasEntrada = reporte.getCargas();
        
        if(cargasEntrada.size() > 0) {
            
            //filas
            Object[] filas = new Object[columnas.length];
            
            for (int i = 0; i < cargasEntrada.size(); i++) {
                
                filas[0] = cargasEntrada.get(i).getBarco().getNombre();
                filas[1] = cargasEntrada.get(i).getCantidad();
                filas[2] = cargasEntrada.get(i).getEspecie();
                filas[3] = cargasEntrada.get(i).getTalla();
                filas[4] = cargasEntrada.get(i).getTemperatura();
                filas[5] = cargasEntrada.get(i).getCondicion();
                
                model.addRow(filas);
            }
            
        }
        
        DecimalFormat formatPorc = new DecimalFormat("0.00");
        DecimalFormat formatTotal = new DecimalFormat("#.##");
        
        //settear totales y porcentajes
        lblTotalMacarela.setText(String.valueOf(formatTotal.format(reporte.getTotalMacarela())));
        lblPorcMacarela.setText(String.valueOf(formatPorc.format(reporte.getPorcentajeMacarela())));
        
        lblTotalJaponesa.setText(String.valueOf(formatTotal.format(reporte.getTotalJaponesa())));
        lblPorcJaponesa.setText(String.valueOf(formatPorc.format(reporte.getPorcentajeJaponesa())));
        
        lblTotalMonterrey.setText(String.valueOf(formatTotal.format(reporte.getTotalMonterrey())));
        lblPorcMonterrey.setText(String.valueOf(formatPorc.format(reporte.getPorcentajeMonterrey())));
        
        lblTotalRayadillo.setText(String.valueOf(formatTotal.format(reporte.getTotalRayadillo())));
        lblPorcRayadillo.setText(String.valueOf(formatPorc.format(reporte.getPorcentajeRayadillo())));
        
        lblTotalBocona.setText(String.valueOf(formatTotal.format(reporte.getTotalBocona())));
        lblPorcBocona.setText(String.valueOf(formatPorc.format(reporte.getPorcentajeBocona())));
        
        lblTotalAnchoveta.setText(String.valueOf(formatTotal.format(reporte.getTotalAnchoveta())));
        lblPorcAnchoveta.setText(String.valueOf(formatPorc.format(reporte.getPorcentajeAnchoveta())));
        
        lblTotalCrinuda.setText(String.valueOf(formatTotal.format(reporte.getTotalCrinuda())));
        lblPorcCrinuda.setText(String.valueOf(formatPorc.format(reporte.getPorcentajeCrinuda())));
        
        lblTotal.setText(String.valueOf(formatTotal.format(reporte.getTotales())));
        
        
        setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
        MainView.tbMain.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
        
    }
    
    public void limpiarTotales() {
        //settear totales y porcentajes
        lblTotalMacarela.setText("");
        lblPorcMacarela.setText("");
        
        lblTotalJaponesa.setText("");
        lblPorcJaponesa.setText("");
        
        lblTotalMonterrey.setText("");
        lblPorcMonterrey.setText("");
        
        lblTotalRayadillo.setText("");
        lblPorcRayadillo.setText("");
        
        lblTotalBocona.setText("");
        lblPorcBocona.setText("");
        
        lblTotalAnchoveta.setText("");
        lblPorcAnchoveta.setText("");
        
        lblTotalCrinuda.setText("");
        lblPorcCrinuda.setText("");
        
        lblTotal.setText("");
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel5 = new javax.swing.JLabel();
        btnGenerarReporte = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblCargasReporte = new javax.swing.JTable();
        jScrollPane4 = new javax.swing.JScrollPane();
        tblEntradasReporte = new javax.swing.JTable();
        btnBuscarReporte = new javax.swing.JButton();
        datFechaFin = new com.toedter.calendar.JDateChooser();
        datFechaInicio = new com.toedter.calendar.JDateChooser();
        jLabel4 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        lblTotalMacarela = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        lblPorcMacarela = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel36 = new javax.swing.JLabel();
        lblTotalBocona = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        lblPorcBocona = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        lblTotalJaponesa = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        lblPorcJaponesa = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel37 = new javax.swing.JLabel();
        lblTotalAnchoveta = new javax.swing.JLabel();
        jLabel39 = new javax.swing.JLabel();
        lblPorcAnchoveta = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        lblTotalMonterrey = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        lblPorcMonterrey = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel42 = new javax.swing.JLabel();
        lblTotalCrinuda = new javax.swing.JLabel();
        lblPorcCrinuda = new javax.swing.JLabel();
        jLabel44 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        lblTotalRayadillo = new javax.swing.JLabel();
        lblPorcRayadillo = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        lblTotal = new javax.swing.JLabel();

        setBackground(new java.awt.Color(245, 245, 245));
        setPreferredSize(new java.awt.Dimension(881, 492));
        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                panelClick(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Lucida Grande", 1, 14)); // NOI18N
        jLabel5.setText("Cargas:");

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

        jLabel8.setFont(new java.awt.Font("Lucida Grande", 1, 14)); // NOI18N
        jLabel8.setText("Entradas:");

        tblCargasReporte.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Barco", "Cantidad", "Especie", "Talla", "Temperatura", "Condición"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Double.class, java.lang.String.class, java.lang.String.class, java.lang.Double.class, java.lang.String.class
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

        tblEntradasReporte.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Folio", "Fecha", "Hora", "Turno", "Generado por", "# Cargas"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
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
        tblEntradasReporte.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                entradaSeleccionada(evt);
            }
        });
        jScrollPane4.setViewportView(tblEntradasReporte);

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

        datFechaFin.setBackground(new java.awt.Color(245, 245, 245));
        datFechaFin.setDateFormatString("dd-MM-yyyy");

        datFechaInicio.setBackground(new java.awt.Color(245, 245, 245));
        datFechaInicio.setDateFormatString("dd-MM-yyyy");

        jLabel4.setFont(new java.awt.Font("Lucida Grande", 0, 14)); // NOI18N
        jLabel4.setText("Inicio:");

        jLabel6.setFont(new java.awt.Font("Lucida Grande", 0, 14)); // NOI18N
        jLabel6.setText("Fin:");

        jPanel1.setBackground(new java.awt.Color(245, 245, 245));
        jPanel1.setLayout(null);

        jLabel9.setFont(new java.awt.Font("Lucida Grande", 1, 14)); // NOI18N
        jLabel9.setText("Macarela");
        jPanel1.add(jLabel9);
        jLabel9.setBounds(0, 0, 63, 17);

        jLabel17.setFont(new java.awt.Font("Lucida Grande", 1, 12)); // NOI18N
        jLabel17.setText("Total:");
        jPanel1.add(jLabel17);
        jLabel17.setBounds(0, 20, 35, 15);

        lblTotalMacarela.setFont(new java.awt.Font("Lucida Grande", 0, 12)); // NOI18N
        lblTotalMacarela.setText("num");
        jPanel1.add(lblTotalMacarela);
        lblTotalMacarela.setBounds(40, 20, 120, 15);

        jLabel18.setFont(new java.awt.Font("Lucida Grande", 1, 12)); // NOI18N
        jLabel18.setText("Porcentaje:");
        jPanel1.add(jLabel18);
        jLabel18.setBounds(0, 40, 67, 15);

        lblPorcMacarela.setFont(new java.awt.Font("Lucida Grande", 0, 12)); // NOI18N
        lblPorcMacarela.setText("porc");
        jPanel1.add(lblPorcMacarela);
        lblPorcMacarela.setBounds(70, 40, 90, 15);

        jLabel13.setFont(new java.awt.Font("Lucida Grande", 1, 14)); // NOI18N
        jLabel13.setText("Bocona");
        jPanel1.add(jLabel13);
        jLabel13.setBounds(0, 70, 51, 17);

        jLabel36.setFont(new java.awt.Font("Lucida Grande", 1, 12)); // NOI18N
        jLabel36.setText("Total:");
        jPanel1.add(jLabel36);
        jLabel36.setBounds(0, 90, 35, 15);

        lblTotalBocona.setFont(new java.awt.Font("Lucida Grande", 0, 12)); // NOI18N
        lblTotalBocona.setText("num");
        jPanel1.add(lblTotalBocona);
        lblTotalBocona.setBounds(40, 90, 120, 15);

        jLabel34.setFont(new java.awt.Font("Lucida Grande", 1, 12)); // NOI18N
        jLabel34.setText("Porcentaje:");
        jPanel1.add(jLabel34);
        jLabel34.setBounds(0, 110, 67, 15);

        lblPorcBocona.setFont(new java.awt.Font("Lucida Grande", 0, 12)); // NOI18N
        lblPorcBocona.setText("porc");
        jPanel1.add(lblPorcBocona);
        lblPorcBocona.setBounds(70, 110, 90, 15);

        jLabel10.setFont(new java.awt.Font("Lucida Grande", 1, 14)); // NOI18N
        jLabel10.setText("Japonesa");
        jPanel1.add(jLabel10);
        jLabel10.setBounds(170, 0, 65, 17);

        jLabel21.setFont(new java.awt.Font("Lucida Grande", 1, 12)); // NOI18N
        jLabel21.setText("Total:");
        jPanel1.add(jLabel21);
        jLabel21.setBounds(170, 20, 35, 15);

        lblTotalJaponesa.setFont(new java.awt.Font("Lucida Grande", 0, 12)); // NOI18N
        lblTotalJaponesa.setText("num");
        jPanel1.add(lblTotalJaponesa);
        lblTotalJaponesa.setBounds(210, 20, 110, 15);

        jLabel23.setFont(new java.awt.Font("Lucida Grande", 1, 12)); // NOI18N
        jLabel23.setText("Porcentaje:");
        jPanel1.add(jLabel23);
        jLabel23.setBounds(170, 40, 67, 15);

        lblPorcJaponesa.setFont(new java.awt.Font("Lucida Grande", 0, 12)); // NOI18N
        lblPorcJaponesa.setText("porc");
        jPanel1.add(lblPorcJaponesa);
        lblPorcJaponesa.setBounds(240, 40, 80, 15);

        jLabel14.setFont(new java.awt.Font("Lucida Grande", 1, 14)); // NOI18N
        jLabel14.setText("Anchoveta");
        jPanel1.add(jLabel14);
        jLabel14.setBounds(170, 70, 74, 17);

        jLabel37.setFont(new java.awt.Font("Lucida Grande", 1, 12)); // NOI18N
        jLabel37.setText("Total:");
        jPanel1.add(jLabel37);
        jLabel37.setBounds(170, 90, 35, 15);

        lblTotalAnchoveta.setFont(new java.awt.Font("Lucida Grande", 0, 12)); // NOI18N
        lblTotalAnchoveta.setText("num");
        jPanel1.add(lblTotalAnchoveta);
        lblTotalAnchoveta.setBounds(210, 90, 110, 15);

        jLabel39.setFont(new java.awt.Font("Lucida Grande", 1, 12)); // NOI18N
        jLabel39.setText("Porcentaje:");
        jPanel1.add(jLabel39);
        jLabel39.setBounds(170, 110, 67, 15);

        lblPorcAnchoveta.setFont(new java.awt.Font("Lucida Grande", 0, 12)); // NOI18N
        lblPorcAnchoveta.setText("porc");
        jPanel1.add(lblPorcAnchoveta);
        lblPorcAnchoveta.setBounds(240, 110, 80, 15);

        jLabel11.setFont(new java.awt.Font("Lucida Grande", 1, 14)); // NOI18N
        jLabel11.setText("Monterrey");
        jPanel1.add(jLabel11);
        jLabel11.setBounds(330, 0, 73, 17);

        jLabel25.setFont(new java.awt.Font("Lucida Grande", 1, 12)); // NOI18N
        jLabel25.setText("Total:");
        jPanel1.add(jLabel25);
        jLabel25.setBounds(330, 20, 35, 15);

        lblTotalMonterrey.setFont(new java.awt.Font("Lucida Grande", 0, 12)); // NOI18N
        lblTotalMonterrey.setText("num");
        jPanel1.add(lblTotalMonterrey);
        lblTotalMonterrey.setBounds(370, 20, 110, 15);

        jLabel27.setFont(new java.awt.Font("Lucida Grande", 1, 12)); // NOI18N
        jLabel27.setText("Porcentaje:");
        jPanel1.add(jLabel27);
        jLabel27.setBounds(330, 40, 67, 15);

        lblPorcMonterrey.setFont(new java.awt.Font("Lucida Grande", 0, 12)); // NOI18N
        lblPorcMonterrey.setText("porc");
        jPanel1.add(lblPorcMonterrey);
        lblPorcMonterrey.setBounds(400, 40, 80, 15);

        jLabel15.setFont(new java.awt.Font("Lucida Grande", 1, 14)); // NOI18N
        jLabel15.setText("Crinuda");
        jPanel1.add(jLabel15);
        jLabel15.setBounds(330, 70, 56, 17);

        jLabel42.setFont(new java.awt.Font("Lucida Grande", 1, 12)); // NOI18N
        jLabel42.setText("Total:");
        jPanel1.add(jLabel42);
        jLabel42.setBounds(330, 90, 35, 15);

        lblTotalCrinuda.setFont(new java.awt.Font("Lucida Grande", 0, 12)); // NOI18N
        lblTotalCrinuda.setText("num");
        jPanel1.add(lblTotalCrinuda);
        lblTotalCrinuda.setBounds(370, 90, 110, 15);

        lblPorcCrinuda.setFont(new java.awt.Font("Lucida Grande", 0, 12)); // NOI18N
        lblPorcCrinuda.setText("porc");
        jPanel1.add(lblPorcCrinuda);
        lblPorcCrinuda.setBounds(400, 110, 80, 15);

        jLabel44.setFont(new java.awt.Font("Lucida Grande", 1, 12)); // NOI18N
        jLabel44.setText("Porcentaje:");
        jPanel1.add(jLabel44);
        jLabel44.setBounds(330, 110, 67, 15);

        jLabel12.setFont(new java.awt.Font("Lucida Grande", 1, 14)); // NOI18N
        jLabel12.setText("Rayadillo");
        jPanel1.add(jLabel12);
        jLabel12.setBounds(490, 0, 67, 17);

        jLabel30.setFont(new java.awt.Font("Lucida Grande", 1, 12)); // NOI18N
        jLabel30.setText("Total:");
        jPanel1.add(jLabel30);
        jLabel30.setBounds(490, 20, 35, 15);

        lblTotalRayadillo.setFont(new java.awt.Font("Lucida Grande", 0, 12)); // NOI18N
        lblTotalRayadillo.setText("num");
        jPanel1.add(lblTotalRayadillo);
        lblTotalRayadillo.setBounds(530, 20, 110, 15);

        lblPorcRayadillo.setFont(new java.awt.Font("Lucida Grande", 0, 12)); // NOI18N
        lblPorcRayadillo.setText(" porc");
        jPanel1.add(lblPorcRayadillo);
        lblPorcRayadillo.setBounds(560, 40, 80, 15);

        jLabel31.setFont(new java.awt.Font("Lucida Grande", 1, 12)); // NOI18N
        jLabel31.setText("Porcentaje:");
        jPanel1.add(jLabel31);
        jLabel31.setBounds(490, 40, 67, 15);

        jLabel16.setFont(new java.awt.Font("Lucida Grande", 1, 14)); // NOI18N
        jLabel16.setText("Total");
        jPanel1.add(jLabel16);
        jLabel16.setBounds(490, 70, 38, 17);

        lblTotal.setFont(new java.awt.Font("Lucida Grande", 0, 14)); // NOI18N
        lblTotal.setText("num");
        jPanel1.add(lblTotal);
        lblTotal.setBounds(490, 90, 150, 17);

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
                                .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 869, Short.MAX_VALUE)
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
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.CENTER)
                            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.CENTER, javax.swing.GroupLayout.PREFERRED_SIZE, 645, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(6, 6, 6))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5)
                            .addComponent(jLabel8))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
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
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE))
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
        
        DefaultTableModel model1 = (DefaultTableModel) tblEntradasReporte.getModel();
        DefaultTableModel model2 = (DefaultTableModel) tblCargasReporte.getModel();
        model1.setRowCount(0);
        model2.setRowCount(0);
        limpiarTotales();
        
        datFechaInicio.setFocusable(false);
        datFechaFin.setFocusable(false);
        
        try {
            fechaInicio = new SimpleDateFormat("yyyy-MM-dd").format(datFechaInicio.getDate());
            fechaFin = new SimpleDateFormat("yyyy-MM-dd").format(datFechaFin.getDate());
            
            LocalDate f1 = LocalDate.parse(fechaInicio);
            LocalDate f2 = LocalDate.parse(fechaFin);
            
            if (f1.isAfter(f2)) {
                JOptionPane.showMessageDialog(this, "Introducir bien un rango de fechas");
                
                
                
            } else {
                System.out.println(fechaInicio + fechaFin);
                
                setTableEntradas();
                
            }
            
        } catch(Exception e) {
            JOptionPane.showMessageDialog(this, "Introducir un rango de fechas");
            System.out.println(e);
        }
        
    }//GEN-LAST:event_btnBuscarReporteActionPerformed

    private void panelClick(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelClick
        // TODO add your handling code here:
    }//GEN-LAST:event_panelClick

    private void entradaSeleccionada(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_entradaSeleccionada
        int row = tblEntradasReporte.rowAtPoint(evt.getPoint());
        
        if (row != -1) {
            
            setTableCargasYTotales(getIdEntradaSeleccionada());
            
        }
    }//GEN-LAST:event_entradaSeleccionada

    public String getIdEntradaSeleccionada() {
        return tblEntradasReporte.getValueAt(tblEntradasReporte.getSelectedRow(), 6).toString();
    }
    

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
    private javax.swing.JPanel jPanel1;
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
