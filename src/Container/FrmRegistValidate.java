package Container;

import Internals.Employee;
import Internals.reloj;
import Internals.check;
import java.awt.Color;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import static java.util.Arrays.asList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class FrmRegistValidate extends javax.swing.JFrame {

    String stringNumber = "";
    String activeField = "jTextField1";
    public static Integer idEmployeeToSave = 0;
    public static String employeeNameToSave = "";

    public FrmRegistValidate() {
        initComponents();
        this.setLocationRelativeTo(null);
        reloj obj = new reloj(lblHora);
        obj.start();

        ButtonListener listener = new ButtonListener();

        b0.addActionListener(listener);
        b1.addActionListener(listener);
        b2.addActionListener(listener);
        b3.addActionListener(listener);
        b4.addActionListener(listener);
        b5.addActionListener(listener);
        b6.addActionListener(listener);
        b7.addActionListener(listener);
        b8.addActionListener(listener);
        b9.addActionListener(listener);

    }

    class ButtonListener implements ActionListener {

        JTextField field;

        @Override
        public void actionPerformed(ActionEvent e) {

            if (activeField.equals("idNumberField")) {
                field = idNumberField;
            }
            if (activeField.equals("badgeIdField")) {
                field = badgeIdField;
            }

            if (e.getSource() == b1) {
                System.out.println(b1.getText());
                stringNumber += "1";
                field.setText(stringNumber);
            } else if (e.getSource() == b2) {
                System.out.println(b2.getText());
                stringNumber += "2";
                field.setText(stringNumber);
            } else if (e.getSource() == b3) {
                System.out.println(b3.getText());
                stringNumber += "3";
                field.setText(stringNumber);
            } else if (e.getSource() == b3) {
                System.out.println(b3.getText());
                stringNumber += "3";
                field.setText(stringNumber);
            } else if (e.getSource() == b4) {
                System.out.println(b4.getText());
                stringNumber += "4";
                field.setText(stringNumber);
            } else if (e.getSource() == b5) {
                System.out.println(b5.getText());
                stringNumber += "5";
                field.setText(stringNumber);
            } else if (e.getSource() == b6) {
                System.out.println(b6.getText());
                stringNumber += "6";
                field.setText(stringNumber);
            } else if (e.getSource() == b7) {
                System.out.println(b7.getText());
                stringNumber += "7";
                field.setText(stringNumber);
            } else if (e.getSource() == b8) {
                System.out.println(b8.getText());
                stringNumber += "8";
                field.setText(stringNumber);
            } else if (e.getSource() == b9) {
                System.out.println(b9.getText());
                stringNumber += "9";
                field.setText(stringNumber);
            } else if (e.getSource() == b0) {
                System.out.println(b0.getText());
                stringNumber += "0";
                field.setText(stringNumber);
            }
            btnCheck.requestFocus();
        }
    }

    public Image getIconImage() {
        Image retValue = Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("Resources/icon.png"));
        return retValue;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        badgeIdField = new javax.swing.JPasswordField();
        jLabel5 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        lblHora = new javax.swing.JLabel();
        numberPanel = new javax.swing.JPanel();
        b1 = new javax.swing.JButton();
        b3 = new javax.swing.JButton();
        b2 = new javax.swing.JButton();
        b4 = new javax.swing.JButton();
        b5 = new javax.swing.JButton();
        b6 = new javax.swing.JButton();
        b7 = new javax.swing.JButton();
        b8 = new javax.swing.JButton();
        b9 = new javax.swing.JButton();
        b0 = new javax.swing.JButton();
        deleteBtn = new javax.swing.JButton();
        btnCheck = new javax.swing.JButton();
        message = new javax.swing.JLabel();
        btnClear = new javax.swing.JButton();
        lblHora2 = new javax.swing.JLabel();
        lblHora3 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        idNumberField = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("CheckMeIn - Validate Access");
        setIconImage(getIconImage());
        setName("frmAlter"); // NOI18N
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setPreferredSize(new java.awt.Dimension(789, 444));
        jPanel1.setLayout(null);

        jLabel1.setFont(new java.awt.Font("Segoe UI Light", 0, 36)); // NOI18N
        jLabel1.setText("CheckMeIn");
        jPanel1.add(jLabel1);
        jLabel1.setBounds(20, 20, 166, 48);

        jLabel4.setFont(new java.awt.Font("Segoe UI Light", 1, 18)); // NOI18N
        jLabel4.setText("Badge ID");
        jPanel1.add(jLabel4);
        jLabel4.setBounds(180, 250, 90, 25);

        badgeIdField.setFont(new java.awt.Font("Segoe UI Light", 0, 18)); // NOI18N
        badgeIdField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                badgeIdFieldFocusGained(evt);
            }
        });
        badgeIdField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                badgeIdFieldActionPerformed(evt);
            }
        });
        jPanel1.add(badgeIdField);
        badgeIdField.setBounds(280, 250, 180, 31);

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/administrator2-128.png"))); // NOI18N
        jPanel1.add(jLabel5);
        jLabel5.setBounds(490, 10, 128, 128);

        jLabel2.setFont(new java.awt.Font("Segoe UI Light", 0, 24)); // NOI18N
        jLabel2.setText("Validate Access");
        jPanel1.add(jLabel2);
        jLabel2.setBounds(20, 70, 155, 32);

        lblHora.setFont(new java.awt.Font("Segoe UI Semibold", 0, 14)); // NOI18N
        lblHora.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jPanel1.add(lblHora);
        lblHora.setBounds(300, 40, 80, 30);

        numberPanel.setBackground(new java.awt.Color(255, 255, 255));

        b1.setFont(new java.awt.Font("Segoe UI Light", 0, 14)); // NOI18N
        b1.setForeground(new java.awt.Color(255, 255, 255));
        b1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/fondoBoton0.png"))); // NOI18N
        b1.setText("1");
        b1.setBorder(null);
        b1.setBorderPainted(false);
        b1.setContentAreaFilled(false);
        b1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        b3.setFont(new java.awt.Font("Segoe UI Light", 0, 14)); // NOI18N
        b3.setForeground(new java.awt.Color(255, 255, 255));
        b3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/fondoBoton0.png"))); // NOI18N
        b3.setText("3");
        b3.setBorder(null);
        b3.setBorderPainted(false);
        b3.setContentAreaFilled(false);
        b3.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        b2.setFont(new java.awt.Font("Segoe UI Light", 0, 14)); // NOI18N
        b2.setForeground(new java.awt.Color(255, 255, 255));
        b2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/fondoBoton0.png"))); // NOI18N
        b2.setText("2");
        b2.setBorder(null);
        b2.setBorderPainted(false);
        b2.setContentAreaFilled(false);
        b2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        b4.setFont(new java.awt.Font("Segoe UI Light", 0, 14)); // NOI18N
        b4.setForeground(new java.awt.Color(255, 255, 255));
        b4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/fondoBoton0.png"))); // NOI18N
        b4.setText("4");
        b4.setBorder(null);
        b4.setBorderPainted(false);
        b4.setContentAreaFilled(false);
        b4.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        b5.setFont(new java.awt.Font("Segoe UI Light", 0, 14)); // NOI18N
        b5.setForeground(new java.awt.Color(255, 255, 255));
        b5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/fondoBoton0.png"))); // NOI18N
        b5.setText("5");
        b5.setBorder(null);
        b5.setBorderPainted(false);
        b5.setContentAreaFilled(false);
        b5.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        b6.setFont(new java.awt.Font("Segoe UI Light", 0, 14)); // NOI18N
        b6.setForeground(new java.awt.Color(255, 255, 255));
        b6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/fondoBoton0.png"))); // NOI18N
        b6.setText("6");
        b6.setBorder(null);
        b6.setBorderPainted(false);
        b6.setContentAreaFilled(false);
        b6.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        b7.setFont(new java.awt.Font("Segoe UI Light", 0, 14)); // NOI18N
        b7.setForeground(new java.awt.Color(255, 255, 255));
        b7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/fondoBoton0.png"))); // NOI18N
        b7.setText("7");
        b7.setBorder(null);
        b7.setBorderPainted(false);
        b7.setContentAreaFilled(false);
        b7.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        b8.setFont(new java.awt.Font("Segoe UI Light", 0, 14)); // NOI18N
        b8.setForeground(new java.awt.Color(255, 255, 255));
        b8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/fondoBoton0.png"))); // NOI18N
        b8.setText("8");
        b8.setBorder(null);
        b8.setBorderPainted(false);
        b8.setContentAreaFilled(false);
        b8.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        b9.setFont(new java.awt.Font("Segoe UI Light", 0, 14)); // NOI18N
        b9.setForeground(new java.awt.Color(255, 255, 255));
        b9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/fondoBoton0.png"))); // NOI18N
        b9.setText("9");
        b9.setBorder(null);
        b9.setBorderPainted(false);
        b9.setContentAreaFilled(false);
        b9.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        b0.setFont(new java.awt.Font("Segoe UI Light", 0, 14)); // NOI18N
        b0.setForeground(new java.awt.Color(255, 255, 255));
        b0.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/fondoBoton0.png"))); // NOI18N
        b0.setText("0");
        b0.setBorder(null);
        b0.setBorderPainted(false);
        b0.setContentAreaFilled(false);
        b0.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        deleteBtn.setFont(new java.awt.Font("Segoe UI Light", 0, 14)); // NOI18N
        deleteBtn.setForeground(new java.awt.Color(255, 255, 255));
        deleteBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/fondoBoton0.png"))); // NOI18N
        deleteBtn.setText("Del");
        deleteBtn.setBorder(null);
        deleteBtn.setBorderPainted(false);
        deleteBtn.setContentAreaFilled(false);
        deleteBtn.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        deleteBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout numberPanelLayout = new javax.swing.GroupLayout(numberPanel);
        numberPanel.setLayout(numberPanelLayout);
        numberPanelLayout.setHorizontalGroup(
            numberPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(numberPanelLayout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(numberPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(numberPanelLayout.createSequentialGroup()
                        .addComponent(b1)
                        .addGap(18, 18, 18)
                        .addComponent(b2))
                    .addGroup(numberPanelLayout.createSequentialGroup()
                        .addComponent(b4)
                        .addGap(18, 18, 18)
                        .addComponent(b5))
                    .addGroup(numberPanelLayout.createSequentialGroup()
                        .addComponent(b7)
                        .addGap(18, 18, 18)
                        .addGroup(numberPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(b0)
                            .addComponent(b8))))
                .addGap(18, 18, 18)
                .addGroup(numberPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(numberPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(b3, javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(b6, javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(b9, javax.swing.GroupLayout.Alignment.TRAILING))
                    .addComponent(deleteBtn))
                .addContainerGap(15, Short.MAX_VALUE))
        );
        numberPanelLayout.setVerticalGroup(
            numberPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(numberPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(numberPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(b1)
                    .addComponent(b3)
                    .addComponent(b2))
                .addGap(18, 18, 18)
                .addGroup(numberPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(b6)
                    .addComponent(b5)
                    .addComponent(b4))
                .addGap(18, 18, 18)
                .addGroup(numberPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(b7)
                    .addComponent(b8)
                    .addComponent(b9))
                .addGap(18, 18, 18)
                .addGroup(numberPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(b0)
                    .addComponent(deleteBtn))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel1.add(numberPanel);
        numberPanel.setBounds(160, 300, 310, 180);

        btnCheck.setFont(new java.awt.Font("Segoe UI Light", 0, 14)); // NOI18N
        btnCheck.setForeground(new java.awt.Color(255, 255, 255));
        btnCheck.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/fondoBoton0.png"))); // NOI18N
        btnCheck.setText("Check");
        btnCheck.setBorder(null);
        btnCheck.setBorderPainted(false);
        btnCheck.setContentAreaFilled(false);
        btnCheck.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnCheck.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCheckActionPerformed(evt);
            }
        });
        jPanel1.add(btnCheck);
        btnCheck.setBounds(360, 500, 79, 29);

        message.setFont(new java.awt.Font("Segoe UI Light", 1, 36)); // NOI18N
        message.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPanel1.add(message);
        message.setBounds(40, 150, 550, 40);

        btnClear.setFont(new java.awt.Font("Segoe UI Light", 0, 14)); // NOI18N
        btnClear.setForeground(new java.awt.Color(255, 255, 255));
        btnClear.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/fondoBoton2.png"))); // NOI18N
        btnClear.setText("Clear");
        btnClear.setBorder(null);
        btnClear.setBorderPainted(false);
        btnClear.setContentAreaFilled(false);
        btnClear.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnClear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClearbtnAceptarActionPerformed(evt);
            }
        });
        jPanel1.add(btnClear);
        btnClear.setBounds(200, 500, 79, 29);

        lblHora2.setFont(new java.awt.Font("Segoe UI Semibold", 0, 14)); // NOI18N
        lblHora2.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jPanel1.add(lblHora2);
        lblHora2.setBounds(300, 30, 80, 30);

        lblHora3.setFont(new java.awt.Font("Segoe UI Semibold", 0, 14)); // NOI18N
        lblHora3.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jPanel1.add(lblHora3);
        lblHora3.setBounds(300, 30, 80, 30);

        jLabel6.setFont(new java.awt.Font("Segoe UI Light", 1, 18)); // NOI18N
        jLabel6.setText("ID number");
        jPanel1.add(jLabel6);
        jLabel6.setBounds(180, 210, 100, 25);

        idNumberField.setFont(new java.awt.Font("Segoe UI Light", 0, 18)); // NOI18N
        idNumberField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                idNumberFieldFocusGained(evt);
            }
        });
        jPanel1.add(idNumberField);
        idNumberField.setBounds(280, 210, 180, 31);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 631, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 549, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        FrmContainer obj = new FrmContainer();
        obj.setVisible(true);
    }//GEN-LAST:event_formWindowClosing

    private void btnCheckActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCheckActionPerformed

        Integer id = null;
        String name = null;
        Boolean isChoice = true;

        check check = new check();
        if (check.isConnected()) {
            JOptionPane.showMessageDialog(null, "Connection Problems", "Warning",
                    JOptionPane.WARNING_MESSAGE);
        } else {
            String identification = idNumberField.getText();

            List employeeData = check.searchEmployeeByIdentificationId(identification);
            if (!employeeData.isEmpty()) {
                if (employeeData.size() > 1) {

                    ArrayList<Employee> optionList = new ArrayList<>();

                    int i = 0;
                    while (i < employeeData.size()) {
                        HashMap emp = (HashMap) employeeData.get(i);
                        Employee employeClass = new Employee((Integer) emp.get("id"), (String) emp.get("name"));
                        optionList.add(employeClass);
                        i++;
                    }

                    Object[] options = optionList.toArray();

                    Object selectedEmailEmployee = JOptionPane.showInputDialog(null,
                            "Find " + employeeData.size() + " Employees with the same ID Number, Select the correct?",
                            "Fingerprint",
                            JOptionPane.QUESTION_MESSAGE,
                            null,
                            options,
                            options[0]);
                    if (selectedEmailEmployee == null) {
                        isChoice = false;
                    } else {
                        String idEmployeeSelected = selectedEmailEmployee.toString().substring(selectedEmailEmployee.toString().lastIndexOf(':') + 1);
                        String nameEmployeeSelected = selectedEmailEmployee.toString().substring(selectedEmailEmployee.toString().lastIndexOf("Name:") + 5, selectedEmailEmployee.toString().indexOf('-'));
                        id = Integer.parseInt(idEmployeeSelected);
                        name = nameEmployeeSelected;
                    }
                } else {
                    HashMap emp = (HashMap) employeeData.get(0);
                    id = (Integer) emp.get("id");
                    name = (String) emp.get("name");
                }

                idEmployeeToSave = id;
                employeeNameToSave = name;

                if (isChoice) {
                    Boolean employeeHasTag = check.hasTag(idEmployeeToSave, "Fingerprint");
                    //Validate if employee has Fingerprint in Odoo
                    if (employeeHasTag) {
                        System.out.println(identification);
                        System.out.println(idEmployeeToSave);
                        System.out.println(employeeNameToSave);

                        String badgeId = new String(badgeIdField.getPassword());
                        if (!badgeId.isEmpty()) {
                            Integer idEmployee = check.searchEmployeeByBadgeId(badgeId);
                            if (idEmployee != 0) {
                                Boolean hasTag = check.hasTag(idEmployee, "Fingerprint Register");
                                if (hasTag) {
                                    FrmLectorRegister obj = new FrmLectorRegister();
                                    obj.setVisible(true);
                                    this.setVisible(false);
                                    badgeIdField.setText(null);
                                    stringNumber = "";
                                } else {
                                    sendText("You don't have the 'Fingerprint Register' Tag", Color.RED, 25);
                                    badgeIdField.setText(null);
                                    stringNumber = "";
                                }
                            } else {
                                sendText("The Badge ID was not found", Color.RED, 25);
                                badgeIdField.setText(null);
                                stringNumber = "";
                            }
                        } else {
                            sendText("The Badge ID is Empty", Color.RED, 25);
                        }
                    } else {
                        sendText("The employee doesn't have the 'Fingerprint' Tag. ", Color.RED, 25);
                        badgeIdField.setText(null);
                        stringNumber = "";
                    }
                }
            } else {
                sendText("The employee with the 'ID Number' " + identification + " given doesn't exist", Color.BLUE, 15);
            }

        }

    }//GEN-LAST:event_btnCheckActionPerformed

    /**
     * Allow to send text to label
     *
     * @param string
     * @param color
     * @param fontSize
     */
    public void sendText(String string, Color color, Integer fontSize) {
        message.setFont(new java.awt.Font("Tahoma", 0, fontSize));
        message.setForeground(color);
        message.setText(string);
    }

    private void btnClearbtnAceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClearbtnAceptarActionPerformed
        // TODO add your handling code here:
        badgeIdField.setText(null);
        idNumberField.setText(null);
        stringNumber = "";
    }//GEN-LAST:event_btnClearbtnAceptarActionPerformed

    private void badgeIdFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_badgeIdFieldActionPerformed
        btnCheckActionPerformed(evt);
    }//GEN-LAST:event_badgeIdFieldActionPerformed

    private void idNumberFieldFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_idNumberFieldFocusGained
        activeField = "idNumberField";
        stringNumber = idNumberField.getText();
    }//GEN-LAST:event_idNumberFieldFocusGained

    private void badgeIdFieldFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_badgeIdFieldFocusGained
        activeField = "badgeIdField";
        String badgeId = new String(badgeIdField.getPassword());
        stringNumber = badgeId;
    }//GEN-LAST:event_badgeIdFieldFocusGained

    private void deleteBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteBtnActionPerformed

        JTextField field = new JTextField();
        if (activeField.equals("idNumberField")) {
            field = idNumberField;
        }
        if (activeField.equals("badgeIdField")) {
            field = badgeIdField;
        }

        if (!field.getText().isEmpty()) {
            field.setText("" + field.getText().substring(0, field.getText().length() - 1));
        }

        if (activeField.equals("idNumberField")) {
            stringNumber = idNumberField.getText();
        }
        if (activeField.equals("badgeIdField")) {
            String badgeId = new String(badgeIdField.getPassword());
            stringNumber = badgeId;
        }

    }//GEN-LAST:event_deleteBtnActionPerformed

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
            java.util.logging.Logger.getLogger(FrmRegistValidate.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmRegistValidate.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmRegistValidate.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmRegistValidate.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrmRegistValidate().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton b0;
    private javax.swing.JButton b1;
    private javax.swing.JButton b2;
    private javax.swing.JButton b3;
    private javax.swing.JButton b4;
    private javax.swing.JButton b5;
    private javax.swing.JButton b6;
    private javax.swing.JButton b7;
    private javax.swing.JButton b8;
    private javax.swing.JButton b9;
    private javax.swing.JPasswordField badgeIdField;
    private javax.swing.JButton btnCheck;
    private javax.swing.JButton btnClear;
    private javax.swing.JButton deleteBtn;
    private javax.swing.JTextField idNumberField;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lblHora;
    private javax.swing.JLabel lblHora2;
    private javax.swing.JLabel lblHora3;
    private javax.swing.JLabel message;
    private javax.swing.JPanel numberPanel;
    // End of variables declaration//GEN-END:variables
}
