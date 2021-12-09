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
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;


import lombok.Data;

@Entity
@Data
public class PagoArticulo {


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long idPagoArticulo;
	
	int cantidad;
	
	@ManyToMany(targetEntity = Venta.class, fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "idPagoArticulo")
	private final List <Venta> ventas = new ArrayList <> ();
	
	//para relaciones muchos a uno solo se recibe un objeto del otro tipo 
	@ManyToOne(targetEntity = Articulo.class, fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "idPagoArticulo")
	private final Articulo articulo= new Articulo() ;
	
	
}

