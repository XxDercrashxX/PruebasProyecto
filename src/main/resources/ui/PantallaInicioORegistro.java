package ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue; // Se mantiene por si se quiere un main temporal para pruebas, pero lo eliminamos de la versión final.
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.AbstractButton;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;

import com.formdev.flatlaf.FlatLightLaf;

import Dao.UsuarioDAO;
import modelos.Usuario;

public class PantallaInicioORegistro {

	public JFrame frame; // Hago el frame public para que pueda ser visible desde otras clases
	private UsuarioDAO usuarioDAO;

	/**
	 * Create the application.
	 */
	public PantallaInicioORegistro() {
		try {
            UIManager.setLookAndFeel(new FlatLightLaf());
            UIManager.put("Button.arc", 90);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        usuarioDAO = new UsuarioDAO(); // Inicializar el DAO de usuario
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

		JPanel panel_1 = new JPanel(); // Borde negro superior
		panel_1.setBackground(new Color(0, 0, 0));
		panel_1.setBounds(0, 0, 1164, 95);
		panel.add(panel_1);
		panel_1.setLayout(null);

		JPanel panel_2 = new JPanel(); // Borde gris debajo del negro
		panel_2.setBackground(new Color(55, 54, 48));
		panel_2.setBounds(0, 95, 1164, 26);
		panel.add(panel_2);
        panel_2.setLayout(null); // Asegurarse de que tenga un layout

		JLabel logo = new JLabel(""); // Logo
		logo.setBounds(0, 0, 170, 95);
		ImageIcon icon01 = new ImageIcon(getClass().getResource("/images/logo.png"));
        Image imagen01 = icon01.getImage().getScaledInstance(170, 95, Image.SCALE_SMOOTH);
        logo.setIcon(new ImageIcon(imagen01));
		panel_1.add(logo);

		JLabel Titulo = new JLabel("Registro/Iniciar sesión"); // Titulo
		Titulo.setForeground(new Color(255, 255, 255));
		Titulo.setFont(new Font("Jost* Medium", Font.PLAIN, 35));
		Titulo.setBounds(180, 11, 521, 73);
		panel_1.add(Titulo);
		
		JButton Iniciar_sesion = new JButton(""); // Se quitó el texto y se usará solo el icono
		Iniciar_sesion.setBackground(new Color(255, 214, 10));
		Iniciar_sesion.setBounds(87, 240, 350, 300); // Ajustado para el tamaño del botón
		ImageIcon iconLogin = new ImageIcon(getClass().getResource("/images/usuario.png"));
        Image imagenLogin = iconLogin.getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH); // Escalar icono
        Iniciar_sesion.setIcon(new ImageIcon(imagenLogin));
		Iniciar_sesion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose(); // Cierra la ventana actual
				Login2 conexion = new Login2();
				conexion.frame.setVisible(true);
			}
		});
		panel.add(Iniciar_sesion);
		
		JButton Registro = new JButton(""); // Se quitó el texto y se usará solo el icono
		Registro.setBackground(new Color(0, 0, 0));
		Registro.setBounds(729, 240, 350, 300); // Ajustado para el tamaño del botón
		ImageIcon iconRegistro = new ImageIcon(getClass().getResource("/images/usuario.png"));
        Image imagenRegistro = iconRegistro.getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH); // Escalar icono
        Registro.setIcon(new ImageIcon(imagenRegistro));
		Registro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose(); // Cierra la ventana actual
				registro conexion = new registro(); // Navega a la pantalla de registro
				conexion.frame.setVisible(true);
			}
		});
		panel.add(Registro);
		
		JLabel lblNewLabel = new JLabel("Iniciar sesión\r\n");
		lblNewLabel.setFont(new Font("Dialog", Font.BOLD, 25));
		lblNewLabel.setBounds(176, 549, 200, 56);
		panel.add(lblNewLabel);
		
		JLabel lblRegistrarse = new JLabel("Registrarse\r\n");
		lblRegistrarse.setFont(new Font("Dialog", Font.BOLD, 25));
		lblRegistrarse.setBounds(839, 550, 209, 56);
		panel.add(lblRegistrarse);
	}

    public void setVisible(boolean b) {
        frame.setVisible(b);
    }
}
