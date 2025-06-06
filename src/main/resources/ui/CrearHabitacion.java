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
import javax.swing.UIManager;

import com.formdev.flatlaf.FlatLightLaf;

import Dao.TipoHabitacionDAO;
import modelos.TiposHabitacion;

import ui.PanelHabitaciones1;
import ui.Clientes;
import ui.Rentas;
import ui.TiposHabitacion;
import ui.Tarifas;

public class CrearHabitacion {

	public JFrame frame;
	private JTextField textFieldNombreTipo;
	private JTextArea textAreaDescripcion;
	private JTextField textFieldCapacidadMaxima;
	private JTextField textFieldPrecioNoche;

    private TipoHabitacionDAO tipoHabitacionDAO;

	public CrearHabitacion() {
		try {
            UIManager.setLookAndFeel(new FlatLightLaf());
            UIManager.put("Button.arc", 90);
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
        Image portada2 = portada1.getImage();
        Image portada3 = portada2.getScaledInstance(170, 95,Image.SCALE_SMOOTH);
        logo.setIcon(new ImageIcon(portada3));
        panel_1.add(logo);
		
        JLabel Titulo = new JLabel("Crear Tipo de Habitación");
        Titulo.setForeground(new Color(255, 255, 255));
        Titulo.setFont(new Font("Jost* Medium", Font.PLAIN, 35));
        Titulo.setBounds(180, 11, 600, 73);
        panel_1.add(Titulo);
		
		JLabel menuTitulo1 = new JLabel("Crear Tipo de Habitación");
		menuTitulo1.setBackground(new Color(255, 255, 255));
		menuTitulo1.setFont(new Font("Jost*", Font.BOLD, 34));
		menuTitulo1.setBounds(131, 126, 579, 56);
		panel.add(menuTitulo1);
		
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
		
		JButton btntarifas = new JButton("<html>Tarifas &#8594;</html>");
		btntarifas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Tarifas conexion = new Tarifas();
				conexion.frame.setVisible(true);
				frame.dispose();
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
				PanelHabitaciones1 conexion = new PanelHabitaciones1();
				conexion.frame.setVisible(true);
				frame.dispose();
			}
		});
		panel_2.add(btnhabitaciones);
		
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
				Clientes conexion = new Clientes();
				conexion.frame.setVisible(true);
				frame.dispose();
			}
		});
		panel_2.add(btnclientes);
		
		JButton btnrentas = new JButton("<html>Rentas &#8594;</html>");
		btnrentas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Rentas conexion = new Rentas();
				conexion.frame.setVisible(true);
				frame.dispose();
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
		
		JButton btnTiposDeRentas = new JButton("<html>Tipos de habitaciones &#8594;</html>");
		btnTiposDeRentas.setFont(new Font("Jost* Medium", Font.PLAIN, 12));
		btnTiposDeRentas.setForeground(new Color(255, 255, 255));
		btnTiposDeRentas.setBackground(new Color(56, 54, 41));
		btnTiposDeRentas.setBorder(BorderFactory.createLineBorder(Color.BLACK,0));
		btnTiposDeRentas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TiposHabitacion conexion = new TiposHabitacion();
				conexion.frame.setVisible(true);
				frame.dispose();
			}
		});
		btnTiposDeRentas.setBounds(1023, 0, 134, 23);
		btnTiposDeRentas.setBorderPainted(false);
        btnTiposDeRentas.setFocusPainted(false);
        btnTiposDeRentas.setContentAreaFilled(true);
		panel_2.add(btnTiposDeRentas);

		JButton botonVolver = new JButton("");
		botonVolver.setForeground(new Color(255, 255, 255));
		botonVolver.setBackground(new Color(255, 255, 255));
		botonVolver.setBorderPainted(false);
		botonVolver.setFocusPainted(false);
		botonVolver.setContentAreaFilled(true);
		botonVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TiposHabitacion conexion = new TiposHabitacion();
				conexion.frame.setVisible(true);
				frame.dispose();
			}
		});
		botonVolver.setBounds(60, 132, 36, 36);
		ImageIcon icon4 = new ImageIcon(getClass().getResource("/images/flecha_izquierda.png"));
        Image imagen4 = icon4.getImage().getScaledInstance(36, 36, Image.SCALE_SMOOTH);
        botonVolver.setIcon(new ImageIcon(imagen4));
		panel.add(botonVolver);
		
		JLabel lblNewLabel = new JLabel("Nombre:");
		lblNewLabel.setFont(new Font("Jost*", Font.BOLD, 24));
		lblNewLabel.setBounds(141, 193, 250, 40);
		panel.add(lblNewLabel);
		
		textFieldNombreTipo = new JTextField();
		textFieldNombreTipo.setBackground(new Color(192, 192, 192));
		textFieldNombreTipo.setBounds(141, 244, 250, 40);
		panel.add(textFieldNombreTipo);
		textFieldNombreTipo.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Descripción:");
		lblNewLabel_1.setFont(new Font("Jost*", Font.BOLD, 24));
		lblNewLabel_1.setBounds(141, 295, 250, 40);
		panel.add(lblNewLabel_1);
		
		textAreaDescripcion = new JTextArea();
		textAreaDescripcion.setWrapStyleWord(true);
		textAreaDescripcion.setLineWrap(true);
		textAreaDescripcion.setBackground(new Color(192, 192, 192));
		textAreaDescripcion.setBounds(141, 346, 250, 100);
		panel.add(textAreaDescripcion);
		
		JLabel lblNewLabel_2 = new JLabel("Capacidad máxima:");
		lblNewLabel_2.setFont(new Font("Jost*", Font.BOLD, 24));
		lblNewLabel_2.setBounds(470, 193, 350, 40);
		panel.add(lblNewLabel_2);
		
		textFieldCapacidadMaxima = new JTextField();
		textFieldCapacidadMaxima.setBackground(new Color(192, 192, 192));
		textFieldCapacidadMaxima.setBounds(470, 244, 250, 40);
		panel.add(textFieldCapacidadMaxima);
		textFieldCapacidadMaxima.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("Precio por noche:");
		lblNewLabel_3.setFont(new Font("Jost*", Font.BOLD, 24));
		lblNewLabel_3.setBounds(470, 295, 240, 40);
		panel.add(lblNewLabel_3);
		
		textFieldPrecioNoche = new JTextField();
		textFieldPrecioNoche.setBackground(new Color(192, 192, 192));
		textFieldPrecioNoche.setBounds(470, 346, 250, 40);
		panel.add(textFieldPrecioNoche);
		textFieldPrecioNoche.setColumns(10);
		
		JButton btnNewButton = new JButton("Crear Tipo de Habitación");
		btnNewButton.setFont(new Font("Inter", Font.BOLD | Font.ITALIC, 20));
		btnNewButton.setBackground(new Color(255, 214, 10));
		btnNewButton.setBounds(870, 580, 250, 40);
		btnNewButton.setBorder(BorderFactory.createLineBorder(Color.BLACK,0));
        btnNewButton.addActionListener(new ActionListener() {
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
		panel.add(btnNewButton);
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
