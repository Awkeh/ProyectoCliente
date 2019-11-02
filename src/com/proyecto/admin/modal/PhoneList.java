package com.proyecto.admin.modal;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;

import com.proyecto.admin.SharedResources;
import com.proyecto.admin.utils.Alert;
import com.proyecto.admin.utils.StringUtils;
import com.proyecto.entidades.Fenomeno;

// TODO Add phone to table
// TODO Remove phone from table
// TODO Fill phenom's ArrayList with data from the table on click 'save'
// TODO Resolve if to update on the fly or on save

public class PhoneList extends JDialog {

	private static final long serialVersionUID = 1L;
	private static final String[] HEADER = {
		"ID",
		"Telefono"
	};
	private static final int PHONE_COL_ID = 1;

	private JScrollPane root;
	private JPanel inputArea;
	private JTable table;
	private DefaultTableModel model;
	private Object[][] data;
	private JTextField fieldPhone;
	private JButton btnAdd;
	private Fenomeno phenom;
	private String valueBeforeEditing = "";
	private int lastRowSelected = 0;

	public PhoneList(Fenomeno f) {
		phenom = f;

		initializeComponents();
		customizeComponents();
		layoutComponents();
		addListenersToComponents();

		pack();
		setVisible(true);
	}

	private void initializeComponents() {

		//data = new Object[phenom.getTelefonos().size()][4];
		data = new Object[20][4];

		Random r = new Random();

		for(int row = 0; row < data.length; row++) {
			data[row] = new Object[] {
				"" + (1 + row),
				"" + (90_000_000 + r.nextInt(99_99_999))
			};
		}

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
		setTitle("Telefonos para " + phenom.getNombre());
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
						// TODO update Fenomeno (due to changed phone)
						System.out.println("The value has been successfully changed");
					}
					else {
						// Quality bug fixing
						if(val.trim().isEmpty()) {
							// TODO delete phone from DB
							Alert.info("", "Si, borrar el telefono, bla bla bla. No jodas");
						} else {
							Alert.warn("Que se yo, necesito dormir", "El telefono ingresado no es valido");
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
	}

}
