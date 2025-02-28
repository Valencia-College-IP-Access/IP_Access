import java.util.ArrayList;

public class Users extends DBHelper {
    private int userID;
    public Users() {

    }
    public void executive() {
        ArrayList<ArrayList<Object>> list = super.executeQuery("select * from Users");
        System.out.println(list);
    }

}
