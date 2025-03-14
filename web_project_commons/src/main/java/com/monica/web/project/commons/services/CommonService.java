package com.monica.web.project.commons.services;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CommonService<E> {
	
	public List<E> findAll();
	
	public Page<E> findAll(Pageable page);
	
	public Optional<E> findById(Long id);
	
	public E save(E entity);
	
	public Optional<E> deleteById(Long id);

}
