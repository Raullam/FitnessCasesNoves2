/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package spdvi.fitnesscasesnoves.logica;

import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import spdvi.fitnesscasesnoves.dataAcces.DataAccess;
import spdvi.fitnesscasesnoves.dto.Intents;
import spdvi.fitnesscasesnoves.dto.Usuari;
import spdvi.fitnesscasesnoves.gui.UsuarisFrame;

/**
 *
 * @author Rulox
 */
public class UsuarisLogica {

    private final JFrame parentFrame;
    private final JTable jTable1;
    private final JTable jTable2;
    private JLabel jLabel3;

    public UsuarisLogica(UsuarisFrame aThis, JTable jTable1, JTable jTable2, JLabel jLabel3) {
        this.parentFrame = aThis;
        this.jTable1 = jTable1;
        this.jTable2 = jTable2;
        this.jLabel3 = jLabel3;

        insertarUsuarisAlJtext();
    }

    public final void insertarUsuarisAlJtext() {
        DataAccess da = new DataAccess();
        ArrayList<Usuari> usuaris = da.getUsuaris(); // Obtener la lista de usuarios desde la base de datos

        DefaultTableModel model = (DefaultTableModel) jTable1.getModel(); // Obtener el modelo de la tabla
        model.setRowCount(0); // Limpiar las filas anteriores del JTable

        // Limpiar columnas anteriores si ya estaban creadas
        model.setColumnCount(0);

        // Añadir las columnas que deseas para jTable1
        model.addColumn("ID Usuari");
        model.addColumn("Nom");
        model.addColumn("Email");
        model.addColumn("Contrasenya");

        // Iterar sobre cada usuario y agregarlo al JTable
        for (Usuari u : usuaris) {
            // Crear un arreglo de objetos para cada fila (usuario)
            Object[] row = {u.getId(), u.getNom(), u.getEmail(), u.getPasswordHash().substring(0, 5)};
            model.addRow(row); // Agregar la fila al modelo de la tabla
        }

        // Añadir ListSelectionListener para detectar la fila seleccionada
        jTable1.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent event) {
                if (!event.getValueIsAdjusting()) {
                    int selectedRow = jTable1.getSelectedRow();
                    if (selectedRow != -1) {
                        // Obtener el ID del usuario seleccionado (columna 0)
                        Object id = jTable1.getValueAt(selectedRow, 0);
                        System.out.println("Id del usuari seleccionat: " + id);

                        // Obtener la lista de intentos del usuario seleccionado
                        ArrayList<Intents> intents = da.getIntentsPerId((int) id);
                        System.out.println("Intentos per l'usuari: " + intents.size());

                        // Actualizar la tabla jTable2 con los intentos
                        DefaultTableModel modelIntents = (DefaultTableModel) jTable2.getModel();
                        modelIntents.setRowCount(0); // Limpiar la tabla de intentos

                        // Limpiar columnas anteriores si ya estaban creadas
                        modelIntents.setColumnCount(0);

                        // Añadir las columnas que deseas para jTable2
                        modelIntents.addColumn("ID Intent");
                        modelIntents.addColumn("ID Exercici");
                        modelIntents.addColumn("Timestamp Inici");
                        modelIntents.addColumn("Timestamp Fi");
                        modelIntents.addColumn("Videofile");

                        // Rellenar el modelo de la tabla con los intentos
                        for (Intents intent : intents) {
                            Object[] intentRow = {
                                intent.getId(),
                                intent.getIdExercici(),
                                intent.getTimestamp_Inici(),
                                intent.getTimestamp_Fi(),
                                intent.getVideofile()
                            };
                            modelIntents.addRow(intentRow);
                        }

                        // Actualizar la etiqueta con el ID seleccionado
                        jLabel3.setText("Id del usuari seleccionat: " + id.toString());
                    }
                }
            }
        });
    }

}
