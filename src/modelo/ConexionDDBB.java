package modelo;

import controlador.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

public class ConexionDDBB {

	private static Connection miConexion;
	private ControlRuta controlRuta = new ControlRuta();

	public void estableceConexion() {
		try {
			Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");//carga de forma dinámica el driver para conexión con base de datos access
			
			//Bloque de codigo para lectura de directorio de base de datos del archivo config.ini
			File archivo = new File ("config.ini");
			FileReader fr = new FileReader (archivo);
			BufferedReader br = new BufferedReader(fr);
			String ruta = br.readLine();
			//----------------------------------------------------------------------------------
			miConexion = DriverManager.getConnection("jdbc:ucanaccess://" + ruta + ";memory=false");
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "ARCHIVO DE BASE DE DATOS ERRONEO. Elija el archivo correcto y reinicie aplicación");
			controlRuta.buscaRuta();//Si no ha sido posible establecer conexión con base de datos vuelve a salir ventana explorador para seleccionar archivo
			//y se cierra aplicación
			System.exit(1);
		}
	}

	public Connection dameConexion() {
		return miConexion;
	}

	public void cierraConexion() {
		try {
			miConexion.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
