package com.proyecto.admin.db;

import java.util.HashMap;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import com.proyecto.admin.lang.BeanNotFoundException;
import com.proyecto.admin.utils.Alert;
import com.proyecto.porotos.CaracteristicaBeanRemote;
import com.proyecto.porotos.DepartamentoBeanRemote;
import com.proyecto.porotos.FenomenoBeanRemote;
import com.proyecto.porotos.LocalidadBeanRemote;
import com.proyecto.porotos.ObservacionesBeanRemote;
import com.proyecto.porotos.RolBeanRemote;
import com.proyecto.porotos.TelefonoBeanRemote;
import com.proyecto.porotos.UsuarioBeanRemote;
import com.proyecto.porotos.ZonaBeanRemote;

public class BeanManager {

	// Don't worry lads, still keeping it simple
	private static final HashMap<Class<?>, Object> beans = new HashMap<Class<?>, Object>() {
		private static final long serialVersionUID = 1L;

		{
			put(RolBeanRemote.class, null);
			put(UsuarioBeanRemote.class, null);
			put(FenomenoBeanRemote.class, null);
			put(TelefonoBeanRemote.class, null);
			put(CaracteristicaBeanRemote.class, null);
			put(ObservacionesBeanRemote.class, null);
			put(LocalidadBeanRemote.class, null);
			put(DepartamentoBeanRemote.class, null);
			put(ZonaBeanRemote.class, null);
		}
	};

	private static boolean lookUp(Class<?> _class) {
		String bean = _class.getSimpleName().split("Remote")[0];
		String beanPath = "ProyectoPorotos/" + bean + "!com.proyecto.porotos." + bean + "Remote";

		try {
			Object _bean = InitialContext.doLookup(beanPath);

			if(_bean != null) {
				beans.put(_class, _bean);
			}
		}
		catch (NamingException ne) {
			Alert.error("Error", ne.getMessage());
			ne.printStackTrace();
		}

		return false;
	}

	private static Object getBean(Class<?> _class) {

		if(beans.get(_class) == null) {
			lookUp(_class);
		}

		return beans.get(_class);
	}

	public static <T> T get(Class<T> _class) throws BeanNotFoundException {

		if(!beans.containsKey(_class))
			throw new BeanNotFoundException("Unknown bean " + _class.getSimpleName());

		return _class.cast(getBean(_class));
	}

}
