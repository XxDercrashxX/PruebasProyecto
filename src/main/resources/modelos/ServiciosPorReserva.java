package modelos;

import java.time.LocalDate; // Para la fecha de asignación del servicio a la reserva

public class ServiciosPorReserva {
    // Esta tabla es una relación N:M, por lo que su clave primaria compuesta
    // suele ser la combinación de las FKs. En el modelo, simplemente almacenamos los IDs.
    private int idReserva;
    private int idServicio;
    private int cantidad; // Cantidad del servicio asignado a la reserva (ej. cuántas toallas, cuántas comidas)
    private LocalDate fechaAsignacion; // Fecha en que el servicio fue asignado a la reserva

    public ServiciosPorReserva() {
    }

    // Constructor completo
    public ServiciosPorReserva(int idReserva, int idServicio, int cantidad, LocalDate fechaAsignacion) {
        this.idReserva = idReserva;
        this.idServicio = idServicio;
        this.cantidad = cantidad;
        this.fechaAsignacion = fechaAsignacion;
    }

    // Getters y Setters
    public int getIdReserva() {
        return idReserva;
    }

    public void setIdReserva(int idReserva) {
        this.idReserva = idReserva;
    }

    public int getIdServicio() {
        return idServicio;
    }

    public void setIdServicio(int idServicio) {
        this.idServicio = idServicio;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public LocalDate getFechaAsignacion() {
        return fechaAsignacion;
    }

    public void setFechaAsignacion(LocalDate fechaAsignacion) {
        this.fechaAsignacion = fechaAsignacion;
    }
}
