/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package spdvi.fitnesscasesnoves.logica;

import at.favre.lib.crypto.bcrypt.BCrypt;
import javax.swing.JOptionPane;
import spdvi.fitnesscasesnoves.dataAcces.DataAccess;
import spdvi.fitnesscasesnoves.dataAcces.LoginDataAccess;
import spdvi.fitnesscasesnoves.dto.Usuari;

/**
 *
 * @author Rulox
 */
public class logicaLogin {

    public static int instructorId = -1; // Variable estática para almacenar el ID del usuario logueado

    public static boolean validarUsuario(String username, String password) {
        DataAccess daa = new DataAccess();
        LoginDataAccess da = new LoginDataAccess();
// Verifica si el campo username está vacío
        if (username == null || username.isEmpty()) {
            JOptionPane.showMessageDialog(null, "El campo de usuario no puede estar vacío.");
            return false;
        }

        Usuari user = da.getUsuarioByUsername(username); // Obtener el usuario desde la BD
        if (user != null) {
            // Verificar la contraseña
            if (BCrypt.verifyer().verify(password.toCharArray(), user.getPasswordHash()).verified) {
                // Validar si el usuario es instructor
                if (user.isInstructor()) {
                    int instructorId = daa.obtenerIdUsuario(username, password);
                    System.out.println(instructorId);
                    return true; // Usuario válido y es instructor
                } else {
                    JOptionPane.showMessageDialog(null, "No tiene acceso por no ser instructor.");
                    return false;
                }
            } else {
                JOptionPane.showMessageDialog(null, "Contraseña incorrecta.");
                return false;
            }
        } else {
            JOptionPane.showMessageDialog(null, "El usuario no existe.");
            return false;
        }
    }
}
