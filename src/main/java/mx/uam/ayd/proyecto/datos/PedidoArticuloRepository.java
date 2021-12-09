package mx.uam.ayd.proyecto.datos;

import org.springframework.data.repository.CrudRepository;

import mx.uam.ayd.proyecto.negocio.modelo.PedidoArticulo;

public interface PedidoArticuloRepository extends CrudRepository<PedidoArticulo, Long>{

}
