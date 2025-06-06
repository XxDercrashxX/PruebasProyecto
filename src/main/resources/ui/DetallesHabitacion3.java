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
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;

import com.formdev.flatlaf.FlatLightLaf;

public class DetallesHabitacion3 {

	public JFrame frame; // <--- ¡CAMBIO AQUÍ! DEBE SER PUBLIC

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					// Set the look and feel here as well, if you want it applied to the main method launch
					UIManager.setLookAndFeel(new FlatLightLaf());
					UIManager.put("Button.arc", 90);

					DetallesHabitacion3 window = new DetallesHabitacion3();
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
	public DetallesHabitacion3() {

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
		menuTitulo.setBounds(131, 126, 441, 56);
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

		JLabel lblNewLabel_2 = new JLabel("Suit");
		lblNewLabel_2.setFont(new Font("Jost*", Font.BOLD | Font.ITALIC, 16));
		lblNewLabel_2.setForeground(Color.WHITE);
		lblNewLabel_2.setBounds(133, 284, 35, 20);
		panel_3.add(lblNewLabel_2);

		JLabel lblNewLabel_3 = new JLabel("Camas:");
		lblNewLabel_3.setFont(new Font("Jost*", Font.BOLD | Font.ITALIC, 16));
		lblNewLabel_3.setBounds(120, 315, 60, 20);
		panel_3.add(lblNewLabel_3);

		JLabel lblNewLabel_4 = new JLabel("4 Camas individuales");
		lblNewLabel_4.setFont(new Font("Jost*", Font.BOLD | Font.ITALIC, 16));
		lblNewLabel_4.setForeground(Color.WHITE);
		lblNewLabel_4.setBounds(68, 340, 168, 20);
		panel_3.add(lblNewLabel_4);

		JLabel lblNewLabel_5 = new JLabel("Ocupante:");
		lblNewLabel_5.setFont(new Font("Jost*", Font.BOLD | Font.ITALIC, 16));
		lblNewLabel_5.setBounds(110, 365, 80, 20);
		panel_3.add(lblNewLabel_5);

		JLabel lblNewLabel_6 = new JLabel("Diego Ontiveros");
		lblNewLabel_6.setFont(new Font("Jost*", Font.BOLD | Font.ITALIC, 16));
		lblNewLabel_6.setForeground(Color.WHITE);
		lblNewLabel_6.setBounds(90, 396, 125, 20);
		panel_3.add(lblNewLabel_6);

		JPanel panel_4 = new JPanel();
		panel_4.setBackground(new Color(0, 187, 249));
		panel_4.setBounds(441, 193, 340, 140);
		panel.add(panel_4);
		panel_4.setLayout(null);

		JLabel lblNewLabel_7 = new JLabel("Historial de rentas:");
		lblNewLabel_7.setFont(new Font("Jost*", Font.BOLD | Font.ITALIC, 32));
		lblNewLabel_7.setBounds(10, 11, 320, 30);
		panel_4.add(lblNewLabel_7);

		JLabel lblNewLabel_8 = new JLabel("rentado del 14 al 15 de mayo 2024");
		lblNewLabel_8.setFont(new Font("Jost*", Font.BOLD | Font.ITALIC, 16));
		lblNewLabel_8.setForeground(Color.WHITE);
		lblNewLabel_8.setBounds(10, 52, 320, 20);
		panel_4.add(lblNewLabel_8);

		JLabel lblNewLabel_9 = new JLabel("rentanado del 13 al 23 de agosto 2018");
		lblNewLabel_9.setFont(new Font("Jost*", Font.BOLD | Font.ITALIC, 16));
		lblNewLabel_9.setForeground(Color.WHITE);
		lblNewLabel_9.setBounds(10, 83, 320, 20);
		panel_4.add(lblNewLabel_9);

		JLabel lblNewLabel_10 = new JLabel("rentado del  4 al 7 de abril 2016");
		lblNewLabel_10.setFont(new Font("Jost*", Font.BOLD | Font.ITALIC, 16));
		lblNewLabel_10.setForeground(Color.WHITE);
		lblNewLabel_10.setBounds(10, 114, 320, 20);
		panel_4.add(lblNewLabel_10);

		JPanel panel_5 = new JPanel();
		panel_5.setBackground(new Color(0, 187, 249));
		panel_5.setBounds(441, 344, 340, 140);
		panel.add(panel_5);
		panel_5.setLayout(null);

		JLabel lblNewLabel_11 = new JLabel("Tarifas:");
		lblNewLabel_11.setFont(new Font("Jost*", Font.BOLD | Font.ITALIC, 32));
		lblNewLabel_11.setBounds(10, 11, 320, 30);
		panel_5.add(lblNewLabel_11);

		JLabel lblNewLabel_12 = new JLabel("$700 pesos por noche");
		lblNewLabel_12.setFont(new Font("Jost*", Font.BOLD | Font.ITALIC, 16));
		lblNewLabel_12.setForeground(Color.WHITE);
		lblNewLabel_12.setBounds(10, 52, 320, 20);
		panel_5.add(lblNewLabel_12);

		JLabel lblNewLabel_13 = new JLabel("descuento los dias primero de cada mes");
		lblNewLabel_13.setFont(new Font("Jost*", Font.BOLD | Font.ITALIC, 16));
		lblNewLabel_13.setForeground(Color.WHITE);
		lblNewLabel_13.setBounds(10, 83, 320, 20);
		panel_5.add(lblNewLabel_13);

		JLabel lblNewLabel_14 = new JLabel("costo $400 pesos");
		lblNewLabel_14.setFont(new Font("Jost*", Font.BOLD | Font.ITALIC, 16));
		lblNewLabel_14.setForeground(Color.WHITE);
		lblNewLabel_14.setBounds(10, 114, 320, 14);
		panel_5.add(lblNewLabel_14);

		JPanel panel_6 = new JPanel();
		panel_6.setBackground(new Color(0, 187, 249));
		panel_6.setBounds(441, 495, 340, 138);
		panel.add(panel_6);
		panel_6.setLayout(null);

		JLabel lblNewLabel_15 = new JLabel("Servicios extras:");
		lblNewLabel_15.setFont(new Font("Jost*", Font.BOLD | Font.ITALIC, 32));
		lblNewLabel_15.setBounds(10, 11, 320, 30);
		panel_6.add(lblNewLabel_15);

		JLabel lblNewLabel_16 = new JLabel("desayuno/comida/cena $500 pesos");
		lblNewLabel_16.setFont(new Font("Jost*", Font.BOLD | Font.ITALIC, 16));
		lblNewLabel_16.setForeground(Color.WHITE);
		lblNewLabel_16.setBounds(10, 52, 320, 20);
		panel_6.add(lblNewLabel_16);

		JLabel lblNewLabel_17 = new JLabel("renta de consola: $450 pesos");
		lblNewLabel_17.setFont(new Font("Jost*", Font.BOLD | Font.ITALIC, 16));
		lblNewLabel_17.setForeground(Color.WHITE);
		lblNewLabel_17.setBounds(10, 83, 320, 20);
		panel_6.add(lblNewLabel_17);

		JButton btnOcupado = new JButton("OCUPADO");
		btnOcupado.setBackground(new Color(239, 35, 60));
		btnOcupado.setForeground(Color.WHITE);
		btnOcupado.setFont(new Font("Inter", Font.BOLD | Font.ITALIC, 24));
		btnOcupado.setBorder(BorderFactory.createLineBorder(Color.BLACK,0));
		btnOcupado.setBounds(845, 200, 270, 60);
		panel.add(btnOcupado);

		JButton btnHistorial = new JButton("Descargar historial");
		btnHistorial.setBackground(new Color(255, 214, 10));
		btnHistorial.setFont(new Font("Inter", Font.BOLD | Font.ITALIC, 24));
		btnHistorial.setBorder(BorderFactory.createLineBorder(Color.BLACK,0));
		btnHistorial.setBounds(845, 495, 270, 60);
		panel.add(btnHistorial);

		JButton btnEditarHabitacion = new JButton("Editar habitación");
		btnEditarHabitacion.setBackground(new Color(50, 186, 124));
		btnEditarHabitacion.setFont(new Font("Inter", Font.BOLD | Font.ITALIC, 24));
		btnEditarHabitacion.setBorder(BorderFactory.createLineBorder(Color.BLACK,0));
		btnEditarHabitacion.setBounds(845, 566, 270, 60);
		panel.add(btnEditarHabitacion);
	}

}
