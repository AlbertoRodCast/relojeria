package mx.uam.ayd.proyecto.presentacion.consultarVenta;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import mx.uam.ayd.proyecto.negocio.ServicioArticulo;
import mx.uam.ayd.proyecto.negocio.ServicioCliente;
import mx.uam.ayd.proyecto.negocio.ServicioVenta;
import mx.uam.ayd.proyecto.negocio.modelo.Articulo;
import mx.uam.ayd.proyecto.negocio.modelo.Cliente;
import mx.uam.ayd.proyecto.negocio.modelo.Venta;
/*
	 * Esta clase lleva el flujo de control para el proceso de consulta de ventas
	 * @author DanielAtonal
	 */
@Component
public class ControlConsultarVentas {
	
	@Autowired 
	private ServicioVenta servicioVenta;
	@Autowired
	private ServicioArticulo servicioArticulo;
	@Autowired
	private ServicioCliente servicioCliente;
	
	
	private VistaConsultarVenta vistaConsultarVenta;
	
	/*
	 * se encarga de iniciar la vista con los datos correspondientes
	 */
	public void inicia() {
		List<Venta> ventas = servicioVenta.dameVentas();
		vistaConsultarVenta.muestra(this, ventas);
	}
	
	/*
	 * Se encarga de actualizar los datos de una venta
	 */
	public void actualizaDatos(String nombre, String producto, String total, String deuda, String fecha) {
		Articulo articulo = servicioArticulo.recuperaArticulo(producto);
		Cliente cliente= servicioCliente.recuperaCliente(nombre);
		LocalDate fechabusca= LocalDate.parse(fecha);
		Boolean resultado= servicioVenta.actualizaDatos(articulo, cliente, fechabusca, Float.parseFloat(total), Float.parseFloat(deuda));
		if(resultado) {
			//muestra dialogo de exito
		}
		else {
			//muestra dialogo de error
		}
	}
	
	
	/*
	 * busca las ventas de un articulo espefico
	 */
	public List<Venta> buscaVentas(String articulo){
		Articulo articuloBusca= servicioArticulo.recuperaArticulo(articulo);
		List<Venta> ventasArticulo=servicioVenta.buscaVentas(articuloBusca);
		return ventasArticulo;
	}
	
	
}
