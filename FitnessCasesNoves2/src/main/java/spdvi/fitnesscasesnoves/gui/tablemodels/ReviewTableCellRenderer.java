/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package spdvi.fitnesscasesnoves.gui.tablemodels;

/**
 *
 * @author Rulox
 */
import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;

public class ReviewTableCellRenderer extends DefaultTableCellRenderer {

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

        // Obtener el valor de la valoración de la fila actual (columna 3)
        int valoracion = (int) table.getValueAt(row, 3); // Suponiendo que la columna de valoración es la 3 (índice 3)

        // Definir colores basados en la valoración
        if (valoracion >= 4) {
            c.setBackground(Color.GREEN); // Valoración alta
        } else {
            c.setBackground(Color.RED); // Valoración baja
        }

        // Si la fila está seleccionada, resaltar con un color diferente
        if (isSelected) {
            c.setBackground(c.getBackground().darker()); // Un poco más oscuro cuando está seleccionado
        }

        return c;
    }
}
