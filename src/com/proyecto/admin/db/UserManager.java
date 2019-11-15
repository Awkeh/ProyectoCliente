package com.proyecto.admin.db;

import com.proyecto.admin.lang.BeanNotFoundException;
import com.proyecto.admin.utils.Alert;
import com.proyecto.entidades.Usuario;
import com.proyecto.porotos.UsuarioBeanRemote;

public class UserManager {

	private static UsuarioBeanRemote getBean() {
		try {
			return BeanManager.get(UsuarioBeanRemote.class);
		} catch (BeanNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static boolean create(Usuario u) {
		try {
			return getBean().create(u);
		}
		catch (Exception e) {
			Alert.error("Error", e.getMessage());
			e.printStackTrace();
		}
		return false;
	}

	public static Usuario update(String user, Usuario u) {
		try {
			return getBean().update(user, u);
		}
		catch (Exception e) {
			Alert.error("Error", e.getMessage());
			e.printStackTrace();
		}
		return null;
	}

	public static Usuario find(String user) {
		try {
			return getBean().find(user);
		}
		catch (Exception e) {
			Alert.error("Error", e.getMessage());
			e.printStackTrace();
		}
		return null;
	}

}
