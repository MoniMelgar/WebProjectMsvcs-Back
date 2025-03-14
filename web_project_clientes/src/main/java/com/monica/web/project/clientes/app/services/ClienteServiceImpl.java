package com.monica.web.project.clientes.app.services;

import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.monica.web.project.clientes.app.models.repositories.ClienteRepository;
import com.monica.web.project.commons.models.entity.Cliente;
import com.monica.web.project.commons.services.CommonServiceImpl;


@Service
public class ClienteServiceImpl extends CommonServiceImpl<Cliente, ClienteRepository> implements ClienteService{
	
	@Override
	@Transactional
	public Cliente update(Cliente cliente, Long id) {
		Optional<Cliente> clienteOptional = repository.findById(id);
		
		if (clienteOptional.isPresent()) {
            // Obtiene el cliente existente
            Cliente clienteExistente = clienteOptional.get();

            // Actualiza solo los campos permitidos
            clienteExistente.setClienteActivo(cliente.getClienteActivo());
            clienteExistente.setClienteNombre(cliente.getClienteNombre());

            // Guarda el cliente actualizado
            return repository.save(clienteExistente);
        } else {
            // Si no se encuentra el cliente, lanza una excepción
            throw new RuntimeException("Cliente no encontrado con ID: " + id);
        }
    }
	
}
