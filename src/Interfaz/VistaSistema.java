package Interfaz;

import javax.swing.*;
import com.toedter.calendar.JDateChooser;

public class VistaSistema extends JFrame {

        private JPanel jPanel1;
        private JLabel jLabel1;
        private JTextField txtMonto;
        private JButton BtnActualizar;
        private JScrollPane jScrollPane1;
        private JTable TbMovimientos;
        private JButton btnAgregarTrans;
        private JButton btnEliminar;
        private JButton btnEditar;
        private JButton btnGuardar;
        private JButton btnSali;
        private JDateChooser DcInicio;
        private JDateChooser DcFin;
        private JButton BtnAplicar;
        private JLabel jLabel2;
        private JPanel jPanel2;
        private JLabel jLabel3;
        private JPanel jPanel3;
        private JLabel jLabel4;
        private JLabel jLabel5;
        private JLabel jLabel6;
        private JButton btnOrdenar;
        private JButton btnRetiro;
        private JButton jButton1;
        private JButton btnAjustarSaldo;

        private JTextField txtFecha;
        private JTextField txtDescripcion;
        private JTextField txtMontoTransaccion;
        private JTextField txtDocRespaldo;
        private JTextField txtIdDoc;
        private JComboBox<String> cbTipoTransaccion;
        private JButton btnRegistrarTransaccion;
        private JRadioButton rbIngreso;
        private JRadioButton rbSalida;
        private JComboBox<String> cbDocumentoRespaldo;
        private ButtonGroup grupoTipoTransaccion;

        public VistaSistema() {
                initComponents();
        }

        private void initComponents() {
                jButton1 = new javax.swing.JButton();
                jPanel1 = new javax.swing.JPanel();
                jLabel2 = new javax.swing.JLabel();
                jPanel2 = new javax.swing.JPanel();
                jLabel1 = new javax.swing.JLabel();
                txtMonto = new javax.swing.JTextField();
                btnAjustarSaldo = new javax.swing.JButton();
                jLabel3 = new javax.swing.JLabel();
                jScrollPane1 = new javax.swing.JScrollPane();
                TbMovimientos = new javax.swing.JTable();
                TbMovimientos.setBackground(new java.awt.Color(255, 255, 204));
                TbMovimientos.setModel(new javax.swing.table.DefaultTableModel(
                                new Object[][] {
                                                { null, null, null, null, null, null },
                                                { null, null, null, null, null, null },
                                                { null, null, null, null, null, null },
                                                { null, null, null, null, null, null }
                                },
                                new String[] {
                                                "Fecha", "Descripción ", "Monto ", "Tipo", "Doc. Respaldo",
                                                "Id Doc."
                                }));
                jScrollPane1.setViewportView(TbMovimientos);
                btnAgregarTrans = new javax.swing.JButton();
                btnEliminar = new javax.swing.JButton();
                jPanel3 = new javax.swing.JPanel();
                jLabel4 = new javax.swing.JLabel();
                jLabel5 = new javax.swing.JLabel();
                DcInicio = new com.toedter.calendar.JDateChooser();
                BtnAplicar = new javax.swing.JButton();
                jLabel6 = new javax.swing.JLabel();
                DcFin = new com.toedter.calendar.JDateChooser();
                btnGuardar = new javax.swing.JButton();
                btnSali = new javax.swing.JButton();
                btnOrdenar = new javax.swing.JButton();
                btnRetiro = new javax.swing.JButton();
                btnEditar = new javax.swing.JButton();
                BtnActualizar = new javax.swing.JButton();

                jButton1.setText("jButton1");
                txtFecha = new JTextField();
                txtDescripcion = new JTextField();
                txtMontoTransaccion = new JTextField();
                txtDocRespaldo = new JTextField();
                txtIdDoc = new JTextField();
                cbTipoTransaccion = new JComboBox<>(new String[] { "Ingreso", "Salida" });
                btnRegistrarTransaccion = new JButton("Registrar Transacción");

                rbIngreso = new JRadioButton("Ingreso", true);
                rbSalida = new JRadioButton("Salida");
                grupoTipoTransaccion = new ButtonGroup();
                grupoTipoTransaccion.add(rbIngreso);
                grupoTipoTransaccion.add(rbSalida);

                cbDocumentoRespaldo = new JComboBox<>(new String[] { "Boleta", "Factura" });
                txtFecha.setToolTipText("Fecha (yyyy-MM-dd)");
                txtDescripcion.setToolTipText("Descripción");
                txtMontoTransaccion.setToolTipText("Monto");
                txtDocRespaldo.setToolTipText("Documento de Respaldo");
                txtIdDoc.setToolTipText("Nombre del Documento");
                cbTipoTransaccion.setToolTipText("Tipo de Transacción");
                JPanel panelTransaccion = new JPanel();
                panelTransaccion.setLayout(new BoxLayout(panelTransaccion, BoxLayout.Y_AXIS));
                panelTransaccion.setLayout(new BoxLayout(panelTransaccion, BoxLayout.Y_AXIS));
                panelTransaccion.add(new JLabel("Fecha:"));
                panelTransaccion.add(txtFecha);
                panelTransaccion.add(new JLabel("Descripción:"));
                panelTransaccion.add(txtDescripcion);
                panelTransaccion.add(new JLabel("Monto:"));
                panelTransaccion.add(txtMontoTransaccion);
                panelTransaccion.add(new JLabel("Tipo de Transacción:"));
                panelTransaccion.add(rbIngreso);
                panelTransaccion.add(rbSalida);
                panelTransaccion.add(new JLabel("Documento de Respaldo: "));
                panelTransaccion.add(cbDocumentoRespaldo);
                panelTransaccion.add(new JLabel("Id Doc.: "));
                panelTransaccion.add(txtIdDoc);
                panelTransaccion.add(btnRegistrarTransaccion);

                // Añadir el panel a la ventana principal
                getContentPane().add(panelTransaccion);

                setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
                setBackground(new java.awt.Color(204, 255, 204));

                jPanel1.setBackground(new java.awt.Color(204, 255, 204));

                jLabel2.setFont(new java.awt.Font("Franklin Gothic Medium Cond", 1, 24)); // NOI18N
                jLabel2.setText("Flujo de Caja");

                javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
                jPanel1.setLayout(jPanel1Layout);
                jPanel1Layout.setHorizontalGroup(
                                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(jPanel1Layout.createSequentialGroup()
                                                                .addGap(303, 303, 303)
                                                                .addComponent(jLabel2)
                                                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                Short.MAX_VALUE)));
                jPanel1Layout.setVerticalGroup(
                                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout
                                                                .createSequentialGroup()
                                                                .addGap(12, 12, 12)
                                                                .addComponent(jLabel2,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                22, Short.MAX_VALUE)
                                                                .addContainerGap()));

                jPanel2.setBackground(new java.awt.Color(204, 204, 255));

                jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
                jLabel1.setText("Resumen de caja");

                txtMonto.setText("00.00");

                btnAjustarSaldo.setFont(new java.awt.Font("Segoe UI", 1, 8)); // NOI18N
                btnAjustarSaldo.setText("Ajustar");
                btnAjustarSaldo.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                btnAjustarSaldoActionPerformed();
                        }
                });

                javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
                jPanel2.setLayout(jPanel2Layout);
                jPanel2Layout.setHorizontalGroup(
                                jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(jPanel2Layout.createSequentialGroup()
                                                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                Short.MAX_VALUE)
                                                                .addComponent(jLabel1,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                99,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addGap(18, 18, 18)
                                                                .addComponent(txtMonto,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                71,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addGap(18, 18, 18)
                                                                .addComponent(btnAjustarSaldo)
                                                                .addGap(25, 25, 25)));
                jPanel2Layout.setVerticalGroup(
                                jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(jPanel2Layout.createSequentialGroup()
                                                                .addContainerGap()
                                                                .addGroup(jPanel2Layout.createParallelGroup(
                                                                                javax.swing.GroupLayout.Alignment.BASELINE)
                                                                                .addComponent(jLabel1)
                                                                                .addComponent(txtMonto,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                .addComponent(btnAjustarSaldo,
                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                Short.MAX_VALUE))
                                                                .addContainerGap(16, Short.MAX_VALUE)));

                jLabel3.setBackground(new java.awt.Color(102, 102, 255));
                jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
                jLabel3.setText("Entradas y salidas");

                btnAgregarTrans.setText("Agregar");
                btnAgregarTrans.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                btnAgregarTransActionPerformed();
                        }
                });

                btnEliminar.setText("Eliminar");
                btnEliminar.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                btnEliminarActionPerformed();
                        }
                });

                jLabel4.setFont(new java.awt.Font("Segoe UI Black", 1, 14)); // NOI18N
                jLabel4.setText("Filtrar por fecha:");

                jLabel5.setText("Inicio:");

                BtnAplicar.setText("Aplicar filtros");
                BtnAplicar.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                BtnAplicarActionPerformed();
                        }
                });

                jLabel6.setText("Fin:");

                javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
                jPanel3.setLayout(jPanel3Layout);
                jPanel3Layout.setHorizontalGroup(
                                jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout
                                                                .createSequentialGroup()
                                                                .addContainerGap(30, Short.MAX_VALUE)
                                                                .addGroup(jPanel3Layout.createParallelGroup(
                                                                                javax.swing.GroupLayout.Alignment.LEADING)
                                                                                .addComponent(BtnAplicar)
                                                                                .addComponent(jLabel4)
                                                                                .addGroup(jPanel3Layout
                                                                                                .createSequentialGroup()
                                                                                                .addComponent(jLabel5,
                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                37,
                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                .addPreferredGap(
                                                                                                                javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                                                .addComponent(DcInicio,
                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                .addGap(18, 18, 18)
                                                                                                .addComponent(jLabel6,
                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                37,
                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                .addPreferredGap(
                                                                                                                javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                                                .addComponent(DcFin,
                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)))
                                                                .addContainerGap()));
                jPanel3Layout.setVerticalGroup(
                                jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout
                                                                .createSequentialGroup()
                                                                .addGap(0, 15, Short.MAX_VALUE)
                                                                .addComponent(jLabel4,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                16,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(
                                                                                javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                                .addGroup(jPanel3Layout.createParallelGroup(
                                                                                javax.swing.GroupLayout.Alignment.LEADING)
                                                                                .addComponent(jLabel5)
                                                                                .addComponent(DcInicio,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                .addComponent(DcFin,
                                                                                                javax.swing.GroupLayout.Alignment.TRAILING,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                .addComponent(jLabel6))
                                                                .addPreferredGap(
                                                                                javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                                .addComponent(BtnAplicar)));

                btnGuardar.setText("Guardar");
                btnGuardar.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                btnGuardarActionPerformed();
                        }
                });

                btnSali.setText("Salir");
                btnSali.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                getBtnSali();
                        }
                });

                btnOrdenar.setFont(new java.awt.Font("Segoe UI", 1, 8)); // NOI18N
                btnOrdenar.setText("Ordenar");
                btnOrdenar.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                btnOrdenarActionPerformed();
                        }
                });

                btnRetiro.setText("Retiro");
                btnRetiro.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                btnRetiroActionPerformed();
                        }
                });

                javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
                getContentPane().setLayout(layout);
                layout.setHorizontalGroup(
                                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addGroup(layout.createSequentialGroup()
                                                                .addContainerGap()
                                                                .addGroup(layout.createParallelGroup(
                                                                                javax.swing.GroupLayout.Alignment.LEADING)
                                                                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING,
                                                                                                layout
                                                                                                                .createSequentialGroup()
                                                                                                                .addGroup(layout
                                                                                                                                .createParallelGroup(
                                                                                                                                                javax.swing.GroupLayout.Alignment.TRAILING)
                                                                                                                                .addGroup(layout.createSequentialGroup()
                                                                                                                                                .addComponent(jPanel3,
                                                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                                                                .addPreferredGap(
                                                                                                                                                                javax.swing.LayoutStyle.ComponentPlacement.RELATED,
                                                                                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                                                                                Short.MAX_VALUE)
                                                                                                                                                .addComponent(btnOrdenar,
                                                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                                                                71,
                                                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                                                                                .addComponent(jScrollPane1,
                                                                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                                                                631,
                                                                                                                                                Short.MAX_VALUE))
                                                                                                                .addGap(25, 25, 25)
                                                                                                                .addGroup(layout
                                                                                                                                .createParallelGroup(
                                                                                                                                                javax.swing.GroupLayout.Alignment.LEADING)
                                                                                                                                .addGroup(layout
                                                                                                                                                .createParallelGroup(
                                                                                                                                                                javax.swing.GroupLayout.Alignment.TRAILING)
                                                                                                                                                .addComponent(btnAgregarTrans)
                                                                                                                                                .addComponent(btnEliminar))
                                                                                                                                .addComponent(btnGuardar))
                                                                                                                .addGap(25, 25, 25))
                                                                                .addGroup(layout.createSequentialGroup()
                                                                                                .addGap(106, 106, 106)
                                                                                                .addComponent(jLabel3,
                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                169,
                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                .addContainerGap(
                                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                                Short.MAX_VALUE))))
                                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout
                                                                .createSequentialGroup()
                                                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                Short.MAX_VALUE)
                                                                .addComponent(btnSali)
                                                                .addGap(18, 18, 18)
                                                                .addComponent(btnRetiro)
                                                                .addContainerGap())
                                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout
                                                                .createSequentialGroup()
                                                                .addGap(0, 0, Short.MAX_VALUE)
                                                                .addComponent(jPanel2,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)));
                layout.setVerticalGroup(
                                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(layout.createSequentialGroup()
                                                                .addComponent(jPanel1,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(
                                                                                javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(jPanel2,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(
                                                                                javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                                .addComponent(jLabel3)
                                                                .addPreferredGap(
                                                                                javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addGroup(layout.createParallelGroup(
                                                                                javax.swing.GroupLayout.Alignment.LEADING)
                                                                                .addGroup(layout.createSequentialGroup()
                                                                                                .addGap(9, 9, 9)
                                                                                                .addComponent(btnAgregarTrans)
                                                                                                .addGap(18, 18, 18)
                                                                                                .addComponent(btnEliminar)
                                                                                                .addGap(18, 18, 18)
                                                                                                .addComponent(btnGuardar))
                                                                                .addComponent(jScrollPane1,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                150,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                .addPreferredGap(
                                                                                javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addGroup(layout.createParallelGroup(
                                                                                javax.swing.GroupLayout.Alignment.LEADING)
                                                                                .addComponent(jPanel3,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                .addComponent(btnOrdenar))
                                                                .addPreferredGap(
                                                                                javax.swing.LayoutStyle.ComponentPlacement.RELATED,
                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                Short.MAX_VALUE)
                                                                .addGroup(layout.createParallelGroup(
                                                                                javax.swing.GroupLayout.Alignment.BASELINE)
                                                                                .addComponent(btnSali)
                                                                                .addComponent(btnRetiro))));

                pack();
        }

        // Getters for UI components to add action listeners in the controller
        public JTextField getTxtMonto() {
                return txtMonto;
        }

        public JButton getBtnActualizar() {
                return BtnActualizar;
        }

        public JTable getTbMovimientos() {
                return TbMovimientos;
        }

        public JButton getBtnAgregarTrans() {
                return btnAgregarTrans;
        }

        public JButton getBtnEliminar() {
                return btnEliminar;
        }

        public JButton getBtnEditar() {
                return btnEditar;
        }

        public JButton getBtnSali() {
                return btnSali;
        }

        public JDateChooser getDcInicio() {
                return DcInicio;
        }

        public JDateChooser getDcFin() {
                return DcFin;
        }

        public JButton getBtnAplicar() {
                return BtnAplicar;
        }

        public JButton btnAjustarSaldoActionPerformed() {
                return btnAjustarSaldo;
        }

        public JButton btnEliminarActionPerformed() {
                return btnEliminar;
        }

        public JButton BtnAplicarActionPerformed() {
                return BtnAplicar;
        }

        public JButton btnGuardarActionPerformed() {
                return btnGuardar;
        }

        public JButton btnOrdenarActionPerformed() {
                return btnOrdenar;
        }

        public JButton btnRetiroActionPerformed() {
                return btnRetiro;
        }

        public JButton btnAgregarTransActionPerformed() {
                return btnAgregarTrans;
        }

        public JTextField getTxtFecha() {
                return txtFecha;
        }

        public JTextField getTxtDescripcion() {
                return txtDescripcion;
        }

        public JTextField getTxtMontoTransaccion() {
                return txtMontoTransaccion;
        }

        public JComboBox<String> getCbTipoTransaccion() {
                return cbTipoTransaccion;
        }

        public JButton getBtnRegistrarTransaccion() {
                return btnRegistrarTransaccion;
        }

        public JTextField getTxtDocRespaldo() {
                return txtDocRespaldo;
        }

        public JTextField getTxtIdDoc() {
                return txtIdDoc;
        }

        public JRadioButton getRbIngreso() {
                return rbIngreso;
        }

        public JRadioButton getRbSalida() {
                return rbSalida;
        }

        public JComboBox<String> getCbDocumentoRespaldo() {
                return cbDocumentoRespaldo;
        }

}
