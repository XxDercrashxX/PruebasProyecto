package Dao;

import conexion.conexion_bd;
import modelos.Pagos; // Importar la clase de modelo Pagos
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class PagoDAO {

    // Método auxiliar para cerrar recursos de la base de datos
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

    public boolean createPago(Pagos pago) {
        String sql = "INSERT INTO Pagos (idReserva, monto, fechaPago, metodoPago, estadoPago) VALUES (?, ?, ?, ?, ?)";
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet generatedKeys = null;

        try {
            conn = conexion_bd.getConnection();
            pstmt = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            pstmt.setInt(1, pago.getIdReserva());
            pstmt.setDouble(2, pago.getMonto());
            pstmt.setDate(3, java.sql.Date.valueOf(pago.getFechaPago())); // Convertir LocalDate a java.sql.Date
            pstmt.setString(4, pago.getMetodoPago());
            pstmt.setString(5, pago.getEstadoPago());

            int affectedRows = pstmt.executeUpdate();

            if (affectedRows > 0) {
                generatedKeys = pstmt.getGeneratedKeys();
                if (generatedKeys.next()) {
                    pago.setIdPago(generatedKeys.getInt(1)); // Asignar el ID generado
                }
                return true;
            }
            return false;
        } catch (SQLException e) {
            System.err.println("Error al crear pago: " + e.getMessage());
            e.printStackTrace();
            return false;
        } finally {
            closeResources(conn, pstmt, generatedKeys);
        }
    }

    public Pagos getPagoById(int id) {
        Pagos pago = null;
        String sql = "SELECT idPago, idReserva, monto, fechaPago, metodoPago, estadoPago FROM Pagos WHERE idPago = ?";
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = conexion_bd.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, id);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                pago = new Pagos(
                    rs.getInt("idPago"),
                    rs.getInt("idReserva"),
                    rs.getDouble("monto"),
                    rs.getDate("fechaPago").toLocalDate(), // Convertir java.sql.Date a LocalDate
                    rs.getString("metodoPago"),
                    rs.getString("estadoPago")
                );
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener pago por ID: " + e.getMessage());
            e.printStackTrace();
        } finally {
            closeResources(conn, pstmt, rs);
        }
        return pago;
    }

    public List<Pagos> getAllPagos() {
        List<Pagos> pagos = new ArrayList<>();
        String sql = "SELECT idPago, idReserva, monto, fechaPago, metodoPago, estadoPago FROM Pagos ORDER BY fechaPago DESC";
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = conexion_bd.getConnection();
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                pagos.add(new Pagos(
                    rs.getInt("idPago"),
                    rs.getInt("idReserva"),
                    rs.getDouble("monto"),
                    rs.getDate("fechaPago").toLocalDate(),
                    rs.getString("metodoPago"),
                    rs.getString("estadoPago")
                ));
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener todos los pagos: " + e.getMessage());
            e.printStackTrace();
        } finally {
            closeResources(conn, pstmt, rs);
        }
        return pagos;
    }

    public List<Pagos> searchPagos(String searchTerm) {
        List<Pagos> pagos = new ArrayList<>();
        // Puedes ajustar los campos de búsqueda. Aquí se busca por método de pago o estado.
        String sql = "SELECT idPago, idReserva, monto, fechaPago, metodoPago, estadoPago FROM Pagos WHERE metodoPago LIKE ? OR estadoPago LIKE ? OR CAST(idReserva AS CHAR) LIKE ? ORDER BY fechaPago DESC";
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = conexion_bd.getConnection();
            pstmt = conn.prepareStatement(sql);
            String searchPattern = "%" + searchTerm + "%";
            pstmt.setString(1, searchPattern); // Búsqueda por método de pago
            pstmt.setString(2, searchPattern); // Búsqueda por estado de pago
            pstmt.setString(3, searchPattern); // Búsqueda por idReserva (convertido a String)
            rs = pstmt.executeQuery();

            while (rs.next()) {
                pagos.add(new Pagos(
                    rs.getInt("idPago"),
                    rs.getInt("idReserva"),
                    rs.getDouble("monto"),
                    rs.getDate("fechaPago").toLocalDate(),
                    rs.getString("metodoPago"),
                    rs.getString("estadoPago")
                ));
            }
        } catch (SQLException e) {
            System.err.println("Error al buscar pagos: " + e.getMessage());
            e.printStackTrace();
        } finally {
            closeResources(conn, pstmt, rs);
        }
        return pagos;
    }

    public boolean updatePago(Pagos pago) {
        String sql = "UPDATE Pagos SET idReserva = ?, monto = ?, fechaPago = ?, metodoPago = ?, estadoPago = ? WHERE idPago = ?";
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = conexion_bd.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, pago.getIdReserva());
            pstmt.setDouble(2, pago.getMonto());
            pstmt.setDate(3, java.sql.Date.valueOf(pago.getFechaPago()));
            pstmt.setString(4, pago.getMetodoPago());
            pstmt.setString(5, pago.getEstadoPago());
            pstmt.setInt(6, pago.getIdPago());
            int affectedRows = pstmt.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            System.err.println("Error al actualizar pago: " + e.getMessage());
            e.printStackTrace();
            return false;
        } finally {
            closeResources(conn, pstmt, null);
        }
    }

    public boolean deletePago(int id) {
        String sql = "DELETE FROM Pagos WHERE idPago = ?";
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = conexion_bd.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, id);
            int affectedRows = pstmt.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            System.err.println("Error al eliminar pago: " + e.getMessage());
            e.printStackTrace();
            return false;
        } finally {
            closeResources(conn, pstmt, null);
        }
    }
}

