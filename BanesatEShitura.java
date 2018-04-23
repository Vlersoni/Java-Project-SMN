/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import LidhjaMeDatabaz.LidhjaDB;
import Modelet_Tabelat.BanesatMeQera;
import java.awt.HeadlessException;
import java.awt.Toolkit;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

/**
 *
 * @author VHaliti
 */
public final class BanesatEShitura extends javax.swing.JFrame {

    /**
     * Creates new form BanesatEShitura
     */
    public BanesatEShitura() {
        try {
            initComponents();
            tregoBanesatEshitura("%!@#");
            ikonaProgramit();
        } catch (ParseException ex) {
            Logger.getLogger(BanesatEShitura.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    private void ikonaProgramit() {
       setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("icon48.png")));
    }
    
    public ArrayList<BanesatMeQera> TeDhenatEbanesave(){
        ArrayList<BanesatMeQera> teDhenatList = new ArrayList<>();
         try{
            Connection con = LidhjaDB.ConnectDB();
            String query1="SELECT tbl_Ndertesa.ndertesaID,tbl_Banesa.*,tbl_BanesatEShitura1.*,tbl_Adresat.*,tbl_Klienti1.*,Kontaktet_Klienti.*\n" +
            "FROM (((((tbl_Ndertesa\n" +
            "Full JOIN tbl_Banesa ON tbl_Banesa.ndertesaID = tbl_Ndertesa.ndertesaID)\n" +
            "Full JOIN tbl_BanesatEShitura1 ON tbl_BanesatEShitura1.banesaID = tbl_Banesa.banesaID)\n" +
            "FULL JOIN tbl_Adresat ON tbl_Ndertesa.adresaID = tbl_Adresat.adresaID)\n" +
            "Full JOIN tbl_Klienti1 ON tbl_BanesatEShitura1.klientiID=tbl_Klienti1.klientiID)\n" +
            "Full join Kontaktet_Klienti ON tbl_Klienti1.klientiID=Kontaktet_Klienti.klientiID);";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(query1);
            BanesatMeQera teDhenatK;
            while(rs.next()){
                teDhenatK = new BanesatMeQera(rs.getInt("IDShitjes"), rs.getInt("banesaID"),rs.getString("nrBaneses"),rs.getString("ndertesaID"),rs.getString("madhesia"),rs.getString("Kati"),rs.getString("qyteti"),rs.getString("rruga"),rs.getString("dataMarrveshjes"),rs.getString("PuntoriQeShiti"),rs.getString("emri"),rs.getString("mbiemri"),rs.getString("telefoni"),rs.getString("email"),rs.getString("QmimiTotal"),rs.getString("Statusi"));
                teDhenatList.add(teDhenatK);
            }
         }catch (HeadlessException | SQLException e) {
          JOptionPane.showMessageDialog(null,e); 
        }
         return teDhenatList;
    }
    public void tregoBanesatEshitura(String emri) throws ParseException{
        ArrayList<BanesatMeQera> list = TeDhenatEbanesave();
        DefaultTableModel model = (DefaultTableModel)tbl1.getModel();
        Object[] row = new Object[16];
        String a = "E Shitur";
        String b="";
        int c=0;
         DateFormat dateFormat = new SimpleDateFormat("yyyy");
        DateFormat dateFormat1 = new SimpleDateFormat("MM");
        DateFormat dateFormat2 = new SimpleDateFormat("dd");
        Date Viti = new Date();
        Date Muaji = new Date();
        Date Dita = new Date();
 
        
        /*
        Tek data fillimit esht data kur u shit banesa 
        tek data e mbarimit esht puntori qe e ka shit banesen po per arsye mos mi bo dy klas 
        te njejta e kom marr klasen qe esht mi tregu te dhenat e banesave me qera
        */
        for(int i =0;i<list.size();i++){
                String dateFromDateChooser = list.get(0).getDataFillimit();    
                Date date11 = new SimpleDateFormat("yyyy-MM-dd").parse(dateFromDateChooser);
                String date2=String.format("%1$tY", date11);
                String date3=String.format("%1$tm", date11);
                String date12 = String.format("%1$td", date11);
                int DitaB1 = Integer.parseInt(date12);
                int sum = DitaB1 + 2;
                String numberAsString = Integer.toString(sum);
                String a12 = (date2+"-"+date3+"-"+numberAsString);
                Date date02=new SimpleDateFormat("yyyy-mm-dd").parse(a12);
                DateFormat formatter = new SimpleDateFormat("yyyy-mm-dd");
                String today = formatter.format(date02);
//                jTextField1.setText(today);
           
            if(a.equals(list.get(i).getStatusi()) && list.get(i).getEmriKlientit() != null){
               c++;
               if(emri.equals(list.get(i).getBanesa()) || emri.equals("%!@#")
                    || emri.equals(list.get(i).getMadhsia()) || emri.equals(today) || emri.equals(list.get(i).getEmriKlientit()) || emri.equals(list.get(i).getMbiemriKlientit())){
                        row[0]=list.get(i).getID();
                        row[1]=list.get(i).getIDBanesa();
                        row[2]=list.get(i).getBanesa();
                        row[3]=list.get(i).getNdertesa();
                        row[4]=list.get(i).getMadhsia();
                        row[5]=list.get(i).getKati();
                        row[6]=list.get(i).getQyteti();
                        row[7]=list.get(i).getRruga();
                        row[8]=today;
                        row[9]=list.get(i).getDataMbarimit();
                        row[10]=list.get(i).getEmriKlientit();
                        row[11]=list.get(i).getMbiemriKlientit();
                        row[12]=list.get(i).getNrTelefonit();
                        row[13]=list.get(i).getEmaili();
                        row[14]=list.get(i).getQmimiTotal();
                        row[15]=list.get(i).getStatusi();
                        model.addRow(row);
                    }
               }
           }
            String countIKonvertuar = Integer.toString(c);
            mesazhZ.setText(countIKonvertuar);
        }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tbl1 = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        Pershkrimi = new javax.swing.JTextArea();
        jButton1 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        mesazhZ = new javax.swing.JLabel();
        idShitja = new javax.swing.JTextField();
        idBanesa = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();
        Banesa = new javax.swing.JTextField();
        jTextField2 = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jTextField3 = new javax.swing.JTextField();
        jTextField4 = new javax.swing.JTextField();
        jTextField5 = new javax.swing.JTextField();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setTitle("Banesat e shitura");
        setBackground(new java.awt.Color(102, 255, 102));

        tbl1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID_Shitja", "ID_Banesa", "Banesa", "ID_Ndertesa", "Madhsia", "Etazhi", "Qyteti", "Rruga", "DataShitjes", "Puntori", "Emri ", "Mbiemri", "NrTelefonit", "Emaili", "Qmimi", "statusi"
            }
        ));
        tbl1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbl1);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Përshkrimi"));
        jPanel1.setToolTipText("");

        Pershkrimi.setColumns(20);
        Pershkrimi.setFont(new java.awt.Font("Monospaced", 1, 14)); // NOI18N
        Pershkrimi.setRows(5);
        Pershkrimi.setText("Nuk ka shenime, per te pasur shenime duhet selektuar ne tabel");
        Pershkrimi.setDisabledTextColor(new java.awt.Color(255, 0, 51));
        Pershkrimi.setEnabled(false);
        jScrollPane2.setViewportView(Pershkrimi);

        jButton1.setText("Anulo shitjen");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Numri i banesave te shitura"));

        mesazhZ.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        mesazhZ.setForeground(new java.awt.Color(255, 51, 51));
        mesazhZ.setText("jLabel6");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(mesazhZ, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(20, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(mesazhZ, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 1005, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 111, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        idShitja.setEnabled(false);

        idBanesa.setEnabled(false);

        jButton2.setText("Dalje");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        Banesa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BanesaActionPerformed(evt);
            }
        });

        jTextField2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField2ActionPerformed(evt);
            }
        });

        jLabel6.setText("Formati dates (yyyy,mm,dd)");

        jTextField3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField3ActionPerformed(evt);
            }
        });

        jTextField4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField4ActionPerformed(evt);
            }
        });

        jTextField5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField5ActionPerformed(evt);
            }
        });

        jButton5.setText("All");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jButton6.setText("Pastro tabelen");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel1.setText("Filtro");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addComponent(idShitja, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(idBanesa, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(43, 43, 43)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(Banesa, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(65, 65, 65)
                        .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(63, 63, 63)
                        .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(44, 44, 44)
                        .addComponent(jButton5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel6)
                        .addComponent(jButton2)
                        .addComponent(jButton5)
                        .addComponent(jButton6)
                        .addComponent(Banesa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel1))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(idBanesa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(idShitja, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 458, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(86, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tbl1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl1MouseClicked
        int i = tbl1.getSelectedRow();

        TableModel model = tbl1.getModel();
        idShitja.setText(model.getValueAt(i,0).toString());
        idBanesa.setText(model.getValueAt(i,1).toString());
        Pershkrimi.setText("Banesa me shifer apo emer "+model.getValueAt(i,2).toString()+" e clia është e madhe  "+model.getValueAt(i,4).toString()+" dhe gjendet në katin e "+ model.getValueAt(i,5).toString()+" \nqe gjendet ne "+model.getValueAt(i,6).toString()+"  ne rrugen "+model.getValueAt(i,7).toString()+"\n"+
            "e shitur me daten "+model.getValueAt(i,8).toString()+" nga puntori "+model.getValueAt(i,9).toString()+" per klientin "+model.getValueAt(i,10).toString()+" "+model.getValueAt(i,11).toString()+" \nme nje qmim prej "+model.getValueAt(i,14).toString()+" euro Kontaktet e klientit \n"+
            "Numri i telefonit "+model.getValueAt(i,12).toString()+" posta elektronike "+model.getValueAt(i,13).toString());

    }//GEN-LAST:event_tbl1MouseClicked

    private void BanesaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BanesaActionPerformed
        try {
            if(Banesa.getText().equals("")){
                JOptionPane.showMessageDialog(this ,"Tregoni qfar po kerkoni","Informim", JOptionPane.INFORMATION_MESSAGE);
            }
            else{
                DefaultTableModel model = (DefaultTableModel) tbl1.getModel();
                model.setRowCount(0);
                tregoBanesatEshitura(Banesa.getText());
            }
                    } catch (ParseException ex) {
            Logger.getLogger(BanesatEShitura.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_BanesaActionPerformed

    private void jTextField2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField2ActionPerformed
        try {
             if(jTextField2.getText().equals("")){
                JOptionPane.showMessageDialog(this ,"Tregoni qfar po kerkoni","Informim", JOptionPane.INFORMATION_MESSAGE);
            }
            else{
                DefaultTableModel model = (DefaultTableModel) tbl1.getModel();
                model.setRowCount(0);
                tregoBanesatEshitura(jTextField2.getText());
             }
        } catch (ParseException ex) {
            Logger.getLogger(BanesatEShitura.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jTextField2ActionPerformed

    private void jTextField3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField3ActionPerformed
        try {
             if(jTextField3.getText().equals("")){
                JOptionPane.showMessageDialog(this ,"Tregoni qfar po kerkoni","Informim", JOptionPane.INFORMATION_MESSAGE);
            }
            else{       
                DefaultTableModel model = (DefaultTableModel) tbl1.getModel();
                model.setRowCount(0);
                tregoBanesatEshitura(jTextField3.getText());
             }
        } catch (ParseException ex) {
            Logger.getLogger(BanesatEShitura.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jTextField3ActionPerformed

    private void jTextField4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField4ActionPerformed
        try {
             if(jTextField4.getText().equals("")){
                JOptionPane.showMessageDialog(this ,"Tregoni qfar po kerkoni","Informim", JOptionPane.INFORMATION_MESSAGE);
            }
            else{      
                DefaultTableModel model = (DefaultTableModel) tbl1.getModel();
                model.setRowCount(0);
                tregoBanesatEshitura(jTextField4.getText());
            }
        } catch (ParseException ex) {
            Logger.getLogger(BanesatEShitura.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jTextField4ActionPerformed

    private void jTextField5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField5ActionPerformed
        try {
             if(jTextField5.getText().equals("")){
                JOptionPane.showMessageDialog(this ,"Tregoni qfar po kerkoni","Informim", JOptionPane.INFORMATION_MESSAGE);
            }
            else{    
                DefaultTableModel model = (DefaultTableModel) tbl1.getModel();
                model.setRowCount(0);
                tregoBanesatEshitura(jTextField5.getText());
             }
        } catch (ParseException ex) {
            Logger.getLogger(BanesatEShitura.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jTextField5ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        try {
            DefaultTableModel model = (DefaultTableModel) tbl1.getModel();
            model.setRowCount(0);
            tregoBanesatEshitura("%!@#");
        } catch (ParseException ex) {
            Logger.getLogger(BanesatEShitura.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
       DefaultTableModel model = (DefaultTableModel) tbl1.getModel();
        model.setRowCount(0);
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
      hide();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        try{
            int row = tbl1.getSelectedRow();
            
            if(tbl1.getSelectedRow() == -1){
                    JOptionPane.showMessageDialog(this ,"Ju nuk mund te riktheni asnje banes nga shitja nese nuk e selektoni ne tabel!","Mesazh nga Autori", JOptionPane.ERROR_MESSAGE);             
            }
            else{
                String IdESelektuar = (tbl1.getModel().getValueAt(row, 0).toString());
//                String IdESelektuar1 = ;
                    Object [] ob = {"Po","Jo"};
                    int p = javax.swing.JOptionPane.showOptionDialog(this, "A jeni i sigurt se deshironi ta riktheni banesen  pas rikthimit nuk ka me kthim te shenimeve ? ", "Konfirmim", JOptionPane.OK_OPTION,JOptionPane.QUESTION_MESSAGE, null, ob, ob[1]);
            
                    if (p==0){
                        Connection con = LidhjaDB.ConnectDB();
    //                    String IdESelektuar = (tbl1.getModel().getValueAt(row, 0).toString());
                        String query = "DELETE FROM tbl_BanesatEShitura1 where IDShitjes="+IdESelektuar;
                        String query1 =" UPDATE tbl_Banesa SET Statusi=? where banesaID="+idBanesa.getText();
                        PreparedStatement pst1 = con.prepareStatement(query1);
                        pst1.setString(1,"E lir");
                        pst1.executeUpdate();
                        PreparedStatement pst = con.prepareStatement(query);
                        pst.executeUpdate();
                        DefaultTableModel model = (DefaultTableModel)tbl1.getModel();
                        model.setRowCount(0);
                    try {
                        tregoBanesatEshitura("%!@#");
                    } catch (ParseException ex) {
                        Logger.getLogger(BanesatEShitura.class.getName()).log(Level.SEVERE, null, ex);
                    }
                         Pershkrimi.setText("Nuk ka shenime, per te pasur shenime duhet selektuar ne tabel");
                        JOptionPane.showMessageDialog(this ,"Banesa u lira nga shitja","Informim", JOptionPane.INFORMATION_MESSAGE);
                       
                    } 
                }            
            } catch (HeadlessException | SQLException e) {
          JOptionPane.showMessageDialog(null,e); 
        }
    }//GEN-LAST:event_jButton1ActionPerformed

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
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(BanesatEShitura.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new BanesatEShitura().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField Banesa;
    private javax.swing.JTextArea Pershkrimi;
    private javax.swing.JTextField idBanesa;
    private javax.swing.JTextField idShitja;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JLabel mesazhZ;
    private javax.swing.JTable tbl1;
    // End of variables declaration//GEN-END:variables
}
