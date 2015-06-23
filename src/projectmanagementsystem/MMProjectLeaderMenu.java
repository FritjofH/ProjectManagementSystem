package projectmanagementsystem;

import java.io.IOException;
import java.sql.SQLException;

public class MMProjectLeaderMenu extends javax.swing.JFrame {

    int userid;
    MainMenu mm;

    public MMProjectLeaderMenu(MainMenu mm, int userid) {
        initComponents();
        setLocationRelativeTo(null);//Mitten av skärmen
        setVisible(true);//gör den synlig
        this.mm = mm;
        this.userid = userid;
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        registerProjectMember = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        projectParticipant = new javax.swing.JButton();
        projectFollowup = new javax.swing.JButton();
        registerProject = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();

        registerProjectMember.setText("Registrera projektdeltagare");
        registerProjectMember.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                registerProjectMemberActionPerformed(evt);
            }
        });

        jButton1.setText("jButton1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel1.setText("Projektledarmeny");

        projectParticipant.setText("Tilldela projektdeltagare");
        projectParticipant.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                projectParticipantActionPerformed(evt);
            }
        });

        projectFollowup.setText("Projektuppföljning");
        projectFollowup.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                projectFollowupActionPerformed(evt);
            }
        });

        registerProject.setText("Registrera ett projekt");
        registerProject.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                registerProjectActionPerformed(evt);
            }
        });

        jButton2.setText("Startmeny");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(projectFollowup, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(10, 10, 10))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(registerProject, javax.swing.GroupLayout.DEFAULT_SIZE, 212, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(projectParticipant, javax.swing.GroupLayout.DEFAULT_SIZE, 212, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(11, 11, 11)
                .addComponent(registerProject, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(projectParticipant, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(projectFollowup, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void registerProjectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_registerProjectActionPerformed
        setVisible(false);
        PLRegisterProject rp = new PLRegisterProject(this, userid);
    }//GEN-LAST:event_registerProjectActionPerformed

    private void registerProjectMemberActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_registerProjectMemberActionPerformed
        //skräpkod som inte fungerar
    }//GEN-LAST:event_registerProjectMemberActionPerformed

    private void projectParticipantActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_projectParticipantActionPerformed
        setVisible(false);
        try {
            PLManageProjectMembers mpm = new PLManageProjectMembers(this, userid);
        } catch (IOException | ClassNotFoundException | SQLException ex) {
        }
    }//GEN-LAST:event_projectParticipantActionPerformed

    private void projectFollowupActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_projectFollowupActionPerformed
        setVisible(false);
        try {
            PLProjectFollowup pf = new PLProjectFollowup(this, userid);
        } catch (IOException | ClassNotFoundException | SQLException ex) {
        }
    }//GEN-LAST:event_projectFollowupActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        this.dispose();
        mm.setVisible(true);
    }//GEN-LAST:event_jButton2ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JButton projectFollowup;
    private javax.swing.JButton projectParticipant;
    private javax.swing.JButton registerProject;
    private javax.swing.JButton registerProjectMember;
    // End of variables declaration//GEN-END:variables
}
