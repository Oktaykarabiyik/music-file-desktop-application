package proje3;
import com.mysql.jdbc.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author nisan
 */
public class DbHelper {

    static String username = "root";
    static String password = "Ok.20002000";
    static String dbUrl = "jdbc:mysql://localhost:3306/muzik_dosyam";

    public Connection getConnection() throws SQLException {//veritabamıyla bağlantı kısmı
        return (Connection) DriverManager.getConnection(dbUrl, username, password);

    }

    public void ShowError(SQLException exception) {//hata mesajını gösteriiyor
        System.out.println("Error: " + exception.getMessage());
        System.out.println("Error Code: " + exception.getErrorCode());//hatanın nerde olduğunu gösteriyor
    }

}
