package mx.uam.ayd.proyecto.datos;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.repository.CrudRepository;

import mx.uam.ayd.proyecto.negocio.modelo.Pedido;

public interface PedidoRepository extends CrudRepository<Pedido, Long> {

	/*
	 * Método que recupera los Pedidos almacenados que se encuentran dentro de un periodo establecido por el usuario
	 * @Param LocalDate fechaInicio dato de tipo fecha local
	 * @Param LocalDate fechaTermino dato de tipo fecha local
	 * Regresa una lista de objetos de tipo Pedido
	 */
	List<Pedido> findAllByFechaLlegadaGreaterThanAndLessThan(LocalDate fechaInicio, LocalDate fechaTermino);

}
