/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import LidhjaMeDatabaz.LidhjaDB;
import Modelet_Tabelat.Adresat;
import Modelet_Tabelat.KontaktetEPuntorve;
import Modelet_Tabelat.KontaktetKlient;
import Modelet_Tabelat.TeDhenatEpuntorve_tbl;
import java.awt.HeadlessException;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
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
import javax.swing.text.JTextComponent;

/**
 *
 * @author VHaliti
 */
public final class RegjistrimiKlienteve extends javax.swing.JFrame {

    /**
     * Creates new form RegjistrimiKlienteve
     * @throws java.text.ParseException
     */
    public RegjistrimiKlienteve() throws ParseException {
        initComponents();
        trego_Klientet();
        trego_kontaktetEKlienteve();
        ikonaProgramit();
        
    }
    private void ikonaProgramit() {
       setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("icon48.png")));
    }
    ////////////////////////////////
    String gjinia="";
//    -------------------------------------------
    public void pastro(){
        emri.setText("");
        mbiemri.setText("");
        nrLeternjoftimit.setText("");
        ((JTextComponent)dataLindjes.getDateEditor().getUiComponent()).setText("");
        adresaID.setText("");
        Adresa1.setText("");
        id.setText("");
        nrTelefonit.setText("");
        email.setText("");
        filter2.setText("");
        filterRruga2.setText("");
        buttonGroup1.clearSelection();
    
    }
//    ----------------------------------------------------------------
    
//  ----------------------------------------------------------------
      public ArrayList<Adresat> adresatList(String a,String b){
        ArrayList<Adresat> teDhenatList = new ArrayList<>();
         try{
            Connection con = LidhjaDB.ConnectDB();
            String query1=" SELECT * FROM tbl_Adresat";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(query1);
            Adresat teDhenatA;
            while(rs.next()){
                if(a.equals(rs.getString("qyteti")) && b.equals(rs.getString("rruga")) || a.equals("!@#") && b.equals("#@!")){
                     teDhenatA = new Adresat(rs.getInt("adresaID"), rs.getString("shteti"),rs.getString("qyteti"),rs.getInt("zipcode"),rs.getString("rruga"), rs.getInt("nrRruges"));
                     teDhenatList.add(teDhenatA);
                }
                else if(a.equals(rs.getString("qyteti")) || b.equals(rs.getString("rruga"))){
                    teDhenatA = new Adresat(rs.getInt("adresaID"), rs.getString("shteti"),rs.getString("qyteti"),rs.getInt("zipcode"),rs.getString("rruga"), rs.getInt("nrRruges"));
                    teDhenatList.add(teDhenatA);
                }
            }
        if(teDhenatList.size() == 0){
                JOptionPane.showMessageDialog(null,"Kjo Adres nuk u gjend ne Databaz");
                
            }
         }catch (HeadlessException | SQLException e) {
          JOptionPane.showMessageDialog(null,e); 
        }
         return teDhenatList;
    }
 
    public final void trego_adresat(String a, String b){
        ArrayList<Adresat> list = adresatList(a,b);
        DefaultTableModel model = (DefaultTableModel)tbl4.getModel();
        Object[] row = new Object[6];
        for(int i =0;i<list.size();i++)
        {
            row[0]=list.get(i).getID();
            row[1]= list.get(i).getShteti();
            row[2]= list.get(i).getQyteti();
            row[3]= list.get(i).getZipCode();
            row[4]= list.get(i).getRruga();
            row[5]= list.get(i).getNrRruges();         
            model.addRow(row);
        }
    }
//    -------------------------------------------------------------------------------
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
         }catch (HeadlessException | SQLException e) {
          JOptionPane.showMessageDialog(null,e); 
        }
         return teDhenatList;
    }

        public void trego_Klientet() throws ParseException{
            
        ArrayList<KontaktetKlient> list = KontaktetEKlienteveList();
        DefaultTableModel model = (DefaultTableModel)tbl1.getModel();
        Object[] row = new Object[9];
        for(int i =0;i<list.size();i++){
//            ------------------
            String ktheDatenNeString = list.get(i).getDataLindjes();    
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
//            ------------------              
            
            
            row[0]=list.get(i).getID();
            row[1]=list.get(i).getEmri();
            row[2]=list.get(i).getMbiemri();
            row[3]=list.get(i).getNrLeternjoftimit();
            row[4]=list.get(i).getGjinia();
            row[5]=DataRregullume;
            row[6]= list.get(i).getShteti();
            row[7]=list.get(i).getQyteti();
            row[8]=list.get(i).getRruga();   
            model.addRow(row);
        }
         String countIKonvertuar = Integer.toString(list.size());
         mesazhZ1.setText(countIKonvertuar);
    }
        //    ---------------------------------------------------------------------------
    public ArrayList<KontaktetEPuntorve> kontaktetEKlienteveList1(){
        ArrayList<KontaktetEPuntorve> teDhenatList = new ArrayList<>();
         try{
            Connection con = LidhjaDB.ConnectDB();
            String query1="SELECT n.kontaktiID,n.telefoni,n.email, tbl_Klienti1.emri,tbl_Klienti1.mbiemri,tbl_Klienti1.gjinia\n" +
            "FROM Kontaktet_Klienti n\n" +
            "INNER JOIN tbl_Klienti1 ON n.klientiID = tbl_Klienti1.klientiID";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(query1);
            KontaktetEPuntorve teDhenatK;
            while(rs.next()){
                teDhenatK = new KontaktetEPuntorve(rs.getInt("kontaktiID"), rs.getString("telefoni"),rs.getString("email"),rs.getString("emri"),rs.getString("mbiemri"),rs.getString("gjinia"));
                teDhenatList.add(teDhenatK);
            }
         }catch (HeadlessException | SQLException e) {
          JOptionPane.showMessageDialog(null,e); 
        }
         return teDhenatList;
    }
//-------------------------------------------------
        public void trego_kontaktetEKlienteve(){
        ArrayList<KontaktetEPuntorve> list = kontaktetEKlienteveList1();
        DefaultTableModel model = (DefaultTableModel)tbl2.getModel();
        Object[] row = new Object[6];
        for(int i =0;i<list.size();i++){
            row[0]=list.get(i).getID();
            row[1]=list.get(i).getEmri1();
            row[2]=list.get(i).getMbiemri1();
            row[3]=list.get(i).getGjinia1();
            row[4]= list.get(i).getTelefoni();
            row[5]= list.get(i).getEmaili();        
            model.addRow(row);
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
        jLabel5 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        filter2 = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        filterRruga2 = new javax.swing.JTextField();
        jButton12 = new javax.swing.JButton();
        jButton13 = new javax.swing.JButton();
        jScrollPane4 = new javax.swing.JScrollPane();
        tbl4 = new javax.swing.JTable();
        sh = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        adresaID = new javax.swing.JTextField();
        Adresa1 = new javax.swing.JTextField();
        emri = new javax.swing.JTextField();
        mbiemri = new javax.swing.JTextField();
        M = new javax.swing.JRadioButton();
        F = new javax.swing.JRadioButton();
        nrLeternjoftimit = new javax.swing.JTextField();
        dataLindjes = new com.toedter.calendar.JDateChooser();
        jPanel6 = new javax.swing.JPanel();
        jButton15 = new javax.swing.JButton();
        jButton17 = new javax.swing.JButton();
        email = new javax.swing.JTextField();
        nrTelefonit = new javax.swing.JTextField();
        id = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jButton8 = new javax.swing.JButton();
        jButton9 = new javax.swing.JButton();
        jButton10 = new javax.swing.JButton();
        jButton11 = new javax.swing.JButton();
        jButton14 = new javax.swing.JButton();
        jButton16 = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        mesazhZ1 = new javax.swing.JLabel();
        jButton18 = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbl2 = new javax.swing.JTable();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbl1 = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();

        setTitle("Regjistrimi i klienteve");

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Plotsoni të dhënat për regjistrimin e klienteve"));

        jLabel1.setText("Emri");

        jLabel2.setText("Mbiemri");

        jLabel3.setText("Gjinia");

        jLabel4.setText("Data e Lindjes");

        jLabel5.setText("Nr Leternjoftimit");

        jPanel5.setBackground(new java.awt.Color(204, 204, 255));
        jPanel5.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel14.setText("Kerko sipas qytetit");

        filter2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                filter2ActionPerformed(evt);
            }
        });

        jLabel15.setText("Kerko sipas Rruges");

        jButton12.setText("Search");
        jButton12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton12ActionPerformed(evt);
            }
        });

        jButton13.setText("Pastro tabelen");
        jButton13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton13ActionPerformed(evt);
            }
        });

        tbl4.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Shteti", "Qyteti", "ZipCode", "Rruga", "NrRruges"
            }
        ));
        tbl4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl4MouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(tbl4);

        sh.setText("ALL");
        sh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                shActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jScrollPane4)
                        .addContainerGap())
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel14)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(filter2, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(filterRruga2, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton12, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton13, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(sh)
                        .addGap(39, 39, 39))))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(filter2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel15)
                    .addComponent(filterRruga2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton12)
                    .addComponent(jButton13)
                    .addComponent(sh))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel7.setText("Id e adreses");

        adresaID.setEnabled(false);
        adresaID.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                adresaIDActionPerformed(evt);
            }
        });
        adresaID.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                adresaIDKeyTyped(evt);
            }
        });

        Adresa1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Adresa1ActionPerformed(evt);
            }
        });

        buttonGroup1.add(M);
        M.setText("Mashkull");

        buttonGroup1.add(F);
        F.setText("Femer");

        nrLeternjoftimit.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                nrLeternjoftimitKeyTyped(evt);
            }
        });

        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder("Regjistrimi kontakteve të puntorit"));

        jButton15.setText("Ruaje");
        jButton15.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton15ActionPerformed(evt);
            }
        });

        jButton17.setText("Fshije");
        jButton17.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton17ActionPerformed(evt);
            }
        });

        email.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                emailActionPerformed(evt);
            }
        });

        id.setEnabled(false);
        id.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                idKeyTyped(evt);
            }
        });

        jLabel18.setText("Id e puntorit në databaz");

        jLabel16.setText("Numri Telefonit");

        jLabel17.setText("Email ");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jLabel17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel18, javax.swing.GroupLayout.DEFAULT_SIZE, 136, Short.MAX_VALUE))
                    .addComponent(jButton15, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton17)
                    .addComponent(nrTelefonit, javax.swing.GroupLayout.DEFAULT_SIZE, 110, Short.MAX_VALUE)
                    .addComponent(email)
                    .addComponent(id))
                .addContainerGap(27, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(id, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel18, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(nrTelefonit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel16))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(email, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel17))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton15)
                    .addComponent(jButton17))
                .addContainerGap(22, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(8, 8, 8)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(18, 18, 18)
                                        .addComponent(emri, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(8, 8, 8)
                                        .addComponent(M)
                                        .addGap(18, 18, 18)
                                        .addComponent(F)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 28, Short.MAX_VALUE)
                                        .addComponent(adresaID, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(Adresa1, javax.swing.GroupLayout.PREFERRED_SIZE, 358, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)))
                                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(mbiemri, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(nrLeternjoftimit, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(18, 18, 18)
                                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(dataLindjes, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(327, 327, 327))))
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addGap(12, 12, 12))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(emri, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel3)
                                    .addComponent(M)
                                    .addComponent(F))
                                .addGap(7, 7, 7)))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(mbiemri, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel2)
                                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(dataLindjes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(11, 11, 11)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(nrLeternjoftimit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(Adresa1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(adresaID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7)))
                    .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jButton8.setText("Ruaje");
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });

        jButton9.setText("Permirso");
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });

        jButton10.setText("Anulo");
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });

        jButton11.setText("Shto Adres");
        jButton11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton11ActionPerformed(evt);
            }
        });

        jButton14.setText("Fshije");
        jButton14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton14ActionPerformed(evt);
            }
        });

        jButton16.setText("Dalje");
        jButton16.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton16ActionPerformed(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel10.setText("të regjistruar");

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel11.setText("Numri  klienteve ");

        mesazhZ1.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        mesazhZ1.setForeground(new java.awt.Color(255, 51, 51));
        mesazhZ1.setText("jLabel6");

        jButton18.setText("Shitja");
        jButton18.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton18ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(jButton11, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 87, Short.MAX_VALUE)
                                .addComponent(jButton14, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(jButton16, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel10)
                            .addComponent(jButton10, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton9, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addComponent(mesazhZ1, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton18, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton14)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton11)
                .addGap(7, 7, 7)
                .addComponent(jButton18)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton16)
                .addGap(18, 18, 18)
                .addComponent(jLabel11)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(mesazhZ1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        tbl2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Emri", "Mbiermi", "Gjinia", "Nr Telefonit", "Emaili"
            }
        ));
        jScrollPane2.setViewportView(tbl2);

        tbl1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Emri", "Mbiemri", "NrLeternjoftimit", "Gjinia", "DataLindjes", "Shteti", "Qyteti", "Rruga"
            }
        ));
        tbl1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbl1);

        jButton1.setText("Vështro kontaktet");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jScrollPane2))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton1))
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 166, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void filter2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_filter2ActionPerformed

    }//GEN-LAST:event_filter2ActionPerformed

    private void jButton12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton12ActionPerformed
        if(filter2.getText().equals("") && filterRruga2.getText().equals("") || filter2.getText().equals("!@#")){
            JOptionPane.showMessageDialog( this, "Nuk mund të bëni search nese nuk shkruani se qfar po kërkoni","Mesazh nga Autori", JOptionPane.ERROR_MESSAGE);
        }
        else{
            DefaultTableModel model = (DefaultTableModel) tbl4.getModel();
            model.setRowCount(0);
            trego_adresat(filter2.getText(),filterRruga2.getText());
            filter2.setText("");
            filterRruga2.setText("");
            sh.setEnabled(true);
        }
    }//GEN-LAST:event_jButton12ActionPerformed

    private void jButton13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton13ActionPerformed
        DefaultTableModel model = (DefaultTableModel) tbl4.getModel();
        model.setRowCount(0);
        sh.setEnabled(true);
    }//GEN-LAST:event_jButton13ActionPerformed

    private void tbl4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl4MouseClicked
        int i = tbl4.getSelectedRow();
        TableModel model = tbl4.getModel();
        adresaID.setText(model.getValueAt(i,0).toString());
        Adresa1.setText("Shteti:"+model.getValueAt(i,1).toString()+", Qyteti:"+model.getValueAt(i,2).toString()+", Rruga:"+model.getValueAt(i,4).toString());
    }//GEN-LAST:event_tbl4MouseClicked

    private void shActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_shActionPerformed
        DefaultTableModel model = (DefaultTableModel) tbl4.getModel();
        model.setRowCount(0);
        trego_adresat("!@#","#@!");
        sh.setEnabled(false);
    }//GEN-LAST:event_shActionPerformed

    private void adresaIDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_adresaIDActionPerformed

    }//GEN-LAST:event_adresaIDActionPerformed

    private void adresaIDKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_adresaIDKeyTyped
        char c = evt.getKeyChar();
        if (!(Character.isDigit(c)|| (c== KeyEvent.VK_BACK_SPACE)||(c==KeyEvent.VK_DELETE))){
            getToolkit().beep();
            evt.consume();
        }
    }//GEN-LAST:event_adresaIDKeyTyped

    private void Adresa1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Adresa1ActionPerformed

    }//GEN-LAST:event_Adresa1ActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        try{
            Connection con = LidhjaDB.ConnectDB();
            String query ="insert into tbl_Klienti1(emri,mbiemri,gjinia,dataLindjes,nrLeternjoftimit,adresaID)values(?,?,?,?,?,?)";
            PreparedStatement pst = con.prepareStatement(query);
            if(M.isSelected()){
                gjinia="Mashkull";
            }
            else if(F.isSelected()){
                gjinia="Femer";
            }
           
            if(emri.getText().equals("")){
                JOptionPane.showMessageDialog( this, "Plotso emrin e klientit","Mesazh nga Autori", JOptionPane.ERROR_MESSAGE);
            }
            else if(mbiemri.getText().equals("")){
                JOptionPane.showMessageDialog( this, "Plotso mbiemrin e klientit","Mesazh nga Autori", JOptionPane.ERROR_MESSAGE);
            }
            else if(dataLindjes.getDate() == null ){
                JOptionPane.showMessageDialog( this, "Plotso rubriken e datlindja e klientit","Mesazh nga Autori", JOptionPane.ERROR_MESSAGE);
            }
            else if(nrLeternjoftimit.getText().equals("")){
                JOptionPane.showMessageDialog( this, "Plotso rubriken e leternjoftimi i klientit","Mesazh nga Autori", JOptionPane.ERROR_MESSAGE);
            }
            else if(gjinia.equals("")){
                JOptionPane.showMessageDialog( this, "Perzgjidh gjinin e klientit","Mesazh nga Autori", JOptionPane.ERROR_MESSAGE);

            }
            else if(adresaID.getText().equals("")){
                JOptionPane.showMessageDialog( this, "Selektoni adresen e puntorit ne tabel","Mesazh nga Autori", JOptionPane.ERROR_MESSAGE);
                 DefaultTableModel model = (DefaultTableModel) tbl4.getModel();
                model.setRowCount(0);
                trego_adresat("!@#","#@!");
                sh.setEnabled(false);
            }
            else{
                pst.setString(1,emri.getText());
                pst.setString(2,mbiemri.getText());
                pst.setString(3, gjinia);
                pst.setString(4,((JTextComponent)dataLindjes.getDateEditor().getUiComponent()).getText());
                pst.setString(5,nrLeternjoftimit.getText());
                pst.setString(6,adresaID.getText());
                pst.executeUpdate();
                DefaultTableModel model = (DefaultTableModel)tbl1.getModel();
                model.setRowCount(0);
                trego_Klientet();
                JOptionPane.showMessageDialog(null,"U rexhistru me sukses!");
                pastro();
            }
        }catch (HeadlessException | SQLException e) {
            JOptionPane.showMessageDialog(null,e);
        } catch (ParseException ex) {
            Logger.getLogger(RegjistrimiKlienteve.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton8ActionPerformed

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        // TODO add your handling code here:
        try{
            int row = tbl1.getSelectedRow();
            if(tbl1.getSelectedRow() == -1){
                JOptionPane.showMessageDialog(this ,"Ju nuk mund te Editoni asnje te dhene nese nuk e selektoni!","Mesazh nga Autori", JOptionPane.ERROR_MESSAGE);
            }
            else{
                Connection con = LidhjaDB.ConnectDB();
                String IdESelektuar = (tbl1.getModel().getValueAt(row, 0).toString());
                String query ="UPDATE tbl_Klienti1 SET emri=?,mbiemri=?,gjinia=?,dataLindjes=?,nrLeternjoftimit=?,adresaID=? where klientiID="+IdESelektuar;
                 PreparedStatement pst = con.prepareStatement(query);
            if(M.isSelected()){
                gjinia="Mashkull";
            }
            else if(F.isSelected()){
                gjinia="Femer";
            }
            if(emri.getText().equals("")){
                JOptionPane.showMessageDialog( this, "Plotso emrin e klientit","Mesazh nga Autori", JOptionPane.ERROR_MESSAGE);
            }
            else if(mbiemri.getText().equals("")){
                JOptionPane.showMessageDialog( this, "Plotso mbiemrin e klientit","Mesazh nga Autori", JOptionPane.ERROR_MESSAGE);
            }
            else if(dataLindjes.getDate() == null ){
                JOptionPane.showMessageDialog( this, "Plotso rubriken e datlindja e klientit","Mesazh nga Autori", JOptionPane.ERROR_MESSAGE);
            }

            else if(nrLeternjoftimit.getText().equals("")){
                JOptionPane.showMessageDialog( this, "Plotso rubriken e leternjoftimi i klientit","Mesazh nga Autori", JOptionPane.ERROR_MESSAGE);
            }
            else if(adresaID.equals("")){
                JOptionPane.showMessageDialog( this, "Selektoni adresen e puntorit ne tabel","Mesazh nga Autori", JOptionPane.ERROR_MESSAGE);
                DefaultTableModel model = (DefaultTableModel) tbl4.getModel();
                model.setRowCount(0);
                trego_adresat("!@#","#@!");
                sh.setEnabled(false);
            }
            else{
                pst.setString(1,emri.getText());
                pst.setString(2,mbiemri.getText());
                pst.setString(3, gjinia);
                pst.setString(4,((JTextComponent)dataLindjes.getDateEditor().getUiComponent()).getText());
                pst.setString(5,nrLeternjoftimit.getText());
                pst.setString(6,adresaID.getText());
                pst.executeUpdate();
                DefaultTableModel model = (DefaultTableModel)tbl1.getModel();
                model.setRowCount(0);
                trego_Klientet();
                JOptionPane.showMessageDialog(null,"E dhëna u editua me sukses!");
                pastro();
            }
            }
        } catch (HeadlessException | SQLException e) {
            JOptionPane.showMessageDialog(null,e);
        } catch (ParseException ex) {
            Logger.getLogger(RegjistrimiKlienteve.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton9ActionPerformed

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
        pastro();
    }//GEN-LAST:event_jButton10ActionPerformed

    private void jButton11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton11ActionPerformed
        AdresaRexhister ar = new AdresaRexhister();
        ar.setVisible(true);
    }//GEN-LAST:event_jButton11ActionPerformed

    private void jButton14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton14ActionPerformed
        try{
            int row = tbl1.getSelectedRow();

            if(tbl1.getSelectedRow() == -1){
                JOptionPane.showMessageDialog(this ,"Ju nuk mund te fshini asnje Klient nese nuk e selektoni!","Mesazh nga Autori", JOptionPane.ERROR_MESSAGE);
            }
            else{
                String IdESelektuar = (tbl1.getModel().getValueAt(row, 0).toString());
                    Object [] ob = {"Po","Jo"};
                    int p = javax.swing.JOptionPane.showOptionDialog(this, "A jeni i sigurt se deshironi ta fshini pas fshirjes nuk ka me kthim te shenimeve dhe kontaktet fshihen se bashku me klientin?", "Konfirmim", JOptionPane.OK_OPTION,JOptionPane.QUESTION_MESSAGE, null, ob, ob[1]);

                    if (p==0){
                        Connection con = LidhjaDB.ConnectDB();
                        //                    String IdESelektuar = (tbl1.getModel().getValueAt(row, 0).toString());
                        String query = "DELETE FROM tbl_Klienti1 where klientiID="+IdESelektuar;
                        String query1 = "DELETE FROM Kontaktet_Klienti where klientiID="+IdESelektuar;
                        PreparedStatement pst1 = con.prepareStatement(query1);
                        pst1.executeUpdate();
                        PreparedStatement pst = con.prepareStatement(query);
                        pst.executeUpdate();
                        DefaultTableModel model = (DefaultTableModel)tbl1.getModel();
                        model.setRowCount(0);
                        DefaultTableModel model1 = (DefaultTableModel)tbl2.getModel();
                        model1.setRowCount(0);
                        trego_Klientet();
                        trego_kontaktetEKlienteve();
                        JOptionPane.showMessageDialog(this ,"E dhena u fshi me sukses","Informim", JOptionPane.INFORMATION_MESSAGE);
                        pastro();
                    }
                }
        } catch (HeadlessException | SQLException e) {
            JOptionPane.showMessageDialog(null,e);
        } catch (ParseException ex) {
            Logger.getLogger(RegjistrimiKlienteve.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton14ActionPerformed

    private void jButton16ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton16ActionPerformed
        hide();
    }//GEN-LAST:event_jButton16ActionPerformed

    private void jButton15ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton15ActionPerformed
        try{
            Connection con = LidhjaDB.ConnectDB();
            String query ="insert into Kontaktet_Klienti(telefoni,email,klientiID)values(?,?,?)";
            PreparedStatement pst = con.prepareStatement(query);

            if(id.getText().equals("")){
                JOptionPane.showMessageDialog( this, "Selektoni Klientin tek tabela","Mesazh nga Autori", JOptionPane.ERROR_MESSAGE);
            }
            else if(nrTelefonit.getText().equals("")){
                JOptionPane.showMessageDialog( this, "Shkruaje numrin e telefonit","Mesazh nga Autori", JOptionPane.ERROR_MESSAGE);
            }
            else if(email.getText().equals("")){
                JOptionPane.showMessageDialog( this, "Shkruaj emailin","Mesazh nga Autori", JOptionPane.ERROR_MESSAGE);
            }
            else if(!email.getText().contains("@")){
                JOptionPane.showMessageDialog( this, "Një email duhet te permbaj @","Mesazh nga Autori", JOptionPane.ERROR_MESSAGE);
            }
            else{
                pst.setString(1,nrTelefonit.getText());
                pst.setString(2,email.getText());
                pst.setString(3,id.getText());

                pst.executeUpdate();
                DefaultTableModel model = (DefaultTableModel)tbl2.getModel();
                model.setRowCount(0);

                 trego_kontaktetEKlienteve();
                JOptionPane.showMessageDialog(null,"Kontakti për puntorin "+emri.getText()+" u regjistrua");
                nrTelefonit.setText("");
                email.setText("");
            }
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null,e);
        }
    }//GEN-LAST:event_jButton15ActionPerformed

    private void jButton17ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton17ActionPerformed
        try{
            int row = tbl2.getSelectedRow();

            if(tbl2.getSelectedRow() == -1){
                JOptionPane.showMessageDialog(this ,"Ju nuk mund te fshini asnje kontakt nese nuk e selektoni!","Mesazh nga Autori", JOptionPane.ERROR_MESSAGE);
            }
            else{
                Object [] ob = {"Po","Jo"};
                int p = javax.swing.JOptionPane.showOptionDialog(this, "A jeni i sigurt se deshironi ta fshini pas fshirjes nuk ka me kthim te shenimeve ?", "Konfirmim", JOptionPane.OK_OPTION,JOptionPane.QUESTION_MESSAGE, null, ob, ob[1]);

                if (p==0){
                    Connection con = LidhjaDB.ConnectDB();
                    String IdESelektuar = (tbl2.getModel().getValueAt(row, 0).toString());
                    String query = "DELETE FROM Kontaktet_Klienti where kontaktiID="+IdESelektuar;
                    PreparedStatement pst = con.prepareStatement(query);
                    pst.executeUpdate();
                    DefaultTableModel model = (DefaultTableModel)tbl2.getModel();
                    model.setRowCount(0);
                    trego_kontaktetEKlienteve();
                    JOptionPane.showMessageDialog(this ,"E dhena u fshi me sukses","Informim", JOptionPane.INFORMATION_MESSAGE);

                }
            }
        } catch (HeadlessException | SQLException e) {
            JOptionPane.showMessageDialog(null,e);
        }
    }//GEN-LAST:event_jButton17ActionPerformed

    private void emailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_emailActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_emailActionPerformed

    private void tbl1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl1MouseClicked
        int i = tbl1.getSelectedRow();
       TableModel model = tbl1.getModel();
       id.setText(model.getValueAt(i,0).toString());
       emri.setText(model.getValueAt(i,1).toString());
       mbiemri.setText(model.getValueAt(i,2).toString());
       nrLeternjoftimit.setText(model.getValueAt(i, 3).toString());
       String a1 = model.getValueAt(i,4).toString();
       if(a1.equals("Mashkull")){
           M.setSelected(true);
       }
       else{
           F.setSelected(true);
       }
        ((JTextComponent)dataLindjes.getDateEditor().getUiComponent()).setText(model.getValueAt(i, 5).toString());
         filter2.setText(model.getValueAt(i, 7).toString());
         filterRruga2.setText(model.getValueAt(i, 8).toString());      
    }//GEN-LAST:event_tbl1MouseClicked

    private void nrLeternjoftimitKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_nrLeternjoftimitKeyTyped
        char c = evt.getKeyChar();
        
        if (!(Character.isDigit(c)|| (c== KeyEvent.VK_BACK_SPACE)||(c==KeyEvent.VK_DELETE))){
          getToolkit().beep();
          evt.consume();
        }
        else if(nrLeternjoftimit.getText().length() == 9){
            getToolkit().beep();
            evt.consume();
        }
    }//GEN-LAST:event_nrLeternjoftimitKeyTyped

    private void idKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_idKeyTyped
          char c = evt.getKeyChar();
        
        if (!(Character.isDigit(c)|| (c== KeyEvent.VK_BACK_SPACE)||(c==KeyEvent.VK_DELETE))){
          getToolkit().beep();
          evt.consume();
        }
    }//GEN-LAST:event_idKeyTyped

    private void jButton18ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton18ActionPerformed
       ShitBanesa shb = new ShitBanesa();
       shb.setVisible(true);
    }//GEN-LAST:event_jButton18ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        KontaktetEKlienteve k = new KontaktetEKlienteve();
        k.setVisible(true);
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
            java.util.logging.Logger.getLogger(RegjistrimiKlienteve.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(RegjistrimiKlienteve.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(RegjistrimiKlienteve.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(RegjistrimiKlienteve.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new RegjistrimiKlienteve().setVisible(true);
                } catch (ParseException ex) {
                    Logger.getLogger(RegjistrimiKlienteve.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField Adresa1;
    private javax.swing.JRadioButton F;
    private javax.swing.JRadioButton M;
    private javax.swing.JTextField adresaID;
    private javax.swing.ButtonGroup buttonGroup1;
    private com.toedter.calendar.JDateChooser dataLindjes;
    private javax.swing.JTextField email;
    private javax.swing.JTextField emri;
    private javax.swing.JTextField filter2;
    private javax.swing.JTextField filterRruga2;
    private javax.swing.JTextField id;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton12;
    private javax.swing.JButton jButton13;
    private javax.swing.JButton jButton14;
    private javax.swing.JButton jButton15;
    private javax.swing.JButton jButton16;
    private javax.swing.JButton jButton17;
    private javax.swing.JButton jButton18;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTextField mbiemri;
    private javax.swing.JLabel mesazhZ1;
    private javax.swing.JTextField nrLeternjoftimit;
    private javax.swing.JTextField nrTelefonit;
    private javax.swing.JButton sh;
    private javax.swing.JTable tbl1;
    private javax.swing.JTable tbl2;
    private javax.swing.JTable tbl4;
    // End of variables declaration//GEN-END:variables
}