//prueba de sincronismo

package vista;

import java.awt.BorderLayout;
import controlador.*;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JToolBar;
import javax.swing.JButton;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JRadioButtonMenuItem;
import java.awt.Color;

public class Vista_Frame extends JFrame implements ActionListener {

	private static ControlVistas controlVistas;
	private static Vista_Inicio vistaInicio;
	private static Vista_FichaEquipos vistaFichaEquipos;
	private static Vista_Busqueda vistaBusqueda;

	private ControlRuta controlRuta;

	private JMenuItem menuItemConsulta;
	private JMenu menuVistas;
	private JMenuItem menuItemFichaEquipos;
	private JMenu menuConfiguracion;
	private JMenuItem menuItemRuta;
	private boolean edicion, consulta;

	public static void main(String[] args) {

		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Vista_Frame frame = new Vista_Frame();
		frame.setVisible(true);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

		if (e.getSource() == menuItemConsulta) {
			consulta = true;
			edicion = false;
			menuVistas.setEnabled(true);
			this.setTitle("CONSULTA");

		}
		if (e.getSource() == menuItemFichaEquipos && consulta) {
			vistaInicio.setVisible(false);
			getContentPane().add(vistaFichaEquipos, BorderLayout.CENTER);
			vistaFichaEquipos.desactivaBotones();
			vistaBusqueda.setVisible(false);
			vistaFichaEquipos.setVisible(true);
		}
	}

	public static void enviaControlVistas() {

		vistaInicio.dameControlVistas(controlVistas);
		vistaFichaEquipos.dameControlVistas(controlVistas);
		vistaBusqueda.dameControlVistas(controlVistas);

	}

	public Vista_Frame() {
		getContentPane().setBackground(new Color(192, 192, 192));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 975, 800);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());

		File archivoConfiguracion = new File("config.ini");
		controlRuta = new ControlRuta();

		while (!archivoConfiguracion.exists()) {
			controlRuta.buscaRuta();
			if (controlRuta.tomaSeleccion() == 0) {
				System.exit(1);
			}
		}
		this.arrancaAplicacion();

	}

	private void arrancaAplicacion() {

		edicion = false;
		consulta = false;

		vistaInicio = new Vista_Inicio();

		controlVistas = new ControlVistas();
		controlVistas.setVistaFrame(this);
		controlVistas.setVistaInicio(vistaInicio);
		vistaFichaEquipos = new Vista_FichaEquipos(1);// Inicia en ID 1 para ver la primera ejecución el primer instrumento de la bbdd
		vistaBusqueda = new Vista_Busqueda();
		controlVistas.setVistaFichaEquipos(vistaFichaEquipos);
		controlVistas.setVistaBusqueda(vistaBusqueda);
		enviaControlVistas();

		getContentPane().add(vistaInicio, BorderLayout.CENTER);

		JMenuBar menuBar = new JMenuBar();
		getContentPane().add(menuBar, BorderLayout.NORTH);

		JMenu menuUsuario = new JMenu("Usuario");
		menuBar.add(menuUsuario);

		menuItemConsulta = new JMenuItem("Consulta");
		menuItemConsulta.addActionListener(this);
		menuUsuario.add(menuItemConsulta);

		JMenuItem menuItemEdicion = new JMenuItem("Edici\u00F3n");
		menuItemEdicion.setEnabled(false);
		menuUsuario.add(menuItemEdicion);

		menuVistas = new JMenu("Vistas");
		menuVistas.setEnabled(false);
		menuBar.add(menuVistas);

		menuItemFichaEquipos = new JMenuItem("Ficha Equipos");
		menuItemFichaEquipos.addActionListener(this);
		menuVistas.add(menuItemFichaEquipos);

	}

}
