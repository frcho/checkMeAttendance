package Container;

import Internals.check;
import Internals.reloj;
import com.digitalpersona.onetouch.DPFPDataPurpose;
import com.digitalpersona.onetouch.DPFPFeatureSet;
import com.digitalpersona.onetouch.DPFPGlobal;
import com.digitalpersona.onetouch.DPFPSample;
import com.digitalpersona.onetouch.DPFPTemplate;
import com.digitalpersona.onetouch.capture.DPFPCapture;
import com.digitalpersona.onetouch.capture.event.DPFPDataAdapter;
import com.digitalpersona.onetouch.capture.event.DPFPDataEvent;
import com.digitalpersona.onetouch.capture.event.DPFPErrorAdapter;
import com.digitalpersona.onetouch.capture.event.DPFPErrorEvent;
import com.digitalpersona.onetouch.capture.event.DPFPReaderStatusAdapter;
import com.digitalpersona.onetouch.capture.event.DPFPReaderStatusEvent;
import com.digitalpersona.onetouch.capture.event.DPFPSensorAdapter;
import com.digitalpersona.onetouch.capture.event.DPFPSensorEvent;
import com.digitalpersona.onetouch.processing.DPFPEnrollment;
import com.digitalpersona.onetouch.processing.DPFPFeatureExtraction;
import com.digitalpersona.onetouch.processing.DPFPImageQualityException;
import static com.digitalpersona.onetouch.processing.DPFPTemplateStatus.TEMPLATE_STATUS_FAILED;
import static com.digitalpersona.onetouch.processing.DPFPTemplateStatus.TEMPLATE_STATUS_READY;
import com.digitalpersona.onetouch.verification.DPFPVerification;
import java.awt.Color;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

public class FrmLectorRegister extends javax.swing.JFrame {

    /*Variable para manejar el lector...*/
    private DPFPCapture Lector = DPFPGlobal.getCaptureFactory().createCapture();

    /*Varible que permite establecer las capturas de la huellas, para determina sus caracteristicas
    y poder estimar la creacion de un template de la huella para luego poder guardarla*/
    private DPFPEnrollment Reclutador = DPFPGlobal.getEnrollmentFactory().createEnrollment();

    /*Esta variable tambien captura una huella del lector y crea sus caracteristcas para auntentificarla 
    o verificarla con alguna guardada en la BD*/
    private DPFPVerification Verificador = DPFPGlobal.getVerificationFactory().createVerification();

    /*Variable que para crear el template de la huella luego de que se hallan creado las caracteriticas
    necesarias de la huella si no ha ocurrido ningun problema*/
    private DPFPTemplate template;
    public static String TEMPLATE_PROPERTY = "template";

    public FrmLectorRegister() {
        initComponents();
        reloj obj = new reloj(lblHora);
        obj.start();
        this.setLocationRelativeTo(null);
        sendText(FrmRegistValidate.employeeNameToSave, Color.BLUE);
    }

    public Image getIconImage() {
        Image retValue = Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("Resources/icon.png"));
        return retValue;
    }

    /**
     * Send text to label employeeName
     *
     * @param string message "custom message"
     * @param color Color e.g Color.orange
     */
    public void sendText(String string, Color color) {
        employeeName.setFont(new java.awt.Font("Tahoma", 0, 20));
        employeeName.setForeground(color);
        employeeName.setText(string);
    }

    protected void Iniciar() {
        //Captura de huella...
        Lector.addDataListener(new DPFPDataAdapter() {
            @Override
            public void dataAcquired(final DPFPDataEvent e) {
                SwingUtilities.invokeLater(new Runnable() {
                    public void run() {
                        EnviarTexto("The fingerprint has been captured");                                           
                        ProcesarCaptura(e.getSample());
                    }
                });
            }
        });
        //Manejo del estado del lector...
        Lector.addReaderStatusListener(new DPFPReaderStatusAdapter() {
            //Lector activado...
            @Override
            public void readerConnected(final DPFPReaderStatusEvent e) {
                SwingUtilities.invokeLater(new Runnable() {
                    public void run() {
                        EnviarTexto("The fingerprint reader is activated");
                    }
                });
            }

            //Lector desactivado...
            @Override
            public void readerDisconnected(final DPFPReaderStatusEvent e) {
                SwingUtilities.invokeLater(new Runnable() {
                    public void run() {
                        EnviarTexto("The fingerprint reader is not available");
                    }
                });
            }
        });
        //Manejo del Sensor...
        Lector.addSensorListener(new DPFPSensorAdapter() {
            //Sensor de colocación sobre la huella digital...
            @Override
            public void fingerTouched(final DPFPSensorEvent e) {
                SwingUtilities.invokeLater(new Runnable() {
                    public void run() {
                        EnviarTexto("The finger has been placed");
                    }
                });
            }

            //Sensor de retiro de dedo del lector...
            @Override
            public void fingerGone(final DPFPSensorEvent e) {
                SwingUtilities.invokeLater(new Runnable() {
                    public void run() {
                        EnviarTexto("The finger has been removed");
                    }
                });
            }
        });
        //Manejor de errores...
        Lector.addErrorListener(new DPFPErrorAdapter() {
            public void errorReader(final DPFPErrorEvent e) {
                SwingUtilities.invokeLater(new Runnable() {
                    public void run() {
                        EnviarTexto("Error: " + e.getError());
                    }
                });
            }
        });
    }

    //Variables para guardar y verificar las huellas en la BD...
    public DPFPFeatureSet featuresinscripcion;
    public DPFPFeatureSet featuresverificacion;

    public DPFPFeatureSet extraerCaracteristicas(DPFPSample sample, DPFPDataPurpose purpose) {
        DPFPFeatureExtraction extractor = DPFPGlobal.getFeatureExtractionFactory().createFeatureExtraction();
        try {
            return extractor.createFeatureSet(sample, purpose);
        } catch (DPFPImageQualityException e) {
            return null;
        }
    }

    public Image CrearImagenHuella(DPFPSample sample) {
        return DPFPGlobal.getSampleConversionFactory().createImage(sample);
    }

    public void DibujarHuella(Image image) {
        lblImagenHuella.setIcon(new ImageIcon(image.getScaledInstance(lblImagenHuella.getWidth(), lblImagenHuella.getHeight(), Image.SCALE_DEFAULT)));
        repaint();
    }

    public void EstadoHuellas() {

        lblNumberOfAttempts.setText("" + Reclutador.getFeaturesNeeded());
        EnviarTexto("Muestra de Huellas Necesarias para Guardar la Huella. Template " + Reclutador.getFeaturesNeeded());
    }

    public void EnviarTexto(String string) {
        lblStatus.setText(string);
    }

    public void start() {
        Lector.startCapture();
        EnviarTexto("Fingerprint reader is being used");
    }

    public void stop() {
        Lector.stopCapture();
        EnviarTexto("Fingerprint reader is not being used");
    }

    public DPFPTemplate getTemplate() {
        return template;
    }

    public void setTemplate(DPFPTemplate template) {
        DPFPTemplate old = this.template;
        this.template = template;
        firePropertyChange(TEMPLATE_PROPERTY, old, template);
    }

    public void ProcesarCaptura(DPFPSample sample) {
        // Procesar la muestra de la huella y crear un conjunto de características con el propósito de inscripción.
        featuresinscripcion = extraerCaracteristicas(sample, DPFPDataPurpose.DATA_PURPOSE_ENROLLMENT);
        // Procesar la muestra de la huella y crear un conjunto de características con el propósito de verificacion.
        featuresverificacion = extraerCaracteristicas(sample, DPFPDataPurpose.DATA_PURPOSE_VERIFICATION);

        // Comprobar la calidad de la muestra de la huella y lo añade a su reclutador si es bueno
        if (featuresinscripcion != null) {
            try {
                System.out.println("Las Caracteristicas de la Huella han sido creada");
                Reclutador.addFeatures(featuresinscripcion);// Agregar las caracteristicas de la huella a la plantilla a crear

                // Dibuja la huella dactilar capturada.
                Image image = CrearImagenHuella(sample);
                DibujarHuella(image);

            } catch (DPFPImageQualityException ex) {
                JOptionPane.showMessageDialog(null, ex);
            } finally {
                EstadoHuellas();
                System.out.println(Reclutador.getTemplateStatus());
                // Comprueba si la plantilla se ha creado.
                switch (Reclutador.getTemplateStatus()) {
                    case TEMPLATE_STATUS_READY:	// informe de éxito y detiene  la captura de huellas
                        stop();
                        setTemplate(Reclutador.getTemplate());
                        EnviarTexto("The fingerprint has been created, now you can save it");
                        btnSave.setEnabled(true);
                        btnSave.grabFocus();
                        break;
                    case TEMPLATE_STATUS_FAILED: // informe de fallas y reiniciar la captura de huellas
                        Reclutador.clear();
                        stop();
                        EstadoHuellas();
                        setTemplate(null);
                        JOptionPane.showMessageDialog(FrmLectorRegister.this, "The fingerprint cannot been created", "CheckMeIn - Fingerprint Template System", JOptionPane.ERROR_MESSAGE);
                        start();
                        break;
                }
            }
        }
    }

    /*
  * Guarda los datos de la huella digital actual en la base de datos
     */
    public void fingerPrintSave() throws IOException {

        //Obtiene los datos del template de la huella actual
        ByteArrayInputStream fingerprintData = new ByteArrayInputStream(template.serialize());
//        Integer fingerprintSize = template.serialize().length;
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        int nRead;
        byte[] info = new byte[1024];
        while ((nRead = fingerprintData.read(info, 0, info.length)) != -1) {
            buffer.write(info, 0, nRead);
        }
        buffer.flush();
        byte[] byteArray = buffer.toByteArray();
        //encoding  byte array into base 64
        byte[] fingerDataEncode = Base64.getEncoder().encode(byteArray);

//        System.out.println("Original String: " +  Arrays.toString(byteArray));
//        System.out.println("Base64 Encoded String : " + new String(encoded));
        //decoding byte array into base64
//        byte[] decoded = Base64.getDecoder().decode(encoded);
//        System.out.println("Base 64 Decoded  String : " + new String(decoded));
        check check = new check();
        if (check.isConnected()) {
            JOptionPane.showMessageDialog(null, "Connection Problems", "Warning",
                    JOptionPane.WARNING_MESSAGE);
        } else {
            List employee = check.searchEmployeeById(FrmRegistValidate.idEmployeeToSave);

            if (!employee.isEmpty()) {
                HashMap emp = (HashMap) employee.get(0);
                int id = (int) emp.get("id");
                Map data = new HashMap();
                //Adding elements to map  
//                data.put("x_fingerprint", "Genial");
                data.put("x_fingerprint", fingerDataEncode);
                check.addFingerprintToEmployee(id, data);
                
                
                lblNumberOfAttempts.setText("4");
                btnSave.setEnabled(false);
                sendText("Fingerprint Saved", Color.GREEN);
            }
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        lblImagenHuella = new javax.swing.JLabel();
        lblHora = new javax.swing.JLabel();
        lblStatus = new javax.swing.JLabel();
        lblNumberOfAttempts = new javax.swing.JLabel();
        btnSave = new javax.swing.JButton();
        employeeName = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("CheckMe Register");
        setIconImage(getIconImage());
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("Segoe UI Light", 0, 36)); // NOI18N
        jLabel1.setText("CheckMe Register");

        jLabel2.setFont(new java.awt.Font("Segoe UI Light", 0, 24)); // NOI18N
        jLabel2.setText("Time Attendance");

        lblImagenHuella.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));

        lblHora.setFont(new java.awt.Font("Segoe UI Light", 0, 14)); // NOI18N

        lblStatus.setFont(new java.awt.Font("Segoe UI Light", 0, 12)); // NOI18N
        lblStatus.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        lblNumberOfAttempts.setFont(new java.awt.Font("Tahoma", 0, 120)); // NOI18N
        lblNumberOfAttempts.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblNumberOfAttempts.setText("4");

        btnSave.setFont(new java.awt.Font("Segoe UI Light", 0, 14)); // NOI18N
        btnSave.setForeground(new java.awt.Color(255, 255, 255));
        btnSave.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/fondoBoton0.png"))); // NOI18N
        btnSave.setText("Save");
        btnSave.setBorder(null);
        btnSave.setBorderPainted(false);
        btnSave.setContentAreaFilled(false);
        btnSave.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSavebtnAceptarActionPerformed(evt);
            }
        });

        employeeName.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(442, 442, 442)
                        .addComponent(jLabel2)
                        .addGap(32, 32, 32)
                        .addComponent(lblHora, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(101, 101, 101)
                        .addComponent(jLabel1)))
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(156, 156, 156)
                .addComponent(lblImagenHuella, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(168, 168, 168)
                .addComponent(lblNumberOfAttempts, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 384, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(employeeName, javax.swing.GroupLayout.PREFERRED_SIZE, 359, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 82, Short.MAX_VALUE)
                .addComponent(btnSave)
                .addGap(241, 241, 241))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblHora, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel2)
                        .addComponent(jLabel1)))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblImagenHuella, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblNumberOfAttempts, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnSave)
                    .addComponent(lblStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(employeeName, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(35, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        Iniciar();
        start();
        btnSave.setEnabled(false);
    }//GEN-LAST:event_formWindowOpened

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        stop();
        FrmContainer obj = new FrmContainer();
        obj.setVisible(true);
    }//GEN-LAST:event_formWindowClosing

    private void btnSavebtnAceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSavebtnAceptarActionPerformed

        try {
            fingerPrintSave();
        } catch (IOException ex) {
            Logger.getLogger(FrmLectorRegister.class.getName()).log(Level.SEVERE, null, ex);
        }
        Reclutador.clear();
        lblImagenHuella.setIcon(null);
        start();
    }//GEN-LAST:event_btnSavebtnAceptarActionPerformed

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
            java.util.logging.Logger.getLogger(FrmLectorRegister.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmLectorRegister.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmLectorRegister.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmLectorRegister.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrmLectorRegister().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnSave;
    private javax.swing.JLabel employeeName;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lblHora;
    private javax.swing.JLabel lblImagenHuella;
    private javax.swing.JLabel lblNumberOfAttempts;
    private javax.swing.JLabel lblStatus;
    // End of variables declaration//GEN-END:variables
}
