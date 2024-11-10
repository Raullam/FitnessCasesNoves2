package spdvi.fitnesscasesnoves.gui.tablemodels;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;
import java.util.Map;

public class IntentTableCellRenderer extends DefaultTableCellRenderer {
    private final Map<Integer, Integer> valoraciones;

    public IntentTableCellRenderer(Map<Integer, Integer> valoraciones) {
        this.valoraciones = valoraciones;
    }

    @Override
public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
    Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

    int intentId = (int) table.getValueAt(row, 0);
    Integer review = valoraciones.get(intentId);

    if (review == null) {
        c.setBackground(Color.YELLOW);
    } else if (review < 4) {
        c.setBackground(Color.RED);
    } else {
        c.setBackground(Color.GREEN);
    }

    return c;
}

}
