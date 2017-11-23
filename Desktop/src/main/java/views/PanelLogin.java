/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import static entities.Constantes.LOGIN;
import javax.swing.JOptionPane;
import javax.swing.JTabbedPane;
import session.Client;
import session.Token;
import static views.Login.panelLogin;

/**
 *
 * @author fernandomarenco
 */
public class PanelLogin extends javax.swing.JPanel {

    /**
     * Creates new form PanelLogin
     */
    public PanelLogin() {
        initComponents();
    }

    Token token;
    
    public void iniciarSesion() {
        //abrir MainView
        MainView view = new MainView();
        view.setVisible(true);

        MainView.tbMain.add("Tab reportes", new PanelReportes());
        MainView.tbMain.add("Tab entradas", new PanelEntradas());
        MainView.tbMain.add("Tab barcos", new PanelBarcos());
        MainView.tbMain.add("Tab usuarios", new PanelUsuarios());
        
    }
    
    public void evaluarSesion(String user, StringBuilder pass) {
        if (!user.equals("") && !pass.toString().equals("")) {
            //obtener token
            String login = Client.postLoginForm(LOGIN, user, pass.toString());
            token = Token.getToken(login);
            
            if(token.getError_description() == null) {
                iniciarSesion();
                
            } else {
                JOptionPane.showMessageDialog(this, token.getError_description());
                Token.setToken();
            }
        }
        else {
            JOptionPane.showMessageDialog(this, "Debe ingresar sus datos");
        }
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel3 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        txtUser = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtPassword = new javax.swing.JPasswordField();
        btnIngresar = new javax.swing.JButton();
        btnOlvidar = new javax.swing.JButton();

        setBackground(new java.awt.Color(255, 255, 255));
        setPreferredSize(new java.awt.Dimension(600, 440));

        jLabel3.setBackground(new java.awt.Color(237, 28, 36));
        jLabel3.setFont(new java.awt.Font("Lucida Grande", 1, 24)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Login");
        jLabel3.setOpaque(true);

        jLabel1.setText("Usuario:");

        jLabel2.setText("Contraseña:");

        btnIngresar.setBackground(new java.awt.Color(237, 28, 36));
        btnIngresar.setFont(new java.awt.Font("Lucida Grande", 1, 12)); // NOI18N
        btnIngresar.setForeground(new java.awt.Color(255, 255, 255));
        btnIngresar.setText("INGRESAR");
        btnIngresar.setBorderPainted(false);
        btnIngresar.setOpaque(true);
        btnIngresar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnIngresarActionPerformed(evt);
            }
        });

        btnOlvidar.setBackground(new java.awt.Color(237, 28, 36));
        btnOlvidar.setFont(new java.awt.Font("Lucida Grande", 1, 12)); // NOI18N
        btnOlvidar.setForeground(new java.awt.Color(255, 255, 255));
        btnOlvidar.setText("OLVIDE MI CONTRASEÑA");
        btnOlvidar.setBorderPainted(false);
        btnOlvidar.setOpaque(true);
        btnOlvidar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOlvidarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(138, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnIngresar)
                        .addGap(18, 18, 18)
                        .addComponent(btnOlvidar))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jLabel2)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(txtPassword))
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(txtUser, javax.swing.GroupLayout.PREFERRED_SIZE, 255, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(141, 141, 141))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(66, 66, 66)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtUser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addGap(67, 67, 67)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnOlvidar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnIngresar, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(115, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnIngresarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnIngresarActionPerformed
        
        StringBuilder pass = new StringBuilder();
        for (char c : txtPassword.getPassword()) {
            pass.append(c);
        }
        String user = txtUser.getText();
        
        //obtener token
        if(token != null && token.getAccess_token() != null) {
            JOptionPane.showMessageDialog(this, "Sesión ya iniciada");
            //iniciarSesion();
        } else {
            evaluarSesion(user, pass);
        }
        
        
    }//GEN-LAST:event_btnIngresarActionPerformed

    private void btnOlvidarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOlvidarActionPerformed
        PanelRecuperar p = new PanelRecuperar();
        p.setLocation(0, 0);
        p.setSize(600, 440);
        
        panelLogin.removeAll();
        panelLogin.add(p);
        panelLogin.revalidate();
        panelLogin.repaint();
    }//GEN-LAST:event_btnOlvidarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnIngresar;
    private javax.swing.JButton btnOlvidar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPasswordField txtPassword;
    private javax.swing.JTextField txtUser;
    // End of variables declaration//GEN-END:variables
}