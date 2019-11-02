package com.proyecto.admin.screens;

import java.awt.Color;
import java.awt.Dimension;
import java.util.Random;

import javax.swing.JButton;

public class ZonesScreen extends Screen {

	private static final long serialVersionUID = 1L;

	public ZonesScreen() {
		setBackground(
			new Color(
				0xFF + new Random().nextInt(0xFFFFFF)
			)
		);
		setMinimumSize(new Dimension(1920, 1080));
		for(int i = 0; i < 5; i++) {
			add(new JButton("button " + i));
		}
	}

}

