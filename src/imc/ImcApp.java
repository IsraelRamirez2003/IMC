package imc;

import java.awt.EventQueue;
import java.awt.Image;
import javax.swing.JFrame;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.net.URL;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.JPasswordField;
import javax.swing.JEditorPane;
import javax.swing.JPanel;
import java.awt.Panel;
import java.awt.TextField;
import javax.swing.JFormattedTextField;
import java.awt.SystemColor;
import javax.swing.DropMode;

public class ImcApp {

	private JFrame frmImcApp;
	private JTextField textField_kg;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField tf_mensaje;
	private JTextField tf_name;
	private JLabel lblIngreseSuMasa;
	private JButton btnClose;

	
	/**
	 * Launch the application.
	 */
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ImcApp window = new ImcApp();
					window.frmImcApp.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	

	/**
	 * Create the application.
	 */
	public ImcApp() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmImcApp = new JFrame();
		frmImcApp.setBackground(Color.WHITE);
		frmImcApp.getContentPane().setForeground(SystemColor.windowText);
		frmImcApp.setTitle("IMC App");
		frmImcApp.getContentPane().setBackground(Color.WHITE);
		frmImcApp.getContentPane().setLayout(null);
		
		URL urlIcono = getClass().getResource("/img/imc.png");
		ImageIcon img = new ImageIcon(urlIcono);
		frmImcApp.setIconImage(img.getImage());
		
		textField_kg = new JTextField();
		textField_kg.setBounds(320, 172, 86, 20);
		frmImcApp.getContentPane().add(textField_kg);
		textField_kg.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Ingrese su estatura en metros");
		lblNewLabel.setBounds(81, 203, 209, 20);
		frmImcApp.getContentPane().add(lblNewLabel);
		
		textField = new JTextField();
		textField.setBackground(Color.WHITE);
		textField.setBounds(320, 203, 86, 20);
		frmImcApp.getContentPane().add(textField);
		textField.setColumns(10);
		
		JButton btnCalcular = new JButton("Calcular");
		btnCalcular.setForeground(Color.BLACK);
		btnCalcular.setBackground(new Color(0, 102, 153));
		btnCalcular.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				double masa = 0, 
					estatura = 0, 
					imc = 0;
				String nombre = null;
				try{
					try{
						masa=Double.parseDouble(textField_kg.getText());  
						estatura=Double.parseDouble(textField.getText());
					}catch(NumberFormatException e){
						JOptionPane.showMessageDialog(null, "Por favor ingrese valores numéricos");
						textField_kg.setText("");
						textField.setText("");
					}
					
					nombre =(tf_name.getText());
					if(masa > 0 & estatura > 0){
						imc = masa/(estatura*estatura);
						clasificar(nombre, imc);
					}else{
						JOptionPane.showMessageDialog(null, nombre + " vuelve a ingresar los datos masa y estatura");
						textField_kg.setText("");
						textField.setText("");
						
					}			    
				}catch(Exception e){
					e.printStackTrace();
				}
						
			}
		});
		btnCalcular.setBounds(317, 256, 89, 23);
		frmImcApp.getContentPane().add(btnCalcular);
		
		textField_1 = new JTextField();
		textField_1.setBackground(Color.WHITE);
		textField_1.setEditable(false);
		textField_1.setBounds(265, 300, 141, 20);
		frmImcApp.getContentPane().add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblSuImcEs = new JLabel("Su IMC es:");
		lblSuImcEs.setBounds(81, 302, 86, 17);
		frmImcApp.getContentPane().add(lblSuImcEs);
		
		tf_mensaje = new JTextField();
		tf_mensaje.setEditable(false);
		tf_mensaje.setFont(new Font("Tw Cen MT Condensed", Font.PLAIN, 20));
		tf_mensaje.setForeground(Color.BLACK);
		tf_mensaje.setBackground(Color.WHITE);
		tf_mensaje.setBounds(81, 332, 325, 20);
		frmImcApp.getContentPane().add(tf_mensaje);
		tf_mensaje.setColumns(10);
		
		tf_name = new JTextField();
		tf_name.setBounds(320, 141, 86, 20);
		frmImcApp.getContentPane().add(tf_name);
		tf_name.setColumns(10);
		
		JLabel lblIngreseSuNombre = new JLabel("Igrese su nombre");
		lblIngreseSuNombre.setBounds(81, 144, 209, 14);
		frmImcApp.getContentPane().add(lblIngreseSuNombre);
		
		lblIngreseSuMasa = new JLabel("Ingrese su masa en Kg");
		lblIngreseSuMasa.setBounds(81, 175, 209, 14);
		frmImcApp.getContentPane().add(lblIngreseSuMasa);
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(ImcApp.class.getResource("/img/images.png")));
		label.setBounds(60, 11, 362, 94);
		frmImcApp.getContentPane().add(label);
		
		JButton btnClean = new JButton("Clean");
		btnClean.setForeground(Color.BLACK);
		btnClean.setBackground(new Color(0, 102, 153));
		btnClean.setBounds(201, 256, 89, 23);
		btnClean.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent arg0) {
			try{
			textField_kg.setText("");
			textField.setText("");
			tf_name.setText("");
			textField_1.setText("");
			tf_mensaje.setText("");
			
			}catch(Exception e){
				
			}
		}	
	});
		frmImcApp.getContentPane().add(btnClean);
		frmImcApp.setBounds(100, 100, 490, 422);
		frmImcApp.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public void clasificar(String nombre ,double imc){
		String estado = null;
		String cuidado = null;
		
		//redondea el "imc" para tener solo dos pociciones decimales y despues convierte a String
		imc = (double)Math.round((imc * 100d) /100d);
		textField_1.setText(String.valueOf(imc));
	    
	    if(imc <= 16){
	    	
	    	JOptionPane.showMessageDialog(null, "Cuida tu salud tienes delgadez severa");
	    	cuidado = "Cuida tu salud tienes ";
	    	estado = "delgadez severa";
	    }else if(imc <= 16.99){
	    	JOptionPane.showMessageDialog(null, "Cuida tu salud tienes delgadez moderada");
	    	cuidado = "Cuida tu salud tienes ";
	    	estado = "delgadez moderada";
	    }else if(imc <= 18.49){
	    	cuidado = "Cuidado tienes ";
	    	estado = "delgadez leve";
	    }else if(imc <= 24.99){
	    	cuidado = "";
	    	estado = "Perfecto estas en tu peso optimo";
	    }else if(imc <= 29.99){
	    	cuidado = "Cuidado eres ";
	    	estado = "preobeso";
	    }else if(imc <= 34.99){
	    	cuidado = "CUIDA tu salud tienes ";
	    	estado = "obesidad leve";
	    }else if(imc <= 39.99){
	    	JOptionPane.showMessageDialog(null, "Cuida tu salud tienes obesidad media");
	    	cuidado = "CUIDA tu salud tienes ";
	    	estado = "obesidad media";
	    }else if(imc >= 40.00){
	    	JOptionPane.showMessageDialog(null, "Cuida tu salud!!!! "
	    			+ "\nTienes obesidad morbida ");
	    	cuidado = "CUIDA tu salud tienes ";
	        estado = "obesidad morbida";
	    }else{
	    	cuidado = "Has ingresado algo mal, ";
	    	estado = "";
	    }
	    tf_mensaje.setText(nombre + " " + cuidado + estado);	
	    
	}
}
