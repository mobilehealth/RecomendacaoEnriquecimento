package mobilehealth.sac.simulation;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTabbedPane;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JComboBox;
import javax.swing.JTextField;

public class ContextGenerator extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JTable table_1;
	private JTextField textField;
	private JTable table_2;
	private JTable table_3;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ContextGenerator frame = new ContextGenerator();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public ContextGenerator() {
		setTitle("Context Generator");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 825, 509);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		contentPane.add(tabbedPane, BorderLayout.CENTER);
		
		JPanel pnlContent = new JPanel();
		tabbedPane.addTab("Content", null, pnlContent, null);
		pnlContent.setLayout(null);
		
		JButton btnReadFile = new JButton("Read File");
		btnReadFile.setBounds(10, 11, 774, 23);
		pnlContent.add(btnReadFile);
		
		table = new JTable();
		table.setBounds(10, 45, 774, 155);
		pnlContent.add(table);
		
		JButton btnLoadToDatabase = new JButton("Load to Database");
		btnLoadToDatabase.setBounds(10, 211, 774, 23);
		pnlContent.add(btnLoadToDatabase);
		
		table_1 = new JTable();
		table_1.setBounds(10, 245, 774, 155);
		pnlContent.add(table_1);
		
		JButton btnClearDatabase = new JButton("Clear Database");
		btnClearDatabase.setBounds(10, 409, 774, 23);
		pnlContent.add(btnClearDatabase);
		
		JPanel pnlPerson = new JPanel();
		tabbedPane.addTab("Person", null, pnlPerson, null);
		pnlPerson.setLayout(null);
		
		JLabel lblQuantityUsers = new JLabel("Quantity of Users per Profile:");
		lblQuantityUsers.setBounds(10, 11, 153, 14);
		pnlPerson.add(lblQuantityUsers);
		
		JPanel pnlContentPerson = new JPanel();
		tabbedPane.addTab("Relation Content Person", null, pnlContentPerson, null);
		pnlContentPerson.setLayout(null);
		
		JButton btnGenerate = new JButton("Generate");
		btnGenerate.setBounds(232, 11, 105, 23);
		pnlContentPerson.add(btnGenerate);
		
		JLabel lblAccessAmount = new JLabel("Quantity of Access:");
		lblAccessAmount.setBounds(10, 15, 116, 14);
		pnlContentPerson.add(lblAccessAmount);
		
		textField = new JTextField();
		textField.setBounds(136, 12, 86, 20);
		pnlContentPerson.add(textField);
		textField.setColumns(10);
		
		JButton btnSaveDatabase = new JButton("Load to Database");
		btnSaveDatabase.setBounds(347, 12, 117, 23);
		pnlContentPerson.add(btnSaveDatabase);
		
		JButton btnClearDatabase_1 = new JButton("Clear Database");
		btnClearDatabase_1.setBounds(474, 12, 117, 23);
		pnlContentPerson.add(btnClearDatabase_1);
		
		table_2 = new JTable();
		table_2.setBounds(10, 45, 345, 376);
		pnlContentPerson.add(table_2);
		
		table_3 = new JTable();
		table_3.setBounds(365, 46, 419, 375);
		pnlContentPerson.add(table_3);
		
		JPanel pnlPHR = new JPanel();
		tabbedPane.addTab("PHR Informations", null, pnlPHR, null);
	}
}
