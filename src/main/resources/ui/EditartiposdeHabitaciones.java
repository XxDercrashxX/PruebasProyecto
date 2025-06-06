package ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.UIManager;

import com.formdev.flatlaf.FlatLightLaf;

public class EditartiposdeHabitaciones {

	JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EditartiposdeHabitaciones window = new EditartiposdeHabitaciones();
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
	public EditartiposdeHabitaciones() {
		try {
            UIManager.setLookAndFeel(new FlatLightLaf());
            UIManager.put("Button.arc", 0); // Esquinas redondas
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
		
		
		JLabel Titulo = new JLabel("Tipos  de habitaciones:"); //Titulo
		Titulo.setForeground(new Color(255, 255, 255));
		Titulo.setFont(new Font("Jost* Medium", Font.PLAIN, 35));
		Titulo.setBounds(180, 11, 410, 73);
		panel_1.add(Titulo);
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
				TiposHabitacion conexion = new TiposHabitacion();
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
        panel_3.setBackground(new Color(152, 193, 217));
        panel_3.setBounds(131, 193, 300, 440);
        panel.add(panel_3);
        panel_3.setLayout(null);

        JLabel lblImagen1 = new JLabel();
        lblImagen1.setBounds(50, 39, 200, 200);
        ImageIcon icon1 = new ImageIcon(getClass().getResource("/images/foto1.png"));
        Image imagen1 = icon1.getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH);
        lblImagen1.setIcon(new ImageIcon(imagen1));
        panel_3.add(lblImagen1);

        JLabel lblTexto1 = new JLabel("Habitaciones:");
        lblTexto1.setFont(new Font("Jost*", Font.BOLD | Font.ITALIC, 20));
        lblTexto1.setBounds(92, 249, 135, 20);
        panel_3.add(lblTexto1);
        
        JLabel lblEstandar = new JLabel("Estandar");
        lblEstandar.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 20));
        lblEstandar.setBounds(92, 10, 135, 20);
        panel_3.add(lblEstandar);
        
        JLabel lblTexto1_2 = new JLabel("3");
        lblTexto1_2.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 30));
        lblTexto1_2.setBounds(147, 289, 40, 29);
        panel_3.add(lblTexto1_2);
        
        JButton btnNewButton_1_1_1_1_1_1_1 = new JButton("Editar\r\n");
        btnNewButton_1_1_1_1_1_1_1.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		frame.dispose(); // Cierra la ventana actual del menú
				EditarTipoDeHabitacion conexion = new EditarTipoDeHabitacion();
				conexion.frame.setVisible(true); 	
        	}
        });
        btnNewButton_1_1_1_1_1_1_1.setFont(new Font("Tahoma", Font.BOLD, 20));
        btnNewButton_1_1_1_1_1_1_1.setBackground(new Color(44, 196, 196));
        btnNewButton_1_1_1_1_1_1_1.setBounds(84, 375, 124, 55);
        panel_3.add(btnNewButton_1_1_1_1_1_1_1);

        // Segundo panel
        JPanel panel_2_1 = new JPanel();
        panel_2_1.setLayout(null);
        panel_2_1.setBackground(new Color(152, 193, 217));
        panel_2_1.setBounds(461, 193, 300, 440);
        panel.add(panel_2_1);

        JLabel lblImagen2 = new JLabel();
        lblImagen2.setBounds(49, 36, 200, 200);
        ImageIcon icon2 = new ImageIcon(getClass().getResource("/images/foto2.png"));
        Image imagen2 = icon2.getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH);
        lblImagen2.setIcon(new ImageIcon(imagen2));
        panel_2_1.add(lblImagen2);
        
        JLabel lblTexto1_1 = new JLabel("Habitaciones:");
        lblTexto1_1.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 20));
        lblTexto1_1.setBounds(86, 246, 135, 20);
        panel_2_1.add(lblTexto1_1);
        
        JLabel lblTexto1_1_2 = new JLabel("Familiar");
        lblTexto1_1_2.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 20));
        lblTexto1_1_2.setBounds(86, 10, 135, 20);
        panel_2_1.add(lblTexto1_1_2);
        
        JLabel lblTexto1_2_1 = new JLabel("4");
        lblTexto1_2_1.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 30));
        lblTexto1_2_1.setBounds(138, 288, 40, 29);
        panel_2_1.add(lblTexto1_2_1);
        
        JButton btnNewButton_1_1_1_1_1_1_1_1 = new JButton("Editar\r\n");
        btnNewButton_1_1_1_1_1_1_1_1.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		frame.dispose(); // Cierra la ventana actual del menú
				EditarTipoDeHabitacion conexion = new EditarTipoDeHabitacion();
				conexion.frame.setVisible(true); 	
        	}
        });
        btnNewButton_1_1_1_1_1_1_1_1.setFont(new Font("Tahoma", Font.BOLD, 20));
        btnNewButton_1_1_1_1_1_1_1_1.setBackground(new Color(44, 196, 196));
        btnNewButton_1_1_1_1_1_1_1_1.setBounds(86, 375, 124, 55);
        panel_2_1.add(btnNewButton_1_1_1_1_1_1_1_1);

        // Tercer panel
        JPanel panel_1_1 = new JPanel();
        panel_1_1.setLayout(null);
        panel_1_1.setBackground(new Color(152, 193, 217));
        panel_1_1.setBounds(811, 193, 300, 440);
        panel.add(panel_1_1);

        JLabel lblImagen3 = new JLabel();
        lblImagen3.setBounds(51, 41, 200, 200);
        ImageIcon icon3 = new ImageIcon(getClass().getResource("/images/foto3.png"));
        Image imagen3 = icon3.getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH);
        lblImagen3.setIcon(new ImageIcon(imagen3));
        panel_1_1.add(lblImagen3);
        
        JLabel lblTexto1_1_1 = new JLabel("Habitaciones:");
        lblTexto1_1_1.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 20));
        lblTexto1_1_1.setBounds(86, 251, 135, 20);
        panel_1_1.add(lblTexto1_1_1);
        
        JLabel lblTexto1_1_1_1 = new JLabel("Suite");
        lblTexto1_1_1_1.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 20));
        lblTexto1_1_1_1.setBounds(86, 10, 135, 20);
        panel_1_1.add(lblTexto1_1_1_1);
        
        JLabel lblTexto1_2_1_1 = new JLabel("5");
        lblTexto1_2_1_1.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 30));
        lblTexto1_2_1_1.setBounds(139, 281, 40, 29);
        panel_1_1.add(lblTexto1_2_1_1);
        
        JButton btnNewButton_1_1_1_1_1_1_1_1_1 = new JButton("Editar\r\n");
        btnNewButton_1_1_1_1_1_1_1_1_1.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		frame.dispose(); // Cierra la ventana actual del menú
				EditarTipoDeHabitacion conexion = new EditarTipoDeHabitacion();
				conexion.frame.setVisible(true); 	
        	}
        });
        btnNewButton_1_1_1_1_1_1_1_1_1.setFont(new Font("Tahoma", Font.BOLD, 20));
        btnNewButton_1_1_1_1_1_1_1_1_1.setBackground(new Color(44, 196, 196));
        btnNewButton_1_1_1_1_1_1_1_1_1.setBounds(97, 375, 124, 55);
        panel_1_1.add(btnNewButton_1_1_1_1_1_1_1_1_1);
        
        JButton btnNewButton_1_1_1_1_1_1 = new JButton("Editar\r\n");
        btnNewButton_1_1_1_1_1_1.setFont(new Font("Tahoma", Font.BOLD, 20));
        btnNewButton_1_1_1_1_1_1.setBackground(new Color(44, 196, 196));
        btnNewButton_1_1_1_1_1_1.setBounds(265, 128, 124, 55);
        panel.add(btnNewButton_1_1_1_1_1_1);
	}
}
