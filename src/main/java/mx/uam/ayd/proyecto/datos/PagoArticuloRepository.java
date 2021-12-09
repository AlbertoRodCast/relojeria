package mx.uam.ayd.proyecto.datos;

import org.springframework.data.repository.CrudRepository;

import mx.uam.ayd.proyecto.negocio.modelo.PagoArticulo;

public interface PagoArticuloRepository extends CrudRepository<PagoArticulo, Long> {

}
