package com.monica.web.project.clientes.app.models.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.monica.web.project.commons.models.entity.Cliente;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long>{

}
