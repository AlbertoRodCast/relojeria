package mx.uam.ayd.proyecto.negocio;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import mx.uam.ayd.proyecto.datos.PedidoRepository;
import mx.uam.ayd.proyecto.negocio.ServicioPedido.Balance;
import mx.uam.ayd.proyecto.negocio.modelo.Pedido;
import mx.uam.ayd.proyecto.negocio.modelo.Venta;

@ExtendWith(MockitoExtension.class)
class ServicioPedidoTest {
	
	@Mock
	private PedidoRepository pedidoRepository;
	
	@InjectMocks
	private ServicioPedido servicioPedido;
	
	@BeforeEach
	void setUp() throws Exception {
	}

	@AfterEach
	void tearDown() throws Exception {
	}
	
	@Test
	void testRecuperaPedidos() {
		//Caso 1: Recupera una lista de Pedidos vacía
		
		List<Pedido> pedidos = servicioPedido.recuperaPedidos();
		
		assertEquals(0, pedidos.size());
		
		//Caso 2: Recupera una lista con Pedidos guardados
		
		ArrayList<Pedido> lista = new ArrayList<>();
		
		Pedido pedido1 = new Pedido();
		pedido1.setIdPedido(1);
		pedido1.setCosto((float) 548.65);
		pedido1.setFechaLlegada(LocalDate.now());
		
		Pedido pedido2 = new Pedido();
		pedido2.setIdPedido(2);
		pedido1.setCosto((float) 348.65);
		pedido1.setFechaLlegada(LocalDate.now());
		
		lista.add(pedido1);
		lista.add(pedido2);
		
		Iterable<Pedido> listaIterable = lista;
		
		when(pedidoRepository.findAll()).thenReturn(listaIterable);
		
		pedidos = servicioPedido.recuperaPedidos();
		
		assertEquals(2, pedidos.size());
	}
	
	@Test
	void testRecuperaPedidosPeriodo() {
		//Caso 1: Recupera una lista de Pedidos vacía
		LocalDate fechaInicio = LocalDate.now().minusMonths(1).with(TemporalAdjusters.firstDayOfMonth());
		LocalDate fechaTermino = LocalDate.now().plusMonths(1).with(TemporalAdjusters.lastDayOfMonth());
		
		List<Pedido> pedidosPeriodo = servicioPedido.recuperaPedidosPeriodo(fechaInicio, fechaTermino);
		
		assertEquals(0, pedidosPeriodo.size());
		
		//Caso 2: Recupera una lista de Pedido con pedidos guardados
		
		ArrayList<Pedido> lista = new ArrayList<>();
		LocalDate fechaIni = LocalDate.now().minusMonths(1).with(TemporalAdjusters.firstDayOfMonth());
		LocalDate fechaTerm = LocalDate.now().plusMonths(1).with(TemporalAdjusters.lastDayOfMonth());
		
		Pedido pedido1 = new Pedido();
		pedido1.setIdPedido(1);
		pedido1.setCosto((float) 548.65);
		pedido1.setFechaLlegada(LocalDate.now());
		
		Pedido pedido2 = new Pedido();
		pedido2.setIdPedido(2);
		pedido2.setCosto((float) 348.65);
		pedido2.setFechaLlegada(LocalDate.now());
		
		lista.add(pedido1);
		lista.add(pedido2);
		
		when(pedidoRepository.findAllByFechaLlegadaGreaterThanAndLessThan(fechaIni, fechaTerm)).thenReturn(lista);
		
		pedidosPeriodo = servicioPedido.recuperaPedidosPeriodo(fechaIni, fechaTerm);
		
		assertEquals(2, pedidosPeriodo.size());
	}

	
	@Test
	void testCalculaGastoAcumuladoDelMes() {
		
		//Caso 1: Calcula la suma del costo de todos los pedidos del mes actual
		List<Pedido> lista = new ArrayList<>();
		Pedido pedido1 = new Pedido();
		pedido1.setCosto(10);
		pedido1.setFechaLlegada(LocalDate.now().minusMonths(1));
		
		Pedido pedido2 = new Pedido();
		pedido2.setCosto(20);
		pedido2.setFechaLlegada(LocalDate.now().minusDays(5));
		
		Pedido pedido3 = new Pedido();
		pedido3.setCosto(30);
		pedido3.setFechaLlegada(LocalDate.now().plusDays(1));
		
		Pedido pedido4 = new Pedido();
		pedido4.setCosto(40);
		pedido4.setFechaLlegada(LocalDate.now().plusMonths(1));
		
		lista.add(pedido1);
		lista.add(pedido2);
		lista.add(pedido3);
		lista.add(pedido4);
		
		float GAM = servicioPedido.calculaGastoAcumuladoDelMes(lista);
		
		assertEquals(50.0, GAM);
		
	}

	@Test
	void testValidaPedidosYVentasNoNulos() {
		
		//Caso 1: dos cadenas no nulas, debe devolver True
		List<Pedido> listaPedidos = new ArrayList<>();
		List<Venta> listaVentas = new ArrayList<>();
		
		Pedido pedido1 = new Pedido();
		pedido1.setCosto(10);
		pedido1.setFechaLlegada(LocalDate.now().minusMonths(1));
		
		Pedido pedido2 = new Pedido();
		pedido2.setCosto(20);
		pedido2.setFechaLlegada(LocalDate.now().minusDays(5));
		
		Pedido pedido3 = new Pedido();
		pedido3.setCosto(30);
		pedido3.setFechaLlegada(LocalDate.now().plusDays(1));
		
		Pedido pedido4 = new Pedido();
		pedido4.setCosto(40);
		pedido4.setFechaLlegada(LocalDate.now().plusMonths(1));
		
		listaPedidos.add(pedido1);
		listaPedidos.add(pedido2);
		listaPedidos.add(pedido3);
		listaPedidos.add(pedido4);
		
		Venta venta1 = new Venta();
		venta1.setTotal(10);
		venta1.setFecha(LocalDate.now().minusMonths(1));
		
		Venta venta2 = new Venta();
		venta2.setTotal(20);
		venta2.setFecha(LocalDate.now().minusDays(5));
		
		Venta venta3 = new Venta();
		venta3.setTotal(30);
		venta3.setFecha(LocalDate.now().plusDays(1));
		
		Venta venta4 = new Venta();
		venta4.setTotal(40);
		venta4.setFecha(LocalDate.now().plusMonths(1));
		
		listaVentas.add(venta1);
		listaVentas.add(venta2);
		listaVentas.add(venta3);
		listaVentas.add(venta4);
		
		boolean val = servicioPedido.validaPedidosYVentasNoNulos(listaPedidos, listaVentas);
		
		assertEquals(true, val);
		
		//Caso 2: Lista de pedidos no vacía y la de ventas vacía
		
		List<Venta> listaVacía = new ArrayList<>();
		
		val = servicioPedido.validaPedidosYVentasNoNulos(listaPedidos, listaVacía);
		
		assertEquals(true, val);
		
		//Caso 3: Lista de pedidos vacía y la de ventas no vacía
		
		List<Pedido> listaVaciaP = new ArrayList<>();
			
		val = servicioPedido.validaPedidosYVentasNoNulos(listaVaciaP, listaVentas);
				
		assertEquals(true, val);
		
		//Caso 4: Ambas listas vacías
		
		val = servicioPedido.validaPedidosYVentasNoNulos(listaVaciaP, listaVacía);
		
		assertEquals(false, val);
	}

	@Test
	void testCalculaBalances() {
		
		//Caso 1: dos listas no vacías, debe devolver dos objetos y 20 como balance en ambos casos
				List<Pedido> listaPedidosB = new ArrayList<>();
				List<Venta> listaVentasB = new ArrayList<>();
				
				Pedido pedido1 = new Pedido();
				pedido1.setCosto(10);;
				pedido1.setFechaLlegada(LocalDate.now());
				
				Pedido pedido2 = new Pedido();
				pedido2.setCosto(20);
				pedido2.setFechaLlegada(LocalDate.now().plusDays(1));
				
				Pedido pedido3 = new Pedido();
				pedido3.setCosto(30);
				pedido3.setFechaLlegada(LocalDate.now().plusMonths(1).plusDays(1));
				
				Pedido pedido4 = new Pedido();
				pedido4.setCosto(40);
				pedido4.setFechaLlegada(LocalDate.now().plusMonths(1));
				
				listaPedidosB.add(pedido1);
				listaPedidosB.add(pedido2);
				listaPedidosB.add(pedido3);
				listaPedidosB.add(pedido4);
				
				Venta venta1 = new Venta();
				venta1.setTotal(20);
				venta1.setFecha(LocalDate.now());
				
				Venta venta2 = new Venta();
				venta2.setTotal(30);
				venta2.setFecha(LocalDate.now().plusDays(1));
				
				Venta venta3 = new Venta();
				venta3.setTotal(40);
				venta3.setFecha(LocalDate.now().plusMonths(1).plusDays(1));
				
				Venta venta4 = new Venta();
				venta4.setTotal(50);
				venta4.setFecha(LocalDate.now().plusMonths(1));
				
				listaVentasB.add(venta1);
				listaVentasB.add(venta2);
				listaVentasB.add(venta3);
				listaVentasB.add(venta4);
				
				List<Balance> balances = servicioPedido.calculaBalances(listaPedidosB, listaVentasB, LocalDate.now().with(TemporalAdjusters.firstDayOfMonth()), LocalDate.now().plusMonths(1).with(TemporalAdjusters.lastDayOfMonth()));
				
			/*	for(Balance balance : balances) {
					System.out.println(balance.getMonto());
				}*/
				
				assertEquals(2, balances.size());
				
		//Caso 2: lista de pedidos no vacía, y la de ventas vacía debe devolver dos objetos y balances de -30 y -70 
				List<Venta> listaVentasBVacía = new ArrayList<>();
														
				List<Balance> balances2 = servicioPedido.calculaBalances(listaPedidosB, listaVentasBVacía, LocalDate.now().with(TemporalAdjusters.firstDayOfMonth()), LocalDate.now().plusMonths(1).with(TemporalAdjusters.lastDayOfMonth()));
				
				for(Balance balance : balances2) {
					System.out.println(balance.getMonto());
				}
				
				assertEquals(2, balances2.size());
				
		//Caso 3: lista de pedidos vacía, y la de ventas no vacía debe devolver dos objetos y balances de 50 y 90 
				List<Pedido> listaPedidosBVacía = new ArrayList<>();
				
				List<Balance> balances3 = servicioPedido.calculaBalances(listaPedidosBVacía, listaVentasB, LocalDate.now().with(TemporalAdjusters.firstDayOfMonth()), LocalDate.now().plusMonths(1).with(TemporalAdjusters.lastDayOfMonth()));
				
				for(Balance balance : balances3) {
					System.out.println(balance.getMonto());
				}
				
				assertEquals(2, balances3.size());
				
		//Caso 4: ambas listas vacías, deben obtenerse una lista vacía 
				
				List<Balance> balances4 = servicioPedido.calculaBalances(listaPedidosBVacía, listaVentasBVacía, LocalDate.now().with(TemporalAdjusters.firstDayOfMonth()), LocalDate.now().plusMonths(1).with(TemporalAdjusters.lastDayOfMonth()));
								
				assertEquals(0, balances4.size());
	}
}
