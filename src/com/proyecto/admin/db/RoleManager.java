package com.proyecto.admin.db;

import java.util.List;

import com.proyecto.admin.lang.BeanNotFoundException;
import com.proyecto.entidades.Rol;
import com.proyecto.porotos.RolBeanRemote;

public class RoleManager {

	private static RolBeanRemote getBean() {
		try {
			return BeanManager.get(RolBeanRemote.class);
		} catch (BeanNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static boolean create(String name, int permissions) throws Exception {
		return getBean().create(name, permissions);
	}

	public static Rol update(String roleName, int newPermissions) throws Exception {
		return getBean().update(roleName, newPermissions);
	}

	public static Rol find(String name) throws Exception {
		return getBean().find(name);
	}

	public static List<Rol> getAll() throws Exception {
		return getBean().getAll();
	}

}
