package modelo;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import controlador.*;

import javax.swing.JOptionPane;

import controlador.*;

public class ConsultaBBDD {

	// Objetos necesarios para gestión dto. Tanto para la fichaEquipos como la
	// ventana de busqueda
	private ObjetoDTO dtoFicha;
	private ObjetoDTO dtoBusqueda;
	private ArrayList<ObjetoDTO> arrayDto;
	private String[][] arrayTableModel;
//------------------------------------------------------
	private ConexionDDBB conexionDDBB;
	private Connection miConexion;
	private Statement miSentencia;
	private ResultSet rs;

	public void ejecutaConsulta(int consultaInt) {// Consulta para la vistaFicha donde solo puede aparecer uno de los equipos

		conexionDDBB = new ConexionDDBB();
		conexionDDBB.estableceConexion();
		miConexion = conexionDDBB.dameConexion();
		dtoFicha = new ObjetoDTO();
		String consultaSQL = "SELECT * FROM EQUIPOS DE MEDIDA WHERE CÓDIGO =" + consultaInt;// valor indicado para busqueda. 1 en caso de primer arranque

		try {
			miSentencia = miConexion.createStatement();
			rs = miSentencia.executeQuery(consultaSQL);

			while (rs.next()) {
				dtoFicha.setCodigo(rs.getString(1));
				dtoFicha.setNombreEquipo(rs.getString(2));
				dtoFicha.setIndGrupo(rs.getString(3));
				dtoFicha.setPeriodoCalibracion(rs.getString(4));
				dtoFicha.setCalibradorExterno(rs.getString(5));
				dtoFicha.setFabricante(rs.getString(6));
				dtoFicha.setModelo(rs.getString(7));
				dtoFicha.setFechaAlta(rs.getDate(8));
				dtoFicha.setFechaBaja(rs.getDate(9));
				dtoFicha.setCodigoUbicacion(rs.getString(10));
				dtoFicha.setFechaUltimaCalibracion(rs.getDate(11));
				dtoFicha.setFechaProximaCalibracion(rs.getDate(12));
				dtoFicha.setDatosTecnicosEquipo(rs.getString(13));
				dtoFicha.setRango(rs.getString(14));
				dtoFicha.setErrorActual(rs.getString(15));
				dtoFicha.setErrorMaxAdmisibre(rs.getString(16));
				dtoFicha.setEquipoCalibrado(rs.getBoolean(17));
				dtoFicha.setIso17025(rs.getBoolean(18));
				dtoFicha.setEnsayoIso17025(rs.getBoolean(19));
				dtoFicha.setPropietario(rs.getString(20));
				dtoFicha.setCoeficiente(rs.getString(21));
			}

		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Base de datos dañada o erronea");
			// En el caso de que el archivo no sea el correcto, vuelve a abrir el explorador
			// de archivos
			ControlRuta controlRuta = new ControlRuta();
			controlRuta.buscaRuta();
			System.exit(1);
		} finally {
			try {
				rs.close();
				miSentencia.close();
				conexionDDBB.cierraConexion();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(null, "No ha sido posible establecer conexión con base de datos.");
				System.exit(1);
			}

		}

	}

	public void ejecutaConsulta(String consultaStr) {// Consulta para la vistaBusqueda donde pueden aparecer varios resultados

		conexionDDBB = new ConexionDDBB();
		conexionDDBB.estableceConexion();
		miConexion = conexionDDBB.dameConexion();
		dtoBusqueda = new ObjetoDTO();
		arrayDto = new ArrayList<ObjetoDTO>();
		String consultaSQL = "SELECT * FROM [EQUIPOS DE MEDIDA] WHERE [NOMBRE DEL EQUIPO] LIKE '%" + consultaStr + "%'";

		try {
			miSentencia = miConexion.createStatement();
			rs = miSentencia.executeQuery(consultaSQL);

			// Se usa un objeto DTO(DataTransferObject) para almacenar los resultados de la
			// consulta e ir añadiendolos a un arrayList
			while (rs.next()) {
				dtoBusqueda.setCodigo(rs.getString(1));
				dtoBusqueda.setNombreEquipo(rs.getString(2));
				dtoBusqueda.setFechaUltimaCalibracion(rs.getDate(11));
				dtoBusqueda.setFechaProximaCalibracion(rs.getDate(12));

				arrayDto.add(dtoBusqueda);
				dtoBusqueda = new ObjetoDTO();// a cada salto de resultset crea un objeto dto nuevo para almacenarlo en el
												// array
			}

			// Bloque de codigo para rellenar un array normal con el arrayList para
			// utilizarlo en la JTable como modelo
			// POSIBLEMENTE HUBIERA SIDO MAS EFICAZ ALMACENAR DIRECTAMENTE EL RESULTADO DEL
			// RESULTSET EN UN ARRAY BIDIMENSIONAL SIN PASAR POR DTO
			int numeroRegistros = arrayDto.size();
			int columnas = 4;// codigo, nombreequipo,fecha ultcal, fecha proxcal
			int index = 0;
			arrayTableModel = new String[numeroRegistros][columnas];
			for (int i = 0; i < numeroRegistros; i++) {
				for (int e = 0; e < columnas; e++) {
					arrayTableModel[i][e] = arrayDto.get(index).getCodigo();
					e++;
					arrayTableModel[i][e] = arrayDto.get(index).getNombreEquipo();
					e++;
					if (arrayDto.get(index).getFechaUltimaCalibracion() != null) {
						arrayTableModel[i][e] = arrayDto.get(index).getFechaUltimaCalibracion().toString();
					} else {
						arrayTableModel[i][e] = "";
					}
					e++;
					if (arrayDto.get(index).getFechaProximaCalibracion() != null) {
						arrayTableModel[i][e] = arrayDto.get(index).getFechaProximaCalibracion().toString();
					} else {
						arrayTableModel[i][e] = "";
					}
					index++;
				}

			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				miSentencia.close();
				conexionDDBB.cierraConexion();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

	}

	public ObjetoDTO tomaDto() {
		return dtoFicha;
	}

	public String[][] tomaTableModel() {
		return arrayTableModel;
	}

}
