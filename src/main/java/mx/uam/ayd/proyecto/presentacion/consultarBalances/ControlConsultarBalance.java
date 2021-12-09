package mx.uam.ayd.proyecto.presentacion.consultarBalances;

import java.time.LocalDate;
import java.util.List;

import javax.swing.JOptionPane;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import mx.uam.ayd.proyecto.negocio.ServicioPedido;
import mx.uam.ayd.proyecto.negocio.ServicioPedido.Balance;
import mx.uam.ayd.proyecto.negocio.ServicioPedido.Salida;
import mx.uam.ayd.proyecto.negocio.ServicioVenta;
import mx.uam.ayd.proyecto.negocio.ServicioVenta.Entrada;
import mx.uam.ayd.proyecto.negocio.modelo.Pedido;
import mx.uam.ayd.proyecto.negocio.modelo.Venta;

@Component
public class ControlConsultarBalance {
	
	@Autowired
	private ServicioPedido servicioPedido;
	
	@Autowired
	private ServicioVenta servicioVenta;
	
	@Autowired
	private VentanaConsultarBalance ventana;
	
	private List<Pedido> pedidos;
	private List<Venta> ventas;
	private List<Balance> balances;
	private List<Salida> salidasMensuales;
	private List<Entrada> entradasMensuales;
	
	public void Inicia() {
		ventana.muestra(this);
	}
	
	public void recuperaDatos(LocalDate fechaInicio, LocalDate fechaTermino) {

		try {
			pedidos = servicioPedido.recuperaPedidosPeriodo(fechaInicio, fechaTermino);
			ventas = servicioVenta.recuperaVentasPeriodo(fechaInicio, fechaTermino);
		}catch(Exception e) {
			
		}
		if(servicioPedido.validaPedidosYVentasNoNulos(pedidos, ventas) == true) {
			salidasMensuales = servicioPedido.calculaMontoPedidosPeriodoPorMes(pedidos, fechaInicio, fechaTermino);
			entradasMensuales = servicioVenta.calculaMontoVentasPeriodoPorMes(ventas, fechaInicio, fechaTermino);
			balances = servicioPedido.calculaBalances(pedidos, ventas, fechaInicio, fechaTermino);
			ventana.muestraDatos(salidasMensuales, entradasMensuales, balances);
		}
		else {
			JOptionPane.showMessageDialog(null, "El periodo seleccionado, no contiene datos, por favor seleccione nuevamente");
		}
	}
}
