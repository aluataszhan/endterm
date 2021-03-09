package com.company;

import java.sql.SQLException;

public class Place {
    private int place_id;
    private int flight_id;
    private int place_number;
    private int passenger_id;
    private String place_type;

    public Place(int place_id, int flight_id, int place_number, int passenger_id, String place_type) {
        this.place_id = place_id;
        this.flight_id = flight_id;
        this.place_number = place_number;
        this.passenger_id = passenger_id;
        this.place_type = place_type;
    }

    public int getPlace_id() {
        return place_id;
    }

    public void setPlace_id(int place_id) {
        this.place_id = place_id;
    }

    public int getFlight_id() {
        return flight_id;
    }

    public void setFlight_id(int flight_id) {
        this.flight_id = flight_id;
    }

    public int getPlace_number() {
        return place_number;
    }

    public void setPlace_number(int place_number) {
        this.place_number = place_number;
    }

    public int getPassenger_id() {
        return passenger_id;
    }

    public void setPassenger_id(int passenger_id) {
        this.passenger_id = passenger_id;
    }

    public String getPlace_type() {
        return place_type;
    }

    public void setPlace_type(String place_type) {
        this.place_type = place_type;
    }

    public void insert() throws SQLException {
        DatabaseConnection.getInstance().getConnection().createStatement()
                .execute("INSERT INTO places (flight_id,place_number,passenger_id,place_type) VALUES ('"
                        + this.flight_id + "','"
                        + this.place_number+"','"
                        + this.passenger_id+"','"
                        + this.place_type+"'"
                        + ")");
    }
}
