package vista;

import controlador.*;
import modelo.*;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JButton;

import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.border.LineBorder;
import java.awt.Font;
import java.awt.Image;

import javax.swing.JTextField;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.JCheckBox;
import javax.swing.JTextArea;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;

public class Vista_FichaEquipos extends JPanel implements ActionListener {

	private ControlVistas controlVistas;
	private ConsultaBBDD consultaBBDD;
	private ObjetoDTO dtoFicha;

	private JTextField textCodigo;
	private JTextField textNombreEquipo;
	private JTextField textIndGrupo;
	private JTextField textNombrePropietario;
	private JTextField textFechaAlta;
	private JTextField textFechaBaja;
	private JTextField textUbicacion;
	private JTextField textFabricante;
	private JTextField textModelo;
	private JTextField textCalibradorExterno;
	private JTextField textFechaUltCal;
	private JTextField textFechaProxCal;
	private JTextField textCoeficienteActual;
	private JTextField textErrorActual;
	private JTextField textErrorMaxAdmisible;
	private JTextField textPeriodoCalibracion;
	private JTextField textRango;
	private JTable table;
	private JButton botonBuscar;
	private JButton botonNuevo;
	private JButton botonGuardar;
	private JButton btnAbrirCarpeta;

	public Vista_FichaEquipos(int consulta) {

		//Nada mas arrancar VistaFichaEquipos se crea un objeto consultaBBDD que se conecta a la bbdd y ejecuta una primer consulta inicial
		//esta consulta muestra el primer equipo de la base de datos (1)
		//Esta consulta es devuelta en forma de objeto Dto al cual tiene todos los datos obtenidos de la consulta
		consultaBBDD = new ConsultaBBDD();
		consultaBBDD.ejecutaConsulta(consulta);		
		dtoFicha = consultaBBDD.tomaDto();

		setBounds(100, 100, 975, 700);
		setForeground(new Color(255, 255, 255));
		setBackground(new Color(128, 128, 128));
		setLayout(null);

		JLabel lblNewLabel_1 = new JLabel("Nombre del equipo");
		lblNewLabel_1.setForeground(new Color(255, 255, 255));
		lblNewLabel_1.setFont(new Font("SansSerif", Font.BOLD, 12));
		lblNewLabel_1.setBounds(157, 65, 126, 16);
		add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("IndGrupo");
		lblNewLabel_2.setForeground(new Color(255, 255, 255));
		lblNewLabel_2.setFont(new Font("SansSerif", Font.BOLD, 12));
		lblNewLabel_2.setBounds(402, 65, 55, 16);
		add(lblNewLabel_2);

		JLabel lblNewLabel_3 = new JLabel("Nombre Propietario");
		lblNewLabel_3.setForeground(new Color(255, 255, 255));
		lblNewLabel_3.setFont(new Font("SansSerif", Font.BOLD, 12));
		lblNewLabel_3.setBounds(508, 65, 141, 16);
		add(lblNewLabel_3);

		JLabel lblNewLabel_4 = new JLabel("Fecha Alta");
		lblNewLabel_4.setForeground(new Color(255, 255, 255));
		lblNewLabel_4.setFont(new Font("SansSerif", Font.BOLD, 12));
		lblNewLabel_4.setBounds(670, 67, 91, 16);
		add(lblNewLabel_4);

		JLabel lblNewLabel_5 = new JLabel("Fecha Baja");
		lblNewLabel_5.setForeground(new Color(255, 255, 255));
		lblNewLabel_5.setFont(new Font("SansSerif", Font.BOLD, 12));
		lblNewLabel_5.setBounds(670, 125, 91, 16);
		add(lblNewLabel_5);

		JLabel lblNewLabel = new JLabel("C\u00F3digo");
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setFont(new Font("SansSerif", Font.BOLD, 12));
		lblNewLabel.setBounds(46, 65, 55, 16);
		add(lblNewLabel);

		textCodigo = new JTextField();
		textCodigo.setBackground(SystemColor.inactiveCaption);
		textCodigo.setEditable(false);
		textCodigo.setBounds(33, 85, 69, 28);
		add(textCodigo);
		textCodigo.setColumns(10);

		textCodigo.setText(dtoFicha.getCodigo());

		textNombreEquipo = new JTextField();
		textNombreEquipo.setBackground(SystemColor.inactiveCaption);
		textNombreEquipo.setEditable(false);
		textNombreEquipo.setBounds(132, 85, 245, 28);
		add(textNombreEquipo);
		textNombreEquipo.setColumns(10);

		textNombreEquipo.setText(dtoFicha.getNombreEquipo());

		textIndGrupo = new JTextField();
		textIndGrupo.setBackground(SystemColor.inactiveCaption);
		textIndGrupo.setEditable(false);
		textIndGrupo.setBounds(389, 85, 97, 28);
		add(textIndGrupo);
		textIndGrupo.setColumns(10);

		textIndGrupo.setText(dtoFicha.getIndGrupo());

		textNombrePropietario = new JTextField();
		textNombrePropietario.setEditable(false);
		textNombrePropietario.setBackground(SystemColor.inactiveCaption);
		textNombrePropietario.setBounds(502, 85, 122, 28);
		add(textNombrePropietario);
		textNombrePropietario.setColumns(10);

		textNombrePropietario.setText(dtoFicha.getPropietario());

		textFechaAlta = new JTextField();
		textFechaAlta.setBackground(SystemColor.inactiveCaption);
		textFechaAlta.setEditable(false);
		textFechaAlta.setBounds(661, 85, 79, 28);
		add(textFechaAlta);
		textFechaAlta.setColumns(10);

		if(dtoFicha.getFechaAlta()!=null)textFechaAlta.setText(dtoFicha.getFechaAlta().toString());

		textFechaBaja = new JTextField();
		textFechaBaja.setBackground(SystemColor.inactiveCaption);
		textFechaBaja.setEditable(false);
		textFechaBaja.setColumns(10);
		textFechaBaja.setBounds(661, 144, 79, 28);
		add(textFechaBaja);

		if(dtoFicha.getFechaBaja()!=null)textFechaBaja.setText(dtoFicha.getFechaBaja().toString());

		JSeparator separator = new JSeparator();
		separator.setBackground(new Color(255, 255, 255));
		separator.setOrientation(SwingConstants.VERTICAL);
		separator.setBounds(812, 44, 21, 619);
		add(separator);

		botonBuscar = new JButton("BUSCAR");
		botonBuscar.setBounds(845, 62, 90, 28);
		botonBuscar.addActionListener(this);
		add(botonBuscar);

		botonNuevo = new JButton("NUEVO");
		botonNuevo.setBounds(845, 119, 90, 28);
		add(botonNuevo);

		botonGuardar = new JButton("GUARDAR");
		botonGuardar.setBounds(845, 177, 90, 28);
		add(botonGuardar);
		
		ImageIcon icono = new ImageIcon(getClass().getResource("icon folder.png"));
		
		btnAbrirCarpeta = new JButton(icono);
		btnAbrirCarpeta.setBounds(844, 232, 91, 42);
		btnAbrirCarpeta.addActionListener(new Carpeta(textCodigo.getText()));
		add(btnAbrirCarpeta);

		JCheckBox chckbxCalibra = new JCheckBox("Se Calibra?");
		chckbxCalibra.setBackground(new Color(128, 128, 128));
		chckbxCalibra.setFont(new Font("SansSerif", Font.BOLD, 12));
		chckbxCalibra.setForeground(new Color(255, 255, 255));
		chckbxCalibra.setBounds(657, 202, 104, 18);
		add(chckbxCalibra);

		chckbxCalibra.setSelected(dtoFicha.isEquipoCalibrado());

		JCheckBox chckbxIso = new JCheckBox("ISO 17025");
		chckbxIso.setBackground(new Color(128, 128, 128));
		chckbxIso.setFont(new Font("SansSerif", Font.BOLD, 12));
		chckbxIso.setForeground(new Color(255, 255, 255));
		chckbxIso.setBounds(657, 242, 104, 18);
		add(chckbxIso);

		chckbxIso.setSelected(dtoFicha.isIso17025());

		JCheckBox chckbxEnsayoIso = new JCheckBox("Ensayo ISO");
		chckbxEnsayoIso.setBackground(new Color(128, 128, 128));
		chckbxEnsayoIso.setFont(new Font("SansSerif", Font.BOLD, 12));
		chckbxEnsayoIso.setForeground(new Color(255, 255, 255));
		chckbxEnsayoIso.setBounds(657, 280, 104, 18);
		add(chckbxEnsayoIso);

		chckbxEnsayoIso.setSelected(dtoFicha.isEnsayoIso17025());

		JLabel lblNewLabel_6 = new JLabel("Ubicaci\u00F3n Habitual");
		lblNewLabel_6.setFont(new Font("SansSerif", Font.BOLD, 12));
		lblNewLabel_6.setForeground(new Color(255, 255, 255));
		lblNewLabel_6.setBounds(43, 125, 115, 16);
		add(lblNewLabel_6);

		textUbicacion = new JTextField();
		textUbicacion.setBackground(SystemColor.inactiveCaption);
		textUbicacion.setEditable(false);
		textUbicacion.setBounds(33, 144, 149, 28);
		add(textUbicacion);
		textUbicacion.setColumns(10);

		textUbicacion.setText(dtoFicha.getCodigoUbicacion());

		JLabel lblNewLabel_6_1 = new JLabel("Fabricante");
		lblNewLabel_6_1.setForeground(Color.WHITE);
		lblNewLabel_6_1.setFont(new Font("SansSerif", Font.BOLD, 12));
		lblNewLabel_6_1.setBounds(204, 125, 115, 16);
		add(lblNewLabel_6_1);

		textFabricante = new JTextField();
		textFabricante.setBackground(SystemColor.inactiveCaption);
		textFabricante.setEditable(false);
		textFabricante.setColumns(10);
		textFabricante.setBounds(194, 144, 194, 28);
		add(textFabricante);

		textFabricante.setText(dtoFicha.getFabricante());

		JLabel lblNewLabel_6_1_1 = new JLabel("Modelo/ Lote/ N Serie");
		lblNewLabel_6_1_1.setForeground(Color.WHITE);
		lblNewLabel_6_1_1.setFont(new Font("SansSerif", Font.BOLD, 12));
		lblNewLabel_6_1_1.setBounds(415, 125, 134, 16);
		add(lblNewLabel_6_1_1);

		textModelo = new JTextField();
		textModelo.setBackground(SystemColor.inactiveCaption);
		textModelo.setEditable(false);
		textModelo.setColumns(10);
		textModelo.setBounds(405, 144, 194, 28);
		add(textModelo);

		textModelo.setText(dtoFicha.getModelo());

		JLabel lblNewLabel_6_2 = new JLabel("Datos T\u00E9cnicos del Equipo");
		lblNewLabel_6_2.setForeground(Color.WHITE);
		lblNewLabel_6_2.setFont(new Font("SansSerif", Font.BOLD, 12));
		lblNewLabel_6_2.setBounds(43, 200, 163, 16);
		add(lblNewLabel_6_2);

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(33, 228, 553, 70);
		add(scrollPane_1);

		JTextArea textAreaDatosTecnicos = new JTextArea();
		textAreaDatosTecnicos.setBackground(SystemColor.inactiveCaption);
		textAreaDatosTecnicos.setEditable(false);
		scrollPane_1.setViewportView(textAreaDatosTecnicos);

		textAreaDatosTecnicos.append(dtoFicha.getDatosTecnicosEquipo());

		JLabel lblNewLabel_3_1 = new JLabel("Calibrador Externo");
		lblNewLabel_3_1.setForeground(Color.WHITE);
		lblNewLabel_3_1.setFont(new Font("SansSerif", Font.BOLD, 12));
		lblNewLabel_3_1.setBounds(41, 314, 122, 16);
		add(lblNewLabel_3_1);

		textCalibradorExterno = new JTextField();
		textCalibradorExterno.setBackground(SystemColor.inactiveCaption);
		textCalibradorExterno.setEditable(false);
		textCalibradorExterno.setColumns(10);
		textCalibradorExterno.setBounds(35, 334, 122, 28);
		add(textCalibradorExterno);

		textCalibradorExterno.setText(dtoFicha.getCalibradorExterno());

		textFechaUltCal = new JTextField();
		textFechaUltCal.setBackground(SystemColor.inactiveCaption);
		textFechaUltCal.setEditable(false);
		textFechaUltCal.setColumns(10);
		textFechaUltCal.setBounds(172, 334, 147, 28);
		add(textFechaUltCal);

		if(dtoFicha.getFechaUltimaCalibracion()!=null)textFechaUltCal.setText(dtoFicha.getFechaUltimaCalibracion().toString());

		JLabel lblNewLabel_3_1_1 = new JLabel("Fecha \u00DAltima Calibraci\u00F3n");
		lblNewLabel_3_1_1.setForeground(Color.WHITE);
		lblNewLabel_3_1_1.setFont(new Font("SansSerif", Font.BOLD, 12));
		lblNewLabel_3_1_1.setBounds(178, 314, 147, 16);
		add(lblNewLabel_3_1_1);

		textFechaProxCal = new JTextField();
		textFechaProxCal.setBackground(SystemColor.inactiveCaption);
		textFechaProxCal.setEditable(false);
		textFechaProxCal.setColumns(10);
		textFechaProxCal.setBounds(331, 334, 163, 28);
		add(textFechaProxCal);

		if(dtoFicha.getFechaProximaCalibracion()!=null)textFechaProxCal.setText(dtoFicha.getFechaProximaCalibracion().toString());

		JLabel lblNewLabel_3_1_2 = new JLabel("Fecha Pr\u00F3xima Calibraci\u00F3n");
		lblNewLabel_3_1_2.setForeground(Color.WHITE);
		lblNewLabel_3_1_2.setFont(new Font("SansSerif", Font.BOLD, 12));
		lblNewLabel_3_1_2.setBounds(337, 314, 169, 16);
		add(lblNewLabel_3_1_2);

		textCoeficienteActual = new JTextField();
		textCoeficienteActual.setBackground(SystemColor.inactiveCaption);
		textCoeficienteActual.setEditable(false);
		textCoeficienteActual.setColumns(10);
		textCoeficienteActual.setBounds(506, 334, 122, 28);
		add(textCoeficienteActual);

		textCoeficienteActual.setText(dtoFicha.getCoeficiente());

		JLabel lblNewLabel_3_1_3 = new JLabel("Coeficiente Actual");
		lblNewLabel_3_1_3.setForeground(Color.WHITE);
		lblNewLabel_3_1_3.setFont(new Font("SansSerif", Font.BOLD, 12));
		lblNewLabel_3_1_3.setBounds(512, 314, 122, 16);
		add(lblNewLabel_3_1_3);

		JLabel lblNewLabel_3_1_4 = new JLabel("Error Actual");
		lblNewLabel_3_1_4.setForeground(Color.WHITE);
		lblNewLabel_3_1_4.setFont(new Font("SansSerif", Font.BOLD, 12));
		lblNewLabel_3_1_4.setBounds(39, 374, 122, 16);
		add(lblNewLabel_3_1_4);

		textErrorActual = new JTextField();
		textErrorActual.setBackground(SystemColor.inactiveCaption);
		textErrorActual.setEditable(false);
		textErrorActual.setColumns(10);
		textErrorActual.setBounds(33, 394, 122, 28);
		add(textErrorActual);

		textErrorActual.setText(dtoFicha.getErrorActual());

		JLabel lblNewLabel_3_1_1_1 = new JLabel("Error M\u00E1ximo Admisible");
		lblNewLabel_3_1_1_1.setForeground(Color.WHITE);
		lblNewLabel_3_1_1_1.setFont(new Font("SansSerif", Font.BOLD, 12));
		lblNewLabel_3_1_1_1.setBounds(176, 374, 143, 16);
		add(lblNewLabel_3_1_1_1);

		textErrorMaxAdmisible = new JTextField();
		textErrorMaxAdmisible.setBackground(SystemColor.inactiveCaption);
		textErrorMaxAdmisible.setEditable(false);
		textErrorMaxAdmisible.setColumns(10);
		textErrorMaxAdmisible.setBounds(170, 394, 149, 28);
		add(textErrorMaxAdmisible);

		textErrorMaxAdmisible.setText(dtoFicha.getErrorMaxAdmisibre());

		JLabel lblNewLabel_3_1_2_1 = new JLabel("Periodo Calibraci\u00F3n");
		lblNewLabel_3_1_2_1.setForeground(Color.WHITE);
		lblNewLabel_3_1_2_1.setFont(new Font("SansSerif", Font.BOLD, 12));
		lblNewLabel_3_1_2_1.setBounds(331, 374, 138, 16);
		add(lblNewLabel_3_1_2_1);

		textPeriodoCalibracion = new JTextField();
		textPeriodoCalibracion.setBackground(SystemColor.inactiveCaption);
		textPeriodoCalibracion.setEditable(false);
		textPeriodoCalibracion.setColumns(10);
		textPeriodoCalibracion.setBounds(331, 394, 155, 28);
		add(textPeriodoCalibracion);

		textPeriodoCalibracion.setText(dtoFicha.getPeriodoCalibracion());

		JLabel lblNewLabel_3_1_3_1 = new JLabel("Rango");
		lblNewLabel_3_1_3_1.setForeground(Color.WHITE);
		lblNewLabel_3_1_3_1.setFont(new Font("SansSerif", Font.BOLD, 12));
		lblNewLabel_3_1_3_1.setBounds(514, 374, 122, 16);
		add(lblNewLabel_3_1_3_1);

		textRango = new JTextField();
		textRango.setBackground(SystemColor.inactiveCaption);
		textRango.setEditable(false);
		textRango.setColumns(10);
		textRango.setBounds(508, 394, 122, 28);
		add(textRango);

		textRango.setText(dtoFicha.getRango());

		JLabel lblNewLabel_3_1_3_1_1 = new JLabel("Hist\u00F3rico");
		lblNewLabel_3_1_3_1_1.setForeground(Color.WHITE);
		lblNewLabel_3_1_3_1_1.setFont(new Font("SansSerif", Font.BOLD, 12));
		lblNewLabel_3_1_3_1_1.setBounds(36, 434, 122, 16);
		add(lblNewLabel_3_1_3_1_1);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(33, 450, 582, 105);
		add(scrollPane);

		table = new JTable();
		table.setBackground(SystemColor.inactiveCaption);
		scrollPane.setViewportView(table);
		table.setModel(new DefaultTableModel(new Object[][] { { null, null, null }, { null, null, null }, { null, null, null }, { null, null, null }, { null, null, null }, },
				new String[] { "Fecha", "Usuario", "Descripci\u00F3n" }) {
			Class[] columnTypes = new Class[] { Object.class, String.class, String.class };

			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		table.getColumnModel().getColumn(0).setResizable(false);
		table.getColumnModel().getColumn(0).setPreferredWidth(69);
		table.getColumnModel().getColumn(1).setResizable(false);
		table.getColumnModel().getColumn(1).setPreferredWidth(100);
		table.getColumnModel().getColumn(2).setResizable(false);
		table.getColumnModel().getColumn(2).setPreferredWidth(270);
		table.setShowVerticalLines(true);
		table.setShowHorizontalLines(true);
		
		
		
		

	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == botonBuscar) {
			this.setVisible(false);
			controlVistas.getVistaFrame().getContentPane().add(controlVistas.getVistaBusqueda(), BorderLayout.CENTER);
			controlVistas.getVistaBusqueda().setVisible(true);
		}
	}
	
	public void desactivaBotones() {
		botonNuevo.setEnabled(false);
		botonGuardar.setEnabled(false);
	}
	
	public void setControlVistas(ControlVistas ctrlV) {
		this.controlVistas = ctrlV;
	}
}
