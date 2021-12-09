package mx.uam.ayd.proyecto.negocio;

import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import mx.uam.ayd.proyecto.datos.VentaRepository;
import mx.uam.ayd.proyecto.negocio.modelo.Articulo;
import mx.uam.ayd.proyecto.negocio.modelo.Cliente;
import mx.uam.ayd.proyecto.negocio.modelo.PagoArticulo;
import mx.uam.ayd.proyecto.negocio.modelo.Venta;

/*
 * se encarga de la comunicaciÃ³n con el repository para editar datos de este.
 */


@Slf4j
@Service
public class ServicioVenta {

	@Autowired
	private VentaRepository ventaRepository;
	
	private List<Entrada> ins = new ArrayList<>();
	
	/*
	 * Registra una venta nueva
	 */
	public boolean registrarVenta(Venta venta) {
		try {
			ventaRepository.save(venta);
			return true;
		} catch (Exception e) {
			System.out.println("error al guardar");
			return false;
		}
		
	}
	
	/*
	 * recupera todas las ventas registradas
	 * @return
	 */
	public List<Venta> dameVentas(){
		
		try {
			List<Venta> ventas = new ArrayList<>();
			for(Venta venta : ventaRepository.findAll()) {
				ventas.add(venta);
			}
			return ventas;
		} catch (Exception e) {
			return null;
		}
		
		
	}
	
	
	/*
	 * busca las ventas de un articulo en especifico
	 */
	public List<Venta> buscaVentas(Articulo articulo){
		try {
			List<Venta> ventas = new ArrayList<>();
		
			for(Venta venta: ventaRepository.findByArticulo(articulo)) {
				ventas.add(venta);
			}
			return ventas;
		} catch (Exception e) {
			return null;
		}
		
	}
	
	/*
	 * crea una nueva instancia de venta. No la registra en la BD
	 */
	
	public Venta CreaVenta(Cliente cliente, PagoArticulo pagoArticulo, Articulo articulo, String pago) {
		float total = articulo.getPrecio()*pagoArticulo.getCantidad();
		float deuda= total - Float.parseFloat(pago);
		Venta venta = new Venta();
		venta.setArticulo(articulo);
		venta.setFecha(LocalDate.now());
		venta.setTotal(total);
		venta.setDeuda(deuda);
		
		return venta;
		
	}
	
	/*
	 * actualiza los datos de una venta
	 */
	
	public Boolean actualizaDatos(Articulo articulo,Cliente cliente, LocalDate fecha, float total, float deuda ){
		try {
			Venta venta = ventaRepository.findByClienteAndFecha(cliente, fecha);
			
			venta.setArticulo(articulo);
			venta.setTotal(total);
			venta.setDeuda(deuda);
			//ya se actualizÃ³ el objeto, hay que actualizar ahora la base
			ventaRepository. save(venta);	
			 return true;
		} catch (Exception e) {
			return false;
		}	
		
	}//fin actualiza datos
	
	
	public Venta buscaVenta(Cliente cliente, LocalDate fecha) {
		try {
			return ventaRepository.findByClienteAndFecha(cliente, fecha);
		} catch (Exception e) {
			return null;
		}
		
	}
		
	//Recupera la lista de Pedidos en un periodo establecido por el usuario
		public List<Venta> recuperaVentasPeriodo(LocalDate fechaInicio, LocalDate fechaTermino){
			List<Venta> ventas = new ArrayList<>();
			
			for(Venta venta: ventaRepository.findAllByFechaDateBetween(fechaInicio, fechaTermino)) 
				ventas.add(venta);
			
			return ventas;
		}
		
		//Calcula el Monto de las Ventas Mensuales dentro del Periodo seleccionado
		public List<Entrada> calculaMontoVentasPeriodoPorMes(List<Venta> ventas, LocalDate fechaInicio, LocalDate fechaTermino){
			int i = 0;
			String mes = null;
			
			while(fechaInicio.plusMonths(i).isBefore(fechaTermino)){
				float entradaMes = 0;
				
				for(Venta venta: ventas) {
					if(venta.getFecha().isAfter(fechaInicio.plusMonths(i).with(TemporalAdjusters.firstDayOfMonth())) && 
						venta.getFecha().isBefore(fechaInicio.plusMonths(i).with(TemporalAdjusters.lastDayOfMonth()))) {
						entradaMes = entradaMes + venta.getTotal();
						mes = venta.getFecha().getMonth().toString();
						log.info("EntradaMes " + entradaMes + " "+ "costo venta " + venta.getTotal() + " " + mes);
					}
				}
				ins.add(new Entrada(mes, entradaMes));
				i++;
			}
			return ins;
		}
	
	/*
	 * Clase para establece el tipo de dato Entrada no requieren ser almacenados, los objetos serán utilizados para la tabla Entradas
	 * de la VentanaCOnsultarBalance	
	 */
	public class Entrada{
			private String mes;
			private Float monto;
			
			public Entrada(String mes, Float monto) {
				this.mes = mes;
				this.monto = monto;
			}

			public String getMes() {
				return mes;
			}

			public void setMes(String mes) {
				this.mes = mes;
			}

			public Float getMonto() {
				return monto;
			}

			public void setMonto(Float monto) {
				this.monto = monto;
			}
		}
}
