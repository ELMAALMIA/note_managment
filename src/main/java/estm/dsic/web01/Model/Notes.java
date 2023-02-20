package estm.dsic.web01.Model;

import java.time.LocalDate;
import java.util.Date;

public class Notes {
    private String title;
    private String description;
    private Date date;
    private  int user_id;
    private int id;

    public Notes(int id,String title, String description, Date now) {
        this.title = title;
        this.description = description;
        this.date = now;
        this.id=id;
    }

    public Notes() {

    }


    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }
    public int getUser_id() {
        return user_id;
    }

    public void setId(int id) {
        this.id = id;
    }
    public int getId() {
        return id;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return getTitle() + " " + getDescription() + " " + getDate() + " " + getUser_id();
    }
}
