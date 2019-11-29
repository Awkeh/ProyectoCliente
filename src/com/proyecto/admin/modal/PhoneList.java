package com.proyecto.admin.modal;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;

import com.proyecto.admin.SharedResources;
import com.proyecto.admin.db.PhoneManager;
import com.proyecto.admin.utils.Alert;
import com.proyecto.admin.utils.StringUtils;
import com.proyecto.entidades.Fenomeno;
import com.proyecto.entidades.Telefono;

public class PhoneList extends JDialog {

	private static final long serialVersionUID = 1L;
	private static final String[] HEADER = {
		"ID",
		"Telefono"
	};
	private static final int PHONE_COL_ID = 1;

	private JScrollPane root;
	private JPanel inputArea;
	private JTable table = null;
	private DefaultTableModel model;
	private Object[][] data;
	private JTextField fieldPhone;
	private JButton btnAdd;
	private String valueBeforeEditing = "";
	private int lastRowSelected = 0;
	private Fenomeno f;

	public PhoneList(Fenomeno f) {

		this.f = f == null ? new Fenomeno() : f;

		initializeComponents();
		customizeComponents();
		layoutComponents();
		addListenersToComponents();

		pack();
	}

	private void initializeComponents() {

		fillTableFromPhoneList();

		root = new JScrollPane();
		inputArea = new JPanel(new GridBagLayout());
		model = new DefaultTableModel(data, HEADER);
		table = new JTable(model);
		fieldPhone = new JTextField();
		btnAdd = new JButton("Agregar");
	}

	private void customizeComponents() {
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		setTitle("Telefonos para " + f.getNombre());
		setModal(true);
		setLayout(new BorderLayout());

		Font f = SharedResources.getInstance().getFont();

		table.setFont(f);
		table.setRowHeight((int) (1.5f * table.getFont().getSize()));
		table.setAutoResizeMode(JTable.AUTO_RESIZE_SUBSEQUENT_COLUMNS);

		fieldPhone.setFont(f);
		btnAdd.setFont(f);
	}

	private void layoutComponents() {
		root.getViewport().add(table);

		GridBagConstraints c = new GridBagConstraints();

		c.gridx = 0;
		c.gridy = 0;
		c.weightx = 0.7d;
		c.fill = GridBagConstraints.HORIZONTAL;
		inputArea.add(fieldPhone, c);

		c.gridx = 1;
		c.weightx = 0.3d;
		inputArea.add(btnAdd, c);

		add(root, BorderLayout.CENTER);
		add(inputArea, BorderLayout.SOUTH);
	}

	private void addListenersToComponents() {
		table.addFocusListener(new FocusListener() {
			public void focusGained(FocusEvent e) {

				if(valueBeforeEditing.isEmpty()) return;

				String val = (String) table.getValueAt(lastRowSelected, PHONE_COL_ID);

				if(!valueBeforeEditing.equals(val) && table.getSelectedColumn() == PHONE_COL_ID) {
					if(StringUtils.isValidPhone(val)) {
						Integer numb = Integer.valueOf(valueBeforeEditing);
						Integer newNumb = Integer.valueOf(val);

						try {
							Telefono t = PhoneManager.update(numb, newNumb);

							for(int i = 0; i < f.getTelefonos().size(); i++) {
								if(f.getTelefonos().get(i).getNumero() == newNumb) {
									f.getTelefonos().set(i, t);
									break;
								}
							}

							valueBeforeEditing = val;
//							Alert.info("Coso ahi", "El telefono fue actualizado");
						} catch (Exception e1) {
							e1.printStackTrace();
							Alert.warn("Nope", "El telefono no pudo ser actualizado");
							return;
						}
					}
					else {
						// Quality bug fixing
						if(val.trim().isEmpty()) {
							try {
								int n = Integer.valueOf(valueBeforeEditing);
								PhoneManager.delete(n);

								for(int i = 0; i < f.getTelefonos().size(); i++) {
									if(f.getTelefonos().get(i).getNumero() == n) {
										f.getTelefonos().remove(i);
										break;
									}
								}

								fillTableFromPhoneList();
//								Alert.info("????", "Telefono borrado");
							} catch (NumberFormatException e1) {
								e1.printStackTrace();
							} catch (Exception e1) {
								e1.printStackTrace();
								Alert.warn("", "El telefono no pudo ser borrado");
							}
						} else {
							Alert.warn("Telefono invalido", "El telefono ingresado no es valido");
							table.setValueAt(valueBeforeEditing, lastRowSelected, PHONE_COL_ID);
						}
					}
				}
			}

			public void focusLost(FocusEvent e) {
				TableCellEditor tce = table.getCellEditor();
		        if(tce != null) {
		        	if(table.getSelectedColumn() == PHONE_COL_ID) {
		        		valueBeforeEditing = (String) table.getValueAt(table.getSelectedRow(), table.getSelectedColumn());
		        		lastRowSelected = table.getSelectedRow();
		        	}
		        	else
		        		tce.stopCellEditing();
		        }
			}

		});

		btnAdd.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				String numb = fieldPhone.getText();

				if(numb.trim().isEmpty()) {
					Alert.warn("Atencion", "Ingrese el numero de telefono a agregar primero");
					return;
				}

				if(!StringUtils.isValidPhone(numb)) {
					Alert.warn("Atencion", "El numero parece invalido. Asegurese de que cumple con el formato 09XXXXXXX");
					return;
				}

				if(numb.length() == 9) {
					numb = numb.substring(1);
				}

				Integer n = null;

				try {
					n = Integer.valueOf(numb);
				} catch (NumberFormatException nfe) {
					Alert.warn("Atencion", "El numero parece invalido. Asegurese de que cumple con el formato 09XXXXXXX");
					nfe.printStackTrace();
				}

				try {
					Telefono t = PhoneManager.find(n);

					if(t != null) {
						Alert.info("Que se yo", "El telefono ya existe en la base de datos");

						if(!f.getTelefonos().contains(t))
							f.getTelefonos().add(t);

						fillTableFromPhoneList();
						return;
					}
				} catch (Exception e1) {
					e1.printStackTrace();
				}

				try {
					boolean succeeded = PhoneManager.create(n);

					if(succeeded) {
						Telefono t = PhoneManager.find(n);
						if(t != null)
							f.getTelefonos().add(t);

						fillTableFromPhoneList();
//						Alert.info("Anduvo piola", "El telefono fue guardado con exito");
					}
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
	}

	private void fillTableFromPhoneList() {

		if(f.getTelefonos() == null) {
			f.setTelefonos(new ArrayList<Telefono>());
		}

		List<Telefono> tList = f.getTelefonos();
		data = new Object[tList.size()][4];

		for(int row = 0; row < data.length; row++) {
			data[row] = new Object[] {
				"" + tList.get(row).getId(),
				"" + tList.get(row).getNumero()
			};
		}

		model = new DefaultTableModel(data, HEADER);

		if(table != null) {
			table.setModel(model);
			table.validate();
		}
	}

	public List<Telefono> getPhoneList() {
		return f.getTelefonos();
	}

}
