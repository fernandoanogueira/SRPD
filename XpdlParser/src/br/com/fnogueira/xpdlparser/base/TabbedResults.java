package br.com.fnogueira.xpdlparser.base;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.TextArea;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;

class TabbedResults extends JFrame {

	private static final long serialVersionUID = 1L;
	private JTabbedPane tabbedPane;
	private JPanel panel1;
	private JPanel panel2;
	private JPanel panel3;

	public TabbedResults() {
		// NOTE: to reduce the amount of code in this example, it uses
		// panels with a NULL layout. This is NOT suitable for
		// production code since it may not display correctly for
		// a look-and-feel.

		setTitle("Tabbed Pane Application");
		setSize(800, 600);
		setBackground(Color.gray);

		JPanel topPanel = new JPanel();
		topPanel.setLayout(new BorderLayout());
		getContentPane().add(topPanel);

		// Create the tab pages
		createPage1();
		createPage2();
		createPage3();

		// Create a tabbed pane
		tabbedPane = new JTabbedPane();
		tabbedPane.addTab("Page 1", panel1);
		tabbedPane.addTab("Page 2", panel2);
		tabbedPane.addTab("Page 3", panel3);
		topPanel.add(tabbedPane, BorderLayout.CENTER);
	}

	public void createPage1() {
		panel1 = new JPanel();
		panel1.setLayout(null);

		JLabel label1 = new JLabel("Username:");
		label1.setBounds(10, 15, 150, 20);
		panel1.add(label1);

		JTextField field = new JTextField();
		field.setBounds(10, 35, 150, 20);
		panel1.add(field);

		JLabel label2 = new JLabel("Password:");
		label2.setBounds(10, 60, 150, 20);
		panel1.add(label2);

		JPasswordField fieldPass = new JPasswordField();
		fieldPass.setBounds(10, 80, 150, 20);
		panel1.add(fieldPass);
		
		this.setVisible(true);
	}

	public void createPage2() {
		panel2 = new JPanel();
		panel2.setLayout(new BorderLayout());

		panel2.add(createHtmlPanel(), BorderLayout.NORTH);

		
		
	}

	public void createPage3() {
		panel3 = new JPanel();
		panel3.setLayout(new GridLayout(3, 2));

		panel3.add(new JLabel("Field 1:"));
		panel3.add(new TextArea());
		panel3.add(new JLabel("Field 2:"));
		panel3.add(new TextArea());
		panel3.add(new JLabel("Field 3:"));
		panel3.add(new TextArea());
	}
	
	public JEditorPane createHtmlPanel() {
		JEditorPane editorPane = new JEditorPane();
		editorPane.setEditable(false);
		java.net.URL helpURL = null;
		try {
			helpURL = new URL("file:////E:/teste/Diagrama 1.htm");
		    editorPane.setPage(helpURL);
		} catch (MalformedURLException e1) {
			e1.printStackTrace();
		}catch (IOException e) {
		    System.err.println("Attempted to read a bad URL: " + helpURL);
		}
		JScrollPane editorScrollPane = new JScrollPane(editorPane);
		editorScrollPane.setVerticalScrollBarPolicy(
		                JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		//editorScrollPane.setPreferredSize(new Dimension(100, 100));
		//editorScrollPane.setMinimumSize(new Dimension(10, 10));
		
		return editorPane;
	}

}