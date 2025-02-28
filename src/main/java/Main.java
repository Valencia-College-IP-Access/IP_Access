/*
Test for the database
commented code does not work, I believe it needs a driver in the POM
 */
import java.sql.*;

public class Main {
    private static Connection connection = null;
    private static Statement statement = null;
    private static ResultSet resultSet = null;
    public static void main(String[] args) throws SQLException {
        DBHelper.setDATABASE_NAME("identifier.sqlite");
        Users user = new Users();
        //System.out.println(DBHelper.connectionIsValid());
        //user.executive();
        /*try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        System.out.println("Hello World!");
        DBHelper.setDATABASE_NAME("jdbc:sqlite:identifier.sqlite");*/
        //Statement statement = conn.createStatement();
        //System.out.println(statement.executeQuery("SELECT COUNT(*) FROM users"));
    }
}
