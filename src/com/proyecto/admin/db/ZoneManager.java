package com.proyecto.admin.db;

import java.util.List;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import com.proyecto.admin.utils.Alert;
import com.proyecto.entidades.Zona;
import com.proyecto.porotos.ZonaBeanRemote;

public class ZoneManager {

	private static ZonaBeanRemote bean = null;

	private static boolean isBeanGTG() {
		return bean != null;
	}

	private static void findBean() {
		try {
			bean = (ZonaBeanRemote) InitialContext.doLookup("ProyectoPorotos/ZonaBean!com.proyecto.porotos.ZonaBeanRemote");
		}
		catch (NamingException ne) {
			Alert.error("Error", ne.getMessage());
			ne.printStackTrace();
		}
	}

	private static void findBeanifNull() {
		if(!isBeanGTG()) {
			findBean();
		}
	}

	public static boolean create(String name) {
		findBeanifNull();

		try {
			return bean.create(name);
		}
		catch (Exception e) {
			Alert.error("Error", e.getMessage());
			e.printStackTrace();
		}
		return false;
	}

	public static Zona update(String name, String newName) {
		findBeanifNull();

		try {
			return bean.update(name, newName);
		}
		catch (Exception e) {
			Alert.error("Error", e.getMessage());
			e.printStackTrace();
		}
		return null;
	}

	public static Zona find(String name) {
		findBeanifNull();

		try {
			return bean.find(name);
		}
		catch (Exception e) {
			Alert.error("Error", e.getMessage());
			e.printStackTrace();
		}
		return null;
	}

	public static List<Zona> getAll() {
		findBeanifNull();

		try {
			return bean.getAll();
		}
		catch (Exception e) {
			Alert.error("Error", e.getMessage());
			e.printStackTrace();
		}
		return null;
	}

}
