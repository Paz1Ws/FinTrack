package Clases.ClasesComunes;

import java.time.LocalDate;

public class Transaccion {
    private LocalDate fecha;
    private String descripcion;
    private double monto;
    private String tipo;

    public Transaccion() {
    }

    public Transaccion(LocalDate fecha, String descripcion, double monto, String tipo) {
        this.fecha = fecha;
        this.descripcion = descripcion;
        this.monto = monto;
        this.tipo = tipo;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    @Override
    public String toString() {
        return "Transaccion{" +
                "fecha=" + fecha +
                ", descripcion='" + descripcion + '\'' +
                ", monto=" + monto +
                ", tipo='" + tipo + '\'' +
                '}';
    }
}