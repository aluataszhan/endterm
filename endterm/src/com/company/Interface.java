package com.company;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Interface {

    public static void menu() throws SQLException {
        System.out.println("Select option: ");
        System.out.println("1. Get info");
        System.out.println("2. Insert info");
        System.out.println("3. Update info");
        System.out.println("4. Remove info");
        System.out.println("0. Quit");

        int option = Integer.parseInt((new Scanner(System.in)).nextLine());

        switch (option) {
            case 1:
                selectMenu();
                break;
            case 2:
                insertMenu();
                break;
            case 3:
                updateMenu();
                break;
            case 4:
                break;
            case 0:
                return;
            default:
                System.out.println("Something went wrong, please try again");
                menu();
        }
    }

    /*
        SELECTS
    */

    public static void selectMenu() throws SQLException {
        System.out.println("Select option: ");
        System.out.println("1. Select planes");
        System.out.println("2. Select flights");
        System.out.println("3. Select passengers");
        System.out.println("4. Select places");
        System.out.println("0. Back to main menu");

        int option = Integer.parseInt((new Scanner(System.in)).nextLine());

        switch (option) {
            case 1:
                selectPlanes();
                break;
            case 2:
                selectFlights();
                break;
            case 3:
                selectPassengers();
                break;
            case 4:
                selectPlaces();
                break;
            case 0:
                return;
            default:
                System.out.println("Something went wrong, please try again");
                menu();
        }
    }

    public static void selectPlanes() throws SQLException {
        for (Plane x : Parser.getAllPlanes()) {
            System.out.println(x.getId() + ". " + x.getPlane_model());
        }
    }

    public static void selectFlights() throws SQLException {
        for (Flight x : Parser.getAllFlights()) {
            System.out.println(
                    x.getFlight_id() + ". " +
                            x.getFrom_place() + " - " +
                            x.getTo_place() + ", " +
                            x.getFlight_time() + ", plane id: " +
                            x.getPlane_id()
            );
        }
    }

    public static void selectPassengers() throws SQLException {
        for (Passenger x : Parser.getAllPassengers()) {
            System.out.println(x.getId() + ". " + x.getPassenger_full_name());
        }
    }

    public static void selectPlaces() throws SQLException {
        for (Place x : Parser.getAllPlaces()) {
            System.out.println(
                    x.getPlace_id() + ". " +
                            x.getPlace_number() + " " +
                            x.getPassenger_id() + " " +
                            x.getPlace_type()
            );
        }
    }

    /*
        INSERTS
    */

    public static void insertMenu() throws SQLException {
        System.out.println("Select option: ");
        System.out.println("1. Insert planes");
        System.out.println("2. Insert flights");
        System.out.println("3. Insert passengers");
        System.out.println("4. Insert places");
        System.out.println("0. Back to main menu");

        int option = Integer.parseInt((new Scanner(System.in)).nextLine());

        switch (option) {
            case 1:
                insertPlane();
                break;
            case 2:
                insertFlight();
                break;
            case 3:
                insertPassengers();
                break;
            case 4:
                insertPlace();
                break;
            case 0:
                return;
            default:
                System.out.println("Something went wrong, please try again");
                menu();
        }
    }

    public static void insertPlane() throws SQLException {
        System.out.println("Insert plane model: ");
        String model = new Scanner(System.in).nextLine();
        new Plane(model).insert();
    }

    public static void insertFlight() throws SQLException {
        Scanner scan = new Scanner(System.in);
        System.out.println("Insert flight place: ");
        String from_place = scan.nextLine();
        System.out.println("Insert point place: ");
        String to_place = scan.nextLine();
        System.out.println("Insert plane id: ");
        int plane_id = Integer.parseInt(scan.nextLine());
        System.out.println("Insert flight time (format: yyyy-MM-dd hh-mm-ss): ");
        String time = scan.nextLine();
        System.out.println("Cost for Economy class: ");
        int e_cost = Integer.parseInt(scan.nextLine());
        System.out.println("Cost for Business class: ");
        int b_cost = Integer.parseInt(scan.nextLine());
        System.out.println("Cost for First class: ");
        int f_cost = Integer.parseInt(scan.nextLine());

        new Flight(0, from_place, to_place, plane_id, time, e_cost, b_cost, f_cost).insert();
    }

    public static void insertPassengers() throws SQLException {
        System.out.println("Insert passenger full name: ");
        String full_name = new Scanner(System.in).nextLine();
        new Passenger(0, full_name).insert();
    }

    public static void insertPlace() throws SQLException {
        Scanner scan = new Scanner(System.in);
        System.out.println("Insert flight id: ");
        int f_id = Integer.parseInt(scan.nextLine());
        System.out.println("Insert place number: ");
        int p_number = Integer.parseInt(scan.nextLine());
        System.out.println("Insert passenger id: ");
        int p_id = Integer.parseInt(scan.nextLine());
        System.out.println("Insert place type (e - economy, b - business, f - first)");
        String type = scan.nextLine();

        new Place(0, f_id, p_number, p_id, type).insert();
    }

    // Update

    public static void updateMenu() throws SQLException {
        System.out.println("Select option: ");
        System.out.println("1. Update planes");
        System.out.println("2. Update flights");
        System.out.println("3. Update passengers");
        System.out.println("4. Update places");
        System.out.println("0. Back to main menu");

        int option = Integer.parseInt((new Scanner(System.in)).nextLine());

        switch (option) {
            case 1:
                updatePlane();
                break;
            case 2:
                updateFlight();
                break;
            case 3:
                updatePassenger();
                break;
            case 4:
                updatePlace();
                break;
            case 0:
                return;
            default:
                System.out.println("Something went wrong, please try again");
                menu();
        }
    }

    public static void updatePlane() throws SQLException {
        selectPlanes();
        Scanner scan = new Scanner(System.in);
        System.out.println("Insert plane id: ");
        int id = Integer.parseInt(scan.nextLine());
        ResultSet rs = DatabaseConnection
                .getInstance()
                .getConnection()
                .createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)
                .executeQuery("SELECT * FROM plane WHERE plane_id = " + id);
        if (rs.next()) {
            System.out.println(rs.getInt("plane_id") + ". " + rs.getString("plane_model"));
            System.out.println("Insert new plane model: ");
            String model = new Scanner(System.in).nextLine();
            DatabaseConnection.getInstance().getConnection().createStatement()
                    .executeUpdate("UPDATE plane SET plane_model = '" + model + "' WHERE plane_id = '" + id + "'");
        } else {
            System.out.println("There no such plane, returning to main menu");
        }
    }

    public static void updateFlight() throws SQLException {
        selectFlights();
        Scanner scan = new Scanner(System.in);
        System.out.println("Insert flight id: ");
        int id = Integer.parseInt(scan.nextLine());
        ResultSet rs = DatabaseConnection
                .getInstance()
                .getConnection()
                .createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)
                .executeQuery("SELECT * FROM flight WHERE flight_id = " + id);
        if (rs.next()) {
            Flight x = new Flight(
                    rs.getInt("flight_id"),
                    rs.getString("from_place"),
                    rs.getString("to_place"),
                    rs.getInt("plane_id"),
                    rs.getString("flight_time"),
                    rs.getInt("e_cost"),
                    rs.getInt("b_cost"),
                    rs.getInt("f_cost")
            );
            System.out.println(
                    x.getFlight_id() + ". " +
                            x.getFrom_place() + " - " +
                            x.getTo_place() + ", " +
                            x.getFlight_time() + ", plane id: " +
                            x.getPlane_id() + ", economy cost: " +
                            x.getE_cost() + ", business cost: " +
                            x.getB_cost() + ", first cost: " +
                            x.getF_cost()
            );
            System.out.println("Insert new flight place: ");
            String from_place = scan.nextLine();
            System.out.println("Insert new point place: ");
            String to_place = scan.nextLine();
            System.out.println("Insert new plane id: ");
            int plane_id = Integer.parseInt(scan.nextLine());
            System.out.println("Insert new flight time (format: yyyy-MM-dd hh-mm-ss): ");
            String time = scan.nextLine();
            System.out.println("New cost for Economy class: ");
            int e_cost = Integer.parseInt(scan.nextLine());
            System.out.println("New cost for Business class: ");
            int b_cost = Integer.parseInt(scan.nextLine());
            System.out.println("New cost for First class: ");
            int f_cost = Integer.parseInt(scan.nextLine());
            DatabaseConnection.getInstance().getConnection().createStatement()
                    .executeUpdate("DELETE FROM flight WHERE flight_id = " + id);

            new Flight(0, from_place, to_place, plane_id, time, e_cost, b_cost, f_cost).insert();
        } else {
            System.out.println("There no such flight, returning to main menu");
        }
    }

    public static void updatePassenger() throws SQLException {
        selectPassengers();
        Scanner scan = new Scanner(System.in);
        System.out.println("Insert Passenger id: ");
        int id = Integer.parseInt(scan.nextLine());
        ResultSet rs = DatabaseConnection
                .getInstance()
                .getConnection()
                .createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)
                .executeQuery("SELECT * FROM passenger WHERE passenger_id = " + id);
        if (rs.next()) {
            System.out.println(rs.getInt("passenger_id") + ". " + rs.getString("passenger_full_name"));
            System.out.println("Insert new full name: ");
            String name = new Scanner(System.in).nextLine();
            DatabaseConnection.getInstance().getConnection().createStatement()
                    .executeUpdate("UPDATE passenger SET passenger_full_name = '" + name + "' WHERE passenger_id = '" + id + "'");
        } else {
            System.out.println("There no such passenger, returning to main menu");
        }
    }

    public static void updatePlace() throws SQLException {
        selectPlaces();
        Scanner scan = new Scanner(System.in);
        System.out.println("Insert place id: ");
        int id = Integer.parseInt(scan.nextLine());
        ResultSet rs = DatabaseConnection
                .getInstance()
                .getConnection()
                .createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)
                .executeQuery("SELECT * FROM place WHERE plce_id = " + id);
        if (rs.next()) {
            Place x = new Place(
                    rs.getInt("place_id"),
                    rs.getInt("flight_id"),
                    rs.getInt("place_number"),
                    rs.getInt("passenger_id"),
                    rs.getString("place_type")
            );
            System.out.println(
                    x.getPlace_id() + ". " +
                            x.getPlace_number() + " " +
                            x.getPassenger_id() + " " +
                            x.getPlace_type()
            );
            System.out.println("Insert nwe flight id: ");
            int f_id = Integer.parseInt(scan.nextLine());
            System.out.println("Insert nwe place number: ");
            int p_number = Integer.parseInt(scan.nextLine());
            System.out.println("Insert new passenger id: ");
            int p_id = Integer.parseInt(scan.nextLine());
            System.out.println("Insert new place type (e - economy, b - business, f - first)");
            String type = scan.nextLine();
            DatabaseConnection.getInstance().getConnection().createStatement()
                    .executeUpdate("DELETE FROM place WHERE flight_id = " + id);

            new Place(0, f_id, p_number, p_id, type).insert();
        } else {
            System.out.println("There no such passenger, returning to main menu");
        }
    }

    //Deletes


    public static void deleteMenu() throws SQLException {
        System.out.println("Select option: ");
        System.out.println("1. Delete planes");
        System.out.println("2. Delete flights");
        System.out.println("3. Delete passengers");
        System.out.println("4. Delete places");
        System.out.println("0. Back to main menu");

        int option = Integer.parseInt((new Scanner(System.in)).nextLine());

        switch (option) {
            case 1:
                deletePlane();
                break;
            case 2:
                deleteFlight();
                break;
            case 3:
                deletePassenger();
                break;
            case 4:
                deletePlace();
                break;
            case 0:
                return;
            default:
                System.out.println("Something went wrong, please try again");
                menu();
        }
    }

    public static void deletePlane() throws SQLException {
        selectPlanes();
        Scanner scan = new Scanner(System.in);
        System.out.println("Insert plane id: ");
        int id = Integer.parseInt(scan.nextLine());
        DatabaseConnection
                .getInstance()
                .getConnection()
                .createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)
                .executeUpdate("DELETE FROM plane WHERE plane_id = " + id);
    }

    public static void deleteFlight() throws SQLException {
        selectPlanes();
        Scanner scan = new Scanner(System.in);
        System.out.println("Insert flight id: ");
        int id = Integer.parseInt(scan.nextLine());
        DatabaseConnection
                .getInstance()
                .getConnection()
                .createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)
                .executeUpdate("DELETE FROM flight WHERE flight_id = " + id);
    }

    public static void deletePassenger() throws SQLException {
        selectPlanes();
        Scanner scan = new Scanner(System.in);
        System.out.println("Insert passenger id: ");
        int id = Integer.parseInt(scan.nextLine());
        DatabaseConnection
                .getInstance()
                .getConnection()
                .createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)
                .executeUpdate("DELETE FROM passenger WHERE passenger_id = " + id);
    }

    public static void deletePlace() throws SQLException {
        selectPlanes();
        Scanner scan = new Scanner(System.in);
        System.out.println("Insert place id: ");
        int id = Integer.parseInt(scan.nextLine());
        DatabaseConnection
                .getInstance()
                .getConnection()
                .createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)
                .executeUpdate("DELETE FROM places WHERE place_id = " + id);
    }
}
