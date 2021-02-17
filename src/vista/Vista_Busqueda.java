package vista;

import controlador.*;
import modelo.*;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.ButtonModel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import java.awt.Font;

public class Vista_Busqueda extends JPanel implements ActionListener, MouseListener {

	private ControlVistas controlVistas;
	private ConsultaBBDD consultaBBDD;
	private Vista_FichaEquipos vistaFichaEquipos;
	private ObjetoDTO objetoDto;
	private String[][] arrayTableModel;
	private final ButtonGroup grupoBotones = new ButtonGroup();
	private JTextField textID;
	private JTextField textDescripcion;
	private JTable table;
	private JButton btnRegresar;
	private JButton btnBuscar;
	private tablaModeloPorDefecto modelo;
	private JRadioButton rdbtnID;
	private JRadioButton rdbtnDesc;

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

		if (e.getSource() == btnRegresar) {
			this.setVisible(false);
			controlVistas.getVistaFichaEquipos().setVisible(true);
		}
		if (e.getSource() == btnBuscar) {
//Bloque si está seleccionado el boton de buscar por ID----------------------------------------------------------------------------------------
			if (rdbtnID.isSelected()) {
				try {
					this.modelo.getDataVector().clear();// Vaciamos lo que haya en la tabla
					int consultaInt = Integer.parseInt(textID.getText());
					consultaBBDD = new ConsultaBBDD();
					consultaBBDD.ejecutaConsulta(consultaInt);
					objetoDto = consultaBBDD.tomaDto();

					String fechaUltimaCal;
					String fechaProxCal;
					if (objetoDto.getFechaUltimaCalibracion() != null) {
						fechaUltimaCal = objetoDto.getFechaUltimaCalibracion().toString();
					} else {
						fechaUltimaCal = "";
					}
					if (objetoDto.getFechaProximaCalibracion() != null) {
						fechaProxCal = objetoDto.getFechaProximaCalibracion().toString();
					} else {
						fechaProxCal = "";
					}

					String[] arrayDto = new String[] { objetoDto.getCodigo(), objetoDto.getNombreEquipo(), fechaUltimaCal, fechaProxCal };
					modelo.addRow(arrayDto);
				} catch (Exception e2) {
					return;
				}
			}
//Bloque de busqueda si está seleccionado el boton de busqueda por descripción-------------------------------------------------		
			if (rdbtnDesc.isSelected()) {
				this.modelo.getDataVector().clear();// Vaciamos lo que haya en la tabla
				String consultaStr = textDescripcion.getText();
				consultaBBDD = new ConsultaBBDD();
				consultaBBDD.ejecutaConsulta(consultaStr);//ejecutamos la sobrecarga de constructor de la clase ConsultaBBDD
				arrayTableModel = consultaBBDD.tomaTableModel();
				String[] arrayRow = new String[4];

				// bucle para almacenar en un array temporal cada row de la consulta y añadirla
				// a la JTable
				for (int x = 0; x < arrayTableModel.length; x++) {
					for (int y = 0; y < arrayTableModel[x].length; y++) {
						arrayRow[y] = arrayTableModel[x][y];//almacena en el array temporal todas las columnas de un registro
					}
					modelo.addRow(arrayRow);//añade el array a la tabla y a cada vuelta de bucle vuelve a sobreescribir los indices del array con los
					//nuevos valores
				}
			}
		}

	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getClickCount() > 1) {//para detectar doble click
			
			//almacenamos el valor de la columna 0 (id) en un int. Para ello pasamos el objeto devuelto a string y de ahí a int
			int consultaInt = Integer.parseInt((table.getValueAt(table.getSelectedRow(), 0)).toString());
			
			controlVistas.getVistaFrame().remove(controlVistas.getVistaFichaEquipos());//Borramos de vistaFrame la vistaFichaEquipos inicial
			vistaFichaEquipos = new Vista_FichaEquipos(consultaInt);//creamos la nueva con la nueva consulta
			controlVistas.setVistaFichaEquipos(vistaFichaEquipos);//almacenamos la nueva vista fichaEquipos
			controlVistas.setVistaBusqueda(this);//tambien almacenamos la actual vistaBusqueda con los resultados de la consulta
			controlVistas.getVistaFrame().getContentPane().add(controlVistas.getVistaFichaEquipos(), BorderLayout.CENTER);
			vistaFichaEquipos.desactivaBotones();
			vistaFichaEquipos.setControlVistas(controlVistas);// Se envia el control vistas  para que pueda interactuar con las demas vistas
			this.setVisible(false);
			controlVistas.getVistaFichaEquipos().setVisible(true);

		}
	}

	public void setContolVistas(ControlVistas ctrlV) {
		this.controlVistas = ctrlV;
	}

	public Vista_Busqueda() {

		setBounds(100, 100, 975, 800);
		setBackground(new Color(128, 128, 128));
		setLayout(null);

		rdbtnID = new JRadioButton("B\u00FAsqueda por ID");
		rdbtnID.setFont(new Font("Tahoma", Font.BOLD, 11));
		rdbtnID.setSelected(true);
		rdbtnID.setForeground(new Color(255, 255, 255));
		rdbtnID.setBackground(new Color(128, 128, 128));
		grupoBotones.add(rdbtnID);
		rdbtnID.setBounds(80, 113, 143, 35);
		add(rdbtnID);

		rdbtnDesc = new JRadioButton("B\u00FAsqueda Por Descripci\u00F3n");
		rdbtnDesc.setFont(new Font("Tahoma", Font.BOLD, 11));
		rdbtnDesc.setForeground(new Color(255, 255, 255));
		rdbtnDesc.setBackground(new Color(128, 128, 128));
		grupoBotones.add(rdbtnDesc);
		rdbtnDesc.setBounds(80, 159, 179, 35);
		add(rdbtnDesc);

		textID = new JTextField();
		textID.setBounds(270, 113, 58, 34);
		add(textID);
		textID.setColumns(10);

		textDescripcion = new JTextField();
		textDescripcion.setBounds(269, 160, 290, 34);
		add(textDescripcion);
		textDescripcion.setColumns(10);

		btnBuscar = new JButton("BUSCAR");
		btnBuscar.setBounds(613, 159, 88, 34);
		btnBuscar.addActionListener(this);
		add(btnBuscar);

		
//Bloque de codigo para la creación de la JTable--------------------------------
		
		modelo = new tablaModeloPorDefecto(0, 4);
		String[] arrayEncabezados = new String[] { "ID", "DESCRIPCIÓN", "FECHA ÚLTIMA CAL", "FECHA PRÓXIMA CAL" };
		modelo.setColumnIdentifiers(arrayEncabezados);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(80, 251, 749, 267);
		add(scrollPane);

		table = new JTable(modelo);

		TableColumnModel columnModel = table.getColumnModel();
		columnModel.getColumn(0).setPreferredWidth(30);
		columnModel.getColumn(1).setPreferredWidth(250);
		columnModel.getColumn(2).setPreferredWidth(80);
		columnModel.getColumn(3).setPreferredWidth(80);
		table.addMouseListener(this);//Oyente de acción de click con ratón para selección de equipo para abrir la vistaFichaEquipo
		scrollPane.setViewportView(table);
//---------------------------------------------------------------------------------

		btnRegresar = new JButton("REGRESAR");
		btnRegresar.setBounds(730, 159, 99, 34);
		btnRegresar.addActionListener(this);
		add(btnRegresar);

		JLabel lblNewLabel = new JLabel("En Blanco lista todos los equipos");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setBounds(270, 194, 251, 25);
		add(lblNewLabel);

	}

	@Override
	public void mouseEntered(MouseEvent arg0) {

	}

	@Override
	public void mouseExited(MouseEvent arg0) {

	}

	@Override
	public void mousePressed(MouseEvent arg0) {

	}

	@Override
	public void mouseReleased(MouseEvent arg0) {

	}

	// Clase privada creada para poder definir que no son editables los registros de
	// la tabla. para esto hay que sobreescribir el metodo isCellEditable(int
	// row,int column)
	private class tablaModeloPorDefecto extends DefaultTableModel {

		private tablaModeloPorDefecto(int filas, int columnas) {
			super(filas, columnas);
		}

		public boolean isCellEditable(int row, int column) {
			return false;
		}
	}

}
