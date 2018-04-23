/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package GUI;

import LidhjaMeDatabaz.LidhjaDB;
import Modelet_Tabelat.Banesat;
import Modelet_Tabelat.KontaktetKlient;
import Modelet_Tabelat.Ndertesa;
import Modelet_Tabelat.TeDhenetPerFilter;
import java.awt.Color;
import java.awt.HeadlessException;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import static java.lang.Thread.sleep;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.MessageFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Clock;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

/**
 *
 * @author Vhaliti
 */
public class ShitBanesa extends javax.swing.JFrame {

    /** Creates new form ShitBanesa */
    public ShitBanesa() {
        initComponents();
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        LeximiNeDb_perNrLeternjoftimitteKlientit();
        LeximiNeDb_perEmrinEKlientit();
        ruaje.setEnabled(false);
        sekori();
        OraDheData();
        ikonaProgramit();
        
    }
    private void ikonaProgramit() {
       setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("icon48.png")));
    }
    String p="";
     
        public void OraDheData(){
                    DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                    Date date = new Date();
                    data.setText(dateFormat.format(date));
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
//-------------------------------------------------------------
    public void pastro(String a){
        if(a.equals("1")){
            filter1.setText("");
            filter2.setText("");
            Drejtimi.setSelectedIndex(0);
            madhsia.setText("");
            kati.setText("");
            buttonGroup1.clearSelection();
             Pershkrimi.setText("");
        }  
        else{
            filter1.setText("");
            filter2.setText("");
            Drejtimi.setSelectedIndex(0);
            madhsia.setText("");
            kati.setText("");
            buttonGroup1.clearSelection();
            nrLeternjoftimit.setSelectedIndex(0);
            emri.setSelectedIndex(0);
            id1.setText("");
            id2.setText("");
            adresa.setText("");
            madhsia1.setText("");
            qmimi1.setText("");
            qmimiTotal.setText("");
            koment.setText("");
            emriB.setText("");
            Pershkrimi.setText("");
            jComboBox1.setSelectedIndex(0);
        }
    }
//    -----------------------------------------------
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
//    ------------------------------------------------------------------------
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
            if(teDhenatList.size() == 0){
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
        String countIKonvertuar = Integer.toString(list.size());
        mesazhZ.setText(countIKonvertuar); 
    }
//    ------------------------------------------------------------------
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
                id1.setText(countIKonvertuar);
            }
        }
        
    }
    
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
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
        jLabel5 = new javax.swing.JLabel();
        Drejtimi = new javax.swing.JComboBox<>();
        jPanel4 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jButton9 = new javax.swing.JButton();
        jButton10 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        jLabel17 = new javax.swing.JLabel();
        ora = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbl1 = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        ruaje = new javax.swing.JButton();
        Kontrata = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jButton11 = new javax.swing.JButton();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        emri = new javax.swing.JComboBox<>();
        emriB = new javax.swing.JTextField();
        qmimi1 = new javax.swing.JTextField();
        qmimiTotal = new javax.swing.JTextField();
        id1 = new javax.swing.JTextField();
        nrLeternjoftimit = new javax.swing.JComboBox<>();
        id2 = new javax.swing.JTextField();
        adresa = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        madhsia1 = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        koment = new javax.swing.JTextArea();
        jComboBox1 = new javax.swing.JComboBox<>();
        jLabel10 = new javax.swing.JLabel();
        emriSh = new javax.swing.JTextField();
        jScrollPane3 = new javax.swing.JScrollPane();
        Pershkrimi = new javax.swing.JTextArea();
        jLabel9 = new javax.swing.JLabel();
        mesazhZ = new javax.swing.JLabel();
        data = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();

        setTitle("Shit banesa");

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Kerkesat"));

        jLabel1.setText("Adresa");

        jLabel2.setText("Drejtimi");

        jLabel3.setText("Madhsia");

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

        jLabel16.setText("Kati");

        kati.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                katiActionPerformed(evt);
            }
        });

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/GUI/ccc.png"))); // NOI18N
        jLabel5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel5MouseClicked(evt);
            }
        });

        Drejtimi.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Zgjidh Drejtimin", "Veri", "Jug", "Lindje", "Perendim", " " }));

        jPanel4.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jButton1.setText("Anulo");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton9.setText("Pastro tabelen");
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });

        jButton10.setText("Search");
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });

        jButton8.setText("Dalje");
        jButton8.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton10, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton9)
                .addGap(14, 14, 14)
                .addComponent(jButton8, javax.swing.GroupLayout.DEFAULT_SIZE, 116, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton9, javax.swing.GroupLayout.DEFAULT_SIZE, 39, Short.MAX_VALUE)
                    .addComponent(jButton10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jLabel17.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel17.setText("Kërko sipas:                Qytetit                      Rruges");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, 90, Short.MAX_VALUE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel16))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(kati)
                            .addComponent(madhsia)
                            .addComponent(Drejtimi, javax.swing.GroupLayout.Alignment.TRAILING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(rbPO, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(61, 61, 61)
                                .addComponent(rbJO)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(filter1, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(filter2))))
                    .addComponent(jLabel17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ora, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(150, 150, 150))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel17)
                    .addComponent(ora, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(filter1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(filter2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(Drejtimi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(madhsia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(4, 4, 4)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel16)
                            .addComponent(kati, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(rbJO)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel4)
                                .addComponent(rbPO))))
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, 4, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

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

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Procedurat e shitjes"));

        jPanel3.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        ruaje.setText("Shite Banesen");
        ruaje.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ruajeActionPerformed(evt);
            }
        });

        Kontrata.setText("Shtyp Kontraten");
        Kontrata.setEnabled(false);
        Kontrata.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                KontrataActionPerformed(evt);
            }
        });

        jButton4.setText("Rexhistro Klient");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton5.setText("Anulo sherbimin");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jButton6.setText("Me Qera");
        jButton6.setCursor(new java.awt.Cursor(java.awt.Cursor.MOVE_CURSOR));
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jButton7.setText("Dalje");
        jButton7.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        jButton11.setText("Llogarit");
        jButton11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton11ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(Kontrata, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(ruaje, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(ruaje)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Kontrata)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton11)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton7)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel11.setText("Shitja po realizohet nga:");

        jLabel12.setText("Emri Blersit");

        jLabel13.setText("Per Banesen");

        jLabel14.setText("Qmimi per meter ne katror");

        jLabel15.setText("Qmimi Total");

        emri.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Emri" }));

        emriB.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        emriB.setEnabled(false);

        qmimi1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                qmimi1ActionPerformed(evt);
            }
        });
        qmimi1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                qmimi1KeyTyped(evt);
            }
        });

        qmimiTotal.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        qmimiTotal.setDisabledTextColor(new java.awt.Color(255, 0, 0));
        qmimiTotal.setEnabled(false);
        qmimiTotal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                qmimiTotalActionPerformed(evt);
            }
        });

        id1.setEnabled(false);
        id1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                id1ActionPerformed(evt);
            }
        });

        nrLeternjoftimit.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Nr leternjoftimit" }));

        id2.setEnabled(false);

        adresa.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        adresa.setEnabled(false);
        adresa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                adresaActionPerformed(evt);
            }
        });

        jLabel7.setText("Adresa");

        jLabel8.setText("Madhsia");

        madhsia1.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        madhsia1.setEnabled(false);

        koment.setColumns(20);
        koment.setRows(1);
        koment.setTabSize(5);
        jScrollPane2.setViewportView(koment);

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Zgjidh metoden e pageses", "Kesh", "Permes Banke" }));

        jLabel10.setText("Metoda e pageses");

        emriSh.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        emriSh.setDisabledTextColor(new java.awt.Color(255, 0, 51));
        emriSh.setEnabled(false);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, 133, Short.MAX_VALUE)
                            .addComponent(jLabel15, javax.swing.GroupLayout.DEFAULT_SIZE, 133, Short.MAX_VALUE)
                            .addComponent(jLabel14, javax.swing.GroupLayout.DEFAULT_SIZE, 133, Short.MAX_VALUE)
                            .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(qmimiTotal, javax.swing.GroupLayout.DEFAULT_SIZE, 86, Short.MAX_VALUE)
                                    .addComponent(qmimi1))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                            .addComponent(jComboBox1, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(id2, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(emriB))
                            .addComponent(adresa)
                            .addComponent(madhsia1)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addComponent(emri, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 10, Short.MAX_VALUE)
                                .addComponent(nrLeternjoftimit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(8, 8, 8)
                                .addComponent(id1, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(emriSh))))
                .addGap(18, 18, 18)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(emriSh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(emri, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(id1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(nrLeternjoftimit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(13, 13, 13)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(emriB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(id2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(adresa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(madhsia1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel14)
                            .addComponent(qmimi1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel15)
                            .addComponent(qmimiTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jScrollPane2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        Pershkrimi.setColumns(20);
        Pershkrimi.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        Pershkrimi.setRows(5);
        Pershkrimi.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        Pershkrimi.setDisabledTextColor(new java.awt.Color(255, 0, 0));
        Pershkrimi.setEnabled(false);
        jScrollPane3.setViewportView(Pershkrimi);

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel9.setText("Rezultatet e klikimit");

        mesazhZ.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        mesazhZ.setForeground(new java.awt.Color(255, 51, 51));

        data.setDisabledTextColor(new java.awt.Color(255, 0, 0));
        data.setEnabled(false);

        jLabel18.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(255, 0, 0));
        jLabel18.setText("Data :");

        jMenu1.setText("Veshtro Banesat e shitura  Kliko F1");

        jMenuItem1.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F1, 0));
        jMenuItem1.setText("Veshtro Banesat e shitura");
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
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1)
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(7, 7, 7))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, 158, Short.MAX_VALUE)
                                    .addComponent(mesazhZ, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(data, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(14, 14, 14)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 873, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(13, 13, 13))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(data, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel18))
                        .addGap(3, 3, 3)
                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(mesazhZ, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 11, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane3)
                        .addGap(15, 15, 15)))
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 248, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void ruajeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ruajeActionPerformed
        try{
            Connection con = LidhjaDB.ConnectDB();
            String query = "Insert into tbl_BanesatEShitura1(dataMarrveshjes,koment,banesaID,klientiID,QmimiTotal,metodaPageses,PuntoriQeShiti)values(?,?,?,?,?,?,?)"; 
            PreparedStatement pst = con.prepareStatement(query);
                pst.setString(1,data.getText());
                pst.setString(2,koment.getText());
                pst.setString(3,id2.getText());
                pst.setString(4,id1.getText());
                pst.setString(5,qmimiTotal.getText());
                String metodapageses;
                metodapageses = jComboBox1.getSelectedItem().toString();            
                pst.setString(6,metodapageses);
                pst.setString(7,emriSh.getText());       
                int row = tbl1.getSelectedRow();
                String IdESelektuar1 = (tbl1.getModel().getValueAt(row, 0).toString());
                String query1 =" UPDATE tbl_Banesa SET Statusi=? where banesaID="+IdESelektuar1;
                PreparedStatement pst1 = con.prepareStatement(query1);
                pst1.setString(1,"E Shitur");
                JOptionPane.showMessageDialog(null,"Banesa u shit");
                
                pst1.executeUpdate();
                pst.executeUpdate();
                DefaultTableModel model = (DefaultTableModel)tbl1.getModel();
                model.setRowCount(0);
                pastro("2");
        }catch(HeadlessException | SQLException e){
            JOptionPane.showMessageDialog(null,e);
        }  
    }//GEN-LAST:event_ruajeActionPerformed

    private void KontrataActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_KontrataActionPerformed
        
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
        }catch(Exception e){
        JOptionPane.showMessageDialog(null, e);
     }
    }//GEN-LAST:event_KontrataActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        hide();
    }//GEN-LAST:event_jButton7ActionPerformed

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

    private void id1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_id1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_id1ActionPerformed

    private void qmimi1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_qmimi1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_qmimi1ActionPerformed

    private void qmimiTotalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_qmimiTotalActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_qmimiTotalActionPerformed

    private void adresaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_adresaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_adresaActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        pastro("1");
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
       pastro("2");
    }//GEN-LAST:event_jButton5ActionPerformed

    private void tbl1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl1MouseClicked
       int i = tbl1.getSelectedRow();
       
       TableModel model = tbl1.getModel();
       
        Pershkrimi.setText("Banesa me shifer apo emer "+model.getValueAt(i,2).toString()+" e clia është e drejtuar nga "+model.getValueAt(i,3).toString()+" dhe gjendet në katin e "+ model.getValueAt(i,5).toString()+" me madhsi "+model.getValueAt(i,4).toString()+" metra katror\n"+
                "Përgigjja me po ose jo për mobilimin ("+model.getValueAt(i,6).toString()+") gjendet ne ndertesen "+model.getValueAt(i,1).toString()+"Koment rreth baneses:"+model.getValueAt(i,8).toString()+"\nPër të dhënat e nderteses ne fjal shikoni rreshtat e mëposhtem:\n"+
                "Parkingu "+model.getValueAt(i,9).toString()+", Ashencori "+model.getValueAt(i,10).toString()+", Kjo ndertes ka "+model.getValueAt(i,11).toString()+" kate lartesi gjithsej, Gjendet në "+
                ": \""+model.getValueAt(i,12).toString()+", "+model.getValueAt(i,13).toString()+", "+model.getValueAt(i,14).toString()+"\"");
                id2.setText(model.getValueAt(i,0).toString());
                emriB.setText(model.getValueAt(i,2).toString());
                adresa.setText(model.getValueAt(i,12).toString()+", "+model.getValueAt(i,13).toString()+", "+model.getValueAt(i,14).toString());
                madhsia1.setText(model.getValueAt(i,4).toString());
                
                
    }//GEN-LAST:event_tbl1MouseClicked

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
        pastro("1");
        }
    }//GEN-LAST:event_jButton10ActionPerformed

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        DefaultTableModel model = (DefaultTableModel) tbl1.getModel();
        model.setRowCount(0);
    }//GEN-LAST:event_jButton9ActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        hide();
    }//GEN-LAST:event_jButton8ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        RegjistrimiKlienteve rk = null;
        try {
            rk = new RegjistrimiKlienteve();
        } catch (ParseException ex) {
            Logger.getLogger(ShitBanesa.class.getName()).log(Level.SEVERE, null, ex);
        }
        rk.setVisible(true);
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        ShloMeQera bmq = new ShloMeQera();
        bmq.setVisible(true);
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton11ActionPerformed
        
        if(madhsia1.getText().equals("")){
                    JOptionPane.showMessageDialog(null,"Perzgjidhe ndertesen qe deshironi per ta llogaritur qmimin");
        }
        else if(qmimi1.getText().equals("")){
            JOptionPane.showMessageDialog(null,"Plotso rubriken e qmimit per meter ne katror");
        }
        else if(emri.getSelectedItem().toString().equals("Emri")){
            JOptionPane.showMessageDialog( this, "Perzgjidh emrin e klientit","Mesazh nga Autori", JOptionPane.ERROR_MESSAGE);
        }
        else if(nrLeternjoftimit.getSelectedItem().toString().equals("Nr leternjoftimit")){
            JOptionPane.showMessageDialog( this, "Perzgjidh numrin e leternjoftimit te klientit","Mesazh nga Autori", JOptionPane.ERROR_MESSAGE);
        }
        else if(jComboBox1.getSelectedItem().toString().equals("Zgjidh metoden e pageses")){
            JOptionPane.showMessageDialog( this, "Perzgjidh menyren e pageses","Mesazh nga Autori", JOptionPane.ERROR_MESSAGE);
        }
        else{
            trego_Klientet(emri.getSelectedItem().toString(),nrLeternjoftimit.getSelectedItem().toString());
            float a = Float.parseFloat(madhsia1.getText());
            float b = Float.parseFloat(qmimi1.getText());
            float c = a * b;
            String TOTAL = String.valueOf(c);
            qmimiTotal.setText(TOTAL);
            Kontrata.setEnabled(true);
            ruaje.setEnabled(true);
        }
    }//GEN-LAST:event_jButton11ActionPerformed

    private void qmimi1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_qmimi1KeyTyped
       char c = evt.getKeyChar();
        if (!(Character.isDigit(c)|| (c== KeyEvent.VK_BACK_SPACE)||(c==KeyEvent.VK_DELETE))){
          getToolkit().beep();
          evt.consume();
        }
    }//GEN-LAST:event_qmimi1KeyTyped

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        hide();
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
       BanesatEShitura bsh = null;
       bsh = new BanesatEShitura();
       bsh.setVisible(true);
    }//GEN-LAST:event_jMenuItem1ActionPerformed

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
            java.util.logging.Logger.getLogger(ShitBanesa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ShitBanesa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ShitBanesa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ShitBanesa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ShitBanesa().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> Drejtimi;
    private javax.swing.JButton Kontrata;
    private javax.swing.JTextArea Pershkrimi;
    private javax.swing.JTextField adresa;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JTextField data;
    private javax.swing.JComboBox<String> emri;
    private javax.swing.JTextField emriB;
    private javax.swing.JTextField emriSh;
    private javax.swing.JTextField filter1;
    private javax.swing.JTextField filter2;
    private javax.swing.JTextField id1;
    private javax.swing.JTextField id2;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
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
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTextField kati;
    private javax.swing.JTextArea koment;
    private javax.swing.JTextField madhsia;
    private javax.swing.JTextField madhsia1;
    private javax.swing.JLabel mesazhZ;
    private javax.swing.JComboBox<String> nrLeternjoftimit;
    private javax.swing.JLabel ora;
    private javax.swing.JTextField qmimi1;
    private javax.swing.JTextField qmimiTotal;
    private javax.swing.JRadioButton rbJO;
    private javax.swing.JRadioButton rbPO;
    private javax.swing.JButton ruaje;
    private javax.swing.JTable tbl1;
    // End of variables declaration//GEN-END:variables

}
