
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


public class KayitEkrani extends javax.swing.JFrame {
int k=0;

    public KayitEkrani() {
        initComponents();
        setTitle("Kayit Ekrani");
		setSize(1920, 1080);
                UlkeTurbox();
    }
 public void NormalKayit() throws SQLException {
     int kullanicd=0;   
     Connection connect = null;
        DbHelper db = new DbHelper();
        PreparedStatement statement = null;
        try {
            connect = db.getConnection();
            String sql = "insert into muzik_dosyam.kullanici(Kullanici_Adi,Email,Sifre,Ulke_Id,Odendi_Mi,Abonelik_Id)" + "values(?,?,?,?,?,?)";
            statement = connect.prepareStatement(sql);
            statement.setString(2, Email.getText());
            statement.setString(1, Kullanici_Adi.getText());
            statement.setString(3, Password.getText());
            statement.setInt(4, Integer.valueOf(freeUlkeSec.getSelectedIndex()+1));
            statement.setInt(5, 0);
            statement.setInt(6, 1);
            statement.executeUpdate();
            JOptionPane.showMessageDialog(null, "Kayıt başarılı");
        } catch (SQLException exception) {
            db.ShowError(exception);
        } finally {
            statement.close();
            connect.close();
        }
          Connection myConn = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/muzik_dosyam", "root", "Ok.20002000");
            Statement myStat = (Statement) myConn.createStatement();
            String sorgu = "SELECT Kullanici_Id FROM kullanici WHERE Kullanici_Adi=\"" + Kullanici_Adi.getText() + "\"";
            ResultSet rs = myStat.executeQuery(sorgu);
             while (rs.next()) {
                 kullanicd = rs.getInt("Kullanici_Id");
            }
             
        try {
            connect = db.getConnection();
            String sql = "insert into muzik_dosyam.calma_listesi(Kullanici_Id,SarkiTur_Id,Adi)" + "values(?,?,?)";
            statement = connect.prepareStatement(sql);
            statement.setInt(2,1);
            statement.setInt(1,kullanicd);
            statement.setString(3,"Pop");
            statement.executeUpdate();
            JOptionPane.showMessageDialog(null, "Kayıt başarılı");
        } catch (SQLException exception) {
            db.ShowError(exception);
        } finally {
            statement.close();
            connect.close();
        }
        try {
            connect = db.getConnection();
            String sql = "insert into muzik_dosyam.calma_listesi(Kullanici_Id,SarkiTur_Id,Adi)" + "values(?,?,?)";
            statement = connect.prepareStatement(sql);
            statement.setInt(2,2);
            statement.setInt(1,kullanicd);
            statement.setString(3,"Jazz");
            statement.executeUpdate();
            JOptionPane.showMessageDialog(null, "Kayıt başarılı");
        } catch (SQLException exception) {
            db.ShowError(exception);
        } finally {
            statement.close();
            connect.close();
        }
        try {
            connect = db.getConnection();
            String sql = "insert into muzik_dosyam.calma_listesi(Kullanici_Id,SarkiTur_Id,Adi)" + "values(?,?,?)";
            statement = connect.prepareStatement(sql);
            statement.setInt(2,3);
            statement.setInt(1,kullanicd);
            statement.setString(3,"Klasik");
            statement.executeUpdate();
            JOptionPane.showMessageDialog(null, "Kayıt başarılı");
        } catch (SQLException exception) {
            db.ShowError(exception);
        } finally {
            statement.close();
            connect.close();
        }
        
        
        
        
        
    }
 public void PremiumKayit() throws SQLException {
     int prekullanicid=0;   
     Connection connect = null;
        DbHelper db = new DbHelper();
        PreparedStatement statement = null;
        try {
            connect = db.getConnection();
            String sql = "insert into muzik_dosyam.kullanici(Kullanici_Adi,Email,Sifre,Ulke_Id,Odendi_Mi,Abonelik_Id)" + "values(?,?,?,?,?,?)";
            statement = connect.prepareStatement(sql);
            statement.setString(2, premiumEmailGir.getText());
            statement.setString(1, premiumKullaniciadiGir.getText());
            statement.setString(3, premiumSifreGir.getText());
            statement.setInt(4, Integer.valueOf(premiumUlkeSec1.getSelectedIndex()+1));
            statement.setInt(5, 1);
            statement.setInt(6, 2);
            statement.executeUpdate();
            JOptionPane.showMessageDialog(null, "Kayıt başarılı");
        } catch (SQLException exception) {
            db.ShowError(exception);
        } finally {
            statement.close();
            connect.close();
        }
        Connection myConn = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/muzik_dosyam", "root", "Ok.20002000");
            Statement myStat = (Statement) myConn.createStatement();
            String sorgu = "SELECT Kullanici_Id FROM kullanici WHERE Kullanici_Adi=\"" +premiumKullaniciadiGir.getText() + "\"";
            ResultSet rs = myStat.executeQuery(sorgu);
             while (rs.next()) {
                 prekullanicid = rs.getInt("Kullanici_Id");
            }
             
        try {
            connect = db.getConnection();
            String sql = "insert into muzik_dosyam.calma_listesi(Kullanici_Id,SarkiTur_Id,Adi)" + "values(?,?,?)";
            statement = connect.prepareStatement(sql);
            statement.setInt(2,1);
            statement.setInt(1,prekullanicid);
            statement.setString(3,"Pop");
            statement.executeUpdate();
            JOptionPane.showMessageDialog(null, "Kayıt başarılı");
        } catch (SQLException exception) {
            db.ShowError(exception);
        } finally {
            statement.close();
            connect.close();
        }
        try {
            connect = db.getConnection();
            String sql = "insert into muzik_dosyam.calma_listesi(Kullanici_Id,SarkiTur_Id,Adi)" + "values(?,?,?)";
            statement = connect.prepareStatement(sql);
            statement.setInt(2,2);
            statement.setInt(1,prekullanicid);
            statement.setString(3,"Jazz");
            statement.executeUpdate();
            JOptionPane.showMessageDialog(null, "Kayıt başarılı");
        } catch (SQLException exception) {
            db.ShowError(exception);
        } finally {
            statement.close();
            connect.close();
        }
        try {
            connect = db.getConnection();
            String sql = "insert into muzik_dosyam.calma_listesi(Kullanici_Id,SarkiTur_Id,Adi)" + "values(?,?,?)";
            statement = connect.prepareStatement(sql);
            statement.setInt(2,3);
            statement.setInt(1,prekullanicid);
            statement.setString(3,"Klasik");
            statement.executeUpdate();
            JOptionPane.showMessageDialog(null, "Kayıt başarılı");
        } catch (SQLException exception) {
            db.ShowError(exception);
        } finally {
            statement.close();
            connect.close();
        }
    }
      private void UlkeTurbox()
    {
        try {
                      Connection myConn=(Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/muzik_dosyam","root","Ok.20002000");
                      Statement myStat=(Statement)myConn.createStatement();
                      ResultSet rs=myStat.executeQuery("SELECT * FROM ulkeler");
                      while(rs.next())
                      {
                         freeUlkeSec.addItem(rs.getString("Ulke_Adi"));
                         premiumUlkeSec1.addItem(rs.getString("Ulke_Adi"));
                          
                      }
        } catch (SQLException ex) {
        Logger.getLogger(KayitEkrani.class.getName()).log(Level.SEVERE, null, ex);
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

        Kartno = new javax.swing.JTextField();
        premiumKullaniciadiGir = new javax.swing.JTextField();
        premiumSifreGir = new javax.swing.JPasswordField();
        premiumEmailGir = new javax.swing.JTextField();
        freeUlkeSec = new javax.swing.JComboBox<>();
        premiumUlkeSec1 = new javax.swing.JComboBox<>();
        premiumKartNo = new javax.swing.JLabel();
        premiumUlke = new javax.swing.JLabel();
        premiumKullaniciAdi = new javax.swing.JLabel();
        premiumSifre = new javax.swing.JLabel();
        premiumEmail = new javax.swing.JLabel();
        premiumKayitol = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        freeUlke = new javax.swing.JLabel();
        Kayit_ol = new javax.swing.JButton();
        Password = new javax.swing.JPasswordField();
        Kullanici_Adi = new javax.swing.JTextField();
        Email = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        KULLANICIADI = new javax.swing.JLabel();
        EMAİL = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        ARKAPLAN = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(null);

        Kartno.setBackground(new java.awt.Color(102, 102, 102));
        Kartno.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        Kartno.setForeground(new java.awt.Color(255, 255, 255));
        Kartno.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                KartnoActionPerformed(evt);
            }
        });
        getContentPane().add(Kartno);
        Kartno.setBounds(1310, 690, 520, 50);

        premiumKullaniciadiGir.setBackground(new java.awt.Color(102, 102, 102));
        premiumKullaniciadiGir.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        premiumKullaniciadiGir.setForeground(new java.awt.Color(255, 255, 255));
        premiumKullaniciadiGir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                premiumKullaniciadiGirActionPerformed(evt);
            }
        });
        getContentPane().add(premiumKullaniciadiGir);
        premiumKullaniciadiGir.setBounds(1310, 460, 510, 50);

        premiumSifreGir.setBackground(new java.awt.Color(102, 102, 102));
        premiumSifreGir.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        premiumSifreGir.setForeground(new java.awt.Color(255, 255, 255));
        getContentPane().add(premiumSifreGir);
        premiumSifreGir.setBounds(1310, 540, 510, 50);

        premiumEmailGir.setBackground(new java.awt.Color(102, 102, 102));
        premiumEmailGir.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        premiumEmailGir.setForeground(new java.awt.Color(255, 255, 255));
        getContentPane().add(premiumEmailGir);
        premiumEmailGir.setBounds(1310, 380, 510, 50);

        freeUlkeSec.setBackground(new java.awt.Color(102, 102, 102));
        freeUlkeSec.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        freeUlkeSec.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                freeUlkeSecActionPerformed(evt);
            }
        });
        getContentPane().add(freeUlkeSec);
        freeUlkeSec.setBounds(360, 670, 510, 50);

        premiumUlkeSec1.setBackground(new java.awt.Color(102, 102, 102));
        premiumUlkeSec1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        getContentPane().add(premiumUlkeSec1);
        premiumUlkeSec1.setBounds(1310, 610, 510, 50);

        premiumKartNo.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        premiumKartNo.setForeground(new java.awt.Color(255, 255, 0));
        premiumKartNo.setText("KART NUMARASI:");
        getContentPane().add(premiumKartNo);
        premiumKartNo.setBounds(1070, 690, 230, 60);

        premiumUlke.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        premiumUlke.setForeground(new java.awt.Color(255, 255, 0));
        premiumUlke.setText("ÜLKE:");
        getContentPane().add(premiumUlke);
        premiumUlke.setBounds(1080, 610, 220, 40);

        premiumKullaniciAdi.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        premiumKullaniciAdi.setForeground(new java.awt.Color(255, 255, 0));
        premiumKullaniciAdi.setText("KULLANICI ADI:");
        getContentPane().add(premiumKullaniciAdi);
        premiumKullaniciAdi.setBounds(1080, 460, 210, 60);

        premiumSifre.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        premiumSifre.setForeground(new java.awt.Color(255, 255, 0));
        premiumSifre.setText("ŞİFRE:");
        getContentPane().add(premiumSifre);
        premiumSifre.setBounds(1080, 530, 180, 70);

        premiumEmail.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        premiumEmail.setForeground(new java.awt.Color(255, 255, 0));
        premiumEmail.setText("E-MAİL:");
        getContentPane().add(premiumEmail);
        premiumEmail.setBounds(1090, 380, 140, 50);

        premiumKayitol.setBackground(new java.awt.Color(255, 255, 51));
        premiumKayitol.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        premiumKayitol.setForeground(new java.awt.Color(51, 51, 51));
        premiumKayitol.setText("KAYIT OL");
        premiumKayitol.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                premiumKayitolActionPerformed(evt);
            }
        });
        getContentPane().add(premiumKayitol);
        premiumKayitol.setBounds(1430, 800, 360, 80);

        jLabel3.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(51, 153, 255), 5, true), "PREMİUM KULLANICI", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 36), new java.awt.Color(51, 153, 255))); // NOI18N
        getContentPane().add(jLabel3);
        jLabel3.setBounds(1030, 290, 850, 620);

        freeUlke.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        freeUlke.setForeground(new java.awt.Color(255, 255, 0));
        freeUlke.setText("ÜLKE");
        getContentPane().add(freeUlke);
        freeUlke.setBounds(120, 660, 210, 60);

        Kayit_ol.setBackground(new java.awt.Color(255, 255, 51));
        Kayit_ol.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        Kayit_ol.setForeground(new java.awt.Color(51, 51, 51));
        Kayit_ol.setText("KAYIT OL");
        Kayit_ol.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Kayit_olActionPerformed(evt);
            }
        });
        getContentPane().add(Kayit_ol);
        Kayit_ol.setBounds(440, 780, 360, 80);

        Password.setBackground(new java.awt.Color(102, 102, 102));
        Password.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        Password.setForeground(new java.awt.Color(255, 255, 255));
        getContentPane().add(Password);
        Password.setBounds(350, 570, 520, 60);

        Kullanici_Adi.setBackground(new java.awt.Color(102, 102, 102));
        Kullanici_Adi.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        Kullanici_Adi.setForeground(new java.awt.Color(255, 255, 255));
        Kullanici_Adi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Kullanici_AdiActionPerformed(evt);
            }
        });
        getContentPane().add(Kullanici_Adi);
        Kullanici_Adi.setBounds(350, 480, 520, 60);

        Email.setBackground(new java.awt.Color(102, 102, 102));
        Email.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        Email.setForeground(new java.awt.Color(255, 255, 255));
        getContentPane().add(Email);
        Email.setBounds(350, 390, 520, 60);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 0));
        jLabel1.setText("ŞİFRE:");
        getContentPane().add(jLabel1);
        jLabel1.setBounds(120, 570, 180, 70);

        KULLANICIADI.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        KULLANICIADI.setForeground(new java.awt.Color(255, 255, 0));
        KULLANICIADI.setText("KULLANICI ADI:");
        getContentPane().add(KULLANICIADI);
        KULLANICIADI.setBounds(120, 480, 210, 60);

        EMAİL.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        EMAİL.setForeground(new java.awt.Color(255, 255, 0));
        EMAİL.setText("E-MAİL:");
        getContentPane().add(EMAİL);
        EMAİL.setBounds(120, 390, 140, 50);

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel2.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(51, 153, 255), 5, true), "FREE KULLANICI", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 36), new java.awt.Color(51, 153, 255))); // NOI18N
        getContentPane().add(jLabel2);
        jLabel2.setBounds(70, 290, 850, 620);

        ARKAPLAN.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resimler/arkakplan.jpg"))); // NOI18N
        ARKAPLAN.setText("jLabel1");
        getContentPane().add(ARKAPLAN);
        ARKAPLAN.setBounds(0, 0, 1920, 1100);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1970, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1190, Short.MAX_VALUE)
        );

        getContentPane().add(jPanel1);
        jPanel1.setBounds(0, 0, 1970, 1190);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void Kullanici_AdiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Kullanici_AdiActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Kullanici_AdiActionPerformed

    private void Kayit_olActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Kayit_olActionPerformed
       this.setVisible(false);
       Menu menu=new Menu();
       menu.setVisible(true);
       if( Email.getText().length()==0 || Kullanici_Adi.getText().length()==0 || Password.getText().length()==0 )
       {
           JOptionPane.showMessageDialog(null, "lutfen alanlari doldurunuz");
       }
       else
       {
                  try {
                      Connection myConn=(Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/muzik_dosyam","root","Ok.20002000");
                      Statement myStat=(Statement)myConn.createStatement();
                      ResultSet rs=myStat.executeQuery("SELECT * FROM kullanici");
                      while(rs.next())
                      {
                          if(Email.getText().equals(rs.getString("Email")) || Kullanici_Adi.getText().equals(rs.getString("kullanici_adi")) )
                      {
                          JOptionPane.showMessageDialog(null, "Boyle bir kullanici var");
                          k++;
                      }
                      }
                      
                      if(k==0)
                      {
                          NormalKayit();
                          k=0;
                      }
                          
                      
                          
            
        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(KayitEkrani.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
       }

    }//GEN-LAST:event_Kayit_olActionPerformed

    private void premiumKullaniciadiGirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_premiumKullaniciadiGirActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_premiumKullaniciadiGirActionPerformed

    private void premiumKayitolActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_premiumKayitolActionPerformed
       this.setVisible(false);
       Menu menu=new Menu();
       menu.setVisible(true);
       if( premiumEmailGir.getText().length()==0 || premiumKullaniciadiGir.getText().length()==0 || premiumSifreGir.getText().length()==0 )
       {
           JOptionPane.showMessageDialog(null, "lutfen alanlari doldurunuz");
       }
       else if(Kartno.getText().length()!=16)
       {
           JOptionPane.showMessageDialog(null, "lutfen Gecerli kart numarasi Giriniz");
       }
       else
       {
                  try {
                      Connection myConn=(Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/muzik_dosyam","root","Ok.20002000");
                      Statement myStat=(Statement)myConn.createStatement();
                      ResultSet rs=myStat.executeQuery("SELECT * FROM kullanici");
                      while(rs.next())
                      {
                          if(premiumEmailGir.getText().equals(rs.getString("Email")) || premiumKullaniciadiGir.getText().equals(rs.getString("kullanici_adi")) )
                      {
                          JOptionPane.showMessageDialog(null, "Boyle bir kullanici var");
                          k++;
                      }
                      }
                      
                      if(k==0)
                      {
                          PremiumKayit();
                          k=0;
                      }
                          
                      
                          
            
        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(KayitEkrani.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
       }
    }//GEN-LAST:event_premiumKayitolActionPerformed

    private void KartnoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_KartnoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_KartnoActionPerformed

    private void freeUlkeSecActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_freeUlkeSecActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_freeUlkeSecActionPerformed

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
            java.util.logging.Logger.getLogger(KayitEkrani.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(KayitEkrani.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(KayitEkrani.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(KayitEkrani.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
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
                new KayitEkrani().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel ARKAPLAN;
    private javax.swing.JLabel EMAİL;
    private javax.swing.JTextField Email;
    private javax.swing.JLabel KULLANICIADI;
    private javax.swing.JTextField Kartno;
    private javax.swing.JButton Kayit_ol;
    private javax.swing.JTextField Kullanici_Adi;
    private javax.swing.JPasswordField Password;
    private javax.swing.JLabel freeUlke;
    private javax.swing.JComboBox<String> freeUlkeSec;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel premiumEmail;
    private javax.swing.JTextField premiumEmailGir;
    private javax.swing.JLabel premiumKartNo;
    private javax.swing.JButton premiumKayitol;
    private javax.swing.JLabel premiumKullaniciAdi;
    private javax.swing.JTextField premiumKullaniciadiGir;
    private javax.swing.JLabel premiumSifre;
    private javax.swing.JPasswordField premiumSifreGir;
    private javax.swing.JLabel premiumUlke;
    private javax.swing.JComboBox<String> premiumUlkeSec1;
    // End of variables declaration//GEN-END:variables
}
