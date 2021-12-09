package mx.uam.ayd.proyecto.datos;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.repository.CrudRepository;

import mx.uam.ayd.proyecto.negocio.modelo.Articulo;
import mx.uam.ayd.proyecto.negocio.modelo.Cliente;
import mx.uam.ayd.proyecto.negocio.modelo.Venta;

public interface VentaRepository extends CrudRepository<Venta, Long> {

	public List<Venta> findByArticulo(Articulo articulo);

	public Venta findByClienteAndFecha(Cliente cliente, LocalDate fecha);
	
	List<Venta> findAllByFechaDateBetween(LocalDate fechaInicio, LocalDate fechaTermino);

}
