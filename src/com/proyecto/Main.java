package com.proyecto;

import javax.naming.InitialContext;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import com.proyecto.admin.Admin;
import com.proyecto.admin.utils.Alert;
import com.proyecto.porotos.RolBeanRemote;
import com.proyecto.porotos.UsuarioBeanRemote;



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

		try {
			RolBeanRemote rolBean = (RolBeanRemote) InitialContext.doLookup("ProyectoPorotos/RolBean!com.proyecto.porotos.RolBeanRemote");
			UsuarioBeanRemote mrBean = (UsuarioBeanRemote) InitialContext.doLookup("ProyectoPorotos/UsuarioBean!com.proyecto.porotos.UsuarioBeanRemote");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
