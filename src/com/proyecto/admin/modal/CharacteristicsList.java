package com.proyecto.admin.modal;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingUtilities;

import com.proyecto.admin.SharedResources;
import com.proyecto.admin.db.CharacteristicManager;
import com.proyecto.admin.utils.Alert;
import com.proyecto.entidades.Caracteristica;
import com.proyecto.entidades.Fenomeno;

public class CharacteristicsList extends JDialog {

	private static final long serialVersionUID = 1L;

	private JScrollPane listWrapper;
	private JPanel inputArea;
	private DefaultListModel<String> model;
	private JList<String> list;
	private JButton btnAdd;
	private JComboBox<String> charChooser;
	private Fenomeno f;
	private List<Caracteristica> characteristics = null;

	public CharacteristicsList(Fenomeno f) {
		this.f = f == null ? new Fenomeno() : f;

		initializeComponents();
		customizeComponents();
		layoutComponents();
		addListenersToComponents();

		pack();
		setLocationRelativeTo(null);
	}

	private void initializeComponents() {
		model = new DefaultListModel<String>();
		list = new JList<String>(model);
		listWrapper = new JScrollPane();
		charChooser = new JComboBox<String>();
		btnAdd = new JButton("Agregar");
		inputArea = new JPanel(new GridBagLayout());
	}

	private void customizeComponents() {
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setTitle("Caracteristicas de " + (f.getNombre() == null ? "fenomeno" : f.getNombre()));
		setModal(true);
		setLayout(new BorderLayout());

		Font font = SharedResources.getInstance().getFont();

		list.setFont(font);
		charChooser.setFont(font);
		btnAdd.setFont(font);

		updateCharChooser();
		updateList();
	}

	private void layoutComponents() {
		listWrapper.getViewport().add(list);

		GridBagConstraints c = new GridBagConstraints();

		c.gridx = 0;
		c.gridy = 0;
		c.weightx = 0.7d;
		c.fill = GridBagConstraints.HORIZONTAL;
		inputArea.add(charChooser, c);

		c.gridx = 1;
		c.weightx = 0.3d;
		inputArea.add(btnAdd, c);

		add(listWrapper, BorderLayout.CENTER);
		add(inputArea, BorderLayout.SOUTH);
	}

	private void addListenersToComponents() {
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addToList((String) charChooser.getSelectedItem());
			}
		});

		list.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent e) {

				if(e.getKeyCode() == KeyEvent.VK_DELETE) {
					if(list.getSelectedValue() != null) {
						removeFromList(list.getSelectedValue());
					}
				}

			}
		});

		list.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if(SwingUtilities.isLeftMouseButton(e)) {
					if(e.getClickCount() == 2) {
						if(list.getSelectedValue() != null) {
							removeFromList(list.getSelectedValue());
						}
					}
				}
			}
		});
	}

	private void updateList() {

		List<Caracteristica> data = f.getCaracteristicas() == null ? new ArrayList<Caracteristica>() : f.getCaracteristicas();
		model.removeAllElements();

		for(Caracteristica c : data) {
			model.addElement(c.getEtiqueta());
		}

		list.validate();
	}

	private void updateCharChooser() {
		try {
			if(characteristics == null) {
				characteristics = CharacteristicManager.getAll();
			}

			charChooser.removeAllItems();

			for(Caracteristica c : characteristics) {
				charChooser.addItem(c.getEtiqueta());
			}

			charChooser.validate();

		} catch (Exception e) {
			e.printStackTrace();
			Alert.error("Error", e.getMessage());
		}
	}

	private void addToList(String val) {

		if(characteristics == null) {
			updateCharChooser();
		}

		if(f.getCaracteristicas() == null) {
			f.setCaracteristicas(new ArrayList<Caracteristica>());
		}

		for(Caracteristica c : f.getCaracteristicas()) {
			if(c.getEtiqueta().equals(val)) {
				Alert.warn("Aviso", "El fenomeno ya posee dicha caracteristica");
				return;
			}
		}

		for(Caracteristica c : characteristics) {
			if(c.getEtiqueta().equals(val)) {
				f.getCaracteristicas().add(c);
				break;
			}
		}

		updateList();
	}

	private void removeFromList(String val) {

		if(characteristics == null) {
			updateCharChooser();
		}

		if(f.getCaracteristicas() == null) {
			f.setCaracteristicas(new ArrayList<Caracteristica>());
		}

		for(Caracteristica c : f.getCaracteristicas()) {
			if(c.getEtiqueta().equals(val)) {
				f.getCaracteristicas().remove(c);
				break;
			}
		}

		updateList();
	}

}
