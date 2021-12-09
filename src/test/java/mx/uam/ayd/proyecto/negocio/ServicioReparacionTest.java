package mx.uam.ayd.proyecto.negocio;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import mx.uam.ayd.proyecto.datos.ReparacionRepository;
import mx.uam.ayd.proyecto.negocio.modelo.Articulo;
import mx.uam.ayd.proyecto.negocio.modelo.Cliente;
import mx.uam.ayd.proyecto.negocio.modelo.Reparacion;

@ExtendWith(MockitoExtension.class)
class ServicioReparacionTest {

	@Mock
	private ReparacionRepository reparacionRepository;
	
	@InjectMocks
	private ServicioReparacion servicioReparacion;
	
	@Test
	void testCreaReparacion() {
		String producto= "Reloj Casio Negro";
		Cliente cliente = new Cliente();
		cliente.setNombre("Juan");
		cliente.setCorreo("@mail.com");
		cliente.setTelefono("5520861234");
		String falla= "cambio de pila";
		LocalDate fecha= LocalDate.parse("2021-10-20");
		float deuda = 0;
		float total=50;
		Articulo articulo= new Articulo();
		articulo.setCantidadDisponible(10);
		articulo.setNombre("Pila");
		Reparacion reparacionEsperada= new Reparacion();
		/*reparacionEsperada.setDeuda(deuda);
		reparacionEsperada.setEstado("En reparacion");
		reparacionEsperada.setFalla(falla);
		reparacionEsperada.setFechaEntrega(fecha);
		reparacionEsperada.setProducto(producto);
		reparacionEsperada.setTotal(total);*/
		Reparacion resultadoReparacion= servicioReparacion.creaReparacion(producto, cliente, falla, fecha, deuda, articulo, total);
		
		//caso de prueba: El objeto es creado correctamente 
		assertEquals(reparacionEsperada.getClass(), resultadoReparacion.getClass());
		//Caso de prueba: se pasan objetos nulos o no validos
		resultadoReparacion= servicioReparacion.creaReparacion(null, null, null, null, -1, null, 0);
		assertEquals(null, resultadoReparacion);
			
	}

	@Test
	void testRegistraReparacion() {
		String producto= "Reloj Casio Negro";
		Cliente cliente = new Cliente();
		cliente.setNombre("Juan");
		cliente.setCorreo("@mail.com");
		cliente.setTelefono("5520861234");
		String falla= "cambio de pila";
		LocalDate fecha= LocalDate.parse("2021-10-20");
		float deuda = 0;
		float total=50;
		Articulo articulo= new Articulo();
		articulo.setCantidadDisponible(10);
		articulo.setNombre("Pila");
		
		Reparacion reparacion= new Reparacion();
		reparacion.setDeuda(deuda);
		reparacion.setEstado("En reparacion");
		reparacion.setFalla(falla);
		reparacion.setFechaEntrega(fecha);
		reparacion.setProducto(producto);
		reparacion.setTotal(total);
		//Caso de prueba: Se le pasa como parametro un objeto de tipo reparacion
		Boolean resultado= servicioReparacion.registraReparacion(reparacion);
		assertEquals(true, resultado);
		
		//Caso de prueba: Se le pasa como parametro un null
		resultado= servicioReparacion.registraReparacion(null);
		assertEquals(false, resultado);
	}

	@Test
	void testRecuperaReparaciones() {
		String producto= "Reloj Casio Negro";
		Cliente cliente = new Cliente();
		cliente.setNombre("Juan");
		cliente.setCorreo("@mail.com");
		cliente.setTelefono("5520861234");
		String falla= "cambio de pila";
		LocalDate fecha= LocalDate.parse("2021-10-20");
		float deuda = 0;
		float total=50;
		Articulo articulo= new Articulo();
		articulo.setCantidadDisponible(10);
		articulo.setNombre("Pila");
		
		Reparacion reparacionDos= new Reparacion();
		reparacionDos.setDeuda(deuda);
		reparacionDos.setEstado("En reparacion");
		reparacionDos.setFalla(falla);
		reparacionDos.setFechaEntrega(fecha);
		reparacionDos.setProducto(producto);
		reparacionDos.setTotal(total);
		Reparacion reparacion= new Reparacion();
		reparacion.setDeuda(deuda);
		reparacion.setEstado("reparado");
		reparacion.setFalla(falla);
		reparacion.setFechaEntrega(fecha);
		reparacion.setProducto(producto);
		reparacion.setTotal(total);
		List<Reparacion> reparaciones= new ArrayList<Reparacion>();
		
		when(reparacionRepository.findAll()).thenReturn(reparaciones);
		//Caso prueba: No hay reparaciones en el repositorio
		List<Reparacion> listaEsperada= servicioReparacion.recuperaReparaciones();
		assertEquals(0, listaEsperada.size());
		
		//Caso prueba: ya hay reparaciones registradas
		reparaciones.add(reparacion);
		reparaciones.add(reparacionDos);
		 listaEsperada= servicioReparacion.recuperaReparaciones();
		assertEquals(2, listaEsperada.size());
	}

}
