package mx.uam.ayd.proyecto.negocio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import mx.uam.ayd.proyecto.datos.ClienteRepository;
import mx.uam.ayd.proyecto.negocio.modelo.Cliente;

@Slf4j
@Service

/*
 * Se encarga de la comuniciÃ³n con su repository, edita datos de este.
 */
public class ServicioCliente {
	@Autowired
	private ClienteRepository clienteRepository;
	
	public Cliente recuperaCliente(String nombreCliente) {
		Cliente cliente;
		cliente = clienteRepository.findByName(nombreCliente);
		return cliente;
	}
	
	
	public Cliente CreaCliente(String nombre) {
		Cliente cliente =  new Cliente();
		cliente.setNombre(nombre);
		clienteRepository.save(cliente);
		return cliente;
	}
	
}

