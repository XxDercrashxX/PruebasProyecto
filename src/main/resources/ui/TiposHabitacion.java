// ui/TiposHabitacion.java
package ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.JScrollPane;
import java.awt.FlowLayout;

import com.formdev.flatlaf.FlatLightLaf; 

import Dao.TipoHabitacionDAO; // Importar el DAO para TipoHabitacion
// import modelos.TiposHabitacion; // ¡¡¡ESTA LÍNEA SE HA ELIMINADO PARA RESOLVER LA COLISIÓN!!!
import ui.Menu;
import ui.Rentas;
import ui.Clientes;
import ui.PanelHabitaciones1; // Asumiendo que esta es la primera vista de habitaciones
import ui.Tarifas;
import ui.DetallesHabitacion4; // Asumiendo que esta clase existe y puede recibir un ID
import ui.Detallesfamiliar; // Asumiendo que esta clase existe
// Importación corregida para Editar y Eliminar: usan el nombre EXACTO de tu clase
import ui.EditarTipoDeHabitacion; // Clase de edición
import ui.EliminarTipodeHabitacion; // Clase de eliminación
import ui.Creartipodehabitacion; // Asumiendo que esta clase existe

public class TiposHabitacion {

	public JFrame frame;
    private TipoHabitacionDAO tipoHabitacionDAO;
    private JPanel tiposHabitacionDisplayPanel;

	public TiposHabitacion() {
		try {
            UIManager.setLookAndFeel(new FlatLightLaf());
            UIManager.put("Button.arc", 0);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        tipoHabitacionDAO = new TipoHabitacionDAO();
		initialize();
        loadTiposHabitacion();
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
		if (portada1.getImageLoadStatus() == java.awt.MediaTracker.ERRORED) {
			System.err.println("Error al cargar la imagen: /images/logo.png");
		}
	    Image portada2 = portada1.getImage();
	    Image portada3 = portada2.getScaledInstance(170, 95,Image.SCALE_SMOOTH);
	    logo.setIcon(new ImageIcon(portada3));
		panel_1.add(logo);
		
		JLabel Titulo = new JLabel("Tipos de habitaciones:");
		Titulo.setForeground(new Color(255, 255, 255));
		Titulo.setFont(new Font("Jost* Medium", Font.PLAIN, 35));
		Titulo.setBounds(180, 11, 410, 73);
		panel_1.add(Titulo);

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
				Menu conexion = new Menu();
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
                // Ya estamos en esta ventana, no hacer nada o refrescar
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


        // Botones de acción principales (Crear, Editar, Eliminar tipos de habitación)
		JButton btnEliminarTipo = new JButton("Eliminar");
		btnEliminarTipo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				EliminarTipodeHabitacion conexion = new EliminarTipodeHabitacion();
				conexion.frame.setVisible(true);
			}
		});
		btnEliminarTipo.setForeground(Color.WHITE);
		btnEliminarTipo.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnEliminarTipo.setBackground(new Color(239, 35, 60));
		btnEliminarTipo.setBounds(131, 131, 124, 55);
		panel.add(btnEliminarTipo);

		JButton btnEditarTipo = new JButton("Editar\r\n");
		btnEditarTipo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				// Se llama al constructor sin argumentos de la clase EditarTipoDeHabitacion
                // La pantalla de edición deberá tener su propia lógica para seleccionar o buscar el tipo de habitación.
				EditarTipoDeHabitacion conexion = new EditarTipoDeHabitacion();
				conexion.frame.setVisible(true);
			}
		});
		btnEditarTipo.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnEditarTipo.setBackground(new Color(44, 196, 196));
		btnEditarTipo.setBounds(265, 128, 124, 55);
		panel.add(btnEditarTipo);

		JButton btnCrearTipo = new JButton("Crear tipo");
		btnCrearTipo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				Creartipodehabitacion conexion = new Creartipodehabitacion();
				conexion.frame.setVisible(true);
			}
		});
		btnCrearTipo.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnCrearTipo.setBackground(Color.YELLOW);
		btnCrearTipo.setBounds(399, 127, 154, 55);
		panel.add(btnCrearTipo);


        // Panel para mostrar los tipos de habitación dinámicamente
        tiposHabitacionDisplayPanel = new JPanel();
        tiposHabitacionDisplayPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 20, 20));
        tiposHabitacionDisplayPanel.setBackground(new Color(255, 255, 255));

        JScrollPane scrollPane = new JScrollPane(tiposHabitacionDisplayPanel);
        scrollPane.setBounds(131, 193 + 55 + 20, 980, 440);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        panel.add(scrollPane);
	}

    private void loadTiposHabitacion() {
        tiposHabitacionDisplayPanel.removeAll();

        List<modelos.TiposHabitacion> tipos = tipoHabitacionDAO.getAllTiposHabitacion();

        ImageIcon defaultIcon = new ImageIcon(getClass().getResource("/images/foto_default.png"));
		if (defaultIcon.getImageLoadStatus() == java.awt.MediaTracker.ERRORED) {
			System.err.println("Error al cargar la imagen por defecto: /images/foto_default.png. Asegúrate de que la ruta es correcta.");
		}
        Image defaultImage = defaultIcon.getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH);
        ImageIcon scaledDefaultIcon = new ImageIcon(defaultImage);

        if (tipos != null && !tipos.isEmpty()) {
            for (modelos.TiposHabitacion tipo : tipos) { // ¡ATENCIÓN! Aquí se usa el nombre completo
                JPanel typePanel = new JPanel();
                typePanel.setLayout(null);
                typePanel.setBackground(new Color(152, 193, 217));
                typePanel.setPreferredSize(new java.awt.Dimension(300, 440));

                JLabel lblNombreTipo = new JLabel(tipo.getNombreTipo());
                lblNombreTipo.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 20));
                lblNombreTipo.setBounds(10, 10, 280, 25);
                typePanel.add(lblNombreTipo);

                JLabel lblDescripcion = new JLabel("<html>" + tipo.getDescripcion() + "</html>");
                lblDescripcion.setFont(new Font("Dialog", Font.PLAIN, 14));
                lblDescripcion.setBounds(10, 40, 280, 100);
                typePanel.add(lblDescripcion);

                JLabel lblImagen = new JLabel(scaledDefaultIcon);
                lblImagen.setBounds(50, 150, 200, 200);
                typePanel.add(lblImagen);


                // Botones de acción individuales para cada tipo de habitación
                JButton btnDetalles = new JButton("Detalles");
                btnDetalles.setBackground(new Color(255, 230, 167));
                btnDetalles.setFont(new Font("Tahoma", Font.BOLD, 20));
                btnDetalles.setBounds(10, 375, 90, 55);
                final int tipoId = tipo.getIdTipoHabitacion();
                btnDetalles.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        frame.dispose();
                        DetallesHabitacion4 detallesWindow = new DetallesHabitacion4(tipoId);
                        detallesWindow.frame.setVisible(true);
                    }
                });
                typePanel.add(btnDetalles);

                JButton btnEditar = new JButton("Editar");
                btnEditar.setBackground(new Color(44, 196, 196));
                btnEditar.setFont(new Font("Tahoma", Font.BOLD, 20));
                btnEditar.setBounds(105, 375, 90, 55);
                btnEditar.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        frame.dispose();
                        // Se llama al constructor sin argumentos de la clase EditarTipoDeHabitacion
                        // NOTA: Esta clase (EditarTipoDeHabitacion) no tiene un constructor que acepte un ID.
                        // La pantalla de edición deberá tener su propia lógica para seleccionar o buscar el tipo de habitación.
                        EditarTipoDeHabitacion editarWindow = new EditarTipoDeHabitacion();
                        editarWindow.frame.setVisible(true);
                    }
                });
                typePanel.add(btnEditar);

                JButton btnEliminar = new JButton("Eliminar");
                btnEliminar.setBackground(new Color(239, 35, 60));
                btnEliminar.setForeground(Color.WHITE);
                btnEliminar.setFont(new Font("Tahoma", Font.BOLD, 20));
                btnEliminar.setBounds(200, 375, 90, 55);
                btnEliminar.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        frame.dispose();
                        // Se llama al constructor sin argumentos de la clase EliminarTipodeHabitacion
                        // NOTA: Esta clase (EliminarTipodeHabitacion) no tiene un constructor que acepte un ID.
                        // La pantalla de eliminación deberá tener su propia lógica para seleccionar o buscar el tipo de habitación.
                        EliminarTipodeHabitacion eliminarWindow = new EliminarTipodeHabitacion();
                        eliminarWindow.frame.setVisible(true);
                    }
                });
                typePanel.add(btnEliminar);

                tiposHabitacionDisplayPanel.add(typePanel);
            }
        } else {
            JLabel noTypesLabel = new JLabel("No se encontraron tipos de habitación.");
            noTypesLabel.setFont(new Font("Jost*", Font.BOLD, 20));
            noTypesLabel.setForeground(Color.GRAY);
            noTypesLabel.setHorizontalAlignment(JLabel.CENTER);
            tiposHabitacionDisplayPanel.add(noTypesLabel);
        }

        tiposHabitacionDisplayPanel.revalidate();
        tiposHabitacionDisplayPanel.repaint();
    }

	public void setVisible(boolean b) {
		frame.setVisible(b);
	}
}
