package mx.uam.ayd.proyecto.presentacion.registrarVenta;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.springframework.stereotype.Component;

import mx.uam.ayd.proyecto.negocio.modelo.Articulo;
import mx.uam.ayd.proyecto.negocio.modelo.Venta;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.Color;
import javax.swing.JSpinner;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
@Component
public class VistaRegistrarVenta extends JFrame {

	private JPanel contentPane;
	private DefaultTableModel modelo;
	
	
	private JTextField txtPrecio;
	private JTextField txtExistencias;
	private JTextField txtTotal;
	private JTextField txtNombreCliente;
	private JTextField txtRecibe;
	private JComboBox<String> comboArticulos;

	ControlRegistrarVenta controlRegistrarVenta;
	private JTable table;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VistaRegistrarVenta frame = new VistaRegistrarVenta();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public VistaRegistrarVenta() {
		setTitle("Relojeria Laura");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 562, 403);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(245, 222, 179));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblProductos = new JLabel("Productos");
		lblProductos.setBounds(10, 23, 65, 14);
		contentPane.add(lblProductos);
		
		JPanel panel = new JPanel();
		panel.setBounds(80, 19, 90, 22);
		contentPane.add(panel);
		panel.setLayout(null);
		
		comboArticulos = new JComboBox();
		comboArticulos.setBounds(0, 0, 90, 22);
		panel.add(comboArticulos);
		comboArticulos.setEditable(true);
		
		JButton btnConsultar = new JButton("Consultar");
		btnConsultar.setForeground(new Color(0, 0, 0));
		btnConsultar.setBackground(new Color(255, 204, 0));
		btnConsultar.setToolTipText("Al presionarlo, le muestra la cantidad de articulos disponibles y el precio\r\n");
		btnConsultar.setBounds(199, 19, 89, 23);
		contentPane.add(btnConsultar);
		
		JLabel lblPrecio = new JLabel("Precio");
		lblPrecio.setBounds(10, 61, 65, 14);
		contentPane.add(lblPrecio);
		
		JLabel lblExistencias = new JLabel("Existencias");
		lblExistencias.setBounds(80, 61, 76, 14);
		contentPane.add(lblExistencias);
		
		txtPrecio = new JTextField();
		txtPrecio.setBounds(10, 86, 45, 20);
		contentPane.add(txtPrecio);
		txtPrecio.setColumns(10);
		
		txtExistencias = new JTextField();
		txtExistencias.setBounds(80, 86, 51, 20);
		contentPane.add(txtExistencias);
		txtExistencias.setColumns(10);
		
		JSpinner spinnerCantidad = new JSpinner();
		spinnerCantidad.setBounds(10, 140, 45, 20);
		contentPane.add(spinnerCantidad);
		
		JLabel lblCantidad = new JLabel("Cantidad");
		lblCantidad.setToolTipText("Introduzca la cantidad a comprar");
		lblCantidad.setBounds(9, 117, 46, 14);
		contentPane.add(lblCantidad);
		
		JLabel lblTotal = new JLabel("Total a pagar");
		lblTotal.setBounds(85, 117, 97, 14);
		contentPane.add(lblTotal);
		
		txtTotal = new JTextField();
		txtTotal.setEnabled(false);
		txtTotal.setBounds(84, 140, 86, 20);
		contentPane.add(txtTotal);
		txtTotal.setColumns(10);
		
		JLabel lblNombreCliente = new JLabel("Nombre del cliente");
		lblNombreCliente.setBounds(199, 117, 121, 14);
		contentPane.add(lblNombreCliente);
		
		txtNombreCliente = new JTextField();
		txtNombreCliente.setBounds(199, 140, 163, 20);
		contentPane.add(txtNombreCliente);
		txtNombreCliente.setColumns(10);
		
		JLabel lblRecibe = new JLabel("Recibe");
		lblRecibe.setBounds(397, 117, 46, 14);
		contentPane.add(lblRecibe);
		
		txtRecibe = new JTextField();
		txtRecibe.setBounds(397, 140, 86, 20);
		contentPane.add(txtRecibe);
		txtRecibe.setColumns(10);
		
		JButton btnFinalizarCompra = new JButton("FinalizarCompra");
		btnFinalizarCompra.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if((Integer)spinnerCantidad.getValue()<=0 ||(Integer)spinnerCantidad.getValue() > Integer.parseInt(txtExistencias.getText()) ) {
					JOptionPane.showMessageDialog(contentPane,"Cantidad no valida");
				}
				if(txtNombreCliente.getText().isEmpty() || Integer.parseInt(txtRecibe.getText())<0) {
					JOptionPane.showMessageDialog(contentPane,"Datos faltantes y/o pago incorrecto");
				}
				else {
					controlRegistrarVenta.registrarVenta(""+comboArticulos.getSelectedItem(), txtNombreCliente.getText(),""+spinnerCantidad.getValue(), txtRecibe.getText());
				
				}
				
			}
		});
		btnFinalizarCompra.setForeground(new Color(255, 255, 255));
		btnFinalizarCompra.setBackground(new Color(0, 153, 0));
		btnFinalizarCompra.setBounds(20, 171, 136, 23);
		contentPane.add(btnFinalizarCompra);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setForeground(new Color(255, 255, 255));
		btnCancelar.setBackground(new Color(204, 0, 0));
		btnCancelar.setBounds(199, 171, 89, 23);
		contentPane.add(btnCancelar);
		
		JLabel lblPedir = new JLabel("Â¿No se cuenta con el producto?");
		lblPedir.setBounds(10, 316, 195, 14);
		contentPane.add(lblPedir);
		
		JButton btnPedir = new JButton("Pedir");
		btnPedir.setBounds(10, 330, 89, 23);
		contentPane.add(btnPedir);
		
		String [] titulos = {"Cliente", "Producto", "Cantidad", "Total", "Deuda"};
		modelo= new DefaultTableModel(null, titulos);
		table = new JTable();
		table.setBounds(10, 203, 526, 90);
		contentPane.add(table);
		table.setModel(modelo);
	}

	/*
	 * Se encarga de inicializar los datos correspondientes y de mostrar la ventana de registrar venta
	 */
	public void muestra(ControlRegistrarVenta control , List<Venta> ventas, List<Articulo>articulos) {
		limpiaCampos();
		this.controlRegistrarVenta= control;
		DefaultComboBoxModel <String> comboBoxModel = new DefaultComboBoxModel <>();
		for(Articulo articulo: articulos) {
			comboBoxModel.addElement(articulo.getNombre());
		}
		comboArticulos.setModel(comboBoxModel);
		
		llenaTabla(ventas);
		
		
		
		setVisible(true);
		
		
	}
	
	
	
	public void muestraDialogoVentaRegistrada() {
		JOptionPane.showMessageDialog(contentPane, "Venta Registrada");
	}
	
	/*
	 * llena la tabla con informacion de las ventas realizadas
	 * 
	 */
	public void llenaTabla(List<Venta> ventas) {
		for(Venta venta: ventas) {
			String [] filaVenta = {venta.getCliente().getNombre(), venta.getArticulo().getNombre(),""+venta.getFecha(),""+venta.getTotal(),""+venta.getDeuda()};
			modelo.addRow(filaVenta);
		}
	}
	
	public void limpiaCampos() {
		txtExistencias.setText("");
		txtNombreCliente.setText("");
		txtPrecio.setText("");
		txtPrecio.setText("");
		txtRecibe.setText("");
		txtTotal.setText("");
		
	}
}
