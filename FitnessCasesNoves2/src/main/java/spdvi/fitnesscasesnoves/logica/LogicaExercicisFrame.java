/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package spdvi.fitnesscasesnoves.logica;

import java.util.ArrayList;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import spdvi.fitnesscasesnoves.dataAcces.DataAccess;
import spdvi.fitnesscasesnoves.dto.Exercici;

/**
 *
 * @author Rulox
 */
public class LogicaExercicisFrame {
    
    private final JLabel jLabel1;
    private final JTable jTable1;

    public LogicaExercicisFrame(JLabel jLabel1, JTable jTable1) {
        this.jLabel1 = jLabel1;
        this.jTable1 = jTable1;
        
    }
    public final void insertarExercicisAlJtext() {
    DataAccess da = new DataAccess();
    ArrayList<Exercici> exercicis = da.getExercicis(); // Obtener la lista de ejercicios desde la base de datos

    DefaultTableModel model = configurarModeloTabla(jTable1); // Configura la tabla
    agregarDatosATabla(model, exercicis); // Llena la tabla con datos
    agregarListenerSeleccion(jTable1); // Agrega el listener de selección
}

private DefaultTableModel configurarModeloTabla(JTable tabla) {
    DefaultTableModel model = (DefaultTableModel) tabla.getModel();
    model.setRowCount(0); // Limpiar las filas anteriores
    model.setColumnCount(0); // Limpiar columnas anteriores

    // Añadir las columnas deseadas
    model.addColumn("ID Exercici");
    model.addColumn("Nom Exercici");
    model.addColumn("Descripció");

    return model;
}

private void agregarDatosATabla(DefaultTableModel model, ArrayList<Exercici> exercicis) {
    for (Exercici exercici : exercicis) {
        Object[] row = {
            exercici.getId(),
            exercici.getNomExercici(),
            exercici.getDescripcio()
        };
        model.addRow(row); // Agregar la fila al modelo de la tabla
    }
}

private void agregarListenerSeleccion(JTable tabla) {
    tabla.getSelectionModel().addListSelectionListener(event -> {
        if (!event.getValueIsAdjusting()) {
            int selectedRow = tabla.getSelectedRow();
            if (selectedRow != -1) {
                Object idExercici = tabla.getValueAt(selectedRow, 0);
                System.out.println("Id del exercici seleccionat: " + idExercici);
                jLabel1.setText("Id del exercici seleccionat: " + idExercici.toString());
            }
        }
    });
}

    
}
