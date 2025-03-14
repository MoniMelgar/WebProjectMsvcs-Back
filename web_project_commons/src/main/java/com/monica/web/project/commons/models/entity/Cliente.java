package com.monica.web.project.commons.models.entity;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="CLIENTE")
public class Cliente {
	
	@Id
	@Column(name = "CLIENTEID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

	@Column(name = "clienteactivo", nullable = false)
    private Boolean clienteActivo;

    @Column(name = "clientenombre", nullable = false)
    private String clienteNombre;

    @CreationTimestamp
    @Column(name = "clientefechacreacion", nullable = false, updatable = false)
    private LocalDateTime clienteFechaCreacion;

    @UpdateTimestamp
    @Column(name = "clientefechamodificacion", nullable = false)
    private LocalDateTime clienteFechaModificacion;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Boolean getClienteActivo() {
		return clienteActivo;
	}

	public void setClienteActivo(Boolean clienteActivo) {
		this.clienteActivo = clienteActivo;
	}

	public String getClienteNombre() {
		return clienteNombre;
	}

	public void setClienteNombre(String clienteNombre) {
		this.clienteNombre = clienteNombre;
	}

	public LocalDateTime getClienteFechaCreacion() {
		return clienteFechaCreacion;
	}

	public void setClienteFechaCreacion(LocalDateTime clienteFechaCreacion) {
		this.clienteFechaCreacion = clienteFechaCreacion;
	}

	public LocalDateTime getClienteFechaModificacion() {
		return clienteFechaModificacion;
	}

	public void setClienteFechaModificacion(LocalDateTime clienteFechaModificacion) {
		this.clienteFechaModificacion = clienteFechaModificacion;
	}

}
