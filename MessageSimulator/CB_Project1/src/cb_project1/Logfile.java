package cb_project1;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Logfile {

    public void textLog(int send_from, int send_to, String mes_data, String mes_date) {

        String textLog = new String();
        if (mes_data != null) {
            textLog = mes_date + "   " + send_from + "     " + send_to + "   " + mes_data;
        }

        try (FileWriter fw = new FileWriter("USER ID " + send_from + ".txt", true);
                BufferedWriter bw = new BufferedWriter(fw);
                PrintWriter out = new PrintWriter(bw)) {
            out.println(textLog);

        } catch (IOException e) {
        }

    }

}
