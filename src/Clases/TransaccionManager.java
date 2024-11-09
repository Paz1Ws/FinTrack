package Clases;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import Clases.ClasesComunes.Transaccion;

public class TransaccionManager {
    private List<Transaccion> transacciones;
    private double saldo;

    public TransaccionManager() {
        transacciones = new ArrayList<>();
        saldo = 0.0;
    }

    public void añadirTransaccion(Transaccion transaccion) {
        transacciones.add(transaccion);
        actualizarTransaccion();
    }

    public void borrarTransaccion(int index) {
        if (index >= 0 && index < transacciones.size()) {
            transacciones.remove(index);
            actualizarTransaccion();
        }
    }

    public void editarTransaccion(int index, Transaccion transaccionEditada) {
        if (index >= 0 && index < transacciones.size()) {
            transacciones.set(index, transaccionEditada);
            actualizarTransaccion();
        }
    }

    public List<Transaccion> filtrarTransaccion(LocalDate inicio, LocalDate fin) {
        return transacciones.stream()
                .filter(t -> !t.getFecha().isBefore(inicio) && !t.getFecha().isAfter(fin))
                .collect(Collectors.toList());
    }

    private void actualizarTransaccion() {
        saldo = transacciones.stream()
                .mapToDouble(t -> t.getTipo().equalsIgnoreCase("Ingreso") ? t.getMonto() : -t.getMonto())
                .sum();
    }

    public double getSaldo() {
        return saldo;
    }

    public List<Transaccion> getTransacciones() {
        return new ArrayList<>(transacciones);
    }
}