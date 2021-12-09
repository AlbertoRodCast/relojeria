package mx.uam.ayd.proyecto.negocio;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import mx.uam.ayd.proyecto.datos.ArticuloRepository;
import mx.uam.ayd.proyecto.negocio.modelo.Articulo;

/**
 * SE Encarga de la comunicaciÃ³n con su repositorio, edita datos de este.
 */
@Slf4j
@Service
public class ServicioArticulo {
	@Autowired 
	private ArticuloRepository articuloRepository;
	
	/**
	 * Recupera todos los articulos en el repository
	 * 
	 */
	
public List <Articulo> recuperaArticulos() {

		try {
			List <Articulo> articulos = new ArrayList<>();
			
			for(Articulo articulo:articuloRepository.findAll()) {
				articulos.add(articulo);
			}
			return articulos;
		}catch(Exception e) {
			return null;
		}
		
		
				
		
	}//Fin del mÃ©todo
	


/**
 * Actualiza el stock de un artÃ­culo
 * @param nombreArticulo
 * @param cantidad
 * @return
 */
public Articulo actualizaArticulo(String nombreArticulo, String cantidad) {
	
	Articulo articulo;
	
	try {
		articulo= articuloRepository.findByName(nombreArticulo);
		int cantid= Integer.parseInt(cantidad);
		articulo.setCantidadDisponible(articulo.getCantidadDisponible()-cantid);
		//hasta aqui solo hemos actualizado el objeto, falta pasarlo al repository
		articuloRepository.save(articulo);  //Para guardar en el repository
	
		return articulo;
	} catch (Exception e) {
		return null;
	}
	
}//Fin del mÃ©todo

/**
 * Actualiza los atributos (Nombre, Cantidad y Precio) 
 * del artículo según el usuario desee.
 * 
 */

public boolean ActualizaArticulo(String nombreArticulo, String cantidad, String precio ) {
	Articulo articulo;
	try {
		articulo= articuloRepository.findByName(nombreArticulo);    //Se trae del repository un artículo por su nombre
		int cantid= Integer.parseInt(cantidad);   //Convertimos de String a Entero (int)
		float preci= Float.parseFloat(precio);    //Convertimos de String a float
		articulo.setCantidadDisponible(cantid);    //le pasamos los atributos al objeto que se recupero. 
		articulo.setNombre(nombreArticulo);
		articulo.setPrecio(preci);
		/**
		 * Hasta aqui solo hemos actualizado
		 * los atributos de artÃ­culo, falta pasarlo al repository
		 */
		articuloRepository.save(articulo);   //Se actualiza en el Repository
		
		return true;
		}catch(Exception e) {           //Si no se cumple lo anterior hay una exception 
		return false;
	}
}//Fin del mÃ©todo


/**
 * Borra por completo el artículo
 */

public boolean BorrarArticulo(String Nombre) {
	Articulo articulo;
	
	try {
		articulo= articuloRepository.findByName(Nombre);          //Se trae del repository un artículo por su nombre
		articulo.setNombre(Nombre);                    
		//Ahora debemos borrar del repository
		articuloRepository.delete(articulo);         //Se Borra del repository
		
		return true;
	}catch (Exception e) {     //Si no se cumple lo anterior hay una exception 
		return false;
	}
}//Fin del Método
/**
 * Aqui es para crear un nuevo Artículo.
 * @param Nombre
 * @param Cantidad
 * @param Precio
 * @return
 */
public boolean CreaArticulo(String Nombre,String Cantidad,String Precio) {
	
	//Regla de negocio: No se permite agregar dos articulos con el mismo nombre
	
	Articulo articulo;
	
	try {
		
		int cantid = Integer.parseInt(Cantidad);   //Convertimos de String a int y float
		float preci = Float.parseFloat(Precio);
		
		articulo = new Articulo();            // Creamos el objeto de la clase Articulo
		articulo.setNombre(Nombre);
		articulo.setCantidadDisponible(cantid);    // Pasmos sus atributos para ser creado.
		articulo.setPrecio(preci);
		
		articuloRepository.save(articulo);   //Para crear en el repository
		
		return true;
		
	}catch (Exception e){
		return false;
	}
	
	
	
}//Fin del método

/*
 * recupera un articulo en especifico
 */
	public Articulo recuperaArticulo(String producto) {
		try {
			return articuloRepository.findByName(producto);
		} catch (Exception e) {
			return null;
		}
		
	
	}


}//Fin de la clase
