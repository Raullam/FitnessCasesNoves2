/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package spdvi.fitnesscasesnoves.dto;

/**
 *
 * @author Rulox
 */
public class Review {
    private int id;              // ID de la reseña
    private int idIntent;        // ID del intent asociado
    private int idReviewer;      // ID del revisor
    private int valoracion;      // Valoración (ej. 1-5)
    private String comentario;    // Comentario de la reseña

    // Constructor
    public Review(int id, int idIntent, int idReviewer, int valoracion, String comentario) {
        this.id = id;
        this.idIntent = idIntent;
        this.idReviewer = idReviewer;
        this.valoracion = valoracion;
        this.comentario = comentario;
    }

    // Métodos getters
    public int getId() {
        return id;
    }

    public int getIdIntent() {
        return idIntent;
    }

    public int getIdReviewer() {
        return idReviewer;
    }

    public int getValoracion() {
        return valoracion;
    }

    public String getComentario() {
        return comentario;
    }

    // Métodos setters (si deseas permitir cambios)
    public void setId(int id) {
        this.id = id;
    }

    public void setIdIntent(int idIntent) {
        this.idIntent = idIntent;
    }

    public void setIdReviewer(int idReviewer) {
        this.idReviewer = idReviewer;
    }

    public void setValoracion(int valoracion) {
        this.valoracion = valoracion;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }
}

