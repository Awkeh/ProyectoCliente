package com.proyecto;

import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import com.proyecto.admin.Admin;
import com.proyecto.admin.db.DepartmentManager;
import com.proyecto.admin.db.TestData;
import com.proyecto.admin.utils.Alert;

public class Main {

	// TODO: Add catch for javax.persistence.NoResultException where needed
	// Razones por la que lanza error de que no sepudo leer la respuesta:
	// 1) Uno o mas tipos de los atributos de la entidad no implementan serializable
	// 2) unknown
	//
	// TODO: Refactor FenomenoBean so as to get by id because nombre is not unique
	//
	// FIXME: find method from DepartamentoBean. Error: failed to read response
	// FOXME: getAll method from FenomenoBeanRemote. Error: failed to read response

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

		// Should use invokeLater, though it works, so meh
		if(JOptionPane.showConfirmDialog(null, "Generar conjunto de datos de pruebas?", "Datos de prueba", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION)
			TestData.generate();

//		System.out.println(100f / 42f);
//		System.out.println(((int) 100 / (int) 42) * 42);

//		Admin  p = new Admin();
//		p.show();

	}

}
