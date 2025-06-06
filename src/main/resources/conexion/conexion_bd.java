package conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLClientInfoException;
import java.sql.SQLException;

public class conexion_bd {
	
	private static final String URL = "jdbc:mysql://sql.freedb.tech:3306/freedb_freehoteldb";
    private static final String USER = "freedb_ZDevv";
    private static final String PASSWORD = "m6wu3SKn$#HEdNC";

    public static Connection getConnection() throws SQLException {
        try {

            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (ClassNotFoundException e) {
            System.err.println("Error: MySQL JDBC Driver no encontrado. " +
                               "Asegúrate de que 'mysql-connector-j-X.X.jar' está en la carpeta Librerias " +
                               "y añadido como librería al proyecto.");
            throw new SQLException("MySQL JDBC Driver not found.", e);
        }
    }

    public static void closeConnection(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                System.err.println("Error al cerrar la conexión: " + e.getMessage());
               
            }
        }
    }
}
