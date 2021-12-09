package mx.uam.ayd.proyecto.negocio.modelo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import lombok.Data;

@Entity
@Data
public class Venta {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long idVenta;
	
	float total;
	LocalDate fecha;
	float deuda;
	boolean apartado;
	Articulo articulo;

	
	@ManyToOne(targetEntity = Cliente.class, fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "idVenta")
	private  Cliente cliente;
	
	@ManyToMany(targetEntity = PagoArticulo.class, fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "idVenta")
	private final List <PagoArticulo> pagoArticulos = new ArrayList <> ();
	
	
}
