package com.proyecto.admin.db;

import java.util.List;

import com.proyecto.admin.lang.BeanNotFoundException;
import com.proyecto.entidades.Localidad;
import com.proyecto.porotos.LocalidadBeanRemote;

public class LocationManager {

	private static LocalidadBeanRemote getBean() {
		try {
			return BeanManager.get(LocalidadBeanRemote.class);
		} catch (BeanNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static boolean create(String name) throws Exception {
		return getBean().create(name);
	}

	public static Localidad update(String name, String newName) throws Exception {
		return getBean().update(name, newName);
	}

	public static Localidad find(String name) throws Exception {
		return getBean().find(name);
	}

	public static List<Localidad> getAll() throws Exception {
		return getBean().getAll();
	}

}
