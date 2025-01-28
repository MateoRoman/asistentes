package com.espe.eventos.model.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "eventos_usuarios", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"evento_id", "usuario_id"}) // Asegura la unicidad por combinación
})
public class EventoUsuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="evento_id", nullable = false)
    private Long evento_id;


    @Column(name="usuario_id", nullable = false)
    private Long usuario_id;

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getEvento() {
        return evento_id;
    }

    public void setEvento(Long evento_id) {
        this.evento_id = evento_id;
    }

    public Long getUsuario() {
        return usuario_id;
    }

    public void setUsuario(Long usuario_id) {
        this.usuario_id = usuario_id;
    }
}
