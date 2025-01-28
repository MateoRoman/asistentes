package com.espe.eventos.clients;
import com.espe.eventos.model.entities.Usuario;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;

import java.util.List;

@FeignClient(name = "asistentes", url = "localhost:8003/api/")
public interface UsuarioClientRest {

    @GetMapping("/asistentes")
    List<Usuario> listarUsuarios();

    @GetMapping("/asistentes/{id}")
    Usuario findById(@PathVariable("id") Long id);

    @PostMapping("/asistentes")
    Usuario crearUsuario(@RequestBody Usuario usuario);

    @DeleteMapping("/asistentes/{id}")
    void eliminarUsuario(@PathVariable("id") Long id);
}