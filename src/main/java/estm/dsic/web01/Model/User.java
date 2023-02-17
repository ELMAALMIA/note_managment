package estm.dsic.web01.Model;

public class User {
    private String username;
    private String email;
    private boolean isAdministrator;
    private String password;
    private int id;

    public User() {
    }

    public void setId(int id) {
        this.id = id;
    }
    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return getUsername() + " " + getEmail() + " " + getPassword() + " " + getId();
    }

    public void setPassword(String password) {
        this.password = password;
    }
    public String getPassword() {
        return password;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public boolean isAdministrator() {
        return isAdministrator;
    }
    public void setAdministrator(boolean administrator) {
        isAdministrator = administrator;
    }

}
