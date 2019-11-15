package com.proyecto.admin.db;

import java.util.List;

import com.proyecto.admin.lang.BeanNotFoundException;
import com.proyecto.admin.utils.Alert;
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

	public static boolean create(String name) {
		try {
			return getBean().create(name);
		}
		catch (Exception e) {
			Alert.error("Error", e.getMessage());
			e.printStackTrace();
		}
		return false;
	}

	public static Zona update(String name, String newName) {
		try {
			return getBean().update(name, newName);
		}
		catch (Exception e) {
			Alert.error("Error", e.getMessage());
			e.printStackTrace();
		}
		return null;
	}

	public static Zona find(String name) {
		try {
			return getBean().find(name);
		}
		catch (Exception e) {
			Alert.error("Error", e.getMessage());
			e.printStackTrace();
		}
		return null;
	}

	public static List<Zona> getAll() {
		try {
			return getBean().getAll();
		}
		catch (Exception e) {
			Alert.error("Error", e.getMessage());
			e.printStackTrace();
		}
		return null;
	}

}
