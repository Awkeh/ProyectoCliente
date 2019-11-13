package com.proyecto;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import com.proyecto.admin.Admin;
import com.proyecto.admin.db.RoleManager;
import com.proyecto.admin.utils.Alert;
<<<<<<< HEAD
=======
import com.proyecto.entidades.Rol;
>>>>>>> refs/heads/Negro

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

		RoleManager.create("Administrador", Integer.MAX_VALUE);
		RoleManager.create("Experto", 65472);
		RoleManager.create("Voluntario", Rol.ALTA_OBSERVACION | Rol.ALTA_TELEFONO | Rol.MODIFICAR_TELEFONO | Rol.BAJA_TELEFONO);

		Admin  p = new Admin();
		p.show();
	}

}
