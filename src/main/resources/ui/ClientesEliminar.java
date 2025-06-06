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

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;

import com.formdev.flatlaf.FlatLightLaf;

import Dao.ClienteDAO;

public class ClientesEliminar {

	private JTextField textField;
	public JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UIManager.setLookAndFeel(new FlatLightLaf());
					UIManager.put("Button.arc", 90);

					ClientesEliminar window = new ClientesEliminar();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public ClientesEliminar() {
		
		try {
            UIManager.setLookAndFeel(new FlatLightLaf());
            UIManager.put("Button.arc", 90); // Esquinas redondas
            UIManager.put("Button.arc", 90);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
		
		initialize();
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
		
		JLabel logo = new JLabel("");
		logo.setBounds(0, 0, 170, 95);
		
		ImageIcon icon13 = new ImageIcon(getClass().getResource("/images/logo.png"));
        Image imagen13 = icon13.getImage().getScaledInstance(170, 95, Image.SCALE_SMOOTH);
        logo.setIcon(new ImageIcon(imagen13));   
		panel_1.add(logo);
		
		JLabel Titulo = new JLabel("Clientes\r\n");
		Titulo.setForeground(new Color(255, 255, 255));
		Titulo.setFont(new Font("Jost* Medium", Font.PLAIN, 35));
		Titulo.setBounds(180, 11, 410, 73);
		panel_1.add(Titulo);
		
		JLabel menuTitulo = new JLabel("Clientes:");
		menuTitulo.setFont(new Font("Jost*", Font.BOLD, 38));
		menuTitulo.setBounds(131, 126, 245, 56);
		panel.add(menuTitulo);
		ImageIcon c2 = new ImageIcon(getClass().getResource("/images/usuario.png"));
        Image c1 = c2.getImage().getScaledInstance(36, 36, Image.SCALE_SMOOTH);
		
		ImageIcon icon14 = new ImageIcon(getClass().getResource("/images/informacion.png"));
        Image imagen14 = icon14.getImage().getScaledInstance(36, 36, Image.SCALE_SMOOTH);
		
		JButton botonVolver = new JButton("");
		botonVolver.setForeground(new Color(255, 255, 255));
		botonVolver.setBackground(new Color(255, 255, 255));
		botonVolver.setBorderPainted(false);
		botonVolver.setFocusPainted(false);
		botonVolver.setContentAreaFilled(true);
		botonVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				Clientes conexion = new Clientes();
				conexion.frame.setVisible(true); 	
			}
		});
		botonVolver.setBounds(60, 132, 50, 50);	
		ImageIcon icon15 = new ImageIcon(getClass().getResource("/images/flecha_izquierda.png"));
        Image imagen15 = icon15.getImage().getScaledInstance(36, 36, Image.SCALE_SMOOTH);
        botonVolver.setIcon(new ImageIcon(imagen15));
		panel.add(botonVolver);
		
		JButton btnTiposDeRentas = new JButton("<html>Tipos de habitaciones &#8594;</html>");
		btnTiposDeRentas.setFont(new Font("Jost* Medium", Font.PLAIN, 12));
		btnTiposDeRentas.setForeground(new Color(255, 255, 255));
		btnTiposDeRentas.setBackground(new Color(56, 54, 41));
		btnTiposDeRentas.setBorder(BorderFactory.createLineBorder(Color.BLACK,0));
		btnTiposDeRentas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				TiposHabitacion tiposHabitacionWindow = new TiposHabitacion();
				tiposHabitacionWindow.frame.setVisible(true);
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
				Rentas rentasWindow = new Rentas();
				rentasWindow.frame.setVisible(true);
			}
		});
		btnrentas.setForeground(Color.WHITE);
		btnrentas.setFont(new Font("Jost* Medium", Font.PLAIN, 12));
		btnrentas.setFocusPainted(false);
		btnrentas.setBorderPainted(false);
		btnrentas.setContentAreaFilled(true);
		btnrentas.setBorder(BorderFactory.createLineBorder(Color.BLACK,0));
		btnrentas.setBackground(new Color(56, 54, 41));
		btnrentas.setBounds(932, 0, 81, 23);
		panel_2.add(btnrentas);
		
		JButton btnclientes = new JButton("<html>Clientes &#8594;</html>");
		btnclientes.setForeground(Color.WHITE);
		btnclientes.setFont(new Font("Jost* Medium", Font.PLAIN, 12));
		btnclientes.setFocusPainted(false);
		btnclientes.setBorderPainted(false);
		btnclientes.setContentAreaFilled(true);
		btnclientes.setBorder(BorderFactory.createLineBorder(Color.BLACK,0));
		btnclientes.setBackground(new Color(56, 54, 41));
		btnclientes.setBounds(841, 0, 81, 23);
		panel_2.add(btnclientes);
		
		JButton btnhabitaciones = new JButton("<html>Habitaciones &#8594;</html>");
		btnhabitaciones.setForeground(Color.WHITE);
		btnhabitaciones.setFont(new Font("Jost* Medium", Font.PLAIN, 12));
		btnhabitaciones.setFocusPainted(false);
		btnhabitaciones.setBorderPainted(false);
		btnhabitaciones.setContentAreaFilled(true);
		btnhabitaciones.setBorder(BorderFactory.createLineBorder(Color.BLACK,0));
		btnhabitaciones.setBackground(new Color(56, 54, 41));
		btnhabitaciones.setBounds(731, 0, 100, 23);
		panel_2.add(btnhabitaciones);
		
		JButton btntarifas = new JButton("<html>Tarifas &#8594;</html>");
		btntarifas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				Tarifas tarifasWindow = new Tarifas();
				tarifasWindow.frame.setVisible(true);
			}
		});
		btntarifas.setForeground(Color.WHITE);
		btntarifas.setFont(new Font("Jost* Medium", Font.PLAIN, 12));
		btntarifas.setFocusPainted(false);
		btntarifas.setBorderPainted(false);
		btntarifas.setContentAreaFilled(true);
		btntarifas.setBorder(BorderFactory.createLineBorder(Color.BLACK,0));
		btntarifas.setBackground(new Color(56, 54, 41));
		btntarifas.setBounds(649, 0, 72, 23);
		panel_2.add(btntarifas);
		
		JButton btnBuscar = new JButton("");
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnBuscar.setBounds(720, 140, 40, 40);	
		ImageIcon icon12 = new ImageIcon(getClass().getResource("/images/busqueda.png"));
        Image imagen12 = icon12.getImage().getScaledInstance(36, 36, Image.SCALE_SMOOTH);
        btnBuscar.setIcon(new ImageIcon(imagen12));
		btnBuscar.setBorderPainted(false);
		btnBuscar.setFocusPainted(false);
		btnBuscar.setContentAreaFilled(true);
		panel.add(btnBuscar);
		
		textField = new JTextField("BUSCAR");
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
		
		JButton btnEliminarCliente = new JButton("Eliminar Cliente");
		btnEliminarCliente.setBackground(new Color(239, 35, 60));
		btnEliminarCliente.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 16));
		btnEliminarCliente.setBorder(BorderFactory.createLineBorder(Color.BLACK,0));
		btnEliminarCliente.setForeground(Color.WHITE);
		btnEliminarCliente.setFocusPainted(false);
		btnEliminarCliente.setContentAreaFilled(true);
		btnEliminarCliente.setBorderPainted(false);
		btnEliminarCliente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnEliminarCliente.setBounds(131, 417, 150, 26);
		panel.add(btnEliminarCliente);
		
		JButton btnCliente1 = new JButton("Usuario#123");
		btnCliente1.setFont(new Font("Jost*", Font.BOLD | Font.ITALIC, 14));
		btnCliente1.setBackground(new Color(0, 175, 185));
		btnCliente1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnCliente1.setBounds(131, 257, 150, 150);
		btnCliente1.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnCliente1.setHorizontalTextPosition(SwingConstants.CENTER);
		btnCliente1.setHorizontalAlignment(SwingConstants.CENTER);
		btnCliente1.setVerticalAlignment(SwingConstants.CENTER);
		btnCliente1.setIconTextGap(1);
		btnCliente1.setFocusPainted(false);
		btnCliente1.setContentAreaFilled(true);
		btnCliente1.setBorderPainted(false);
		btnCliente1.setBorder(BorderFactory.createLineBorder(Color.BLACK,0));
		ImageIcon icon1 = new ImageIcon(getClass().getResource("/images/usuario.png"));
        Image imagen1 = icon1.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
        btnCliente1.setIcon(new ImageIcon(imagen1));
		panel.add(btnCliente1);
		
		JButton btnCliente6 = new JButton("Usuario#123");
		btnCliente6.setFont(new Font("Jost*", Font.BOLD | Font.ITALIC, 14));
		btnCliente6.setBorder(BorderFactory.createLineBorder(Color.BLACK,0));
		btnCliente6.setBackground(new Color(0, 175, 185));
		btnCliente6.setBounds(131, 453, 150, 150);
		btnCliente6.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnCliente6.setHorizontalTextPosition(SwingConstants.CENTER);
		btnCliente6.setHorizontalAlignment(SwingConstants.CENTER);
		btnCliente6.setVerticalAlignment(SwingConstants.CENTER);
		btnCliente6.setIconTextGap(1);
		btnCliente6.setFocusPainted(false);
		btnCliente6.setContentAreaFilled(true);
		btnCliente6.setBorderPainted(false);
		
		ImageIcon icon2= new ImageIcon(getClass().getResource("/images/usuario.png"));
        Image imagen2= icon1.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
        btnCliente6.setIcon(new ImageIcon(imagen1));
		panel.add(btnCliente6);
		
		JButton btnCliente2 = new JButton("Usuario#123");
		btnCliente2.setFont(new Font("Jost*", Font.BOLD | Font.ITALIC, 14));
		btnCliente2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnCliente2.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnCliente2.setVerticalAlignment(SwingConstants.CENTER);
		btnCliente2.setIconTextGap(1);
		btnCliente2.setFocusPainted(false);
		btnCliente2.setContentAreaFilled(true);
		btnCliente2.setBorderPainted(false);
		btnCliente2.setHorizontalTextPosition(SwingConstants.CENTER);
		btnCliente2.setHorizontalAlignment(SwingConstants.CENTER);
		btnCliente2.setBorder(BorderFactory.createLineBorder(Color.BLACK,0));
		btnCliente2.setBackground(new Color(0, 175, 185));
		btnCliente2.setBounds(331, 257, 150, 150);
		ImageIcon icon3= new ImageIcon(getClass().getResource("/images/usuario.png"));
        Image imagen3= icon1.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
        btnCliente2.setIcon(new ImageIcon(imagen3));
		panel.add(btnCliente2);
		
		JButton btnCliente7 = new JButton("Usuario#123");
		btnCliente7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnCliente7.setFont(new Font("Jost*", Font.BOLD | Font.ITALIC, 14));
		btnCliente7.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnCliente7.setVerticalAlignment(SwingConstants.CENTER);
		btnCliente7.setIconTextGap(1);
		btnCliente7.setFocusPainted(false);
		btnCliente7.setContentAreaFilled(true);
		btnCliente7.setBorderPainted(false);
		btnCliente7.setHorizontalTextPosition(SwingConstants.CENTER);
		btnCliente7.setHorizontalAlignment(SwingConstants.CENTER);
		btnCliente7.setBorder(BorderFactory.createLineBorder(Color.BLACK,0));
		btnCliente7.setBackground(new Color(0, 175, 185));
		btnCliente7.setBounds(331, 453, 150, 150);
		ImageIcon icon4= new ImageIcon(getClass().getResource("/images/usuario.png"));
        Image imagen4= icon1.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
        btnCliente7.setIcon(new ImageIcon(imagen4));
		panel.add(btnCliente7);
		
		JButton btnCliente3 = new JButton("Usuario#123");
		btnCliente3.setFont(new Font("Jost*", Font.BOLD | Font.ITALIC, 14));
		btnCliente3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnCliente3.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnCliente3.setVerticalAlignment(SwingConstants.CENTER);
		btnCliente3.setIconTextGap(1);
		btnCliente3.setFocusPainted(false);
		btnCliente3.setContentAreaFilled(true);
		btnCliente3.setBorderPainted(false);
		btnCliente3.setHorizontalTextPosition(SwingConstants.CENTER);
		btnCliente3.setHorizontalAlignment(SwingConstants.CENTER);
		btnCliente3.setBorder(BorderFactory.createLineBorder(Color.BLACK,0));
		btnCliente3.setBackground(new Color(0, 175, 185));
		btnCliente3.setBounds(531, 257, 150, 150);
		ImageIcon icon5 = new ImageIcon(getClass().getResource("/images/usuario.png"));
        Image imagen5 = icon1.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
        btnCliente3.setIcon(new ImageIcon(imagen3));
		panel.add(btnCliente3);
		
		JButton btnCliente4 = new JButton("Usuario#123");
		btnCliente4.setFont(new Font("Jost*", Font.BOLD | Font.ITALIC, 14));
		btnCliente4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnCliente4.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnCliente4.setVerticalAlignment(SwingConstants.CENTER);
		btnCliente4.setIconTextGap(1);
		btnCliente4.setFocusPainted(false);
		btnCliente4.setContentAreaFilled(true);
		btnCliente4.setBorderPainted(false);
		btnCliente4.setHorizontalTextPosition(SwingConstants.CENTER);
		btnCliente4.setHorizontalAlignment(SwingConstants.CENTER);
		btnCliente4.setBorder(BorderFactory.createLineBorder(Color.BLACK,0));
		btnCliente4.setBackground(new Color(0, 175, 185));
		btnCliente4.setBounds(731, 257, 150, 150);
		ImageIcon icon6= new ImageIcon(getClass().getResource("/images/usuario.png"));
        Image imagen6= icon1.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
        btnCliente4.setIcon(new ImageIcon(imagen6));
		panel.add(btnCliente4);
		
		JButton btnCliente5 = new JButton("Usuario#123");
		btnCliente5.setFont(new Font("Jost*", Font.BOLD | Font.ITALIC, 14));
		btnCliente5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnCliente5.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnCliente5.setVerticalAlignment(SwingConstants.CENTER);
		btnCliente5.setIconTextGap(1);
		btnCliente5.setFocusPainted(false);
		btnCliente5.setContentAreaFilled(true);
		btnCliente5.setBorderPainted(false);
		btnCliente5.setHorizontalTextPosition(SwingConstants.CENTER);
		btnCliente5.setHorizontalAlignment(SwingConstants.CENTER);
		btnCliente5.setBorder(BorderFactory.createLineBorder(Color.BLACK,0));
		btnCliente5.setBackground(new Color(0, 175, 185));
		btnCliente5.setBounds(931, 257, 150, 150);
		ImageIcon icon7= new ImageIcon(getClass().getResource("/images/usuario.png"));
        Image imagen7= icon1.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
        btnCliente5.setIcon(new ImageIcon(imagen7));
		panel.add(btnCliente5);
		
		JButton btnCliente8 = new JButton("Usuario#123");
		btnCliente8.setFont(new Font("Jost*", Font.BOLD | Font.ITALIC, 14));
		btnCliente8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnCliente8.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnCliente8.setVerticalAlignment(SwingConstants.CENTER);
		btnCliente8.setIconTextGap(1);
		btnCliente8.setFocusPainted(false);
		btnCliente8.setContentAreaFilled(true);
		btnCliente8.setBorderPainted(false);
		btnCliente8.setHorizontalTextPosition(SwingConstants.CENTER);
		btnCliente8.setHorizontalAlignment(SwingConstants.CENTER);
		btnCliente8.setBorder(BorderFactory.createLineBorder(Color.BLACK,0));
		btnCliente8.setBackground(new Color(0, 175, 185));
		btnCliente8.setBounds(531, 453, 150, 150);
		ImageIcon icon8= new ImageIcon(getClass().getResource("/images/usuario.png"));
        Image imagen8 = icon1.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
        btnCliente8.setIcon(new ImageIcon(imagen8));
		panel.add(btnCliente8);
		
		JButton btnCliente9 = new JButton("Usuario#123");
		btnCliente9.setFont(new Font("Jost*", Font.BOLD | Font.ITALIC, 14));
		btnCliente9.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnCliente9.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnCliente9.setVerticalAlignment(SwingConstants.CENTER);
		btnCliente9.setIconTextGap(1);
		btnCliente9.setFocusPainted(false);
		btnCliente9.setContentAreaFilled(true);
		btnCliente9.setBorderPainted(false);
		btnCliente9.setHorizontalTextPosition(SwingConstants.CENTER);
		btnCliente9.setHorizontalAlignment(SwingConstants.CENTER);
		btnCliente9.setBorder(BorderFactory.createLineBorder(Color.BLACK,0));
		btnCliente9.setBackground(new Color(0, 175, 185));
		btnCliente9.setBounds(731, 453, 150, 150);
		ImageIcon icon10= new ImageIcon(getClass().getResource("/images/usuario.png"));
        Image imagen10 = icon1.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
        btnCliente9.setIcon(new ImageIcon(imagen10));
		panel.add(btnCliente9);
		
		JButton btnCliente10 = new JButton("Usuario#123");
		btnCliente10.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnCliente10.setFont(new Font("Jost*", Font.BOLD | Font.ITALIC, 14));
		btnCliente10.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnCliente10.setVerticalAlignment(SwingConstants.CENTER);
		btnCliente10.setIconTextGap(1);
		btnCliente10.setFocusPainted(false);
		btnCliente10.setContentAreaFilled(true);
		btnCliente10.setBorderPainted(false);
		btnCliente10.setHorizontalTextPosition(SwingConstants.CENTER);
		btnCliente10.setHorizontalAlignment(SwingConstants.CENTER);
		btnCliente10.setBorder(BorderFactory.createLineBorder(Color.BLACK,0));
		btnCliente10.setBackground(new Color(0, 175, 185));
		btnCliente10.setBounds(931, 453, 150, 150);
		ImageIcon icon11= new ImageIcon(getClass().getResource("/images/usuario.png"));
        Image imagen11= icon1.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
        btnCliente10.setIcon(new ImageIcon(imagen10));
		panel.add(btnCliente10);
		
		JButton btnEliminarCliente2 = new JButton("Eliminar Cliente");
		btnEliminarCliente2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnEliminarCliente2.setForeground(Color.WHITE);
		btnEliminarCliente2.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 16));
		btnEliminarCliente2.setBorder(BorderFactory.createLineBorder(Color.BLACK,0));
		btnEliminarCliente2.setBackground(new Color(239, 35, 60));
		btnEliminarCliente2.setBounds(331, 417, 150, 26);
		btnEliminarCliente2.setFocusPainted(false);
		btnEliminarCliente2.setContentAreaFilled(true);
		btnEliminarCliente2.setBorderPainted(false);
		panel.add(btnEliminarCliente2);
		
		JButton btnEliminarCliente3 = new JButton("Eliminar Cliente");
		btnEliminarCliente3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnEliminarCliente3.setForeground(Color.WHITE);
		btnEliminarCliente3.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 16));
		btnEliminarCliente3.setBorder(BorderFactory.createLineBorder(Color.BLACK,0));
		btnEliminarCliente3.setBackground(new Color(239, 35, 60));
		btnEliminarCliente3.setBounds(531, 417, 150, 26);
		btnEliminarCliente3.setFocusPainted(false);
		btnEliminarCliente3.setContentAreaFilled(true);
		btnEliminarCliente3.setBorderPainted(false);
		panel.add(btnEliminarCliente3);
		
		JButton btnEliminarCliente4 = new JButton("Eliminar Cliente");
		btnEliminarCliente4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnEliminarCliente4.setForeground(Color.WHITE);
		btnEliminarCliente4.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 16));
		btnEliminarCliente4.setBorder(BorderFactory.createLineBorder(Color.BLACK,0));
		btnEliminarCliente4.setBackground(new Color(239, 35, 60));
		btnEliminarCliente4.setBounds(731, 417, 150, 26);
		btnEliminarCliente4.setFocusPainted(false);
		btnEliminarCliente4.setContentAreaFilled(true);
		btnEliminarCliente4.setBorderPainted(false);
		panel.add(btnEliminarCliente4);
		
		JButton btnEliminarCliente5 = new JButton("Eliminar Cliente");
		btnEliminarCliente5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnEliminarCliente5.setForeground(Color.WHITE);
		btnEliminarCliente5.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 16));
		btnEliminarCliente5.setBorder(BorderFactory.createLineBorder(Color.BLACK,0));
		btnEliminarCliente5.setBackground(new Color(239, 35, 60));
		btnEliminarCliente5.setBounds(931, 417, 150, 26);
		btnEliminarCliente5.setFocusPainted(false);
		btnEliminarCliente5.setContentAreaFilled(true);
		btnEliminarCliente5.setBorderPainted(false);
		panel.add(btnEliminarCliente5);
		
		JButton btnEliminarCliente6 = new JButton("Eliminar Cliente");
		btnEliminarCliente6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnEliminarCliente6.setForeground(Color.WHITE);
		btnEliminarCliente6.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 16));
		btnEliminarCliente6.setBorder(BorderFactory.createLineBorder(Color.BLACK,0));
		btnEliminarCliente6.setBackground(new Color(239, 35, 60));
		btnEliminarCliente6.setBounds(131, 613, 150, 26);
		btnEliminarCliente6.setFocusPainted(false);
		btnEliminarCliente6.setContentAreaFilled(true);
		btnEliminarCliente6.setBorderPainted(false);
		panel.add(btnEliminarCliente6);
		
		JButton btnEliminarCliente7 = new JButton("Eliminar Cliente");
		btnEliminarCliente7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnEliminarCliente7.setForeground(Color.WHITE);
		btnEliminarCliente7.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 16));
		btnEliminarCliente7.setBorder(BorderFactory.createLineBorder(Color.BLACK,0));
		btnEliminarCliente7.setBackground(new Color(239, 35, 60));
		btnEliminarCliente7.setBounds(331, 613, 150, 26);
		btnEliminarCliente7.setFocusPainted(false);
		btnEliminarCliente7.setContentAreaFilled(true);
		btnEliminarCliente7.setBorderPainted(false);
		panel.add(btnEliminarCliente7);
		
		JButton btnEliminarCliente8 = new JButton("Eliminar Cliente");
		btnEliminarCliente8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnEliminarCliente8.setForeground(Color.WHITE);
		btnEliminarCliente8.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 16));
		btnEliminarCliente8.setBorder(BorderFactory.createLineBorder(Color.BLACK,0));
		btnEliminarCliente8.setBackground(new Color(239, 35, 60));
		btnEliminarCliente8.setBounds(531, 613, 150, 26);
		btnEliminarCliente8.setFocusPainted(false);
		btnEliminarCliente8.setContentAreaFilled(true);
		btnEliminarCliente8.setBorderPainted(false);
		panel.add(btnEliminarCliente8);
		
		JButton btnEliminarCliente9 = new JButton("Eliminar Cliente");
		btnEliminarCliente9.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnEliminarCliente9.setForeground(Color.WHITE);
		btnEliminarCliente9.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 16));
		btnEliminarCliente9.setBorder(BorderFactory.createLineBorder(Color.BLACK,0));
		btnEliminarCliente9.setBackground(new Color(239, 35, 60));
		btnEliminarCliente9.setBounds(731, 613, 150, 26);
		btnEliminarCliente9.setFocusPainted(false);
		btnEliminarCliente9.setContentAreaFilled(true);
		btnEliminarCliente9.setBorderPainted(false);
		panel.add(btnEliminarCliente9);
		
		JButton btnEliminarCliente10 = new JButton("Eliminar Cliente");
		btnEliminarCliente10.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnEliminarCliente10.setForeground(Color.WHITE);
		btnEliminarCliente10.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 16));
		btnEliminarCliente10.setBorder(BorderFactory.createLineBorder(Color.BLACK,0));
		btnEliminarCliente10.setBackground(new Color(239, 35, 60));
		btnEliminarCliente10.setBounds(931, 613, 150, 26);
		btnEliminarCliente10.setFocusPainted(false);
		btnEliminarCliente10.setContentAreaFilled(true);
		btnEliminarCliente10.setBorderPainted(false);
		panel.add(btnEliminarCliente10);
		
		JButton btnPrincipalEliminarCliente = new JButton("Eliminar Cliente");
		btnPrincipalEliminarCliente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnPrincipalEliminarCliente.setForeground(Color.WHITE);
		btnPrincipalEliminarCliente.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 24));
		btnPrincipalEliminarCliente.setBorder(BorderFactory.createLineBorder(Color.BLACK,0));
		btnPrincipalEliminarCliente.setBackground(new Color(239, 35, 60));
		btnPrincipalEliminarCliente.setBounds(131, 192, 216, 40);
		btnPrincipalEliminarCliente.setFocusPainted(false);
		btnPrincipalEliminarCliente.setContentAreaFilled(true);
		btnPrincipalEliminarCliente.setBorderPainted(false);
		panel.add(btnPrincipalEliminarCliente);
	}
}