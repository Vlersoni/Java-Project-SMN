/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import LidhjaMeDatabaz.LidhjaDB;
import Modelet_Tabelat.KontaktetEKompanis;
import Modelet_Tabelat.Shfrytzust_tbl;
import Modelet_Tabelat.TeDhenatEKompanis_tbl;
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
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

/**
 *
 * @author Vhaliti
 */
public class TeDhenatEKompanis extends javax.swing.JFrame {
    String testemri="";
    /**
     * Creates new form TeDhenatEKompanis
     */
  
    public TeDhenatEKompanis() {
       
        initComponents();
         setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        trego_TeDhenatEKompanis();
        BllokoRuaje();
        trego_kontaktetEKompanis();
        ikonaProgramit();
    }
    private void ikonaProgramit() {
       setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("icon48.png")));
    }

   
    @SuppressWarnings("unchecked")
    //metod per me dit a me blloku butonin ruaje
    public void BllokoRuaje(){
        try{
            Connection con = LidhjaDB.ConnectDB();	

               String sql= "Select emri from tbl_Kompania";
               PreparedStatement pst = con.prepareStatement(sql);
               ResultSet rs = pst.executeQuery();
               while(rs.next()){
                   if( !testemri.equals((rs.getString("emri"))))
                       ruaje.setEnabled(false);
                   else
                       ruaje.setEnabled(true);
               }	
             
        }catch(SQLException e){
		JOptionPane.showMessageDialog(null,e);
        }		
    }
    //Metoda qe te dhenat e kompanis i fut ne ArrayList 
     public ArrayList<TeDhenatEKompanis_tbl> TeDhenatEKompanisList(){
        ArrayList<TeDhenatEKompanis_tbl> teDhenatList = new ArrayList<>();
         try{
            Connection con = LidhjaDB.ConnectDB();
            String query1=" SELECT * FROM tbl_Kompania";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(query1);
            TeDhenatEKompanis_tbl teDhenatK;
            while(rs.next()){
                teDhenatK = new TeDhenatEKompanis_tbl(rs.getInt("kompaniaID"), rs.getString("emri"),rs.getString("shteti"),rs.getString("qyteti"),rs.getInt("zipcode"),rs.getString("rruga"), rs.getInt("nrBiznesit"), rs.getInt("nrTvsh"), rs.getInt("nrFiskal"),rs.getInt("vitiThemelimit"));
                teDhenatList.add(teDhenatK);
            }
         }catch (HeadlessException | SQLException e) {
          JOptionPane.showMessageDialog(null,e); 
        }
         return teDhenatList;
    }
//     ----------------------------------------------------
      public ArrayList<KontaktetEKompanis> kontaktetEKompanisList(){
        ArrayList<KontaktetEKompanis> teDhenatList = new ArrayList<>();
         try{
            Connection con = LidhjaDB.ConnectDB();
            String query1=" SELECT * FROM Kontaktet_Kompania";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(query1);
            KontaktetEKompanis teDhenatK;
            while(rs.next()){
                teDhenatK = new KontaktetEKompanis(rs.getInt("kontaktiID"), rs.getString("telefoni"),rs.getString("email"));
                teDhenatList.add(teDhenatK);
            }
         }catch (HeadlessException | SQLException e) {
          JOptionPane.showMessageDialog(null,e); 
        }
         return teDhenatList;
    }
//-------------------------------------------------
        public void trego_kontaktetEKompanis(){
        ArrayList<KontaktetEKompanis> list = kontaktetEKompanisList();
        DefaultTableModel model = (DefaultTableModel)tbl2.getModel();
        Object[] row = new Object[3];
        for(int i =0;i<list.size();i++){
            row[0]=list.get(i).getID();
            row[1]= list.get(i).getTelefoni();
            row[2]= list.get(i).getEmaili();        
            model.addRow(row);
        }
    }

    
    //Metoda qe tregon Te Dhenat e kompanis
    public void trego_TeDhenatEKompanis(){
        ArrayList<TeDhenatEKompanis_tbl> list = TeDhenatEKompanisList();
        DefaultTableModel model = (DefaultTableModel)tbl1.getModel();
        Object[] row = new Object[10];
        for(int i =0;i<list.size();i++){
            row[0]=list.get(i).getID();
            row[1]= list.get(i).getEmri();
            row[2]= list.get(i).getShteti();
            row[3]= list.get(i).getQyteti();
            row[4]= list.get(i).getZipcode();
            row[5]= list.get(i).getRruga();
            row[6]= list.get(i).getNrBiznesit();
            row[7]= list.get(i).getNrTvsh();
            row[8]= list.get(i).getNrFiskal();
            row[9]= list.get(i).getVitiThemelimit();         
            model.addRow(row);
        }
    }
    //Metoda pastro
    public void pastro(){
        emri.setText("");
        adresa1.setText("");
        adresa2.setText("");
        adresa3.setText("");
        rruga.setText("");
        emri.setText("");
        NRBiz.setText("");
        NrTVSH.setText("");
        NrFiskal.setText("");
        vitiThemelimit.setText("");
        id.setText("");
        nrTelefonit.setText("");
        email.setText("");
    }
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        NRBiz = new javax.swing.JTextField();
        NrTVSH = new javax.swing.JTextField();
        NrFiskal = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        emri = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        vitiThemelimit = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        adresa1 = new javax.swing.JTextField();
        adresa2 = new javax.swing.JTextField();
        adresa3 = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        rruga = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbl1 = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        ruaje = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        nrTelefonit = new javax.swing.JTextField();
        email = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        id = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbl2 = new javax.swing.JTable();

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Rexhistrimi i te dhenave te kompanis"));

        NRBiz.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                NRBizKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                NRBizKeyTyped(evt);
            }
        });

        NrTVSH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NrTVSHActionPerformed(evt);
            }
        });
        NrTVSH.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                NrTVSHKeyTyped(evt);
            }
        });

        NrFiskal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NrFiskalActionPerformed(evt);
            }
        });
        NrFiskal.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                NrFiskalKeyTyped(evt);
            }
        });

        jLabel1.setText("Emri");

        jLabel2.setText("NRB i Biznesit");

        jLabel3.setText("NRB Tvsh");

        jLabel4.setText("Nr Fiskal");

        jLabel6.setText("Viti Themelimit");

        vitiThemelimit.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                vitiThemelimitKeyTyped(evt);
            }
        });

        jLabel7.setText("Adresa");

        jLabel8.setText("Qyteti");

        jLabel9.setText("Zipcode");

        adresa1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                adresa1ActionPerformed(evt);
            }
        });

        jLabel5.setText("Rruga");

        rruga.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rrugaActionPerformed(evt);
            }
        });

        jLabel10.setText("Shteti");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(NRBiz, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(emri, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(NrTVSH, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(NrFiskal, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(vitiThemelimit))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7))
                        .addGap(35, 35, 35)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(rruga)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel9)
                                .addContainerGap())
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(adresa1, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(adresa2, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(adresa3, javax.swing.GroupLayout.DEFAULT_SIZE, 121, Short.MAX_VALUE))))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel10)
                            .addComponent(jLabel8)
                            .addComponent(jLabel9))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(adresa2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(adresa1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(adresa3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(rruga, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(vitiThemelimit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(emri, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(NRBiz, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(NrTVSH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(NrFiskal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(17, Short.MAX_VALUE))
        );

        tbl1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Emri", "Shteti", "Qyteti", "Zipcode", "Rruga", "NRBTvsh", "nrFiskal", "NRBisnesit", "vitiThemelimit"
            }
        ));
        tbl1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl1MouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tbl1);

        jPanel3.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        ruaje.setText("Ruaj");
        ruaje.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ruajeActionPerformed(evt);
            }
        });

        jButton2.setText("Permirso");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("Dalje");
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

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(ruaje, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton2)
                .addGap(27, 27, 27)
                .addComponent(jButton4)
                .addGap(28, 28, 28)
                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ruaje)
                    .addComponent(jButton2)
                    .addComponent(jButton4)
                    .addComponent(jButton3))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createTitledBorder("Regjistrimi kontakteve te kompanis")));

        jLabel11.setText("Numri Telefonit");

        jLabel12.setText("Email ");

        email.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                emailActionPerformed(evt);
            }
        });

        jLabel13.setText("Id e kompanis në databaz");

        id.setEnabled(false);
        id.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                idKeyTyped(evt);
            }
        });

        jButton1.setText("Ruaje");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton5.setText("Fshije");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, 93, Short.MAX_VALUE)
                            .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(email)
                            .addComponent(nrTelefonit)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel13)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(id))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap(45, Short.MAX_VALUE)
                        .addComponent(jButton1)
                        .addGap(18, 18, 18)
                        .addComponent(jButton5)
                        .addGap(34, 34, 34)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(id, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(nrTelefonit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(email, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 30, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton5)
                    .addComponent(jButton1))
                .addGap(21, 21, 21))
        );

        tbl2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Numrat e Telefonit", "Emalet"
            }
        ));
        jScrollPane1.setViewportView(tbl2);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane2)
                    .addComponent(jScrollPane1))
                .addContainerGap(48, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        hide();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void NrTVSHKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_NrTVSHKeyTyped
      char c=evt.getKeyChar();
      if (!(Character.isDigit(c)|| (c== KeyEvent.VK_BACK_SPACE)||(c==KeyEvent.VK_DELETE))){
          getToolkit().beep();
          evt.consume();
          }
    }//GEN-LAST:event_NrTVSHKeyTyped

    private void NrFiskalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NrFiskalActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_NrFiskalActionPerformed

    private void NrFiskalKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_NrFiskalKeyTyped
      char c = evt.getKeyChar();
      if (!(Character.isDigit(c)|| (c== KeyEvent.VK_BACK_SPACE)||(c==KeyEvent.VK_DELETE))){
          getToolkit().beep();
          evt.consume();
          }
    }//GEN-LAST:event_NrFiskalKeyTyped

    private void NrTVSHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NrTVSHActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_NrTVSHActionPerformed

    private void ruajeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ruajeActionPerformed
        try{
             Connection con = LidhjaDB.ConnectDB();	
             String query ="insert into tbl_Kompania(emri,shteti,qyteti,zipcode,rruga,NRBiznesit,NrTvsh,nrFiskal,vitiThemelimit)values(?,?,?,?,?,?,?,?,?)";
             PreparedStatement pst = con.prepareStatement(query);
            
            if(emri.getText().equals("")){
               JOptionPane.showMessageDialog( this, "Shkruaj Emrin e kompanis","Mesazh nga Autori", JOptionPane.ERROR_MESSAGE);
            }
            else if(adresa1.getText().equals("")){
             JOptionPane.showMessageDialog( this, "Shkruaj Emrin e shtetit","Mesazh nga Autori", JOptionPane.ERROR_MESSAGE);
            }
            else if(adresa2.getText().equals("")){
             JOptionPane.showMessageDialog( this, "Shkruaj emrin e qytetit","Mesazh nga Autori", JOptionPane.ERROR_MESSAGE);
            }
            else if(adresa3.getText().equals("")){
             JOptionPane.showMessageDialog( this, "Shkruaj emrin e zipcode","Mesazh nga Autori", JOptionPane.ERROR_MESSAGE);
            }
            else if(rruga.getText().equals("")){
             JOptionPane.showMessageDialog( this, "Shkruaj emrin e rruges","Mesazh nga Autori", JOptionPane.ERROR_MESSAGE);
            }
            else if(NRBiz.getText().equals("")){
                JOptionPane.showMessageDialog( this, "Plotso Rubriken per NRB Biznesit","Mesazh nga Autori", JOptionPane.ERROR_MESSAGE);
            }
            else if(NrTVSH.getText().equals("")){
                JOptionPane.showMessageDialog( this, "Plotso Rubriken per NrTVSH-s","Mesazh nga Autori", JOptionPane.ERROR_MESSAGE);
            }
            else if(NrFiskal.getText().equals("")){
                JOptionPane.showMessageDialog( this, "Plotso Rubriken per Nr Fiskal","Mesazh nga Autori", JOptionPane.ERROR_MESSAGE);
            }
            
            else if(vitiThemelimit.getText().equals("")){
                JOptionPane.showMessageDialog( this, "Shkruaj Vitin e themelimit","Mesazh nga Autori", JOptionPane.ERROR_MESSAGE);
            }
            else{
                pst.setString(1,emri.getText());
                pst.setString(2,adresa1.getText());
                pst.setString(3,adresa2.getText());
                pst.setString(4,adresa3.getText());
                pst.setString(5,rruga.getText());
                pst.setString(6,NRBiz.getText());
                pst.setString(7,NrTVSH.getText());
                pst.setString(8,NrFiskal.getText());
                pst.setString(9,vitiThemelimit.getText());
                pst.executeUpdate();
                DefaultTableModel model = (DefaultTableModel)tbl1.getModel();
                model.setRowCount(0);
                trego_TeDhenatEKompanis();
                JOptionPane.showMessageDialog(null,"Te dhenat u rexhistruan ne databaze");
                ruaje.setEnabled(false);
                pastro();
            }              
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null,e);
        }
    }//GEN-LAST:event_ruajeActionPerformed

    private void tbl1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl1MouseClicked
       int i = tbl1.getSelectedRow();
       TableModel model = tbl1.getModel();
       id.setText(model.getValueAt(i,0).toString());
       emri.setText(model.getValueAt(i,1).toString());
       adresa1.setText(model.getValueAt(i,2).toString());
       adresa2.setText(model.getValueAt(i,3).toString());
       adresa3.setText(model.getValueAt(i,4).toString());
       rruga.setText(model.getValueAt(i,5).toString());
       NrTVSH.setText(model.getValueAt(i,6).toString());
       NrFiskal.setText(model.getValueAt(i,7).toString());
       NRBiz.setText(model.getValueAt(i,8).toString());
       vitiThemelimit.setText(model.getValueAt(i,9).toString());
       
    }//GEN-LAST:event_tbl1MouseClicked

    private void NRBizKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_NRBizKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_NRBizKeyPressed

    private void NRBizKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_NRBizKeyTyped
      char c = evt.getKeyChar();
      if (!(Character.isDigit(c)|| (c== KeyEvent.VK_BACK_SPACE)||(c==KeyEvent.VK_DELETE))){
          getToolkit().beep();
          evt.consume();
          }
    }//GEN-LAST:event_NRBizKeyTyped

    private void vitiThemelimitKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_vitiThemelimitKeyTyped
      char c = evt.getKeyChar();
      if (!(Character.isDigit(c)|| (c== KeyEvent.VK_BACK_SPACE)||(c==KeyEvent.VK_DELETE))){
          getToolkit().beep();
          evt.consume();
          }
    }//GEN-LAST:event_vitiThemelimitKeyTyped

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        pastro();
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
         try{
            int row = tbl1.getSelectedRow();
            if(tbl1.getSelectedRow() == -1){
                    JOptionPane.showMessageDialog(this ,"Ju nuk mund te Editoni asnje shfrytezues nese nuk e selektoni!","Mesazh nga Autori", JOptionPane.ERROR_MESSAGE);             
            }
            else{
            Connection con = LidhjaDB.ConnectDB();
            String IdESelektuar = (tbl1.getModel().getValueAt(row, 0).toString());
            String query =" UPDATE tbl_Kompania SET emri=?,shteti=?,qyteti=?,zipcode=?,rruga=?,nrBiznesit=?, nrTvsh=? ,nrFiskal=?, vitiThemelimit=? where kompaniaID="+IdESelektuar;
            PreparedStatement pst = con.prepareStatement(query);  
            if(emri.getText().equals("")){
               JOptionPane.showMessageDialog( this, "Shkruaj Emrin e kompanis","Mesazh nga Autori", JOptionPane.ERROR_MESSAGE);
            }
            else if(adresa1.getText().equals("")){
             JOptionPane.showMessageDialog( this, "Shkruaj Emrin e shtetit","Mesazh nga Autori", JOptionPane.ERROR_MESSAGE);
            }
            else if(adresa2.getText().equals("")){
             JOptionPane.showMessageDialog( this, "Shkruaj emrin e qytetit","Mesazh nga Autori", JOptionPane.ERROR_MESSAGE);
            }
            else if(adresa3.getText().equals("")){
             JOptionPane.showMessageDialog( this, "Shkruaj emrin e zipcode","Mesazh nga Autori", JOptionPane.ERROR_MESSAGE);
            }
            else if(rruga.getText().equals("")){
             JOptionPane.showMessageDialog( this, "Shkruaj emrin e rruges","Mesazh nga Autori", JOptionPane.ERROR_MESSAGE);
            }
            else if(NRBiz.getText().equals("")){
                JOptionPane.showMessageDialog( this, "Plotso Rubriken per NRB Biznesit","Mesazh nga Autori", JOptionPane.ERROR_MESSAGE);
            }
            else if(NrTVSH.getText().equals("")){
                JOptionPane.showMessageDialog( this, "Plotso Rubriken per NrTVSH-s","Mesazh nga Autori", JOptionPane.ERROR_MESSAGE);
            }
            else if(NrFiskal.getText().equals("")){
                JOptionPane.showMessageDialog( this, "Plotso Rubriken per Nr Fiskal","Mesazh nga Autori", JOptionPane.ERROR_MESSAGE);
            }
           
            else if(vitiThemelimit.getText().equals("")){
                JOptionPane.showMessageDialog( this, "Shkruaj Vitin e themelimit","Mesazh nga Autori", JOptionPane.ERROR_MESSAGE);
            }
            else{
                pst.setString(1,emri.getText());
                pst.setString(2,adresa1.getText());
                pst.setString(3,adresa2.getText());
                pst.setString(4,adresa3.getText());
                pst.setString(5,rruga.getText());
                pst.setString(6,NRBiz.getText());
                pst.setString(7,NrTVSH.getText());
                pst.setString(8,NrFiskal.getText());
                pst.setString(9,vitiThemelimit.getText());
                pst.executeUpdate();
                DefaultTableModel model = (DefaultTableModel)tbl1.getModel();
                model.setRowCount(0);
                trego_TeDhenatEKompanis();
                JOptionPane.showMessageDialog(null,"Te dhenat për kompanin u edituan me sukses");
                pastro();
                }
            }
         } catch (HeadlessException | SQLException e) {
          JOptionPane.showMessageDialog(null,e); 
        }    
    }//GEN-LAST:event_jButton2ActionPerformed

    private void adresa1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_adresa1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_adresa1ActionPerformed

    private void rrugaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rrugaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rrugaActionPerformed

    private void emailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_emailActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_emailActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
         try{
             
             Connection con = LidhjaDB.ConnectDB();	
             String query ="insert into Kontaktet_Kompania(telefoni,email,kompaniaID)values(?,?,?)";
             PreparedStatement pst = con.prepareStatement(query);
            
            if(id.getText().equals("")){
               JOptionPane.showMessageDialog( this, "Selektoni kompanin tek tabela","Mesazh nga Autori", JOptionPane.ERROR_MESSAGE);
            }
            else if(nrTelefonit.getText().equals("")){
             JOptionPane.showMessageDialog( this, "Shkruaje numrin e telefonit","Mesazh nga Autori", JOptionPane.ERROR_MESSAGE);
            }
            else if(email.getText().equals("")){
             JOptionPane.showMessageDialog( this, "Shkruaj emailin","Mesazh nga Autori", JOptionPane.ERROR_MESSAGE);
            }
            else if(!email.getText().contains("@")){
                             JOptionPane.showMessageDialog( this, "Një email duhet te permbaj @!","Mesazh nga Autori", JOptionPane.ERROR_MESSAGE);
            }
            else{
                pst.setString(1,nrTelefonit.getText());
                pst.setString(2,email.getText());
                pst.setString(3,id.getText());
               
                pst.executeUpdate();
                DefaultTableModel model = (DefaultTableModel)tbl2.getModel();
                model.setRowCount(0);
                trego_kontaktetEKompanis();
                JOptionPane.showMessageDialog(null,"Kontaktu u regjistrua");
                pastro();
            }              
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null,e);
        }            
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
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
                    String query = "DELETE FROM Kontaktet_Kompania where kontaktiID="+IdESelektuar;
                    PreparedStatement pst = con.prepareStatement(query);
                    pst.executeUpdate();
                    DefaultTableModel model = (DefaultTableModel)tbl2.getModel();
                    model.setRowCount(0);
                    trego_kontaktetEKompanis();
                    JOptionPane.showMessageDialog(this ,"E dhena u fshi me sukses","Informim", JOptionPane.INFORMATION_MESSAGE);
                    pastro(); 
                    }
                }            
            } catch (HeadlessException | SQLException e) {
          JOptionPane.showMessageDialog(null,e); 
        }
    }//GEN-LAST:event_jButton5ActionPerformed

    private void idKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_idKeyTyped
          char c = evt.getKeyChar();
      if (!(Character.isDigit(c)|| (c== KeyEvent.VK_BACK_SPACE)||(c==KeyEvent.VK_DELETE))){
          getToolkit().beep();
          evt.consume();
          }
    }//GEN-LAST:event_idKeyTyped

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
            java.util.logging.Logger.getLogger(TeDhenatEKompanis.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TeDhenatEKompanis.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TeDhenatEKompanis.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TeDhenatEKompanis.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new TeDhenatEKompanis().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField NRBiz;
    private javax.swing.JTextField NrFiskal;
    private javax.swing.JTextField NrTVSH;
    private javax.swing.JTextField adresa1;
    private javax.swing.JTextField adresa2;
    private javax.swing.JTextField adresa3;
    private javax.swing.JTextField email;
    private javax.swing.JTextField emri;
    private javax.swing.JTextField id;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
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
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextField nrTelefonit;
    private javax.swing.JTextField rruga;
    private javax.swing.JButton ruaje;
    private javax.swing.JTable tbl1;
    private javax.swing.JTable tbl2;
    private javax.swing.JTextField vitiThemelimit;
    // End of variables declaration//GEN-END:variables
}
