/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import LidhjaMeDatabaz.LidhjaDB;
import Modelet_Tabelat.BanesatQera1;
import java.awt.BorderLayout;
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
import java.time.Clock;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Vhaliti
 */
public class Kryefaqja extends javax.swing.JFrame {

    /**
     * Creates new form Kryefaqja
     */
    private Login [] login;
 
    public Kryefaqja() throws ParseException{
       
        initComponents();
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
//        setExtendedState(java.awt.Frame.MAXIMIZED_BOTH); 
       
        kompania();
        sekori();
        OraDheData();
        privilegji();
        sekori1();
        ikonaProgramit();
        Banesateskaduarasod();
    }
    private void ikonaProgramit() {
       setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("icon48.png")));
    }
     
    public void privilegji(){
         try{
	 Connection con = LidhjaDB.ConnectDB();	
												
            String sql= "Select privilegji from tbl_Sektori";
            PreparedStatement pst = con.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            
            while(rs.next()){
		String emri =rs.getString("privilegji");
                
                 if(emri.equals("Administrator")){
                    menu1.setEnabled(true);
                    jMenu5.setEnabled(true);
                    jMenuItem13.setEnabled(true);
                    jMenuItem12.setEnabled(true);
                    jMenuItem10.setEnabled(true);
                    jMenu9.setEnabled(true);
                    jMenuItem7.setEnabled(true);
                    jMenu4.setEnabled(true);
                }
                else if(emri.equals("Shites")){
                     menu1.setEnabled(true);
                    jMenu5.setEnabled(true);
                    jMenuItem13.setEnabled(true);
                    jMenuItem12.setEnabled(true);
                    jMenuItem10.setEnabled(true);
                }
                else{
                  jMenu9.setEnabled(true);
                  jMenuItem12.setEnabled(true);
                  jMenuItem10.setEnabled(true);
                }
           
            }
         }catch(SQLException e){
		JOptionPane.showMessageDialog(null,e);
        }
    }
      public void sekori(){
        try{
	 Connection con = LidhjaDB.ConnectDB();	
												
            String sql= "Select Shfrytzuesi from tbl_Sektori";
            PreparedStatement pst = con.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            while(rs.next()){
		String emri =rs.getString("Shfrytzuesi");
                emriSh.setText(emri);
            }	
	}
	catch(SQLException e){
		JOptionPane.showMessageDialog(null,e);
        }  
    }   
        public void sekori1(){
        try{
	 Connection con = LidhjaDB.ConnectDB();	
												
            String sql= "Select privilegji from tbl_Sektori";
            PreparedStatement pst = con.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            while(rs.next()){
		String emri =rs.getString("privilegji");
                emriSh1.setText(emri);
            }	
	}
	catch(SQLException e){
		JOptionPane.showMessageDialog(null,e);
        }
        
    } 
    //Metoda qe lexon emrin e kompanis
    public void kompania(){
        try{
	 Connection con = LidhjaDB.ConnectDB();	
												
            String sql= "Select emri,shteti,qyteti,zipcode,rruga,nrBiznesit,nrTvsh,nrFiskal,vitiThemelimit from tbl_Kompania";
            PreparedStatement pst = con.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            while(rs.next()){
		String emri =rs.getString("emri");
                String Shteti1 = rs.getString("shteti");
                String qyteti1 = rs.getString("qyteti");
                String zipcode = rs.getString("zipcode");
                String nrB= rs.getString("NRBiznesit");
                String nrT= rs.getString("nrTvsh"); 
                String nrF= rs.getString("nrFiskal"); 
                String vitiTh = rs.getString("vitiThemelimit");
  
		Emrikompanis.setText("\""+emri+"\"");
                Nrbiz.setText("Numri Biznesit:"+nrB);
		nrTvsh.setText("Numri TVSH:"+nrT);
		nrFiskal.setText("Numri Fiskal:"+nrF);
		adresaKompanis.setText("Adresa:"+ Shteti1 +"/"+qyteti1+"/"+zipcode);
                vt.setText("E themeluar ne vitin:"+vitiTh);
               
            }	
	}
	catch(SQLException e){
		JOptionPane.showMessageDialog(null,e);
        }				 
    }
   
    //Metoada per or dhe dat
    public void OraDheData(){
        
        Thread clock=new Thread(){
             public  void run(){
                 try {
                     while(true){
                    Calendar cal=new GregorianCalendar();
                    int day=cal.get(Calendar.DAY_OF_MONTH);
                    int month=cal.get(Calendar.MONTH);
                    int year=cal.get(Calendar.YEAR);
        
                    int second=cal.get(Calendar.SECOND);
                    int minute=cal.get(Calendar.MINUTE);
                    int hour=cal.get(Calendar.HOUR);
                    DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
                    Date date = new Date();
                    
                    jLabel1Clock.setText( "  Data:  "+dateFormat.format(date));
                    jLabel1Time.setText("  Ora:  "+hour+":"+minute+":"+second);
        
                     sleep(1000);
                     }
            
                 } catch (InterruptedException ex) {
                     Logger.getLogger(Clock.class.getName()).log(Level.SEVERE, null, ex);
                 }  
            }
        };
        clock.start();
    } 
//    --------------------------------------------------------------------------
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
                        QerajaSkaduar qs = new QerajaSkaduar();
                        qs.setVisible(true);
                    }
               }
           }
        }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jTextField1 = new javax.swing.JTextField();
        jMenuBar2 = new javax.swing.JMenuBar();
        jMenu6 = new javax.swing.JMenu();
        jMenu7 = new javax.swing.JMenu();
        jPanel3 = new javax.swing.JPanel();
        Emrikompanis = new javax.swing.JLabel();
        Nrbiz = new javax.swing.JLabel();
        nrTvsh = new javax.swing.JLabel();
        nrFiskal = new javax.swing.JLabel();
        vt = new javax.swing.JLabel();
        adresaKompanis = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel1Clock = new javax.swing.JLabel();
        jLabel1Time = new javax.swing.JLabel();
        jButton7 = new javax.swing.JButton();
        emriSh = new javax.swing.JLabel();
        MainPanel = new javax.swing.JPanel();
        emriSh1 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        menu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenu5 = new javax.swing.JMenu();
        jMenuItem11 = new javax.swing.JMenuItem();
        jMenuItem5 = new javax.swing.JMenuItem();
        jMenuItem6 = new javax.swing.JMenuItem();
        jMenuItem14 = new javax.swing.JMenuItem();
        jMenuItem16 = new javax.swing.JMenuItem();
        jMenu4 = new javax.swing.JMenu();
        jMenuItem8 = new javax.swing.JMenuItem();
        jMenuItem9 = new javax.swing.JMenuItem();
        jMenu9 = new javax.swing.JMenu();
        jMenuItem15 = new javax.swing.JMenuItem();
        jMenuItem7 = new javax.swing.JMenuItem();
        jMenuItem13 = new javax.swing.JMenuItem();
        jMenuItem10 = new javax.swing.JMenuItem();
        jMenuItem12 = new javax.swing.JMenuItem();
        jMenu3 = new javax.swing.JMenu();
        jMenuItem4 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        jTextField1.setText("jTextField1");

        jMenu6.setText("File");
        jMenuBar2.add(jMenu6);

        jMenu7.setText("Edit");
        jMenuBar2.add(jMenu7);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Kryefaqja");
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setMaximizedBounds(new java.awt.Rectangle(0, 0, 0, 0));
        setMaximumSize(new java.awt.Dimension(2147483647, 214748364));

        jPanel3.setBackground(new java.awt.Color(204, 204, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        Emrikompanis.setFont(new java.awt.Font("Cambria", 1, 36)); // NOI18N
        Emrikompanis.setText("Emri Kompanis");

        Nrbiz.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        Nrbiz.setText("NRBiz");

        nrTvsh.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        nrTvsh.setText("NRTVSH");

        nrFiskal.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        nrFiskal.setText("NrFiskal");

        vt.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        vt.setText("VitiThemelimit");

        adresaKompanis.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        adresaKompanis.setText("adresa kompanis");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(adresaKompanis, javax.swing.GroupLayout.PREFERRED_SIZE, 307, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Emrikompanis, javax.swing.GroupLayout.PREFERRED_SIZE, 291, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 182, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(vt, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(Nrbiz, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(nrTvsh, javax.swing.GroupLayout.DEFAULT_SIZE, 151, Short.MAX_VALUE)
                        .addComponent(nrFiskal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(Nrbiz)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(nrTvsh, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(nrFiskal)
                        .addGap(14, 14, 14))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addComponent(Emrikompanis, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)))
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(vt, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(adresaKompanis)))
        );

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Koha"));

        jLabel1Clock.setFont(new java.awt.Font("Cambria Math", 1, 14)); // NOI18N
        jLabel1Clock.setText("DATA");

        jLabel1Time.setFont(new java.awt.Font("Cambria", 1, 18)); // NOI18N
        jLabel1Time.setText("ORa");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1Time, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel1Clock, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 194, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel1Time, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel1Clock, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jButton7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/GUI/exit.png"))); // NOI18N
        jButton7.setText("Dalje");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        emriSh.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        emriSh.setText("Shfrytzusi");

        MainPanel.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

        javax.swing.GroupLayout MainPanelLayout = new javax.swing.GroupLayout(MainPanel);
        MainPanel.setLayout(MainPanelLayout);
        MainPanelLayout.setHorizontalGroup(
            MainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 537, Short.MAX_VALUE)
        );
        MainPanelLayout.setVerticalGroup(
            MainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 297, Short.MAX_VALUE)
        );

        emriSh1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        emriSh1.setText("jLabel1");

        jLabel11.setText("Shfrytzusi Aktiv:");

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel1.setText("Pozita:");

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder("Puna me Tastier"));

        jTextArea1.setColumns(20);
        jTextArea1.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jTextArea1.setForeground(new java.awt.Color(255, 0, 0));
        jTextArea1.setRows(5);
        jTextArea1.setText("F1 - Regjistrimi i ndertesave\nF2 - Regjistrimi i banesave\nF3 - Regjistrimi i klienteve\nF12 - Shitja e banesave\nF11 - Jep me Qera\nF10 - Opcionet e punes\nF9 - Vështro banesat që lira\nF8 - Vështro banesat e shitura\nF5 - Veshtro Banesat e leshuara me Qera\nCtrl+1 - Të dhënat e kompanis\nCtrl+2 - Të dhënat e puntorëve\nCtrl+V - Realizimet\nCtrl+R - Shto Shfrytzues\nCtrl+A - Regjistro adresa\nCtrl+Tab - Autori\nShift+Esc - Dalje nga Programi");
        jTextArea1.setCaretColor(new java.awt.Color(255, 0, 0));
        jTextArea1.setDisabledTextColor(new java.awt.Color(255, 0, 0));
        jTextArea1.setEnabled(false);
        jScrollPane1.setViewportView(jTextArea1);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 312, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 344, Short.MAX_VALUE)
                .addContainerGap())
        );

        jMenu1.setText("Opsionet e punes");

        menu1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/GUI/building-icon.png"))); // NOI18N
        menu1.setText("Regjistrimet");
        menu1.setEnabled(false);

        jMenuItem1.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F1, 0));
        jMenuItem1.setText("Regjistro Ndertesa");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        menu1.add(jMenuItem1);

        jMenuItem2.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F2, 0));
        jMenuItem2.setText("Regjistro Banesa");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        menu1.add(jMenuItem2);

        jMenuItem3.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F3, 0));
        jMenuItem3.setText("Regjistro Klient");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        menu1.add(jMenuItem3);

        jMenu1.add(menu1);

        jMenu5.setIcon(new javax.swing.ImageIcon("D:\\Projekti Final\\SMN-Software\\src\\GUI\\logi2.png")); // NOI18N
        jMenu5.setText("Shitja");
        jMenu5.setEnabled(false);

        jMenuItem11.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F12, 0));
        jMenuItem11.setText("Shit Banesa");
        jMenuItem11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem11ActionPerformed(evt);
            }
        });
        jMenu5.add(jMenuItem11);

        jMenuItem5.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F11, 0));
        jMenuItem5.setText("Banesat me qera");
        jMenuItem5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem5ActionPerformed(evt);
            }
        });
        jMenu5.add(jMenuItem5);

        jMenuItem6.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F9, 0));
        jMenuItem6.setText("Banesat e Lira");
        jMenuItem6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem6ActionPerformed(evt);
            }
        });
        jMenu5.add(jMenuItem6);

        jMenuItem14.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F8, 0));
        jMenuItem14.setText("Banesa te shitura");
        jMenuItem14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem14ActionPerformed(evt);
            }
        });
        jMenu5.add(jMenuItem14);

        jMenuItem16.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F5, 0));
        jMenuItem16.setText("Banesat me qera");
        jMenuItem16.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem16ActionPerformed(evt);
            }
        });
        jMenu5.add(jMenuItem16);

        jMenu1.add(jMenu5);

        jMenu4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/GUI/IconKompania.png"))); // NOI18N
        jMenu4.setText("Kompania");
        jMenu4.setEnabled(false);

        jMenuItem8.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_1, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem8.setText("Te dhenat e Kompanis");
        jMenuItem8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem8ActionPerformed(evt);
            }
        });
        jMenu4.add(jMenuItem8);

        jMenuItem9.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_2, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem9.setText("Te dhenat e puntorve");
        jMenuItem9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem9ActionPerformed(evt);
            }
        });
        jMenu4.add(jMenuItem9);

        jMenu1.add(jMenu4);

        jMenu9.setIcon(new javax.swing.ImageIcon("D:\\Projekti Final\\SMN-Software\\src\\GUI\\logo4.png")); // NOI18N
        jMenu9.setText("Vështrimet");
        jMenu9.setEnabled(false);

        jMenuItem15.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_V, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem15.setText("Realizimet ");
        jMenuItem15.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem15ActionPerformed(evt);
            }
        });
        jMenu9.add(jMenuItem15);

        jMenu1.add(jMenu9);

        jMenuItem7.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_R, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem7.setIcon(new javax.swing.ImageIcon("D:\\Projekti Final\\SMN-Software\\src\\GUI\\logo1.png")); // NOI18N
        jMenuItem7.setText("Shto Shfrytzues");
        jMenuItem7.setEnabled(false);
        jMenuItem7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem7ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem7);

        jMenuItem13.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_A, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem13.setIcon(new javax.swing.ImageIcon("D:\\Projekti Final\\SMN-Software\\src\\GUI\\logo3.png")); // NOI18N
        jMenuItem13.setText("Regjistro Adresat");
        jMenuItem13.setEnabled(false);
        jMenuItem13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem13ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem13);

        jMenuItem10.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_TAB, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/GUI/authors-icon.png"))); // NOI18N
        jMenuItem10.setText("Autori");
        jMenuItem10.setEnabled(false);
        jMenuItem10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem10ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem10);

        jMenuItem12.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_ESCAPE, java.awt.event.InputEvent.SHIFT_MASK));
        jMenuItem12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/GUI/exit.png"))); // NOI18N
        jMenuItem12.setText("Dalje");
        jMenuItem12.setEnabled(false);
        jMenuItem12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem12ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem12);

        jMenuBar1.add(jMenu1);

        jMenu3.setText("Shfaq Butonat");

        jMenuItem4.setText("Butonat");
        jMenuItem4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem4ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem4);

        jMenuBar1.add(jMenu3);

        jMenu2.setText("Ndihma");
        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jLabel11)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(emriSh, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(24, 24, 24))
                            .addComponent(MainPanel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(emriSh1, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(63, 63, 63))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(94, 94, 94)
                        .addComponent(MainPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 48, Short.MAX_VALUE)
                .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(emriSh, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(emriSh1, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(8, 8, 8))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
//       System.exit(0);
        Login l = new Login();
        l.setVisible(true);
        hide();
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
        RegjistrimiKlienteve rK = null;
        try {
            rK = new RegjistrimiKlienteve();
        } catch (ParseException ex) {
            Logger.getLogger(Kryefaqja.class.getName()).log(Level.SEVERE, null, ex);
        }
        rK.setVisible(true);
       
    }//GEN-LAST:event_jMenuItem3ActionPerformed

    private void jMenuItem5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem5ActionPerformed
         ShloMeQera bmq = new ShloMeQera();
         bmq.setVisible(true);
    }//GEN-LAST:event_jMenuItem5ActionPerformed

    private void jMenuItem8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem8ActionPerformed
         TeDhenatEKompanis tdhk = new TeDhenatEKompanis();
         tdhk.setVisible(true);
         
    }//GEN-LAST:event_jMenuItem8ActionPerformed

    private void jMenuItem9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem9ActionPerformed
        TeDhenatEPuntorve tdhp = null;
        try {
            tdhp = new TeDhenatEPuntorve();
        } catch (ParseException ex) {
            Logger.getLogger(Kryefaqja.class.getName()).log(Level.SEVERE, null, ex);
        }
        tdhp.setVisible(true);
    }//GEN-LAST:event_jMenuItem9ActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
       RexhistrimiNdertesave rN = new RexhistrimiNdertesave();
       rN.setVisible(true);
       rN.show();
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        RexhistrimiBanesave b = new RexhistrimiBanesave();
        b.setVisible(true);
        b.show();
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void jMenuItem11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem11ActionPerformed
         ShitBanesa shb = new ShitBanesa();
         shb.setVisible(true);
    }//GEN-LAST:event_jMenuItem11ActionPerformed

    private void jMenuItem7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem7ActionPerformed
        ShtoShfrytezues shf = new ShtoShfrytezues();
        shf.setVisible(true);
    }//GEN-LAST:event_jMenuItem7ActionPerformed

    private void jMenuItem10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem10ActionPerformed
        Autori a= new Autori();
        a.setVisible(true);
    }//GEN-LAST:event_jMenuItem10ActionPerformed

    private void jMenuItem12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem12ActionPerformed
//        System.exit(0);
        Login l = new Login();
        l.setVisible(true);
        hide();
    }//GEN-LAST:event_jMenuItem12ActionPerformed

    private void jMenuItem13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem13ActionPerformed
        AdresaRexhister ar = new AdresaRexhister();
        ar.setVisible(true);
    }//GEN-LAST:event_jMenuItem13ActionPerformed

    private void jMenuItem6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem6ActionPerformed
        BanesatELira bel = new BanesatELira();
        bel.setVisible(true);
    }//GEN-LAST:event_jMenuItem6ActionPerformed

    private void jMenuItem15ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem15ActionPerformed
       try {
            Financat f = new Financat();
            f.setVisible(true);
        } catch (ParseException ex) {
            Logger.getLogger(Kryefaqja.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jMenuItem15ActionPerformed

    private void jMenuItem4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem4ActionPerformed
      Butonat re = new Butonat();
      re.setSize(480,210);
      re.setLocation(5,5);
      
      MainPanel.removeAll();
      MainPanel.add(re,BorderLayout.CENTER);
      MainPanel.revalidate();
      MainPanel.repaint();
    }//GEN-LAST:event_jMenuItem4ActionPerformed

    private void jMenuItem14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem14ActionPerformed
        BanesatEShitura bsh = null;
        bsh = new BanesatEShitura();
        bsh.setVisible(true);
    }//GEN-LAST:event_jMenuItem14ActionPerformed

    private void jMenuItem16ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem16ActionPerformed
        BanesaEShluaraMeQera bshq = null;
        try {
            bshq = new BanesaEShluaraMeQera();
        } catch (ParseException ex) {
            Logger.getLogger(Kryefaqja.class.getName()).log(Level.SEVERE, null, ex);
        }
        bshq.setVisible(true);
    }//GEN-LAST:event_jMenuItem16ActionPerformed

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
            java.util.logging.Logger.getLogger(Kryefaqja.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Kryefaqja.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Kryefaqja.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Kryefaqja.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new Kryefaqja().setVisible(true);
                } catch (ParseException ex) {
                    Logger.getLogger(Kryefaqja.class.getName()).log(Level.SEVERE, null, ex);
//                    Kryefaqja.setExtendedState(Kryefaqja.getExtendedState() | Kryefaqja.MAXIMIZED_BOTH);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Emrikompanis;
    private javax.swing.JPanel MainPanel;
    private javax.swing.JLabel Nrbiz;
    private javax.swing.JLabel adresaKompanis;
    private static javax.swing.JLabel emriSh;
    private javax.swing.JLabel emriSh1;
    private javax.swing.JButton jButton7;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel1Clock;
    private javax.swing.JLabel jLabel1Time;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenu jMenu5;
    private javax.swing.JMenu jMenu6;
    private javax.swing.JMenu jMenu7;
    private javax.swing.JMenu jMenu9;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuBar jMenuBar2;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem10;
    private javax.swing.JMenuItem jMenuItem11;
    private javax.swing.JMenuItem jMenuItem12;
    private javax.swing.JMenuItem jMenuItem13;
    private javax.swing.JMenuItem jMenuItem14;
    private javax.swing.JMenuItem jMenuItem15;
    private javax.swing.JMenuItem jMenuItem16;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JMenuItem jMenuItem6;
    private javax.swing.JMenuItem jMenuItem7;
    private javax.swing.JMenuItem jMenuItem8;
    private javax.swing.JMenuItem jMenuItem9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JMenu menu1;
    private javax.swing.JLabel nrFiskal;
    private javax.swing.JLabel nrTvsh;
    private javax.swing.JLabel vt;
    // End of variables declaration//GEN-END:variables
}
