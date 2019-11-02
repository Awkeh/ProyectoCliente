package com.proyecto.admin;

import java.awt.Font;

public class SharedResources {

	private static SharedResources instance = null;

	private Font font;

	private SharedResources() {
		font = new Font("Calibri", Font.PLAIN, 18);
	}

	public static SharedResources getInstance() {
		if(instance == null) {
			instance = new SharedResources();
		}

		return instance;
	}

	public Font getFont() {
		return font;
	}

}
