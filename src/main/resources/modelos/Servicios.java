package modelos;

public class Servicios {
    private int idServicio;
    private String nombreServicio;
    private double precio;
    private String descripcion;

    public Servicios() {
    }

    // Constructor completo
    public Servicios(int idServicio, String nombreServicio, double precio, String descripcion) {
        this.idServicio = idServicio;
        this.nombreServicio = nombreServicio;
        this.precio = precio;
        this.descripcion = descripcion;
    }

    // Constructor para crear nuevos servicios (sin idServicio, ya que es AUTO_INCREMENT)
    public Servicios(String nombreServicio, double precio, String descripcion) {
        this.nombreServicio = nombreServicio;
        this.precio = precio;
        this.descripcion = descripcion;
    }

    // Getters y Setters
    public int getIdServicio() {
        return idServicio;
    }

    public void setIdServicio(int idServicio) {
        this.idServicio = idServicio;
    }

    public String getNombreServicio() {
        return nombreServicio;
    }

    public void setNombreServicio(String nombreServicio) {
        this.nombreServicio = nombreServicio;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}

