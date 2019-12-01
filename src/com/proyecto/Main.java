package com.proyecto;

import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import com.proyecto.admin.Admin;
import com.proyecto.admin.db.TestData;
import com.proyecto.admin.utils.Alert;

public class Main {

	public static void main(String[] args) {

		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException e) {
			Alert.error("ERROR", UIManager.getSystemLookAndFeelClassName() + ": No existe la clase");
		} catch (InstantiationException e) {
			Alert.error("ERROR", "No se pudo crear la instancia");
		} catch (IllegalAccessException e) {
			Alert.warn("ADVERTENCIA", e.getMessage());
		} catch (UnsupportedLookAndFeelException e) {
			Alert.error("ERROR", "Este programa no soporta el look and feel del sistema");
		}

		if(JOptionPane.showConfirmDialog(null, "Generar conjunto de datos de pruebas?", "Datos de prueba", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION)
			TestData.generate();

		Admin  p = new Admin();
		p.show();
	}

}
