package ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.time.LocalDate; // Importar para LocalDate

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

import Dao.ClienteDAO;
import modelos.Cliente; // Importación corregida: modelos.Cliente (singular)

import ui.TiposHabitacion;
import ui.Rentas;
import ui.Clientes; // Referencia a la clase UI Clientes (plural, nombre de la clase UI)
import ui.PanelHabitaciones1;
import ui.Tarifas;

public class CrearNuevoCliente {

	public JFrame frame;
	private JTextField textFieldNombre;
	private JTextField textFieldApellido;
	private JTextField textFieldTelefono;
	private JTextField textFieldEmail;
    private JTextField textFieldDireccion; // Campo para la dirección
    private ClienteDAO clienteDAO;

	public CrearNuevoCliente() {
        try {
            UIManager.setLookAndFeel(new FlatLightLaf());
            UIManager.put("Button.arc", 90);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        clienteDAO = new ClienteDAO();
		initialize();
	}

	private void initialize() {
		frame = new JFrame();
		frame.setResizable(false);
		frame.setBounds(100, 100, 1180, 700);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 255, 255));
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);

		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(0, 0, 0));
		panel_1.setBounds(0, 0, 1164, 95);
		panel.add(panel_1);
		panel_1.setLayout(null);

		JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(55, 54, 48));
		panel_2.setBounds(0, 95, 1164, 26);
		panel.add(panel_2);
		panel_2.setLayout(null);

		JLabel logo = new JLabel("");
		logo.setBounds(0, 0, 170, 95);
		ImageIcon portada1 = new ImageIcon(getClass().getResource("/images/logo.png"));
        Image portada2 = portada1.getImage();
        Image portada3 = portada2.getScaledInstance(170, 95,Image.SCALE_SMOOTH);
        logo.setIcon(new ImageIcon(portada3));
		panel_1.add(logo);

		JLabel Titulo = new JLabel("Panel de clientes:");
		Titulo.setForeground(new Color(255, 255, 255));
		Titulo.setFont(new Font("Jost* Medium", Font.PLAIN, 35));
		Titulo.setBounds(180, 11, 410, 73);
		panel_1.add(Titulo);

		JLabel menuTitulo = new JLabel("Crear nuevo cliente:");
		menuTitulo.setFont(new Font("Jost*", Font.BOLD, 38));
		menuTitulo.setBounds(131, 126, 441, 56);
		panel.add(menuTitulo);
		
		JButton botonSuperior1 = new JButton("");
		botonSuperior1.setBackground(new Color(0, 0, 0));
		botonSuperior1.setBorderPainted(false);
		botonSuperior1.setFocusPainted(false);
		botonSuperior1.setContentAreaFilled(true);
		botonSuperior1.setBounds(1098, 11, 56, 56);
		ImageIcon c1 = new ImageIcon(getClass().getResource("/images/usuario.png"));
		Image c2 = c1.getImage();
		Image c3 = c2.getScaledInstance(36, 36, Image.SCALE_SMOOTH);
		botonSuperior1.setIcon(new ImageIcon(c3));
		panel_1.add(botonSuperior1);

		JButton botonSuperior2 = new JButton("");
		botonSuperior2.setBackground(new Color(0, 0, 0));
		botonSuperior2.setBorderPainted(false);
		botonSuperior2.setFocusPainted(false);
		botonSuperior2.setContentAreaFilled(true);
		botonSuperior2.setBounds(1032, 11, 56, 56);
		ImageIcon e1 = new ImageIcon(getClass().getResource("/images/informacion.png"));
		Image e2 = e1.getImage();
		Image e3 = e2.getScaledInstance(36, 36, Image.SCALE_SMOOTH);
		botonSuperior2.setIcon(new ImageIcon(e3));
		panel_1.add(botonSuperior2);

		JButton botonVolver = new JButton("");
		botonVolver.setForeground(new Color(255, 255, 255));
		botonVolver.setBackground(new Color(255, 255, 255));
		botonVolver.setBorderPainted(false);
		botonVolver.setFocusPainted(false);
		botonVolver.setContentAreaFilled(true);
		botonVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				Clientes conexion = new Clientes();
				conexion.frame.setVisible(true); 	
			}
		});
		botonVolver.setBounds(60, 132, 50, 50);
		ImageIcon f1 = new ImageIcon(getClass().getResource("/images/flecha_izquierda.png"));
		Image f2 = f1.getImage();
		Image f3 = f2.getScaledInstance(36, 36, Image.SCALE_SMOOTH);
		botonVolver.setIcon(new ImageIcon(f3));
		panel.add(botonVolver);

		JButton btnTiposDeRentas = new JButton("<html>Tipos de habitaciones &#8594;</html>");
		btnTiposDeRentas.setFont(new Font("Jost* Medium", Font.PLAIN, 12));
		btnTiposDeRentas.setForeground(new Color(255, 255, 255));
		btnTiposDeRentas.setBackground(new Color(56, 54, 41));
		btnTiposDeRentas.setBorder(BorderFactory.createLineBorder(Color.BLACK,0));
		btnTiposDeRentas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				TiposHabitacion conexion = new TiposHabitacion();
				conexion.frame.setVisible(true);
			}
		});
		btnTiposDeRentas.setBounds(1023, 0, 134, 23);
		btnTiposDeRentas.setBorderPainted(false);
        btnTiposDeRentas.setFocusPainted(false);
        btnTiposDeRentas.setContentAreaFilled(true);
		panel_2.add(btnTiposDeRentas);
		
		JButton btnrentas = new JButton("<html>Rentas &#8594;</html>");
		btnrentas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
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
		
		JButton btnclientes = new JButton("<html>Clientes &#8594;</html>");
		btnclientes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
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
		
		JButton btnhabitaciones = new JButton("<html>Habitaciones &#8594;</html>");
		btnhabitaciones.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
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
		
		JButton btntarifas = new JButton("<html>Tarifas &#8594;</html>");
		btntarifas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
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
		ImageIcon icon1 = new ImageIcon(getClass().getResource("/Images/usuario.png"));
        Image imagen1 = icon1.getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH);
        lblNewLabel.setIcon(new ImageIcon(imagen1));
		panel_3.add(lblNewLabel);

		JLabel lblNewLabel_1_id = new JLabel("ID del cliente:");
		lblNewLabel_1_id.setFont(new Font("Jost*", Font.BOLD | Font.ITALIC, 20));
		lblNewLabel_1_id.setBounds(90, 222, 135, 20);
		panel_3.add(lblNewLabel_1_id);

		JLabel textoHabitacion = new JLabel("Nombre:");
		textoHabitacion.setFont(new Font("Jost*", Font.BOLD | Font.ITALIC, 16));
		textoHabitacion.setBounds(120, 272, 145, 20);
		panel_3.add(textoHabitacion);

		JLabel lblNewLabel_3_correo = new JLabel("Correo:");
		lblNewLabel_3_correo.setFont(new Font("Jost*", Font.BOLD | Font.ITALIC, 16));
		lblNewLabel_3_correo.setBounds(120, 326, 60, 20);
		panel_3.add(lblNewLabel_3_correo);

		JLabel lblNewLabel_5_telefono = new JLabel("Telefono:");
		lblNewLabel_5_telefono.setFont(new Font("Jost*", Font.BOLD | Font.ITALIC, 16));
		lblNewLabel_5_telefono.setBounds(120, 368, 80, 20);
		panel_3.add(lblNewLabel_5_telefono);

		JButton btnCrearNuevoCliente = new JButton("Crear nuevo cliente");
		btnCrearNuevoCliente.setBackground(new Color(255, 214, 10));
		btnCrearNuevoCliente.setFont(new Font("Inter", Font.BOLD | Font.ITALIC, 24));
		btnCrearNuevoCliente.setBorder(BorderFactory.createLineBorder(Color.BLACK,0));
		btnCrearNuevoCliente.setBounds(856, 573, 270, 60);
        btnCrearNuevoCliente.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String nombre = textFieldNombre.getText().trim();
                String apellido = textFieldApellido.getText().trim();
                String telefono = textFieldTelefono.getText().trim();
                String email = textFieldEmail.getText().trim();
                String direccion = textFieldDireccion.getText().trim(); // Obtener la dirección

                if (nombre.isEmpty() || apellido.isEmpty() || telefono.isEmpty() || email.isEmpty() || direccion.isEmpty()) { // Validar dirección
                    JOptionPane.showMessageDialog(frame, "Todos los campos son obligatorios.", "Error de Validación", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                if (!email.matches("^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,6}$")) {
                    JOptionPane.showMessageDialog(frame, "Formato de email inválido.", "Error de Validación", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                if (!telefono.matches("^\\d{10}$")) {
                    JOptionPane.showMessageDialog(frame, "El teléfono debe contener 10 dígitos numéricos.", "Error de Validación", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                // La llamada al constructor de Cliente ahora es consistente con el modelo Cliente.java (7 argumentos)
                Cliente nuevoCliente = new Cliente(0, nombre, apellido, telefono, email, direccion, LocalDate.now()); 
                
                if (clienteDAO.agregarCliente(nuevoCliente)) { // Cambiado a agregarCliente
                    JOptionPane.showMessageDialog(frame, "Cliente creado exitosamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                    textFieldNombre.setText("");
                    textFieldApellido.setText("");
                    textFieldTelefono.setText("");
                    textFieldEmail.setText("");
                    textFieldDireccion.setText(""); // Limpiar campo de dirección
                    frame.dispose();
                    Clientes pantallaClientes = new Clientes();
                    pantallaClientes.setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(frame, "Error al crear el cliente. El cliente podría ya existir o hay un problema en la base de datos.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
		panel.add(btnCrearNuevoCliente);
		
		JLabel lblNombreCliente = new JLabel("Nombre:");
		lblNombreCliente.setFont(new Font("Dialog", Font.BOLD, 24));
		lblNombreCliente.setBounds(441, 200, 200, 30);
		panel.add(lblNombreCliente);
		
		textFieldNombre = new JTextField();
		textFieldNombre.setBackground(Color.LIGHT_GRAY);
		textFieldNombre.setBounds(441, 240, 250, 35);
		panel.add(textFieldNombre);
		textFieldNombre.setColumns(10);
		
		JLabel lblApellidoCliente = new JLabel("Apellido:");
		lblApellidoCliente.setFont(new Font("Dialog", Font.BOLD, 24));
		lblApellidoCliente.setBounds(700, 200, 200, 30);
		panel.add(lblApellidoCliente);
		
		textFieldApellido = new JTextField();
		textFieldApellido.setBackground(Color.LIGHT_GRAY);
		textFieldApellido.setBounds(700, 240, 250, 35);
		panel.add(textFieldApellido);
		textFieldApellido.setColumns(10);
		
		JLabel lblNumeroCliente = new JLabel("Teléfono:");
		lblNumeroCliente.setFont(new Font("Dialog", Font.BOLD, 24));
		lblNumeroCliente.setBounds(441, 300, 200, 30);
		panel.add(lblNumeroCliente);
		
		textFieldTelefono = new JTextField();
		textFieldTelefono.setBackground(Color.LIGHT_GRAY);
		textFieldTelefono.setBounds(441, 340, 250, 35);
		panel.add(textFieldTelefono);
		textFieldTelefono.setColumns(10);
		
		JLabel lblCorreoElectronico = new JLabel("Correo electrónico:");
		lblCorreoElectronico.setFont(new Font("Dialog", Font.BOLD, 24));
		lblCorreoElectronico.setBounds(700, 300, 250, 30);
		panel.add(lblCorreoElectronico);
		
		textFieldEmail = new JTextField();
		textFieldEmail.setBackground(Color.LIGHT_GRAY);
		textFieldEmail.setBounds(700, 340, 250, 35);
		panel.add(textFieldEmail);
		textFieldEmail.setColumns(10);

        JLabel lblDireccionCliente = new JLabel("Dirección:"); // Nueva etiqueta para dirección
        lblDireccionCliente.setFont(new Font("Dialog", Font.BOLD, 24));
        lblDireccionCliente.setBounds(441, 400, 200, 30);
        panel.add(lblDireccionCliente);

        textFieldDireccion = new JTextField(); // Nuevo campo para dirección
        textFieldDireccion.setBackground(Color.LIGHT_GRAY);
        textFieldDireccion.setBounds(441, 440, 250, 35);
        panel.add(textFieldDireccion);
        textFieldDireccion.setColumns(10);
	}

    public void setVisible(boolean b) {
        if (frame != null) {
            frame.setVisible(b);
        }
    }
}
