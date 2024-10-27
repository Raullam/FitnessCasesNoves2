/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package spdvi.fitnesscasesnoves.dataAcces;

import spdvi.fitnesscasesnoves.dto.Usuari;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.sql.Types;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Raül Lama
 */
// DAO Data access object
public class DataAccess {
    private Connection getConnection() {
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
    
    public ArrayList<Usuari> getUsuaris() {
        ArrayList<Usuari> usuaris = new ArrayList<>(); // mos cream s'objecte
        String sql = "select * from usuaris"; // feim sa query
        
        Connection connection = getConnection(); // mos connectam
        try {
            PreparedStatement selectStatement = connection.prepareStatement(sql); // preparam la query y la enviamos al servidor
            ResultSet resultSet = selectStatement.executeQuery();
            while (resultSet.next()) {
        Usuari user = new Usuari(
        resultSet.getInt("Id"),
        resultSet.getString("Nom"),
        resultSet.getString("Email"),
        resultSet.getString("PasswordHash"),
        //resultSet.getBytes("Foto"),
        resultSet.getBoolean("IsInstructor")
        );
    usuaris.add(user);
}
        } catch (SQLException ex) {
            Logger.getLogger(DataAccess.class.getName()).log(Level.SEVERE, null, ex);
        }
        return usuaris; 
    }
    
    // intents amb timestamp_fi no null i sense valoració
public ArrayList<String> getIntentosByUserId(int idUsuario) {
    ArrayList<String> descripciones = new ArrayList<>(); // Lista para almacenar las descripciones de los ejercicios
    String sql = "SELECT e.Descripcio " +
             "FROM Intents i " +
             "JOIN Exercicis e ON i.IdExercici = e.Id " +
             "JOIN Review r ON i.Id = r.IdIntent " +
             "WHERE i.IdUsuari = ? AND r.Valoracio IS NULL AND i.Timestamp_Fi IS NOT NULL";
    Connection connection = getConnection(); // Obtener la conexión a la base de datos
    try {
        PreparedStatement selectStatement = connection.prepareStatement(sql); // Preparar la consulta
        selectStatement.setInt(1, idUsuario); // Establecer el valor del parámetro idUsuario
        ResultSet resultSet = selectStatement.executeQuery(); // Ejecutar la consulta

        // Iterar sobre los resultados y agregar las descripciones de los ejercicios a la lista
        while (resultSet.next()) {
            descripciones.add(resultSet.getString("Descripcio")); // Obtener la descripción del ejercicio
        }
    } catch (SQLException ex) {
        Logger.getLogger(DataAccess.class.getName()).log(Level.SEVERE, null, ex);
    } finally {
        try {
            if (connection != null) connection.close(); // Cerrar la conexión después de usarla
        } catch (SQLException e) {
            Logger.getLogger(DataAccess.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    return descripciones;
}


    public boolean updateUsuaris(Usuari user) {
    String sql = "UPDATE usuaris SET Nom = ?, Email = ?, PasswordHash = ?, Foto = ?, IsInstructor = ? WHERE Id = ?";
    boolean updated = false; // Variable para controlar si se actualizó

    try (Connection connection = getConnection(); 
         PreparedStatement updateStatement = connection.prepareStatement(sql)) {

        // Asignar valores a los parámetros del PreparedStatement
        updateStatement.setString(1, user.getNom());
        updateStatement.setString(2, user.getEmail());
        updateStatement.setString(3, user.getPasswordHash());
        updateStatement.setBytes(4, user.getFoto());
        updateStatement.setBoolean(5, user.isInstructor()); // Usar el nuevo método isInstructor
        updateStatement.setInt(6, user.getId());

        // Ejecutar la actualización
        int rowsAffected = updateStatement.executeUpdate();
        if (rowsAffected > 0) { 
            updated = true; // La actualización fue exitosa
        }
    } catch (SQLException ex) {
        Logger.getLogger(DataAccess.class.getName()).log(Level.SEVERE, null, ex);
    }

    return updated; // Retornar el estado de la actualización
}

    public Usuari getUsuarioByUsername(String username) {
    Usuari user = null; // Inicializa el objeto Usuari a null
    String sql = "SELECT * FROM usuaris WHERE Nom = ?"; // Query para buscar el usuario por nombre

    try (Connection connection = getConnection(); 
         PreparedStatement selectStatement = connection.prepareStatement(sql)) {
        
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
    }
    
    
    
    // Retornar el objeto Usuari si se encontró, o null si no
    return user;
}
    
    public String getNombreById(int id) {
    String nombreUsuario = null; // Inicializa la variable para el nombre de usuario
    String sql = "SELECT Nom FROM usuaris WHERE Id = ?"; // Consulta para obtener el nombre por ID

    try (Connection connection = getConnection();
         PreparedStatement selectStatement = connection.prepareStatement(sql)) {
        
        // Establece el parámetro en la consulta
        selectStatement.setInt(1, id);
        
        // Ejecuta la consulta
        ResultSet resultSet = selectStatement.executeQuery();
        
        // Si existe un resultado, obten el nombre de usuario
        if (resultSet.next()) {
            nombreUsuario = resultSet.getString("Nom");
        }
        
    } catch (SQLException ex) {
        Logger.getLogger(DataAccess.class.getName()).log(Level.SEVERE, null, ex);
    }
    
    return nombreUsuario; // Devuelve el nombre del usuario o null si no existe
}

    
    public int registerUser(Usuari u) {
        Connection connection = getConnection();
        String sql = "INSERT INTO Usuaris(Nom, Email, PasswordHash, IsInstructor) "
                + " VALUES (?, ?, ?, ?);";
        
        try {
            PreparedStatement insertStatement = connection.prepareStatement(sql);
            insertStatement.setString(1,u.getNom());
            insertStatement.setString(2,u.getEmail());
            insertStatement.setString(3,u.getPasswordHash());
            insertStatement.setBoolean(4, u.isInstructor()); 
            insertStatement.executeUpdate();
            connection.close();
            
            int newUserId = getLastId();
            return newUserId;
           
        } catch (SQLException ex) {
            Logger.getLogger(DataAccess.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return 0;
    }
    public int getLastId() {
    int lastId = 0; // Inicializa el ID a 0 (o a otro valor que consideres adecuado)

    // Define la consulta SQL para obtener el máximo ID
    String sql = "SELECT MAX(id) AS maxId FROM Usuaris"; // Ajusta el nombre del campo si es necesario

    // Manejo de la conexión y los recursos
    try (Connection connection = getConnection();
         PreparedStatement preparedStatement = connection.prepareStatement(sql);
         ResultSet resultSet = preparedStatement.executeQuery()) {

        // Verifica si hay un resultado
        if (resultSet.next()) {
            lastId = resultSet.getInt("maxId"); // Obtiene el máximo ID
        }
    } catch (SQLException ex) {
        Logger.getLogger(Usuari.class.getName()).log(Level.SEVERE, null, ex);
    }
    
    return lastId; // Retorna el último ID encontrado
}
public int getLastIdExercicis() {
    int lastId = 0; // Inicializa el ID a 0 (o a otro valor que consideres adecuado)

    // Define la consulta SQL para obtener el máximo ID
    String sql = "SELECT MAX(id) AS maxId FROM Exercicis"; // Ajusta el nombre del campo si es necesario

    // Manejo de la conexión y los recursos
    try (Connection connection = getConnection();
         PreparedStatement preparedStatement = connection.prepareStatement(sql);
         ResultSet resultSet = preparedStatement.executeQuery()) {

        // Verifica si hay un resultado
        if (resultSet.next()) {
            lastId = resultSet.getInt("maxId"); // Obtiene el máximo ID
        }
    } catch (SQLException ex) {
        Logger.getLogger(Usuari.class.getName()).log(Level.SEVERE, null, ex);
    }
    
    return lastId; // Retorna el último ID encontrado
}
// Afegir exercici i intent per poder asignar un exercici a un usuari
public int crearExerciciEnBD(int usuarioId, String nombreExercici, String descripcionExercici) {
    String sql = "INSERT INTO Exercicis (NomExercici, Descripcio) VALUES (?, ?)"; 
    String sqlInsertIntent = "INSERT INTO Intents (idUsuari, IdExercici, Timestamp_Inici, Timestamp_Fi, Videofile) VALUES (?, ?, ?, ?, ?)";
    String sqlInsertReview = "INSERT INTO Review (IdIntent, IdReviewer, Valoracio, Comentari) VALUES (?, ?, ?, ?)";

    try (Connection connection = getConnection();
         PreparedStatement insertStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

        // Insertar el ejercicio
        insertStatement.setString(1, nombreExercici); 
        insertStatement.setString(2, descripcionExercici); 
        int rowsAffected = insertStatement.executeUpdate();

        if (rowsAffected > 0) {
            // Obtener el ID del nuevo ejercicio
            ResultSet generatedKeys = insertStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                int idExercici = generatedKeys.getInt(1); // Retorna el ID del nuevo ejercicio

                // Crear el intento
                try (PreparedStatement insertIntentStatement = connection.prepareStatement(sqlInsertIntent, Statement.RETURN_GENERATED_KEYS)) {
                    insertIntentStatement.setInt(1, usuarioId);
                    insertIntentStatement.setInt(2, idExercici);
                    insertIntentStatement.setTimestamp(3, new Timestamp(System.currentTimeMillis())); // Timestamp de inicio
                    insertIntentStatement.setTimestamp(4, null); // Timestamp de fin, se puede actualizar después
                    insertIntentStatement.setString(5, null); // Videofile, se puede definir después

                    insertIntentStatement.executeUpdate();
                    ResultSet intentKeys = insertIntentStatement.getGeneratedKeys();
                    if (intentKeys.next()) {
                        int idIntent = intentKeys.getInt(1); // Obtener el ID del nuevo intento

                        // Crear la reseña con valoración nula
                        try (PreparedStatement insertReviewStatement = connection.prepareStatement(sqlInsertReview)) {
                            insertReviewStatement.setInt(1, idIntent);
                            insertReviewStatement.setInt(2, usuarioId); // Usar el mismo ID del usuario como revisor
                            insertReviewStatement.setNull(3, Types.INTEGER); // Valoración nula
                            insertReviewStatement.setString(4, "Aún no he realizado el ejercicio, por lo que no puedo proporcionar una valoración.");
                            insertReviewStatement.executeUpdate();
                        }
                    }
                }
                return idExercici; // Retorna el ID del nuevo ejercicio
            }
        }
    } catch (SQLException ex) {
        Logger.getLogger(DataAccess.class.getName()).log(Level.SEVERE, null, ex);
    }
    return -1; // Retorna -1 si hubo un error
}


    
}

// hacer un listener de la celula y añadirle el evento
