package modelos;

public class Habitaciones {
    private int idHabitacion;
    private String numero;
    private int idTipoHabitacion; // Relacionado con TiposHabitacion
    private int numeroCamas;
    private String estado; // Por ejemplo: "disponible", "ocupada", "mantenimiento"
    private String descripcion;
    private String caracteristicas;

    public Habitaciones() {
    }

    // Constructor completo
    public Habitaciones(int idHabitacion, String numero, int idTipoHabitacion, int numeroCamas, String estado, String descripcion, String caracteristicas) {
        this.idHabitacion = idHabitacion;
        this.numero = numero;
        this.idTipoHabitacion = idTipoHabitacion;
        this.numeroCamas = numeroCamas;
        this.estado = estado;
        this.descripcion = descripcion;
        this.caracteristicas = caracteristicas;
    }

    // Constructor para crear nuevas habitaciones (sin idHabitacion, ya que es AUTO_INCREMENT)
    public Habitaciones(String numero, int idTipoHabitacion, int numeroCamas, String estado, String descripcion, String caracteristicas) {
        this.numero = numero;
        this.idTipoHabitacion = idTipoHabitacion;
        this.numeroCamas = numeroCamas;
        this.estado = estado;
        this.descripcion = descripcion;
        this.caracteristicas = caracteristicas;
    }

    // Getters y Setters
    public int getIdHabitacion() {
        return idHabitacion;
    }

    public void setIdHabitacion(int idHabitacion) {
        this.idHabitacion = idHabitacion;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public int getIdTipoHabitacion() {
        return idTipoHabitacion;
    }

    public void setIdTipoHabitacion(int idTipoHabitacion) {
        this.idTipoHabitacion = idTipoHabitacion;
    }

    public int getNumeroCamas() {
        return numeroCamas;
    }

    public void setNumeroCamas(int numeroCamas) {
        this.numeroCamas = numeroCamas;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getCaracteristicas() {
        return caracteristicas;
    }

    public void setCaracteristicas(String caracteristicas) {
        this.caracteristicas = caracteristicas;
    }
}
