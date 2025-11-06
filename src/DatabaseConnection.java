import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DatabaseConnection
{
    private static String URL;
    private static String USER;
    private static String PASSWORD;

    static {
        try (FileInputStream fis = new FileInputStream("C:\\Users\\saiog\\Desktop\\java\\LibraryManagementSystem\\src\\DB.properties")) {
            Properties p = new Properties();
            p.load(fis);
            URL = p.getProperty("URL");
            USER = p.getProperty("USER");
            PASSWORD = p.getProperty("PASSWORD");

            if (URL == null || USER == null || PASSWORD == null) {
                throw new RuntimeException("Missing database configuration in DB.properties");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    public static Connection getConnection()
    {
        Connection con=null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con=DriverManager.getConnection(URL, USER, PASSWORD);
        }
        catch (ClassNotFoundException | SQLException e)
        {
            e.printStackTrace();
        }
        return con;
    }
}
