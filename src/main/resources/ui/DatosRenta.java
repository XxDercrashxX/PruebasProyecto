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
import java.time.format.DateTimeFormatter; // Para formatear fechas

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.SystemColor;

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
import ui.Rentas;
import ui.TiposHabitacion;
import ui.Clientes;
import ui.PanelHabitaciones1;
import ui.Tarifas;
// Asumo que estas clases recibirán el ID de la renta
import ui.DatosRenta2; // Clase para liberar habitación
import ui.RentasModificarReserva; // Clase para modificar reserva

public class DatosRenta {

	JFrame frame;
	private JTextField searchField; // Renombrado de textField para claridad
    private int rentaId; // ID de la renta que se está mostrando
    private RentaDAO rentaDAO;
    private HabitacionDAO habitacionDAO;
    private ClienteDAO clienteDAO;
    private TipoHabitacionDAO tipoHabitacionDAO; // Nuevo DAO para TiposHabitacion

    private JLabel lblNumeroHabitacion; // Etiqueta para "Cuarto B3"
    private JLabel lblTipoHabitacion;   // Etiqueta para "Suit"
    private JLabel lblNumCamas;         // Etiqueta para "4 Camas individuales"
    private JLabel lblOcupanteNombre;   // Etiqueta para "Diego Ontiveros"
    private JButton btnEstadoHabitacion; // Botón de estado (OCUPADO/DISPONIBLE/MANTENIMIENTO)

	/**
	 * Create the application with a given rental ID.
	 * @param rentalId The ID of the rental to display.
	 */
	public DatosRenta(int rentalId) {
        this.rentaId = rentalId;
        this.rentaDAO = new RentaDAO();
        this.habitacionDAO = new HabitacionDAO();
        this.clienteDAO = new ClienteDAO();
        this.tipoHabitacionDAO = new TipoHabitacionDAO(); // Inicializar TipoHabitacionDAO
		initialize();
        loadRentalData(); // Cargar los datos de la renta específica
	}

    // Constructor sin ID, para compatibilidad si alguna otra parte del código lo llama así
    // Sin embargo, se recomienda siempre pasar un ID para cargar datos específicos
    public DatosRenta() {
        this(-1); // Llama al constructor con -1 como ID por defecto, lo cual significaría que no hay renta cargada
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

		JLabel menuTitulo = new JLabel("Detalles de la renta:"); //Texto menú actualizado
		menuTitulo.setFont(new Font("Jost*", Font.BOLD, 38));
		menuTitulo.setBounds(131, 126, 475, 56);
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
				Rentas conexion = new Rentas(); // Vuelve a la lista de rentas
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

		JButton btnBuscar = new JButton(""); //Boton para la barra de busqueda (sin funcionalidad en esta ventana)
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			    // No hay funcionalidad de búsqueda directa en esta ventana de detalles
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

		searchField = new JTextField("BUSCAR"); //Campo de texto de búsqueda (sin funcionalidad en esta ventana)
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

		JPanel panel_3 = new JPanel(); // Panel de detalles de la habitación
		panel_3.setBackground(new Color(0, 187, 249));
		panel_3.setBounds(103, 228, 313, 422);
		panel.add(panel_3);
		panel_3.setLayout(null);

		JLabel lblRoomImage = new JLabel(""); // Etiqueta para la imagen de la habitación
		lblRoomImage.setBounds(57, 11, 200, 170);
		ImageIcon v1 = new ImageIcon(getClass().getResource("/images/cama_matrimonial2.png"));
		Image v2 = v1.getImage();
		Image v3 = v2.getScaledInstance(200, 200, Image.SCALE_SMOOTH);
		lblRoomImage.setIcon(new ImageIcon(v3));
		panel_3.add(lblRoomImage);

		lblNumeroHabitacion = new JLabel("Cargando..."); // Etiqueta para el número de habitación (antes "Cuarto B3")
		lblNumeroHabitacion.setFont(new Font("Jost*", Font.BOLD | Font.ITALIC, 20));
		lblNumeroHabitacion.setBounds(110, 192, 180, 20); // Ajustado el ancho
		panel_3.add(lblNumeroHabitacion);

		JLabel textoHabitacion = new JLabel("Tipo de habitación:");
		textoHabitacion.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 14));
		textoHabitacion.setBounds(90, 223, 167, 20);
		panel_3.add(textoHabitacion);

		lblTipoHabitacion = new JLabel("Cargando..."); // Etiqueta para el tipo de habitación (antes "Suit")
		lblTipoHabitacion.setFont(new Font("Jost*", Font.BOLD | Font.ITALIC, 16));
		lblTipoHabitacion.setForeground(Color.WHITE);
		lblTipoHabitacion.setBounds(142, 254, 150, 20); // Ajustado el ancho
		panel_3.add(lblTipoHabitacion);

		JLabel lblCamas = new JLabel("Camas:");
		lblCamas.setFont(new Font("Jost*", Font.BOLD | Font.ITALIC, 16));
		lblCamas.setBounds(131, 285, 60, 20);
		panel_3.add(lblCamas);

		lblNumCamas = new JLabel("Cargando..."); // Etiqueta para el número de camas (antes "4 Camas individuales")
		lblNumCamas.setFont(new Font("Jost*", Font.BOLD | Font.ITALIC, 16));
		lblNumCamas.setForeground(Color.WHITE);
		lblNumCamas.setBounds(76, 316, 200, 20); // Ajustado el ancho
		panel_3.add(lblNumCamas);

		JLabel lblOcupante = new JLabel("Ocupante:");
		lblOcupante.setFont(new Font("Jost*", Font.BOLD | Font.ITALIC, 16));
		lblOcupante.setBounds(120, 347, 80, 20);
		panel_3.add(lblOcupante);

		lblOcupanteNombre = new JLabel("Cargando..."); // Etiqueta para el nombre del ocupante (antes "Diego Ontiveros")
		lblOcupanteNombre.setFont(new Font("Jost*", Font.BOLD | Font.ITALIC, 16));
		lblOcupanteNombre.setForeground(Color.WHITE);
		lblOcupanteNombre.setBounds(90, 378, 200, 20); // Ajustado el ancho
		panel_3.add(lblOcupanteNombre);

		btnEstadoHabitacion = new JButton("Cargando..."); // Botón de estado (OCUPADO/DISPONIBLE/MANTENIMIENTO)
		btnEstadoHabitacion.setBackground(new Color(239, 35, 60)); // Color por defecto (Rojo)
		btnEstadoHabitacion.setForeground(Color.WHITE);
		btnEstadoHabitacion.setFont(new Font("Inter", Font.BOLD | Font.ITALIC, 24));
		btnEstadoHabitacion.setBorder(BorderFactory.createLineBorder(Color.BLACK,0));
		btnEstadoHabitacion.setBounds(120, 177, 296, 40);
		panel.add(btnEstadoHabitacion);

		JButton btnCheckOut = new JButton("Check out \r\n");
		btnCheckOut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			    // Lógica para realizar el checkout. Se puede actualizar el estado de la renta a "Finalizada".
			    // Luego, se puede mostrar el JOptionPane.
                if (rentaDAO.updateRentaStatus(rentaId, "Finalizada")) { // Suponiendo un método updateRentaStatus en RentaDAO
                    JOptionPane.showMessageDialog(
                        null,
                        "La renta ha sido finalizada y la habitación se liberará a las 7:00 a.m.",
                        "Información",
                        JOptionPane.INFORMATION_MESSAGE
                    );
                    loadRentalData(); // Recargar datos para reflejar el cambio de estado
                } else {
                    JOptionPane.showMessageDialog(
                        null,
                        "Error al finalizar la renta.",
                        "Error",
                        JOptionPane.ERROR_MESSAGE
                    );
                }
			}
		});
		btnCheckOut.setBackground(new Color(255, 214, 10));
		btnCheckOut.setFont(new Font("Inter", Font.BOLD | Font.ITALIC, 24));
		btnCheckOut.setBorder(BorderFactory.createLineBorder(Color.BLACK,0));
		btnCheckOut.setBounds(426, 441, 270, 60);
		panel.add(btnCheckOut);

		JButton btnLiberarHabitacion = new JButton("Liberar habitacion");
		btnLiberarHabitacion.setForeground(new Color(255, 255, 255));
		btnLiberarHabitacion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose(); // Cierra la ventana actual
				// Asumo que DatosRenta2 recibe el ID de la renta
				DatosRenta2 conexion = new DatosRenta2(rentaId);
				conexion.frame.setVisible(true);
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
				// Asumo que RentasModificarReserva recibe el ID de la renta
				RentasModificarReserva conexion = new RentasModificarReserva(rentaId);
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
                // Lógica para bloquear la habitación por mantenimiento
                // Esto podría implicar actualizar el estado de la habitación a "Mantenimiento"
                // y posiblemente también el estado de la renta si estuviera activa.
                // Aquí solo se muestra un mensaje, la lógica real iría en un DAO.
                JOptionPane.showMessageDialog(
                    null,
                    "Habitación bloqueada por mantenimiento.",
                    "Información",
                    JOptionPane.INFORMATION_MESSAGE
                );
            }
        });
		panel.add(btnBloquearMantenimiento);
	}

    // Método para cargar los datos de la renta y actualizar la UI
    private void loadRentalData() {
        if (rentaId == -1) { // Si no se proporcionó un ID válido
            // Mostrar un mensaje de error o deshabilitar la UI
            menuTitulo.setText("Error: No se encontró ID de renta.");
            return;
        }

        Rentas renta = rentaDAO.getRentaById(rentaId);

        if (renta != null) {
            menuTitulo.setText("Detalles de la renta: " + rentaId); // Actualizar título con el ID de la renta

            // Obtener datos de la habitación
            Habitaciones habitacion = habitacionDAO.getHabitacionById(renta.getIdHabitacion());
            if (habitacion != null) {
                lblNumeroHabitacion.setText(habitacion.getNumero());
                lblNumCamas.setText(habitacion.getNumeroCamas() + " Camas"); // O ajusta el texto
                
                // Obtener el tipo de habitación
                TiposHabitacion tipoHabitacion = tipoHabitacionDAO.getTipoHabitacionById(habitacion.getIdTipoHabitacion());
                if (tipoHabitacion != null) {
                    lblTipoHabitacion.setText(tipoHabitacion.getNombreTipo());
                } else {
                    lblTipoHabitacion.setText("Tipo desconocido");
                }

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
                        btnEstadoHabitacion.setBackground(SystemColor.activeCaptionBorder); // Gris
                        break;
                    default:
                        btnEstadoHabitacion.setBackground(new Color(0, 187, 249)); // Azul por defecto
                        break;
                }
            } else {
                lblNumeroHabitacion.setText("Hab. no encontrada");
                lblTipoHabitacion.setText("N/A");
                lblNumCamas.setText("N/A");
                btnEstadoHabitacion.setText("ERROR");
                btnEstadoHabitacion.setBackground(Color.LIGHT_GRAY);
            }

            // Obtener datos del cliente (ocupante)
            Clientes cliente = clienteDAO.obtenerClientePorId(renta.getIdCliente());
            if (cliente != null) {
                lblOcupanteNombre.setText(cliente.getNombre() + " " + cliente.getApellido());
            } else {
                lblOcupanteNombre.setText("Cliente no encontrado");
            }
        } else {
            // Manejar caso donde la renta no se encuentra
            menuTitulo.setText("Renta #" + rentaId + " no encontrada.");
            lblNumeroHabitacion.setText("N/A");
            lblTipoHabitacion.setText("N/A");
            lblNumCamas.setText("N/A");
            lblOcupanteNombre.setText("N/A");
            btnEstadoHabitacion.setText("ERROR");
            btnEstadoHabitacion.setBackground(Color.LIGHT_GRAY);
        }
    }
}
