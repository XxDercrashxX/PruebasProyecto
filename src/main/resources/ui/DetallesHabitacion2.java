package ui;

import java.awt.BorderLayout;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;

import com.formdev.flatlaf.FlatLightLaf;

public class DetallesHabitacion2 {

	JFrame frame;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DetallesHabitacion2 window = new DetallesHabitacion2();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public DetallesHabitacion2() {

		try {
            UIManager.setLookAndFeel(new FlatLightLaf());
            UIManager.put("Button.arc", 90); // Esquinas redondas
        } catch (Exception ex) {
            ex.printStackTrace();
        }

		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setResizable(false);
		frame.setBounds(100, 100, 1180, 700);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 255, 255));
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);

		JPanel panel_1 = new JPanel(); //Borde negro
		panel_1.setBackground(new Color(0, 0, 0));
		panel_1.setBounds(0, 0, 1164, 95);
		panel.add(panel_1);
		panel_1.setLayout(null);

		JPanel panel_2 = new JPanel(); //Borde gris
		panel_2.setBackground(new Color(55, 54, 48));
		panel_2.setBounds(0, 95, 1164, 26);
		panel.add(panel_2);
		panel_2.setLayout(null);

		JLabel logo = new JLabel(""); //Logo
		logo.setBounds(0, 0, 170, 95);
		// CORREGIDO: new ImageIcon(getClass().getResource("/images/logo.png"));
		ImageIcon portada1 = new ImageIcon(getClass().getResource("/images/logo.png"));
	    Image portada2 = portada1.getImage();
	    Image portada3 = portada2.getScaledInstance(170, 95,Image.SCALE_SMOOTH);
	    logo.setIcon(new ImageIcon(portada3));
		panel_1.add(logo);

		JLabel Titulo = new JLabel("Panel de Habitaciones"); //Titulo
		Titulo.setForeground(new Color(255, 255, 255));
		Titulo.setFont(new Font("Jost* Medium", Font.PLAIN, 35));
		Titulo.setBounds(180, 11, 410, 73);
		panel_1.add(Titulo);

		JLabel menuTitulo = new JLabel("Detalles de la habitación:"); //Texto menú
		menuTitulo.setFont(new Font("Jost*", Font.BOLD, 38));
		menuTitulo.setBounds(131, 126, 549, 56);
		panel.add(menuTitulo);
		// CORREGIDO: new ImageIcon(getClass().getResource("/images/usuario.png"));
		ImageIcon c1 = new ImageIcon(getClass().getResource("/images/usuario.png"));
		Image c2 = c1.getImage();
		Image c3 = c2.getScaledInstance(36, 36, Image.SCALE_SMOOTH);
		// CORREGIDO: new ImageIcon(getClass().getResource("/images/informacion.png"));
		ImageIcon e1 = new ImageIcon(getClass().getResource("/images/informacion.png"));
		Image e2 = e1.getImage();
		Image e3 = e2.getScaledInstance(36, 36, Image.SCALE_SMOOTH);

		JButton botonVolver = new JButton(""); // Boton para volver atrás
		botonVolver.setForeground(new Color(255, 255, 255));
		botonVolver.setBackground(new Color(255, 255, 255));
		botonVolver.setBorderPainted(false);
		botonVolver.setFocusPainted(false);
		botonVolver.setContentAreaFilled(true);
		botonVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose(); // Cierra la ventana actual del menú
				DetallesHabitacion1 conexion = new DetallesHabitacion1();
				conexion.frame.setVisible(true); 	
			}
		});
		botonVolver.setBounds(60, 132, 50, 50);
		// CORREGIDO: new ImageIcon(getClass().getResource("/images/flecha_izquierda.png"));
		ImageIcon f1 = new ImageIcon(getClass().getResource("/images/flecha_izquierda.png"));
		Image f2 = f1.getImage();
		Image f3 = f2.getScaledInstance(36, 36, Image.SCALE_SMOOTH);
		botonVolver.setIcon(new ImageIcon(f3));
		panel.add(botonVolver);


		JButton btnTiposDeRentas = new JButton("<html>Tipos de habitaciones &#8594;</html>"); //Botón superior tipos de habitaciones
		btnTiposDeRentas.setFont(new Font("Jost* Medium", Font.PLAIN, 12));
		btnTiposDeRentas.setForeground(new Color(255, 255, 255));
		btnTiposDeRentas.setBackground(new Color(56, 54, 41));
		btnTiposDeRentas.setBorder(BorderFactory.createLineBorder(Color.BLACK,0));
		btnTiposDeRentas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose(); // Cierra la ventana actual del menú
				TiposHabitacion conexion = new TiposHabitacion();
				conexion.frame.setVisible(true); 
			}
		});
		btnTiposDeRentas.setBounds(1023, 0, 134, 23);
		btnTiposDeRentas.setBorderPainted(false);
        btnTiposDeRentas.setFocusPainted(false);
        btnTiposDeRentas.setContentAreaFilled(true);
		panel_2.add(btnTiposDeRentas);
		
		JButton btnrentas = new JButton("<html>Rentas &#8594;</html>"); //Botón superior rentas
		btnrentas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose(); // Cierra la ventana actual del menú
				Rentas conexion = new Rentas();
				conexion.frame.setVisible(true); 
			}
		});
		btnrentas.setForeground(Color.WHITE);
		btnrentas.setFont(new Font("Jost* Medium", Font.PLAIN, 12));
		btnrentas.setFocusPainted(false);
		btnrentas.setContentAreaFilled(true);
		btnrentas.setBorderPainted(false);
		btnrentas.setBorder(BorderFactory.createLineBorder(Color.BLACK,0));
		btnrentas.setBackground(new Color(56, 54, 41));
		btnrentas.setBounds(932, 0, 81, 23);
		panel_2.add(btnrentas);
		
		JButton btnclientes = new JButton("<html>Clientes &#8594;</html>"); //Botón superior clientes
		btnclientes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose(); // Cierra la ventana actual del menú
				Clientes conexion = new Clientes();
				conexion.frame.setVisible(true); 
			}
		});
		btnclientes.setForeground(Color.WHITE);
		btnclientes.setFont(new Font("Jost* Medium", Font.PLAIN, 12));
		btnclientes.setFocusPainted(false);
		btnclientes.setContentAreaFilled(true);
		btnclientes.setBorderPainted(false);
		btnclientes.setBorder(BorderFactory.createLineBorder(Color.BLACK,0));
		btnclientes.setBackground(new Color(56, 54, 41));
		btnclientes.setBounds(841, 0, 81, 23);
		panel_2.add(btnclientes);
		
		JButton btnhabitaciones = new JButton("<html>Habitaciones &#8594;</html>"); //Botón superior habitaciones
		btnhabitaciones.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose(); // Cierra la ventana actual del menú
				PanelHabitaciones1 conexion = new PanelHabitaciones1();
				conexion.frame.setVisible(true); 
			}
		});
		btnhabitaciones.setForeground(Color.WHITE);
		btnhabitaciones.setFont(new Font("Jost* Medium", Font.PLAIN, 12));
		btnhabitaciones.setFocusPainted(false);
		btnhabitaciones.setContentAreaFilled(true);
		btnhabitaciones.setBorderPainted(false);
		btnhabitaciones.setBorder(BorderFactory.createLineBorder(Color.BLACK,0));
		btnhabitaciones.setBackground(new Color(56, 54, 41));
		btnhabitaciones.setBounds(731, 0, 100, 23);
		panel_2.add(btnhabitaciones);
		
		JButton btntarifas = new JButton("<html>Tarifas &#8594;</html>"); //Botón superior tarifas
		btntarifas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose(); // Cierra la ventana actual del menú
				Tarifas conexion = new Tarifas();
				conexion.frame.setVisible(true); 
			}
		});
		btntarifas.setForeground(Color.WHITE);
		btntarifas.setFont(new Font("Jost* Medium", Font.PLAIN, 12));
		btntarifas.setFocusPainted(false);
		btntarifas.setContentAreaFilled(true);
		btntarifas.setBorderPainted(false);
		btntarifas.setBorder(BorderFactory.createLineBorder(Color.BLACK,0));
		btntarifas.setBackground(new Color(56, 54, 41));
		btntarifas.setBounds(649, 0, 72, 23);
		panel_2.add(btntarifas);
		// CORREGIDO: new ImageIcon(getClass().getResource("/images/busqueda.png"));
		ImageIcon u1 = new ImageIcon(getClass().getResource("/images/busqueda.png"));
		Image u2 = u1.getImage();
		Image u3 = u2.getScaledInstance(36, 36, Image.SCALE_SMOOTH);
        final String placeholder = "BUSCAR";

		JPanel panel_3 = new JPanel();
		panel_3.setBackground(new Color(0, 187, 249));
		panel_3.setBounds(131, 193, 300, 440);
		panel.add(panel_3);
		panel_3.setLayout(null);

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setBounds(45, 11, 200, 200);
		// CORREGIDO: new ImageIcon(getClass().getResource("/images/cama_matrimonial2.png"));
		ImageIcon v1 = new ImageIcon(getClass().getResource("/images/cama_matrimonial2.png"));
		Image v2 = v1.getImage();
		Image v3 = v2.getScaledInstance(200, 200, Image.SCALE_SMOOTH);
		lblNewLabel.setIcon(new ImageIcon(v3));
		panel_3.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Cuarto B3");
		lblNewLabel_1.setFont(new Font("Jost*", Font.BOLD | Font.ITALIC, 20));
		lblNewLabel_1.setBounds(100, 222, 100, 20);
		panel_3.add(lblNewLabel_1);

		JLabel textoHabitacion = new JLabel("Tipo de habitación:");
		textoHabitacion.setFont(new Font("Jost*", Font.BOLD | Font.ITALIC, 16));
		textoHabitacion.setBounds(80, 253, 145, 20);
		panel_3.add(textoHabitacion);

		JLabel lblNewLabel_2 = new JLabel("Estándar");
		lblNewLabel_2.setFont(new Font("Jost*", Font.BOLD | Font.ITALIC, 16));
		lblNewLabel_2.setForeground(Color.WHITE);
		lblNewLabel_2.setBounds(115, 284, 70, 20);
		panel_3.add(lblNewLabel_2);

		JLabel lblNewLabel_3 = new JLabel("Camas:");
		lblNewLabel_3.setFont(new Font("Jost*", Font.BOLD | Font.ITALIC, 16));
		lblNewLabel_3.setBounds(120, 315, 60, 20);
		panel_3.add(lblNewLabel_3);

		JLabel lblNewLabel_4 = new JLabel("2 Camas individuales");
		lblNewLabel_4.setFont(new Font("Jost*", Font.BOLD | Font.ITALIC, 16));
		lblNewLabel_4.setForeground(Color.WHITE);
		lblNewLabel_4.setBounds(68, 340, 168, 20);
		panel_3.add(lblNewLabel_4);

		JButton btnEditarHabitacion = new JButton("Editar habitación");
		btnEditarHabitacion.addActionListener(new ActionListener() {
			  public void actionPerformed(ActionEvent e) {
			        int respuesta = JOptionPane.showConfirmDialog(
			                null, 
			                "¿Estás seguro de que desea editar esta habitación?","Confirmación",JOptionPane.YES_NO_OPTION,   JOptionPane.WARNING_MESSAGE  );

			        if (respuesta == JOptionPane.YES_OPTION) {
			            JOptionPane.showMessageDialog(
			                null,"Datos editados.", "exito", JOptionPane.INFORMATION_MESSAGE);
			        }
			    }
			});
		btnEditarHabitacion.setBackground(new Color(50, 186, 124));
		btnEditarHabitacion.setFont(new Font("Inter", Font.BOLD | Font.ITALIC, 24));
		btnEditarHabitacion.setBorder(BorderFactory.createLineBorder(Color.BLACK,0));
		btnEditarHabitacion.setBounds(845, 566, 270, 60);
		panel.add(btnEditarHabitacion);
		
		JLabel lblNewLabel_5 = new JLabel("Tipo de habitación:");
		lblNewLabel_5.setBackground(new Color(255, 255, 255));
		lblNewLabel_5.setFont(new Font("Jost*", Font.BOLD, 22));
		lblNewLabel_5.setBounds(441, 193, 270, 40);
		panel.add(lblNewLabel_5);
		
		textField_1 = new JTextField();
		textField_1.setBackground(new Color(192, 192, 192));
		textField_1.setBounds(441, 244, 300, 40);
		panel.add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblNewLabel_6 = new JLabel("Camas:");
		lblNewLabel_6.setFont(new Font("Jost*", Font.BOLD, 22));
		lblNewLabel_6.setBounds(441, 295, 270, 40);
		panel.add(lblNewLabel_6);
		
		textField_2 = new JTextField();
		textField_2.setBackground(new Color(192, 192, 192));
		textField_2.setBounds(441, 346, 300, 40);
		panel.add(textField_2);
		textField_2.setColumns(10);
		
		JLabel lblNewLabel_7 = new JLabel("Tarifa:");
		lblNewLabel_7.setFont(new Font("Jost*", Font.BOLD, 22));
		lblNewLabel_7.setBounds(441, 397, 270, 40);
		panel.add(lblNewLabel_7);
		
		textField_3 = new JTextField();
		textField_3.setBackground(new Color(192, 192, 192));
		textField_3.setBounds(441, 448, 300, 40);
		panel.add(textField_3);
		textField_3.setColumns(10);
		
		JLabel lblNewLabel_8 = new JLabel("Extras:");
		lblNewLabel_8.setFont(new Font("Jost*", Font.BOLD, 22));
		lblNewLabel_8.setBounds(441, 499, 270, 40);
		panel.add(lblNewLabel_8);
		
		textField_4 = new JTextField();
		textField_4.setBackground(new Color(192, 192, 192));
		textField_4.setBounds(441, 550, 300, 40);
		panel.add(textField_4);
		textField_4.setColumns(10);
	}
}
