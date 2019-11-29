package com.proyecto.admin.screens;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import com.proyecto.admin.SharedResources;
import com.proyecto.admin.db.CharacteristicManager;
import com.proyecto.admin.utils.Alert;
import com.proyecto.admin.utils.StringUtils;
import com.proyecto.entidades.Caracteristica;
import com.proyecto.entidades.TipoDato;

public class CharacteristicsScreen extends Screen {

	private static final long serialVersionUID = 1L;

	private JPanel form, btnGroup;
	private JLabel labId, labTag, labDataType;
	private JTextField fieldId, fieldTag;
	private JComboBox<String> dataTypeChooser;
	private JButton btnLoad, btnSave, btnClear;
	private Caracteristica characteristic;

	public CharacteristicsScreen() {
		initializeComponents();
		customizeComponents();
		layoutComponents();
		addListenersToComponents();
	}

	private void initializeComponents() {

		form = new JPanel(new GridBagLayout());
		btnGroup = new JPanel();

		labId = new JLabel("ID");
		labTag = new JLabel("Etiqueta");
		labDataType = new JLabel("Tipo de dato");

		fieldId = new JTextField();
		fieldTag = new JTextField();

		dataTypeChooser = new JComboBox<String>();
		for(TipoDato t : TipoDato.values()) {
			String label = t.toString();
			label = label.charAt(0) + label.toLowerCase().substring(1);

			dataTypeChooser.addItem(label);
		}

		btnLoad = new JButton("Cargar");
		btnSave = new JButton("Guardar");
		btnClear = new JButton("Limpiar");

		characteristic = new Caracteristica();

	}

	private void customizeComponents() {
		setLayout(new BorderLayout());

		form.setBorder(new EmptyBorder(25, 25, 25, 25));

		Font f = SharedResources.getInstance().getFont();

		labId.setFont(f);
		labTag.setFont(f);
		labDataType.setFont(f);

		fieldId.setFont(f);
		fieldId.setEnabled(false);
		fieldTag.setFont(f);

		btnLoad.setFont(f);
		btnSave.setFont(f);
		btnClear.setFont(f);
	}

	private void layoutComponents() {
		add(form);

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
		c.weighty = 0.3d;

		c.gridx = 0;
		c.gridy = 0;
		form.add(labId, c);

		c.gridx = 2;
		form.add(labTag, c);

		c.gridx = 0;
		c.gridy = 1;
		form.add(labDataType, c);

		// Fields constraints
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 0.9d;

		c.gridx = 1;
		c.gridy = 0;
		form.add(fieldId, c);

		c.gridx = 3;
		form.add(fieldTag, c);

		c.gridx = 1;
		c.gridy = 1;
		c.gridwidth = 3;
		form.add(dataTypeChooser, c);

		// Button group constraints
		c.anchor = GridBagConstraints.EAST;
		c.fill = GridBagConstraints.NONE;
		c.gridwidth = 4;
		c.gridy = 2;
		c.weightx = 1;
		c.gridx = 0;
		form.add(btnGroup, c);
	}

	private void addListenersToComponents() {
		btnLoad.addActionListener(
			new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					loadCharacteristic();
				}
			}
		);

		btnSave.addActionListener(
			new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					saveCharacteristic();
				}
			}
		);

		btnClear.addActionListener(
			new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					clearForm();
				}
			}
		);
	}

	private void loadCharacteristic() {
		String label = fieldTag.getText();

		if(label.isEmpty()) {
			Alert.warn("Atencion", "Ingrese el nombre / etiqueta de la caracteristica a cargar");
			return;
		}

		if(label.length() < 2) {
			Alert.warn("Atencion", "El nombre / etiqueta de las caracteristicas es de 2 o mas caracteres");
			return;
		}

		if(!StringUtils.isValidCharacteristic(label)) {
			Alert.warn("Atencion", "Los nombres / etiquetas no tienen numeros ni simbolos");
			return;
		}

		characteristic = CharacteristicManager.find(label);

		if(characteristic != null) {
			fieldId.setText("" + characteristic.getId());
			fieldTag.setText(label);
			dataTypeChooser.setSelectedItem(
				characteristic.getTipoDato().toString().charAt(0) +
				characteristic.getTipoDato().toString().toLowerCase().substring(1)
			);
		}
		else {
			Alert.warn("JPA siendo JPA", "JPA no pudo cargar la entidad, que sorpresa");
		}
	}

	private void saveCharacteristic() {

		String label = fieldTag.getText();
		String id = fieldId.getText();
		TipoDato datatype = null;

		if(label.isEmpty()) {
			Alert.warn("Atencion", "Ingrese el nombre / etiqueta de la caracteristica");
			return;
		}

		if(label.length() < 2) {
			Alert.warn("Atencion", "El nombre / etiqueta de la caracteristica debe tener al menos 2 caracteres");
			return;
		}

		if(!StringUtils.isValidCharacteristic(label)) {
			Alert.warn("Atencion", "El nombre / etiqueta no puede tener numeros ni simbolos");
			return;
		}

		for(TipoDato t : TipoDato.values()) {
			// Match's garanteed Java, come on
			if(t.toString().equals(dataTypeChooser.getSelectedItem().toString().toUpperCase())) {
				datatype = t;
			}
		}

		if(id.isEmpty()) {
			if(CharacteristicManager.create(label, datatype)) {
				Alert.info("Exito", "Caracteristica creada con exito");

				characteristic = CharacteristicManager.find(label);

				if(characteristic != null)
					fieldId.setText("" + characteristic.getId());
				else
					Alert.warn("JPA siendo JPA", "JPA no pudo cargar la entidad que acaba de crear, que sorpresa");
			}
		}
		else {

			if(characteristic != null) {
				String oldLabel = characteristic.getEtiqueta();
				characteristic = CharacteristicManager.update(oldLabel, label, datatype);
				if(characteristic != null)
					Alert.info("Exito", "La caracteristica se actualizo correctamente");
				else
					Alert.warn("JPA siendo JPA", "JPA fallo al actualizar porque no pudo cargar la entidad, otra vez");
			}
		}

	}

	private void clearForm() {
		fieldId.setText("");
		fieldTag.setText("");
		dataTypeChooser.setSelectedIndex(0);
	}

}
