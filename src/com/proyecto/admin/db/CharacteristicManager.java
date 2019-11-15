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

	public static boolean create(String label, TipoDato datatype) {
		try {
			return getBean().create(label, datatype);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public static List<Caracteristica> getAll() {
		try {
			return  getBean().getAll();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
