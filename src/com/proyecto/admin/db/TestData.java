package com.proyecto.admin.db;

import java.awt.BorderLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JProgressBar;
import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;

import com.proyecto.admin.components.ImageView;
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

	private static JFrame splash;
	private static ImageView duck;
	private static JProgressBar progressBar;
	private static SwingWorker<Void, Integer> worker;
	private static Float percent = 0f;
	/*
	 * Total entidades: 45
	 * Total entidades que se pueden crear: 44
	 * Total entidades cuyos bean funcionan: 42
	 */
	private static final float ENTITIES = 42f;

	private static void createAndShowSplash() {

		duck = new ImageView(TestData.class.getResource("/ducko.png"));
		duck.setFit(ImageView.FIT);

		progressBar = new JProgressBar();

		splash = new JFrame();
		splash.add(duck);
		splash.add(progressBar, BorderLayout.SOUTH);

		splash.setSize(452, 302);
		splash.setResizable(false);
		splash.setUndecorated(true);
		splash.setLocationRelativeTo(null);
		splash.setAlwaysOnTop(true);
		splash.setVisible(true);
	}

	private static void disposeSplash() {
		worker.cancel(true);
		splash.dispose();
		splash = null;
		duck = null;
		progressBar = null;
		worker = null;
		percent = null;

		// collect dat shit
		System.gc();
	}

	private static void createWorker() {
		worker = new SwingWorker<Void, Integer>() {

			protected Void doInBackground() throws Exception {

				while(!isCancelled() && (progressBar == null ? false : progressBar.getValue() < 100f)) {

					int pbVal = progressBar.getValue();

					if(percent != pbVal) {
						publish(Math.round(percent));
					}

					Thread.sleep(1);
				}

				return null;
			}

			protected void process(List<Integer> chunks) {
				if(progressBar != null) {
					progressBar.setValue(
						chunks.get(
							chunks.size() - 1
						)
					);
				}
			}

			protected void done() {
				SwingUtilities.invokeLater(
					new Runnable() {
						public void run() {
							try {
								/* Shhh.. */
								Thread.sleep(1000);
							} catch (InterruptedException e) {
								e.printStackTrace();
							}

							disposeSplash();
						}
					}
				);
			}

		};
	}

	private static void entityCreated() {
		percent += 100f / ENTITIES;
	}

	public static void generate() {
		// Why the fuck not?
		createAndShowSplash();
		createWorker();
		worker.execute();

		SwingUtilities.invokeLater(
			new Runnable() {
				public void run() {
					createData();
				}
			}
		);
	}

	private static synchronized void createData() {

		// 10 puntos para griffindor por organizar tan bien las mascaras de bits
		try {
			RoleManager.create("Administrador", Integer.MAX_VALUE);
			entityCreated();

			RoleManager.create("Experto",
				Rol.REVISAR_OBSERVACION 	 |
				Rol.ALTA_CARACTERISTICA 	 | Rol.ALTA_FENOMENO 	  | Rol.ALTA_OBSERVACION 	  | Rol.ALTA_TELEFONO 	  |
				Rol.BAJA_CARACTERISTICA 	 | Rol.BAJA_FENOMENO 	  | Rol.BAJA_OBSERVACION 	  | Rol.BAJA_TELEFONO 	  |
				Rol.MODIFICAR_CARACTERISTICA | Rol.MODIFICAR_FENOMENO | Rol.MODIFICAR_OBSERVACION | Rol.MODIFICAR_TELEFONO);
			entityCreated();

			RoleManager.create("Voluntario",   Rol.ALTA_OBSERVACION   | Rol.MODIFICAR_OBSERVACION                         );
			entityCreated();

			RoleManager.create("Dios", Integer.MAX_VALUE);
			entityCreated();
		}
		catch(Exception e) {
			e.printStackTrace();
			disposeSplash();
			return;
		}

		try {
			LocationManager.create("Las piedras");
			entityCreated();

			LocationManager.create("Durazno");
			entityCreated();

			LocationManager.create("Ciudad vieja");
			entityCreated();
		}
		catch (Exception e2) {
			e2.printStackTrace();
			disposeSplash();
			return;
		}

		try {
			List<Localidad> l = new ArrayList<Localidad>();
			l.add(LocationManager.find("Las piedras"));

			Departamento d = new Departamento();
			d.setNombre("Canelones");
			d.setLocalidades(l);
			DepartmentManager.create(d);
			entityCreated();

			l.clear();
			l.add(LocationManager.find("Durazno"));

			d.setNombre("Durazno");
			d.setLocalidades(l);
			DepartmentManager.create(d);
			entityCreated();

			l.clear();
			l.add(LocationManager.find("Ciudad vieja"));

			d.setNombre("Montevideo");
			d.setLocalidades(l);
			DepartmentManager.create(d);
			entityCreated();

			}
		catch (Exception e2) {
				e2.printStackTrace();
				disposeSplash();
				return;
			}

			try {
				List<Departamento> ds = new ArrayList<Departamento>();

				Zona z = new Zona();
				z.setNombre("Norte");
				z.setDepartamentos(ds);

				ZoneManager.create(z);
				entityCreated();

//				Departamento d = DepartmentManager.find("Durazno");

//				ds.add(d);

				z.setNombre("Centro");
				z.setDepartamentos(ds);

				ZoneManager.create(z);
				entityCreated();

				ds.clear();
//				ds.add(DepartmentManager.find("Canelones"));
//				ds.add(DepartmentManager.find("Montevideo"));

				z.setNombre("Sur");
				z.setDepartamentos(ds);

				ZoneManager.create(z);
				entityCreated();

				ds.clear();
				z.setNombre("Este");
				z.setDepartamentos(ds);
				ZoneManager.create(z);
				entityCreated();

				ds.clear();
				z.setNombre("Oeste");
				z.setDepartamentos(ds);
				ZoneManager.create(z);
				entityCreated();
			}
			catch (Exception e1) {
				e1.printStackTrace();
				disposeSplash();
				return;
			}

			try {
				Usuario u = new Usuario();
				u.setUsuario("quacker");
				u.setNombre("Try");
				u.setApellido("Hard");
				u.setEmail("duckduck@motherfucker.uy");
				u.setRol(RoleManager.find("Dios"));
				u.setActivo(true);

				UserManager.create(u);
				entityCreated();

				u.setUsuario("admin");
				u.setNombre("Admin");
				u.setApellido("istrador");
				u.setEmail("getBanned@nerd.uy");
				u.setRol(RoleManager.find("Administrador"));
				u.setActivo(true);

				UserManager.create(u);
				entityCreated();

				u.setUsuario("tutor");
				u.setNombre("Lads");
				u.setApellido("Lasses");
				u.setEmail("weDonKnoeShit@utec.uy");
				u.setRol(RoleManager.find("Experto"));
				u.setActivo(true);

				UserManager.create(u);
				entityCreated();

				u.setUsuario("unpibe");
				u.setNombre("Un");
				u.setApellido("Pibe");
				u.setEmail("pibe@cantina.uy");
				u.setRol(RoleManager.find("Voluntario"));
				u.setActivo(true);

				UserManager.create(u);
				entityCreated();
			}
			catch(Exception e) {
				e.printStackTrace();
				disposeSplash();
				return;
			}

			try {
				CharacteristicManager.create("Altitud", TipoDato.NUMERO);
				entityCreated();

				CharacteristicManager.create("Altura", TipoDato.NUMERO);
				entityCreated();

				CharacteristicManager.create("Ancho", TipoDato.NUMERO);
				entityCreated();

				CharacteristicManager.create("Densidad", TipoDato.NUMERO);
				entityCreated();

				CharacteristicManager.create("Diametro", TipoDato.NUMERO);
				entityCreated();

				CharacteristicManager.create("Duracion", TipoDato.FECHAHORA);
				entityCreated();

				CharacteristicManager.create("Forma", TipoDato.TEXTO);
				entityCreated();

				CharacteristicManager.create("Largo", TipoDato.NUMERO);
				entityCreated();

				CharacteristicManager.create("Masa", TipoDato.NUMERO);
				entityCreated();

				CharacteristicManager.create("Material", TipoDato.TEXTO);
				entityCreated();

				CharacteristicManager.create("Temperatura", TipoDato.NUMERO);
				entityCreated();

				CharacteristicManager.create("Velocidad angular", TipoDato.NUMERO);
				entityCreated();

				CharacteristicManager.create("Viscosidad", TipoDato.NUMERO);
				entityCreated();
			}
			catch(Exception e) {
				e.printStackTrace();
				disposeSplash();
				return;
			}

			try {
				PhoneManager.create(99999999);
				entityCreated();

				PhoneManager.create(99999998);
				entityCreated();

				PhoneManager.create(99999997);
				entityCreated();

				PhoneManager.create(99999996);
				entityCreated();

				PhoneManager.create(99999995);
				entityCreated();

				PhoneManager.create(99999994);
				entityCreated();

				PhoneManager.create(99999993);
				entityCreated();

				PhoneManager.create(99999992);
				entityCreated();

				PhoneManager.create(99999991);
				entityCreated();

				PhoneManager.create(99999990);
				entityCreated();
			}
			catch (Exception e) {
				e.printStackTrace();
				disposeSplash();
				return;
			}

//			try {
//				List<Caracteristica> c1 = new ArrayList<Caracteristica>();
//				c1.add(CharacteristicManager.find("Densidad"));
//				c1.add(CharacteristicManager.find("Masa"));
//				c1.add(CharacteristicManager.find("Temperatura"));
//				c1.add(CharacteristicManager.find("Viscosidad"));
//
//				List<Telefono> p1 = new ArrayList<Telefono>();
//				p1.add(PhoneManager.find(99999999));
//				p1.add(PhoneManager.find(99999998));
//				p1.add(PhoneManager.find(99999997));
//				p1.add(PhoneManager.find(99999996));
//				p1.add(PhoneManager.find(99999995));
//
//				Fenomeno f = new Fenomeno();
//				f.setNombre("Superfluido");
//				f.setDescripcion("Liquido cuya temperatura tiende a cero absoluto");
//				f.setCaracteristicas(c1);
//				f.setTelefonos(p1);
//
//				PhenomenaManager.create(f);
//				entityCreated();
//
//				c1.clear();
//				c1.add(CharacteristicManager.find("Diametro"));
//				c1.add(CharacteristicManager.find("Masa"));
//
//				p1.clear();
//				p1.add(PhoneManager.find(99999994));
//				p1.add(PhoneManager.find(99999993));
//				p1.add(PhoneManager.find(99999992));
//				p1.add(PhoneManager.find(99999991));
//				p1.add(PhoneManager.find(99999990));
//
//				f.setNombre("Granizo");
//				f.setDescripcion("Piedras de hielo que caen dadas las condiciones adecuadas en un dia de lluvia");
//				f.setCaracteristicas(c1);
//				f.setTelefonos(p1);
//
//				PhenomenaManager.create(f);
//				entityCreated();
//			}
//			catch (Exception e) {
//				e.printStackTrace();
//				disposeSplash();
//				return;
//			}
//
//			try {
//				Observacion o = new Observacion();
//				o.setDescripcion("Descripcion de la observacion");
//				o.setFechaRegistro(new Date());
//				o.setFechaRevision(o.getFechaRegistro());
//				o.setFenomeno(PhenomenaManager.getAll().get(0));
//				o.setFiabilidad(1f);
//				o.setLatitud(0f);
//				o.setLongitud(0f);
//				o.setLocalidad(LocationManager.find("Durazno"));
//				o.setReportero(UserManager.find("quacker"));
//				// FIXME: Observacion manager missing
//			}
//			catch(Exception e) {
//				e.printStackTrace();
//				disposeSplash();
//			}
	}

}
