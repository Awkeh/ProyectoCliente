package com.proyecto.admin.db;

import java.util.List;

import com.proyecto.admin.lang.BeanNotFoundException;
import com.proyecto.entidades.Zona;
import com.proyecto.porotos.ZonaBeanRemote;

public class ZoneManager {

	private static ZonaBeanRemote getBean() {
		try {
			return BeanManager.get(ZonaBeanRemote.class);
		} catch (BeanNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static boolean create(Zona z) throws Exception {
		return getBean().create(z);
	}

	public static Zona update(String name, Zona z) throws Exception {
		return getBean().update(name, z);
	}

	public static Zona find(String name) throws Exception {
		return getBean().find(name);
	}

	public static List<Zona> getAll() throws Exception{
		return getBean().getAll();
	}

}
