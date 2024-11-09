package Interfaz;

import javax.swing.*;
import javax.swing.event.TableModelEvent;
import javax.swing.table.DefaultTableModel;
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

        public VistaSistema() {
                initComponents();
        }

        private void initComponents() {
                jPanel1 = new JPanel();
                jLabel2 = new JLabel();
                jPanel2 = new JPanel();
                jLabel1 = new JLabel();
                txtMonto = new JTextField();
                BtnActualizar = new JButton();
                jLabel3 = new JLabel();
                jScrollPane1 = new JScrollPane();
                TbMovimientos = new JTable();
                btnAgregarTrans = new JButton();
                btnEliminar = new JButton();
                jPanel3 = new JPanel();
                jLabel4 = new JLabel();
                jLabel5 = new JLabel();
                DcInicio = new JDateChooser();
                BtnAplicar = new JButton();
                jLabel6 = new JLabel();
                DcFin = new JDateChooser();
                btnGuardar = new JButton();
                btnSali = new JButton();
                btnEditar = new JButton();

                setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

                jPanel1.setBackground(new java.awt.Color(204, 255, 204));

                jLabel2.setFont(new java.awt.Font("Franklin Gothic Medium Cond", 1, 24)); // NOI18N
                jLabel2.setText("Flujo de Caja");

                GroupLayout jPanel1Layout = new GroupLayout(jPanel1);
                jPanel1.setLayout(jPanel1Layout);
                jPanel1Layout.setHorizontalGroup(
                                jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                .addGroup(jPanel1Layout.createSequentialGroup()
                                                                .addGap(303, 303, 303)
                                                                .addComponent(jLabel2)
                                                                .addContainerGap(GroupLayout.DEFAULT_SIZE,
                                                                                Short.MAX_VALUE)));
                jPanel1Layout.setVerticalGroup(
                                jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                .addGroup(GroupLayout.Alignment.TRAILING, jPanel1Layout
                                                                .createSequentialGroup()
                                                                .addGap(12, 12, 12)
                                                                .addComponent(jLabel2, GroupLayout.PREFERRED_SIZE, 22,
                                                                                Short.MAX_VALUE)
                                                                .addContainerGap()));

                jPanel2.setBackground(new java.awt.Color(204, 204, 255));

                jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
                jLabel1.setText("Resumen de caja");

                txtMonto.setText("00.00");

                BtnActualizar.setFont(new java.awt.Font("Segoe UI", 1, 8)); // NOI18N
                BtnActualizar.setText("Actualizar");

                GroupLayout jPanel2Layout = new GroupLayout(jPanel2);
                jPanel2.setLayout(jPanel2Layout);
                jPanel2Layout.setHorizontalGroup(
                                jPanel2Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                .addGroup(jPanel2Layout.createSequentialGroup()
                                                                .addContainerGap()
                                                                .addComponent(jLabel1, GroupLayout.PREFERRED_SIZE, 99,
                                                                                GroupLayout.PREFERRED_SIZE)
                                                                .addGap(18, 18, 18)
                                                                .addComponent(txtMonto, GroupLayout.PREFERRED_SIZE, 71,
                                                                                GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(
                                                                                LayoutStyle.ComponentPlacement.UNRELATED)
                                                                .addComponent(BtnActualizar, GroupLayout.PREFERRED_SIZE,
                                                                                71, GroupLayout.PREFERRED_SIZE)
                                                                .addContainerGap(7, Short.MAX_VALUE)));
                jPanel2Layout.setVerticalGroup(
                                jPanel2Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                .addGroup(jPanel2Layout.createSequentialGroup()
                                                                .addContainerGap()
                                                                .addGroup(jPanel2Layout.createParallelGroup(
                                                                                GroupLayout.Alignment.BASELINE)
                                                                                .addComponent(jLabel1)
                                                                                .addComponent(txtMonto,
                                                                                                GroupLayout.PREFERRED_SIZE,
                                                                                                GroupLayout.DEFAULT_SIZE,
                                                                                                GroupLayout.PREFERRED_SIZE)
                                                                                .addComponent(BtnActualizar))
                                                                .addContainerGap(GroupLayout.DEFAULT_SIZE,
                                                                                Short.MAX_VALUE)));

                jLabel3.setBackground(new java.awt.Color(102, 102, 255));
                jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
                jLabel3.setText("Entradas y salidas");

                TbMovimientos.setBackground(new java.awt.Color(255, 255, 204));
                TbMovimientos.setModel(new DefaultTableModel(
                                new Object[][] {
                                                { null, null, null, null },
                                                { null, null, null, null },
                                                { null, null, null, null },
                                                { null, null, null, null }
                                },
                                new String[] {
                                                "Fecha", "Descripción ", "Monto ", "Tipo"
                                }));
                // 1.11. Confirmación de actualización de monto
                TbMovimientos.getModel().addTableModelListener(e -> {
                        if (e.getType() == TableModelEvent.UPDATE) {
                                int row = e.getFirstRow();
                                int column = e.getColumn();
                                if (column == 2) { // Si la columna modificada es "Monto"
                                        DefaultTableModel model = (DefaultTableModel) TbMovimientos.getModel();
                                        Object oldValue = model.getValueAt(row, column);
                                        int confirmacion = JOptionPane.showConfirmDialog(null,
                                                        "¿Está seguro de que desea actualizar el monto?",
                                                        "Confirmar Actualización", JOptionPane.YES_NO_OPTION);

                                        if (confirmacion != JOptionPane.YES_OPTION) {
                                                model.setValueAt(oldValue, row, column);
                                        }
                                }
                        }
                });
                jScrollPane1.setViewportView(TbMovimientos);

                btnAgregarTrans.setText("Agregar");

                btnEliminar.setText("Eliminar");

                jLabel4.setFont(new java.awt.Font("Segoe UI Black", 1, 14)); // NOI18N
                jLabel4.setText("Filtrar por fecha:");

                jLabel5.setText("Inicio:");

                BtnAplicar.setText("Aplicar filtros");

                jLabel6.setText("Fin:");

                GroupLayout jPanel3Layout = new GroupLayout(jPanel3);
                jPanel3.setLayout(jPanel3Layout);
                jPanel3Layout.setHorizontalGroup(
                                jPanel3Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                .addGroup(GroupLayout.Alignment.TRAILING, jPanel3Layout
                                                                .createSequentialGroup()
                                                                .addContainerGap(30, Short.MAX_VALUE)
                                                                .addGroup(jPanel3Layout.createParallelGroup(
                                                                                GroupLayout.Alignment.LEADING)
                                                                                .addComponent(BtnAplicar)
                                                                                .addComponent(jLabel4)
                                                                                .addGroup(jPanel3Layout
                                                                                                .createSequentialGroup()
                                                                                                .addComponent(jLabel5,
                                                                                                                GroupLayout.PREFERRED_SIZE,
                                                                                                                37,
                                                                                                                GroupLayout.PREFERRED_SIZE)
                                                                                                .addPreferredGap(
                                                                                                                LayoutStyle.ComponentPlacement.RELATED)
                                                                                                .addComponent(DcInicio,
                                                                                                                GroupLayout.PREFERRED_SIZE,
                                                                                                                GroupLayout.DEFAULT_SIZE,
                                                                                                                GroupLayout.PREFERRED_SIZE)
                                                                                                .addGap(18, 18, 18)
                                                                                                .addComponent(jLabel6,
                                                                                                                GroupLayout.PREFERRED_SIZE,
                                                                                                                37,
                                                                                                                GroupLayout.PREFERRED_SIZE)
                                                                                                .addPreferredGap(
                                                                                                                LayoutStyle.ComponentPlacement.RELATED)
                                                                                                .addComponent(DcFin,
                                                                                                                GroupLayout.PREFERRED_SIZE,
                                                                                                                GroupLayout.DEFAULT_SIZE,
                                                                                                                GroupLayout.PREFERRED_SIZE)))
                                                                .addContainerGap()));
                jPanel3Layout.setVerticalGroup(
                                jPanel3Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                .addGroup(GroupLayout.Alignment.TRAILING, jPanel3Layout
                                                                .createSequentialGroup()
                                                                .addGap(0, 15, Short.MAX_VALUE)
                                                                .addComponent(jLabel4, GroupLayout.PREFERRED_SIZE, 16,
                                                                                GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(
                                                                                LayoutStyle.ComponentPlacement.UNRELATED)
                                                                .addGroup(jPanel3Layout.createParallelGroup(
                                                                                GroupLayout.Alignment.LEADING)
                                                                                .addComponent(jLabel5)
                                                                                .addComponent(DcInicio,
                                                                                                GroupLayout.PREFERRED_SIZE,
                                                                                                GroupLayout.DEFAULT_SIZE,
                                                                                                GroupLayout.PREFERRED_SIZE)
                                                                                .addComponent(DcFin,
                                                                                                GroupLayout.Alignment.TRAILING,
                                                                                                GroupLayout.PREFERRED_SIZE,
                                                                                                GroupLayout.DEFAULT_SIZE,
                                                                                                GroupLayout.PREFERRED_SIZE)
                                                                                .addComponent(jLabel6))
                                                                .addPreferredGap(
                                                                                LayoutStyle.ComponentPlacement.UNRELATED)
                                                                .addComponent(BtnAplicar)));

                btnGuardar.setText("Guardar");

                btnSali.setText("Salir");

                GroupLayout layout = new GroupLayout(getContentPane());
                getContentPane().setLayout(layout);
                layout.setHorizontalGroup(
                                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                .addComponent(jPanel1, GroupLayout.DEFAULT_SIZE,
                                                                GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                                .addGap(0, 0, Short.MAX_VALUE)
                                                                .addComponent(jPanel2, GroupLayout.PREFERRED_SIZE,
                                                                                GroupLayout.DEFAULT_SIZE,
                                                                                GroupLayout.PREFERRED_SIZE))
                                                .addGroup(layout.createSequentialGroup()
                                                                .addContainerGap()
                                                                .addGroup(layout.createParallelGroup(
                                                                                GroupLayout.Alignment.LEADING)
                                                                                .addGroup(layout.createSequentialGroup()
                                                                                                .addComponent(jScrollPane1,
                                                                                                                GroupLayout.DEFAULT_SIZE,
                                                                                                                628,
                                                                                                                Short.MAX_VALUE)
                                                                                                .addGap(28, 28, 28)
                                                                                                .addGroup(layout.createParallelGroup(
                                                                                                                GroupLayout.Alignment.LEADING)
                                                                                                                .addGroup(layout
                                                                                                                                .createParallelGroup(
                                                                                                                                                GroupLayout.Alignment.LEADING)
                                                                                                                                .addComponent(btnAgregarTrans)
                                                                                                                                .addComponent(btnEliminar))
                                                                                                                .addComponent(btnGuardar))
                                                                                                .addGap(25, 25, 25))
                                                                                .addGroup(layout.createSequentialGroup()
                                                                                                .addGap(106, 106, 106)
                                                                                                .addComponent(jLabel3,
                                                                                                                GroupLayout.PREFERRED_SIZE,
                                                                                                                169,
                                                                                                                GroupLayout.PREFERRED_SIZE)
                                                                                                .addContainerGap(
                                                                                                                GroupLayout.DEFAULT_SIZE,
                                                                                                                Short.MAX_VALUE))
                                                                                .addGroup(layout.createSequentialGroup()
                                                                                                .addComponent(jPanel3,
                                                                                                                GroupLayout.PREFERRED_SIZE,
                                                                                                                GroupLayout.DEFAULT_SIZE,
                                                                                                                GroupLayout.PREFERRED_SIZE)
                                                                                                .addContainerGap(
                                                                                                                GroupLayout.DEFAULT_SIZE,
                                                                                                                Short.MAX_VALUE))))
                                                .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                                .addContainerGap(GroupLayout.DEFAULT_SIZE,
                                                                                Short.MAX_VALUE)
                                                                .addComponent(btnSali)
                                                                .addContainerGap()));
                layout.setVerticalGroup(
                                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                .addGroup(layout.createSequentialGroup()
                                                                .addComponent(jPanel1, GroupLayout.PREFERRED_SIZE,
                                                                                GroupLayout.DEFAULT_SIZE,
                                                                                GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(jPanel2, GroupLayout.PREFERRED_SIZE,
                                                                                GroupLayout.DEFAULT_SIZE,
                                                                                GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(
                                                                                LayoutStyle.ComponentPlacement.UNRELATED)
                                                                .addComponent(jLabel3)
                                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                                .addGroup(layout.createParallelGroup(
                                                                                GroupLayout.Alignment.LEADING)
                                                                                .addGroup(layout.createSequentialGroup()
                                                                                                .addGap(9, 9, 9)
                                                                                                .addComponent(btnAgregarTrans)
                                                                                                .addGap(18, 18, 18)
                                                                                                .addComponent(btnEliminar)
                                                                                                .addGap(18, 18, 18)
                                                                                                .addComponent(btnGuardar))
                                                                                .addComponent(jScrollPane1,
                                                                                                GroupLayout.PREFERRED_SIZE,
                                                                                                150,
                                                                                                GroupLayout.PREFERRED_SIZE))
                                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(jPanel3, GroupLayout.PREFERRED_SIZE,
                                                                                GroupLayout.DEFAULT_SIZE,
                                                                                GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED,
                                                                                GroupLayout.DEFAULT_SIZE,
                                                                                Short.MAX_VALUE)
                                                                .addComponent(btnSali)));

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

        public JButton getBtnGuardar() {
                return btnGuardar;
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
}