package com.proyecto.admin.db;

import java.util.ArrayList;
import java.util.List;

import javax.naming.InitialContext;

import com.proyecto.porotos.CaracteristicaBeanRemote;
import com.proyecto.porotos.FenomenoBeanRemote;
import com.proyecto.porotos.RolBeanRemote;
import com.proyecto.porotos.UsuarioBeanRemote;
import com.proyecto.entidades.*;

public abstract class DBManager {
	
	public static boolean insertRol(String n, int p) {
		try {
			RolBeanRemote ro = (RolBeanRemote)InitialContext.doLookup("ProyectoPorotos/RolBean!com.proyecto.porotos.RolBeanRemote");
			ro.crearRol(n, p);
		}catch (Exception e) {
			System.out.println(e.getMessage());
			return false;
		}
		return true;
	}
	
	public static Rol getRol(String n) {
		Rol r=new Rol();
		try {
			RolBeanRemote ro = (RolBeanRemote)InitialContext.doLookup("ProyectoPorotos/RolBean!com.proyecto.porotos.RolBeanRemote");
			r=ro.buscarRol(n);
		}catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}
		return r;
	}
	
	public static List<Rol> getRoles() {
		List<Rol> l = new ArrayList<Rol>();
		try {
			RolBeanRemote ro = (RolBeanRemote)InitialContext.doLookup("ProyectoPorotos/RolBean!com.proyecto.porotos.RolBeanRemote");
			l=ro.obtenerRoles();
		}catch(Exception e) {
			System.out.println(e.getMessage());
			l=null;
		}
		return l;
	}

	public static boolean insertUser(Usuario u) {
		try {
			UsuarioBeanRemote ur = (UsuarioBeanRemote)InitialContext.doLookup("ProyectoPorotos/UsuarioBean!com.proyecto.porotos.UsuarioBeanRemote");
			ur.AltaUsuario(u.getNombre(),u.getApellido(),u.getEmail(),u.getUsuario(),u.isActivo(),u.getRol().getId());
		}catch(Exception e) {
			e.printStackTrace();
			System.out.println("Se rompio en el DBManager");
			return false;
		}
		return true;
	}

	public static boolean updateUser(Usuario u) {
		try {
			UsuarioBeanRemote ur = (UsuarioBeanRemote)InitialContext.doLookup("ProyectoPorotos/UsuarioBean!com.proyecto.porotos.UsuarioBeanRemote");
			ur.ModificarUsuario(u.getNombre(),u.getApellido(),u.getEmail(),u.getUsuario(),u.isActivo(),u.getRol().getId());
		}catch(Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public static boolean deleteUser(Usuario u) {
		// TODO delete user
		return true;
	}

	public static Usuario[] getUsers() {
		// TODO select * users
		return null;
	}

	public static Usuario searchUser() {
		// TODO search user

		Usuario u = new Usuario();
		u.setId(1L);
		u.setUsuario("duck");
		u.setNombre("Ente");
		u.setApellido("Pato");
		u.setEmail("patopato@patotero.com");

		return u;
	}

	public static void insertPhenomena(Fenomeno f) {
		try {
			FenomenoBeanRemote fr = (FenomenoBeanRemote)InitialContext.doLookup("ProyectoPorotos/FenomenoBean!com.proyecto.porotos.FenomenoBeanRemote");
			fr.AltaFenomeno(f);
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static List<Caracteristica> getCharact(){
		List<Caracteristica> l = new ArrayList<Caracteristica>();
		try {
			CaracteristicaBeanRemote cr = (CaracteristicaBeanRemote)InitialContext.doLookup("ProyectoPorotos/CaracteristicaBean!com.proyecto.porotos.CaracteristicaBeanRemote");
			l=cr.obtenerCaracteristica();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return l;
	}

}
