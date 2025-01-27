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
    private Long eventoId;

    @Column(name="usuario_id", nullable = false)
    private Long usuarioId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getEventoId() {
        return eventoId;
    }

    public void setEventoId(Long eventoId) {
        this.eventoId = eventoId;
    }

    public Long getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(Long usuarioId) {
        this.usuarioId = usuarioId;
    }
}
