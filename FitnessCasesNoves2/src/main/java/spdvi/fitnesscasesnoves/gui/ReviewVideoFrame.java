/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package spdvi.fitnesscasesnoves.gui;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import spdvi.fitnesscasesnoves.dataAcces.DataAccess;
import uk.co.caprica.vlcj.factory.MediaPlayerFactory;
import uk.co.caprica.vlcj.player.embedded.EmbeddedMediaPlayer;
import uk.co.caprica.vlcj.player.embedded.videosurface.VideoSurface;

/**
 *
 * @author Rulox
 */
public class ReviewVideoFrame extends javax.swing.JFrame {

    private String idIntent;
    private int id, idUsuari,Idintentt;
    private String exercici;
    private String idExercici, timestampInici, timestampFi, videofile;
    private EmbeddedMediaPlayer mediaPlayer;
    private MediaPlayerFactory mediaPlayerFactory;
    private DataAccess dataAccess;
    static File videoFile ;

    /**
     * Creates new form DetallIntent
     */
    public ReviewVideoFrame() {
        initComponents();
        this.setLocationRelativeTo(null); // Centro en la pantalla
        initializeVLCPlayer();
        loadVideoFromIntent();
        dataAccess = new DataAccess();
        setTitle("Crea VALORACIÓ ");

    }

    public ReviewVideoFrame(String intent) {
        this.idIntent = intent;
        initComponents();
        this.setLocationRelativeTo(null); // Centro en la pantalla
        jLabel1.setText(idIntent);
        initializeVLCPlayer();
        loadVideoFromIntent();
        dataAccess = new DataAccess();
        ponerIdInstructorLabel();
        setTitle("Crea VALORACIÓ ");

    }

    public ReviewVideoFrame(String intent, int id, String exercici) {
        this.idIntent = intent;
        this.id = id;
        this.exercici = exercici;
        initComponents();
        this.setLocationRelativeTo(null); // esto es para que se centre en la pantalla
        dataAccess = new DataAccess();

        jLabel1.setText(idIntent);
        initializeVLCPlayer();
        loadVideoFromIntent();
        ponerIdInstructorLabel();
        setTitle("Crea VALORACIÓ ");

    }
     public ReviewVideoFrame(int Idintentt) {
        this.Idintentt = Idintentt;
        initComponents();
        this.setLocationRelativeTo(null); // esto es para que se centre en la pantalla
        dataAccess = new DataAccess();
        jLabel1.setText(idIntent);
        initializeVLCPlayer();
        loadVideoFromIntent2();
        ponerIdInstructorLabel();
        setTitle("Crea VALORACIÓ ");

    }

    ReviewVideoFrame(String idIntent, int idUsuari, String idExercici, String timestampInici, String timestampFi, String videofile) {
        this.idIntent = idIntent;
        this.idUsuari = idUsuari;
        this.idExercici = idExercici;
        this.timestampInici = timestampInici;
        this.timestampFi = timestampFi;
        this.videofile = videofile;
        dataAccess = new DataAccess();
        initializeVLCPlayer();
        loadVideoFromIntent();
        ponerIdInstructorLabel();
        setTitle("Crea VALORACIÓ ");

    }
    

    private void initializeVLCPlayer() {
        mediaPlayerFactory = new MediaPlayerFactory();
        mediaPlayer = mediaPlayerFactory.mediaPlayers().newEmbeddedMediaPlayer();
        Canvas videoCanvas = new Canvas();
        videoCanvas.setBackground(Color.black);
        videoCanvas.setSize(jPanel1.getSize());

        // Configurar el Canvas de video en jPanel1
        jPanel1.setLayout(new BorderLayout());
        jPanel1.add(videoCanvas, BorderLayout.CENTER);

        // Configurar el media player con el videoCanvas como superficie de reproducción
        VideoSurface videoSurface = mediaPlayerFactory.videoSurfaces().newVideoSurface(videoCanvas);
        mediaPlayer.videoSurface().set(videoSurface);
    }

    private void loadVideoFromIntent() {
        String labelText = jLabel1.getText();
        String intentIdString = labelText.substring(3, 8).trim();
        int intentId = Integer.parseInt(intentIdString);

        // Usar DataAccess para obtener el archivo de video
        String videoFilePath = dataAccess.getVideoFile(intentId);

        if (videoFilePath != null) {
             videoFile = new File("videos/" + videoFilePath);
            if (videoFile.exists()) {
                mediaPlayer.media().startPaused(videoFile.getAbsolutePath());
            } else {
                System.out.println("El archivo de video no existe: " + videoFilePath);
            }
        } else {
            System.out.println("No se encontró el video para el intento: " + intentId);
        }
    }
    private void loadVideoFromIntent2() {
        String labelText = jLabel1.getText();
        int intentId = Idintentt;

        // Usar DataAccess para obtener el archivo de video
        String videoFilePath = dataAccess.getVideoFile(intentId);

        if (videoFilePath != null) {
             videoFile = new File("videos/" + videoFilePath);
            if (videoFile.exists()) {
                mediaPlayer.media().startPaused(videoFile.getAbsolutePath());
            } else {
                System.out.println("El archivo de video no existe: " + videoFilePath);
            }
        } else {
            System.out.println("No se encontró el video para el intento: " + intentId);
        }
    }

    private void playVideo() {
        if (mediaPlayer != null) {
            mediaPlayer.controls().play();
        }
    }

    private void pauseVideo() {
        if (mediaPlayer != null) {
            mediaPlayer.controls().pause();
        }
    }

    private void stopVideo() {
        if (mediaPlayer != null) {
            mediaPlayer.controls().stop();
                            mediaPlayer.media().startPaused(videoFile.getAbsolutePath());

        }
    }

    private void ponerIdInstructorLabel() {
        int idInstructor = DataAccess.idInstructor;
        jLabel4.setText("Id Instructor: " + idInstructor);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        jButton1.setText("Crear Review");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel2.setText("Valoració:");

        jLabel3.setText("Comentari:");

        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane1.setViewportView(jTextArea1);

        jButton2.setText("Torna enrera");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("Play");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setText("Pause");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton5.setText("Stop");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel1.setLayout(new java.awt.BorderLayout());

        jLabel4.setText("Id Instructor: ");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(43, 43, 43)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 335, Short.MAX_VALUE)
                        .addGap(40, 40, 40)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, 134, Short.MAX_VALUE)
                            .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(37, 37, 37)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(14, 14, 14)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jButton4)
                                            .addComponent(jButton3)
                                            .addComponent(jButton5)))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(12, 12, 12)
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(17, 17, 17))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 14, Short.MAX_VALUE)
                        .addComponent(jButton4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton5)
                        .addGap(24, 24, 24)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(jLabel4))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel3)
                        .addGap(6, 6, 6))
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButton1)
                        .addGap(10, 10, 10)
                        .addComponent(jButton2))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(11, 11, 11))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // Extraer y validar la valoración de jTextField1
        String valoracioText = jTextField1.getText().trim();
        int valoracio;

        try {
            valoracio = Integer.parseInt(valoracioText);
            if (valoracio < 0 || valoracio > 10) {
                // Valor fuera de rango
                JOptionPane.showMessageDialog(this, "La valoración debe estar entre 0 y 10.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
        } catch (NumberFormatException e) {
            // No es un número válido
            JOptionPane.showMessageDialog(this, "Introduce un número entero válido para la valoración (0-10).", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Obtener el comentario desde jTextArea1 (sin validación adicional)
        String comentari = jTextArea1.getText().trim();

        // Extraer el Id del intento desde jLabel1
        String labelText = jLabel1.getText();
        String intentIdString = labelText.length() >= 8 ? labelText.substring(3, 8).trim() : "";
        int idIntent;

        try {
            idIntent = Integer.parseInt(intentIdString);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "No se pudo obtener un ID válido para el intento.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // El id del revisor (monitor) lo obtenemos de una variable de sesión (o parámetro similar)
        int idReviewer = DataAccess.idInstructor; // Asumiendo que tienes un método para obtener el ID del revisor

        // Insertar la review en la base de datos
        boolean reviewCreated = dataAccess.createReview(idIntent, idReviewer, valoracio, comentari);

        if (reviewCreated) {
            JOptionPane.showMessageDialog(this, "Review creada exitosamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            dispose();
        } else {
            JOptionPane.showMessageDialog(this, "Hubo un error al crear la review.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField1ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        pauseVideo();
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        playVideo();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        stopVideo();
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        this.dispose();
    }//GEN-LAST:event_jButton2ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ReviewVideoFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ReviewVideoFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ReviewVideoFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ReviewVideoFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ReviewVideoFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextField jTextField1;
    // End of variables declaration//GEN-END:variables
}
