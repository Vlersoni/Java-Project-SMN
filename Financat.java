/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import LidhjaMeDatabaz.LidhjaDB;
import Modelet_Tabelat.BanesatMeQera;
import Modelet_Tabelat.BanesatQera1;
import java.awt.HeadlessException;
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

/**
 *
 * @author VHaliti
 */
public final class Financat extends javax.swing.JFrame {

    /**
     * Creates new form Financat
     * @throws java.text.ParseException
     */
    public Financat() throws ParseException {
        initComponents();
        tregoFitimiEBanesaveTeShitura();
        tregoFitiminEBanesavemeQera();
        qmimiTotal();
        tregoBanesatMeqera("2017-12-12");
        tregoBanesatMeqera12("a");
    }
    public void qmimiTotal(){ 
        
      float qeraja = Float.parseFloat(shitjaTotale.getText());
      float shitja = Float.parseFloat(QerajaTotale.getText());
      float totali1 = qeraja + shitja;
      String numriIKonvertuar = Double.toString(totali1);
      totali.setText(numriIKonvertuar);
      
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
   
    public void tregoFitimiEBanesaveTeShitura() throws ParseException{
        ArrayList<BanesatMeQera> list = TeDhenatEbanesave();
        Object[] row = new Object[16];
        String a = "E Shitur";
        String b="";
        double sum1=0;
        for(int i =0;i<list.size();i++){
                if(a.equals(list.get(i).getStatusi()) && list.get(i).getEmriKlientit() != null){
                             float f = Float.parseFloat(list.get(i).getQmimiTotal());
                             sum1+=f;
                   }
           }
            String countIKonvertuar = Double.toString(sum1);
            shitjaTotale.setText(countIKonvertuar);
        }
//    ************************************************
       public ArrayList<BanesatQera1> TeDhenatEbanesave1(){
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
    public void tregoFitiminEBanesavemeQera() throws ParseException{
        ArrayList<BanesatQera1> list = TeDhenatEbanesave1();
        Object[] row = new Object[17];
        String a = "Me Qera";
        String b="";
        double sum1=0;
        for(int i =0;i<list.size();i++){
           if(a.equals(list.get(i).getStatusi()) && list.get(i).getEmriKlientit() != null ){
                        float f = Float.parseFloat(list.get(i).getQmimiTotal());
                        sum1+=f;
               }
           }
            String countIKonvertuar = Double.toString(sum1);
            QerajaTotale.setText(countIKonvertuar);
        } 
//    ************************************************************

      public void tregoBanesatMeqera(String emri) throws ParseException{
        ArrayList<BanesatQera1> list = TeDhenatEbanesave1();
        DefaultTableModel model = (DefaultTableModel)tbl1.getModel();
        Object[] row = new Object[4];
        String a = "Me Qera";
        String b="";
               
        for(int i =0;i<list.size();i++){
           if(a.equals(list.get(i).getStatusi()) && list.get(i).getEmriKlientit() != null ){
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
                if("06".equals(dataMuaji)){
//                        row[0]=list.get(i).getID();
//                        row[1]=list.get(i).getIDBanesa();
//                        row[2]=list.get(i).getBanesa();
                        row[1]=list.get(i).getQmimiTotal();
                        model.addRow(row);
               
                    }
               }
           }
         ArrayList<BanesatMeQera> list1 = TeDhenatEbanesave();   
         String a1 = "E Shitur";
         String b1="";
               
        for(int i =0;i<list.size();i++){
           if(a1.equals(list1.get(i).getStatusi()) && list1.get(i).getEmriKlientit() != null ){
                String ktheDatenNeString = list1.get(i).getDataFillimit();    
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
                if("06".equals(dataMuaji)){
//                        row[1]=list.get(i).getIDBanesa();
//                        row[2]=list.get(i).getBanesa();
                        row[3]=list1.get(i).getQmimiTotal();
                        model.addRow(row);
                    }
               }
           }
           
        
        
        
        
           
        }
      public void tregoBanesatMeqera12(String emri) throws ParseException{
        ArrayList<BanesatMeQera> list = TeDhenatEbanesave();
        DefaultTableModel model = (DefaultTableModel)tbl1.getModel();
        Object[] row = new Object[4];
        String a = "E Shitur";
        String b="";
               
        for(int i =0;i<list.size();i++){
           if(a.equals(list.get(i).getStatusi()) && list.get(i).getEmriKlientit() != null ){
                String ktheDatenNeString = list.get(i).getDataFillimit();    
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
                if("06".equals(dataMuaji)){
//                        row[0]=list.get(i).getID();
//                        row[1]=list.get(i).getIDBanesa();
//                        row[2]=list.get(i).getBanesa();
                        row[3]=list.get(i).getQmimiTotal();
                        model.addRow(row);
                    }
               }
           }
           
        }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        ComboBox2 = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbl1 = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        QerajaTotale = new javax.swing.JTextField();
        shitjaTotale = new javax.swing.JTextField();
        totali = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();

        setTitle("Statistikat");

        ComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Janar", "Shkurt", "Mars", "Prill", "Maj", "Qershor", "Korrik", "Gusht", "Shtator", "Tetor", "Nëntor", "Dhjetor" }));
        ComboBox2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ComboBox2ActionPerformed(evt);
            }
        });

        jLabel1.setText("Shiko Fitimet sipas muajve");

        jButton1.setText("Dalje");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        tbl1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Muaji", "Fitimi Qeras", "Fitimi Shitjes", "Fitimi Total"
            }
        ));
        jScrollPane1.setViewportView(tbl1);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Llogaritjet"));

        jLabel2.setText("Fitimi nga banesat e shluara me qera");

        jLabel3.setText("Fitimi nga banesat e shitura");

        jLabel4.setText("Fitimi total");

        QerajaTotale.setText("jTextField1");

        shitjaTotale.setText("jTextField2");

        totali.setText("jLabel5");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 185, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(QerajaTotale, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 69, Short.MAX_VALUE)
                                .addComponent(totali, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(59, 59, 59)
                                .addComponent(shitjaTotale)))))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(QerajaTotale, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(shitjaTotale, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(totali, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/GUI/Printer-icon.png"))); // NOI18N
        jButton2.setText("Printo");

        jButton3.setText("Shfaq Rezultatet");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setText("Pastro Tablen");

        jButton5.setText("Komplet muajt");

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/GUI/analysis.jpg"))); // NOI18N

        jTextField1.setText("jTextField1");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButton2)
                        .addGap(45, 45, 45)
                        .addComponent(jButton1))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(0, 0, Short.MAX_VALUE)
                                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(21, 21, 21))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel5)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jButton5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jButton4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(ComboBox2, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, 144, Short.MAX_VALUE)
                                    .addComponent(jTextField1))
                                .addGap(18, 18, 18)
                                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(jScrollPane1))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(ComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jButton3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jButton5))
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 319, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 29, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void ComboBox2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ComboBox2ActionPerformed
//       switch(ComboBox2.getSelectedItem().toString()){
//            case "Janar":
//       {
//           try {
//               PerTabel("01");
//           } catch (ParseException ex) {
//               Logger.getLogger(Financat.class.getName()).log(Level.SEVERE, null, ex);
//           }
//       }
//            break;  
//             case "Shkurt":
//       {
//           try {
//               PerTabel("02");
//           } catch (ParseException ex) {
//               Logger.getLogger(Financat.class.getName()).log(Level.SEVERE, null, ex);
//           }
//       }
//            break;  
//             case "Mars":
//       {
//           try {
//               PerTabel("03");
//           } catch (ParseException ex) {
//               Logger.getLogger(Financat.class.getName()).log(Level.SEVERE, null, ex);
//           }
//       }
//            break;  
//             case "Prill":
//       {
//           try {
//               PerTabel("04");
//           } catch (ParseException ex) {
//               Logger.getLogger(Financat.class.getName()).log(Level.SEVERE, null, ex);
//           }
//       }
//            break;  
//             case "Maj":
//       {
//           try {
//               PerTabel("05");
//           } catch (ParseException ex) {
//               Logger.getLogger(Financat.class.getName()).log(Level.SEVERE, null, ex);
//           }
//       }
//            break;  
//             case "Qershor":
//       {
//           try {
//               PerTabel("06");
//           } catch (ParseException ex) {
//               Logger.getLogger(Financat.class.getName()).log(Level.SEVERE, null, ex);
//           }
//       }
//            break;  
//             case "Korrik":
//       {
//           try {
//               PerTabel("07");
//           } catch (ParseException ex) {
//               Logger.getLogger(Financat.class.getName()).log(Level.SEVERE, null, ex);
//           }
//       }
//            break;  case "Gusht":
//       {
//           try {
//               PerTabel("08");
//           } catch (ParseException ex) {
//               Logger.getLogger(Financat.class.getName()).log(Level.SEVERE, null, ex);
//           }
//       }
//            break;  
//             case "Shtator":
//       {
//           try {
//               PerTabel("09");
//           } catch (ParseException ex) {
//               Logger.getLogger(Financat.class.getName()).log(Level.SEVERE, null, ex);
//           }
//       }
//            break;  
//             case "Tetor":
//       {
//           try {
//               PerTabel("10");
//           } catch (ParseException ex) {
//               Logger.getLogger(Financat.class.getName()).log(Level.SEVERE, null, ex);
//           }
//       }
//            break;  
//             case "Nëntor":
//       {
//           try {
//               PerTabel("11");
//           } catch (ParseException ex) {
//               Logger.getLogger(Financat.class.getName()).log(Level.SEVERE, null, ex);
//           }
//       }
//            break;  
//             case "Dhjetor":
//       {
//           try {
//               PerTabel("12");
//           } catch (ParseException ex) {
//               Logger.getLogger(Financat.class.getName()).log(Level.SEVERE, null, ex);
//           }
//       }
//            break;
//        }
    }//GEN-LAST:event_ComboBox2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        hide();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        switch(ComboBox2.getSelectedItem().toString()){
            case "Janar":
                jTextField1.setText("Janari1");
            break; 
             case "Shkurt":
                jTextField1.setText("Shkurt");
            break; 
             case "Mars":
                jTextField1.setText("Mars");
            break; 
             case "Prill":
                jTextField1.setText("Prill");
            break; 
             case "Maj":
                jTextField1.setText("Maj");
            break; 
             case "Qershor":
                jTextField1.setText("Qershor");
            break; 
             case "Korrik":
                jTextField1.setText("Korrik");
            break;  case "Gusht":
                jTextField1.setText("Gusht");
            break; 
             case "Shtator":
                jTextField1.setText("Shtator");
            break; 
             case "Tetor":
                jTextField1.setText("Tetor");
            break; 
             case "Nëntor":
                jTextField1.setText("Nëntor");
            break; 
             case "Dhjetor":
                jTextField1.setText("Dhjetor");
            break;
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
            java.util.logging.Logger.getLogger(Financat.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Financat.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Financat.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Financat.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new Financat().setVisible(true);
                } catch (ParseException ex) {
                    Logger.getLogger(Financat.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> ComboBox2;
    private javax.swing.JTextField QerajaTotale;
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
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField shitjaTotale;
    private javax.swing.JTable tbl1;
    private javax.swing.JLabel totali;
    // End of variables declaration//GEN-END:variables

}
