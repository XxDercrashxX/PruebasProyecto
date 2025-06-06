package modelos;

import java.time.LocalDate; // Para manejar la fecha de pago

public class Pagos {
    private int idPago;
    private int idReserva; // Clave foránea a la tabla Reservas
    private double monto;
    private LocalDate fechaPago;
    private String metodoPago; // Por ejemplo: "Tarjeta", "Efectivo", "Transferencia"
    private String estadoPago; // Por ejemplo: "Pendiente", "Completado", "Reembolsado"

    public Pagos() {
    }

    // Constructor completo
    public Pagos(int idPago, int idReserva, double monto, LocalDate fechaPago, String metodoPago, String estadoPago) {
        this.idPago = idPago;
        this.idReserva = idReserva;
        this.monto = monto;
        this.fechaPago = fechaPago;
        this.metodoPago = metodoPago;
        this.estadoPago = estadoPago;
    }

    // Constructor para crear nuevos pagos (sin idPago, ya que es AUTO_INCREMENT)
    public Pagos(int idReserva, double monto, LocalDate fechaPago, String metodoPago, String estadoPago) {
        this.idReserva = idReserva;
        this.monto = monto;
        this.fechaPago = fechaPago;
        this.metodoPago = metodoPago;
        this.estadoPago = estadoPago;
    }

    // Getters y Setters
    public int getIdPago() {
        return idPago;
    }

    public void setIdPago(int idPago) {
        this.idPago = idPago;
    }

    public int getIdReserva() {
        return idReserva;
    }

    public void setIdReserva(int idReserva) {
        this.idReserva = idReserva;
    }

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }

    public LocalDate getFechaPago() {
        return fechaPago;
    }

    public void setFechaPago(LocalDate fechaPago) {
        this.fechaPago = fechaPago;
    }

    public String getMetodoPago() {
        return metodoPago;
    }

    public void setMetodoPago(String metodoPago) {
        this.metodoPago = metodoPago;
    }

    public String getEstadoPago() {
        return estadoPago;
    }

    public void setEstadoPago(String estadoPago) {
        this.estadoPago = estadoPago;
    }
}
