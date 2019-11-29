package com.proyecto.admin.screens;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.function.Supplier;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import com.proyecto.admin.SharedResources;
import com.proyecto.admin.db.RoleManager;
import com.proyecto.admin.db.UserManager;
import com.proyecto.admin.utils.Alert;
import com.proyecto.admin.utils.StringUtils;
import com.proyecto.entidades.Rol;
import com.proyecto.entidades.Usuario;

public class UsersScreen extends Screen {

	private static final long serialVersionUID = 1L;

	private JPanel userFormPane, btnGroup;
	private JLabel labId, labRol, labUser, labName, labSurname, labEmail;
	private JTextField fieldId, fieldUser, fieldName, fieldSurname, fieldEmail;
	private JCheckBox cbActive;
	private JComboBox<String> roleChooser;
	private JButton btnLoad, btnSave, btnClear;
	private Usuario user;
	private List<Rol> roles;

	public UsersScreen() {
		initializeComponents();
		customizeComponents();
		layoutComponents();
		addListenersToComponents();
	}

	private void initializeComponents() {
		userFormPane = new JPanel(new GridBagLayout());
		btnGroup = new JPanel();

		labId = new JLabel("ID");
		labRol = new JLabel("Rol");
		labUser = new JLabel("Usuario");
		labName = new JLabel("Nombre");
		labSurname = new JLabel("Apellido");
		labEmail = new JLabel("E-mail");

		fieldId = new JTextField();
		fieldUser = new JTextField();
		fieldName = new JTextField();
		fieldSurname = new JTextField();
		fieldEmail = new JTextField();

		cbActive = new JCheckBox("Activo");

		roles = RoleManager.getAll();
		roleChooser = new JComboBox<String>();
		for (Rol r: roles) {
			roleChooser.addItem(r.getNombre());
		}

		btnLoad = new JButton("Cargar");
		btnSave = new JButton("Guardar");
		btnClear = new JButton("Limpiar");

		user = new Usuario();
	}

	private void customizeComponents() {
		setLayout(new BorderLayout());

		userFormPane.setBorder(new EmptyBorder(25, 25, 25, 25));

		Font f = SharedResources.getInstance().getFont();

		labId.setFont(f);
		labRol.setFont(f);
		labUser.setFont(f);
		labName.setFont(f);
		labSurname.setFont(f);
		labEmail.setFont(f);

		cbActive.setFont(f);
		cbActive.setHorizontalTextPosition(SwingConstants.LEFT);

		fieldId.setFont(f);
		fieldId.setEnabled(false);
		fieldUser.setFont(f);
		fieldName.setFont(f);
		fieldSurname.setFont(f);
		fieldEmail.setFont(f);

		roleChooser.setFont(f);

		btnLoad.setFont(f);
		btnSave.setFont(f);
		btnClear.setFont(f);
	}

	private void layoutComponents() {
		add(userFormPane);

		btnGroup.add(btnLoad);
		btnGroup.add(btnSave);
		btnGroup.add(btnClear);

		GridBagConstraints c = new GridBagConstraints();

		// Common constraints
		c.gridwidth = 1;
		c.gridheight = 1;
		c.weighty = 0.25d;
		c.insets = new Insets(2, 2, 2, 2);

		// Label constraints
		c.anchor = GridBagConstraints.WEST;
		c.fill = GridBagConstraints.NONE;
		c.weightx = 0.1d;

		c.gridx = 0;
		c.gridy = 0;
		userFormPane.add(labId, c);

		c.gridx = 2;
		userFormPane.add(labUser, c);

		c.gridx = 0;
		c.gridy = 2;
		userFormPane.add(labName, c);

		c.gridx = 2;
		userFormPane.add(labSurname, c);

		c.gridx = 0;
		c.gridy = 3;
		userFormPane.add(labEmail, c);

		// Fields constraints
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 0.9d;

		c.gridx = 1;
		c.gridy = 0;
		userFormPane.add(fieldId, c);

		c.gridx = 3;
		userFormPane.add(fieldUser, c);

		c.gridx = 1;
		c.gridy = 2;
		userFormPane.add(fieldName, c);

		c.gridx = 3;
		userFormPane.add(fieldSurname, c);

		c.gridx = 1;
		c.gridy = 3;
		c.gridwidth = 3;
		userFormPane.add(fieldEmail, c);

		c.weightx = 7/8d;
		c.gridx = 0;
		c.gridy = 1;
		c.gridwidth = 2;
		userFormPane.add(roleChooser, c);

		// Checkbox constraints
		c.anchor = GridBagConstraints.CENTER;
		c.fill = GridBagConstraints.NONE;
		c.weightx = 1/8d;
		c.gridx = 2;
		c.gridy = 1;
		c.gridwidth = 2;
		userFormPane.add(cbActive, c);

		// Button group constraints
		c.anchor = GridBagConstraints.EAST;
		c.gridwidth = 4;
		c.gridy = 4;
		c.weightx = 1;
		c.gridx = 0;
		userFormPane.add(btnGroup, c);
	}

	private void addListenersToComponents() {
		btnLoad.addActionListener(
			new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					loadUser();
				}
			}
		);

		btnSave.addActionListener(
			new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					saveUser();
				}
			}
		);

		btnClear.addActionListener(
			new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					clearUserForm();
				}
			}
		);
	}

	private void loadUser() {
		user = UserManager.find(fieldUser.getText());

		if(user == null)
			user = new Usuario();

		fieldId.setText("" + user.getId());
		fieldUser.setText(user.getUsuario());
		fieldName.setText(user.getNombre());
		fieldSurname.setText(user.getApellido());
		fieldEmail.setText(user.getEmail());
		roleChooser.getModel().setSelectedItem(user.getRol().getNombre());
		cbActive.setSelected(user.isActivo());
	}

	private void saveUser() {

		if(fieldUser.getText().trim().isEmpty()) {
			Alert.info("Campo vacio", "Ingrese el usuario");
		}

		else if(fieldUser.getText().trim().length() < 4) {
			Alert.info("Campo invalido", "El usuario debe tener al menos 4 caracteres");
		}

		else if(!StringUtils.isUserValid(fieldUser.getText())) {
			Alert.warn("Campo invalido", "El usuario es invalido");
		}

		else if(fieldName.getText().trim().isEmpty()) {
			Alert.info("Campo vacio", "Ingrese el nombre");
		}

		else if(fieldName.getText().trim().length() < 2) {
			Alert.info("Campo invalido", "El nombre es muy corto");
		}

		else if(!StringUtils.isNameValid(fieldName.getText())) {
			Alert.warn("Campo invalido", "El nombre es invalido");
		}

		else if(fieldSurname.getText().trim().isEmpty()) {
			Alert.info("Campo vacio", "Ingrese el apellido");
		}

		else if(fieldSurname.getText().trim().length() < 2) {
			Alert.info("Campo invalido", "El apellido es muy corto");
		}

		else if(!StringUtils.isSurnameValid(fieldSurname.getText())) {
			Alert.warn("Campo invalido", "El apellido es invalido");
		}

		else if(fieldEmail.getText().trim().isEmpty()) {
			Alert.info("Campo vacio", "Ingrese el correo");
		}

		else if(fieldEmail.getText().trim().length() < 9) {
			Alert.info("Campo vacio", "El correo parece invalido");
		}

		else if(!StringUtils.isEmailValid(fieldEmail.getText())) {
			Alert.warn("Campo invalido", "El correo es invalido");
			return;
		}

		else {
			String oldUser = user.getUsuario();
			user.setUsuario(fieldUser.getText());
			user.setNombre(fieldName.getText());
			user.setApellido(fieldSurname.getText());
			user.setEmail(fieldEmail.getText());

			// Simpl3 stuff, don't worry bois
			user.setRol(
				(new Supplier<Rol>() {
					private Rol r = null;
					public Rol get() {
						roles.forEach(role -> {
							if(role.getNombre().equals(roleChooser.getSelectedItem().toString())) {
								r = role;
								return;
							}
						});
						return r;
					}
				}).get()
			);
			user.setActivo(cbActive.isSelected());

			if(!fieldId.getText().isEmpty()) {
				user.setId(Long.valueOf(fieldId.getText()));

				Usuario u = UserManager.update(oldUser,  user);

				if(u != null) {
					fieldId.setText("" + u.getId());
					user = u;
					Alert.info("Exito", "El usuario fue actualizado correctamente");
				}
			}

			else {
				if(UserManager.create(user)) {
					Usuario u = UserManager.find(user.getUsuario());

					if(u != null) {
						fieldId.setText("" + u.getId());
						user = u;
					}

					Alert.info("Exito", "El usuario fue agregado correctamente");
				}
			}
		}
	}

	private void clearUserForm() {
		fieldId.setText("");
		fieldUser.setText("");
		fieldName.setText("");
		fieldSurname.setText("");
		fieldEmail.setText("");
		roleChooser.getModel().setSelectedItem(roleChooser.getItemAt(0));
		cbActive.setSelected(false);
	}

}
