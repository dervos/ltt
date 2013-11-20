package model;

/**
 *
 * @author reintjehard
 */
public class User {
<<<<<<< HEAD

    private String username, password;
    private int rights;
    private final static User admin = new User("admin", "admin");
=======
    
    private String username, password;
    private int rights;
    private final static User admin = new User("admin", "admin");
    
>>>>>>> database

    /**
     *
     * @param username
     * @param password
     */
    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    /**
     *
     * @return
     */
    public String getUsername() {
        return username;
    }

    /**
     *
     * @param username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     *
     * @return
     */
    public String getPassword() {
        return password;
    }

    /**
     *
     * @param password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     *
     * @return
     */
    public static User getAdmin() {
        return admin;
    }
    
     /**
     * @return the rights
     */
    public int getRights() {
        return rights;
    }

    /**
     * @param rights the rights to set
     */
    public void setRights(int rights) {
        this.rights = rights;
    }

    @Override
    public String toString() {
        return "User{" + "username=" + username + ", password=" + password + '}';
    }

}
