package proje3;

import com.mysql.jdbc.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class AdminSayfasi extends javax.swing.JFrame {

    String id;
    String ulke;
    String ad;
    String sarkiId;
    String sarkiAdi;
    String sarkiTarih;
    String sarkiTurId;
    String sarkiSure;
    String sarkiSuresi;
    String sarkiDinlenme;
    String turId;
    String tur;
    String sanatcid;
    String sanatciad;
    String sanatciulkeid;
    String sanatcisarkid;
    String sanatciId;
    String sarkId;
    String dinlenmesayisi;
    String albumid;
    String albumad;
    String albumturid;
    String albumsarkilarıd;
    String albumId;
    String sanatcisarkiId;
    String kullaniciId;
    String kullaniciadi;
    String email;
    String sifre;
    String ulkeId;
    String odendimi;
    String abonelikıd;
    String adminid;
    String adminkullaniciad;
    String adminsifre;
    String adminemail;
    String abonelikid;
    String aboneliktur;
    String calmalistesid;
    String calmakullanicid;
    String calmasarkiturid;
    String calmadi;
    String calmalistesisarkilarid;
    String calmasarkilarid;
    String calmasanatcisarkid;
    String kullanicitakipıd;
    String takipedenkullanicid;
    String takipedilenkullanicid;
    int sanatcikontrol = 0;
    int sarkikontrol=0;
    int silkontrol=0;
    int silkontrol1=0;
    int ulkekontrol=0;
    int turkontrol=0;
    int sanatcisarkikontrol=0;
    int albumkontrol=0;
    int albumsarkilarkontrol=0;
    int abonekontrol=0;
    String ulkeid;
    String ulkead;
    public AdminSayfasi() {
        initComponents();
        setTitle("Admin Ekranı");
        setSize(1920, 1080);
        SarkiTurbox();
        AlbumTurbox();
        ulkegoster();
         sarkiGoster();
         TurGoster();
         SanatciGoster();
       SanatciSarkiGoster();
      AlbumGoster();
      AlbumSarkilarGoster();
      KullanicilarGoster();
        AdminGoster();
        AbonelikTurGoster();
        CalmaListesiGoster();
        CalmaListesiSarkilarGoster();
          TakiplesmeGoster();
    }
     private void SarkiTurbox()
    {
        try {
                      Connection myConn=(Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/muzik_dosyam","root","Ok.20002000");
                      Statement myStat=(Statement)myConn.createStatement();
                      ResultSet rs=myStat.executeQuery("SELECT * FROM tur");
                      while(rs.next())
                      {
                          sarkiTurSeccbox.addItem(rs.getString("SarkiTur_ID"));
                          
                      }
        } catch (SQLException ex) {
        Logger.getLogger(KayitEkrani.class.getName()).log(Level.SEVERE, null, ex);
    }
        
    }
     public void CalmaListesiGoster()
     {
          try {

            Connection myConn = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/muzik_dosyam", "root", "Ok.20002000");
            Statement myStat = (Statement) myConn.createStatement();
            ResultSet rs = myStat.executeQuery("SELECT * FROM calma_listesi");

            while (rs.next()) {
               calmalistesid = String.valueOf(rs.getInt("CalmaListesi_Id"));
                calmakullanicid = String.valueOf(rs.getInt("Kullanici_Id"));
                calmasarkiturid = String.valueOf(rs.getInt("SarkiTur_Id")); 
                calmadi = rs.getString("Adi");
                String tbData[] = {calmalistesid,calmakullanicid,calmasarkiturid,calmadi };
                DefaultTableModel tblModel = (DefaultTableModel) calmaListesiTablosu.getModel();
                tblModel.addRow(tbData);
                
            }

        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(KayitEkrani.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } 
     }
     public void CalmaListesiSarkilarGoster()
     {
        try {

            Connection myConn = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/muzik_dosyam", "root", "Ok.20002000");
            Statement myStat = (Statement) myConn.createStatement();
            ResultSet rs = myStat.executeQuery("SELECT * FROM calma_listesi_sarkilar");

            while (rs.next()) {
               calmalistesisarkilarid = String.valueOf(rs.getInt("CalmaListesiSarkilar_Id"));
                calmasarkilarid = String.valueOf(rs.getInt("CalmaListesi_Id"));
               calmasanatcisarkid = String.valueOf(rs.getInt("SanatciSarki_Id")); 
                
                String tbData[] = {calmalistesisarkilarid,calmasarkilarid,calmasanatcisarkid};
                DefaultTableModel tblModel = (DefaultTableModel) calmaListesiSarkilarTablosu.getModel();
                tblModel.addRow(tbData);
                
                
            }

        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(KayitEkrani.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }  
     }
     public void TakiplesmeGoster()
     {
       try {

            Connection myConn = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/muzik_dosyam", "root", "Ok.20002000");
            Statement myStat = (Statement) myConn.createStatement();
            ResultSet rs = myStat.executeQuery("SELECT * FROM kullanici_takip");

            while (rs.next()) {
               kullanicitakipıd = String.valueOf(rs.getInt("KullaniciTakip_Id"));
                takipedenkullanicid = String.valueOf(rs.getInt("TakipEdenKullanicilar_Id"));
               takipedilenkullanicid = String.valueOf(rs.getInt("TakipEdilenKullanicilar_Id")); 
                
                String tbData[] = {kullanicitakipıd,takipedenkullanicid,takipedilenkullanicid};
                DefaultTableModel tblModel = (DefaultTableModel) kullaniciTakipTablosu1.getModel();
                tblModel.addRow(tbData);
                
                
            }

        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(KayitEkrani.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }     
     }
 public void Ulkekle() throws SQLException {
        Connection connect = null;
        DbHelper db = new DbHelper();
        PreparedStatement statement = null;
        try {
            connect = db.getConnection();
            String sql = "insert into muzik_dosyam.ulkeler(Ulke_Id,Ulke_Adi)" + "values(?,?)";
            statement = connect.prepareStatement(sql);
            statement.setInt(1, Integer.valueOf(ulkeIdGir.getText()));
            statement.setString(2,ulkeAdiGir.getText());
            statement.executeUpdate();
            JOptionPane.showMessageDialog(null, "Kayıt başarılı");
        } catch (SQLException exception) {
            db.ShowError(exception);
        } finally {
            statement.close();
            connect.close();
        }
    }
   public void ulkegoster()
   {
       try {

            Connection myConn = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/muzik_dosyam", "root", "Ok.20002000");
            Statement myStat = (Statement) myConn.createStatement();
            ResultSet rs = myStat.executeQuery("SELECT * FROM ulkeler");

            while (rs.next()) {
                ulkeid = String.valueOf(rs.getInt("Ulke_Id"));
                ulkead = rs.getString("Ulke_Adi");
                String tbData[] = {ulkeid, ulkead};
                DefaultTableModel tblModel = (DefaultTableModel) ulkeTablosu.getModel();
                tblModel.addRow(tbData);
            }

        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(KayitEkrani.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } 
   }
   public void ulkeSil() throws SQLException
   {
       Connection connect = null;
        DbHelper db = new DbHelper();
        PreparedStatement statement = null;
        try {
            connect = db.getConnection();
            String sql = "DELETE from ulkeler WHERE Ulke_Id=?";
            statement = connect.prepareStatement(sql);
            statement.setInt(1, Integer.valueOf(ulkeIdGir.getText()));
            statement.executeUpdate();
            JOptionPane.showMessageDialog(null, " ulke Silme Başarili");
        } catch (SQLException exception) {
            db.ShowError(exception);
        } finally {
            statement.close();
            connect.close();
        }
   }

   
    public void sarkiEkle() throws SQLException {
        Connection connect = null;
        DbHelper db = new DbHelper();
        PreparedStatement statement = null;
        try {
            connect = db.getConnection();
            String sql = "insert into muzik_dosyam.sarki(Sarki_Id,Sarki_Adi,Tarih,SarkiTur_Id,Sure)" + "values(?,?,?,?,?)";
            statement = connect.prepareStatement(sql);
            statement.setInt(1, Integer.valueOf(sarkiIdGir.getText()));
            statement.setString(2, sarkiAdiGir.getText());
            statement.setString(3, sarkiTarihGir.getText());
            statement.setInt(4, sarkiTurSeccbox.getSelectedIndex()+1);
            statement.setDouble(5, Double.valueOf(sarkiSureGir.getText()));
            statement.executeUpdate();
            JOptionPane.showMessageDialog(null, "Kayıt başarılı");
        } catch (SQLException exception) {
            db.ShowError(exception);
        } finally {
            statement.close();
            connect.close();
        }
    }
    public void sarkiGoster()
    {
      try {

            Connection myConn = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/muzik_dosyam", "root", "Ok.20002000");
            Statement myStat = (Statement) myConn.createStatement();
            ResultSet rs = myStat.executeQuery("SELECT * FROM sarki");

            while (rs.next()) {
                sarkiId = String.valueOf(rs.getInt("Sarki_Id"));
                sarkiAdi = rs.getString("Sarki_Adi");
                sarkiTarih=rs.getString("Tarih");
                sarkiTurId=String.valueOf(rs.getInt("SarkiTur_Id"));
                sarkiSure=String.valueOf(rs.getDouble("Sure"));
                String tbData[] = {sarkiId, sarkiAdi,sarkiTarih,sarkiTurId,sarkiSure};
                DefaultTableModel tblModel = (DefaultTableModel) sarkiTablosu.getModel();
                tblModel.addRow(tbData);
            }

        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(KayitEkrani.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }    
    }
    public void TurEkle() throws SQLException
    {
        Connection connect = null;
        DbHelper db = new DbHelper();
        PreparedStatement statement = null;
        try {
            connect = db.getConnection();
            String sql = "insert into muzik_dosyam.tur(SarkiTur_Id,Tur)" + "values(?,?)";
            statement = connect.prepareStatement(sql);
            statement.setInt(1, Integer.valueOf(sarkiTurIDGir.getText()));
            statement.setString(2,turGir.getText());
            statement.executeUpdate();
            JOptionPane.showMessageDialog(null, "Kayıt başarılı");
        } catch (SQLException exception) {
            db.ShowError(exception);
        } finally {
            statement.close();
            connect.close();
        }
    }
    public void TurGoster()
    {
        try {

            Connection myConn = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/muzik_dosyam", "root", "Ok.20002000");
            Statement myStat = (Statement) myConn.createStatement();
            ResultSet rs = myStat.executeQuery("SELECT * FROM tur");

            while (rs.next()) {
                turId = String.valueOf(rs.getInt("SarkiTur_Id"));
                tur = rs.getString("Tur");
                String tbData[] = {turId, tur};
                DefaultTableModel tblModel = (DefaultTableModel) turTablosu.getModel();
                tblModel.addRow(tbData);
            }

        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(KayitEkrani.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } 
    }
    public void SanatciEkle() throws SQLException
    {
         Connection connect = null;
        DbHelper db = new DbHelper();
        PreparedStatement statement = null;
        try {
            connect = db.getConnection();
            String sql = "insert into muzik_dosyam.sanatci(Sanatci_Id,Sanatci_Adi,Ulke_Id)" + "values(?,?,?)";
            statement = connect.prepareStatement(sql);
            statement.setInt(1, Integer.valueOf(sanatciSarkiSanatciIDGir.getText()));
            statement.setString(2, sarkiSanatciSanatciAdiGir.getText());
            statement.setInt(3, Integer.valueOf(sarkiSanatciUlkeIDGir.getText()));
            statement.executeUpdate();
            JOptionPane.showMessageDialog(null, "Kayıt başarılı");
        } catch (SQLException exception) {
            db.ShowError(exception);
        } finally {
            statement.close();
            connect.close();
        }
    }
    public void SanatciGoster()
    {
        try {

            Connection myConn = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/muzik_dosyam", "root", "Ok.20002000");
            Statement myStat = (Statement) myConn.createStatement();
            ResultSet rs = myStat.executeQuery("SELECT * FROM sanatci");

            while (rs.next()) {
                sanatcid = String.valueOf(rs.getInt("Sanatci_Id"));
                sanatciad = rs.getString("Sanatci_Adi");
                sanatciulkeid=String.valueOf(rs.getInt("Ulke_Id"));
                String tbData[] = {sanatcid,sanatciad,sanatciulkeid};
                DefaultTableModel tblModel = (DefaultTableModel) sanatciTablosu1.getModel();
                tblModel.addRow(tbData);
            }

        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(KayitEkrani.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } 
    }
    public void SanatciSil() throws SQLException
    {
    Connection connect = null;
        DbHelper db = new DbHelper();
        PreparedStatement statement = null;
        try {
            connect = db.getConnection();
            String sql = "DELETE from sanatci WHERE Sanatci_Id=?";
            statement = connect.prepareStatement(sql);
            statement.setInt(1, Integer.valueOf(sanatciSarkiSanatciIDGir.getText()));
            statement.executeUpdate();
            JOptionPane.showMessageDialog(null, " Sanatci Silme Başarili");
        } catch (SQLException exception) {
            db.ShowError(exception);
        } finally {
            statement.close();
            connect.close();
        }
   
    }
    public void SanatciGuncelle() throws SQLException
    {
        Connection connect = null;
        DbHelper db = new DbHelper();
        PreparedStatement statement = null;
        try {
            connect = db.getConnection();
            String sql = "UPDATE  sanatci SET Sanatci_Adi=?,Ulke_Id=? WHERE Sanatci_Id=?";
            statement = connect.prepareStatement(sql);
            statement.setInt(3, Integer.valueOf(sanatciSarkiSanatciIDGir.getText()));
            statement.setString(1, sarkiSanatciSanatciAdiGir.getText());
            statement.setInt(2,Integer.valueOf(sarkiSanatciUlkeIDGir.getText()));
            statement.executeUpdate();
            JOptionPane.showMessageDialog(null, " Sanatci Guncelleme Başarili");
        } catch (SQLException exception) {
            db.ShowError(exception);
        } finally {
            statement.close();
            connect.close();
        }
    
    }
    
    public void SanatciileSanatciSarkiSil() throws SQLException
    {
    Connection connect = null;
        DbHelper db = new DbHelper();
        PreparedStatement statement = null;
        try {
            connect = db.getConnection();
            String sql = "DELETE from sanatci_sarki WHERE Sanatci_Id=?";
            statement = connect.prepareStatement(sql);
            statement.setInt(1, Integer.valueOf(sanatciSarkiSanatciIDGir.getText()));
            statement.executeUpdate();
            JOptionPane.showMessageDialog(null, " Sanatci Silme Başarili");
        } catch (SQLException exception) {
            db.ShowError(exception);
        } finally {
            statement.close();
            connect.close();
        }
   
    }
     public void SanatciileAlbumSarkilarSil() throws SQLException
     {
            Connection connect = null;
        DbHelper db = new DbHelper();
        PreparedStatement statement = null;
        try {
            connect = db.getConnection();
            String sql = "Delete from album_sarkilar where SanatciSarki_Id in( select SanatciSarki_Id from sanatci_sarki  where Sanatci_Id in(select Sanatci_Id from sanatci  where Sanatci_Id =? )); ";
            statement = connect.prepareStatement(sql);
            statement.setInt(1, Integer.valueOf(sanatciSarkiSanatciIDGir.getText()));
            statement.executeUpdate();
            JOptionPane.showMessageDialog(null, "Silme Başarili");
        } catch (SQLException exception) {
            db.ShowError(exception);
        } finally {
            statement.close();
            connect.close();
        }
     }
     public void SanatcileCalmaListesindenSil() throws SQLException
     {
              Connection connect = null;
        DbHelper db = new DbHelper();
        PreparedStatement statement = null;
        try {
            connect = db.getConnection();
            String sql = "Delete from calma_listesi_sarkilar where SanatciSarki_Id in( select SanatciSarki_Id from sanatci_sarki  where Sanatci_Id in(select Sanatci_Id from sanatci  where Sanatci_Id =? )); ";
            statement = connect.prepareStatement(sql);
            statement.setInt(1, Integer.valueOf(sanatciSarkiSanatciIDGir.getText()));
            statement.executeUpdate();
            JOptionPane.showMessageDialog(null, "Silme Başarili");
        } catch (SQLException exception) {
            db.ShowError(exception);
        } finally {
            statement.close();
            connect.close();
        } 
     }
 public void SanatciSarkiEkle() throws SQLException
 {
     Connection connect = null;
        DbHelper db = new DbHelper();
        PreparedStatement statement = null;
        try {
            connect = db.getConnection();
            String sql = "insert into muzik_dosyam.sanatci_sarki(SanatciSarki_Id,Sanatci_Id,Sarki_Id,Dinlenme_Sayisi)" + "values(?,?,?,?)";
            statement = connect.prepareStatement(sql);
            statement.setInt(1, Integer.valueOf(sanatciSarkiIdGir.getText()));
            statement.setInt(2, Integer.valueOf(sanatciIDGir.getText()));
            statement.setInt(3, Integer.valueOf(sarkiIDGir.getText()));
            statement.setInt(4, Integer.valueOf(dinlenmeSayisiGir.getText()));
            statement.executeUpdate();
            JOptionPane.showMessageDialog(null, "Kayıt başarılı");
        } catch (SQLException exception) {
            db.ShowError(exception);
        } finally {
            statement.close();
            connect.close();
        }
 }
 public void SanatciSarkiGoster()
 {
     try {

            Connection myConn = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/muzik_dosyam", "root", "Ok.20002000");
            Statement myStat = (Statement) myConn.createStatement();
            ResultSet rs = myStat.executeQuery("SELECT * FROM sanatci_sarki");

            while (rs.next()) {
                sanatcisarkid = String.valueOf(rs.getInt("SanatciSarki_Id"));
                sanatciId = String.valueOf(rs.getInt("Sanatci_Id"));
                sarkId=String.valueOf(rs.getInt("Sarki_Id"));
                dinlenmesayisi=String.valueOf(rs.getInt("Dinlenme_Sayisi"));
                String tbData[] = {sanatcisarkid,sanatciId,sarkId,dinlenmesayisi};
                DefaultTableModel tblModel = (DefaultTableModel) sanatciTablosu.getModel();
                tblModel.addRow(tbData);
            }

        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(KayitEkrani.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } 
 }
  private void AlbumTurbox()
    {
        try {
                      Connection myConn=(Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/muzik_dosyam","root","Ok.20002000");
                      Statement myStat=(Statement)myConn.createStatement();
                      ResultSet rs=myStat.executeQuery("SELECT * FROM tur");
                      while(rs.next())
                      {
                          albumTurIDGir.addItem(rs.getString("SarkiTur_ID"));
                          
                      }
        } catch (SQLException ex) {
        Logger.getLogger(KayitEkrani.class.getName()).log(Level.SEVERE, null, ex);
    }
        
    }
  public void AlbumEkle() throws SQLException
 {
     Connection connect = null;
        DbHelper db = new DbHelper();
        PreparedStatement statement = null;
        try {
            connect = db.getConnection();
            String sql = "insert into muzik_dosyam.album(Album_Id,Album_Adi,AlbumTur_Id)" + "values(?,?,?)";
            statement = connect.prepareStatement(sql);
            statement.setInt(1, Integer.valueOf(albumIdGir.getText()));
            statement.setString(2, albumAdiGir.getText());
            statement.setInt(3, albumTurIDGir.getSelectedIndex()+1);
            statement.executeUpdate();
            JOptionPane.showMessageDialog(null, "Kayıt başarılı");
        } catch (SQLException exception) {
            db.ShowError(exception);
        } finally {
            statement.close();
            connect.close();
        }
 }
  public void AlbumGoster()
  {
      try {

            Connection myConn = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/muzik_dosyam", "root", "Ok.20002000");
            Statement myStat = (Statement) myConn.createStatement();
            ResultSet rs = myStat.executeQuery("SELECT * FROM album");

            while (rs.next()) {
                albumid = String.valueOf(rs.getInt("Album_Id"));
                albumad = rs.getString("Album_Adi");
                albumturid=String.valueOf(rs.getInt("AlbumTur_Id"));
                String tbData[] = {albumid,albumad,albumturid};
                DefaultTableModel tblModel = (DefaultTableModel) albumTablosu.getModel();
                tblModel.addRow(tbData);
            }

        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(KayitEkrani.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } 
  }
  public void AlbumSil() throws SQLException
  {
       Connection connect = null;
        DbHelper db = new DbHelper();
        PreparedStatement statement = null;
        try {
            connect = db.getConnection();
            String sql = "DELETE from album WHERE Album_Id=?";
            statement = connect.prepareStatement(sql);
            statement.setInt(1, Integer.valueOf(albumIdGir.getText()));
            statement.executeUpdate();
            JOptionPane.showMessageDialog(null, " album Silme Başarili");
        } catch (SQLException exception) {
            db.ShowError(exception);
        } finally {
            statement.close();
            connect.close();
        }
  }
  public void AlbumGuncelle() throws SQLException
  {
     
        Connection connect = null;
        DbHelper db = new DbHelper();
        PreparedStatement statement = null;
        try {
            connect = db.getConnection();
            String sql = "UPDATE  album SET Album_Adi=?,AlbumTur_Id=? WHERE Album_Id=?";
            statement = connect.prepareStatement(sql);
            statement.setInt(3, Integer.valueOf(albumIdGir.getText()));
            statement.setString(1, albumAdiGir.getText());
            statement.setInt(2, albumTurIDGir.getSelectedIndex()+1);
            statement.executeUpdate();
            JOptionPane.showMessageDialog(null, " Album Guncelleme Başarili");
        } catch (SQLException exception) {
            db.ShowError(exception);
        } finally {
            statement.close();
            connect.close();
        }
    
  }
  public void AlbumileAlbumSarkilarSil() throws SQLException
  {
          Connection connect = null;
        DbHelper db = new DbHelper();
        PreparedStatement statement = null;
        try {
            connect = db.getConnection();
            String sql = "DELETE from album_sarkilar WHERE Album_Id=?";
            statement = connect.prepareStatement(sql);
            statement.setInt(1, Integer.valueOf(albumIdGir.getText()));
            statement.executeUpdate();
            JOptionPane.showMessageDialog(null, " albumsarkilar Silme Başarili");
        } catch (SQLException exception) {
            db.ShowError(exception);
        } finally {
            statement.close();
            connect.close();
        }
  }
  public void AlbumSarkilarEkle() throws SQLException
  {
     Connection connect = null;
        DbHelper db = new DbHelper();
        PreparedStatement statement = null;
        try {
            connect = db.getConnection();
            String sql = "insert into muzik_dosyam.album_sarkilar(AlbumSarkilar_Id,Album_Id,SanatciSarki_Id)" + "values(?,?,?)";
            statement = connect.prepareStatement(sql);
            statement.setInt(1, Integer.valueOf(albumSarkilarIDGir.getText()));
            statement.setInt(2, Integer.valueOf(albumSarkilarAlbumIDGir.getText()));
            statement.setInt(3, Integer.valueOf(albumSarkilarSanatciSarkiIDGir.getText()));
            statement.executeUpdate();
            JOptionPane.showMessageDialog(null, "Kayıt başarılı");
        } catch (SQLException exception) {
            db.ShowError(exception);
        } finally {
            statement.close();
            connect.close();
        } 
  }
  public void AlbumSarkilarGoster()
  {
      try {

            Connection myConn = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/muzik_dosyam", "root", "Ok.20002000");
            Statement myStat = (Statement) myConn.createStatement();
            ResultSet rs = myStat.executeQuery("SELECT * FROM album_sarkilar");

            while (rs.next()) {
                albumsarkilarıd = String.valueOf(rs.getInt("AlbumSarkilar_Id"));
                albumId = String.valueOf(rs.getInt("Album_Id"));
                sanatcisarkiId=String.valueOf(rs.getInt("SanatciSarki_Id"));
                String tbData[] = {albumsarkilarıd,albumId,sanatcisarkiId};
                DefaultTableModel tblModel = (DefaultTableModel) albumSarkilarTablosu.getModel();
                tblModel.addRow(tbData);
            }

        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(KayitEkrani.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } 
  }
  public void KullanicilarGoster()
  {
       try {

            Connection myConn = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/muzik_dosyam", "root", "Ok.20002000");
            Statement myStat = (Statement) myConn.createStatement();
            ResultSet rs = myStat.executeQuery("SELECT * FROM kullanici");

            while (rs.next()) {
                kullaniciId = String.valueOf(rs.getInt("Kullanici_Id"));
                kullaniciadi = rs.getString("Kullanici_Adi");
                email=rs.getString("Email");
                sifre=rs.getString("Sifre");
                ulkeId=String.valueOf(rs.getInt("Ulke_Id"));
                odendimi=String.valueOf(rs.getBoolean("Odendi_Mi"));
                abonelikıd=String.valueOf(rs.getInt("Abonelik_Id"));
                String tbData[] = {kullaniciId,kullaniciadi,email,sifre,ulkeId,odendimi,abonelikıd};
                DefaultTableModel tblModel = (DefaultTableModel) kullaniciTablosu.getModel();
                tblModel.addRow(tbData);
    
            }

        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(KayitEkrani.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } 
  }
  public void AdminGoster()
  {
     
    try {

            Connection myConn = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/muzik_dosyam", "root", "Ok.20002000");
            Statement myStat = (Statement) myConn.createStatement();
            ResultSet rs = myStat.executeQuery("SELECT * FROM admin");

            while (rs.next()) {
                adminid = String.valueOf(rs.getInt("Admin_Id"));
                adminkullaniciad = rs.getString("Kullanici_Adi");
                adminemail=rs.getString("Email");
                adminsifre=rs.getString("Sifre");
                
                String tbData[] = {adminid,adminkullaniciad,adminemail,adminsifre};
                DefaultTableModel tblModel = (DefaultTableModel) adminTablosu.getModel();
                tblModel.addRow(tbData);
    
            }

        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(KayitEkrani.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
  }
  public void AbonelikEkle() throws SQLException
  {
         Connection connect = null;
        DbHelper db = new DbHelper();
        PreparedStatement statement = null;
        try {
            connect = db.getConnection();
            String sql = "insert into muzik_dosyam.abonelik_turu(Abonelik_Id,Abone_Turu)" + "values(?,?)";
            statement = connect.prepareStatement(sql);
            statement.setInt(1, Integer.valueOf(abonelikIDGir.getText()));
            statement.setString(2, abonelikTuruGir.getText());
            statement.executeUpdate();
            JOptionPane.showMessageDialog(null, "Kayıt başarılı");
        } catch (SQLException exception) {
            db.ShowError(exception);
        } finally {
            statement.close();
            connect.close();
        } 
  }
  public void AbonelikTurGoster()
  {
     try {

            Connection myConn = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/muzik_dosyam", "root", "Ok.20002000");
            Statement myStat = (Statement) myConn.createStatement();
            ResultSet rs = myStat.executeQuery("SELECT * FROM abonelik_turu");

            while (rs.next()) {
                abonelikid = String.valueOf(rs.getInt("Abonelik_Id"));
                aboneliktur = rs.getString("Abone_Turu");
                String tbData[] = { abonelikid, aboneliktur};
                DefaultTableModel tblModel = (DefaultTableModel) abonelikTablosu.getModel();
                tblModel.addRow(tbData);
            }

        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(KayitEkrani.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }   
  }
 

   public void SarkiSil() throws SQLException {
        Connection connect = null;
        DbHelper db = new DbHelper();
        PreparedStatement statement = null;
        try {
            connect = db.getConnection();
            String sql = "DELETE from sarki WHERE Sarki_Id=?";
            statement = connect.prepareStatement(sql);
            statement.setInt(1, Integer.valueOf(sarkiIdGir.getText()));
            statement.executeUpdate();
            JOptionPane.showMessageDialog(null, " Sarki Silme Başarili");
        } catch (SQLException exception) {
            db.ShowError(exception);
        } finally {
            statement.close();
            connect.close();
        }
    }
    public void SarkiGuncelle() throws SQLException {
        Connection connect = null;
        DbHelper db = new DbHelper();
        PreparedStatement statement = null;
        try {
            connect = db.getConnection();
            String sql = "UPDATE  sarki SET Sarki_Adi=?,Tarih=?,SarkiTur_Id=?,Sure=? WHERE Sarki_Id=?";
            statement = connect.prepareStatement(sql);
            statement.setInt(5, Integer.valueOf(sarkiIdGir.getText()));
            statement.setString(1, sarkiAdiGir.getText());
            statement.setString(2, sarkiTarihGir.getText());
            statement.setInt(3, sarkiTurSeccbox.getSelectedIndex()+1);
            statement.setDouble(4, Double.valueOf(sarkiSureGir.getText()));
            statement.executeUpdate();
            JOptionPane.showMessageDialog(null, " Sarki Guncelleme Başarili");
        } catch (SQLException exception) {
            db.ShowError(exception);
        } finally {
            statement.close();
            connect.close();
        }
    }
   public void SanatciSarkiSil() throws SQLException
   {
         Connection connect = null;
        DbHelper db = new DbHelper();
        PreparedStatement statement = null;
        try {
            connect = db.getConnection();
            String sql = "DELETE from sanatci_sarki WHERE SanatciSarki_Id=?";
            statement = connect.prepareStatement(sql);
            statement.setInt(1, Integer.valueOf(sanatciSarkiIdGir.getText()));
            statement.executeUpdate();
            JOptionPane.showMessageDialog(null, " Silme Başarili");
        } catch (SQLException exception) {
            db.ShowError(exception);
        } finally {
            statement.close();
            connect.close();
        }
   }
     public void AlbumSarkilardanSanatciSarkiSil() throws SQLException
   {
         Connection connect = null;
        DbHelper db = new DbHelper();
        PreparedStatement statement = null;
        try {
            connect = db.getConnection();
            String sql = "DELETE from album_sarkilar WHERE SanatciSarki_Id=?";
            statement = connect.prepareStatement(sql);
            statement.setInt(1, Integer.valueOf(sanatciSarkiIdGir.getText()));
            statement.executeUpdate();
            JOptionPane.showMessageDialog(null, " Silme Başarili");
        } catch (SQLException exception) {
            db.ShowError(exception);
        } finally {
            statement.close();
            connect.close();
        }
   }
     public void CalmaListesindenSanatcıSarkileSil() throws SQLException
     {
             Connection connect = null;
        DbHelper db = new DbHelper();
        PreparedStatement statement = null;
        try {
            connect = db.getConnection();
            String sql = "DELETE from calma_listesi_sarkilar WHERE SanatciSarki_Id=?";
            statement = connect.prepareStatement(sql);
            statement.setInt(1, Integer.valueOf(sanatciSarkiIdGir.getText()));
            statement.executeUpdate();
            JOptionPane.showMessageDialog(null, " Silme Başarili");
        } catch (SQLException exception) {
            db.ShowError(exception);
        } finally {
            statement.close();
            connect.close();
        }
     }
       public void SarkileAlbumdenSil() throws SQLException
       {
           Connection connect = null;
        DbHelper db = new DbHelper();
        PreparedStatement statement = null;
        try {
            connect = db.getConnection();
            String sql = "Delete from album_sarkilar where SanatciSarki_Id in( select SanatciSarki_Id from sanatci_sarki  where Sarki_Id in(select Sarki_Id from sarki  where Sarki_Id =? )); ";
            statement = connect.prepareStatement(sql);
            statement.setInt(1, Integer.valueOf(sarkiIdGir.getText()));
            statement.executeUpdate();
            JOptionPane.showMessageDialog(null, " Silme Başarili");
        } catch (SQLException exception) {
            db.ShowError(exception);
        } finally {
            statement.close();
            connect.close();
        } 
       }
       public void SarkileCalmalistesindenSil() throws SQLException
       {
           Connection connect = null;
        DbHelper db = new DbHelper();
        PreparedStatement statement = null;
        try {
            connect = db.getConnection();
            String sql = "Delete from calma_listesi_sarkilar where SanatciSarki_Id in( select SanatciSarki_Id from sanatci_sarki  where Sarki_Id in(select Sarki_Id from sarki  where Sarki_Id =? )); ";
            statement = connect.prepareStatement(sql);
            statement.setInt(1, Integer.valueOf(sarkiIdGir.getText()));
            statement.executeUpdate();
            JOptionPane.showMessageDialog(null, " Silme Başarili");
        } catch (SQLException exception) {
            db.ShowError(exception);
        } finally {
            statement.close();
            connect.close();
        }    
       }
       public void sarkileSanatciSarkidanSil() throws SQLException
       {
           Connection connect = null;
        DbHelper db = new DbHelper();
        PreparedStatement statement = null;
        try {
            connect = db.getConnection();
            String sql = "DELETE from sanatci_sarki WHERE Sarki_Id=?";
            statement = connect.prepareStatement(sql);
            statement.setInt(1, Integer.valueOf(sarkiIdGir.getText()));
            statement.executeUpdate();
            JOptionPane.showMessageDialog(null, " Silme Başarili");
        } catch (SQLException exception) {
            db.ShowError(exception);
        } finally {
            statement.close();
            connect.close();
        } 
       }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        adminSekmeler = new javax.swing.JTabbedPane();
        Sarki = new javax.swing.JPanel();
        sarkiListePanel = new javax.swing.JPanel();
        sarkiScrollPane = new javax.swing.JScrollPane();
        sarkiTablosu = new javax.swing.JTable();
        sarkiEkleButon = new javax.swing.JButton();
        sarkiGuncelleButon = new javax.swing.JButton();
        sarkiSilButon = new javax.swing.JButton();
        sarkiIdGir = new javax.swing.JTextField();
        sarkiAdiGir = new javax.swing.JTextField();
        sarkiTarihGir = new javax.swing.JTextField();
        sarkiSureGir = new javax.swing.JTextField();
        sarkiTurSeccbox = new javax.swing.JComboBox<>();
        s1 = new javax.swing.JLabel();
        s2 = new javax.swing.JLabel();
        s3 = new javax.swing.JLabel();
        s6 = new javax.swing.JLabel();
        s5 = new javax.swing.JLabel();
        jButton4 = new javax.swing.JButton();
        turEklePanel = new javax.swing.JPanel();
        turScrollPane2 = new javax.swing.JScrollPane();
        turTablosu = new javax.swing.JTable();
        turEkleButon = new javax.swing.JButton();
        sarkiTurIDGir = new javax.swing.JTextField();
        turGir = new javax.swing.JTextField();
        sa8 = new javax.swing.JLabel();
        sa9 = new javax.swing.JLabel();
        ulkeEklePanel = new javax.swing.JPanel();
        ulkeiScrollPane1 = new javax.swing.JScrollPane();
        ulkeTablosu = new javax.swing.JTable();
        ulkeIdGir = new javax.swing.JTextField();
        ulkeAdiGir = new javax.swing.JTextField();
        ulkeEkleButon3 = new javax.swing.JButton();
        sa5 = new javax.swing.JLabel();
        sa4 = new javax.swing.JLabel();
        Sanatci = new javax.swing.JPanel();
        sanatciListePanel = new javax.swing.JPanel();
        sanatciScrollPane = new javax.swing.JScrollPane();
        sanatciTablosu = new javax.swing.JTable();
        sanatciSarkiIdGir = new javax.swing.JTextField();
        sanatciIDGir = new javax.swing.JTextField();
        sarkiIDGir = new javax.swing.JTextField();
        dinlenmeSayisiGir = new javax.swing.JTextField();
        sa2 = new javax.swing.JLabel();
        sa3 = new javax.swing.JLabel();
        sa1 = new javax.swing.JLabel();
        sa6 = new javax.swing.JLabel();
        sanatciEkleButon = new javax.swing.JButton();
        sanatciGuncelleButon = new javax.swing.JButton();
        sanatciSilButon = new javax.swing.JButton();
        sanatciSarkiPanel = new javax.swing.JPanel();
        sanatciSarkiScrollPane = new javax.swing.JScrollPane();
        sanatciTablosu1 = new javax.swing.JTable();
        sanatciSarkiSanatciIDGir = new javax.swing.JTextField();
        sarkiSanatciSanatciAdiGir = new javax.swing.JTextField();
        sarkiSanatciUlkeIDGir = new javax.swing.JTextField();
        sanatciSarkiEkleButon = new javax.swing.JButton();
        sarkiSanatciGuncelleButon = new javax.swing.JButton();
        sarkiSanatciSilButon = new javax.swing.JButton();
        sa11 = new javax.swing.JLabel();
        sa10 = new javax.swing.JLabel();
        sa7 = new javax.swing.JLabel();
        Album = new javax.swing.JPanel();
        albumSarkilarPanel = new javax.swing.JPanel();
        albumSarkilarPane = new javax.swing.JScrollPane();
        albumSarkilarTablosu = new javax.swing.JTable();
        albumSarkilarIDGir = new javax.swing.JTextField();
        albumSarkilarAlbumIDGir = new javax.swing.JTextField();
        albumSarkilarSanatciSarkiIDGir = new javax.swing.JTextField();
        albumSarkilarEkleButon = new javax.swing.JButton();
        a3 = new javax.swing.JLabel();
        a2 = new javax.swing.JLabel();
        a1 = new javax.swing.JLabel();
        albumEklePanel = new javax.swing.JPanel();
        albumPane1 = new javax.swing.JScrollPane();
        albumTablosu = new javax.swing.JTable();
        albumSİlButon = new javax.swing.JButton();
        albumGuncelleButon = new javax.swing.JButton();
        albumEkleButon = new javax.swing.JButton();
        albumIdGir = new javax.swing.JTextField();
        albumAdiGir = new javax.swing.JTextField();
        albumTurIDGir = new javax.swing.JComboBox<>();
        a6 = new javax.swing.JLabel();
        a7 = new javax.swing.JLabel();
        a8 = new javax.swing.JLabel();
        calmaListesiPanel = new javax.swing.JPanel();
        calmaListesiGoruntulePanel = new javax.swing.JPanel();
        calmaListesiScrollPane = new javax.swing.JScrollPane();
        calmaListesiTablosu = new javax.swing.JTable();
        calmaListesiSarkilarPanel = new javax.swing.JPanel();
        calmaListesiSarkilarScrollPane = new javax.swing.JScrollPane();
        calmaListesiSarkilarTablosu = new javax.swing.JTable();
        kullaniciPanel = new javax.swing.JPanel();
        kullanicilarPanel = new javax.swing.JPanel();
        kullanicilarScrollPane = new javax.swing.JScrollPane();
        kullaniciTablosu = new javax.swing.JTable();
        kullaniciTakipPanel = new javax.swing.JPanel();
        kullaniciTakipScrollPane1 = new javax.swing.JScrollPane();
        kullaniciTakipTablosu1 = new javax.swing.JTable();
        abonelikScrollPane = new javax.swing.JScrollPane();
        abonelikTablosu = new javax.swing.JTable();
        abonelikIDGir = new javax.swing.JTextField();
        a4 = new javax.swing.JLabel();
        abonelikTuruGir = new javax.swing.JTextField();
        a5 = new javax.swing.JLabel();
        abonelikEkleButon = new javax.swing.JButton();
        adminPanel = new javax.swing.JPanel();
        adminScrollPane = new javax.swing.JScrollPane();
        adminTablosu = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(null);

        adminSekmeler.setBackground(new java.awt.Color(51, 51, 51));
        adminSekmeler.setForeground(new java.awt.Color(204, 204, 204));
        adminSekmeler.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N

        Sarki.setBackground(new java.awt.Color(43, 42, 42));
        Sarki.setForeground(new java.awt.Color(51, 51, 51));
        Sarki.setLayout(null);

        sarkiListePanel.setBackground(new java.awt.Color(46, 46, 46));
        sarkiListePanel.setForeground(new java.awt.Color(51, 51, 51));
        sarkiListePanel.setLayout(null);

        sarkiScrollPane.setBackground(new java.awt.Color(102, 102, 102));
        sarkiScrollPane.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 204, 0), 5), "ŞARKI", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 36), new java.awt.Color(204, 204, 204))); // NOI18N
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
                "Şarkı ID", "Şarkı Adı", "Şarkı Tarihi", "Şarkı Tür ID", "Süre"
            }
        ));
        sarkiTablosu.setDoubleBuffered(true);
        sarkiTablosu.setFocusCycleRoot(true);
        sarkiTablosu.setRowHeight(30);
        sarkiTablosu.setSelectionBackground(new java.awt.Color(255, 204, 0));
        sarkiTablosu.setSelectionForeground(new java.awt.Color(102, 102, 102));
        sarkiTablosu.setShowVerticalLines(false);
        sarkiScrollPane.setViewportView(sarkiTablosu);
        sarkiTablosu.getAccessibleContext().setAccessibleDescription("");

        sarkiListePanel.add(sarkiScrollPane);
        sarkiScrollPane.setBounds(30, 20, 890, 420);

        sarkiEkleButon.setBackground(new java.awt.Color(255, 204, 0));
        sarkiEkleButon.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        sarkiEkleButon.setForeground(new java.awt.Color(51, 51, 51));
        sarkiEkleButon.setText("ŞARKI EKLE");
        sarkiEkleButon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sarkiEkleButonActionPerformed(evt);
            }
        });
        sarkiListePanel.add(sarkiEkleButon);
        sarkiEkleButon.setBounds(630, 470, 290, 100);

        sarkiGuncelleButon.setBackground(new java.awt.Color(255, 204, 0));
        sarkiGuncelleButon.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        sarkiGuncelleButon.setForeground(new java.awt.Color(51, 51, 51));
        sarkiGuncelleButon.setText("GÜNCELLE");
        sarkiGuncelleButon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sarkiGuncelleButonActionPerformed(evt);
            }
        });
        sarkiListePanel.add(sarkiGuncelleButon);
        sarkiGuncelleButon.setBounds(630, 580, 290, 110);

        sarkiSilButon.setBackground(new java.awt.Color(255, 204, 0));
        sarkiSilButon.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        sarkiSilButon.setForeground(new java.awt.Color(51, 51, 51));
        sarkiSilButon.setText("SİL");
        sarkiSilButon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sarkiSilButonActionPerformed(evt);
            }
        });
        sarkiListePanel.add(sarkiSilButon);
        sarkiSilButon.setBounds(630, 700, 290, 100);

        sarkiIdGir.setBackground(new java.awt.Color(51, 51, 51));
        sarkiIdGir.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        sarkiIdGir.setForeground(new java.awt.Color(204, 204, 204));
        sarkiIdGir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sarkiIdGirActionPerformed(evt);
            }
        });
        sarkiListePanel.add(sarkiIdGir);
        sarkiIdGir.setBounds(40, 490, 490, 40);

        sarkiAdiGir.setBackground(new java.awt.Color(51, 51, 51));
        sarkiAdiGir.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        sarkiAdiGir.setForeground(new java.awt.Color(204, 204, 204));
        sarkiAdiGir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sarkiAdiGirActionPerformed(evt);
            }
        });
        sarkiListePanel.add(sarkiAdiGir);
        sarkiAdiGir.setBounds(40, 590, 490, 40);

        sarkiTarihGir.setBackground(new java.awt.Color(51, 51, 51));
        sarkiTarihGir.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        sarkiTarihGir.setForeground(new java.awt.Color(204, 204, 204));
        sarkiTarihGir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sarkiTarihGirActionPerformed(evt);
            }
        });
        sarkiListePanel.add(sarkiTarihGir);
        sarkiTarihGir.setBounds(40, 670, 490, 50);

        sarkiSureGir.setBackground(new java.awt.Color(51, 51, 51));
        sarkiSureGir.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        sarkiSureGir.setForeground(new java.awt.Color(204, 204, 204));
        sarkiSureGir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sarkiSureGirActionPerformed(evt);
            }
        });
        sarkiListePanel.add(sarkiSureGir);
        sarkiSureGir.setBounds(40, 770, 490, 50);

        sarkiTurSeccbox.setBackground(new java.awt.Color(51, 51, 51));
        sarkiTurSeccbox.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        sarkiTurSeccbox.setForeground(new java.awt.Color(204, 204, 204));
        sarkiTurSeccbox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sarkiTurSeccboxActionPerformed(evt);
            }
        });
        sarkiListePanel.add(sarkiTurSeccbox);
        sarkiTurSeccbox.setBounds(40, 860, 480, 40);

        s1.setBackground(new java.awt.Color(204, 204, 204));
        s1.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        s1.setForeground(new java.awt.Color(204, 204, 204));
        s1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 204, 0), 4), "Şarkı ID", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 18), new java.awt.Color(204, 204, 204))); // NOI18N
        sarkiListePanel.add(s1);
        s1.setBounds(30, 460, 510, 90);

        s2.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        s2.setForeground(new java.awt.Color(204, 204, 204));
        s2.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 204, 0), 4), "Şarkı Adı", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 18), new java.awt.Color(204, 204, 204))); // NOI18N
        sarkiListePanel.add(s2);
        s2.setBounds(30, 560, 510, 80);

        s3.setBackground(new java.awt.Color(204, 204, 204));
        s3.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        s3.setForeground(new java.awt.Color(204, 204, 204));
        s3.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 204, 0), 4), "Şarkı Tarihi", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 18), new java.awt.Color(204, 204, 204))); // NOI18N
        sarkiListePanel.add(s3);
        s3.setBounds(30, 640, 510, 90);

        s6.setBackground(new java.awt.Color(204, 204, 204));
        s6.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        s6.setForeground(new java.awt.Color(204, 204, 204));
        s6.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 204, 0), 4), "Şarkı Süresi", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 18), new java.awt.Color(204, 204, 204))); // NOI18N
        sarkiListePanel.add(s6);
        s6.setBounds(30, 740, 510, 90);

        s5.setBackground(new java.awt.Color(204, 204, 204));
        s5.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        s5.setForeground(new java.awt.Color(204, 204, 204));
        s5.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 204, 0), 4), " Şarkı Tür ID", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 18), new java.awt.Color(204, 204, 204))); // NOI18N
        sarkiListePanel.add(s5);
        s5.setBounds(30, 830, 510, 90);

        jButton4.setBackground(new java.awt.Color(60, 60, 60));
        jButton4.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jButton4.setText("MENÜYE DÖN");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        sarkiListePanel.add(jButton4);
        jButton4.setBounds(630, 820, 280, 100);

        Sarki.add(sarkiListePanel);
        sarkiListePanel.setBounds(0, -10, 950, 970);

        turEklePanel.setBackground(new java.awt.Color(102, 102, 102));
        turEklePanel.setLayout(null);

        turScrollPane2.setBackground(new java.awt.Color(51, 51, 51));
        turScrollPane2.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 204, 0), 5), "TÜRLER", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 36), new java.awt.Color(204, 204, 204))); // NOI18N

        turTablosu.setBackground(new java.awt.Color(51, 51, 51));
        turTablosu.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        turTablosu.setForeground(new java.awt.Color(204, 204, 204));
        turTablosu.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Şarkı Tür ID", "Tür"
            }
        ));
        turTablosu.setRowHeight(30);
        turScrollPane2.setViewportView(turTablosu);

        turEklePanel.add(turScrollPane2);
        turScrollPane2.setBounds(30, 10, 450, 380);

        turEkleButon.setBackground(new java.awt.Color(255, 204, 0));
        turEkleButon.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        turEkleButon.setForeground(new java.awt.Color(51, 51, 51));
        turEkleButon.setText("TÜR EKLE");
        turEkleButon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                turEkleButonActionPerformed(evt);
            }
        });
        turEklePanel.add(turEkleButon);
        turEkleButon.setBounds(520, 340, 430, 50);

        sarkiTurIDGir.setBackground(new java.awt.Color(51, 51, 51));
        sarkiTurIDGir.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        sarkiTurIDGir.setForeground(new java.awt.Color(204, 204, 204));
        sarkiTurIDGir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sarkiTurIDGirActionPerformed(evt);
            }
        });
        turEklePanel.add(sarkiTurIDGir);
        sarkiTurIDGir.setBounds(540, 100, 390, 50);

        turGir.setBackground(new java.awt.Color(51, 51, 51));
        turGir.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        turGir.setForeground(new java.awt.Color(204, 204, 204));
        turGir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                turGirActionPerformed(evt);
            }
        });
        turEklePanel.add(turGir);
        turGir.setBounds(540, 240, 390, 50);

        sa8.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        sa8.setForeground(new java.awt.Color(204, 204, 204));
        sa8.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 204, 0), 4), "Tür", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 18), new java.awt.Color(204, 204, 204))); // NOI18N
        turEklePanel.add(sa8);
        sa8.setBounds(520, 200, 430, 110);

        sa9.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        sa9.setForeground(new java.awt.Color(204, 204, 204));
        sa9.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 204, 0), 4), "Şarkı Tür ID", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 18), new java.awt.Color(204, 204, 204))); // NOI18N
        turEklePanel.add(sa9);
        sa9.setBounds(520, 60, 430, 110);

        Sarki.add(turEklePanel);
        turEklePanel.setBounds(950, 500, 970, 470);

        ulkeEklePanel.setBackground(new java.awt.Color(68, 68, 68));
        ulkeEklePanel.setLayout(null);

        ulkeiScrollPane1.setBackground(new java.awt.Color(51, 51, 51));
        ulkeiScrollPane1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 204, 0), 5), "ÜLKELER", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 36), new java.awt.Color(204, 204, 204))); // NOI18N

        ulkeTablosu.setBackground(new java.awt.Color(51, 51, 51));
        ulkeTablosu.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        ulkeTablosu.setForeground(new java.awt.Color(204, 204, 204));
        ulkeTablosu.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Ülke ID", "Ülke Adı"
            }
        ));
        ulkeTablosu.setRowHeight(30);
        ulkeiScrollPane1.setViewportView(ulkeTablosu);

        ulkeEklePanel.add(ulkeiScrollPane1);
        ulkeiScrollPane1.setBounds(30, 20, 450, 410);

        ulkeIdGir.setBackground(new java.awt.Color(51, 51, 51));
        ulkeIdGir.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        ulkeIdGir.setForeground(new java.awt.Color(204, 204, 204));
        ulkeIdGir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ulkeIdGirActionPerformed(evt);
            }
        });
        ulkeEklePanel.add(ulkeIdGir);
        ulkeIdGir.setBounds(540, 100, 390, 50);

        ulkeAdiGir.setBackground(new java.awt.Color(51, 51, 51));
        ulkeAdiGir.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        ulkeAdiGir.setForeground(new java.awt.Color(204, 204, 204));
        ulkeAdiGir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ulkeAdiGirActionPerformed(evt);
            }
        });
        ulkeEklePanel.add(ulkeAdiGir);
        ulkeAdiGir.setBounds(540, 240, 390, 50);

        ulkeEkleButon3.setBackground(new java.awt.Color(255, 204, 0));
        ulkeEkleButon3.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        ulkeEkleButon3.setForeground(new java.awt.Color(51, 51, 51));
        ulkeEkleButon3.setText("ÜLKE EKLE");
        ulkeEkleButon3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ulkeEkleButon3ActionPerformed(evt);
            }
        });
        ulkeEklePanel.add(ulkeEkleButon3);
        ulkeEkleButon3.setBounds(520, 380, 430, 50);

        sa5.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        sa5.setForeground(new java.awt.Color(204, 204, 204));
        sa5.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 204, 0), 4), "Ülke Adı", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 18), new java.awt.Color(204, 204, 204))); // NOI18N
        ulkeEklePanel.add(sa5);
        sa5.setBounds(520, 200, 430, 110);

        sa4.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        sa4.setForeground(new java.awt.Color(204, 204, 204));
        sa4.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 204, 0), 4), "Ülke ID", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 18), new java.awt.Color(204, 204, 204))); // NOI18N
        ulkeEklePanel.add(sa4);
        sa4.setBounds(520, 60, 430, 110);

        Sarki.add(ulkeEklePanel);
        ulkeEklePanel.setBounds(950, 0, 970, 500);

        adminSekmeler.addTab("ŞARKI", Sarki);

        Sanatci.setBackground(new java.awt.Color(44, 44, 44));
        Sanatci.setLayout(null);

        sanatciListePanel.setBackground(new java.awt.Color(46, 46, 46));
        sanatciListePanel.setForeground(new java.awt.Color(51, 51, 51));
        sanatciListePanel.setLayout(null);

        sanatciScrollPane.setBackground(new java.awt.Color(102, 102, 102));
        sanatciScrollPane.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 204, 0), 5), "SANATÇI ŞARKI", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 36), new java.awt.Color(204, 204, 204))); // NOI18N
        sanatciScrollPane.setForeground(new java.awt.Color(102, 102, 102));
        sanatciScrollPane.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N

        sanatciTablosu.setBackground(new java.awt.Color(51, 51, 51));
        sanatciTablosu.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 24))); // NOI18N
        sanatciTablosu.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        sanatciTablosu.setForeground(new java.awt.Color(204, 204, 204));
        sanatciTablosu.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Sanatçı Şarkı ID", "Sanatçı ID", "Şarkı ID", "Dinlenme Sayısı"
            }
        ));
        sanatciTablosu.setDoubleBuffered(true);
        sanatciTablosu.setRowHeight(30);
        sanatciTablosu.setSelectionBackground(new java.awt.Color(255, 204, 0));
        sanatciTablosu.setSelectionForeground(new java.awt.Color(102, 102, 102));
        sanatciTablosu.setShowVerticalLines(false);
        sanatciScrollPane.setViewportView(sanatciTablosu);
        if (sanatciTablosu.getColumnModel().getColumnCount() > 0) {
            sanatciTablosu.getColumnModel().getColumn(0).setResizable(false);
            sanatciTablosu.getColumnModel().getColumn(2).setResizable(false);
        }

        sanatciListePanel.add(sanatciScrollPane);
        sanatciScrollPane.setBounds(30, 20, 620, 890);

        sanatciSarkiIdGir.setBackground(new java.awt.Color(51, 51, 51));
        sanatciSarkiIdGir.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        sanatciSarkiIdGir.setForeground(new java.awt.Color(204, 204, 204));
        sanatciSarkiIdGir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sanatciSarkiIdGirActionPerformed(evt);
            }
        });
        sanatciListePanel.add(sanatciSarkiIdGir);
        sanatciSarkiIdGir.setBounds(690, 100, 400, 60);

        sanatciIDGir.setBackground(new java.awt.Color(51, 51, 51));
        sanatciIDGir.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        sanatciIDGir.setForeground(new java.awt.Color(204, 204, 204));
        sanatciIDGir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sanatciIDGirActionPerformed(evt);
            }
        });
        sanatciListePanel.add(sanatciIDGir);
        sanatciIDGir.setBounds(690, 270, 400, 60);

        sarkiIDGir.setBackground(new java.awt.Color(51, 51, 51));
        sarkiIDGir.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        sarkiIDGir.setForeground(new java.awt.Color(204, 204, 204));
        sarkiIDGir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sarkiIDGirActionPerformed(evt);
            }
        });
        sanatciListePanel.add(sarkiIDGir);
        sarkiIDGir.setBounds(690, 430, 410, 60);

        dinlenmeSayisiGir.setBackground(new java.awt.Color(51, 51, 51));
        dinlenmeSayisiGir.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        dinlenmeSayisiGir.setForeground(new java.awt.Color(204, 204, 204));
        dinlenmeSayisiGir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dinlenmeSayisiGirActionPerformed(evt);
            }
        });
        sanatciListePanel.add(dinlenmeSayisiGir);
        dinlenmeSayisiGir.setBounds(690, 580, 410, 60);

        sa2.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        sa2.setForeground(new java.awt.Color(204, 204, 204));
        sa2.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 204, 0), 4), "Sanatçı ID", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 18), new java.awt.Color(204, 204, 204))); // NOI18N
        sanatciListePanel.add(sa2);
        sa2.setBounds(680, 220, 420, 140);

        sa3.setBackground(new java.awt.Color(204, 204, 204));
        sa3.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        sa3.setForeground(new java.awt.Color(204, 204, 204));
        sa3.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 204, 0), 4), "Şarkı ID", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 18), new java.awt.Color(204, 204, 204))); // NOI18N
        sanatciListePanel.add(sa3);
        sa3.setBounds(680, 380, 430, 140);

        sa1.setBackground(new java.awt.Color(204, 204, 204));
        sa1.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        sa1.setForeground(new java.awt.Color(204, 204, 204));
        sa1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 204, 0), 4), "Sanatçı Şarkı ID", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 18), new java.awt.Color(204, 204, 204))); // NOI18N
        sanatciListePanel.add(sa1);
        sa1.setBounds(680, 50, 420, 140);

        sa6.setBackground(new java.awt.Color(204, 204, 204));
        sa6.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        sa6.setForeground(new java.awt.Color(204, 204, 204));
        sa6.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 204, 0), 4), "Dinlenme Sayısı", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 18), new java.awt.Color(204, 204, 204))); // NOI18N
        sanatciListePanel.add(sa6);
        sa6.setBounds(680, 530, 430, 130);

        sanatciEkleButon.setBackground(new java.awt.Color(255, 204, 0));
        sanatciEkleButon.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        sanatciEkleButon.setForeground(new java.awt.Color(51, 51, 51));
        sanatciEkleButon.setText("EKLE");
        sanatciEkleButon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sanatciEkleButonActionPerformed(evt);
            }
        });
        sanatciListePanel.add(sanatciEkleButon);
        sanatciEkleButon.setBounds(680, 700, 430, 50);

        sanatciGuncelleButon.setBackground(new java.awt.Color(255, 204, 0));
        sanatciGuncelleButon.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        sanatciGuncelleButon.setForeground(new java.awt.Color(51, 51, 51));
        sanatciGuncelleButon.setText("GÜNCELLE");
        sanatciListePanel.add(sanatciGuncelleButon);
        sanatciGuncelleButon.setBounds(680, 780, 430, 50);

        sanatciSilButon.setBackground(new java.awt.Color(255, 204, 0));
        sanatciSilButon.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        sanatciSilButon.setForeground(new java.awt.Color(51, 51, 51));
        sanatciSilButon.setText("SİL");
        sanatciListePanel.add(sanatciSilButon);
        sanatciSilButon.setBounds(680, 850, 430, 50);

        Sanatci.add(sanatciListePanel);
        sanatciListePanel.setBounds(0, 0, 1150, 1020);

        sanatciSarkiPanel.setBackground(new java.awt.Color(70, 70, 70));
        sanatciSarkiPanel.setLayout(null);

        sanatciSarkiScrollPane.setBackground(new java.awt.Color(102, 102, 102));
        sanatciSarkiScrollPane.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 204, 0), 5), "SANATÇILAR", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 36), new java.awt.Color(204, 204, 204))); // NOI18N
        sanatciSarkiScrollPane.setForeground(new java.awt.Color(102, 102, 102));
        sanatciSarkiScrollPane.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N

        sanatciTablosu1.setBackground(new java.awt.Color(51, 51, 51));
        sanatciTablosu1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 24))); // NOI18N
        sanatciTablosu1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        sanatciTablosu1.setForeground(new java.awt.Color(204, 204, 204));
        sanatciTablosu1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Sanatçı ID", "Sanatçı Adı", "Ülke ID"
            }
        ));
        sanatciTablosu1.setDoubleBuffered(true);
        sanatciTablosu1.setRowHeight(30);
        sanatciTablosu1.setSelectionBackground(new java.awt.Color(255, 204, 0));
        sanatciTablosu1.setSelectionForeground(new java.awt.Color(102, 102, 102));
        sanatciTablosu1.setShowVerticalLines(false);
        sanatciSarkiScrollPane.setViewportView(sanatciTablosu1);
        if (sanatciTablosu1.getColumnModel().getColumnCount() > 0) {
            sanatciTablosu1.getColumnModel().getColumn(0).setResizable(false);
            sanatciTablosu1.getColumnModel().getColumn(2).setResizable(false);
        }

        sanatciSarkiPanel.add(sanatciSarkiScrollPane);
        sanatciSarkiScrollPane.setBounds(30, 30, 420, 880);

        sanatciSarkiSanatciIDGir.setBackground(new java.awt.Color(51, 51, 51));
        sanatciSarkiSanatciIDGir.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        sanatciSarkiSanatciIDGir.setForeground(new java.awt.Color(204, 204, 204));
        sanatciSarkiSanatciIDGir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sanatciSarkiSanatciIDGirActionPerformed(evt);
            }
        });
        sanatciSarkiPanel.add(sanatciSarkiSanatciIDGir);
        sanatciSarkiSanatciIDGir.setBounds(490, 90, 240, 60);

        sarkiSanatciSanatciAdiGir.setBackground(new java.awt.Color(51, 51, 51));
        sarkiSanatciSanatciAdiGir.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        sarkiSanatciSanatciAdiGir.setForeground(new java.awt.Color(204, 204, 204));
        sarkiSanatciSanatciAdiGir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sarkiSanatciSanatciAdiGirActionPerformed(evt);
            }
        });
        sanatciSarkiPanel.add(sarkiSanatciSanatciAdiGir);
        sarkiSanatciSanatciAdiGir.setBounds(490, 310, 240, 60);

        sarkiSanatciUlkeIDGir.setBackground(new java.awt.Color(51, 51, 51));
        sarkiSanatciUlkeIDGir.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        sarkiSanatciUlkeIDGir.setForeground(new java.awt.Color(204, 204, 204));
        sarkiSanatciUlkeIDGir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sarkiSanatciUlkeIDGirActionPerformed(evt);
            }
        });
        sanatciSarkiPanel.add(sarkiSanatciUlkeIDGir);
        sarkiSanatciUlkeIDGir.setBounds(480, 520, 250, 60);

        sanatciSarkiEkleButon.setBackground(new java.awt.Color(255, 204, 0));
        sanatciSarkiEkleButon.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        sanatciSarkiEkleButon.setForeground(new java.awt.Color(51, 51, 51));
        sanatciSarkiEkleButon.setText("EKLE");
        sanatciSarkiEkleButon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sanatciSarkiEkleButonActionPerformed(evt);
            }
        });
        sanatciSarkiPanel.add(sanatciSarkiEkleButon);
        sanatciSarkiEkleButon.setBounds(470, 660, 270, 60);

        sarkiSanatciGuncelleButon.setBackground(new java.awt.Color(255, 204, 0));
        sarkiSanatciGuncelleButon.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        sarkiSanatciGuncelleButon.setForeground(new java.awt.Color(51, 51, 51));
        sarkiSanatciGuncelleButon.setText("GÜNCELLE");
        sarkiSanatciGuncelleButon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sarkiSanatciGuncelleButonActionPerformed(evt);
            }
        });
        sanatciSarkiPanel.add(sarkiSanatciGuncelleButon);
        sarkiSanatciGuncelleButon.setBounds(470, 740, 270, 60);

        sarkiSanatciSilButon.setBackground(new java.awt.Color(255, 204, 0));
        sarkiSanatciSilButon.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        sarkiSanatciSilButon.setForeground(new java.awt.Color(51, 51, 51));
        sarkiSanatciSilButon.setText("SİL");
        sarkiSanatciSilButon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sarkiSanatciSilButonActionPerformed(evt);
            }
        });
        sanatciSarkiPanel.add(sarkiSanatciSilButon);
        sarkiSanatciSilButon.setBounds(470, 820, 270, 60);

        sa11.setBackground(new java.awt.Color(204, 204, 204));
        sa11.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        sa11.setForeground(new java.awt.Color(204, 204, 204));
        sa11.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 204, 0), 4), "Ülke ID", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 18), new java.awt.Color(204, 204, 204))); // NOI18N
        sanatciSarkiPanel.add(sa11);
        sa11.setBounds(470, 470, 270, 140);

        sa10.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        sa10.setForeground(new java.awt.Color(204, 204, 204));
        sa10.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 204, 0), 4), "Sanatçı Adı", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 18), new java.awt.Color(204, 204, 204))); // NOI18N
        sanatciSarkiPanel.add(sa10);
        sa10.setBounds(480, 260, 260, 140);

        sa7.setBackground(new java.awt.Color(204, 204, 204));
        sa7.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        sa7.setForeground(new java.awt.Color(204, 204, 204));
        sa7.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 204, 0), 4), "Sanatçı ID", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 18), new java.awt.Color(204, 204, 204))); // NOI18N
        sanatciSarkiPanel.add(sa7);
        sa7.setBounds(480, 40, 260, 140);

        Sanatci.add(sanatciSarkiPanel);
        sanatciSarkiPanel.setBounds(1148, 0, 770, 1030);

        adminSekmeler.addTab("SANATÇI", Sanatci);

        Album.setBackground(new java.awt.Color(44, 44, 44));
        Album.setLayout(null);

        albumSarkilarPanel.setBackground(new java.awt.Color(46, 46, 46));
        albumSarkilarPanel.setForeground(new java.awt.Color(51, 51, 51));
        albumSarkilarPanel.setLayout(null);

        albumSarkilarPane.setBackground(new java.awt.Color(102, 102, 102));
        albumSarkilarPane.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 204, 0), 5), "ALBÜM ŞARKILAR", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 36), new java.awt.Color(204, 204, 204))); // NOI18N
        albumSarkilarPane.setForeground(new java.awt.Color(102, 102, 102));
        albumSarkilarPane.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N

        albumSarkilarTablosu.setBackground(new java.awt.Color(51, 51, 51));
        albumSarkilarTablosu.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 24))); // NOI18N
        albumSarkilarTablosu.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        albumSarkilarTablosu.setForeground(new java.awt.Color(204, 204, 204));
        albumSarkilarTablosu.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Albüm Şarkılar ID ", "Albüm ID", "Sanatçı Şarkı ID"
            }
        ));
        albumSarkilarTablosu.setDoubleBuffered(true);
        albumSarkilarTablosu.setRowHeight(30);
        albumSarkilarTablosu.setSelectionBackground(new java.awt.Color(255, 204, 0));
        albumSarkilarTablosu.setSelectionForeground(new java.awt.Color(102, 102, 102));
        albumSarkilarTablosu.setShowVerticalLines(false);
        albumSarkilarPane.setViewportView(albumSarkilarTablosu);
        if (albumSarkilarTablosu.getColumnModel().getColumnCount() > 0) {
            albumSarkilarTablosu.getColumnModel().getColumn(0).setResizable(false);
        }

        albumSarkilarPanel.add(albumSarkilarPane);
        albumSarkilarPane.setBounds(40, 30, 560, 850);

        albumSarkilarIDGir.setBackground(new java.awt.Color(51, 51, 51));
        albumSarkilarIDGir.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        albumSarkilarIDGir.setForeground(new java.awt.Color(204, 204, 204));
        albumSarkilarIDGir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                albumSarkilarIDGirActionPerformed(evt);
            }
        });
        albumSarkilarPanel.add(albumSarkilarIDGir);
        albumSarkilarIDGir.setBounds(670, 90, 270, 50);

        albumSarkilarAlbumIDGir.setBackground(new java.awt.Color(51, 51, 51));
        albumSarkilarAlbumIDGir.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        albumSarkilarAlbumIDGir.setForeground(new java.awt.Color(204, 204, 204));
        albumSarkilarAlbumIDGir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                albumSarkilarAlbumIDGirActionPerformed(evt);
            }
        });
        albumSarkilarPanel.add(albumSarkilarAlbumIDGir);
        albumSarkilarAlbumIDGir.setBounds(670, 270, 270, 50);

        albumSarkilarSanatciSarkiIDGir.setBackground(new java.awt.Color(51, 51, 51));
        albumSarkilarSanatciSarkiIDGir.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        albumSarkilarSanatciSarkiIDGir.setForeground(new java.awt.Color(204, 204, 204));
        albumSarkilarSanatciSarkiIDGir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                albumSarkilarSanatciSarkiIDGirActionPerformed(evt);
            }
        });
        albumSarkilarPanel.add(albumSarkilarSanatciSarkiIDGir);
        albumSarkilarSanatciSarkiIDGir.setBounds(670, 470, 270, 50);

        albumSarkilarEkleButon.setBackground(new java.awt.Color(255, 204, 0));
        albumSarkilarEkleButon.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        albumSarkilarEkleButon.setForeground(new java.awt.Color(51, 51, 51));
        albumSarkilarEkleButon.setText("EKLE");
        albumSarkilarEkleButon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                albumSarkilarEkleButonActionPerformed(evt);
            }
        });
        albumSarkilarPanel.add(albumSarkilarEkleButon);
        albumSarkilarEkleButon.setBounds(660, 650, 290, 60);

        a3.setBackground(new java.awt.Color(204, 204, 204));
        a3.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        a3.setForeground(new java.awt.Color(204, 204, 204));
        a3.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 204, 0), 4), "Sanatçı Şarkı ID", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 18), new java.awt.Color(204, 204, 204))); // NOI18N
        albumSarkilarPanel.add(a3);
        a3.setBounds(660, 440, 290, 100);

        a2.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        a2.setForeground(new java.awt.Color(204, 204, 204));
        a2.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 204, 0), 4), "Albüm ID", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 18), new java.awt.Color(204, 204, 204))); // NOI18N
        albumSarkilarPanel.add(a2);
        a2.setBounds(660, 240, 290, 100);

        a1.setBackground(new java.awt.Color(204, 204, 204));
        a1.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        a1.setForeground(new java.awt.Color(204, 204, 204));
        a1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 204, 0), 4), "Albüm Şarkılar ID", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 18), new java.awt.Color(204, 204, 204))); // NOI18N
        albumSarkilarPanel.add(a1);
        a1.setBounds(660, 60, 290, 100);

        Album.add(albumSarkilarPanel);
        albumSarkilarPanel.setBounds(0, 0, 1000, 1020);

        albumEklePanel.setBackground(new java.awt.Color(70, 70, 70));
        albumEklePanel.setLayout(null);

        albumPane1.setBackground(new java.awt.Color(102, 102, 102));
        albumPane1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 204, 0), 5), "ALBÜM", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 36), new java.awt.Color(204, 204, 204))); // NOI18N
        albumPane1.setForeground(new java.awt.Color(102, 102, 102));
        albumPane1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N

        albumTablosu.setBackground(new java.awt.Color(51, 51, 51));
        albumTablosu.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 24))); // NOI18N
        albumTablosu.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        albumTablosu.setForeground(new java.awt.Color(204, 204, 204));
        albumTablosu.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Albüm ID", "Albüm Adı", "Albüm Tür ID"
            }
        ));
        albumTablosu.setDoubleBuffered(true);
        albumTablosu.setRowHeight(30);
        albumTablosu.setSelectionBackground(new java.awt.Color(255, 204, 0));
        albumTablosu.setSelectionForeground(new java.awt.Color(102, 102, 102));
        albumTablosu.setShowVerticalLines(false);
        albumPane1.setViewportView(albumTablosu);

        albumEklePanel.add(albumPane1);
        albumPane1.setBounds(50, 30, 500, 830);

        albumSİlButon.setBackground(new java.awt.Color(255, 204, 0));
        albumSİlButon.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        albumSİlButon.setForeground(new java.awt.Color(51, 51, 51));
        albumSİlButon.setText("SİL");
        albumSİlButon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                albumSİlButonActionPerformed(evt);
            }
        });
        albumEklePanel.add(albumSİlButon);
        albumSİlButon.setBounds(580, 810, 290, 60);

        albumGuncelleButon.setBackground(new java.awt.Color(255, 204, 0));
        albumGuncelleButon.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        albumGuncelleButon.setForeground(new java.awt.Color(51, 51, 51));
        albumGuncelleButon.setText("GÜNCELLE");
        albumGuncelleButon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                albumGuncelleButonActionPerformed(evt);
            }
        });
        albumEklePanel.add(albumGuncelleButon);
        albumGuncelleButon.setBounds(580, 700, 290, 60);

        albumEkleButon.setBackground(new java.awt.Color(255, 204, 0));
        albumEkleButon.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        albumEkleButon.setForeground(new java.awt.Color(51, 51, 51));
        albumEkleButon.setText("EKLE");
        albumEkleButon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                albumEkleButonActionPerformed(evt);
            }
        });
        albumEklePanel.add(albumEkleButon);
        albumEkleButon.setBounds(580, 590, 290, 60);

        albumIdGir.setBackground(new java.awt.Color(51, 51, 51));
        albumIdGir.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        albumIdGir.setForeground(new java.awt.Color(204, 204, 204));
        albumIdGir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                albumIdGirActionPerformed(evt);
            }
        });
        albumEklePanel.add(albumIdGir);
        albumIdGir.setBounds(590, 110, 270, 50);

        albumAdiGir.setBackground(new java.awt.Color(51, 51, 51));
        albumAdiGir.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        albumAdiGir.setForeground(new java.awt.Color(204, 204, 204));
        albumAdiGir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                albumAdiGirActionPerformed(evt);
            }
        });
        albumEklePanel.add(albumAdiGir);
        albumAdiGir.setBounds(590, 290, 270, 50);

        albumTurIDGir.setBackground(new java.awt.Color(51, 51, 51));
        albumTurIDGir.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        albumTurIDGir.setForeground(new java.awt.Color(204, 204, 204));
        albumTurIDGir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                albumTurIDGirActionPerformed(evt);
            }
        });
        albumEklePanel.add(albumTurIDGir);
        albumTurIDGir.setBounds(590, 450, 270, 50);

        a6.setBackground(new java.awt.Color(204, 204, 204));
        a6.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        a6.setForeground(new java.awt.Color(204, 204, 204));
        a6.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 204, 0), 4), "Albüm Tür ID", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 18), new java.awt.Color(204, 204, 204))); // NOI18N
        albumEklePanel.add(a6);
        a6.setBounds(580, 420, 290, 100);

        a7.setBackground(new java.awt.Color(204, 204, 204));
        a7.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        a7.setForeground(new java.awt.Color(204, 204, 204));
        a7.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 204, 0), 4), "Albüm ID", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 18), new java.awt.Color(204, 204, 204))); // NOI18N
        albumEklePanel.add(a7);
        a7.setBounds(580, 80, 290, 100);

        a8.setBackground(new java.awt.Color(204, 204, 204));
        a8.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        a8.setForeground(new java.awt.Color(204, 204, 204));
        a8.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 204, 0), 4), "Albüm Adı", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 18), new java.awt.Color(204, 204, 204))); // NOI18N
        albumEklePanel.add(a8);
        a8.setBounds(580, 260, 290, 100);

        Album.add(albumEklePanel);
        albumEklePanel.setBounds(998, 0, 920, 1030);

        adminSekmeler.addTab("ALBÜM", Album);

        calmaListesiPanel.setLayout(null);

        calmaListesiGoruntulePanel.setBackground(new java.awt.Color(46, 46, 46));
        calmaListesiGoruntulePanel.setForeground(new java.awt.Color(51, 51, 51));
        calmaListesiGoruntulePanel.setLayout(null);

        calmaListesiScrollPane.setBackground(new java.awt.Color(102, 102, 102));
        calmaListesiScrollPane.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 204, 0), 5), "ÇALMA LİSTESİ ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 36), new java.awt.Color(204, 204, 204))); // NOI18N
        calmaListesiScrollPane.setForeground(new java.awt.Color(102, 102, 102));
        calmaListesiScrollPane.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N

        calmaListesiTablosu.setBackground(new java.awt.Color(51, 51, 51));
        calmaListesiTablosu.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 24))); // NOI18N
        calmaListesiTablosu.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        calmaListesiTablosu.setForeground(new java.awt.Color(204, 204, 204));
        calmaListesiTablosu.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Çalma Listesi ID", "Kullanıcı ID", "Şarkı Tür ID", "Adı"
            }
        ));
        calmaListesiTablosu.setDoubleBuffered(true);
        calmaListesiTablosu.setRowHeight(30);
        calmaListesiTablosu.setSelectionBackground(new java.awt.Color(255, 204, 0));
        calmaListesiTablosu.setSelectionForeground(new java.awt.Color(102, 102, 102));
        calmaListesiTablosu.setShowVerticalLines(false);
        calmaListesiScrollPane.setViewportView(calmaListesiTablosu);
        if (calmaListesiTablosu.getColumnModel().getColumnCount() > 0) {
            calmaListesiTablosu.getColumnModel().getColumn(0).setResizable(false);
            calmaListesiTablosu.getColumnModel().getColumn(2).setResizable(false);
        }

        calmaListesiGoruntulePanel.add(calmaListesiScrollPane);
        calmaListesiScrollPane.setBounds(70, 30, 790, 840);

        calmaListesiPanel.add(calmaListesiGoruntulePanel);
        calmaListesiGoruntulePanel.setBounds(0, 0, 960, 1020);

        calmaListesiSarkilarPanel.setBackground(new java.awt.Color(70, 70, 70));
        calmaListesiSarkilarPanel.setLayout(null);

        calmaListesiSarkilarScrollPane.setBackground(new java.awt.Color(102, 102, 102));
        calmaListesiSarkilarScrollPane.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 204, 0), 5), "ÇALMA LİSTESİ ŞARKILAR", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 36), new java.awt.Color(204, 204, 204))); // NOI18N
        calmaListesiSarkilarScrollPane.setForeground(new java.awt.Color(102, 102, 102));
        calmaListesiSarkilarScrollPane.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N

        calmaListesiSarkilarTablosu.setBackground(new java.awt.Color(51, 51, 51));
        calmaListesiSarkilarTablosu.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 24))); // NOI18N
        calmaListesiSarkilarTablosu.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        calmaListesiSarkilarTablosu.setForeground(new java.awt.Color(204, 204, 204));
        calmaListesiSarkilarTablosu.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Çalma Listesi Şarkılar ID", "Çalma Listesi ID", "Sanatçı Şarkı ID"
            }
        ));
        calmaListesiSarkilarTablosu.setDoubleBuffered(true);
        calmaListesiSarkilarTablosu.setRowHeight(30);
        calmaListesiSarkilarTablosu.setSelectionBackground(new java.awt.Color(255, 204, 0));
        calmaListesiSarkilarTablosu.setSelectionForeground(new java.awt.Color(102, 102, 102));
        calmaListesiSarkilarTablosu.setShowVerticalLines(false);
        calmaListesiSarkilarScrollPane.setViewportView(calmaListesiSarkilarTablosu);
        if (calmaListesiSarkilarTablosu.getColumnModel().getColumnCount() > 0) {
            calmaListesiSarkilarTablosu.getColumnModel().getColumn(0).setResizable(false);
            calmaListesiSarkilarTablosu.getColumnModel().getColumn(2).setResizable(false);
        }

        calmaListesiSarkilarPanel.add(calmaListesiSarkilarScrollPane);
        calmaListesiSarkilarScrollPane.setBounds(100, 30, 770, 840);

        calmaListesiPanel.add(calmaListesiSarkilarPanel);
        calmaListesiSarkilarPanel.setBounds(958, 0, 960, 1030);

        adminSekmeler.addTab("ÇALMA LİSTESİ", calmaListesiPanel);

        kullaniciPanel.setLayout(null);

        kullanicilarPanel.setBackground(new java.awt.Color(46, 46, 46));
        kullanicilarPanel.setForeground(new java.awt.Color(51, 51, 51));
        kullanicilarPanel.setLayout(null);

        kullanicilarScrollPane.setBackground(new java.awt.Color(102, 102, 102));
        kullanicilarScrollPane.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 204, 0), 5), "KULLANICILAR", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 36), new java.awt.Color(204, 204, 204))); // NOI18N
        kullanicilarScrollPane.setForeground(new java.awt.Color(102, 102, 102));
        kullanicilarScrollPane.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N

        kullaniciTablosu.setBackground(new java.awt.Color(51, 51, 51));
        kullaniciTablosu.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 24))); // NOI18N
        kullaniciTablosu.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        kullaniciTablosu.setForeground(new java.awt.Color(204, 204, 204));
        kullaniciTablosu.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Kullanıcı ID", "Kullanıcı Adı", "Email", "Sifre", "Ulke ID", "Ödendi mi", "Abonelik ID"
            }
        ));
        kullaniciTablosu.setDoubleBuffered(true);
        kullaniciTablosu.setFocusCycleRoot(true);
        kullaniciTablosu.setRowHeight(30);
        kullaniciTablosu.setSelectionBackground(new java.awt.Color(255, 204, 0));
        kullaniciTablosu.setSelectionForeground(new java.awt.Color(102, 102, 102));
        kullaniciTablosu.setShowVerticalLines(false);
        kullanicilarScrollPane.setViewportView(kullaniciTablosu);

        kullanicilarPanel.add(kullanicilarScrollPane);
        kullanicilarScrollPane.setBounds(30, 20, 1290, 390);

        kullaniciPanel.add(kullanicilarPanel);
        kullanicilarPanel.setBounds(0, 0, 1370, 470);

        kullaniciTakipPanel.setBackground(new java.awt.Color(68, 68, 68));
        kullaniciTakipPanel.setLayout(null);

        kullaniciTakipScrollPane1.setBackground(new java.awt.Color(51, 51, 51));
        kullaniciTakipScrollPane1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 204, 0), 5), "KULLANICI TAKİP", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 36), new java.awt.Color(204, 204, 204))); // NOI18N

        kullaniciTakipTablosu1.setBackground(new java.awt.Color(51, 51, 51));
        kullaniciTakipTablosu1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        kullaniciTakipTablosu1.setForeground(new java.awt.Color(204, 204, 204));
        kullaniciTakipTablosu1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Kullanıcı Takip ID", "Takip Eden Kullanıcı ID", "Takip Edilen Kullanıcı ID"
            }
        ));
        kullaniciTakipTablosu1.setRowHeight(30);
        kullaniciTakipScrollPane1.setViewportView(kullaniciTakipTablosu1);
        if (kullaniciTakipTablosu1.getColumnModel().getColumnCount() > 0) {
            kullaniciTakipTablosu1.getColumnModel().getColumn(0).setHeaderValue("Kullanıcı Takip ID");
        }

        kullaniciTakipPanel.add(kullaniciTakipScrollPane1);
        kullaniciTakipScrollPane1.setBounds(30, 20, 820, 400);

        abonelikScrollPane.setBackground(new java.awt.Color(51, 51, 51));
        abonelikScrollPane.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 204, 0), 5), "Abonelik", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 36), new java.awt.Color(204, 204, 204))); // NOI18N

        abonelikTablosu.setBackground(new java.awt.Color(51, 51, 51));
        abonelikTablosu.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        abonelikTablosu.setForeground(new java.awt.Color(204, 204, 204));
        abonelikTablosu.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Abonelik ID", "Abonelik Türü"
            }
        ));
        abonelikTablosu.setRowHeight(30);
        abonelikScrollPane.setViewportView(abonelikTablosu);

        kullaniciTakipPanel.add(abonelikScrollPane);
        abonelikScrollPane.setBounds(890, 20, 430, 200);

        abonelikIDGir.setBackground(new java.awt.Color(51, 51, 51));
        abonelikIDGir.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        abonelikIDGir.setForeground(new java.awt.Color(204, 204, 204));
        abonelikIDGir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                abonelikIDGirActionPerformed(evt);
            }
        });
        kullaniciTakipPanel.add(abonelikIDGir);
        abonelikIDGir.setBounds(900, 270, 270, 30);

        a4.setBackground(new java.awt.Color(204, 204, 204));
        a4.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        a4.setForeground(new java.awt.Color(204, 204, 204));
        a4.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 204, 0), 4), "Abonelik ID", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 18), new java.awt.Color(204, 204, 204))); // NOI18N
        kullaniciTakipPanel.add(a4);
        a4.setBounds(890, 230, 290, 80);

        abonelikTuruGir.setBackground(new java.awt.Color(51, 51, 51));
        abonelikTuruGir.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        abonelikTuruGir.setForeground(new java.awt.Color(204, 204, 204));
        abonelikTuruGir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                abonelikTuruGirActionPerformed(evt);
            }
        });
        kullaniciTakipPanel.add(abonelikTuruGir);
        abonelikTuruGir.setBounds(900, 380, 270, 30);

        a5.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        a5.setForeground(new java.awt.Color(204, 204, 204));
        a5.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 204, 0), 4), "Abonelik Türü", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 18), new java.awt.Color(204, 204, 204))); // NOI18N
        kullaniciTakipPanel.add(a5);
        a5.setBounds(890, 340, 290, 80);

        abonelikEkleButon.setBackground(new java.awt.Color(255, 204, 0));
        abonelikEkleButon.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        abonelikEkleButon.setForeground(new java.awt.Color(51, 51, 51));
        abonelikEkleButon.setText("EKLE");
        abonelikEkleButon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                abonelikEkleButonActionPerformed(evt);
            }
        });
        kullaniciTakipPanel.add(abonelikEkleButon);
        abonelikEkleButon.setBounds(1200, 300, 150, 60);

        kullaniciPanel.add(kullaniciTakipPanel);
        kullaniciTakipPanel.setBounds(0, 470, 1370, 500);

        adminPanel.setBackground(new java.awt.Color(102, 102, 102));
        adminPanel.setLayout(null);

        adminScrollPane.setBackground(new java.awt.Color(51, 51, 51));
        adminScrollPane.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 204, 0), 5), "ADMİN", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 36), new java.awt.Color(204, 204, 204))); // NOI18N

        adminTablosu.setBackground(new java.awt.Color(51, 51, 51));
        adminTablosu.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        adminTablosu.setForeground(new java.awt.Color(204, 204, 204));
        adminTablosu.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Admin ID", "Kullanıcı Adı", "Email", "Şifre"
            }
        ));
        adminTablosu.setRowHeight(30);
        adminScrollPane.setViewportView(adminTablosu);

        adminPanel.add(adminScrollPane);
        adminScrollPane.setBounds(60, 20, 440, 870);

        kullaniciPanel.add(adminPanel);
        adminPanel.setBounds(1370, 0, 550, 970);

        adminSekmeler.addTab("KULLANICILAR", kullaniciPanel);

        getContentPane().add(adminSekmeler);
        adminSekmeler.setBounds(0, 0, 1920, 1080);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void sanatciSilButonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sanatciSilButonActionPerformed
      int silkontrol2=0;
           try {

                Connection myConn = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/muzik_dosyam", "root", "Ok.20002000");
                Statement myStat = (Statement) myConn.createStatement();
                ResultSet rs = myStat.executeQuery("SELECT * FROM calma_listesi_sarkilar");
                
                while (rs.next()) {
                    if (sanatciSarkiIdGir.getText().equals(rs.getString("SanatciSarki_Id"))) {
                        silkontrol1=1;
                        silkontrol2=1;
                        
                    }
                    else {
                        if(silkontrol1==0)
                        {
                           silkontrol++; 
                        }
                        
                        sanatcisarkiId = sarkiIDGir.getText();
                       
                    }
                       if (silkontrol1==1)
                   {
                       String tbData[] = {calmalistesisarkilarid,calmasarkilarid,calmasanatcisarkid};
                DefaultTableModel tblModel = (DefaultTableModel) calmaListesiSarkilarTablosu.getModel();
                    tblModel.removeRow(silkontrol);
                    silkontrol1=0;
                   }
                }
                
                   
                  
                     
                        
                        
                    try {
                      
                        if(silkontrol2==1)
                        {
                            System.out.println(silkontrol1);
                           CalmaListesindenSanatcıSarkileSil();    
                        }
                        silkontrol1=0;
                         silkontrol=0;
                         silkontrol2=0;
                        
                          

           
        } catch (SQLException ex) {
            Logger.getLogger(AdminSayfasi.class.getName()).log(Level.SEVERE, null, ex);
        }
        }
        catch (SQLException ex) {
                java.util.logging.Logger.getLogger(KayitEkrani.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
            } 
        
        
        
        silkontrol2=0;
        
        
        
        
        
        
        
        try {

                Connection myConn = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/muzik_dosyam", "root", "Ok.20002000");
                Statement myStat = (Statement) myConn.createStatement();
                ResultSet rs = myStat.executeQuery("SELECT * FROM album_sarkilar");
                
                while (rs.next()) {
                    if (sanatciSarkiIdGir.getText().equals(rs.getString("SanatciSarki_Id"))) {
                        silkontrol1=1;
                        silkontrol2=1;
                    }
                    else {
                        if(silkontrol1==0)
                        {
                           silkontrol++; 
                        }
                        
                        sanatcisarkiId = sarkiIDGir.getText();
                       
                    }
                     if (silkontrol1==1)
                   {
                        String tbData1[] = { albumsarkilarıd,albumId, sanatcisarkiId};
                    DefaultTableModel tblModel = (DefaultTableModel)albumSarkilarTablosu.getModel();
                    tblModel.removeRow(silkontrol);
                    silkontrol1=0;
                   }
                  
                }
                
                   
                     
                        
                        
                    try {
                      
                        if(silkontrol2==1)
                        {
                            System.out.println(silkontrol1);
                          AlbumSarkilardanSanatciSarkiSil();    
                        }
                        silkontrol1=0;
                         silkontrol=0;
                         silkontrol2=0;
                                 
                        
                          

           
        } catch (SQLException ex) {
            Logger.getLogger(AdminSayfasi.class.getName()).log(Level.SEVERE, null, ex);
        }
        }
        catch (SQLException ex) {
                java.util.logging.Logger.getLogger(KayitEkrani.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
            }    
        
        
        
        
         silkontrol1=0;
        silkontrol=0;
        
        
        
        
        try {

                Connection myConn = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/muzik_dosyam", "root", "Ok.20002000");
                Statement myStat = (Statement) myConn.createStatement();
                ResultSet rs = myStat.executeQuery("SELECT * FROM sanatci_sarki");
                while (rs.next()) {
                    if (sanatciSarkiIdGir.getText().equals(rs.getString("SanatciSarki_Id"))) {
                        silkontrol1=1;
                        
                    }
                    else {
                        if(silkontrol1==0)
                        {
                           silkontrol++; 
                        }
                        
                        sanatcisarkiId = sarkiIDGir.getText();
                       
                    }

                }
                
                    String tbData1[] = {sanatcisarkid, sanatciId, sarkId, dinlenmesayisi};
                    DefaultTableModel tblModel = (DefaultTableModel) sanatciTablosu.getModel();
                    tblModel.removeRow(silkontrol);
                     
                       if(silkontrol1==0)
                        {
                            JOptionPane.showMessageDialog(null, "Boyle bir eslesme yok");
                        }
                        
                    try {
                        if(silkontrol1==1)
                        {
                            System.out.println(silkontrol1);
                            SanatciSarkiSil();  
                        }
                         
                           silkontrol1=0;
                         silkontrol=0;

           
        } catch (SQLException ex) {
            Logger.getLogger(AdminSayfasi.class.getName()).log(Level.SEVERE, null, ex);
        }
        }
        catch (SQLException ex) {
                java.util.logging.Logger.getLogger(KayitEkrani.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
            }    
    }//GEN-LAST:event_sanatciSilButonActionPerformed

    private void albumSarkilarEkleButonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_albumSarkilarEkleButonActionPerformed
   int albumsarkiturid2=0;
    int sarkiturid2=0;
        if (albumSarkilarIDGir.getText().length() == 0 || albumSarkilarAlbumIDGir.getText().length() == 0 || albumSarkilarSanatciSarkiIDGir .getText().length() == 0 ) {
            JOptionPane.showMessageDialog(null, "lutfen alanlari doldurunuz");
        } else { 
               
               
            try {
                Connection myConn = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/muzik_dosyam", "root", "Ok.20002000");
                Statement myStat = (Statement) myConn.createStatement();
                Statement myStat2 = (Statement) myConn.createStatement();
                Statement myStat3 = (Statement) myConn.createStatement();
                ResultSet rs = myStat.executeQuery("SELECT * FROM album_sarkilar");
                ResultSet rs2;
                ResultSet rs3;
                rs2 = myStat2.executeQuery("SELECT AlbumTur_Id FROM album WHERE Album_Id="+albumSarkilarAlbumIDGir.getText());//sql sorgusu;
                  while(rs2.next())
                {
                    albumsarkiturid2=rs2.getInt("AlbumTur_Id");
                } 
               rs3=myStat3.executeQuery("SELECT SarkiTur_Id FROM sarki WHERE Sarki_Id in(SELECT Sarki_Id FROM sanatci_sarki WHERE SanatciSarki_Id="+albumSarkilarSanatciSarkiIDGir.getText()+")") ;
                  while(rs3.next())
                  {
                     sarkiturid2=rs3.getInt("SarkiTur_Id");
                  }
                  
                  
                  System.out.println(albumsarkiturid2);
                  System.out.println(sarkiturid2);
                  
                while (rs.next()) {
                    if (albumSarkilarIDGir.getText().equals(rs.getString("AlbumSarkilar_Id"))) {
                        JOptionPane.showMessageDialog(null, "Böyle bir eslesme  var");
                        albumsarkilarkontrol++;
                    }
                    else
                    {
                        albumsarkilarıd = albumSarkilarIDGir.getText();
                        albumId = albumSarkilarAlbumIDGir.getText();
                       sanatcisarkiId= albumSarkilarSanatciSarkiIDGir .getText();
                        
                    }

                }
                if(albumsarkilarkontrol==0 && albumsarkiturid2==sarkiturid2)
                {
                    try {
                        AlbumSarkilarEkle();
                    } catch (SQLException ex) {
                        Logger.getLogger(AdminSayfasi.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    String tbData1[] = { albumsarkilarıd,albumId, sanatcisarkiId};
                    DefaultTableModel tblModel1 = (DefaultTableModel) albumSarkilarTablosu.getModel();
                    tblModel1.addRow(tbData1);
                }
                albumsarkilarkontrol=0;

            } catch (SQLException ex) {
                java.util.logging.Logger.getLogger(KayitEkrani.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
            }

        }
        if(albumsarkiturid2!=sarkiturid2)
        {
            JOptionPane.showMessageDialog(null, "Album turu ile Sarki turu eslesmiyor");
        }
        albumsarkiturid2=0;
        sarkiturid2=0;
    }//GEN-LAST:event_albumSarkilarEkleButonActionPerformed

    private void albumSarkilarIDGirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_albumSarkilarIDGirActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_albumSarkilarIDGirActionPerformed

    private void albumSarkilarAlbumIDGirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_albumSarkilarAlbumIDGirActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_albumSarkilarAlbumIDGirActionPerformed

    private void albumSarkilarSanatciSarkiIDGirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_albumSarkilarSanatciSarkiIDGirActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_albumSarkilarSanatciSarkiIDGirActionPerformed

    private void albumIdGirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_albumIdGirActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_albumIdGirActionPerformed

    private void albumAdiGirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_albumAdiGirActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_albumAdiGirActionPerformed

    private void albumEkleButonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_albumEkleButonActionPerformed
      if (albumIdGir.getText().length() == 0 || albumAdiGir.getText().length() == 0 ) {
            JOptionPane.showMessageDialog(null, "lutfen alanlari doldurunuz");
        } else {

            try {
                Connection myConn = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/muzik_dosyam", "root", "Ok.20002000");
                Statement myStat = (Statement) myConn.createStatement();
                ResultSet rs = myStat.executeQuery("SELECT * FROM album");

                while (rs.next()) {
                    if (albumIdGir.getText().equals(rs.getString("Album_Id"))) {
                        JOptionPane.showMessageDialog(null, "Böyle bir Album var");
                        albumkontrol++;
                    }
                    else
                    {
                        albumid = albumIdGir.getText();
                        albumad = albumAdiGir.getText();
                        albumturid= String.valueOf(albumTurIDGir.getSelectedIndex()+1);
                        
                       
                    }

                }
                if(albumkontrol==0)
                {
                    try {
                        AlbumEkle();
                    } catch (SQLException ex) {
                        Logger.getLogger(AdminSayfasi.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    String tbData1[] = {albumid, albumad, albumturid};
                    DefaultTableModel tblModel1 = (DefaultTableModel) albumTablosu.getModel();
                    tblModel1.addRow(tbData1);
                }
                albumkontrol=0;

            } catch (SQLException ex) {
                java.util.logging.Logger.getLogger(KayitEkrani.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
            }

        }        
    }//GEN-LAST:event_albumEkleButonActionPerformed

    private void albumGuncelleButonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_albumGuncelleButonActionPerformed
       try {

                Connection myConn = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/muzik_dosyam", "root", "Ok.20002000");
                Statement myStat = (Statement) myConn.createStatement();
                ResultSet rs = myStat.executeQuery("SELECT * FROM album");
                while (rs.next()) {
                    if (albumIdGir.getText().equals(rs.getString("Album_Id"))) {
                        silkontrol1=1;
                     
                    }
                        if(silkontrol1==0)
                        {
                           silkontrol++; 
                        }
                        
                       albumid = albumIdGir.getText();
                        albumad = albumAdiGir.getText();
                        albumturid= String.valueOf(albumTurIDGir.getSelectedIndex()+1);
                       
                }
                
                    
                       if(silkontrol1==1)
                        {
                         String tbData1[] = {albumid, albumad, albumturid};
                    DefaultTableModel tblModel1 = (DefaultTableModel) albumTablosu.getModel();
                    tblModel1.removeRow(silkontrol);
                        }
                        
                    try {
                        if(silkontrol1==1)
                        {
                            System.out.println(silkontrol1);
                            AlbumGuncelle();  
                        }
                         
                           

           
        } catch (SQLException ex) {
            Logger.getLogger(AdminSayfasi.class.getName()).log(Level.SEVERE, null, ex);
        }
        }
        catch (SQLException ex) {
                java.util.logging.Logger.getLogger(KayitEkrani.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
            }   
                     
                        if(silkontrol1==1)
                        {
                            String tbData1[] = {albumid, albumad, albumturid};
                    DefaultTableModel tblModel1 = (DefaultTableModel) albumTablosu.getModel();
                    tblModel1.addRow(tbData1);
                        }
                         silkontrol1=0;
                         silkontrol=0;
    }//GEN-LAST:event_albumGuncelleButonActionPerformed

    private void albumSİlButonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_albumSİlButonActionPerformed
      int silkontrol2=0;
        try {

                Connection myConn = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/muzik_dosyam", "root", "Ok.20002000");
                Statement myStat = (Statement) myConn.createStatement();
                ResultSet rs = myStat.executeQuery("SELECT * FROM album_sarkilar");
                
                while (rs.next()) {
                    if (albumIdGir.getText().equals(rs.getString("Album_Id"))) {
                        silkontrol1=1;
                        silkontrol2=1;
                    }
                    else {
                        if(silkontrol1==0)
                        {
                           silkontrol++; 
                        }
                        
                        sanatcisarkiId = sarkiIDGir.getText();
                       
                    }
                     if (silkontrol1==1)
                   {
                        String tbData1[] = { albumsarkilarıd,albumId, sanatcisarkiId};
                    DefaultTableModel tblModel1 = (DefaultTableModel) albumSarkilarTablosu.getModel();
                    tblModel1.removeRow(silkontrol);
                    silkontrol1=0;
                    
                   }

                }
                
                  
                     
                    try {
                      
                        if(silkontrol2==1)
                        {
                            System.out.println(silkontrol1);
                           AlbumileAlbumSarkilarSil();    
                        }
                        silkontrol1=0;
                         silkontrol=0;
                        
                          

           
        } catch (SQLException ex) {
            Logger.getLogger(AdminSayfasi.class.getName()).log(Level.SEVERE, null, ex);
        }
        }
        catch (SQLException ex) {
                java.util.logging.Logger.getLogger(KayitEkrani.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
            }    
         silkontrol1=0;
        silkontrol=0;
        try {

                Connection myConn = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/muzik_dosyam", "root", "Ok.20002000");
                Statement myStat = (Statement) myConn.createStatement();
                ResultSet rs = myStat.executeQuery("SELECT * FROM album");
                while (rs.next()) {
                    if (albumIdGir.getText().equals(rs.getString("Album_Id"))) {
                        silkontrol1=1;
                     
                    }
                    else {
                        if(silkontrol1==0)
                        {
                           silkontrol++; 
                        }
                        
                        sanatcisarkiId = sarkiIDGir.getText();
                       
                    }

                }
                
                    String tbData1[] = {albumid, albumad, albumturid};
                    DefaultTableModel tblModel1 = (DefaultTableModel) albumTablosu.getModel();
                    tblModel1.removeRow(silkontrol);
                       if(silkontrol1==0)
                        {
                            JOptionPane.showMessageDialog(null, "Boyle bir eslesme yok");
                        }
                        
                    try {
                        if(silkontrol1==1)
                        {
                            System.out.println(silkontrol1);
                            AlbumSil();  
                        }
                         
                           silkontrol1=0;
                         silkontrol=0;

           
        } catch (SQLException ex) {
            Logger.getLogger(AdminSayfasi.class.getName()).log(Level.SEVERE, null, ex);
        }
        }
        catch (SQLException ex) {
                java.util.logging.Logger.getLogger(KayitEkrani.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
            }    
    }//GEN-LAST:event_albumSİlButonActionPerformed

    private void sanatciEkleButonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sanatciEkleButonActionPerformed
 if (sanatciIDGir.getText().length() == 0 || sarkiIDGir.getText().length() == 0  ||dinlenmeSayisiGir.getText().length() == 0 ||sanatciSarkiIdGir.getText().length() == 0) {
            JOptionPane.showMessageDialog(null, "lutfen alanlari doldurunuz");
        } else {
            
            try {
                Connection myConn = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/muzik_dosyam", "root", "Ok.20002000");
                Statement myStat = (Statement) myConn.createStatement();
                ResultSet rs = myStat.executeQuery("SELECT * FROM sanatci_sarki");

                while (rs.next()) {
                    if (sanatciSarkiIdGir.getText().equals(rs.getString("SanatciSarki_Id"))) {
                        JOptionPane.showMessageDialog(null, "Böyle bir sanatci_sarki eslesmesi   var");
                        sanatcisarkikontrol++;
                    }
                    else
                    {
                    sanatcisarkid = sanatciSarkiIdGir.getText();
                    sanatciId= sanatciIDGir.getText();
                    sarkId =sarkiIDGir.getText() ;
                    dinlenmesayisi=dinlenmeSayisiGir.getText();
    
                    
                    }
                    
                }
                if(sanatcisarkikontrol==0)
                {
                    try {
                SanatciSarkiEkle();
            } catch (SQLException ex) {
                Logger.getLogger(AdminSayfasi.class.getName()).log(Level.SEVERE, null, ex);
            }
                    String tbData1[] = {sanatcisarkid, sanatciId, sarkId, dinlenmesayisi};
                DefaultTableModel tblModel1 = (DefaultTableModel) sanatciTablosu.getModel();
                tblModel1.addRow(tbData1);
                }
                sanatcisarkikontrol=0;

            } catch (SQLException ex) {
                java.util.logging.Logger.getLogger(KayitEkrani.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
            }

        }               
    }//GEN-LAST:event_sanatciEkleButonActionPerformed

    private void dinlenmeSayisiGirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dinlenmeSayisiGirActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_dinlenmeSayisiGirActionPerformed

    private void sarkiIDGirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sarkiIDGirActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_sarkiIDGirActionPerformed

    private void sanatciIDGirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sanatciIDGirActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_sanatciIDGirActionPerformed

    private void sanatciSarkiIdGirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sanatciSarkiIdGirActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_sanatciSarkiIdGirActionPerformed

    private void sanatciSarkiEkleButonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sanatciSarkiEkleButonActionPerformed
 if (sanatciSarkiSanatciIDGir.getText().length() == 0 || sarkiSanatciSanatciAdiGir.getText().length() == 0  || sarkiSanatciUlkeIDGir.getText().length() == 0) {
            JOptionPane.showMessageDialog(null, "lutfen alanlari doldurunuz");
        } else {
            
            try {
                Connection myConn = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/muzik_dosyam", "root", "Ok.20002000");
                Statement myStat = (Statement) myConn.createStatement();
                ResultSet rs = myStat.executeQuery("SELECT * FROM sanatci");

                while (rs.next()) {
                    if (sanatciSarkiSanatciIDGir.getText().equals(rs.getString("Sanatci_Id"))) {
                        JOptionPane.showMessageDialog(null, "Böyle bir Sanatci  var");
                        sanatcikontrol++;
                    }
                    else
                    {
                    sanatcid = sanatciSarkiSanatciIDGir.getText();
                    sanatciad = sarkiSanatciSanatciAdiGir.getText();
                    sanatciulkeid=sarkiSanatciUlkeIDGir.getText();
    
                    
                    }
                    
                }
                if(sanatcikontrol==0)
                {
                    try {
                SanatciEkle();
            } catch (SQLException ex) {
                Logger.getLogger(AdminSayfasi.class.getName()).log(Level.SEVERE, null, ex);
            }
                    String tbData1[] = {sanatcid, sanatciad,sanatciulkeid};
                DefaultTableModel tblModel1 = (DefaultTableModel) sanatciTablosu1.getModel();
                tblModel1.addRow(tbData1);
                }
                sanatcikontrol=0;

            } catch (SQLException ex) {
                java.util.logging.Logger.getLogger(KayitEkrani.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
            }

        }       
    }//GEN-LAST:event_sanatciSarkiEkleButonActionPerformed

    private void sarkiSanatciUlkeIDGirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sarkiSanatciUlkeIDGirActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_sarkiSanatciUlkeIDGirActionPerformed

    private void sarkiSanatciSanatciAdiGirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sarkiSanatciSanatciAdiGirActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_sarkiSanatciSanatciAdiGirActionPerformed

    private void sanatciSarkiSanatciIDGirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sanatciSarkiSanatciIDGirActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_sanatciSarkiSanatciIDGirActionPerformed

    private void ulkeEkleButon3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ulkeEkleButon3ActionPerformed
          if (ulkeIdGir.getText().length() == 0 || ulkeAdiGir.getText().length() == 0) {
            JOptionPane.showMessageDialog(null, "lutfen alanlari doldurunuz");
        } else {
            
            try {
                Connection myConn = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/muzik_dosyam", "root", "Ok.20002000");
                Statement myStat = (Statement) myConn.createStatement();
                ResultSet rs = myStat.executeQuery("SELECT * FROM ulkeler");

                while (rs.next()) {
                    if (ulkeIdGir.getText().equals(rs.getString("Ulke_Id"))) {
                        JOptionPane.showMessageDialog(null, "Böyle bir ulke var");
                        ulkekontrol++;
                    }
                    else
                    {
                    ulkeid = ulkeIdGir.getText();
                    ulkead = ulkeAdiGir.getText();
                    
                    }
                    
                }
                if(ulkekontrol==0)
                {
                    try {
                Ulkekle();
            } catch (SQLException ex) {
                Logger.getLogger(AdminSayfasi.class.getName()).log(Level.SEVERE, null, ex);
            }
                    String tbData1[] = {ulkeid, ulkead};
                DefaultTableModel tblModel1 = (DefaultTableModel) ulkeTablosu.getModel();
                tblModel1.addRow(tbData1);
                }
                ulkekontrol=0;

            } catch (SQLException ex) {
                java.util.logging.Logger.getLogger(KayitEkrani.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
            }

        }
    }//GEN-LAST:event_ulkeEkleButon3ActionPerformed

    private void ulkeAdiGirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ulkeAdiGirActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ulkeAdiGirActionPerformed

    private void ulkeIdGirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ulkeIdGirActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ulkeIdGirActionPerformed

    private void sarkiEkleButonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sarkiEkleButonActionPerformed
        if (sarkiIdGir.getText().length() == 0 || sarkiAdiGir.getText().length() == 0 || sarkiTarihGir.getText().length() == 0 || sarkiSureGir.getText().length() == 0) {
            JOptionPane.showMessageDialog(null, "lutfen alanlari doldurunuz");
        } else {

            try {
                Connection myConn = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/muzik_dosyam", "root", "Ok.20002000");
                Statement myStat = (Statement) myConn.createStatement();
                ResultSet rs = myStat.executeQuery("SELECT * FROM sarki");

                while (rs.next()) {
                    if (sarkiIdGir.getText().equals(rs.getString("Sarki_Id"))) {
                        JOptionPane.showMessageDialog(null, "Böyle bir kullanıcı var");
                        sarkikontrol++;
                    }
                    else
                    {
                        sarkiId = sarkiIdGir.getText();
                        sarkiAdi = sarkiAdiGir.getText();
                        sarkiTarih = sarkiTarihGir.getText();
                        sarkiTurId= String.valueOf(sarkiTurSeccbox.getSelectedIndex()+1);
                        sarkiSuresi = String.valueOf(sarkiSureGir.getText());
                       
                    }

                }
                if(sarkikontrol==0)
                {
                    try {
                        sarkiEkle();
                    } catch (SQLException ex) {
                        Logger.getLogger(AdminSayfasi.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    String tbData1[] = {sarkiId, sarkiAdi, sarkiTarih, sarkiTurId, sarkiSuresi};
                    DefaultTableModel tblModel1 = (DefaultTableModel) sarkiTablosu.getModel();
                    tblModel1.addRow(tbData1);
                }
                sarkikontrol=0;

            } catch (SQLException ex) {
                java.util.logging.Logger.getLogger(KayitEkrani.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
            }

        }
    }//GEN-LAST:event_sarkiEkleButonActionPerformed

    private void sarkiTurSeccboxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sarkiTurSeccboxActionPerformed

    }//GEN-LAST:event_sarkiTurSeccboxActionPerformed

    private void sarkiSureGirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sarkiSureGirActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_sarkiSureGirActionPerformed

    private void sarkiIdGirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sarkiIdGirActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_sarkiIdGirActionPerformed

    private void sarkiAdiGirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sarkiAdiGirActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_sarkiAdiGirActionPerformed

    private void sarkiTarihGirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sarkiTarihGirActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_sarkiTarihGirActionPerformed

    private void sarkiSilButonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sarkiSilButonActionPerformed
        int silkontrol2=0;   
        try {

                Connection myConn = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/muzik_dosyam", "root", "Ok.20002000");
                Statement myStat = (Statement) myConn.createStatement();
                ResultSet rs = myStat.executeQuery("SELECT * FROM sanatci_sarki,calma_listesi_sarkilar WHERE sanatci_sarki.SanatciSarki_Id=calma_listesi_sarkilar.SanatciSarki_Id ");
                while (rs.next()) {
                      if (sarkiIdGir.getText().equals(rs.getString("Sarki_Id")) )
                      {
                          silkontrol1=1; 
                          silkontrol2=1;
                      }
        
                    
                         if(silkontrol1==0)
                        {
                           silkontrol++; 
                        }
                         
                           if(silkontrol1==1)
                 {
                     String tbData[] = {calmalistesisarkilarid,calmasarkilarid,calmasanatcisarkid};
                DefaultTableModel tblModel = (DefaultTableModel) calmaListesiSarkilarTablosu.getModel();
                    tblModel.removeRow(silkontrol);
                    silkontrol1=0;
                 }
                  
                }
                
                  
                
                    
                    try {        
                        if(silkontrol2==1)
                        {
                         
                            SarkileCalmalistesindenSil();  
                        }
                     silkontrol1=0;
                    silkontrol=0;
                    silkontrol2=0;
           
        } catch (SQLException ex) {
            Logger.getLogger(AdminSayfasi.class.getName()).log(Level.SEVERE, null, ex);
        }
        }
        catch (SQLException ex) {
                java.util.logging.Logger.getLogger(KayitEkrani.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
            } 
        
        
        
        
        
        try {

                Connection myConn = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/muzik_dosyam", "root", "Ok.20002000");
                Statement myStat = (Statement) myConn.createStatement();
                ResultSet rs = myStat.executeQuery("SELECT * FROM sanatci_sarki,album_sarkilar WHERE sanatci_sarki.SanatciSarki_Id=album_sarkilar.SanatciSarki_Id ");
                while (rs.next()) {
                      if (sarkiIdGir.getText().equals(rs.getString("Sarki_Id")) )
                      {
                          silkontrol1=1; 
                          silkontrol2=1;
                      }
        
                    
                         if(silkontrol1==0)
                        {
                           silkontrol++; 
                        }
                       if(silkontrol1==1)
                 {
                       String tbData1[] = { albumsarkilarıd,albumId, sanatcisarkiId};
                    DefaultTableModel tblModel1 = (DefaultTableModel) albumSarkilarTablosu.getModel();
                    tblModel1.removeRow(silkontrol);
                    silkontrol1=0;
                 
                 }
                }  
                
                   
                    
                    
                    try {        
                        if(silkontrol2==1)
                        {
                         
                            SarkileAlbumdenSil();  
                        }
                     silkontrol1=0;
                    silkontrol=0;
           
        } catch (SQLException ex) {
            Logger.getLogger(AdminSayfasi.class.getName()).log(Level.SEVERE, null, ex);
        }
        }
        catch (SQLException ex) {
                java.util.logging.Logger.getLogger(KayitEkrani.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
            } 
        
        try {

                Connection myConn = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/muzik_dosyam", "root", "Ok.20002000");
                Statement myStat = (Statement) myConn.createStatement();
                ResultSet rs = myStat.executeQuery("SELECT * FROM sanatci_sarki");
                while (rs.next()) {
                   
                        if (sarkiIdGir.getText().equals(rs.getString("Sarki_Id"))) 
                        silkontrol1=1;
                    
                    
                        
                    
                    
                        if(silkontrol1==0)
                        {
                           silkontrol++; 
                        }
                        
                        sarkiId = sarkiIdGir.getText();
                       
                    

                }
                 if(silkontrol1==1)
                 {
                      String tbData1[] = {sanatcisarkid, sanatciId, sarkId, dinlenmesayisi};
                DefaultTableModel tblModel1 = (DefaultTableModel) sanatciTablosu.getModel();
                    tblModel1.removeRow(silkontrol);
                    
                 }
                    
                    
                    try {
                        if(silkontrol1==1)
                        {
                         sarkileSanatciSarkidanSil();    
                        }
                    silkontrol1=0;
                    silkontrol=0;
           
        } catch (SQLException ex) {
            Logger.getLogger(AdminSayfasi.class.getName()).log(Level.SEVERE, null, ex);
        }
        }
        catch (SQLException ex) {
                java.util.logging.Logger.getLogger(KayitEkrani.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
            }         
        
        
        try {

                Connection myConn = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/muzik_dosyam", "root", "Ok.20002000");
                Statement myStat = (Statement) myConn.createStatement();
                ResultSet rs = myStat.executeQuery("SELECT * FROM sarki");
                while (rs.next()) {
                    if (sarkiIdGir.getText().equals(rs.getString("Sarki_Id"))) {
                        silkontrol1=1;
                        
                    }
                    else {
                        if(silkontrol1==0)
                        {
                           silkontrol++; 
                        }
                        
                        sarkiId = sarkiIdGir.getText();
                       
                    }

                }
                
                    String tbData1[] = {sarkiId, sarkiAdi, sarkiTarih, sarkiTurId, sarkiSuresi};
                    DefaultTableModel tblModel = (DefaultTableModel) sarkiTablosu.getModel();
                    tblModel.removeRow(silkontrol);
                    
                    silkontrol1=0;
                    silkontrol=0;
                    try {        
            SarkiSil();
        } catch (SQLException ex) {
            Logger.getLogger(AdminSayfasi.class.getName()).log(Level.SEVERE, null, ex);
        }
        }
        catch (SQLException ex) {
                java.util.logging.Logger.getLogger(KayitEkrani.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
            }    
    }//GEN-LAST:event_sarkiSilButonActionPerformed

    private void sarkiTurIDGirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sarkiTurIDGirActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_sarkiTurIDGirActionPerformed

    private void turGirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_turGirActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_turGirActionPerformed

    private void turEkleButonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_turEkleButonActionPerformed
     if (sarkiTurIDGir.getText().length() == 0 || turGir.getText().length() == 0) {
            JOptionPane.showMessageDialog(null, "lutfen alanlari doldurunuz");
        } else {
            
            try {
                Connection myConn = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/muzik_dosyam", "root", "Ok.20002000");
                Statement myStat = (Statement) myConn.createStatement();
                ResultSet rs = myStat.executeQuery("SELECT * FROM tur");

                while (rs.next()) {
                    if (sarkiTurIDGir.getText().equals(rs.getString("SarkiTur_Id"))) {
                        JOptionPane.showMessageDialog(null, "Böyle bir Tur var");
                        turkontrol++;
                    }
                    else
                    {
                    turId = sarkiTurIDGir.getText();
                    tur = turGir.getText();
                    
                    }
                    
                }
                if(turkontrol==0)
                {
                    try {
               TurEkle();
            } catch (SQLException ex) {
                Logger.getLogger(AdminSayfasi.class.getName()).log(Level.SEVERE, null, ex);
            }
                    String tbData1[] = {turId, tur};
                DefaultTableModel tblModel1 = (DefaultTableModel) turTablosu.getModel();
                tblModel1.addRow(tbData1);
                }
                turkontrol=0;

            } catch (SQLException ex) {
                java.util.logging.Logger.getLogger(KayitEkrani.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
            }

        }      
    }//GEN-LAST:event_turEkleButonActionPerformed

    private void albumTurIDGirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_albumTurIDGirActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_albumTurIDGirActionPerformed

    private void abonelikIDGirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_abonelikIDGirActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_abonelikIDGirActionPerformed

    private void abonelikTuruGirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_abonelikTuruGirActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_abonelikTuruGirActionPerformed

    private void abonelikEkleButonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_abonelikEkleButonActionPerformed
        if (abonelikIDGir.getText().length() == 0 || abonelikTuruGir.getText().length() == 0) {
            JOptionPane.showMessageDialog(null, "lutfen alanlari doldurunuz");
        } else {
            
            try {
                Connection myConn = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/muzik_dosyam", "root", "Ok.20002000");
                Statement myStat = (Statement) myConn.createStatement();
                ResultSet rs = myStat.executeQuery("SELECT * FROM abonelik_turu");

                while (rs.next()) {
                    if (abonelikIDGir.getText().equals(rs.getString("Abonelik_Id"))) {
                        JOptionPane.showMessageDialog(null, "Böyle bir Tur var");
                        abonekontrol++;
                    }
                    else
                    {
                    abonelikid= abonelikIDGir.getText();
                    aboneliktur = abonelikTuruGir.getText();
                    
                    }
                    
                }
                if(abonekontrol==0)
                {
                    try {
              AbonelikEkle();
            } catch (SQLException ex) {
                Logger.getLogger(AdminSayfasi.class.getName()).log(Level.SEVERE, null, ex);
            }
                    String tbData1[] = {abonelikid,aboneliktur};
                DefaultTableModel tblModel1 = (DefaultTableModel) abonelikTablosu.getModel();
                tblModel1.addRow(tbData1);
                }
                abonekontrol=0;

            } catch (SQLException ex) {
                java.util.logging.Logger.getLogger(KayitEkrani.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
            }

        }      
    }//GEN-LAST:event_abonelikEkleButonActionPerformed

    private void sarkiSanatciSilButonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sarkiSanatciSilButonActionPerformed
     int silkontrol2=0;
        try {

                Connection myConn = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/muzik_dosyam", "root", "Ok.20002000");
                Statement myStat = (Statement) myConn.createStatement();
                ResultSet rs = myStat.executeQuery("SELECT * FROM sanatci_sarki,calma_listesi_sarkilar WHERE sanatci_sarki.SanatciSarki_Id=calma_listesi_sarkilar.SanatciSarki_Id");
                while (rs.next()) {
                   
                        if (sanatciSarkiSanatciIDGir.getText().equals(rs.getString("Sanatci_Id"))) 
                        {
                            silkontrol1=1;
                            silkontrol2=1;
                        }
                        
                        if(silkontrol1==0)
                        {
                           silkontrol++; 
                        }
                        
                       if(silkontrol1==1)
                 {
                        String tbData[] = {calmalistesisarkilarid,calmasarkilarid,calmasanatcisarkid};
                DefaultTableModel tblModel = (DefaultTableModel) calmaListesiSarkilarTablosu.getModel();
                    tblModel.removeRow(silkontrol);
                    silkontrol1=0;
                    
                 }
                       
                }
                 
                    
                    
                    try {
                        if(silkontrol2==1)
                        {
                         SanatcileCalmaListesindenSil();   
                        }
                    silkontrol1=0;
                    silkontrol=0;
                    silkontrol2=0;
           
        } catch (SQLException ex) {
            Logger.getLogger(AdminSayfasi.class.getName()).log(Level.SEVERE, null, ex);
        }
        }
        catch (SQLException ex) {
                java.util.logging.Logger.getLogger(KayitEkrani.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
            }
        
        
        
        
        try {

                Connection myConn = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/muzik_dosyam", "root", "Ok.20002000");
                Statement myStat = (Statement) myConn.createStatement();
                ResultSet rs = myStat.executeQuery("SELECT * FROM sanatci_sarki,album_sarkilar WHERE sanatci_sarki.SanatciSarki_Id=album_sarkilar.SanatciSarki_Id ");
                while (rs.next()) {
                      if (sanatciSarkiSanatciIDGir.getText().equals(rs.getString("sanatci_Id")) )
                      {
                          silkontrol1=1; 
                      }
        
                    
                         if(silkontrol1==0)
                        {
                           silkontrol++; 
                        }
                         if(silkontrol1==1)
                 {
                     String tbData1[] = { albumsarkilarıd,albumId, sanatcisarkiId};
                    DefaultTableModel tblModel1 = (DefaultTableModel) albumSarkilarTablosu.getModel();
                    tblModel1.removeRow(silkontrol);
                 }
                        
                }
                 
                    
                    
                    
                    try {        
                        if(silkontrol1==1)
                        {
                         
                            SanatciileAlbumSarkilarSil();  
                        }
                     silkontrol1=0;
                    silkontrol=0;
           
        } catch (SQLException ex) {
            Logger.getLogger(AdminSayfasi.class.getName()).log(Level.SEVERE, null, ex);
        }
        }
        catch (SQLException ex) {
                java.util.logging.Logger.getLogger(KayitEkrani.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
            } 
        
        try {

                Connection myConn = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/muzik_dosyam", "root", "Ok.20002000");
                Statement myStat = (Statement) myConn.createStatement();
                ResultSet rs = myStat.executeQuery("SELECT * FROM sanatci_sarki");
                while (rs.next()) {
                   
                        if (sanatciSarkiSanatciIDGir.getText().equals(rs.getString("Sanatci_Id"))) 
                        {
                            silkontrol1=1;
                        }
                        
                        if(silkontrol1==0)
                        {
                           silkontrol++; 
                        }
                        
                       
                       
                }
                 if(silkontrol1==1)
                 {
                      String tbData1[] = {sanatcisarkid, sanatciId, sarkId, dinlenmesayisi};
                DefaultTableModel tblModel1 = (DefaultTableModel) sanatciTablosu.getModel();
                    tblModel1.removeRow(silkontrol);
                    
                 }
                    
                    
                    try {
                        if(silkontrol1==1)
                        {
                         SanatciileSanatciSarkiSil();    
                        }
                    silkontrol1=0;
                    silkontrol=0;
           
        } catch (SQLException ex) {
            Logger.getLogger(AdminSayfasi.class.getName()).log(Level.SEVERE, null, ex);
        }
        }
        catch (SQLException ex) {
                java.util.logging.Logger.getLogger(KayitEkrani.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
            }         
        
        
        try {

                Connection myConn = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/muzik_dosyam", "root", "Ok.20002000");
                Statement myStat = (Statement) myConn.createStatement();
                ResultSet rs = myStat.executeQuery("SELECT * FROM sanatci");
                while (rs.next()) {
                    if (sanatciSarkiSanatciIDGir.getText().equals(rs.getString("Sanatci_Id"))) {
                        silkontrol1=1;
                        
                    }
                    else {
                        if(silkontrol1==0)
                        {
                           silkontrol++; 
                        }
                        
                        sarkiId = sarkiIdGir.getText();
                       
                    }

                }
                
                     String tbData1[] = {sanatcid, sanatciad,sanatciulkeid};
                DefaultTableModel tblModel1 = (DefaultTableModel) sanatciTablosu1.getModel();
                    tblModel1.removeRow(silkontrol);
                    
                    silkontrol1=0;
                    silkontrol=0;
                    try {        
            SanatciSil();
        } catch (SQLException ex) {
            Logger.getLogger(AdminSayfasi.class.getName()).log(Level.SEVERE, null, ex);
        }
        }
        catch (SQLException ex) {
                java.util.logging.Logger.getLogger(KayitEkrani.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
            }    
    }//GEN-LAST:event_sarkiSanatciSilButonActionPerformed

    private void sarkiGuncelleButonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sarkiGuncelleButonActionPerformed
         try {

                Connection myConn = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/muzik_dosyam", "root", "Ok.20002000");
                Statement myStat = (Statement) myConn.createStatement();
                ResultSet rs = myStat.executeQuery("SELECT * FROM sarki");
                while (rs.next()) {
                    if (sarkiIdGir.getText().equals(rs.getString("Sarki_Id"))) {
                        silkontrol1=1;
                        
                    }
                    else {
                        if(silkontrol1==0)
                        {
                           silkontrol++; 
                        }
                       
                    }
                        sarkiId = sarkiIdGir.getText();
                        sarkiAdi = sarkiAdiGir.getText();
                        sarkiTarih = sarkiTarihGir.getText();
                        sarkiTurId= String.valueOf(sarkiTurSeccbox.getSelectedIndex()+1);
                        sarkiSuresi = String.valueOf(sarkiSureGir.getText());
                }
                
                if(silkontrol1==1)
                {
                    String tbData1[] = {sarkiId, sarkiAdi, sarkiTarih, sarkiTurId, sarkiSuresi};
                    DefaultTableModel tblModel = (DefaultTableModel) sarkiTablosu.getModel();
                    tblModel.removeRow(silkontrol);
                }
                
                    
                    
                    try {   
                        if(silkontrol1==1)
                        {
                        SarkiGuncelle(); 
                        
                        }
                    
        } catch (SQLException ex) {
            Logger.getLogger(AdminSayfasi.class.getName()).log(Level.SEVERE, null, ex);
        }
        }
        catch (SQLException ex) {
                java.util.logging.Logger.getLogger(KayitEkrani.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
            }
         if(silkontrol1==1)
         {
             String tbData1[] = {sarkiId, sarkiAdi, sarkiTarih, sarkiTurId, sarkiSuresi};
                    DefaultTableModel tblModel = (DefaultTableModel) sarkiTablosu.getModel();
                     tblModel.addRow(tbData1);
         }
                    silkontrol1=0;
                    silkontrol=0;
    }//GEN-LAST:event_sarkiGuncelleButonActionPerformed

    private void sarkiSanatciGuncelleButonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sarkiSanatciGuncelleButonActionPerformed
         try {

                Connection myConn = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/muzik_dosyam", "root", "Ok.20002000");
                Statement myStat = (Statement) myConn.createStatement();
                ResultSet rs = myStat.executeQuery("SELECT * FROM sanatci");
                while (rs.next()) {
                    if (sanatciSarkiSanatciIDGir.getText().equals(rs.getString("Sanatci_Id"))) {
                        silkontrol1=1;
                        
                    }
                    
                        if(silkontrol1==0)
                        {
                           silkontrol++; 
                        }
                        
                    sanatcid = sanatciSarkiSanatciIDGir.getText();
                    sanatciad = sarkiSanatciSanatciAdiGir.getText();
                    sanatciulkeid=sarkiSanatciUlkeIDGir.getText();
                       
                    

                }
                
                if(silkontrol1==1)
                        {
                          String tbData1[] = {sanatcid, sanatciad,sanatciulkeid};
                DefaultTableModel tblModel1 = (DefaultTableModel) sanatciTablosu1.getModel();
                    tblModel1.removeRow(silkontrol);
                    
                        }
                    
                    
                    try {   
                        if(silkontrol1==1)
                        {
                          SanatciGuncelle();  
                        }
            
        } catch (SQLException ex) {
            Logger.getLogger(AdminSayfasi.class.getName()).log(Level.SEVERE, null, ex);
        }
        }
        catch (SQLException ex) {
                java.util.logging.Logger.getLogger(KayitEkrani.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
            }  
         if(silkontrol1==1)
         {
               String tbData1[] = {sanatcid, sanatciad,sanatciulkeid};
                DefaultTableModel tblModel1 = (DefaultTableModel) sanatciTablosu1.getModel();
                    tblModel1.addRow(tbData1);
         }
         silkontrol1=0;
          silkontrol=0;
    }//GEN-LAST:event_sarkiSanatciGuncelleButonActionPerformed

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
            java.util.logging.Logger.getLogger(AdminSayfasi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AdminSayfasi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AdminSayfasi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AdminSayfasi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
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

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AdminSayfasi().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel Album;
    private javax.swing.JPanel Sanatci;
    private javax.swing.JPanel Sarki;
    private javax.swing.JLabel a1;
    private javax.swing.JLabel a2;
    private javax.swing.JLabel a3;
    private javax.swing.JLabel a4;
    private javax.swing.JLabel a5;
    private javax.swing.JLabel a6;
    private javax.swing.JLabel a7;
    private javax.swing.JLabel a8;
    private javax.swing.JButton abonelikEkleButon;
    private javax.swing.JTextField abonelikIDGir;
    private javax.swing.JScrollPane abonelikScrollPane;
    private javax.swing.JTable abonelikTablosu;
    private javax.swing.JTextField abonelikTuruGir;
    private javax.swing.JPanel adminPanel;
    private javax.swing.JScrollPane adminScrollPane;
    private javax.swing.JTabbedPane adminSekmeler;
    private javax.swing.JTable adminTablosu;
    private javax.swing.JTextField albumAdiGir;
    private javax.swing.JButton albumEkleButon;
    private javax.swing.JPanel albumEklePanel;
    private javax.swing.JButton albumGuncelleButon;
    private javax.swing.JTextField albumIdGir;
    private javax.swing.JScrollPane albumPane1;
    private javax.swing.JTextField albumSarkilarAlbumIDGir;
    private javax.swing.JButton albumSarkilarEkleButon;
    private javax.swing.JTextField albumSarkilarIDGir;
    private javax.swing.JScrollPane albumSarkilarPane;
    private javax.swing.JPanel albumSarkilarPanel;
    private javax.swing.JTextField albumSarkilarSanatciSarkiIDGir;
    private javax.swing.JTable albumSarkilarTablosu;
    private javax.swing.JButton albumSİlButon;
    private javax.swing.JTable albumTablosu;
    private javax.swing.JComboBox<String> albumTurIDGir;
    private javax.swing.JPanel calmaListesiGoruntulePanel;
    private javax.swing.JPanel calmaListesiPanel;
    private javax.swing.JPanel calmaListesiSarkilarPanel;
    private javax.swing.JScrollPane calmaListesiSarkilarScrollPane;
    private javax.swing.JTable calmaListesiSarkilarTablosu;
    private javax.swing.JScrollPane calmaListesiScrollPane;
    private javax.swing.JTable calmaListesiTablosu;
    private javax.swing.JTextField dinlenmeSayisiGir;
    private javax.swing.JButton jButton4;
    private javax.swing.JPanel kullaniciPanel;
    private javax.swing.JTable kullaniciTablosu;
    private javax.swing.JPanel kullaniciTakipPanel;
    private javax.swing.JScrollPane kullaniciTakipScrollPane1;
    private javax.swing.JTable kullaniciTakipTablosu1;
    private javax.swing.JPanel kullanicilarPanel;
    private javax.swing.JScrollPane kullanicilarScrollPane;
    private javax.swing.JLabel s1;
    private javax.swing.JLabel s2;
    private javax.swing.JLabel s3;
    private javax.swing.JLabel s5;
    private javax.swing.JLabel s6;
    private javax.swing.JLabel sa1;
    private javax.swing.JLabel sa10;
    private javax.swing.JLabel sa11;
    private javax.swing.JLabel sa2;
    private javax.swing.JLabel sa3;
    private javax.swing.JLabel sa4;
    private javax.swing.JLabel sa5;
    private javax.swing.JLabel sa6;
    private javax.swing.JLabel sa7;
    private javax.swing.JLabel sa8;
    private javax.swing.JLabel sa9;
    private javax.swing.JButton sanatciEkleButon;
    private javax.swing.JButton sanatciGuncelleButon;
    private javax.swing.JTextField sanatciIDGir;
    private javax.swing.JPanel sanatciListePanel;
    private javax.swing.JButton sanatciSarkiEkleButon;
    private javax.swing.JTextField sanatciSarkiIdGir;
    private javax.swing.JPanel sanatciSarkiPanel;
    private javax.swing.JTextField sanatciSarkiSanatciIDGir;
    private javax.swing.JScrollPane sanatciSarkiScrollPane;
    private javax.swing.JScrollPane sanatciScrollPane;
    private javax.swing.JButton sanatciSilButon;
    private javax.swing.JTable sanatciTablosu;
    private javax.swing.JTable sanatciTablosu1;
    private javax.swing.JTextField sarkiAdiGir;
    private javax.swing.JButton sarkiEkleButon;
    private javax.swing.JButton sarkiGuncelleButon;
    private javax.swing.JTextField sarkiIDGir;
    private javax.swing.JTextField sarkiIdGir;
    private javax.swing.JPanel sarkiListePanel;
    private javax.swing.JButton sarkiSanatciGuncelleButon;
    private javax.swing.JTextField sarkiSanatciSanatciAdiGir;
    private javax.swing.JButton sarkiSanatciSilButon;
    private javax.swing.JTextField sarkiSanatciUlkeIDGir;
    private javax.swing.JScrollPane sarkiScrollPane;
    private javax.swing.JButton sarkiSilButon;
    private javax.swing.JTextField sarkiSureGir;
    private javax.swing.JTable sarkiTablosu;
    private javax.swing.JTextField sarkiTarihGir;
    private javax.swing.JTextField sarkiTurIDGir;
    private javax.swing.JComboBox<String> sarkiTurSeccbox;
    private javax.swing.JButton turEkleButon;
    private javax.swing.JPanel turEklePanel;
    private javax.swing.JTextField turGir;
    private javax.swing.JScrollPane turScrollPane2;
    private javax.swing.JTable turTablosu;
    private javax.swing.JTextField ulkeAdiGir;
    private javax.swing.JButton ulkeEkleButon3;
    private javax.swing.JPanel ulkeEklePanel;
    private javax.swing.JTextField ulkeIdGir;
    private javax.swing.JTable ulkeTablosu;
    private javax.swing.JScrollPane ulkeiScrollPane1;
    // End of variables declaration//GEN-END:variables
}
