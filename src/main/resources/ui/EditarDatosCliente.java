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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.time.LocalDate; // Importar para LocalDate

import Dao.ClienteDAO;
import modelos.Cliente; // Importación corregida: modelos.Cliente (singular)

import ui.TiposHabitacion;
import ui.Rentas;
import ui.Clientes; // Referencia a la clase UI Clientes (plural)
import ui.PanelHabitaciones1;
import ui.Tarifas;
import ui.DatosUsuario; // Para volver a la vista de detalles del usuario

public class EditarDatosCliente {

	public JFrame frame;
	private JTextField textFieldID;
	private JTextField textFieldNombreApellido;
	private JTextField textFieldTelefono;
	private JTextField textFieldEmail;
    private JTextField textFieldDireccion; // Campo para la dirección
    private ClienteDAO clienteDAO;
    private Cliente clienteAEditar; // Cambiado a Cliente (singular)

	public EditarDatosCliente(int idCliente) {
        this(); // Llama al constructor sin argumentos para inicializar componentes
        clienteAEditar = clienteDAO.obtenerClientePorId(idCliente);

        if (clienteAEditar != null) {
            textFieldID.setText(String.valueOf(clienteAEditar.getIdCliente()));
            textFieldNombreApellido.setText(clienteAEditar.getNombre() + " " + clienteAEditar.getApellido());
            textFieldTelefono.setText(clienteAEditar.getTelefono());
            textFieldEmail.setText(clienteAEditar.getEmail());
            textFieldDireccion.setText(clienteAEditar.getDireccion()); // Cargar la dirección
            textFieldID.setEditable(false); // El ID no debe ser editable
        } else {
            JOptionPane.showMessageDialog(frame, "Cliente no encontrado para editar.", "Error", JOptionPane.ERROR_MESSAGE);
            frame.dispose();
            Clientes clientesWindow = new Clientes(); // Vuelve a la pantalla de clientes
            clientesWindow.frame.setVisible(true);
        }
    }

	public EditarDatosCliente() {
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

		JLabel Titulo = new JLabel("Editar Datos Cliente");
		Titulo.setForeground(new Color(255, 255, 255));
		Titulo.setFont(new Font("Jost* Medium", Font.PLAIN, 35));
		Titulo.setBounds(180, 11, 410, 73);
		panel_1.add(Titulo);

		JLabel menuTitulo = new JLabel("Editar datos:");
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
                if (clienteAEditar != null) {
                    DatosUsuario conexion = new DatosUsuario(clienteAEditar.getIdCliente());
                    conexion.frame.setVisible(true);
                } else {
                    Clientes conexion = new Clientes();
                    conexion.frame.setVisible(true);
                }
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
		
		JPanel panel_3 = new JPanel();
		panel_3.setBackground(new Color(0, 175, 185));
		panel_3.setBounds(131, 193, 300, 440);
		panel.add(panel_3);
		panel_3.setLayout(null);

		JLabel lblUserIcon = new JLabel("");
		lblUserIcon.setBounds(45, 11, 200, 200);
		ImageIcon iconUser = new ImageIcon(getClass().getResource("/Images/usuario.png"));
        Image imagenUser = iconUser.getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH);
        lblUserIcon.setIcon(new ImageIcon(imagenUser));
		panel_3.add(lblUserIcon);

		JLabel lblNewLabel_1 = new JLabel("ID del cliente:");
		lblNewLabel_1.setFont(new Font("Jost*", Font.BOLD | Font.ITALIC, 20));
		lblNewLabel_1.setBounds(90, 222, 135, 20);
		panel_3.add(lblNewLabel_1);

		JLabel textoNombre = new JLabel("Nombre:");
		textoNombre.setFont(new Font("Jost*", Font.BOLD | Font.ITALIC, 16));
		textoNombre.setBounds(120, 272, 145, 20);
		panel_3.add(textoNombre);

		JLabel lblCorreo = new JLabel("Correo:");
		lblCorreo.setFont(new Font("Jost*", Font.BOLD | Font.ITALIC, 16));
		lblCorreo.setBounds(120, 326, 60, 20);
		panel_3.add(lblCorreo);

		JLabel lblTelefonoPanel3 = new JLabel("Telefono:");
		lblTelefonoPanel3.setFont(new Font("Jost*", Font.BOLD | Font.ITALIC, 16));
		lblTelefonoPanel3.setBounds(120, 368, 80, 20);
		panel_3.add(lblTelefonoPanel3);

        JLabel lblDireccionPanel3 = new JLabel("Dirección:"); // Etiqueta para la dirección
        lblDireccionPanel3.setFont(new Font("Jost*", Font.BOLD | Font.ITALIC, 16));
        lblDireccionPanel3.setBounds(120, 410, 80, 20);
        panel_3.add(lblDireccionPanel3);


		JButton btnGuardarCambios = new JButton("Guardar cambios");
		btnGuardarCambios.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (clienteAEditar == null) {
                    JOptionPane.showMessageDialog(frame, "No hay cliente seleccionado para editar.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                String nombreCompleto = textFieldNombreApellido.getText().trim();
                String[] partesNombre = nombreCompleto.split(" ", 2);
                String nombre = partesNombre.length > 0 ? partesNombre[0] : "";
                String apellido = partesNombre.length > 1 ? partesNombre[1] : "";
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

                clienteAEditar.setNombre(nombre);
                clienteAEditar.setApellido(apellido);
                clienteAEditar.setTelefono(telefono);
                clienteAEditar.setEmail(email);
                clienteAEditar.setDireccion(direccion); // Establecer la dirección

                if (clienteDAO.actualizarCliente(clienteAEditar)) {
                    JOptionPane.showMessageDialog(frame, "Cliente actualizado exitosamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                    frame.dispose();
                    DatosUsuario datosUsuarioWindow = new DatosUsuario(clienteAEditar.getIdCliente());
                    datosUsuarioWindow.frame.setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(frame, "Error al actualizar el cliente.", "Error", JOptionPane.ERROR_MESSAGE);
                }
			}
		});
		btnGuardarCambios.setBackground(new Color(255, 214, 10));
		btnGuardarCambios.setFont(new Font("Inter", Font.BOLD | Font.ITALIC, 24));
		btnGuardarCambios.setBorder(BorderFactory.createLineBorder(Color.BLACK,0));
		btnGuardarCambios.setBounds(856, 573, 270, 60);
		panel.add(btnGuardarCambios);
		
		JLabel menuTitulo_1 = new JLabel("ID del cliente:");
		menuTitulo_1.setFont(new Font("Dialog", Font.BOLD, 24));
		menuTitulo_1.setBounds(441, 193, 239, 40);
		panel.add(menuTitulo_1);
		
		textFieldID = new JTextField();
		textFieldID.setBackground(Color.LIGHT_GRAY);
		textFieldID.setBounds(441, 244, 400, 40);
        textFieldID.setEditable(false);
		panel.add(textFieldID);
		textFieldID.setColumns(10);
		
		JLabel menuTitulo_1_1 = new JLabel("Nombre del cliente:");
		menuTitulo_1_1.setFont(new Font("Dialog", Font.BOLD, 24));
		menuTitulo_1_1.setBounds(441, 295, 300, 40);
		panel.add(menuTitulo_1_1);
		
		textFieldNombreApellido = new JTextField();
		textFieldNombreApellido.setBackground(Color.LIGHT_GRAY);
		textFieldNombreApellido.setBounds(441, 346, 400, 40);
		panel.add(textFieldNombreApellido);
		textFieldNombreApellido.setColumns(10);
		
		JLabel menuTitulo_1_1_1 = new JLabel("Numero del cliente:");
		menuTitulo_1_1_1.setFont(new Font("Dialog", Font.BOLD, 24));
		menuTitulo_1_1_1.setBounds(441, 397, 300, 40);
		panel.add(menuTitulo_1_1_1);
		
		textFieldTelefono = new JTextField();
		textFieldTelefono.setBackground(Color.LIGHT_GRAY);
		textFieldTelefono.setBounds(441, 448, 400, 40);
		panel.add(textFieldTelefono);
		textFieldTelefono.setColumns(10);
		
		JLabel menuTitulo_1_1_1_1 = new JLabel("Correo electronico:");
		menuTitulo_1_1_1_1.setFont(new Font("Dialog", Font.BOLD, 24));
		menuTitulo_1_1_1_1.setBounds(441, 499, 300, 40);
		panel.add(menuTitulo_1_1_1_1);
		
		textFieldEmail = new JTextField();
		textFieldEmail.setBackground(Color.LIGHT_GRAY);
		textFieldEmail.setBounds(441, 550, 400, 40);
		panel.add(textFieldEmail);
		textFieldEmail.setColumns(10);

        JLabel lblDireccion = new JLabel("Dirección:"); // Nueva etiqueta para la dirección
        lblDireccion.setFont(new Font("Dialog", Font.BOLD, 24));
        lblDireccion.setBounds(441, 601, 300, 40);
        panel.add(lblDireccion);

        textFieldDireccion = new JTextField(); // Nuevo campo para la dirección
        textFieldDireccion.setBackground(Color.LIGHT_GRAY);
        textFieldDireccion.setBounds(441, 652, 400, 40);
        panel.add(textFieldDireccion);
        textFieldDireccion.setColumns(10);
	}

    public void setVisible(boolean b) {
        if (frame != null) {
            frame.setVisible(b);
        }
    }
}
