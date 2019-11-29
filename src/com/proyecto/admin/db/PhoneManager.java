package com.proyecto.admin.db;

import java.util.List;

import com.proyecto.admin.lang.BeanNotFoundException;
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

	public static boolean create(int numb) throws Exception {
		return getBean().create(numb);
	}

	public static Telefono update(int numb, int newNumb) throws Exception {
		return getBean().update(numb, newNumb);
	}

	public static boolean delete(int numb) throws Exception {
		return getBean().delete(numb);
	}

	public static Telefono find(int numb) throws Exception {
		return getBean().find(numb);
	}

	public static List<Telefono> getAll() throws Exception {
		return getBean().getAll();
	}

}
