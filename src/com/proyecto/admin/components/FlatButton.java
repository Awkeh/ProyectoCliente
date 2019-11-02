package com.proyecto.admin.components;

import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.font.LineMetrics;

import javax.swing.ButtonModel;
import javax.swing.JButton;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class FlatButton extends JButton implements ChangeListener {

	private static final long serialVersionUID = 1L;
	private static final byte HOVERED = 1;
	private static final byte ARMED = 2;
	byte state = 0;

	public FlatButton(String text) {
		super(text);
		getModel().addChangeListener(this);
	}

	public void paintComponent(Graphics g) {
		Color bg;

		if(state == ARMED)
			bg = getBackground().darker();
		else if(state == HOVERED)
			bg = getBackground().brighter();
		else
			bg = getBackground();

		g.setColor(bg);
		g.fillRect(0, 0, getWidth(), getHeight());

		g.setColor(getForeground());
		g.setFont(getFont());

		FontMetrics fm = g.getFontMetrics();
		LineMetrics lm = fm.getLineMetrics(getText(), g);

		int height = getHeight() - getInsets().top - getInsets().bottom;
		int strX = getInsets().left;
		// Mathematics are all about aproximations not exact reasults, fuck off
		int strY = (int) (0.6125d * height + 0.5d * lm.getHeight());

		g.drawString(getText(), strX, strY);
	}

	public void stateChanged(ChangeEvent e) {
		ButtonModel m = getModel();

		if(m.isArmed()) {
			state = ARMED;
		} else
		if(m.isRollover()) {
			state = HOVERED;
		}
		else {
			state = 0;
		}
	}

}
