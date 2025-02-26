/*
Test for the database
commented code does not work, I believe it needs a driver in the POM
 */
import java.sql.*;

public class Main {
    public static void main(String[] args) throws SQLException {
        System.out.println("Hello World!");
        //Connection conn = DriverManager.getConnection("jdbc:sqlite:identifier.sqlite");
        //Statement statement = conn.createStatement();
        //System.out.println(statement.executeQuery("SELECT COUNT(*) FROM users"));
    }
}
