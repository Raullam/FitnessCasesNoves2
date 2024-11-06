package spdvi.fitnesscasesnoves.dto;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author Rulox
 */
public class Exercici {

    private int id;             // ID del ejercicio
    private String nomExercici; // Nombre del ejercicio
    private String descripcio;  // Descripción del ejercicio

    // Constructor vacío
    public Exercici() {
    }

    // Constructor completo
    public Exercici(int id, String nomExercici, String descripcio) {
        this.id = id;
        this.nomExercici = nomExercici;
        this.descripcio = descripcio;
    }

    // Getters y Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNomExercici() {
        return nomExercici;
    }

    public void setNomExercici(String nomExercici) {
        this.nomExercici = nomExercici;
    }

    public String getDescripcio() {
        return descripcio;
    }

    public void setDescripcio(String descripcio) {
        this.descripcio = descripcio;
    }

    // Método toString para representar el objeto en forma de cadena
    @Override
    public String toString() {
        return "Exercici{"
                + "id=" + id
                + ", nomExercici='" + nomExercici + '\''
                + ", descripcio='" + descripcio + '\''
                + '}';
    }
}
