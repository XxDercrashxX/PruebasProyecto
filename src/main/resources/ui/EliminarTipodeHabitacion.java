package ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List; // Para listas de habitaciones

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane; // Usado temporalmente, se recomienda JDialog personalizado
import javax.swing.JPanel;
import javax.swing.UIManager;

import com.formdev.flatlaf.FlatLightLaf;

// DAOs y Modelos necesarios
import Dao.TipoHabitacionDAO;
import Dao.HabitacionDAO; // Para verificar habitaciones asociadas
import modelos.TiposHabitacion;
import modelos.Habitaciones; // Para verificar habitaciones asociadas

// Clases de UI para navegación
import ui.TiposHabitacion;
import ui.Rentas;
import ui.Clientes;
import ui.PanelHabitaciones1;
import ui.Tarifas;

public class EliminarTipodeHabitacion {

	JFrame frame;
    private int idTipoHabitacionAEliminar; // ID del tipo de habitación a eliminar
    private TipoHabitacionDAO tipoHabitacionDAO;
    private HabitacionDAO habitacionDAO; // Para verificar habitaciones asociadas

    // Componentes de UI que se actualizarán dinámicamente
    private JLabel Titulo;
    private JLabel lblEstandar; // Para mostrar el nombre del tipo de habitación
    private JLabel lblTexto1_2; // Para mostrar la cantidad de habitaciones de ese tipo
    private JLabel lblImagen1; // Para mostrar la imagen del tipo de habitación


	/**
	 * Create the application with a given room type ID to delete.
	 * @param idTipoHabitacionAEliminar The ID of the room type to be deleted.
	 */
	public EliminarTipodeHabitacion(int idTipoHabitacionAEliminar) {
        this.idTipoHabitacionAEliminar = idTipoHabitacionAEliminar;
        this.tipoHabitacionDAO = new TipoHabitacionDAO();
        this.habitacionDAO = new HabitacionDAO(); // Inicializar HabitacionDAO

		try {
            UIManager.setLookAndFeel(new FlatLightLaf());
            UIManager.put("Button.arc", 0); // Esquinas redondas
        } catch (Exception ex) {
            ex.printStackTrace();
        }
		initialize();
        loadTipoHabitacionData(); // Cargar los datos del tipo de habitación
	}

    // Constructor sin ID (para compatibilidad si alguna otra parte del código lo llama así)
    public EliminarTipodeHabitacion() {
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

		JPanel panel_2_nav = new JPanel(); //Borde gris (Panel de navegación superior)
		panel_2_nav.setBackground(new Color(55, 54, 48));
		panel_2_nav.setBounds(0, 95, 1164, 26);
		panel.add(panel_2_nav);
		panel_2_nav.setLayout(null);

		JLabel logo = new JLabel(""); //Logo
		logo.setBounds(0, 0, 170, 95);
		ImageIcon portada1 = new ImageIcon(getClass().getResource("/images/logo.png"));
	    Image portada2 = portada1.getImage();
	    Image portada3 = portada2.getScaledInstance(170, 95,Image.SCALE_SMOOTH);
	    logo.setIcon(new ImageIcon(portada3));
		panel_1.add(logo);
		
		Titulo = new JLabel("Eliminar Tipo de Habitación:"); //Titulo - HECHO VARIABLE
		Titulo.setForeground(new Color(255, 255, 255));
		Titulo.setFont(new Font("Jost* Medium", Font.PLAIN, 35));
		Titulo.setBounds(180, 11, 810, 73); // Ajustado para un título más largo
		panel_1.add(Titulo);

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
				TiposHabitacion conexion = new TiposHabitacion(); // Vuelve a TiposHabitacion
				conexion.frame.setVisible(true); 	
			}
		});
		botonVolver.setBounds(60, 132, 50, 50); // Ajustado a 50x50 para icono
		ImageIcon f1 = new ImageIcon(getClass().getResource("/images/flecha_izquierda.png"));
		Image f2 = f1.getImage();
		Image f3 = f2.getScaledInstance(36, 36, Image.SCALE_SMOOTH);
		botonVolver.setIcon(new ImageIcon(f3));
		panel.add(botonVolver);

		// Botones de navegación superior - Se mantienen
		JButton btnTiposDeHabitacionesNav = new JButton("<html>Tipos de habitaciones &#8594;</html>");
		btnTiposDeHabitacionesNav.setFont(new Font("Jost* Medium", Font.PLAIN, 12));
		btnTiposDeHabitacionesNav.setForeground(new Color(255, 255, 255));
		btnTiposDeHabitacionesNav.setBackground(new Color(56, 54, 41));
		btnTiposDeHabitacionesNav.setBorder(BorderFactory.createLineBorder(Color.BLACK,0));
		btnTiposDeHabitacionesNav.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				TiposHabitacion conexion = new TiposHabitacion();
				conexion.frame.setVisible(true);
			}
		});
		btnTiposDeHabitacionesNav.setBounds(1023, 0, 134, 23);
		btnTiposDeHabitacionesNav.setBorderPainted(false);
        btnTiposDeHabitacionesNav.setFocusPainted(false);
        btnTiposDeHabitacionesNav.setContentAreaFilled(true);
		panel_2_nav.add(btnTiposDeHabitacionesNav);
		
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
		panel_2_nav.add(btnrentas);
		
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
		panel_2_nav.add(btnclientes);
		
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
		panel_2_nav.add(btnhabitaciones);
		
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
		panel_2_nav.add(btntarifas);
		
        // Panel para mostrar la información del tipo de habitación a eliminar
        JPanel panel_info_eliminar = new JPanel();
        panel_info_eliminar.setBackground(new Color(152, 193, 217));
        panel_info_eliminar.setBounds(430, 200, 300, 440); // Centrado para un solo panel
        panel.add(panel_info_eliminar);
        panel_info_eliminar.setLayout(null);

        lblImagen1 = new JLabel(); // Imagen del tipo de habitación
        lblImagen1.setBounds(50, 39, 200, 200);
        // La imagen se cargará dinámicamente en loadTipoHabitacionData()
        lblImagen1.setIcon(new ImageIcon(new ImageIcon(getClass().getResource("/images/default_room_type.png")).getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH))); // Placeholder
        panel_info_eliminar.add(lblImagen1);

        lblEstandar = new JLabel("Cargando Tipo..."); // Nombre del tipo de habitación
        lblEstandar.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 20));
        lblEstandar.setHorizontalAlignment(JLabel.CENTER); // Centrar texto
        lblEstandar.setBounds(10, 10, 280, 20);
        panel_info_eliminar.add(lblEstandar);

        JLabel lblHabitacionesTexto = new JLabel("Habitaciones de este tipo:");
        lblHabitacionesTexto.setFont(new Font("Jost*", Font.BOLD | Font.ITALIC, 20));
        lblHabitacionesTexto.setHorizontalAlignment(JLabel.CENTER); // Centrar texto
        lblHabitacionesTexto.setBounds(10, 249, 280, 20);
        panel_info_eliminar.add(lblHabitacionesTexto);
        
        lblTexto1_2 = new JLabel("0"); // Cantidad de habitaciones
        lblTexto1_2.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 30));
        lblTexto1_2.setHorizontalAlignment(JLabel.CENTER); // Centrar texto
        lblTexto1_2.setBounds(10, 289, 280, 29);
        panel_info_eliminar.add(lblTexto1_2);
        
        JButton btnEliminar = new JButton("Eliminar Tipo");
        btnEliminar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Verificar si hay habitaciones asociadas
                List<Habitaciones> habitacionesAsociadas = habitacionDAO.getHabitacionesByTipoId(idTipoHabitacionAEliminar);
                if (habitacionesAsociadas != null && !habitacionesAsociadas.isEmpty()) {
                    JOptionPane.showMessageDialog(
                        null,
                        "No se puede eliminar este tipo de habitación porque tiene habitaciones asociadas. Por favor, elimine o reasigne las habitaciones primero.",
                        "Error de Eliminación",
                        JOptionPane.ERROR_MESSAGE
                    );
                    return; // Salir del método si hay habitaciones asociadas
                }

                int respuesta = JOptionPane.showConfirmDialog(
                        null,
                        "¿Estás seguro de que desea eliminar este tipo de habitación? Esta acción es irreversible.",
                        "Confirmación de Eliminación",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.WARNING_MESSAGE
                );

                if (respuesta == JOptionPane.YES_OPTION) {
                    if (tipoHabitacionDAO.deleteTipoHabitacion(idTipoHabitacionAEliminar)) {
                        JOptionPane.showMessageDialog(
                                null,
                                "Tipo de habitación eliminado exitosamente.",
                                "Información",
                                JOptionPane.INFORMATION_MESSAGE
                        );
                        frame.dispose(); // Cierra la ventana actual
                        TiposHabitacion conexion = new TiposHabitacion(); // Vuelve a la lista de tipos
                        conexion.frame.setVisible(true);
                    } else {
                        JOptionPane.showMessageDialog(
                                null,
                                "Error al eliminar el tipo de habitación. Puede que aún haya datos relacionados.",
                                "Error",
                                JOptionPane.ERROR_MESSAGE
                        );
                    }
                }
            }
        });
        btnEliminar.setForeground(Color.WHITE);
        btnEliminar.setFont(new Font("Tahoma", Font.BOLD, 20));
        btnEliminar.setBackground(new Color(239, 35, 60));
        btnEliminar.setBounds(81, 375, 124, 55);
        panel_info_eliminar.add(btnEliminar);
	}

    /**
     * Carga los datos del tipo de habitación y actualiza la UI.
     */
    private void loadTipoHabitacionData() {
        if (idTipoHabitacionAEliminar == -1) {
            Titulo.setText("Error: ID de Tipo de Habitación inválido.");
            lblEstandar.setText("Tipo no encontrado");
            lblTexto1_2.setText("N/A");
            lblImagen1.setIcon(null); // Quitar imagen si hay error
            return;
        }

        TiposHabitacion tipo = tipoHabitacionDAO.getTipoHabitacionById(idTipoHabitacionAEliminar);

        if (tipo != null) {
            Titulo.setText("Eliminar Tipo de Habitación: " + tipo.getNombreTipo());
            lblEstandar.setText(tipo.getNombreTipo());

            // Contar habitaciones de este tipo
            List<Habitaciones> habitacionesDeEsteTipo = habitacionDAO.getHabitacionesByTipoId(idTipoHabitacionAEliminar);
            lblTexto1_2.setText(String.valueOf(habitacionesDeEsteTipo != null ? habitacionesDeEsteTipo.size() : 0));

            // Establecer imagen (asumiendo que tienes una imagen por cada tipo o una genérica)
            // Aquí puedes poner lógica para cargar la imagen específica del tipo si la tienes.
            // Por ahora, solo usamos la cama_matrimonial2.png si no tienes otra lógica.
            ImageIcon roomTypeIcon = new ImageIcon(getClass().getResource("/images/cama_matrimonial2.png"));
            Image roomTypeImage = roomTypeIcon.getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH);
            lblImagen1.setIcon(new ImageIcon(roomTypeImage));

        } else {
            Titulo.setText("Tipo de Habitación no encontrado.");
            lblEstandar.setText("Tipo no encontrado");
            lblTexto1_2.setText("N/A");
            lblImagen1.setIcon(null); // Quitar imagen si no se encuentra
        }
    }

    public void setVisible(boolean b) {
        frame.setVisible(b);
    }
}
