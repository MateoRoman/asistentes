package com.espe.eventos.model.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "Eventos")
public class Evento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "El nombre no puede estar vacío")
    @Column(nullable = false)
    private String nombre;

    @NotEmpty(message = "La descripción no puede estar vacía")
    @Column(nullable = false)
    private String descripcion;

    @NotNull(message = "La fecha no puede ser nula")
    @Column(nullable = false)
    private Date fecha;

    @NotEmpty(message = "El lugar no puede estar vacío")
    @Column(nullable = false)
    private String lugar;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false, updatable = false)
    private Date creadoEn;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "evento_id")
    private List<EventoUsuario> eventoUsuarios;

    @Transient
    private List<Usuario> asistentes;

    public Evento() {
        eventoUsuarios = new ArrayList<>();
        asistentes = new ArrayList<>();
    }

    @PrePersist
    protected void onCreate() {
        this.creadoEn = new Date();
    }

    public void addEventoUsuario(EventoUsuario eventoUsuario) {
        eventoUsuarios.add(eventoUsuario);
    }

    public void removeEventoUsuario(EventoUsuario eventoUsuario) {
        eventoUsuarios.remove(eventoUsuario);
    }

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getLugar() {
        return lugar;
    }

    public void setLugar(String lugar) {
        this.lugar = lugar;
    }

    public Date getCreadoEn() {
        return creadoEn;
    }

    public List<EventoUsuario> getEventoUsuarios() {
        return eventoUsuarios;
    }

    public void setEventoUsuarios(List<EventoUsuario> eventoUsuarios) {
        this.eventoUsuarios = eventoUsuarios;
    }
}
