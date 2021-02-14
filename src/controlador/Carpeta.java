package controlador;

import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.swing.JOptionPane;

public class Carpeta implements ActionListener {

	private String carpetaDestino;

	public Carpeta(String carpetaDestino) {

		this.carpetaDestino = carpetaDestino;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub

		String sCarpAct = System.getProperty("user.dir");
		File carpeta = new File(sCarpAct);
		File carpetaMadre = carpeta.getParentFile();
		String[] listado = carpetaMadre.list();

		String codigoBuscar = carpetaDestino;
		String codigoBuscarAmp = codigoBuscar.concat("-");
		File Destino = null;

		for (String e : listado) {
			int posicion = e.indexOf("-");
			if (posicion != -1) {
				String subString = e.substring(0, posicion + 1);
				if (codigoBuscarAmp.equals(subString)) {
					Destino = new File(carpetaMadre.toString().concat("\\" + e));
					try {
						Desktop.getDesktop().open(Destino);
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						JOptionPane.showMessageDialog(null, "Carpeta no encontrada");
					}
				}
			}
		}

	}

}
