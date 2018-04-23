/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import LidhjaMeDatabaz.LidhjaDB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author VHaliti
 */
public final class Butonat extends javax.swing.JPanel {

    /**
     * Creates new form Butonat
     */
    public Butonat() {
        initComponents();
        privilegji();
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
                    rAdresat.setEnabled(true);
                    regjistrimiNdertesave.setEnabled(true);
                    kompania.setEnabled(true);
                    rBanesa.setEnabled(true);
                    rKlientet.setEnabled(true);
                    puntori.setEnabled(true);
                    shitBanesa.setEnabled(true);
                    jepMeQera.setEnabled(true);
                    shtoShfrytzues.setEnabled(true);
                    banesaTeLira.setEnabled(true);
                    BanesatEShitura.setEnabled(true);
                    BanesatMeQera.setEnabled(true);
                    Autori.setEnabled(true);
                    Realizimi.setEnabled(true);
                    ndihma.setEnabled(true);
                }
                else if(emri.equals("Shites")){
                    rAdresat.setEnabled(true);
                    regjistrimiNdertesave.setEnabled(true);
                    rBanesa.setEnabled(true);
                    rKlientet.setEnabled(true);
                    shitBanesa.setEnabled(true);
                    jepMeQera.setEnabled(true);
                    banesaTeLira.setEnabled(true);
                    BanesatEShitura.setEnabled(true);
                    BanesatMeQera.setEnabled(true);
                    Autori.setEnabled(true);
                    ndihma.setEnabled(true);
                }
                else{
                  Realizimi.setEnabled(true);
                  Autori.setEnabled(true);
                }
           
            }
         }catch(SQLException e){
		JOptionPane.showMessageDialog(null,e);
        }
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        regjistrimiNdertesave = new javax.swing.JButton();
        rBanesa = new javax.swing.JButton();
        rKlientet = new javax.swing.JButton();
        kompania = new javax.swing.JButton();
        puntori = new javax.swing.JButton();
        shitBanesa = new javax.swing.JButton();
        jepMeQera = new javax.swing.JButton();
        shtoShfrytzues = new javax.swing.JButton();
        rAdresat = new javax.swing.JButton();
        BanesatEShitura = new javax.swing.JButton();
        banesaTeLira = new javax.swing.JButton();
        BanesatMeQera = new javax.swing.JButton();
        Autori = new javax.swing.JButton();
        Realizimi = new javax.swing.JButton();
        ndihma = new javax.swing.JButton();

        setBackground(new java.awt.Color(0, 255, 153));
        setBorder(javax.swing.BorderFactory.createEtchedBorder());
        setForeground(new java.awt.Color(51, 255, 0));

        regjistrimiNdertesave.setText("Regjistrimi Ndertesave");
        regjistrimiNdertesave.setEnabled(false);
        regjistrimiNdertesave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                regjistrimiNdertesaveActionPerformed(evt);
            }
        });

        rBanesa.setText("Regjistrimi Banesave");
        rBanesa.setEnabled(false);
        rBanesa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rBanesaActionPerformed(evt);
            }
        });

        rKlientet.setText("Regjitstrimi Klienteve");
        rKlientet.setEnabled(false);
        rKlientet.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rKlientetActionPerformed(evt);
            }
        });

        kompania.setText("Kompania");
        kompania.setEnabled(false);
        kompania.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                kompaniaActionPerformed(evt);
            }
        });

        puntori.setText("Puntoret");
        puntori.setEnabled(false);
        puntori.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                puntoriActionPerformed(evt);
            }
        });

        shitBanesa.setText("Shit Banesa");
        shitBanesa.setEnabled(false);
        shitBanesa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                shitBanesaActionPerformed(evt);
            }
        });

        jepMeQera.setText("Jep me Qera");
        jepMeQera.setEnabled(false);
        jepMeQera.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jepMeQeraActionPerformed(evt);
            }
        });

        shtoShfrytzues.setText("Shto Shfrytëzues");
        shtoShfrytzues.setEnabled(false);
        shtoShfrytzues.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                shtoShfrytzuesActionPerformed(evt);
            }
        });

        rAdresat.setText("Regjistrimi Adresave");
        rAdresat.setEnabled(false);
        rAdresat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rAdresatActionPerformed(evt);
            }
        });

        BanesatEShitura.setText("Banesat e shitura");
        BanesatEShitura.setEnabled(false);
        BanesatEShitura.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BanesatEShituraActionPerformed(evt);
            }
        });

        banesaTeLira.setText("Banesat e lira");
        banesaTeLira.setEnabled(false);
        banesaTeLira.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                banesaTeLiraActionPerformed(evt);
            }
        });

        BanesatMeQera.setText("Banesat me Qera");
        BanesatMeQera.setEnabled(false);
        BanesatMeQera.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BanesatMeQeraActionPerformed(evt);
            }
        });

        Autori.setText("Autori");
        Autori.setEnabled(false);
        Autori.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AutoriActionPerformed(evt);
            }
        });

        Realizimi.setText("Realizimet");
        Realizimi.setEnabled(false);
        Realizimi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RealizimiActionPerformed(evt);
            }
        });

        ndihma.setText("ndihma");
        ndihma.setEnabled(false);
        ndihma.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ndihmaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(rAdresat, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(rBanesa, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(shitBanesa, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(banesaTeLira, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(Autori, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(BanesatEShitura, javax.swing.GroupLayout.DEFAULT_SIZE, 141, Short.MAX_VALUE)
                                    .addComponent(Realizimi, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(BanesatMeQera, javax.swing.GroupLayout.DEFAULT_SIZE, 149, Short.MAX_VALUE)
                                    .addComponent(ndihma, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(rKlientet, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jepMeQera, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(12, 12, 12)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(puntori, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(shtoShfrytzues, javax.swing.GroupLayout.DEFAULT_SIZE, 147, Short.MAX_VALUE)))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(regjistrimiNdertesave, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(kompania, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(regjistrimiNdertesave)
                    .addComponent(rAdresat)
                    .addComponent(kompania))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(rBanesa)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(rKlientet)
                        .addComponent(puntori)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(shitBanesa)
                    .addComponent(jepMeQera)
                    .addComponent(shtoShfrytzues))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(banesaTeLira)
                    .addComponent(BanesatEShitura)
                    .addComponent(BanesatMeQera))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Realizimi)
                    .addComponent(Autori)
                    .addComponent(ndihma))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void BanesatEShituraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BanesatEShituraActionPerformed
        BanesatEShitura bsh = new BanesatEShitura();
        bsh.setVisible(true);
    }//GEN-LAST:event_BanesatEShituraActionPerformed

    private void puntoriActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_puntoriActionPerformed
        try {
            TeDhenatEPuntorve tdhp = new TeDhenatEPuntorve();
            tdhp.setVisible(true);
        } catch (ParseException ex) {
            Logger.getLogger(Butonat.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_puntoriActionPerformed

    private void shitBanesaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_shitBanesaActionPerformed
       ShitBanesa shb = new ShitBanesa();
       shb.setVisible(true);
    }//GEN-LAST:event_shitBanesaActionPerformed

    private void rAdresatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rAdresatActionPerformed
        AdresaRexhister ar = new AdresaRexhister();
        ar.setVisible(true);
    }//GEN-LAST:event_rAdresatActionPerformed

    private void AutoriActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AutoriActionPerformed
        Autori a = new Autori();
        a.setVisible(true);
    }//GEN-LAST:event_AutoriActionPerformed

    @SuppressWarnings("null")
    private void BanesatMeQeraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BanesatMeQeraActionPerformed
        try {
            BanesaEShluaraMeQera bshq = new BanesaEShluaraMeQera();
            bshq.setVisible(true);
        } catch (ParseException ex) {
            Logger.getLogger(Butonat.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }//GEN-LAST:event_BanesatMeQeraActionPerformed

    private void banesaTeLiraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_banesaTeLiraActionPerformed
       BanesatELira bl = new BanesatELira();
       bl.setVisible(true);
    }//GEN-LAST:event_banesaTeLiraActionPerformed

    private void RealizimiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RealizimiActionPerformed
        try {
            Financat f = new Financat();
            f.setVisible(true);
        } catch (ParseException ex) {
            Logger.getLogger(Butonat.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_RealizimiActionPerformed

    private void ndihmaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ndihmaActionPerformed
        Ndihma n = new Ndihma();
        n.setVisible(true);
    }//GEN-LAST:event_ndihmaActionPerformed

    private void rKlientetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rKlientetActionPerformed
        try {
            RegjistrimiKlienteve rk = new RegjistrimiKlienteve();
            rk.setVisible(true);
        } catch (ParseException ex) {
            Logger.getLogger(Butonat.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_rKlientetActionPerformed

    private void rBanesaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rBanesaActionPerformed
        RexhistrimiBanesave rb = new RexhistrimiBanesave();
        rb.setVisible(true);
    }//GEN-LAST:event_rBanesaActionPerformed

    private void regjistrimiNdertesaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_regjistrimiNdertesaveActionPerformed
       RexhistrimiNdertesave rn = new RexhistrimiNdertesave();
       rn.setVisible(true);
    }//GEN-LAST:event_regjistrimiNdertesaveActionPerformed

    private void jepMeQeraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jepMeQeraActionPerformed
        ShloMeQera shmq = new ShloMeQera();
        shmq.setVisible(true);
    }//GEN-LAST:event_jepMeQeraActionPerformed

    private void shtoShfrytzuesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_shtoShfrytzuesActionPerformed
        ShtoShfrytezues shf = new ShtoShfrytezues();
        shf.setVisible(true);
    }//GEN-LAST:event_shtoShfrytzuesActionPerformed

    private void kompaniaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_kompaniaActionPerformed
       TeDhenatEKompanis tdhk = new TeDhenatEKompanis();
       tdhk.setVisible(true);
    }//GEN-LAST:event_kompaniaActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Autori;
    private javax.swing.JButton BanesatEShitura;
    private javax.swing.JButton BanesatMeQera;
    private javax.swing.JButton Realizimi;
    private javax.swing.JButton banesaTeLira;
    private javax.swing.JButton jepMeQera;
    private javax.swing.JButton kompania;
    private javax.swing.JButton ndihma;
    private javax.swing.JButton puntori;
    private javax.swing.JButton rAdresat;
    private javax.swing.JButton rBanesa;
    private javax.swing.JButton rKlientet;
    private javax.swing.JButton regjistrimiNdertesave;
    private javax.swing.JButton shitBanesa;
    private javax.swing.JButton shtoShfrytzues;
    // End of variables declaration//GEN-END:variables
}
