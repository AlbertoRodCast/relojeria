package mx.uam.ayd.proyecto.datos;

import org.springframework.data.repository.CrudRepository;

import mx.uam.ayd.proyecto.negocio.modelo.Cliente;

public interface ClienteRepository extends CrudRepository<Cliente, Long> {

	Cliente findByName(String nombreCliente);
	
	
}
