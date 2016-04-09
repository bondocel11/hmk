package views;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import io.IOStream;
import models.Product;
import models.Warehouse;

public class AdminView {
	private Warehouse wh;
	private IOStream io;
	private JTable table;
	private DefaultTableModel model;

	public AdminView() {

		JFrame frame = new JFrame();
		io = new IOStream();
		table = new JTable();
		wh = new Warehouse();
		wh = io.deserializeWarehouse();
		wh.print();
		// create a table model and set a Column Identifiers to this model
		Object[] columns = { "Item", "Size", "Color", "Price", "Stock" };
		model = new DefaultTableModel();
		model.setColumnIdentifiers(columns);

		Font font = new Font("", 1, 22);
		table.setModel(model);
		table.setBackground(Color.LIGHT_GRAY);
		table.setForeground(Color.black);
		table.setFont(font);
		table.setRowHeight(30);

		for (Product product : wh.getProducts()) {
			if (LogIn.adminButton().isSelected()) {
				Object[] row = { product.getItem(), product.getSize(), product.getColor(), product.getPrice(),
						product.getStock() };
				model.addRow(row);
			}
		}

		// JTextField textId = new JTextField();

		JTextField textItem = new JTextField();
		JTextField textSize = new JTextField();
		JTextField textColor = new JTextField();
		JTextField textPrice = new JTextField();
		JTextField textStock = new JTextField();

		// create JButtons

		JButton btnAdd = new JButton("Add");
		JButton btnDelete = new JButton("Delete");
		JButton btnUpdate = new JButton("Update");
		JButton btnViewOrders = new JButton("Orders");

		// textId.setBounds(20, 220, 100, 25);

		textItem.setBounds(20, 220, 100, 25);
		textSize.setBounds(20, 240, 100, 25);
		textColor.setBounds(20, 265, 100, 25);
		textPrice.setBounds(20, 285, 100, 25);
		textStock.setBounds(20, 310, 100, 25);
		btnAdd.setBounds(150, 220, 100, 25);
		btnUpdate.setBounds(150, 250, 100, 25);
		btnDelete.setBounds(150, 280, 100, 25);
		btnViewOrders.setBounds(150, 310, 100, 25);

		// create JScrollPane

		JScrollPane pane = new JScrollPane(table);
		pane.setBounds(0, 0, 880, 200);
		frame.setLayout(null);
		frame.add(pane);

		// add JTextFields to the jframe
		// frame.add(textId);

		frame.add(textItem);
		frame.add(textSize);
		frame.add(textColor);
		frame.add(textPrice);
		frame.add(textStock);

		// add JButtons to the jframe

		frame.add(btnAdd);
		frame.add(btnDelete);
		frame.add(btnUpdate);
		frame.add(btnViewOrders);

		// create an array of objects to set the row data

		Object[] row = new Object[5];

		// button add row

		btnAdd.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				row[0] = textItem.getText();
				row[1] = textSize.getText();
				row[2] = textColor.getText();
				row[3] = textPrice.getText();
				row[4] = textStock.getText();

				// add row to the model

				model.addRow(row);
				Product product = new Product(row[0].toString(), row[1].toString(), row[2].toString(),
						Double.parseDouble(row[3].toString()), Integer.parseInt(row[4].toString()));
				wh.add(product);
				io.SerializeWarehouse(wh);
			}
		});

		btnViewOrders.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
			}

		});

		// button remove row
		btnDelete.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// i = the index of the selected row
				int i = table.getSelectedRow();
				if (i >= 0) {
					// remove a row from jtable
					// wh.deleteCorrespondingProduct(row[0].toString(),
					// row[1].toString(), row[2].toString(),
					// (Product) wh.getRoot());
					// model.removeRow(i);
				} else {
					System.out.println("Delete Error");
				}
			}
		});
		// get selected row data From table to textfields
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// i = the index of the selected row
				int i = table.getSelectedRow();
				textItem.setText(model.getValueAt(i, 0).toString());
				textSize.setText(model.getValueAt(i, 1).toString());
				textColor.setText(model.getValueAt(i, 2).toString());
				textPrice.setText(model.getValueAt(i, 2).toString());
				textStock.setText(model.getValueAt(i, 4).toString());
			}
		});
		// button update row
		btnUpdate.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// i = the index of the selected row
				int i = table.getSelectedRow();
				if (i >= 0) {
					model.setValueAt(textItem.getText(), i, 0);
					model.setValueAt(textSize.getText(), i, 1);
					model.setValueAt(textColor.getText(), i, 2);
					model.setValueAt(textPrice.getText(), i, 3);
					model.setValueAt(textStock.getText(), i, 4);

					row[3] = textStock.getText();
					// wh.updateCorrespondingProduct(row[0].toString(),
					// row[1].toString(), row[2].toString(),
					// Integer.parseInt(row[3].toString()), (Product)
					// wh.getRoot());
				} else {
					System.out.println("Update Error");
				}
			}
		});
		frame.setSize(900, 400);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);

	}

	/*public static DefaultTableModel getModel() {
		return model;
	}*/
}
