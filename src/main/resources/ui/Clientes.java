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
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.JScrollPane;
import java.awt.GridLayout;

import Dao.ClienteDAO;
import modelos.Cliente; // Importación corregida: modelos.Cliente (singular)
import ui.Menu;
import ui.TiposHabitacion;
import ui.Rentas;
import ui.PanelHabitaciones1;
import ui.Tarifas;
import ui.ClientesEliminar;
import ui.DatosUsuario;
import ui.CrearNuevoCliente;

public class Clientes {

	public JFrame frame;
	private JTextField searchField;
    private ClienteDAO clienteDAO;
    private JPanel clientsDisplayPanel;

	public Clientes() {
        try {
            UIManager.setLookAndFeel(new com.formdev.flatlaf.FlatLightLaf());
            UIManager.put("Button.arc", 90);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        clienteDAO = new ClienteDAO();
		initialize();
        loadClients(""); // Cargar clientes al iniciar la ventana
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
		ImageIcon icon20065= new ImageIcon(getClass().getResource("/images/logo.png"));
        Image imagen20065= icon20065.getImage().getScaledInstance(170,95, Image.SCALE_SMOOTH);
        logo.setIcon(new ImageIcon(imagen20065));
		panel_1.add(logo);

		JLabel Titulo = new JLabel("Panel de clientes");
		Titulo.setForeground(new Color(255, 255, 255));
		Titulo.setFont(new Font("Jost* Medium", Font.PLAIN, 35));
		Titulo.setBounds(180, 11, 410, 73);
		panel_1.add(Titulo);

		JLabel menuTitulo = new JLabel("Clientes:");
		menuTitulo.setFont(new Font("Jost*", Font.BOLD, 38));
		menuTitulo.setBounds(131, 126, 245, 56);
		panel.add(menuTitulo);

		JButton botonSuperior1 = new JButton(""); // Asumiendo que es para el usuario actual
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

		JButton botonSuperior2 = new JButton(""); // Asumiendo que es para información
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
		botonVolver.setBounds(60, 132, 50, 50);
		botonVolver.setForeground(new Color(255, 255, 255));
		botonVolver.setBackground(new Color(255, 255, 255));
		botonVolver.setBorderPainted(false);
		botonVolver.setFocusPainted(false);
		botonVolver.setContentAreaFilled(true);
		botonVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				Menu conexion = new Menu();
				conexion.frame.setVisible(true);
			}
		});
		ImageIcon icon2006= new ImageIcon(getClass().getResource("/images/flecha_izquierda.png"));
        Image imagen2006= icon2006.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
        botonVolver.setIcon(new ImageIcon(imagen2006));
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

		JButton btnBuscar = new JButton("");
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
                String searchTerm = searchField.getText();
                loadClients(searchTerm.equals("BUSCAR") ? "" : searchTerm);
			}
		});
		btnBuscar.setBounds(720, 140, 40, 40);
		ImageIcon icon134 = new ImageIcon(getClass().getResource("/images/busqueda.png"));
        Image imagen134 = icon134.getImage().getScaledInstance(36, 36, Image.SCALE_SMOOTH);
        btnBuscar.setIcon(new ImageIcon(imagen134));
		btnBuscar.setBorderPainted(false);
		btnBuscar.setFocusPainted(false);
		btnBuscar.setContentAreaFilled(true);
		panel.add(btnBuscar);

		searchField = new JTextField("BUSCAR");
		searchField.setToolTipText("");
		searchField.setBounds(770, 140, 290, 40);
		searchField.setColumns(10);
		searchField.setBackground(new Color(217, 217, 217));
        searchField.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 24));
        searchField.setForeground(Color.GRAY);
        final String placeholder = "BUSCAR";
        searchField.addFocusListener(new FocusAdapter() {
            public void focusGained(FocusEvent e) {
                if (searchField.getText().equals(placeholder)) {
                    searchField.setText("");
                    searchField.setForeground(Color.BLACK);
                }
            }
            public void focusLost(FocusEvent e) {
                if (searchField.getText().isEmpty()) {
                    searchField.setText(placeholder);
                    searchField.setForeground(Color.GRAY);
                }
            }
        });
		panel.add(searchField);

		JButton btnEliminarCliente = new JButton("Eliminar Cliente");
		btnEliminarCliente.setBackground(new Color(239, 35, 60));
		btnEliminarCliente.setFont(new Font("Inter", Font.BOLD | Font.ITALIC, 24));
		btnEliminarCliente.setBorder(BorderFactory.createLineBorder(Color.BLACK,0));
		btnEliminarCliente.setForeground(Color.WHITE);
		btnEliminarCliente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				ClientesEliminar conexion = new ClientesEliminar();
				conexion.frame.setVisible(true);
			}
		});
		btnEliminarCliente.setBounds(131, 193, 245, 40);
		panel.add(btnEliminarCliente);

		JButton btnCrearClienteNuevo = new JButton("Crear cliente nuevo");
		btnCrearClienteNuevo.setForeground(Color.DARK_GRAY);
		btnCrearClienteNuevo.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 24));
		btnCrearClienteNuevo.setBorder(BorderFactory.createLineBorder(Color.BLACK,0));
		btnCrearClienteNuevo.setBackground(Color.YELLOW);
		btnCrearClienteNuevo.setBounds(388, 193, 245, 40);
		btnCrearClienteNuevo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				CrearNuevoCliente conexion = new CrearNuevoCliente();
				conexion.frame.setVisible(true);
			}
		});
		panel.add(btnCrearClienteNuevo);

        clientsDisplayPanel = new JPanel();
        clientsDisplayPanel.setLayout(new GridLayout(0, 5, 20, 20));
        clientsDisplayPanel.setBackground(new Color(255, 255, 255));

        JScrollPane scrollPane = new JScrollPane(clientsDisplayPanel);
        scrollPane.setBounds(131, 257, 950, 360);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        panel.add(scrollPane);
	}

    private void loadClients(String searchTerm) {
        clientsDisplayPanel.removeAll();

        List<Cliente> clientes; // Tipo de lista Cliente (singular)
        if (searchTerm == null || searchTerm.trim().isEmpty()) {
            clientes = clienteDAO.getAllClientes();
        } else {
            clientes = clienteDAO.searchClientes(searchTerm);
        }

        ImageIcon userIcon = new ImageIcon(getClass().getResource("/images/usuario.png"));
        Image userImage = userIcon.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
        ImageIcon scaledUserIcon = new ImageIcon(userImage);

        if (clientes != null && !clientes.isEmpty()) {
            for (Cliente cliente : clientes) { // Iterar sobre Cliente (singular)
                JButton clientButton = new JButton("<html>" + cliente.getNombre() + "<br>" + cliente.getApellido() + "</html>");
                clientButton.setFont(new Font("Jost*", Font.BOLD | Font.ITALIC, 14));
                clientButton.setBackground(new Color(0, 175, 185));
                clientButton.setVerticalTextPosition(SwingConstants.BOTTOM);
                clientButton.setHorizontalTextPosition(SwingConstants.CENTER);
                clientButton.setHorizontalAlignment(SwingConstants.CENTER);
                clientButton.setVerticalAlignment(SwingConstants.CENTER);
                clientButton.setIconTextGap(1);
                clientButton.setBorder(BorderFactory.createLineBorder(Color.BLACK,0));
                clientButton.setIcon(scaledUserIcon);

                final int clienteId = cliente.getIdCliente();
                clientButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        frame.dispose();
                        DatosUsuario datosUsuarioWindow = new DatosUsuario(clienteId);
                        datosUsuarioWindow.frame.setVisible(true);
                    }
                });
                clientsDisplayPanel.add(clientButton);
            }
        } else {
            JLabel noClientsLabel = new JLabel("No se encontraron clientes.");
            noClientsLabel.setFont(new Font("Jost*", Font.BOLD, 20));
            noClientsLabel.setForeground(Color.GRAY);
            noClientsLabel.setHorizontalAlignment(SwingConstants.CENTER);
            clientsDisplayPanel.add(noClientsLabel);
        }

        clientsDisplayPanel.revalidate();
        clientsDisplayPanel.repaint();
    }

    public void setVisible(boolean b) {
        if (frame != null) {
            frame.setVisible(b);
        }
    }
}
