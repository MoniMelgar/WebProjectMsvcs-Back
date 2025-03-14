package com.monica.web.project.clientes.app.services;

import com.monica.web.project.commons.models.entity.Cliente;
import com.monica.web.project.commons.services.CommonService;

public interface ClienteService extends CommonService<Cliente>{
	
	public Cliente update(Cliente cliente, Long id);

}