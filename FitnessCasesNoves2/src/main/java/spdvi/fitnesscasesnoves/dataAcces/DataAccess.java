/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package spdvi.fitnesscasesnoves.dataAcces;

import at.favre.lib.crypto.bcrypt.BCrypt;
import spdvi.fitnesscasesnoves.dto.Usuari;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import spdvi.fitnesscasesnoves.dto.Exercici;
import spdvi.fitnesscasesnoves.dto.Intents;
import spdvi.fitnesscasesnoves.dto.Review;

/**
 *
 * @author Raül Lama
 */
// DAO Data access object
public class DataAccess {

    public static int idInstructor = -1; // Valor por defecto si el usuario no se encuentra
    private Properties config;

    //SELECTS
    private static String SELECT_USUARIS = "SELECT * from usuaris";
    private static String SELECT_INTENTS = "SELECT * from Intents";
    private static String SELECT_REVIEWS = "SELECT * from Review";
    private static String SELECT_EXERCICIS = "SELECT * from Exercicis";
    private static String SELECT_INTENTS_PER_ID = "SELECT Id, IdExercici, Timestamp_Inici, Timestamp_Fi, Videofile FROM Intents WHERE IdUsuari = ?";

    private static String SELECT_NOM_PER_ID = "SELECT Nom FROM usuaris WHERE Id = ?";
    private static String SELECT_MAX_ID_EXERCICIS = "SELECT MAX(id) AS maxId FROM Exercicis";
    private static String SELECT_MAX_ID_USUARIS = "SELECT MAX(id) AS maxId FROM Usuaris";
    private static String SELECT_VIDEOFILE_PER_ID_INTENT = "SELECT Videofile FROM Intents WHERE Id = ?";
    private static String SELECT_VALORACIO_PER_ID_INTENT = "SELECT valoracio FROM Review WHERE IdIntent = ?";
    private static String SELECT_INTENT_I_VALORACIO = "SELECT IdIntent, valoracio FROM Review";
    private static String SELECT_idUSUARI_PASSWORD = "SELECT Id, PasswordHash FROM Usuaris WHERE nom = ?";
    private static String SELECT_INTENTS_SENSE_REVIEW = "SELECT Intents.Id AS IdIntent, Exercicis.NomExercici "
            + "FROM Intents "
            + "JOIN Exercicis ON Intents.IdExercici = Exercicis.Id "
            + "LEFT JOIN Review ON Intents.Id = Review.IdIntent "
            + "WHERE Review.Id IS NULL";
    private static String SELECT_INTENTS_SENSE_REVIEW_PER_ID_ORDER_BY_TIMESTAMP_INICI = "SELECT Intents.Id AS IdIntent, Exercicis.NomExercici "
            + "FROM Intents "
            + "JOIN Exercicis ON Intents.IdExercici = Exercicis.Id "
            + "LEFT JOIN Review ON Intents.Id = Review.IdIntent "
            + "WHERE Review.Id IS NULL AND Intents.IdUsuari = ?"
            +"ORDER BY Intents.Timestamp_Inici";
    private static String SELECT_DESCRIPCIO_INTENT_SENSE_REVIEW_PER_ID = "SELECT e.Descripcio "
            + "FROM Intents i "
            + "JOIN Exercicis e ON i.IdExercici = e.Id "
            + "JOIN Review r ON i.Id = r.IdIntent "
            + "WHERE i.IdUsuari = ? AND r.Valoracio IS NULL AND i.Timestamp_Fi IS NOT NULL";
    private static String SELECT_DESCRIPCIO_INTENT_SENSE_REVIEW = "SELECT e.Descripcio "
            + "FROM Intents i "
            + "JOIN Exercicis e ON i.IdExercici = e.Id "
            + "JOIN Review r ON i.Id = r.IdIntent "
            + "WHERE r.Valoracio IS NULL AND i.Timestamp_Fi IS NOT NULL";

    // INSERTS
    private static String INSERT_USUARI = "INSERT INTO Usuaris(Nom, Email, PasswordHash, IsInstructor) "
            + " VALUES (?, ?, ?, ?);";
    private static String INSERT_REVIEW = "INSERT INTO Review (IdIntent, IdReviewer, Valoracio, Comentari) VALUES (?, ?, ?, ?)";
    private static String INSERT_EXERCICI = "INSERT INTO Exercicis (NomExercici, Descripcio) VALUES (?, ?)";
    private static String INSERT_INTENT = "INSERT INTO Intents (idUsuari, IdExercici, Timestamp_Inici, Timestamp_Fi, Videofile) VALUES (?, ?, ?, ?, ?)";

    //DELETES
    private static String DELETE_REVIEW = "DELETE FROM Review WHERE id = ?";
    private static String DELETE_INTENT_IDUSUARI = "DELETE FROM Intents WHERE IdUsuari = ?";
    private static String DELETE_INTENT_IDINTENT = "DELETE FROM Intents WHERE id = ?";
    private static String DELETE_USUARI = "DELETE FROM Usuaris WHERE id = ?";
    private static String DELETE_EXERCICI_ID = "DELETE FROM Exercicis WHERE id = ?";

    //UPDATES
    // Constructor que carrega les configuracions al crear la classe
    public DataAccess() {
        config = new CarregaConfiguracio().carregarConfig("database.conf");
    }

    // Mètode per obtenir la connexió usant les dades del fitxer de configuració
    public Connection getConnection() {
        Connection connection = null;

        // Llegir les propietats des de config
        String host = config.getProperty("host", "localhost");
        String nomDatabase = config.getProperty("database_name", "simulapdb");
        String user = config.getProperty("user", "sa");
        String password = config.getProperty("password", "1234");

        // Crear el string de connexió amb els valors del fitxer .conf
        String connectionString = String.format("jdbc:sqlserver://%s;database=%s;trustServerCertificate=true;user=%s;password=%s;",
                host, nomDatabase, user, password
        );

        try {
            connection = DriverManager.getConnection(connectionString);
        } catch (SQLException ex) {
            Logger.getLogger(LoginDataAccess.class.getName()).log(Level.SEVERE, "La connexió ha fallat", ex);
        }
        return connection;
    }

    //LLISTA D'INTENTS SENSE REVIEW PER CARREGAR AL JLIST DEL JFRAME PAGINA PRINCIPAL
    public List<String> getIntentsSinReview() throws SQLException {
        List<String> resultList = new ArrayList<>();

        try (PreparedStatement stmt = getConnection().prepareStatement(SELECT_INTENTS_SENSE_REVIEW); ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                int idIntent = rs.getInt("IdIntent");
                String nomExercici = rs.getString("NomExercici");
                if (idIntent <= 9) {
                    resultList.add("ID: " + idIntent + "         " + nomExercici);
                }
                if (idIntent > 9 && idIntent <= 99) {
                    resultList.add("ID: " + idIntent + "       " + nomExercici);
                }
                if (idIntent > 99 && idIntent <= 999) {
                    resultList.add("ID: " + idIntent + "      " + nomExercici);
                }
                if (idIntent > 999 && idIntent <= 9999) {
                    resultList.add("ID: " + idIntent + "      " + nomExercici);
                }
                if (idIntent > 9999 && idIntent <= 99999) {
                    resultList.add("ID: " + idIntent + "     " + nomExercici);
                }
            }
        }

        return resultList;
    }

    public ArrayList<Usuari> getUsuaris() {
        ArrayList<Usuari> usuaris = new ArrayList<>(); // mos cream s'objecte

        try {
            PreparedStatement selectStatement = getConnection().prepareStatement(SELECT_USUARIS); // preparam Sa query
            ResultSet resultSet = selectStatement.executeQuery();
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
            Logger.getLogger(DataAccess.class.getName()).log(Level.SEVERE, null, ex);
        }
        return usuaris;
    }

    public ArrayList<Intents> getIntents() {
        ArrayList<Intents> intents = new ArrayList<>();

        try (PreparedStatement stmt = getConnection().prepareStatement(SELECT_INTENTS); ResultSet rs = stmt.executeQuery();) {

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
            rs.close();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return intents;
    }

    public ArrayList<Intents> getIntentsPerId(int userId) {
        ArrayList<Intents> intents = new ArrayList<>();

        try (PreparedStatement pstmt = getConnection().prepareStatement(SELECT_INTENTS_PER_ID)) {
            pstmt.setInt(1, userId);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                Intents intent = new Intents();
                intent.setId(rs.getInt("Id"));
                intent.setIdExercici(rs.getInt("IdExercici"));
                intent.setTimestamp_Inici(rs.getString("Timestamp_Inici"));
                intent.setTimestamp_Fi(rs.getString("Timestamp_Fi"));
                intent.setVideofile(rs.getString("Videofile"));
                intents.add(intent);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return intents;
    }

    public ArrayList<String> getIntentsSinReviewPerId(int idUsuario) {
        ArrayList<String> intentosSinReview = new ArrayList<>(); // Lista para almacenar resultados

        try (PreparedStatement selectStatement = getConnection().prepareStatement(SELECT_INTENTS_SENSE_REVIEW_PER_ID_ORDER_BY_TIMESTAMP_INICI)) {
            // Establecer el valor del parámetro idUsuario
            selectStatement.setInt(1, idUsuario);

            // Ejecutar la consulta
            ResultSet resultSet = selectStatement.executeQuery();

            // Iterar sobre los resultados y agregar a la lista
            while (resultSet.next()) {
                int idIntent = resultSet.getInt("IdIntent");
                String nomExercici = resultSet.getString("NomExercici");
                if (idIntent <= 9) {
                    intentosSinReview.add("ID: " + idIntent + "         " + nomExercici);
                }
                if (idIntent > 9 && idIntent <=99) {
                    intentosSinReview.add("ID: " + idIntent + "       " + nomExercici);
                }
                if (idIntent > 99&& idIntent <=999) {
                    intentosSinReview.add("ID: " + idIntent + "      " + nomExercici);
                }
                if (idIntent > 999&& idIntent <=9999) {
                    intentosSinReview.add("ID: " + idIntent + "      " + nomExercici);
                }
                if (idIntent > 9999&& idIntent <=99999) {
                    intentosSinReview.add("ID: " + idIntent + "     " + nomExercici);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(DataAccess.class.getName()).log(Level.SEVERE, null, ex);
        }

        return intentosSinReview;
    }

    // intents amb timestamp_fi no null i sense valoració
    public ArrayList<String> getIntentosByUserId(int idUsuario) {
        ArrayList<String> descripciones = new ArrayList<>(); // Lista para almacenar las descripciones de los ejercicios
        try {
            PreparedStatement selectStatement = getConnection().prepareStatement(SELECT_DESCRIPCIO_INTENT_SENSE_REVIEW_PER_ID); // Preparar la consulta
            selectStatement.setInt(1, idUsuario); // Establecer el valor del parámetro idUsuario
            ResultSet resultSet = selectStatement.executeQuery(); // Ejecutar la consulta

            // Iterar sobre los resultados y agregar las descripciones de los ejercicios a la lista
            while (resultSet.next()) {
                descripciones.add(resultSet.getString("Descripcio")); // Obtener la descripción del ejercicio
            }
        } catch (SQLException ex) {
            Logger.getLogger(DataAccess.class.getName()).log(Level.SEVERE, null, ex);

        }

        return descripciones;
    }

    public ArrayList<String> getIntentosSinRevision() {
        ArrayList<String> descripciones = new ArrayList<>(); // Lista para almacenar las descripciones de los ejercicios
        try {
            PreparedStatement selectStatement = getConnection().prepareStatement(SELECT_DESCRIPCIO_INTENT_SENSE_REVIEW); // Preparar la consulta
            ResultSet resultSet = selectStatement.executeQuery(); // Ejecutar la consulta

            // Iterar sobre los resultados y agregar las descripciones de los ejercicios a la lista
            while (resultSet.next()) {
                descripciones.add(resultSet.getString("Descripcio")); // Obtener la descripción del ejercicio
            }
        } catch (SQLException ex) {
            Logger.getLogger(DataAccess.class.getName()).log(Level.SEVERE, null, ex);
        }
        return descripciones;
    }

    public String getNombreById(int id) {
        String nombreUsuario = null; // Inicializa la variable para el nombre de usuario

        try (PreparedStatement selectStatement = getConnection().prepareStatement(SELECT_NOM_PER_ID)) {

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

    public int getLastIdExercicis() {
        int lastId = 0; // Inicializa el ID a 0 (o a otro valor que consideres adecuado)

        // Manejo de la conexión y los recursos
        try (PreparedStatement preparedStatement = getConnection().prepareStatement(SELECT_MAX_ID_EXERCICIS); ResultSet resultSet = preparedStatement.executeQuery()) {

            // Verifica si hay un resultado
            if (resultSet.next()) {
                lastId = resultSet.getInt("maxId"); // Obtiene el máximo ID
            }
        } catch (SQLException ex) {
            Logger.getLogger(Usuari.class.getName()).log(Level.SEVERE, null, ex);
        }

        return lastId; // Retorna el último ID encontrado
    }

    public int getLastId() {
        int lastId = 0; // Inicializa el ID a 0 (o a otro valor que consideres adecuado)

        // Manejo de la conexión y los recursos
        try (PreparedStatement preparedStatement = getConnection().prepareStatement(SELECT_MAX_ID_USUARIS); ResultSet resultSet = preparedStatement.executeQuery()) {

            // Verifica si hay un resultado
            if (resultSet.next()) {
                lastId = resultSet.getInt("maxId"); // Obtiene el máximo ID
            }
        } catch (SQLException ex) {
            Logger.getLogger(Usuari.class.getName()).log(Level.SEVERE, null, ex);
        }

        return lastId; // Retorna el último ID encontrado
    }

    // Método para obtener todas las reseñas desde la base de datos
    public ArrayList<Review> getReviews() {
        ArrayList<Review> reviews = new ArrayList<>();
        //String query = "SELECT [Id], [IdIntent], [IdReviewer], [Valoracio], [Comentari] FROM [simulapdb].[dbo].[Review]";

        try (Statement statement = getConnection().createStatement(); ResultSet resultSet = statement.executeQuery(SELECT_REVIEWS)) {

            while (resultSet.next()) {
                // Crear un objeto Review a partir de los datos de la fila
                int id = resultSet.getInt("Id");
                int idIntent = resultSet.getInt("IdIntent");
                int idReviewer = resultSet.getInt("IdReviewer");
                int valoracion = resultSet.getInt("Valoracio");
                String comentario = resultSet.getString("Comentari");

                Review review = new Review(id, idIntent, idReviewer, valoracion, comentario);
                reviews.add(review); // Añadir la reseña a la lista
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return reviews; // Devolver la lista de reseñas
    }

    // Método para obtener la lista de ejercicios
    public ArrayList<Exercici> getExercicis() {
        ArrayList<Exercici> exercicis = new ArrayList<>();

        //String query = "SELECT Id, NomExercici AS Nom, Descripcio FROM Exercicis";
        try (Statement stmt = getConnection().createStatement(); ResultSet rs = stmt.executeQuery(SELECT_EXERCICIS)) {

            while (rs.next()) {
                // Crear un objeto Exercici con solo Id, Nombre y Descripción
                Exercici exercici = new Exercici(
                        rs.getInt("Id"), // Id del ejercicio
                        rs.getString("NomExercici"), // Nombre del ejercicio
                        rs.getString("Descripcio") // Descripción del ejercicio
                );

                // Agregar el objeto Exercici a la lista
                exercicis.add(exercici);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return exercicis;
    }

    public String getVideoFile(int intentId) {
        String videoFilePath = null;

        try (PreparedStatement stmt = getConnection().prepareStatement(SELECT_VIDEOFILE_PER_ID_INTENT)) {

            stmt.setInt(1, intentId);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                videoFilePath = rs.getString("Videofile");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return videoFilePath;
    }

    // Método para verificar si un intento tiene una valoración
    public Integer mirarSiTeReview(int intentId) {
        Integer review = null;  

        try (PreparedStatement stmt = getConnection().prepareStatement(SELECT_VALORACIO_PER_ID_INTENT)) {

            stmt.setInt(1, intentId);  

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    review = rs.getInt("Valoracio"); 
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return review;  
    }

    public Map<Integer, Integer> obtenerTodasLasValoraciones() {
        Map<Integer, Integer> valoraciones = new HashMap<>();

        try (PreparedStatement stmt = getConnection().prepareStatement(SELECT_INTENT_I_VALORACIO); ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                int idIntento = rs.getInt("IdIntent"); 
                int valoracion = rs.getInt("valoracio"); 
                valoraciones.put(idIntento, valoracion);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return valoraciones;
    }

    //CANVIAR QUERY
    // Método para obtener el ID del usuario basado en su nombre de usuario y contraseña
    public int obtenerIdUsuario(String username, String password) {

        try (PreparedStatement stmt = getConnection().prepareStatement(SELECT_idUSUARI_PASSWORD)) {
            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                // Obtenemos el hash de la base de datos
                String storedHash = rs.getString("PasswordHash");
                // Verificamos la contraseña proporcionada
                if (BCrypt.verifyer().verify(password.toCharArray(), storedHash.toCharArray()).verified) {
                    idInstructor = rs.getInt("Id"); // Obtiene el ID del usuario si la contraseña es válida
                } else {
                    System.out.println("Contrassenya incorrecta.");
                }
            } else {
                System.out.println("No s'ha trobat l'usuari.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return idInstructor; // Retorna el ID del usuario o -1 si no se encontró
    }

    public int registerUser(Usuari u) {

        try {
            PreparedStatement insertStatement = getConnection().prepareStatement(INSERT_USUARI);
            insertStatement.setString(1, u.getNom());
            insertStatement.setString(2, u.getEmail());
            insertStatement.setString(3, u.getPasswordHash());
            insertStatement.setBoolean(4, u.isInstructor());
            insertStatement.executeUpdate();

            int newUserId = getLastId();
            return newUserId;

        } catch (SQLException ex) {
            Logger.getLogger(DataAccess.class.getName()).log(Level.SEVERE, null, ex);
        }

        return 0;
    }

    public boolean createReview(int idIntent, int idReviewer, int valoracio, String comentari) {

        try (PreparedStatement stmt = getConnection().prepareStatement(INSERT_REVIEW)) {

            stmt.setInt(1, idIntent);
            stmt.setInt(2, idReviewer);
            stmt.setInt(3, valoracio);
            stmt.setString(4, comentari);

            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0; // Devuelve true si se insertó correctamente

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

// Afegir exercici i intent per poder asignar un exercici a un usuari EXEMPLE EL CORRECTE ES EL DE ABAIX DE AQUEST
    public int crearExerciciEnBD(int usuarioId, String nombreExercici, String descripcionExercici) {
        //String sqlInsertReview = "INSERT INTO Review (IdIntent, IdReviewer, Valoracio, Comentari) VALUES (?, ?, ?, ?)";

        try (PreparedStatement insertStatement = getConnection().prepareStatement(INSERT_EXERCICI, Statement.RETURN_GENERATED_KEYS)) {

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
                    try (PreparedStatement insertIntentStatement = getConnection().prepareStatement(INSERT_INTENT, Statement.RETURN_GENERATED_KEYS)) {
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
                            /* try (PreparedStatement insertReviewStatement = connection.prepareStatement(sqlInsertReview)) {
                            insertReviewStatement.setInt(1, idIntent);
                            insertReviewStatement.setInt(2, usuarioId); // Usar el mismo ID del usuario como revisor
                            insertReviewStatement.setNull(3, Types.INTEGER); // Valoración nula
                            insertReviewStatement.setString(4, "Aún no he realizado el ejercicio, por lo que no puedo proporcionar una valoración.");
                            insertReviewStatement.executeUpdate();
                        }*/
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
    
    public int crearExerciciEnBD(String nombreExercici, String descripcionExercici) {
    // Definir la sentencia SQL para insertar el ejercicio
    String sqlInsertExercici = "INSERT INTO Exercicis (NomExercici, Descripcio) VALUES (?, ?)";
    
    try (PreparedStatement insertStatement = getConnection().prepareStatement(sqlInsertExercici, Statement.RETURN_GENERATED_KEYS)) {
        
        // Configurar los parámetros de la consulta
        insertStatement.setString(1, nombreExercici);
        insertStatement.setString(2, descripcionExercici);
        
        // Ejecutar la consulta de inserción
        int rowsAffected = insertStatement.executeUpdate();

        // Si se insertó correctamente, obtener el ID generado
        if (rowsAffected > 0) {
            ResultSet generatedKeys = insertStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                return generatedKeys.getInt(1); // Retorna el ID del nuevo ejercicio
            }
        }
        
    } catch (SQLException ex) {
        Logger.getLogger(DataAccess.class.getName()).log(Level.SEVERE, null, ex);
    }
    
    return -1; // Retorna -1 si hubo un error
}


    public void eliminarUsuario(int usuarioId) {

        try (Connection conn = getConnection()) {
            // Eliminar reseñas
            try (PreparedStatement pstmt = conn.prepareStatement(DELETE_REVIEW)) {
                pstmt.setInt(1, usuarioId);
                pstmt.executeUpdate();
            }

            // Eliminar intentos
            try (PreparedStatement pstmt = conn.prepareStatement(DELETE_INTENT_IDUSUARI)) {
                pstmt.setInt(1, usuarioId);
                pstmt.executeUpdate();
            }

            // Finalmente, eliminar el usuario
            try (PreparedStatement pstmt = conn.prepareStatement(DELETE_USUARI)) {
                pstmt.setInt(1, usuarioId);
                pstmt.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Manejo de excepciones
        }
    }
// Método para eliminar un intent por su ID

    public void eliminarIntent(int intentId) {

        try (PreparedStatement stmt = getConnection().prepareStatement(DELETE_INTENT_IDINTENT)) {
            stmt.setInt(1, intentId); // Asigna el ID del intent al parámetro de la consulta

            int rowsAffected = stmt.executeUpdate(); // Ejecuta la consulta
            if (rowsAffected > 0) {
                System.out.println("Intent eliminado correctamente.");
            } else {
                System.out.println("No se encontró ningún intent con el ID proporcionado.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error al eliminar el intent: " + e.getMessage());
        }
    }

    public void eliminarReviewsPorIntentId(int idIntent) throws SQLException {
        String sql = "DELETE FROM Review WHERE IdIntent = ?";
        try (PreparedStatement pstmt = getConnection().prepareStatement(sql)) {
            pstmt.setInt(1, idIntent);
            pstmt.executeUpdate();
        }
    }

    // Método para eliminar una review por su ID
    public void eliminarReviewPorId(int idReview) {
        String sql = "DELETE FROM Review WHERE id = ?"; // Asume que 'reviews' es la tabla donde almacenas las reviews y 'id' es la columna con el ID de la review

        try (PreparedStatement stmt = getConnection().prepareStatement(sql)) {

            // Establecer el valor del parámetro en la consulta
            stmt.setInt(1, idReview);

            // Ejecutar la consulta de eliminación
            int filasAfectadas = stmt.executeUpdate();

            // Verificar si se eliminó alguna fila
            if (filasAfectadas > 0) {
                System.out.println("Review eliminada con éxito.");
            } else {
                System.out.println("No se encontró ninguna review con ese ID.");
            }

        } catch (SQLException e) {
            System.out.println("Error al eliminar la review: " + e.getMessage());
        }
    }
    // Método para eliminar un ejercicio y todos sus intentos y reviews asociados

    public boolean eliminarExercici(int idExercici) {
        String deleteReviewsSQL = "DELETE FROM Review WHERE IdIntent IN (SELECT Id FROM Intents WHERE IdExercici = ?)";
        String deleteIntentsSQL = "DELETE FROM Intents WHERE IdExercici = ?";
        String deleteExerciciSQL = "DELETE FROM Exercicis WHERE Id = ?";

        try {
            // Iniciar una transacción
            getConnection().setAutoCommit(false);

            // Paso 1: Eliminar las reviews asociadas con los intents del exercici
            try (PreparedStatement deleteReviewsStmt = getConnection().prepareStatement(deleteReviewsSQL)) {
                deleteReviewsStmt.setInt(1, idExercici);
                deleteReviewsStmt.executeUpdate();
            }

            // Paso 2: Eliminar los intents asociados con el exercici
            try (PreparedStatement deleteIntentsStmt = getConnection().prepareStatement(deleteIntentsSQL)) {
                deleteIntentsStmt.setInt(1, idExercici);
                deleteIntentsStmt.executeUpdate();
            }

            // Paso 3: Eliminar el exercici
            try (PreparedStatement deleteExerciciStmt = getConnection().prepareStatement(deleteExerciciSQL)) {
                deleteExerciciStmt.setInt(1, idExercici);
                int rowsAffected = deleteExerciciStmt.executeUpdate();

                // Confirmar la transacción solo si el ejercicio fue eliminado
                if (rowsAffected > 0) {
                    getConnection().commit();
                    return true;
                } else {
                    getConnection().rollback();
                    return false;
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
            // Si ocurre algún error, revertimos la transacción
            try {
                getConnection().rollback();
            } catch (SQLException rollbackEx) {
                rollbackEx.printStackTrace();
            }
            return false;
        } finally {
            // Restaurar el modo de auto-commit
            try {
                getConnection().setAutoCommit(true);
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    // En la clase DataAccess
    public void actualizarIntent(Intents intent) {
        // Ejemplo de código SQL para actualizar en la base de datos
        String query = "UPDATE intents SET idUsuari = ?, idExercici = ?, timestamp_Inici = ?, "
                + " videoFile = ? WHERE id = ?";

        try (PreparedStatement stmt = getConnection().prepareStatement(query)) {
            System.out.println("Actualizando intento con ID: " + intent.getId()); // Verificar qué intent estamos intentando actualizar

            stmt.setInt(1, intent.getIdUsuari());
            stmt.setInt(2, intent.getIdExercici());
            stmt.setString(3, intent.getTimestamp_Inici() != null ? intent.getTimestamp_Inici() : null);
            // stmt.setString(4, intent.getTimestamp_Fi() != null ? intent.getTimestamp_Fi() : null);
            stmt.setString(4, intent.getVideofile() != null ? intent.getVideofile() : null);
            stmt.setInt(5, intent.getId());

            stmt.executeUpdate();
            System.out.println("Intento actualizado correctamente.");
        } catch (SQLException e) {
            System.out.println("Error al actualizar el intento.");
            e.printStackTrace(); // Esto imprimirá el stack trace completo del error
        }
    }

    public void actualizarUsuario(int usuarioId, String nuevoNombre, String nuevoEmail, String nuevoPasswordHash) {
        String sql = "UPDATE Usuaris SET Nom = ?, Email = ?, PasswordHash = ? WHERE Id = ?";

        try (PreparedStatement ps = getConnection().prepareStatement(sql)) {

            // Asignar los parámetros al PreparedStatement
            ps.setString(1, nuevoNombre);
            ps.setString(2, nuevoEmail);
            ps.setString(3, nuevoPasswordHash);
            ps.setInt(4, usuarioId);

            // Ejecutar la actualización
            int rowsUpdated = ps.executeUpdate();

            if (rowsUpdated > 0) {
                System.out.println("Usuario actualizado con éxito.");
            } else {
                System.out.println("No se encontró el usuario con ID " + usuarioId);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error al actualizar el usuario.");
        }
    }

    public boolean updateUsuaris(Usuari user) {
        String sql = "UPDATE usuaris SET Nom = ?, Email = ?, PasswordHash = ?, Foto = ?, IsInstructor = ? WHERE Id = ?";
        boolean updated = false; // Variable para controlar si se actualizó

        try (PreparedStatement updateStatement = getConnection().prepareStatement(sql)) {

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
    // Método para actualizar la revisión

    public boolean updateReview(int idReview, int valoracio, String comentari, int idReviewer) {
        String sql = "UPDATE Review SET Valoracio = ?, Comentari = ?, IdReviewer = ? WHERE Id = ?";

        try (PreparedStatement preparedStatement = getConnection().prepareStatement(sql)) {
            // Asignar los valores a los parámetros
            preparedStatement.setInt(1, valoracio);
            preparedStatement.setString(2, comentari);
            preparedStatement.setInt(3, idReviewer);
            preparedStatement.setInt(4, idReview);

            // Ejecutar la actualización
            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;  // Retorna true si se actualizó al menos una fila

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Método para actualizar los datos de un ejercicio
    public boolean actualizarExercici(Exercici exercici) {
        String sql = "UPDATE Exercicis SET NomExercici = ?, Descripcio = ? WHERE Id = ?";

        try (PreparedStatement preparedStatement = getConnection().prepareStatement(sql)) {
            preparedStatement.setString(1, exercici.getNomExercici());
            preparedStatement.setString(2, exercici.getDescripcio());
            preparedStatement.setInt(3, exercici.getId());

            // Ejecuta la actualización y verifica si se actualizó alguna fila
            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

}

// hacer un listener de la celula y añadirle el evento
