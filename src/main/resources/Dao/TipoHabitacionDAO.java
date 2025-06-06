package Dao;

import conexion.conexion_bd;
import modelos.TiposHabitacion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TipoHabitacionDAO {

    // Método auxiliar para cerrar recursos
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

    // Método para obtener el nombre del tipo de habitación por su ID
    public String getNombreTipoHabitacionById(int idTipoHabitacion) {
        String nombreTipo = null;
        String sql = "SELECT nombreTipo FROM TiposHabitacion WHERE idTipoHabitacion = ?";
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            conn = conexion_bd.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, idTipoHabitacion);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                nombreTipo = rs.getString("nombreTipo");
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener nombre de tipo de habitación por ID: " + e.getMessage());
            e.printStackTrace();
        } finally {
            closeResources(conn, pstmt, rs);
        }
        return nombreTipo;
    }

    // Método para obtener el ID del tipo de habitación por su nombre
    public int getIdTipoHabitacionByNombre(String nombreTipo) {
        int idTipo = -1; // Valor por defecto si no se encuentra
        String sql = "SELECT idTipoHabitacion FROM TiposHabitacion WHERE nombreTipo = ?";
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            conn = conexion_bd.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, nombreTipo);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                idTipo = rs.getInt("idTipoHabitacion");
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener ID de tipo de habitación por nombre: " + e.getMessage());
            e.printStackTrace();
        } finally {
            closeResources(conn, pstmt, rs);
        }
        return idTipo;
    }
    
    // Método para obtener todos los tipos de habitación
    public List<TiposHabitacion> getAllTiposHabitacion() {
        List<TiposHabitacion> tipos = new ArrayList<>();
        String sql = "SELECT idTipoHabitacion, nombreTipo, descripcion FROM TiposHabitacion";
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            conn = conexion_bd.getConnection();
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                tipos.add(new TiposHabitacion(
                    rs.getInt("idTipoHabitacion"),
                    rs.getString("nombreTipo"),
                    rs.getString("descripcion")
                ));
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener todos los tipos de habitación: " + e.getMessage());
            e.printStackTrace();
        } finally {
            closeResources(conn, pstmt, rs);
        }
        return tipos;
    }
}
