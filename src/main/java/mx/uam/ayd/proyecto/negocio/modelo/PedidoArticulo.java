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
public class PedidoArticulo{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long idPedidoArticulo;
	
	int cantidad;
	
	@ManyToOne(targetEntity = Articulo.class, fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "idPedidoArticulo")
	private final List <Articulo> Articulos = new ArrayList <> ();
	
	@ManyToMany(targetEntity = Pedido.class, fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "idPedidoArticulo")
	private final List <Pedido> pedidos = new ArrayList <> ();

}
