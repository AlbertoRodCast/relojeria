package mx.uam.ayd.proyecto.presentacion.consultarVenta;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.springframework.stereotype.Component;

import mx.uam.ayd.proyecto.negocio.modelo.Venta;
import java.awt.Color;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;
@SuppressWarnings("serial")
@Component
public class VistaConsultarVenta extends JFrame {

	private JPanel contentPane;
	
	
	private ControlConsultarVentas controlConsultarVentas;
	private JTable table;
	private JTextField txtNombre;
	private JTextField txtProducto;
	private JTextField txtCantidad;
	private JTextField txtTotal;
	private JTextField txtDeuda;
	private JTextField txtFecha;
	private JLabel lblVentasRealizadas;
	private JButton btnActualiza;
	private JButton btnBuscar;
	private JButton btnCancelar;
	private JButton btnInicio;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VistaConsultarVenta frame = new VistaConsultarVenta();
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
	public VistaConsultarVenta() {
		setTitle("Reojeria Laura");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 539, 350);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(245, 222, 179));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		table = new JTable();
		table.setBounds(10, 145, 503, 166);
		contentPane.add(table);
		
		txtNombre = new JTextField();
		txtNombre.setEnabled(false);
		txtNombre.setBounds(10, 114, 86, 20);
		contentPane.add(txtNombre);
		txtNombre.setColumns(10);
		
		txtProducto = new JTextField();
		txtProducto.setBounds(118, 114, 72, 20);
		contentPane.add(txtProducto);
		txtProducto.setColumns(10);
		
		txtCantidad = new JTextField();
		txtCantidad.setEnabled(false);
		txtCantidad.setBounds(200, 114, 54, 20);
		contentPane.add(txtCantidad);
		txtCantidad.setColumns(10);
		
		txtTotal = new JTextField();
		txtTotal.setBounds(264, 114, 54, 20);
		contentPane.add(txtTotal);
		txtTotal.setColumns(10);
		
		txtDeuda = new JTextField();
		txtDeuda.setBounds(328, 114, 72, 20);
		contentPane.add(txtDeuda);
		txtDeuda.setColumns(10);
		
		txtFecha = new JTextField();
		txtFecha.setEnabled(false);
		txtFecha.setBounds(410, 114, 86, 20);
		contentPane.add(txtFecha);
		txtFecha.setColumns(10);
		
		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setBounds(14, 89, 46, 14);
		contentPane.add(lblNombre);
		
		JLabel lblProducto = new JLabel("Producto");
		lblProducto.setBounds(119, 89, 46, 14);
		contentPane.add(lblProducto);
		
		JLabel lblCantidad = new JLabel("Cantidad");
		lblCantidad.setBounds(200, 89, 46, 14);
		contentPane.add(lblCantidad);
		
		JLabel lblTotal = new JLabel("Total");
		lblTotal.setBounds(264, 89, 46, 14);
		contentPane.add(lblTotal);
		
		JLabel lblDeuda = new JLabel("Deuda");
		lblDeuda.setBounds(328, 89, 46, 14);
		contentPane.add(lblDeuda);
		
		JLabel lblFecha = new JLabel("Fecha");
		lblFecha.setBounds(410, 89, 46, 14);
		contentPane.add(lblFecha);
		
		lblVentasRealizadas = new JLabel("Ventas Realizadas");
		lblVentasRealizadas.setBounds(14, 11, 133, 23);
		contentPane.add(lblVentasRealizadas);
		
		btnActualiza = new JButton("Actualiza");
		btnActualiza.setForeground(new Color(255, 250, 250));
		btnActualiza.setBackground(new Color(0, 0, 205));
		btnActualiza.setBounds(296, 55, 89, 23);
		contentPane.add(btnActualiza);
		
		btnBuscar = new JButton("Buscar");
		btnBuscar.setForeground(new Color(255, 250, 250));
		btnBuscar.setBackground(new Color(0, 128, 0));
		btnBuscar.setBounds(183, 55, 89, 23);
		contentPane.add(btnBuscar);
		
		btnCancelar = new JButton("Cancelar");
		btnCancelar.setForeground(new Color(255, 255, 255));
		btnCancelar.setBackground(new Color(255, 0, 0));
		btnCancelar.setBounds(410, 55, 89, 23);
		contentPane.add(btnCancelar);
		
		btnInicio = new JButton("Inicio");
		btnInicio.setBackground(new Color(255, 215, 0));
		btnInicio.setBounds(447, 9, 66, 19);
		contentPane.add(btnInicio);
		
	}
	
	
	/*
	 * Se encarga de mostrar la vista para consultar las ventas
	 * 
	 */
	public void muestra(ControlConsultarVentas control, List<Venta> ventas) {
		this.controlConsultarVentas=control;
		//aqui debe llevar el cÃ³digo para llenar una tabla con los datos correspondientes
		
		setVisible(true);
		
		
	}
	public void muestraDialogoBusqueda() {
		JOptionPane.showMessageDialog(contentPane, "Busqueda realizada");
	}
	
	
	/*
	 * Se va a encargar de llenar la tabla con los datos de la busqueda realizada
	 */
	public void muestraVentas() {
		
	}
	
	

}
