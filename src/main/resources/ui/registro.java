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
import javax.swing.JPasswordField;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.UIManager;

import com.formdev.flatlaf.FlatLightLaf;

import Dao.UsuarioDAO;
import modelos.Usuario;
import ui.Menu;
import ui.PantallaInicioORegistro;

public class registro {

    public JFrame frame;
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JPasswordField confirmPasswordField;
    private JLabel messageLabel;
    private UsuarioDAO usuarioDAO;

    public registro() {
        try {
            UIManager.setLookAndFeel(new FlatLightLaf());
            UIManager.put("Button.arc", 0);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        usuarioDAO = new UsuarioDAO();
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
        Image portada3 = portada2.getScaledInstance(170, 95, Image.SCALE_SMOOTH);
        logo.setIcon(new ImageIcon(portada3));
        panel_1.add(logo);
        
        JButton botonVolver = new JButton("");
		botonVolver.setForeground(new Color(255, 255, 255));
		botonVolver.setBackground(new Color(255, 255, 255));
		botonVolver.setBorderPainted(false);
		botonVolver.setFocusPainted(false);
		botonVolver.setContentAreaFilled(true);
		botonVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				PantallaInicioORegistro conexion = new PantallaInicioORegistro();
				conexion.frame.setVisible(true);
			}
		});
		botonVolver.setBounds(60, 132, 50, 50);
		ImageIcon f1 = new ImageIcon(getClass().getResource("/images/flecha_izquierda.png"));
		Image f2 = f1.getImage();
		Image f3 = f2.getScaledInstance(36, 36, Image.SCALE_SMOOTH);
		botonVolver.setIcon(new ImageIcon(f3));
		panel.add(botonVolver);

        JLabel Titulo = new JLabel("Registro / Inicio de Sesión");
        Titulo.setForeground(new Color(255, 255, 255));
        Titulo.setFont(new Font("Jost* Medium", Font.PLAIN, 35));
        Titulo.setBounds(180, 11, 450, 73);
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

        JPanel loginPanel = new JPanel();
        loginPanel.setLayout(null);
        loginPanel.setBackground(new Color(255, 255, 255));
        loginPanel.setBounds(380, 205, 400, 470);

        JLabel loginTitle = new JLabel("Registro");
        loginTitle.setFont(new Font("Dialog", Font.BOLD, 32));
        loginTitle.setHorizontalAlignment(SwingConstants.CENTER);
        loginTitle.setBounds(0, 10, 400, 40);
        loginPanel.add(loginTitle);

        JLabel userLabel = new JLabel("Usuario:");
        userLabel.setFont(new Font("Jost*", Font.PLAIN, 18));
        userLabel.setBounds(50, 80, 100, 30);
        loginPanel.add(userLabel);

        usernameField = new JTextField();
        usernameField.setBounds(50, 115, 300, 35);
        usernameField.setFont(new Font("Jost*", Font.PLAIN, 16));
        loginPanel.add(usernameField);

        JLabel passwordLabel = new JLabel("Contraseña:");
        passwordLabel.setFont(new Font("Jost*", Font.PLAIN, 18));
        passwordLabel.setBounds(50, 160, 150, 30);
        loginPanel.add(passwordLabel);

        passwordField = new JPasswordField();
        passwordField.setBounds(50, 195, 300, 35);
        passwordField.setFont(new Font("Jost*", Font.PLAIN, 16));
        loginPanel.add(passwordField);

        JLabel confirmPasswordLabel = new JLabel("Confirmar contraseña:");
        confirmPasswordLabel.setFont(new Font("Jost*", Font.PLAIN, 18));
        confirmPasswordLabel.setBounds(50, 240, 200, 30);
        loginPanel.add(confirmPasswordLabel);

        confirmPasswordField = new JPasswordField();
        confirmPasswordField.setBounds(50, 275, 300, 35);
        confirmPasswordField.setFont(new Font("Jost*", Font.PLAIN, 16));
        loginPanel.add(confirmPasswordField);

        JButton doLoginButton = new JButton("Ingresar");
        doLoginButton.setBackground(new Color(255, 214, 10));
        doLoginButton.setForeground(new Color(0, 0, 0));
        doLoginButton.setFont(new Font("Jost*", Font.BOLD, 20));
        doLoginButton.setBounds(127, 360, 150, 45);
        doLoginButton.setFocusPainted(false);
        doLoginButton.setBorderPainted(false);
        doLoginButton.setContentAreaFilled(true);
        doLoginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                performLogin();
            }
        });
        loginPanel.add(doLoginButton);

        JButton doRegisterButton = new JButton("Registrarse");
        doRegisterButton.setBackground(new Color(10, 150, 255));
        doRegisterButton.setForeground(new Color(255, 255, 255));
        doRegisterButton.setFont(new Font("Jost*", Font.BOLD, 20));
        doRegisterButton.setBounds(127, 415, 150, 45);
        doRegisterButton.setFocusPainted(false);
        doRegisterButton.setBorderPainted(false);
        doRegisterButton.setContentAreaFilled(true);
        doRegisterButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                performRegistration();
            }
        });
        loginPanel.add(doRegisterButton);

        messageLabel = new JLabel("");
        messageLabel.setForeground(Color.RED);
        messageLabel.setFont(new Font("Jost*", Font.PLAIN, 14));
        messageLabel.setHorizontalAlignment(SwingConstants.CENTER);
        messageLabel.setBounds(0, 320, 400, 20);
        loginPanel.add(messageLabel);

        panel.add(loginPanel);
    }

    private void performLogin() {
        String username = usernameField.getText();
        String password = new String(passwordField.getPassword());

        if (username.isEmpty() || password.isEmpty()) {
            messageLabel.setForeground(Color.RED);
            messageLabel.setText("Por favor, ingresa usuario y contraseña.");
            return;
        }

        Usuario authenticatedUser = usuarioDAO.login(username, password);

        if (authenticatedUser != null) {
            messageLabel.setForeground(new Color(0, 150, 0));
            messageLabel.setText("¡Login exitoso!");
            JOptionPane.showMessageDialog(frame, "Bienvenido, " + authenticatedUser.getNombreUsuario() + "!", "Bienvenido", JOptionPane.INFORMATION_MESSAGE);

            frame.dispose();
            Menu menuWindow = new Menu();
            menuWindow.frame.setVisible(true);

        } else {
            messageLabel.setForeground(Color.RED);
            messageLabel.setText("Credenciales incorrectas. Intenta de nuevo.");
            JOptionPane.showMessageDialog(frame, "Credenciales incorrectas.", "Error de Login", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void performRegistration() {
        String username = usernameField.getText();
        String password = new String(passwordField.getPassword());
        String confirmPassword = new String(confirmPasswordField.getPassword());

        if (username.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
            messageLabel.setForeground(Color.RED);
            messageLabel.setText("Por favor, completa todos los campos para registrarte.");
            return;
        }

        if (!password.equals(confirmPassword)) {
            messageLabel.setForeground(Color.RED);
            messageLabel.setText("Las contraseñas no coinciden.");
            return;
        }

        try {
            boolean registrado = usuarioDAO.registrarUsuario(username, password);

            if (registrado) {
                messageLabel.setForeground(new Color(0, 150, 0));
                messageLabel.setText("¡Registro exitoso! Ya puedes iniciar sesión.");
                JOptionPane.showMessageDialog(frame, "Usuario " + username + " registrado exitosamente.", "Registro Exitoso", JOptionPane.INFORMATION_MESSAGE);
                usernameField.setText("");
                passwordField.setText("");
                confirmPasswordField.setText("");
            } else {
                messageLabel.setForeground(Color.RED);
                messageLabel.setText("Error al registrar. El usuario podría ya existir.");
                JOptionPane.showMessageDialog(frame, "No se pudo registrar el usuario. Intenta con otro nombre de usuario.", "Error de Registro", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception e) {
            messageLabel.setForeground(Color.RED);
            messageLabel.setText("Ocurrió un error al intentar registrar el usuario.");
            JOptionPane.showMessageDialog(frame, "Error interno: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }

    public void setVisible(boolean b) {
        frame.setVisible(b);
    }
}
