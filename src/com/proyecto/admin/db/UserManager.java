package com.proyecto.admin.db;

import com.proyecto.admin.lang.BeanNotFoundException;
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

	public static boolean create(Usuario u) throws Exception {
		return getBean().create(u);
	}

	public static Usuario update(String user, Usuario u) throws Exception {
		return getBean().update(user, u);
	}

	public static Usuario find(String user) throws Exception {
		return getBean().find(user);
	}

}
