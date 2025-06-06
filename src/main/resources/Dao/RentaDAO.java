package Dao;

import conexion.conexion_bd;
import modelos.Rentas; // Importar la clase de modelo Rentas
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp; // Para manejar LocalDateTime
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDateTime; // Para manejar LocalDateTime

public class RentaDAO {

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

    public boolean createRenta(Rentas renta) {
        // SQL para insertar una nueva renta. 'idReserva' es INTEGER y puede ser NULL.
        // 'costoTotal' es DECIMAL y puede ser NULL, lo manejamos como double en Java.
        String sql = "INSERT INTO Rentas (idReserva, idHabitacion, idCliente, fechaCheckIn, fechaCheckOut, costoTotal, estado, notas) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet generatedKeys = null;

        try {
            conn = conexion_bd.getConnection();
            pstmt = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);

            // idReserva puede ser NULL, verificar antes de setear
            if (renta.getIdReserva() == null) {
                pstmt.setNull(1, java.sql.Types.INTEGER);
            } else {
                pstmt.setInt(1, renta.getIdReserva());
            }

            pstmt.setInt(2, renta.getIdHabitacion());
            pstmt.setInt(3, renta.getIdCliente());
            pstmt.setTimestamp(4, Timestamp.valueOf(renta.getFechaCheckIn())); // Convertir LocalDateTime a Timestamp

            // fechaCheckOut puede ser NULL, verificar antes de setear
            if (renta.getFechaCheckOut() == null) {
                pstmt.setNull(5, java.sql.Types.TIMESTAMP);
            } else {
                pstmt.setTimestamp(5, Timestamp.valueOf(renta.getFechaCheckOut()));
            }

            // costoTotal puede ser NULL en la BD, pero en el modelo es double.
            // Aquí asumimos que si es 0.0, puede que se quiera insertar NULL si la columna lo permite y es su lógica de negocio.
            // Para simplicidad, siempre insertamos el valor. Si la columna en la BD fuera nullable y quisieras insertar NULL para 0.0,
            // tendrías que cambiar el tipo de costoTotal en el modelo a Double (objeto).
            pstmt.setDouble(6, renta.getCostoTotal());

            pstmt.setString(7, renta.getEstado());
            pstmt.setString(8, renta.getNotas());

            int affectedRows = pstmt.executeUpdate();

            if (affectedRows > 0) {
                generatedKeys = pstmt.getGeneratedKeys();
                if (generatedKeys.next()) {
                    renta.setIdRenta(generatedKeys.getInt(1)); // Asignar el ID generado
                }
                return true;
            }
            return false;
        } catch (SQLException e) {
            System.err.println("Error al crear renta: " + e.getMessage());
            e.printStackTrace();
            return false;
        } finally {
            closeResources(conn, pstmt, generatedKeys);
        }
    }

    public Rentas getRentaById(int id) {
        Rentas renta = null;
        String sql = "SELECT idRenta, idReserva, idHabitacion, idCliente, fechaCheckIn, fechaCheckOut, costoTotal, estado, notas FROM Rentas WHERE idRenta = ?";
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = conexion_bd.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, id);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                Integer idReserva = rs.getObject("idReserva", Integer.class); // Obtener como Integer para manejar NULL
                LocalDateTime fechaCheckOut = null;
                if (rs.getTimestamp("fechaCheckOut") != null) {
                    fechaCheckOut = rs.getTimestamp("fechaCheckOut").toLocalDateTime(); // Convertir Timestamp a LocalDateTime
                }
                
                renta = new Rentas(
                    rs.getInt("idRenta"),
                    idReserva,
                    rs.getInt("idHabitacion"),
                    rs.getInt("idCliente"),
                    rs.getTimestamp("fechaCheckIn").toLocalDateTime(), // Convertir Timestamp a LocalDateTime
                    fechaCheckOut,
                    rs.getDouble("costoTotal"),
                    rs.getString("estado"),
                    rs.getString("notas")
                );
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener renta por ID: " + e.getMessage());
            e.printStackTrace();
        } finally {
            closeResources(conn, pstmt, rs);
        }
        return renta;
    }

    public List<Rentas> getAllRentas() {
        List<Rentas> rentas = new ArrayList<>();
        String sql = "SELECT idRenta, idReserva, idHabitacion, idCliente, fechaCheckIn, fechaCheckOut, costoTotal, estado, notas FROM Rentas ORDER BY fechaCheckIn DESC";
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = conexion_bd.getConnection();
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                Integer idReserva = rs.getObject("idReserva", Integer.class); // Obtener como Integer para manejar NULL
                LocalDateTime fechaCheckOut = null;
                if (rs.getTimestamp("fechaCheckOut") != null) {
                    fechaCheckOut = rs.getTimestamp("fechaCheckOut").toLocalDateTime(); // Convertir Timestamp a LocalDateTime
                }

                rentas.add(new Rentas(
                    rs.getInt("idRenta"),
                    idReserva,
                    rs.getInt("idHabitacion"),
                    rs.getInt("idCliente"),
                    rs.getTimestamp("fechaCheckIn").toLocalDateTime(),
                    fechaCheckOut,
                    rs.getDouble("costoTotal"),
                    rs.getString("estado"),
                    rs.getString("notas")
                ));
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener todas las rentas: " + e.getMessage());
            e.printStackTrace();
        } finally {
            closeResources(conn, pstmt, rs);
        }
        return rentas;
    }

    public List<Rentas> searchRentas(String searchTerm) {
        List<Rentas> rentas = new ArrayList<>();
        // Puedes ajustar los campos de búsqueda según sea necesario. Aquí se busca por estado o ID de renta (como string).
        String sql = "SELECT idRenta, idReserva, idHabitacion, idCliente, fechaCheckIn, fechaCheckOut, costoTotal, estado, notas FROM Rentas WHERE estado LIKE ? OR CAST(idRenta AS CHAR) LIKE ? OR CAST(idHabitacion AS CHAR) LIKE ? OR CAST(idCliente AS CHAR) LIKE ? ORDER BY fechaCheckIn DESC";
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = conexion_bd.getConnection();
            pstmt = conn.prepareStatement(sql);
            String searchPattern = "%" + searchTerm + "%";
            pstmt.setString(1, searchPattern); // Búsqueda por estado
            pstmt.setString(2, searchPattern); // Búsqueda por idRenta (convertido a String)
            pstmt.setString(3, searchPattern); // Búsqueda por idHabitacion (convertido a String)
            pstmt.setString(4, searchPattern); // Búsqueda por idCliente (convertido a String)
            rs = pstmt.executeQuery();

            while (rs.next()) {
                Integer idReserva = rs.getObject("idReserva", Integer.class); // Obtener como Integer para manejar NULL
                LocalDateTime fechaCheckOut = null;
                if (rs.getTimestamp("fechaCheckOut") != null) {
                    fechaCheckOut = rs.getTimestamp("fechaCheckOut").toLocalDateTime();
                }

                rentas.add(new Rentas(
                    rs.getInt("idRenta"),
                    idReserva,
                    rs.getInt("idHabitacion"),
                    rs.getInt("idCliente"),
                    rs.getTimestamp("fechaCheckIn").toLocalDateTime(),
                    fechaCheckOut,
                    rs.getDouble("costoTotal"),
                    rs.getString("estado"),
                    rs.getString("notas")
                ));
            }
        } catch (SQLException e) {
            System.err.println("Error al buscar rentas: " + e.getMessage());
            e.printStackTrace();
        } finally {
            closeResources(conn, pstmt, rs);
        }
        return rentas;
    }

    public boolean updateRenta(Rentas renta) {
        String sql = "UPDATE Rentas SET idReserva = ?, idHabitacion = ?, idCliente = ?, fechaCheckIn = ?, fechaCheckOut = ?, costoTotal = ?, estado = ?, notas = ? WHERE idRenta = ?";
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = conexion_bd.getConnection();
            pstmt = conn.prepareStatement(sql);

            // idReserva puede ser NULL
            if (renta.getIdReserva() == null) {
                pstmt.setNull(1, java.sql.Types.INTEGER);
            } else {
                pstmt.setInt(1, renta.getIdReserva());
            }

            pstmt.setInt(2, renta.getIdHabitacion());
            pstmt.setInt(3, renta.getIdCliente());
            pstmt.setTimestamp(4, Timestamp.valueOf(renta.getFechaCheckIn()));

            // fechaCheckOut puede ser NULL
            if (renta.getFechaCheckOut() == null) {
                pstmt.setNull(5, java.sql.Types.TIMESTAMP);
            } else {
                pstmt.setTimestamp(5, Timestamp.valueOf(renta.getFechaCheckOut()));
            }

            pstmt.setDouble(6, renta.getCostoTotal());
            pstmt.setString(7, renta.getEstado());
            pstmt.setString(8, renta.getNotas());
            pstmt.setInt(9, renta.getIdRenta());
            int affectedRows = pstmt.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            System.err.println("Error al actualizar renta: " + e.getMessage());
            e.printStackTrace();
            return false;
        } finally {
            closeResources(conn, pstmt, null);
        }
    }

    public boolean deleteRenta(int id) {
        String sql = "DELETE FROM Rentas WHERE idRenta = ?";
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = conexion_bd.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, id);
            int affectedRows = pstmt.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            System.err.println("Error al eliminar renta: " + e.getMessage());
            e.printStackTrace();
            return false;
        } finally {
            closeResources(conn, pstmt, null);
        }
    }
}
