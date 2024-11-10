/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package spdvi.fitnesscasesnoves.gui;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import javax.swing.JPanel;

/**
 *
 * @author Raül Lama
 */
public class App extends javax.swing.JFrame {

    public App() {
        initComponents();
        configurarLabel();
        getContentPane().setBackground(Color.BLACK); // Cambia a tu color deseado
        setTitle("Inici");


        //AlumnesTableModel atm = new AlumnesTableModel(logica.Negoci)
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButtonAcedirALaApp = new javax.swing.JButton();
        jLabelImatge = new javax.swing.JLabel();
        jLabelTitol = new javax.swing.JLabel();
        jLabel1Link = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        jButtonAcedirALaApp.setText("Accedir a la app");
        jButtonAcedirALaApp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAcedirALaAppActionPerformed(evt);
            }
        });

        jLabelImatge.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Fitnes-Club2.png"))); // NOI18N

        jLabelTitol.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabelTitol.setForeground(new java.awt.Color(255, 255, 255));
        jLabelTitol.setText("Fitness Cases noves");

        jLabel1Link.setBackground(new java.awt.Color(204, 204, 204));
        jLabel1Link.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel1LinkMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButtonAcedirALaApp)
                .addGap(20, 20, 20))
            .addGroup(layout.createSequentialGroup()
                .addGap(131, 131, 131)
                .addComponent(jLabelImatge, javax.swing.GroupLayout.PREFERRED_SIZE, 255, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(52, Short.MAX_VALUE)
                .addComponent(jLabel1Link, javax.swing.GroupLayout.PREFERRED_SIZE, 285, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(63, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabelTitol, javax.swing.GroupLayout.PREFERRED_SIZE, 255, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(35, 35, 35))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(50, Short.MAX_VALUE)
                .addComponent(jLabelTitol, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabelImatge, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1Link, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButtonAcedirALaApp)
                .addGap(23, 23, 23))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonAcedirALaAppActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAcedirALaAppActionPerformed
        LogIn Label3 = new LogIn();
        Label3.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_jButtonAcedirALaAppActionPerformed

    private void jLabel1LinkMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1LinkMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel1LinkMouseClicked
    private void configurarLabel() {
        this.setLocationRelativeTo(null);

        jLabel1Link.setText("<html><a href=''>https://github.com/Raullam/FitnessCasesNoves2</a></html>");

        jLabel1Link.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                abrirEnlace("https://github.com/Raullam/FitnessCasesNoves2");
            }

            @Override
            public void mouseEntered(MouseEvent evt) {
                jLabel1Link.setCursor(new Cursor(Cursor.HAND_CURSOR)); // Cambiar a cursor de mano
            }

            @Override
            public void mouseExited(MouseEvent evt) {
                jLabel1Link.setCursor(new Cursor(Cursor.DEFAULT_CURSOR)); // Volver al cursor por defecto
            }
        });
    }
    // PONER EN LOGICA APP
    private void abrirEnlace(String url) {
        try {
            Desktop.getDesktop().browse(new URI(url));
        } catch (IOException | URISyntaxException ex) {
            ex.printStackTrace();
        }
    }

    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new App().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonAcedirALaApp;
    private javax.swing.JLabel jLabel1Link;
    private javax.swing.JLabel jLabelImatge;
    private javax.swing.JLabel jLabelTitol;
    // End of variables declaration//GEN-END:variables
}
