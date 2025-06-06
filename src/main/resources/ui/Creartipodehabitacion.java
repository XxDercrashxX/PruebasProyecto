package ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.JComboBox;
import javax.swing.UIManager;

import com.formdev.flatlaf.FlatLightLaf;

import Dao.TipoHabitacionDAO;
import modelos.TiposHabitacion;

import ui.TiposHabitacion;
import ui.Rentas;
import ui.Clientes;
import ui.PanelHabitaciones1;
import ui.Tarifas;
import ui.Menu;

public class Creartipodehabitacion {

	public JFrame frame;
	private JTextField textFieldNombreTipo;
	private JTextArea textAreaDescripcion;
	private JTextField textFieldCapacidadMaxima;
	private JTextField textFieldPrecioNoche;

	private TipoHabitacionDAO tipoHabitacionDAO;

	public Creartipodehabitacion() {
		try {
            UIManager.setLookAndFeel(new FlatLightLaf());
            UIManager.put("Button.arc", 0);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        tipoHabitacionDAO = new TipoHabitacionDAO();
		initialize();
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
		btnclientes.setForeground(Color.WHITE);
		btnclientes.setFont(new Font("Jost* Medium", Font.PLAIN, 12));
		btnclientes.setFocusPainted(false);
		btnclientes.setContentAreaFilled(true);
		btnclientes.setBorderPainted(false);
		btnclientes.setBorder(BorderFactory.createLineBorder(Color.BLACK,0));
		btnclientes.setBackground(new Color(56, 54, 41));
		btnclientes.setBounds(841, 0, 81, 23);
		btnclientes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				Clientes conexion = new Clientes();
				conexion.frame.setVisible(true);
			}
		});
		panel_2.add(btnclientes);
		
		JButton btnhabitaciones = new JButton("<html>Habitaciones &#8594;</html>");
		btnhabitaciones.setForeground(Color.WHITE);
		btnhabitaciones.setFont(new Font("Jost* Medium", Font.PLAIN, 12));
		btnhabitaciones.setFocusPainted(false);
		btnhabitaciones.setContentAreaFilled(true);
		btnhabitaciones.setBorderPainted(false);
		btnhabitaciones.setBorder(BorderFactory.createLineBorder(Color.BLACK,0));
		btnhabitaciones.setBackground(new Color(56, 54, 41));
		btnhabitaciones.setBounds(731, 0, 100, 23);
		btnhabitaciones.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				PanelHabitaciones1 conexion = new PanelHabitaciones1();
				conexion.frame.setVisible(true);
			}
		});
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

		JLabel logoPanel = new JLabel("");
		logoPanel.setBounds(0, 0, 170, 95);
		ImageIcon icon2 = new ImageIcon(getClass().getResource("/images/logo.png"));
        Image imagen2 = icon2.getImage().getScaledInstance(170, 95, Image.SCALE_SMOOTH);
        logoPanel.setIcon(new ImageIcon(imagen2));
		panel_1.add(logoPanel);
		
		JLabel lblTarifas = new JLabel("Tipos de habitacion");
		lblTarifas.setForeground(new Color(255, 255, 255));
		lblTarifas.setFont(new Font("Dialog", Font.BOLD, 36));
		lblTarifas.setBounds(141, 31, 483, 33);
		panel_1.add(lblTarifas);
		
		JButton botonVolver = new JButton("");
		botonVolver.setForeground(new Color(255, 255, 255));
		botonVolver.setBackground(new Color(255, 255, 255));
		botonVolver.setBorderPainted(false);
		botonVolver.setFocusPainted(false);
		botonVolver.setContentAreaFilled(true);
		botonVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				TiposHabitacion conexion = new TiposHabitacion();
				conexion.frame.setVisible(true);
			}
		});
		botonVolver.setBounds(59, 131, 50, 50);
		ImageIcon icon42 = new ImageIcon(getClass().getResource("/images/flecha_izquierda.png"));
        Image imagen42 = icon42.getImage().getScaledInstance(36, 36, Image.SCALE_SMOOTH);
		botonVolver.setIcon(new ImageIcon(imagen42));
		panel.add(botonVolver);
		
		JLabel lblEditarTarifas = new JLabel("Crear Tipo de Habitación:");
		lblEditarTarifas.setForeground(Color.BLACK);
		lblEditarTarifas.setFont(new Font("Dialog", Font.BOLD, 30));
		lblEditarTarifas.setBounds(108, 143, 400, 33);
		panel.add(lblEditarTarifas);
		
		JLabel lblNombre = new JLabel("Nombre:");
		lblNombre.setForeground(Color.BLACK);
		lblNombre.setFont(new Font("Dialog", Font.BOLD, 30));
		lblNombre.setBounds(108, 223, 289, 33);
		panel.add(lblNombre);
		
		textFieldNombreTipo = new JTextField();
		textFieldNombreTipo.setFont(new Font("Tahoma", Font.BOLD, 20));
		textFieldNombreTipo.setBounds(108, 266, 289, 47);
		panel.add(textFieldNombreTipo);
		textFieldNombreTipo.setColumns(10);
		
		JLabel lblDescripcion = new JLabel("Descripcion:");
		lblDescripcion.setForeground(Color.BLACK);
		lblDescripcion.setFont(new Font("Dialog", Font.BOLD, 30));
		lblDescripcion.setBounds(108, 323, 289, 33);
		panel.add(lblDescripcion);
		
		textAreaDescripcion = new JTextArea();
		textAreaDescripcion.setLineWrap(true);
		textAreaDescripcion.setWrapStyleWord(true);
		textAreaDescripcion.setFont(new Font("Tahoma", Font.BOLD, 20));
		JScrollPane scrollPaneDescripcion = new JScrollPane(textAreaDescripcion);
		scrollPaneDescripcion.setBounds(108, 366, 289, 211);
		panel.add(scrollPaneDescripcion);
		
		JLabel lblCapacidadMaxima = new JLabel("Capacidad máxima:");
		lblCapacidadMaxima.setForeground(Color.BLACK);
		lblCapacidadMaxima.setFont(new Font("Dialog", Font.BOLD, 30));
		lblCapacidadMaxima.setBounds(440, 223, 333, 33);
		panel.add(lblCapacidadMaxima);
		
		textFieldCapacidadMaxima = new JTextField();
		textFieldCapacidadMaxima.setFont(new Font("Tahoma", Font.BOLD, 20));
		textFieldCapacidadMaxima.setBounds(440, 266, 310, 50);
		panel.add(textFieldCapacidadMaxima);
		textFieldCapacidadMaxima.setColumns(10);

		JLabel lblPrecioNoche = new JLabel("Precio por noche:");
		lblPrecioNoche.setForeground(Color.BLACK);
		lblPrecioNoche.setFont(new Font("Dialog", Font.BOLD, 30));
		lblPrecioNoche.setBounds(440, 323, 333, 33);
		panel.add(lblPrecioNoche);

		textFieldPrecioNoche = new JTextField();
		textFieldPrecioNoche.setFont(new Font("Tahoma", Font.BOLD, 20));
		textFieldPrecioNoche.setBounds(440, 366, 310, 50);
		panel.add(textFieldPrecioNoche);
		textFieldPrecioNoche.setColumns(10);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setBackground(Color.RED);
		btnCancelar.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 24));
		btnCancelar.setForeground(new Color(255, 255, 255));
		btnCancelar.setBounds(923, 468, 214, 80);
        btnCancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                TiposHabitacion tiposHabitacionWindow = new TiposHabitacion();
                tiposHabitacionWindow.frame.setVisible(true);
            }
        });
		panel.add(btnCancelar);
		
		JButton btnGuardarCambios = new JButton("Crear Tipo de Habitación");
		btnGuardarCambios.setForeground(Color.WHITE);
		btnGuardarCambios.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 20));
		btnGuardarCambios.setBackground(new Color(255, 214, 10));
		btnGuardarCambios.setBounds(923, 558, 214, 84);
        btnGuardarCambios.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nombre = textFieldNombreTipo.getText().trim();
                String descripcion = textAreaDescripcion.getText().trim();
                String capacidadStr = textFieldCapacidadMaxima.getText().trim();
                String precioStr = textFieldPrecioNoche.getText().trim();

                if (nombre.isEmpty() || descripcion.isEmpty() || capacidadStr.isEmpty() || precioStr.isEmpty()) {
                    JOptionPane.showMessageDialog(frame, "Todos los campos son obligatorios.", "Error de Validación", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                try {
                    int capacidadMaxima = Integer.parseInt(capacidadStr);
                    double precioNoche = Double.parseDouble(precioStr);

                    TiposHabitacion nuevoTipo = new TiposHabitacion(0, nombre, descripcion, capacidadMaxima, precioNoche);
                    
                    if (tipoHabitacionDAO.createTipoHabitacion(nuevoTipo)) {
                        JOptionPane.showMessageDialog(frame, "Tipo de habitación creado exitosamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                        textFieldNombreTipo.setText("");
                        textAreaDescripcion.setText("");
                        textFieldCapacidadMaxima.setText("");
                        textFieldPrecioNoche.setText("");
                        frame.dispose();
                        TiposHabitacion tiposHabitacionWindow = new TiposHabitacion();
                        tiposHabitacionWindow.frame.setVisible(true);
                    } else {
                        JOptionPane.showMessageDialog(frame, "Error al crear el tipo de habitación. Puede que el nombre ya exista.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(frame, "Capacidad máxima y Precio por noche deben ser números válidos.", "Error de Formato", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
		panel.add(btnGuardarCambios);
	}

    public void setVisible(boolean b) {
        if (frame != null) {
            frame.setVisible(b);
        }
    }

    public JFrame getFrame() {
        return frame;
    }
}
