package org.jahap;

/**
 * Created by russ on 23.10.2015.
 */
public class TestDatabase {
    private String user="root";
    private String password="gandhi";

    private static TestDatabase ourInstance = new TestDatabase();

    public static TestDatabase getInstance() {
        return ourInstance;
    }

    private TestDatabase() {
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
