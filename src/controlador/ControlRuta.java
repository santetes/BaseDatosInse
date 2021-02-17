package controlador;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

public class ControlRuta {
	
	int seleccion = 0;

	public void buscaRuta() {

		//Bloqe de codigo que se encarga de abrir la ventan del explorador y filtrar selección por tipo de archivo y extensión
		BufferedWriter archivoEscritura = null;
		JFileChooser selectorArchivos = new JFileChooser();
		selectorArchivos.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
		FileNameExtensionFilter filtro = new FileNameExtensionFilter("Archivos mdb", "mdb");
		selectorArchivos.setFileFilter(filtro);

		int resultado = selectorArchivos.showOpenDialog(null);
		File archivo = selectorArchivos.getSelectedFile();
//--------------------------------------------------------------------------------
		try {
			if (resultado == JFileChooser.APPROVE_OPTION) {
				FileWriter fileWr = new FileWriter("config.ini", false);
				archivoEscritura = new BufferedWriter(fileWr);
				archivoEscritura.write(archivo.toString());
				seleccion = 1;
				try {
					archivoEscritura.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}else {
				seleccion = 0;
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
	
	public int tomaSeleccion() {//metodo para indicar a VistaFrame si se ha selecionado un archivo en la ventana del esplorador
		return seleccion;
	}
}
