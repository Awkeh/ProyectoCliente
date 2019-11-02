package com.proyecto.admin.db;

import java.util.List;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import com.proyecto.admin.utils.Alert;
import com.proyecto.entidades.Rol;
import com.proyecto.porotos.RolBeanRemote;

public class RoleManager {

	private static RolBeanRemote bean = null;

	/**
	 * Is bean good to go?
	 * @return true if the bean is not null
	 */
	private static boolean isBeanGTG() {
		if(bean == null) {
			try {
				bean = (RolBeanRemote) InitialContext.doLookup("ProyectoPorotos/RolBean!com.proyecto.porotos.RolBeanRemote");
			} catch (NamingException e) {
				Alert.error("Error de poroto", e.getMessage());
				e.printStackTrace();
			}
		}

		return bean != null;
	}

	/**
	 *
	 * @param nombre Role name
	 * @param permisos bitmask formed out of Rol class' flags
	 */
	public static void createRole(String nombre, int permisos) {
		if(!isBeanGTG())
			return;

		bean.crearRol(nombre, permisos);
	}

	/**
	 *
	 * @param nombre Role name
	 * @param permisos bitmask formed out of Rol class' flags
	 */
	public static void updateRole(String nombre, int permisos) {
		if(!isBeanGTG())
			return;

		bean.modificarRol(nombre, permisos);
	}

	public static Rol findRole(String nombre) {
		if(!isBeanGTG())
			return null;

		return bean.buscarRol(nombre);
	}

	public static List<Rol> getRoles() {
		if(!isBeanGTG())
			return null;

		return bean.obtenerRoles();
	}

}
