package modelos;

import java.time.LocalDate;

public class Cliente {
    private int idCliente;
    private String nombre;
    private String apellido;
    private String telefono;
    private String email;
    private String direccion; // Propiedad 'direccion' añadida para coincidir con la base de datos
    private LocalDate fechaRegistro;

    public Cliente() {
    }

    // Constructor completo con todos los campos, incluyendo 'direccion'
    public Cliente(int idCliente, String nombre, String apellido, String telefono, String email, String direccion, LocalDate fechaRegistro) {
        this.idCliente = idCliente;
        this.nombre = nombre;
        this.apellido = apellido;
        this.telefono = telefono;
        this.email = email;
        this.direccion = direccion; // Inicializar la nueva propiedad
        this.fechaRegistro = fechaRegistro;
    }

    // Constructor para crear un nuevo cliente (sin idCliente, ya que es AUTO_INCREMENT)
    // Asume que la fecha de registro se establece al momento de la creación (LocalDate.now())
    public Cliente(String nombre, String apellido, String telefono, String email, String direccion) {
        this(0, nombre, apellido, telefono, email, direccion, LocalDate.now());
    }

    // Getters y Setters
    public int getIdCliente() { return idCliente; }
    public void setIdCliente(int idCliente) { this.idCliente = idCliente; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getApellido() { return apellido; }
    public void setApellido(String apellido) { this.apellido = apellido; }

    public String getTelefono() { return telefono; }
    public void setTelefono(String telefono) { this.telefono = telefono; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getDireccion() { return direccion; }
    public void setDireccion(String direccion) { this.direccion = direccion; }

    public LocalDate getFechaRegistro() { return fechaRegistro; }
    public void setFechaRegistro(LocalDate fechaRegistro) { this.fechaRegistro = fechaRegistro; }

    @Override
    public String toString() {
        return "Cliente{" +
               "idCliente=" + idCliente +
               ", nombre='" + nombre + '\'' +
               ", apellido='" + apellido + '\'' +
               ", telefono='" + telefono + '\'' +
               ", email='" + email + '\'' +
               ", direccion='" + direccion + '\'' +
               ", fechaRegistro=" + fechaRegistro +
               '}';
    }
}
