package Dao;

import conexion.conexion_bd;
import modelos.Cliente; // Importación corregida: modelos.Cliente (singular)
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ClienteDAO {

    private void closeResources(Connection conn, PreparedStatement pstmt, ResultSet rs) {
        try {
            if (rs != null) rs.close();
            if (pstmt != null) pstmt.close();
            if (conn != null) conexion_bd.closeConnection(conn);
        } catch (SQLException e) {
            System.err.println("Error al cerrar recursos: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public boolean agregarCliente(Cliente cliente) { // Usar modelos.Cliente (singular)
        String sql = "INSERT INTO Clientes (nombre, apellido, email, telefono, direccion, fechaRegistro) VALUES (?, ?, ?, ?, ?, ?)";
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = conexion_bd.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, cliente.getNombre());
            pstmt.setString(2, cliente.getApellido());
            pstmt.setString(3, cliente.getEmail());
            pstmt.setString(4, cliente.getTelefono());
            pstmt.setString(5, cliente.getDireccion()); // Ahora el modelo Cliente tiene getDireccion()
            pstmt.setDate(6, java.sql.Date.valueOf(cliente.getFechaRegistro()));

            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            System.err.println("Error al agregar cliente: " + e.getMessage());
            e.printStackTrace();
            return false;
        } finally {
            closeResources(conn, pstmt, null);
        }
    }

    public Cliente obtenerClientePorId(int idCliente) { // Usar modelos.Cliente (singular)
        String sql = "SELECT idCliente, nombre, apellido, email, telefono, direccion, fechaRegistro FROM Clientes WHERE idCliente = ?";
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Cliente cliente = null; // Usar modelos.Cliente (singular)

        try {
            conn = conexion_bd.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, idCliente);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                cliente = new Cliente(); // Usar modelos.Cliente (singular)
                cliente.setIdCliente(rs.getInt("idCliente"));
                cliente.setNombre(rs.getString("nombre"));
                cliente.setApellido(rs.getString("apellido"));
                cliente.setEmail(rs.getString("email"));
                cliente.setTelefono(rs.getString("telefono"));
                cliente.setDireccion(rs.getString("direccion")); // Ahora el modelo Cliente tiene setDireccion()
                cliente.setFechaRegistro(rs.getDate("fechaRegistro").toLocalDate());
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener cliente por ID: " + e.getMessage());
            e.printStackTrace();
        } finally {
            closeResources(conn, pstmt, rs);
        }
        return cliente;
    }

    public List<Cliente> getAllClientes() { // Usar List<Cliente> (singular)
        String sql = "SELECT idCliente, nombre, apellido, email, telefono, direccion, fechaRegistro FROM Clientes ORDER BY nombre ASC";
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List<Cliente> clientes = new ArrayList<>();

        try {
            conn = conexion_bd.getConnection();
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                Cliente cliente = new Cliente(); // Usar modelos.Cliente (singular)
                cliente.setIdCliente(rs.getInt("idCliente"));
                cliente.setNombre(rs.getString("nombre"));
                cliente.setApellido(rs.getString("apellido"));
                cliente.setEmail(rs.getString("email"));
                cliente.setTelefono(rs.getString("telefono"));
                cliente.setDireccion(rs.getString("direccion")); // Ahora el modelo Cliente tiene setDireccion()
                cliente.setFechaRegistro(rs.getDate("fechaRegistro").toLocalDate());
                clientes.add(cliente);
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener todos los clientes: " + e.getMessage());
            e.printStackTrace();
        } finally {
            closeResources(conn, pstmt, rs);
        }
        return clientes;
    }

    public List<Cliente> searchClientes(String searchTerm) { // Usar List<Cliente> (singular)
        String sql = "SELECT idCliente, nombre, apellido, email, telefono, direccion, fechaRegistro FROM Clientes WHERE nombre LIKE ? OR apellido LIKE ? OR email LIKE ? ORDER BY nombre ASC";
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List<Cliente> clientes = new ArrayList<>();

        try {
            conn = conexion_bd.getConnection();
            pstmt = conn.prepareStatement(sql);
            String searchPattern = "%" + searchTerm + "%";
            pstmt.setString(1, searchPattern);
            pstmt.setString(2, searchPattern);
            pstmt.setString(3, searchPattern);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                Cliente cliente = new Cliente(); // Usar modelos.Cliente (singular)
                cliente.setIdCliente(rs.getInt("idCliente"));
                cliente.setNombre(rs.getString("nombre"));
                cliente.setApellido(rs.getString("apellido"));
                cliente.setEmail(rs.getString("email"));
                cliente.setTelefono(rs.getString("telefono"));
                cliente.setDireccion(rs.getString("direccion")); // Ahora el modelo Cliente tiene setDireccion()
                cliente.setFechaRegistro(rs.getDate("fechaRegistro").toLocalDate());
                clientes.add(cliente);
            }
        } catch (SQLException e) {
            System.err.println("Error al buscar clientes: " + e.getMessage());
            e.printStackTrace();
        } finally {
            closeResources(conn, pstmt, rs);
        }
        return clientes;
    }

    public boolean actualizarCliente(Cliente cliente) { // Usar modelos.Cliente (singular)
        String sql = "UPDATE Clientes SET nombre = ?, apellido = ?, email = ?, telefono = ?, direccion = ?, fechaRegistro = ? WHERE idCliente = ?";
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = conexion_bd.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, cliente.getNombre());
            pstmt.setString(2, cliente.getApellido());
            pstmt.setString(3, cliente.getEmail());
            pstmt.setString(4, cliente.getTelefono());
            pstmt.setString(5, cliente.getDireccion()); // Ahora el modelo Cliente tiene getDireccion()
            pstmt.setDate(6, java.sql.Date.valueOf(cliente.getFechaRegistro()));
            pstmt.setInt(7, cliente.getIdCliente());

            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            System.err.println("Error al actualizar cliente: " + e.getMessage());
            e.printStackTrace();
            return false;
        } finally {
            closeResources(conn, pstmt, null);
        }
    }

    public boolean eliminarCliente(int idCliente) {
        String sql = "DELETE FROM Clientes WHERE idCliente = ?";
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = conexion_bd.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, idCliente);

            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            System.err.println("Error al eliminar cliente: " + e.getMessage());
            e.printStackTrace();
            return false;
        } finally {
            closeResources(conn, pstmt, null);
        }
    }
}
