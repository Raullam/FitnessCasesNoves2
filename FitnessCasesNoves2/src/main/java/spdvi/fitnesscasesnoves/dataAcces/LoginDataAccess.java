/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package spdvi.fitnesscasesnoves.dataAcces;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import spdvi.fitnesscasesnoves.dto.Usuari;

/**
 *
 * @author Raül Lama
 */
public class LoginDataAccess {

    public Connection getConnection() {
        Connection connection = null;
        String connectionString = "jdbc:sqlserver://localhost;database=simulapdb;trustServerCertificate=true;user=sa;password=1234;";

        //despues de hacer focus que se haga el update
        try {
            connection = DriverManager.getConnection(connectionString);
        } catch (SQLException ex) {
            Logger.getLogger(DataAccess.class.getName()).log(Level.SEVERE, null, ex);
        }
        return connection;
    }

    public Usuari getUsuarioByUsername(String username) {
        Usuari user = null;
        String sql = "SELECT * FROM usuaris WHERE Nom = ?"; // Query para buscar el usuario por nombre

        try (Connection connection = getConnection(); PreparedStatement selectStatement = connection.prepareStatement(sql)) {

            // Establecer el parámetro en la consulta
            selectStatement.setString(1, username);

            // Ejecutar la consulta
            ResultSet resultSet = selectStatement.executeQuery();

            // Si el usuario existe, obtener los datos y crear un objeto Usuari
            if (resultSet.next()) {
                user = new Usuari(
                        resultSet.getInt("Id"),
                        resultSet.getString("Nom"),
                        resultSet.getString("Email"),
                        resultSet.getString("PasswordHash"),
                        //resultSet.getBytes("Foto"),
                        resultSet.getBoolean("IsInstructor")
                );
            }

        } catch (SQLException ex) {
            Logger.getLogger(DataAccess.class.getName()).log(Level.SEVERE, null, ex);
            System.out.print("Alguna cosa no ha anat be a LoginDataAcces");
        }

        // Retornar el objeto Usuari si se encontró, o null si no
        return user;
    }

}
