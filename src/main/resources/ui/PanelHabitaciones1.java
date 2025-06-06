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
import java.util.List; // Importar List
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JScrollPane; // Importar JScrollPane
import java.awt.GridLayout; // Para organizar los botones de habitaciones

import com.formdev.flatlaf.FlatLightLaf;

import Dao.HabitacionDAO; // Importar el DAO para Habitaciones
import modelos.Habitaciones; // Importar la clase de modelo Habitaciones
import ui.Menu;
import ui.TiposHabitacion;
import ui.Rentas;
import ui.Clientes;
import ui.Tarifas;
// Clases de detalle/edición/eliminación que necesitarán constructores con ID
import ui.DetallesHabitacion1; // Asumo que esta es la clase de detalles para una habitación
import ui.PanelHabitaciones2; // Asumo que esta es la clase para eliminar o una vista diferente

public class PanelHabitaciones1 {

	JFrame frame;
	private JTextField searchField; // Renombrado de textField para mayor claridad
    private HabitacionDAO habitacionDAO; // Instancia del DAO para habitaciones
    private JPanel habitacionesDisplayPanel; // Panel para mostrar los botones de habitaciones

	public PanelHabitaciones1() {
		try {
            UIManager.setLookAndFeel(new FlatLightLaf());
            UIManager.put("Button.arc", 90); // Se mantiene el valor original de 90
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        habitacionDAO = new HabitacionDAO(); // Inicializar el DAO
		initialize();
        loadHabitaciones(""); // Cargar todas las habitaciones al inicio
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
		ImageIcon icon423 = new ImageIcon(getClass().getResource("/images/logo.png"));
        Image imagen423 = icon423.getImage().getScaledInstance(170, 95, Image.SCALE_SMOOTH);
	    logo.setIcon(new ImageIcon(imagen423));
		panel_1.add(logo);

		JLabel Titulo = new JLabel("Panel de Habitaciones");
		Titulo.setForeground(new Color(255, 255, 255));
		Titulo.setFont(new Font("Jost* Medium", Font.PLAIN, 35));
		Titulo.setBounds(180, 11, 410, 73);
		panel_1.add(Titulo);

		JLabel menuTitulo = new JLabel("Habitaciones:");
		menuTitulo.setFont(new Font("Jost*", Font.BOLD, 38));
		menuTitulo.setBounds(131, 126, 283, 56);
		panel.add(menuTitulo);

		// Botones superiores (usuario, información)
		JButton botonSuperior1 = new JButton("");
		botonSuperior1.setBackground(new Color(0, 0, 0));
		botonSuperior1.setBorderPainted(false);
		botonSuperior1.setFocusPainted(false);
		botonSuperior1.setContentAreaFilled(true);
		botonSuperior1.setBounds(1098, 11, 56, 56);
		ImageIcon icon69 = new ImageIcon(getClass().getResource("/images/usuario.png"));
		Image imagen69 = icon69.getImage().getScaledInstance(35, 35, Image.SCALE_SMOOTH);
		botonSuperior1.setIcon(new ImageIcon(imagen69));
		panel_1.add(botonSuperior1);

		JButton botonSuperior2 = new JButton("");
		botonSuperior2.setBackground(new Color(0, 0, 0));
		botonSuperior2.setBorderPainted(false);
		botonSuperior2.setFocusPainted(false);
		botonSuperior2.setContentAreaFilled(true);
		botonSuperior2.setBounds(1032, 11, 56, 56);
		ImageIcon icon79 = new ImageIcon(getClass().getResource("/images/informacion.png"));
		Image imagen79 = icon79.getImage().getScaledInstance(35, 35, Image.SCALE_SMOOTH);
		botonSuperior2.setIcon(new ImageIcon(imagen79));
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
				Menu conexion = new Menu();
				conexion.frame.setVisible(true);
			}
		});
		botonVolver.setBounds(60, 132, 50, 50);
		ImageIcon icon42 = new ImageIcon(getClass().getResource("/images/flecha_izquierda.png"));
        Image imagen42 = icon42.getImage().getScaledInstance(36, 36, Image.SCALE_SMOOTH);
		botonVolver.setIcon(new ImageIcon(imagen42));
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
				PanelHabitaciones1 conexion = new PanelHabitaciones1(); // Ya estamos aquí
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
                loadHabitaciones(searchTerm.equals("BUSCAR") ? "" : searchTerm); // Cargar habitaciones filtradas
			}
		});
		btnBuscar.setBounds(720, 140, 40, 40);
		ImageIcon icon45 = new ImageIcon(getClass().getResource("/images/busqueda.png"));
        Image imagen56 = icon45.getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH);
	    btnBuscar.setIcon(new ImageIcon(imagen56));
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
		
		JButton btnEliminarHabitacion = new JButton("Eliminar habitacion");
		btnEliminarHabitacion.setBackground(new Color(239, 35, 60));
		btnEliminarHabitacion.setFont(new Font("Inter", Font.BOLD | Font.ITALIC, 24));
		btnEliminarHabitacion.setBorder(BorderFactory.createLineBorder(Color.BLACK,0));
		btnEliminarHabitacion.setForeground(Color.WHITE);
		btnEliminarHabitacion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				// Asumo que PanelHabitaciones2 es para la eliminación o una vista relacionada con acciones de habitación.
				// Podría ser más específico como EliminarHabitacion si es una ventana para eliminar por ID.
				PanelHabitaciones2 conexion = new PanelHabitaciones2();
				conexion.frame.setVisible(true);
			}
		});
		btnEliminarHabitacion.setBounds(131, 193, 280, 40);
		panel.add(btnEliminarHabitacion);
		
		// Panel para los botones de habitaciones dinámicos
        habitacionesDisplayPanel = new JPanel();
        habitacionesDisplayPanel.setLayout(new GridLayout(0, 5, 20, 20)); // 5 columnas, filas ilimitadas, 20px de espacio
        habitacionesDisplayPanel.setBackground(new Color(255, 255, 255));

        JScrollPane scrollPane = new JScrollPane(habitacionesDisplayPanel);
        scrollPane.setBounds(131, 257, 950, 360); // Ajustar tamaño para que quepan los botones
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        panel.add(scrollPane);
	}

    // Método para cargar y mostrar las habitaciones
    private void loadHabitaciones(String searchTerm) {
        habitacionesDisplayPanel.removeAll(); // Limpiar el panel antes de añadir nuevos botones

        List<Habitaciones> habitaciones;
        if (searchTerm == null || searchTerm.trim().isEmpty() || searchTerm.equals("BUSCAR")) {
            habitaciones = habitacionDAO.getAllHabitaciones(); // Obtener todas las habitaciones
        } else {
            habitaciones = habitacionDAO.searchHabitaciones(searchTerm); // Buscar habitaciones por término
        }

        // Icono por defecto para habitaciones (puedes ajustar el nombre de la imagen)
        ImageIcon defaultRoomIcon = new ImageIcon(getClass().getResource("/images/cama_matrimonial2.png"));
        Image defaultRoomImage = defaultRoomIcon.getImage().getScaledInstance(120, 120, Image.SCALE_SMOOTH);
        ImageIcon scaledDefaultRoomIcon = new ImageIcon(defaultRoomImage);


        if (habitaciones != null && !habitaciones.isEmpty()) {
            for (Habitaciones habitacion : habitaciones) {
                JButton roomButton = new JButton("<html>" + habitacion.getNumero() + "<br>" + habitacion.getEstado() + "</html>");
                roomButton.setFont(new Font("Jost*", Font.BOLD | Font.ITALIC, 14));
                roomButton.setBackground(new Color(0, 187, 249)); // Color azul de tus botones originales
                roomButton.setVerticalTextPosition(SwingConstants.BOTTOM);
                roomButton.setHorizontalTextPosition(SwingConstants.CENTER);
                roomButton.setHorizontalAlignment(SwingConstants.CENTER);
                roomButton.setVerticalAlignment(SwingConstants.CENTER);
                roomButton.setIconTextGap(1);
                roomButton.setBorder(BorderFactory.createLineBorder(Color.BLACK,0));
                roomButton.setIcon(scaledDefaultRoomIcon); // Usar el icono escalado

                final int habitacionId = habitacion.getIdHabitacion(); // Usar final para el ActionListener
                roomButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        frame.dispose();
                        // Asumo que DetallesHabitacion1 puede recibir un ID de habitación
                        DetallesHabitacion1 detallesWindow = new DetallesHabitacion1(habitacionId);
                        detallesWindow.frame.setVisible(true);
                    }
                });
                habitacionesDisplayPanel.add(roomButton);
            }
        } else {
            // Mostrar un mensaje si no hay habitaciones o la búsqueda no arrojó resultados
            JLabel noRoomsLabel = new JLabel("No se encontraron habitaciones.");
            noRoomsLabel.setFont(new Font("Jost*", Font.BOLD, 20));
            noRoomsLabel.setForeground(Color.GRAY);
            noRoomsLabel.setHorizontalAlignment(SwingConstants.CENTER);
            habitacionesDisplayPanel.add(noRoomsLabel);
        }

        habitacionesDisplayPanel.revalidate(); // Revalidar el panel para que se actualice la vista
        habitacionesDisplayPanel.repaint(); // Repintar el panel
    }
}
