/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proje3;

import com.mysql.jdbc.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class PremiumKullanici extends javax.swing.JFrame {

    JComboBox jComboBox1 = new JComboBox();
    String premiumcalmaListesiId;
    String premiumcalmaListesiAdi;
    String takipedenid;
    String takipedilenid;
    int takipkontrol = 0;

    public PremiumKullanici() {
        initComponents();
       
        jTable2.setVisible(false);
        sarkiTablosu.setVisible(false);
        jComboBox1.setVisible(false);
        TakipKontrol();
        try {
            Connection myConn = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/muzik_dosyam", "root", "Ok.20002000");
            Statement myStat9 = (Statement) myConn.createStatement();
            ResultSet rs = myStat9.executeQuery("SELECT Adi FROM calma_listesi WHERE Kullanici_Id=" + Menu.kullanicialma);
            Statement myStat2 = (Statement) myConn.createStatement();
            ResultSet rs2 = myStat2.executeQuery("SELECT Kullanici_Adi FROM kullanici WHERE Kullanici_Id=" + kullaniciSayfa.gidilecekprofil);
            while (rs2.next()) {

                jLabel1.setText(rs2.getString("Kullanici_Adi"));
            }
            while (rs.next()) {
                jComboBox1.addItem(rs.getString("Adi"));

            }
        } catch (SQLException ex) {
            Logger.getLogger(KayitEkrani.class.getName()).log(Level.SEVERE, null, ex);
        }

        jComboBox1.setBackground(new java.awt.Color(51, 51, 51));

        jComboBox1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N

        jComboBox1.setForeground(new java.awt.Color(0, 0, 0));

        jComboBox1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(42, 107, 149), 5));

        sarkiListePanel.add(jComboBox1);
        jComboBox1.setBounds(1230, 90, 300, 70);
        calmaListesiGoster();
    }

    public void calmaListesiGoster() {
        try {

            Connection myConn = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/muzik_dosyam", "root", "Ok.20002000");
            Statement myStat = (Statement) myConn.createStatement();

            ResultSet rs = myStat.executeQuery("SELECT * FROM calma_listesi WHERE Kullanici_Id= " + kullaniciSayfa.gidilecekprofil);//sql sorgus
            while (rs.next()) {
                premiumcalmaListesiId = String.valueOf(rs.getInt("CalmaListesi_Id"));//sql deki kolon isimleri
                premiumcalmaListesiAdi = String.valueOf(rs.getString("Adi"));
                String tbData[] = {premiumcalmaListesiId, premiumcalmaListesiAdi};//tablo içinn dizi oluşturdu
                DefaultTableModel tblModel = (DefaultTableModel) jTable2.getModel();//tablomuz
                tblModel.addRow(tbData);//tabloya oluşturduğu diziyi verdi
            }

        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(KayitEkrani.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
    }

    public void calmaListesineSarkiEkle() throws SQLException {
        Connection connect = null;
        Connection myConn = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/muzik_dosyam", "root", "Ok.20002000");
        DbHelper db = new DbHelper();
        PreparedStatement statement = null;

        try {
            Statement myStat = (Statement) myConn.createStatement();
            Statement myStat2 = (Statement) myConn.createStatement();
            Statement myStat3 = (Statement) myConn.createStatement();
            Statement myStat4 = (Statement) myConn.createStatement();
            connect = db.getConnection();

            String sql = "insert into muzik_dosyam.calma_listesi_sarkilar(CalmaListesi_Id,SanatciSarki_Id)" + "values(?,?)";
            statement = connect.prepareStatement(sql);

            String calmaListesiId = "SELECT CalmaListesi_Id FROM calma_listesi WHERE  Adi=" + "\"" + jComboBox1.getSelectedItem() + "\"";
            ResultSet rs1 = myStat.executeQuery(calmaListesiId);

            ResultSet rs2 = myStat2.executeQuery("SELECT SarkiTur_Id FROM sarki WHERE Sarki_Id in ( SELECT Sarki_Id FROM sanatci_sarki WHERE SanatciSarki_Id=" + ssanatciIdGirr.getText() + ")");
            ResultSet rs3 = myStat3.executeQuery("SELECT SarkiTur_Id FROM calma_listesi WHERE Adi=" + "\"" + jComboBox1.getSelectedItem() + "\"");
            ResultSet rs4 = myStat4.executeQuery("SELECT SanatciSarki_Id FROM calma_listesi_sarkilar WHERE CalmaListesi_Id in(SELECT CalmaListesi_Id FROM calma_listesi WHERE Adi=" + "\"" + jComboBox1.getSelectedItem() + "\"" + ")");
            int idsarki = Integer.valueOf(ssanatciIdGirr.getText());
            int idcalma;
            int kontrol = 0;
            while (rs4.next()) {
                idcalma = rs4.getInt("SanatciSarki_Id");
                if (idcalma == idsarki) {
                    kontrol = 1;
                }
            }
            String calmaId = null;
            while (rs3.next()) {
                calmaId = rs3.getString("SarkiTur_Id");
            }
            String sarkiId = null;
            while (rs2.next()) {
                sarkiId = rs2.getString("SarkiTur_Id");
            }
            if (calmaId.contains(sarkiId)) {
                if (kontrol == 0) {
                    while (rs1.next()) {
                        statement.setInt(1, (rs1.getInt("CalmaListesi_Id")));
                    }
                    statement.setInt(2, Integer.valueOf(ssanatciIdGirr.getText()));

                    statement.executeUpdate();
                    JOptionPane.showMessageDialog(null, "Kayıt başarılı");
                } else {
                    JOptionPane.showMessageDialog(null, "Şarkı bu listede zaten var. Tekrar eklenmiyor.");
                }
            } else {
                JOptionPane.showMessageDialog(null, "Çalma Listesi Ve Şarkı Türü farklı.Şarkı eklenemiyor");
            }

        } catch (SQLException exception) {
            db.ShowError(exception);
        } finally {
            statement.close();

        }
    }

    public void KullaniciTakipEkle() throws SQLException {

        Connection connect = null;
        DbHelper db = new DbHelper();
        PreparedStatement statement = null;
        try {
            connect = db.getConnection();
            String sql = "insert into muzik_dosyam.kullanici_takip(TakipEdenKullanicilar_Id,TakipEdilenKullanicilar_Id)" + "values(?,?)";
            statement = connect.prepareStatement(sql);
            statement.setInt(1, Menu.kullanicialma);
            statement.setInt(2, kullaniciSayfa.gidilecekprofil);
            statement.executeUpdate();
            JOptionPane.showMessageDialog(null, "Kayıt başarılı");
        } catch (SQLException exception) {
            db.ShowError(exception);
        } finally {
            statement.close();

        }

    }

    public void KullaniciTakipCikar() throws SQLException {
        Connection connect = null;
        DbHelper db = new DbHelper();
        PreparedStatement statement = null;
        try {
            connect = db.getConnection();
            String sql = "DELETE from kullanici_Takip WHERE TakipEdenKullanicilar_Id=? && TakipEdilenKullanicilar_Id=?";
            statement = connect.prepareStatement(sql);
            statement.setInt(1, Integer.valueOf(Menu.kullanicialma));
            statement.setInt(2, Integer.valueOf(kullaniciSayfa.gidilecekprofil));
            statement.executeUpdate();
            JOptionPane.showMessageDialog(null, " Takip Cikarma Başarili");
        } catch (SQLException exception) {
            db.ShowError(exception);
        } finally {
            statement.close();
            connect.close();
        }
    }

    public void TakipKontrol() {
        try {
            Connection myConn = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/muzik_dosyam", "root", "Ok.20002000");
            Statement myStat = (Statement) myConn.createStatement();
            ResultSet rs = myStat.executeQuery("SELECT * FROM kullanici_takip");

            while (rs.next()) {
                if (Menu.kullanicialma == rs.getInt("TakipEdenKullanicilar_Id") && kullaniciSayfa.gidilecekprofil == rs.getInt("TakipEdilenKullanicilar_Id")) {
                    jToggleButton1.setText("Takip Ediliyor");
                    takipkontrol = 1;
                    jTable2.setVisible(true);
                    sarkiTablosu.setVisible(true);
                    jComboBox1.setVisible(true);

                }

            }
        } catch (SQLException ex) {
            Logger.getLogger(kullaniciSayfa.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
     


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jButton2 = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator2 = new javax.swing.JSeparator();
        sarkiIdGir1 = new javax.swing.JTextField();
        s3 = new javax.swing.JLabel();
        sarkiListePanel = new javax.swing.JPanel();
        sarkiSilButon = new javax.swing.JButton();
        ssanatciIdGirr = new javax.swing.JTextField();
        jButton7 = new javax.swing.JButton();
        jLabel15 = new javax.swing.JLabel();
        jButton11 = new javax.swing.JButton();
        sarkiScrollPane = new javax.swing.JScrollPane();
        sarkiTablosu = new javax.swing.JTable();
        s8 = new javax.swing.JLabel();
        jButton13 = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jToggleButton1 = new javax.swing.JToggleButton();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(43, 42, 42));
        jPanel1.setLayout(null);

        jPanel3.setBackground(new java.awt.Color(0, 0, 0));
        jPanel3.setLayout(null);

        jScrollPane2.setBackground(new java.awt.Color(51, 51, 51));
        jScrollPane2.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(42, 107, 149), 5), "Çalma Listeleri", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 18), new java.awt.Color(204, 204, 204))); // NOI18N

        jTable2.setBackground(new java.awt.Color(51, 51, 51));
        jTable2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 18), new java.awt.Color(204, 204, 204))); // NOI18N
        jTable2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id", "Ad"
            }
        ));
        jTable2.setRowHeight(30);
        jScrollPane2.setViewportView(jTable2);

        jPanel3.add(jScrollPane2);
        jScrollPane2.setBounds(30, 60, 300, 410);

        jButton2.setBackground(new java.awt.Color(51, 51, 51));
        jButton2.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jButton2.setForeground(new java.awt.Color(0, 0, 0));
        jButton2.setText("Çalma Listesini Görüntüle");
        jButton2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 51, 255), 0));
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel3.add(jButton2);
        jButton2.setBounds(30, 590, 300, 70);

        jSeparator1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jSeparator1.setMinimumSize(new java.awt.Dimension(300, 20));
        jPanel3.add(jSeparator1);
        jSeparator1.setBounds(20, 30, 320, 10);
        jPanel3.add(jSeparator2);
        jSeparator2.setBounds(20, 680, 320, 30);

        sarkiIdGir1.setBackground(new java.awt.Color(51, 51, 51));
        sarkiIdGir1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        sarkiIdGir1.setForeground(new java.awt.Color(204, 204, 204));
        sarkiIdGir1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sarkiIdGir1ActionPerformed(evt);
            }
        });
        jPanel3.add(sarkiIdGir1);
        sarkiIdGir1.setBounds(40, 520, 280, 40);

        s3.setBackground(new java.awt.Color(204, 204, 204));
        s3.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        s3.setForeground(new java.awt.Color(204, 204, 204));
        s3.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(42, 107, 149), 4), "ID", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 18), new java.awt.Color(204, 204, 204))); // NOI18N
        jPanel3.add(s3);
        s3.setBounds(30, 490, 300, 80);

        jPanel1.add(jPanel3);
        jPanel3.setBounds(-10, 240, 350, 800);

        sarkiListePanel.setBackground(new java.awt.Color(51, 51, 51));
        sarkiListePanel.setForeground(new java.awt.Color(51, 51, 51));
        sarkiListePanel.setLayout(null);

        sarkiSilButon.setBackground(new java.awt.Color(255, 204, 0));
        sarkiSilButon.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        sarkiSilButon.setForeground(new java.awt.Color(51, 51, 51));
        sarkiSilButon.setText("SİL");
        sarkiListePanel.add(sarkiSilButon);
        sarkiSilButon.setBounds(80, 800, 1040, 60);

        ssanatciIdGirr.setBackground(new java.awt.Color(51, 51, 51));
        ssanatciIdGirr.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        ssanatciIdGirr.setForeground(new java.awt.Color(204, 204, 204));
        ssanatciIdGirr.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ssanatciIdGirrActionPerformed(evt);
            }
        });
        sarkiListePanel.add(ssanatciIdGirr);
        ssanatciIdGirr.setBounds(1240, 380, 290, 50);

        jButton7.setBackground(new java.awt.Color(60, 60, 60));
        jButton7.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jButton7.setForeground(new java.awt.Color(0, 0, 0));
        jButton7.setText("ÇALMA LİSTESİNE EKLE");
        jButton7.setBorder(null);
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });
        sarkiListePanel.add(jButton7);
        jButton7.setBounds(1230, 470, 310, 90);

        jLabel15.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(204, 204, 204));
        jLabel15.setText("Şarkı Listesi");
        sarkiListePanel.add(jLabel15);
        jLabel15.setBounds(30, 10, 850, 60);

        jButton11.setBackground(new java.awt.Color(60, 60, 60));
        jButton11.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jButton11.setForeground(new java.awt.Color(0, 0, 0));
        jButton11.setText("TÜMÜNÜ EKLE");
        jButton11.setBorder(null);
        jButton11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton11ActionPerformed(evt);
            }
        });
        sarkiListePanel.add(jButton11);
        jButton11.setBounds(1230, 250, 310, 90);

        sarkiScrollPane.setBackground(new java.awt.Color(102, 102, 102));
        sarkiScrollPane.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(42, 107, 149), 5), "", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 36), new java.awt.Color(204, 204, 204))); // NOI18N
        sarkiScrollPane.setForeground(new java.awt.Color(102, 102, 102));
        sarkiScrollPane.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N

        sarkiTablosu.setBackground(new java.awt.Color(51, 51, 51));
        sarkiTablosu.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 24))); // NOI18N
        sarkiTablosu.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        sarkiTablosu.setForeground(new java.awt.Color(204, 204, 204));
        sarkiTablosu.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Şarkı ID", "Şarkı Adı", "Şarkı Tarihi", "Şarkı Tür ID", "Süre", "Sanatçı_Şarkı ID", "Sanatçı", "Albüm", "Dinlenme Sayısı"
            }
        ));
        sarkiTablosu.setDoubleBuffered(true);
        sarkiTablosu.setRowHeight(30);
        sarkiTablosu.setSelectionBackground(new java.awt.Color(42, 107, 149));
        sarkiTablosu.setSelectionForeground(new java.awt.Color(204, 204, 204));
        sarkiTablosu.setShowGrid(true);
        sarkiTablosu.setShowVerticalLines(false);
        sarkiScrollPane.setViewportView(sarkiTablosu);

        sarkiListePanel.add(sarkiScrollPane);
        sarkiScrollPane.setBounds(20, 80, 1180, 610);

        s8.setBackground(new java.awt.Color(204, 204, 204));
        s8.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        s8.setForeground(new java.awt.Color(204, 204, 204));
        s8.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(42, 107, 149), 4), "Sanatçı_Şarkı ID", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 18), new java.awt.Color(204, 204, 204))); // NOI18N
        sarkiListePanel.add(s8);
        s8.setBounds(1230, 350, 310, 90);

        jButton13.setBackground(new java.awt.Color(60, 60, 60));
        jButton13.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jButton13.setForeground(new java.awt.Color(0, 0, 0));
        jButton13.setText("ANASAYFAYA DÖN");
        jButton13.setBorder(null);
        jButton13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton13ActionPerformed(evt);
            }
        });
        sarkiListePanel.add(jButton13);
        jButton13.setBounds(1230, 590, 310, 90);

        jPanel1.add(sarkiListePanel);
        sarkiListePanel.setBounds(340, 240, 1570, 850);

        jPanel4.setBackground(new java.awt.Color(42, 107, 149));
        jPanel4.setLayout(null);

        jLabel12.setBackground(new java.awt.Color(51, 51, 51));
        jLabel12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resimler/profil.png"))); // NOI18N
        jLabel12.setText("jLabel12");
        jPanel4.add(jLabel12);
        jLabel12.setBounds(50, 10, 210, 180);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 30)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 0));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Kullanıcı Adı");
        jLabel1.setToolTipText("");
        jLabel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(42, 107, 149), 5));
        jPanel4.add(jLabel1);
        jLabel1.setBounds(-20, 140, 400, 140);

        jToggleButton1.setBackground(new java.awt.Color(51, 51, 51));
        jToggleButton1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jToggleButton1.setForeground(new java.awt.Color(0, 0, 0));
        jToggleButton1.setText("Takip Et");
        jToggleButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButton1ActionPerformed(evt);
            }
        });
        jPanel4.add(jToggleButton1);
        jToggleButton1.setBounds(1570, 80, 320, 100);

        jLabel2.setFont(new java.awt.Font("Lucida Handwriting", 3, 220)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 0, 0));
        jLabel2.setText("Premium");
        jPanel4.add(jLabel2);
        jLabel2.setBounds(310, 10, 1240, 210);

        jPanel1.add(jPanel4);
        jPanel4.setBounds(0, 0, 1910, 240);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 1908, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 1037, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    String sarkiId2, sarkiAdi2,  idsanatcisarki2,sanatciAdi2,albumAdi2,dinlenmeSayisi2,sanatci_sarki_id2,sarkiTarih2, sarkiTurId2, sarkiSure2, calmaSanatciAdi2, calmaAlbumAdi2, calmaDinlenme2, calmasanatcisarkiid2;
   
    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        try {

            String idAl2;
            idAl2 = sarkiIdGir1.getText();
            Connection myConn = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/muzik_dosyam", "root", "Ok.20002000");
            Statement myStat = (Statement) myConn.createStatement();
            String sorgu = "SELECT SanatciSarki_Id FROM sanatci_sarki WHERE  SanatciSarki_Id in(SELECT SanatciSarki_Id FROM calma_listesi_sarkilar WHERE CalmaListesi_Id in (SELECT CalmaListesi_Id FROM calma_listesi,kullanici_takip WHERE CalmaLİstesi_Id="+idAl2+" && kullanici_takip.TakipEdilenKullanicilar_Id=calma_listesi.Kullanici_Id && TakipEdilenKullanicilar_Id="+kullaniciSayfa.gidilecekprofil+ "))";
            ResultSet rs = myStat.executeQuery(sorgu);//sql sorgusu

            Statement myStat2 = (Statement) myConn.createStatement();
            ResultSet rs2 = myStat2.executeQuery("SELECT calma_listesi.Adi FROM calma_listesi,kullanici,kullanici_takip WHERE CalmaLİstesi_Id=" + idAl2 + " && kullanici_takip.TakipEdilenKullanicilar_Id=calma_listesi.Kullanici_Id && TakipEdilenKullanicilar_Id=" + kullaniciSayfa.gidilecekprofil );
            Statement myStat3 = (Statement) myConn.createStatement();
            Statement myStat4 = (Statement) myConn.createStatement();
            Statement myStat5 = (Statement) myConn.createStatement();
            Statement myStat6 = (Statement) myConn.createStatement();
            String baslik2=null;
            while (rs2.next()) {
                 baslik2 = rs2.getString("Adi");
            }

            jLabel15.setText(baslik2);

            DefaultTableModel tblModel = (DefaultTableModel) sarkiTablosu.getModel();//tablomuz
            tblModel.setRowCount(0);

              while (rs.next()) {
                idsanatcisarki2=String.valueOf(rs.getInt("SanatciSarki_Id"));
                  System.out.println(idsanatcisarki2+"\n");
                  ResultSet rs5 = myStat5.executeQuery("SELECT * FROM sarki WHERE Sarki_Id in (SELECT Sarki_Id FROM sanatci_sarki WHERE SanatciSarki_Id="+idsanatcisarki2+")");//sql sorgusu
                
                  while(rs5.next()){
                sarkiId2= String.valueOf(rs5.getInt("Sarki_Id"));//sql deki kolon isimleri
                sarkiAdi2 = rs5.getString("Sarki_Adi");
                sarkiTarih2 = rs5.getString("Tarih");
                sarkiTurId2 = String.valueOf(rs5.getInt("SarkiTur_Id"));
                sarkiSure2 = String.valueOf(rs5.getDouble("Sure"));}

                ResultSet rs7;

                rs7 = myStat6.executeQuery("SELECT Sanatci_Adi FROM sanatci Where Sanatci_Id in (SELECT Sanatci_Id FROM sanatci_sarki WHERE SanatciSarki_Id=" + idsanatcisarki2 + ")");//sql sorgusu;
                while (rs7.next()) {
                    sanatciAdi2 = rs7.getString("Sanatci_Adi");
                }

                ResultSet rs3;
                rs3 = myStat3.executeQuery("SELECT Album_Adi FROM album Where Album_Id in (SELECT Album_Id FROM album_sarkilar WHERE SanatciSarki_Id in (SELECT SanatciSarki_Id FROM sanatci_sarki WHERE SanatciSarki_Id=" + idsanatcisarki2 + "))");
                while (rs3.next()) {
                    albumAdi2 = rs3.getString("Album_Adi");

                }

                ResultSet rs4;

                rs4 = myStat4.executeQuery("SELECT Dinlenme_Sayisi,SanatciSarki_Id FROM sanatci_sarki WHERE SanatciSarki_Id=" +idsanatcisarki2);//sql sorgusu;
                while (rs4.next()) {
                    dinlenmeSayisi2 = rs4.getString("Dinlenme_Sayisi");
                    sanatci_sarki_id2 = String.valueOf(rs4.getInt("SanatciSarki_Id"));
                }

                String tbData[] = {sarkiId2, sarkiAdi2, sarkiTarih2, sarkiTurId2, sarkiSure2, sanatci_sarki_id2, sanatciAdi2, albumAdi2, dinlenmeSayisi2};//tablo içinn dizi oluşturdu

                tblModel.addRow(tbData);//tabloya oluşturduğu diziyi verdi
            }

        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(KayitEkrani.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }


    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        try {
            calmaListesineSarkiEkle();
        } catch (SQLException ex) {
            Logger.getLogger(PremiumKullanici.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton7ActionPerformed
      String idAl, baslik = null;
    String sarkiId, sarkiAdi,  idsanatcisarki,sanatciAdi,albumAdi,dinlenmeSayisi,sanatci_sarki_id,sarkiTarih, sarkiTurId, sarkiSure, calmaSanatciAdi, calmaAlbumAdi, calmaDinlenme, calmasanatcisarkiid;
   
    private void jButton11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton11ActionPerformed
        idAl = sarkiIdGir1.getText();
        int calmlistesid = 0;
        Connection connect = null;
        DbHelper db = new DbHelper();
        PreparedStatement statement = null;
        try {

            Connection myConn = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/muzik_dosyam", "root", "Ok.20002000");
            Statement myStat = (Statement) myConn.createStatement();
            Statement myStat1 = (Statement) myConn.createStatement();
            Statement myStat2 = (Statement) myConn.createStatement();
            Statement myStat3 = (Statement) myConn.createStatement();
            Statement myStat4 = (Statement) myConn.createStatement();
            Statement myStat5 = (Statement) myConn.createStatement();
            String sorgu = "SELECT SanatciSarki_Id FROM calma_listesi_sarkilar WHERE CalmaListesi_Id=" + idAl;
            ResultSet rs = myStat.executeQuery(sorgu);
            String calmaListesiId = "SELECT CalmaListesi_Id FROM calma_listesi WHERE  Adi=" + "\"" + jComboBox1.getSelectedItem() + "\";";
            ResultSet rs1 = myStat1.executeQuery(calmaListesiId);
            ResultSet rs2 = myStat2.executeQuery("SELECT SarkiTur_Id FROM calma_listesi WHERE CalmaListesi_Id=" + idAl);
            ResultSet rs3 = myStat3.executeQuery("SELECT SarkiTur_Id FROM calma_listesi WHERE Adi=" + "\"" + jComboBox1.getSelectedItem() + "\"");

            ResultSet rs4 = myStat4.executeQuery("SELECT SanatciSarki_Id FROM calma_listesi_sarkilar WHERE CalmaListesi_Id in(SELECT CalmaListesi_Id FROM calma_listesi WHERE Adi=" + "\"" + jComboBox1.getSelectedItem() + "\"" + ")");
            ResultSet rs5 = myStat5.executeQuery(sorgu);
            int idsarki = 0;
            while (rs5.next()) {
                idsarki = rs5.getInt("SanatciSarki_Id");

            }
            int idcalma;
            int kontrol = 0;
            while (rs4.next()) {
                idcalma = rs4.getInt("SanatciSarki_Id");
                if (idcalma == idsarki) {
                    kontrol = 1;
                }
            }

            String calmaId = null;
            while (rs3.next()) {
                calmaId = rs3.getString("SarkiTur_Id");
            }
            String sarkiId = null;
            while (rs2.next()) {
                sarkiId = rs2.getString("SarkiTur_Id");
            }
            while (rs1.next()) {
                calmlistesid = rs1.getInt("CalmaListesi_Id");
            }
           
                try { 
                    connect = db.getConnection();
                    String sql = "insert into muzik_dosyam.calma_listesi_sarkilar(calmaListesi_Id,SanatciSarki_Id)" + "values(?,?)";
                    statement = connect.prepareStatement(sql);
                     if (calmaId.contains(sarkiId)) {
                        if (kontrol == 0) {
                            while (rs.next()) {
                       
                            statement.setInt(1, calmlistesid);
                            statement.setInt(2, rs.getInt("SanatciSarki_Id"));
                            statement.executeUpdate();
                           
                        } JOptionPane.showMessageDialog(null, "Kayıt başarılı");} else {
                            JOptionPane.showMessageDialog(null, "Şarkı bu listede zaten var.Tekrar eklenmiyor");
                        }
                     } else {
                        JOptionPane.showMessageDialog(null, "Çalma listelerinin türleri farklı. şarkılar eklenemiyor");
                    }
                } catch (SQLException exception) {
                    db.ShowError(exception);
                } finally {
                    statement.close();

                }
            

        } catch (SQLException exception) {
            db.ShowError(exception);
        }


    }//GEN-LAST:event_jButton11ActionPerformed

    private void jToggleButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButton1ActionPerformed
        if (jToggleButton1.isSelected()) {
            if (takipkontrol == 0) {
                jToggleButton1.setText("Takip Ediliyor");
                jTable2.setVisible(true);
                sarkiTablosu.setVisible(true);
                jComboBox1.setVisible(true);
                try {
                    KullaniciTakipEkle();
                } catch (SQLException ex) {
                    Logger.getLogger(PremiumKullanici.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                try {
                    KullaniciTakipCikar();
                } catch (SQLException ex) {
                    Logger.getLogger(PremiumKullanici.class.getName()).log(Level.SEVERE, null, ex);
                }
                jToggleButton1.setText("Takip Et");
                jTable2.setVisible(false);
                sarkiTablosu.setVisible(false);
                jComboBox1.setVisible(false);
            }
        } else {
            if (takipkontrol == 0) {

                jToggleButton1.setText("Takip Et");
                jTable2.setVisible(false);
                sarkiTablosu.setVisible(false);
                jComboBox1.setVisible(false);
                try {
                    KullaniciTakipCikar();
                } catch (SQLException ex) {
                    Logger.getLogger(PremiumKullanici.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                jToggleButton1.setText("Takip Ediliyor");
                jTable2.setVisible(true);
                sarkiTablosu.setVisible(true);
                jComboBox1.setVisible(true);
                try {
                    KullaniciTakipEkle();
                } catch (SQLException ex) {
                    Logger.getLogger(PremiumKullanici.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            System.out.println("merhaba takip etmiyorummyorum");
        }

    }//GEN-LAST:event_jToggleButton1ActionPerformed

    private void sarkiIdGir1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sarkiIdGir1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_sarkiIdGir1ActionPerformed

    private void ssanatciIdGirrActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ssanatciIdGirrActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ssanatciIdGirrActionPerformed

    private void jButton13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton13ActionPerformed
        this.setVisible(false);
        kullaniciSayfa kullanici = new kullaniciSayfa();
        kullanici.setVisible(true);
    }//GEN-LAST:event_jButton13ActionPerformed

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
            java.util.logging.Logger.getLogger(PremiumKullanici.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PremiumKullanici.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PremiumKullanici.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PremiumKullanici.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new PremiumKullanici().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton13;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton7;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JTable jTable2;
    private javax.swing.JToggleButton jToggleButton1;
    private javax.swing.JLabel s3;
    private javax.swing.JLabel s8;
    private javax.swing.JTextField sarkiIdGir1;
    private javax.swing.JPanel sarkiListePanel;
    private javax.swing.JScrollPane sarkiScrollPane;
    private javax.swing.JButton sarkiSilButon;
    private javax.swing.JTable sarkiTablosu;
    private javax.swing.JTextField ssanatciIdGirr;
    // End of variables declaration//GEN-END:variables
}
