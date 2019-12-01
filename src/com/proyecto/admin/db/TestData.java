package com.proyecto.admin.db;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.proyecto.entidades.Caracteristica;
import com.proyecto.entidades.Departamento;
import com.proyecto.entidades.Fenomeno;
import com.proyecto.entidades.Localidad;
import com.proyecto.entidades.Observacion;
import com.proyecto.entidades.Rol;
import com.proyecto.entidades.Telefono;
import com.proyecto.entidades.TipoDato;
import com.proyecto.entidades.Usuario;
import com.proyecto.entidades.Zona;

public class TestData {

	public static void generate() {
		// 10 puntos para griffindor por organizar tan bien las mascaras de bits
				RoleManager.create("Administrador", Integer.MAX_VALUE);
				RoleManager.create("Experto",
					Rol.REVISAR_OBSERVACION 	 |
					Rol.ALTA_CARACTERISTICA 	 | Rol.ALTA_FENOMENO 	  | Rol.ALTA_OBSERVACION 	  | Rol.ALTA_TELEFONO 	  |
					Rol.BAJA_CARACTERISTICA 	 | Rol.BAJA_FENOMENO 	  | Rol.BAJA_OBSERVACION 	  | Rol.BAJA_TELEFONO 	  |
					Rol.MODIFICAR_CARACTERISTICA | Rol.MODIFICAR_FENOMENO | Rol.MODIFICAR_OBSERVACION | Rol.MODIFICAR_TELEFONO);
				RoleManager.create("Voluntario",   Rol.ALTA_OBSERVACION   | Rol.MODIFICAR_OBSERVACION                         );
				RoleManager.create("Dios", Integer.MAX_VALUE);

				try {
					LocationManager.create("Las piedras");
					LocationManager.create("Durazno");
					LocationManager.create("Ciudad vieja");
				} catch (Exception e2) {
					e2.printStackTrace();
					return;
				}

				try {
					List<Localidad> l = new ArrayList<Localidad>();
					l.add(LocationManager.find("Las piedras"));

					Departamento d = new Departamento();
					d.setNombre("Canelones");
					d.setLocalidades(l);
					DepartmentManager.create(d);

					l.clear();
					l.add(LocationManager.find("Durazno"));

					d.setNombre("Durazno");
					d.setLocalidades(l);
					DepartmentManager.create(d);

					l.clear();
					l.add(LocationManager.find("Ciudad vieja"));

					d.setNombre("Montevideo");
					d.setLocalidades(l);
					DepartmentManager.create(d);

				} catch (Exception e2) {
					e2.printStackTrace();
					return;
				}

				try {
					List<Departamento> ds = new ArrayList<Departamento>();

					Zona z = new Zona();
					z.setNombre("Norte");
					z.setDepartamentos(ds);

					ds.add(DepartmentManager.find("Durazno"));

					z.setNombre("Centro");
					z.setDepartamentos(ds);

					ZoneManager.create(z);

					ds.clear();
					ds.add(DepartmentManager.find("Canelones"));
					ds.add(DepartmentManager.find("Montevideo"));

					z.setNombre("Sur");
					z.setDepartamentos(ds);

					ZoneManager.create(z);

					ds.clear();
					z.setNombre("Este");
					z.setDepartamentos(ds);
					ZoneManager.create(z);

					ds.clear();
					z.setNombre("Oeste");
					z.setDepartamentos(ds);
					ZoneManager.create(z);
				} catch (Exception e1) {
					e1.printStackTrace();
					return;
				}

				Usuario u = new Usuario();
				u.setUsuario("quacker");
				u.setNombre("Try");
				u.setApellido("Hard");
				u.setEmail("duckduck@motherfucker.uy");
				u.setRol(RoleManager.find("Dios"));
				u.setActivo(true);

				UserManager.create(u);

				u.setUsuario("admin");
				u.setNombre("Admin");
				u.setApellido("istrador");
				u.setEmail("getBanned@nerd.uy");
				u.setRol(RoleManager.find("Administrador"));
				u.setActivo(true);

				UserManager.create(u);

				u.setUsuario("tutor");
				u.setNombre("Lads");
				u.setApellido("Lasses");
				u.setEmail("weDonKnoeShit@utec.uy");
				u.setRol(RoleManager.find("Experto"));
				u.setActivo(true);

				UserManager.create(u);

				u.setUsuario("unpibe");
				u.setNombre("Un");
				u.setApellido("Pibe");
				u.setEmail("pibe@cantina.uy");
				u.setRol(RoleManager.find("Voluntario"));
				u.setActivo(true);

				UserManager.create(u);

				CharacteristicManager.create("Altitud", TipoDato.NUMERO);
				CharacteristicManager.create("Altura", TipoDato.NUMERO);
				CharacteristicManager.create("Ancho", TipoDato.NUMERO);
				CharacteristicManager.create("Densidad", TipoDato.NUMERO);
				CharacteristicManager.create("Diametro", TipoDato.NUMERO);
				CharacteristicManager.create("Duracion", TipoDato.FECHAHORA);
				CharacteristicManager.create("Forma", TipoDato.TEXTO);
				CharacteristicManager.create("Largo", TipoDato.NUMERO);
				CharacteristicManager.create("Masa", TipoDato.NUMERO);
				CharacteristicManager.create("Material", TipoDato.TEXTO);
				CharacteristicManager.create("Temperatura", TipoDato.NUMERO);
				CharacteristicManager.create("Velocidad angular", TipoDato.NUMERO);
				CharacteristicManager.create("Viscosidad", TipoDato.NUMERO);

				try {
					PhoneManager.create(99999999);
					PhoneManager.create(99999998);
					PhoneManager.create(99999997);
					PhoneManager.create(99999996);
					PhoneManager.create(99999995);
					PhoneManager.create(99999994);
					PhoneManager.create(99999993);
					PhoneManager.create(99999992);
					PhoneManager.create(99999991);
					PhoneManager.create(99999990);
				}
				catch (Exception e) {
					e.printStackTrace();
					return;
				}

				try {
					List<Caracteristica> c1 = new ArrayList<Caracteristica>();
					c1.add(CharacteristicManager.find("Densidad"));
					c1.add(CharacteristicManager.find("Masa"));
					c1.add(CharacteristicManager.find("Temperatura"));
					c1.add(CharacteristicManager.find("Viscosidad"));

					List<Telefono> p1 = new ArrayList<Telefono>();
					p1.add(PhoneManager.find(99999999));
					p1.add(PhoneManager.find(99999998));
					p1.add(PhoneManager.find(99999997));
					p1.add(PhoneManager.find(99999996));
					p1.add(PhoneManager.find(99999995));

					Fenomeno f = new Fenomeno();
					f.setNombre("Superfluido");
					f.setDescripcion("Liquido cuya temperatura tiende a cero absoluto");
					f.setCaracteristicas(c1);
					f.setTelefonos(p1);

					PhenomenaManager.create(f);

					c1.clear();
					c1.add(CharacteristicManager.find("Diametro"));
					c1.add(CharacteristicManager.find("Masa"));

					p1.clear();
					p1.add(PhoneManager.find(99999994));
					p1.add(PhoneManager.find(99999993));
					p1.add(PhoneManager.find(99999992));
					p1.add(PhoneManager.find(99999991));
					p1.add(PhoneManager.find(99999990));

					f.setNombre("Granizo");
					f.setDescripcion("Piedras de hielo que caen dadas las condiciones adecuadas en un dia de lluvia");
					f.setCaracteristicas(c1);
					f.setTelefonos(p1);

					PhenomenaManager.create(f);

					Observacion o = new Observacion();
					o.setDescripcion("Descripcion de la observacion");
					o.setFechaRegistro(new Date());
					o.setFechaRevision(o.getFechaRegistro());
					o.setFenomeno(PhenomenaManager.find("Granizo"));
					o.setFiabilidad(1f);
					o.setLatitud(0f);
					o.setLongitud(0f);
					o.setLocalidad(LocationManager.find("Durazno"));
					o.setReportero(UserManager.find("quacker"));
				} catch (Exception e) {
					e.printStackTrace();
					return;
				}
	}

}
