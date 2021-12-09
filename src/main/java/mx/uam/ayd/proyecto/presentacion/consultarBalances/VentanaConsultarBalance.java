package mx.uam.ayd.proyecto.presentacion.consultarBalances;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JComboBox;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import javax.swing.JTable;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

import org.springframework.stereotype.Component;

import mx.uam.ayd.proyecto.negocio.ServicioPedido.Balance;
import mx.uam.ayd.proyecto.negocio.ServicioPedido.Salida;
import mx.uam.ayd.proyecto.negocio.ServicioVenta.Entrada;

import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.JScrollPane;

import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.border.TitledBorder;
import javax.swing.border.EtchedBorder;
import java.awt.Color;
import java.awt.Font;

@SuppressWarnings("serial")
@Component
public class VentanaConsultarBalance extends JFrame{

	private JFrame frame;
	private JTable tSalidas;
	private JTable tEntradas;
	private JTable tBalance;
	private JPanel panel;
	private JButton btnConsultar;
	private JButton btnLimpiar;
	private JComboBox cBFechaTermino;
	private JComboBox cBFechaInicio;
	private JLabel lblNewLabel;
	private JLabel lblNewLabel_1;
	private JLabel lblNewLabel_2;
	private LocalDate fechaActual;
	private LocalDate fechaInicio;
	private LocalDate fechaTermino;
	private int longitudPeriodo;
	private ControlConsultarBalance control;
	private List<Salida> outs;
	private TMSalida modeloS;
	private List<Entrada> ins;
	private TMEntrada modeloE;
	private List<Balance> balances;
	private TMBalance modeloB;
	private JPanel panel_1;
		
	/**
	 * Launch the application.
	*/
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaConsultarBalance window = new VentanaConsultarBalance();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public VentanaConsultarBalance() {
				
		frame = new JFrame();
		frame.setBounds(100, 100, 800, 350);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] {30, 30, 30};
		gridBagLayout.rowHeights = new int[] {30, 50, 30, 40, 20, 40, 20, 40, 20};
		gridBagLayout.columnWeights = new double[]{0.0, 1.0};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		frame.getContentPane().setLayout(gridBagLayout);
		
		panel = new JPanel();
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.insets = new Insets(0, 0, 5, 5);
		gbc_panel.gridx = 1;
		gbc_panel.gridy = 1;
		frame.getContentPane().add(panel, gbc_panel);
		panel.setLayout(new GridLayout(1, 1, 30, 10));
		
		panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Periodo de Consulta", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel.add(panel_1);
		GridBagLayout gbl_panel_1 = new GridBagLayout();
		gbl_panel_1.columnWidths = new int[] {100, 30, 100, 200, 100, 30, 100, 0};
		gbl_panel_1.rowHeights = new int[]{23, 0};
		gbl_panel_1.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_panel_1.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		panel_1.setLayout(gbl_panel_1);
		
		cBFechaInicio = new JComboBox();
		GridBagConstraints gbc_cBFechaInicio = new GridBagConstraints();
		gbc_cBFechaInicio.fill = GridBagConstraints.BOTH;
		gbc_cBFechaInicio.insets = new Insets(0, 0, 0, 5);
		gbc_cBFechaInicio.gridx = 0;
		gbc_cBFechaInicio.gridy = 0;
		panel_1.add(cBFechaInicio, gbc_cBFechaInicio);
		cBFechaInicio.addItem("Fecha Inicio");
		
		for(int i = 0; i < 12; i++) {
			cBFechaInicio.addItem(fechaActual.now().with(TemporalAdjusters.firstDayOfMonth()).minusMonths(i));
		}
		
		cBFechaTermino = new JComboBox();
		GridBagConstraints gbc_cBFechaTermino = new GridBagConstraints();
		gbc_cBFechaTermino.fill = GridBagConstraints.BOTH;
		gbc_cBFechaTermino.insets = new Insets(0, 0, 0, 5);
		gbc_cBFechaTermino.gridx = 2;
		gbc_cBFechaTermino.gridy = 0;
		panel_1.add(cBFechaTermino, gbc_cBFechaTermino);
		cBFechaTermino.addItem("Fecha Termino");
		
		for(int i = 0; i < 12; i++) {
			cBFechaTermino.addItem(fechaActual.now().with(TemporalAdjusters.lastDayOfMonth()).minusMonths(i));
		}
		
		btnConsultar = new JButton("Consultar");
		btnConsultar.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnConsultar.setForeground(Color.WHITE);
		btnConsultar.setBackground(new Color(65, 105, 225));
		GridBagConstraints gbc_btnConsultar = new GridBagConstraints();
		gbc_btnConsultar.fill = GridBagConstraints.BOTH;
		gbc_btnConsultar.insets = new Insets(0, 0, 0, 5);
		gbc_btnConsultar.gridx = 4;
		gbc_btnConsultar.gridy = 0;
		panel_1.add(btnConsultar, gbc_btnConsultar);
		
		btnConsultar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean	fechasValidas = false;
				if(e.getSource() == btnConsultar) {
					try {
					fechaInicio = (LocalDate)cBFechaInicio.getSelectedItem();
					fechaTermino = (LocalDate)cBFechaTermino.getSelectedItem();
					fechasValidas = validaFechas(fechaInicio, fechaTermino);
					}catch(java.lang.ClassCastException exc) {
						JOptionPane.showMessageDialog(null, "Por favor seleccione fechas validas");
					}
					if(fechasValidas == true) {
						control = new ControlConsultarBalance();
						control.recuperaDatos(fechaInicio, fechaTermino);
					}
				}
			}
		});
		
		
		btnLimpiar = new JButton("Limpiar");
		btnLimpiar.setForeground(Color.WHITE);
		btnLimpiar.setBackground(new Color(255, 0, 0));
		GridBagConstraints gbc_btnLimpiar = new GridBagConstraints();
		gbc_btnLimpiar.fill = GridBagConstraints.BOTH;
		gbc_btnLimpiar.gridx = 6;
		gbc_btnLimpiar.gridy = 0;
		panel_1.add(btnLimpiar, gbc_btnLimpiar);
		btnLimpiar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(e.getSource() == btnLimpiar) {
					cBFechaInicio.setSelectedItem("Fecha Inicio");
					cBFechaTermino.setSelectedItem("Fecha Termino");
				}
			}
		});
		
		lblNewLabel = new JLabel("Salidas");
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.anchor = GridBagConstraints.WEST;
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.gridx = 1;
		gbc_lblNewLabel.gridy = 2;
		frame.getContentPane().add(lblNewLabel, gbc_lblNewLabel);
		
		tSalidas = new JTable();
		modeloS = new TMSalida(outs);
		tSalidas.setModel(modeloS);
		GridBagConstraints gbc_tSalidas = new GridBagConstraints();
		//gbc_salidas.insets = new Insets(0, 0, 1, 0);
		gbc_tSalidas.fill = GridBagConstraints.BOTH;
		gbc_tSalidas.gridx = 1;
		gbc_tSalidas.gridy = 3;
		frame.getContentPane().add(new JScrollPane(tSalidas), gbc_tSalidas);
		
		lblNewLabel_1 = new JLabel("Entradas");
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.anchor = GridBagConstraints.WEST;
		gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_1.gridx = 1;
		gbc_lblNewLabel_1.gridy = 4;
		frame.getContentPane().add(lblNewLabel_1, gbc_lblNewLabel_1);
		
		tEntradas = new JTable();
		modeloE = new TMEntrada(ins);
		tEntradas.setModel(modeloE);
		GridBagConstraints gbc_tEntradas = new GridBagConstraints();
		//gbc_entradas.insets = new Insets(0, 0, 5, 5);
		gbc_tEntradas.fill = GridBagConstraints.BOTH;
		gbc_tEntradas.gridx = 1;
		gbc_tEntradas.gridy = 5;
		frame.getContentPane().add(new JScrollPane(tEntradas), gbc_tEntradas);
		
		lblNewLabel_2 = new JLabel("Balance");
		GridBagConstraints gbc_lblNewLabel_2 = new GridBagConstraints();
		gbc_lblNewLabel_2.anchor = GridBagConstraints.WEST;
		gbc_lblNewLabel_2.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_2.gridx = 1;
		gbc_lblNewLabel_2.gridy = 6;
		frame.getContentPane().add(lblNewLabel_2, gbc_lblNewLabel_2);
		
		tBalance = new JTable();
		modeloB = new TMBalance(balances);
		tBalance.setModel(modeloB);
		GridBagConstraints gbc_tBalance = new GridBagConstraints();
		//gbc_balance.insets = new Insets(0, 0, 5, 5);
		gbc_tBalance.fill = GridBagConstraints.BOTH;
		gbc_tBalance.gridx = 1;
		gbc_tBalance.gridy = 7;
		frame.getContentPane().add(new JScrollPane(tBalance), gbc_tBalance);
	}

	public void muestra(ControlConsultarBalance control) {
		this.control = control;	
	}
	
	//Método que muestra los datos recuperados en las tablas de la interfaz de usuario
	public void muestraDatos(List<Salida> listaSalidas, List<Entrada> listaEntradas, List<Balance> listaBalances) {
		
		outs = listaSalidas;
		ins = listaEntradas;
		balances = listaBalances;
		
		modeloS = new TMSalida(outs);
		tSalidas.setModel(modeloS);
		
		modeloE = new TMEntrada(ins);
		tEntradas.setModel(modeloE);
		
		modeloB = new TMBalance(balances);
		tBalance.setModel(modeloB);
	}
	
	//Método que valida que las fechas se hayan introducido en el orden correcto
	public boolean validaFechas(LocalDate fechaInicio, LocalDate fechaTermino) {
		longitudPeriodo = (int) ChronoUnit.MONTHS.between(fechaInicio.withDayOfMonth(1), fechaTermino.withDayOfMonth(1)) + 1;
		if(longitudPeriodo <= 0) {
			JOptionPane.showMessageDialog(null, "Fechas en orden incorrecto. Por favor seleccione nuevamente");
			return false;
		}
		return true;
	}

/*	//Método que controla las acciones realizadas al activar los botones de la interfaz de usuario
	@Override
	public void actionPerformed(ActionEvent e) {
		boolean	fechasValidas = false;
		if(e.getSource() == btnConsultar) {
			try {
			fechaInicio = (LocalDate)cBFechaInicio.getSelectedItem();
			fechaTermino = (LocalDate)cBFechaTermino.getSelectedItem();
			fechasValidas = validaFechas(fechaInicio, fechaTermino);
			}catch(java.lang.ClassCastException exc) {
				JOptionPane.showMessageDialog(null, "Por favor seleccione fechas validas");
			}
			if(fechasValidas == true) {
				control.recuperaDatos(fechaInicio, fechaTermino);
			}
		}else {
			cBFechaInicio.setSelectedItem("Fecha Inicio");
			cBFechaTermino.setSelectedItem("Fecha Termino");
		}
	}*/
	
		
	public class TMSalida implements TableModel{
		
		private List<Salida> outs;
		
		public TMSalida(List<Salida> lista) {
			outs = lista;
		}

		@Override
		public int getRowCount() {
			// TODO Auto-generated method stub
			return 1;
		}

		@Override
		public int getColumnCount() {
			if(outs != null)
				return outs.size();
			else
				return 6;
		}

		@Override
		public String getColumnName(int columnIndex) {
			String titulo = null;
			if(outs != null)
				titulo = outs.get(columnIndex).getMes();
			else
				titulo = LocalDate.now().format(DateTimeFormatter.ofPattern("MMM uuuu")).toString();
			return titulo;
		}

		@Override
		public Class<?> getColumnClass(int columnIndex) {
			return Float.class;
		}

		@Override
		public boolean isCellEditable(int rowIndex, int columnIndex) {
			return false;
		}

		@Override
		public Object getValueAt(int rowIndex, int columnIndex) {
			Float monto = null;
			if(outs != null)
				monto = outs.get(columnIndex).getMonto();
			else
				monto = null;
			return monto;
		}

		@Override
		public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void addTableModelListener(TableModelListener l) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void removeTableModelListener(TableModelListener l) {
			// TODO Auto-generated method stub
			
		}
		
	}
	
	public class TMEntrada implements TableModel{
		
		private List<Entrada> ins;
		
		public TMEntrada(List<Entrada> lista) {
			ins = lista;
		}

		@Override
		public int getRowCount() {
			// TODO Auto-generated method stub
			return 1;
		}

		@Override
		public int getColumnCount() {
			if(ins != null)
				return ins.size();
			else
				return 6;
		}

		@Override
		public String getColumnName(int columnIndex) {
			String titulo = null;
			if(ins != null)
				titulo = outs.get(columnIndex).getMes();
			else
				titulo = LocalDate.now().format(DateTimeFormatter.ofPattern("MMM uuuu")).toString();
			return titulo;
		}

		@Override
		public Class<?> getColumnClass(int columnIndex) {
			return Float.class;
		}

		@Override
		public boolean isCellEditable(int rowIndex, int columnIndex) {
			return false;
		}

		@Override
		public Object getValueAt(int rowIndex, int columnIndex) {
			Float monto = null;
			if(ins != null)
				monto = outs.get(columnIndex).getMonto();
			else
				monto = null;
			return monto;
		}

		@Override
		public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void addTableModelListener(TableModelListener l) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void removeTableModelListener(TableModelListener l) {
			// TODO Auto-generated method stub
			
		}
		
	}

	public class TMBalance implements TableModel{
		
		private List<Balance> balances;
		
		public TMBalance(List<Balance> lista) {
			balances = lista;
		}

		@Override
		public int getRowCount() {
			// TODO Auto-generated method stub
			return 1;
		}

		@Override
		public int getColumnCount() {
			if(balances != null)
				return balances.size();
			else
				return 6;
		}

		@Override
		public String getColumnName(int columnIndex) {
			String titulo = null;
			if(balances != null)
				titulo = outs.get(columnIndex).getMes();
			else
				titulo = LocalDate.now().format(DateTimeFormatter.ofPattern("MMM uuuu")).toString();
			return titulo;
		}

		@Override
		public Class<?> getColumnClass(int columnIndex) {
			return Float.class;
		}

		@Override
		public boolean isCellEditable(int rowIndex, int columnIndex) {
			return false;
		}

		@Override
		public Object getValueAt(int rowIndex, int columnIndex) {
			Float monto = null;
			if(balances != null)
				monto = outs.get(columnIndex).getMonto();
			else
				monto = null;
			return monto;
		}

		@Override
		public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void addTableModelListener(TableModelListener l) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void removeTableModelListener(TableModelListener l) {
			// TODO Auto-generated method stub
			
		}
		
	}
}
