package com.proyecto;

import javax.naming.InitialContext;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import com.proyecto.admin.Admin;
import com.proyecto.admin.db.DBManager;
import com.proyecto.admin.utils.Alert;
import com.proyecto.entidades.Rol;
import com.proyecto.porotos.RolBeanRemote;
import com.proyecto.porotos.UsuarioBeanRemote;



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
		DBManager.insertRol("Administrador", Integer.MAX_VALUE);
		DBManager.insertRol("Experto", 65472);
		DBManager.insertRol("Voluntario", Rol.ALTA_OBSERVACION | Rol.ALTA_TELEFONO | Rol.MODIFICAR_TELEFONO | Rol.BAJA_TELEFONO);

		Admin  p = new Admin();
		p.show();
	}

}
