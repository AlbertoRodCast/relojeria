package mx.uam.ayd.proyecto.presentacion.consultarPedido;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import mx.uam.ayd.proyecto.negocio.ServicioPedido;
import mx.uam.ayd.proyecto.negocio.ServicioPedidoArticulo;
import mx.uam.ayd.proyecto.negocio.modelo.Pedido;
import mx.uam.ayd.proyecto.negocio.modelo.PedidoArticulo;

@Component
public class ControlConsultarPedido {

	@Autowired
	private ServicioPedidoArticulo servicioPedidoArticulo;
	@Autowired
	private ServicioPedido servicioPedido;
	@Autowired
	private VentanaConsultarPedido ventana;
	
	/*
	 * Método que inicia la historia de Usuario ConsultarPedidos
	 */
	public void inicia() {
		List<Pedido> pedidos = servicioPedido.recuperaPedidos();
		float gAM = servicioPedido.calculaGastoAcumuladoDelMes(pedidos);
		List<PedidoArticulo> pedidoArticulos = servicioPedidoArticulo.recuperaPedidoArticulos();
		ventana.muestra(this, pedidoArticulos, gAM);
	}
}
