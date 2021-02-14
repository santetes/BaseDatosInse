package vista;

import controlador.*;
import javax.swing.JPanel;
import java.awt.Color;

public class Vista_Inicio extends JPanel {
	
	private ControlVistas controlVistas;

	
	public Vista_Inicio() {
		setBackground(new Color(128, 128, 128));

	}
	
	public void dameControlVistas(ControlVistas ctrlV) {
		this.controlVistas = ctrlV;
	}

}
