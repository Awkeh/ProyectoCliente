package com.proyecto.admin.db;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import com.proyecto.admin.utils.Alert;
import com.proyecto.entidades.Rol;
import com.proyecto.entidades.Usuario;
import com.proyecto.porotos.UsuarioBeanRemote;

public class UserManager {

	private static UsuarioBeanRemote bean = null;

	/**
	 * Is bean good to go?
	 * @return true if the bean is not null
	 */
	private static boolean isBeanGTG() {
		if(bean == null) {
			try {
				bean = (UsuarioBeanRemote) InitialContext.doLookup("ProyectoPorotos/UsuarioBean!com.proyecto.porotos.UsuarioBeanRemote");
			} catch (NamingException e) {
				Alert.error("Error de poroto", e.getMessage());
				e.printStackTrace();
			}
		}

		return bean != null;
	}

	public static void createUser(String nickname, String name, String surname, String email, Rol role, boolean isActive) {

		if(!isBeanGTG())
			return;

		Usuario u = new Usuario();
		u.setUsuario(nickname);
		u.setNombre(name);
		u.setApellido(surname);
		u.setEmail(email);
		u.setRol(role);
		u.setActivo(isActive);

		bean.AltaUsuario(u);
	}

	public static void updateUser(Usuario u) {
		if(!isBeanGTG())
			return;

		if(u.getId() != null) {
			bean.ModificarUsuario(u);
		}
	}

	public static Usuario findUser(String username) {
		if(!isBeanGTG())
			return null;

		return bean.BuscarUsuario(username);
	}

}
