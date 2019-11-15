package com.proyecto.admin.db;

import java.util.List;

import com.proyecto.admin.lang.BeanNotFoundException;
import com.proyecto.admin.utils.Alert;
import com.proyecto.entidades.Telefono;
import com.proyecto.porotos.TelefonoBeanRemote;

public class PhoneManager {

	private static TelefonoBeanRemote getBean() {
		try {
			return BeanManager.get(TelefonoBeanRemote.class);
		} catch (BeanNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static boolean create(int numb) {
		try {
			return getBean().create(numb);
		}
		catch (Exception e) {
			Alert.error("Error", e.getMessage());
			e.printStackTrace();
		}
		return false;
	}

	public static Telefono update(int numb, int newNumb) {
		try {
			return getBean().update(numb, newNumb);
		}
		catch (Exception e) {
			Alert.error("Error", e.getMessage());
			e.printStackTrace();
		}
		return null;
	}

	public static Telefono find(int numb) {
		try {
			return getBean().find(numb);
		}
		catch (Exception e) {
			Alert.error("Error", e.getMessage());
			e.printStackTrace();
		}
		return null;
	}

	public static List<Telefono> getAll() {
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
