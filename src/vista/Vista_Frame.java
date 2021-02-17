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

		//Configuración del aspecto visual de las ventanas
		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//Crea el objeto frame que dá inicio a la aplicación
		Vista_Frame frame = new Vista_Frame();
		frame.setVisible(true);

	}

	

	

	public Vista_Frame() {
		
		getContentPane().setBackground(new Color(192, 192, 192));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 975, 800);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());

		File archivoConfiguracion = new File("config.ini");
		controlRuta = new ControlRuta();

//Comprobación de existencia de archivo de configuración en la raíz de la aplicación donde se indica la ubicación
//del archivo de la base de datos. Si este no existe, se ejecuta el metodo busca ruta para que muestre una ventana de explorador
		while (!archivoConfiguracion.exists()) {
			controlRuta.buscaRuta();
			if (controlRuta.tomaSeleccion() == 0) {//sino se seleciona nada, sale de la aplicación
				System.exit(1);
			}
		}
		this.arrancaAplicacion();
//--------------------------------------------------------------------------------------------------------------------
	}

	private void arrancaAplicacion() {
		//establece en false los dos tipos de acceso a la aplicación
		edicion = false;
		consulta = false;
		
		//bloque de gestión de ventanas donde se crea un objeto controlVistas que se encarga de almacenar todos los JPanel de cada vista
		//Para ir haciendo visible y no visible las ventanas de la aplicación
		controlVistas = new ControlVistas();
		vistaInicio = new Vista_Inicio();
		controlVistas.setVistaFrame(this);
		controlVistas.setVistaInicio(vistaInicio);
		vistaFichaEquipos = new Vista_FichaEquipos(1);// Inicia en ID 1 para ver la primera ejecución el primer instrumento de la bbdd
		vistaBusqueda = new Vista_Busqueda();
		controlVistas.setVistaFichaEquipos(vistaFichaEquipos);
		controlVistas.setVistaBusqueda(vistaBusqueda);
		
		enviaControlVistas();//Metodo que envía a todas las vistas las vistas de las otras ventanas

		//añade al Frame la primera ventana (VistaInicio) cuando se ejecuta la aplicación
		getContentPane().add(vistaInicio, BorderLayout.CENTER);

		//Parte de configuración de la barra superio del menu y sus actionListener correspondientes-----------
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
//------------------------------------------------------------------------------------------------------------
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		//Cuando se pulsa sobre consulta, se activa el menuVistas y pone en la cabecera de la ventana el tipo de acceso (CONSULTA)
		if (e.getSource() == menuItemConsulta) {
			consulta = true;
			edicion = false;
			menuVistas.setEnabled(true);
			this.setTitle("CONSULTA");

		}
		//Cuando se clica en fichaEquipos, se oculta vista inicio y se añade al frame la vistaEquipos y se hace visible.
		//Tambien se hace invisible la vistaBusqueda si se viene de esa ventana.
		if (e.getSource() == menuItemFichaEquipos && consulta) {
			vistaInicio.setVisible(false);
			getContentPane().add(vistaFichaEquipos, BorderLayout.CENTER);
			vistaFichaEquipos.desactivaBotones();//Desactiva botones de nuevo y guardar de la ventana vistaFichaEquipos ya que estamos en modo consulta
			vistaBusqueda.setVisible(false);
			vistaFichaEquipos.setVisible(true);
		}
	}
	
	public static void enviaControlVistas() {

		vistaInicio.setControVistas(controlVistas);
		vistaFichaEquipos.setControlVistas(controlVistas);
		vistaBusqueda.setContolVistas(controlVistas);

	}

}
