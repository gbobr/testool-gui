package ar.edu.unlam.analisissoftware.testool.gui;

import java.awt.Desktop;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.net.URI;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import ar.edu.unlam.analisissoftware.testool.Main;

import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.RowSpec;

public class TestToolWindow {

	private JFrame frmTesttool;
	private JButton btnBrowseProject;
	private JButton btnBrowseOut;
	private JLabel lblDirectorioDelProyecto;
	private JLabel lblDirectorioDesalida;
	private JTextField projectPath;
	private JTextField outputPath;
	private JButton btnGenerateReport;

	private JFileChooser chooser;
	private String choosertitle;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TestToolWindow window = new TestToolWindow();
					window.frmTesttool.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public TestToolWindow() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmTesttool = new JFrame();
		frmTesttool.setTitle("Herramienta de testing");
		frmTesttool.setBounds(100, 100, 510, 190);
		frmTesttool.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmTesttool.getContentPane().setLayout(new FormLayout(new ColumnSpec[] {
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,},
			new RowSpec[] {
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,}));
		
		lblDirectorioDelProyecto = new JLabel("Directorio del proyecto");
		frmTesttool.getContentPane().add(lblDirectorioDelProyecto, "4, 4");
		
		projectPath = new JTextField("");
		projectPath.setEditable(false);
		projectPath.setText("< Seleccione el directorio >");
		frmTesttool.getContentPane().add(projectPath, "8, 4, 17, 1, fill, default");
		projectPath.setColumns(10);
		
		btnBrowseProject = new JButton("...");
		btnBrowseProject.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			    int result;
		        
			    chooser = new JFileChooser(); 
			    chooser.setCurrentDirectory(new java.io.File("."));
			    chooser.setDialogTitle(choosertitle);
			    chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
			    //
			    // disable the "All files" option.
			    //
			    chooser.setAcceptAllFileFilterUsed(false);
			    //    
			    if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) { 
			      projectPath.setText(chooser.getSelectedFile().getAbsolutePath());
			      btnGenerateReport.setEnabled(true);
			    }
			    else {
			      System.out.println("No Selection ");
			      }
			}
		});
		frmTesttool.getContentPane().add(btnBrowseProject, "26, 4");
		
		lblDirectorioDesalida = new JLabel("Directorio de salida");
		frmTesttool.getContentPane().add(lblDirectorioDesalida, "4, 6");
		
		outputPath = new JTextField();
		outputPath.setText("./report/");
		outputPath.setColumns(10);
		frmTesttool.getContentPane().add(outputPath, "8, 6, 17, 1, fill, default");
		
		btnBrowseOut = new JButton("...");
		btnBrowseOut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				chooser = new JFileChooser(); 
			    chooser.setCurrentDirectory(new java.io.File("."));
			    chooser.setDialogTitle(choosertitle);
			    chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
			    //
			    // disable the "All files" option.
			    //
			    chooser.setAcceptAllFileFilterUsed(false);
			    //    
			    if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) { 
			      outputPath.setText(chooser.getSelectedFile().getAbsolutePath());			      
			    }
			    else {
			      System.out.println("No Selection ");
			      }
			}
		});
		frmTesttool.getContentPane().add(btnBrowseOut, "26, 6");
		
		btnGenerateReport = new JButton("Generar ...");
		btnGenerateReport.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Main.main(new String[]{projectPath.getText(),outputPath.getText()});
				try{
					if(!outputPath.getText().endsWith("/")) outputPath.setText(outputPath.getText()+"/");
					Desktop.getDesktop().browse(new URI("file:/"+new File(outputPath.getText()+"index.html").getCanonicalPath().replace("\\", "/")));
				} catch(Exception ex){
					ex.printStackTrace();
				}
			}
		});
		btnGenerateReport.setEnabled(false);
		frmTesttool.getContentPane().add(btnGenerateReport, "10, 10, 9, 1, center, center");
	}

}
