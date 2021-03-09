package com.company;

import java.sql.SQLException;

public class Passenger {
    private int id;
    private String passenger_full_name;

    public Passenger(int id, String passenger_full_name) {
        this.id = id;
        this.passenger_full_name = passenger_full_name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPassenger_full_name() {
        return passenger_full_name;
    }

    public void setPassenger_full_name(String passenger_full_name) {
        this.passenger_full_name = passenger_full_name;
    }

    public void insert() throws SQLException {
        DatabaseConnection.getInstance().getConnection().createStatement()
                .execute("INSERT INTO passenger (passenger_full_name) VALUES ('"+this.passenger_full_name+"')");
    }
}
