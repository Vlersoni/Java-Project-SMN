/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;
/*      
KA PUN ENDE!
*/
import LidhjaMeDatabaz.LidhjaDB;
import Modelet_Tabelat.Adresat;
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
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

/**
 *
 * @author Vhaliti
 */
public class RexhistrimiNdertesave extends javax.swing.JFrame {

    /**
     * Creates new form RexhistrimiNdertesave
     */
    public RexhistrimiNdertesave() {
        initComponents();
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        trego_Ndertesat();
        ikonaProgramit();
 
             
    }
    private void ikonaProgramit() {
       setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("icon48.png")));
    }
    //Variabla 
    String PosedimiParkingut="";
    String PosedimiAshencorit="";
    String PosedimiParkingut2="";
    String PosedimiAshencorit2="";

    int numroClick=0;
    int count=0;
    
    public void pastro()
    {
        txt_emriN.setText("");
        txt_nrK.setText("");
        adresaID.setText("");
        txt_vitiN.setText("");
        filter.setText("");
        filterRruga.setText("");
        buttonGroup1.clearSelection();
        buttonGroup2.clearSelection();
    }
    
   
    
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
    public final void trego_Ndertesat(){
        ArrayList<Ndertesa> list = NdertesatList();
        DefaultTableModel model = (DefaultTableModel)tbl2.getModel();
        Object[] row = new Object[9];
        for(int i =0;i<list.size();i++){
            row[0]=list.get(i).getID();
            row[1]=list.get(i).getEmri();
            row[2]=list.get(i).getNrKateve();
            row[3]=list.get(i).getParkingu();
            row[4]= list.get(i).getAshencori();
            row[5]= list.get(i).getVitiNdertimit();
            row[6]= list.get(i).getShteti();
            row[7]=list.get(i).getQyteti();
            row[8]=list.get(i).getRruga();
            model.addRow(row);
        }
         String countIKonvertuar = Integer.toString(list.size());
            mesazhZ.setText(countIKonvertuar);
    }
    
    
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
                numroClick=0;
            }
         }catch (HeadlessException | SQLException e) {
          JOptionPane.showMessageDialog(null,e); 
        }
         return teDhenatList;
    }
 
 
    public final void trego_adresat(String a, String b){
        ArrayList<Adresat> list = adresatList(a,b);
        DefaultTableModel model = (DefaultTableModel)tbl1.getModel();
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
    
    
//    -------------------------------------------------------------------
  
    
    
    
   
   
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txt_nrK = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        rbPO = new javax.swing.JRadioButton();
        rbJO = new javax.swing.JRadioButton();
        rbPO1 = new javax.swing.JRadioButton();
        rbJO1 = new javax.swing.JRadioButton();
        txt_vitiN = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txt_emriN = new javax.swing.JTextField();
        adresaID = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbl1 = new javax.swing.JTable();
        jLabel7 = new javax.swing.JLabel();
        filter = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        filterRruga = new javax.swing.JTextField();
        jButton7 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        sh = new javax.swing.JButton();
        ra = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        jButton4 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        mesazhZ = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbl2 = new javax.swing.JTable();

        setTitle("Regjistrimi Ndertesave");

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Plotso te dhenat"));

        jLabel1.setText("Emri Nderteses");

        txt_nrK.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_nrKKeyTyped(evt);
            }
        });

        jLabel2.setText("ID e selektuar e Adresa");

        buttonGroup1.add(rbPO);
        rbPO.setText("PO");

        buttonGroup1.add(rbJO);
        rbJO.setText("JO");

        buttonGroup2.add(rbPO1);
        rbPO1.setText("PO");

        buttonGroup2.add(rbJO1);
        rbJO1.setText("JO");

        txt_vitiN.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_vitiNKeyTyped(evt);
            }
        });

        jLabel3.setText("Nr Kateve");

        jLabel4.setText("Posedimi Ashencorit");

        jLabel5.setText("Posedimi Parkingut");

        jLabel6.setText("Viti Ndertimit");

        adresaID.setEnabled(false);

        jPanel3.setBackground(new java.awt.Color(204, 204, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        tbl1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Shteti", "Qyteti", "ZipCode", "Rruga", "NrRruges"
            }
        ));
        tbl1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl1MouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tbl1);

        jLabel7.setText("Kerko sipas qytetit");

        filter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                filterActionPerformed(evt);
            }
        });

        jLabel8.setText("Kerko sipas Rruges");

        jButton7.setText("Search");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        jButton8.setText("Pastro tabelen");
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });

        sh.setText("ALL");
        sh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                shActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(filter, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(filterRruga, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(sh)
                        .addGap(4, 4, 4)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(filter, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8)
                    .addComponent(filterRruga, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton7)
                    .addComponent(jButton8)
                    .addComponent(sh))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 26, Short.MAX_VALUE)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel4))
                        .addGap(25, 25, 25)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(rbPO)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(rbJO))
                            .addComponent(txt_emriN, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_nrK, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(rbPO1)
                                        .addGap(48, 48, 48)
                                        .addComponent(rbJO1))
                                    .addComponent(txt_vitiN)))
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(adresaID, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(ra)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txt_emriN, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(rbPO1)
                    .addComponent(rbJO1))
                .addGap(4, 4, 4)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txt_nrK, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6)
                    .addComponent(txt_vitiN, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(rbPO)
                    .addComponent(rbJO))
                .addGap(13, 13, 13)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(adresaID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ra, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jButton4.setText("Ruaj");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton1.setText("Permirso");
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

        jButton3.setText("Dalje");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton5.setText("Anulo");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jButton6.setText("Shto Adres");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel9.setText("Deri më tani keni");

        jLabel10.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel10.setText("regjistruar :");

        mesazhZ.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        mesazhZ.setForeground(new java.awt.Color(255, 102, 102));
        mesazhZ.setText("jLabel11");

        jLabel12.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel12.setText("Ndertesa");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(mesazhZ, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jButton6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jButton5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jButton4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(jLabel10))
                        .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(mesazhZ, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel12)
                .addContainerGap())
        );

        tbl2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Ndertesa", "Nr Kateve", "Parkingu", "Ashencori", "Viti Ndertimit", "Shteti", "Qyteti", "Rruga"
            }
        ));
        tbl2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl2MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbl2);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 836, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 282, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        hide();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
      try{
            Connection con = LidhjaDB.ConnectDB();
            String query = "Insert into tbl_Ndertesa(emri,nrKateve,parkingu,ashensori,vitiNdertimit,adresaID)values(?,?,?,?,?,?)"; // Esht dhe adresaID por niher jem ne fazen testuse
            PreparedStatement pst = con.prepareStatement(query);
            if(rbPO.isSelected()){
                PosedimiParkingut="Po";
            }
            else if(rbJO.isSelected()){
                PosedimiParkingut="Jo";
            }
             if(rbPO1.isSelected()){
                PosedimiAshencorit="Po";
            }
            else if(rbJO1.isSelected()){
                PosedimiAshencorit="Jo";
            }
            
            if(txt_emriN.getText().equals("")){
                JOptionPane.showMessageDialog( this, "Shkruaj Emrin e nderteses","Mesazh nga Autori", JOptionPane.ERROR_MESSAGE);
            }
            else if(txt_nrK.getText().equals("")){
                JOptionPane.showMessageDialog( this, "Shkruaj nr e Kateve e nderteses","Mesazh nga Autori", JOptionPane.ERROR_MESSAGE);
            }
            else if(PosedimiParkingut.equals("")){
                JOptionPane.showMessageDialog( this, "Perzgjidh Posedimin e parkingut per ndertes","Mesazh nga Autori", JOptionPane.ERROR_MESSAGE);
            }
            else if(PosedimiAshencorit.equals("")){
                JOptionPane.showMessageDialog( this, "Perzgjidh Posedimin e parkingut per ndertes","Mesazh nga Autori", JOptionPane.ERROR_MESSAGE);
            }
            
            else if(txt_vitiN.getText().equals("")){
                JOptionPane.showMessageDialog( this, "Shkruaj vitin e themelimit te nderteses","Mesazh nga Autori", JOptionPane.ERROR_MESSAGE);
            }  
            else if(adresaID.getText().equals("")){
                JOptionPane.showMessageDialog( this, "Pergjidh adresen se ku ndodhet ndertesa","Mesazh nga Autori", JOptionPane.ERROR_MESSAGE);
            }
            else{
                pst.setString(1,txt_emriN.getText());
                pst.setString(2,txt_nrK.getText());
                pst.setString(3,PosedimiParkingut);
                pst.setString(4,PosedimiAshencorit);
                pst.setString(5,txt_vitiN.getText());
                pst.setString(6,adresaID.getText());
            

                pst.executeUpdate();
                DefaultTableModel model = (DefaultTableModel)tbl2.getModel();
                model.setRowCount(0);
                trego_Ndertesat();
                JOptionPane.showMessageDialog(null,"Te dhenat u regjistruan ne databaze");
                pastro();                
            }    
        }
        catch(SQLException e){
            JOptionPane.showMessageDialog(null,e);
        }                                     
    }//GEN-LAST:event_jButton4ActionPerformed
    
    private void tbl1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl1MouseClicked
        int i = tbl1.getSelectedRow();
        TableModel model = tbl1.getModel();
        adresaID.setText(model.getValueAt(i,0).toString());
        ra.setText("Shteti:"+model.getValueAt(i,1).toString()+", Qyteti:"+model.getValueAt(i,2).toString()+", Rruga:"+model.getValueAt(i,4).toString());
    }//GEN-LAST:event_tbl1MouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        try{
            int row = tbl2.getSelectedRow();
            if(tbl2.getSelectedRow() == -1){
                    JOptionPane.showMessageDialog(this ,"Ju nuk mund te Editoni asnje shfrytezues nese nuk e selektoni!","Mesazh nga Autori", JOptionPane.ERROR_MESSAGE);             
            }
            else{
                Connection con = LidhjaDB.ConnectDB();
                String IdESelektuar1 = (tbl2.getModel().getValueAt(row, 0).toString());
                String query =" UPDATE tbl_Ndertesa SET emri=?, nrKateve=?, parkingu=?, ashensori=?, vitiNdertimit=?, adresaID=? where ndertesaID="+IdESelektuar1;
                PreparedStatement pst = con.prepareStatement(query);
            
            if(rbPO.isSelected()){
                PosedimiParkingut="Po";
            }
            else if(rbJO.isSelected()){
                PosedimiParkingut="Jo";
            }
             if(rbPO1.isSelected()){
                PosedimiAshencorit="Po";
            }
            else if(rbJO1.isSelected()){
                PosedimiAshencorit="Jo";
            }
            
            if(txt_emriN.getText().equals("")){
                JOptionPane.showMessageDialog( this, "Shkruaj Emrin e nderteses","Mesazh nga Autori", JOptionPane.ERROR_MESSAGE);
            }
            if(txt_nrK.getText().equals("")){
                JOptionPane.showMessageDialog( this, "Shkruaj nr e Kateve e nderteses","Mesazh nga Autori", JOptionPane.ERROR_MESSAGE);
            }
            else if(PosedimiParkingut.equals("")){
                JOptionPane.showMessageDialog( this, "Perzgjidh Posedimin e parkingut per ndertes","Mesazh nga Autori", JOptionPane.ERROR_MESSAGE);
            }
            else if(PosedimiAshencorit.equals("")){
                JOptionPane.showMessageDialog( this, "Perzgjidh Posedimin e ashencorit per ndertes","Mesazh nga Autori", JOptionPane.ERROR_MESSAGE);
            }
            
            else if(txt_vitiN.getText().equals("")){
                JOptionPane.showMessageDialog( this, "Shkruaj vitin e themelimit te nderteses","Mesazh nga Autori", JOptionPane.ERROR_MESSAGE);
            }     
            else if(adresaID.getText().equals("")){
                JOptionPane.showMessageDialog( this, "Pergjidh adresen se ku ndodhet ndertesa","Mesazh nga Autori", JOptionPane.ERROR_MESSAGE);
            }
            else{
                pst.setString(1,txt_emriN.getText());
                pst.setString(2,txt_nrK.getText());
                pst.setString(3,PosedimiParkingut);
                pst.setString(4,PosedimiAshencorit);
                pst.setString(5,txt_vitiN.getText());
                pst.setString(6,adresaID.getText());
              
                pst.executeUpdate();
                DefaultTableModel model = (DefaultTableModel)tbl2.getModel();
                model.setRowCount(0);
                trego_Ndertesat();
                JOptionPane.showMessageDialog(null,"Te dhenat mbi ndertesen u edituan me sukses");
                pastro();
                  
                }
            }
        } catch (HeadlessException | SQLException e) {
          JOptionPane.showMessageDialog(null,e); 
        }   
    }//GEN-LAST:event_jButton1ActionPerformed

    private void tbl2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl2MouseClicked
       int i = tbl2.getSelectedRow();
       TableModel model = tbl2.getModel();
       txt_emriN.setText(model.getValueAt(i,1).toString());
       txt_nrK.setText(model.getValueAt(i,2).toString());
       String a1 = model.getValueAt(i,3).toString();
       if(a1.equals("Po")){
           rbPO.setSelected(true);
       }
       else{
           rbJO.setSelected(true);
       }
       String a2 = model.getValueAt(i,4).toString();
       if(a2.equals("Po")){
           rbPO1.setSelected(true);
       }
       else{
           rbJO1.setSelected(true);
       }
       txt_vitiN.setText(model.getValueAt(i,5).toString());
       filter.setText(model.getValueAt(i,7).toString());
       filterRruga.setText(model.getValueAt(i,8).toString());

                  
    }//GEN-LAST:event_tbl2MouseClicked

    private void filterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_filterActionPerformed
            
    }//GEN-LAST:event_filterActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        if(filter.getText().equals("") && filterRruga.getText().equals("")){
            JOptionPane.showMessageDialog( this, "Nuk mund të bëni search nese nuk shkruani se qfar po kërkoni","Mesazh nga Autori", JOptionPane.ERROR_MESSAGE);
        }
        else{
            DefaultTableModel model = (DefaultTableModel) tbl1.getModel();
            model.setRowCount(0);
            trego_adresat(filter.getText(),filterRruga.getText());
            filter.setText("");
            filterRruga.setText("");
            sh.setEnabled(true);
        }
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
       pastro();
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
     AdresaRexhister ar = new AdresaRexhister();
     ar.setVisible(true);
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
         DefaultTableModel model = (DefaultTableModel) tbl1.getModel();
        model.setRowCount(0);
        sh.setEnabled(true);
    }//GEN-LAST:event_jButton8ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
       try{
            int row = tbl2.getSelectedRow();
            
            if(tbl2.getSelectedRow() == -1){
                    JOptionPane.showMessageDialog(this ,"Ju nuk mund te fshini asnje shfrytezues nese nuk e selektoni!","Mesazh nga Autori", JOptionPane.ERROR_MESSAGE);             
            }
            else{
                Object [] ob = {"Po","Jo"};
                int p = javax.swing.JOptionPane.showOptionDialog(this, "A jeni i sigurt se deshironi ta fshini pas fshirjes nuk ka me kthim te shenimeve ?", "Konfirmim", JOptionPane.OK_OPTION,JOptionPane.QUESTION_MESSAGE, null, ob, ob[1]);
            
                    if (p==0){
                    Connection con = LidhjaDB.ConnectDB();
                    String IdESelektuar = (tbl2.getModel().getValueAt(row, 0).toString());
                    String query = "DELETE FROM tbl_Ndertesa where ndertesaID="+IdESelektuar;
                    PreparedStatement pst = con.prepareStatement(query);
                    pst.executeUpdate();
                    DefaultTableModel model = (DefaultTableModel)tbl2.getModel();
                    model.setRowCount(0);
                    trego_Ndertesat();
                    JOptionPane.showMessageDialog(this ,"E dhena u fshi me sukses","Informim", JOptionPane.INFORMATION_MESSAGE);
                    pastro(); 
                    }
                }            
            } catch (HeadlessException | SQLException e) {
          JOptionPane.showMessageDialog(null,"Kjo ndertes nuk mund te fshihet per arsyeje se ka banesa te rexhistruara ne te!"); 
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void txt_nrKKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_nrKKeyTyped
         char c = evt.getKeyChar();
      if (!(Character.isDigit(c)|| (c== KeyEvent.VK_BACK_SPACE)||(c==KeyEvent.VK_DELETE))){
          getToolkit().beep();
          evt.consume();
          }
    }//GEN-LAST:event_txt_nrKKeyTyped

    private void txt_vitiNKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_vitiNKeyTyped
        char c = evt.getKeyChar();
      if (!(Character.isDigit(c)|| (c== KeyEvent.VK_BACK_SPACE)||(c==KeyEvent.VK_DELETE))){
          getToolkit().beep();
          evt.consume();
          }
    }//GEN-LAST:event_txt_vitiNKeyTyped

    private void shActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_shActionPerformed
        DefaultTableModel model = (DefaultTableModel) tbl1.getModel();
        model.setRowCount(0);
        trego_adresat("!@#","#@!");
        sh.setEnabled(false);
    }//GEN-LAST:event_shActionPerformed

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
            java.util.logging.Logger.getLogger(RexhistrimiNdertesave.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(RexhistrimiNdertesave.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(RexhistrimiNdertesave.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(RexhistrimiNdertesave.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new RexhistrimiNdertesave().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField adresaID;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.JTextField filter;
    private javax.swing.JTextField filterRruga;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel12;
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
    private javax.swing.JLabel mesazhZ;
    private javax.swing.JTextField ra;
    private javax.swing.JRadioButton rbJO;
    private javax.swing.JRadioButton rbJO1;
    private javax.swing.JRadioButton rbPO;
    private javax.swing.JRadioButton rbPO1;
    private javax.swing.JButton sh;
    private javax.swing.JTable tbl1;
    private javax.swing.JTable tbl2;
    private javax.swing.JTextField txt_emriN;
    private javax.swing.JTextField txt_nrK;
    private javax.swing.JTextField txt_vitiN;
    // End of variables declaration//GEN-END:variables
}
