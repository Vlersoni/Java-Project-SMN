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
import java.awt.Toolkit;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.MessageFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.chart.PieChart.Data;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.text.JTextComponent;

/**
 *
 * @author VHaliti
 */
public final class BanesaEShluaraMeQera extends javax.swing.JFrame {

    /**
     * Creates new form BanesaEShluaraMeQera
     * @throws java.text.ParseException
     */
    public BanesaEShluaraMeQera() throws ParseException {
        initComponents();
        tregoBanesatMeqera("%!@#");
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
                    data.setText(dateFormat.format(date));
    }
     public void pastro(){
     
    idBanesa.setText("");
    Banesa.setText("");
    jTextField2.setText("");
    jTextField3.setText("");
    jTextField4.setText("");
    jTextField5.setText("");
    ((JTextComponent)AfatiQeras.getDateEditor().getUiComponent()).setText("");
   
    qmimi.setText("");
    perPagesTotali.setText("");
     
     }
       public void Pagesa(){
          DateFormat dateFormat = new SimpleDateFormat("MM");
          Date date = new Date();
          String muajiAktual= dateFormat.format(date);     
          Date dateFromDateChooser = AfatiQeras.getDate();       
//        %1$td-%1$tm-%1$tY"
        String dateString = String.format("%1$tm", dateFromDateChooser);  
        int qmimiQeras = Integer.parseInt(qmimi.getText());
        int AfatiIQeras = Integer.parseInt(dateString);
        int MuajiAktaul = Integer.parseInt(muajiAktual);
        int PagesaMeparshme = Integer.parseInt(pagesaDikur.getText());

        int Muajt = AfatiIQeras - MuajiAktaul;
        
        String pagesa = String.valueOf((Muajt*qmimiQeras)-PagesaMeparshme);
        perPagesTotali.setText(pagesa);
        
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
    public void tregoBanesatMeqera(String emri) throws ParseException{
        ArrayList<BanesatQera1> list = TeDhenatEbanesave();
        DefaultTableModel model = (DefaultTableModel)tbl1.getModel();
        Object[] row = new Object[17];
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
                if(emri.equals(list.get(i).getBanesa()) || emri.equals("%!@#")
                    || emri.equals(list.get(i).getMadhsia()) || emri.equals(DataRregullume) || emri.equals(list.get(i).getEmriKlientit()) || emri.equals(list.get(i).getMbiemriKlientit())){
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
//         String countIKonvertuar = Integer.toString(list.size());
//         mesazhZ.setText(countIKonvertuar);
    public void Banesateskaduarasod() throws ParseException{
        ArrayList<BanesatQera1> list = TeDhenatEbanesave();
        DefaultTableModel model = (DefaultTableModel)tbl2.getModel();
        Object[] row = new Object[17];
        //test.setText(data1);
        SimpleDateFormat sd = new SimpleDateFormat("yyyy-mm-dd");
        Date d1 = sd.parse(data.getText());
      
        String a = "Me Qera";
        String b=""; 
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
            if(d1.after(date02)){
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

        jScrollPane1 = new javax.swing.JScrollPane();
        tbl1 = new javax.swing.JTable();
        Banesa = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        Pershkrimi = new javax.swing.JTextArea();
        jPanel2 = new javax.swing.JPanel();
        AfatiQeras = new com.toedter.calendar.JDateChooser();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        data = new javax.swing.JTextField();
        perPagesTotali = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        qmimi = new javax.swing.JTextField();
        jButton8 = new javax.swing.JButton();
        idBanesa = new javax.swing.JTextField();
        idQera = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        jButton3 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jTextField2 = new javax.swing.JTextField();
        jTextField3 = new javax.swing.JTextField();
        jTextField4 = new javax.swing.JTextField();
        jTextField5 = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tbl2 = new javax.swing.JTable();
        pagesaDikur = new javax.swing.JTextField();

        setTitle("Banesat e shluara me qera");

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

        Banesa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BanesaActionPerformed(evt);
            }
        });

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Përshkrimi"));
        jPanel1.setToolTipText("");

        Pershkrimi.setColumns(20);
        Pershkrimi.setFont(new java.awt.Font("Monospaced", 1, 14)); // NOI18N
        Pershkrimi.setRows(5);
        Pershkrimi.setDisabledTextColor(new java.awt.Color(255, 0, 51));
        Pershkrimi.setEnabled(false);
        jScrollPane2.setViewportView(Pershkrimi);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2)
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel1.setText("Data Afatizimit te Ri");

        jLabel2.setText("Data Aktuale");

        data.setEditable(false);
        data.setEnabled(false);

        jLabel3.setText("Per pages");

        jLabel4.setText("Qmimi qeras");

        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(data, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4)
                            .addComponent(jLabel3))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(perPagesTotali, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(qmimi, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(AfatiQeras, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(15, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(data, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(AfatiQeras, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addComponent(qmimi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(perPagesTotali, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jButton8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        idBanesa.setEnabled(false);

        idQera.setEnabled(false);

        jPanel3.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jButton3.setText("Dalje");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton1.setText("Afatizo");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Liro");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton4.setText("Aunlo");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton3)
                .addContainerGap(25, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton3)
                    .addComponent(jButton1)
                    .addComponent(jButton2)
                    .addComponent(jButton4))
                .addGap(19, 19, 19))
        );

        jTextField2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField2ActionPerformed(evt);
            }
        });

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

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel5.setText("Filtro");

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

        jButton7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/GUI/Printer-icon.png"))); // NOI18N
        jButton7.setText("Printo");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        jLabel6.setText("Formati dates (yyyy,mm,dd)");

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel7.setText("Banesave te cilat u ka skaduar afati Qeras");

        tbl2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID_Qera", "ID_Banesa", "Banesa", "ID_Ndertesa", "Madhsia", "Etazhi", "Qyteti", "Rruga", "DataFillimit", "DataMbarimit", "Emri ", "Mbiemri", "NrTelefonit", "Emaili", "Qmimi", "Q-Qera", "statusi"
            }
        ));
        tbl2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl2MouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tbl2);

        pagesaDikur.setEnabled(false);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(26, 26, 26))
                    .addComponent(jScrollPane1)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(idQera, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(idBanesa, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(Banesa, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(49, 49, 49)
                                .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(108, 108, 108)
                                .addComponent(jLabel6)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(8, 8, 8)
                                .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(57, 57, 57)
                                .addComponent(jButton5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jButton6)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jButton7))
                            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 256, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(pagesaDikur, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Banesa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(idBanesa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(idQera, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(jButton5)
                    .addComponent(jButton6)
                    .addComponent(jButton7)
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 243, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel7)
                    .addComponent(pagesaDikur, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tbl1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl1MouseClicked
       int i = tbl1.getSelectedRow();
       
       TableModel model = tbl1.getModel();
       idQera.setText(model.getValueAt(i,0).toString());
       idBanesa.setText(model.getValueAt(i,1).toString());
       Pershkrimi.setText("Banesa me shifer apo emer "+model.getValueAt(i,2).toString()+" e clia është e madhe  "+model.getValueAt(i,4).toString()+" dhe gjendet në katin e "+ model.getValueAt(i,5).toString()+" \nqe gjendet ne "+model.getValueAt(i,6).toString()+"  ne rrugen "+model.getValueAt(i,7).toString()+"\n"+
               "e shluar me qera me daten "+model.getValueAt(i,8).toString()+" deri me daten "+model.getValueAt(i,9).toString()+" per klientin "+model.getValueAt(i,10).toString()+" "+model.getValueAt(i,11).toString()+" \nme nje qmim prej "+model.getValueAt(i,14).toString()+" euro Kontaktet e klientit \n"+
               "Numri i telefonit "+model.getValueAt(i,12).toString()+" posta elektronike "+model.getValueAt(i,13).toString());
        qmimi.setText(model.getValueAt(i,15).toString());
        pagesaDikur.setText(model.getValueAt(i,14).toString());        
    }//GEN-LAST:event_tbl1MouseClicked

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        hide();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void BanesaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BanesaActionPerformed
        DefaultTableModel model = (DefaultTableModel) tbl1.getModel();
        model.setRowCount(0);
        try {
            tregoBanesatMeqera(Banesa.getText());
        } catch (ParseException ex) {
            Logger.getLogger(BanesaEShluaraMeQera.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_BanesaActionPerformed

    private void jTextField2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField2ActionPerformed
       DefaultTableModel model = (DefaultTableModel) tbl1.getModel();
        model.setRowCount(0);
        try {
            tregoBanesatMeqera(jTextField2.getText());
        } catch (ParseException ex) {
            Logger.getLogger(BanesaEShluaraMeQera.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jTextField2ActionPerformed

    private void jTextField3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField3ActionPerformed
        DefaultTableModel model = (DefaultTableModel) tbl1.getModel();
        model.setRowCount(0);
        try {
            tregoBanesatMeqera(jTextField3.getText());
        } catch (ParseException ex) {
            Logger.getLogger(BanesaEShluaraMeQera.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jTextField3ActionPerformed

    private void jTextField4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField4ActionPerformed
        DefaultTableModel model = (DefaultTableModel) tbl1.getModel();
        model.setRowCount(0);
        try {
            tregoBanesatMeqera(jTextField4.getText());
        } catch (ParseException ex) {
            Logger.getLogger(BanesaEShluaraMeQera.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jTextField4ActionPerformed

    private void jTextField5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField5ActionPerformed
        DefaultTableModel model = (DefaultTableModel) tbl1.getModel();
        model.setRowCount(0);
        try {
            tregoBanesatMeqera(jTextField3.getText());
        } catch (ParseException ex) {
            Logger.getLogger(BanesaEShluaraMeQera.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jTextField5ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        pastro();
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
    try{
         if(AfatiQeras.getDate() == null ){
            JOptionPane.showMessageDialog( this, "Caktoni daten se deri kur do qendojn ne banes","Mesazh nga Autori", JOptionPane.ERROR_MESSAGE);
    }else{
        DateFormat dateFormat = new SimpleDateFormat("yyyy");
        DateFormat dateFormat1 = new SimpleDateFormat("MM");
        DateFormat dateFormat2 = new SimpleDateFormat("dd");
        Date Viti = new Date();
        Date Muaji = new Date();
        Date Dita = new Date();

        String viti= dateFormat.format(Viti); 
        String muaji= dateFormat1.format(Muaji); 
        String dita= dateFormat2.format(Dita); 
        Date dateFromDateChooser = AfatiQeras.getDate();
        Date dateFromDateChooser1 = AfatiQeras.getDate();       
        Date dateFromDateChooser2 = AfatiQeras.getDate();       
        
//      %1$td-%1$tm-%1$tY"
        String dateStringViti = String.format("%1$tY", dateFromDateChooser);
        String dateStringMuaji = String.format("%1$tm", dateFromDateChooser1);
        String dateStringDita = String.format("%1$td", dateFromDateChooser2);
//        -------------------------------------------------------------------------------
        int VitiB = Integer.parseInt(viti);
        int MuajiB = Integer.parseInt(muaji);
        int DitaB = Integer.parseInt(dita);
//        ----------------------------------------------
        int VitiB1 = Integer.parseInt(dateStringViti);
        int MuajiB1 = Integer.parseInt(dateStringMuaji);
        int DitaB1 = Integer.parseInt(dateStringDita);
        int vitiB;
        if(VitiB > VitiB1){
            JOptionPane.showMessageDialog( this, "jemi ne vitin "+viti+" ndersa ju keni shkruar "+dateStringViti+" andaj nuk panohet kjo dat","Mesazh nga Autori", JOptionPane.ERROR_MESSAGE);
        }
         else if(VitiB == VitiB1){
            if(MuajiB > MuajiB1){
                 JOptionPane.showMessageDialog( this, "jemi ne Muajin e "+MuajiB+" ndersa ju keni selektuar muajin e "+MuajiB1+" te vitit te njejt andaj nuk pranohet kjo dat","Mesazh nga Autori", JOptionPane.ERROR_MESSAGE);  
                }
            else if(MuajiB == MuajiB1){
                JOptionPane.showMessageDialog( this, "jemi ne Muajin e "+MuajiB+" ndersa ju keni selektuar po te njejtin muaj andaj nuk panohet kjo dat","Mesazh nga Autori", JOptionPane.ERROR_MESSAGE);  
            }
            else{
        
                int row = tbl1.getSelectedRow();
                if(tbl1.getSelectedRow() == -1){
                    JOptionPane.showMessageDialog(this ,"Ju nuk mund ta afatizoni asnje te banes nese nuk e selektoni!","Mesazh nga Autori", JOptionPane.ERROR_MESSAGE);
                }
                else{
                    Connection con = LidhjaDB.ConnectDB();
                    String IdESelektuar = (tbl1.getModel().getValueAt(row, 0).toString());
                    String query = "UPDATE tbl_BanesatMeQera1 SET datambarimit=? where banesatmeQiraID="+IdESelektuar;; 
                    PreparedStatement pst = con.prepareStatement(query);
                        pst.setString(1,((JTextComponent)AfatiQeras.getDateEditor().getUiComponent()).getText());
                        JOptionPane.showMessageDialog(null,"Banesa u afatizua deri me daten "+((JTextComponent)AfatiQeras.getDateEditor().getUiComponent()).getText());
                        pst.executeUpdate();
                        DefaultTableModel model = (DefaultTableModel)tbl1.getModel();
                        model.setRowCount(0);
                        tregoBanesatMeqera("%!@#");
                        pastro();
                     }
                }
            }
            }
        }catch(HeadlessException | SQLException e){
            JOptionPane.showMessageDialog(null,e);
        } catch (ParseException ex) {
            Logger.getLogger(BanesaEShluaraMeQera.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        try{
            int row = tbl1.getSelectedRow();
            
            if(tbl1.getSelectedRow() == -1){
                    JOptionPane.showMessageDialog(this ,"Ju nuk mund ta lironi asnje banes nese nuk e selektoni ne tabel!","Mesazh nga Autori", JOptionPane.ERROR_MESSAGE);             
            }
            else{
                String IdESelektuar = (tbl1.getModel().getValueAt(row, 0).toString());
//                String IdESelektuar1 = ;
                    Object [] ob = {"Po","Jo"};
                    int p = javax.swing.JOptionPane.showOptionDialog(this, "A jeni i sigurt se deshironi ta lironi  Banesen pas Lirimit nuk ka me kthim te shenimeve ?", "Konfirmim", JOptionPane.OK_OPTION,JOptionPane.QUESTION_MESSAGE, null, ob, ob[1]);
            
                    if (p==0){
                        Connection con = LidhjaDB.ConnectDB();
                        String query1 =" UPDATE tbl_Banesa SET Statusi=? where banesaID="+idBanesa.getText();
                        PreparedStatement pst1 = con.prepareStatement(query1);
                        pst1.setString(1,"E lir");
                        pst1.executeUpdate();
                        DefaultTableModel model = (DefaultTableModel)tbl1.getModel();
                        model.setRowCount(0);
                        tregoBanesatMeqera("%!@#");
                        JOptionPane.showMessageDialog(this ,"Banesa u lira nga qeraja","Informim", JOptionPane.INFORMATION_MESSAGE);
                        pastro(); 
                    } 
                }            
            } catch (HeadlessException | SQLException e) {
          JOptionPane.showMessageDialog(null,e); 
        } catch (ParseException ex) {
            Logger.getLogger(BanesaEShluaraMeQera.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
       try{
            MessageFormat header = new MessageFormat("Banesat me qera");
            MessageFormat footer = new MessageFormat("Faqe(1)");
            tbl1.print(JTable.PrintMode.FIT_WIDTH, header, footer);
            
        }catch(Exception e){
            JOptionPane.showConfirmDialog(this, e);
        }
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        DefaultTableModel model = (DefaultTableModel) tbl1.getModel();
        model.setRowCount(0);
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        try {
            DefaultTableModel model = (DefaultTableModel) tbl1.getModel();
            model.setRowCount(0);
            tregoBanesatMeqera("%!@#");
        } catch (ParseException ex) {
            Logger.getLogger(BanesaEShluaraMeQera.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton5ActionPerformed

    private void tbl2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl2MouseClicked
       int i = tbl2.getSelectedRow();
       
       TableModel model = tbl2.getModel();
       idQera.setText(model.getValueAt(i,0).toString());
       idBanesa.setText(model.getValueAt(i,1).toString());
       Pershkrimi.setText("Banesa me shifer apo emer "+model.getValueAt(i,2).toString()+" e clia është e madhe  "+model.getValueAt(i,4).toString()+" dhe gjendet në katin e "+ model.getValueAt(i,5).toString()+" \nqe gjendet ne "+model.getValueAt(i,6).toString()+"  ne rrugen "+model.getValueAt(i,7).toString()+"\n"+
               "e shluar me qera me daten "+model.getValueAt(i,8).toString()+" deri me daten "+model.getValueAt(i,9).toString()+" per klientin "+model.getValueAt(i,10).toString()+" "+model.getValueAt(i,11).toString()+" \nme nje qmim prej "+model.getValueAt(i,14).toString()+" euro Kontaktet e klientit \n"+
                "Numri i telefonit "+model.getValueAt(i,12).toString()+" posta elektronike "+model.getValueAt(i,13).toString());
       qmimi.setText(model.getValueAt(i,15).toString());
    }//GEN-LAST:event_tbl2MouseClicked

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        Pagesa();
    }//GEN-LAST:event_jButton8ActionPerformed

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
            java.util.logging.Logger.getLogger(BanesaEShluaraMeQera.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(BanesaEShluaraMeQera.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(BanesaEShluaraMeQera.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(BanesaEShluaraMeQera.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new BanesaEShluaraMeQera().setVisible(true);
                } catch (ParseException ex) {
                    Logger.getLogger(BanesaEShluaraMeQera.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.toedter.calendar.JDateChooser AfatiQeras;
    private javax.swing.JTextField Banesa;
    private javax.swing.JTextArea Pershkrimi;
    private javax.swing.JTextField data;
    private javax.swing.JTextField idBanesa;
    private javax.swing.JTextField idQera;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JTextField pagesaDikur;
    private javax.swing.JTextField perPagesTotali;
    private javax.swing.JTextField qmimi;
    private javax.swing.JTable tbl1;
    private javax.swing.JTable tbl2;
    // End of variables declaration//GEN-END:variables

    void setVibible(boolean b) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
