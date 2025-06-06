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
import java.util.List; // Para manejar listas de objetos
import java.awt.GridLayout; // Para organizar los botones de habitaciones

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.JScrollPane; // Para el scroll de las habitaciones

import com.formdev.flatlaf.FlatLightLaf;

// DAOs y Modelos necesarios
import Dao.TipoHabitacionDAO;
import Dao.HabitacionDAO;
import modelos.TiposHabitacion;
import modelos.Habitaciones;

// Clases de UI para navegación
import ui.TiposHabitacion;
import ui.Rentas;
import ui.Clientes;
import ui.PanelHabitaciones1;
import ui.Tarifas;
import ui.DetallesHabitacion1; // Para ir a los detalles de una habitación específica

public class Detallesfamiliar {

	JFrame frame;
    private int idTipoHabitacion; // ID del tipo de habitación a mostrar
    private TipoHabitacionDAO tipoHabitacionDAO;
    private HabitacionDAO habitacionDAO;

    // Componentes de UI que se actualizarán dinámicamente
    private JLabel Titulo; // "Detalles del tipo de habitación"
    private JLabel menuTitulo; // Descripción del tipo de habitación
    private JLabel lblTipoHabitacionNombre; // "Tipo de habitación: Familiar"

    private JPanel habitacionesDisplayPanel; // Panel para mostrar los botones de habitaciones


	/**
	 * Create the application with a given room type ID.
	 * @param idTipoHabitacion The ID of the room type to display.
	 */
	public Detallesfamiliar(int idTipoHabitacion) {
        this.idTipoHabitacion = idTipoHabitacion;
        this.tipoHabitacionDAO = new TipoHabitacionDAO();
        this.habitacionDAO = new HabitacionDAO();

		try {
            UIManager.setLookAndFeel(new FlatLightLaf());
            UIManager.put("Button.arc", 90); // Esquinas redondas
        } catch (Exception ex) {
            ex.printStackTrace();
        }
		initialize();
        loadTipoHabitacionData(); // Cargar los datos del tipo de habitación
        loadHabitacionesForTipo(); // Cargar las habitaciones de este tipo
	}

    // Constructor sin ID (para compatibilidad si alguna otra parte del código lo llama así)
    public Detallesfamiliar() {
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
		ImageIcon icon1 = new ImageIcon(getClass().getResource("/images/logo.png"));
        Image imagen1 = icon1.getImage().getScaledInstance(170, 95, Image.SCALE_SMOOTH);
        logo.setIcon(new ImageIcon(imagen1));
		panel_1.add(logo);
		
		Titulo = new JLabel("Detalles del tipo de habitación"); //Titulo - HECHO VARIABLE
		Titulo.setForeground(new Color(255, 255, 255));
		Titulo.setFont(new Font("Jost* Medium", Font.PLAIN, 35));
		Titulo.setBounds(180, 11, 810, 73);
		panel_1.add(Titulo);
		
		lblTipoHabitacionNombre = new JLabel("Cargando Tipo..."); // "Tipo de habitación: Familiar" - HECHO VARIABLE
		lblTipoHabitacionNombre.setBackground(new Color(255, 255, 255));
		lblTipoHabitacionNombre.setFont(new Font("Jost*", Font.BOLD, 34));
		lblTipoHabitacionNombre.setBounds(131, 126, 579, 56);
		panel.add(lblTipoHabitacionNombre);

		menuTitulo = new JLabel("<html><div style='text-align: left;'>Cargando descripción...</div></html>"); //Texto menú - HECHO VARIABLE
		menuTitulo.setFont(new Font("Jost*", Font.BOLD, 16));
		menuTitulo.setBounds(131, 191, 504, 56);
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
		botonVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose(); // Cierra la ventana actual
				TiposHabitacion conexion = new TiposHabitacion(); // Vuelve a la pantalla de TiposHabitacion
				conexion.frame.setVisible(true); 	
			}
		});
		botonVolver.setBounds(60, 132, 36, 36);
		ImageIcon icon4 = new ImageIcon(getClass().getResource("/images/flecha_izquierda.png"));
        Image imagen4 = icon4.getImage().getScaledInstance(36, 36, Image.SCALE_SMOOTH);
        botonVolver.setIcon(new ImageIcon(imagen4));
		panel.add(botonVolver);
		
		JButton btnTiposDeRentas = new JButton("<html>Tipos de habitaciones &#8594;</html>"); //Botón superior tipos de habitaciones (unificado)
		btnTiposDeRentas.setFont(new Font("Jost* Medium", Font.PLAIN, 12));
		btnTiposDeRentas.setForeground(new Color(255, 255, 255));
		btnTiposDeRentas.setBackground(new Color(56, 54, 41));
		btnTiposDeRentas.setBorder(BorderFactory.createLineBorder(Color.BLACK,0));
		btnTiposDeRentas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose(); // Cierra la ventana actual
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
				frame.dispose(); // Cierra la ventana actual
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
				frame.dispose(); // Cierra la ventana actual
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
				frame.dispose(); // Cierra la ventana actual
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
				frame.dispose(); // Cierra la ventana actual
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
		
		// Barra de búsqueda (se mantiene por diseño, pero sin funcionalidad activa)
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
		
        // Panel para los botones de habitaciones dinámicos
        habitacionesDisplayPanel = new JPanel();
        // Ajustar el GridLayout para que quepan las columnas y tenga espacio
        habitacionesDisplayPanel.setLayout(new GridLayout(0, 5, 20, 20)); // 5 columnas, filas ilimitadas, 20px de espacio
        habitacionesDisplayPanel.setBackground(new Color(255, 255, 255));

        JScrollPane scrollPane = new JScrollPane(habitacionesDisplayPanel);
        scrollPane.setBounds(131, 260, 950, 360); // Ajustar tamaño para que quepan los botones
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        panel.add(scrollPane);
	}

    /**
     * Carga los datos del tipo de habitación y actualiza las etiquetas de la UI.
     */
    private void loadTipoHabitacionData() {
        if (idTipoHabitacion == -1) {
            lblTipoHabitacionNombre.setText("Error: ID de Tipo de Habitación inválido.");
            menuTitulo.setText("No se pudo cargar la descripción.");
            return;
        }

        TiposHabitacion tipo = tipoHabitacionDAO.getTipoHabitacionById(idTipoHabitacion);

        if (tipo != null) {
            lblTipoHabitacionNombre.setText("Tipo de habitación: " + tipo.getNombreTipo());
            // Usar HTML para el JLabel para permitir saltos de línea en la descripción
            menuTitulo.setText("<html><div style='text-align: left;'>" + tipo.getDescripcion() + "</div></html>");
            Titulo.setText("Detalles del tipo de habitación: " + tipo.getNombreTipo()); // Actualizar el título principal
        } else {
            lblTipoHabitacionNombre.setText("Tipo de habitación no encontrada.");
            menuTitulo.setText("Descripción no disponible.");
            Titulo.setText("Detalles del tipo de habitación: Error");
        }
    }

    /**
     * Carga y muestra las habitaciones que pertenecen a este tipo de habitación.
     */
    private void loadHabitacionesForTipo() {
        habitacionesDisplayPanel.removeAll(); // Limpiar antes de añadir

        List<Habitaciones> habitaciones = habitacionDAO.getHabitacionesByTipoId(idTipoHabitacion); // Asumo este método en HabitacionDAO

        // Icono por defecto para habitaciones
        ImageIcon defaultRoomIcon = new ImageIcon(getClass().getResource("/images/cama_matrimonial2.png"));
        Image defaultRoomImage = defaultRoomIcon.getImage().getScaledInstance(120, 120, Image.SCALE_SMOOTH);
        ImageIcon scaledDefaultRoomIcon = new ImageIcon(defaultRoomImage);

        if (habitaciones != null && !habitaciones.isEmpty()) {
            for (Habitaciones habitacion : habitaciones) {
                JButton roomButton = new JButton("<html>" + habitacion.getNumero() + "<br>" + habitacion.getEstado() + "</html>");
                roomButton.setFont(new Font("Jost*", Font.BOLD | Font.ITALIC, 14));
                roomButton.setVerticalTextPosition(SwingConstants.BOTTOM);
                roomButton.setHorizontalTextPosition(SwingConstants.CENTER);
                roomButton.setHorizontalAlignment(SwingConstants.CENTER);
                roomButton.setVerticalAlignment(SwingConstants.CENTER);
                roomButton.setIconTextGap(1);
                roomButton.setBorder(BorderFactory.createLineBorder(Color.BLACK,0));
                roomButton.setIcon(scaledDefaultRoomIcon);

                // Determinar el color según el estado de la habitación
                switch (habitacion.getEstado().toLowerCase()) {
                    case "disponible":
                        roomButton.setBackground(new Color(173, 102, 67)); // Color café de tu diseño original
                        break;
                    case "ocupada":
                        roomButton.setBackground(new Color(239, 35, 60)); // Rojo
                        break;
                    case "mantenimiento":
                        roomButton.setBackground(Color.LIGHT_GRAY); // Gris
                        break;
                    default:
                        roomButton.setBackground(new Color(0, 187, 249)); // Azul por defecto
                        break;
                }

                final int currentHabitacionId = habitacion.getIdHabitacion(); // Usar final para el ActionListener
                roomButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        frame.dispose();
                        DetallesHabitacion1 detallesWindow = new DetallesHabitacion1(currentHabitacionId);
                        detallesWindow.frame.setVisible(true);
                    }
                });
                habitacionesDisplayPanel.add(roomButton);
            }
        } else {
            JLabel noHabitacionesLabel = new JLabel("No hay habitaciones de este tipo.");
            noHabitacionesLabel.setFont(new Font("Jost*", Font.BOLD, 20));
            noHabitacionesLabel.setForeground(Color.GRAY);
            noHabitacionesLabel.setHorizontalAlignment(SwingConstants.CENTER);
            habitacionesDisplayPanel.add(noHabitacionesLabel);
        }

        habitacionesDisplayPanel.revalidate();
        habitacionesDisplayPanel.repaint();
    }

    public void setVisible(boolean b) {
        frame.setVisible(b);
    }
}
