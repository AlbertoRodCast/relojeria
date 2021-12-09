package mx.uam.ayd.proyecto.presentacion.consultarInventario;

import java.awt.EventQueue;

import javax.swing.JFrame;

import org.springframework.stereotype.Component;

import mx.uam.ayd.proyecto.negocio.modelo.Articulo;
import mx.uam.ayd.proyecto.presentacion.agregarUsuario.ControlAgregarUsuario;

import javax.swing.JTextField;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import javax.swing.JTextPane;
import java.awt.Color;

import javax.management.modelmbean.ModelMBeanOperationInfo;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

@SuppressWarnings("serial")
@Component
public class VentanaConsultarInventario {

	private JFrame frmRelojera;
	private JTextField txtProducto;
	private JTextField txtCantidad;
	private JTextField txtPrecio;
	private JTable table;
	
	ControlConsultarInventario controlConsultarInventario;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaConsultarInventario window = new VentanaConsultarInventario();
					window.frmRelojera.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public VentanaConsultarInventario() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmRelojera = new JFrame();
		frmRelojera.getContentPane().setBackground(new Color(238, 232, 170));
		frmRelojera.getContentPane().setForeground(Color.WHITE);
		frmRelojera.setTitle("RelojerÃ­a-Inventario");
		frmRelojera.setBounds(100, 100, 450, 300);
		frmRelojera.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmRelojera.getContentPane().setLayout(null);
		
		txtProducto = new JTextField();
		txtProducto.setBounds(27, 38, 86, 20);
		frmRelojera.getContentPane().add(txtProducto);
		txtProducto.setColumns(10);
		
		txtCantidad = new JTextField();
		txtCantidad.setBounds(123, 38, 86, 20);
		frmRelojera.getContentPane().add(txtCantidad);
		txtCantidad.setColumns(10);
		
		txtPrecio = new JTextField();
		txtPrecio.setBounds(222, 38, 86, 20);
		frmRelojera.getContentPane().add(txtPrecio);
		txtPrecio.setColumns(10);
		
		JButton btnNewButton = new JButton("Registrar");
		btnNewButton.setBackground(Color.GREEN);
		btnNewButton.setForeground(Color.WHITE);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton.setBounds(24, 85, 89, 23);
		frmRelojera.getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Borrar");
		btnNewButton_1.setBackground(Color.RED);
		btnNewButton_1.setForeground(Color.WHITE);
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton_1.setBounds(123, 85, 89, 23);
		frmRelojera.getContentPane().add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Actualizar");
		btnNewButton_2.setBackground(Color.BLUE);
		btnNewButton_2.setForeground(Color.WHITE);
		btnNewButton_2.setBounds(222, 85, 100, 23);
		frmRelojera.getContentPane().add(btnNewButton_2);
		
		JLabel lblNewLabel = new JLabel("Producto");
		lblNewLabel.setBounds(46, 23, 67, 14);
		frmRelojera.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Cantidad");
		lblNewLabel_1.setBounds(143, 23, 66, 14);
		frmRelojera.getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Precio");
		lblNewLabel_2.setBounds(239, 23, 46, 14);
		frmRelojera.getContentPane().add(lblNewLabel_2);
		
		table = new JTable();
		table.setToolTipText("");
		table.setBackground(new Color(255, 255, 255));
		table.setBounds(10, 119, 414, 131);
		frmRelojera.getContentPane().add(table);
	}


	/**
	 * Se encarga de inicializar los datos y mostrar la 
	 * ventana de Inventario.
	 *
	 * */

	public void muestra(ControlConsultarInventario control , List<Articulo> articulos) {
		limpiaCampos();
		this.controlConsultarInventario= control;
//		DefaultTableModel tableModel = new DefaultTableModel();
//		for(Articulo articulo: articulos) {
//			tableModel.addRow(articulo.getNombre());
//		}
//		table.setModel(tableModel);
		llenaTabla(articulos);
		
		
		//setVisible(true);
	}
	
	/*
	 * llena la tabla con informacion de los artículos
	 * 
	 */
	public void llenaTabla(List<Articulo> articulos) {
		for(Articulo articulo: articulos) {
			String [] filaArticulo = {""+articulo.getIdArticulo(),articulo.getNombre(),""+ articulo.getCantidadDisponible(),""+articulo.getPrecio()};
			table.addRowSelectionInterval(0, 3);
		}
	}
	
	public void limpiaCampos() {
		txtProducto.setText("");
		txtCantidad.setText("");
		txtPrecio.setText("");
		
		
	}
	
	/**
	 * Mensajes de confirmación
	 */
	
	public void MensajeProductoActualizado() {
		JOptionPane.showMessageDialog(frmRelojera, "Producto Actualizado");
	}
	
	public void MensajeProductoBorrado() {
		JOptionPane.showMessageDialog(frmRelojera, "Producto Borrado");
	}
	
	public void MensajeProductoRegistrado() {
		JOptionPane.showMessageDialog(frmRelojera, "Producto Registrado");
	}
	
	/**
	 * Mensajes de Error
	 */
	
	public void MensajeDeErrorActualizado() {
		JOptionPane.showMessageDialog(null, "Error No se actualizo","Actualizar",JOptionPane.ERROR_MESSAGE);
	}
	
	public void MensajeDeErrorBorrado() {
		JOptionPane.showMessageDialog(null, "Error NO se Borro", "Borrar",JOptionPane.ERROR_MESSAGE);
	}
	
	public void MensajeDeErrorRegistrado() {
		JOptionPane.showMessageDialog(null, "Error NO se Registro", "Registrar", JOptionPane.ERROR_MESSAGE);
	}
}