package Dao;

import conexion.conexion_bd;
import modelos.Reservas;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ReservaDAO {

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

    public boolean createReserva(Reservas reserva) {
        String sql = "INSERT INTO Reservas (idCliente, idHabitacion, idTarifaAplicada, fechaEntrada, fechaSalida, estado, costoTotalEstimado) VALUES (?, ?, ?, ?, ?, ?, ?)";
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet generatedKeys = null;

        try {
            conn = conexion_bd.getConnection();
            pstmt = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            pstmt.setInt(1, reserva.getIdCliente());
            pstmt.setInt(2, reserva.getIdHabitacion());
            pstmt.setInt(3, reserva.getIdTarifaAplicada());
            pstmt.setDate(4, java.sql.Date.valueOf(reserva.getFechaEntrada()));
            pstmt.setDate(5, java.sql.Date.valueOf(reserva.getFechaSalida()));
            pstmt.setString(6, reserva.getEstado());
            pstmt.setDouble(7, reserva.getCostoTotalEstimado());

            int affectedRows = pstmt.executeUpdate();

            if (affectedRows > 0) {
                generatedKeys = pstmt.getGeneratedKeys();
                if (generatedKeys.next()) {
                    reserva.setIdReserva(generatedKeys.getInt(1));
                }
                return true;
            }
            return false;
        } catch (SQLException e) {
            System.err.println("Error al crear reserva: " + e.getMessage());
            e.printStackTrace();
            return false;
        } finally {
            closeResources(conn, pstmt, generatedKeys);
        }
    }

    public Reservas getReservaById(int id) {
        Reservas reserva = null;
        String sql = "SELECT idReserva, idCliente, idHabitacion, idTarifaAplicada, fechaEntrada, fechaSalida, estado, costoTotalEstimado FROM Reservas WHERE idReserva = ?";
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = conexion_bd.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, id);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                reserva = new Reservas(
                    rs.getInt("idReserva"),
                    rs.getInt("idCliente"),
                    rs.getInt("idHabitacion"),
                    rs.getInt("idTarifaAplicada"),
                    rs.getDate("fechaEntrada").toLocalDate(),
                    rs.getDate("fechaSalida").toLocalDate(),
                    rs.getString("estado"),
                    rs.getDouble("costoTotalEstimado")
                );
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener reserva por ID: " + e.getMessage());
            e.printStackTrace();
        } finally {
            closeResources(conn, pstmt, rs);
        }
        return reserva;
    }

    public List<Reservas> getAllReservas() {
        List<Reservas> reservas = new ArrayList<>();
        String sql = "SELECT idReserva, idCliente, idHabitacion, idTarifaAplicada, fechaEntrada, fechaSalida, estado, costoTotalEstimado FROM Reservas ORDER BY fechaEntrada DESC";
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = conexion_bd.getConnection();
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                reservas.add(new Reservas(
                    rs.getInt("idReserva"),
                    rs.getInt("idCliente"),
                    rs.getInt("idHabitacion"),
                    rs.getInt("idTarifaAplicada"),
                    rs.getDate("fechaEntrada").toLocalDate(),
                    rs.getDate("fechaSalida").toLocalDate(),
                    rs.getString("estado"),
                    rs.getDouble("costoTotalEstimado")
                ));
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener todas las reservas: " + e.getMessage());
            e.printStackTrace();
        } finally {
            closeResources(conn, pstmt, rs);
        }
        return reservas;
    }

    public List<Reservas> searchReservas(String searchTerm) {
        List<Reservas> reservas = new ArrayList<>();
        // Puedes ajustar los campos de búsqueda según sea necesario. Aquí se busca por estado.
        String sql = "SELECT idReserva, idCliente, idHabitacion, idTarifaAplicada, fechaEntrada, fechaSalida, estado, costoTotalEstimado FROM Reservas WHERE estado LIKE ? ORDER BY fechaEntrada DESC";
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = conexion_bd.getConnection();
            pstmt = conn.prepareStatement(sql);
            String searchPattern = "%" + searchTerm + "%";
            pstmt.setString(1, searchPattern);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                reservas.add(new Reservas(
                    rs.getInt("idReserva"),
                    rs.getInt("idCliente"),
                    rs.getInt("idHabitacion"),
                    rs.getInt("idTarifaAplicada"),
                    rs.getDate("fechaEntrada").toLocalDate(),
                    rs.getDate("fechaSalida").toLocalDate(),
                    rs.getString("estado"),
                    rs.getDouble("costoTotalEstimado")
                ));
            }
        } catch (SQLException e) {
            System.err.println("Error al buscar reservas: " + e.getMessage());
            e.printStackTrace();
        } finally {
            closeResources(conn, pstmt, rs);
        }
        return reservas;
    }

    public boolean updateReserva(Reservas reserva) {
        String sql = "UPDATE Reservas SET idCliente = ?, idHabitacion = ?, idTarifaAplicada = ?, fechaEntrada = ?, fechaSalida = ?, estado = ?, costoTotalEstimado = ? WHERE idReserva = ?";
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = conexion_bd.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, reserva.getIdCliente());
            pstmt.setInt(2, reserva.getIdHabitacion());
            pstmt.setInt(3, reserva.getIdTarifaAplicada());
            pstmt.setDate(4, java.sql.Date.valueOf(reserva.getFechaEntrada()));
            pstmt.setDate(5, java.sql.Date.valueOf(reserva.getFechaSalida()));
            pstmt.setString(6, reserva.getEstado());
            pstmt.setDouble(7, reserva.getCostoTotalEstimado());
            pstmt.setInt(8, reserva.getIdReserva());
            int affectedRows = pstmt.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            System.err.println("Error al actualizar reserva: " + e.getMessage());
            e.printStackTrace();
            return false;
        } finally {
            closeResources(conn, pstmt, null);
        }
    }

    public boolean deleteReserva(int id) {
        String sql = "DELETE FROM Reservas WHERE idReserva = ?";
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = conexion_bd.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, id);
            int affectedRows = pstmt.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            System.err.println("Error al eliminar reserva: " + e.getMessage());
            e.printStackTrace();
            return false;
        } finally {
            closeResources(conn, pstmt, null);
        }
    }
}
