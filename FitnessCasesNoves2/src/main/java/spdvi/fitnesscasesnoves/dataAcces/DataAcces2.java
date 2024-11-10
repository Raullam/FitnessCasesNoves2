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
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import spdvi.fitnesscasesnoves.dto.Intents;
import spdvi.fitnesscasesnoves.dto.Usuari;

/**
 *
 * @author Rulox
 */
public class DataAcces2 implements AutoCloseable {

    public static int idInstructor = -1;
    private Properties config;
    private Connection connection;

    // Definir las constantes para las consultas SQL
    private static final String SQL_SELECT_INTENTS_SIN_REVIEW = "SELECT Intents.Id AS IdIntent, Exercicis.NomExercici "
            + "FROM Intents "
            + "JOIN Exercicis ON Intents.IdExercici = Exercicis.Id "
            + "LEFT JOIN Review ON Intents.Id = Review.IdIntent "
            + "WHERE Review.Id IS NULL";
    private static final String SQL_SELECT_USUARIOS = "SELECT * FROM Usuaris";
    private static final String SQL_SELECT_INTENTS = "SELECT * FROM Intents";
    private static final String SQL_SELECT_INTENTS_POR_USUARIO = "SELECT Id, IdExercici, Timestamp_Inici, Timestamp_Fi, Videofile FROM Intents WHERE IdUsuari = ?";
    private static final String SQL_SELECT_INTENTS_SENSE_REVIEW_PER_ID = "SELECT Intents.Id AS IdIntent, Exercicis.NomExercici " +
                     "FROM Intents " +
                     "JOIN Exercicis ON Intents.IdExercici = Exercicis.Id " +
                     "LEFT JOIN Review ON Intents.Id = Review.IdIntent " +
                     "WHERE Review.Id IS NULL AND Intents.IdUsuari = ?";
    private static final String SQL_INSERT_USUARIO = "INSERT INTO Usuaris(Nom, Email, PasswordHash, IsInstructor) VALUES (?, ?, ?, ?)";
    private static final String SQL_SELECT_USUARIO_POR_ID = "SELECT * FROM Usuaris WHERE Id = ?";

    // Constructor con conexión inicializada
    public DataAcces2() {
        config = new CarregaConfiguracio().carregarConfig("database.conf");
        this.connection = createConnection();
    }

    // Método para crear la conexión inicial
    private Connection createConnection() {
        String host = config.getProperty("host", "localhost");
        String nomDatabase = config.getProperty("database_name", "simulapdb");
        String user = config.getProperty("user", "sa");
        String password = config.getProperty("password", "1234");

        String connectionString = String.format(
                "jdbc:sqlserver://%s;database=%s;trustServerCertificate=true;user=%s;password=%s;",
                host, nomDatabase, user, password
        );

        try {
            return DriverManager.getConnection(connectionString);
        } catch (SQLException ex) {
            Logger.getLogger(DataAccess.class.getName()).log(Level.SEVERE, "La conexión ha fallado", ex);
            return null;
        }
    }

    // Método para obtener la conexión, la crea si está cerrada
    public Connection getConnection() {
        try {
            if (connection == null || connection.isClosed()) {
                connection = createConnection();
            }
        } catch (SQLException e) {
            Logger.getLogger(DataAccess.class.getName()).log(Level.SEVERE, "No se pudo comprobar la conexión", e);
        }
        return connection;
    }

    // Método para obtener los intentos sin review
    public List<String> getIntentsSinReview() {
        List<String> resultList = new ArrayList<>();

        // Usamos la constante SQL para la consulta
        try (PreparedStatement stmt = getConnection().prepareStatement(SQL_SELECT_INTENTS_SIN_REVIEW); ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                resultList.add(String.format("ID: %-5d %s", rs.getInt("IdIntent"), rs.getString("NomExercici")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(DataAccess.class.getName()).log(Level.SEVERE, "Error al obtener los intentos sin review", ex);
        }

        return resultList;
    }

    // Método para obtener todos los usuarios
    public ArrayList<Usuari> getUsuaris() {
        ArrayList<Usuari> usuaris = new ArrayList<>();
        try (PreparedStatement stmt = getConnection().prepareStatement(SQL_SELECT_USUARIOS); ResultSet resultSet = stmt.executeQuery()) {

            while (resultSet.next()) {
                Usuari user = new Usuari(
                        resultSet.getInt("Id"),
                        resultSet.getString("Nom"),
                        resultSet.getString("Email"),
                        resultSet.getString("PasswordHash"),
                        resultSet.getBoolean("IsInstructor")
                );
                usuaris.add(user);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DataAcces2.class.getName()).log(Level.SEVERE, "Error al obtener usuarios", ex);
        }
        return usuaris;
    }

    // Método para obtener todos los intentos
    public ArrayList<Intents> getIntents() {
        ArrayList<Intents> intents = new ArrayList<>();
        try (PreparedStatement stmt = getConnection().prepareStatement(SQL_SELECT_INTENTS); ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Intents intent = new Intents(
                        rs.getInt("Id"),
                        rs.getInt("IdUsuari"),
                        rs.getInt("IdExercici"),
                        rs.getString("Timestamp_Inici"),
                        rs.getString("Timestamp_Fi"),
                        rs.getString("Videofile")
                );
                intents.add(intent);
            }
        } catch (SQLException e) {
            Logger.getLogger(DataAcces2.class.getName()).log(Level.SEVERE, "Error al obtener intentos", e);
        }
        return intents;
    }

    // Método para obtener intentos por ID de usuario
    public ArrayList<Intents> getIntentsPerId(int userId) {
        ArrayList<Intents> intents = new ArrayList<>();
        try (PreparedStatement pstmt = getConnection().prepareStatement(SQL_SELECT_INTENTS_POR_USUARIO)) {
            pstmt.setInt(1, userId);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    Intents intent = new Intents();
                    intent.setId(rs.getInt("Id"));
                    intent.setIdExercici(rs.getInt("IdExercici"));
                    intent.setTimestamp_Inici(rs.getString("Timestamp_Inici"));
                    intent.setTimestamp_Fi(rs.getString("Timestamp_Fi"));
                    intent.setVideofile(rs.getString("Videofile"));
                    intents.add(intent);
                }
            }
        } catch (SQLException e) {
            Logger.getLogger(DataAcces2.class.getName()).log(Level.SEVERE, "Error al obtener intentos por ID de usuario", e);
        }
        return intents;
    }

     // Obtener Intentos sin Review por Id de Usuario
    public ArrayList<String> getIntentsSinReviewPerId(int idUsuario) {
        ArrayList<String> intentosSinReview = new ArrayList<>();

        try (PreparedStatement stmt = connection.prepareStatement(SQL_SELECT_INTENTS_SENSE_REVIEW_PER_ID)) {
            stmt.setInt(1, idUsuario);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    intentosSinReview.add(String.format("ID: %-5d %s", rs.getInt("IdIntent"), rs.getString("NomExercici")));
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(DataAccess.class.getName()).log(Level.SEVERE, null, ex);
        }
        return intentosSinReview;
    }

    // Método para registrar un usuario, devuelve el ID generado
    public int registerUser(Usuari u) {
        try (PreparedStatement stmt = getConnection().prepareStatement(SQL_INSERT_USUARIO, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, u.getNom());
            stmt.setString(2, u.getEmail());
            stmt.setString(3, u.getPasswordHash());
            stmt.setBoolean(4, u.isInstructor());

            stmt.executeUpdate();
            ResultSet rs = stmt.getGeneratedKeys();

            return rs.next() ? rs.getInt(1) : 0; // Retorna el ID generado o 0

        } catch (SQLException ex) {
            Logger.getLogger(DataAccess.class.getName()).log(Level.SEVERE, "Error al registrar usuario", ex);
            return 0;
        }
    }

    // Método para obtener usuario por ID
    public Usuari getUsuarioPorId(int usuarioId) {
        try (PreparedStatement stmt = getConnection().prepareStatement(SQL_SELECT_USUARIO_POR_ID)) {

            stmt.setInt(1, usuarioId);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new Usuari(rs.getInt("Id"), rs.getString("Nom"), rs.getString("Email"),
                        rs.getString("PasswordHash"), rs.getBoolean("IsInstructor"));
            }

        } catch (SQLException ex) {
            Logger.getLogger(DataAccess.class.getName()).log(Level.SEVERE, "Error al obtener usuario por ID", ex);
        }
        return null;
    }

    // Cierre de la conexión
    @Override
    public void close() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        } catch (SQLException ex) {
            Logger.getLogger(DataAccess.class.getName()).log(Level.SEVERE, "Error al cerrar la conexión", ex);
        }
    }

}
