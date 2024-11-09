package Clases;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import Clases.ClasesComunes.Transaccion;
import SistemaInterfaz.*;

public class ControladorSistema {
    private TransaccionManager transaccionManager;
    private VistaSistema view;

    public ControladorSistema(TransaccionManager transaccionManager, VistaSistema view) {
        this.transaccionManager = transaccionManager;
        this.view = view;
        initListeners();
        view.setVisible(true);
        actualizarSaldo();
        actualizarTabla();
    }

    private void initListeners() {
        view.getBtnActualizar().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                actualizarSaldo();
            }
        });
        view.getBtnAgregarTrans().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                btnAgregarTransActionPerformed();
            }
        });
        view.getBtnEliminar().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                btnEliminarActionPerformed();
            }
        });
        view.getBtnEditar().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                btnEditarActionPerformed();
            }
        });
        view.getBtnAplicar().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                BtnAplicarActionPerformed();
            }
        });
        view.getBtnGuardar().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                btnGuardarActionPerformed();
            }
        });
        view.getBtnSali().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                btnSaliActionPerformed();
            }
        });
    }

    private void actualizarSaldo() {
        double saldo = transaccionManager.getSaldo();
        view.getTxtMonto().setText(String.format("%.2f", saldo));
    }

    private void actualizarTabla() {
        DefaultTableModel model = (DefaultTableModel) view.getTbMovimientos().getModel();
        model.setRowCount(0); // Limpia la tabla
        for (Transaccion t : transaccionManager.getTransacciones()) {
            model.addRow(new Object[] { t.getFecha(), t.getDescripcion(), t.getMonto(), t.getTipo() });
        }
    }

    private void btnAgregarTransActionPerformed() {
        String fechaStr = JOptionPane.showInputDialog("Fecha (yyyy-MM-dd):");
        LocalDate fecha = null;
        try {
            fecha = new SimpleDateFormat("yyyy-MM-dd").parse(fechaStr).toInstant()
                    .atZone(java.time.ZoneId.systemDefault()).toLocalDate();
        } catch (ParseException e) {
            JOptionPane.showMessageDialog(view, "Formato de fecha incorrecto. Use yyyy-MM-dd.");
            return;
        }
        String descripcion = JOptionPane.showInputDialog("Descripción:");
        double monto = 0;
        boolean validInput = false;
        while (!validInput) {
            try {
                monto = Double.parseDouble(JOptionPane.showInputDialog("Monto:"));
                validInput = true;
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(view, "Monto inválido. Por favor, ingrese un número.");
            }
        }
        String tipo = JOptionPane.showInputDialog("Tipo (Ingreso/Salida):");
        while (!tipo.equalsIgnoreCase("Ingreso") && !tipo.equalsIgnoreCase("Salida")) {
            tipo = JOptionPane.showInputDialog("Tipo inválido. Por favor, ingrese 'Ingreso' o 'Salida':");
        }

        Transaccion nuevaTransaccion = new Transaccion(fecha, descripcion, monto, tipo);
        transaccionManager.añadirTransaccion(nuevaTransaccion);
        actualizarSaldo();
        actualizarTabla();
    }

    private void btnEliminarActionPerformed() {
        int filaSeleccionada = view.getTbMovimientos().getSelectedRow();
        if (filaSeleccionada >= 0) {
            transaccionManager.borrarTransaccion(filaSeleccionada);
            actualizarSaldo();
            actualizarTabla();
        } else {
            JOptionPane.showMessageDialog(view, "Selecciona una transacción para eliminar.");
        }
    }

    private void btnEditarActionPerformed() {
        int filaSeleccionada = view.getTbMovimientos().getSelectedRow();
        if (filaSeleccionada >= 0) {
            Transaccion transaccion = transaccionManager.getTransacciones().get(filaSeleccionada);

            String fechaStr = JOptionPane.showInputDialog("Fecha (yyyy-MM-dd):",
                    transaccion.getFecha().toString());
            LocalDate fecha = null;
            try {
                fecha = new SimpleDateFormat("yyyy-MM-dd").parse(fechaStr).toInstant()
                        .atZone(java.time.ZoneId.systemDefault()).toLocalDate();
            } catch (ParseException e) {
                JOptionPane.showMessageDialog(view, "Formato de fecha incorrecto. Use yyyy-MM-dd.");
                return;
            }

            String descripcion = JOptionPane.showInputDialog("Descripción:", transaccion.getDescripcion());
            double monto = 0;
            boolean validInput = false;
            while (!validInput) {
                try {
                    monto = Double.parseDouble(
                            JOptionPane.showInputDialog("Monto:", transaccion.getMonto()));
                    validInput = true;
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(view,
                            "Monto inválido. Por favor, ingrese un número.");
                }
            }

            String tipo = JOptionPane.showInputDialog("Tipo (Ingreso/Salida):", transaccion.getTipo());
            while (!tipo.equalsIgnoreCase("Ingreso") && !tipo.equalsIgnoreCase("Salida")) {
                tipo = JOptionPane.showInputDialog(
                        "Tipo inválido. Por favor, ingrese 'Ingreso' o 'Salida':",
                        transaccion.getTipo());
            }

            Transaccion transaccionEditada = new Transaccion(fecha, descripcion, monto, tipo);
            transaccionManager.editarTransaccion(filaSeleccionada, transaccionEditada);
            actualizarSaldo();
            actualizarTabla();
        } else {
            JOptionPane.showMessageDialog(view, "Selecciona una transacción para editar.");
        }
    }

    private void BtnAplicarActionPerformed() {
        Date fechaInicioDate = view.getDcInicio().getDate();
        Date fechaFinDate = view.getDcFin().getDate();

        if (fechaInicioDate == null || fechaFinDate == null) {
            JOptionPane.showMessageDialog(view, "Por favor, seleccione ambas fechas de inicio y fin.");
            return;
        }

        LocalDate fechaInicio = fechaInicioDate.toInstant().atZone(java.time.ZoneId.systemDefault())
                .toLocalDate();
        LocalDate fechaFin = fechaFinDate.toInstant().atZone(java.time.ZoneId.systemDefault()).toLocalDate();

        if (fechaInicio.isAfter(fechaFin)) {
            JOptionPane.showMessageDialog(view,
                    "La fecha de inicio no puede ser posterior a la fecha de fin.");
            return;
        }

        List<Transaccion> transaccionesFiltradas = transaccionManager.filtrarTransaccion(fechaInicio, fechaFin);
        DefaultTableModel model = (DefaultTableModel) view.getTbMovimientos().getModel();
        model.setRowCount(0); // Limpia la tabla

        for (Transaccion t : transaccionesFiltradas) {
            model.addRow(new Object[] { t.getFecha(), t.getDescripcion(), t.getMonto(), t.getTipo() });
        }
    }

    private void btnGuardarActionPerformed() {
        List<Transaccion> transacciones = transaccionManager.getTransacciones();
        if (transacciones.isEmpty()) {
            JOptionPane.showMessageDialog(view, "No hay transacciones para guardar.");
            return;
        }
        try (PrintWriter writer = new PrintWriter(new FileWriter("transacciones.txt"))) {
            for (Transaccion t : transacciones) {
                writer.printf("Fecha: %s, Descripción: %s, Monto: %.2f, Tipo: %s%n",
                        t.getFecha(), t.getDescripcion(), t.getMonto(), t.getTipo());
            }
            JOptionPane.showMessageDialog(view, "Transacciones guardadas exitosamente en: "
                    + new java.io.File("transacciones.txt").getAbsolutePath());
        } catch (IOException e) {
            JOptionPane.showMessageDialog(view, "Error al guardar las transacciones: " + e.getMessage());
        }
    }

    private void btnSaliActionPerformed() {
        view.dispose(); // Cierra el formulario
    }
}