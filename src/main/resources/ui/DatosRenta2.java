package ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.formdev.flatlaf.FlatLightLaf;

import Dao.RentaDAO;
import Dao.HabitacionDAO;
import Dao.ClienteDAO;
import Dao.TipoHabitacionDAO; // Necesario para obtener el nombre del tipo de habitación

import modelos.Rentas;
import modelos.Habitaciones;
import modelos.Clientes;
import modelos.TiposHabitacion; // Importar el modelo de TiposHabitacion
import ui.Menu;
import ui.Rentas; // Para volver a la pantalla de Rentas

public class DatosRenta2 {

	JFrame frame;
	private JTextField textField; // Campo de búsqueda, se mantiene aunque no se use activamente aquí
    private int rentaId; // ID de la renta que se está gestionando
    private RentaDAO rentaDAO;
    private HabitacionDAO habitacionDAO;
    private ClienteDAO clienteDAO;
    private TipoHabitacionDAO tipoHabitacionDAO; // Nuevo DAO para TiposHabitacion

    // Componentes de UI que mostrarán datos dinámicos
    private JLabel lblNumeroHabitacion;
    private JLabel lblTipoHabitacion;
    private JLabel lblNumCamas;
    private JLabel lblOcupanteNombre;
    private JButton btnEstadoHabitacion; // El botón "LIBRE" o "OCUPADO"

	/**
	 * Create the application.
     * @param rentaId El ID de la renta para la cual se desean mostrar los detalles o realizar acciones.
	 */
	public DatosRenta2(int rentaId) {
        this.rentaId = rentaId;
        this.rentaDAO = new RentaDAO();
        this.habitacionDAO = new HabitacionDAO();
        this.clienteDAO = new ClienteDAO();
        this.tipoHabitacionDAO = new TipoHabitacionDAO(); // Inicializar TipoHabitacionDAO
		initialize();
        loadData(); // Cargar los datos al inicializar
	}

    // Constructor sin ID (para compatibilidad, pero debería usarse con un ID)
    public DatosRenta2() {
        this(-1); // Llama al constructor principal con un ID inválido por defecto
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

		JLabel Titulo = new JLabel("Rentas\r\n"); //Titulo
		Titulo.setForeground(new Color(255, 255, 255));
		Titulo.setFont(new Font("Jost* Medium", Font.PLAIN, 35));
		Titulo.setBounds(180, 11, 410, 73);
		panel_1.add(Titulo);

		JLabel menuTitulo = new JLabel("Liberar habitación:"); //Texto menú actualizado
		menuTitulo.setFont(new Font("Jost*", Font.BOLD, 38));
		menuTitulo.setBounds(131, 126, 441, 56);
		panel.add(menuTitulo);

		// Iconos superiores (usuario, información)
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
		botonVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose(); // Cierra la ventana actual
				Rentas conexion = new Rentas();
				conexion.frame.setVisible(true);
			}
		});
		botonVolver.setBounds(60, 132, 50, 50);
		ImageIcon f1 = new ImageIcon(getClass().getResource("/images/flecha_izquierda.png"));
		Image f2 = f1.getImage();
		Image f3 = f2.getScaledInstance(36, 36, Image.SCALE_SMOOTH);
		botonVolver.setIcon(new ImageIcon(f3));
		panel.add(botonVolver);

		// Botones de navegación superior
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

		JButton btnBuscar = new JButton(""); //Boton para la barra de busqueda
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

		textField = new JTextField("BUSCAR"); //Texto de ejemplo
		textField.setToolTipText("");
		textField.setBounds(770, 140, 290, 40);
		textField.setColumns(10);
		textField.setBackground(new Color(217, 217, 217));
        textField.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 24));
        textField.setForeground(Color.GRAY);
        final String placeholder = "BUSCAR";
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

		JPanel panel_3 = new JPanel();
		panel_3.setBackground(new Color(0, 187, 249));
		panel_3.setBounds(103, 228, 313, 422);
		panel.add(panel_3);
		panel_3.setLayout(null);

		JLabel lblRoomImage = new JLabel("");
		lblRoomImage.setBounds(57, 11, 200, 170);
		ImageIcon v1 = new ImageIcon(getClass().getResource("/images/cama_matrimonial2.png"));
		Image v2 = v1.getImage();
		Image v3 = v2.getScaledInstance(200, 200, Image.SCALE_SMOOTH);
		lblRoomImage.setIcon(new ImageIcon(v3));
		panel_3.add(lblRoomImage);

		lblNumeroHabitacion = new JLabel("Cargando..."); // Etiqueta para el número de habitación
		lblNumeroHabitacion.setFont(new Font("Jost*", Font.BOLD | Font.ITALIC, 20));
		lblNumeroHabitacion.setBounds(110, 192, 180, 20);
		panel_3.add(lblNumeroHabitacion);

		JLabel textoTipoHabitacionLabel = new JLabel("Tipo de habitación:");
		textoTipoHabitacionLabel.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 14));
		textoTipoHabitacionLabel.setBounds(90, 223, 167, 20);
		panel_3.add(textoTipoHabitacionLabel);

		lblTipoHabitacion = new JLabel("Cargando..."); // Etiqueta para el tipo de habitación
		lblTipoHabitacion.setFont(new Font("Jost*", Font.BOLD | Font.ITALIC, 16));
		lblTipoHabitacion.setForeground(Color.WHITE);
		lblTipoHabitacion.setBounds(142, 254, 150, 20);
		panel_3.add(lblTipoHabitacion);

		JLabel lblCamasLabel = new JLabel("Camas:");
		lblCamasLabel.setFont(new Font("Jost*", Font.BOLD | Font.ITALIC, 16));
		lblCamasLabel.setBounds(131, 285, 60, 20);
		panel_3.add(lblCamasLabel);

		lblNumCamas = new JLabel("Cargando..."); // Etiqueta para el número de camas
		lblNumCamas.setFont(new Font("Jost*", Font.BOLD | Font.ITALIC, 16));
		lblNumCamas.setForeground(Color.WHITE);
		lblNumCamas.setBounds(76, 316, 168, 20);
		panel_3.add(lblNumCamas);

		JLabel lblOcupanteLabel = new JLabel("Ocupante:");
		lblOcupanteLabel.setFont(new Font("Jost*", Font.BOLD | Font.ITALIC, 16));
		lblOcupanteLabel.setBounds(120, 347, 80, 20);
		panel_3.add(lblOcupanteLabel);

		lblOcupanteNombre = new JLabel("Cargando..."); // Etiqueta para el nombre del ocupante
		lblOcupanteNombre.setFont(new Font("Jost*", Font.BOLD | Font.ITALIC, 16));
		lblOcupanteNombre.setForeground(Color.WHITE);
		lblOcupanteNombre.setBounds(90, 378, 154, 20);
		panel_3.add(lblOcupanteNombre);

		btnEstadoHabitacion = new JButton("Cargando..."); // Botón de estado (LIBRE/OCUPADO/MANTENIMIENTO)
		btnEstadoHabitacion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
                // No se necesita acción al hacer clic en el botón de estado, solo muestra el estado
			}
		});
		btnEstadoHabitacion.setBackground(new Color(50, 186, 124)); // Color por defecto (Verde)
		btnEstadoHabitacion.setForeground(Color.WHITE);
		btnEstadoHabitacion.setFont(new Font("Inter", Font.BOLD | Font.ITALIC, 24));
		btnEstadoHabitacion.setBorder(BorderFactory.createLineBorder(Color.BLACK,0));
		btnEstadoHabitacion.setBounds(120, 177, 296, 40);
		panel.add(btnEstadoHabitacion);

		JButton btnCheckOut = new JButton("Check out \r\n"); // Este botón no tiene funcionalidad en esta vista
		btnCheckOut.setBackground(new Color(255, 214, 10));
		btnCheckOut.setFont(new Font("Inter", Font.BOLD | Font.ITALIC, 24));
		btnCheckOut.setBorder(BorderFactory.createLineBorder(Color.BLACK,0));
		btnCheckOut.setBounds(426, 441, 270, 60);
		btnCheckOut.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(
                    null,
                    "Esta acción de 'Check Out' se realiza desde la vista de 'Datos Renta' (la anterior).",
                    "Información",
                    JOptionPane.INFORMATION_MESSAGE
                );
            }
        });
		panel.add(btnCheckOut);

		JButton btnLiberarHabitacion = new JButton("Liberar habitacion");
		btnLiberarHabitacion.setForeground(new Color(255, 255, 255));
		btnLiberarHabitacion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
                // Lógica para liberar la habitación y finalizar la renta
                if (rentaId != -1) {
                    Rentas renta = rentaDAO.getRentaById(rentaId);
                    if (renta != null) {
                        // 1. Actualizar el estado de la habitación a "disponible"
                        Habitaciones habitacion = habitacionDAO.getHabitacionById(renta.getIdHabitacion());
                        if (habitacion != null) {
                            habitacion.setEstado("disponible");
                            if (habitacionDAO.updateHabitacion(habitacion)) {
                                // 2. Actualizar el estado de la renta a "Finalizada"
                                renta.setEstado("Finalizada");
                                renta.setFechaCheckOut(LocalDateTime.now()); // Establecer la fecha de finalización
                                if (rentaDAO.updateRenta(renta)) {
                                    JOptionPane.showMessageDialog(
                                        null,
                                        "La renta " + rentaId + " ha sido finalizada y la habitación " + habitacion.getNumero() + " ha sido liberada.",
                                        "Éxito",
                                        JOptionPane.INFORMATION_MESSAGE
                                    );
                                    frame.dispose(); // Cierra la ventana actual
                                    Rentas conexion = new Rentas(); // Vuelve a la lista de rentas
                                    conexion.frame.setVisible(true);
                                } else {
                                    JOptionPane.showMessageDialog(
                                        null,
                                        "Error al finalizar la renta.",
                                        "Error",
                                        JOptionPane.ERROR_MESSAGE
                                    );
                                }
                            } else {
                                JOptionPane.showMessageDialog(
                                    null,
                                    "Error al liberar la habitación.",
                                    "Error",
                                    JOptionPane.ERROR_MESSAGE
                                );
                            }
                        } else {
                            JOptionPane.showMessageDialog(
                                null,
                                "No se pudo encontrar la habitación asociada a esta renta.",
                                "Error",
                                JOptionPane.ERROR_MESSAGE
                            );
                        }
                    } else {
                        JOptionPane.showMessageDialog(
                            null,
                            "No se pudo encontrar la renta con ID " + rentaId + ".",
                            "Error",
                            JOptionPane.ERROR_MESSAGE
                        );
                    }
                } else {
                    JOptionPane.showMessageDialog(
                        null,
                        "No se ha proporcionado un ID de renta válido.",
                        "Error",
                        JOptionPane.ERROR_MESSAGE
                    );
                }
			}
		});
		btnLiberarHabitacion.setBackground(new Color(50, 186, 124));
		btnLiberarHabitacion.setFont(new Font("Inter", Font.BOLD | Font.ITALIC, 24));
		btnLiberarHabitacion.setBorder(BorderFactory.createLineBorder(Color.BLACK,0));
		btnLiberarHabitacion.setBounds(426, 228, 270, 60);
		panel.add(btnLiberarHabitacion);
		
		JButton btnModificarReserva = new JButton("Modificar reserva\r\n");
		btnModificarReserva.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 24));
		btnModificarReserva.setBorder(BorderFactory.createLineBorder(Color.BLACK,0));
		btnModificarReserva.setBackground(new Color(255, 214, 10));
		btnModificarReserva.setBounds(426, 299, 270, 60);
		btnModificarReserva.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose(); // Cierra la ventana actual
				RentasModificarReserva conexion = new RentasModificarReserva(rentaId); // Pasa el ID de la renta
				conexion.frame.setVisible(true);
			}
		});
		panel.add(btnModificarReserva);
		
		JButton btnBloquearMantenimiento = new JButton("Bloquear por mantenimiento \r\n");
		btnBloquearMantenimiento.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 20));
		btnBloquearMantenimiento.setBorder(BorderFactory.createLineBorder(Color.BLACK,0));
		btnBloquearMantenimiento.setBackground(SystemColor.activeCaptionBorder);
		btnBloquearMantenimiento.setBounds(426, 370, 270, 60);
        btnBloquearMantenimiento.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (rentaId != -1) {
                    Rentas renta = rentaDAO.getRentaById(rentaId);
                    if (renta != null) {
                        Habitaciones habitacion = habitacionDAO.getHabitacionById(renta.getIdHabitacion());
                        if (habitacion != null) {
                            int confirm = JOptionPane.showConfirmDialog(
                                null,
                                "¿Desea cambiar el estado de la habitación " + habitacion.getNumero() + " a 'Mantenimiento'?",
                                "Confirmar Mantenimiento",
                                JOptionPane.YES_NO_OPTION
                            );
                            if (confirm == JOptionPane.YES_OPTION) {
                                habitacion.setEstado("Mantenimiento");
                                if (habitacionDAO.updateHabitacion(habitacion)) {
                                    // Opcional: Si la renta está activa, podrías preguntar si también deseas finalizarla
                                    // Por simplicidad, aquí solo cambia el estado de la habitación.
                                    JOptionPane.showMessageDialog(
                                        null,
                                        "Habitación " + habitacion.getNumero() + " puesta en mantenimiento.",
                                        "Información",
                                        JOptionPane.INFORMATION_MESSAGE
                                    );
                                    loadData(); // Recargar para reflejar el cambio
                                } else {
                                    JOptionPane.showMessageDialog(
                                        null,
                                        "Error al poner la habitación en mantenimiento.",
                                        "Error",
                                        JOptionPane.ERROR_MESSAGE
                                    );
                                }
                            }
                        } else {
                            JOptionPane.showMessageDialog(
                                null,
                                "No se pudo encontrar la habitación asociada.",
                                "Error",
                                JOptionPane.ERROR_MESSAGE
                            );
                        }
                    } else {
                        JOptionPane.showMessageDialog(
                            null,
                            "No se pudo encontrar la renta asociada.",
                            "Error",
                            JOptionPane.ERROR_MESSAGE
                        );
                    }
                } else {
                    JOptionPane.showMessageDialog(
                        null,
                        "No se ha proporcionado un ID de renta válido para bloquear.",
                        "Error",
                        JOptionPane.ERROR_MESSAGE
                    );
                }
            }
        });
		panel.add(btnBloquearMantenimiento);
	}

    /**
     * Carga los datos de la renta, habitación y cliente asociados
     * y actualiza los componentes de la UI.
     */
    private void loadData() {
        if (rentaId == -1) {
            menuTitulo.setText("Error: ID de Renta inválido.");
            lblNumeroHabitacion.setText("N/A");
            lblTipoHabitacion.setText("N/A");
            lblNumCamas.setText("N/A");
            lblOcupanteNombre.setText("N/A");
            btnEstadoHabitacion.setText("ERROR");
            btnEstadoHabitacion.setBackground(Color.LIGHT_GRAY);
            return;
        }

        Rentas renta = rentaDAO.getRentaById(rentaId);

        if (renta != null) {
            menuTitulo.setText("Liberar habitación (Renta ID: " + renta.getIdRenta() + ")");

            Habitaciones habitacion = habitacionDAO.getHabitacionById(renta.getIdHabitacion());
            Clientes cliente = clienteDAO.obtenerClientePorId(renta.getIdCliente());
            TiposHabitacion tipoHabitacion = null;

            if (habitacion != null) {
                tipoHabitacion = tipoHabitacionDAO.getTipoHabitacionById(habitacion.getIdTipoHabitacion());
            }

            lblNumeroHabitacion.setText(habitacion != null ? habitacion.getNumero() : "No encontrada");
            lblTipoHabitacion.setText(tipoHabitacion != null ? tipoHabitacion.getNombreTipo() : "Desconocido");
            lblNumCamas.setText(habitacion != null ? habitacion.getNumeroCamas() + " Camas" : "N/A");
            lblOcupanteNombre.setText(cliente != null ? cliente.getNombre() + " " + cliente.getApellido() : "No encontrado");

            // Actualizar el botón de estado de la habitación
            if (habitacion != null) {
                btnEstadoHabitacion.setText(habitacion.getEstado().toUpperCase());
                switch (habitacion.getEstado().toLowerCase()) {
                    case "disponible":
                        btnEstadoHabitacion.setBackground(new Color(50, 186, 124)); // Verde
                        break;
                    case "ocupada":
                        btnEstadoHabitacion.setBackground(new Color(239, 35, 60)); // Rojo
                        break;
                    case "mantenimiento":
                        btnEstadoHabitacion.setBackground(SystemColor.activeCaptionBorder); // Gris
                        break;
                    default:
                        btnEstadoHabitacion.setBackground(new Color(0, 187, 249)); // Azul por defecto
                        break;
                }
            } else {
                btnEstadoHabitacion.setText("ERROR");
                btnEstadoHabitacion.setBackground(Color.LIGHT_GRAY);
            }
        } else {
            menuTitulo.setText("Renta #" + rentaId + " no encontrada.");
            lblNumeroHabitacion.setText("N/A");
            lblTipoHabitacion.setText("N/A");
            lblNumCamas.setText("N/A");
            lblOcupanteNombre.setText("N/A");
            btnEstadoHabitacion.setText("ERROR");
            btnEstadoHabitacion.setBackground(Color.LIGHT_GRAY);
        }
    }

    public void setVisible(boolean b) {
        frame.setVisible(b);
    }
}
