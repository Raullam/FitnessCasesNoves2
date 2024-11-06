/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package spdvi.fitnesscasesnoves.logica;

import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import spdvi.fitnesscasesnoves.dataAcces.DataAccess;
import spdvi.fitnesscasesnoves.dto.Usuari;
import spdvi.fitnesscasesnoves.gui.PaginaPrincipal;
import spdvi.fitnesscasesnoves.gui.UsuarisFrame;

/**
 *
 * @author Rulox
 */
public class LogicaPaginaPrincipal {

    private final JFrame parentFrame;
    private final JTable jTable1;
    private final JTable jTable2;
    private JLabel jLabel3;
    private JList<String> jList1;
    private DataAccess da;

    // Constructor que recibe el JFrame y JTable como parámetros
    public LogicaPaginaPrincipal(JFrame parentFrame, JTable jTable1) {
        this.parentFrame = parentFrame;
        this.jTable1 = jTable1;
        this.jTable2 = null;
    }

    public LogicaPaginaPrincipal(PaginaPrincipal aThis, JTable jTable1, JLabel jLabel3, JList<String> jList1) {
        // Inicializamos los componentes y la instancia de acceso a datos
        this.jTable1 = jTable1;
        this.jLabel3 = jLabel3;
        this.jList1 = jList1;
        this.da = new DataAccess();

        // Llama al método que inserta los usuarios en la tabla al inicializar la clase
        insertarUsuarisAlJtext();
        this.parentFrame = null;
        this.jTable2 = null;
    }

    public LogicaPaginaPrincipal(UsuarisFrame aThis, JTable jTable1, JTable jTable2, JLabel jLabel3) {
        this.parentFrame = aThis;
        this.jTable1 = jTable1;
        this.jTable2 = jTable2;
        this.jLabel3 = jLabel3;

        insertarUsuarisAlJtext();
    }

    // Método para eliminar el usuario seleccionado
    public void eliminarUsuarioSeleccionado() {
        // Verificar si se ha seleccionado una fila en jTable1
        int selectedRow = jTable1.getSelectedRow();

        if (selectedRow != -1) { // Si una fila está seleccionada
            // Obtener el ID y el nombre del usuario seleccionado
            String usuarioId = jTable1.getValueAt(selectedRow, 0).toString(); // ID del usuario
            String usuarioNombre = jTable1.getValueAt(selectedRow, 1).toString(); // Nombre del usuario

            // Crear el mensaje del modal
            String mensaje = "¿Estás seguro de querer eliminar el usuario con ID " + usuarioId + " y nombre " + usuarioNombre + "?";

            // Mostrar el modal de confirmación
            int respuesta = JOptionPane.showConfirmDialog(parentFrame, mensaje, "Confirmar eliminación", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

            // Verificar la respuesta del usuario
            if (respuesta == JOptionPane.YES_OPTION) {
                // Lógica para eliminar el usuario de la base de datos
                DataAccess da = new DataAccess();
                da.eliminarUsuario(Integer.parseInt(usuarioId)); // Método que elimina el usuario

                // Actualizar la tabla después de eliminar
                insertarUsuarisAlJtext(); // Método para volver a cargar la tabla
                JOptionPane.showMessageDialog(parentFrame, "Usuario eliminado con éxito."); // Mensaje de éxito
            }
        } else {
            JOptionPane.showMessageDialog(parentFrame, "Por favor, selecciona un usuario para eliminar."); // Mensaje si no hay selección
        }
    }

    public final void insertarUsuarisAlJtext() {
        DataAccess da = new DataAccess();
        ArrayList<Usuari> usuaris = da.getUsuaris(); // Obtener la lista de usuarios desde la base de datos

        DefaultTableModel model = (DefaultTableModel) jTable1.getModel(); // Obtener el modelo de la tabla
        model.setRowCount(0); // Limpiar las filas anteriores del JTable

        // Limpiar columnas anteriores si ya estaban creadas
        model.setColumnCount(0);

        // Añadir las columnas que deseas
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
                        System.out.println("Id del usuri selecionat: " + id);

                        // Obtener la lista de descripciones de los ejercicios asociados al usuario
                        ArrayList<String> intentsSinReview = da.getIntentsSinReviewPerId((int) id);
                        System.out.println("Exercicis per revisar " + intentsSinReview.size());

                        // Convertir la ArrayList a un array de String
                        //String[] descripcionesArray = descripciones.toArray(new String[0]);
                        jLabel3.setText("Id del usuari selecionat: " + id.toString());

                        // Actualizar la JList con las descripciones
                        if (intentsSinReview.isEmpty()) {
                            // Crear un array con el mensaje "està buit" y asignarlo a jList1
                            String[] emptyMessage = {"Tot revisat"};
                            jList1.setListData(emptyMessage);
                        } else {
                            // Convertir la lista de intentos a un array de String y asignarlo a jList1
                            jList1.setListData(intentsSinReview.toArray(new String[0]));
                        }

                    }
                }
            }
        });
    }

}
