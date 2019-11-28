package com.proyecto.admin.screens;

import javax.swing.JPanel;

public abstract class Screen extends JPanel {

	private static final long serialVersionUID = 1L;
	private static final Screen[] screens = new Screen[] { null, null, null, null, null, null, null, null };
	public static final int DUCK_SCREEN = 0;
	public static final int USUERS_SCREEN = 1;
	public static final int PHENOMENA_SCREEN = 2;
	public static final int CHARACTERISTICS_SCREEN = 3;
	public static final int OBSERVATIONS_SCREEN = 4;
	public static final int LOCATIONS_SCREEN = 5;
	public static final int DEPARTMENTS_SCREEN = 6;
	public static final int ZONES_SCREEN = 7;

	private static Screen instanceOf(int screen) {
		switch(screen) {
			case DUCK_SCREEN:
				screens[screen] = new DuckScreen();
				break;
			case USUERS_SCREEN:
				screens[screen] = new UsersScreen();
				break;
			case PHENOMENA_SCREEN:
				screens[screen] = new PhenomenaScreen();
				break;
			case CHARACTERISTICS_SCREEN:
				screens[screen] = new CharacteristicsScreen();
				break;
			case OBSERVATIONS_SCREEN:
				screens[screen] = new ObservationsScreen();
				break;
			case LOCATIONS_SCREEN:
				screens[screen] = new LocationsScreen();
				break;
			case DEPARTMENTS_SCREEN:
				screens[screen] = new DepartmentsScreen();
				break;
			case ZONES_SCREEN:
				screens[screen] = new ZonesScreen();
				break;
		}
		return screens[screen];
	}

	public static Screen get(int screen) {
		if(screen >= 0 && screen < screens.length) {
			return screens[screen] == null ? instanceOf(screen) : screens[screen];
		}
		return null;
	}
}