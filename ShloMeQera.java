/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import LidhjaMeDatabaz.LidhjaDB;
import Modelet_Tabelat.KontaktetKlient;
import Modelet_Tabelat.TeDhenetPerFilter;
import java.awt.HeadlessException;
import java.awt.Toolkit;
import java.awt.print.PrinterException;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
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
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.text.JTextComponent;

/**
 *
 * @author VHaliti
 */
public class ShloMeQera extends javax.swing.JFrame {

    /**
     * Creates new form ShloMeQera
     */
    public ShloMeQera() {
        initComponents();
        sekori();
        LeximiNeDb_perEmrinEKlientit();
        LeximiNeDb_perNrLeternjoftimitteKlientit();
        OraDheData();
        ikonaProgramit();
    }
    private void ikonaProgramit() {
       setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("icon48.png")));
    }
    String p="";
     public void pastro(){
            filter1.setText("");
            filter2.setText("");
            Drejtimi.setSelectedIndex(0);
            madhsia.setText("");
            kati.setText("");
            buttonGroup1.clearSelection();
            nrLeternjoftimit.setSelectedIndex(0);
            emri.setSelectedIndex(0);
            id.setText("");
            id2.setText("");
            emriB.setText("");
            Pershkrimi.setText("");
            qmimi.setText("");
            perPages.setText("");
            ((JTextComponent)AfatiQeras.getDateEditor().getUiComponent()).setText("");
    }
    
    public void OraDheData(){
                    DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                    Date date = new Date();
                    data.setText(dateFormat.format(date));
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
        int Muajt = AfatiIQeras - MuajiAktaul;
        
        String pagesa = String.valueOf(Muajt*qmimiQeras);
        perPages.setText(pagesa);
        
   }
     
         
    
    
    
   public void sekori(){
        try{
	 Connection con = LidhjaDB.ConnectDB();	
												
            String sql= "Select Shfrytzuesi from tbl_Sektori";
            PreparedStatement pst = con.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            while(rs.next()){
		String emri =rs.getString("Shfrytzuesi");
                emriSh.setText("      "+emri);
            }	
	}
	catch(SQLException e){
		JOptionPane.showMessageDialog(null,e);
        }				 
    }
 private void LeximiNeDb_perEmrinEKlientit(){
	try{
	 Connection con = LidhjaDB.ConnectDB();	
												
            String sql= "Select emri from tbl_Klienti1";
            PreparedStatement pst = con.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            while(rs.next()){
		String name =rs.getString("emri");
		this.emri.addItem(name);	
            }	
	}
	catch(SQLException e){
		JOptionPane.showMessageDialog(null,e);
        }																
    }
    private void LeximiNeDb_perNrLeternjoftimitteKlientit(){
	try{
	 Connection con = LidhjaDB.ConnectDB();	
												
            String sql= "Select nrLeternjoftimit from tbl_Klienti1";
            PreparedStatement pst = con.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            while(rs.next()){
		String name =rs.getString("nrLeternjoftimit");
		this.nrLeternjoftimit.addItem(name);	
            }	
	}
	catch(SQLException e){
		JOptionPane.showMessageDialog(null,e);
        }																
    }
       public ArrayList<TeDhenetPerFilter> TeDhenatTotaleList(String a1,String a2,String a3,String a4,String a5,String a6){

         ArrayList<TeDhenetPerFilter> teDhenatList = new ArrayList<>();
         try{
            Connection con = LidhjaDB.ConnectDB();
            String query1="SELECT b.banesaID,b.nrBaneses,b.mobilimi,b.drejtimi,b.koment,b.madhesia,b.Kati,b.Statusi,tbl_Ndertesa.emri,tbl_Ndertesa.nrKateve,\n" +
            "tbl_Ndertesa.parkingu,tbl_Ndertesa.ashensori,tbl_Adresat.shteti,tbl_Adresat.qyteti,tbl_Adresat.rruga\n" +
            "FROM ((tbl_Banesa b\n" +
            "FULL OUTER JOIN tbl_Ndertesa ON tbl_Ndertesa.ndertesaID=b.ndertesaID)\n" +
            "FULL OUTER JOIN tbl_Adresat ON tbl_Ndertesa.adresaID = tbl_Adresat.adresaID);";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(query1);
            TeDhenetPerFilter teDhenatA1;
            while(rs.next()){
                 if((a1.equals(rs.getString("qyteti")) && a2.equals(rs.getString("rruga"))) && (a3.equals(rs.getString("drejtimi")) && a4.equals(rs.getString("madhesia")))
              && (a5.equals(rs.getString("Kati")) && a6.equals(rs.getString("mobilimi"))) && ("E lir".equals(rs.getString("Statusi"))))
                 {
                    teDhenatA1 = new TeDhenetPerFilter(rs.getInt("banesaID"), rs.getString("nrBaneses"),rs.getString("mobilimi"),rs.getString("drejtimi"),rs.getString("koment"),
                    rs.getString("madhesia"),rs.getString("Kati"),rs.getString("emri"),rs.getString("Statusi"),rs.getString("nrKateve"),rs.getString("parkingu"),
                    rs.getString("ashensori"),rs.getString("shteti"),rs.getString("qyteti"),rs.getString("rruga"));
                    teDhenatList.add(teDhenatA1);
                 }
            }
            if(teDhenatList.isEmpty()){
                JOptionPane.showMessageDialog(null,"Me keto te dhena nuk u gjend as nje informacion!");
            }
         }catch (HeadlessException | SQLException e) {
          JOptionPane.showMessageDialog(null,e); 
        }
         return teDhenatList;
    }

    public final void Kthe_Filterin(String a1,String a2,String a3,String a4,String a5,String a6){
        ArrayList<TeDhenetPerFilter> list = TeDhenatTotaleList(a1,a2,a3,a4,a5,a6);
        DefaultTableModel model = (DefaultTableModel)tbl1.getModel();
        Object[] row = new Object[15];
      
        for(int i =0;i<list.size();i++){                  
                    row[0]= list.get(i).getID();
                    row[1]= list.get(i).getEmriN();
                    row[2]= list.get(i).getNrBaneses();
                    row[3]= list.get(i).getDrejtimi();
                    row[4]= list.get(i).getMadhsia();
                    row[5]= list.get(i).getKati();
                    row[6]= list.get(i).getMobilimi();
                    row[7]= list.get(i).getStatusi();
                    row[8]= list.get(i).getKoment();
                    row[9]= list.get(i).getParkingu();
                    row[10]=list.get(i).getAshencori();
                    row[11]=list.get(i).getNrKateve();
                    row[12]=list.get(i).getShteti();
                    row[13]=list.get(i).getQyteti();
                    row[14]=list.get(i).getRruga();
                    model.addRow(row);             
        }
//        String countIKonvertuar = Integer.toString(list.size());
//        mesazhZ.setText(countIKonvertuar); 
    }
      public ArrayList<KontaktetKlient> KontaktetEKlienteveList(){
        ArrayList<KontaktetKlient> teDhenatList = new ArrayList<>();
         try{
            Connection con = LidhjaDB.ConnectDB();
            String query1=" SELECT n.klientiID,n.emri,n.mbiemri,n.gjinia,n.dataLindjes,n.nrLeternjoftimit,tbl_Adresat.shteti,tbl_Adresat.qyteti,tbl_Adresat.rruga\n" +
            "FROM tbl_Klienti1 n\n" +
            "INNER JOIN tbl_Adresat ON n.adresaID = tbl_Adresat.adresaID";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(query1);
            KontaktetKlient teDhenatK;
            while(rs.next()){
                    teDhenatK = new KontaktetKlient(rs.getInt("klientiID"), rs.getString("emri"),rs.getString("mbiemri"),rs.getString("gjinia"),rs.getString("dataLindjes"),rs.getInt("nrLeternjoftimit"),rs.getString("shteti"),rs.getString("qyteti"),rs.getString("rruga"));
                    teDhenatList.add(teDhenatK);
            }
            if(teDhenatList.size() == 0)
            {
                JOptionPane.showMessageDialog(null,"Kombinimi per gjetjen e klientit ishte gabim!");
            }
         }catch (HeadlessException | SQLException e) {
          JOptionPane.showMessageDialog(null,e); 
        }
         return teDhenatList;
    }

        public final void trego_Klientet(String a,String b){
        ArrayList<KontaktetKlient> list = KontaktetEKlienteveList();
//        DefaultTableModel model = (DefaultTableModel)tbl1.getModel();
        
        int c=1;
        int StringConvert = Integer.parseInt(b);
        
        for(int i =0;i<list.size();i++){
            if(list.get(i).getEmri().equals(a) && StringConvert == list.get(i).getNrLeternjoftimit()){
                String countIKonvertuar = Integer.toString(list.get(i).getID());
                id.setText(countIKonvertuar);
            }
        }
        
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbl1 = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        filter1 = new javax.swing.JTextField();
        madhsia = new javax.swing.JTextField();
        rbPO = new javax.swing.JRadioButton();
        rbJO = new javax.swing.JRadioButton();
        jLabel16 = new javax.swing.JLabel();
        filter2 = new javax.swing.JTextField();
        kati = new javax.swing.JTextField();
        Drejtimi = new javax.swing.JComboBox<>();
        jLabel17 = new javax.swing.JLabel();
        ora = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jButton10 = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        Pershkrimi = new javax.swing.JTextArea();
        jPanel2 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        emri = new javax.swing.JComboBox<>();
        nrLeternjoftimit = new javax.swing.JComboBox<>();
        id = new javax.swing.JTextField();
        emriSh = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        id2 = new javax.swing.JTextField();
        emriB = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        data = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        AfatiQeras = new com.toedter.calendar.JDateChooser();
        jButton2 = new javax.swing.JButton();
        Qera = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        kontrata = new javax.swing.JButton();
        jLabel11 = new javax.swing.JLabel();
        qmimi = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        perPages = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();

        setTitle("Jep me Qera");

        tbl1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Ndertesa", "Banesa", "Drejtimi", "Madhsia", "Kati", "Mobilimi", "Statusi", "Koment", "Parkingu", "Ashencori", "NrKateve", "Shteti", "Qyteti", "Rruga"
            }
        ));
        tbl1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbl1);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Kerkesat"));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 153, 153));
        jLabel1.setText("Filteri");

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel2.setText("Drejtimi");

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel3.setText("      Madhsia");

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel4.setText("Mobilimi");

        buttonGroup1.add(rbPO);
        rbPO.setText("PO");

        buttonGroup1.add(rbJO);
        rbJO.setText("JO");
        rbJO.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbJOActionPerformed(evt);
            }
        });

        jLabel16.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel16.setText("Kati");

        kati.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                katiActionPerformed(evt);
            }
        });

        Drejtimi.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Zgjidh Drejtimin", "Veri", "Jug", "Lindje", "Perendim", " " }));

        jLabel17.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel17.setText("Kërko sipas: Qytetit                       Rruges");

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/GUI/search1.png"))); // NOI18N
        jLabel5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel5MouseClicked(evt);
            }
        });

        jButton10.setText("Search");
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(150, 150, 150))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(filter1, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(filter2, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Drejtimi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(madhsia, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(kati, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 244, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(14, 14, 14)
                        .addComponent(jLabel3)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(rbPO, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(rbJO))
                    .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(ora, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jButton10)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(ora, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton10, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(90, 90, 90))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(1, 1, 1)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel17)
                                        .addComponent(jLabel2)
                                        .addComponent(jLabel4)
                                        .addComponent(jLabel16)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(rbPO)
                                    .addComponent(rbJO)
                                    .addComponent(kati, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(madhsia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(Drejtimi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(filter2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(filter1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel1))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        Pershkrimi.setColumns(20);
        Pershkrimi.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        Pershkrimi.setRows(5);
        Pershkrimi.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        Pershkrimi.setDisabledTextColor(new java.awt.Color(255, 0, 0));
        Pershkrimi.setEnabled(false);
        jScrollPane3.setViewportView(Pershkrimi);

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Procedurat e shlurjes me qera"));

        jLabel8.setText("Shfrytzusi aktiv");

        jLabel7.setText("Zgjedh Klientin sipas");

        emri.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Emri" }));

        nrLeternjoftimit.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "nrLeternjoftimit" }));

        id.setEnabled(false);

        emriSh.setEditable(false);
        emriSh.setBackground(new java.awt.Color(255, 255, 255));
        emriSh.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        emriSh.setForeground(new java.awt.Color(255, 0, 0));
        emriSh.setEnabled(false);

        jLabel13.setText("Per Banesen");

        id2.setEnabled(false);

        emriB.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        emriB.setEnabled(false);

        jButton1.setText("Dalje");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel9.setText("Nga data ");

        data.setEditable(false);
        data.setBackground(new java.awt.Color(204, 204, 204));
        data.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N

        jLabel10.setText("deri");

        jButton2.setText("Llogarit");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        Qera.setText("Qera");
        Qera.setEnabled(false);
        Qera.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                QeraActionPerformed(evt);
            }
        });

        jButton4.setText("Anulo");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        kontrata.setText("Kontrata");
        kontrata.setEnabled(false);
        kontrata.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                kontrataActionPerformed(evt);
            }
        });

        jLabel11.setText("Qmimi për muaj");

        qmimi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                qmimiActionPerformed(evt);
            }
        });

        jLabel12.setText("Për pages");

        perPages.setEditable(false);
        perPages.setEnabled(false);
        perPages.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                perPagesActionPerformed(evt);
            }
        });

        jLabel14.setText("Metoda e pageses");

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Zgjidh metoden e pageses", "Kesh", "Permes Banke" }));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(0, 0, Short.MAX_VALUE))
                                    .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(14, 14, 14)))
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(perPages, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addGroup(jPanel2Layout.createSequentialGroup()
                                            .addComponent(id2, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(emriB))
                                        .addGroup(jPanel2Layout.createSequentialGroup()
                                            .addComponent(data, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(AfatiQeras, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addContainerGap())
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(emriSh, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(emri, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(nrLeternjoftimit, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(id, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(28, 28, 28))))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jButton2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(kontrata)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(Qera, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(24, 24, 24)
                                .addComponent(qmimi, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel12)))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 246, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(emriSh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(emri, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(nrLeternjoftimit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(id, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(emriB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(id2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel9)
                        .addComponent(data, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel10))
                    .addComponent(AfatiQeras, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel11)
                        .addComponent(qmimi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel12))
                    .addComponent(perPages, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel14))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(Qera)
                    .addComponent(kontrata)
                    .addComponent(jButton4)
                    .addComponent(jButton2))
                .addContainerGap())
        );

        jMenu1.setText("Vështro banesat me qera kliko F2");

        jMenuItem1.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F2, 0));
        jMenuItem1.setText("Banesat me qera");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem1);

        jMenuItem2.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_ESCAPE, 0));
        jMenuItem2.setText("Dalje");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem2);

        jMenuBar1.add(jMenu1);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jScrollPane1)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 752, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 752, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane3))
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(22, 22, 22)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 362, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tbl1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl1MouseClicked
        int i = tbl1.getSelectedRow();

        TableModel model = tbl1.getModel();

        Pershkrimi.setText("Banesa me shifer apo emer "+model.getValueAt(i,2).toString()+" e clia është e drejtuar nga "+model.getValueAt(i,3).toString()+" dhe gjendet në katin e "+ model.getValueAt(i,5).toString()+" me madhsi "+model.getValueAt(i,4).toString()+" metra katror\n"+
            "Përgigjja me po ose jo për mobilimin ("+model.getValueAt(i,6).toString()+") gjendet ne ndertesen "+model.getValueAt(i,1).toString()+"Koment rreth baneses:"+model.getValueAt(i,8).toString()+"\nPër të dhënat e nderteses ne fjal shikoni rreshtat e mëposhtem:\n"+
            "Parkingu "+model.getValueAt(i,9).toString()+", Ashencori "+model.getValueAt(i,10).toString()+", Kjo ndertes ka "+model.getValueAt(i,11).toString()+" kate lartesi gjithsej, Gjendet në "+
            ": \""+model.getValueAt(i,12).toString()+", "+model.getValueAt(i,13).toString()+", "+model.getValueAt(i,14).toString()+"\"");
        id2.setText(model.getValueAt(i,0).toString());
        emriB.setText(model.getValueAt(i,2).toString());
//        adresa.setText(model.getValueAt(i,12).toString()+", "+model.getValueAt(i,13).toString()+", "+model.getValueAt(i,14).toString());
//        madhsia1.setText(model.getValueAt(i,4).toString());

    }//GEN-LAST:event_tbl1MouseClicked

    private void rbJOActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbJOActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rbJOActionPerformed

    private void katiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_katiActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_katiActionPerformed

    private void jLabel5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel5MouseClicked
        if(rbPO.isSelected()){
            p="Po";
        }
        else if(rbJO.isSelected()){
            p="Jo";
        }

        Kthe_Filterin(filter1.getText(),filter2.getText(),Drejtimi.getSelectedItem().toString(),madhsia.getText(),kati.getText(),p);
    }//GEN-LAST:event_jLabel5MouseClicked

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
        //       Ka pun hala validimin...
        DefaultTableModel model = (DefaultTableModel) tbl1.getModel();
        model.setRowCount(0);
        if(rbPO.isSelected()){
            p="Po";
        }
        else if(rbJO.isSelected()){
            p="Jo";
        }
        if(filter1.getText().equals("") || filter2.getText().equals("") || Drejtimi.getSelectedItem().toString().equals("Zgjidh Drejtimin")
            ||  madhsia.getText().equals("") || kati.getText().equals("") || p.equals("")){
            JOptionPane.showMessageDialog(null,"Duhen plotsuar te gjitha te dhenat!");
        }else{
            Kthe_Filterin(filter1.getText(),filter2.getText(),Drejtimi.getSelectedItem().toString(),madhsia.getText(),kati.getText(),p);
//            pastro("1");
        }
    }//GEN-LAST:event_jButton10ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        hide();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
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
            else if(id2.getText().equals("")){
            JOptionPane.showMessageDialog( this, "Duhet selektuar Banesa qe deshironi ta jepni me qera","Mesazh nga Autori", JOptionPane.ERROR_MESSAGE);
            }
             else if(emri.getSelectedItem().toString().equals("Emri")){
            JOptionPane.showMessageDialog( this, "Perzgjidh emrin e klientit","Mesazh nga Autori", JOptionPane.ERROR_MESSAGE);
            }
             else if(nrLeternjoftimit.getSelectedItem().toString().equals("Nr leternjoftimit")){
            JOptionPane.showMessageDialog( this, "Perzgjidh numrin e leternjoftimit te klientit","Mesazh nga Autori", JOptionPane.ERROR_MESSAGE);
            }
             else if(qmimi.getText().equals("")){
             JOptionPane.showMessageDialog( this, "Shkruaj qmimin e qeras per nje muaj","Mesazh nga Autori", JOptionPane.ERROR_MESSAGE);
             }
             else if(jComboBox1.getSelectedItem().toString().equals("Zgjidh metoden e pageses")){
            JOptionPane.showMessageDialog( this, "Perzgjidh menyren e pageses","Mesazh nga Autori", JOptionPane.ERROR_MESSAGE);
             }
             else{
            trego_Klientet(emri.getSelectedItem().toString(),nrLeternjoftimit.getSelectedItem().toString());
            Pagesa();
            Qera.setEnabled(true);
            kontrata.setEnabled(true);
            }
       }
        else{
             if(id2.getText().equals("")){
                    JOptionPane.showMessageDialog( this, "Duhet selektuar Banesa qe deshironi ta jepni me qera","Mesazh nga Autori", JOptionPane.ERROR_MESSAGE);
             }
             else if(emri.getSelectedItem().toString().equals("Emri")){
                    JOptionPane.showMessageDialog( this, "Perzgjidh emrin e klientit","Mesazh nga Autori", JOptionPane.ERROR_MESSAGE);
                }
             else if(nrLeternjoftimit.getSelectedItem().toString().equals("Nr leternjoftimit")){
                    JOptionPane.showMessageDialog( this, "Perzgjidh numrin e leternjoftimit te klientit","Mesazh nga Autori", JOptionPane.ERROR_MESSAGE);
                }
             else if(qmimi.getText().equals("")){
             JOptionPane.showMessageDialog( this, "Shkruaj qmimin e qeras per nje muaj","Mesazh nga Autori", JOptionPane.ERROR_MESSAGE);
             }
             else{
                   JOptionPane.showMessageDialog( this, "Llogaritja esht Planifikaur te behet vetem mbrenda vitit qe jemi dmth "+VitiB,"Mesazh nga Autori", JOptionPane.ERROR_MESSAGE);
                }
            }
    }//GEN-LAST:event_jButton2ActionPerformed
}
    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        pastro();
    }//GEN-LAST:event_jButton4ActionPerformed

    private void kontrataActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_kontrataActionPerformed
        JFileChooser ShfaqeRrugen = new JFileChooser();
        ShfaqeRrugen.showOpenDialog(null);
        File kuMelexu = ShfaqeRrugen.getSelectedFile();
        String EmriFajllitKuLexon = kuMelexu.getAbsolutePath();
        try{
            FileReader lexo = new FileReader(EmriFajllitKuLexon);
            BufferedReader br = new BufferedReader(lexo);
            Pershkrimi.read(br,null);
            br.close();
            Pershkrimi.requestFocus();
            Pershkrimi.print();
        }catch(PrinterException | IOException e){
        JOptionPane.showMessageDialog(null, e);
     }       
    }//GEN-LAST:event_kontrataActionPerformed
    
    
    private void QeraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_QeraActionPerformed
        try{
//            Date date02 = new SimpleDateFormat("yyyy-mm-dd").parse(((JTextComponent)AfatiQeras.getDateEditor().getUiComponent()).getText());
//            DateFormat formatter = new SimpleDateFormat("yyyy-mm-dd");
//            String data123 = formatter.format(((JTextComponent)AfatiQeras.getDateEditor().getUiComponent()).getText());
            Connection con = LidhjaDB.ConnectDB();
            String query = "Insert into tbl_BanesatMeQera1(datafillimit,datambarimit,banesaID,KlientiID,PagesaMujore,metodaPageses,PuntoriQeShiti,QerajaMujore)values(?,?,?,?,?,?,?,?)"; 
            PreparedStatement pst = con.prepareStatement(query);
                pst.setString(1,data.getText());
                pst.setString(2,((JTextComponent)AfatiQeras.getDateEditor().getUiComponent()).getText());
                pst.setString(3,id2.getText());
                pst.setString(4,id.getText());
                pst.setString(5,perPages.getText());
                String metodapageses;
                metodapageses = jComboBox1.getSelectedItem().toString();            
                pst.setString(6,metodapageses);
                pst.setString(7,emriSh.getText()); 
                pst.setString(8,qmimi.getText());
                int row = tbl1.getSelectedRow();
                String IdESelektuar1 = (tbl1.getModel().getValueAt(row, 0).toString());
                String query1 =" UPDATE tbl_Banesa SET Statusi=? where banesaID="+IdESelektuar1;
                PreparedStatement pst1 = con.prepareStatement(query1);
                pst1.setString(1,"Me Qera");
                JOptionPane.showMessageDialog(null,"Banesa u leshua me Qera");
                pst1.executeUpdate();
                pst.executeUpdate();
                DefaultTableModel model = (DefaultTableModel)tbl1.getModel();
                model.setRowCount(0);
                pastro();
        }catch(HeadlessException | SQLException e){
            JOptionPane.showMessageDialog(null,e);
        }
    }//GEN-LAST:event_QeraActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        try {
            BanesaEShluaraMeQera bshq = new BanesaEShluaraMeQera();
            bshq.setVisible(true);
        } catch (ParseException ex) {
            Logger.getLogger(ShloMeQera.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void perPagesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_perPagesActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_perPagesActionPerformed

    private void qmimiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_qmimiActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_qmimiActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        hide();
    }//GEN-LAST:event_jMenuItem2ActionPerformed

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
            java.util.logging.Logger.getLogger(ShloMeQera.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ShloMeQera.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ShloMeQera.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ShloMeQera.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ShloMeQera().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.toedter.calendar.JDateChooser AfatiQeras;
    private javax.swing.JComboBox<String> Drejtimi;
    private javax.swing.JTextArea Pershkrimi;
    private javax.swing.JButton Qera;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JTextField data;
    private javax.swing.JComboBox<String> emri;
    private javax.swing.JTextField emriB;
    private javax.swing.JTextField emriSh;
    private javax.swing.JTextField filter1;
    private javax.swing.JTextField filter2;
    private javax.swing.JTextField id;
    private javax.swing.JTextField id2;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton4;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTextField kati;
    private javax.swing.JButton kontrata;
    private javax.swing.JTextField madhsia;
    private javax.swing.JComboBox<String> nrLeternjoftimit;
    private javax.swing.JLabel ora;
    private javax.swing.JTextField perPages;
    private javax.swing.JTextField qmimi;
    private javax.swing.JRadioButton rbJO;
    private javax.swing.JRadioButton rbPO;
    private javax.swing.JTable tbl1;
    // End of variables declaration//GEN-END:variables
}
