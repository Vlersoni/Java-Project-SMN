/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import LidhjaMeDatabaz.LidhjaDB;
import Modelet_Tabelat.Adresat;
import java.awt.HeadlessException;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.MessageFormat;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

/**
 *
 * @author Blerton
 */
public class AdresaRexhister extends javax.swing.JFrame {

    /**
     * Creates new form AdresaRexhister
     */
    public AdresaRexhister() {
        initComponents();
        trego_adresat();
        pastro();
//        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE
        ikonaProgramit();
    }
     private void ikonaProgramit() {
       setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("icon48.png")));
    }

    public boolean ekziston(String a1,String a2,String a3,String a4,String a5){
        try{
	 Connection con = LidhjaDB.ConnectDB();	
												
            String sql= "SELECT * FROM tbl_Adresat";
            PreparedStatement pst = con.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            while(rs.next()){
		if(a1.equals(rs.getString("shteti")) && a2.equals(rs.getString("qyteti")) && a3.equals(rs.getString("zipcode")) && a4.equals(rs.getString("rruga")) && a5.equals(rs.getString("nrRruges"))){
                    return true;
                }	
            }	
            return false;
	}
	catch(SQLException e){
		JOptionPane.showMessageDialog(null,e);
        }		
        return false;
    }
    
    
     public ArrayList<Adresat> adresatList(){
        ArrayList<Adresat> teDhenatList = new ArrayList<>();
         try{
            Connection con = LidhjaDB.ConnectDB();
            String query1=" SELECT * FROM tbl_Adresat";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(query1);
            Adresat teDhenatA;
            while(rs.next()){
                teDhenatA = new Adresat(rs.getInt("adresaID"), rs.getString("shteti"),rs.getString("qyteti"),rs.getInt("zipcode"),rs.getString("rruga"), rs.getInt("nrRruges"));
                teDhenatList.add(teDhenatA);
            }
         }catch (HeadlessException | SQLException e) {
          JOptionPane.showMessageDialog(null,e); 
        }
         return teDhenatList;
    }
      //Metoda qe tregon Te Adresat
    public final void trego_adresat(){
        ArrayList<Adresat> list = adresatList();
        DefaultTableModel model = (DefaultTableModel)tbl1.getModel();
        Object[] row = new Object[6];
        for(int i =0;i<list.size();i++){
            row[0]=list.get(i).getID();
            row[1]= list.get(i).getShteti();
            row[2]= list.get(i).getQyteti();
            row[3]= list.get(i).getZipCode();
            row[4]= list.get(i).getRruga();
            row[5]= list.get(i).getNrRruges();         
            model.addRow(row);
        }
        String countIKonvertuar = Integer.toString(list.size());
            mesazhZ.setText(countIKonvertuar);
    }
    public void pastro(){
        txt_shteti.setText("");
        txt_qyteti.setText("");
        txt_zipCode.setText("");
        txt_rruga.setText("");
        txt_nrRruges.setText("");
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txt_shteti = new javax.swing.JTextField();
        txt_qyteti = new javax.swing.JTextField();
        txt_zipCode = new javax.swing.JTextField();
        txt_rruga = new javax.swing.JTextField();
        txt_nrRruges = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbl1 = new javax.swing.JTable();
        mesazhZ = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();

        setTitle("Regjistrimi Adresave");
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Regjistrimi Adresave"));

        jLabel1.setText("Shteti");

        jLabel2.setText("Qyteti");

        jLabel3.setText("Zip Code");

        jLabel4.setText("Rruga");

        jLabel5.setText("Nr Rruges");

        txt_shteti.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_shtetiKeyTyped(evt);
            }
        });

        txt_zipCode.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_zipCodeActionPerformed(evt);
            }
        });
        txt_zipCode.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_zipCodeKeyTyped(evt);
            }
        });

        txt_nrRruges.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_nrRrugesActionPerformed(evt);
            }
        });
        txt_nrRruges.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_nrRrugesKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, 63, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txt_shteti)
                    .addComponent(txt_qyteti)
                    .addComponent(txt_zipCode, javax.swing.GroupLayout.DEFAULT_SIZE, 131, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txt_rruga, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addGap(53, 53, 53)
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txt_nrRruges, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(111, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txt_shteti, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_rruga, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txt_qyteti, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_nrRruges, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txt_zipCode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(17, Short.MAX_VALUE))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jButton1.setText("Ruaj");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Permirso");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton4.setText("Dalje");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton5.setText("Anulo");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jButton3.setText("Printo Adresat");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(227, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton3))
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        tbl1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Shteti", "Qyteti", "ZipCode", "Rruga", "NrRruges"
            }
        ));
        tbl1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbl1);

        mesazhZ.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        mesazhZ.setForeground(new java.awt.Color(255, 51, 51));
        mesazhZ.setText("jLabel6");

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel7.setText("Numri adresave që keni regjistruar");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(29, 29, 29)
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(mesazhZ, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(21, 21, 21)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 656, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(29, 29, 29)
                                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(43, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 269, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(mesazhZ, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(9, 9, 9))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        hide();
    }//GEN-LAST:event_jButton4ActionPerformed

    private void txt_zipCodeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_zipCodeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_zipCodeActionPerformed

    private void txt_nrRrugesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_nrRrugesActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_nrRrugesActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        try{
             Connection con = LidhjaDB.ConnectDB();	
             String query ="insert into tbl_Adresat(shteti,qyteti,zipcode,rruga,nrRruges)values(?,?,?,?,?)";
             PreparedStatement pst = con.prepareStatement(query);
            
            if(txt_shteti.getText().equals("")){
               JOptionPane.showMessageDialog( this, "Plotso rubriken Shteti","Mesazh nga Autori", JOptionPane.ERROR_MESSAGE);
            }
            else if(txt_qyteti.getText().equals("")){
             JOptionPane.showMessageDialog( this, "Plotso rubriken Qyteti ","Mesazh nga Autori", JOptionPane.ERROR_MESSAGE);
            }
            else if(txt_zipCode.getText().equals("")){
             JOptionPane.showMessageDialog( this, "Plotso rubriken Zipcode","Mesazh nga Autori", JOptionPane.ERROR_MESSAGE);
            }
            else if(txt_rruga.getText().equals("")){
             JOptionPane.showMessageDialog( this, "Plotso rubriken Rruga","Mesazh nga Autori", JOptionPane.ERROR_MESSAGE);
            }
            else if(txt_nrRruges.getText().equals("")){
             JOptionPane.showMessageDialog( this, "Plotso rubriken nrRruges","Mesazh nga Autori", JOptionPane.ERROR_MESSAGE);
            }
            else if(ekziston(txt_shteti.getText(),txt_qyteti.getText(),txt_zipCode.getText(),txt_rruga.getText(),txt_nrRruges.getText())){
                JOptionPane.showMessageDialog( this, "Kjo Adres esht rexhistruar me par ne databaz","Mesazh nga Autori", JOptionPane.ERROR_MESSAGE);
                pastro();
            }
            else{
                pst.setString(1,txt_shteti.getText());
                pst.setString(2,txt_qyteti.getText());
                pst.setString(3,txt_zipCode.getText());
                pst.setString(4,txt_rruga.getText());
                pst.setString(5,txt_nrRruges.getText());
                pst.executeUpdate();
                DefaultTableModel model = (DefaultTableModel)tbl1.getModel();
                model.setRowCount(0);
                trego_adresat();
                JOptionPane.showMessageDialog(null,"Te dhenat u rexhistruan ne databaze");
                pastro();
            }              
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null,e);
        }              
    }//GEN-LAST:event_jButton1ActionPerformed

    private void tbl1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl1MouseClicked
        int i = tbl1.getSelectedRow();
       TableModel model = tbl1.getModel();
       txt_shteti.setText(model.getValueAt(i,1).toString());
       txt_qyteti.setText(model.getValueAt(i,2).toString());
       txt_zipCode.setText(model.getValueAt(i,3).toString());
       txt_rruga.setText(model.getValueAt(i,4).toString());
       txt_nrRruges.setText(model.getValueAt(i,5).toString());
       
    }//GEN-LAST:event_tbl1MouseClicked

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        try{
            int row = tbl1.getSelectedRow();
            if(tbl1.getSelectedRow() == -1){
                    JOptionPane.showMessageDialog(this ,"Ju nuk mund te Editoni asnje shfrytezues nese nuk e selektoni!","Mesazh nga Autori", JOptionPane.ERROR_MESSAGE);             
            }
            else{
            Connection con = LidhjaDB.ConnectDB();
            String IdESelektuar = (tbl1.getModel().getValueAt(row, 0).toString());
            String query =" UPDATE tbl_Adresat SET shteti=?,qyteti=?,zipcode=?,rruga=?,nrRruges=? where adresaID="+IdESelektuar;
            PreparedStatement pst = con.prepareStatement(query);  
             if(txt_shteti.getText().equals("")){
               JOptionPane.showMessageDialog( this, "Shkruaj Emrin e kompanis","Mesazh nga Autori", JOptionPane.ERROR_MESSAGE);
            }
            else if(txt_qyteti.getText().equals("")){
             JOptionPane.showMessageDialog( this, "Shkruaj Emrin e shtetit","Mesazh nga Autori", JOptionPane.ERROR_MESSAGE);
            }
            else if(txt_zipCode.getText().equals("")){
             JOptionPane.showMessageDialog( this, "Shkruaj emrin e qytetit","Mesazh nga Autori", JOptionPane.ERROR_MESSAGE);
            }
            else if(txt_rruga.getText().equals("")){
             JOptionPane.showMessageDialog( this, "Shkruaj emrin e zipcode","Mesazh nga Autori", JOptionPane.ERROR_MESSAGE);
            }
            else if(txt_nrRruges.getText().equals("")){
             JOptionPane.showMessageDialog( this, "Shkruaj emrin e zipcode","Mesazh nga Autori", JOptionPane.ERROR_MESSAGE);
            }
            else if(ekziston(txt_shteti.getText(),txt_qyteti.getText(),txt_zipCode.getText(),txt_rruga.getText(),txt_nrRruges.getText())){
                JOptionPane.showMessageDialog( this, "Kjo Adres esht rexhistruar me par ne databaz","Mesazh nga Autori", JOptionPane.ERROR_MESSAGE);
                pastro();
            }
            else{
                 Object [] ob = {"Vazhdo","Anulo"};
                    int p = javax.swing.JOptionPane.showOptionDialog(this, "Pas permirsimit te gjitha ndertesat qe e kan këtë Id do ta ndrrojn adresen sipas editimit!. A jeni i sigurt qe deshironi ta editoni ?", "Konfirmim", JOptionPane.OK_OPTION,JOptionPane.QUESTION_MESSAGE, null, ob, ob[1]);
            
                    if (p==0){
                pst.setString(1,txt_shteti.getText());
                pst.setString(2,txt_qyteti.getText());
                pst.setString(3,txt_zipCode.getText());
                pst.setString(4,txt_rruga.getText());
                pst.setString(5,txt_nrRruges.getText());
                pst.executeUpdate();
                DefaultTableModel model = (DefaultTableModel)tbl1.getModel();
                model.setRowCount(0);
                trego_adresat();
                JOptionPane.showMessageDialog(null,"Te dhenat mbi adresen u edituan me sukses");
                pastro();
                }
            }
            }
         } catch (HeadlessException | SQLException e) {
          JOptionPane.showMessageDialog(null,e); 
        }    
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
       pastro();
    }//GEN-LAST:event_jButton5ActionPerformed

    private void txt_nrRrugesKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_nrRrugesKeyTyped
          char c = evt.getKeyChar();
      if (!(Character.isDigit(c)|| (c== KeyEvent.VK_BACK_SPACE)||(c==KeyEvent.VK_DELETE))){
          getToolkit().beep();
          evt.consume();
          }
    }//GEN-LAST:event_txt_nrRrugesKeyTyped

    private void txt_zipCodeKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_zipCodeKeyTyped
         char c = evt.getKeyChar();
      if (!(Character.isDigit(c)|| (c== KeyEvent.VK_BACK_SPACE)||(c==KeyEvent.VK_DELETE))){
          getToolkit().beep();
          evt.consume();
          }
    }//GEN-LAST:event_txt_zipCodeKeyTyped

    private void txt_shtetiKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_shtetiKeyTyped
//     char key = (char) evt.getKeyCode();
//    if (key == KeyEvent.VK_ENTER){
//     transferFocus(next());
//    }
//     
    }//GEN-LAST:event_txt_shtetiKeyTyped

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        try{
            MessageFormat header = new MessageFormat("Kontaktet e puntorve");
            MessageFormat footer = new MessageFormat("Faqe(1)");
            tbl1.print(JTable.PrintMode.FIT_WIDTH, header, footer);
        }catch(Exception e){
            JOptionPane.showConfirmDialog(this, e);
        }
    }//GEN-LAST:event_jButton3ActionPerformed

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
            java.util.logging.Logger.getLogger(AdresaRexhister.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AdresaRexhister.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AdresaRexhister.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AdresaRexhister.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AdresaRexhister().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel mesazhZ;
    private javax.swing.JTable tbl1;
    private javax.swing.JTextField txt_nrRruges;
    private javax.swing.JTextField txt_qyteti;
    private javax.swing.JTextField txt_rruga;
    private javax.swing.JTextField txt_shteti;
    private javax.swing.JTextField txt_zipCode;
    // End of variables declaration//GEN-END:variables
}
