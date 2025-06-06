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
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;
import java.util.List;

import javax.swing.AbstractButton;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.FlowLayout;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

import com.formdev.flatlaf.FlatLightLaf;

import Dao.RentaDAO;
import Dao.ReservaDAO; // Aunque no se usa directamente en este código, se mantiene si tu lógica lo necesita
import Dao.HabitacionDAO;
import Dao.ClienteDAO;
import Dao.ServicioDAO;
import Dao.ServiciosPorReservaDAO;
import Dao.TipoHabitacionDAO; // Importado para obtener el nombre del tipo de habitación

import modelos.Rentas;
import modelos.Reservas; // Aunque no se usa directamente, se mantiene si tu lógica lo necesita
import modelos.Habitaciones;
import modelos.Clientes;
import modelos.Servicios;
import modelos.ServiciosPorReserva;
import modelos.TiposHabitacion; // Importado para obtener el nombre del tipo de habitación

import ui.Menu;
import ui.Rentas;
import ui.TiposHabitacion;
import ui.Clientes;
import ui.PanelHabitaciones1;
import ui.Tarifas;
import ui.DatosRenta; // Para volver a la vista de detalles de renta

public class RentasModificarReserva {

	public JFrame frame;
    private int rentaId; // ID de la renta a modificar (-1 si es nueva)
    private int habitacionIdParaCreacion; // ID de la habitación para una nueva renta
    private boolean isCreationMode;

    private Rentas currentRenta; // Renta actual para modificación o nueva renta para creación
    private RentaDAO rentaDAO;
    private ReservaDAO reservaDAO;
    private HabitacionDAO habitacionDAO;
    private ClienteDAO clienteDAO;
    private ServicioDAO servicioDAO;
    private ServiciosPorReservaDAO serviciosPorReservaDAO;
    private TipoHabitacionDAO tipoHabitacionDAO; // Añadido para buscar tipo de habitación

    private JLabel menuTitulo;
    private JLabel lblDiasHospedaje;
    private JLabel lblHabitacionesUsadas;
    private JComboBox<String> comboBoxServicios;
    private JButton btnGuardarCambios; // El botón principal de acción

    // Nuevos campos para creación/modificación
    private JTextField textFieldClienteId; // Repurposed from searchField
    private JTextField textFieldFechaCheckIn;
    private JTextField textFieldFechaCheckOut;
    private JTextField textFieldNumeroHuespedes;


	// Constructor para modificar una renta existente
	public RentasModificarReserva(int rentaId) {
        this.rentaId = rentaId;
        this.habitacionIdParaCreacion = -1; // No aplica para modificación
        this.isCreationMode = (rentaId == -1); // Si rentaId es -1, es modo creación (solo si no hay habitacionId tambien)
        initializeDAOs();
		initialize();
        loadRentaData(); // Cargar los datos de la renta específica para modificación
        populateServiciosComboBox();
	}

    // Constructor para crear una nueva renta para una habitación específica
    public RentasModificarReserva(int rentaId, int habitacionId) {
        this.rentaId = rentaId; // Debería ser -1 para creación
        this.habitacionIdParaCreacion = habitacionId;
        this.isCreationMode = (rentaId == -1 && habitacionId != -1);
        initializeDAOs();
        initialize();
        loadRentaData(); // Cargar datos iniciales para la creación (ej. de la habitación)
        populateServiciosComboBox();
    }

    // Constructor por defecto, por si acaso, aunque se debería usar uno con ID
    public RentasModificarReserva() {
        this(-1, -1); // Por defecto, sin renta ni habitación cargada
    }

    private void initializeDAOs() {
        try {
            UIManager.setLookAndFeel(new FlatLightLaf());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        this.rentaDAO = new RentaDAO();
        this.reservaDAO = new ReservaDAO(); // Se mantiene por si es necesario para el modelo de datos
        this.habitacionDAO = new HabitacionDAO();
        this.clienteDAO = new ClienteDAO();
        this.servicioDAO = new ServicioDAO();
        this.serviciosPorReservaDAO = new ServiciosPorReservaDAO();
        this.tipoHabitacionDAO = new TipoHabitacionDAO(); // Inicializar
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
		
		JLabel logo = new JLabel("");
		logo.setBounds(0, 0, 170, 95);
		ImageIcon icon1 = new ImageIcon(getClass().getResource("/images/logo.png"));
        Image imagen1 = icon1.getImage().getScaledInstance(170, 95, Image.SCALE_SMOOTH);
        logo.setIcon(new ImageIcon(imagen1));
		panel_1.add(logo);
		
		JLabel Titulo = new JLabel("Rentas:");
		Titulo.setForeground(new Color(255, 255, 255));
		Titulo.setFont(new Font("Jost* Medium", Font.PLAIN, 35));
		Titulo.setBounds(180, 11, 410, 73);
		panel_1.add(Titulo);
		
		menuTitulo = new JLabel("Modificar reserva:"); // Será actualizado dinámicamente
		menuTitulo.setBackground(new Color(255, 255, 255));
		menuTitulo.setBounds(131, 126, 600, 56); // Aumentar ancho para "Crear Nueva Renta"
		menuTitulo.setFont(new Font("Jost*", Font.BOLD, 38));
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
		botonVolver.setBounds(60, 132, 50, 50);
		botonVolver.setForeground(new Color(255, 255, 255));
		botonVolver.setBackground(new Color(255, 255, 255));
		botonVolver.setBorderPainted(false);
		botonVolver.setFocusPainted(false);
		botonVolver.setContentAreaFilled(true);
		botonVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
                if (isCreationMode) {
                    Rentas conexion = new Rentas();
                    conexion.frame.setVisible(true);
                } else {
                    DatosRenta conexion = new DatosRenta(rentaId);
                    conexion.frame.setVisible(true);
                }
			}
		});
		ImageIcon icon2 = new ImageIcon(getClass().getResource("/images/flecha_izquierda.png"));
        Image imagen2 = icon2.getImage().getScaledInstance(36, 36, Image.SCALE_SMOOTH);
        botonVolver.setIcon(new ImageIcon(imagen2));
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
		
		// Campo para ID de Cliente (reutilizado de searchField)
		textFieldClienteId = new JTextField();
		textFieldClienteId.setToolTipText("ID del Cliente");
		textFieldClienteId.setBounds(770, 140, 290, 40);
		textFieldClienteId.setColumns(10);
		textFieldClienteId.setBackground(new Color(217, 217, 217));
        textFieldClienteId.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 24));
        textFieldClienteId.setForeground(Color.GRAY);
        final String placeholderClienteId = "ID Cliente";
        textFieldClienteId.setText(placeholderClienteId);
        textFieldClienteId.addFocusListener(new FocusAdapter() {
            public void focusGained(FocusEvent e) {
                if (textFieldClienteId.getText().equals(placeholderClienteId)) {
                    textFieldClienteId.setText("");
                    textFieldClienteId.setForeground(Color.BLACK);
                }
            }
            public void focusLost(FocusEvent e) {
                if (textFieldClienteId.getText().isEmpty()) {
                    textFieldClienteId.setText(placeholderClienteId);
                    textFieldClienteId.setForeground(Color.GRAY);
                }
            }
        });
		panel.add(textFieldClienteId);

		JButton btnBuscarCliente = new JButton(""); // Botón para buscar/seleccionar cliente
		btnBuscarCliente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
                // En modo creación, podrías implementar una búsqueda de cliente por ID
                // o abrir una ventana de selección de cliente.
                // Por ahora, solo es visual, el ID se toma del campo.
                if (isCreationMode) {
                    JOptionPane.showMessageDialog(frame, "Ingrese el ID del cliente y presione 'Crear Renta'.", "Información", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(frame, "Esta función es para la creación de rentas.", "Información", JOptionPane.INFORMATION_MESSAGE);
                }
			}
		});
		btnBuscarCliente.setBounds(720, 140, 40, 40);
		ImageIcon u1 = new ImageIcon(getClass().getResource("/images/busqueda.png"));
		Image u2 = u1.getImage();
		Image u3 = u2.getScaledInstance(36, 36, Image.SCALE_SMOOTH);
		btnBuscarCliente.setIcon(new ImageIcon(u3));
		btnBuscarCliente.setBorderPainted(false);
		btnBuscarCliente.setFocusPainted(false);
		btnBuscarCliente.setContentAreaFilled(true);
		panel.add(btnBuscarCliente);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBounds(79, 230, 439, 144);
		panel.add(panel_3);
		panel_3.setLayout(null);
		
		lblDiasHospedaje = new JLabel("Días de hospedaje: 0 días"); // Inicializado para creación
		lblDiasHospedaje.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblDiasHospedaje.setBounds(10, 10, 390, 29);
		panel_3.add(lblDiasHospedaje);
		
		JLabel lblAumentar = new JLabel("Aumentar/Disminuir días:");
		lblAumentar.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 20));
		lblAumentar.setBounds(10, 50, 265, 29);
		panel_3.add(lblAumentar);
		
		JButton btnAumentarDias = new JButton("+");
		btnAumentarDias.setBackground(new Color(50, 186, 124));
		btnAumentarDias.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 25));
		btnAumentarDias.setBounds(10, 89, 72, 45);
        btnAumentarDias.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // La lógica de modificación de días se manejará via los campos de fecha
                JOptionPane.showMessageDialog(frame, "Ajuste los días modificando las fechas de Check-in y Check-out.", "Información", JOptionPane.INFORMATION_MESSAGE);
            }
        });
		panel_3.add(btnAumentarDias);
		
		JButton btnDisminuirDias = new JButton("-");
		btnDisminuirDias.setBackground(Color.RED);
		btnDisminuirDias.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 25));
		btnDisminuirDias.setBounds(357, 89, 72, 45);
        btnDisminuirDias.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                 JOptionPane.showMessageDialog(frame, "Ajuste los días modificando las fechas de Check-in y Check-out.", "Información", JOptionPane.INFORMATION_MESSAGE);
            }
        });
		panel_3.add(btnDisminuirDias);
		
		// Campos de fecha para Check-in y Check-out
		JLabel lblFechaCheckIn = new JLabel("Fecha Check-in (AAAA-MM-DD):");
		lblFechaCheckIn.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblFechaCheckIn.setBounds(546, 200, 350, 29);
		panel.add(lblFechaCheckIn);

		textFieldFechaCheckIn = new JTextField();
		textFieldFechaCheckIn.setBackground(new Color(217, 217, 217));
		textFieldFechaCheckIn.setFont(new Font("Dialog", Font.PLAIN, 16));
		textFieldFechaCheckIn.setBounds(546, 230, 200, 35);
		panel.add(textFieldFechaCheckIn);
		textFieldFechaCheckIn.setColumns(10);
		
		JLabel lblFechaCheckOut = new JLabel("Fecha Check-out (AAAA-MM-DD):");
		lblFechaCheckOut.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblFechaCheckOut.setBounds(546, 275, 350, 29);
		panel.add(lblFechaCheckOut);

		textFieldFechaCheckOut = new JTextField();
		textFieldFechaCheckOut.setBackground(new Color(217, 217, 217));
		textFieldFechaCheckOut.setFont(new Font("Dialog", Font.PLAIN, 16));
		textFieldFechaCheckOut.setBounds(546, 305, 200, 35);
		panel.add(textFieldFechaCheckOut);
		textFieldFechaCheckOut.setColumns(10);

		// Campo para Número de Huéspedes
		JLabel lblNumeroHuespedes = new JLabel("Número de Huéspedes:");
		lblNumeroHuespedes.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNumeroHuespedes.setBounds(546, 350, 250, 29);
		panel.add(lblNumeroHuespedes);

		textFieldNumeroHuespedes = new JTextField();
		textFieldNumeroHuespedes.setBackground(new Color(217, 217, 217));
		textFieldNumeroHuespedes.setFont(new Font("Dialog", Font.PLAIN, 16));
		textFieldNumeroHuespedes.setBounds(546, 380, 200, 35);
		panel.add(textFieldNumeroHuespedes);
		textFieldNumeroHuespedes.setColumns(10);

		JPanel panel_3_1 = new JPanel();
		panel_3_1.setBounds(79, 425, 439, 144); // Reubicado para que no se solape
		panel.add(panel_3_1);
		panel_3_1.setLayout(null);

		lblHabitacionesUsadas = new JLabel("Habitaciones a reservar: 1"); // Etiqueta para creación, ajustada
		lblHabitacionesUsadas.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblHabitacionesUsadas.setBounds(10, 10, 380, 29);
		panel_3_1.add(lblHabitacionesUsadas);
		
		JLabel lblAgregarMas = new JLabel("Añadir/Quitar habitaciones:");
		lblAgregarMas.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 20));
		lblAgregarMas.setBounds(10, 50, 300, 29);
		panel_3_1.add(lblAgregarMas);
		
		JButton btnAumentarHabitaciones = new JButton("+");
		btnAumentarHabitaciones.setBackground(new Color(50, 186, 124));
		btnAumentarHabitaciones.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 25));
		btnAumentarHabitaciones.setBounds(10, 89, 72, 45);
        btnAumentarHabitaciones.addActionListener(new ActionListener() {
            private int currentRooms = 1;
            @Override
            public void actionPerformed(ActionEvent e) {
                currentRooms++;
                lblHabitacionesUsadas.setText("Habitaciones a reservar: " + currentRooms);
            }
        });
		panel_3_1.add(btnAumentarHabitaciones);
		
		JButton btnDisminuirHabitaciones = new JButton("-");
		btnDisminuirHabitaciones.setBackground(Color.RED);
		btnDisminuirHabitaciones.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 25));
		btnDisminuirHabitaciones.setBounds(356, 89, 72, 45);
        btnDisminuirHabitaciones.addActionListener(new ActionListener() {
            private int currentRooms = 1;
            @Override
            public void actionPerformed(ActionEvent e) {
                if (currentRooms > 1) {
                    currentRooms--;
                    lblHabitacionesUsadas.setText("Habitaciones a reservar: " + currentRooms);
                } else {
                    JOptionPane.showMessageDialog(frame, "No se pueden eliminar más habitaciones.", "Advertencia", JOptionPane.WARNING_MESSAGE);
                }
            }
        });
		panel_3_1.add(btnDisminuirHabitaciones);

        // Panel de Servicios (reubicado para evitar solapamiento)
		JPanel panel_servicios = new JPanel();
		panel_servicios.setLayout(null);
		panel_servicios.setBounds(546, 425, 403, 144);
		panel.add(panel_servicios);
		
		JLabel lblAgregarUnExtra = new JLabel("Agregar un extra:");
		lblAgregarUnExtra.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblAgregarUnExtra.setBounds(21, 10, 265, 29);
		panel_servicios.add(lblAgregarUnExtra);
		
		comboBoxServicios = new JComboBox<>();
		comboBoxServicios.setBackground(Color.WHITE);
		comboBoxServicios.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 20));
		comboBoxServicios.setBounds(21, 76, 360, 41);
		panel_servicios.add(comboBoxServicios);
		
		JLabel lblExtra = new JLabel("Extra:");
		lblExtra.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblExtra.setBounds(21, 38, 265, 29);
		panel_servicios.add(lblExtra);
		
		btnGuardarCambios = new JButton("Guardar cambios"); // Será actualizado dinámicamente
		btnGuardarCambios.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
                if (isCreationMode) {
                    // Lógica para CREAR una nueva renta
                    String clienteIdStr = textFieldClienteId.getText().trim();
                    String fechaCheckInStr = textFieldFechaCheckIn.getText().trim();
                    String fechaCheckOutStr = textFieldFechaCheckOut.getText().trim();
                    String numHuespedesStr = textFieldNumeroHuespedes.getText().trim();
                    int numHabitaciones = parseRoomsUsedLabel(); // Obtener del JLabel de habitaciones

                    if (clienteIdStr.isEmpty() || fechaCheckInStr.isEmpty() || fechaCheckOutStr.isEmpty() || numHuespedesStr.isEmpty()) {
                        JOptionPane.showMessageDialog(frame, "Todos los campos son obligatorios.", "Error de Validación", JOptionPane.WARNING_MESSAGE);
                        return;
                    }

                    try {
                        int clienteId = Integer.parseInt(clienteIdStr);
                        LocalDate fechaCheckIn = LocalDate.parse(fechaCheckInStr);
                        LocalDate fechaCheckOut = LocalDate.parse(fechaCheckOutStr);
                        int numHuespedes = Integer.parseInt(numHuespedesStr);

                        if (fechaCheckIn.isAfter(fechaCheckOut)) {
                            JOptionPane.showMessageDialog(frame, "La fecha de Check-in no puede ser posterior a la de Check-out.", "Error de Validación", JOptionPane.WARNING_MESSAGE);
                            return;
                        }

                        // Validar que la habitación seleccionada (habitacionIdParaCreacion) esté disponible
                        Habitaciones habitacionSeleccionada = habitacionDAO.getHabitacionById(habitacionIdParaCreacion);
                        if (habitacionSeleccionada == null || !"disponible".equalsIgnoreCase(habitacionSeleccionada.getEstado())) {
                            JOptionPane.showMessageDialog(frame, "La habitación seleccionada no está disponible o no existe.", "Error", JOptionPane.ERROR_MESSAGE);
                            return;
                        }
                        
                        // Validar capacidad de la habitación
                        TiposHabitacion tipoHabitacion = tipoHabitacionDAO.getTipoHabitacionById(habitacionSeleccionada.getIdTipoHabitacion());
                        if (tipoHabitacion != null && numHuespedes > tipoHabitacion.getCapacidadMaxima()) {
                            JOptionPane.showMessageDialog(frame, "El número de huéspedes excede la capacidad máxima del tipo de habitación (" + tipoHabitacion.getCapacidadMaxima() + ").", "Error de Validación", JOptionPane.WARNING_MESSAGE);
                            return;
                        }

                        // Crear la nueva renta
                        Rentas nuevaRenta = new Rentas(
                            0, // ID se autogenera
                            clienteId,
                            habitacionIdParaCreacion,
                            LocalDateTime.of(fechaCheckIn, LocalDateTime.now().toLocalTime()), // Solo fecha
                            LocalDateTime.of(fechaCheckOut, LocalDateTime.now().toLocalTime()), // Solo fecha
                            "Activa" // Estado inicial
                        );
                        // Suponiendo que el modelo Rentas tiene un campo para el número de huéspedes
                        // y que se puede asociar una renta a un cliente y una habitación.
                        // También se debe manejar el número de habitaciones, aunque el UI es estático a 1
                        // Por simplicidad, asumimos una renta por habitación.
                        nuevaRenta.setNumHuespedes(numHuespedes);


                        if (rentaDAO.createRenta(nuevaRenta)) {
                            // Actualizar el estado de la habitación a "Ocupada"
                            habitacionSeleccionada.setEstado("ocupada");
                            habitacionDAO.updateHabitacion(habitacionSeleccionada); // Actualizar estado en BD

                            JOptionPane.showMessageDialog(frame, "Nueva renta creada exitosamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                            
                            // Añadir servicio extra si se seleccionó
                            String selectedServiceText = (String) comboBoxServicios.getSelectedItem();
                            if (selectedServiceText != null && !selectedServiceText.isEmpty() && !"Seleccione un servicio".equals(selectedServiceText)) {
                                Servicios selectedService = servicioDAO.getServicioByName(selectedServiceText);
                                if (selectedService != null) {
                                    // Asumo que la renta recien creada tiene un ID válido para asociar el servicio
                                    // Necesitamos el ID de la renta recién creada.
                                    // La forma ideal sería que createRenta devuelva el ID.
                                    // Por simplicidad, buscaremos la última renta creada por el cliente o habitación.
                                    // Una solución más robusta sería que createRenta devuelva el objeto Renta completo o su ID.
                                    Rentas createdRenta = rentaDAO.getLastRentaByClienteAndHabitacion(clienteId, habitacionIdParaCreacion);
                                    if (createdRenta != null) {
                                        ServiciosPorReserva spr = new ServiciosPorReserva(
                                            createdRenta.getIdRenta(), // Usar el ID de la renta recién creada
                                            selectedService.getIdServicio(),
                                            1, // Cantidad, si tu modelo de servicios lo permite
                                            LocalDate.now()
                                        );
                                        serviciosPorReservaDAO.createServicioPorReserva(spr); // Asumo que esto se inserta
                                    }
                                }
                            }

                            frame.dispose();
                            Rentas rentasWindow = new Rentas();
                            rentasWindow.frame.setVisible(true);
                        } else {
                            JOptionPane.showMessageDialog(frame, "Error al crear la nueva renta.", "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(frame, "ID de Cliente, Número de Huéspedes deben ser números válidos.", "Error de Formato", JOptionPane.ERROR_MESSAGE);
                    } catch (DateTimeParseException ex) {
                        JOptionPane.showMessageDialog(frame, "Formato de fecha inválido. Use AAAA-MM-DD.", "Error de Formato", JOptionPane.ERROR_MESSAGE);
                    } catch (Exception ex) {
                        ex.printStackTrace();
                        JOptionPane.showMessageDialog(frame, "Ocurrió un error inesperado al crear la renta: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                    }

                } else {
                    // Lógica para MODIFICAR una renta existente
                    if (currentRenta == null) {
                        JOptionPane.showMessageDialog(frame, "No hay una renta cargada para guardar.", "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                    int respuesta = JOptionPane.showConfirmDialog(
                            null,
                            "¿Estás seguro de que desea guardar los cambios de esta renta?",
                            "Confirmación",
                            JOptionPane.YES_NO_OPTION,
                            JOptionPane.WARNING_MESSAGE
                    );

                    if (respuesta == JOptionPane.YES_OPTION) {
                        String clienteIdStr = textFieldClienteId.getText().trim();
                        String fechaCheckInStr = textFieldFechaCheckIn.getText().trim();
                        String fechaCheckOutStr = textFieldFechaCheckOut.getText().trim();
                        String numHuespedesStr = textFieldNumeroHuespedes.getText().trim();
                        // int numHabitaciones = parseRoomsUsedLabel(); // Ya no se usa directamente para modificar

                        try {
                            int clienteId = Integer.parseInt(clienteIdStr);
                            LocalDate fechaCheckIn = LocalDate.parse(fechaCheckInStr);
                            LocalDate fechaCheckOut = LocalDate.parse(fechaCheckOutStr);
                            int numHuespedes = Integer.parseInt(numHuespedesStr);

                            if (fechaCheckIn.isAfter(fechaCheckOut)) {
                                JOptionPane.showMessageDialog(frame, "La fecha de Check-in no puede ser posterior a la de Check-out.", "Error de Validación", JOptionPane.WARNING_MESSAGE);
                                return;
                            }

                            // Actualizar el objeto currentRenta
                            currentRenta.setIdCliente(clienteId);
                            currentRenta.setFechaCheckIn(LocalDateTime.of(fechaCheckIn, currentRenta.getFechaCheckIn().toLocalTime()));
                            currentRenta.setFechaCheckOut(LocalDateTime.of(fechaCheckOut, currentRenta.getFechaCheckOut().toLocalTime()));
                            currentRenta.setNumHuespedes(numHuespedes); // Suponiendo que el modelo Rentas tiene setNumHuespedes

                            boolean success = rentaDAO.updateRenta(currentRenta);

                            String selectedServiceText = (String) comboBoxServicios.getSelectedItem();
                            if (selectedServiceText != null && !selectedServiceText.isEmpty() && !"Seleccione un servicio".equals(selectedServiceText)) {
                                Servicios selectedService = servicioDAO.getServicioByName(selectedServiceText);
                                if (selectedService != null) {
                                    ServiciosPorReserva spr = new ServiciosPorReserva(
                                        currentRenta.getIdRenta(),
                                        selectedService.getIdServicio(),
                                        1, // Cantidad por defecto
                                        LocalDate.now()
                                    );
                                    ServiciosPorReserva existingSpr = serviciosPorReservaDAO.getServicioPorReservaByIds(spr.getIdReserva(), spr.getIdServicio());
                                    if (existingSpr == null) {
                                        if (serviciosPorReservaDAO.createServicioPorReserva(spr)) {
                                            JOptionPane.showMessageDialog(frame, "Servicio extra '" + selectedService.getNombreServicio() + "' añadido.", "Información", JOptionPane.INFORMATION_MESSAGE);
                                        } else {
                                            JOptionPane.showMessageDialog(frame, "Error al añadir el servicio extra.", "Error", JOptionPane.ERROR_MESSAGE);
                                        }
                                    } else {
                                        JOptionPane.showMessageDialog(frame, "El servicio '" + selectedService.getNombreServicio() + "' ya está asociado a esta reserva.", "Información", JOptionPane.INFORMATION_MESSAGE);
                                    }
                                } else {
                                    JOptionPane.showMessageDialog(frame, "Servicio seleccionado no válido.", "Error", JOptionPane.ERROR_MESSAGE);
                                }
                            }

                            if (success) {
                                JOptionPane.showMessageDialog(
                                    null,
                                    "Renta editada exitosamente.",
                                    "Información",
                                    JOptionPane.INFORMATION_MESSAGE
                                );
                                frame.dispose();
                                DatosRenta conexion = new DatosRenta(rentaId);
                                conexion.frame.setVisible(true);
                            } else {
                                JOptionPane.showMessageDialog(
                                    null,
                                    "Error al guardar cambios de la renta principal.",
                                    "Error",
                                    JOptionPane.ERROR_MESSAGE
                                );
                            }
                        } catch (NumberFormatException ex) {
                            JOptionPane.showMessageDialog(frame, "ID de Cliente, Número de Huéspedes deben ser números válidos.", "Error de Formato", JOptionPane.ERROR_MESSAGE);
                        } catch (DateTimeParseException ex) {
                            JOptionPane.showMessageDialog(frame, "Formato de fecha inválido. Use AAAA-MM-DD.", "Error de Formato", JOptionPane.ERROR_MESSAGE);
                        } catch (Exception ex) {
                            ex.printStackTrace();
                            JOptionPane.showMessageDialog(frame, "Ocurrió un error inesperado al actualizar la renta: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                }
            }
        });
		btnGuardarCambios.setBackground(new Color(50, 186, 124));
		btnGuardarCambios.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 20));
		btnGuardarCambios.setBounds(189, 608, 211, 45);
		panel.add(btnGuardarCambios);
		
		JButton btnEliminarCliente = new JButton("Eliminar Cliente");
		btnEliminarCliente.setBackground(new Color(239, 35, 60));
		btnEliminarCliente.setFont(new Font("Inter", Font.BOLD | Font.ITALIC, 24));
		btnEliminarCliente.setBorder(BorderFactory.createLineBorder(Color.BLACK,0));
		btnEliminarCliente.setForeground(Color.WHITE);
		btnEliminarCliente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(frame, "Esta acción de eliminar cliente no es parte de la modificación/creación de reserva.", "Información", JOptionPane.INFORMATION_MESSAGE);
			}
		});
        btnEliminarCliente.setBounds(700, 500, 270, 60);
        btnEliminarCliente.setVisible(false); // Ocultar este botón ya que no encaja en esta pantalla
        panel.add(btnEliminarCliente);
	}

    // Método para cargar los datos de la renta o preparar para una nueva
    private void loadRentaData() {
        if (isCreationMode) {
            menuTitulo.setText("Crear Nueva Renta");
            btnGuardarCambios.setText("Crear Renta");

            // Para una nueva renta, pre-rellenar con datos de la habitación seleccionada
            if (habitacionIdParaCreacion != -1) {
                Habitaciones habitacion = habitacionDAO.getHabitacionById(habitacionIdParaCreacion);
                if (habitacion != null) {
                    lblHabitacionesUsadas.setText("Habitación: " + habitacion.getNumero());
                    // Puedes añadir más detalles de la habitación aquí si es necesario
                } else {
                    lblHabitacionesUsadas.setText("Habitación no encontrada");
                    JOptionPane.showMessageDialog(frame, "La habitación seleccionada para la nueva renta no existe.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                lblHabitacionesUsadas.setText("Seleccione una habitación");
                JOptionPane.showMessageDialog(frame, "No se proporcionó una habitación para crear la renta.", "Advertencia", JOptionPane.WARNING_MESSAGE);
            }

            // Inicializar campos vacíos o con valores por defecto
            textFieldClienteId.setText(""); // El usuario debe ingresar el ID del cliente
            textFieldFechaCheckIn.setText(LocalDate.now().format(java.time.format.DateTimeFormatter.ISO_LOCAL_DATE)); // Fecha actual por defecto
            textFieldFechaCheckOut.setText(LocalDate.now().plusDays(1).format(java.time.format.DateTimeFormatter.ISO_LOCAL_DATE)); // Mañana por defecto
            textFieldNumeroHuespedes.setText("");
            lblDiasHospedaje.setText("Días de hospedaje: 1 día"); // Por defecto 1 día

            // Deshabilitar la capacidad de aumentar/disminuir días a través de botones genéricos,
            // la modificación será directamente en los campos de fecha.
            // Los botones +/- para días de hospedaje en el Figma están directamente en la pantalla de "Modificar Reserva"
            // y no corresponden a la UI de Creación de una nueva renta de forma directa.
            // Mantenerlos solo como un mensaje informativo para que el usuario use los campos de fecha.

        } else {
            // Modo Modificación
            menuTitulo.setText("Modificar Reserva:");
            btnGuardarCambios.setText("Guardar cambios");

            if (rentaId == -1) {
                JOptionPane.showMessageDialog(frame, "Error: ID de Renta inválido para modificación.", "Error", JOptionPane.ERROR_MESSAGE);
                menuTitulo.setText("Renta no encontrada");
                return;
            }

            currentRenta = rentaDAO.getRentaById(rentaId);

            if (currentRenta != null) {
                // Rellenar campos con datos de la renta existente
                textFieldClienteId.setText(String.valueOf(currentRenta.getIdCliente()));
                textFieldFechaCheckIn.setText(currentRenta.getFechaCheckIn().toLocalDate().format(java.time.format.DateTimeFormatter.ISO_LOCAL_DATE));
                textFieldFechaCheckOut.setText(currentRenta.getFechaCheckOut().toLocalDate().format(java.time.format.DateTimeFormatter.ISO_LOCAL_DATE));
                textFieldNumeroHuespedes.setText(String.valueOf(currentRenta.getNumHuespedes())); // Suponiendo que Rentas tiene getNumHuespedes

                updateDiasHospedajeLabel(); // Actualizar basado en las fechas de la renta cargada
                Habitaciones habitacion = habitacionDAO.getHabitacionById(currentRenta.getIdHabitacion());
                if (habitacion != null) {
                    lblHabitacionesUsadas.setText("Habitación: " + habitacion.getNumero());
                } else {
                    lblHabitacionesUsadas.setText("Habitación no encontrada");
                }
            } else {
                menuTitulo.setText("Renta #" + rentaId + " no encontrada.");
                JOptionPane.showMessageDialog(frame, "Renta no encontrada para modificar.", "Error", JOptionPane.ERROR_MESSAGE);
                // Deshabilitar campos si no se encuentra la renta
                textFieldClienteId.setText("");
                textFieldFechaCheckIn.setText("");
                textFieldFechaCheckOut.setText("");
                textFieldNumeroHuespedes.setText("");
                lblDiasHospedaje.setText("Días de hospedaje: N/A");
                lblHabitacionesUsadas.setText("Habitaciones: N/A");
                btnGuardarCambios.setEnabled(false);
            }
        }
    }

    private void updateDiasHospedajeLabel() {
        try {
            LocalDate checkIn = LocalDate.parse(textFieldFechaCheckIn.getText());
            LocalDate checkOut = LocalDate.parse(textFieldFechaCheckOut.getText());
            if (checkIn.isBefore(checkOut) || checkIn.isEqual(checkOut)) {
                long days = ChronoUnit.DAYS.between(checkIn, checkOut);
                lblDiasHospedaje.setText("Días de hospedaje: " + days + " días");
            } else {
                lblDiasHospedaje.setText("Días de hospedaje: Fechas inválidas");
            }
        } catch (DateTimeParseException e) {
            lblDiasHospedaje.setText("Días de hospedaje: Formato inválido");
        }
    }

    private void populateServiciosComboBox() {
        DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>();
        model.addElement("Seleccione un servicio");
        List<Servicios> servicios = servicioDAO.getAllServicios();
        if (servicios != null) {
            for (Servicios servicio : servicios) {
                model.addElement(servicio.getNombreServicio());
            }
        }
        comboBoxServicios.setModel(model);
    }

    // Helper method to parse the "Habitaciones usadas" label text to integer
    // This is a workaround due to UI constraints. Ideally, this would be a direct input.
    private int parseRoomsUsedLabel() {
        String text = lblHabitacionesUsadas.getText();
        try {
            // Expecting format like "Habitaciones a reservar: X"
            String numPart = text.replaceAll("[^\\d]", ""); // Remove non-digits
            return Integer.parseInt(numPart);
        } catch (NumberFormatException e) {
            return 1; // Default to 1 if parsing fails
        }
    }

    public void setVisible(boolean b) {
        frame.setVisible(b);
    }
}
