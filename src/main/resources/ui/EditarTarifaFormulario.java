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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JComboBox; // Para la selección de tipo de habitación
import javax.swing.UIManager;

import com.formdev.flatlaf.FlatLightLaf;

import Dao.TarifaDAO;
import Dao.TipoHabitacionDAO;
import modelos.Tarifa;
import modelos.TiposHabitacion;

import ui.TiposHabitacion;
import ui.Rentas;
import ui.Clientes;
import ui.PanelHabitaciones1;
import ui.Tarifas;
import ui.Menu;

public class EditarTarifaFormulario {

	public JFrame frame;
	private JTextField textFieldIDTarifa;
	private JTextField textFieldNombreTarifa;
	private JTextField textFieldPrecioBase;
	private JTextField textFieldCondiciones;
	private JTextField textFieldDescripcion;
    private JComboBox<String> comboBoxTipoHabitacion;
    
    private TarifaDAO tarifaDAO;
    private TipoHabitacionDAO tipoHabitacionDAO;
    private Tarifa tarifaAEditar; // La tarifa que estamos editando

	public EditarTarifaFormulario(int idTarifa) {
        // Llamar al constructor sin argumentos para inicializar componentes GUI y DAOs
        this(); 
        
        tarifaAEditar = tarifaDAO.getTarifaById(idTarifa);

        if (tarifaAEditar != null) {
            textFieldIDTarifa.setText(String.valueOf(tarifaAEditar.getIdTarifa()));
            textFieldIDTarifa.setEditable(false); // El ID no debe ser editable

            // Cargar tipos de habitación en el ComboBox y luego seleccionar el correcto
            cargarTiposHabitacion(); 
            String nombreTipoHabitacionActual = tipoHabitacionDAO.getNombreTipoHabitacionById(tarifaAEditar.getIdTipoHabitacion());
            if (nombreTipoHabitacionActual != null) {
                comboBoxTipoHabitacion.setSelectedItem(nombreTipoHabitacionActual);
            } else {
                comboBoxTipoHabitacion.setSelectedIndex(-1); // Ninguna selección si no se encuentra
            }

            textFieldNombreTarifa.setText(tarifaAEditar.getNombreTarifa());
            textFieldPrecioBase.setText(String.valueOf(tarifaAEditar.getPrecioBase()));
            textFieldCondiciones.setText(tarifaAEditar.getCondiciones());
            textFieldDescripcion.setText(tarifaAEditar.getDescripcion());
        } else {
            JOptionPane.showMessageDialog(frame, "Tarifa no encontrada para editar.", "Error", JOptionPane.ERROR_MESSAGE);
            frame.dispose();
            Tarifas tarifasWindow = new Tarifas(); // Vuelve a la pantalla de tarifas
            tarifasWindow.frame.setVisible(true);
        }
    }

	public EditarTarifaFormulario() {
        try {
            UIManager.setLookAndFeel(new FlatLightLaf());
            UIManager.put("Button.arc", 90);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        tarifaDAO = new TarifaDAO();
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

		JLabel Titulo = new JLabel("Panel de Tarifas:");
		Titulo.setForeground(new Color(255, 255, 255));
		Titulo.setFont(new Font("Jost* Medium", Font.PLAIN, 35));
		Titulo.setBounds(180, 11, 410, 73);
		panel_1.add(Titulo);

		JLabel menuTitulo = new JLabel("Editar tarifa:");
		menuTitulo.setFont(new Font("Jost*", Font.BOLD, 38));
		menuTitulo.setBounds(131, 126, 441, 56);
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
		botonVolver.setForeground(new Color(255, 255, 255));
		botonVolver.setBackground(new Color(255, 255, 255));
		botonVolver.setBorderPainted(false);
		botonVolver.setFocusPainted(false);
		botonVolver.setContentAreaFilled(true);
		botonVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
                Tarifas conexion = new Tarifas(); // Volver a la pantalla de Tarifas
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
		
		JPanel panel_3 = new JPanel();
		panel_3.setBackground(new Color(0, 187, 249));
		panel_3.setBounds(131, 193, 300, 440);
		panel.add(panel_3);
		panel_3.setLayout(null);

		JLabel lblUserIcon = new JLabel("");
		lblUserIcon.setBounds(45, 11, 200, 200);
		ImageIcon iconUser = new ImageIcon(getClass().getResource("/Images/usuario.png"));
        Image imagenUser = iconUser.getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH);
        lblUserIcon.setIcon(new ImageIcon(imagenUser));
		panel_3.add(lblUserIcon);

		JLabel lblNewLabel_1 = new JLabel("Editar Tarifa");
		lblNewLabel_1.setFont(new Font("Jost*", Font.BOLD | Font.ITALIC, 20));
		lblNewLabel_1.setBounds(90, 222, 150, 20);
		panel_3.add(lblNewLabel_1);

		JLabel textoIDTarifa = new JLabel("ID Tarifa:");
		textoIDTarifa.setFont(new Font("Jost*", Font.BOLD | Font.ITALIC, 16));
		textoIDTarifa.setBounds(120, 272, 145, 20);
		panel_3.add(textoIDTarifa);

        textFieldIDTarifa = new JTextField();
        textFieldIDTarifa.setBackground(Color.LIGHT_GRAY);
        textFieldIDTarifa.setBounds(441, 240, 250, 35);
        panel.add(textFieldIDTarifa);
        textFieldIDTarifa.setColumns(10);
		
		JLabel lblTipoHabitacion = new JLabel("Tipo Habitación:");
		lblTipoHabitacion.setFont(new Font("Dialog", Font.BOLD, 24));
		lblTipoHabitacion.setBounds(441, 200, 200, 30);
		panel.add(lblTipoHabitacion);
        
        comboBoxTipoHabitacion = new JComboBox<>();
        comboBoxTipoHabitacion.setBounds(700, 240, 250, 35); // Posicionado a la derecha
        panel.add(comboBoxTipoHabitacion);

		JLabel lblNombreTarifa = new JLabel("Nombre Tarifa:");
		lblNombreTarifa.setFont(new Font("Dialog", Font.BOLD, 24));
		lblNombreTarifa.setBounds(441, 300, 200, 30);
		panel.add(lblNombreTarifa);
		
		textFieldNombreTarifa = new JTextField();
		textFieldNombreTarifa.setBackground(Color.LIGHT_GRAY);
		textFieldNombreTarifa.setBounds(441, 340, 250, 35);
		panel.add(textFieldNombreTarifa);
		textFieldNombreTarifa.setColumns(10);
		
		JLabel lblPrecioBase = new JLabel("Precio Base:");
		lblPrecioBase.setFont(new Font("Dialog", Font.BOLD, 24));
		lblPrecioBase.setBounds(700, 300, 200, 30);
		panel.add(lblPrecioBase);
		
		textFieldPrecioBase = new JTextField();
		textFieldPrecioBase.setBackground(Color.LIGHT_GRAY);
		textFieldPrecioBase.setBounds(700, 340, 250, 35);
		panel.add(textFieldPrecioBase);
		textFieldPrecioBase.setColumns(10);
		
		JLabel lblCondiciones = new JLabel("Condiciones:");
		lblCondiciones.setFont(new Font("Dialog", Font.BOLD, 24));
		lblCondiciones.setBounds(441, 400, 250, 30);
		panel.add(lblCondiciones);
		
		textFieldCondiciones = new JTextField();
		textFieldCondiciones.setBackground(Color.LIGHT_GRAY);
		textFieldCondiciones.setBounds(441, 440, 250, 35);
		panel.add(textFieldCondiciones);
		textFieldCondiciones.setColumns(10);

        JLabel lblDescripcion = new JLabel("Descripción:");
        lblDescripcion.setFont(new Font("Dialog", Font.BOLD, 24));
        lblDescripcion.setBounds(700, 400, 200, 30);
        panel.add(lblDescripcion);

        textFieldDescripcion = new JTextField();
        textFieldDescripcion.setBackground(Color.LIGHT_GRAY);
        textFieldDescripcion.setBounds(700, 440, 250, 35);
        panel.add(textFieldDescripcion);
        textFieldDescripcion.setColumns(10);

		JButton btnGuardarCambios = new JButton("Guardar cambios");
		btnGuardarCambios.setBackground(new Color(255, 214, 10));
		btnGuardarCambios.setFont(new Font("Inter", Font.BOLD | Font.ITALIC, 24));
		btnGuardarCambios.setBorder(BorderFactory.createLineBorder(Color.BLACK,0));
		btnGuardarCambios.setBounds(856, 573, 270, 60);
        btnGuardarCambios.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (tarifaAEditar == null) {
                    JOptionPane.showMessageDialog(frame, "No hay tarifa seleccionada para editar.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                String nombreTarifa = textFieldNombreTarifa.getText().trim();
                String precioBaseStr = textFieldPrecioBase.getText().trim();
                String condiciones = textFieldCondiciones.getText().trim();
                String descripcion = textFieldDescripcion.getText().trim();
                String tipoHabitacionNombre = (String) comboBoxTipoHabitacion.getSelectedItem();

                if (nombreTarifa.isEmpty() || precioBaseStr.isEmpty() || condiciones.isEmpty() || descripcion.isEmpty() || tipoHabitacionNombre == null || tipoHabitacionNombre.isEmpty()) {
                    JOptionPane.showMessageDialog(frame, "Todos los campos son obligatorios.", "Error de Validación", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                double precioBase;
                try {
                    precioBase = Double.parseDouble(precioBaseStr);
                    if (precioBase <= 0) {
                        JOptionPane.showMessageDialog(frame, "El precio base debe ser un número positivo.", "Error de Validación", JOptionPane.WARNING_MESSAGE);
                        return;
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(frame, "El precio base debe ser un número válido.", "Error de Validación", JOptionPane.WARNING_MESSAGE);
                    return;
                }
                
                int idTipoHabitacion = tipoHabitacionDAO.getIdTipoHabitacionByNombre(tipoHabitacionNombre);
                if (idTipoHabitacion == -1) {
                    JOptionPane.showMessageDialog(frame, "Tipo de habitación seleccionado no es válido.", "Error de Validación", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                tarifaAEditar.setNombreTarifa(nombreTarifa);
                tarifaAEditar.setPrecioBase(precioBase);
                tarifaAEditar.setCondiciones(condiciones);
                tarifaAEditar.setDescripcion(descripcion);
                tarifaAEditar.setIdTipoHabitacion(idTipoHabitacion);

                if (tarifaDAO.updateTarifa(tarifaAEditar)) {
                    JOptionPane.showMessageDialog(frame, "Tarifa actualizada exitosamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                    frame.dispose();
                    Tarifas pantallaTarifas = new Tarifas();
                    pantallaTarifas.setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(frame, "Error al actualizar la tarifa. El nombre de tarifa podría ya existir o hay un problema en la base de datos.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
		panel.add(btnGuardarCambios);
	}

    private void cargarTiposHabitacion() {
        List<TiposHabitacion> tipos = tipoHabitacionDAO.getAllTiposHabitacion();
        comboBoxTipoHabitacion.removeAllItems();
        if (tipos != null) {
            for (TiposHabitacion tipo : tipos) {
                comboBoxTipoHabitacion.addItem(tipo.getNombreTipo());
            }
        }
    }

    public void setVisible(boolean b) {
        if (frame != null) {
            frame.setVisible(b);
        }
    }
}
