package Dao;

import conexion.conexion_bd;
import modelos.Habitaciones;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class HabitacionDAO {

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

    public boolean createHabitacion(Habitaciones habitacion) {
        String sql = "INSERT INTO Habitaciones (numero, idTipoHabitacion, numeroCamas, estado, descripcion, caracteristicas) VALUES (?, ?, ?, ?, ?, ?)";
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet generatedKeys = null;

        try {
            conn = conexion_bd.getConnection();
            pstmt = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            pstmt.setString(1, habitacion.getNumero());
            pstmt.setInt(2, habitacion.getIdTipoHabitacion());
            pstmt.setInt(3, habitacion.getNumeroCamas());
            pstmt.setString(4, habitacion.getEstado());
            pstmt.setString(5, habitacion.getDescripcion());
            pstmt.setString(6, habitacion.getCaracteristicas());

            int affectedRows = pstmt.executeUpdate();

            if (affectedRows > 0) {
                generatedKeys = pstmt.getGeneratedKeys();
                if (generatedKeys.next()) {
                    habitacion.setIdHabitacion(generatedKeys.getInt(1));
                }
                return true;
            }
            return false;
        } catch (SQLException e) {
            System.err.println("Error al crear habitación: " + e.getMessage());
            e.printStackTrace();
            return false;
        } finally {
            closeResources(conn, pstmt, generatedKeys);
        }
    }

    public Habitaciones getHabitacionById(int id) {
        Habitaciones habitacion = null;
        String sql = "SELECT idHabitacion, numero, idTipoHabitacion, numeroCamas, estado, descripcion, caracteristicas FROM Habitaciones WHERE idHabitacion = ?";
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = conexion_bd.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, id);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                habitacion = new Habitaciones(
                    rs.getInt("idHabitacion"),
                    rs.getString("numero"),
                    rs.getInt("idTipoHabitacion"),
                    rs.getInt("numeroCamas"),
                    rs.getString("estado"),
                    rs.getString("descripcion"),
                    rs.getString("caracteristicas")
                );
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener habitación por ID: " + e.getMessage());
            e.printStackTrace();
        } finally {
            closeResources(conn, pstmt, rs);
        }
        return habitacion;
    }

    public List<Habitaciones> getAllHabitaciones() {
        List<Habitaciones> habitaciones = new ArrayList<>();
        String sql = "SELECT idHabitacion, numero, idTipoHabitacion, numeroCamas, estado, descripcion, caracteristicas FROM Habitaciones ORDER BY numero ASC";
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = conexion_bd.getConnection();
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                habitaciones.add(new Habitaciones(
                    rs.getInt("idHabitacion"),
                    rs.getString("numero"),
                    rs.getInt("idTipoHabitacion"),
                    rs.getInt("numeroCamas"),
                    rs.getString("estado"),
                    rs.getString("descripcion"),
                    rs.getString("caracteristicas")
                ));
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener todas las habitaciones: " + e.getMessage());
            e.printStackTrace();
        } finally {
            closeResources(conn, pstmt, rs);
        }
        return habitaciones;
    }

    public List<Habitaciones> searchHabitaciones(String searchTerm) {
        List<Habitaciones> habitaciones = new ArrayList<>();
        String sql = "SELECT idHabitacion, numero, idTipoHabitacion, numeroCamas, estado, descripcion, caracteristicas FROM Habitaciones WHERE numero LIKE ? OR descripcion LIKE ? OR caracteristicas LIKE ? OR estado LIKE ? ORDER BY numero ASC";
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = conexion_bd.getConnection();
            pstmt = conn.prepareStatement(sql);
            String searchPattern = "%" + searchTerm + "%";
            pstmt.setString(1, searchPattern);
            pstmt.setString(2, searchPattern);
            pstmt.setString(3, searchPattern);
            pstmt.setString(4, searchPattern);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                habitaciones.add(new Habitaciones(
                    rs.getInt("idHabitacion"),
                    rs.getString("numero"),
                    rs.getInt("idTipoHabitacion"),
                    rs.getInt("numeroCamas"),
                    rs.getString("estado"),
                    rs.getString("descripcion"),
                    rs.getString("caracteristicas")
                ));
            }
        } catch (SQLException e) {
            System.err.println("Error al buscar habitaciones: " + e.getMessage());
            e.printStackTrace();
        } finally {
            closeResources(conn, pstmt, rs);
        }
        return habitaciones;
    }

    public boolean updateHabitacion(Habitaciones habitacion) {
        String sql = "UPDATE Habitaciones SET numero = ?, idTipoHabitacion = ?, numeroCamas = ?, estado = ?, descripcion = ?, caracteristicas = ? WHERE idHabitacion = ?";
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = conexion_bd.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, habitacion.getNumero());
            pstmt.setInt(2, habitacion.getIdTipoHabitacion());
            pstmt.setInt(3, habitacion.getNumeroCamas());
            pstmt.setString(4, habitacion.getEstado());
            pstmt.setString(5, habitacion.getDescripcion());
            pstmt.setString(6, habitacion.getCaracteristicas());
            pstmt.setInt(7, habitacion.getIdHabitacion());
            int affectedRows = pstmt.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            System.err.println("Error al actualizar habitación: " + e.getMessage());
            e.printStackTrace();
            return false;
        } finally {
            closeResources(conn, pstmt, null);
        }
    }

    public boolean deleteHabitacion(int id) {
        String sql = "DELETE FROM Habitaciones WHERE idHabitacion = ?";
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = conexion_bd.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, id);
            int affectedRows = pstmt.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            System.err.println("Error al eliminar habitación: " + e.getMessage());
            e.printStackTrace();
            return false;
        } finally {
            closeResources(conn, pstmt, null);
        }
    }
}
