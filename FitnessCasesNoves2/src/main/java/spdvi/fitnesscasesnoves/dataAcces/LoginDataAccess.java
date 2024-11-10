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
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import spdvi.fitnesscasesnoves.dto.Usuari;

public class LoginDataAccess {
    private Properties config;

    // Constructor que carrega les configuracions al crear la classe
    public LoginDataAccess() {
        config = new CarregaConfiguracio().carregarConfig("database.conf");
    }

    // Mètode per obtenir la connexió usant les dades del fitxer de configuració
    public Connection getConnection() {
        Connection connection = null;

        // Llegir les propietats des de config
        String host = config.getProperty("host", "localhost");
        String databaseName = config.getProperty("database_name", "simulapdb");
        String user = config.getProperty("user", "sa");
        String password = config.getProperty("password", "1234");

        // Crear el string de connexió amb els valors del fitxer .conf
        String connectionString = String.format(
            "jdbc:sqlserver://%s;database=%s;trustServerCertificate=true;user=%s;password=%s;",
            host, databaseName, user, password
        );

        try {
            connection = DriverManager.getConnection(connectionString);
        } catch (SQLException ex) {
            Logger.getLogger(LoginDataAccess.class.getName()).log(Level.SEVERE, "La connexió ha fallat", ex);
        }
        return connection;
    }

    // Mètode per obtenir un usuari pel seu nom d'usuari
    public Usuari getUsuarioByUsername(String username) {
        Usuari user = null;
        String sql = "SELECT * FROM usuaris WHERE Nom = ?";

        try (Connection connection = getConnection(); PreparedStatement selectStatement = connection.prepareStatement(sql)) {
            selectStatement.setString(1, username);
            ResultSet resultSet = selectStatement.executeQuery();

            if (resultSet.next()) {
                user = new Usuari(
                        resultSet.getInt("Id"),
                        resultSet.getString("Nom"),
                        resultSet.getString("Email"),
                        resultSet.getString("PasswordHash"),
                        resultSet.getBoolean("IsInstructor")
                );
            }
        } catch (SQLException ex) {
            Logger.getLogger(LoginDataAccess.class.getName()).log(Level.SEVERE, "Execució de la consulta fallida", ex);
        }
        return user;
    }
}
