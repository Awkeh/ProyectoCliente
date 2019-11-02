package com.proyecto.admin.screens;

import java.awt.Color;
import java.util.Random;

import javax.swing.JLabel;

public class ObservationsScreen extends Screen {

	private static final long serialVersionUID = 1L;

	public ObservationsScreen() {
		setBackground(
			new Color(
				0xFF + new Random().nextInt(0xFFFFFF)
			)
		);
		add(new JLabel("ObservationsScreen"));
	}

}

