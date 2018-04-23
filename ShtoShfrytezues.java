/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import LidhjaMeDatabaz.LidhjaDB;
import Modelet_Tabelat.Shfrytzust_tbl;
import Modelet_Tabelat.TeDhenatEpuntorve_tbl;
import java.awt.HeadlessException;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import static javafx.scene.input.KeyCode.P;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

/**
 *
 * @author Vhaliti
 */
public class ShtoShfrytezues extends javax.swing.JFrame {
//    private Shfrytzust_tbl [] shf; 
    /**
     * Creates new form ShtoShfrytezues
     */
    public ShtoShfrytezues() {
       
        initComponents();
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        trego_shfrytzust();
        ikonaProgramit();
        trego_Puntoret();
//        btnFshije.setEnabled(false);
    }
    private void ikonaProgramit() {
       setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("icon48.png")));
    }
//    Connection con = LidhjaDB.ConnectDB();
    //Metoda qe e shikon a esht i rexhistruar shfrytzusi me par
    public boolean ekziston(String shfrytzusiRi){
        try{
	 Connection con = LidhjaDB.ConnectDB();	
												
            String sql= "Select username from tbl_Shfrytzuesi";
            PreparedStatement pst = con.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            while(rs.next()){
		if(shfrytzusiRi.equals(rs.getString("username"))){
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
   
     public ArrayList<TeDhenatEpuntorve_tbl> puntoretEKompanisList(){
        ArrayList<TeDhenatEpuntorve_tbl> teDhenatList = new ArrayList<>();
         try{
            Connection con = LidhjaDB.ConnectDB();
            String query1=" SELECT n.puntoriID,n.emri,n.mbiemri, n.nrLeternjoftimit, n.gjinia,n.statusi,n.dataLindjes, tbl_Adresat.shteti,tbl_Adresat.qyteti,tbl_Adresat.rruga\n" +
            "FROM tbl_Puntori n\n" +
            "INNER JOIN tbl_Adresat ON n.adresaID = tbl_Adresat.adresaID";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(query1);
            TeDhenatEpuntorve_tbl teDhenatK;
            while(rs.next()){
                teDhenatK = new TeDhenatEpuntorve_tbl(rs.getInt("puntoriID"), rs.getString("emri"),rs.getString("mbiemri"),rs.getInt("nrLeternjoftimit"),rs.getString("gjinia"),rs.getString("statusi"),rs.getString("dataLindjes"),rs.getString("shteti"),rs.getString("qyteti"),rs.getString("rruga"));
                teDhenatList.add(teDhenatK);
            }
         }catch (HeadlessException | SQLException e) {
          JOptionPane.showMessageDialog(null,e); 
        }
         return teDhenatList;
    }

        public void trego_Puntoret(){
        ArrayList<TeDhenatEpuntorve_tbl> list = puntoretEKompanisList();
        DefaultTableModel model = (DefaultTableModel)tbl2.getModel();
        Object[] row = new Object[10];
        for(int i =0;i<list.size();i++){
            row[0]=list.get(i).getID();
            row[1]=list.get(i).getEmri();
            row[2]=list.get(i).getMbiemri();
            row[3]=list.get(i).getDataLindjes();
            row[4]=list.get(i).getGjinia();
            row[5]=list.get(i).getStatusi();
            row[6]=list.get(i).getDataLindjes();
            row[7]= list.get(i).getShteti();
            row[8]=list.get(i).getQyteti();
            row[9]=list.get(i).getRruga();   

            model.addRow(row);
        }
    }
    //Lisa e shfrytzusve
    public ArrayList<Shfrytzust_tbl> ListaShfrytzusve(){
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
                shfrytzust = new Shfrytzust_tbl(rs.getInt("shfrytzuesiID"),rs.getString("emri"),rs.getString("mbiemri"),rs.getString("username"), rs.getString("fjalkalimi"), rs.getString("privilegji"));
                listaShfrytzusve.add(shfrytzust);
            }
         }catch (HeadlessException | SQLException e) {
          JOptionPane.showMessageDialog(null,e); 
        }
         return listaShfrytzusve;
    }
    
    
    //Metoda per reset
    public void pastro(){
        nickname.setText("");
        fjalkalimi1.setText("");
        fjalkalimi2.setText("");
        idP.setText("");
        comboBox2.setSelectedIndex(0);
        emriPuntorit.setText("");
    }
    //Metoda qe tregon Shfrytzust ne tabel
     public void trego_shfrytzust(){
        ArrayList<Shfrytzust_tbl> list = ListaShfrytzusve();
        DefaultTableModel model = (DefaultTableModel)tbl.getModel();
        Object[] rresht = new Object[5];
        for(int i =0;i<list.size();i++){
            rresht[0]=list.get(i).getID();
            rresht[1]=list.get(i).getEmri()+" "+list.get(i).getMbiemri();
            rresht[2]= list.get(i).getNickName();
            rresht[3]= list.get(i).getPasvord();
            rresht[4]= list.get(i).getPozita();
          
            model.addRow(rresht);
        }
        String countIKonvertuar = Integer.toString(list.size());
         mesazhZ.setText(countIKonvertuar);
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        nickname = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        comboBox2 = new javax.swing.JComboBox<>();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        btnFshije = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        fjalkalimi1 = new javax.swing.JPasswordField();
        fjalkalimi2 = new javax.swing.JPasswordField();
        jButton3 = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbl2 = new javax.swing.JTable();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbl = new javax.swing.JTable();
        idP = new javax.swing.JTextField();
        emriPuntorit = new javax.swing.JTextField();
        mesazhZ = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Shto Shfrytzues"));

        jLabel2.setText("Id e puntorit ne databaz");

        jLabel1.setText("Shfrytzusi");

        jLabel3.setText("Fjalkalimi");

        jLabel4.setText("Verteto Fjalkalimin");

        nickname.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                nicknameKeyTyped(evt);
            }
        });

        jLabel5.setText("Privilegji");

        comboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Privilegjet", "Administrator", "Shites", "Kontabilist", "" }));
        comboBox2.setToolTipText("");
        comboBox2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboBox2ActionPerformed(evt);
            }
        });

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

        btnFshije.setText("Fshije");
        btnFshije.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFshijeActionPerformed(evt);
            }
        });

        jButton4.setText("Dalje");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        fjalkalimi1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fjalkalimi1ActionPerformed(evt);
            }
        });
        fjalkalimi1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                fjalkalimi1KeyTyped(evt);
            }
        });

        fjalkalimi2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                fjalkalimi2KeyTyped(evt);
            }
        });

        jButton3.setText("Anulo");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        tbl2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Emri", "Mbiemri", "DataLindjes"
            }
        ));
        tbl2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl2MouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tbl2);

        tbl.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        tbl.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Emri", "Shfrytzusi", "Fjalkalimi", "Pozita"
            }
        ));
        tbl.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbl);

        idP.setEnabled(false);

        mesazhZ.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        mesazhZ.setForeground(new java.awt.Color(255, 51, 51));
        mesazhZ.setText("jLabel6");

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel6.setText("Numri i shfrytzusve të regjistruar:");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 768, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(nickname)
                                    .addComponent(comboBox2, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(fjalkalimi1)
                                    .addComponent(fjalkalimi2)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(idP)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(emriPuntorit, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(27, 27, 27))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jButton1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnFshije)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jButton4)))
                        .addGap(68, 68, 68)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 349, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(mesazhZ, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(idP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(emriPuntorit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(nickname, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(comboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(fjalkalimi1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(fjalkalimi2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton1)
                            .addComponent(jButton2)
                            .addComponent(jButton3)
                            .addComponent(btnFshije)
                            .addComponent(jButton4)))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 271, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(mesazhZ, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6)))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(24, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnFshijeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFshijeActionPerformed
        
        try{
            int row = tbl.getSelectedRow();
            
            if(tbl.getSelectedRow() == -1){
                    JOptionPane.showMessageDialog(this ,"Ju nuk mund te fshini asnje shfrytezues nese nuk e selektoni!","Mesazh nga Autori", JOptionPane.ERROR_MESSAGE);             
            }
            else{
            Object [] ob = {"Po","Jo"};
            int p = javax.swing.JOptionPane.showOptionDialog(this, "A jeni i sigurt se deshironi ta fshini pas fshirjes nuk ka me kthim te shenimeve ?", "Konfirmim", JOptionPane.OK_OPTION,JOptionPane.QUESTION_MESSAGE, null, ob, ob[1]);
            
            if (p==0){
            Connection con = LidhjaDB.ConnectDB();
//            int row = tbl.getSelectedRow();
            String IdESelektuar = (tbl.getModel().getValueAt(row, 0).toString());
            String query = "DELETE FROM tbl_Shfrytzuesi where shfrytzuesiID="+IdESelektuar;
            PreparedStatement pst = con.prepareStatement(query);
            pst.executeUpdate();
            DefaultTableModel model = (DefaultTableModel)tbl.getModel();
            model.setRowCount(0);
            trego_shfrytzust();
            JOptionPane.showMessageDialog(this ,"E dhena u fshi me sukses","Informim", JOptionPane.INFORMATION_MESSAGE);
            pastro(); 
            }
            }            
            } catch (HeadlessException | SQLException e) {
          JOptionPane.showMessageDialog(null,e); 
        }
    }//GEN-LAST:event_btnFshijeActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
       hide();
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        try{ 
             Connection con = LidhjaDB.ConnectDB();	
             String query ="insert into tbl_Shfrytzuesi(username,fjalkalimi,privilegji,puntoriID)values(?,?,?,?)";
             PreparedStatement pst = con.prepareStatement(query);
        
            if(idP.getText().toString().equals("")){
               JOptionPane.showMessageDialog( this, "Duhet zgjedhur Puntori selektoni ne tabel","Mesazh nga Autori", JOptionPane.ERROR_MESSAGE);
            }
            else if(nickname.getText().equals("")){
               JOptionPane.showMessageDialog( this, "Plotso nicnamin e shfrytzusit","Mesazh nga Autori", JOptionPane.ERROR_MESSAGE);

            }
            else if(ekziston(nickname.getText())){
                JOptionPane.showMessageDialog( this, "Ndrroje emrin e Shfrytzusit ngase nje shfrytzus i till esht rexhistruar me par","Mesazh nga Autori", JOptionPane.ERROR_MESSAGE);
                nickname.setText("");
            }
            else if(comboBox2.getSelectedItem().toString().equals("Privilegjet")){
               JOptionPane.showMessageDialog( this, "Cakto Privilegjet per shfrytzusin","Mesazh nga Autori", JOptionPane.ERROR_MESSAGE);

            }
            else if(fjalkalimi1.getText().equals("")){
               JOptionPane.showMessageDialog( this, "Plotso rubriken e fjalkalimit","Mesazh nga Autori", JOptionPane.ERROR_MESSAGE);
            }
            else if(fjalkalimi2.getText().equals("")){
               JOptionPane.showMessageDialog( this, "Plotso rubriken e verteto Fjalkalimin","Mesazh nga Autori", JOptionPane.ERROR_MESSAGE);            
            }
            else if(!fjalkalimi1.getText().equals(fjalkalimi2.getText())){
               JOptionPane.showMessageDialog( this, "Vertetimi i fjalkalimit ishte gabim","Mesazh nga Autori", JOptionPane.ERROR_MESSAGE);
            }
            else{
                String ShkronjateMadhaja = nickname.getText().toUpperCase(); 
 
                pst.setString(1,ShkronjateMadhaja);
                pst.setString(2,fjalkalimi1.getText());
                String comboSend;
                comboSend=comboBox2.getSelectedItem().toString();
                pst.setString(3,comboSend);
                pst.setString(4,idP.getText());
                pst.executeUpdate();  
                DefaultTableModel model = (DefaultTableModel)tbl.getModel();
                model.setRowCount(0);
                trego_shfrytzust();
            JOptionPane.showMessageDialog(null,"Te dhenat u rexhistruan ne databaze");
            pastro();
           
            }
    
        }catch (HeadlessException | SQLException e) {
          JOptionPane.showMessageDialog(null,e);
    }//GEN-LAST:event_jButton1ActionPerformed
}
    private void tblMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblMouseClicked
       int i = tbl.getSelectedRow();
       TableModel model = tbl.getModel();
       nickname.setText(model.getValueAt(i,2).toString());
       fjalkalimi1.setText(model.getValueAt(i,3).toString()); 
       fjalkalimi2.setText(model.getValueAt(i,3).toString());
       String comboBox2Copy = model.getValueAt(i,4).toString();
       switch(comboBox2Copy){
           case "Privilegjet":
               comboBox2.setSelectedIndex(0);
                break;
           case "Administrator":
                comboBox2.setSelectedIndex(1);
                break;
           case "Shites":
               comboBox2.setSelectedIndex(2);
               break;
           case "Kontabilist":
               comboBox2.setSelectedIndex(3);
               break;
       }
    }//GEN-LAST:event_tblMouseClicked

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        pastro();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // Permirsimi i te dhenave 
         try{
            int row = tbl.getSelectedRow();
            if(tbl.getSelectedRow() == -1){
                    JOptionPane.showMessageDialog(this ,"Ju nuk mund te Editoni asnje shfrytezues nese nuk e selektoni!","Mesazh nga Autori", JOptionPane.ERROR_MESSAGE);             
            }
            else{
            Connection con = LidhjaDB.ConnectDB();
           
            String IdESelektuar = (tbl.getModel().getValueAt(row, 0).toString());
            String query =" UPDATE tbl_Shfrytzuesi SET username=?, fjalkalimi=?, privilegji=? where shfrytzuesiID="+IdESelektuar;
            PreparedStatement pst = con.prepareStatement(query);
            if(nickname.getText().equals("")){
               JOptionPane.showMessageDialog( this, "Plotso emrin e shfrytzusit","Mesazh nga Autori", JOptionPane.ERROR_MESSAGE);

            }
            else if(comboBox2.getSelectedItem().toString().equals("Privilegjet")){
               JOptionPane.showMessageDialog( this, "Cakto Privilegjet per shfrytzusin","Mesazh nga Autori", JOptionPane.ERROR_MESSAGE);

            }
            else if(fjalkalimi1.getText().equals("")){
               JOptionPane.showMessageDialog( this, "Plotso rubriken e fjalkalimit","Mesazh nga Autori", JOptionPane.ERROR_MESSAGE);

            }
            else if(fjalkalimi2.getText().equals("")){
               JOptionPane.showMessageDialog( this, "Plotso rubriken e verteto Fjalkalimin","Mesazh nga Autori", JOptionPane.ERROR_MESSAGE);            
            }
            else if(!fjalkalimi1.getText().equals(fjalkalimi2.getText())){
               JOptionPane.showMessageDialog( this, "Vertetimi i fjalkalimit ishte gabim","Mesazh nga Autori", JOptionPane.ERROR_MESSAGE);
            }
            else{
                pst.setString(1,nickname.getText());
                pst.setString(2,fjalkalimi1.getText());
                String comboSend;
                comboSend=comboBox2.getSelectedItem().toString();
                pst.setString(3,comboSend);
                pst.executeUpdate();
                DefaultTableModel model = (DefaultTableModel)tbl.getModel();
                model.setRowCount(0);
                trego_shfrytzust();
                pastro();
                JOptionPane.showMessageDialog(this ,"E dhena u editua me sukses","Informim", JOptionPane.INFORMATION_MESSAGE); 
            }
            }
        } catch (HeadlessException | SQLException e) {
          JOptionPane.showMessageDialog(null,e); 
        }         
    }//GEN-LAST:event_jButton2ActionPerformed

    private void fjalkalimi1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fjalkalimi1ActionPerformed
      
    }//GEN-LAST:event_fjalkalimi1ActionPerformed

    private void fjalkalimi1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_fjalkalimi1KeyTyped
       //     --------
    }//GEN-LAST:event_fjalkalimi1KeyTyped

    private void nicknameKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_nicknameKeyTyped
       //     --------

    }//GEN-LAST:event_nicknameKeyTyped

    private void fjalkalimi2KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_fjalkalimi2KeyTyped
       //     --------

    }//GEN-LAST:event_fjalkalimi2KeyTyped

    private void comboBox2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboBox2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_comboBox2ActionPerformed

    private void tbl2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl2MouseClicked
       int i = tbl2.getSelectedRow();
       TableModel model = tbl2.getModel();
       idP.setText(model.getValueAt(i,0).toString());
       emriPuntorit.setText(model.getValueAt(i,1).toString()+" "+model.getValueAt(i,2).toString());

    }//GEN-LAST:event_tbl2MouseClicked

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
            java.util.logging.Logger.getLogger(ShtoShfrytezues.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ShtoShfrytezues.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ShtoShfrytezues.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ShtoShfrytezues.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new ShtoShfrytezues().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnFshije;
    private javax.swing.JComboBox<String> comboBox2;
    private javax.swing.JTextField emriPuntorit;
    private javax.swing.JPasswordField fjalkalimi1;
    private javax.swing.JPasswordField fjalkalimi2;
    private javax.swing.JTextField idP;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel mesazhZ;
    private javax.swing.JTextField nickname;
    private javax.swing.JTable tbl;
    private javax.swing.JTable tbl2;
    // End of variables declaration//GEN-END:variables

    
}
