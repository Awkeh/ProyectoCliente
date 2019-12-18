package com.proyecto.admin.db;

import java.util.List;

import com.proyecto.admin.lang.BeanNotFoundException;
import com.proyecto.entidades.Fenomeno;
import com.proyecto.porotos.FenomenoBeanRemote;

public class PhenomenaManager {

	private static FenomenoBeanRemote getBean() {
		try {
			return BeanManager.get(FenomenoBeanRemote.class);
		} catch (BeanNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static boolean create(Fenomeno f) throws Exception {
		return getBean().create(f);
	}

	public static Fenomeno update(String name, Fenomeno f) throws Exception {
		return getBean().update(name, f);
	}

	public static Fenomeno find(String name) throws Exception {
		return getBean().find(name);
	}

	public static List<Fenomeno> getAll() throws Exception {
		return getBean().getAll();
	}

}
