package com.proyecto.admin.db;

import java.util.List;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import com.proyecto.admin.utils.Alert;
import com.proyecto.entidades.Caracteristica;
import com.proyecto.entidades.TipoDato;
import com.proyecto.porotos.CaracteristicaBeanRemote;

public class CharManager {

	private static CaracteristicaBeanRemote bean = null;

	private static boolean isBeanGTG() {
		return bean != null;
	}

	private static void findBean() {
		try {
			bean = (CaracteristicaBeanRemote) InitialContext.doLookup("ProyectoPorotos/CaracteristicaBean!com.proyecto.porotos.CaracteristicaBeanRemote");
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

	public static boolean create(String label, TipoDato datatype) {
		findBeanifNull();

		try {
			return bean.create(label, datatype);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public static List<Caracteristica> getAll() {
		try {
			return  bean.getAll();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
