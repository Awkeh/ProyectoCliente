package com.proyecto.admin.screens;

import java.awt.BorderLayout;

import com.proyecto.admin.components.ImageView;

public class DuckScreen extends Screen {

	private static final long serialVersionUID = 1L;
	private ImageView iv;

	public DuckScreen() {
		setLayout(new BorderLayout());

		iv = new ImageView(
			getClass().getResource("/ducko.png")
		);
		iv.setFit(ImageView.FIT);

		add(iv);
	}

}