package modelos;

import java.time.LocalDateTime; // Para manejar fechas y horas (DATETIME)

public class Rentas {
    private int idRenta;
    private Integer idReserva; // Puede ser nulo
    private int idHabitacion;
    private int idCliente;
    private LocalDateTime fechaCheckIn;
    private LocalDateTime fechaCheckOut; // Puede ser nulo
    private double costoTotal; // Puede ser nulo en la BD, pero lo manejamos como double
    private String estado; // Por ejemplo: "Activa", "Finalizada", "Cancelada"
    private String notas;

    public Rentas() {
    }

    // Constructor completo
    public Rentas(int idRenta, Integer idReserva, int idHabitacion, int idCliente, LocalDateTime fechaCheckIn, LocalDateTime fechaCheckOut, double costoTotal, String estado, String notas) {
        this.idRenta = idRenta;
        this.idReserva = idReserva;
        this.idHabitacion = idHabitacion;
        this.idCliente = idCliente;
        this.fechaCheckIn = fechaCheckIn;
        this.fechaCheckOut = fechaCheckOut;
        this.costoTotal = costoTotal;
        this.estado = estado;
        this.notas = notas;
    }

    // Constructor para crear nuevas rentas (sin idRenta, ya que es AUTO_INCREMENT)
    public Rentas(Integer idReserva, int idHabitacion, int idCliente, LocalDateTime fechaCheckIn, LocalDateTime fechaCheckOut, double costoTotal, String estado, String notas) {
        this.idReserva = idReserva;
        this.idHabitacion = idHabitacion;
        this.idCliente = idCliente;
        this.fechaCheckIn = fechaCheckIn;
        this.fechaCheckOut = fechaCheckOut;
        this.costoTotal = costoTotal;
        this.estado = estado;
        this.notas = notas;
    }

    // Getters y Setters
    public int getIdRenta() {
        return idRenta;
    }

    public void setIdRenta(int idRenta) {
        this.idRenta = idRenta;
    }

    public Integer getIdReserva() { // Usar Integer para permitir null
        return idReserva;
    }

    public void setIdReserva(Integer idReserva) {
        this.idReserva = idReserva;
    }

    public int getIdHabitacion() {
        return idHabitacion;
    }

    public void setIdHabitacion(int idHabitacion) {
        this.idHabitacion = idHabitacion;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public LocalDateTime getFechaCheckIn() {
        return fechaCheckIn;
    }

    public void setFechaCheckIn(LocalDateTime fechaCheckIn) {
        this.fechaCheckIn = fechaCheckIn;
    }

    public LocalDateTime getFechaCheckOut() {
        return fechaCheckOut;
    }

    public void setFechaCheckOut(LocalDateTime fechaCheckOut) {
        this.fechaCheckOut = fechaCheckOut;
    }

    public double getCostoTotal() {
        return costoTotal;
    }

    public void setCostoTotal(double costoTotal) {
        this.costoTotal = costoTotal;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getNotas() {
        return notas;
    }

    public void setNotas(String notas) {
        this.notas = notas;
    }
}
