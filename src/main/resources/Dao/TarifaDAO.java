package Dao;

import modelos.Tarifa;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import conexion.conexion_bd;

public class TarifaDAO {

    public boolean createTarifa(Tarifa tarifa) {
        // SQL coincide con la DB: idTipoHabitacion, nombreTarifa, precioBase, condiciones, descripcion
        String sql = "INSERT INTO Tarifas (idTipoHabitacion, nombreTarifa, precioBase, condiciones, descripcion) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = conexion_bd.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
            
            pstmt.setInt(1, tarifa.getIdTipoHabitacion());
            pstmt.setString(2, tarifa.getNombreTarifa());
            pstmt.setDouble(3, tarifa.getPrecioBase());
            pstmt.setString(4, tarifa.getCondiciones());
            pstmt.setString(5, tarifa.getDescripcion());
            
            int affectedRows = pstmt.executeUpdate();

            if (affectedRows > 0) {
                try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        tarifa.setIdTarifa(generatedKeys.getInt(1));
                    }
                }
                return true;
            }
            return false;
        } catch (SQLException e) {
            System.err.println("Error al crear tarifa: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    public Tarifa getTarifaById(int id) {
        Tarifa tarifa = null;
        // SQL selecciona solo las columnas que el modelo Tarifa tiene
        String sql = "SELECT idTarifa, idTipoHabitacion, nombreTarifa, precioBase, condiciones, descripcion FROM Tarifas WHERE idTarifa = ?";
        try (Connection conn = conexion_bd.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    tarifa = new Tarifa(
                            rs.getInt("idTarifa"),
                            rs.getInt("idTipoHabitacion"),
                            rs.getString("nombreTarifa"),
                            rs.getDouble("precioBase"),
                            rs.getString("condiciones"),
                            rs.getString("descripcion")
                    );
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener tarifa por ID: " + e.getMessage());
            e.printStackTrace();
        }
        return tarifa;
    }

    public List<Tarifa> getAllTarifas() {
        List<Tarifa> tarifas = new ArrayList<>();
        // SQL selecciona solo las columnas que el modelo Tarifa tiene
        String sql = "SELECT idTarifa, idTipoHabitacion, nombreTarifa, precioBase, condiciones, descripcion FROM Tarifas";
        try (Connection conn = conexion_bd.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                Tarifa tarifa = new Tarifa(
                        rs.getInt("idTarifa"),
                        rs.getInt("idTipoHabitacion"),
                        rs.getString("nombreTarifa"),
                        rs.getDouble("precioBase"),
                        rs.getString("condiciones"),
                        rs.getString("descripcion")
                );
                tarifas.add(tarifa);
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener todas las tarifas: " + e.getMessage());
            e.printStackTrace();
        }
        return tarifas;
    }

    public List<Tarifa> getTarifasByTipoHabitacion(int idTipoHabitacionFiltro) { // Filtra por ID, no por nombre
        List<Tarifa> tarifas = new ArrayList<>();
        // SQL filtra por idTipoHabitacion, que es lo que tiene el modelo
        String sql = "SELECT idTarifa, idTipoHabitacion, nombreTarifa, precioBase, condiciones, descripcion FROM Tarifas WHERE idTipoHabitacion = ?";
        try (Connection conn = conexion_bd.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, idTipoHabitacionFiltro);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    Tarifa tarifa = new Tarifa(
                            rs.getInt("idTarifa"),
                            rs.getInt("idTipoHabitacion"),
                            rs.getString("nombreTarifa"),
                            rs.getDouble("precioBase"),
                            rs.getString("condiciones"),
                            rs.getString("descripcion")
                    );
                    tarifas.add(tarifa);
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener tarifas por tipo de habitación: " + e.getMessage());
            e.printStackTrace();
        }
        return tarifas;
    }

    public boolean updateTarifa(Tarifa tarifa) {
        // SQL coincide con la DB
        String sql = "UPDATE Tarifas SET idTipoHabitacion = ?, nombreTarifa = ?, precioBase = ?, condiciones = ?, descripcion = ? WHERE idTarifa = ?";
        try (Connection conn = conexion_bd.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, tarifa.getIdTipoHabitacion());
            pstmt.setString(2, tarifa.getNombreTarifa());
            pstmt.setDouble(3, tarifa.getPrecioBase());
            pstmt.setString(4, tarifa.getCondiciones());
            pstmt.setString(5, tarifa.getDescripcion());
            pstmt.setInt(6, tarifa.getIdTarifa());
            int affectedRows = pstmt.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            System.err.println("Error al actualizar tarifa: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteTarifa(int id) {
        String sql = "DELETE FROM Tarifas WHERE idTarifa = ?";
        try (Connection conn = conexion_bd.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            int affectedRows = pstmt.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            System.err.println("Error al eliminar tarifa: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }
}
