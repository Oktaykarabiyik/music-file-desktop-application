/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proje3;

import com.mysql.jdbc.Connection;
import java.awt.event.ActionEvent;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;

public class kullaniciSayfa extends javax.swing.JFrame {

    static int gidilecekprofil;
    JComboBox jComboBox1 = new JComboBox();
 int listekontrol=0;
    public kullaniciSayfa() {
        initComponents();
       

        jPanel5.setVisible(false);
        jLabel13.setVisible(false);
        jLabel14.setVisible(false);
        jButton8.setVisible(false);

        jComboBox1.setBackground(new java.awt.Color(51, 51, 51));

        jComboBox1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N

        jComboBox1.setForeground(new java.awt.Color(0, 0, 0));

//jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboBox1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(42, 107, 149), 5));

        sarkiListePanel.add(jComboBox1);
        jComboBox1.setBounds(30, 540, 220, 50);
        SarkiTurbox();
        calmaListesiGoster();
        Ulkebox();
        premiumKullaniciCalmaListele();
        TakipEdilenKullanicilarGoster();
        PremiumListebox();
        TakipEdilenlerListebox();
         calmaListeBox();
    }
    String sarkiId;
    String sarkiAdi;
    String sarkiTarih;
    String sarkiTurId;
    String sarkiSure;
    String sarkiSuresi;
    String sarkiDinlenme;
    String calmaListesiId;
    String calmaListesiAdi;
    String calmaListesiTur;
    String premiumListe;
    String premiumListe1;
    String takipedilenid;
    String takipedilenad;
public void calmaListeBox()
{
        try {
            Connection myConn = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/muzik_dosyam", "root", "Ok.20002000");
            Statement myStat = (Statement) myConn.createStatement();
            ResultSet rs = myStat.executeQuery("SELECT Adi FROM calma_listesi WHERE Kullanici_Id="+Menu.kullanicialma);
              Statement myStat2 = (Statement) myConn.createStatement();
            ResultSet rs2 = myStat2.executeQuery("SELECT Kullanici_Adi FROM kullanici WHERE Kullanici_Id="+Menu.kullanicialma);
          while (rs2.next()) {
             
               jLabel1.setText(rs2.getString("Kullanici_Adi"));
            }  while (rs.next()) {
                jComboBox1.addItem(rs.getString("Adi"));
              
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(KayitEkrani.class.getName()).log(Level.SEVERE, null, ex);
        }
}
    private void SarkiTurbox() {
        try {
            Connection myConn = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/muzik_dosyam", "root", "Ok.20002000");
            Statement myStat = (Statement) myConn.createStatement();
            ResultSet rs = myStat.executeQuery("SELECT * FROM tur");
            while (rs.next()) {
                sarkiTurSeccbox.addItem(rs.getString("Tur"));

            }
        } catch (SQLException ex) {
            Logger.getLogger(KayitEkrani.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void Ulkebox() {
        try {
            Connection myConn = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/muzik_dosyam", "root", "Ok.20002000");
            Statement myStat = (Statement) myConn.createStatement();
            ResultSet rs = myStat.executeQuery("SELECT Ulke_Adi FROM ulkeler");
            while (rs.next()) {
                ulkeSeccbox1.addItem(rs.getString("Ulke_Adi"));

            }
        } catch (SQLException ex) {
            Logger.getLogger(KayitEkrani.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void calmaListesiEkle() throws SQLException {
        Connection connect = null;
        DbHelper db = new DbHelper();
        PreparedStatement statement = null;
        try {
            connect = db.getConnection();
            String sql = "insert into muzik_dosyam.calma_listesi(Kullanici_Id,SarkiTur_Id,Adi)" + "values(?,?,?)";
            statement = connect.prepareStatement(sql);

            statement.setInt(1, Menu.kullanicialma);
            statement.setInt(2, sarkiTurSeccbox.getSelectedIndex() + 1);
            statement.setString(3, sarkiIdGir.getText());

            statement.executeUpdate();
            JOptionPane.showMessageDialog(null, "Kayıt başarılı");
        } catch (SQLException exception) {
            db.ShowError(exception);
        } finally {
            statement.close();

        }
    }

    public void calmaListesiGoster() {
        try {

            Connection myConn = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/muzik_dosyam", "root", "Ok.20002000");
            Statement myStat = (Statement) myConn.createStatement();

            ResultSet rs = myStat.executeQuery("SELECT * FROM calma_listesi WHERE Kullanici_Id= " + Menu.kullanicialma);//sql sorgus
            System.out.println(Menu.kullanicialma);
            while (rs.next()) {
                calmaListesiId = String.valueOf(rs.getInt("CalmaListesi_Id"));//sql deki kolon isimleri
                calmaListesiTur = String.valueOf(rs.getInt("SarkiTur_Id"));
                calmaListesiAdi = String.valueOf(rs.getString("Adi"));
                String tbData[] = {calmaListesiId, calmaListesiAdi, calmaListesiTur};//tablo içinn dizi oluşturdu
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
           
            String calmaListesiId = "SELECT CalmaListesi_Id FROM calma_listesi WHERE  Adi="+"\"" + jComboBox1.getSelectedItem()+"\""  ;
            ResultSet rs1 = myStat.executeQuery(calmaListesiId);
          
            ResultSet rs2 = myStat2.executeQuery("SELECT SarkiTur_Id FROM sarki WHERE Sarki_Id in ( SELECT Sarki_Id FROM sanatci_sarki WHERE SanatciSarki_Id="+sanatciSarkiIDGir.getText()+")") ;
            ResultSet rs3 = myStat3.executeQuery("SELECT SarkiTur_Id FROM calma_listesi WHERE Adi="+"\"" +jComboBox1.getSelectedItem()+"\""  );
            ResultSet rs4 = myStat4.executeQuery("SELECT SanatciSarki_Id FROM calma_listesi_sarkilar WHERE CalmaListesi_Id in(SELECT CalmaListesi_Id FROM calma_listesi WHERE Adi="+"\"" + jComboBox1.getSelectedItem()+"\""+")"  );
            int idsarki=Integer.valueOf(sanatciSarkiIDGir.getText());
            int idcalma;
            int kontrol=0;
            while(rs4.next())
            {
                idcalma=rs4.getInt("SanatciSarki_Id");
                if(idcalma==idsarki)
                {
                    kontrol=1;
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
            if (calmaId.contains(sarkiId) ) {
                if(kontrol==0){
                while (rs1.next()) {
                    statement.setInt(1, (rs1.getInt("CalmaListesi_Id")));
                }
                 statement.setInt(2, Integer.valueOf(sanatciSarkiIDGir.getText()));
               

                statement.executeUpdate();
                JOptionPane.showMessageDialog(null, "Kayıt başarılı");
                }
                else
                {
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
    String idsanatcisarki;
     public void CalmaListesindekiSarkilariGoster()
    {
        try {

            String idAl;
            idAl = sarkiIdGir1.getText();
            Connection myConn = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/muzik_dosyam", "root", "Ok.20002000");
            Statement myStat = (Statement) myConn.createStatement();
            String sorgu = "SELECT SanatciSarki_Id FROM calma_listesi_sarkilar WHERE CalmaListesi_Id =" + idAl;
            ResultSet rs = myStat.executeQuery(sorgu);//sql sorgusu

            Statement myStat2 = (Statement) myConn.createStatement();
            ResultSet rs2 = myStat2.executeQuery("SELECT Adi FROM calma_listesi WHERE CalmaListesi_Id=" + idAl);
            Statement myStat3 = (Statement) myConn.createStatement();
            Statement myStat4 = (Statement) myConn.createStatement();
            Statement myStat5 = (Statement) myConn.createStatement();
            Statement myStat6 = (Statement) myConn.createStatement();
            while (rs2.next()) {
                baslik2 = rs2.getString("Adi");
            }

            jLabel15.setText(baslik2);

            DefaultTableModel tblModel = (DefaultTableModel) sarkiTablosu.getModel();//tablomuz
            tblModel.setRowCount(0);

              while (rs.next()) {
                idsanatcisarki=String.valueOf(rs.getInt("SanatciSarki_Id"));
                  ResultSet rs5 = myStat5.executeQuery("SELECT * FROM sarki WHERE Sarki_Id in (SELECT Sarki_Id FROM sanatci_sarki WHERE SanatciSarki_Id="+idsanatcisarki+")");//sql sorgusu
                
                  while(rs5.next()){
                sarkiId = String.valueOf(rs5.getInt("Sarki_Id"));//sql deki kolon isimleri
                sarkiAdi = rs5.getString("Sarki_Adi");
                sarkiTarih = rs5.getString("Tarih");
                sarkiTurId = String.valueOf(rs5.getInt("SarkiTur_Id"));
                sarkiSure = String.valueOf(rs5.getDouble("Sure"));}

                ResultSet rs7;

                rs7 = myStat6.executeQuery("SELECT Sanatci_Adi FROM sanatci Where Sanatci_Id in (SELECT Sanatci_Id FROM sanatci_sarki WHERE SanatciSarki_Id=" + idsanatcisarki + ")");//sql sorgusu;
                while (rs7.next()) {
                    sanatciAdi = rs7.getString("Sanatci_Adi");
                }

                ResultSet rs3;
                rs3 = myStat3.executeQuery("SELECT Album_Adi FROM album Where Album_Id in (SELECT Album_Id FROM album_sarkilar WHERE SanatciSarki_Id in (SELECT SanatciSarki_Id FROM sanatci_sarki WHERE SanatciSarki_Id=" + idsanatcisarki + "))");
                while (rs3.next()) {
                    albumAdi = rs3.getString("Album_Adi");

                }

                ResultSet rs4;

                rs4 = myStat4.executeQuery("SELECT Dinlenme_Sayisi,SanatciSarki_Id FROM sanatci_sarki WHERE SanatciSarki_Id=" +idsanatcisarki);//sql sorgusu;
                while (rs4.next()) {
                    dinlenmeSayisi = rs4.getString("Dinlenme_Sayisi");
                    sanatci_sarki_id = String.valueOf(rs4.getInt("SanatciSarki_Id"));
                }

                String tbData[] = {sarkiId, sarkiAdi, sarkiTarih, sarkiTurId, sarkiSure, sanatci_sarki_id, sanatciAdi, albumAdi, dinlenmeSayisi};//tablo içinn dizi oluşturdu

                tblModel.addRow(tbData);//tabloya oluşturduğu diziyi verdi
            }

        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(KayitEkrani.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

    }

String sanatcisarki;
    public void popTopGoster() {
        try {
            jLabel15.setText("POP TOP 10");
            Connection myConn = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/muzik_dosyam", "root", "Ok.20002000");
            Statement myStat = (Statement) myConn.createStatement();
            Statement myStat2 = (Statement) myConn.createStatement();
            Statement myStat3 = (Statement) myConn.createStatement();
            Statement myStat4 = (Statement) myConn.createStatement();
            ResultSet rs = myStat.executeQuery("SELECT sarki.Sarki_Id,sarki.Sarki_Adi, sarki.Tarih,sarki.SarkiTur_Id,sarki.Sure,sanatci.Sanatci_Adi ,sanatci_sarki.SanatciSarki_Id FROM sarki, sanatci_sarki,sanatci  WHERE sanatci.Sanatci_Id=sanatci_sarki.Sanatci_Id && sarki.Sarki_Id=sanatci_sarki.Sarki_Id && SarkiTur_Id=1 ORDER BY sanatci_sarki.Dinlenme_Sayisi DESC  limit 10");//sql sorgusu

            DefaultTableModel tblModel = (DefaultTableModel) sarkiTablosu.getModel();//tablomuz
            tblModel.setRowCount(0);
            while (rs.next()) {
                sarkiId = String.valueOf(rs.getInt("Sarki_Id"));//sql deki kolon isimleri
                sanatcisarki=String.valueOf(rs.getInt("SanatciSarki_Id"));
               
                sarkiAdi = rs.getString("Sarki_Adi");
                sarkiTarih = rs.getString("Tarih");
                sarkiTurId = String.valueOf(rs.getInt("SarkiTur_Id"));
                sarkiSure = String.valueOf(rs.getDouble("Sure"));

                ResultSet rs2;

                rs2 = myStat2.executeQuery("SELECT Sanatci_Adi FROM sanatci Where Sanatci_Id in (SELECT Sanatci_Id FROM sanatci_sarki WHERE SanatciSarki_Id=" + sanatcisarki+ ")");//sql sorgusu;
                while (rs2.next()) {
                    sanatciAdi = rs2.getString("Sanatci_Adi");
                }

                ResultSet rs3;
                rs3 = myStat3.executeQuery("SELECT Album_Adi FROM album Where Album_Id in (SELECT Album_Id FROM album_sarkilar WHERE SanatciSarki_Id in (SELECT SanatciSarki_Id FROM sanatci_sarki WHERE SanatciSarki_Id=" + sanatcisarki + "))");
                while (rs3.next()) {
                    albumAdi = rs3.getString("Album_Adi");

                }

                 ResultSet rs4;

                rs4 = myStat4.executeQuery("SELECT Dinlenme_Sayisi,SanatciSarki_Id FROM sanatci_sarki WHERE SanatciSarki_Id=" + sanatcisarki);//sql sorgusu;
                while (rs4.next()) {
                    dinlenmeSayisi = rs4.getString("Dinlenme_Sayisi");
                    sanatci_sarki_id = String.valueOf(rs4.getInt("SanatciSarki_Id"));
                }

                String tbData[] = {sarkiId, sarkiAdi, sarkiTarih, sarkiTurId, sarkiSure, sanatci_sarki_id, sanatciAdi, albumAdi, dinlenmeSayisi};//tablo içinn dizi oluşturdu
                tblModel.addRow(tbData);//tabloya oluşturduğu diziyi verdi
            }

        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(KayitEkrani.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
    }

    public void jazzTopGoster() {
      try {
            jLabel15.setText("JAZZ TOP 10");
            Connection myConn = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/muzik_dosyam", "root", "Ok.20002000");
            Statement myStat = (Statement) myConn.createStatement();
            Statement myStat2 = (Statement) myConn.createStatement();
            Statement myStat3 = (Statement) myConn.createStatement();
            Statement myStat4 = (Statement) myConn.createStatement();
            ResultSet rs = myStat.executeQuery("SELECT sarki.Sarki_Id,sarki.Sarki_Adi, sarki.Tarih,sarki.SarkiTur_Id,sarki.Sure,sanatci.Sanatci_Adi ,sanatci_sarki.SanatciSarki_Id FROM sarki, sanatci_sarki,sanatci  WHERE sanatci.Sanatci_Id=sanatci_sarki.Sanatci_Id && sarki.Sarki_Id=sanatci_sarki.Sarki_Id && SarkiTur_Id=2 ORDER BY sanatci_sarki.Dinlenme_Sayisi DESC  limit 10");//sql sorgusu

            DefaultTableModel tblModel = (DefaultTableModel) sarkiTablosu.getModel();//tablomuz
            tblModel.setRowCount(0);
            while (rs.next()) {
                sarkiId = String.valueOf(rs.getInt("Sarki_Id"));//sql deki kolon isimleri
                sanatcisarki=String.valueOf(rs.getInt("SanatciSarki_Id"));
               
                sarkiAdi = rs.getString("Sarki_Adi");
                sarkiTarih = rs.getString("Tarih");
                sarkiTurId = String.valueOf(rs.getInt("SarkiTur_Id"));
                sarkiSure = String.valueOf(rs.getDouble("Sure"));

                ResultSet rs2;

                rs2 = myStat2.executeQuery("SELECT Sanatci_Adi FROM sanatci Where Sanatci_Id in (SELECT Sanatci_Id FROM sanatci_sarki WHERE SanatciSarki_Id=" + sanatcisarki+ ")");//sql sorgusu;
                while (rs2.next()) {
                    sanatciAdi = rs2.getString("Sanatci_Adi");
                }

                ResultSet rs3;
                rs3 = myStat3.executeQuery("SELECT Album_Adi FROM album Where Album_Id in (SELECT Album_Id FROM album_sarkilar WHERE SanatciSarki_Id in (SELECT SanatciSarki_Id FROM sanatci_sarki WHERE SanatciSarki_Id=" + sanatcisarki + "))");
                while (rs3.next()) {
                    albumAdi = rs3.getString("Album_Adi");

                }

                 ResultSet rs4;

                rs4 = myStat4.executeQuery("SELECT Dinlenme_Sayisi,SanatciSarki_Id FROM sanatci_sarki WHERE SanatciSarki_Id=" + sanatcisarki);//sql sorgusu;
                while (rs4.next()) {
                    dinlenmeSayisi = rs4.getString("Dinlenme_Sayisi");
                    sanatci_sarki_id = String.valueOf(rs4.getInt("SanatciSarki_Id"));
                }

                String tbData[] = {sarkiId, sarkiAdi, sarkiTarih, sarkiTurId, sarkiSure, sanatci_sarki_id, sanatciAdi, albumAdi, dinlenmeSayisi};//tablo içinn dizi oluşturdu
                tblModel.addRow(tbData);//tabloya oluşturduğu diziyi verdi
            }

        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(KayitEkrani.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
    }

    public void klasikTopGoster() {
        try {
            jLabel15.setText("KLASİK TOP 10");
            Connection myConn = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/muzik_dosyam", "root", "Ok.20002000");
            Statement myStat = (Statement) myConn.createStatement();
            Statement myStat2 = (Statement) myConn.createStatement();
            Statement myStat3 = (Statement) myConn.createStatement();
            Statement myStat4 = (Statement) myConn.createStatement();
            ResultSet rs = myStat.executeQuery("SELECT sarki.Sarki_Id,sarki.Sarki_Adi, sarki.Tarih,sarki.SarkiTur_Id,sarki.Sure,sanatci.Sanatci_Adi ,sanatci_sarki.SanatciSarki_Id FROM sarki, sanatci_sarki,sanatci  WHERE sanatci.Sanatci_Id=sanatci_sarki.Sanatci_Id && sarki.Sarki_Id=sanatci_sarki.Sarki_Id && SarkiTur_Id=3 ORDER BY sanatci_sarki.Dinlenme_Sayisi DESC  limit 10");//sql sorgusu

            DefaultTableModel tblModel = (DefaultTableModel) sarkiTablosu.getModel();//tablomuz
            tblModel.setRowCount(0);
            while (rs.next()) {
                sarkiId = String.valueOf(rs.getInt("Sarki_Id"));//sql deki kolon isimleri
                sanatcisarki=String.valueOf(rs.getInt("SanatciSarki_Id"));
               
                sarkiAdi = rs.getString("Sarki_Adi");
                sarkiTarih = rs.getString("Tarih");
                sarkiTurId = String.valueOf(rs.getInt("SarkiTur_Id"));
                sarkiSure = String.valueOf(rs.getDouble("Sure"));

                ResultSet rs2;

                rs2 = myStat2.executeQuery("SELECT Sanatci_Adi FROM sanatci Where Sanatci_Id in (SELECT Sanatci_Id FROM sanatci_sarki WHERE SanatciSarki_Id=" + sanatcisarki+ ")");//sql sorgusu;
                while (rs2.next()) {
                    sanatciAdi = rs2.getString("Sanatci_Adi");
                }

                ResultSet rs3;
                rs3 = myStat3.executeQuery("SELECT Album_Adi FROM album Where Album_Id in (SELECT Album_Id FROM album_sarkilar WHERE SanatciSarki_Id in (SELECT SanatciSarki_Id FROM sanatci_sarki WHERE SanatciSarki_Id=" + sanatcisarki + "))");
                while (rs3.next()) {
                    albumAdi = rs3.getString("Album_Adi");

                }

                 ResultSet rs4;

                rs4 = myStat4.executeQuery("SELECT Dinlenme_Sayisi,SanatciSarki_Id FROM sanatci_sarki WHERE SanatciSarki_Id=" + sanatcisarki);//sql sorgusu;
                while (rs4.next()) {
                    dinlenmeSayisi = rs4.getString("Dinlenme_Sayisi");
                    sanatci_sarki_id = String.valueOf(rs4.getInt("SanatciSarki_Id"));
                }

                String tbData[] = {sarkiId, sarkiAdi, sarkiTarih, sarkiTurId, sarkiSure, sanatci_sarki_id, sanatciAdi, albumAdi, dinlenmeSayisi};//tablo içinn dizi oluşturdu
                tblModel.addRow(tbData);//tabloya oluşturduğu diziyi verdi
            }

        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(KayitEkrani.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
    }

    public void genelTopGoster() {
         try {
            jLabel15.setText("GENEL TOP 10");
            Connection myConn = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/muzik_dosyam", "root", "Ok.20002000");
            Statement myStat = (Statement) myConn.createStatement();
            Statement myStat2 = (Statement) myConn.createStatement();
            Statement myStat3 = (Statement) myConn.createStatement();
            Statement myStat4 = (Statement) myConn.createStatement();
            ResultSet rs = myStat.executeQuery("SELECT sarki.Sarki_Id,sarki.Sarki_Adi, sarki.Tarih,sarki.SarkiTur_Id,sarki.Sure,sanatci.Sanatci_Adi ,sanatci_sarki.SanatciSarki_Id FROM sarki, sanatci_sarki,sanatci  WHERE sanatci.Sanatci_Id=sanatci_sarki.Sanatci_Id && sarki.Sarki_Id=sanatci_sarki.Sarki_Id ORDER BY sanatci_sarki.Dinlenme_Sayisi DESC  limit 10");//sql sorgusu

            DefaultTableModel tblModel = (DefaultTableModel) sarkiTablosu.getModel();//tablomuz
            tblModel.setRowCount(0);
            while (rs.next()) {
                sarkiId = String.valueOf(rs.getInt("Sarki_Id"));//sql deki kolon isimleri
                sanatcisarki=String.valueOf(rs.getInt("SanatciSarki_Id"));
               
                sarkiAdi = rs.getString("Sarki_Adi");
                sarkiTarih = rs.getString("Tarih");
                sarkiTurId = String.valueOf(rs.getInt("SarkiTur_Id"));
                sarkiSure = String.valueOf(rs.getDouble("Sure"));

                ResultSet rs2;

                rs2 = myStat2.executeQuery("SELECT Sanatci_Adi FROM sanatci Where Sanatci_Id in (SELECT Sanatci_Id FROM sanatci_sarki WHERE SanatciSarki_Id=" + sanatcisarki+ ")");//sql sorgusu;
                while (rs2.next()) {
                    sanatciAdi = rs2.getString("Sanatci_Adi");
                }

                ResultSet rs3;
                rs3 = myStat3.executeQuery("SELECT Album_Adi FROM album Where Album_Id in (SELECT Album_Id FROM album_sarkilar WHERE SanatciSarki_Id in (SELECT SanatciSarki_Id FROM sanatci_sarki WHERE SanatciSarki_Id=" + sanatcisarki + "))");
                while (rs3.next()) {
                    albumAdi = rs3.getString("Album_Adi");

                }

                 ResultSet rs4;

                rs4 = myStat4.executeQuery("SELECT Dinlenme_Sayisi,SanatciSarki_Id FROM sanatci_sarki WHERE SanatciSarki_Id=" + sanatcisarki);//sql sorgusu;
                while (rs4.next()) {
                    dinlenmeSayisi = rs4.getString("Dinlenme_Sayisi");
                    sanatci_sarki_id = String.valueOf(rs4.getInt("SanatciSarki_Id"));
                }

                String tbData[] = {sarkiId, sarkiAdi, sarkiTarih, sarkiTurId, sarkiSure, sanatci_sarki_id, sanatciAdi, albumAdi, dinlenmeSayisi};//tablo içinn dizi oluşturdu
                tblModel.addRow(tbData);//tabloya oluşturduğu diziyi verdi
            }

        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(KayitEkrani.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
    }

    public void ulkeTopGoster() {
        int ulkeIdAl = 0;
        try {
            jLabel15.setText(ulkeSeccbox1.getSelectedItem() + " TOP 10");
            Connection myConn = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/muzik_dosyam", "root", "Ok.200020003");
            Statement myStat = (Statement) myConn.createStatement();
            Statement myStat2 = (Statement) myConn.createStatement();
            Statement myStat3 = (Statement) myConn.createStatement();
            Statement myStat4 = (Statement) myConn.createStatement();
            Statement myStat5 = (Statement) myConn.createStatement();
            ResultSet rs5 = myStat.executeQuery("SELECT Ulke_Id FROM ulkeler WHERE Ulke_Adi=" + "\"" + ulkeSeccbox1.getSelectedItem() + "\"");
            while (rs5.next()) {
                ulkeIdAl = rs5.getInt("Ulke_Id");
            }
            ResultSet rs = myStat.executeQuery("SELECT sanatci_sarki.SanatciSarki_Id,sarki.Sarki_Id,sarki.Sarki_Adi, sarki.Tarih,sarki.SarkiTur_Id,sarki.Sure,sanatci.Sanatci_Adi FROM sarki, sanatci_sarki,sanatci  WHERE sanatci.Sanatci_Id=sanatci_sarki.Sanatci_Id && sarki.Sarki_Id=sanatci_sarki.Sarki_Id && sanatci.Ulke_Id=" + ulkeIdAl + " ORDER BY sanatci_sarki.Dinlenme_Sayisi DESC  limit 10");//sql sorgusu

            DefaultTableModel tblModel = (DefaultTableModel) sarkiTablosu.getModel();//tablomuz
            tblModel.setRowCount(0);
            while (rs.next()) {
                sarkiId = String.valueOf(rs.getInt("Sarki_Id"));//sql deki kolon isimleri
                sanatcisarki=String.valueOf(rs.getInt("SanatciSarki_Id"));
                sarkiAdi = rs.getString("Sarki_Adi");
                sarkiTarih = rs.getString("Tarih");
                sarkiTurId = String.valueOf(rs.getInt("SarkiTur_Id"));
                sarkiSure = String.valueOf(rs.getDouble("Sure"));

                ResultSet rs2;

                rs2 = myStat2.executeQuery("SELECT Sanatci_Adi FROM sanatci Where Sanatci_Id in (SELECT Sanatci_Id FROM sanatci_sarki WHERE SanatciSarki_Id=" + sanatcisarki + ")");//sql sorgusu;
                while (rs2.next()) {
                    sanatciAdi = rs2.getString("Sanatci_Adi");
                }

                ResultSet rs3;
                rs3 = myStat3.executeQuery("SELECT Album_Adi FROM album Where Album_Id in (SELECT Album_Id FROM album_sarkilar WHERE SanatciSarki_Id in (SELECT SanatciSarki_Id FROM sanatci_sarki WHERE SanatciSarki_Id=" + sanatcisarki  + "))");
                while (rs3.next()) {
                    albumAdi = rs3.getString("Album_Adi");

                }

                 ResultSet rs4;

                rs4 = myStat4.executeQuery("SELECT Dinlenme_Sayisi,SanatciSarki_Id FROM sanatci_sarki WHERE SanatciSarki_Id=" + sanatcisarki );//sql sorgusu;
                while (rs4.next()) {
                    dinlenmeSayisi = rs4.getString("Dinlenme_Sayisi");
                    sanatci_sarki_id = String.valueOf(rs4.getInt("SanatciSarki_Id"));
                }

                String tbData[] = {sarkiId, sarkiAdi, sarkiTarih, sarkiTurId, sarkiSure, sanatci_sarki_id, sanatciAdi, albumAdi, dinlenmeSayisi};//tablo içinn dizi oluşturdu
                tblModel.addRow(tbData);//tabloya oluşturduğu diziyi verdi
            }

        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(KayitEkrani.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
    }
    String sanatciAdi, albumAdi, sarkisanatciID, dinlenmeSayisi,sanatci_sarki_id,sanatci_sarkiid,sanatcisarkiidal;

    public void sarkiGoster() throws SQLException {
        try {

            Connection myConn = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/muzik_dosyam", "root", "Ok.20002000");
            Statement myStat = (Statement) myConn.createStatement();
            Statement myStat2 = (Statement) myConn.createStatement();
            Statement myStat3 = (Statement) myConn.createStatement();
            Statement myStat4 = (Statement) myConn.createStatement();
            ResultSet rs = myStat.executeQuery("SELECT SanatciSarki_Id FROM sanatci_sarki ");//sql sorgusu
             Statement myStat5 = (Statement) myConn.createStatement();
           
            DefaultTableModel tblModel = (DefaultTableModel) sarkiTablosu.getModel();//tablomuz
            tblModel.setRowCount(0);
            while (rs.next()) {
                sanatci_sarkiid=String.valueOf(rs.getInt("SanatciSarki_Id"));
                  ResultSet rs5 = myStat5.executeQuery("SELECT * FROM sarki WHERE Sarki_Id in(SELECT Sarki_Id FROM sanatci_sarki WHERE SanatciSarki_Id="+sanatci_sarkiid+")");//sql sorgusu
                
                  while(rs5.next()){
                sarkiId = String.valueOf(rs5.getInt("Sarki_Id"));//sql deki kolon isimleri
                sarkiAdi = rs5.getString("Sarki_Adi");
                sarkiTarih = rs5.getString("Tarih");
                sarkiTurId = String.valueOf(rs5.getInt("SarkiTur_Id"));
                sarkiSure = String.valueOf(rs5.getDouble("Sure"));}

                ResultSet rs2;

                rs2 = myStat2.executeQuery("SELECT Sanatci_Adi FROM sanatci Where Sanatci_Id in (SELECT Sanatci_Id FROM sanatci_sarki WHERE SanatciSarki_Id=" + sanatci_sarkiid + ")");//sql sorgusu;
                while (rs2.next()) {
                    sanatciAdi = rs2.getString("Sanatci_Adi");
                }

                ResultSet rs3;
                rs3 = myStat3.executeQuery("SELECT Album_Adi FROM album Where Album_Id in (SELECT Album_Id FROM album_sarkilar WHERE SanatciSarki_Id in (SELECT SanatciSarki_Id FROM sanatci_sarki WHERE SanatciSarki_Id=" + sanatci_sarkiid + "))");
                while (rs3.next()) {
                    albumAdi = rs3.getString("Album_Adi");

                }

                ResultSet rs4;

                rs4 = myStat4.executeQuery("SELECT Dinlenme_Sayisi,SanatciSarki_Id FROM sanatci_sarki WHERE SanatciSarki_Id=" + sanatci_sarkiid);//sql sorgusu;
                while (rs4.next()) {
                    dinlenmeSayisi = rs4.getString("Dinlenme_Sayisi");
                    sanatci_sarki_id = String.valueOf(rs4.getInt("SanatciSarki_Id"));
                }

                String tbData[] = {sarkiId, sarkiAdi, sarkiTarih, sarkiTurId, sarkiSure, sanatci_sarki_id, sanatciAdi, albumAdi, dinlenmeSayisi};//tablo içinn dizi oluşturdu

                tblModel.addRow(tbData);//tabloya oluşturduğu diziyi verdi
            }

        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(KayitEkrani.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
    }

    public void premiumKullaniciCalmaListele() {
        try {

            Connection myConn = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/muzik_dosyam", "root", "Ok.20002000");
            Statement myStat = (Statement) myConn.createStatement();
            ResultSet rs = myStat.executeQuery("SELECT Kullanici_Id,Kullanici_Adi FROM kullanici WHERE Abonelik_Id=2");//sql sorgusu

            while (rs.next()) {
                premiumListe1 = String.valueOf(rs.getInt("Kullanici_Id"));
                premiumListe = rs.getString("Kullanici_Adi");
                String tbData[] = {premiumListe1, premiumListe};
                DefaultTableModel tblModel = (DefaultTableModel) jTable1.getModel();
                tblModel.addRow(tbData);
            }

        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(KayitEkrani.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
    }

    public void TakipEdilenKullanicilarGoster() {
        try {

            Connection myConn = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/muzik_dosyam", "root", "Ok.20002000");
            Statement myStat = (Statement) myConn.createStatement();
            ResultSet rs = myStat.executeQuery("SELECT  kullanici.Kullanici_Id ,kullanici.Kullanici_Adi FROM kullanici,kullanici_takip WHERE kullanici.Kullanici_Id=kullanici_takip.TakipEdilenKullanicilar_Id && TakipEdenKullanicilar_Id=" + Menu.kullanicialma);//sql sorgusu

            while (rs.next()) {
                takipedilenid = String.valueOf(rs.getInt("Kullanici_Id"));
                takipedilenad = rs.getString("Kullanici_Adi");
                String tbData[] = {takipedilenid, takipedilenad};
                DefaultTableModel tblModel = (DefaultTableModel) jTable3.getModel();
                tblModel.addRow(tbData);
            }

        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(KayitEkrani.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
    }
      public void SarkiDinlenmeArtir() throws SQLException {
         float dinlenmesayisi=0;
         try {

            Connection myConn = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/muzik_dosyam", "root", "Ok.20002000");
            Statement myStat = (Statement) myConn.createStatement();
            ResultSet rs = myStat.executeQuery("SELECT Dinlenme_Sayisi FROM sanatci_Sarki WHERE SanatciSarki_Id =" + sanatciSarkiIDGir.getText());//sql sorgusu

            while (rs.next()) {
                dinlenmesayisi = rs.getInt("Dinlenme_Sayisi");
            }

        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(KayitEkrani.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
         
         Connection connect = null;
        DbHelper db = new DbHelper();
        PreparedStatement statement = null;
        try {
            connect = db.getConnection();
            String sql = "UPDATE  sanatci_sarki SET Dinlenme_Sayisi=?  WHERE SanatciSarki_Id=?";
            statement = connect.prepareStatement(sql);
            statement.setInt(2, Integer.valueOf(sanatciSarkiIDGir.getText()));
            statement.setFloat(1, dinlenmesayisi+1);
            statement.executeUpdate();
        } catch (SQLException exception) {
            db.ShowError(exception);
        } finally {
            statement.close();
            
        }
    }
     public void PremiumListebox()
     {
          try {
                      Connection myConn=(Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/muzik_dosyam","root","Ok.20002000");
                      Statement myStat=(Statement)myConn.createStatement();
                      ResultSet rs=myStat.executeQuery("SELECT * FROM kullanici WHERE Abonelik_Id=2");
                      while(rs.next())
                      {
                          sarkiTurSeccbox2.addItem(rs.getString("Kullanici_Adi"));
                          
                      }
        } catch (SQLException ex) {
        Logger.getLogger(KayitEkrani.class.getName()).log(Level.SEVERE, null, ex);
    }
     }
     public void TakipEdilenlerListebox()
     {
        try {
                      Connection myConn=(Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/muzik_dosyam","root","Ok.20002000");
                      Statement myStat=(Statement)myConn.createStatement();
                      ResultSet rs=myStat.executeQuery("SELECT kullanici.Kullanici_Adi FROM kullanici,kullanici_takip WHERE kullanici.Kullanici_Id=kullanici_takip.TakipEdilenKullanicilar_Id && TakipEdenKullanicilar_Id=" + Menu.kullanicialma);
                      while(rs.next())
                      {
                          sarkiTurSeccbox1.addItem(rs.getString("Kullanici_Adi"));
                          
                      }
        } catch (SQLException ex) {
        Logger.getLogger(KayitEkrani.class.getName()).log(Level.SEVERE, null, ex);
    } 
     }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jButton8 = new javax.swing.JButton();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jSeparator3 = new javax.swing.JSeparator();
        jButton3 = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable3 = new javax.swing.JTable();
        jButton6 = new javax.swing.JButton();
        sarkiTurSeccbox1 = new javax.swing.JComboBox<>();
        s5 = new javax.swing.JLabel();
        s6 = new javax.swing.JLabel();
        sarkiTurSeccbox2 = new javax.swing.JComboBox<>();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator2 = new javax.swing.JSeparator();
        sarkiIdGir = new javax.swing.JTextField();
        s1 = new javax.swing.JLabel();
        s2 = new javax.swing.JLabel();
        jButton10 = new javax.swing.JButton();
        sarkiTurSeccbox = new javax.swing.JComboBox<>();
        sarkiIdGir1 = new javax.swing.JTextField();
        s3 = new javax.swing.JLabel();
        sarkiListePanel = new javax.swing.JPanel();
        sarkiScrollPane = new javax.swing.JScrollPane();
        sarkiTablosu = new javax.swing.JTable();
        sarkiSilButon = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jLabel15 = new javax.swing.JLabel();
        jButton9 = new javax.swing.JButton();
        sanatciSarkiIDGir = new javax.swing.JTextField();
        s8 = new javax.swing.JLabel();
        jButton4 = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        ulkeSeccbox1 = new javax.swing.JComboBox<>();
        popTopButon = new javax.swing.JButton();
        jazzTopButon = new javax.swing.JButton();
        KlasikTopButon = new javax.swing.JButton();
        GenelTopButon = new javax.swing.JButton();
        jButton14 = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(43, 42, 42));
        jPanel1.setLayout(null);

        jPanel5.setBackground(new java.awt.Color(28, 28, 28));
        jPanel5.setLayout(null);

        jButton8.setBackground(new java.awt.Color(0, 0, 0));
        jButton8.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jButton8.setForeground(new java.awt.Color(204, 204, 204));
        jButton8.setText("KAPAT");
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });
        jPanel5.add(jButton8);
        jButton8.setBounds(1390, 10, 111, 71);

        jLabel13.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(204, 204, 204));
        jLabel13.setText("ŞARKI ADI");
        jPanel5.add(jLabel13);
        jLabel13.setBounds(40, 20, 243, 55);

        jLabel14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resimler/muzikDinle.png"))); // NOI18N
        jLabel14.setText("jLabel14");
        jPanel5.add(jLabel14);
        jLabel14.setBounds(310, 0, 970, 110);

        jPanel1.add(jPanel5);
        jPanel5.setBounds(340, 930, 1560, 100);

        jPanel2.setBackground(new java.awt.Color(0, 0, 0));
        jPanel2.setLayout(null);

        jScrollPane1.setBackground(new java.awt.Color(51, 51, 51));
        jScrollPane1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(42, 107, 149), 5), " Premium Kullanıcılar", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 18), new java.awt.Color(204, 204, 204))); // NOI18N

        jTable1.setBackground(new java.awt.Color(51, 51, 51));
        jTable1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 18), new java.awt.Color(204, 204, 204))); // NOI18N
        jTable1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Kullanıcı ID", "Kullanıcı Adı"
            }
        ));
        jTable1.setRowHeight(30);
        jScrollPane1.setViewportView(jTable1);

        jPanel2.add(jScrollPane1);
        jScrollPane1.setBounds(10, 530, 320, 290);
        jPanel2.add(jSeparator3);
        jSeparator3.setBounds(10, 490, 330, 10);

        jButton3.setBackground(new java.awt.Color(51, 51, 51));
        jButton3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton3.setForeground(new java.awt.Color(0, 0, 0));
        jButton3.setText("Kullanıcının Profiline Git");
        jButton3.setBorder(null);
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton3);
        jButton3.setBounds(160, 850, 160, 60);

        jScrollPane3.setBackground(new java.awt.Color(51, 51, 51));
        jScrollPane3.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(42, 107, 149), 5), "Takip Edilen Kullanıcılar", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 18), new java.awt.Color(204, 204, 204))); // NOI18N

        jTable3.setBackground(new java.awt.Color(51, 51, 51));
        jTable3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 18), new java.awt.Color(204, 204, 204))); // NOI18N
        jTable3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jTable3.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Kullanıcı ID", "Kullanıcı Adı"
            }
        ));
        jTable3.setRowHeight(30);
        jScrollPane3.setViewportView(jTable3);

        jPanel2.add(jScrollPane3);
        jScrollPane3.setBounds(10, 30, 320, 330);

        jButton6.setBackground(new java.awt.Color(51, 51, 51));
        jButton6.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton6.setForeground(new java.awt.Color(0, 0, 0));
        jButton6.setText("Kullanıcının Profiline Git");
        jButton6.setBorder(null);
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton6);
        jButton6.setBounds(170, 390, 160, 60);

        sarkiTurSeccbox1.setBackground(new java.awt.Color(51, 51, 51));
        sarkiTurSeccbox1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        sarkiTurSeccbox1.setForeground(new java.awt.Color(204, 204, 204));
        sarkiTurSeccbox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sarkiTurSeccbox1ActionPerformed(evt);
            }
        });
        jPanel2.add(sarkiTurSeccbox1);
        sarkiTurSeccbox1.setBounds(10, 400, 140, 40);

        s5.setBackground(new java.awt.Color(204, 204, 204));
        s5.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        s5.setForeground(new java.awt.Color(204, 204, 204));
        s5.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(42, 107, 149), 4), "Kullanıcı Adı", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 18), new java.awt.Color(204, 204, 204))); // NOI18N
        jPanel2.add(s5);
        s5.setBounds(0, 830, 150, 80);

        s6.setBackground(new java.awt.Color(204, 204, 204));
        s6.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        s6.setForeground(new java.awt.Color(204, 204, 204));
        s6.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(42, 107, 149), 4), "Kullanıcı Adı", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 18), new java.awt.Color(204, 204, 204))); // NOI18N
        jPanel2.add(s6);
        s6.setBounds(0, 370, 160, 80);

        sarkiTurSeccbox2.setBackground(new java.awt.Color(51, 51, 51));
        sarkiTurSeccbox2.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        sarkiTurSeccbox2.setForeground(new java.awt.Color(204, 204, 204));
        sarkiTurSeccbox2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sarkiTurSeccbox2ActionPerformed(evt);
            }
        });
        jPanel2.add(sarkiTurSeccbox2);
        sarkiTurSeccbox2.setBounds(10, 860, 130, 40);

        jPanel1.add(jPanel2);
        jPanel2.setBounds(1570, 0, 340, 1040);

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
                "ID", "Adı", "Türü"
            }
        ));
        jTable2.setRowHeight(30);
        jScrollPane2.setViewportView(jTable2);

        jPanel3.add(jScrollPane2);
        jScrollPane2.setBounds(30, 300, 300, 230);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(204, 204, 204));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Kullanıcı Adı");
        jLabel1.setToolTipText("");
        jLabel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(42, 107, 149), 5));
        jPanel3.add(jLabel1);
        jLabel1.setBounds(40, 220, 260, 50);

        jLabel12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resimler/profil.png"))); // NOI18N
        jLabel12.setText("jLabel12");
        jPanel3.add(jLabel12);
        jLabel12.setBounds(70, 20, 210, 180);

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
        jButton2.setBounds(110, 560, 220, 50);

        jButton1.setBackground(new java.awt.Color(51, 51, 51));
        jButton1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jButton1.setForeground(new java.awt.Color(0, 0, 0));
        jButton1.setText("Çalma Listesi Ekle");
        jButton1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 51, 255), 0));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel3.add(jButton1);
        jButton1.setBounds(30, 920, 300, 40);

        jSeparator1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jSeparator1.setMinimumSize(new java.awt.Dimension(300, 20));
        jPanel3.add(jSeparator1);
        jSeparator1.setBounds(20, 280, 320, 10);
        jPanel3.add(jSeparator2);
        jSeparator2.setBounds(20, 680, 320, 30);

        sarkiIdGir.setBackground(new java.awt.Color(51, 51, 51));
        sarkiIdGir.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        sarkiIdGir.setForeground(new java.awt.Color(204, 204, 204));
        sarkiIdGir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sarkiIdGirActionPerformed(evt);
            }
        });
        jPanel3.add(sarkiIdGir);
        sarkiIdGir.setBounds(40, 730, 280, 40);

        s1.setBackground(new java.awt.Color(204, 204, 204));
        s1.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        s1.setForeground(new java.awt.Color(204, 204, 204));
        s1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(42, 107, 149), 4), "Çalma Listesi Adı", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 18), new java.awt.Color(204, 204, 204))); // NOI18N
        jPanel3.add(s1);
        s1.setBounds(30, 700, 300, 80);

        s2.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        s2.setForeground(new java.awt.Color(204, 204, 204));
        s2.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(42, 107, 149), 4), "Çalma Listesi Türü", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 18), new java.awt.Color(204, 204, 204))); // NOI18N
        jPanel3.add(s2);
        s2.setBounds(30, 810, 300, 80);

        jButton10.setBackground(new java.awt.Color(51, 51, 51));
        jButton10.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jButton10.setForeground(new java.awt.Color(0, 0, 0));
        jButton10.setText("Tüm Şarkıları Görüntüle");
        jButton10.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 51, 255), 0));
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });
        jPanel3.add(jButton10);
        jButton10.setBounds(30, 620, 300, 40);

        sarkiTurSeccbox.setBackground(new java.awt.Color(51, 51, 51));
        sarkiTurSeccbox.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        sarkiTurSeccbox.setForeground(new java.awt.Color(204, 204, 204));
        sarkiTurSeccbox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sarkiTurSeccboxActionPerformed(evt);
            }
        });
        jPanel3.add(sarkiTurSeccbox);
        sarkiTurSeccbox.setBounds(40, 840, 280, 40);

        sarkiIdGir1.setBackground(new java.awt.Color(51, 51, 51));
        sarkiIdGir1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        sarkiIdGir1.setForeground(new java.awt.Color(204, 204, 204));
        sarkiIdGir1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sarkiIdGir1ActionPerformed(evt);
            }
        });
        jPanel3.add(sarkiIdGir1);
        sarkiIdGir1.setBounds(40, 560, 50, 40);

        s3.setBackground(new java.awt.Color(204, 204, 204));
        s3.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        s3.setForeground(new java.awt.Color(204, 204, 204));
        s3.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(42, 107, 149), 4), "ID", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 18), new java.awt.Color(204, 204, 204))); // NOI18N
        jPanel3.add(s3);
        s3.setBounds(30, 530, 70, 80);

        jPanel1.add(jPanel3);
        jPanel3.setBounds(-10, 0, 350, 1040);

        sarkiListePanel.setBackground(new java.awt.Color(51, 51, 51));
        sarkiListePanel.setForeground(new java.awt.Color(51, 51, 51));
        sarkiListePanel.setLayout(null);

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
        sarkiScrollPane.setBounds(20, 80, 1180, 430);

        sarkiSilButon.setBackground(new java.awt.Color(255, 204, 0));
        sarkiSilButon.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        sarkiSilButon.setForeground(new java.awt.Color(51, 51, 51));
        sarkiSilButon.setText("SİL");
        sarkiListePanel.add(sarkiSilButon);
        sarkiSilButon.setBounds(80, 800, 1040, 60);

        jButton7.setBackground(new java.awt.Color(60, 60, 60));
        jButton7.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jButton7.setForeground(new java.awt.Color(0, 0, 0));
        jButton7.setText("Çalma Listesine Ekle");
        jButton7.setBorder(null);
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });
        sarkiListePanel.add(jButton7);
        jButton7.setBounds(730, 540, 450, 60);

        jLabel15.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(204, 204, 204));
        jLabel15.setText("Şarkı Listesi");
        sarkiListePanel.add(jLabel15);
        jLabel15.setBounds(30, 10, 850, 60);

        jButton9.setBackground(new java.awt.Color(60, 60, 60));
        jButton9.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jButton9.setForeground(new java.awt.Color(0, 0, 0));
        jButton9.setText("DİNLE");
        jButton9.setBorder(null);
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });
        sarkiListePanel.add(jButton9);
        jButton9.setBounds(730, 620, 460, 50);

        sanatciSarkiIDGir.setBackground(new java.awt.Color(51, 51, 51));
        sanatciSarkiIDGir.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        sanatciSarkiIDGir.setForeground(new java.awt.Color(204, 204, 204));
        sanatciSarkiIDGir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sanatciSarkiIDGirActionPerformed(evt);
            }
        });
        sarkiListePanel.add(sanatciSarkiIDGir);
        sanatciSarkiIDGir.setBounds(360, 570, 300, 50);

        s8.setBackground(new java.awt.Color(204, 204, 204));
        s8.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        s8.setForeground(new java.awt.Color(204, 204, 204));
        s8.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(42, 107, 149), 4), "Sanatçı_Şarkı ID", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 18), new java.awt.Color(204, 204, 204))); // NOI18N
        sarkiListePanel.add(s8);
        s8.setBounds(350, 540, 320, 90);

        jButton4.setBackground(new java.awt.Color(60, 60, 60));
        jButton4.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jButton4.setForeground(new java.awt.Color(0, 0, 0));
        jButton4.setText("MENÜYE DÖN");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        sarkiListePanel.add(jButton4);
        jButton4.setBounds(30, 620, 260, 50);

        jPanel1.add(sarkiListePanel);
        sarkiListePanel.setBounds(340, 240, 1230, 800);

        jPanel4.setBackground(new java.awt.Color(42, 107, 149));
        jPanel4.setLayout(null);

        ulkeSeccbox1.setBackground(new java.awt.Color(51, 51, 51));
        ulkeSeccbox1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        ulkeSeccbox1.setForeground(new java.awt.Color(204, 204, 204));
        ulkeSeccbox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ulkeSeccbox1ActionPerformed(evt);
            }
        });
        jPanel4.add(ulkeSeccbox1);
        ulkeSeccbox1.setBounds(1020, 160, 170, 31);

        popTopButon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resimler/top1.jpg"))); // NOI18N
        popTopButon.setText("jButton4");
        popTopButon.setSelected(true);
        popTopButon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                popTopButonActionPerformed(evt);
            }
        });
        jPanel4.add(popTopButon);
        popTopButon.setBounds(20, 30, 170, 150);

        jazzTopButon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resimler/top2.jpg"))); // NOI18N
        jazzTopButon.setText("jButton11");
        jazzTopButon.setFocusable(false);
        jazzTopButon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jazzTopButonActionPerformed(evt);
            }
        });
        jPanel4.add(jazzTopButon);
        jazzTopButon.setBounds(280, 30, 170, 150);

        KlasikTopButon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resimler/top3.jpg"))); // NOI18N
        KlasikTopButon.setText("jButton12");
        KlasikTopButon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                KlasikTopButonActionPerformed(evt);
            }
        });
        jPanel4.add(KlasikTopButon);
        KlasikTopButon.setBounds(530, 30, 170, 150);

        GenelTopButon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resimler/top4.jpg"))); // NOI18N
        GenelTopButon.setText("jButton13");
        GenelTopButon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                GenelTopButonActionPerformed(evt);
            }
        });
        jPanel4.add(GenelTopButon);
        GenelTopButon.setBounds(780, 30, 170, 150);

        jButton14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resimler/top5.jpg"))); // NOI18N
        jButton14.setText("jButton14");
        jButton14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton14ActionPerformed(evt);
            }
        });
        jPanel4.add(jButton14);
        jButton14.setBounds(1020, 30, 170, 120);

        jLabel9.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 5), "KLASİK  TOP 10", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.BOTTOM, new java.awt.Font("Segoe UI", 1, 18), new java.awt.Color(0, 0, 0))); // NOI18N
        jPanel4.add(jLabel9);
        jLabel9.setBounds(520, 10, 190, 210);

        jLabel7.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 5), "GENEL TOP 10", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.BOTTOM, new java.awt.Font("Segoe UI", 1, 18), new java.awt.Color(0, 0, 0))); // NOI18N
        jPanel4.add(jLabel7);
        jLabel7.setBounds(770, 10, 190, 215);

        jLabel8.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 5), "ÜLKELER TOP 10", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.BOTTOM, new java.awt.Font("Segoe UI", 1, 18), new java.awt.Color(0, 0, 0))); // NOI18N
        jPanel4.add(jLabel8);
        jLabel8.setBounds(1010, 10, 190, 215);

        jLabel10.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 5), "JAZZ TOP 10", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.BOTTOM, new java.awt.Font("Segoe UI", 1, 18), new java.awt.Color(0, 0, 0))); // NOI18N
        jPanel4.add(jLabel10);
        jLabel10.setBounds(270, 10, 190, 210);

        jLabel11.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 5), " POP  TOP 10 ", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.BOTTOM, new java.awt.Font("Segoe UI", 1, 18), new java.awt.Color(0, 0, 0))); // NOI18N
        jPanel4.add(jLabel11);
        jLabel11.setBounds(10, 10, 190, 215);

        jPanel1.add(jPanel4);
        jPanel4.setBounds(340, 0, 1230, 240);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 1916, Short.MAX_VALUE)
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

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
   int premiumklnnicid=0;
        try {
                Connection myConn = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/muzik_dosyam", "root", "Ok.20002000");
                Statement myStat = (Statement) myConn.createStatement();
              ResultSet rs = myStat.executeQuery("SELECT Kullanici_Id FROM kullanici WHERE Kullanici_Adi="+"\""+sarkiTurSeccbox2.getSelectedItem()+"\"");
               
                  while(rs.next())
                {
                    premiumklnnicid=rs.getInt("Kullanici_Id");
                }
   }    catch (SQLException ex) {
            Logger.getLogger(kullaniciSayfa.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        System.out.println(premiumklnnicid);
        this.setVisible(false);
        gidilecekprofil = premiumklnnicid;
        PremiumKullanici premium = new PremiumKullanici();
        premium.setVisible(true);
    }//GEN-LAST:event_jButton3ActionPerformed
    String baslik2, calmaSanatciAdi, calmaAlbumAdi, calmaDinlenme,calmasanatcisarkiid;
    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed

    CalmaListesindekiSarkilariGoster();

    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
         int takipedlnid=0;
        try {
                Connection myConn = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/muzik_dosyam", "root", "Ok.20002000");
                Statement myStat = (Statement) myConn.createStatement();
              ResultSet rs = myStat.executeQuery("SELECT Kullanici_Id FROM kullanici WHERE Kullanici_Adi="+"\""+sarkiTurSeccbox1.getSelectedItem()+"\"");
               
                  while(rs.next())
                {
                    takipedlnid=rs.getInt("Kullanici_Id");
                }
   }    catch (SQLException ex) {
            Logger.getLogger(kullaniciSayfa.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        this.setVisible(false);
        gidilecekprofil = takipedlnid;
        PremiumKullanici premium = new PremiumKullanici();
        premium.setVisible(true);
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
try {
            calmaListesiEkle();
             
        } catch (SQLException ex) {
            Logger.getLogger(kullaniciSayfa.class.getName()).log(Level.SEVERE, null, ex);
        }
           
             try {

          
          Connection myConn = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/muzik_dosyam", "root", "Ok.20002000");
            Statement myStat = (Statement) myConn.createStatement();
 
            ResultSet rs = myStat.executeQuery("SELECT * FROM calma_listesi WHERE Kullanici_Id= "+Menu.kullanicialma);//sql sorgus
            Statement myStat2 = (Statement) myConn.createStatement();
 
            ResultSet rs2 = myStat2.executeQuery("SELECT SarkiTur_Id FROM tur WHERE Tur= "+"\""+sarkiTurSeccbox.getSelectedItem()+"\"");//sql sorgus
             System.out.println(Menu.kullanicialma);
             String sarkiturid = null;
            while(rs2.next())
            {
                sarkiturid= String.valueOf(rs2.getInt("SarkiTur_Id"));
            }
            while (rs.next()) {
               calmaListesiId = String.valueOf(rs.getInt("CalmaListesi_Id"));//sql deki kolon isimleri
               calmaListesiTur=sarkiturid;
                calmaListesiAdi=String.valueOf(rs.getString("Adi"));
                
            }
                String tbData[] = {calmaListesiId,calmaListesiAdi,calmaListesiTur};//tablo içinn dizi oluşturdu
                DefaultTableModel tblModel = (DefaultTableModel) jTable2.getModel();//tablomuz
                tblModel.addRow(tbData);//tabloya oluşturduğu diziyi verdi

        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(KayitEkrani.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
          
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        try {
            calmaListesineSarkiEkle();
        } catch (SQLException ex) {
            Logger.getLogger(kullaniciSayfa.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        jPanel5.setVisible(false);
        jLabel13.setVisible(false);
        jLabel14.setVisible(false);
        jButton8.setVisible(false);
    }//GEN-LAST:event_jButton8ActionPerformed

    private void sarkiIdGirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sarkiIdGirActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_sarkiIdGirActionPerformed

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
       jPanel5.setVisible(true);
        jLabel13.setVisible(true);
        jLabel14.setVisible(true);
        jButton8.setVisible(true);
       try {

            Connection myConn = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/muzik_dosyam", "root", "Ok.20002000");
            Statement myStat = (Statement) myConn.createStatement();
            ResultSet rs = myStat.executeQuery("SELECT Sarki_Adi FROM sarki WHERE Sarki_Id in (SELECT Sarki_Id FROM sanatci_sarki WHERE SanatciSarki_Id="+sanatciSarkiIDGir.getText()+")");//sql sorgusu

            while (rs.next()) {

                jLabel13.setText(rs.getString("Sarki_Adi"));
            }

        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(KayitEkrani.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        try {
            SarkiDinlenmeArtir();
            if(listekontrol==1)
            {
                sarkiGoster();
            }
            else if(listekontrol==2)
            {
           CalmaListesindekiSarkilariGoster();
            }
            else if(listekontrol==3)
            {
                 popTopGoster();
            }
            else if(listekontrol==4)
            {
                jazzTopGoster();
            }
            else if(listekontrol==5)
            {
                klasikTopGoster();
            }
            else if(listekontrol==6)
            {
                 genelTopGoster();
            }
            else if(listekontrol==7)
            {
                ulkeTopGoster();
            }
            
            
        } catch (SQLException ex) {
            Logger.getLogger(kullaniciSayfa.class.getName()).log(Level.SEVERE, null, ex);
        }

                    

    }//GEN-LAST:event_jButton9ActionPerformed

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
listekontrol=1;
        jLabel15.setText("TÜM ŞARKILAR");
        try {
            sarkiGoster();
        } catch (SQLException ex) {
            Logger.getLogger(kullaniciSayfa.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton10ActionPerformed

    private void sarkiTurSeccboxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sarkiTurSeccboxActionPerformed

    }//GEN-LAST:event_sarkiTurSeccboxActionPerformed

    private void sarkiIdGir1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sarkiIdGir1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_sarkiIdGir1ActionPerformed

    private void popTopButonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_popTopButonActionPerformed
         listekontrol=3;
        popTopGoster();
    }//GEN-LAST:event_popTopButonActionPerformed

    private void jazzTopButonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jazzTopButonActionPerformed
        listekontrol=4;
        jazzTopGoster();
    }//GEN-LAST:event_jazzTopButonActionPerformed

    private void KlasikTopButonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_KlasikTopButonActionPerformed
        listekontrol=5;
        klasikTopGoster();
    }//GEN-LAST:event_KlasikTopButonActionPerformed

    private void GenelTopButonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_GenelTopButonActionPerformed
 listekontrol=6;
genelTopGoster();    }//GEN-LAST:event_GenelTopButonActionPerformed

    private void ulkeSeccbox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ulkeSeccbox1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ulkeSeccbox1ActionPerformed

    private void jButton14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton14ActionPerformed
        listekontrol=7;
        ulkeTopGoster();
    }//GEN-LAST:event_jButton14ActionPerformed

    private void sanatciSarkiIDGirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sanatciSarkiIDGirActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_sanatciSarkiIDGirActionPerformed

    private void sarkiTurSeccbox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sarkiTurSeccbox1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_sarkiTurSeccbox1ActionPerformed

    private void sarkiTurSeccbox2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sarkiTurSeccbox2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_sarkiTurSeccbox2ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
       this.setVisible(false);
       Menu menu =new Menu();
       menu.setVisible(true);
    }//GEN-LAST:event_jButton4ActionPerformed

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
            java.util.logging.Logger.getLogger(kullaniciSayfa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(kullaniciSayfa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(kullaniciSayfa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(kullaniciSayfa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
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
                new kullaniciSayfa().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton GenelTopButon;
    private javax.swing.JButton KlasikTopButon;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton14;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JTable jTable3;
    private javax.swing.JButton jazzTopButon;
    private javax.swing.JButton popTopButon;
    private javax.swing.JLabel s1;
    private javax.swing.JLabel s2;
    private javax.swing.JLabel s3;
    private javax.swing.JLabel s5;
    private javax.swing.JLabel s6;
    private javax.swing.JLabel s8;
    private javax.swing.JTextField sanatciSarkiIDGir;
    private javax.swing.JTextField sarkiIdGir;
    private javax.swing.JTextField sarkiIdGir1;
    private javax.swing.JPanel sarkiListePanel;
    private javax.swing.JScrollPane sarkiScrollPane;
    private javax.swing.JButton sarkiSilButon;
    private javax.swing.JTable sarkiTablosu;
    private javax.swing.JComboBox<String> sarkiTurSeccbox;
    private javax.swing.JComboBox<String> sarkiTurSeccbox1;
    private javax.swing.JComboBox<String> sarkiTurSeccbox2;
    private javax.swing.JComboBox<String> ulkeSeccbox1;
    // End of variables declaration//GEN-END:variables
}
