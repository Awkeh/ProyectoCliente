package com.proyecto.admin.db;

import java.util.List;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import com.proyecto.admin.utils.Alert;
import com.proyecto.entidades.Fenomeno;
import com.proyecto.porotos.FenomenoBeanRemote;

/**
 * Phenomena manager class
 *
 */
public class PhenomenaManager {

	private static FenomenoBeanRemote bean = null;

	private static boolean isBeanGTG() {
		return bean != null;
	}

	private static boolean findBean() {
		try {
			bean = (FenomenoBeanRemote) InitialContext.doLookup("ProyectoPorotos/FenomenoBean!com.proyecto.porotos.FenomenoBeanRemote");
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

	public static boolean create(Fenomeno f) {
		findBeanifNull();

		try {
			return bean.create(f);
		}
		catch(Exception e) {
			Alert.error("Error", e.getMessage());
			e.printStackTrace();
			return false;
		}
	}

	public static Fenomeno update(String name, Fenomeno f) {
		findBeanifNull();

		try {
			return bean.update(name, f);
		}
		catch(Exception e) {
			Alert.error("Error", e.getMessage());
			e.printStackTrace();
			return null;
		}
	}

	public static Fenomeno find(String name) {
		findBeanifNull();

		try {
			return bean.find(name);
		}
		catch(Exception e) {
			Alert.error("Error", e.getMessage());
			e.printStackTrace();
			return null;
		}
	}

	public static List<Fenomeno> getAll() {
		findBeanifNull();

		try {
			return bean.getAll();
		}
		catch(Exception e) {
			Alert.error("Error", e.getMessage());
			e.printStackTrace();
			return null;
		}
	}

}
