package mx.uam.ayd.proyecto.negocio;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import mx.uam.ayd.proyecto.datos.ReparacionRepository;
import mx.uam.ayd.proyecto.negocio.modelo.Articulo;
import mx.uam.ayd.proyecto.negocio.modelo.Cliente;
import mx.uam.ayd.proyecto.negocio.modelo.Reparacion;
import mx.uam.ayd.proyecto.negocio.modelo.Venta;

@Slf4j
@Service
public class ServicioReparacion {
	private ReparacionRepository reparacionRepository;
	
	public Reparacion creaReparacion(String producto, Cliente cliente, String falla, LocalDate fechaEntrega, float deuda, Articulo articulo, float total) {
		try {
			Reparacion reparacion = new Reparacion();
			reparacion.setProducto(producto);
			reparacion.setDeuda(deuda);
			reparacion.setEstado("En reparacion");
			reparacion.setFechaEntrega(fechaEntrega);
			reparacion.setTotal(total);
		return reparacion;
		} catch (Exception e) {
			return null;
		}
		
	}
	
	public boolean registraReparacion(Reparacion reparacion) {
		try {
			reparacionRepository.save(reparacion);
			
			return true;
		} catch (Exception e) {
			return false;
		}
	}//fin registraReparacion
	
	public List<Reparacion> recuperaReparaciones(){
		List<Reparacion> reparaciones = new ArrayList<>();
		for(Reparacion reparacion : reparacionRepository.findAll()) {
			reparaciones.add(reparacion);
		}
		return reparaciones;
	}
	
	
}
