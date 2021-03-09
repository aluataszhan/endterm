package com.company;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

public class Parser {
    public static Vector<Plane> getAllPlanes() throws SQLException {
        ResultSet rs = DatabaseConnection
                .getInstance()
                .getConnection()
                .createStatement()
                .executeQuery("SELECT * FROM plane");
        Vector<Plane> planes = new Vector<>();
        while (rs.next()) {
            planes.add(
                    new Plane(
                            rs.getInt("plane_id"),
                            rs.getString("plane_model")
                    )
            );
        }
        return planes;
    }

    public static Vector<Flight> getAllFlights() throws SQLException {
        ResultSet rs = DatabaseConnection
                .getInstance()
                .getConnection()
                .createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)
                .executeQuery("SELECT * FROM flight");
        Vector<Flight> flights = new Vector<>();
        while (rs.next()) {
            flights.add(
                    new Flight(
                            rs.getInt("flight_id"),
                            rs.getString("from_place"),
                            rs.getString("to_place"),
                            rs.getInt("plane_id"),
                            rs.getString("flight_time"),
                            rs.getInt("e_cost"),
                            rs.getInt("b_cost"),
                            rs.getInt("f_cost")
                    )
            );
        }
        return flights;
    }

    public static Vector<Place> getAllPlaces() throws SQLException {
        ResultSet rs = DatabaseConnection
                .getInstance()
                .getConnection()
                .createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)
                .executeQuery("SELECT * FROM places");
        Vector<Place> places = new Vector<>();
        while (rs.next()) {
            places.add(
                    new Place(
                            rs.getInt("place_id"),
                            rs.getInt("flight_id"),
                            rs.getInt("place_number"),
                            rs.getInt("passenger_id"),
                            rs.getString("place_type")
                    )
            );
        }
        return places;
    }

    public static Vector<Passenger> getAllPassengers() throws SQLException {
        ResultSet rs = DatabaseConnection
                .getInstance()
                .getConnection()
                .createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)
                .executeQuery("SELECT * FROM passenger");
        Vector<Passenger> passengers = new Vector<>();
        while (rs.next()) {
            passengers.add(
                    new Passenger(
                            rs.getInt("passenger_id"),
                            rs.getString("passenger_full_name")
                    )
            );
        }
        return passengers;
    }


}
