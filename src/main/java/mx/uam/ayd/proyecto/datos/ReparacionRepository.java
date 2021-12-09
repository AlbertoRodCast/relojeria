package mx.uam.ayd.proyecto.datos;

import org.springframework.data.repository.CrudRepository;

import mx.uam.ayd.proyecto.negocio.modelo.Reparacion;

public interface ReparacionRepository extends CrudRepository<Reparacion, Long>{
	
}
