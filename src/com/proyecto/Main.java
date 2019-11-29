package com.proyecto;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import com.proyecto.admin.Admin;
import com.proyecto.admin.db.CharacteristicManager;
import com.proyecto.admin.db.RoleManager;
import com.proyecto.admin.utils.Alert;
import com.proyecto.entidades.Rol;
import com.proyecto.entidades.TipoDato;

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

		// 10 puntos para griffindor por organizar atn bien las mascaras de bits
		RoleManager.create("Administrador", Integer.MAX_VALUE);
		RoleManager.create("Experto",
			Rol.REVISAR_OBSERVACION 	 |
			Rol.ALTA_CARACTERISTICA 	 | Rol.ALTA_FENOMENO 	  | Rol.ALTA_OBSERVACION 	  | Rol.ALTA_TELEFONO 	  |
			Rol.BAJA_CARACTERISTICA 	 | Rol.BAJA_FENOMENO 	  | Rol.BAJA_OBSERVACION 	  | Rol.BAJA_TELEFONO 	  |
			Rol.MODIFICAR_CARACTERISTICA | Rol.MODIFICAR_FENOMENO | Rol.MODIFICAR_OBSERVACION | Rol.MODIFICAR_TELEFONO);
		RoleManager.create("Voluntario",   Rol.ALTA_OBSERVACION   | Rol.MODIFICAR_OBSERVACION                         );

		CharacteristicManager.create("Altitud", TipoDato.NUMERO);
		CharacteristicManager.create("Nivel Maribel", TipoDato.NUMERO);
		CharacteristicManager.create("Duracion", TipoDato.FECHAHORA);

		Admin  p = new Admin();
		p.show();
	}

}
