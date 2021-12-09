package mx.uam.ayd.proyecto.presentacion.consultarInventario;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import mx.uam.ayd.proyecto.negocio.ServicioArticulo;
import mx.uam.ayd.proyecto.negocio.modelo.Articulo;
import mx.uam.ayd.proyecto.negocio.modelo.Grupo;


/**
* MÃ³dulo de control para la historia de usuario ConsultarInventario
*
**/
@Component
public class ControlConsultarInventario {
	
	@Autowired
	private ServicioArticulo servicioArticulo;
	
	@Autowired
	private VentanaConsultarInventario ventana;
	
	
	
	/**
	 * Inicia la Historia de Usuario
	 */
	
public void inicia() {
		
		List <Articulo> articulos = servicioArticulo.recuperaArticulos();
		
		ventana.muestra(this, articulos);
		
	}

public void ActualizaArticulo(String Nombre, String Cantidad, String Precio) {
	
	 Boolean actualiza = servicioArticulo.ActualizaArticulo(Nombre, Cantidad, Precio);  //Manda a llamar al método de servicio Articulo
	 ventana.MensajeProductoActualizado();                                         // Manda mensaje de producto Actualizado
	 
	 if(actualiza) {
		 //Muestra la ventana del producto actualizado
		 
		 ventana.MensajeProductoActualizado();
	 }
	 else {
		 ventana.MensajeDeErrorActualizado();
	 }
	 
}

public void BorrarArticulo(String Nombre) {
	
	 Boolean borra = servicioArticulo.BorrarArticulo(Nombre);     //Manda a llamar al método de servicio articulo
	 ventana.MensajeProductoBorrado();                             //Manda mensaje de producto borrado
	 
	 if(borra) {
		 //Muestra la ventana de ProductoBorrado
		 
		 ventana.MensajeProductoBorrado();
	 }
	 else {
		 ventana.MensajeDeErrorBorrado();
	 }
	 
}


public void CreaArticulo(String Nombre, String Cantidad, String Precio ) {
	
	Boolean crea = servicioArticulo.CreaArticulo(Nombre, Cantidad, Precio);       //Manda a llamar al método de servicio articulo
	ventana.MensajeProductoRegistrado();
	
	if(crea) {
		//Muestra la ventana de Producto Actualizado
		ventana.MensajeProductoActualizado();
	}
	else {
		ventana.MensajeDeErrorRegistrado();
	}
	
}

}
