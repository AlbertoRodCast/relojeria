package mx.uam.ayd.proyecto.presentacion.registrarReparacion;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import mx.uam.ayd.proyecto.negocio.ServicioArticulo;
import mx.uam.ayd.proyecto.negocio.ServicioCliente;
import mx.uam.ayd.proyecto.negocio.ServicioReparacion;
import mx.uam.ayd.proyecto.negocio.modelo.Articulo;
import mx.uam.ayd.proyecto.negocio.modelo.Cliente;
import mx.uam.ayd.proyecto.negocio.modelo.Reparacion;

@Component
public class ControlRegistrarReparacion {

	@Autowired
	private ServicioReparacion servicioReparacion;
	
	@Autowired
	private ServicioArticulo servicioArticulo;
	
	@Autowired
	private ServicioCliente servicioCliente;
	
	
	VistaRegistrarReparacion vistaRegistrarReparacion;
	
	public void inicia() {
		List<Articulo> articulos = servicioArticulo.recuperaArticulos();
		vistaRegistrarReparacion.muestra(this, articulos);
		
	}
	
	public void registrarReparacion(String producto, String nombreCliente, String falla, String costo, String fechaDeEntrega, String anticipo, String refaccion ) {
		Articulo articulo = servicioArticulo.actualizaArticulo(refaccion, ""+1);
		Cliente cliente = servicioCliente.recuperaCliente(nombreCliente);
		//la fecha ingresada por el usuario debe tener el formato ej: 2004-12-31
		LocalDate fechaent= LocalDate.parse(fechaDeEntrega);
		float deuda = Float.parseFloat(costo)- Float.parseFloat(anticipo);
		
		Reparacion reparacion = servicioReparacion.creaReparacion(producto, cliente, falla,fechaent, deuda, articulo, Float.parseFloat(costo));
		Boolean registro = servicioReparacion.registraReparacion(reparacion);
		if(registro) {
			//muestra Dialogo de exito
		}
		else {
			//muestra dialogo de error
		}
	}
	
}
