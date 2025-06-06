package Dao;

import conexion.conexion_bd;
import modelos.ServiciosPorReserva; // Importar la clase de modelo ServiciosPorReserva
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ServiciosPorReservaDAO {

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

    /**
     * Crea un nuevo registro de servicio por reserva en la base de datos.
     * Dado que la clave primaria es compuesta (idReserva, idServicio),
     * no se genera un ID automáticamente.
     *
     * @param servicioPorReserva El objeto ServiciosPorReserva a insertar.
     * @return true si la inserción fue exitosa, false en caso contrario.
     */
    public boolean createServicioPorReserva(ServiciosPorReserva servicioPorReserva) {
        // SQL para insertar un nuevo servicio por reserva.
        // La tabla Servicios_por_Reserva usa una clave primaria compuesta de idReserva y idServicio.
        String sql = "INSERT INTO Servicios_por_Reserva (idReserva, idServicio, cantidad, fechaAsignacion) VALUES (?, ?, ?, ?)";
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = conexion_bd.getConnection();
            pstmt = conn.prepareStatement(sql);

            pstmt.setInt(1, servicioPorReserva.getIdReserva());
            pstmt.setInt(2, servicioPorReserva.getIdServicio());
            pstmt.setInt(3, servicioPorReserva.getCantidad());
            pstmt.setDate(4, java.sql.Date.valueOf(servicioPorReserva.getFechaAsignacion())); // Convertir LocalDate a java.sql.Date

            int affectedRows = pstmt.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            System.err.println("Error al crear servicio por reserva: " + e.getMessage());
            e.printStackTrace();
            return false;
        } finally {
            closeResources(conn, pstmt, null);
        }
    }

    /**
     * Obtiene un registro de servicio por reserva usando su clave compuesta (idReserva y idServicio).
     *
     * @param idReserva El ID de la reserva.
     * @param idServicio El ID del servicio.
     * @return El objeto ServiciosPorReserva si se encuentra, o null si no existe.
     */
    public ServiciosPorReserva getServicioPorReservaByIds(int idReserva, int idServicio) {
        ServiciosPorReserva servicioPorReserva = null;
        String sql = "SELECT idReserva, idServicio, cantidad, fechaAsignacion FROM Servicios_por_Reserva WHERE idReserva = ? AND idServicio = ?";
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = conexion_bd.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, idReserva);
            pstmt.setInt(2, idServicio);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                servicioPorReserva = new ServiciosPorReserva(
                    rs.getInt("idReserva"),
                    rs.getInt("idServicio"),
                    rs.getInt("cantidad"),
                    rs.getDate("fechaAsignacion").toLocalDate() // Convertir java.sql.Date a LocalDate
                );
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener servicio por reserva por IDs: " + e.getMessage());
            e.printStackTrace();
        } finally {
            closeResources(conn, pstmt, rs);
        }
        return servicioPorReserva;
    }

    /**
     * Obtiene todos los registros de servicios por reserva.
     *
     * @return Una lista de objetos ServiciosPorReserva.
     */
    public List<ServiciosPorReserva> getAllServiciosPorReserva() {
        List<ServiciosPorReserva> serviciosPorReservaList = new ArrayList<>();
        String sql = "SELECT idReserva, idServicio, cantidad, fechaAsignacion FROM Servicios_por_Reserva ORDER BY idReserva, idServicio ASC";
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = conexion_bd.getConnection();
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                serviciosPorReservaList.add(new ServiciosPorReserva(
                    rs.getInt("idReserva"),
                    rs.getInt("idServicio"),
                    rs.getInt("cantidad"),
                    rs.getDate("fechaAsignacion").toLocalDate()
                ));
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener todos los servicios por reserva: " + e.getMessage());
            e.printStackTrace();
        } finally {
            closeResources(conn, pstmt, rs);
        }
        return serviciosPorReservaList;
    }

    /**
     * Obtiene todos los servicios asociados a una reserva específica.
     *
     * @param idReserva El ID de la reserva para la cual se desean obtener los servicios.
     * @return Una lista de objetos ServiciosPorReserva asociados a esa reserva.
     */
    public List<ServiciosPorReserva> getServiciosByReservaId(int idReserva) {
        List<ServiciosPorReserva> serviciosPorReservaList = new ArrayList<>();
        String sql = "SELECT idReserva, idServicio, cantidad, fechaAsignacion FROM Servicios_por_Reserva WHERE idReserva = ? ORDER BY idServicio ASC";
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = conexion_bd.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, idReserva);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                serviciosPorReservaList.add(new ServiciosPorReserva(
                    rs.getInt("idReserva"),
                    rs.getInt("idServicio"),
                    rs.getInt("cantidad"),
                    rs.getDate("fechaAsignacion").toLocalDate()
                ));
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener servicios por ID de reserva: " + e.getMessage());
            e.printStackTrace();
        } finally {
            closeResources(conn, pstmt, rs);
        }
        return serviciosPorReservaList;
    }

    /**
     * Actualiza un registro existente de servicio por reserva.
     *
     * @param servicioPorReserva El objeto ServiciosPorReserva con los datos actualizados.
     * @return true si la actualización fue exitosa, false en caso contrario.
     */
    public boolean updateServicioPorReserva(ServiciosPorReserva servicioPorReserva) {
        // Actualiza la cantidad y fechaAsignacion para una combinación específica de idReserva e idServicio
        String sql = "UPDATE Servicios_por_Reserva SET cantidad = ?, fechaAsignacion = ? WHERE idReserva = ? AND idServicio = ?";
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = conexion_bd.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, servicioPorReserva.getCantidad());
            pstmt.setDate(2, java.sql.Date.valueOf(servicioPorReserva.getFechaAsignacion()));
            pstmt.setInt(3, servicioPorReserva.getIdReserva());
            pstmt.setInt(4, servicioPorReserva.getIdServicio());
            int affectedRows = pstmt.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            System.err.println("Error al actualizar servicio por reserva: " + e.getMessage());
            e.printStackTrace();
            return false;
        } finally {
            closeResources(conn, pstmt, null);
        }
    }

    /**
     * Elimina un registro de servicio por reserva usando su clave compuesta.
     *
     * @param idReserva El ID de la reserva.
     * @param idServicio El ID del servicio.
     * @return true si la eliminación fue exitosa, false en caso contrario.
     */
    public boolean deleteServicioPorReserva(int idReserva, int idServicio) {
        String sql = "DELETE FROM Servicios_por_Reserva WHERE idReserva = ? AND idServicio = ?";
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = conexion_bd.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, idReserva);
            pstmt.setInt(2, idServicio);
            int affectedRows = pstmt.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            System.err.println("Error al eliminar servicio por reserva: " + e.getMessage());
            e.printStackTrace();
            return false;
        } finally {
            closeResources(conn, pstmt, null);
        }
    }


    public boolean deleteAllServiciosFromReserva(int idReserva) {
        String sql = "DELETE FROM Servicios_por_Reserva WHERE idReserva = ?";
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = conexion_bd.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, idReserva);
            int affectedRows = pstmt.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            System.err.println("Error al eliminar todos los servicios de la reserva: " + e.getMessage());
            e.printStackTrace();
            return false;
        } finally {
            closeResources(conn, pstmt, null);
        }
    }
}
