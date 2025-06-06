package ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;

import com.formdev.flatlaf.FlatLightLaf;

public class EditarTipoDeHabitacion {
//NOTA:
	/*
	 * CIAN
	 */
	/*
	 * LA IDEA ES QUE CUANDO EL SE TENGA QUE EDITAR LA TARIFA TENGAN QUE SER LAS QUE APARECEN EN 
	 * LA TABLA DE TARIFAS, CON ESTO SE LE ASIGNA EL PFRECIO  Y EL TIPO DE TARIFA QUE ES.
	 */
	JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EditarTipoDeHabitacion window = new EditarTipoDeHabitacion();
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
	public EditarTipoDeHabitacion() {
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
		panel_1.setBounds(0, 0, 1164, 95);
		panel_1.setBackground(new Color(0, 0, 0));
		panel.add(panel_1);
		panel_1.setLayout(null);

		JPanel panel_2 = new JPanel(); //Borde gris
		panel_2.setBounds(0, 95, 1164, 26);
		panel_2.setBackground(new Color(55, 54, 48));
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

		JLabel logo = new JLabel(""); //Logo
		logo.setBounds(0, 0, 170, 95);
		// CORRECCIÓN: Usar getResource para cargar la imagen
		ImageIcon icon2 = new ImageIcon(getClass().getResource("/images/logo.png"));
        Image imagen2 = icon2.getImage().getScaledInstance(170, 95, Image.SCALE_SMOOTH);
        logo.setIcon(new ImageIcon(imagen2));
		panel_1.add(logo);
		
		JLabel lblTarifas = new JLabel("Tipos de Habitacion");
		lblTarifas.setForeground(new Color(255, 255, 255));
		lblTarifas.setFont(new Font("Dialog", Font.BOLD, 36));
		lblTarifas.setBounds(141, 31, 583, 33);
		panel_1.add(lblTarifas);
		
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
		JButton meButton = new JButton("<html>Rentas &#8594;</html>"); //Botón superior rentas
		botonVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Aquí podrías abrir la ventana de Rentas
				frame.dispose(); // Cierra la ventana actual del menú
				EditartiposdeHabitaciones conexion = new EditartiposdeHabitaciones();
				conexion.frame.setVisible(true); 	
			}
		});
		botonVolver.setBounds(59, 131, 50, 50);
		ImageIcon icon42 = new ImageIcon(getClass().getResource("/images/flecha_izquierda.png"));
        Image imagen42 = icon42.getImage().getScaledInstance(36, 36, Image.SCALE_SMOOTH);
		botonVolver.setIcon(new ImageIcon(imagen42));
		panel.add(botonVolver);
		
		JLabel lblEditarTarifas = new JLabel("Editar habitacion:");
		lblEditarTarifas.setForeground(new Color(0, 0, 0));
		lblEditarTarifas.setFont(new Font("Dialog", Font.BOLD, 30));
		lblEditarTarifas.setBounds(108, 143, 315, 33);
		panel.add(lblEditarTarifas);
		
		JLabel lblNombre = new JLabel("Nombre:");
		lblNombre.setForeground(Color.BLACK);
		lblNombre.setFont(new Font("Dialog", Font.BOLD, 30));
		lblNombre.setBounds(108, 223, 289, 33);
		panel.add(lblNombre);
		
		JTextField textField = new JTextField();
		textField.setBounds(108, 266, 250, 33);
		panel.add(textField);
		textField.setColumns(10);
		
		JLabel lblDescripcion = new JLabel("Descripcion:");
		lblDescripcion.setForeground(Color.BLACK);
		lblDescripcion.setFont(new Font("Dialog", Font.BOLD, 30));
		lblDescripcion.setBounds(108, 323, 289, 33);
		panel.add(lblDescripcion);
		
		JTextField textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(108, 366, 237, 145);
		panel.add(textField_1);
		
		JLabel lblTipoDeTarifa = new JLabel("Tipo de tarifa:");
		lblTipoDeTarifa.setForeground(Color.BLACK);
		lblTipoDeTarifa.setFont(new Font("Dialog", Font.BOLD, 30));
		lblTipoDeTarifa.setBounds(440, 223, 289, 33);
		panel.add(lblTipoDeTarifa);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setFont(new Font("Dialog", Font.BOLD, 16));
		comboBox.setToolTipText("Rembolsable\r\nNo rembolsable\r\ncorporativa \r\nPromocional\r\n");
		comboBox.setBounds(440, 266, 250, 44);
		panel.add(comboBox);
		
		JLabel lblPrecio = new JLabel("Capacidad maxima");
		lblPrecio.setForeground(Color.BLACK);
		lblPrecio.setFont(new Font("Dialog", Font.BOLD, 30));
		lblPrecio.setBounds(440, 323, 333, 33);
		panel.add(lblPrecio);
		
		JButton btnNewButton = new JButton("Cancelar");
		btnNewButton.setBackground(Color.RED);
		btnNewButton.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 20));
		btnNewButton.setForeground(new Color(255, 255, 255));
		btnNewButton.setBounds(967, 517, 170, 50);
		panel.add(btnNewButton);
		
		JButton btnGuardarCambios = new JButton("Guardar cambios:\r\n");
		btnGuardarCambios.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
		        int respuesta = JOptionPane.showConfirmDialog(
		                null, 
		                "¿Estás seguro de que desea editar esta habitacion?", 
		                "Confirmación", 
		                JOptionPane.YES_NO_OPTION, 
		                JOptionPane.WARNING_MESSAGE
		        );

		        if (respuesta == JOptionPane.YES_OPTION) {
		            JOptionPane.showMessageDialog(
		                null, 
		                "habitacion editada.", 
		                "Información", 
		                JOptionPane.INFORMATION_MESSAGE
		            );
		        }
		    }
		});		
		btnGuardarCambios.setForeground(Color.WHITE);
		btnGuardarCambios.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 16));
		btnGuardarCambios.setBackground(new Color(255, 214, 10));
		btnGuardarCambios.setBounds(967, 592, 170, 50);
		panel.add(btnGuardarCambios);
		
		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setModel(new DefaultComboBoxModel(new String[] {"1 persona", "2 personas", "3 personas", "4 personas", "5 personas", "6 personas"}));
		comboBox_1.setToolTipText("Rembolsable\r\nNo rembolsable\r\ncorporativa \r\nPromocional\r\n");
		comboBox_1.setFont(new Font("Dialog", Font.BOLD, 16));
		comboBox_1.setBounds(440, 383, 250, 44);
		panel.add(comboBox_1);
		
		
	}
}