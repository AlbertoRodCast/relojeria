package mx.uam.ayd.proyecto.negocio.modelo;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import lombok.Data;
@Entity
@Data

public class Articulo {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long idArticulo;
	
	String nombre;
	int cantidadDisponible;
	float precio;
	
	
	@OneToMany(targetEntity = PagoArticulo.class, fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "idArticulo")
	private final List <PagoArticulo> pagoArticulos = new ArrayList <> ();
	
	
	@OneToMany(targetEntity = Reparacion.class, fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "idArticulo")
	private final List <Reparacion> reparaciones = new ArrayList <> ();
	
	@OneToMany(targetEntity = PedidoArticulo.class, fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "idArticulo")
	private final List <PedidoArticulo> pedidoArticulos = new ArrayList <> ();
	
	
}
