package mx.uam.ayd.proyecto.negocio;

import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import mx.uam.ayd.proyecto.datos.PedidoRepository;
import mx.uam.ayd.proyecto.negocio.modelo.Pedido;
import mx.uam.ayd.proyecto.negocio.modelo.Venta;

@Slf4j
@Service
public class ServicioPedido{
	private List<Salida> outs = new ArrayList<>();
	@Autowired
	private PedidoRepository pedidoRepository;
	
	public Pedido registraPedido(LocalDate fechaLlegada, float costo) {
		
		Pedido pedido = new Pedido();
		pedido.setFechaLlegada(fechaLlegada);
		pedido.setCosto(costo);
		
		pedidoRepository.save(pedido);
		
		return pedido;
	}
	
	/*
	 * No recibe ningún parámetro y regresa una lista con todos los pedidos registrados
	 */
	public List<Pedido> recuperaPedidos(){
		
		List<Pedido> pedidos = new ArrayList<>();
		
		for(Pedido pedido: pedidoRepository.findAll())
			pedidos.add(pedido);
		
		return pedidos;
	}
	
	/*
	 * Recupera la lista de Pedidos en un periodo establecido por el usuario
	 * @Param LocalDate fechaInicio dato de tipo fecha local
	 * @Param LocalDate fechaTermino dato de tipo fecha local
	 */
	public List<Pedido> recuperaPedidosPeriodo(LocalDate fechaInicio, LocalDate fechaTermino){
			
		List<Pedido> pedidosPeriodo = new ArrayList<>();
		for(Pedido pedido: pedidoRepository.findAllByFechaLlegadaGreaterThanAndLessThan(fechaInicio, fechaTermino)) {
			pedidosPeriodo.add(pedido);
		}
		return pedidosPeriodo;
	}
	
	/*
	 * Calcula el gasto llevado a cabo por los pedidos del mes
	 * @Param List<Pedidos> listaPedidos lista de Objetos de tipo Pedido
	 * Regresa un valor real.
	 */
	public float calculaGastoAcumuladoDelMes(List<Pedido> pedidos) {
		float gastoAcumuladoDelMes = 0;
		
		LocalDate inicioMes = LocalDate.now().with(TemporalAdjusters.firstDayOfMonth());
		LocalDate finDeMes = LocalDate.now().with(TemporalAdjusters.lastDayOfMonth());
		
		for(Pedido pedido: pedidos) {
			if(pedido.getFechaLlegada().isAfter(inicioMes) && pedido.getFechaLlegada().isBefore(finDeMes))
				gastoAcumuladoDelMes = gastoAcumuladoDelMes + pedido.getCosto();
		}
		return gastoAcumuladoDelMes;
	}
	
	/*
	 * Calcula el el monto mensual de los pedidos en el periodo seleccionado por el usuario
	 * @Param List<Pedido> pedidos lista de objetos de tipo Pedido
	 * @Param LocalDate fechaInicio dato de tipo fecha local
	 * @Param LocalDate fechaTermino dato de tipo fecha local
	 * Regresa una lista de Objetos de tipo Salida
	 */
	public List<Salida> calculaMontoPedidosPeriodoPorMes(List<Pedido> pedidos, LocalDate fechaInicio, LocalDate fechaTermino){
		int i = 0;
		String mes = null;
		
		while(fechaInicio.plusMonths(i).isBefore(fechaTermino)){
			float costoMes = 0;
			
			for(Pedido pedido: pedidos) {
				if(pedido.getFechaLlegada().isAfter(fechaInicio.plusMonths(i).with(TemporalAdjusters.firstDayOfMonth())) && 
					pedido.getFechaLlegada().isBefore(fechaInicio.plusMonths(i).with(TemporalAdjusters.lastDayOfMonth()))) {
					costoMes = costoMes + pedido.getCosto();
					mes = pedido.getFechaLlegada().getMonth().toString();
					log.info("CostoMes " + costoMes + " "+ "costo pedido " + pedido.getCosto() + " " + mes);
				}
			}
			outs.add(new Salida(mes, costoMes));
			i++;
		}
		return outs;
	}
	
	/*
	 * Calcula los balances mensuales entre pedidos y ventas
	 * @Param List<Pedidos> pedidos lista de Objetos de tipo Pedido
	 * @Param List<Venta> ventas lista de Objetos de tipo Venta
	 * @Param LocalDate fechaInicio dato de tipo fecha local
	 * @Param LocalDate fechaTermino dato de tipo fecha local
	 * Regresa una lista de Objetos de tipo Balance
	 */
	public List<Balance> calculaBalances(List<Pedido> pedidos, List<Venta> ventas, LocalDate fechaInicio, LocalDate fechaTermino){
		int i = 0;
		String mes = null;
		
		List<Balance> balances = new ArrayList<>();
		
		while(fechaInicio.plusMonths(i).isBefore(fechaTermino)){
			
			float costoMes = 0;
			float entradaMes = 0;
			
			for(Pedido pedido: pedidos) {
				if(pedido.getFechaLlegada().isAfter(fechaInicio.plusMonths(i).with(TemporalAdjusters.firstDayOfMonth())) && 
					pedido.getFechaLlegada().isBefore(fechaInicio.plusMonths(i).with(TemporalAdjusters.lastDayOfMonth()))) {
					costoMes = costoMes + pedido.getCosto();
					mes = pedido.getFechaLlegada().getMonth().toString();
				}
			}
			
			for(Venta venta: ventas) {
				if(venta.getFecha().isAfter(fechaInicio.plusMonths(i).with(TemporalAdjusters.firstDayOfMonth())) && 
					venta.getFecha().isBefore(fechaInicio.plusMonths(i).with(TemporalAdjusters.lastDayOfMonth()))) {
					entradaMes = entradaMes + venta.getTotal();
					if(mes == null)
						mes = venta.getFecha().getMonth().toString();
				}
			}
			if(mes != null)
				balances.add(new Balance(mes, (entradaMes-costoMes)));
			
			log.info("mes " + mes + " entrada mes " + entradaMes + " salida mes "+ costoMes + "balance " + (entradaMes-costoMes));
			i++;
		}
				
		return balances;
	}
	
	/*
	 * Valida que las listas obtenidas de productos y ventas no sean nulos
	 * @Param List<Pedido> pedidos lista de objetos de tipo Pedido
	 * @Param List<Venta> ventas lista de objetos de tipo Venta
	 * Si cualquiera de ambos no es nulo regresa true, si ambas son nulas regresa false
	 */
	public Boolean validaPedidosYVentasNoNulos(List<Pedido> pedidos, List<Venta> ventas) {
		if(pedidos.size() != 0 || ventas.size() != 0)
			return true;
					
		return false;
	}
	
	/*Clase para establecer el tipo Balance, no es un tipo de dato que se requiera almacenar, se utiliza para el llenado de
	 * la tabla Balances en la Ventana Consultar Balance 
	 */
	public class Balance{
		private String mes;
		private Float monto;
		
		public Balance(String mes, Float monto) {
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

	/*Clase para establecer el tipo Salida, no es un tipo de dato que se requiera almacenar, se utiliza para el llenado de
	 * la tabla Salidas en la Ventana Consultar Balance 
	 */
	public class Salida{
		private String mes;
		private Float monto;
		
		public Salida(String mes, Float monto) {
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
