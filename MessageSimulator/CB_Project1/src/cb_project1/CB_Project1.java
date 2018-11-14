package cb_project1;

import java.text.SimpleDateFormat;

public class CB_Project1 {

    private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public static void main(String[] args) {
        DataBase db = new DataBase();
        Users user = new Users();
        user = Helper.details(db);
        System.out.println("");
        MenuApp menu = new MenuApp();
        menu.mainMenu(user);
    }

}
