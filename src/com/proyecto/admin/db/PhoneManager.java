package com.proyecto.admin.db;

import java.util.List;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import com.proyecto.admin.utils.Alert;
import com.proyecto.entidades.Telefono;
import com.proyecto.porotos.TelefonoBeanRemote;

public class PhoneManager {

	private static TelefonoBeanRemote bean = null;

	private static boolean isBeanGTG() {
		return bean != null;
	}

	private static void findBean() {
		try {
			bean = (TelefonoBeanRemote) InitialContext.doLookup("ProyectoPorotos/TelefonoBean!com.proyecto.porotos.TelefonoBeanRemote");
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

	public static boolean create(int numb) {
		findBeanifNull();

		try {
			return bean.create(numb);
		}
		catch (Exception e) {
			Alert.error("Error", e.getMessage());
			e.printStackTrace();
		}
		return false;
	}

	public static Telefono update(int numb, int newNumb) {
		findBeanifNull();

		try {
			return bean.update(numb, newNumb);
		}
		catch (Exception e) {
			Alert.error("Error", e.getMessage());
			e.printStackTrace();
		}
		return null;
	}

	public static Telefono find(int numb) {
		findBeanifNull();

		try {
			return bean.find(numb);
		}
		catch (Exception e) {
			Alert.error("Error", e.getMessage());
			e.printStackTrace();
		}
		return null;
	}

	public static List<Telefono> getAll() {
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
