package ui;


import org.mindrot.jbcrypt.BCrypt;

public class HashGenerator {
    public static void main(String[] args) {
        String plainPassword = "admin123"; 
        String hashedPassword = BCrypt.hashpw(plainPassword, BCrypt.gensalt());
        System.out.println("Contraseña original: " + plainPassword);
        System.out.println("Hash BCrypt generado: " + hashedPassword);
        System.out.println("------------------------------------------------------------------");
        System.out.println("Copia este hash y pégalo en tu base de datos para el usuario de prueba.");
    }
}