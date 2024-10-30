/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package spdvi.fitnesscasesnoves.dto;

/**
 *
 * @author Rulox
 */
public class Intents {
    int id;
    int idUsuari;
    int idExercici;
    String Timestamp_Inici;
    String Timestamp_Fi;
    String videofile;
    String nomExercici;

    public Intents(int id, int idUsuari, int idExercici, String Timestamp_Inici, String Timestamp_Fi, String videofile) {
        this.id = id;
        this.idUsuari = idUsuari;
        this.idExercici = idExercici;
        this.Timestamp_Inici = Timestamp_Inici;
        this.Timestamp_Fi = Timestamp_Fi;
        this.videofile = videofile;
    }

    public Intents(int id, String nomExercici) {
        this.id = id;
        this.nomExercici = nomExercici;
    }
    

    public Intents(int id, int idUsuari, int idExercici, String Timestamp_Inici, String Timestamp_Fi, String videofile, String nomExercici) {
        this.id = id;
        this.idUsuari = idUsuari;
        this.idExercici = idExercici;
        this.Timestamp_Inici = Timestamp_Inici;
        this.Timestamp_Fi = Timestamp_Fi;
        this.videofile = videofile;
        this.nomExercici = nomExercici;
    }

    @Override
    public String toString() {
        return "Intents{" + "id=" + id + ", idUsuari=" + idUsuari + ", idExercici=" + idExercici + ", Timestamp_Inici=" + Timestamp_Inici + ", Timestamp_Fi=" + Timestamp_Fi + ", videofile=" + videofile + '}';
    }
    
    
    
    
}
