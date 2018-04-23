/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import LidhjaMeDatabaz.LidhjaDB;
import Modelet_Tabelat.BanesatQera1;
import java.awt.HeadlessException;
import java.awt.Toolkit;
import java.sql.Connection;
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
public final class QerajaSkaduar extends javax.swing.JFrame {

    /**
     * Creates new form QerajaSkaduar
     * @throws java.text.ParseException
     */
    public QerajaSkaduar() throws ParseException {
        initComponents();
        OraDheData();
        ikonaProgramit();
        Banesateskaduarasod();
    }
    private void ikonaProgramit() {
       setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("icon48.png")));
    }

    public void OraDheData(){
                    DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                    Date date = new Date();
                    data.setText("Qeraja qe ka skaduar Sod më "+dateFormat.format(date));
    }
    public ArrayList<BanesatQera1> TeDhenatEbanesave(){
        ArrayList<BanesatQera1> teDhenatList = new ArrayList<>();
         try{
            Connection con = LidhjaDB.ConnectDB();
            String query1="SELECT tbl_Ndertesa.ndertesaID,tbl_Banesa.*,tbl_BanesatMeQera1.*,tbl_Adresat.*,tbl_Klienti1.*,Kontaktet_Klienti.*\n" +
            "FROM (((((tbl_Ndertesa\n" +
            "Full JOIN tbl_Banesa ON tbl_Banesa.ndertesaID = tbl_Ndertesa.ndertesaID)\n" +
            "Full JOIN tbl_BanesatMeQera1 ON tbl_BanesatMeQera1.banesaID = tbl_Banesa.banesaID)\n" +
            "FULL JOIN tbl_Adresat ON tbl_Ndertesa.adresaID = tbl_Adresat.adresaID)\n" +
            "Full JOIN tbl_Klienti1 ON tbl_BanesatMeQera1.KlientiID=tbl_Klienti1.klientiID)\n" +
            "Full join Kontaktet_Klienti ON tbl_Klienti1.klientiID=Kontaktet_Klienti.klientiID);";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(query1);
            BanesatQera1 teDhenatK;
            while(rs.next()){
                teDhenatK = new BanesatQera1(rs.getInt("banesatmeQiraID"), rs.getInt("banesaID"),rs.getString("nrBaneses"),rs.getString("ndertesaID"),rs.getString("madhesia"),rs.getString("Kati"),rs.getString("qyteti"),rs.getString("rruga"),rs.getString("datafillimit"),rs.getString("datambarimit"),rs.getString("emri"),rs.getString("mbiemri"),rs.getString("telefoni"),rs.getString("email"),rs.getString("PagesaMujore"),rs.getString("Statusi"),rs.getString("QerajaMujore"));
                teDhenatList.add(teDhenatK);
            }
         }catch (HeadlessException | SQLException e) {
          JOptionPane.showMessageDialog(null,e); 
        }
         return teDhenatList;
    }
    public void Banesateskaduarasod() throws ParseException{
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        String data1 = dateFormat.format(date);
        ArrayList<BanesatQera1> list = TeDhenatEbanesave();
        DefaultTableModel model = (DefaultTableModel)tbl1.getModel();
        Object[] row = new Object[17];
        //test.setText(data1);
        String a = "Me Qera";
        String b="";
        DateFormat dateFormat1 = new SimpleDateFormat("yyyy-MM-dd");
        Date date1 = new Date();
        String d12 = dateFormat.format(date1);
        SimpleDateFormat sd = new SimpleDateFormat("yyyy-mm-dd");
        Date d1 = sd.parse(d12);
        for(int i =0;i<list.size();i++){
           if(a.equals(list.get(i).getStatusi()) && list.get(i).getEmriKlientit() != null){
            String ktheDatenNeString = list.get(i).getDataMbarimit();    
            Date kompletData=new SimpleDateFormat("yyyy-MM-dd").parse(ktheDatenNeString);
            String dataViti=String.format("%1$tY", kompletData);
            String dataMuaji=String.format("%1$tm", kompletData);
            String dataDita = String.format("%1$td", kompletData);
            int KtheDitenNeNumer = Integer.parseInt(dataDita);
            int ShtoDit = KtheDitenNeNumer + 2;
            String DitetEshtuaraString = Integer.toString(ShtoDit);
            String dataRe = (dataViti+"-"+dataMuaji+"-"+DitetEshtuaraString);
            Date date02=new SimpleDateFormat("yyyy-mm-dd").parse(dataRe);
            DateFormat formatter = new SimpleDateFormat("yyyy-mm-dd");
            String DataRregullume = formatter.format(date02);
            if(d1.equals(date02)){
                        row[0]=list.get(i).getID();
                        row[1]=list.get(i).getIDBanesa();
                        row[2]=list.get(i).getBanesa();
                        row[3]=list.get(i).getNdertesa();
                        row[4]=list.get(i).getMadhsia();
                        row[5]=list.get(i).getKati();
                        row[6]=list.get(i).getQyteti();
                        row[7]=list.get(i).getRruga();
                        row[8]=list.get(i).getDataFillimit();
                        row[9]=DataRregullume;
                        row[10]=list.get(i).getEmriKlientit();
                        row[11]=list.get(i).getMbiemriKlientit();
                        row[12]=list.get(i).getNrTelefonit();
                        row[13]=list.get(i).getEmaili();
                        row[14]=list.get(i).getQmimiTotal();
                        row[15]=list.get(i).getQeraja();
                        row[16]=list.get(i).getStatusi();
                        model.addRow(row); 
                    }
               }
           }
        }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane2 = new javax.swing.JScrollPane();
        Pershkrimi = new javax.swing.JTextArea();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbl1 = new javax.swing.JTable();
        jScrollPane3 = new javax.swing.JScrollPane();
        Pershkrimi1 = new javax.swing.JTextArea();
        data = new javax.swing.JLabel();

        Pershkrimi.setColumns(20);
        Pershkrimi.setFont(new java.awt.Font("Monospaced", 1, 14)); // NOI18N
        Pershkrimi.setRows(5);
        Pershkrimi.setDisabledTextColor(new java.awt.Color(255, 0, 51));
        Pershkrimi.setEnabled(false);
        jScrollPane2.setViewportView(Pershkrimi);

        setTitle("Mesazh Banesave qe u ka kaluar afati qeras sod");

        jButton1.setText("Afatizimi Qeras");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Dalje");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        tbl1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID_Qera", "ID_Banesa", "Banesa", "ID_Ndertesa", "Madhsia", "Etazhi", "Qyteti", "Rruga", "DataFillimit", "DataMbarimit", "Emri ", "Mbiemri", "NrTelefonit", "Emaili", "Qmimi", "Q-Qera", "statusi"
            }
        ));
        tbl1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbl1);

        Pershkrimi1.setColumns(20);
        Pershkrimi1.setFont(new java.awt.Font("Monospaced", 1, 14)); // NOI18N
        Pershkrimi1.setRows(5);
        Pershkrimi1.setDisabledTextColor(new java.awt.Color(255, 0, 51));
        Pershkrimi1.setEnabled(false);
        jScrollPane3.setViewportView(Pershkrimi1);

        data.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        data.setText("Qeraja qe ka skaduar Sod më");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane3)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1022, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton1)
                .addGap(445, 445, 445))
            .addGroup(layout.createSequentialGroup()
                .addGap(203, 203, 203)
                .addComponent(data, javax.swing.GroupLayout.PREFERRED_SIZE, 550, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(data, javax.swing.GroupLayout.DEFAULT_SIZE, 42, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton2)
                    .addComponent(jButton1))
                .addGap(21, 21, 21))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tbl1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl1MouseClicked
        int i = tbl1.getSelectedRow();
       
       TableModel model = tbl1.getModel();
       Pershkrimi1.setText("Banesa me shifer apo emer "+model.getValueAt(i,2).toString()+" e clia është e madhe  "+model.getValueAt(i,4).toString()+" dhe gjendet në katin e "+ model.getValueAt(i,5).toString()+" \nqe gjendet ne "+model.getValueAt(i,6).toString()+"  ne rrugen "+model.getValueAt(i,7).toString()+"\n"+
               "e shluar me qera me daten "+model.getValueAt(i,8).toString()+" deri me daten "+model.getValueAt(i,9).toString()+" per klientin "+model.getValueAt(i,10).toString()+" "+model.getValueAt(i,11).toString()+" \nme nje qmim prej "+model.getValueAt(i,14).toString()+" euro Kontaktet e klientit \n"+
               "Numri i telefonit "+model.getValueAt(i,12).toString()+" posta elektronike "+model.getValueAt(i,13).toString());
       
    }//GEN-LAST:event_tbl1MouseClicked

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        hide();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        try {
            BanesaEShluaraMeQera bqsh = new BanesaEShluaraMeQera();
            bqsh.setVisible(true);
            hide();
        } catch (ParseException ex) {
            Logger.getLogger(QerajaSkaduar.class.getName()).log(Level.SEVERE, null, ex);
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
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(QerajaSkaduar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(QerajaSkaduar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(QerajaSkaduar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(QerajaSkaduar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new QerajaSkaduar().setVisible(true);
                } catch (ParseException ex) {
                    Logger.getLogger(QerajaSkaduar.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextArea Pershkrimi;
    private javax.swing.JTextArea Pershkrimi1;
    private javax.swing.JLabel data;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable tbl1;
    // End of variables declaration//GEN-END:variables
}
