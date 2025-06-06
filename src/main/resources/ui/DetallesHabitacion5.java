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
import javax.swing.SwingConstants;
import javax.swing.UIManager;

import com.formdev.flatlaf.FlatLightLaf;

public class DetallesHabitacion5 {

	private JFrame frame;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DetallesHabitacion5 window = new DetallesHabitacion5();
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
	public DetallesHabitacion5() {
		
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
		ImageIcon icon1 = new ImageIcon(getClass().getResource("/images/logo.png"));
        Image imagen1 = icon1.getImage().getScaledInstance(170, 95, Image.SCALE_SMOOTH);
        logo.setIcon(new ImageIcon(imagen1));
		panel_1.add(logo);
		
		JLabel Titulo = new JLabel("Panel de Habitaciones"); //Titulo 
		Titulo.setForeground(new Color(255, 255, 255));
		Titulo.setFont(new Font("Jost* Medium", Font.PLAIN, 35));
		Titulo.setBounds(180, 11, 410, 73);
		panel_1.add(Titulo);
		
		JLabel menuTitulo = new JLabel("Habitaciones:"); //Texto menú
		menuTitulo.setFont(new Font("Jost*", Font.BOLD, 38));
		menuTitulo.setBounds(131, 126, 245, 56);
		panel.add(menuTitulo);
		ImageIcon icon3 = new ImageIcon(getClass().getResource("/images/usuario.png"));
        Image imagen3 = icon3.getImage().getScaledInstance(36, 36, Image.SCALE_SMOOTH);
		ImageIcon icon2 = new ImageIcon(getClass().getResource("/images/informacion.png"));
        Image imagen2 = icon2.getImage().getScaledInstance(36, 36, Image.SCALE_SMOOTH);
		
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
		ImageIcon icon4 = new ImageIcon(getClass().getResource("/images/flecha_izquierda.png"));
        Image imagen4 = icon4.getImage().getScaledInstance(36, 36, Image.SCALE_SMOOTH);
        botonVolver.setIcon(new ImageIcon(imagen4));
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
		
		JButton btnBuscar = new JButton(""); //Boton para la barra de busqueda
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnBuscar.setBounds(720, 140, 40, 40);
		ImageIcon icon5 = new ImageIcon(getClass().getResource("/images/busqueda.png"));
        Image imagen5 = icon5.getImage().getScaledInstance(36, 36, Image.SCALE_SMOOTH);
        btnBuscar.setIcon(new ImageIcon(imagen5));
		btnBuscar.setBorderPainted(false);
		btnBuscar.setFocusPainted(false);
		btnBuscar.setContentAreaFilled(true);
		panel.add(btnBuscar);
		
		textField = new JTextField("BUSCAR"); //Texto de ejemplo
		textField.setToolTipText("");
		textField.setBounds(770, 140, 290, 40);
		textField.setColumns(10);
		textField.setBackground(new Color(217, 217, 217));
        textField.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 24));
        textField.setForeground(Color.GRAY);
        final String placeholder = "BUSCAR"; //Borra el texto de ejemplo al escribir en el campo
        textField.addFocusListener(new FocusAdapter() {

            public void focusGained(FocusEvent e) {
                if (textField.getText().equals(placeholder)) {
                    textField.setText("");
                    textField.setForeground(Color.BLACK);
                }
            }

            public void focusLost(FocusEvent e) {
                if (textField.getText().isEmpty()) {
                    textField.setText(placeholder);
                    textField.setForeground(Color.GRAY);
                }
            }
        });
		panel.add(textField);	
		
		JButton btnEliminarCliente = new JButton("Eliminar Habitación"); //Botón prinicipal eliminar cliente
		btnEliminarCliente.setBackground(new Color(239, 35, 60));
		btnEliminarCliente.setFont(new Font("Inter", Font.BOLD | Font.ITALIC, 24));
		btnEliminarCliente.setBorder(BorderFactory.createLineBorder(Color.BLACK,0));
		btnEliminarCliente.setForeground(Color.WHITE);
		btnEliminarCliente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnEliminarCliente.setBounds(131, 193, 280, 40);
		panel.add(btnEliminarCliente);
		
		JLabel lblNewLabel = new JLabel("Habitación no encontrada");
		lblNewLabel.setFont(new Font("Jost*", Font.BOLD, 60));
		lblNewLabel.setBounds(196, 368, 958, 63);
		panel.add(lblNewLabel);
		ImageIcon icon6 = new ImageIcon(getClass().getResource("/images/cama_matrimonial2.png"));
        Image imagen6 = icon6.getImage().getScaledInstance(120, 120, Image.SCALE_SMOOTH);
	}

}
