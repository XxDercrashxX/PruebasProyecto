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
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.FlowLayout;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JScrollPane;
import javax.swing.UIManager; // Necesario para UIManager.setLookAndFeel

import com.formdev.flatlaf.FlatLightLaf;

import Dao.RentaDAO;
import Dao.ReservaDAO;
import Dao.HabitacionDAO;
import Dao.ClienteDAO;
import Dao.ServicioDAO;
import Dao.ServiciosPorReservaDAO;
import Dao.TipoHabitacionDAO;

import modelos.Rentas; // Confirmado: es 'Rentas' (plural)
import modelos.TiposHabitacion; // Confirmado: es 'TiposHabitacion' para el objeto de habitacion
import modelos.Cliente;
import modelos.Servicios;

// Importaciones de clases de UI adicionales que se usan en esta clase
import ui.Menu;
import ui.Clientes;
import ui.PanelHabitaciones1;
import ui.Tarifas;
import ui.TiposHabitacion; // Esta es la clase UI de TiposHabitacion
import ui.CrearRenta;     // Añadido: Se requiere para compilar los botones
import ui.EditarRenta;    // Añadido: Se requiere para compilar los botones
import ui.EliminarRenta; // Asumo que esta sí existe, ya que no fue mencionada como faltante
import ui.DetallesHabitacion4;


public class Rentas {

    public JFrame frame;
    private JTextField textFieldBusqueda;
    private JPanel rentasDisplayPanel;

    private RentaDAO rentaDAO;
    private HabitacionDAO habitacionDAO;
    private ClienteDAO clienteDAO;
    private ServicioDAO servicioDAO;
    private ServiciosPorReservaDAO serviciosPorReservaDAO;
    private TipoHabitacionDAO tipoHabitacionDAO;

    private JTextField textFieldCostoInicial;
    private JTextField textFieldCantidadPersonas;
    private JLabel lblHabitacionesUsadas;
    private JLabel lblCostoTotal;
    private JTextField textFieldFechaCheckIn;
    private JTextField textFieldFechaCheckOut;
    private JLabel lblDiasHospedaje;
    private JComboBox<String> comboBoxServicios;


    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Rentas window = new Rentas();
                    window.frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public Rentas() {
        try {
            UIManager.setLookAndFeel(new FlatLightLaf());
            UIManager.put("Button.arc", 0);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        rentaDAO = new RentaDAO();
        habitacionDAO = new HabitacionDAO();
        clienteDAO = new ClienteDAO();
        servicioDAO = new ServicioDAO();
        serviciosPorReservaDAO = new ServiciosPorReservaDAO();
        tipoHabitacionDAO = new TipoHabitacionDAO();

        initialize();
        loadRentas();
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

        JLabel Titulo = new JLabel("Rentas:");
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
        btnTiposDeRentas.setBorder(BorderFactory.createLineBorder(Color.BLACK, 0));
        btnTiposDeRentas.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                ui.TiposHabitacion conexion = new ui.TiposHabitacion(); // Usando la clase UI TiposHabitacion
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
                // Ya estamos en esta ventana, no hacer nada o refrescar
            }
        });
        btnrentas.setForeground(Color.WHITE);
        btnrentas.setFont(new Font("Jost* Medium", Font.PLAIN, 12));
        btnrentas.setFocusPainted(false);
        btnrentas.setContentAreaFilled(true);
        btnrentas.setBorderPainted(false);
        btnrentas.setBorder(BorderFactory.createLineBorder(Color.BLACK, 0));
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
        btnclientes.setBorder(BorderFactory.createLineBorder(Color.BLACK, 0));
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
        btnhabitaciones.setBorder(BorderFactory.createLineBorder(Color.BLACK, 0));
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
        btntarifas.setBorder(BorderFactory.createLineBorder(Color.BLACK, 0));
        btntarifas.setBackground(new Color(56, 54, 41));
        btntarifas.setBounds(649, 0, 72, 23);
        panel_2.add(btntarifas);

        textFieldBusqueda = new JTextField();
        textFieldBusqueda.setBounds(791, 142, 200, 30);
        panel.add(textFieldBusqueda);
        textFieldBusqueda.setColumns(10);

        JButton btnBuscar = new JButton("Buscar");
        btnBuscar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                loadRentas();
            }
        });
        btnBuscar.setBounds(1001, 142, 89, 30);
        panel.add(btnBuscar);

        JButton btnCrearRenta = new JButton("Crear");
        btnCrearRenta.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                CrearRenta conexion = new CrearRenta(); // Llama al placeholder CrearRenta
                conexion.frame.setVisible(true);
            }
        });
        btnCrearRenta.setFont(new Font("Tahoma", Font.BOLD, 20));
        btnCrearRenta.setBackground(Color.YELLOW);
        btnCrearRenta.setBounds(131, 131, 124, 55);
        panel.add(btnCrearRenta);

        rentasDisplayPanel = new JPanel();
        rentasDisplayPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 20, 20));
        rentasDisplayPanel.setBackground(new Color(255, 255, 255));

        JScrollPane scrollPane = new JScrollPane(rentasDisplayPanel);
        scrollPane.setBounds(131, 193, 980, 440);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        panel.add(scrollPane);

        textFieldCostoInicial = new JTextField();
        textFieldCantidadPersonas = new JTextField();
        lblHabitacionesUsadas = new JLabel("Habitaciones usadas: 1");
        lblCostoTotal = new JLabel("Costo total: $0.00");
        textFieldFechaCheckIn = new JTextField();
        textFieldFechaCheckOut = new JTextField();
        lblDiasHospedaje = new JLabel("Días de hospedaje: 0 días");
        comboBoxServicios = new JComboBox<>();

        textFieldFechaCheckOut.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                updateDaysOfStayLabel();
            }
        });

        populateServiciosComboBox();
    }

    private void loadRentas() {
        rentasDisplayPanel.removeAll();
        List<Rentas> rentas = rentaDAO.getAllRentas(); // Usando 'Rentas' (plural)

        ImageIcon defaultIcon = new ImageIcon(getClass().getResource("/images/habitacion.png"));
        if (defaultIcon.getImageLoadStatus() == java.awt.MediaTracker.ERRORED) {
            System.err.println("Error al cargar la imagen por defecto: /images/habitacion.png. Asegúrate de que la ruta es correcta.");
        }
        Image defaultImage = defaultIcon.getImage();
        if (defaultImage != null) {
            defaultImage = defaultImage.getScaledInstance(200, 200, Image.SCALE_SMOOTH);
        }
        ImageIcon scaledDefaultIcon = new ImageIcon(defaultImage);

        if (rentas != null && !rentas.isEmpty()) {
            for (Rentas renta : rentas) { // Usando 'Rentas' (plural)
                JPanel rentaPanel = new JPanel();
                rentaPanel.setLayout(null);
                rentaPanel.setBackground(new Color(152, 193, 217));
                rentaPanel.setPreferredSize(new java.awt.Dimension(300, 440));

                JLabel lblIdRenta = new JLabel("ID Renta: " + renta.getIdRenta());
                lblIdRenta.setFont(new Font("Dialog", Font.BOLD, 16));
                lblIdRenta.setBounds(10, 10, 280, 20);
                rentaPanel.add(lblIdRenta);

                // Usando 'TiposHabitacion' para el objeto de habitación
                TiposHabitacion habitacion = habitacionDAO.getHabitacionById(renta.getIdHabitacion());
                JLabel lblNumeroHabitacion = new JLabel("Habitación: " + (habitacion != null ? habitacion.getNumeroHabitacion() : "N/A"));
                lblNumeroHabitacion.setFont(new Font("Dialog", Font.PLAIN, 14));
                lblNumeroHabitacion.setBounds(10, 35, 280, 20);
                rentaPanel.add(lblNumeroHabitacion);

                Cliente cliente = clienteDAO.getClienteById(renta.getIdCliente());
                JLabel lblCliente = new JLabel("Cliente: " + (cliente != null ? cliente.getNombre() + " " + cliente.getApellido() : "N/A"));
                lblCliente.setFont(new Font("Dialog", Font.PLAIN, 14));
                lblCliente.setBounds(10, 60, 280, 20);
                rentaPanel.add(lblCliente);

                JLabel lblCheckIn = new JLabel("Check-In: " + (renta.getFechaCheckIn() != null ? renta.getFechaCheckIn().toLocalDate().toString() : "N/A"));
                lblCheckIn.setFont(new Font("Dialog", Font.PLAIN, 14));
                lblCheckIn.setBounds(10, 85, 280, 20);
                rentaPanel.add(lblCheckIn);

                String checkOutDate = (renta.getFechaCheckOut() != null ? renta.getFechaCheckOut().toLocalDate().toString() : "Activa / Sin fecha de salida");
                JLabel lblCheckOut = new JLabel("Check-Out: " + checkOutDate);
                lblCheckOut.setFont(new Font("Dialog", Font.PLAIN, 14));
                lblCheckOut.setBounds(10, 110, 280, 20);
                rentaPanel.add(lblCheckOut);

                JLabel lblCosto = new JLabel("Costo: $" + String.format("%.2f", renta.getCostoTotal()));
                lblCosto.setFont(new Font("Dialog", Font.BOLD, 14));
                lblCosto.setBounds(10, 135, 280, 20);
                rentaPanel.add(lblCosto);

                JLabel lblEstado = new JLabel("Estado: " + renta.getEstado());
                lblEstado.setFont(new Font("Dialog", Font.PLAIN, 14));
                lblEstado.setBounds(10, 160, 280, 20);
                rentaPanel.add(lblEstado);

                JLabel lblNotas = new JLabel("Notas: " + renta.getNotas());
                lblNotas.setFont(new Font("Dialog", Font.PLAIN, 12));
                lblNotas.setBounds(10, 185, 280, 20);
                rentaPanel.add(lblNotas);


                JLabel lblImagen = new JLabel(scaledDefaultIcon);
                lblImagen.setBounds(50, 220, 200, 200);
                rentaPanel.add(lblImagen);


                JButton btnDetalles = new JButton("Detalles");
                btnDetalles.setBackground(new Color(255, 230, 167));
                btnDetalles.setFont(new Font("Tahoma", Font.BOLD, 16));
                btnDetalles.setBounds(10, 375, 90, 55);
                final int rentaId = renta.getIdRenta();
                btnDetalles.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        frame.dispose();
                        DetallesHabitacion4 detallesWindow = new DetallesHabitacion4(renta.getIdHabitacion());
                        detallesWindow.frame.setVisible(true);
                    }
                });
                rentaPanel.add(btnDetalles);

                JButton btnEditar = new JButton("Editar");
                btnEditar.setBackground(new Color(44, 196, 196));
                btnEditar.setFont(new Font("Tahoma", Font.BOLD, 16));
                btnEditar.setBounds(105, 375, 90, 55);
                btnEditar.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        frame.dispose();
                        EditarRenta editarWindow = new EditarRenta(); // Llama al placeholder EditarRenta
                        editarWindow.frame.setVisible(true);
                    }
                });
                rentaPanel.add(btnEditar);

                JButton btnEliminar = new JButton("Eliminar");
                btnEliminar.setBackground(new Color(239, 35, 60));
                btnEliminar.setForeground(Color.WHITE);
                btnEliminar.setFont(new Font("Tahoma", Font.BOLD, 16));
                btnEliminar.setBounds(200, 375, 90, 55);
                btnEliminar.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        int confirm = JOptionPane.showConfirmDialog(frame, "¿Estás seguro de que quieres eliminar esta renta?", "Confirmar Eliminación", JOptionPane.YES_NO_OPTION);
                        if (confirm == JOptionPane.YES_OPTION) {
                            if (rentaDAO.deleteRenta(renta.getIdRenta())) {
                                JOptionPane.showMessageDialog(frame, "Renta eliminada exitosamente.");
                                loadRentas();
                            } else {
                                JOptionPane.showMessageDialog(frame, "Error al eliminar la renta.", "Error", JOptionPane.ERROR_MESSAGE);
                            }
                        }
                    }
                });
                rentaPanel.add(btnEliminar);

                rentasDisplayPanel.add(rentaPanel);
            }
        } else {
            JLabel noRentasLabel = new JLabel("No se encontraron rentas.");
            noRentasLabel.setFont(new Font("Jost*", Font.BOLD, 20));
            noRentasLabel.setForeground(Color.GRAY);
            noRentasLabel.setHorizontalAlignment(JLabel.CENTER);
            rentasDisplayPanel.add(noRentasLabel);
        }
        rentasDisplayPanel.revalidate();
        rentasDisplayPanel.repaint();
    }

    private void updateDaysOfStayLabel() {
        try {
            LocalDate checkIn = LocalDate.parse(textFieldFechaCheckIn.getText());
            LocalDate checkOut = LocalDate.parse(textFieldFechaCheckOut.getText());
            if (checkIn.isBefore(checkOut) || checkIn.isEqual(checkOut)) {
                long days = ChronoUnit.DAYS.between(checkIn, checkOut);
                lblDiasHospedaje.setText("Días de hospedaje: " + days + " días");
            } else {
                lblDiasHospedaje.setText("Días de hospedaje: Fechas inválidas");
            }
        } catch (DateTimeParseException e) {
            lblDiasHospedaje.setText("Días de hospedaje: Formato inválido");
        }
    }

    private void populateServiciosComboBox() {
        DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>();
        model.addElement("Seleccione un servicio");
        List<Servicios> servicios = servicioDAO.getAllServicios();
        if (servicios != null) {
            for (Servicios servicio : servicios) {
                model.addElement(servicio.getNombreServicio());
            }
        }
        comboBoxServicios.setModel(model);
    }

    private int parseRoomsUsedLabel() {
        String text = lblHabitacionesUsadas.getText();
        try {
            String numPart = text.replaceAll("[^\\d]", "");
            return Integer.parseInt(numPart);
        } catch (NumberFormatException e) {
            return 1;
        }
    }

    private void recalculateTotalCost() {
        try {
            double initialCost = Double.parseDouble(textFieldCostoInicial.getText());
            int roomsUsed = parseRoomsUsedLabel();
            double totalCost = initialCost * roomsUsed;
            lblCostoTotal.setText("Costo total: $" + String.format("%.2f", totalCost));
        } catch (NumberFormatException e) {
            lblCostoTotal.setText("Costo total: Error en el cálculo");
        }
    }

    public void setVisible(boolean b) {
        frame.setVisible(b);
    }
}