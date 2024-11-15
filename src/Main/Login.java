package Main;

import java.util.ArrayList;
import Clases.ClasesComunes.Usuario;

public class Login extends javax.swing.JFrame {
        String nombre;
        String pass;
        @SuppressWarnings({ "rawtypes", "unchecked" })
        public static ArrayList<Usuario> listaDeUsuarios = new ArrayList();

        public Login() {
                initComponents();
                this.setLocationRelativeTo(null);
                Usuario uGlobal = new Usuario();
                uGlobal.setNombre("FINTRACK");
                uGlobal.setPass("DEMO123");
                listaDeUsuarios.add(uGlobal);
        }

        private void initComponents() {
                jPanel1 = new javax.swing.JPanel();
                jLabel1 = new javax.swing.JLabel();
                jLabel2 = new javax.swing.JLabel();
                jLabel3 = new javax.swing.JLabel();
                txtUsuario = new javax.swing.JTextField();
                BtnIngresar = new javax.swing.JButton();
                btnSalir = new javax.swing.JButton();
                txtPass = new javax.swing.JPasswordField();

                setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

                jPanel1.setBackground(new java.awt.Color(204, 255, 255));

                jLabel1.setFont(new java.awt.Font("Yu Gothic UI", 1, 24)); // NOI18N
                jLabel1.setText("FinTrack");

                javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
                jPanel1.setLayout(jPanel1Layout);
                jPanel1Layout.setHorizontalGroup(
                                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(jPanel1Layout.createSequentialGroup()
                                                                .addGap(212, 212, 212)
                                                                .addComponent(jLabel1)
                                                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                Short.MAX_VALUE)));
                jPanel1Layout.setVerticalGroup(
                                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(jPanel1Layout.createSequentialGroup()
                                                                .addGap(40, 40, 40)
                                                                .addComponent(jLabel1,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                42,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addContainerGap(22, Short.MAX_VALUE)));

                jLabel2.setFont(new java.awt.Font("Segoe UI Black", 1, 12)); // NOI18N
                jLabel2.setText("Usuario:");

                jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
                jLabel3.setText("Contraseña:");

                txtUsuario.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                txtUsuarioActionPerformed(evt);
                        }
                });

                BtnIngresar.setBackground(javax.swing.UIManager.getDefaults().getColor("Actions.Blue"));
                BtnIngresar.setText("Ingresar");
                BtnIngresar.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                BtnIngresarActionPerformed(evt);
                        }
                });

                btnSalir.setBackground(javax.swing.UIManager.getDefaults().getColor("Actions.Blue"));
                btnSalir.setText("Salir");
                btnSalir.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                btnSalirActionPerformed(evt);
                        }
                });

                txtPass.setText("");

                javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
                getContentPane().setLayout(layout);
                layout.setHorizontalGroup(
                                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addGroup(layout.createSequentialGroup()
                                                                .addGap(135, 135, 135)
                                                                .addGroup(layout.createParallelGroup(
                                                                                javax.swing.GroupLayout.Alignment.LEADING)
                                                                                .addGroup(layout.createSequentialGroup()
                                                                                                .addGroup(layout.createParallelGroup(
                                                                                                                javax.swing.GroupLayout.Alignment.TRAILING)
                                                                                                                .addComponent(jLabel3,
                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                                71,
                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                                .addComponent(jLabel2))
                                                                                                .addPreferredGap(
                                                                                                                javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                                                .addGroup(layout.createParallelGroup(
                                                                                                                javax.swing.GroupLayout.Alignment.LEADING,
                                                                                                                false)
                                                                                                                .addComponent(txtUsuario,
                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                                160,
                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                                .addGroup(layout.createSequentialGroup()
                                                                                                                                .addComponent(txtPass)
                                                                                                                                .addGap(1, 1, 1))))
                                                                                .addGroup(layout.createSequentialGroup()
                                                                                                .addComponent(BtnIngresar,
                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                95,
                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                .addGap(51, 51, 51)
                                                                                                .addComponent(btnSalir,
                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                90,
                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)))
                                                                .addContainerGap(211, Short.MAX_VALUE)));
                layout.setVerticalGroup(
                                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(layout.createSequentialGroup()
                                                                .addComponent(jPanel1,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addGap(30, 30, 30)
                                                                .addGroup(layout.createParallelGroup(
                                                                                javax.swing.GroupLayout.Alignment.BASELINE)
                                                                                .addComponent(jLabel2)
                                                                                .addComponent(txtUsuario,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                .addPreferredGap(
                                                                                javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addGroup(layout.createParallelGroup(
                                                                                javax.swing.GroupLayout.Alignment.BASELINE)
                                                                                .addComponent(jLabel3)
                                                                                .addComponent(txtPass,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                .addPreferredGap(
                                                                                javax.swing.LayoutStyle.ComponentPlacement.RELATED,
                                                                                23, Short.MAX_VALUE)
                                                                .addGroup(layout.createParallelGroup(
                                                                                javax.swing.GroupLayout.Alignment.BASELINE)
                                                                                .addComponent(BtnIngresar)
                                                                                .addComponent(btnSalir))
                                                                .addGap(67, 67, 67)));

                pack();
        }// </editor-fold>//GEN-END:initComponents

        private void txtUsuarioActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_txtUsuarioActionPerformed
        }// GEN-LAST:event_txtUsuarioActionPerformed

        private void BtnIngresarActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_BtnIngresarActionPerformed
                // Maneja el evento de acción del botón "Ingresar"
                String usuario = txtUsuario.getText();
                String contrasena = new String(txtPass.getPassword());

                // Verifica si las credenciales son correctas
                boolean credencialesCorrectas = false;
                for (Usuario u : listaDeUsuarios) {
                        if (u.getNombre().equals(usuario) && u.getPass().equals(contrasena)) {
                                credencialesCorrectas = true;
                                break;
                        }
                }

                if (credencialesCorrectas) {
                        new Thread(new Runnable() {
                                public void run() {
                                        dispose();
                                }
                        }).start();
                } else {
                        // Si las credenciales son incorrectas, muestra un mensaje
                        javax.swing.JOptionPane.showMessageDialog(this, "Usuario o contraseña incorrectos.");
                }
        }// GEN-LAST:event_BtnIngresarActionPerformed

        private void btnSalirActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_btnSalirActionPerformed
                System.exit(0); // Cierra la aplicación
        }// GEN-LAST:event_btnSalirActionPerformed

        /**
         * @param args the command line arguments
         */
        public static void main(String args[]) {
                // Configuración del look and feel y creación del formulario de login
                java.awt.EventQueue.invokeLater(new Runnable() {
                        public void run() {
                                new Login().setVisible(true);
                        }
                });
        }

        // Variables declaration - do not modify//GEN-BEGIN:variables
        private javax.swing.JButton BtnIngresar;
        private javax.swing.JButton btnSalir;
        private javax.swing.JLabel jLabel1;
        private javax.swing.JLabel jLabel2;
        private javax.swing.JLabel jLabel3;
        private javax.swing.JPanel jPanel1;
        private javax.swing.JPasswordField txtPass;
        private javax.swing.JTextField txtUsuario;
        // End of variables declaration//GEN-END:variables
}