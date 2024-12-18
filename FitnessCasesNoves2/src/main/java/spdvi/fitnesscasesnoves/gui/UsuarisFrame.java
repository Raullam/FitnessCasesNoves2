/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package spdvi.fitnesscasesnoves.gui;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;
import spdvi.fitnesscasesnoves.logica.LogicaPaginaPrincipal;
import spdvi.fitnesscasesnoves.logica.UsuarisLogica;

/**
 *
 * @author Rulox
 */
public class UsuarisFrame extends javax.swing.JFrame {

    LogicaPaginaPrincipal logicaPaginaPrincipal;

    /**
     * Creates new form ExercicisFrame
     */
    public UsuarisFrame() {
        initComponents();
        this.setLocationRelativeTo(null); // esto es para que se centre en la pantalla
        UsuarisLogica ul = new UsuarisLogica(this, jTable1, jTable2, jLabel3IdUsuariSelecionat);
        setTitle("Usuaris");

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1Usuaris = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jScrollPane2Intents = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jLabel1Usuaris = new javax.swing.JLabel();
        jLabel2Intents = new javax.swing.JLabel();
        jButton1Tornarenrere = new javax.swing.JButton();
        jLabel3IdUsuariSelecionat = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1Usuaris.setViewportView(jTable1);

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jTable2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable2MouseClicked(evt);
            }
        });
        jScrollPane2Intents.setViewportView(jTable2);

        jLabel1Usuaris.setText("Usuaris");

        jLabel2Intents.setText("Intents");

        jButton1Tornarenrere.setText("Tornar enrera");
        jButton1Tornarenrere.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1TornarenrereActionPerformed(evt);
            }
        });

        jLabel3IdUsuariSelecionat.setText("Id del usuari seleccionat: ");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(204, 204, 204)
                .addComponent(jLabel1Usuaris, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel2Intents, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(137, 137, 137))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton1Tornarenrere, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel3IdUsuariSelecionat, javax.swing.GroupLayout.PREFERRED_SIZE, 319, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jScrollPane1Usuaris, javax.swing.GroupLayout.PREFERRED_SIZE, 405, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 63, Short.MAX_VALUE)
                                .addComponent(jScrollPane2Intents, javax.swing.GroupLayout.PREFERRED_SIZE, 406, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGap(35, 35, 35))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(44, 44, 44)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1Usuaris)
                    .addComponent(jLabel2Intents))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane2Intents, javax.swing.GroupLayout.DEFAULT_SIZE, 271, Short.MAX_VALUE)
                    .addComponent(jScrollPane1Usuaris, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(jLabel3IdUsuariSelecionat)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 12, Short.MAX_VALUE)
                .addComponent(jButton1Tornarenrere, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(17, 17, 17))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1TornarenrereActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1TornarenrereActionPerformed
        PaginaPrincipal aa = new PaginaPrincipal();
        aa.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_jButton1TornarenrereActionPerformed

    private void jTable2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable2MouseClicked
// Obtenim el text complet de jLabel3IdUsuariSelecionat

        int selectedRow = jTable2.getSelectedRow(); // Obtenir la fila seleccionada de jTable1

        if (selectedRow != -1) { // Comprova que hi hagi files seleccionades a ambdues taules
            // Obtenir els valors de la fila seleccionada de jTable2
            int idIntent = Integer.parseInt(jTable2.getValueAt(selectedRow, 0).toString()); // ID del intent

            // Crear i inicialitzar el frame ReviewVideoFrame amb les dades de l'intent
            ReviewVideoFrame2 reviewFrame = new ReviewVideoFrame2(idIntent);
            reviewFrame.setIdIntentLabel(idIntent);
            reviewFrame.setVisible(true); // Mostrar el frame
            reviewFrame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
            // Obtener la ventana principal y deshabilitarla
            JFrame mainFrame = (JFrame) SwingUtilities.getWindowAncestor(jTable1);
            mainFrame.setEnabled(false);

            // Agregar un WindowListener para reactivar la ventana principal cuando se cierre el frame secundario
            reviewFrame.addWindowListener(new java.awt.event.WindowAdapter() {
                @Override
                public void windowClosed(java.awt.event.WindowEvent e) {
                    mainFrame.setEnabled(true);
                    mainFrame.toFront(); // Llevar la ventana principal al frente
                }
            });

        } else {
            System.out.println("Selecciona una fila a ambdues taules abans de continuar.");
        } 
    }//GEN-LAST:event_jTable2MouseClicked

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
            java.util.logging.Logger.getLogger(UsuarisFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(UsuarisFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(UsuarisFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(UsuarisFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new UsuarisFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1Tornarenrere;
    private javax.swing.JLabel jLabel1Usuaris;
    private javax.swing.JLabel jLabel2Intents;
    private javax.swing.JLabel jLabel3IdUsuariSelecionat;
    private javax.swing.JScrollPane jScrollPane1Usuaris;
    private javax.swing.JScrollPane jScrollPane2Intents;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    // End of variables declaration//GEN-END:variables
}
