package com.proyecto.admin.db;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import com.proyecto.admin.utils.Alert;
import com.proyecto.entidades.Usuario;
import com.proyecto.porotos.UsuarioBeanRemote;

public class UserManager {

	private static UsuarioBeanRemote bean = null;

	private static boolean isBeanGTG() {
		return bean != null;
	}

	private static boolean findBean() {
		try {
			bean = (UsuarioBeanRemote) InitialContext.doLookup("ProyectoPorotos/UsuarioBean!com.proyecto.porotos.UsuarioBeanRemote");
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

	public static boolean create(Usuario u) {
		findBeanifNull();

		try {
			bean.crear(u);
			return true;
		}
		catch (Exception e) {
			Alert.error("Error", e.getMessage());
			e.printStackTrace();
			return false;
		}
	}

	public static boolean update(String user, Usuario u) {
		findBeanifNull();

		try {
			bean.modificar(user, u);
			return true;
		}
		catch (Exception e) {
			Alert.error("Error", e.getMessage());
			e.printStackTrace();
			return false;
		}
	}

	public static Usuario find(String user) {
		findBeanifNull();

		try {
			return bean.buscar(user);
		}
		catch (Exception e) {
			Alert.error("Error", e.getMessage());
			e.printStackTrace();
			return null;
		}
	}

}
