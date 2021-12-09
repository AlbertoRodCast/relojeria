package mx.uam.ayd.proyecto.negocio.modelo;

import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Data;

@Entity
@Data
public class Reparacion {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long idReparacion;
	
	float total;
	float deuda;
	String falla;
	LocalDate fechaEntrega;
	String estado;
	String producto;
	
	
	@ManyToOne(targetEntity = Cliente.class, fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "idReparacion")
	private final Cliente cliente = new Cliente() ;
	
	@ManyToOne(targetEntity = Articulo.class, fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "idReparacion")
	private final  Articulo articulo = new Articulo();
}
