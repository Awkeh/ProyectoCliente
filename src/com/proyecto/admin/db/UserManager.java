package com.proyecto.admin.db;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import com.proyecto.admin.utils.Alert;
import com.proyecto.entidades.Usuario;
import com.proyecto.porotos.UsuarioBeanRemote;

public class UserManager {

	public static boolean create(Usuario u) {
		try {
			UsuarioBeanRemote bean = (UsuarioBeanRemote) InitialContext.doLookup("ProyectoPorotos/UsuarioBean!com.proyecto.porotos.UsuarioBeanRemote");
			bean.crear(u);
			return true;
		}
		catch (NamingException ne) {
			Alert.error("Error", ne.getMessage());
			ne.printStackTrace();
			return false;
		}
	}

	public static boolean update(String user, Usuario u) {
		try {
			UsuarioBeanRemote bean = (UsuarioBeanRemote) InitialContext.doLookup("ProyectoPorotos/UsuarioBean!com.proyecto.porotos.UsuarioBeanRemote");
			bean.modificar(user, u);
			return true;
		}
		catch (NamingException ne) {
			Alert.error("Error", ne.getMessage());
			ne.printStackTrace();
			return false;
		}
	}

	public static Usuario find(String user) {
		try {
			UsuarioBeanRemote bean = (UsuarioBeanRemote) InitialContext.doLookup("ProyectoPorotos/UsuarioBean!com.proyecto.porotos.UsuarioBeanRemote");
			return bean.buscar(user);
		}
		catch (NamingException ne) {
			Alert.error("Error", ne.getMessage());
			ne.printStackTrace();
			return null;
		}
	}

}
