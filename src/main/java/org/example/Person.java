package org.example;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Person {

    String username;
    String email;
    String pass;
    Boolean enabled;
    Integer id;

    public Person ( String username, String email, String pass, Boolean enabled, Integer id) {

        this.username = username;
        this.email = email;
        this.pass = pass;
        this.enabled = enabled;
        this.id = id;
    }
    public void save(DatabaseConnector dbc){
        String insert = "insert in to person values (" +
                " ' " + this.username+ "'," +
                " ' " + this.email+ "'," +
                " ' " + this.pass+ "'," +
                this.enabled + "," +
                this.id;
        dbc.executeInsert(insert);
    }
    public void read(DatabaseConnector dbc) throws SQLException {
        String sql = "SELECT * FROM person WHERE username = '" + this.username + "'";
        ResultSet rs = dbc.executeSelect(sql);
        while (rs.next()) {
            String username = rs.getString("username");
            String email = rs.getString("email");
            String pass = rs.getString("password");
            boolean enabled = rs.getBoolean("enabled");
            long id = rs.getLong("id");
            Person p = new Person(username, email, pass, enabled, (int) id);
            // przetwarzanie obiektu Person
            System.out.println(" username: " + username + ", email: " + email + ", pass: " + pass + ", enabled: " + enabled + ", id: " + id);
        }
    }

    public void update(DatabaseConnector dbc) {
        //String sql = "UPDATE person SET username = " + this.username, email = ?, password = ?, enabled = ? WHERE id = ?";
        String sql = "UPDATE person SET email = '" + this.email + "' WHERE username = '" + this.username + "'";
        int count = dbc.executeUpdate(sql);
        System.out.println("Zmodyfikowano " + count + " rekordów.");
    }

    public void delete(DatabaseConnector dbc) {
        String sql = "DELETE FROM person WHERE username = '" + this.username + "'";
        int count = dbc.executeDelete(sql);
        System.out.println("Delete " + count );
    }
}


