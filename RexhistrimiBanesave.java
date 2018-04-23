/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import LidhjaMeDatabaz.LidhjaDB;
import Modelet_Tabelat.Banesat;
import Modelet_Tabelat.Ndertesa;
import java.awt.HeadlessException;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

/**
 *
 * @author Vhaliti
 */
public class RexhistrimiBanesave extends javax.swing.JFrame {

    /**
     * Creates new form RexhistrimiBanesave
     */
    public RexhistrimiBanesave() {
        initComponents();
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        LeximiNeDb_perComboBox();
        trego_Banesa();
        ikonaProgramit();

       
    }
    private void ikonaProgramit() {
       setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("icon48.png")));
    }
       String a="";
       String mobilimi ="";
//    ----------------------------------
  
    private void LeximiNeDb_perComboBox(){
	try{
	 Connection con = LidhjaDB.ConnectDB();	
												
            String sql= "Select emri from tbl_Ndertesa";
            PreparedStatement pst = con.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            while(rs.next()){
		String name =rs.getString("emri");
		this.combo1.addItem(name);	
            }	
	}
	catch(SQLException e){
		JOptionPane.showMessageDialog(null,e);
        }																
    }
//    ----------------------------------------------------------
     public ArrayList<Ndertesa> NdertesatList(){
        ArrayList<Ndertesa> teDhenatList = new ArrayList<>();
         try{
            Connection con = LidhjaDB.ConnectDB();
            String query1="SELECT n.ndertesaID,n.emri,n.nrKateve, n.parkingu, n.ashensori,n.vitiNdertimit, tbl_Adresat.shteti,tbl_Adresat.qyteti,tbl_Adresat.rruga\n" +
            "FROM tbl_Ndertesa n\n" +
            "INNER JOIN tbl_Adresat ON n.adresaID = tbl_Adresat.adresaID";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(query1);
            Ndertesa teDhenatA1;
            while(rs.next()){
                teDhenatA1 = new Ndertesa(rs.getInt("ndertesaID"), rs.getString("emri"),rs.getString("nrKateve"),rs.getString("parkingu"),rs.getString("ashensori"), rs.getInt("vitiNdertimit"),rs.getString("shteti"),rs.getString("qyteti"),rs.getString("rruga"));
                teDhenatList.add(teDhenatA1);
               
            }   
         }catch (HeadlessException | SQLException e) {
          JOptionPane.showMessageDialog(null,e); 
        }
         return teDhenatList;
    }
     //Metoda qe tregon Ndertesat e rexhistruara
    public final void trego_Ndertesat(String a){
        ArrayList<Ndertesa> list = NdertesatList();
//        DefaultTableModel model = (DefaultTableModel)tbl1.getModel();
        Object[] row = new Object[9];
        int c = 0;
        for(int i =0;i<list.size();i++){
            if(a.equals(list.get(i).getEmri())){
               c = list.get(i).getID(); 
            }
            row[0]=list.get(i).getID();
            row[1]=list.get(i).getEmri();
            row[2]=list.get(i).getNrKateve();
            row[3]=list.get(i).getParkingu();
            row[4]= list.get(i).getAshencori();
            row[5]= list.get(i).getVitiNdertimit();
            row[6]= list.get(i).getShteti();
            row[7]=list.get(i).getQyteti();
            row[8]=list.get(i).getRruga();
        }
        String countIKonvertuar = Integer.toString(c);
        id.setText(countIKonvertuar); 
    }
//    ----------------------------------------------------------
    public ArrayList<Banesat> BanesatList(){
        ArrayList<Banesat> teDhenatList = new ArrayList<>();
         try{
            Connection con = LidhjaDB.ConnectDB();
            String query1="SELECT n.banesaID,n.nrBaneses,n.mobilimi, n.drejtimi, n.koment,n.madhesia, n.Kati,n.Statusi, tbl_Ndertesa.emri\n" +
            "FROM tbl_Banesa n\n" +
            "INNER JOIN tbl_Ndertesa ON n.ndertesaID = tbl_Ndertesa.ndertesaID";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(query1);
            Banesat teDhenatA1;
            while(rs.next()){
                teDhenatA1 = new Banesat(rs.getInt("banesaID"), rs.getString("nrBaneses"),rs.getString("mobilimi"),rs.getString("drejtimi"),rs.getString("koment"), rs.getString("madhesia"),rs.getString("Kati"),rs.getString("emri"),rs.getString("Statusi"));
                teDhenatList.add(teDhenatA1);
            }   
         }catch (HeadlessException | SQLException e) {
          JOptionPane.showMessageDialog(null,e); 
        }
         return teDhenatList;
    }
     //Metoda qe tregon Ndertesat e rexhistruara
    public final void trego_Banesa(){
        ArrayList<Banesat> list = BanesatList();
        DefaultTableModel model = (DefaultTableModel)tbl1.getModel();
        Object[] row = new Object[9];
        int c = 0;
        for(int i =0;i<list.size();i++){
            row[0]=list.get(i).getID();
            row[1]=list.get(i).getEmriN();
            row[2]=list.get(i).getEmri();
            row[3]=list.get(i).getMobilimi();
            row[4]=list.get(i).getDrejtimi();
            row[5]= list.get(i).getKoment();
            row[6]= list.get(i).getMadhsia();
            row[7]= list.get(i).getKati();
            row[8]= list.get(i).getStatusi();
            model.addRow(row);
        }
         String countIKonvertuar = Integer.toString(list.size());
         mesazhZ.setText(countIKonvertuar);
    }
//    --------------------------------------------
//    Metoda Pastro
    public void pastro(){
        txt_banesa.setText("");
        id.setText("");
        combo1.setSelectedIndex(0);
        drejtimi.setSelectedIndex(0);
        txt_madhsia.setText("");
        kati.setText("");
        buttonGroup1.clearSelection();
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
        jScrollPane2 = new javax.swing.JScrollPane();
        koment = new javax.swing.JTextArea();
        jLabel6 = new javax.swing.JLabel();
        txt_banesa = new javax.swing.JTextField();
        combo1 = new javax.swing.JComboBox<>();
        drejtimi = new javax.swing.JComboBox<>();
        rbPO = new javax.swing.JRadioButton();
        rbJO = new javax.swing.JRadioButton();
        txt_madhsia = new javax.swing.JTextField();
        id = new javax.swing.JTextField();
        jButton7 = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        kati = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbl1 = new javax.swing.JTable();
        jLabel8 = new javax.swing.JLabel();
        mesazhZ = new javax.swing.JLabel();

        setTitle("Regjistrimi Banesave");

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Rexhistrimi te dhenave"));

        jLabel1.setText("Emri Baneses(shifra)");

        jLabel2.setText("Ciles Ndertes i takon");

        jLabel3.setText("Drejtimi");

        jLabel4.setText("Madhsia");

        jLabel5.setText("Mobilimi");

        koment.setColumns(20);
        koment.setRows(5);
        jScrollPane2.setViewportView(koment);

        jLabel6.setText("Koment rreth baneses");

        txt_banesa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_banesaActionPerformed(evt);
            }
        });

        combo1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Zgjidh Ndertesen" }));

        drejtimi.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Zgjidh Drejtimin", "Veri", "Jug", "Lindje", "Perendim", " " }));

        buttonGroup1.add(rbPO);
        rbPO.setText("PO");

        buttonGroup1.add(rbJO);
        rbJO.setText("JO");

        txt_madhsia.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_madhsiaKeyTyped(evt);
            }
        });

        id.setEnabled(false);
        id.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                idActionPerformed(evt);
            }
        });

        jButton7.setText("S");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        jLabel7.setText("Kati");

        jLabel9.setText("metra ");

        jLabel10.setText("2");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(id, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(combo1, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jButton7))
                            .addComponent(txt_banesa, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 98, Short.MAX_VALUE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 53, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(rbPO)
                                        .addGap(50, 50, 50)
                                        .addComponent(rbJO))
                                    .addComponent(txt_madhsia, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel10))
                            .addComponent(drejtimi, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(kati, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 348, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(4, 4, 4)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txt_banesa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(id, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(combo1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(drejtimi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txt_madhsia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4)
                            .addComponent(jLabel9)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addComponent(jLabel10)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(kati, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rbJO)
                    .addComponent(rbPO)
                    .addComponent(jLabel5))
                .addGap(31, 31, 31))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jButton1.setText("Ruaj");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Fshie");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("Permirso");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setText("Anulo");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton6.setText("Dalje");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jButton8.setText("Ndertesat");
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
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton8, javax.swing.GroupLayout.DEFAULT_SIZE, 87, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton8)
                .addContainerGap(35, Short.MAX_VALUE))
        );

        tbl1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Ndertesa", "Banesa", "Moblimi", "Drejtimi", "Komenti", "Madhsia", "Kati", "Statusi"
            }
        ));
        tbl1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl1MouseClicked(evt);
            }
        });
        tbl1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tbl1KeyTyped(evt);
            }
        });
        jScrollPane1.setViewportView(tbl1);

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel8.setText("Numri i  banesave të regjistruar:");

        mesazhZ.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        mesazhZ.setForeground(new java.awt.Color(255, 51, 51));
        mesazhZ.setText("jLabel6");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(mesazhZ, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 800, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap())))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 233, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(mesazhZ, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        hide();
    }//GEN-LAST:event_jButton6ActionPerformed

    private void txt_banesaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_banesaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_banesaActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        try{
            Connection con = LidhjaDB.ConnectDB();
            String query = "Insert into tbl_Banesa(nrBaneses,mobilimi,drejtimi,koment,madhesia,ndertesaID,Kati,Statusi)values(?,?,?,?,?,?,?,?)"; 
            PreparedStatement pst = con.prepareStatement(query);
            if(rbPO.isSelected()){
                mobilimi="Po";
            }
            else if(rbJO.isSelected()){
                mobilimi="Jo";
            }        
            if(txt_banesa.getText().equals("")){
                JOptionPane.showMessageDialog( this, "Shkruaj Emrin apo shifren e baneses","Mesazh nga Autori", JOptionPane.ERROR_MESSAGE);
            }
            else if(combo1.getSelectedItem().toString().equals("Zgjidh Ndertesen")){
                JOptionPane.showMessageDialog( this, "Zgjidh Ndertesen pastaj kliko butonin S","Mesazh nga Autori", JOptionPane.ERROR_MESSAGE);
            }
            else if(id.getText().equals("")){
                JOptionPane.showMessageDialog( this, "Ju lutem klikoni butonin S","Mesazh nga Autori", JOptionPane.ERROR_MESSAGE);
            }
            else if(drejtimi.getSelectedItem().toString().equals("Zgjidh Drejtimin")){
                JOptionPane.showMessageDialog( this, "Perzgjidh drejtimin e baneses","Mesazh nga Autori", JOptionPane.ERROR_MESSAGE);
            }
            else if(mobilimi.equals("")){
                JOptionPane.showMessageDialog( this, "Perzgjidh a është e mobiluar banesa","Mesazh nga Autori", JOptionPane.ERROR_MESSAGE);
            }
            else if(kati.getText().equals("")){
                JOptionPane.showMessageDialog( this, "Shkrauj katin ku ndodhet banesa","Mesazh nga Autori", JOptionPane.ERROR_MESSAGE);
            }
            else{ 
                pst.setString(1,txt_banesa.getText());
                pst.setString(2,mobilimi);
                String drejtimiSend;
                drejtimiSend = drejtimi.getSelectedItem().toString();
                pst.setString(3,drejtimiSend);
                pst.setString(4,koment.getText());
                pst.setString(5,txt_madhsia.getText());
                pst.setString(6,id.getText());
                pst.setString(7,kati.getText());
                pst.setString(8,"E lir");
                pst.executeUpdate();
                DefaultTableModel model = (DefaultTableModel)tbl1.getModel();
                model.setRowCount(0);
                trego_Banesa();
                JOptionPane.showMessageDialog(null,"Te dhenat u rexhistruan ne databaze");
                pastro();
            }
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null,e);
        }  
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        trego_Ndertesat(combo1.getSelectedItem().toString());
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
       pastro();
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        try{
            int row = tbl1.getSelectedRow();
            if(tbl1.getSelectedRow() == -1){
                    JOptionPane.showMessageDialog(this ,"Ju nuk mund te Editoni asnje Banes nese nuk e selektoni në tabel!","Mesazh nga Autori", JOptionPane.ERROR_MESSAGE);             
            }
            else{
                Connection con = LidhjaDB.ConnectDB();
                String IdESelektuar1 = (tbl1.getModel().getValueAt(row, 0).toString());
                String query =" UPDATE tbl_Ndertesa SET nrBaneses=?, mobilimi=?, drejtimi=?, koment=?, madhesia=?, ndertesaID=? ,kati=?where ndertesaID="+IdESelektuar1;
                PreparedStatement pst = con.prepareStatement(query);
            
            if(rbPO.isSelected()){
                mobilimi="Po";
            }
            else if(rbJO.isSelected()){
                mobilimi="Jo";
            }        
            if(txt_banesa.getText().equals("")){
                JOptionPane.showMessageDialog( this, "Shkruaj Emrin apo shifren e baneses","Mesazh nga Autori", JOptionPane.ERROR_MESSAGE);
            }
            else if(combo1.getSelectedItem().toString().equals("Zgjidh Ndertesen")){
                JOptionPane.showMessageDialog( this, "Zgjidh Ndertesen pastaj kliko butonin S","Mesazh nga Autori", JOptionPane.ERROR_MESSAGE);
            }
            else if(id.getText().equals("")){
                JOptionPane.showMessageDialog( this, "Ju lutem klikoni butonin S","Mesazh nga Autori", JOptionPane.ERROR_MESSAGE);
            }
            else if(drejtimi.getSelectedItem().toString().equals("Zgjidh Drejtimin")){
                JOptionPane.showMessageDialog( this, "Perzgjidh drejtimin e baneses","Mesazh nga Autori", JOptionPane.ERROR_MESSAGE);
            }
            else if(mobilimi.equals("")){
                JOptionPane.showMessageDialog( this, "Perzgjidh a është e mobiluar banesa","Mesazh nga Autori", JOptionPane.ERROR_MESSAGE);
            }
            else if(kati.getText().equals("")){
                JOptionPane.showMessageDialog( this, "Shkrauj katin ku ndodhet banesa","Mesazh nga Autori", JOptionPane.ERROR_MESSAGE);
            }
            else
                { 
                    pst.setString(1,txt_banesa.getText());
                    pst.setString(2,mobilimi);
                    String drejtimiSend;
                    drejtimiSend = drejtimi.getSelectedItem().toString();
                    pst.setString(3,drejtimiSend);
                    pst.setString(4,koment.getText());
                    pst.setString(5,txt_madhsia.getText());
                    pst.setString(6,id.getText());
                    pst.setString(7,kati.getText());

                    pst.executeUpdate();
                    DefaultTableModel model = (DefaultTableModel)tbl1.getModel();
                    model.setRowCount(0);
                    trego_Banesa();
                    JOptionPane.showMessageDialog(null,"Te dhenat u rexhistruan ne databaze");
                    pastro();
                }
            }
        } catch (HeadlessException | SQLException e) {
          JOptionPane.showMessageDialog(null,e); 
        }   
    }//GEN-LAST:event_jButton3ActionPerformed

    private void tbl1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbl1KeyTyped
        
    }//GEN-LAST:event_tbl1KeyTyped

    private void tbl1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl1MouseClicked
       int i = tbl1.getSelectedRow();
       TableModel model = tbl1.getModel();
       txt_banesa.setText(model.getValueAt(i,2).toString());
       String a1 = model.getValueAt(i,3).toString();
       if(a1.equals("Po")){
           rbPO.setSelected(true);
       }
       else{
           rbJO.setSelected(true);
       }
       String comboBox2Copy = model.getValueAt(i,4).toString();
       switch(comboBox2Copy){
           case "Zgjidh Drejtimin":
               drejtimi.setSelectedIndex(0);
                break;
           case "Veri":
                drejtimi.setSelectedIndex(1);
                break;
           case "Jug":
               drejtimi.setSelectedIndex(2);
               break;
           case "Lindje":
               drejtimi.setSelectedIndex(3);
               break;
               case "Perendim":
               drejtimi.setSelectedIndex(4);
               break;
       }
       koment.setText(model.getValueAt(i,5).toString());
       txt_madhsia.setText(model.getValueAt(i,6).toString());
       kati.setText(model.getValueAt(i,7).toString());
    }//GEN-LAST:event_tbl1MouseClicked

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        try{
            int row = tbl1.getSelectedRow();
            
            if(tbl1.getSelectedRow() == -1){
                    JOptionPane.showMessageDialog(this ,"Ju nuk mund te fshini asnje shfrytezues nese nuk e selektoni!","Mesazh nga Autori", JOptionPane.ERROR_MESSAGE);             
            }
            else{
            Object [] ob = {"Po","Jo"};
            int p = javax.swing.JOptionPane.showOptionDialog(this, "A jeni i sigurt se deshironi ta fshini pas fshirjes nuk ka me kthim te shenimeve ?", "Konfirmim", JOptionPane.OK_OPTION,JOptionPane.QUESTION_MESSAGE, null, ob, ob[1]);
            
            if (p==0){
            Connection con = LidhjaDB.ConnectDB();
//            int row = tbl.getSelectedRow();
            String IdESelektuar = (tbl1.getModel().getValueAt(row, 0).toString());
            String query = "DELETE FROM tbl_Banesa where banesaID="+IdESelektuar;
            PreparedStatement pst = con.prepareStatement(query);
            pst.executeUpdate();
            DefaultTableModel model = (DefaultTableModel)tbl1.getModel();
            model.setRowCount(0);
            trego_Banesa();
            JOptionPane.showMessageDialog(this ,"E dhena u fshi me sukses","Informim", JOptionPane.INFORMATION_MESSAGE);
            pastro(); 
            }
            }            
            } catch (HeadlessException | SQLException e) {
          JOptionPane.showMessageDialog(null,"Kjo banes nuk mund te fshihet per arsyeje sepse esht ne shfrytzim"); 
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        RexhistrimiNdertesave rn = new RexhistrimiNdertesave();
        rn.setVisible(true);
        hide();
    }//GEN-LAST:event_jButton8ActionPerformed

    private void idActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_idActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_idActionPerformed

    private void txt_madhsiaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_madhsiaKeyTyped
       char c = evt.getKeyChar();
        if (!(Character.isDigit(c)|| (c== KeyEvent.VK_BACK_SPACE)||(c==KeyEvent.VK_DELETE))){
          getToolkit().beep();
          evt.consume();
        }
        else if(txt_madhsia.getText().length() == 4){
            getToolkit().beep();
            evt.consume();
        }
    }//GEN-LAST:event_txt_madhsiaKeyTyped
    
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
            java.util.logging.Logger.getLogger(RexhistrimiBanesave.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(RexhistrimiBanesave.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(RexhistrimiBanesave.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(RexhistrimiBanesave.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new RexhistrimiBanesave().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JComboBox<String> combo1;
    private javax.swing.JComboBox<String> drejtimi;
    private javax.swing.JTextField id;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextField kati;
    private javax.swing.JTextArea koment;
    private javax.swing.JLabel mesazhZ;
    private javax.swing.JRadioButton rbJO;
    private javax.swing.JRadioButton rbPO;
    private javax.swing.JTable tbl1;
    private javax.swing.JTextField txt_banesa;
    private javax.swing.JTextField txt_madhsia;
    // End of variables declaration//GEN-END:variables
}
