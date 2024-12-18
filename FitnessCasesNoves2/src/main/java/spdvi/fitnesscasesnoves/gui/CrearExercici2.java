package spdvi.fitnesscasesnoves.gui;

import javax.swing.JOptionPane;
import spdvi.fitnesscasesnoves.dataAcces.DataAccess;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
/**
 *
 * @author Rulox
 */
public class CrearExercici2 extends javax.swing.JFrame {

    static int usuariIdd;
    private ExercicisFrame exercicisFrame;

    /**
     * Creates new form CrearExercici
     */
    public CrearExercici2() {
        initComponents();
        this.setLocationRelativeTo(null); // esto es para que se centre en la pantalla
        setTitle("CREA UN EXERCICI");
    }

    CrearExercici2(ExercicisFrame aThis) {
        initComponents();
        this.setLocationRelativeTo(null); // esto es para que se centre en la pantalla
        this.exercicisFrame = aThis;
        setTitle("CREA UN EXERCICI");
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabelNomUsuari = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextFieldDescripcioExercici = new javax.swing.JTextArea();
        jButton1 = new javax.swing.JButton();
        jTextFieldNomExercici = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        jLabel1.setText("Descripció nou exercici");

        jTextFieldDescripcioExercici.setColumns(20);
        jTextFieldDescripcioExercici.setRows(5);
        jScrollPane1.setViewportView(jTextFieldDescripcioExercici);

        jButton1.setText("Enviar exercici");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jTextFieldNomExercici.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldNomExerciciActionPerformed(evt);
            }
        });

        jLabel3.setText("Nom de l'exercici");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(55, 55, 55)
                .addComponent(jLabelNomUsuari, javax.swing.GroupLayout.PREFERRED_SIZE, 332, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 55, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 332, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jTextFieldNomExercici, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 157, Short.MAX_VALUE)))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addComponent(jLabelNomUsuari, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextFieldNomExercici, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton1)
                .addContainerGap(14, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTextFieldNomExerciciActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldNomExerciciActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldNomExerciciActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        String nombreExercici = jTextFieldNomExercici.getText().trim();
        String descripcionExercici = jTextFieldDescripcioExercici.getText().trim();

        if (nombreExercici.isEmpty()) {
            JOptionPane.showMessageDialog(this, "El nom de l'exercici no pot esta buit.");
            return; // Sorti del metode si el nom de l'exercici esta buit 
        }

        if (descripcionExercici.isEmpty()) {
            JOptionPane.showMessageDialog(this, "La descripcio de l'exercici no pot estar buida.");
            return; // Sorti del metode si la descripcio esta buida 
        }

        DataAccess dataAccess = new DataAccess();
        int idExercici = dataAccess.crearExerciciEnBD(nombreExercici, descripcionExercici);

        // Verificar si la inserció ha estat exitosa
        if (idExercici != -1) {
            JOptionPane.showMessageDialog(this, "Ejercicio creado exitosamente con ID: " + idExercici);
            exercicisFrame.insertarExercicisAlJTable();
            dispose();

        } else {
            JOptionPane.showMessageDialog(this, "Error al crear el ejercicio.");
    }    }//GEN-LAST:event_jButton1ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new CrearExercici2().setVisible(true);
            }
        });
    }

    public void setUsuario(int usuarioId) {
        DataAccess da = new DataAccess();
        if (usuarioId > 0) {
            // Obte el nom de l'usuari utilizant l'ID
            usuariIdd = usuarioId;
            String nombreUsuario = da.getNombreById(usuarioId);

            jLabelNomUsuari.setText("Crean exercici per l'usuari amb id: " + usuarioId + ". I amb el nom: " + nombreUsuario);
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabelNomUsuari;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextFieldDescripcioExercici;
    private javax.swing.JTextField jTextFieldNomExercici;
    // End of variables declaration//GEN-END:variables
}
