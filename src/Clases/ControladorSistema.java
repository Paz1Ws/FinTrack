package Clases;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import Clases.ClasesComunes.Transaccion;
import Interfaz.*;

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

    // 1.8. Alerta de caja vacía
    public void retirarSaldo() {
        // Verificar el saldo de caja
        double saldoCaja = transaccionManager.getSaldo();
        if (saldoCaja == 0 || saldoCaja < 0) {
            JOptionPane.showMessageDialog(view, "El saldo de caja es cero o negativo. No se puede realizar un retiro.");
            return;
        }

        // Confirmar retiro
        int confirmacion = JOptionPane.showConfirmDialog(view, "¿Está seguro de que desea retirar el saldo?",
                "Confirmar Retiro", JOptionPane.YES_NO_OPTION);
        if (confirmacion == JOptionPane.NO_OPTION) {
            return;
        }

        JOptionPane.showMessageDialog(view, "Saldo retirado: " + saldoCaja);

        transaccionManager.ajustarSaldo(0);
        actualizarSaldo();
    }

    // 1.6. Mensaje de error para campos vacíos en una transacción
    private void btnAgregarTransActionPerformed() {
        // Solicitar la fecha de la transacción
        String fechaStr = JOptionPane.showInputDialog("Fecha (yyyy MM dd):");
        LocalDate fecha = null;
        if (fechaStr == null || fechaStr.trim().isEmpty()) {
            // 1.10. Fecha automática de transacción
            // Asignar la fecha actual si el campo está en blanco
            fecha = LocalDate.now();
        } else {
            try {
                fechaStr = fechaStr.replace(" ", "-");
                fecha = LocalDate.parse(fechaStr, java.time.format.DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            } catch (DateTimeParseException e) {
                JOptionPane.showMessageDialog(view, "Formato de fecha incorrecto. Use yyyy MM dd.");
                return;
            }
        }
        // Solicitar la descripción de la transacción
        String descripcion = JOptionPane.showInputDialog("Descripción:");
        if (descripcion == null || descripcion.trim().isEmpty()) {
            JOptionPane.showMessageDialog(view, "El campo 'Descripción' no puede estar vacío.");
            return;
        }

        // 1.7. Mensaje de error en monto negativo
        // 1.9. Bloqueo de letras en campo de monto
        double monto = 0;
        boolean validInput = false;
        while (!validInput) {
            try {
                String montoStr = JOptionPane.showInputDialog("Monto:");
                if (montoStr == null || montoStr.trim().isEmpty() || montoStr.equals("0")) {
                    JOptionPane.showMessageDialog(view, "El campo 'Monto' no puede ser cero o vacío.");
                    return;
                }
                monto = Double.parseDouble(montoStr);
                if (monto < 0) {
                    JOptionPane.showMessageDialog(view,
                            "El monto no puede ser negativo. Por favor, ingrese un monto positivo.");
                } else {
                    validInput = true;
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(view, "Monto inválido. Por favor, ingrese un número.");
            }
        }

        // Solicitar el tipo de transacción
        String tipo = JOptionPane.showInputDialog("Tipo (Ingreso/Salida):");
        while (tipo == null || tipo.trim().isEmpty()
                || (!tipo.equalsIgnoreCase("Ingreso") && !tipo.equalsIgnoreCase("Salida"))) {
            tipo = JOptionPane.showInputDialog("Tipo inválido. Por favor, ingrese 'Ingreso' o 'Salida':");
        }

        // Confirmación de registro de transacción
        int confirmacion = JOptionPane.showConfirmDialog(view, "¿Desea guardar esta transacción?", "Confirmación",
                JOptionPane.YES_NO_OPTION);
        if (confirmacion == JOptionPane.YES_OPTION) {
            Transaccion nuevaTransaccion = new Transaccion(fecha, descripcion, monto, tipo);
            transaccionManager.añadirTransaccion(nuevaTransaccion);
            actualizarSaldo();
            actualizarTabla();
        } else {
            JOptionPane.showMessageDialog(view, "Transacción cancelada.");
        }
    }

    private void btnEliminarActionPerformed() {
        int filaSeleccionada = view.getTbMovimientos().getSelectedRow();
        if (filaSeleccionada >= 0) {
            int confirmacion = JOptionPane.showConfirmDialog(view, "¿Desea eliminar esta transacción?", "Confirmación",
                    JOptionPane.YES_NO_OPTION);
            if (confirmacion == JOptionPane.YES_OPTION) {
                transaccionManager.borrarTransaccion(filaSeleccionada);
                actualizarSaldo();
                actualizarTabla();
                JOptionPane.showMessageDialog(view, "Transacción eliminada exitosamente.");
            } else {
                JOptionPane.showMessageDialog(view, "Eliminación de transacción cancelada.");
            }
        } else {
            JOptionPane.showMessageDialog(view, "Selecciona una transacción para eliminar.");
        }
    }

    /// 1.1. Edición rápida de transacciones
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

    // 1.14. Filtro de fechas para generación de reportes
    private void btnGuardarActionPerformed() {
        // Solicitar la fecha de inicio del reporte
        String fechaInicioStr = JOptionPane.showInputDialog("Ingrese la fecha de inicio (yyyy MM dd):");
        final LocalDate fechaInicio;
        if (fechaInicioStr == null || fechaInicioStr.trim().isEmpty()) {
            fechaInicio = null;
        } else {
            try {
                fechaInicioStr = fechaInicioStr.replace(" ", "-");
                fechaInicio = LocalDate.parse(fechaInicioStr,
                        java.time.format.DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            } catch (DateTimeParseException e) {
                JOptionPane.showMessageDialog(view, "Formato de fecha incorrecto. Use yyyy MM dd.");
                return;
            }
        }

        // Solicitar la fecha de fin del reporte
        String fechaFinStr = JOptionPane.showInputDialog("Ingrese la fecha de fin (yyyy MM dd):");
        final LocalDate fechaFin;
        if (fechaFinStr == null || fechaFinStr.trim().isEmpty()) {
            fechaFin = null;
        } else {
            try {
                fechaFinStr = fechaFinStr.replace(" ", "-");
                fechaFin = LocalDate.parse(fechaFinStr, java.time.format.DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            } catch (DateTimeParseException e) {
                JOptionPane.showMessageDialog(view, "Formato de fecha incorrecto. Use yyyy MM dd.");
                return;
            }
        }

        List<Transaccion> transacciones = transaccionManager.getTransacciones();
        List<Transaccion> transaccionesFiltradas;
        if (fechaInicio != null && fechaFin != null) {
            transaccionesFiltradas = transacciones.stream()
                    .filter(t -> !t.getFecha().isBefore(fechaInicio) && !t.getFecha().isAfter(fechaFin))
                    .collect(Collectors.toList());
        } else {
            transaccionesFiltradas = transacciones;
        }

        if (transaccionesFiltradas.isEmpty()) {
            JOptionPane.showMessageDialog(view,
                    "No hay transacciones para guardar en el rango de fechas seleccionado.");
            return;
        }

        // Guardar las transacciones filtradas en el archivo
        try (PrintWriter writer = new PrintWriter(new FileWriter("transacciones.txt"))) {
            for (Transaccion t : transaccionesFiltradas) {
                writer.printf("Fecha: %s, Descripción: %s, Monto: %.2f, Tipo: %s%n",
                        t.getFecha(), t.getDescripcion(), t.getMonto(), t.getTipo());
            }
            JOptionPane.showMessageDialog(view, "Transacciones guardadas exitosamente en: "
                    + new java.io.File("transacciones.txt").getAbsolutePath());
        } catch (IOException e) {
            JOptionPane.showMessageDialog(view, "Error al guardar las transacciones: " + e.getMessage());
        }
    }

    public void visualizarIngresos() {
        // Obtener todas las transacciones
        List<Transaccion> transacciones = transaccionManager.getTransacciones();

        // Filtrar solo las transacciones de tipo "ingreso"
        List<Transaccion> ingresos = transacciones.stream()
                .filter(t -> t.getTipo().equalsIgnoreCase("ingreso"))
                .collect(Collectors.toList());

        if (ingresos.isEmpty()) {
            JOptionPane.showMessageDialog(view, "No hay ingresos para mostrar.");
            return;
        }

        // Mostrar los ingresos
        StringBuilder ingresosStr = new StringBuilder("Listado de Ingresos:\n");
        for (Transaccion t : ingresos) {
            ingresosStr.append(String.format("Fecha: %s, Descripción: %s, Monto: %.2f%n",
                    t.getFecha(), t.getDescripcion(), t.getMonto()));
        }
        JOptionPane.showMessageDialog(view, ingresosStr.toString());

        // Exportar los ingresos a un archivo
        int confirmacion = JOptionPane.showConfirmDialog(view, "¿Desea exportar el listado de ingresos a un archivo?",
                "Exportar Ingresos", JOptionPane.YES_NO_OPTION);
        if (confirmacion == JOptionPane.YES_OPTION) {
            try (PrintWriter writer = new PrintWriter(new FileWriter("ingresos.txt"))) {
                for (Transaccion t : ingresos) {
                    writer.printf("Fecha: %s, Descripción: %s, Monto: %.2f%n",
                            t.getFecha(), t.getDescripcion(), t.getMonto());
                }
                JOptionPane.showMessageDialog(view, "Ingresos exportados exitosamente en: "
                        + new java.io.File("ingresos.txt").getAbsolutePath());
            } catch (IOException e) {
                JOptionPane.showMessageDialog(view, "Error al exportar los ingresos: " + e.getMessage());
            }
        }
    }

    // 1.3. Ajuste manual de saldo
    // 1.15. Contraseña para ajuste de saldo
    // 1.16. Validación de contraseña en ajuste de saldo en la caja
    private void btnAjustarSaldoActionPerformed() {
        // Mostrar la contraseña de prueba
        JOptionPane.showMessageDialog(view, "Contraseña de prueba: FINTRACK123");

        // Solicitar la contraseña de autorización
        String password = JOptionPane.showInputDialog(view, "Ingrese la contraseña de autorización:", "Ajuste de Saldo",
                JOptionPane.PLAIN_MESSAGE);

        // Validar la contraseña
        if (password != null && password.equals("FINTRACK123")) {
            // Solicitar al usuario que ingrese el monto del ajuste
            String montoStr = JOptionPane.showInputDialog(view, "Ingrese el monto del ajuste:", "Ajuste de Saldo",
                    JOptionPane.PLAIN_MESSAGE);
            if (montoStr != null && !montoStr.isEmpty()) {
                try {
                    double montoAjuste = Double.parseDouble(montoStr);
                    // Ajustar el saldo en TransaccionManager
                    transaccionManager.ajustarSaldo(montoAjuste);
                    // Actualizar el saldo en la vista
                    actualizarSaldo();
                    JOptionPane.showMessageDialog(view, "Saldo ajustado exitosamente.");
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(view, "Monto inválido. Por favor, ingrese un número válido.");
                }
            } else {
                JOptionPane.showMessageDialog(view, "Ajuste de saldo cancelado.");
            }
        } else {
            JOptionPane.showMessageDialog(view, "Contraseña incorrecta. Ajuste de saldo no autorizado.");
        }
    }

    // 1.5. Ordenamiento de transacciones por monto
    private void ordenarTransacciones(boolean ascendente) {
        List<Transaccion> transacciones = transaccionManager.getTransacciones();
        if (ascendente) {
            Collections.sort(transacciones, Comparator.comparingDouble(Transaccion::getMonto).reversed());
        } else {
            Collections.sort(transacciones, Comparator.comparingDouble(Transaccion::getMonto));
        }
        // Actualizar la tabla con las transacciones ordenadas
        DefaultTableModel model = (DefaultTableModel) view.getTbMovimientos().getModel();
        model.setRowCount(0); // Limpia la tabla
        for (Transaccion t : transacciones) {
            model.addRow(new Object[] { t.getFecha(), t.getDescripcion(), t.getMonto(), t.getTipo() });
        }
    }

    // 1.12. Confirmación de cierre de caja
    private void btnSaliActionPerformed() {
        visualizarIngresos();
    }
}