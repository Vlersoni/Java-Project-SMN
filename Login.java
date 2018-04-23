/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import LidhjaMeDatabaz.LidhjaDB;
import Modelet_Tabelat.Sektori;
import Modelet_Tabelat.Shfrytzust_tbl;
import Modelet_Tabelat.TeDhenatEKompanis_tbl;
import java.awt.HeadlessException;
import java.awt.Toolkit;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Vhaliti
 */

public class Login extends javax.swing.JFrame {

    /**
     * Creates new form Login
     */
     
     
    public Login() {
        initComponents();
//      filljcombo();
        setIcon();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        MesazhZ = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        pasword = new javax.swing.JPasswordField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        Konfirmo = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        MesazhY = new javax.swing.JLabel();
        username = new javax.swing.JTextField();
        smsY = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Kyqja"); // NOI18N

        MesazhZ.setFont(new java.awt.Font("Bell MT", 0, 24)); // NOI18N
        MesazhZ.setText("Autorizohuni për punë");

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        pasword.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                paswordActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel2.setText("Shfrytzuesi");

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel3.setText("Fjalkalimi");

        Konfirmo.setText("Konfirmo");
        Konfirmo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                KonfirmoActionPerformed(evt);
            }
        });

        jButton2.setText("Anulo");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        MesazhY.setBackground(new java.awt.Color(255, 51, 51));
        MesazhY.setForeground(new java.awt.Color(255, 51, 51));

        username.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                usernameActionPerformed(evt);
            }
        });

        smsY.setForeground(new java.awt.Color(255, 0, 0));
        smsY.setText(" ");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(username, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(smsY, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(pasword, javax.swing.GroupLayout.DEFAULT_SIZE, 124, Short.MAX_VALUE))))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(Konfirmo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 1, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(40, 40, 40))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(MesazhY, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(username, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(smsY)
                .addGap(3, 3, 3)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pasword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(MesazhY, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton2)
                    .addComponent(Konfirmo))
                .addContainerGap())
        );

        jTextField1.setEditable(false);
        jTextField1.setBackground(new java.awt.Color(204, 204, 204));

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/GUI/123.png"))); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap(23, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(42, 42, 42)
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 327, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(77, 77, 77)
                        .addComponent(MesazhZ, javax.swing.GroupLayout.PREFERRED_SIZE, 257, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(MesazhZ)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 2, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(22, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    //metoda reset
    
    public ArrayList<Shfrytzust_tbl> ListaShfrytzusve(String a){
        ArrayList<Shfrytzust_tbl> listaShfrytzusve = new ArrayList<>();
         try{
                Connection con = LidhjaDB.ConnectDB();	               
                String query1="SELECT n.shfrytzuesiID,tbl_Puntori.emri,tbl_Puntori.mbiemri,n.username,n.fjalkalimi, n.privilegji\n" +
            "FROM tbl_Shfrytzuesi n\n" +
            "INNER JOIN tbl_Puntori ON n.puntoriID = tbl_Puntori.puntoriID";
                Statement st = con.createStatement();
                ResultSet rs = st.executeQuery(query1);
                Shfrytzust_tbl shfrytzust;
            while(rs.next()){
                if(a.equals(rs.getString("username"))){
                shfrytzust = new Shfrytzust_tbl(rs.getInt("shfrytzuesiID"),rs.getString("emri"),rs.getString("mbiemri"),rs.getString("username"), rs.getString("fjalkalimi"), rs.getString("privilegji"));
                listaShfrytzusve.add(shfrytzust);
                }
            }
         }catch (HeadlessException | SQLException e) {
          JOptionPane.showMessageDialog(null,e); 
        }
         return listaShfrytzusve;
    }
     public void Trego_Poziten(String a){   
         ArrayList<Shfrytzust_tbl> list = ListaShfrytzusve(a);
         int c=0;
         String abc="";
         abc = list.get(0).getPozita();
         if(abc == null){
            abc="admin";
            jTextField1.setText(abc);
         }else{
         jTextField1.setText(abc);
         }
     }
    
    public void Reset(){
        pasword.setText("");
        username.setText("");
    }
    
    private void KonfirmoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_KonfirmoActionPerformed
           try{
                    if(username.getText().equals(""))
                        smsY.setText("Plotso rubrikën!");
                    else
                           smsY.setText(" ");
                     if(pasword.getText().equals(""))
                         MesazhY.setText("Plotso rubrikën!");
                     else
                         MesazhY.setText(" ");
                if(!pasword.getText().equals("") && !username.getText().equals("")){
                   Connection con = LidhjaDB.ConnectDB();
                   String a="1";
                   String sql = "Select * from tbl_Shfrytzuesi where username=? and fjalkalimi = ?";
                   
                   PreparedStatement pst = con.prepareStatement(sql);
                   pst.setString(1, username.getText());
                   pst.setString(2, pasword.getText());
                   ResultSet rs = pst.executeQuery();
                   if(rs.next()){
                     
                      String dergesa = username.getText().toUpperCase();
                       Trego_Poziten(dergesa); 
                       String sql1 = "UPDATE tbl_Sektori SET Shfrytzuesi=?, privilegji=? where SektoriID = "+a;
                       PreparedStatement pst1 = con.prepareStatement(sql1);
                       pst1.setString(1, dergesa);
                       pst1.setString(2,jTextField1.getText());
                       pst1.executeUpdate();  
                       Kryefaqja k = new Kryefaqja();
                       k.setVisible(true);
                       hide();
                   }
                   else{
                       JOptionPane.showMessageDialog(null,"Pasvordi esht gabim!");
                       Reset();
                   }
               }
        }catch(HeadlessException | SQLException e){
            JOptionPane.showMessageDialog(null,e);
        } catch (ParseException ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        }                            
    }//GEN-LAST:event_KonfirmoActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        System.exit(0);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void paswordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_paswordActionPerformed
          try{
                    if(username.getText().equals(""))
                        smsY.setText("Plotso rubrikën!");
                    else
                           smsY.setText(" ");
                     if(pasword.getText().equals(""))
                         MesazhY.setText("Plotso rubrikën!");
                     else
                         MesazhY.setText(" ");
                if(!pasword.getText().equals("") && !username.getText().equals("")){
                   Connection con = LidhjaDB.ConnectDB();
                   String a="1";
                   String sql = "Select * from tbl_Shfrytzuesi where username=? and fjalkalimi = ?";
                   
                   PreparedStatement pst = con.prepareStatement(sql);
                   pst.setString(1, username.getText());
                   pst.setString(2, pasword.getText());
                   ResultSet rs = pst.executeQuery();
                   

                   if(rs.next()){
                       String dergesa = username.getText().toUpperCase();
                       Trego_Poziten(dergesa); 
                       String sql1 = "UPDATE tbl_Sektori SET Shfrytzuesi=?, privilegji=? where SektoriID = "+a;
                       PreparedStatement pst1 = con.prepareStatement(sql1);
                       pst1.setString(1, dergesa);
                       pst1.setString(2,jTextField1.getText());
                       pst1.executeUpdate();  
                       Kryefaqja k = new Kryefaqja();
                       k.setVisible(true);
                       hide();
                   }
                   else{
                       JOptionPane.showMessageDialog(null,"Kontrolloni emrin e shfrytzusit dhe paswordin");
                       Reset();
                   }
               }
        }catch(HeadlessException | SQLException e){
            JOptionPane.showMessageDialog(null,e);
        } catch (ParseException ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        }                                               
    }//GEN-LAST:event_paswordActionPerformed

    private void usernameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_usernameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_usernameActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Login().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Konfirmo;
    private javax.swing.JLabel MesazhY;
    private javax.swing.JLabel MesazhZ;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JPasswordField pasword;
    private javax.swing.JLabel smsY;
    private javax.swing.JTextField username;
    // End of variables declaration//GEN-END:variables

    private void setIcon() {
       setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("icon48.png")));
       
    }
}
