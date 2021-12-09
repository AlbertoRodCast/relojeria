package mx.uam.ayd.proyecto.presentacion.registrarReparacion;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.springframework.stereotype.Component;

import mx.uam.ayd.proyecto.negocio.modelo.Articulo;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JTable;

@SuppressWarnings("serial")
@Component
public class VistaRegistrarReparacion extends JFrame {

	private JPanel contentPane;
	ControlRegistrarReparacion controlRegistrarReparacion;
	private JTextField txtProducto;
	private JTextField txtCliente;
	private JTextField txtFalla;
	private JTextField txtCosto;
	private JTextField txtFechaEntrega;
	private JTextField txtExistencia;
	private JTable tableReparaciones;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VistaRegistrarReparacion frame = new VistaRegistrarReparacion();
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
	public VistaRegistrarReparacion() {
		setTitle("Relojeria Laura");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 580, 350);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(245, 222, 179));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblProducto = new JLabel("Producto");
		lblProducto.setBounds(15, 40, 64, 14);
		contentPane.add(lblProducto);
		
		txtProducto = new JTextField();
		txtProducto.setBounds(150, 34, 86, 20);
		contentPane.add(txtProducto);
		txtProducto.setColumns(10);
		
		JLabel lblCliente = new JLabel("Nombre Cliente");
		lblCliente.setBounds(15, 76, 78, 14);
		contentPane.add(lblCliente);
		
		txtCliente = new JTextField();
		txtCliente.setBounds(150, 70, 86, 20);
		contentPane.add(txtCliente);
		txtCliente.setColumns(10);
		
		JLabel lblFalla = new JLabel("Falla");
		lblFalla.setBounds(15, 107, 46, 14);
		contentPane.add(lblFalla);
		
		txtFalla = new JTextField();
		txtFalla.setBounds(150, 101, 86, 20);
		contentPane.add(txtFalla);
		txtFalla.setColumns(10);
		
		JLabel lblCosto = new JLabel("Costo");
		lblCosto.setBounds(15, 138, 46, 14);
		contentPane.add(lblCosto);
		
		txtCosto = new JTextField();
		txtCosto.setText("");
		txtCosto.setBounds(150, 132, 86, 20);
		contentPane.add(txtCosto);
		txtCosto.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Fecha de entrega");
		lblNewLabel.setBounds(15, 170, 117, 14);
		contentPane.add(lblNewLabel);
		
		txtFechaEntrega = new JTextField();
		txtFechaEntrega.setToolTipText("La fecha debe ser escrita aÃ±o-mes-dia ej \"2010-12-31\"");
		txtFechaEntrega.setBounds(150, 167, 86, 20);
		contentPane.add(txtFechaEntrega);
		txtFechaEntrega.setColumns(10);
		
		JComboBox comboArticulos = new JComboBox();
		comboArticulos.setBounds(358, 36, 64, 22);
		contentPane.add(comboArticulos);
		
		JLabel lblRefaccion = new JLabel("Refaccion");
		lblRefaccion.setBounds(291, 40, 75, 14);
		contentPane.add(lblRefaccion);
		
		txtExistencia = new JTextField();
		txtExistencia.setBounds(358, 73, 64, 20);
		contentPane.add(txtExistencia);
		txtExistencia.setColumns(10);
		
		JLabel lblExistencia = new JLabel("Existencia");
		lblExistencia.setBounds(291, 76, 75, 14);
		contentPane.add(lblExistencia);
		
		JButton btnConsultar = new JButton("Consultar");
		btnConsultar.setBackground(new Color(255, 215, 0));
		btnConsultar.setBounds(358, 103, 89, 23);
		contentPane.add(btnConsultar);
		
		tableReparaciones = new JTable();
		tableReparaciones.setBounds(10, 212, 530, 88);
		contentPane.add(tableReparaciones);
		
		JButton btnRealizar = new JButton("Realizar");
		btnRealizar.setForeground(new Color(255, 255, 255));
		btnRealizar.setBackground(new Color(0, 128, 0));
		btnRealizar.setBounds(291, 178, 89, 23);
		contentPane.add(btnRealizar);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setForeground(new Color(255, 255, 255));
		btnCancelar.setBackground(new Color(255, 0, 0));
		btnCancelar.setBounds(390, 178, 89, 23);
		contentPane.add(btnCancelar);
	}

	public void muestra(ControlRegistrarReparacion controlRegistrarReparacion, List<Articulo> articulos) {
		this.controlRegistrarReparacion= controlRegistrarReparacion;
		setVisible(true);
		
	}
	
	public void muestraDialogoReparacionRegistrada() {
		JOptionPane.showMessageDialog(contentPane, "Reparacion Registrada");
	}
	public void limpiaCampos() {
		txtCliente.setText("");
		txtCosto.setText("");
		txtExistencia.setText("");
		txtFalla.setText("");
		txtFechaEntrega.setText("");
		txtProducto.setText("");
	}
}
