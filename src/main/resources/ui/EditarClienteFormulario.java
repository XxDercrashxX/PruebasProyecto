package ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;

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
import modelos.Cliente;

import ui.TiposHabitacion;
import ui.Rentas;
import ui.Clientes;
import ui.PanelHabitaciones1;
import ui.Tarifas;
import ui.Menu;

public class EditarClienteFormulario {

	public JFrame frame;
	private JTextField textFieldNombre;
	private JTextField textFieldApellido;
	private JTextField textFieldTelefono;
	private JTextField textFieldEmail;
	private ClienteDAO clienteDAO;
	private Cliente clienteAEditar;

	public EditarClienteFormulario(int idCliente) {
		this();
		clienteDAO = new ClienteDAO();
		clienteAEditar = clienteDAO.obtenerClientePorId(idCliente);

		if (clienteAEditar != null) {
			textFieldNombre.setText(clienteAEditar.getNombre());
			textFieldApellido.setText(clienteAEditar.getApellido());
			textFieldTelefono.setText(clienteAEditar.getTelefono());
			textFieldEmail.setText(clienteAEditar.getEmail());
		} else {
			JOptionPane.showMessageDialog(frame, "Cliente no encontrado.", "Error", JOptionPane.ERROR_MESSAGE);
			frame.dispose();
			Clientes clientesWindow = new Clientes();
			clientesWindow.frame.setVisible(true);
		}
	}

	public EditarClienteFormulario() {
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
		panel_1.setBounds(0, 0, 1164, 95);
		panel_1.setBackground(new Color(0, 0, 0));
		panel.add(panel_1);
		panel_1.setLayout(null);

		JPanel panel_2 = new JPanel();
		panel_2.setBounds(0, 95, 1164, 26);
		panel_2.setBackground(new Color(55, 54, 48));
		panel.add(panel_2);
		panel_2.setLayout(null);

		JButton btnTiposDeRentas = new JButton("<html>Tipos de habitaciones &#8594;</html>");
		btnTiposDeRentas.setFont(new Font("Jost* Medium", Font.PLAIN, 12));
		btnTiposDeRentas.setForeground(new Color(255, 255, 255));
		btnTiposDeRentas.setBackground(new Color(56, 54, 41));
		btnTiposDeRentas.setBorder(BorderFactory.createLineBorder(Color.BLACK,0));
		btnTiposDeRentas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				TiposHabitacion tiposHabitacionWindow = new TiposHabitacion();
				tiposHabitacionWindow.frame.setVisible(true);
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
				Rentas rentasWindow = new Rentas();
				rentasWindow.frame.setVisible(true);
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
		btnclientes.setForeground(Color.WHITE);
		btnclientes.setFont(new Font("Jost* Medium", Font.PLAIN, 12));
		btnclientes.setFocusPainted(false);
		btnclientes.setContentAreaFilled(true);
		btnclientes.setBorderPainted(false);
		btnclientes.setBorder(BorderFactory.createLineBorder(Color.BLACK,0));
		btnclientes.setBackground(new Color(56, 54, 41));
		btnclientes.setBounds(841, 0, 81, 23);
		btnclientes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				Clientes clientesWindow = new Clientes();
				clientesWindow.frame.setVisible(true);
			}
		});
		panel_2.add(btnclientes);

		JButton btnhabitaciones = new JButton("<html>Habitaciones &#8594;</html>");
		btnhabitaciones.setForeground(Color.WHITE);
		btnhabitaciones.setFont(new Font("Jost* Medium", Font.PLAIN, 12));
		btnhabitaciones.setFocusPainted(false);
		btnhabitaciones.setContentAreaFilled(true);
		btnhabitaciones.setBorderPainted(false);
		btnhabitaciones.setBorder(BorderFactory.createLineBorder(Color.BLACK,0));
		btnhabitaciones.setBackground(new Color(56, 54, 41));
		btnhabitaciones.setBounds(731, 0, 100, 23);
		btnhabitaciones.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				PanelHabitaciones1 habitacionesWindow = new PanelHabitaciones1();
				habitacionesWindow.frame.setVisible(true);
			}
		});
		panel_2.add(btnhabitaciones);

		JButton btntarifas = new JButton("<html>Tarifas &#8594;</html>");
		btntarifas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				Tarifas tarifasWindow = new Tarifas();
				tarifasWindow.frame.setVisible(true);
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

		JLabel logoPanel1 = new JLabel("");
		logoPanel1.setBounds(0, 0, 170, 95);
		ImageIcon icon12 = new ImageIcon(getClass().getResource("/images/logo.png"));
        Image imagen12 = icon12.getImage().getScaledInstance(170, 95, Image.SCALE_SMOOTH);
        logoPanel1.setIcon(new ImageIcon(imagen12));
		panel_1.add(logoPanel1);

		JLabel TituloPanel1 = new JLabel("Editar Cliente");
		TituloPanel1.setForeground(new Color(255, 255, 255));
		TituloPanel1.setFont(new Font("Jost* Medium", Font.PLAIN, 35));
		TituloPanel1.setBounds(180, 11, 410, 73);
		panel_1.add(TituloPanel1);

		JButton botonVolver = new JButton("");
		botonVolver.setForeground(new Color(255, 255, 255));
		botonVolver.setBackground(new Color(255, 255, 255));
		botonVolver.setBorderPainted(false);
		botonVolver.setFocusPainted(false);
		botonVolver.setContentAreaFilled(true);
		botonVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				Clientes clientesWindow = new Clientes();
				clientesWindow.frame.setVisible(true);
			}
		});
		botonVolver.setBounds(60, 132, 36, 36);
		ImageIcon icon69 = new ImageIcon(getClass().getResource("/images/flecha_izquierda.png"));
        Image imagen69 = icon69.getImage().getScaledInstance(36, 36, Image.SCALE_SMOOTH);
		botonVolver.setIcon(new ImageIcon(imagen69));
		panel.add(botonVolver);

		JLabel lblEditarCliente = new JLabel("Editar Cliente:");
		lblEditarCliente.setFont(new Font("Jost*", Font.BOLD, 38));
		lblEditarCliente.setBounds(131, 126, 400, 56);
		panel.add(lblEditarCliente);

		JLabel lblNombre = new JLabel("Nombre:");
		lblNombre.setFont(new Font("Dialog", Font.BOLD, 24));
		lblNombre.setBounds(100, 200, 200, 30);
		panel.add(lblNombre);

		textFieldNombre = new JTextField();
		textFieldNombre.setFont(new Font("Dialog", Font.PLAIN, 16));
		textFieldNombre.setBounds(100, 240, 250, 35);
		panel.add(textFieldNombre);
		textFieldNombre.setColumns(10);

		JLabel lblApellido = new JLabel("Apellido:");
		lblApellido.setFont(new Font("Dialog", Font.BOLD, 24));
		lblApellido.setBounds(400, 200, 200, 30);
		panel.add(lblApellido);

		textFieldApellido = new JTextField();
		textFieldApellido.setFont(new Font("Dialog", Font.PLAIN, 16));
		textFieldApellido.setBounds(400, 240, 250, 35);
		panel.add(textFieldApellido);
		textFieldApellido.setColumns(10);

		JLabel lblTelefono = new JLabel("Teléfono:");
		lblTelefono.setFont(new Font("Dialog", Font.BOLD, 24));
		lblTelefono.setBounds(100, 300, 200, 30);
		panel.add(lblTelefono);

		textFieldTelefono = new JTextField();
		textFieldTelefono.setFont(new Font("Dialog", Font.PLAIN, 16));
		textFieldTelefono.setBounds(100, 340, 250, 35);
		panel.add(textFieldTelefono);
		textFieldTelefono.setColumns(10);

		JLabel lblEmail = new JLabel("Email:");
		lblEmail.setFont(new Font("Dialog", Font.BOLD, 24));
		lblEmail.setBounds(400, 300, 200, 30);
		panel.add(lblEmail);

		textFieldEmail = new JTextField();
		textFieldEmail.setFont(new Font("Dialog", Font.PLAIN, 16));
		textFieldEmail.setBounds(400, 340, 250, 35);
		panel.add(textFieldEmail);
		textFieldEmail.setColumns(10);

		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setBackground(Color.RED);
		btnCancelar.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 20));
		btnCancelar.setForeground(new Color(255, 255, 255));
		btnCancelar.setBounds(967, 517, 170, 50);
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				Clientes clientesWindow = new Clientes();
				clientesWindow.frame.setVisible(true);
			}
		});
		panel.add(btnCancelar);

		JButton btnGuardarCambios = new JButton("Guardar Cambios");
		btnGuardarCambios.setForeground(Color.WHITE);
		btnGuardarCambios.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 18));
		btnGuardarCambios.setBackground(new Color(50, 186, 125));
		btnGuardarCambios.setBounds(967, 592, 170, 50);
		btnGuardarCambios.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (clienteAEditar == null) {
					JOptionPane.showMessageDialog(frame, "No hay cliente seleccionado para editar.", "Error", JOptionPane.ERROR_MESSAGE);
					return;
				}

				String nombre = textFieldNombre.getText().trim();
				String apellido = textFieldApellido.getText().trim();
				String telefono = textFieldTelefono.getText().trim();
				String email = textFieldEmail.getText().trim();

				if (nombre.isEmpty() || apellido.isEmpty() || telefono.isEmpty() || email.isEmpty()) {
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

				if (clienteDAO.actualizarCliente(clienteAEditar)) {
					JOptionPane.showMessageDialog(frame, "Cliente actualizado exitosamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
					frame.dispose();
					Clientes clientesWindow = new Clientes();
					clientesWindow.frame.setVisible(true);
				} else {
					JOptionPane.showMessageDialog(frame, "Error al actualizar el cliente.", "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		panel.add(btnGuardarCambios);
	}

	public void dispose() {
		if (frame != null) {
			frame.dispose();
		}
	}

	public void setVisible(boolean b) {
		if (frame != null) {
			frame.setVisible(b);
		}
	}

	public JFrame getFrame() {
		return frame;
	}
}
