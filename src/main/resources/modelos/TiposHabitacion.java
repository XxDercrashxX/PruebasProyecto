package modelos;

public class TiposHabitacion {
    private int idTipoHabitacion;
    private String nombreTipo;
    private String descripcion;

    public TiposHabitacion() {}

    public TiposHabitacion(int idTipoHabitacion, String nombreTipo, String descripcion) {
        this.idTipoHabitacion = idTipoHabitacion;
        this.nombreTipo = nombreTipo;
        this.descripcion = descripcion;
    }

    // Getters and Setters
    public int getIdTipoHabitacion() { return idTipoHabitacion; }
    public void setIdTipoHabitacion(int idTipoHabitacion) { this.idTipoHabitacion = idTipoHabitacion; }
    public String getNombreTipo() { return nombreTipo; }
    public void setNombreTipo(String nombreTipo) { this.nombreTipo = nombreTipo; }
    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

    @Override
    public String toString() {
        return "TiposHabitacion{" +
               "idTipoHabitacion=" + idTipoHabitacion +
               ", nombreTipo='" + nombreTipo + '\'' +
               ", descripcion='" + descripcion + '\'' +
               '}';
    }
}
