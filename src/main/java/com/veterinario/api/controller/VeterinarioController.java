package com.veterinario.api.controller;

import com.veterinario.api.exception.ResourceNotFoundException;
import com.veterinario.api.entity.Veterinario;
import com.veterinario.api.repository.VeterinarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class VeterinarioController {
    @Autowired
    private VeterinarioRepository veterinarioRepository;

    @GetMapping("/veterinarios")
    public List<Veterinario> obtenerTodosLosVeterinarios() {
        return veterinarioRepository.findAll();
    }

    @GetMapping("/veterinarios/{id}")
    public ResponseEntity<Veterinario> buscarVeterinarioPorId(@PathVariable(value = "id") Long idVeterinario) throws ResourceNotFoundException {
        Veterinario veterinario = veterinarioRepository.findById(idVeterinario).orElseThrow(() -> new ResourceNotFoundException("No se encontró un empleado para el id ::" + idVeterinario));
        return ResponseEntity.ok().body(veterinario);
    }

    @PostMapping("/veterinarios")
    public Veterinario agregarVeterinario(@RequestBody Veterinario veterinario) {
        return veterinarioRepository.save(veterinario);
    }


}
