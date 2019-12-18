package com.proyecto.admin.db;

import java.util.List;

import com.proyecto.admin.lang.BeanNotFoundException;
import com.proyecto.entidades.Caracteristica;
import com.proyecto.entidades.TipoDato;
import com.proyecto.porotos.CaracteristicaBeanRemote;

public class CharacteristicManager {

	private static CaracteristicaBeanRemote getBean() {
		try {
			return BeanManager.get(CaracteristicaBeanRemote.class);
		} catch (BeanNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static boolean create(String label, TipoDato datatype) throws Exception {
		return getBean().create(label, datatype);
	}

	public static Caracteristica update(String label, String newLabel, TipoDato datatype) throws Exception {
		return getBean().update(label, newLabel, datatype);
	}

	public static Caracteristica find(String label) throws Exception {
		return getBean().find(label);
	}

	public static List<Caracteristica> getAll() throws Exception {
		return  getBean().getAll();
	}

}
