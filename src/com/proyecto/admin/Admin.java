package com.proyecto.admin;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.border.EmptyBorder;

import com.proyecto.admin.components.FlatButton;
import com.proyecto.admin.screens.Screen;

public class Admin {

	private JFrame frame;
	private JSplitPane splitPane;
	private JPanel menuPane, content;
	private JScrollPane contentPane;
	private JButton[] menuItems;

	public Admin() {
		initializeComponents();
		customizeComponents();
		layoutComponents();
		addListenersToComponents();
		setScreen(Screen.DUCK_SCREEN);
	}

	protected void initializeComponents() {
		frame = new JFrame("Control panel");
		splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
		menuPane = new JPanel(new GridBagLayout());
		content = new JPanel(new GridBagLayout());
		contentPane = new JScrollPane(content);
		menuItems = new FlatButton[] {
			new FlatButton("Usuarios"),
			new FlatButton("Fenomenos"),
			new FlatButton("Caracteristicas"),
			new FlatButton("Observaciones"),
			new FlatButton("Localidades"),
			new FlatButton("Departamentos"),
			new FlatButton("Zonas")
		};
	}

	protected void customizeComponents() {
		frame.setSize(640, 480);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		splitPane.setDividerSize(1);

		Color bg = new Color(0xFF373737);
		Color fg = new Color(0xFFEAEAEB);

		menuPane.setBackground(bg);

		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.BOTH;
		c.weightx = 1d;
		c.ipady = 10;

		for(int y = 0; y < menuItems.length; y++) {
			c.gridy = y;

			menuItems[y].setBorder(new EmptyBorder(10, 25, 10, 50));
			menuItems[y].setFont(SharedResources.getInstance().getFont());
			menuItems[y].setBackground(bg);
			menuItems[y].setForeground(fg);

			menuPane.add(menuItems[y], c);
		}
	}

	protected void layoutComponents() {
		frame.add(splitPane);
		splitPane.add(menuPane);
		splitPane.add(contentPane);
	}

	protected void addListenersToComponents() {
		menuItems[0].addActionListener(
			new ActionListener() {
				public void actionPerformed(ActionEvent e) { setScreen(Screen.USUERS_SCREEN); }
			}
		);
		menuItems[1].addActionListener(
			new ActionListener() {
				public void actionPerformed(ActionEvent e) { setScreen(Screen.PHENOMENA_SCREEN); }
			}
		);
		menuItems[2].addActionListener(
			new ActionListener() {
				public void actionPerformed(ActionEvent e) { setScreen(Screen.CHARACTERISTICS_SCREEN); }
			}
		);
		menuItems[3].addActionListener(
			new ActionListener() {
				public void actionPerformed(ActionEvent e) { setScreen(Screen.OBSERVATIONS_SCREEN); }
			}
		);
		menuItems[4].addActionListener(
			new ActionListener() {
				public void actionPerformed(ActionEvent e) { setScreen(Screen.LOCATIONS_SCREEN); }
			}
		);
		menuItems[5].addActionListener(
			new ActionListener() {
				public void actionPerformed(ActionEvent e) { setScreen(Screen.DEPARTMENTS_SCREEN); }
			}
		);
		menuItems[6].addActionListener(
			new ActionListener() {
				public void actionPerformed(ActionEvent e) { setScreen(Screen.ZONES_SCREEN); }
			}
		);
	}

	private void setScreen(int screen) {
		GridBagConstraints c = new GridBagConstraints();
		c.weightx = 1d;
		c.weighty = 1d;
		c.fill = GridBagConstraints.BOTH;

		content.removeAll();
		content.add(Screen.get(screen), c);

		// Swing at its best
		content.repaint();
		contentPane.validate();

		// Gotta clean that shit
		System.gc();
	}

	public void show() {
		if(!frame.isVisible())
			frame.setVisible(true);
	}

	public void hide() {
		if(frame.isVisible())
			frame.setVisible(false);
	}
}
