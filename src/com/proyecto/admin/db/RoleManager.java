package com.proyecto.admin.db;

import java.util.List;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import com.proyecto.admin.utils.Alert;
import com.proyecto.entidades.Rol;
import com.proyecto.porotos.RolBeanRemote;

public class RoleManager {

	public static boolean create(String name, int permissions) {
		try {
			RolBeanRemote bean = (RolBeanRemote) InitialContext.doLookup("ProyectoPorotos/RolBean!com.proyecto.porotos.RolBeanRemote");
			bean.crearRol(name, permissions);
			return true;
		}
		catch (NamingException ne) {
			Alert.error("Error", ne.getMessage());
			ne.printStackTrace();
			return false;
		}
	}

	public static boolean update(String roleName, int newPermissions) {
		try {
			RolBeanRemote bean = (RolBeanRemote) InitialContext.doLookup("ProyectoPorotos/RolBean!com.proyecto.porotos.RolBeanRemote");
			bean.modificarRol(roleName, newPermissions);
			return true;
		}
		catch (NamingException ne) {
			Alert.error("Error", ne.getMessage());
			ne.printStackTrace();
			return false;
		}
	}

	public static boolean find(String name) {
		try {
			RolBeanRemote bean = (RolBeanRemote) InitialContext.doLookup("ProyectoPorotos/RolBean!com.proyecto.porotos.RolBeanRemote");
			bean.buscarRol(name);
			return true;
		}
		catch (NamingException ne) {
			Alert.error("Error", ne.getMessage());
			ne.printStackTrace();
			return false;
		}
	}

	public static List<Rol> getAll() {
		try {
			RolBeanRemote bean = (RolBeanRemote) InitialContext.doLookup("ProyectoPorotos/RolBean!com.proyecto.porotos.RolBeanRemote");
			return bean.obtenerRoles();
		}
		catch (NamingException ne) {
			Alert.error("Error", ne.getMessage());
			ne.printStackTrace();
			return null;
		}
	}

}
