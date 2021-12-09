package mx.uam.ayd.proyecto.presentacion.registrarVenta;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import mx.uam.ayd.proyecto.negocio.ServicioArticulo;
import mx.uam.ayd.proyecto.negocio.ServicioCliente;
import mx.uam.ayd.proyecto.negocio.ServicioPagoArticulo;
import mx.uam.ayd.proyecto.negocio.ServicioVenta;
import mx.uam.ayd.proyecto.negocio.modelo.Articulo;
import mx.uam.ayd.proyecto.negocio.modelo.Cliente;
import mx.uam.ayd.proyecto.negocio.modelo.PagoArticulo;
import mx.uam.ayd.proyecto.negocio.modelo.Venta;

/*
 * Esta clase lleva el flujo de control de la ventana de registrar ventas
 * 
 * @author DanielAtonal
 */

@Component
public class ControlRegistrarVenta {
	@Autowired
	private ServicioVenta servicioVenta;
	
	
	@Autowired
	private ServicioArticulo servicioArticulo;
	
	@Autowired
	private ServicioPagoArticulo servicioPagoArticulo;
	
	@Autowired
	private ServicioCliente servicioCliente;
	
	
	private VistaRegistrarVenta vistaRegistrarVenta;
	
	
	//Inicia el proceso para mostrar la ventana de registrar Ventas
	public void inicia() {
		List <Venta> ventas= servicioVenta.dameVentas();
		List <Articulo> articulos = servicioArticulo.recuperaArticulos();
		
		vistaRegistrarVenta.muestra(this , ventas, articulos);
	}
	
	
	/*
	 * Se encarga de registrar la nueva venta y actualizar el articulo
	 * Si el cliente no existe crea uno 
	 */
	public void registrarVenta(String nombreArticulo, String nombreCliente, String cantidad, String pago) {
		Articulo articulo = servicioArticulo.actualizaArticulo(nombreArticulo, cantidad);
		PagoArticulo pagoArticulo = servicioPagoArticulo.creaPagoArticulo(cantidad);
		Cliente cliente = servicioCliente.recuperaCliente(nombreCliente);
		if(cliente==null) {
			cliente= servicioCliente.CreaCliente(nombreCliente);
		}
		Venta venta =servicioVenta.CreaVenta(cliente, pagoArticulo, articulo, pago);
		Boolean agregado = servicioVenta.registrarVenta(venta);
		vistaRegistrarVenta.muestraDialogoVentaRegistrada();
		if(agregado) {
			//muestra la ventana de venta registrada
			vistaRegistrarVenta.muestraDialogoVentaRegistrada();
		}
		else {
			//muestra dialogo de error
		}
		
	}
	
}
