package com.proyecto.admin.db;

import java.util.List;

import com.proyecto.admin.lang.BeanNotFoundException;
import com.proyecto.admin.utils.Alert;
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

	public static boolean create(Fenomeno f) {
		try {
			return getBean().create(f);
		}
		catch(Exception e) {
			Alert.error("Error", e.getMessage());
			e.printStackTrace();
			return false;
		}
	}

	public static Fenomeno update(String name, Fenomeno f) {
		try {
			return getBean().update(name, f);
		}
		catch(Exception e) {
			Alert.error("Error", e.getMessage());
			e.printStackTrace();
			return null;
		}
	}

	public static Fenomeno find(String name) {
		try {
			return getBean().find(name);
		}
		catch(Exception e) {
			Alert.error("Error", e.getMessage());
			e.printStackTrace();
			return null;
		}
	}

	public static List<Fenomeno> getAll() {
		try {
			return getBean().getAll();
		}
		catch(Exception e) {
			Alert.error("Error", e.getMessage());
			e.printStackTrace();
			return null;
		}
	}

}
