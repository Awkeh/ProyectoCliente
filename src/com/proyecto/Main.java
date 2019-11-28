package com.proyecto;

import java.util.List;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import com.proyecto.admin.db.CharacteristicManager;
import com.proyecto.admin.db.PhenomenaManager;
import com.proyecto.admin.db.PhoneManager;
import com.proyecto.admin.db.RoleManager;
import com.proyecto.admin.utils.Alert;
import com.proyecto.entidades.Caracteristica;
import com.proyecto.entidades.Fenomeno;
import com.proyecto.entidades.Rol;
import com.proyecto.entidades.Telefono;
import com.proyecto.entidades.TipoDato;

public class Main {

	public static void main(String[] args) {

//		try {
//			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
//		} catch (ClassNotFoundException e) {
//			Alert.error("ERROR", UIManager.getSystemLookAndFeelClassName() + ": No existe la clase");
//		} catch (InstantiationException e) {
//			Alert.error("ERROR", "No se pudo crear la instancia");
//		} catch (IllegalAccessException e) {
//			Alert.warn("ADVERTENCIA", e.getMessage());
//		} catch (UnsupportedLookAndFeelException e) {
//			Alert.error("ERROR", "Este programa no soporta el look and feel del sistema");
//		}
//
//		RoleManager.create("Administrador", Integer.MAX_VALUE);
//		RoleManager.create("Experto", 65472);
//		RoleManager.create("Voluntario", Rol.ALTA_OBSERVACION | Rol.ALTA_TELEFONO | Rol.MODIFICAR_TELEFONO | Rol.BAJA_TELEFONO);

		CharacteristicManager.create("Altitude", TipoDato.NUMERO);
		CharacteristicManager.create("Rotation speed", TipoDato.NUMERO);
		CharacteristicManager.create("Duration", TipoDato.FECHAHORA);

		List<Caracteristica> chars = CharacteristicManager.getAll();

		PhoneManager.create(0);
		PhoneManager.create(1);
		PhoneManager.create(2);

		List<Telefono> phones = PhoneManager.getAll();

		Fenomeno ff = new Fenomeno();
		ff.setNombre("Tornado");
		ff.setDescripcion("Colision de masas de aire fria y caliente que se vuelve un vortice rotatorio");
		ff.setCaracteristicas(chars);
		ff.setTelefonos(phones);

		PhenomenaManager.create(ff);

		List<Fenomeno> phenom = PhenomenaManager.getAll();

		if(phenom != null) {
			for(Fenomeno f : phenom) {
				System.out.printf("%s: %s\n", f.getNombre(), f.getDescripcion());

				for(Caracteristica c : f.getCaracteristicas()) {
					System.out.printf("%s:  %s\n", c.getEtiqueta(), c.getTipoDato().toString());
				}

				System.out.print("Telefonos: ");

				for(Telefono t : f.getTelefonos()) {
					System.out.printf("%d ", t.getNumero());
				}
			}
		}

//		Admin  p = new Admin();
//		p.show();
	}

}
