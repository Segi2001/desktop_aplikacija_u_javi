/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package rs.ac.bg.fon.ai.projekat_teretana.formEvidencijaPrisustva;

import static com.google.protobuf.JavaFeaturesProto.java;
import java.io.IOException;
import rs.ac.bg.fon.ai.projekat_teretana.controller.ClientController;
import rs.ac.bg.fon.ai.projekat_teretana.domain.EvidentiranjePrisustva;
import rs.ac.bg.fon.ai.projekat_teretana.domain.Korisnik;
import rs.ac.bg.fon.ai.projekat_teretana.domain.TipTreninga;
import rs.ac.bg.fon.ai.projekat_teretana.domain.Trening;
import rs.ac.bg.fon.ai.projekat_teretana.formEvidencijaPrisustva.constants.MyConstantsEvidencija;
import rs.ac.bg.fon.ai.projekat_teretana.mainForms.MainForm;
import rs.ac.bg.fon.ai.projekat_teretana.models.ModelTabeleEP;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.TableColumn;
import rs.ac.bg.fon.ai.projekat_teretana.json.JsonUtils;

/**
 *
 * @author Stefan Segrt
 */
public class EvidencijaPrisustvaForm extends javax.swing.JDialog {

    private int mode;
    private MainForm parent;

    Trening trening;

    /**
     * Creates new form EvidencijaPrisustvaForm
     */
    public EvidencijaPrisustvaForm(java.awt.Frame parent, boolean modal, int mode) {
        super(parent, modal);
        initComponents();
        this.mode = mode;
        this.parent = (MainForm) parent;
        prepareForm();
        if (mode == MyConstantsEvidencija.DODAJ_PRISUSTVA) {
            popuniComboBoxTreninzima();
        }
        List<EvidentiranjePrisustva> prisustva = new ArrayList<>();
        ModelTabeleEP mtep = new ModelTabeleEP(prisustva);
        tblEP.setModel(mtep);
        if (mode == MyConstantsEvidencija.IZMENI_PODATKE_O_EVIDENCIJAMA) {
            btnEvidentiraj.setEnabled(false);
            btnIzbaciIzEP.setEnabled(false);
            txtKalorije.setEnabled(false);
            txtOtkucaji.setEnabled(false);
            popuniComboBoxSvimTreninzima();
            btnUcitajEvidencije.setEnabled(false);

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
        cmbTrening = new javax.swing.JComboBox<>();
        btnUcitajKorisnike = new javax.swing.JButton();
        cmbKorisnici = new javax.swing.JComboBox<>();
        btnEvidentiraj = new javax.swing.JButton();
        btnIzbaciIzEP = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblEP = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        txtOtkucaji = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtKalorije = new javax.swing.JTextField();
        btnSacuvaj = new javax.swing.JButton();
        btnUcitajEvidencije = new javax.swing.JButton();
        btnDetalji = new javax.swing.JButton();
        btnIzmeni = new javax.swing.JButton();
        btnOtkazi = new javax.swing.JButton();
        btnOmoguciIspocetka = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setText("Izaberite trening:");

        btnUcitajKorisnike.setText("Ucitaj Korisnike");
        btnUcitajKorisnike.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUcitajKorisnikeActionPerformed(evt);
            }
        });

        btnEvidentiraj.setText("Evidentiraj korisnika");
        btnEvidentiraj.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEvidentirajActionPerformed(evt);
            }
        });

        btnIzbaciIzEP.setText("Izbaci korisnika iz evidencije");
        btnIzbaciIzEP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnIzbaciIzEPActionPerformed(evt);
            }
        });

        tblEP.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(tblEP);

        jLabel2.setText("Upisite otkucaje srca korisnika:");

        jLabel3.setText("Upisite potrosene kalorije korisnika:");

        btnSacuvaj.setText("Sacuvaj");
        btnSacuvaj.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSacuvajActionPerformed(evt);
            }
        });

        btnUcitajEvidencije.setText("Ucitaj evidencije");
        btnUcitajEvidencije.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUcitajEvidencijeActionPerformed(evt);
            }
        });

        btnDetalji.setText("Detalji");
        btnDetalji.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDetaljiActionPerformed(evt);
            }
        });

        btnIzmeni.setText("Izmeni");
        btnIzmeni.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnIzmeniActionPerformed(evt);
            }
        });

        btnOtkazi.setText("Otkazi");

        btnOmoguciIspocetka.setText("Omoguci ispocetka");
        btnOmoguciIspocetka.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOmoguciIspocetkaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnUcitajEvidencije)
                        .addGap(63, 63, 63)
                        .addComponent(btnOmoguciIspocetka, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 52, Short.MAX_VALUE)
                        .addComponent(btnEvidentiraj)
                        .addGap(101, 101, 101)
                        .addComponent(btnIzbaciIzEP)
                        .addGap(95, 95, 95))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel1)
                                    .addComponent(btnUcitajKorisnike)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel3)
                                    .addComponent(btnSacuvaj))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(44, 44, 44)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                    .addComponent(txtOtkucaji, javax.swing.GroupLayout.DEFAULT_SIZE, 239, Short.MAX_VALUE)
                                                    .addComponent(txtKalorije))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(btnOtkazi))
                                            .addGroup(layout.createSequentialGroup()
                                                .addGap(89, 89, 89)
                                                .addComponent(btnDetalji)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(btnIzmeni)))
                                        .addGap(101, 101, 101))
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                        .addGap(31, 31, 31)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(cmbTrening, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(cmbKorisnici, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))))
                        .addGap(38, 38, 38))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnUcitajKorisnike)
                            .addComponent(cmbKorisnici, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnEvidentiraj)
                            .addComponent(btnIzbaciIzEP)
                            .addComponent(btnUcitajEvidencije)
                            .addComponent(btnOmoguciIspocetka))
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(txtOtkucaji, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(cmbTrening, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnOtkazi)
                        .addGap(36, 36, 36)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnDetalji)
                            .addComponent(btnIzmeni)))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtKalorije, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3))
                        .addGap(27, 27, 27)
                        .addComponent(btnSacuvaj)))
                .addGap(49, 49, 49))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnDetaljiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDetaljiActionPerformed

        int selektovaniRed = tblEP.getSelectedRow();
        if (selektovaniRed == -1) {
            JOptionPane.showMessageDialog(this, "Niste selektovali ni jedan red");
            return;
        }
        ModelTabeleEP mtep = (ModelTabeleEP) tblEP.getModel();
        EvidentiranjePrisustva ep = mtep.getPrisustva().get(selektovaniRed);

        DetaljiEvidencijaForm def = new DetaljiEvidencijaForm(this, true, ep);
        def.setVisible(true);
    }//GEN-LAST:event_btnDetaljiActionPerformed

    private void btnIzmeniActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnIzmeniActionPerformed

        try {
            ModelTabeleEP mtep = (ModelTabeleEP) tblEP.getModel();
            List<EvidentiranjePrisustva> prisustva = mtep.getPrisustva();

            ClientController.getInstance().updatePrisustva(prisustva);

            JOptionPane.showMessageDialog(this, "Sistem je uspesno izmenio podatke o evidencijama");

            List<EvidentiranjePrisustva> praznaLista = new ArrayList<>();
            mtep = new ModelTabeleEP(praznaLista);
            tblEP.setModel(mtep);
            cmbKorisnici.removeAllItems();
            cmbTrening.setSelectedIndex(-1);

            btnUcitajEvidencije.setEnabled(false);
            cmbTrening.setEnabled(true);
            btnUcitajKorisnike.setEnabled(true);
            btnEvidentiraj.setEnabled(false);
            btnIzbaciIzEP.setEnabled(false);
            txtKalorije.setEnabled(false);
            txtOtkucaji.setEnabled(false);

        } catch (Exception ex) {
            if (ex instanceof IOException) {
                JOptionPane.showMessageDialog(this, "GRESKA,POKUSAJTE KASNIJE!!!!!");
                System.exit(0);

            }
            JOptionPane.showMessageDialog(this, ex.getMessage());
        }


    }//GEN-LAST:event_btnIzmeniActionPerformed

    private void btnUcitajKorisnikeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUcitajKorisnikeActionPerformed

        try {
            cmbKorisnici.removeAllItems();
            trening = (Trening) cmbTrening.getSelectedItem();
            if (trening == null) {
                JOptionPane.showMessageDialog(this, "Morate izabrati neki trening!!!");
                return;
            }
            TipTreninga tip = trening.getTip();

            List<TipTreninga> tipovi = new ArrayList<>();
            tipovi.add(tip);

            Korisnik k = new Korisnik();
            k.setTipovi(tipovi);

            List<Korisnik> korisnici = ClientController.getInstance().searchKorisnik(k);
            JOptionPane.showMessageDialog(this, "Sistem je nasao korisnike po zadatoj vrednosti");
            for (Korisnik korisnik : korisnici) {
                cmbKorisnici.addItem(korisnik);
            }

            System.out.println(korisnici.get(0).getTipovi());
            cmbKorisnici.setSelectedIndex(-1);

            btnUcitajKorisnike.setEnabled(false);
            cmbTrening.setEnabled(false);
            btnUcitajEvidencije.setEnabled(true);
        } catch (Exception ex) {
            if (ex instanceof IOException) {
                JOptionPane.showMessageDialog(this, "GRESKA,POKUSAJTE KASNIJE!!!!!");
                System.exit(0);

            }
            JOptionPane.showMessageDialog(this, ex.getMessage());
        }


    }//GEN-LAST:event_btnUcitajKorisnikeActionPerformed

    private void btnEvidentirajActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEvidentirajActionPerformed

        try {
//            if (txtKalorije.getText().isEmpty() || txtOtkucaji.getText().isEmpty() || cmbKorisnici.getSelectedIndex() == -1) {
//                JOptionPane.showMessageDialog(this, "Niste popunili prazna polja!!");
//                return;
//            }
            Trening trening = (Trening) cmbTrening.getSelectedItem();

            Korisnik korisnik = (Korisnik) cmbKorisnici.getSelectedItem();
            if (korisnik == null) {
                JOptionPane.showMessageDialog(this, "Morate izabrati nekog korisnika!!!");
                return;
            }
            //System.out.println(korisnik.getTipovi());

            int kalorije = validateKalorije(txtKalorije.getText());
            int brojotk = validateOtkucaje(txtOtkucaji.getText());
            String porukaZaPrazno = "";
            int brojac = 0;
            if (txtKalorije.getText().isEmpty() || txtOtkucaji.getText().isEmpty()) {

                if (txtKalorije.getText().isEmpty()) {
                    porukaZaPrazno += "kalorije, ";
                    brojac++;
                }
                if (txtOtkucaji.getText().isEmpty()) {
                    porukaZaPrazno += "otkucaji ";
                    brojac++;
                }
                if (brojac == 1) {
                    porukaZaPrazno = "Polje " + porukaZaPrazno + " nije popunjeno";
                    JOptionPane.showMessageDialog(this, porukaZaPrazno);
                    return;
                }
                JOptionPane.showMessageDialog(this, "Polja " + porukaZaPrazno + " nisu popunjena");
                return;
            }
            brojac = 0;
            String porukaZaValidaciju = "";

            if (kalorije == 0) {
                porukaZaValidaciju += "kalorije, ";
                brojac++;
            }
            if (brojotk == 0) {
                porukaZaValidaciju += "otkucaji srca, ";
                brojac++;
            }
            if (brojac == 1 && porukaZaValidaciju.contains("otkucaji srca")) {
                JOptionPane.showMessageDialog(this, "Polje " + porukaZaValidaciju + " nije u dobrom formatu. "
                        + "Ocekivano je da broj otkucaja bude pozitivan broj manji od 205.");
                return;
            }
            if (brojac == 1) {
                JOptionPane.showMessageDialog(this, "Polje " + porukaZaValidaciju + " nije u dobrom formatu.");
                return;
            }
            if (!porukaZaValidaciju.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Polja " + porukaZaValidaciju + " nisu u dobrom formatu. "
                        + "Ocekivano je da broj otkucaja bude pozitivan broj manji od 205.");
                return;
            }

            EvidentiranjePrisustva ep = new EvidentiranjePrisustva(trening, korisnik, kalorije, brojotk);

            ModelTabeleEP mtep = (ModelTabeleEP) tblEP.getModel();
            mtep.addEP(ep);

            int width = 120;
            TableColumn column = tblEP.getColumnModel().getColumn(0);
            column.setPreferredWidth(width);

            int width1 = 100;
            TableColumn column1 = tblEP.getColumnModel().getColumn(2);
            column1.setPreferredWidth(width);

            int width2 = 100;
            TableColumn column2 = tblEP.getColumnModel().getColumn(3);
            column2.setPreferredWidth(width);

            txtKalorije.setText("");
            txtOtkucaji.setText("");

            cmbKorisnici.removeItem(korisnik);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage());
            txtKalorije.setText("");
            txtOtkucaji.setText("");
        }

    }//GEN-LAST:event_btnEvidentirajActionPerformed

    private void btnIzbaciIzEPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnIzbaciIzEPActionPerformed

        int selektovaniRed = tblEP.getSelectedRow();
        if (selektovaniRed == -1) {
            JOptionPane.showMessageDialog(this, "Niste selektovali ni jedan red");
            return;
        }
        ModelTabeleEP mtep = (ModelTabeleEP) tblEP.getModel();
        EvidentiranjePrisustva ep = mtep.getPrisustva().get(selektovaniRed);

        mtep.deleteEP(ep);

        cmbKorisnici.addItem(ep.getKorisnik());


    }//GEN-LAST:event_btnIzbaciIzEPActionPerformed

    private void btnSacuvajActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSacuvajActionPerformed

        try {
            ModelTabeleEP mtep = (ModelTabeleEP) tblEP.getModel();
            List<EvidentiranjePrisustva> prisustva = mtep.getPrisustva();

            // System.out.println(prisustva.get(0).getKorisnik().getTipovi());
            ClientController.getInstance().addPrisustva(prisustva);

            JOptionPane.showMessageDialog(this, "Sistem je evidentirao korisnike na treningu");

            cmbTrening.removeItem(trening);

            List<EvidentiranjePrisustva> praznaLista = new ArrayList<>();
            mtep = new ModelTabeleEP(praznaLista);
            tblEP.setModel(mtep);
            cmbKorisnici.removeAllItems();

            cmbTrening.setSelectedIndex(-1);
            cmbTrening.setEnabled(true);
            btnUcitajKorisnike.setEnabled(true);
            
            
            String filePath = "src/main/resources/file.json";
            JsonUtils.UpisiUJSONSaNazivomKlase(filePath, prisustva, "Naziv klase: ", "EvidentiranjePrisustva");

        } catch (Exception ex) {

            if (ex instanceof IOException) {
                JOptionPane.showMessageDialog(this, "GRESKA,POKUSAJTE KASNIJE!!!!!");
                System.exit(0);
            }
            List<EvidentiranjePrisustva> praznaLista = new ArrayList<>();
            ModelTabeleEP mtep = new ModelTabeleEP(praznaLista);
            tblEP.setModel(mtep);
            cmbKorisnici.removeAllItems();
            cmbTrening.setSelectedIndex(-1);
            txtKalorije.setText("");
            txtOtkucaji.setText("");
            cmbTrening.setEnabled(true);
            btnUcitajKorisnike.setEnabled(true);

            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, ex.getMessage());
        }

    }//GEN-LAST:event_btnSacuvajActionPerformed

    private void btnUcitajEvidencijeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUcitajEvidencijeActionPerformed

        try {
            Trening trening = (Trening) cmbTrening.getSelectedItem();
            if (trening == null) {
                JOptionPane.showMessageDialog(this, "Morate selektovati neki trening!!!");
                return;
            }
            EvidentiranjePrisustva ep = new EvidentiranjePrisustva();
            ep.setTrening(trening);
            List<Korisnik> korisnici = new ArrayList<>();
            //System.out.println(trening.getTip());

            List<EvidentiranjePrisustva> prisustva = ClientController.getInstance().searchPrisustva(ep);
            for (EvidentiranjePrisustva evidentiranjePrisustva : prisustva) {
                korisnici.add(evidentiranjePrisustva.getKorisnik());
            }

            for (Korisnik korisnik : korisnici) {
                cmbKorisnici.removeItem(korisnik);
            }

            ModelTabeleEP mtep = new ModelTabeleEP(prisustva);
            tblEP.setModel(mtep);
            JOptionPane.showMessageDialog(this, "Sistem je nasao Evidencije po zadatoj vrednosti");

            txtKalorije.setEnabled(true);
            txtOtkucaji.setEnabled(true);
            btnEvidentiraj.setEnabled(true);
            btnIzbaciIzEP.setEnabled(true);

            btnUcitajEvidencije.setEnabled(false);

        } catch (Exception ex) {
            if (ex instanceof IOException) {
                JOptionPane.showMessageDialog(this, "GRESKA,POKUSAJTE KASNIJE!!!!!");
                System.exit(0);

            }
            List<EvidentiranjePrisustva> lista = new ArrayList<>();
            ModelTabeleEP mtep = new ModelTabeleEP(lista);
            tblEP.setModel(mtep);
            btnUcitajKorisnike.setEnabled(true);
            cmbTrening.setEnabled(true);
            cmbTrening.setSelectedIndex(-1);
            JOptionPane.showMessageDialog(this, ex.getMessage());

           

        }
    }//GEN-LAST:event_btnUcitajEvidencijeActionPerformed

    private void btnOmoguciIspocetkaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOmoguciIspocetkaActionPerformed

        btnUcitajEvidencije.setEnabled(false);
        cmbTrening.setEnabled(true);
        btnUcitajKorisnike.setEnabled(true);
        if (mode == MyConstantsEvidencija.IZMENI_PODATKE_O_EVIDENCIJAMA) {
            btnEvidentiraj.setEnabled(false);
            btnIzbaciIzEP.setEnabled(false);
            txtKalorije.setEnabled(false);
            txtOtkucaji.setEnabled(false);
        }

        cmbKorisnici.removeAllItems();
        cmbKorisnici.setSelectedIndex(-1);
        cmbTrening.setSelectedIndex(-1);
        List<EvidentiranjePrisustva> lista = new ArrayList<>();
        ModelTabeleEP mtep = new ModelTabeleEP(lista);
        tblEP.setModel(mtep);

    }//GEN-LAST:event_btnOmoguciIspocetkaActionPerformed

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
//            java.util.logging.Logger.getLogger(EvidencijaPrisustvaForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(EvidencijaPrisustvaForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(EvidencijaPrisustvaForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(EvidencijaPrisustvaForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//
//        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new EvidencijaPrisustvaForm().setVisible(true);
//            }
//        });
//    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnDetalji;
    private javax.swing.JButton btnEvidentiraj;
    private javax.swing.JButton btnIzbaciIzEP;
    private javax.swing.JButton btnIzmeni;
    private javax.swing.JButton btnOmoguciIspocetka;
    private javax.swing.JButton btnOtkazi;
    private javax.swing.JButton btnSacuvaj;
    private javax.swing.JButton btnUcitajEvidencije;
    private javax.swing.JButton btnUcitajKorisnike;
    private javax.swing.JComboBox<Korisnik> cmbKorisnici;
    private javax.swing.JComboBox<Trening> cmbTrening;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblEP;
    private javax.swing.JTextField txtKalorije;
    private javax.swing.JTextField txtOtkucaji;
    // End of variables declaration//GEN-END:variables

    private void prepareForm() {

        switch (mode) {

            case MyConstantsEvidencija.DODAJ_PRISUSTVA:
                btnIzmeni.setVisible(false);
                btnUcitajEvidencije.setVisible(false);
                btnDetalji.setVisible(false);
                btnOtkazi.setVisible(true);
                setLocationRelativeTo(null);
                break;
            case MyConstantsEvidencija.IZMENI_PODATKE_O_EVIDENCIJAMA:
                btnSacuvaj.setVisible(false);
                setLocationRelativeTo(null);
                break;

        }

    }

    private void popuniComboBoxTreninzima() {

        try {
            Trening t = new Trening();
            t.setKonstanta(1);
            List<Trening> treninzi = ClientController.getInstance().searchTrening(t);

            for (Trening trening : treninzi) {
                cmbTrening.addItem(trening);
            }
            cmbTrening.setSelectedIndex(-1);

        } catch (Exception ex) {
            if (ex instanceof IOException) {
                JOptionPane.showMessageDialog(this, "GRESKA,POKUSAJTE KASNIJE!!!!!");
                System.exit(0);
            }

        }

    }

    void refreshTable() {

        ModelTabeleEP mtep = (ModelTabeleEP) tblEP.getModel();
        mtep.refreshTable();

    }

    private void popuniComboBoxSvimTreninzima() {

        try {

            List<Trening> treninzi = ClientController.getInstance().getListTrening();

            for (Trening trening : treninzi) {
                cmbTrening.addItem(trening);
            }
            cmbTrening.setSelectedIndex(-1);

        } catch (Exception ex) {
            if (ex instanceof IOException) {
                JOptionPane.showMessageDialog(this, "GRESKA,POKUSAJTE KASNIJE!!!!!");
                System.exit(0);
            }
        }
    }

    private int validateKalorije(String kalorijeStr) {

        int kalorije = 0;
        try {
            kalorije = Integer.parseInt(kalorijeStr);
        } catch (NumberFormatException nfe) {
            return 0;
        }
        if (kalorije <= 0) {
            return 0;
        }
        return kalorije;
    }

    private int validateOtkucaje(String otkucajiStr) {

        int otkucaji = 0;
        try {
            otkucaji = Integer.parseInt(otkucajiStr);
        } catch (NumberFormatException nfe) {
            return 0;
        }
        if (otkucaji <= 0 || otkucaji > 205) {
            return 0;
        }
        return otkucaji;
    }

}
