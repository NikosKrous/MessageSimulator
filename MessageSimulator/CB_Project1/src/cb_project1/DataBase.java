package cb_project1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DataBase {

    private static final String DB_URL = "localhost:3306";
    private static final String FULL_DB_URL = "jdbc:mysql://" + DB_URL + "/db_pp?zeroDateTimeBehavior=EXCEPTION&useSSL=false&serverTimezone=UTC";
    private static final String DB_USER = "user";
    private static final String DB_PASSWD = "123456";

    private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    //Method to connect to the Database
    public Connection connectToDb() {
        Connection connection = null;
        try {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
            } catch (ClassNotFoundException cnfe) {
                System.out.println("Error loading driver: " + cnfe);
            }
            connection = DriverManager.getConnection(FULL_DB_URL, DB_USER, DB_PASSWD);

        } catch (SQLException ex) {
            Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, ex);
        }
        return connection;
    }

    //Method to send a Message to a user
    public void sendMessage(Messages m) {
        Connection con = connectToDb();
        Logfile lg = new Logfile();
        makeMessage(m);
//        Users user = new Users();
//        dbDetails(user.getUsername(), user.getPassword());
        String tmstp = sdf.format(m.getMes_date());
        System.out.print("Inserting a new row into table...");
        String sql = new StringBuilder()
                .append("INSERT INTO `db_pp`.`messages` (`mes_date`, `mes_data`)")
                .append("VALUES (?, ?);").toString();
        try (PreparedStatement statement = con.prepareStatement(sql)) {
            statement.setString(1, tmstp);
            statement.setString(2, m.getMes_data());
            int rowsAffected = statement.executeUpdate();
            System.out.println(rowsAffected + " row(s) inserted");
        } catch (SQLException ex) {
            Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, ex);
        }

        System.out.print("Reading data from table...");
        sql = "SELECT mes_id FROM messages WHERE mes_date LIKE '" + tmstp + "';";
        System.out.println(sql);
        try (Statement statement = con.createStatement(); ResultSet resultSet = statement.executeQuery(sql)) {
            resultSet.first();
            m.setMes_id(resultSet.getInt(1));
        } catch (SQLException ex) {
            Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, ex);
        }

        String sql2 = new StringBuilder()
                .append("INSERT INTO `db_pp`.`send` (`send_from`, `send_to`, `mes_id`)")
                .append("VALUES (?, ?, ?);").toString();
        try (PreparedStatement statement = con.prepareStatement(sql2)) {
            statement.setInt(1, m.getId_from());
            statement.setInt(2, m.getId_to());
            statement.setInt(3, m.getMes_id());
            int rowsAffected = statement.executeUpdate();
            printMessage();
            System.out.println(rowsAffected + " row(s) inserted");
            lg.textLog(m.getId_from(), m.getId_to(), m.getMes_data(), tmstp); //code to call the textlog method in Logfile class for the file creation

        } catch (SQLException ex) {
            Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //Print all the users from the DB
    public void printUsers() {
        Connection con = connectToDb();
        String sql6 = "SELECT id, name, surname, username, role_id FROM users;";
        try (Statement statement = con.createStatement(); ResultSet resultSet = statement.executeQuery(sql6)) {
            System.out.println("ID \tNAME \t\tSURNAME \tUSERNAME \tROLE_ID");
            while (resultSet.next()) {
                System.out.println(resultSet.getInt(1) + "\t" + resultSet.getString(2) + "\t\t" + resultSet.getString(3) + "\t\t" + resultSet.getString(4) + "\t\t" + resultSet.getInt(5));
            }

        } catch (SQLException ex) {
            Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    //Print all the users from the DB for the admin to see            
    public void showUsers() {
        Connection con = connectToDb();
        String sql7 = "SELECT * FROM users;";
        try (Statement statement = con.createStatement(); ResultSet resultSet = statement.executeQuery(sql7)) {
            System.out.println("ID \tNAME \t\tSURNAME \tUSERNAME \tPASSWORD \tROLE_ID");
            while (resultSet.next()) {
                System.out.println(resultSet.getInt(1) + "\t" + resultSet.getString(2) + "\t\t" + resultSet.getString(3) + "\t\t" + resultSet.getString(4) + "\t\t" + resultSet.getString(5) + "\t\t" + resultSet.getInt(6));
            }

        } catch (SQLException ex) {
            Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Users dbDetails(String username, String password) {
        Connection con = connectToDb();
        Users user = null;
        String sql3 = "SELECT id, name, surname, username, password, role_id FROM users;";
        try (Statement statement = con.createStatement(); ResultSet resultSet = statement.executeQuery(sql3)) {
            while (resultSet.next()) {
                if (username.equals(resultSet.getString(4)) && password.equals(resultSet.getString(5))) {
                    user = new Users(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4), resultSet.getString(5), resultSet.getInt(6));

                    return user;
                }
            }
            System.out.println("Wrong username or password");
            user = Helper.details(this);
        } catch (SQLException ex) {
            Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, ex);
        }
        return user;

    }

    //Method to print all the messages
    public void printMessage() {
        Connection con = connectToDb();
        String sql4 = "SELECT send_from, send_to, messages.mes_id, mes_date, mes_data FROM send, messages WHERE messages.mes_id = send.mes_id;";
        try (Statement statement = con.createStatement(); ResultSet resultSet = statement.executeQuery(sql4)) {
            System.out.println("S_ID \tR_ID \tMes_ID\tDate - Time\t\tMessage");
            while (resultSet.next()) {
                System.out.println(resultSet.getInt(1) + "\t" + resultSet.getInt(2) + "\t" + resultSet.getInt(3) + "\t" + resultSet.getString(4) + "\t" + resultSet.getString(5));
            }
        } catch (SQLException ex) {
            Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //Method to print the messages of the current logged in user
    public void userMessage(Users user) {
        Connection con = connectToDb();
        String sql5 = "SELECT send_from, send_to, messages.mes_id, mes_date, mes_data FROM send, messages WHERE (send_from = " + user.getId() + " or send_to = " + user.getId() + ")" + " and messages.mes_id = send.mes_id;";
        try (Statement statement = con.createStatement(); ResultSet resultSet = statement.executeQuery(sql5)) {
            System.out.println("S_ID \tR_ID \tMes_ID\tDate - Time\t\tMessage");
            while (resultSet.next()) {
                System.out.println(resultSet.getInt(1) + "\t" + resultSet.getInt(2) + "\t" + resultSet.getInt(3) + "\t" + resultSet.getString(4) + "\t" + resultSet.getString(5));
            }
        } catch (SQLException ex) {
            Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //Method to create and send the message
    public Messages makeMessage(Messages m) {
        Connection con = connectToDb();
        String message;
        String tmstp = sdf.format(m.getMes_date());
        printUsers();
        System.out.println("");
        System.out.println("Where would you like to send the message? Please insert the receiver ID.");
        Scanner sc = new Scanner(System.in);
        String id = sc.nextLine();
        while (Helper.containsOnlyNumbers(id) == false) {
            System.out.println("Please type only the reciever ID");
            id = sc.nextLine();
        }
        int user_id = Integer.parseInt(id);
        String sql8 = "SELECT id FROM users;";
        try (Statement statement = con.createStatement(); ResultSet resultSet = statement.executeQuery(sql8)) {
            while (resultSet.next()) {
                if (resultSet.getInt(1) == user_id) {
                    System.out.println("Please type a message.");
                    message = sc.nextLine();
                    while (message.length() >= 251 || message.length() <= 0) {
                        System.out.println("Please type an actual message smaller than 250 characters.");
                        message = sc.nextLine();
                    }
                    m.setId_to(user_id);
                    m.setMes_data(message);
                    return m;
                }
            }
            System.out.println("Please type a correct ID.");
            m = makeMessage(m);

        } catch (SQLException ex) {
            Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, ex);
        }
        return m;
    }

    //Method to update the chosen message
    public void updateMessage() {
        Connection con = connectToDb();
        Timestamp date = new Timestamp(System.currentTimeMillis());
        String tmstp = sdf.format(date);
        String message;
        printMessage();
        System.out.println("");
        System.out.println("Which message would you like to update? Please insert the message ID.");
        Scanner sc = new Scanner(System.in);
        String mes_id = sc.nextLine();
        while (Helper.containsOnlyNumbers(mes_id) == false) {
            System.out.println("Please type only the message ID");
            mes_id = sc.nextLine();
        }
        int message_id = Integer.parseInt(mes_id);
        String sql9 = "SELECT mes_id FROM messages;";
        try (Statement statement = con.createStatement(); ResultSet resultSet = statement.executeQuery(sql9)) {

            while (resultSet.next()) {
                if (resultSet.getInt(1) == message_id) {
                    System.out.println("Please type a message.");
                    message = sc.nextLine();
                    String sql10 = new String();
                    sql10 = "update messages set messages.mes_date = '" + tmstp + "',  messages.mes_data = '" + message + "' where messages.mes_id = '" + mes_id + "'";
                    try (PreparedStatement statement2 = con.prepareStatement(sql10)) {
                        int rowsAffected = statement2.executeUpdate();
                        System.out.println(rowsAffected + " row(s) updated");
                    }
                }
            }
            // System.out.println("Please type a correct ID.");
            printMessage();

        } catch (SQLException ex) {
            Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //Method to delete the selected message
    public void deleteMessage() {
        Connection con = connectToDb();
        printMessage();
        System.out.println("");
        System.out.println("Which message would you like to delete? Please insert the message ID.");
        Scanner sc = new Scanner(System.in);
        String mes_id = sc.nextLine();
        while (Helper.containsOnlyNumbers(mes_id) == false) {
            System.out.println("Please type only the message ID");
            mes_id = sc.nextLine();
        }
        int message_id = Integer.parseInt(mes_id);
        String sql11 = "SELECT mes_id FROM send;";
        try (Statement statement = con.createStatement(); ResultSet resultSet = statement.executeQuery(sql11)) {

            while (resultSet.next()) {
                if (resultSet.getInt(1) == message_id) {
                    sql11 = "delete from send where mes_id = '" + mes_id + "';";
                    try (PreparedStatement statement2 = con.prepareStatement(sql11)) {
                        int rowsAffected = statement2.executeUpdate();
                        System.out.println(rowsAffected + " row(s) updated");
                    }
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, ex);
        }

        String sql12 = null;
        sql12 = "delete from messages where mes_id = '" + mes_id + "';";
        try (PreparedStatement statement3 = con.prepareStatement(sql12)) {
            int rowsAffected = statement3.executeUpdate();
            System.out.println(rowsAffected + " row(s) updated");
        } catch (SQLException ex) {
            Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, ex);
        }
        printMessage();

    }

    //Method to create a new user
    public void createUser(Users user) {
        Connection con = connectToDb();
        Scanner sc = new Scanner(System.in);
        System.out.print("Please type a name:");
        user.setName(sc.nextLine());
        System.out.print("Please type a surname:");
        user.setSurname(sc.nextLine());
        System.out.print("Please type a username:");
        user.setUsername(sc.nextLine());
        System.out.print("Please type a password:");
        user.setPassword(sc.nextLine());
        System.out.print("Please type the role_id:");
        user.setRole_id(sc.nextInt());
        System.out.print("Inserting a new row into table...");
        String sql13 = new StringBuilder()
                .append("INSERT INTO `users` (`name`, `surname`, `username`, `password`, `role_id`) ")
                .append("VALUES (?, ?, ?, ?, ?);").toString();
        try (PreparedStatement statement = con.prepareStatement(sql13)) {
            statement.setString(1, user.getName());
            statement.setString(2, user.getSurname());
            statement.setString(3, user.getUsername());
            statement.setString(4, user.getPassword());
            statement.setInt(5, user.getRole_id());
            int rowsAffected = statement.executeUpdate();
            System.out.println(rowsAffected + " row(s) inserted");

        } catch (SQLException ex) {
            Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, ex);
        }
        showUsers();
    }

    //Method to delete the chosen user
    public void deleteUser() {
        Connection con = connectToDb();
        showUsers();
        System.out.println("");
        System.out.println("Which user would you like to delete? Please insert the user ID.");
        Scanner sc = new Scanner(System.in);
        String user_id = sc.nextLine();
        String sql14 = null;
        while (Helper.containsOnlyNumbers(user_id) == false) {
            System.out.println("Please type only the user ID");
            user_id = sc.nextLine();
        }
        sql14 = "delete from users where id = '" + user_id + "';";
        try (PreparedStatement statement = con.prepareStatement(sql14)) {
            int rowsAffected = statement.executeUpdate();
            System.out.println(rowsAffected + " row(s) updated");
        } catch (SQLException ex) {
            Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, ex);
        }
        showUsers();
    }

    //Method to update a     user
    public void updateUser() {
        Connection con = connectToDb();
        showUsers();
        String name, surname, username;
        int password, role_id, id;
        System.out.println("");
        System.out.println("Which user would you like to update? Please insert the user ID.");
        Scanner sc = new Scanner(System.in);
        String user_id = sc.nextLine();
        while (Helper.containsOnlyNumbers(user_id) == false) {
            System.out.println("Please type only the user ID");
            user_id = sc.nextLine();
        }
        String sql15 = "SELECT id FROM users;";
        try (Statement statement = con.createStatement(); ResultSet resultSet = statement.executeQuery(sql15)) {

            System.out.println("Please update the user you wish.");
            System.out.println("Type a new name:");
            name = sc.nextLine();
            System.out.println("Type a new surname:");
            surname = sc.nextLine();
            System.out.println("Type a new username:");
            username = sc.nextLine();
            System.out.println("Type a new password:");
            password = sc.nextInt();
            System.out.println("Type a new role_id:");
            role_id = sc.nextInt();
            String sql16 = new String();
            sql16 = "update users set name = '" + name + "',  surname = '" + surname + "',  username = '" + username + "',  password = '" + password + "',  role_id = '" + role_id + "' where id = '" + user_id + "'";
            try (PreparedStatement statement2 = con.prepareStatement(sql16)) {
                int rowsAffected = statement2.executeUpdate();
                System.out.println(rowsAffected + " row(s) updated");
            } catch (SQLException ex) {
                Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, ex);
            }
            showUsers();

        } catch (SQLException ex) {
            Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
