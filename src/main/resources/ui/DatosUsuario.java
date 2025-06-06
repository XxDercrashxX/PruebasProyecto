package ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JScrollPane; // Importar JScrollPane

import Dao.ClienteDAO;
import Dao.RentaDAO; // Asegúrate de que RentaDAO use modelos.Rentas y tenga getRentasByClienteId
import modelos.Cliente; // Importación corregida: modelos.Cliente (singular)
import modelos.Rentas; // Asegúrate de que este modelo exista y sea correcto

import ui.Clientes;
import ui.Menu;
import ui.PanelHabitaciones1;
import ui.Rentas;
import ui.Tarifas;
import ui.TiposHabitacion;
import ui.EditarDatosCliente;

public class DatosUsuario {

	public JFrame frame;
    private int clienteId;
    private ClienteDAO clienteDAO;
    private RentaDAO rentaDAO;

    private JLabel lblUsuarioId;
    private JLabel lblNombreCompleto;
    private JLabel lblCorreo;
    private JLabel lblTelefono;
    private JLabel lblDireccion; // Etiqueta para la dirección

    private JPanel historialRentasPanel;

	public DatosUsuario(int clienteId) {
        this.clienteId = clienteId;
        this.clienteDAO = new ClienteDAO();
        this.rentaDAO = new RentaDAO();
		initialize();
        loadClienteData();
	}

    public DatosUsuario() {
        this(-1); // Constructor sin argumentos, por si se llama sin ID
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

		JLabel Titulo = new JLabel("Clientes");
		Titulo.setForeground(new Color(255, 255, 255));
		Titulo.setFont(new Font("Jost* Medium", Font.PLAIN, 35));
		Titulo.setBounds(180, 11, 410, 73);
		panel_1.add(Titulo);

		JLabel menuTitulo = new JLabel("Detalles del cliente");
		menuTitulo.setFont(new Font("Jost*", Font.BOLD, 38));
		menuTitulo.setBounds(131, 126, 460, 56);
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

		lblUsuarioId = new JLabel("ID del cliente: N/A"); // Se inicializa con N/A
		lblUsuarioId.setFont(new Font("Jost*", Font.BOLD | Font.ITALIC, 20));
		lblUsuarioId.setBounds(90, 222, 200, 20); // Ajustado para más espacio
		panel_3.add(lblUsuarioId);

		JLabel textoNombreLabel = new JLabel("Nombre:");
		textoNombreLabel.setFont(new Font("Jost*", Font.BOLD | Font.ITALIC, 16));
		textoNombreLabel.setBounds(120, 272, 145, 20);
		panel_3.add(textoNombreLabel);

        lblNombreCompleto = new JLabel("N/A"); // Campo para el nombre completo
        lblNombreCompleto.setFont(new Font("Jost*", Font.PLAIN, 16));
        lblNombreCompleto.setBounds(120, 292, 160, 20); // Ajustado para que quepa debajo de la etiqueta
        panel_3.add(lblNombreCompleto);


		JLabel lblCorreoLabel = new JLabel("Correo:");
		lblCorreoLabel.setFont(new Font("Jost*", Font.BOLD | Font.ITALIC, 16));
		lblCorreoLabel.setBounds(120, 326, 60, 20);
		panel_3.add(lblCorreoLabel);

        lblCorreo = new JLabel("N/A"); // Campo para el correo
        lblCorreo.setFont(new Font("Jost*", Font.PLAIN, 16));
        lblCorreo.setBounds(120, 346, 160, 20);
        panel_3.add(lblCorreo);

		JLabel lblTelefonoPanel3 = new JLabel("Telefono:");
		lblTelefonoPanel3.setFont(new Font("Jost*", Font.BOLD | Font.ITALIC, 16));
		lblTelefonoPanel3.setBounds(120, 368, 80, 20);
		panel_3.add(lblTelefonoPanel3);

        lblTelefono = new JLabel("N/A"); // Campo para el teléfono
        lblTelefono.setFont(new Font("Jost*", Font.PLAIN, 16));
        lblTelefono.setBounds(120, 388, 160, 20);
        panel_3.add(lblTelefono);

        JLabel lblDireccionLabel = new JLabel("Dirección:"); // Etiqueta para la dirección
        lblDireccionLabel.setFont(new Font("Jost*", Font.BOLD | Font.ITALIC, 16));
        lblDireccionLabel.setBounds(120, 410, 80, 20);
        panel_3.add(lblDireccionLabel);

        lblDireccion = new JLabel("N/A"); // Campo para la dirección
        lblDireccion.setFont(new Font("Jost*", Font.PLAIN, 16));
        lblDireccion.setBounds(120, 430, 160, 20);
        panel_3.add(lblDireccion);


		JButton btnEditarCliente = new JButton("Editar cliente");
		btnEditarCliente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				EditarDatosCliente conexion = new EditarDatosCliente(clienteId);
				conexion.frame.setVisible(true);
			}
		});
		btnEditarCliente.setBackground(new Color(50, 186, 124));
		btnEditarCliente.setFont(new Font("Inter", Font.BOLD | Font.ITALIC, 24));
		btnEditarCliente.setBorder(BorderFactory.createLineBorder(Color.BLACK,0));
		btnEditarCliente.setBounds(856, 573, 270, 60);
		panel.add(btnEditarCliente);
		
		JLabel HistorialRentas = new JLabel("Historial de rentas:");
		HistorialRentas.setFont(new Font("Jost*", Font.BOLD, 32));
		HistorialRentas.setBounds(441, 193, 370, 40);
		panel.add(HistorialRentas);

        historialRentasPanel = new JPanel();
        historialRentasPanel.setLayout(null); // Usamos layout null para posicionar manualmente
        historialRentasPanel.setBackground(new Color(255, 255, 255));
        
        JScrollPane scrollPaneHistorial = new JScrollPane(historialRentasPanel);
        scrollPaneHistorial.setBounds(441, 244, 400, 300);
        scrollPaneHistorial.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPaneHistorial.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        panel.add(scrollPaneHistorial);
		
		JButton btnDescargarHistorial = new JButton("Descargar historial");
		btnDescargarHistorial.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 24));
		btnDescargarHistorial.setBorder(BorderFactory.createLineBorder(Color.BLACK,0));
		btnDescargarHistorial.setBackground(new Color(255, 214, 10));
		btnDescargarHistorial.setBounds(856, 474, 270, 60);
		btnDescargarHistorial.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(frame, "Funcionalidad 'Descargar historial' no implementada.", "Información", JOptionPane.INFORMATION_MESSAGE);
            }
        });
		panel.add(btnDescargarHistorial);
		
		JLabel lblHabitacionesRentadas = new JLabel("Habitaciones rentadas:"); // Esta etiqueta parece redundante o fuera de lugar si ya tienes "Historial de rentas"
		lblHabitacionesRentadas.setFont(new Font("Jost*", Font.BOLD, 32));
		lblHabitacionesRentadas.setBounds(441, 397, 370, 40); // Puede superponerse con historialRentasPanel
		// panel.add(lblHabitacionesRentadas); // Comentado para evitar superposición por ahora
	}

    private void loadClienteData() {
        if (clienteId == -1) {
            JOptionPane.showMessageDialog(frame, "No se proporcionó un ID de cliente válido.", "Error", JOptionPane.ERROR_MESSAGE);
            menuTitulo.setText("Detalles del cliente: Error");
            return;
        }

        Cliente cliente = clienteDAO.obtenerClientePorId(clienteId); // Usa Cliente (singular)

        if (cliente != null) {
            menuTitulo.setText("Detalles del cliente: " + cliente.getNombre() + " " + cliente.getApellido()); // Muestra nombre en el título
            lblUsuarioId.setText("ID del cliente: #" + cliente.getIdCliente());
            lblNombreCompleto.setText(cliente.getNombre() + " " + cliente.getApellido());
            lblCorreo.setText(cliente.getEmail());
            lblTelefono.setText(cliente.getTelefono());
            lblDireccion.setText(cliente.getDireccion()); // Muestra la dirección
            
            // Asumiendo que existe un RentaDAO y un modelo Rentas para el historial
            // Si el RentaDAO usa Cliente (singular) y Rentas tiene getFechaCheckIn() y getIdRenta()
            loadHistorialRentas(cliente.getIdCliente()); 
        } else {
            JOptionPane.showMessageDialog(frame, "Cliente no encontrado con ID: " + clienteId, "Error", JOptionPane.ERROR_MESSAGE);
            menuTitulo.setText("Detalles del cliente: No encontrado");
            lblUsuarioId.setText("ID del cliente: N/A");
            lblNombreCompleto.setText("N/A");
            lblCorreo.setText("N/A");
            lblTelefono.setText("N/A");
            lblDireccion.setText("N/A");
        }
    }

    private void loadHistorialRentas(int idCliente) {
        historialRentasPanel.removeAll();
        historialRentasPanel.setPreferredSize(null);

        // Suponemos que rentaDAO.getRentasByClienteId devuelve List<modelos.Rentas>
        List<Rentas> historial = rentaDAO.getRentasByClienteId(idCliente);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd 'de' MMMM yyyy"); // Formato de fecha
        
        if (historial != null && !historial.isEmpty()) {
            int yOffset = 0;
            for (Rentas renta : historial) {
                // Asegúrate de que Rentas tenga getIdRenta() y getFechaCheckIn()
                JLabel lblRentaInfo = new JLabel("Renta #" + renta.getIdRenta() + ": desde " + renta.getFechaCheckIn().toLocalDate().format(formatter));
                lblRentaInfo.setFont(new Font("Jost*", Font.PLAIN, 14));
                lblRentaInfo.setBounds(5, yOffset, 390, 20); // Ajuste de posición y tamaño
                historialRentasPanel.add(lblRentaInfo);
                yOffset += 25; // Espacio entre elementos
            }
            // Ajusta el tamaño preferido del panel para el scroll
            historialRentasPanel.setPreferredSize(new java.awt.Dimension(400, yOffset));
        } else {
            JLabel noHistorialLabel = new JLabel("No hay historial de rentas para este cliente.");
            noHistorialLabel.setFont(new Font("Jost*", Font.PLAIN, 14));
            noHistorialLabel.setForeground(Color.GRAY);
            noHistorialLabel.setBounds(5, 0, 390, 20);
            historialRentasPanel.add(noHistorialLabel);
            historialRentasPanel.setPreferredSize(new java.awt.Dimension(400, 20)); // Tamaño mínimo si no hay historial
        }

        historialRentasPanel.revalidate();
        historialRentasPanel.repaint();
    }

    public void setVisible(boolean b) {
        if (frame != null) {
            frame.setVisible(b);
        }
    }
}
