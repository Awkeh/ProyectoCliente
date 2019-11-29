package com.proyecto.admin.screens;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import com.proyecto.admin.SharedResources;
import com.proyecto.admin.db.PhenomenaManager;
import com.proyecto.admin.modal.PhoneList;
import com.proyecto.admin.utils.Alert;
import com.proyecto.admin.utils.StringUtils;
import com.proyecto.entidades.Fenomeno;

public class PhenomenaScreen extends Screen {

	private static final long serialVersionUID = 1L;

	private JTabbedPane tabbyBoi;
	private JPanel form, searchPane, btnGroup;
	private JLabel labId, labName, labDesc;
	private JTextField fieldId, fieldName;
	private JTextArea fieldDesc;
	private JButton btnTel, btnChar, btnLoad, btnSave, btnClear;
	private Fenomeno phenom;
	private PhoneList ph;

	public PhenomenaScreen() {
		initializeComponents();
		customizeComponents();
		layoutComponents();
		addListenersToComponents();
	}

	private void initializeComponents() {
		tabbyBoi = new JTabbedPane();

		form = new JPanel(new GridBagLayout());
		searchPane = new JPanel();
		btnGroup = new JPanel();

		labId = new JLabel("ID");
		labName = new JLabel("Nombre");
		labDesc = new JLabel("Descripcion");

		fieldId = new JTextField();
		fieldName = new JTextField();

		fieldDesc = new JTextArea();

		btnTel = new JButton("Ver telefonos");
		btnChar = new JButton("Ver caracteristicas");
		btnLoad = new JButton("Cargar");
		btnSave = new JButton("Guardar");
		btnClear = new JButton("Limpiar");

		phenom = new Fenomeno();
	}

	private void customizeComponents() {
		setLayout(new BorderLayout());

		form.setBorder(new EmptyBorder(25, 25, 25, 25));

		Font f = SharedResources.getInstance().getFont();

		labId.setFont(f);
		labName.setFont(f);
		labDesc.setFont(f);

		fieldId.setFont(f);
		fieldId.setEnabled(false);
		fieldName.setFont(f);
		fieldDesc.setFont(f);
		fieldDesc.setWrapStyleWord(true);

		btnTel.setFont(f);
		btnChar.setFont(f);
		btnLoad.setFont(f);
		btnSave.setFont(f);
		btnClear.setFont(f);
	}

	private void layoutComponents() {
		add(tabbyBoi);

		tabbyBoi.addTab("ABM de fenomenos", form);
		tabbyBoi.addTab("Lista de fenomenos", searchPane);

		btnGroup.add(btnLoad);
		btnGroup.add(btnSave);
		btnGroup.add(btnClear);

		GridBagConstraints c = new GridBagConstraints();

		// Common constraints
		c.gridwidth = 1;
		c.gridheight = 1;
		c.insets = new Insets(2, 2, 2, 2);

		// Label constraints
		c.anchor = GridBagConstraints.WEST;
		c.fill = GridBagConstraints.NONE;
		c.weightx = 0.1d;
		c.weighty = 0.2d;

		c.gridx = 0;
		c.gridy = 0;
		form.add(labId, c);

		c.gridx = 2;
		form.add(labName, c);

		c.gridx = 0;
		c.gridy = 1;
		form.add(labDesc, c);

		// Fields constraints
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 0.9d;

		c.gridx = 1;
		c.gridy = 0;
		form.add(fieldId, c);

		c.gridx = 3;
		form.add(fieldName, c);

		// FIXME resize behavior issues
		c.gridx = 0;
		c.gridy = 2;
		c.gridwidth = 4;
		c.weighty = 0.4d;
		c.fill = GridBagConstraints.BOTH;
		form.add(fieldDesc, c);

		c.gridy = 3;
		c.gridwidth = 2;
		c.weighty = 0.2;
		c.fill = GridBagConstraints.HORIZONTAL;
		form.add(btnTel, c);

		c.gridx = 2;
		form.add(btnChar, c);

		// Button group constraints
		c.anchor = GridBagConstraints.EAST;
		c.fill = GridBagConstraints.NONE;
		c.gridwidth = 4;
		c.gridy = 5;
		c.weightx = 1;
		c.gridx = 0;
		form.add(btnGroup, c);
	}

	private void addListenersToComponents() {
		btnTel.addActionListener(
			new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					showPhoneDialog();
				}
			}
		);

		btnLoad.addActionListener(
			new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					loadPhenomena();
				}
			}
		);

		btnSave.addActionListener(
			new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					savePhenomena();
				}
			}
		);

		btnChar.addActionListener(
			new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					showCharDialog();
				}
			}
		);
	}

	private void loadPhenomena() {
		if(!fieldName.getText().trim().isEmpty()) {
			phenom = PhenomenaManager.find(fieldName.getText());

			if(phenom != null) {
				fieldId.setText("" + phenom.getId());
				fieldName.setText(phenom.getNombre());
				fieldDesc.setText(phenom.getDescripcion());
			}
		}
	}

	private void savePhenomena() {
		// TODO: add thorough validation
		if (fieldName.getText().trim().isEmpty()) {
			Alert.warn("Campo nombre vacio", "Debe ingresar un nombre");
		}
		else if (fieldDesc.getText().trim().isEmpty()) {
			Alert.warn("Campo descripcion vacio", "Debe ingresar una descripcion");
		}
		else if (!StringUtils.isNameValid(fieldName.getText())) {
			Alert.warn("Nombre invalido", "Ingrese un nombre valido");
		}
		else {
			String oldName = phenom.getNombre();
			phenom.setNombre(fieldName.getText());
			phenom.setDescripcion(fieldDesc.getText());

			// FIXME: Creating / updating Phenomenas will throw persistence errors caused by the relations of the entity. It has been tested, there's no doubt. But There's no explanation as to why exactly the relations cause this persistence exception

			if(fieldId.getText().isEmpty()) {

//				phenom.setTelefonos(null);
//				phenom.setCaracteristicas(null);

				if(PhenomenaManager.create(phenom)) {
					Fenomeno f = PhenomenaManager.find(phenom.getNombre());

					if(f != null) {
						fieldId.setText("" + f.getId());
						phenom = f;
						Alert.info("Exito", "Fenomeno creado con exito");
					}
				}
			}
			else {
				Fenomeno f = PhenomenaManager.update(oldName, phenom);

				if(f != null) {
					Alert.info("Exito", "Fenomeno actualizado con exito");
					fieldId.setText("" + f.getId());
					phenom = f;
				}
			}

		}
	}

	private void showPhoneDialog() {
		ph = new PhoneList(phenom);
		ph.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				ph = null;
				System.gc();
			}
		});

		ph.setVisible(true);
	}

	private void showCharDialog() {
		System.gc();
	}
}
