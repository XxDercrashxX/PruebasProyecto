package ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.ArrayList; // Para manejar listas de objetos de UI

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.JScrollPane; // Para el historial y servicios/tarifas si hay muchos
import javax.swing.SwingConstants; // Para alinear texto en botones

import com.formdev.flatlaf.FlatLightLaf;

// DAOs necesarios
import Dao.HabitacionDAO;
import Dao.TipoHabitacionDAO;
import Dao.RentaDAO;
import Dao.ClienteDAO;
import Dao.TarifaDAO;
import Dao.ServicioDAO;
import Dao.ServiciosPorReservaDAO;

// Modelos necesarios
import modelos.Habitaciones;
import modelos.TiposHabitacion;
import modelos.Rentas;
import modelos.Clientes;
import modelos.Tarifa;
import modelos.Servicios;
import modelos.ServiciosPorReserva;

// Clases de UI para navegación
import ui.Menu;
import ui.PanelHabitaciones1;
import ui.TiposHabitacion;
import ui.Rentas;
import ui.Clientes;
import ui.Tarifas;
import ui.DetallesHabitacion2; // Asumimos que esta es la pantalla de edición

public class DetallesHabitacion1 {

	public JFrame frame;
    private int habitacionId; // ID de la habitación que se va a mostrar
    private HabitacionDAO habitacionDAO;
    private TipoHabitacionDAO tipoHabitacionDAO;
    private RentaDAO rentaDAO;
    private ClienteDAO clienteDAO;
    private TarifaDAO tarifaDAO;
    private ServicioDAO servicioDAO;
    private ServiciosPorReservaDAO serviciosPorReservaDAO;

    // Componentes de UI que se actualizarán dinámicamente
    private JLabel menuTitulo; // El título principal "Detalles de la habitación"
    private JLabel lblNumeroHabitacion; // "Cuarto B3"
    private JLabel lblTipoHabitacion;   // "Estándar"
    private JLabel lblNumCamas;         // "2 Camas individuales"
    private JLabel lblOcupanteNombre;   // "Diego Ontiveros"
    private JButton btnEstadoHabitacion; // El botón "OCUPADO"

    // Componentes para historial, tarifas y servicios
    private JPanel panelHistorialContent; // Para el historial dinámico
    private JPanel panelTarifasContent; // Para las tarifas dinámicas
    private JPanel panelServiciosContent; // Para los servicios dinámicos

	/**
	 * Create the application.
	 * @param habitacionId El ID de la habitación para mostrar los detalles.
	 */
	public DetallesHabitacion1(int habitacionId) {
        this.habitacionId = habitacionId;
        // Inicializar DAOs
        this.habitacionDAO = new HabitacionDAO();
        this.tipoHabitacionDAO = new TipoHabitacionDAO();
        this.rentaDAO = new RentaDAO();
        this.clienteDAO = new ClienteDAO();
        this.tarifaDAO = new TarifaDAO();
        this.servicioDAO = new ServicioDAO();
        this.serviciosPorReservaDAO = new ServiciosPorReservaDAO();

		try {
            UIManager.setLookAndFeel(new FlatLightLaf());
            UIManager.put("Button.arc", 90); // Esquinas redondas
        } catch (Exception ex) {
            ex.printStackTrace();
        }
		initialize();
        loadHabitacionData(); // Cargar los datos de la habitación específica
	}

    // Constructor sin ID, para compatibilidad si alguna otra parte del código lo llama así
    // Sin embargo, se recomienda siempre pasar un ID para cargar datos específicos
    public DetallesHabitacion1() {
        this(-1); // Llama al constructor con -1 como ID por defecto (no válido)
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

		menuTitulo = new JLabel("Detalles de la habitación:"); //Texto menú - HECHO VARIABLE
		menuTitulo.setFont(new Font("Jost*", Font.BOLD, 38));
		menuTitulo.setBounds(131, 126, 539, 56);
		panel.add(menuTitulo);

		// Iconos superiores (usuario, información) - Se mantienen
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

		JButton botonVolver = new JButton(""); // Boton para volver atrás
		botonVolver.setForeground(new Color(255, 255, 255));
		botonVolver.setBackground(new Color(255, 255, 255));
		botonVolver.setBorderPainted(false);
		botonVolver.setFocusPainted(false);
		botonVolver.setContentAreaFilled(true);
		botonVolver.setBounds(60, 132, 50, 50);
		ImageIcon f1 = new ImageIcon(getClass().getResource("/images/flecha_izquierda.png"));
		Image f2 = f1.getImage();
		Image f3 = f2.getScaledInstance(36, 36, Image.SCALE_SMOOTH);
		botonVolver.setIcon(new ImageIcon(f3));
		botonVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose(); // Cierra la ventana actual
				PanelHabitaciones1 conexion = new PanelHabitaciones1(); // Vuelve a la pantalla de habitaciones
				conexion.frame.setVisible(true);
			}
		});
		panel.add(botonVolver);

		// Botones de navegación superior - Se mantienen
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
		
		// Barra de búsqueda (se mantiene, pero sin funcionalidad activa en esta vista)
		JTextField textField = new JTextField("BUSCAR");
		textField.setToolTipText("");
		textField.setBounds(770, 140, 290, 40);
		textField.setColumns(10);
		textField.setBackground(new Color(217, 217, 217));
        textField.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 24));
        textField.setForeground(Color.GRAY);
        final String placeholder = "BUSCAR";
        textField.addFocusListener(new FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent e) {
                if (textField.getText().equals(placeholder)) {
                    textField.setText("");
                    textField.setForeground(Color.BLACK);
                }
            }
            public void focusLost(java.awt.event.FocusEvent e) {
                if (textField.getText().isEmpty()) {
                    textField.setText(placeholder);
                    textField.setForeground(Color.GRAY);
                }
            }
        });
		panel.add(textField);

		JButton btnBuscar = new JButton("");
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			    // No hay funcionalidad de búsqueda directa en esta ventana
			}
		});
		btnBuscar.setBounds(720, 140, 40, 40);
		ImageIcon u1 = new ImageIcon(getClass().getResource("/images/busqueda.png"));
		Image u2 = u1.getImage();
		Image u3 = u2.getScaledInstance(36, 36, Image.SCALE_SMOOTH);
		btnBuscar.setIcon(new ImageIcon(u3));
		btnBuscar.setBorderPainted(false);
		btnBuscar.setFocusPainted(false);
		btnBuscar.setContentAreaFilled(true);
		panel.add(btnBuscar);

		JPanel panel_3 = new JPanel(); // Panel de detalles de la habitación
		panel_3.setBackground(new Color(0, 187, 249));
		panel_3.setBounds(131, 193, 300, 440);
		panel.add(panel_3);
		panel_3.setLayout(null);

		JLabel lblRoomImage = new JLabel(""); // Etiqueta para la imagen de la habitación
		lblRoomImage.setBounds(45, 11, 200, 200);
		ImageIcon v1 = new ImageIcon(getClass().getResource("/images/cama_matrimonial2.png"));
		Image v2 = v1.getImage();
		Image v3 = v2.getScaledInstance(200, 200, Image.SCALE_SMOOTH);
		lblRoomImage.setIcon(new ImageIcon(v3));
		panel_3.add(lblRoomImage);

		lblNumeroHabitacion = new JLabel("Cargando..."); // Etiqueta para el número de habitación (antes "Cuarto B3") - HECHO VARIABLE
		lblNumeroHabitacion.setFont(new Font("Jost*", Font.BOLD | Font.ITALIC, 20));
		lblNumeroHabitacion.setBounds(100, 222, 180, 20); // Ajustado el ancho
		panel_3.add(lblNumeroHabitacion);

		JLabel textoTipoHabitacion = new JLabel("Tipo de habitación:");
		textoTipoHabitacion.setFont(new Font("Jost*", Font.BOLD | Font.ITALIC, 16));
		textoTipoHabitacion.setBounds(80, 253, 145, 20);
		panel_3.add(textoTipoHabitacion);

		lblTipoHabitacion = new JLabel("Cargando..."); // Etiqueta para el tipo de habitación (antes "Estándar") - HECHO VARIABLE
		lblTipoHabitacion.setFont(new Font("Jost*", Font.BOLD | Font.ITALIC, 16));
		lblTipoHabitacion.setForeground(Color.WHITE);
		lblTipoHabitacion.setBounds(115, 284, 150, 20); // Ajustado el ancho
		panel_3.add(lblTipoHabitacion);

		JLabel lblCamas = new JLabel("Camas:");
		lblCamas.setFont(new Font("Jost*", Font.BOLD | Font.ITALIC, 16));
		lblCamas.setBounds(120, 315, 60, 20);
		panel_3.add(lblCamas);

		lblNumCamas = new JLabel("Cargando..."); // Etiqueta para el número de camas (antes "2 Camas individuales") - HECHO VARIABLE
		lblNumCamas.setFont(new Font("Jost*", Font.BOLD | Font.ITALIC, 16));
		lblNumCamas.setForeground(Color.WHITE);
		lblNumCamas.setBounds(68, 340, 200, 20); // Ajustado el ancho
		panel_3.add(lblNumCamas);

		JLabel lblOcupante = new JLabel("Ocupante:");
		lblOcupante.setFont(new Font("Jost*", Font.BOLD | Font.ITALIC, 16));
		lblOcupante.setBounds(110, 365, 80, 20);
		panel_3.add(lblOcupante);

		lblOcupanteNombre = new JLabel("Cargando..."); // Etiqueta para el nombre del ocupante (antes "Diego Ontiveros") - HECHO VARIABLE
		lblOcupanteNombre.setFont(new Font("Jost*", Font.BOLD | Font.ITALIC, 16));
		lblOcupanteNombre.setForeground(Color.WHITE);
		lblOcupanteNombre.setBounds(90, 396, 150, 20); // Ajustado el ancho
		panel_3.add(lblOcupanteNombre);

        // Panel de Historial de rentas
		JPanel panel_4 = new JPanel();
		panel_4.setBackground(new Color(0, 187, 249));
		panel_4.setBounds(441, 193, 340, 140);
		panel.add(panel_4);
		panel_4.setLayout(null);

		JLabel lblHistorialTitulo = new JLabel("Historial de rentas:");
		lblHistorialTitulo.setFont(new Font("Jost*", Font.BOLD | Font.ITALIC, 32));
		lblHistorialTitulo.setBounds(10, 11, 320, 30);
		panel_4.add(lblHistorialTitulo);

        // Se usa un JScrollPane para el historial si hay muchas entradas
        panelHistorialContent = new JPanel();
        panelHistorialContent.setLayout(new javax.swing.BoxLayout(panelHistorialContent, javax.swing.BoxLayout.Y_AXIS));
        panelHistorialContent.setBackground(new Color(0, 187, 249));

        JScrollPane scrollHistorial = new JScrollPane(panelHistorialContent);
        scrollHistorial.setBounds(10, 52, 320, 78); // Ajustar tamaño para que quepa en el panel
        scrollHistorial.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollHistorial.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollHistorial.setBorder(BorderFactory.createEmptyBorder()); // Quitar borde
        panel_4.add(scrollHistorial);


        // Panel de Tarifas
		JPanel panel_5 = new JPanel();
		panel_5.setBackground(new Color(0, 187, 249));
		panel_5.setBounds(441, 344, 340, 140);
		panel.add(panel_5);
		panel_5.setLayout(null);

		JLabel lblTarifasTitulo = new JLabel("Tarifas:");
		lblTarifasTitulo.setFont(new Font("Jost*", Font.BOLD | Font.ITALIC, 32));
		lblTarifasTitulo.setBounds(10, 11, 320, 30);
		panel_5.add(lblTarifasTitulo);

        // Se usa un JScrollPane para tarifas
        panelTarifasContent = new JPanel();
        panelTarifasContent.setLayout(new javax.swing.BoxLayout(panelTarifasContent, javax.swing.BoxLayout.Y_AXIS));
        panelTarifasContent.setBackground(new Color(0, 187, 249));

        JScrollPane scrollTarifas = new JScrollPane(panelTarifasContent);
        scrollTarifas.setBounds(10, 52, 320, 78); // Ajustar tamaño
        scrollTarifas.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollTarifas.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollTarifas.setBorder(BorderFactory.createEmptyBorder()); // Quitar borde
        panel_5.add(scrollTarifas);


        // Panel de Servicios extras
		JPanel panel_6 = new JPanel();
		panel_6.setBackground(new Color(0, 187, 249));
		panel_6.setBounds(441, 495, 340, 138);
		panel.add(panel_6);
		panel_6.setLayout(null);

		JLabel lblServiciosTitulo = new JLabel("Servicios extras:");
		lblServiciosTitulo.setFont(new Font("Jost*", Font.BOLD | Font.ITALIC, 32));
		lblServiciosTitulo.setBounds(10, 11, 320, 30);
		panel_6.add(lblServiciosTitulo);

        // Se usa un JScrollPane para servicios
        panelServiciosContent = new JPanel();
        panelServiciosContent.setLayout(new javax.swing.BoxLayout(panelServiciosContent, javax.swing.BoxLayout.Y_AXIS));
        panelServiciosContent.setBackground(new Color(0, 187, 249));

        JScrollPane scrollServicios = new JScrollPane(panelServiciosContent);
        scrollServicios.setBounds(10, 52, 320, 76); // Ajustar tamaño
        scrollServicios.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollServicios.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollServicios.setBorder(BorderFactory.createEmptyBorder()); // Quitar borde
        panel_6.add(scrollServicios);


		btnEstadoHabitacion = new JButton("Cargando..."); // Botón de estado de la habitación - HECHO VARIABLE
		btnEstadoHabitacion.setBackground(new Color(239, 35, 60)); // Color por defecto (Rojo)
		btnEstadoHabitacion.setForeground(Color.WHITE);
		btnEstadoHabitacion.setFont(new Font("Inter", Font.BOLD | Font.ITALIC, 24));
		btnEstadoHabitacion.setBorder(BorderFactory.createLineBorder(Color.BLACK,0));
		btnEstadoHabitacion.setBounds(845, 200, 270, 60);
		panel.add(btnEstadoHabitacion);

		JButton btnDescargarHistorial = new JButton("Descargar historial");
		btnDescargarHistorial.setBackground(new Color(255, 214, 10));
		btnDescargarHistorial.setFont(new Font("Inter", Font.BOLD | Font.ITALIC, 24));
		btnDescargarHistorial.setBorder(BorderFactory.createLineBorder(Color.BLACK,0));
		btnDescargarHistorial.setBounds(845, 495, 270, 60);
		panel.add(btnDescargarHistorial);

		JButton btnEditarHabitacion = new JButton("Editar habitación");
		btnEditarHabitacion.setBackground(new Color(50, 186, 124));
		btnEditarHabitacion.setFont(new Font("Inter", Font.BOLD | Font.ITALIC, 24));
		btnEditarHabitacion.setBorder(BorderFactory.createLineBorder(Color.BLACK,0));
		btnEditarHabitacion.setBounds(845, 566, 270, 60);
		btnEditarHabitacion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose(); // Cierra la ventana actual
				DetallesHabitacion2 conexion = new DetallesHabitacion2(habitacionId); // Pasa el ID de la habitación
				conexion.frame.setVisible(true);
			}
		});
		panel.add(btnEditarHabitacion);
	}

    /**
     * Carga los datos de la habitación y la información relacionada
     * desde la base de datos y actualiza la UI.
     */
    private void loadHabitacionData() {
        if (habitacionId == -1) {
            menuTitulo.setText("Error: ID de Habitación inválido.");
            // Deshabilitar botones o mostrar mensaje de error en la UI
            return;
        }

        Habitaciones habitacion = habitacionDAO.getHabitacionById(habitacionId);

        if (habitacion != null) {
            menuTitulo.setText("Detalles de la habitación: " + habitacion.getNumero());

            // 1. Detalles de la Habitación
            lblNumeroHabitacion.setText(habitacion.getNumero());
            lblNumCamas.setText(habitacion.getNumeroCamas() + " Camas");

            TiposHabitacion tipoHabitacion = tipoHabitacionDAO.getTipoHabitacionById(habitacion.getIdTipoHabitacion());
            lblTipoHabitacion.setText(tipoHabitacion != null ? tipoHabitacion.getNombreTipo() : "Desconocido");

            // Actualizar el botón de estado de la habitación
            btnEstadoHabitacion.setText(habitacion.getEstado().toUpperCase());
            switch (habitacion.getEstado().toLowerCase()) {
                case "disponible":
                    btnEstadoHabitacion.setBackground(new Color(50, 186, 124)); // Verde
                    break;
                case "ocupada":
                    btnEstadoHabitacion.setBackground(new Color(239, 35, 60)); // Rojo
                    break;
                case "mantenimiento":
                    btnEstadoHabitacion.setBackground(Color.LIGHT_GRAY); // Gris
                    break;
                default:
                    btnEstadoHabitacion.setBackground(new Color(0, 187, 249)); // Azul por defecto
                    break;
            }

            // 2. Ocupante actual (si la habitación está ocupada por una renta activa)
            // Se asume que una habitación solo tiene una renta activa a la vez.
            // Para simplificar, obtenemos todas las rentas y buscamos una activa para esta habitación.
            String ocupanteNombre = "N/A";
            Rentas activeRenta = null;
            List<Rentas> todasLasRentas = rentaDAO.getAllRentas(); // O buscar por idHabitacion si el DAO lo permite
            for (Rentas r : todasLasRentas) {
                if (r.getIdHabitacion() == habitacionId && "Activa".equalsIgnoreCase(r.getEstado())) {
                    activeRenta = r;
                    break;
                }
            }

            if (activeRenta != null) {
                Clientes cliente = clienteDAO.obtenerClientePorId(activeRenta.getIdCliente());
                if (cliente != null) {
                    ocupanteNombre = cliente.getNombre() + " " + cliente.getApellido();
                }
            }
            lblOcupanteNombre.setText(ocupanteNombre);


            // 3. Historial de Rentas
            panelHistorialContent.removeAll(); // Limpiar antes de añadir
            List<Rentas> historialRentas = rentaDAO.searchRentasByHabitacion(habitacionId); // Asumiendo un método de búsqueda por habitacionId
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd 'de' MMMM yyyy");
            if (historialRentas != null && !historialRentas.isEmpty()) {
                // Mostrar solo los últimos 3, por ejemplo, para que quepa en el diseño
                for (int i = 0; i < Math.min(historialRentas.size(), 3); i++) {
                    Rentas r = historialRentas.get(i);
                    String fechaEntrada = r.getFechaCheckIn().format(formatter);
                    String fechaSalida = (r.getFechaCheckOut() != null) ? r.getFechaCheckOut().format(formatter) : "Actualidad";
                    JLabel lblHistorial = new JLabel("rentado del " + fechaEntrada + " al " + fechaSalida);
                    lblHistorial.setFont(new Font("Jost*", Font.BOLD | Font.ITALIC, 16));
                    lblHistorial.setForeground(Color.WHITE);
                    panelHistorialContent.add(lblHistorial);
                }
            } else {
                JLabel noHistorial = new JLabel("No hay historial de rentas.");
                noHistorial.setFont(new Font("Jost*", Font.ITALIC, 14));
                noHistorial.setForeground(Color.LIGHT_GRAY);
                panelHistorialContent.add(noHistorial);
            }
            panelHistorialContent.revalidate();
            panelHistorialContent.repaint();


            // 4. Tarifas aplicables
            panelTarifasContent.removeAll(); // Limpiar
            List<Tarifa> tarifasAplicables = tarifaDAO.getTarifasByTipoHabitacion(habitacion.getIdTipoHabitacion()); // Asumiendo este método
            if (tarifasAplicables != null && !tarifasAplicables.isEmpty()) {
                for (Tarifa t : tarifasAplicables) {
                    JLabel lblTarifa = new JLabel("$" + String.format("%.2f", t.getPrecioBase()) + " pesos por noche (" + t.getNombreTarifa() + ")");
                    lblTarifa.setFont(new Font("Jost*", Font.BOLD | Font.ITALIC, 16));
                    lblTarifa.setForeground(Color.WHITE);
                    panelTarifasContent.add(lblTarifa);
                    if (t.getCondiciones().isEmpty()){
                         JLabel lblCondicion = new JLabel("Sin condiciones");
                         lblCondicion.setFont(new Font("Jost*", Font.BOLD | Font.ITALIC, 14));
                         lblCondicion.setForeground(Color.LIGHT_GRAY);
                         panelTarifasContent.add(lblCondicion);
                    } else {
                         JLabel lblCondicion = new JLabel(t.getCondiciones());
                         lblCondicion.setFont(new Font("Jost*", Font.BOLD | Font.ITALIC, 14));
                         lblCondicion.setForeground(Color.LIGHT_GRAY);
                         panelTarifasContent.add(lblCondicion);
                    }
                }
            } else {
                JLabel noTarifas = new JLabel("No hay tarifas definidas.");
                noTarifas.setFont(new Font("Jost*", Font.ITALIC, 14));
                noTarifas.setForeground(Color.LIGHT_GRAY);
                panelTarifasContent.add(noTarifas);
            }
            panelTarifasContent.revalidate();
            panelTarifasContent.repaint();


            // 5. Servicios extras (asociados a la renta activa si existe)
            panelServiciosContent.removeAll(); // Limpiar
            if (activeRenta != null && activeRenta.getIdReserva() != null) {
                List<ServiciosPorReserva> serviciosAsociados = serviciosPorReservaDAO.getServiciosByReservaId(activeRenta.getIdReserva());
                if (serviciosAsociados != null && !serviciosAsociados.isEmpty()) {
                    for (ServiciosPorReserva spr : serviciosAsociados) {
                        Servicios servicio = servicioDAO.getServicioById(spr.getIdServicio());
                        if (servicio != null) {
                            JLabel lblServicio = new JLabel(servicio.getNombreServicio() + ": " + String.format("%.2f", servicio.getPrecio()) + " (x" + spr.getCantidad() + ")");
                            lblServicio.setFont(new Font("Jost*", Font.BOLD | Font.ITALIC, 16));
                            lblServicio.setForeground(Color.WHITE);
                            panelServiciosContent.add(lblServicio);
                        }
                    }
                } else {
                    JLabel noServicios = new JLabel("No hay servicios extras activos.");
                    noServicios.setFont(new Font("Jost*", Font.ITALIC, 14));
                    noServicios.setForeground(Color.LIGHT_GRAY);
                    panelServiciosContent.add(noServicios);
                }
            } else {
                JLabel noServicios = new JLabel("No hay renta activa con servicios.");
                noServicios.setFont(new Font("Jost*", Font.ITALIC, 14));
                noServicios.setForeground(Color.LIGHT_GRAY);
                panelServiciosContent.add(noServicios);
            }
            panelServiciosContent.revalidate();
            panelServiciosContent.repaint();

        } else {
            // Manejar caso donde la habitación no se encuentra
            menuTitulo.setText("Habitación #" + habitacionId + " no encontrada.");
            lblNumeroHabitacion.setText("N/A");
            lblTipoHabitacion.setText("N/A");
            lblNumCamas.setText("N/A");
            lblOcupanteNombre.setText("N/A");
            btnEstadoHabitacion.setText("ERROR");
            btnEstadoHabitacion.setBackground(Color.LIGHT_GRAY);
            panelHistorialContent.removeAll();
            panelHistorialContent.add(new JLabel("Error al cargar datos."));
            panelTarifasContent.removeAll();
            panelTarifasContent.add(new JLabel("Error al cargar datos."));
            panelServiciosContent.removeAll();
            panelServiciosContent.add(new JLabel("Error al cargar datos."));
            panelHistorialContent.revalidate(); panelHistorialContent.repaint();
            panelTarifasContent.revalidate(); panelTarifasContent.repaint();
            panelServiciosContent.revalidate(); panelServiciosContent.repaint();
        }
    }

    public void setVisible(boolean b) {
        frame.setVisible(b);
    }
}
