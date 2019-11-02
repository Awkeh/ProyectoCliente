package com.proyecto;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import com.proyecto.admin.Admin;
import com.proyecto.admin.utils.Alert;

public class Main {

	public static void main(String[] args) {

		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException e) {
			Alert.error("????", UIManager.getSystemLookAndFeelClassName() + "? Quien te conoce papa");
		} catch (InstantiationException e) {
			Alert.error("Error", "Rompiste todo wacho, que onda?");
		} catch (IllegalAccessException e) {
			Alert.warn("El programa se puso lagorra", "Date la vuelta y pone las manos en la espalda");
		} catch (UnsupportedLookAndFeelException e) {
			Alert.error("El LAF del sistema se hace el cheto", "Este programa de mierda no soporta el look and feel del sistema");
		}

		Admin  p = new Admin();
		p.show();
	}

}
