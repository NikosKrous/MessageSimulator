package cb_project1;

import java.sql.Timestamp;

public class Messages {

    private int mes_id;
    private Timestamp mes_date;
    private String mes_data;
    private int id_from;
    private int id_to;

    public int getId_from() {
        return id_from;
    }

    public void setId_from(int id_from) {
        this.id_from = id_from;
    }

    public int getId_to() {
        return id_to;
    }

    public void setId_to(int id_to) {
        this.id_to = id_to;
    }

    public int getMes_id() {
        return mes_id;
    }

    public void setMes_id(int mes_id) {
        this.mes_id = mes_id;
    }

    public Timestamp getMes_date() {
        return mes_date;
    }

    public void setMes_date(Timestamp mes_date) {
        this.mes_date = mes_date;
    }

    public String getMes_data() {
        return mes_data;
    }

    public void setMes_data(String mes_data) {
        this.mes_data = mes_data;
    }

    public Messages(int id_from, int id_to, Timestamp mes_date, String mes_data) {
        this.mes_date = mes_date;
        this.mes_data = mes_data;
        this.id_from = id_from;
        this.id_to = id_to;
    }

    public Messages() {

    }

}
