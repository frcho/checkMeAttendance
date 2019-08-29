package Container;

import DB.ConexionMySQL;
import Internals.check;
import Internals.reloj;
import Utils.Util;
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
import com.digitalpersona.onetouch.verification.DPFPVerificationResult;
import java.awt.Color;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.IOException;
import static java.util.Arrays.asList;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

public class FrmLector extends javax.swing.JFrame {

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

    public FrmLector() {
        initComponents();
        reloj obj = new reloj(lblHora);
        obj.start();
        this.setLocationRelativeTo(null);
    }

    public Image getIconImage() {
        Image retValue = Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("Resources/icon.png"));
        return retValue;
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
                        try {
                            identificarHuella();
                            Reclutador.clear();
                        } catch (IOException ex) {
                            Logger.getLogger(FrmLector.class.getName()).log(Level.SEVERE, null, ex);
                        } finally {
//                            lblImagenHuella.setIcon(null);
                        }
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
        //EnviarTexto("Muestra de Huellas Necesarias para Guardar la Huella. Template "+ Reclutador.getFeaturesNeeded());
    }

    public void EnviarTexto(String string) {
        //txtArea.append(string + "\n");
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
                //System.out.println("Las Caracteristicas de la Huella han sido creada");
                Reclutador.addFeatures(featuresinscripcion);// Agregar las caracteristicas de la huella a la plantilla a crear

                // Dibuja la huella dactilar capturada.
                Image image = CrearImagenHuella(sample);
                DibujarHuella(image);

            } catch (DPFPImageQualityException ex) {
                JOptionPane.showMessageDialog(null, ex);
//                System.err.println("Error: "+ex.getMessage());
            } finally {
                EstadoHuellas();
                // Comprueba si la plantilla se ha creado.
                switch (Reclutador.getTemplateStatus()) {
                    case TEMPLATE_STATUS_READY:	// informe de éxito y detiene  la captura de huellas
                        stop();
                        setTemplate(Reclutador.getTemplate());
                        EnviarTexto("The fingerprint has been created, now you can save it");
                        break;
                    case TEMPLATE_STATUS_FAILED: // informe de fallas y reiniciar la captura de huellas
                        Reclutador.clear();
                        stop();
                        EstadoHuellas();
                        setTemplate(null);
                        JOptionPane.showMessageDialog(FrmLector.this, "The fingerprint cannot been created", "CheckMeIn - Fingerprint Template System", JOptionPane.ERROR_MESSAGE);
                        start();
                        break;
                }
            }
        }
    }

    public static int usuario;

    public void identificarHuella() throws IOException {

        if (featuresverificacion != null) {
            check check = new check();
            List employeList = check.allEmployeeWithFingerPrint();

            int i = 0;
            while (i < employeList.size()) {
                HashMap emp = (HashMap) employeList.get(i);
//          String email = (String) emp.get("work_email");
//          List employee = check.searchEmployee((String) selectedEmailEmployee);
                if (!employeList.isEmpty()) {
//                HashMap emp = (HashMap) employee.get(0);
                    Integer id = (Integer) emp.get("id");
                    String name = (String) emp.get("name");
                    String finger = (String) emp.get("x_fingerprint");
                    //decoding byte array into base64
                    byte[] fingerDataDecode = Base64.getDecoder().decode(finger);
                    //Crea una nueva plantilla a partir de la guardada en la base de datos
                    DPFPTemplate referenceTemplate = DPFPGlobal.getTemplateFactory().createTemplate(fingerDataDecode);
                    //Envia la plantilla creada al objeto contendor de Template del componente de huella digital
                    setTemplate(referenceTemplate);
                    // Compara las caracteriticas de la huella recientemente capturda con la
                    // alguna plantilla guardada en la base de datos que coincide con ese tipo
                    DPFPVerificationResult result = Verificador.verify(featuresverificacion, getTemplate());
                    //compara las plantilas (actual vs bd)
                    //Si encuentra correspondencia dibuja el mapa
                    //e indica el nombre de la persona que coincidió.
                    if (result.isVerified()) {
                        registerAttendee(name, id);
                        return;
                    }
                }
                i++;
            }
            //Si no encuentra alguna huella correspondiente al nombre lo indica con un mensaje
            JOptionPane.showMessageDialog(null, "No existe ningún registro que coincida con la huella", "Verificacion de Huella", JOptionPane.ERROR_MESSAGE);
            setTemplate(null);
        } else {
            //Si no encuentra alguna huella correspondiente al nombre lo indica con un mensaje
//            JOptionPane.showMessageDialog(null, "Something happend with device, Maybe Its broken, Try with other", "Verificacion de Huella", JOptionPane.ERROR_MESSAGE);
            EnviarTexto("Something happend with device, Maybe Its broken, Try with other");
            setTemplate(null);
        }
    }

    /**
     * Allo to send text to label
     *
     * @param string
     * @param status
     */
    public void sendTextInOut(String string, Integer status) {
        employeeName.setForeground(Color.orange);
        employeeName.setText(string);
        if (status == 1) {
            employeeName.setForeground(Color.GREEN);
            employeeName.setText(string);
        }
    }

    public void sendHour(String string, Integer status) {
        lblHora2.setForeground(Color.orange);
        lblHora2.setFont(new java.awt.Font("Tahoma", 0, 85));
        lblHora2.setText(string);
        if (status == 1) {
            lblHora2.setForeground(Color.GREEN);
            lblHora2.setFont(new java.awt.Font("Tahoma", 0, 50));
            lblHora2.setText(string);
        }
    }

    public void registerAttendee(String name, Integer id) {
        check check = new check();
        Map attendance = check.checkinOut(id);

        if (attendance.containsKey("check_out")) {
            String checkIn = (String) attendance.get("check_in");
            String checkOut = (String) attendance.get("check_out");

            String calculateWorkedTime = Util.DifferenceBetweenDates(checkIn, checkOut);

            sendHour(calculateWorkedTime, 0);
            sendTextInOut(name, 0);
        } else {
            String checkIn = (String) attendance.get("check_in");
            sendHour(checkIn, 1);
            sendTextInOut(name, 1);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        employeeName = new javax.swing.JLabel();
        lblImagenHuella = new javax.swing.JLabel();
        lblHora = new javax.swing.JLabel();
        lblStatus = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        lblHora1 = new javax.swing.JLabel();
        lblHora2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Check In");
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
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("CheckMeIn");

        employeeName.setFont(new java.awt.Font("Segoe UI Light", 1, 36)); // NOI18N
        employeeName.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        lblImagenHuella.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));

        lblHora.setFont(new java.awt.Font("Segoe UI Light", 0, 14)); // NOI18N

        lblStatus.setFont(new java.awt.Font("Segoe UI Light", 0, 12)); // NOI18N
        lblStatus.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        jLabel3.setFont(new java.awt.Font("Segoe UI Light", 0, 24)); // NOI18N
        jLabel3.setText("Time Attendance Checker");

        lblHora1.setFont(new java.awt.Font("Segoe UI Light", 0, 14)); // NOI18N

        lblHora2.setFont(new java.awt.Font("Segoe UI Light", 1, 80)); // NOI18N
        lblHora2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblHora2.setText("00:00");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(122, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(173, 173, 173)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblHora, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(57, 57, 57))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(lblImagenHuella, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(55, 55, 55)
                        .addComponent(lblHora2, javax.swing.GroupLayout.PREFERRED_SIZE, 520, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(24, 24, 24))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addComponent(employeeName, javax.swing.GroupLayout.PREFERRED_SIZE, 643, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(116, Short.MAX_VALUE))))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(lblStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 365, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                    .addContainerGap(707, Short.MAX_VALUE)
                    .addComponent(lblHora1, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap()))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(34, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(jLabel3))
                        .addGap(18, 18, 18)
                        .addComponent(lblImagenHuella, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addComponent(lblHora, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lblHora2, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(19, 19, 19)))
                .addGap(13, 13, 13)
                .addComponent(lblStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(employeeName, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(25, 25, 25))
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addGap(50, 50, 50)
                    .addComponent(lblHora1, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(324, Short.MAX_VALUE)))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
        //EstadoHuellas();
    }//GEN-LAST:event_formWindowOpened

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        stop();
        FrmContainer obj = new FrmContainer();
        obj.setVisible(true);
    }//GEN-LAST:event_formWindowClosing

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
            java.util.logging.Logger.getLogger(FrmLector.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmLector.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmLector.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmLector.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrmLector().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel employeeName;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lblHora;
    private javax.swing.JLabel lblHora1;
    private javax.swing.JLabel lblHora2;
    private javax.swing.JLabel lblImagenHuella;
    private javax.swing.JLabel lblStatus;
    // End of variables declaration//GEN-END:variables
}
