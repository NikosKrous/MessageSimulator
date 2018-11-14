package cb_project1;

import java.util.Scanner;

public class Helper {

    //In this method, the login is happening 
    public static Users details(DataBase db) {
        Users temp;
        System.out.println("Please enter your username");
        Scanner sc = new Scanner(System.in);
        String un = sc.nextLine();
        System.out.println("Please enter your password");
        Scanner sc2 = new Scanner(System.in);
        String pass = sc2.nextLine();

        temp = db.dbDetails(un, pass);
        return temp;
    }

    //Checking if you give a number(int) or str
    public static boolean containsOnlyNumbers(String str) {
        for (int i = 0; i < str.length(); i++) {
            if (!Character.isDigit(str.charAt(i))) {
                return false;
            }
        }
        return true;
    }
}
