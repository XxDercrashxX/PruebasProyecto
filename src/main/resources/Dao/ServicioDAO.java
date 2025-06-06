package Dao;

import conexion.conexion_bd;
import modelos.Servicios;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ServicioDAO {

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

    public boolean createServicio(Servicios servicio) {
        String sql = "INSERT INTO Servicios (nombreServicio, precio, descripcion) VALUES (?, ?, ?)";
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet generatedKeys = null;

        try {
            conn = conexion_bd.getConnection();
            pstmt = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            pstmt.setString(1, servicio.getNombreServicio());
            pstmt.setDouble(2, servicio.getPrecio());
            pstmt.setString(3, servicio.getDescripcion());

            int affectedRows = pstmt.executeUpdate();

            if (affectedRows > 0) {
                generatedKeys = pstmt.getGeneratedKeys();
                if (generatedKeys.next()) {
                    servicio.setIdServicio(generatedKeys.getInt(1));
                }
                return true;
            }
            return false;
        } catch (SQLException e) {
            System.err.println("Error al crear servicio: " + e.getMessage());
            e.printStackTrace();
            return false;
        } finally {
            closeResources(conn, pstmt, generatedKeys);
        }
    }

    public Servicios getServicioById(int id) {
        Servicios servicio = null;
        String sql = "SELECT idServicio, nombreServicio, precio, descripcion FROM Servicios WHERE idServicio = ?";
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = conexion_bd.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, id);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                servicio = new Servicios(
                    rs.getInt("idServicio"),
                    rs.getString("nombreServicio"),
                    rs.getDouble("precio"),
                    rs.getString("descripcion")
                );
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener servicio por ID: " + e.getMessage());
            e.printStackTrace();
        } finally {
            closeResources(conn, pstmt, rs);
        }
        return servicio;
    }

    public List<Servicios> getAllServicios() {
        List<Servicios> servicios = new ArrayList<>();
        String sql = "SELECT idServicio, nombreServicio, precio, descripcion FROM Servicios ORDER BY nombreServicio ASC";
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = conexion_bd.getConnection();
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                servicios.add(new Servicios(
                    rs.getInt("idServicio"),
                    rs.getString("nombreServicio"),
                    rs.getDouble("precio"),
                    rs.getString("descripcion")
                ));
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener todos los servicios: " + e.getMessage());
            e.printStackTrace();
        } finally {
            closeResources(conn, pstmt, rs);
        }
        return servicios;
    }

    public List<Servicios> searchServicios(String searchTerm) {
        List<Servicios> servicios = new ArrayList<>();
        String sql = "SELECT idServicio, nombreServicio, precio, descripcion FROM Servicios WHERE nombreServicio LIKE ? OR descripcion LIKE ? ORDER BY nombreServicio ASC";
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = conexion_bd.getConnection();
            pstmt = conn.prepareStatement(sql);
            String searchPattern = "%" + searchTerm + "%";
            pstmt.setString(1, searchPattern);
            pstmt.setString(2, searchPattern);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                servicios.add(new Servicios(
                    rs.getInt("idServicio"),
                    rs.getString("nombreServicio"),
                    rs.getDouble("precio"),
                    rs.getString("descripcion")
                ));
            }
        } catch (SQLException e) {
            System.err.println("Error al buscar servicios: " + e.getMessage());
            e.printStackTrace();
        } finally {
            closeResources(conn, pstmt, rs);
        }
        return servicios;
    }

    public boolean updateServicio(Servicios servicio) {
        String sql = "UPDATE Servicios SET nombreServicio = ?, precio = ?, descripcion = ? WHERE idServicio = ?";
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = conexion_bd.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, servicio.getNombreServicio());
            pstmt.setDouble(2, servicio.getPrecio());
            pstmt.setString(3, servicio.getDescripcion());
            pstmt.setInt(4, servicio.getIdServicio());
            int affectedRows = pstmt.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            System.err.println("Error al actualizar servicio: " + e.getMessage());
            e.printStackTrace();
            return false;
        } finally {
            closeResources(conn, pstmt, null);
        }
    }

    public boolean deleteServicio(int id) {
        String sql = "DELETE FROM Servicios WHERE idServicio = ?";
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = conexion_bd.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, id);
            int affectedRows = pstmt.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            System.err.println("Error al eliminar servicio: " + e.getMessage());
            e.printStackTrace();
            return false;
        } finally {
            closeResources(conn, pstmt, null);
        }
    }
}

