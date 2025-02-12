/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package rs.ac.bg.fon.ai.projekat_teretana.formKorisnik;

import java.io.IOException;
import rs.ac.bg.fon.ai.projekat_teretana.controller.ClientController;
import rs.ac.bg.fon.ai.projekat_teretana.domain.Korisnik;
import rs.ac.bg.fon.ai.projekat_teretana.formKorisnik.constants.MyConstantsKorisnik;
import rs.ac.bg.fon.ai.projekat_teretana.mainForms.MainForm;
import rs.ac.bg.fon.ai.projekat_teretana.models.ModelTabeleKorisnik;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;



/**
 *
 * @author Stefan Segrt
 */
public class FormPretraziKorisnike extends javax.swing.JDialog {

    private int mode;
    private MainForm parent;

    /**
     * Creates new form FormPretraziKorisnike
     */
    public FormPretraziKorisnike(java.awt.Frame parent, boolean modal, int mode) {
        super(parent, modal);
        initComponents();
        this.mode = mode;
        this.parent = (MainForm) parent;
        List<Korisnik> korisnici;
        try {
            korisnici = ClientController.getInstance().getListKorisnik();
            ModelTabeleKorisnik mtk = new ModelTabeleKorisnik(korisnici);
            tblKorisnici.setModel(mtk);

        } catch (Exception ex) {
            if (ex instanceof IOException) {
                JOptionPane.showMessageDialog(this, "GRESKA,POKUSAJTE KASNIJE!!!!!");
                System.exit(0);

            }
        }

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        txtIme = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtPrezime = new javax.swing.JTextField();
        btnFiltriraj = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblKorisnici = new javax.swing.JTable();
        btnDetalji = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setText("Unesite ime:");

        jLabel2.setText("Unesite prezime:");

        btnFiltriraj.setText("Filtriraj");
        btnFiltriraj.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFiltrirajActionPerformed(evt);
            }
        });

        tblKorisnici.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(tblKorisnici);

        btnDetalji.setText("Detalji");
        btnDetalji.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDetaljiActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(207, 207, 207)
                        .addComponent(btnDetalji))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(41, 41, 41)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 509, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel1)
                                    .addComponent(jLabel2))
                                .addGap(56, 56, 56)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtIme)
                                    .addComponent(txtPrezime, javax.swing.GroupLayout.DEFAULT_SIZE, 177, Short.MAX_VALUE))
                                .addGap(34, 34, 34)
                                .addComponent(btnFiltriraj)))))
                .addContainerGap(22, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtIme, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(txtPrezime, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(31, 31, 31)
                        .addComponent(btnFiltriraj)))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 212, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnDetalji)
                .addContainerGap(21, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnDetaljiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDetaljiActionPerformed

        try {
            int selektovaniRed = tblKorisnici.getSelectedRow();
            if (selektovaniRed == -1) {
                JOptionPane.showMessageDialog(this, "Niste selektovali ni jedan red");
                return;
            }
            ModelTabeleKorisnik mtk = (ModelTabeleKorisnik) tblKorisnici.getModel();
            List<Korisnik> korisnici = mtk.getKorisnici();
            Korisnik k = korisnici.get(selektovaniRed);

            k = ClientController.getInstance().loadKorisnik(k);
            JOptionPane.showMessageDialog(this,
                    "Sistem je ucitao korisnika");

            if (MyConstantsKorisnik.DETALJI == this.mode) {
                KorisnikForm kf = new KorisnikForm(this, true, mode, k);
                kf.setVisible(true);
            } else {
                KorisnikForm kf = new KorisnikForm(this, true, mode, k);
                kf.setVisible(true);
            }

        } catch (Exception ex) {
            if (ex instanceof IOException) {
                JOptionPane.showMessageDialog(this, "GRESKA,POKUSAJTE KASNIJE!!!!!");
                System.exit(0);

            }
            JOptionPane.showMessageDialog(this, ex.getMessage());
        }
    }//GEN-LAST:event_btnDetaljiActionPerformed

    private void btnFiltrirajActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFiltrirajActionPerformed

        try {
            String ime = txtIme.getText();
            String prezime = txtPrezime.getText();

            Korisnik korisnik = new Korisnik();
            korisnik.setIme(ime);
            korisnik.setPrezime(prezime);

            if (txtIme.getText().isEmpty() && txtPrezime.getText().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Morate popuniti barem jedno polje!!!");
                return;
            }

            List<Korisnik> korisnici = ClientController.getInstance().searchKorisnik(korisnik);
            ModelTabeleKorisnik mtk = new ModelTabeleKorisnik(korisnici);
            tblKorisnici.setModel(mtk);
            JOptionPane.showMessageDialog(this, "Sistem je nasao korisnike po zadatoj vrednosti");

            txtIme.setText("");
            txtPrezime.setText("");

//
//
//            for (int i = 0; i < korisnici.size(); i++) {
//                Korisnik k = korisnici.get(i);
//                JComboBox<TipTreninga> cmbTip = comboMap.get(k);
//                tblKorisnici.setValueAt(cmbTip, i, 3); 
//            }
//            List<TipTreninga> tipovi;
//
//            for (Korisnik k : korisnici) {
//
//                tipovi = k.getTipovi();
//                JComboBox<TipTreninga> cmbTip = new JComboBox<>();
//                for (TipTreninga tipTreninga : tipovi) {
//                    cmbTip.addItem(tipTreninga);
//                }
//                TableColumn kolonaTipa = tblKorisnici.getColumnModel().getColumn(3);
//                kolonaTipa.setCellEditor(new DefaultCellEditor(cmbTip));
//            }
//            int width = 150;
//            TableColumn column = tblKorisnici.getColumnModel().getColumn(3);
//            column.setPreferredWidth(width);
        } catch (Exception ex) {
            if (ex instanceof IOException) {
                JOptionPane.showMessageDialog(this, "GRESKA,POKUSAJTE KASNIJE!!!!!");
                System.exit(0);

            }
            List<Korisnik> korisnici = new ArrayList<>();
            ModelTabeleKorisnik mtk = new ModelTabeleKorisnik(korisnici);
            tblKorisnici.setModel(mtk);
            JOptionPane.showMessageDialog(this, ex.getMessage());
        }

    }//GEN-LAST:event_btnFiltrirajActionPerformed

    /**
     * @param args the command line arguments
     */
//    public static void main(String args[]) {
//        /* Set the Nimbus look and feel */
//        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
//        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
//         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
//         */
//        try {
//            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
//                if ("Nimbus".equals(info.getName())) {
//                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
//                    break;
//                }
//            }
//        } catch (ClassNotFoundException ex) {
//            java.util.logging.Logger.getLogger(FormPretraziKorisnike.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(FormPretraziKorisnike.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(FormPretraziKorisnike.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(FormPretraziKorisnike.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//
//        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new FormPretraziKorisnike().setVisible(true);
//            }
//        });
//    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnDetalji;
    private javax.swing.JButton btnFiltriraj;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblKorisnici;
    private javax.swing.JTextField txtIme;
    private javax.swing.JTextField txtPrezime;
    // End of variables declaration//GEN-END:variables

    void refreshForm() {

        try {
            String ime = txtIme.getText();
            String prezime = txtPrezime.getText();

            Korisnik korisnik = new Korisnik();
            korisnik.setIme(ime);
            korisnik.setPrezime(prezime);

            List<Korisnik> korisnici = ClientController.getInstance().searchKorisnik(korisnik);
            ModelTabeleKorisnik mtk = new ModelTabeleKorisnik(korisnici);
            tblKorisnici.setModel(mtk);

        } catch (Exception ex) {
            if(ex instanceof IOException){
            JOptionPane.showMessageDialog(this, "GRESKA,POKUSAJTE KASNIJE!!!!!");
            System.exit(0);
            }
            List<Korisnik> korisnici = new ArrayList<>();
            ModelTabeleKorisnik mtk = new ModelTabeleKorisnik(korisnici);
            tblKorisnici.setModel(mtk);
        }
    }
}
