package projectmanagementsystem;

import java.sql.SQLException;
import javax.swing.JOptionPane;

public class LoginMenu extends javax.swing.JFrame {

    public LoginMenu() {
        initComponents();
        setVisible(true);
        setLocationRelativeTo(null);
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        RegisterEmployee = new javax.swing.JButton();
        Login = new javax.swing.JButton();
        dbCreate = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        quit = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        RegisterEmployee.setText("Registrera anställd");
        RegisterEmployee.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RegisterEmployeeActionPerformed(evt);
            }
        });

        Login.setText("Logga in");
        Login.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                LoginActionPerformed(evt);
            }
        });

        dbCreate.setText("Skapa databasen");
        dbCreate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dbCreateActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel1.setText("Start");

        quit.setText("Avsluta programmet");
        quit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                quitActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(RegisterEmployee, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(Login, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(dbCreate, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(quit, javax.swing.GroupLayout.DEFAULT_SIZE, 142, Short.MAX_VALUE))
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(66, 66, 66)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Login, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(RegisterEmployee, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(dbCreate, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(quit, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void RegisterEmployeeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RegisterEmployeeActionPerformed
        setVisible(false);
        LMRegisterEmployee re = new LMRegisterEmployee(this);
    }//GEN-LAST:event_RegisterEmployeeActionPerformed

    private void LoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_LoginActionPerformed
        setVisible(false);
        LMLogin log = new LMLogin(this);
    }//GEN-LAST:event_LoginActionPerformed

    private void dbCreateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dbCreateActionPerformed
        int reply = JOptionPane.showConfirmDialog(null, "Har du redan skapat en database"
                + " kommer den tas bort om du fortsätter"
                + "\nÄr du säker på att du vill fortsätta?", "Potentiell förlust av data", JOptionPane.YES_NO_OPTION,
                JOptionPane.WARNING_MESSAGE);

        if (reply == JOptionPane.YES_OPTION) {
            DatabaseCalls dbc = new DatabaseCalls();
            try {
                dbc.createDatabase();
                JOptionPane.showMessageDialog(null, "Databasen har skapats");
            } catch (SQLException | ClassNotFoundException ex) {
            }
        }
    }//GEN-LAST:event_dbCreateActionPerformed

    private void quitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_quitActionPerformed
        System.exit(0);
    }//GEN-LAST:event_quitActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Login;
    private javax.swing.JButton RegisterEmployee;
    private javax.swing.JButton dbCreate;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JButton quit;
    // End of variables declaration//GEN-END:variables
}
