package com.proyecto.admin.db;

import com.proyecto.admin.mockup.entities.Experto;
import com.proyecto.admin.mockup.entities.Usuario;

public abstract class DBManager {

	public static boolean insertUser(Usuario u) {
		// TODO insert user
		return true;
	}

	public static boolean updateUser(Usuario u) {
		// TODO update user
		// Si le cambiaron el rol -> problema
		return true;
	}

	public static boolean deleteUser(Usuario u) {
		// TODO delete user
		return true;
	}

	public static Usuario[] getUsers() {
		// TODO select * users
		return null;
	}

	public static Usuario searchUser() {
		// TODO search user

		Usuario u = new Experto();
		u.setId(1L);
		u.setUsuario("duck");
		u.setNombre("Ente");
		u.setApellido("Pato");
		u.setEmail("patopato@patotero.com");

		return u;
	}

}
