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
import mx.uam.ayd.proyecto.datos.VentaRepository;
import mx.uam.ayd.proyecto.negocio.modelo.Articulo;
import mx.uam.ayd.proyecto.negocio.modelo.Cliente;
import mx.uam.ayd.proyecto.negocio.modelo.PagoArticulo;
import mx.uam.ayd.proyecto.negocio.modelo.Venta;
@ExtendWith(MockitoExtension.class)

class ServicioVentaTest {

	@Mock
	private VentaRepository ventaRepository;
	
	@InjectMocks
	private ServicioVenta servicioVenta;
	
	@Test
	void testRegistrarVenta() {
		Articulo articulo= new Articulo();
		articulo.setCantidadDisponible(10);
		articulo.setNombre("Pila");
		articulo.setPrecio(10);
		Cliente cliente = new Cliente();
		cliente.setNombre("Juan");
		cliente.setCorreo("@mail.com");
		cliente.setTelefono("5520861234");
		Venta venta= new Venta();
		venta.setApartado(false);
		venta.setArticulo(articulo);
		venta.setDeuda(0);
		venta.setFecha(LocalDate.now());
		venta.setTotal(10);
		venta.setCliente(cliente);
		
		//Caso1: Se comporta correctamente al pasarle un objeto valido
		Boolean resultado = servicioVenta.registrarVenta(venta);
		
		assertEquals(true, resultado, "Se esperaba un true");
		
		//caso2: Se comporta correctamente al pasarle un null
		Boolean resultadoDos= servicioVenta.registrarVenta(null);
		assertEquals(false, resultadoDos);
		
	}

	@Test
	void testDameVentas() {
		List<Venta> ventas = new ArrayList<Venta>();
		Articulo articulo= new Articulo();
		articulo.setCantidadDisponible(10);
		articulo.setNombre("Pila");
		articulo.setPrecio(10);
		Cliente cliente = new Cliente();
		cliente.setNombre("Juan");
		cliente.setCorreo("@mail.com");
		cliente.setTelefono("5520861234");
		Venta venta= new Venta();
		venta.setApartado(false);
		venta.setArticulo(articulo);
		venta.setDeuda(0);
		venta.setFecha(LocalDate.now());
		venta.setTotal(10);
		venta.setCliente(cliente);
		
		
		Cliente clienteDos = new Cliente();
		cliente.setNombre("Juana");
		cliente.setCorreo("@mail.com");
		cliente.setTelefono("5520871234");
		Venta ventaDos= new Venta();
		ventaDos.setApartado(false);
		ventaDos.setArticulo(articulo);
		ventaDos.setDeuda(0);
		ventaDos.setFecha(LocalDate.now());
		ventaDos.setTotal(10);
		ventaDos.setCliente(clienteDos);
		when(ventaRepository.findAll()).thenReturn(ventas);
		
		//caso Donde no hay ventas registradas
		List<Venta>resultado = servicioVenta.dameVentas();
		assertEquals(0, resultado.size());
		
		//caso con ventas registradas
		ventas.add(venta);
		ventas.add(ventaDos);
		resultado=servicioVenta.dameVentas();
		assertEquals(2, resultado.size());
		
	}

	@Test
	void testBuscaVentas() {
		List<Venta> ventas = new ArrayList<Venta>();
		Articulo articulo= new Articulo();
		articulo.setCantidadDisponible(10);
		articulo.setNombre("Pila");
		articulo.setPrecio(10);
		Cliente cliente = new Cliente();
		cliente.setNombre("Juan");
		cliente.setCorreo("@mail.com");
		cliente.setTelefono("5520861234");
		Venta venta= new Venta();
		venta.setApartado(false);
		venta.setArticulo(articulo);
		venta.setDeuda(0);
		venta.setFecha(LocalDate.now());
		venta.setTotal(10);
		venta.setCliente(cliente);
		
		
		Cliente clienteDos = new Cliente();
		cliente.setNombre("Juana");
		cliente.setCorreo("@mail.com");
		cliente.setTelefono("5520871234");
		Venta ventaDos= new Venta();
		ventaDos.setApartado(false);
		ventaDos.setArticulo(articulo);
		ventaDos.setDeuda(0);
		ventaDos.setFecha(LocalDate.now());
		ventaDos.setTotal(10);
		ventaDos.setCliente(clienteDos);
		when(ventaRepository.findByArticulo(articulo)).thenReturn(ventas);
		
		//Caso de prueba: no hay ventas con ese articulo
		List<Venta> resultadoVentas= servicioVenta.buscaVentas(articulo);
		assertEquals(0, resultadoVentas.size());
		
		//Caso de prueba: Se introduce el nombre de un articulo valido
		ventas.add(ventaDos);
		ventas.add(venta);
		resultadoVentas= servicioVenta.buscaVentas(articulo);
		assertEquals(2, resultadoVentas.size());
		
	}

	@Test
	void testCreaVenta() {
		Cliente cliente = new Cliente();
		cliente.setNombre("Juan");
		cliente.setCorreo("@mail.com");
		cliente.setTelefono("5520861234");
		PagoArticulo pagoart= new PagoArticulo();
		pagoart.setCantidad(1);
		Articulo articulo= new Articulo();
		articulo.setCantidadDisponible(10);
		articulo.setNombre("Pila");
		articulo.setPrecio(10);
		Venta venta= new Venta();
		venta.setArticulo(articulo);
		venta.setDeuda(0);
		venta.setFecha(LocalDate.now());
		venta.setTotal(10);
		venta.setCliente(cliente);
		
		//Caso Datos introducidos son del tipo correcto
		Venta resultadoVenta=servicioVenta.CreaVenta(cliente, pagoart, articulo, "10");
		assertEquals(venta, resultadoVenta);
		 
	}

	@Test
	void testActualizaDatos() {
		//se crea la venta a actualizar
		Cliente cliente = new Cliente();
		cliente.setNombre("Juan");
		cliente.setCorreo("@mail.com");
		cliente.setTelefono("5520861234");
		PagoArticulo pagoart= new PagoArticulo();
		pagoart.setCantidad(1);
		Articulo articulo= new Articulo();
		articulo.setCantidadDisponible(10);
		articulo.setNombre("Pila");
		articulo.setPrecio(10);
		Venta venta= new Venta();
		venta.setArticulo(articulo);
		venta.setDeuda(10);
		venta.setFecha(LocalDate.now());
		venta.setTotal(10);
		venta.setCliente(cliente);
		
		//caso de prueba: no se encuentra la venta
		Boolean resultadoActualiza= servicioVenta.actualizaDatos(articulo,  null, null, 0, 0);
		assertEquals(false, resultadoActualiza);
		//Caso de prueba: Se le pasan datos validos para el metodo
		when(ventaRepository.findByClienteAndFecha(cliente, LocalDate.now())).thenReturn(venta);
		resultadoActualiza=servicioVenta.actualizaDatos(articulo, cliente, LocalDate.now(), 20, 0);
		assertEquals(true, resultadoActualiza);
		
	}

	@Test
	void testBuscaVenta() {
		
		Articulo articulo= new Articulo();
		articulo.setCantidadDisponible(10);
		articulo.setNombre("Pila");
		articulo.setPrecio(10);
		Cliente cliente = new Cliente();
		cliente.setNombre("Juan");
		cliente.setCorreo("@mail.com");
		cliente.setTelefono("5520861234");
		Venta venta= new Venta();
		venta.setApartado(false);
		venta.setArticulo(articulo);
		venta.setDeuda(0);
		venta.setFecha(LocalDate.now());
		venta.setTotal(10);
		venta.setCliente(cliente);
		
		
		Cliente clienteDos = new Cliente();
		cliente.setNombre("Juana");
		cliente.setCorreo("@mail.com");
		cliente.setTelefono("5520871234");
		Venta ventaDos= new Venta();
		ventaDos.setApartado(false);
		ventaDos.setArticulo(articulo);
		ventaDos.setDeuda(0);
		ventaDos.setFecha(LocalDate.now());
		ventaDos.setTotal(10);
		ventaDos.setCliente(clienteDos);
		when(ventaRepository.findByClienteAndFecha(cliente, LocalDate.now())).thenReturn(venta);
		
		//Caso de prueba: se encuentra la venta deseada
		Venta resultadoBusqueda = servicioVenta.buscaVenta(cliente, LocalDate.now());
		assertEquals(venta, resultadoBusqueda);
		
		//caso de prueba: No se encuentra la venta
		resultadoBusqueda= servicioVenta.buscaVenta(null, null);
		assertEquals(null, resultadoBusqueda);
	}

}
