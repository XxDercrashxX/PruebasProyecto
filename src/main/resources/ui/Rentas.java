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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.JScrollPane;
import java.awt.GridLayout;
import java.awt.SystemColor;

import com.formdev.flatlaf.FlatLightLaf;

import Dao.HabitacionDAO; // Ahora necesitamos el DAO de Habitaciones
import Dao.RentaDAO; // Para buscar rentas activas por habitación
import modelos.Habitaciones; // Modelo de Habitaciones
import modelos.Rentas; // Modelo de Rentas (para rentas activas)

import ui.Menu;
import ui.TiposHabitacion;
import ui.Clientes;
import ui.PanelHabitaciones1;
import ui.Tarifas;
import ui.DatosRenta; // Para ver detalles de una renta existente
import ui.RentasModificarReserva; // Para crear/modificar rentas

public class Rentas {

	public JFrame frame;
	private JTextField searchField;
    private HabitacionDAO habitacionDAO; // Usaremos HabitacionDAO
    private RentaDAO rentaDAO; // También necesitaremos RentaDAO para buscar rentas activas
    private JPanel habitacionesDisplayPanel; // Panel para mostrar los botones de habitaciones

	public Rentas() {
		try {
            UIManager.setLookAndFeel(new FlatLightLaf());
            UIManager.put("Button.arc", 90);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        habitacionDAO = new HabitacionDAO();
        rentaDAO = new RentaDAO(); // Inicializar RentaDAO
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
		ImageIcon icon01 = new ImageIcon(getClass().getResource("/images/logo.png"));
        Image imagen01 = icon01.getImage().getScaledInstance(170, 95, Image.SCALE_SMOOTH);
        logo.setIcon(new ImageIcon(imagen01));
		panel_1.add(logo);

		JLabel Titulo = new JLabel("Rentas");
		Titulo.setForeground(new Color(255, 255, 255));
		Titulo.setFont(new Font("Jost* Medium", Font.PLAIN, 35));
		Titulo.setBounds(180, 11, 410, 73);
		panel_1.add(Titulo);

		JLabel menuTitulo = new JLabel("Verde disponible / Rojo ocupado / Gris mantenimiento");
		menuTitulo.setFont(new Font("Jost*", Font.BOLD, 22));
		menuTitulo.setBounds(131, 126, 579, 56);
		panel.add(menuTitulo);

		JButton botonSuperior1 = new JButton("");
		botonSuperior1.setBackground(new Color(0, 0, 0));
		botonSuperior1.setBorderPainted(false);
		botonSuperior1.setFocusPainted(false);
		botonSuperior1.setContentAreaFilled(true);
		botonSuperior1.setBounds(1098, 11, 56, 56);
		ImageIcon icon03 = new ImageIcon(getClass().getResource("/images/usuario.png"));
		Image imagen03 = icon03.getImage().getScaledInstance(36, 36, Image.SCALE_SMOOTH);
		botonSuperior1.setIcon(new ImageIcon(imagen03));
		panel_1.add(botonSuperior1);

		JButton botonSuperior2 = new JButton("");
		botonSuperior2.setBackground(new Color(0, 0, 0));
		botonSuperior2.setBorderPainted(false);
		botonSuperior2.setFocusPainted(false);
		botonSuperior2.setContentAreaFilled(true);
		botonSuperior2.setBounds(1032, 11, 56, 56);
		ImageIcon icon02 = new ImageIcon(getClass().getResource("/images/informacion.png"));
		Image imagen02 = icon02.getImage().getScaledInstance(36, 36, Image.SCALE_SMOOTH);
		botonSuperior2.setIcon(new ImageIcon(imagen02));
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
				Menu conexion = new Menu();
				conexion.frame.setVisible(true);
			}
		});
		botonVolver.setBounds(60, 132, 36, 36);
		ImageIcon icon10 = new ImageIcon(getClass().getResource("/images/flecha_izquierda.png"));
        Image imagen10 = icon10.getImage().getScaledInstance(36, 36, Image.SCALE_SMOOTH);
		botonVolver.setIcon(new ImageIcon(imagen10));
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
                loadHabitaciones(searchTerm.equals("BUSCAR") ? "" : searchTerm);
			}
		});
		btnBuscar.setBounds(720, 140, 40, 40);
		ImageIcon icon11 = new ImageIcon(getClass().getResource("/images/busqueda.png"));
        Image imagen11 = icon11.getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH);
		btnBuscar.setIcon(new ImageIcon(imagen11));
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

        habitacionesDisplayPanel = new JPanel();
        habitacionesDisplayPanel.setLayout(new GridLayout(0, 5, 20, 20)); // 5 columnas, filas ilimitadas, 20px de espacio
        habitacionesDisplayPanel.setBackground(new Color(255, 255, 255));

        JScrollPane scrollPane = new JScrollPane(habitacionesDisplayPanel);
        scrollPane.setBounds(131, 257, 950, 360);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        panel.add(scrollPane);
	}

    // Método para cargar y mostrar las habitaciones
    private void loadHabitaciones(String searchTerm) {
        habitacionesDisplayPanel.removeAll();

        List<Habitaciones> habitaciones;
        if (searchTerm == null || searchTerm.trim().isEmpty() || searchTerm.equals("BUSCAR")) {
            habitaciones = habitacionDAO.getAllHabitaciones();
        } else {
            // Asumo que la búsqueda por término buscará por número de habitación o estado
            habitaciones = habitacionDAO.searchHabitaciones(searchTerm);
        }

        ImageIcon defaultRoomIcon = new ImageIcon(getClass().getResource("/images/cama_matrimonial2.png"));
        Image defaultRoomImage = defaultRoomIcon.getImage().getScaledInstance(120, 120, Image.SCALE_SMOOTH);
        ImageIcon scaledDefaultRoomIcon = new ImageIcon(defaultRoomImage);


        if (habitaciones != null && !habitaciones.isEmpty()) {
            for (Habitaciones habitacion : habitaciones) {
                Color bgColor;
                String statusText = habitacion.getEstado();
                switch (statusText.toLowerCase()) {
                    case "disponible":
                        bgColor = new Color(50, 186, 124); // Verde para disponible
                        break;
                    case "ocupada":
                        bgColor = new Color(239, 35, 60); // Rojo para ocupada
                        break;
                    case "mantenimiento":
                        bgColor = SystemColor.activeCaptionBorder; // Gris para mantenimiento
                        break;
                    default:
                        bgColor = new Color(0, 187, 249); // Azul por defecto
                        break;
                }

                JButton roomButton = new JButton("<html>Cuarto " + habitacion.getNumero() + "<br>" + statusText + "</html>");
                roomButton.setFont(new Font("Jost*", Font.BOLD | Font.ITALIC, 14));
                roomButton.setBackground(bgColor);
                roomButton.setVerticalTextPosition(SwingConstants.BOTTOM);
                roomButton.setHorizontalTextPosition(SwingConstants.CENTER);
                roomButton.setHorizontalAlignment(SwingConstants.CENTER);
                roomButton.setVerticalAlignment(SwingConstants.CENTER);
                roomButton.setIconTextGap(1);
                roomButton.setBorder(BorderFactory.createLineBorder(Color.BLACK,0));
                roomButton.setIcon(scaledDefaultRoomIcon);

                final int habitacionId = habitacion.getIdHabitacion(); // Usar final para el ActionListener
                final String estadoHabitacion = habitacion.getEstado(); // Usar final

                roomButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        frame.dispose();
                        if ("disponible".equalsIgnoreCase(estadoHabitacion)) {
                            // Abrir RentasModificarReserva en modo CREACIÓN
                            RentasModificarReserva nuevaRentaWindow = new RentasModificarReserva(-1, habitacionId); // -1 para rentaId indica creación
                            nuevaRentaWindow.frame.setVisible(true);
                        } else if ("ocupada".equalsIgnoreCase(estadoHabitacion)) {
                            // Buscar la renta activa asociada a esta habitación
                            Rentas rentaActiva = rentaDAO.getRentaActivaByHabitacionId(habitacionId);
                            if (rentaActiva != null) {
                                // Abrir DatosRenta para ver los detalles de la renta existente
                                DatosRenta datosRentaWindow = new DatosRenta(rentaActiva.getIdRenta());
                                datosRentaWindow.frame.setVisible(true);
                            } else {
                                JOptionPane.showMessageDialog(frame, "Habitación ocupada pero no se encontró renta activa asociada.", "Error", JOptionPane.ERROR_MESSAGE);
                                // Podrías forzar un cambio de estado de la habitación aquí si hay inconsistencia.
                            }
                        } else if ("mantenimiento".equalsIgnoreCase(estadoHabitacion)) {
                            JOptionPane.showMessageDialog(frame, "La habitación " + habitacion.getNumero() + " está en mantenimiento.", "Información", JOptionPane.INFORMATION_MESSAGE);
                            // Podrías abrir una ventana de detalles de mantenimiento si existiera
                        }
                    }
                });
                habitacionesDisplayPanel.add(roomButton);
            }
        } else {
            JLabel noHabitacionesLabel = new JLabel("No se encontraron habitaciones.");
            noHabitacionesLabel.setFont(new Font("Jost*", Font.BOLD, 20));
            noHabitacionesLabel.setForeground(Color.GRAY);
            noHabitacionesLabel.setHorizontalAlignment(SwingConstants.CENTER);
            habitacionesDisplayPanel.add(noHabitacionesLabel);
        }

        habitacionesDisplayPanel.revalidate();
        habitacionesDisplayPanel.repaint();
    }

    public void setVisible(boolean b) {
        if (frame != null) {
            frame.setVisible(b);
        }
    }
}
