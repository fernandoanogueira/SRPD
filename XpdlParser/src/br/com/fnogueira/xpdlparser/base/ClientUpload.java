package br.com.fnogueira.xpdlparser.base;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.PrintStream;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.wfmc._2009.xpdl2.PackageType;

import br.com.fnogueira.xpdlparser.entity.ProcessDefinitionParser;
import br.com.fnogueira.xpdlparser.enums.ParametersEnum;

class ClientUpload extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;

	private JFileChooser fc;
	private JButton b, b1, b2, b3;
	private JTextField tf;
	private JLabel lb;
	private File file;

	private JTextArea txtConsole = new JTextArea();
	private PrintStream out = new PrintStream(new TextAreaOutputStream(
			txtConsole));

	public ClientUpload() {
		super("SRPD - Software Requirements from Process Definitions");

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);

		lb = new JLabel();
		lb.setBounds(20, 20, 190, 20);
		lb.setText("Choose a XPDL file to parse:");
		add(lb);

		tf = new JTextField();
		tf.setBounds(20, 45, 250, 20);
		tf.setEditable(false);
		add(tf);

		b = new JButton("Browse");
		b.setBounds(270, 45, 80, 20);
		b.addActionListener(this);
		add(b);

		b1 = new JButton("Parse");
		b1.setBounds(360, 45, 80, 20);
		b1.addActionListener(this);
		add(b1);

		b2 = new JButton("Clean");
		b2.setBounds(450, 45, 80, 20);
		b2.addActionListener(this);
		add(b2);
		
		b3 = new JButton("Test");
		b3.setBounds(540, 45, 80, 20);
		b3.addActionListener(this);
		add(b3);

		txtConsole.setAutoscrolls(true);
		txtConsole.setLineWrap(true);
		txtConsole.setEditable(false);
		txtConsole.setWrapStyleWord(true);

		JScrollPane scrollPane = new JScrollPane(txtConsole);
		scrollPane.setBounds(20, 80, 750, 200);
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		add(scrollPane);

		fc = new JFileChooser(ParametersEnum.BASE_FOLDER.getValue());
		fc.setAcceptAllFileFilterUsed(false);  
		FileNameExtensionFilter filter = new FileNameExtensionFilter("XPDL files", "xpdl");
		fc.addChoosableFileFilter(filter);
		setLayout(null);
		setSize(800, 350);
		setVisible(true);

		//TODO standard output
		//System.setOut(out);
		//System.setErr(out);
	}

	public void actionPerformed(ActionEvent e) {
		try {
			if (e.getSource() == b) {
				int x = fc.showOpenDialog(null);
				if (x == JFileChooser.APPROVE_OPTION) {
					copy();
				}
			}
			if (e.getSource() == b1) {
				retrieveFromXml(true);
			} else if (e.getSource() == b2) {
				clean();
			}else if (e.getSource() == b3) {
				test();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	@SuppressWarnings("static-access")
	public void copy()  {
		txtConsole.setText(null);
		txtConsole.setDefaultLocale(getLocale());
		file = fc.getSelectedFile();
		tf.setText(file.getName());
	}

	@SuppressWarnings("rawtypes")
	public void retrieveFromXml(boolean showResults) {
		if (file != null && file.canRead()) {
			if(showResults)
				System.out.println("Parsing " + file.getAbsolutePath());
			JAXBContext jaxbContext = null;
			try {
				jaxbContext = JAXBContext.newInstance(PackageType.class);
			} catch (JAXBException e) {
				e.printStackTrace();
			}
			Unmarshaller jaxbUnmarshaller = null;
			try {
				jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			} catch (JAXBException e) {
				e.printStackTrace();
			}
			JAXBElement rootElement = null;
			try {
				rootElement = (JAXBElement) jaxbUnmarshaller.unmarshal(file);
			} catch (JAXBException e) {
				e.printStackTrace();
			}
			PackageType root = (PackageType) rootElement.getValue();

			ProcessDefinitionParser processDefinition = new ProcessDefinitionParser();
			processDefinition.setRootPackage(root);
			if(showResults)
				System.out.println("Loading elements...");
			ProcessDefinitionParser.loadElements(file);
			if(showResults)
				System.out.println("Saving results...");
			ProcessDefinitionParser.printElements(file, showResults);
			if(showResults)
				System.out.println("Results will be shown in a new window...");
			
		}
	}
	
	public void clean() {
		txtConsole.setText(null);
		tf.setText(null);
		file = null;
	}
	
	public void test() {
		final File folder = new File(ParametersEnum.BASE_FOLDER.getValue());
		listFilesForFolder(folder);
	}
	public void listFilesForFolder(final File folder) {
	    for (final File fileEntry : folder.listFiles()) {
	        if (fileEntry.isDirectory()) {
	            listFilesForFolder(fileEntry);
	        } else {
	            if(fileEntry.getName().contains(".xpdl")){
	            	file = new File(fileEntry.getAbsolutePath());
	            	retrieveFromXml(false);
	            }
	        }
	    }
	}

}