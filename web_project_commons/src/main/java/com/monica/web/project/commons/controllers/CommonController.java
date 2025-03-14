package com.monica.web.project.commons.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.monica.web.project.commons.services.CommonService;

import jakarta.validation.Valid;

public class CommonController<E, S extends CommonService<E>> {
	
	@Autowired
	protected S service;
	
	@GetMapping
	public ResponseEntity<List<E>> getAll() {
		return ResponseEntity.ok().body(service.findAll());
	}
	
	@GetMapping("/pagina")
	public ResponseEntity<Page<E>> getAll(Pageable pageable) {
		return ResponseEntity.ok().body(service.findAll(pageable));
	}

	@GetMapping("/{id}")
	public ResponseEntity<E> getById(@PathVariable Long id) {
		Optional<E> entity = service.findById(id);
		if (entity.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok().body(entity.get());
	}
	
	@PostMapping
	public ResponseEntity<?> create(@Valid @RequestBody E entity, BindingResult result) {
		if (result.hasErrors()) {
			return this.validar(result);
		}
		E entityDb = service.save(entity);
		return ResponseEntity.status(HttpStatus.CREATED).body(entityDb);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<E> delete(@PathVariable Long id) {
		Optional<E> entity = service.deleteById(id);
		if (entity.isPresent()) {
			return ResponseEntity.ok().body(entity.get()); 
		}
		return ResponseEntity.notFound().build();
	}
	
	protected ResponseEntity<?> validar(BindingResult result) {
		Map<String, Object> errores = new HashMap<>();
		result.getFieldErrors().forEach(err -> {
			errores.put(err.getField(), "Campo: " + err.getField() + " " + err.getDefaultMessage());
		});
		return ResponseEntity.badRequest().body(errores);
	}

}
