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

	private static void findBean() {
		try {
			bean = (RolBeanRemote) InitialContext.doLookup("ProyectoPorotos/RolBean!com.proyecto.porotos.RolBeanRemote");
		}
		catch (NamingException ne) {
			Alert.error("Error", ne.getMessage());
			ne.printStackTrace();
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
			return bean.create(name, permissions);
		}
		catch (Exception e) {
			Alert.error("Error", e.getMessage());
			e.printStackTrace();
		}
		return false;
	}

	public static Rol update(String roleName, int newPermissions) {
		findBeanifNull();

		try {
			return bean.update(roleName, newPermissions);
		}
		catch (Exception e) {
			Alert.error("Error", e.getMessage());
			e.printStackTrace();
		}
		return null;
	}

	public static Rol find(String name) {
		findBeanifNull();

		try {
			return bean.find(name);
		}
		catch (Exception e) {
			Alert.error("Error", e.getMessage());
			e.printStackTrace();
		}
		return null;
	}

	public static List<Rol> getAll() {
		findBeanifNull();

		try {
			return bean.getAll();
		}
		catch (Exception e) {
			Alert.error("Error", e.getMessage());
			e.printStackTrace();
		}
		return null;
	}

}
