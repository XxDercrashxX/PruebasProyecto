package modelos;

public class Tarifa {
    private int idTarifa;
    private int idTipoHabitacion; // Corregido: Ahora es un ID que referencia a TiposHabitacion
    private String nombreTarifa; // Corregido: Es el nombre específico de esta tarifa (ej. "Tarifa Estándar")
    private double precioBase;
    private String condiciones; // Corregido: Mapea a la columna 'condiciones'
    private String descripcion; // Corregido: Mapea a la columna 'descripcion'

    public Tarifa() {
    }

    // Constructor con todos los campos
    public Tarifa(int idTarifa, int idTipoHabitacion, String nombreTarifa, double precioBase, String condiciones, String descripcion) {
        this.idTarifa = idTarifa;
        this.idTipoHabitacion = idTipoHabitacion;
        this.nombreTarifa = nombreTarifa;
        this.precioBase = precioBase;
        this.condiciones = condiciones;
        this.descripcion = descripcion;
    }

    // Constructor sin idTarifa (para crear nuevas tarifas)
    public Tarifa(int idTipoHabitacion, String nombreTarifa, double precioBase, String condiciones, String descripcion) {
        this.idTipoHabitacion = idTipoHabitacion;
        this.nombreTarifa = nombreTarifa;
        this.precioBase = precioBase;
        this.condiciones = condiciones;
        this.descripcion = descripcion;
    }

    // Getters y Setters
    public int getIdTarifa() { return idTarifa; }
    public void setIdTarifa(int idTarifa) { this.idTarifa = idTarifa; }
    public int getIdTipoHabitacion() { return idTipoHabitacion; }
    public void setIdTipoHabitacion(int idTipoHabitacion) { this.idTipoHabitacion = idTipoHabitacion; }
    public String getNombreTarifa() { return nombreTarifa; }
    public void setNombreTarifa(String nombreTarifa) { this.nombreTarifa = nombreTarifa; }
    public double getPrecioBase() { return precioBase; }
    public void setPrecioBase(double precioBase) { this.precioBase = precioBase; }
    public String getCondiciones() { return condiciones; }
    public void setCondiciones(String condiciones) { this.condiciones = condiciones; }
    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }
}
