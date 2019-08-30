package Container;

import About.FrmAbout;
import Internals.check;
import Logging.OdooXmlRpc;
import java.awt.Image;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;

public class FrmContainer extends javax.swing.JFrame {

    public FrmContainer() {
        initComponents();
        this.setLocationRelativeTo(null);
    }

    //Icono de la imagen...
    public Image getIconImage() {
        Image retValue = Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("Resources/icon.png"));
        return retValue;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        btnLector = new javax.swing.JButton();
        btnAlternativo = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        btnRegister = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        menuBar2 = new javax.swing.JMenuBar();
        mnuInicio2 = new javax.swing.JMenu();
        mnuSalir = new javax.swing.JMenuItem();
        helpMenu = new javax.swing.JMenu();
        aboutMenuItem = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("CheckMeIn - Register");
        setIconImage(getIconImage());
        setName("frmPrincipal"); // NOI18N
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("Segoe UI Light", 0, 36)); // NOI18N
        jLabel1.setText("CheckMeIn");

        btnLector.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/device.png"))); // NOI18N
        btnLector.setToolTipText("Pase de Asistencia con lector biométrico");
        btnLector.setBorder(null);
        btnLector.setBorderPainted(false);
        btnLector.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLectorActionPerformed(evt);
            }
        });

        btnAlternativo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/employe.png"))); // NOI18N
        btnAlternativo.setToolTipText("Pase de Asistencia alternativo");
        btnAlternativo.setBorder(null);
        btnAlternativo.setBorderPainted(false);
        btnAlternativo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAlternativoActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Segoe UI Light", 0, 14)); // NOI18N
        jLabel3.setText("Fingerprint Reader");

        jLabel4.setFont(new java.awt.Font("Segoe UI Light", 0, 14)); // NOI18N
        jLabel4.setText("Username & Password");

        btnRegister.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/administrator2-128.png"))); // NOI18N
        btnRegister.setToolTipText("Registrar Huella Con Lector biométrico");
        btnRegister.setBorder(null);
        btnRegister.setBorderPainted(false);
        btnRegister.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegisterActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Segoe UI Light", 0, 24)); // NOI18N
        jLabel5.setText("Time Attendance Odoo");

        jLabel6.setFont(new java.awt.Font("Segoe UI Light", 0, 14)); // NOI18N
        jLabel6.setText("Add Employee");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel3)
                    .addComponent(btnLector))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 100, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(btnRegister)
                        .addGap(103, 103, 103)
                        .addComponent(btnAlternativo))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addGap(124, 124, 124)
                        .addComponent(jLabel4)))
                .addGap(19, 19, 19))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(252, 252, 252)
                .addComponent(jLabel1)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel5)
                .addGap(205, 205, 205))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(42, 42, 42)
                .addComponent(jLabel5)
                .addGap(34, 34, 34)
                .addComponent(jLabel1)
                .addGap(42, 42, 42)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnAlternativo)
                            .addComponent(btnLector))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(btnRegister)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel6)))
                .addContainerGap(44, Short.MAX_VALUE))
        );

        menuBar2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        mnuInicio2.setMnemonic('f');
        mnuInicio2.setText("File");
        mnuInicio2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        mnuSalir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/shutdown-26.png"))); // NOI18N
        mnuSalir.setMnemonic('x');
        mnuSalir.setText("Exit");
        mnuSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuSalirActionPerformed(evt);
            }
        });
        mnuInicio2.add(mnuSalir);

        menuBar2.add(mnuInicio2);

        helpMenu.setMnemonic('h');
        helpMenu.setText("Ayuda");
        helpMenu.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        aboutMenuItem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/under_computer-26.png"))); // NOI18N
        aboutMenuItem.setMnemonic('a');
        aboutMenuItem.setText("About CheckMeIn");
        aboutMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                aboutMenuItemActionPerformed(evt);
            }
        });
        helpMenu.add(aboutMenuItem);

        menuBar2.add(helpMenu);

        setJMenuBar(menuBar2);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnLectorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLectorActionPerformed
        FrmLector obj = new FrmLector();
        obj.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_btnLectorActionPerformed

    private void btnAlternativoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAlternativoActionPerformed
        FrmAlter obj = new FrmAlter();
        obj.setVisible(true);
        this.setVisible(false);

//         OdooXmlRpc odoo = new OdooXmlRpc();
//        odoo.login("http://localhost:8071", "odoo", "info@example.com", "aaa");
//        odoo.listRecords("hr.employee");
//        odoo.dumpRequest();
//        check check = new check();
//        List employee = check.searchEmployee("aiden.hughes71@example.com");       
//
//     
//        if (!employee.isEmpty()) {
//            HashMap emp = (HashMap) employee.get(0);
//            int id = (int) emp.get("id");
//            System.out.println(emp);
//            System.out.println(check.searchEmployee("aiden.hughes71@example.com"));
//        }
//        List employees = check.AllEmployee();
//        System.out.println(employees);
//        ConexionMySQL mysql = new ConexionMySQL();
//        Connection con = mysql.Conectar();
//
//        try {
//            //Obtiene todas las huellas de la bd
//            PreparedStatement identificarStmt = con.prepareStatement("SELECT huenombre,huehuella FROM huellas");
//            ResultSet rs = identificarStmt.executeQuery();
//
//            //Si se encuentra el nombre en la base de datos
//            while (rs.next()) {
//
////                Blob blob = rs.getBlob("huehuella");
////                System.out.println("Read " + blob.length() + " bytes ");
////                byte[] array = blob.getBytes(1, (int) blob.length());
//
////                File file = File.createTempFile(rs.getString("huenombre")+"-", ".binary", new File("."));
////                try (FileOutputStream out = new FileOutputStream(file)) {
////                    out.write(array);
////                }
//
////                java.sql.Blob ablob = rs.getBlob("huehuella");
//                //Lee la plantilla de la base de datos
//                System.err.println(rs.getString("huenombre"));
//            }
//
//            //Si no encuentra alguna huella correspondiente al nombre lo indica con un mensaje
////            JOptionPane.showMessageDialog(null, "There is no record that matches the fingerprint", "Error", JOptionPane.ERROR_MESSAGE);
//        } catch (SQLException e) {
//            //Si ocurre un error lo indica en la consola
//            //System.err.println("Error al identificar huella dactilar."+e.getMessage());
//            JOptionPane.showMessageDialog(null, "Failed to identify the fingerprint" + e);
//        }
//       

    }//GEN-LAST:event_btnAlternativoActionPerformed

    private void mnuSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuSalirActionPerformed
        System.exit(0);
    }//GEN-LAST:event_mnuSalirActionPerformed

    private void aboutMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_aboutMenuItemActionPerformed
        FrmAbout obj = new FrmAbout();
        obj.setVisible(true);
        obj.setLocationRelativeTo(null);
    }//GEN-LAST:event_aboutMenuItemActionPerformed

    private void btnRegisterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegisterActionPerformed

        FrmRegistValidate obj = new FrmRegistValidate();
        obj.setVisible(true);
        this.setVisible(false);
        
        
//         check check = new check();
//
//        List employeList = check.allEmployee();
//        List<String> optionList = new ArrayList<>();
//
//        int i = 0;
//        while (i < employeList.size()) {
//            HashMap emp = (HashMap) employeList.get(i);
//            String email = (String) emp.get("work_email");
//            optionList.add(email);
//            i++;
//        }
//
//        Object[] options = optionList.toArray();
//        Object selectedEmailEmployee = JOptionPane.showInputDialog(null,
//                "Choice Employee?",
//                "Fingerprint",
//                JOptionPane.QUESTION_MESSAGE,
//                null,
//                options,
//                options[0]);


//        int action;
//        String badgeId = "";
//        check check = new check();
//        do {
//            JPasswordField badge = new JPasswordField(10);
//            action = JOptionPane.showConfirmDialog(null, badge, "Enter Badge ID", JOptionPane.OK_CANCEL_OPTION);
//            System.out.println(action);
//            if (action < 0) {
//                JOptionPane.showMessageDialog(null, "Cancel, X or escape key selected");
//            } else {
//                badgeId = new String(badge.getPassword());
//                Integer id = check.searchEmployeeByBadgeId(badgeId);
//                Boolean hasTag = check.hasTag(id, "Fingerprint");
//
//                if (hasTag) {
//                    FrmLectorRegister obj = new FrmLectorRegister();
//                    obj.setVisible(true);
//                    this.setVisible(false);
//                    action = 2;
//                }
//            }
//        } while ((!badgeId.equals("0") &&  action != 2));
    }//GEN-LAST:event_btnRegisterActionPerformed

    public static void main(String args[]) {
        /* Set the Windows look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(FrmContainer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmContainer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmContainer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmContainer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Thread.sleep(1500);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Oops! Something wrong happended " + ex);
                }
                new FrmContainer().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem aboutMenuItem;
    private javax.swing.JButton btnAlternativo;
    private javax.swing.JButton btnLector;
    private javax.swing.JButton btnRegister;
    private javax.swing.JMenu helpMenu;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JMenuBar menuBar2;
    private javax.swing.JMenu mnuInicio2;
    private javax.swing.JMenuItem mnuSalir;
    // End of variables declaration//GEN-END:variables
}
