package cb_project1;

import java.sql.Timestamp;

public class MenuApp {

    DataBase db = new DataBase();
    Users myuser = null;

    public void mainMenu(Users user) {
        myuser = user;
        Menu menu = new Menu();
        int ri = user.role_id;
        menu.setTitle("*** Main Menu ***");
        menu.setTitle("*** PLEASE CHOOSE AN ACTION ***");
        switch (ri) {
            case 1:
                System.out.println("*** WELCOME " + myuser.getName() + " " + myuser.getSurname() + " ***");
                menu.addItem(new MenuItem("SEND A MESSAGE", this, "sendMesOpt"));
                menu.addItem(new MenuItem("UPDATE A MESSAGE", this, "updateMesOpt"));
                menu.addItem(new MenuItem("DELETE A MESSAGE", this, "deleteMesOpt"));
                menu.addItem(new MenuItem("SHOW MESSAGES", this, "showMesOpt"));
                menu.addItem(new MenuItem("VIEW ONLY YOUR MESSAGES", this, "showUserMesOpt"));
                menu.addItem(new MenuItem("CREATE A USER", this, "createUserOpt"));
                menu.addItem(new MenuItem("UPDATE A USER", this, "updateUserOpt"));
                menu.addItem(new MenuItem("DELETE A USER", this, "deleteUserOpt"));
                menu.addItem(new MenuItem("SHOW ALL USERS", this, "showUserOpt"));
                menu.execute();
                break;

            case 2:
                System.out.println("*** WELCOME " + myuser.getName() + " " + myuser.getSurname() + " ***");
                menu.addItem(new MenuItem("SEND A MESSAGE", this, "sendMesOpt"));
                menu.addItem(new MenuItem("UPDATE A MESSAGE", this, "updateMesOpt"));
                menu.addItem(new MenuItem("DELETE A MESSAGE", this, "deleteMesOpt"));
                menu.addItem(new MenuItem("SHOW MESSAGES", this, "showMesOpt"));
                menu.addItem(new MenuItem("VIEW ONLY YOUR MESSAGES", this, "showUserMesOpt"));
                menu.addItem(new MenuItem("UPDATE A USER", this, "updateUserOpt"));
                menu.execute();
                break;

            case 3:
                System.out.println("*** WELCOME " + myuser.getName() + " " + myuser.getSurname() + " ***");
                menu.addItem(new MenuItem("SEND A MESSAGE", this, "sendMesOpt"));
                menu.addItem(new MenuItem("UPDATE A MESSAGE", this, "updateMesOpt"));
                menu.addItem(new MenuItem("DELETE A MESSAGE", this, "deleteMesOpt"));
                menu.addItem(new MenuItem("SHOW MESSAGES", this, "showMesOpt"));
                menu.addItem(new MenuItem("VIEW ONLY YOUR MESSAGES", this, "showUserMesOpt"));
                menu.execute();
                break;

            case 4:
                System.out.println("*** WELCOME " + myuser.getName() + " " + myuser.getSurname() + " ***");
                menu.addItem(new MenuItem("SEND A MESSAGE", this, "sendMesOpt"));
                menu.addItem(new MenuItem("VIEW ONLY YOUR MESSAGES", this, "showUserMesOpt"));
                menu.execute();
                break;
        }

    }

    public void sendMesOpt() {
        Messages m = new Messages();
        m.setId_from(myuser.getId());
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        m.setMes_date(timestamp);
        System.out.println("");
        db.sendMessage(m);
        System.out.println("");
    }

    public void updateMesOpt() {
        System.out.println("");
        db.updateMessage();
        System.out.println("");
    }

    public void deleteMesOpt() {
        System.out.println("");
        db.deleteMessage();
        System.out.println("");
    }

    public void showMesOpt() {
        System.out.println("");
        db.printMessage();
        System.out.println("");
    }

    public void showUserMesOpt() {
        System.out.println("");
        db.userMessage(myuser);
        System.out.println("");
    }

    public void createUserOpt() {
        System.out.println("");
        db.createUser(myuser);
        System.out.println("");
    }

    public void updateUserOpt() {
        System.out.println("");
        db.updateUser();
        System.out.println("");
    }

    public void deleteUserOpt() {
        System.out.println("");
        db.deleteUser();
        System.out.println("");
    }

    public void showUserOpt() {
        System.out.println("");
        db.showUsers();
        System.out.println("");
    }
}
