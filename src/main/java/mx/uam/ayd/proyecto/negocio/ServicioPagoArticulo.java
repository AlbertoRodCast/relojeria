package mx.uam.ayd.proyecto.negocio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import mx.uam.ayd.proyecto.datos.PagoArticuloRepository;
import mx.uam.ayd.proyecto.negocio.modelo.PagoArticulo;

/*
 * Se encarga de la comuniciÃ³n con su repository, edita datos de este.
 */
@Slf4j
@Service

public class ServicioPagoArticulo {
	@Autowired
	private PagoArticuloRepository pagoArticuloRepository;
	
	
	public PagoArticulo creaPagoArticulo(String cantidad) {
		PagoArticulo pagoArticulo= new PagoArticulo();
		pagoArticulo.setCantidad(Integer.parseInt(cantidad));
		pagoArticuloRepository.save(pagoArticulo);
		return pagoArticulo;
	}
}
