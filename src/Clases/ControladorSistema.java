package Clases;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.sql.*;
import javax.swing.ButtonGroup;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.table.DefaultTableModel;
import Clases.ClasesComunes.Transaccion;
import Clases.ClasesComunes.TransaccionDO;
import Clases.ClasesComunes.ConexionBDFT;
import Interfaz.*;
import Main.Login;

public class ControladorSistema {
    private TransaccionManager transaccionManager;
    private VistaSistema view;
    @SuppressWarnings("unused")
    private Login login;

    public ControladorSistema(TransaccionManager transaccionManager, VistaSistema view, Login login) {
        this.transaccionManager = transaccionManager;
        this.view = view;
        this.login = login;
        initListeners();
        view.setVisible(false); // Inicialmente ocultamos la vista del sistema
        login.setVisible(true); // Mostramos la vista de login

        // Añadimos un listener para detectar cuando se cierra la ventana de login
        login.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                // Cuando se cierra la ventana de login, mostramos la vista del sistema
                view.setVisible(true);
                actualizarSaldo();
                actualizarTabla();
            }
        });
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

        view.getBtnSali().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                btnSaliActionPerformed();
            }
        });
        view.btnGuardarActionPerformed().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                btnGuardarActionPerformed();
            }
        });
        view.btnAjustarSaldoActionPerformed().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                btnAjustarSaldoActionPerformed();

            }
        });

        view.btnOrdenarActionPerformed().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ordenarTransacciones(true);
            }
        });
        view.btnRetiroActionPerformed().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                retirarSaldo();
            }
        });
        view.getBtnRegistrarTransaccion().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                registrarTransaccion();
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
            model.addRow(new Object[] { t.getFecha(), t.getDescripcion(), t.getMonto(), t.getTipo(), t.getDocRespaldo(),
                    t.getIdDoc() });
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

        // Solicitar la contraseña de autorización
        String password = JOptionPane.showInputDialog(view, "Ingrese la contraseña de autorización:", "Ajuste de Saldo",
                JOptionPane.PLAIN_MESSAGE);

        // Validar la contraseña
        if (password != null && password.equals("FINTRACK123")) {
            JOptionPane.showMessageDialog(view, "Saldo retirado: " + saldoCaja);
            transaccionManager.ajustarSaldo(0);
            actualizarSaldo();
        } else {
            JOptionPane.showMessageDialog(view, "Contraseña incorrecta. Retiro de saldo no autorizado.");
        }
    }

  private void btnAgregarTransActionPerformed() {
    LocalDate fecha = null;
    String descripcion = "";
    double monto = 0;
    String tipo = "";
    String documento = "";
    final String[] idDocumento = { "" };
    TransaccionDO transaccionDO = new TransaccionDO(); // DAO para manejo de la base de datos

    while (true) {
        try {
            // Solicitar la fecha de la transacción
            String fechaStr = JOptionPane.showInputDialog("Fecha (yyyy MM dd):", fecha != null ? fecha.toString() : "");
            if (fechaStr == null || fechaStr.trim().isEmpty()) {
                fecha = LocalDate.now();
            } else {
                fechaStr = fechaStr.replace(" ", "-");
                fecha = LocalDate.parse(fechaStr, java.time.format.DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            }

            // Solicitar la descripción de la transacción
            descripcion = JOptionPane.showInputDialog("Descripción:", descripcion);
            if (descripcion == null || descripcion.trim().isEmpty()) {
                JOptionPane.showMessageDialog(view, "El campo 'Descripción' no puede estar vacío.");
                continue;
            }

            // Solicitar el monto de la transacción
            boolean validInput = false;
            while (!validInput) {
                try {
                    String montoStr = JOptionPane.showInputDialog("Monto:", monto != 0 ? String.valueOf(monto) : "");
                    if (montoStr == null || montoStr.trim().isEmpty() || montoStr.equals("0")) {
                        JOptionPane.showMessageDialog(view, "El campo 'Monto' no puede ser cero o vacío.");
                        continue;
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

            // Crear un panel personalizado para las opciones
            JPanel panel = new JPanel(new GridLayout(3, 2));
            ButtonGroup grupoTipo = new ButtonGroup();
            JRadioButton rbIngreso = new JRadioButton("Ingreso", tipo.equals("Ingreso"));
            JRadioButton rbSalida = new JRadioButton("Salida", tipo.equals("Salida"));
            grupoTipo.add(rbIngreso);
            grupoTipo.add(rbSalida);

            JComboBox<String> comboDocumento = new JComboBox<>(new String[] { "Boleta", "Factura" });
            comboDocumento.setSelectedItem(documento);

            panel.add(new JLabel("Tipo de Transacción:"));
            panel.add(rbIngreso);
            panel.add(new JLabel(""));
            panel.add(rbSalida);
            panel.add(new JLabel("Documento de Respaldo: "));
            panel.add(comboDocumento);

            // Mostrar el panel
            int result = JOptionPane.showConfirmDialog(view, panel, "Seleccione el tipo y documento",
                    JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

            if (result != JOptionPane.OK_OPTION) {
                JOptionPane.showMessageDialog(view, "Operación cancelada.");
                return;
            }

            // Determinar los valores seleccionados
            tipo = rbIngreso.isSelected() ? "Ingreso" : "Salida";
            documento = (String) comboDocumento.getSelectedItem();

            // Solicitar el número o nombre del documento
            idDocumento[0] = JOptionPane
                    .showInputDialog("Ingrese el nombre o número del documento (" + documento + "):", idDocumento[0]);
            if (idDocumento[0] == null || idDocumento[0].trim().isEmpty()) {
                JOptionPane.showMessageDialog(view, "El nombre o número del documento no puede estar vacío.");
                continue;
            }

            // Validar que no haya IDs repetidos en la base de datos
            List<Transaccion> transacciones = transaccionDO.leerTransacciones();
            boolean idRepetido = transacciones.stream().anyMatch(t -> t.getIdDoc().equals(idDocumento[0]));
            if (idRepetido) {
                JOptionPane.showMessageDialog(view, "Error: El ID del documento ya existe. Por favor, ingrese un ID único.");
                continue;
            }

            // Confirmación de registro de transacción
            int confirmacion = JOptionPane.showConfirmDialog(view, "¿Desea guardar esta transacción?", "Confirmación",
                    JOptionPane.YES_NO_OPTION);
            if (confirmacion == JOptionPane.YES_OPTION) {
                Transaccion nuevaTransaccion = new Transaccion(fecha, descripcion, monto, tipo, documento,
                        idDocumento[0]);
                transaccionDO.crearTransaccion(nuevaTransaccion);
                transaccionManager.añadirTransaccion(nuevaTransaccion);
                JOptionPane.showMessageDialog(view, "Transacción registrada con éxito.");
                actualizarSaldo();
                actualizarTabla();
                break;
            } else {
                JOptionPane.showMessageDialog(view, "Transacción cancelada.");
                return;
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(view, "Error al interactuar con la base de datos: " + e.getMessage());
            return;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(view, "Error inesperado: " + e.getMessage());
            return;
        }
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

            Transaccion transaccionEditada = new Transaccion(fecha, descripcion, monto, tipo, "", "");
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

        DefaultTableModel model = (DefaultTableModel) view.getTbMovimientos().getModel();
        List<Transaccion> transaccionesFiltradas = new ArrayList<>();

        for (int i = 0; i < model.getRowCount(); i++) {
            LocalDate fecha = LocalDate.parse(model.getValueAt(i, 0).toString(),
                    java.time.format.DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            String descripcion = model.getValueAt(i, 1).toString();
            double monto = Double.parseDouble(model.getValueAt(i, 2).toString());
            String tipo = model.getValueAt(i, 3).toString();
            String docRespaldo = model.getValueAt(i, 4).toString();
            String idDoc = model.getValueAt(i, 5).toString();

            if ((fechaInicio == null || !fecha.isBefore(fechaInicio))
                    && (fechaFin == null || !fecha.isAfter(fechaFin))) {
                transaccionesFiltradas.add(new Transaccion(fecha, descripcion, monto, tipo, docRespaldo, idDoc));
            }
        }

        if (transaccionesFiltradas.isEmpty()) {
            JOptionPane.showMessageDialog(view,
                    "No hay transacciones para guardar en el rango de fechas seleccionado.");
            return;
        }

        // Guardar las transacciones filtradas en un archivo PDF
        Document document = new Document();
        try {
            PdfWriter.getInstance(document, new FileOutputStream("transacciones.pdf"));
            document.open();

            // Agregar una imagen en el lado superior izquierdo
            Image image = Image.getInstance("assets/images.jpg");
            image.scaleToFit(100, 100); // Ajusta el tamaño de la imagen
            image.setAlignment(Element.ALIGN_LEFT);
            document.add(image);

            // Agregar un título
            Font titleFont = new Font(Font.FontFamily.HELVETICA, 18, Font.BOLD, BaseColor.BLACK);
            Paragraph title = new Paragraph("Reporte de Transacciones", titleFont);
            title.setAlignment(Element.ALIGN_CENTER);
            document.add(title);

            // Agregar un espacio
            document.add(new Paragraph(" "));

            // Crear una tabla para las transacciones
            PdfPTable table = new PdfPTable(6); // 6 columnas
            table.setWidthPercentage(100);
            table.setSpacingBefore(10f);
            table.setSpacingAfter(10f);

            // Encabezados de la tabla
            Font headerFont = new Font(Font.FontFamily.HELVETICA, 12, Font.BOLD, BaseColor.WHITE);
            PdfPCell headerCell;

            headerCell = new PdfPCell(new Phrase("Fecha", headerFont));
            headerCell.setBackgroundColor(BaseColor.GRAY);
            headerCell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(headerCell);

            headerCell = new PdfPCell(new Phrase("Descripción", headerFont));
            headerCell.setBackgroundColor(BaseColor.GRAY);
            headerCell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(headerCell);

            headerCell = new PdfPCell(new Phrase("Monto", headerFont));
            headerCell.setBackgroundColor(BaseColor.GRAY);
            headerCell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(headerCell);

            headerCell = new PdfPCell(new Phrase("Tipo", headerFont));
            headerCell.setBackgroundColor(BaseColor.GRAY);
            headerCell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(headerCell);

            headerCell = new PdfPCell(new Phrase("Doc. Respaldo", headerFont));
            headerCell.setBackgroundColor(BaseColor.GRAY);
            headerCell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(headerCell);

            headerCell = new PdfPCell(new Phrase("Id Doc.", headerFont));
            headerCell.setBackgroundColor(BaseColor.GRAY);
            headerCell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(headerCell);

            // Agregar las transacciones a la tabla
            Font cellFont = new Font(Font.FontFamily.HELVETICA, 12, Font.NORMAL, BaseColor.BLACK);
            for (Transaccion t : transaccionesFiltradas) {
                table.addCell(new PdfPCell(new Phrase(t.getFecha().toString(), cellFont)));
                table.addCell(new PdfPCell(new Phrase(t.getDescripcion(), cellFont)));
                table.addCell(new PdfPCell(new Phrase(String.format("%.2f", t.getMonto()), cellFont)));
                table.addCell(new PdfPCell(new Phrase(t.getTipo(), cellFont)));
                table.addCell(new PdfPCell(new Phrase(t.getDocRespaldo(), cellFont)));
                table.addCell(new PdfPCell(new Phrase(t.getIdDoc(), cellFont)));
            }

            document.add(table);

            JOptionPane.showMessageDialog(view, "Transacciones guardadas exitosamente en: "
                    + new java.io.File("transacciones.pdf").getAbsolutePath());
        } catch (DocumentException | IOException e) {
            JOptionPane.showMessageDialog(view, "Error al guardar las transacciones: " + e.getMessage());
        } finally {
            document.close();
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
            model.addRow(new Object[] { t.getFecha(), t.getDescripcion(), t.getMonto(), t.getTipo(), t.getDocRespaldo(),
                    t.getIdDoc() });
        }
    }

    private void registrarTransaccion() {
        try {
            String fechaStr = view.getTxtFecha().getText();
            LocalDate fecha = LocalDate.parse(fechaStr, java.time.format.DateTimeFormatter.ofPattern("yyyy-MM-dd"));

            String descripcion = view.getTxtDescripcion().getText();
            if (descripcion.trim().isEmpty()) {
                JOptionPane.showMessageDialog(view, "El campo 'Descripción' no puede estar vacío.");
                return;
            }

            double monto = Double.parseDouble(view.getTxtMontoTransaccion().getText());
            if (monto <= 0) {
                JOptionPane.showMessageDialog(view, "El monto debe ser mayor que cero.");
                return;
            }

            String tipo = (String) view.getCbTipoTransaccion().getSelectedItem();

            Transaccion nuevaTransaccion = new Transaccion(fecha, descripcion, monto, tipo, "", "");
            transaccionManager.añadirTransaccion(nuevaTransaccion);
            actualizarSaldo();
            actualizarTabla();
            JOptionPane.showMessageDialog(view, "Transacción registrada exitosamente.");
        } catch (DateTimeParseException e) {
            JOptionPane.showMessageDialog(view, "Formato de fecha incorrecto. Use yyyy-MM-dd.");
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(view, "Monto inválido. Por favor, ingrese un número.");
        }
    }

    // 1.12. Confirmación de cierre de caja
    private void btnSaliActionPerformed() {
        view.dispose();
    }

}