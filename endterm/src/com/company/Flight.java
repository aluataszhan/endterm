package com.company;

import java.sql.SQLException;

public class Flight {
    private int flight_id;
    private String from_place;
    private String to_place;
    private int plane_id;
    private String flight_time;
    private int e_cost;
    private int b_cost;
    private int f_cost;

    public Flight(int flight_id, String from_place, String to_place, int plane_id, String flight_time, int e_cost, int b_cost, int f_cost) {
        this.flight_id = flight_id;
        this.from_place = from_place;
        this.to_place = to_place;
        this.plane_id = plane_id;
        this.flight_time = flight_time;
        this.e_cost = e_cost;
        this.b_cost = b_cost;
        this.f_cost = f_cost;
    }

    public int getFlight_id() {
        return flight_id;
    }

    public void setFlight_id(int flight_id) {
        this.flight_id = flight_id;
    }

    public String getFrom_place() {
        return from_place;
    }

    public void setFrom_place(String from_place) {
        this.from_place = from_place;
    }

    public String getTo_place() {
        return to_place;
    }

    public void setTo_place(String to_place) {
        this.to_place = to_place;
    }

    public int getPlane_id() {
        return plane_id;
    }

    public void setPlane_id(int plane_id) {
        this.plane_id = plane_id;
    }

    public String getFlight_time() {
        return flight_time;
    }

    public void setFlight_time(String flight_time) {
        this.flight_time = flight_time;
    }

    public int getE_cost() {
        return e_cost;
    }

    public void setE_cost(int e_cost) {
        this.e_cost = e_cost;
    }

    public int getB_cost() {
        return b_cost;
    }

    public void setB_cost(int b_cost) {
        this.b_cost = b_cost;
    }

    public int getF_cost() {
        return f_cost;
    }

    public void setF_cost(int f_cost) {
        this.f_cost = f_cost;
    }

    public void insert() throws SQLException {
        DatabaseConnection.getInstance().getConnection().createStatement()
                .execute("INSERT INTO flight (from_place, to_place, plane_id, flight_time, e_cost, b_cost, f_cost) VALUES ('"
                        + this.from_place + "','"
                        + this.to_place+"','"
                        + this.plane_id+"','"
                        + this.flight_time+"','"
                        + this.e_cost+"','"
                        + this.b_cost+"','"
                        + this.f_cost+"'"
                        + ")"
                );
    }
}
