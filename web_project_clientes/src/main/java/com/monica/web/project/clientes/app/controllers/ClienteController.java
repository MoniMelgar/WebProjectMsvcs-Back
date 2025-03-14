package com.monica.web.project.clientes.app.controllers;

import java.util.HashMap;
import java.util.Map;

import org.apache.hc.core5.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.monica.web.project.clientes.app.services.ClienteService;
import com.monica.web.project.commons.controllers.CommonController;
import com.monica.web.project.commons.models.entity.Cliente;

import jakarta.validation.Valid;


@RestController
public class ClienteController extends CommonController<Cliente, ClienteService>{

	@PutMapping("/{id}")
    public ResponseEntity<?> update(@Valid @RequestBody Cliente cliente, @PathVariable Long id, BindingResult result) {
        if (result.hasErrors()) {
            return this.validar(result); // Método para manejar errores de validación
        }

        try {
            Cliente clienteActualizado = service.update(cliente, id);
            return ResponseEntity.ok().body(clienteActualizado);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.SC_NOT_FOUND).body(e.getMessage());
        }
    }

    // Método para manejar errores de validación
    protected ResponseEntity<?> validar(BindingResult result) {
        Map<String, Object> errores = new HashMap<>();
        result.getFieldErrors().forEach(err -> {
            errores.put(err.getField(), "Campo: " + err.getField() + " " + err.getDefaultMessage());
        });
        return ResponseEntity.badRequest().body(errores);
    }
	
}
