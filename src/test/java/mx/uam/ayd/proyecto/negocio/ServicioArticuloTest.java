package mx.uam.ayd.proyecto.negocio;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import mx.uam.ayd.proyecto.datos.ArticuloRepository;
import mx.uam.ayd.proyecto.negocio.modelo.Articulo;

@ExtendWith(MockitoExtension.class)
class ServicioArticuloTest {

	@Mock
	private ArticuloRepository articuloRepository;
	
	@InjectMocks
	private ServicioArticulo servicioArticulo;
	
	@Test
	void testRecuperaArticulos() {
		List<Articulo> articulos = new ArrayList<Articulo>();
		Articulo articulo= new Articulo();
		articulo.setCantidadDisponible(10);
		articulo.setNombre("Pila");
		articulo.setPrecio(10);
		Articulo articuloDos= new Articulo();
		articuloDos.setCantidadDisponible(15);
		articuloDos.setNombre("Correa");
		articuloDos.setPrecio(20);
		
		when(articuloRepository.findAll()).thenReturn(articulos);
		//Caso de prueba: No hay articulos registrados
		List<Articulo> resultadoArticulos= servicioArticulo.recuperaArticulos();
		assertEquals(0, resultadoArticulos.size());
		
		//caso de prueba: hay articulos previamente registrados
		articulos.add(articulo);
		articulos.add(articuloDos);
		resultadoArticulos= servicioArticulo.recuperaArticulos();
		assertEquals(2, resultadoArticulos.size());
	}

	@Test
	void testActualizaArticulo() {
		Articulo articulo= new Articulo();
		articulo.setCantidadDisponible(10);
		articulo.setNombre("Pila");
		articulo.setPrecio(10);
		
		when(articuloRepository.findByName("Pila")).thenReturn(articulo);
		
		//caso de prueba: Se le mandan datos validos para la actualizacion de infomracion
		Articulo resultadoArticulo= servicioArticulo.actualizaArticulo("Pila", "9");
		assertEquals(articulo, resultadoArticulo);
		
		//Caso de prueba: articulo no existente
		resultadoArticulo= servicioArticulo.actualizaArticulo("Pila AA", "9");
		assertEquals(null, resultadoArticulo);
	}

	@Test
	void testRecuperaArticulo() {
		Articulo articulo= new Articulo();
		articulo.setCantidadDisponible(10);
		articulo.setNombre("Pila");
		articulo.setPrecio(10);
		
		when(articuloRepository.findByName("Pila")).thenReturn(articulo);
		
		//Caso de prueba: Se realiza la busqueda de un articulo
		Articulo articuloBuscado= servicioArticulo.recuperaArticulo("Pila");
		assertEquals(articulo, articuloBuscado);
	}
	
	@Test
	void testactualizaArticulo() {
		Articulo arti = new Articulo();
		arti.setNombre("relojcasio");
		arti.setCantidadDisponible(2);
		arti.setPrecio(350);
		
		when(articuloRepository.findByName("relojcasio")).thenReturn(arti);
		//Caso de prueba: mandar datos validos para actualizar
		boolean resultado = servicioArticulo.ActualizaArticulo("relojcasio", "5", "400");
		assertEquals(true, resultado);
		//Caso de prueba: mandar dator erroneos o null
		
		resultado = servicioArticulo.ActualizaArticulo(null, null, null);
		assertEquals(false, resultado);
		
		resultado = servicioArticulo.ActualizaArticulo("34","casio", "450");
		assertEquals(false, resultado);
	}
	
	@Test
	void testBorrarArticulo() {
		Articulo articulo = new Articulo();
		articulo.setNombre("relojcasio");
		articulo.setCantidadDisponible(2);
		articulo.setPrecio(350);
		
		when(articuloRepository.findByName("relojcasio")).thenReturn(articulo);
		
		//Caso de prueba: dando nombre correcto a borrar
		
		boolean resultado = servicioArticulo.BorrarArticulo("relojcasio");
		assertEquals(true, resultado);
		
		//caso de Prueba: mandar datos erroneos o null
		
		resultado= servicioArticulo.BorrarArticulo(null);
		assertEquals(false, resultado);
		
		resultado= servicioArticulo.BorrarArticulo("3456");
		assertEquals(false, resultado);
		
	}
	
	@Test
	void testCreaArticulo() {
		
				
		//Caso Prueba: Mandar datos correctos
		
		boolean resultado = servicioArticulo.CreaArticulo("Reloj-Rolex","2", "5000");
		assertEquals(true, resultado);
		
		//Caso Prueba: Mandar datos erroneos o nulos
		
		resultado= servicioArticulo.CreaArticulo(null, null, null);
		assertEquals(false, resultado);
		
		resultado= servicioArticulo.CreaArticulo("12353", "Reloj-Rolexx", "aqwe");
		assertEquals(false, resultado);
		
		
	}

}
