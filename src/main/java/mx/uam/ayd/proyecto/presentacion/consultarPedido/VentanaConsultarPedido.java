package mx.uam.ayd.proyecto.presentacion.consultarPedido;

import java.util.ArrayList;
import java.util.List;

import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;

import org.springframework.stereotype.Component;

import mx.uam.ayd.proyecto.negocio.ServicioPedidoArticulo;
import mx.uam.ayd.proyecto.negocio.modelo.PedidoArticulo;

import java.awt.GridLayout;
import javax.swing.SwingConstants;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;

import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;

@SuppressWarnings("serial")
@Component
public class VentanaConsultarPedido extends JFrame{
	
	private ServicioPedidoArticulo servicioPedidoArticulo;
	
	private ControlConsultarPedido control;
	private List<PedidoArticulo> listaPedidoArticulos = new ArrayList<>();
	private JPanel panel;
	private JPanel panel1;
	private JPanel panel2;
	private JPanel panel3;
	private JLabel etiquetaPedidosHechos;
	private JLabel etiquetaGAM;
	private JTable tablaPedidos;
	private JTextField textGAM;
	private JLabel label;
	
	public VentanaConsultarPedido() {
		
		//List<PedidoArticulo> listaPedidoArticulos = servicioPedidoArticulo.recuperaPedidoArticulos();
		setTitle("Relojeria Laura");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 500, 350);
		
		panel = new JPanel();
		setContentPane(panel);
		getContentPane().setLayout(new BorderLayout());
		
		panel1 = new JPanel();
		panel2 = new JPanel();
		panel3 = new JPanel();
		panel2.setLayout(new BorderLayout());
		textGAM = new JTextField();
		textGAM.setEditable(false);
		tablaPedidos = new JTable();
		TableModel tableModel = new PedidoArticuloTableModel(listaPedidoArticulos);
		tablaPedidos.setAutoCreateRowSorter(true);
        tablaPedidos = new JTable(tableModel);
		
		GridBagLayout gbl_panel1 = new GridBagLayout();
		gbl_panel1.columnWidths = new int[] {50, 0, 0};
		gbl_panel1.rowHeights = new int[] {100, 0};
		gbl_panel1.columnWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		gbl_panel1.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		panel1.setLayout(gbl_panel1);
		panel2.add(tablaPedidos, BorderLayout.CENTER);
		panel3.setLayout(new GridLayout(0, 3, 0, 0));
		
		label = new JLabel("");
		panel3.add(label);
		etiquetaGAM = new JLabel("Gasto Acumulado del Mes");
		etiquetaGAM.setLabelFor(textGAM);
		panel3.add(etiquetaGAM);
		panel3.add(textGAM);
		
		panel.add(panel1, BorderLayout.NORTH);
		
		etiquetaPedidosHechos = new JLabel("Pedidos Hechos");
		etiquetaPedidosHechos.setFont(new Font("Tahoma", Font.PLAIN, 30));
		etiquetaPedidosHechos.setHorizontalAlignment(SwingConstants.CENTER);
		
		GridBagConstraints gbc_etiquetaPedidosHechos = new GridBagConstraints();
		gbc_etiquetaPedidosHechos.fill = GridBagConstraints.BOTH;
		gbc_etiquetaPedidosHechos.gridx = 1;
		gbc_etiquetaPedidosHechos.gridy = 0;
		panel1.add(etiquetaPedidosHechos, gbc_etiquetaPedidosHechos);
		panel.add(panel2, BorderLayout.CENTER);
		panel.add(panel3, BorderLayout.SOUTH);
		
		setVisible(true); 
	}
	/*
		public static void main(String[] args) {
			new VentanaConsultarPedido();
	}*/
	
	/*
	 * Método con el que se muestra VentanaConsultarPedido
	 * @Param ControlConsultarPedido control 
	 * @List<PedidoArticulo> pedidoArticulos lista de Objetos de tipo PedidoArticulo
	 */
	public void muestra(ControlConsultarPedido control, List<PedidoArticulo> pedidoArticulos, float gastoAcumuladoMensual) {
		
		this.control = control;
		listaPedidoArticulos = pedidoArticulos;
		textGAM.setText(gastoAcumuladoMensual + "");
	}
	
	
	/*
	 * Clase donde se ha creado el Modelo de la Tabla donde se presentan la información de los pedidos
	 */
	public class PedidoArticuloTableModel extends AbstractTableModel{
		private final static int COLUMNA_ID = 0;
		private final static int COLUMNA_PRODUCTO = 1;
		private final static int COLUMNA_CANT_ACTUAL = 2;
		private final static int COLUMNA_CANT_PEDIDA = 3;
		private final static int COLUMNA_PRECIO = 4;
		private final static int COLUMNA_FECHA = 5;
		
		private String[] nombresColumnas = {"Id", "Producto", "Cantidad\nActual", "Cantidad\nPedidda", "Precio", "Fecha"};
		private List<PedidoArticulo> listaPedidoArticulos;
		
		public PedidoArticuloTableModel(List<PedidoArticulo> listaPedidoArticulos) {
			this.listaPedidoArticulos = listaPedidoArticulos;
			
		}
		
		@Override
		public int getRowCount() {
			return listaPedidoArticulos.size();
		}

		@Override
		public int getColumnCount() {
			return nombresColumnas.length;
		}
		
		@Override
		public String getColumnName(int columnIndex) {
			return nombresColumnas[columnIndex];
		}
		
		@Override 
	    public Class<?> getColumnClass(int columnIndex) {
	        if (listaPedidoArticulos.isEmpty()) {
	            return Object.class;
	        }
	        return getValueAt(0, columnIndex).getClass();
	    }
		
		@Override
		public Object getValueAt(int rowIndex, int columnIndex) {
			PedidoArticulo pedidoArticulo = listaPedidoArticulos.get(rowIndex);
			Object returnValue = null;
			
			switch(columnIndex) {
				case COLUMNA_ID         : returnValue = pedidoArticulo.getArticulos().get(rowIndex).getIdArticulo();
										break;
				case COLUMNA_PRODUCTO   : returnValue = pedidoArticulo.getArticulos().get(rowIndex).getNombre();
										break;
				case COLUMNA_CANT_ACTUAL: returnValue = pedidoArticulo.getArticulos().get(rowIndex).getCantidadDisponible();
										break;
				case COLUMNA_CANT_PEDIDA: returnValue = pedidoArticulo.getCantidad();
										break;
				case COLUMNA_PRECIO 	: returnValue = pedidoArticulo.getArticulos().get(rowIndex).getPrecio();
										break;
				case COLUMNA_FECHA		: returnValue = pedidoArticulo.getPedidos().get(rowIndex).getFechaLlegada();
										break;
				default             	: throw new IllegalArgumentException("Indice de columna no válido"); 
			}
			return returnValue;
		}
	}
}
