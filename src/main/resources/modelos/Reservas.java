package modelos;

import java.time.LocalDate; // Para manejar fechas

public class Reservas {
    private int idReserva;
    private int idCliente;
    private int idHabitacion;
    private int idTarifaAplicada; // Nuevo: Referencia a la tarifa aplicada
    private LocalDate fechaEntrada;
    private LocalDate fechaSalida;
    private String estado; // Por ejemplo: "Pendiente", "Confirmada", "Cancelada"
    private double costoTotalEstimado;

    public Reservas() {
    }

    // Constructor completo
    public Reservas(int idReserva, int idCliente, int idHabitacion, int idTarifaAplicada, LocalDate fechaEntrada, LocalDate fechaSalida, String estado, double costoTotalEstimado) {
        this.idReserva = idReserva;
        this.idCliente = idCliente;
        this.idHabitacion = idHabitacion;
        this.idTarifaAplicada = idTarifaAplicada;
        this.fechaEntrada = fechaEntrada;
        this.fechaSalida = fechaSalida;
        this.estado = estado;
        this.costoTotalEstimado = costoTotalEstimado;
    }

    // Constructor para crear nuevas reservas (sin idReserva, ya que es AUTO_INCREMENT)
    public Reservas(int idCliente, int idHabitacion, int idTarifaAplicada, LocalDate fechaEntrada, LocalDate fechaSalida, String estado, double costoTotalEstimado) {
        this.idCliente = idCliente;
        this.idHabitacion = idHabitacion;
        this.idTarifaAplicada = idTarifaAplicada;
        this.fechaEntrada = fechaEntrada;
        this.fechaSalida = fechaSalida;
        this.estado = estado;
        this.costoTotalEstimado = costoTotalEstimado;
    }

    // Getters y Setters
    public int getIdReserva() {
        return idReserva;
    }

    public void setIdReserva(int idReserva) {
        this.idReserva = idReserva;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public int getIdHabitacion() {
        return idHabitacion;
    }

    public void setIdHabitacion(int idHabitacion) {
        this.idHabitacion = idHabitacion;
    }

    public int getIdTarifaAplicada() {
        return idTarifaAplicada;
    }

    public void setIdTarifaAplicada(int idTarifaAplicada) {
        this.idTarifaAplicada = idTarifaAplicada;
    }

    public LocalDate getFechaEntrada() {
        return fechaEntrada;
    }

    public void setFechaEntrada(LocalDate fechaEntrada) {
        this.fechaEntrada = fechaEntrada;
    }

    public LocalDate getFechaSalida() {
        return fechaSalida;
    }

    public void setFechaSalida(LocalDate fechaSalida) {
        this.fechaSalida = fechaSalida;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public double getCostoTotalEstimado() {
        return costoTotalEstimado;
    }

    public void setCostoTotalEstimado(double costoTotalEstimado) {
        this.costoTotalEstimado = costoTotalEstimado;
    }
}
