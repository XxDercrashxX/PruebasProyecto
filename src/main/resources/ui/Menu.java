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
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;

import com.formdev.flatlaf.FlatLightLaf;
import javax.swing.JButton;

public class Menu {

	JFrame frame; // El frame es privado, pero su visibilidad se controla con el método setVisible(boolean b)

	/**
	 * Launch the application.
	 */
	
	/**
	 * Create the application.
	 */
	public Menu() {

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
				// Aquí podrías abrir la ventana de Rentas
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
		btnclientes.setForeground(Color.WHITE);
		btnclientes.setFont(new Font("Jost* Medium", Font.PLAIN, 12));
		btnclientes.setFocusPainted(false);
		btnclientes.setContentAreaFilled(true);
		btnclientes.setBorderPainted(false);
		btnclientes.setBorder(BorderFactory.createLineBorder(Color.BLACK,0));
		btnclientes.setBackground(new Color(56, 54, 41));
		btnclientes.setBounds(841, 0, 81, 23);
		panel_2.add(btnclientes);
		
		JButton btnClientes = new JButton("<html>Rentas &#8594;</html>"); //Botón superior rentas
		btnclientes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Aquí podrías abrir la ventana de Rentas
				frame.dispose(); // Cierra la ventana actual del menú
				Clientes conexion = new Clientes();
				conexion.frame.setVisible(true); 	
			}
		});
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
		
		JButton btnHabitaciones = new JButton("<html>Rentas &#8594;</html>"); //Botón superior rentas
		btnhabitaciones.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Aquí podrías abrir la ventana de Rentas
				frame.dispose(); // Cierra la ventana actual del menú
				PanelHabitaciones1 conexion = new PanelHabitaciones1();
				conexion.frame.setVisible(true); 	
			}
		});

		JButton btntarifas = new JButton("<html>Tarifas &#8594;</html>"); //Botón superior tarifas
		btntarifas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Aquí podrías abrir la ventana de Tarifas
				
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

		JLabel logo = new JLabel(""); //Logo
		logo.setBounds(0, 0, 170, 95);
		// CORRECCIÓN: Usar getResource para cargar la imagen
		ImageIcon icon2 = new ImageIcon(getClass().getResource("/images/logo.png"));
        Image imagen2 = icon2.getImage().getScaledInstance(170, 95, Image.SCALE_SMOOTH);
        logo.setIcon(new ImageIcon(imagen2));
		panel_1.add(logo);

		JLabel Titulo = new JLabel("Bienvenidos"); //Titulo
		Titulo.setForeground(new Color(255, 255, 255));
		Titulo.setFont(new Font("Jost* Medium", Font.PLAIN, 35));
		Titulo.setBounds(180, 11, 410, 73);
		panel_1.add(Titulo);

		JLabel menuTitulo = new JLabel("Menú:"); //Texto menú
		menuTitulo.setFont(new Font("Jost*", Font.BOLD, 38));
		menuTitulo.setBounds(60, 131, 144, 56);
		panel.add(menuTitulo);

		JPanel panel_3 = new JPanel(); //Recuadro al fondo de los botones
		panel_3.setBackground(new Color(56, 54, 41));
		panel_3.setBounds(60, 190, 660, 360);
		panel.add(panel_3);
		panel_3.setLayout(null);

		JButton botonPanelDeHabitaciones = new JButton("<html><div style='text-align:center;'>Panel de Habitaciones<br><br></div></html>"); //Botón panel de habitaciones
		botonPanelDeHabitaciones.setFont(new Font("Jost*", Font.BOLD, 16));
		botonPanelDeHabitaciones.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Ejemplo de cómo abrir otra ventana (DetallesHabitacion1)
				frame.dispose(); // Cierra la ventana actual del menú
				PanelHabitaciones1 conexion = new PanelHabitaciones1();
				conexion.frame.setVisible(true); 	
			}
		});
		botonPanelDeHabitaciones.setBackground(new Color(255, 214, 10));
		botonPanelDeHabitaciones.setBounds(22, 10, 125, 150);
		botonPanelDeHabitaciones.setVerticalTextPosition(SwingConstants.BOTTOM); //Para poner texto debajo de la imagen dentro del botón
        botonPanelDeHabitaciones.setHorizontalTextPosition(SwingConstants.CENTER);
        botonPanelDeHabitaciones.setHorizontalAlignment(SwingConstants.CENTER);
        botonPanelDeHabitaciones.setVerticalAlignment(SwingConstants.CENTER);
        botonPanelDeHabitaciones.setIconTextGap(1);
		// CORRECCIÓN: Usar getResource para cargar la imagen
        ImageIcon icon121 = new ImageIcon(getClass().getResource("/images/cama_individual.png"));
        Image imagen121 = icon121.getImage().getScaledInstance(70, 80, Image.SCALE_SMOOTH);
        botonPanelDeHabitaciones.setIcon(new ImageIcon(imagen121));
		botonPanelDeHabitaciones.setBorder(BorderFactory.createLineBorder(Color.BLACK,0));
		panel_3.add(botonPanelDeHabitaciones);

		JButton botonClientes = new JButton("Clientes"); //Botón de clientes
		botonClientes.setFont(new Font("Jost*", Font.BOLD, 16));
		botonClientes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Aquí podrías abrir la ventana de Clientes
				frame.dispose(); // Cierra la ventana actual del menú
				Clientes conexion = new Clientes();
				conexion.frame.setVisible(true); 	
			}
		});
		botonClientes.setBorder(BorderFactory.createLineBorder(Color.BLACK,0));
		botonClientes.setBackground(new Color(255, 214, 10));
		botonClientes.setBounds(182, 10, 125, 150);
		botonClientes.setVerticalTextPosition(SwingConstants.BOTTOM); //Para poner texto debajo de la imagen dentro del botón
		botonClientes.setHorizontalTextPosition(SwingConstants.CENTER);
		botonClientes.setHorizontalAlignment(SwingConstants.CENTER);
		botonClientes.setVerticalAlignment(SwingConstants.CENTER);
		botonClientes.setIconTextGap(1);
		// CORRECCIÓN: Usar getResource para cargar la imagen
		ImageIcon s1 = new ImageIcon(getClass().getResource("/images/clientes.png"));
		Image s2 = s1.getImage();
		Image s3 = s2.getScaledInstance(65, 60, Image.SCALE_SMOOTH);
		botonClientes.setIcon(new ImageIcon(s3));
		botonClientes.setBorder(BorderFactory.createLineBorder(Color.BLACK,0));
		panel_3.add(botonClientes);

		JButton botonRentas = new JButton("Rentas"); //Botón de rentas
		botonRentas.setFont(new Font("Jost*", Font.BOLD, 16));
		botonRentas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Aquí podrías abrir la ventana de Rentas
				frame.dispose(); // Cierra la ventana actual del menú
				Rentas renta = new Rentas();
				renta.frame.setVisible(true); 				
			}
		});
		botonRentas.setBorder(BorderFactory.createLineBorder(Color.BLACK,0));
		botonRentas.setBackground(new Color(255, 214, 10));
		botonRentas.setBounds(347, 10, 125, 150);
		botonRentas.setVerticalTextPosition(SwingConstants.BOTTOM); //Para poner texto debajo de la imagen dentro del botón
		botonRentas.setHorizontalTextPosition(SwingConstants.CENTER);
		botonRentas.setHorizontalAlignment(SwingConstants.CENTER);
        botonRentas.setVerticalAlignment(SwingConstants.CENTER);
        botonRentas.setIconTextGap(1);
		// CORRECCIÓN: Usar getResource para cargar la imagen
        ImageIcon icon112 = new ImageIcon(getClass().getResource("/images/renta.png"));
        Image imagen112 = icon112.getImage().getScaledInstance(70, 80, Image.SCALE_SMOOTH);
        botonRentas.setIcon(new ImageIcon(imagen112));
		panel_3.add(botonRentas);

		JButton botonTiposDeHabitaciones = new JButton("<html><div style='text-align:center;'>Tipos de Habitaciones<br><br></div></html>"); // Botón tipos de habitación
		botonTiposDeHabitaciones.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        frame.dispose(); // Cierra la ventana actual del menú
		        TiposHabitacion conexion = new TiposHabitacion();
		        conexion.frame.setVisible(true); 
		    }
		});
		botonTiposDeHabitaciones.setFont(new Font("Jost*", Font.BOLD, 16));
		botonTiposDeHabitaciones.setBorder(BorderFactory.createLineBorder(Color.BLACK, 0));
		botonTiposDeHabitaciones.setBackground(new Color(255, 214, 10));
		botonTiposDeHabitaciones.setBounds(512, 10, 125, 150);
		botonTiposDeHabitaciones.setVerticalTextPosition(SwingConstants.BOTTOM); // Para poner texto debajo de la imagen dentro del botón
		botonTiposDeHabitaciones.setHorizontalTextPosition(SwingConstants.CENTER);
		botonTiposDeHabitaciones.setHorizontalAlignment(SwingConstants.CENTER);
		botonTiposDeHabitaciones.setVerticalAlignment(SwingConstants.CENTER);
		botonTiposDeHabitaciones.setIconTextGap(1);

		// Asigna un ícono exclusivo a botonTiposDeHabitaciones
		ImageIcon iconTiposHabitaciones = new ImageIcon(getClass().getResource("/images/cama_doble.png"));
		Image imagenTiposHabitaciones = iconTiposHabitaciones.getImage().getScaledInstance(70, 80, Image.SCALE_SMOOTH);
		botonTiposDeHabitaciones.setIcon(new ImageIcon(imagenTiposHabitaciones));

		panel_3.add(botonTiposDeHabitaciones);


		JButton botonTarifas = new JButton("Tarifas"); //Botón de tarifas
		botonTarifas.setFont(new Font("Jost*", Font.BOLD, 16));
		botonTarifas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Aquí podrías abrir la ventana de Tarifas
				frame.dispose(); // Cierra la ventana actual del menú
				Tarifas conexion = new Tarifas();
				conexion.frame.setVisible(true); 	
				
				
			}
		});
		botonTarifas.setBorder(BorderFactory.createLineBorder(Color.BLACK,0));
		botonTarifas.setBackground(new Color(255, 214, 10));
		botonTarifas.setBounds(22, 199, 125, 150);
		botonTarifas.setVerticalTextPosition(SwingConstants.BOTTOM); //Para poner texto debajo de la imagen dentro del botón
		botonTarifas.setHorizontalTextPosition(SwingConstants.CENTER);
		botonTarifas.setHorizontalAlignment(SwingConstants.CENTER);
		botonTarifas.setVerticalAlignment(SwingConstants.CENTER);
		botonTarifas.setIconTextGap(1);
		// CORRECCIÓN: Usar getResource para cargar la imagen
		ImageIcon icon1 = new ImageIcon(getClass().getResource("/images/tarifa_por_hora.png"));
        Image imagen1 = icon1.getImage().getScaledInstance(50, 60, Image.SCALE_SMOOTH);
        botonTarifas.setIcon(new ImageIcon(imagen1));
		panel_3.add(botonTarifas);

		JLabel decoracion = new JLabel(""); //imagen junto al menú
		decoracion.setBounds(730, 150, 424, 400);
		// CORRECCIÓN: Usar getResource para cargar la imagen
		ImageIcon icon12 = new ImageIcon(getClass().getResource("/images/decoracion.png"));
        Image imagen12 = icon12.getImage().getScaledInstance(400, 400, Image.SCALE_SMOOTH);
        decoracion.setIcon(new ImageIcon(imagen12));
		panel.add(decoracion);
	}

	protected void dispose() {
		// TODO Auto-generated method stub
		
	}

	// Este método debe hacer visible el JFrame interno 'frame'
	public void setVisible(boolean b) {
		if (frame != null) {
			frame.setVisible(b);
		}
	}

	// Opcional: un getter para el frame si lo necesitas acceder directamente desde otras clases
	public JFrame getFrame() {
		return frame;
	}
}
