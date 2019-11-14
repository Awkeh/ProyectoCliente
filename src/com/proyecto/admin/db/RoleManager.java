package com.proyecto.admin.db;

import java.util.List;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import com.proyecto.admin.utils.Alert;
import com.proyecto.entidades.Rol;
import com.proyecto.porotos.RolBeanRemote;

public class RoleManager {

	private static RolBeanRemote bean = null;

	private static boolean isBeanGTG() {
		return bean != null;
	}

	private static boolean findBean() {
		try {
			bean = (RolBeanRemote) InitialContext.doLookup("ProyectoPorotos/RolBean!com.proyecto.porotos.RolBeanRemote");
			return true;
		}
		catch (NamingException ne) {
			Alert.error("Error", ne.getMessage());
			ne.printStackTrace();
			return false;
		}
	}

	private static void findBeanifNull() {
		if(!isBeanGTG()) {
			findBean();
		}
	}

	public static boolean create(String name, int permissions) {
		findBeanifNull();

		try {
			bean.crearRol(name, permissions);
			return true;
		}
		catch (Exception e) {
			Alert.error("Error", e.getMessage());
			e.printStackTrace();
			return false;
		}
	}

	public static boolean update(String roleName, int newPermissions) {
		findBeanifNull();

		try {
			bean.modificarRol(roleName, newPermissions);
			return true;
		}
		catch (Exception e) {
			Alert.error("Error", e.getMessage());
			e.printStackTrace();
			return false;
		}
	}

	public static boolean find(String name) {
		findBeanifNull();

		try {
			bean.buscarRol(name);
			return true;
		}
		catch (Exception e) {
			Alert.error("Error", e.getMessage());
			e.printStackTrace();
			return false;
		}
	}

	public static List<Rol> getAll() {
		findBeanifNull();

		try {
			return bean.obtenerRoles();
		}
		catch (Exception e) {
			Alert.error("Error", e.getMessage());
			e.printStackTrace();
			return null;
		}
	}

}
