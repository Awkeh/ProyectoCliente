package com.proyecto.admin.utils;

import javax.swing.JOptionPane;

public abstract class Alert {

	public static void msg(String title, String body) {
		JOptionPane.showMessageDialog(null, body, title, JOptionPane.PLAIN_MESSAGE);
	}

	public static void info(String title, String body) {
		JOptionPane.showMessageDialog(null, body, title, JOptionPane.INFORMATION_MESSAGE);
	}

	public static void warn(String title, String body) {
		JOptionPane.showMessageDialog(null, body, title, JOptionPane.WARNING_MESSAGE);
	}

	public static void error(String title, String body) {
		JOptionPane.showMessageDialog(null, body, title, JOptionPane.WARNING_MESSAGE);
	}

}
