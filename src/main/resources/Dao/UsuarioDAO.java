package Dao;

import conexion.conexion_bd;
import modelos.Usuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.mindrot.jbcrypt.BCrypt; // Asegúrate de tener la librería jbcrypt en tu proyecto

public class UsuarioDAO {

    // Método para cerrar los recursos de la base de datos de forma segura
    private void closeResources(Connection conn, PreparedStatement pstmt, ResultSet rs) {
        try {
            if (rs != null) rs.close();
            if (pstmt != null) pstmt.close();
            if (conn != null) conexion_bd.closeConnection(conn); // Asume que conexion_bd.closeConnection cierra la conexión
        } catch (SQLException e) {
            System.err.println("Error al cerrar recursos: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // Método para verificar si un usuario con un nombre de usuario dado ya existe
    public boolean existeUsuario(String nombreUsuario) {
        String sql = "SELECT COUNT(*) FROM Usuarios WHERE nombreUsuario = ?";
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            conn = conexion_bd.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, nombreUsuario);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0; // Si count > 0, el usuario existe
            }
            return false;
        } catch (SQLException e) {
            System.err.println("Error al verificar existencia de usuario: " + e.getMessage());
            e.printStackTrace();
            return false; // En caso de error, asumimos que no se pudo verificar correctamente
        } finally {
            closeResources(conn, pstmt, rs);
        }
    }

    // Método para registrar un nuevo usuario
    public boolean registrarUsuario(String nombreUsuario, String contrasenaPlana) {
        // Primero, verifica si el nombre de usuario ya existe
        if (existeUsuario(nombreUsuario)) {
            System.out.println("El nombre de usuario '" + nombreUsuario + "' ya está en uso.");
            return false;
        }

        // Si el usuario no existe, procede con el registro
        String hashedPassword = BCrypt.hashpw(contrasenaPlana, BCrypt.gensalt()); // Hashear la contraseña

        // Crea un objeto Usuario con la contraseña hasheada y un rol por defecto (ej. "usuario")
        // Asegúrate de que tu constructor de Usuario o sus setters permitan establecer estos valores
        Usuario nuevoUsuario = new Usuario();
        nuevoUsuario.setNombreUsuario(nombreUsuario);
        nuevoUsuario.setContraseñaHash(hashedPassword);
        nuevoUsuario.setRol("usuario"); // Asigna un rol por defecto, puedes ajustarlo según tus necesidades

        // Utiliza el método createUsuario existente para guardar el nuevo usuario en la BD
        return createUsuario(nuevoUsuario);
    }

    // Método para iniciar sesión de un usuario
    public Usuario login(String nombreUsuario, String contrasenaPlana) {
        String sql = "SELECT idUsuario, nombreUsuario, contraseñaHash, rol FROM Usuarios WHERE nombreUsuario = ?";
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Usuario usuario = null;

        try {
            conn = conexion_bd.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, nombreUsuario);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                String storedHash = rs.getString("contraseñaHash"); // Obtener el hash almacenado
                // Verificar la contraseña plana con el hash almacenado usando BCrypt
                if (BCrypt.checkpw(contrasenaPlana, storedHash)) {
                    usuario = new Usuario();
                    usuario.setIdUsuario(rs.getInt("idUsuario"));
                    usuario.setNombreUsuario(rs.getString("nombreUsuario"));
                    usuario.setContraseñaHash(storedHash); // Almacenar el hash en el objeto modelo
                    usuario.setRol(rs.getString("rol"));
                }
            }
        } catch (SQLException e) {
            System.err.println("Error en el login: " + e.getMessage());
            e.printStackTrace();
        } finally {
            closeResources(conn, pstmt, rs);
        }
        return usuario;
    }

    // Método para crear un nuevo usuario en la base de datos (se usa internamente para el registro)
    public boolean createUsuario(Usuario usuario) {
        // Nota: la contraseña ya debería estar hasheada cuando se llama a este método desde registrarUsuario
        String sql = "INSERT INTO Usuarios (nombreUsuario, contraseñaHash, rol) VALUES (?, ?, ?)";
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            conn = conexion_bd.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, usuario.getNombreUsuario());
            pstmt.setString(2, usuario.getContraseñaHash()); // Se guarda el hash
            pstmt.setString(3, usuario.getRol());

            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            // Error de SQL, podría ser una restricción de unicidad si el nombre de usuario ya existe
            System.err.println("Error al crear usuario en la BD: " + e.getMessage());
            e.printStackTrace();
            return false;
        } finally {
            closeResources(conn, pstmt, null);
        }
    }

    // Método para obtener un usuario por su ID
    public Usuario getUsuarioById(int idUsuario) {
        String sql = "SELECT idUsuario, nombreUsuario, contraseñaHash, rol FROM Usuarios WHERE idUsuario = ?";
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Usuario usuario = null;
        try {
            conn = conexion_bd.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, idUsuario);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                usuario = new Usuario();
                usuario.setIdUsuario(rs.getInt("idUsuario"));
                usuario.setNombreUsuario(rs.getString("nombreUsuario"));
                usuario.setContraseñaHash(rs.getString("contraseñaHash"));
                usuario.setRol(rs.getString("rol"));
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener usuario por ID: " + e.getMessage());
            e.printStackTrace();
        } finally {
            closeResources(conn, pstmt, rs);
        }
        return usuario;
    }

    // Método para actualizar un usuario existente
    public boolean updateUsuario(Usuario usuario) {
        String sql = "UPDATE Usuarios SET nombreUsuario = ?, contraseñaHash = ?, rol = ? WHERE idUsuario = ?";
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            conn = conexion_bd.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, usuario.getNombreUsuario());
            pstmt.setString(2, usuario.getContraseñaHash()); // Se asume que el hash ya está en el objeto
            pstmt.setString(3, usuario.getRol());
            pstmt.setInt(4, usuario.getIdUsuario());
            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            System.err.println("Error al actualizar usuario: " + e.getMessage());
            e.printStackTrace();
            return false;
        } finally {
            closeResources(conn, pstmt, null);
        }
    }

    // Método para eliminar un usuario por su ID
    public boolean deleteUsuario(int idUsuario) {
        String sql = "DELETE FROM Usuarios WHERE idUsuario = ?";
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            conn = conexion_bd.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, idUsuario);
            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            System.err.println("Error al eliminar usuario: " + e.getMessage());
            e.printStackTrace();
            return false;
        } finally {
            closeResources(conn, pstmt, null);
        }
    }
}
