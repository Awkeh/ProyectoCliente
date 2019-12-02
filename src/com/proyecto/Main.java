package com.proyecto;

import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import com.proyecto.admin.Admin;
import com.proyecto.admin.db.DepartmentManager;
import com.proyecto.admin.db.TestData;
import com.proyecto.admin.utils.Alert;
import com.proyecto.entidades.Departamento;

public class Main {

	// TODO: Add catch for javax.persistence.NoResultException where needed

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

		Departamento d = null;
		try {
			d = DepartmentManager.find("Durazno");
		} catch (Exception e) {
			e.printStackTrace();
		}
		Alert.info("Y? que paso vieja", (d == null ? "Nah, sigue sin funcionar la pija esta" : "Alfin la concha de tu vieja, funciona"));

		Admin  p = new Admin();
		p.show();
	}

}
