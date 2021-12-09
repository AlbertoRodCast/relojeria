package mx.uam.ayd.proyecto.negocio;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import mx.uam.ayd.proyecto.negocio.modelo.PedidoArticulo;
import mx.uam.ayd.proyecto.datos.PedidoArticuloRepository;

@Slf4j
@Service
public class ServicioPedidoArticulo {
	@Autowired
	private PedidoArticuloRepository pedidoArticuloRepository;
	
	/*
	 * Método que recupera todos los PedidoArticulos almacenados
	 * No recibe parámetros y regresa una lista de objetos de tipo PedidoArticulo
	 */
		public List<PedidoArticulo> recuperaPedidoArticulos(){
		
		List<PedidoArticulo> pedidoArticulos = new ArrayList<>();
		
		for(PedidoArticulo pedidoArticulo: pedidoArticuloRepository.findAll()) {
			pedidoArticulos.add(pedidoArticulo);
		}
		
		return pedidoArticulos;
	}
	
}

