package com.monica.web.project.commons.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

public class CommonServiceImpl<E, R extends JpaRepository<E, Long>> implements CommonService<E> {
	
	@Autowired
	protected R repository;

	@Override
	@Transactional(readOnly = true)
	public List<E> findAll() {
		return (List<E>) repository.findAll();
	}
	
	@Override
	@Transactional(readOnly = true)
	public Page<E> findAll(Pageable pageable) {
		return repository.findAll(pageable);
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<E> findById(Long id) {
		return repository.findById(id);
	}

	@Override
	@Transactional
	public E save(E entity) {
		return repository.save(entity);
	}

	@Override
	@Transactional
	public Optional<E> deleteById(Long id) {
		Optional<E> entity = repository.findById(id);
		if (entity.isPresent()) {
			repository.deleteById(id);
			return entity;
		}
		return Optional.empty();
	}

}
