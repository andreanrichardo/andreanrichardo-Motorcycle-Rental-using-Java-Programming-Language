package controler;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class koneksi {

    public String driver = "com.mysql.jdbc.Driver";
    public String url = "jdbc:mysql://localhost/db_rentalmotor";
    public String username = "root";
    public String password = "";
    public Connection con;

    public koneksi() {
        try {
            Class.forName(driver);
            con = DriverManager.getConnection(url, username, password);
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public Connection getConnection() {
        return con;
    }

    public static void main(String[] args) {
        System.out.println(new koneksi().getConnection());
    }
}
