package com.codingf;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Affiche {

    public static void afficheActor(Connection connection) throws SQLException {
        Statement stmt= connection.createStatement();

        ResultSet filmTitle= stmt.executeQuery("SELECT * FROM actor");

        System.out.println();
        while(filmTitle.next()){
            System.out.println("actor_id: "+ filmTitle.getString(1));
            System.out.println("first_name: "+ filmTitle.getString(2));
            System.out.println("last_name: "+ filmTitle.getString(3));
            System.out.println("last_update: "+ filmTitle.getString(4));

            System.out.println("========================================================================================================");
        }
    }
    public static void afficheActorInfo(Connection connection) throws SQLException {
        Statement stmt= connection.createStatement();

        ResultSet filmTitle= stmt.executeQuery("SELECT * FROM actor_info");

        System.out.println();
        while(filmTitle.next()){
            System.out.println("actor_id: "+ filmTitle.getString(1));
            System.out.println("first_name: "+ filmTitle.getString(2));
            System.out.println("last_name: "+ filmTitle.getString(3));
            System.out.println("film_info: "+ filmTitle.getString(4));

            System.out.println("========================================================================================================");
        }
    }
    public static void afficheAddress(Connection connection) throws SQLException {
        Statement stmt= connection.createStatement();

        ResultSet filmTitle= stmt.executeQuery("SELECT * FROM address");

        System.out.println();
        while(filmTitle.next()){
            System.out.println("address_id: "+ filmTitle.getString(1));
            System.out.println("address: "+ filmTitle.getString(2));
            System.out.println("address2: "+ filmTitle.getString(3));
            System.out.println("district: "+ filmTitle.getString(4));
            System.out.println("city_id: "+ filmTitle.getString(5));
            System.out.println("postal_code: "+ filmTitle.getString(6));
            System.out.println("phone: "+ filmTitle.getString(7));
            System.out.println("last_update: "+ filmTitle.getString(9));

            System.out.println("========================================================================================================");
        }
    }
    public static void afficheCategory(Connection connection) throws SQLException {
        Statement stmt= connection.createStatement();

        ResultSet filmTitle= stmt.executeQuery("SELECT * FROM category");

        System.out.println();
        while(filmTitle.next()){
            System.out.println("category_id: "+ filmTitle.getString(1));
            System.out.println("name: "+ filmTitle.getString(2));
            System.out.println("last_update: "+ filmTitle.getString(3));

            System.out.println("========================================================================================================");
        }

    }
    public static void afficheCity(Connection connection) throws SQLException {
        Statement stmt= connection.createStatement();

        ResultSet filmTitle= stmt.executeQuery("SELECT * FROM city");

        System.out.println();
        while(filmTitle.next()){
            System.out.println("city_id: "+ filmTitle.getString(1));
            System.out.println("city: "+ filmTitle.getString(2));
            System.out.println("country_id: "+ filmTitle.getString(3));
            System.out.println("last_update: "+ filmTitle.getString(4));

            System.out.println("========================================================================================================");
        }
    }
    public static void afficheCountry(Connection connection) throws SQLException {
        Statement stmt= connection.createStatement();

        ResultSet filmTitle= stmt.executeQuery("SELECT * FROM country");

        System.out.println();
        while(filmTitle.next()){
            System.out.println("country_id: "+ filmTitle.getString(1));
            System.out.println("country: "+ filmTitle.getString(2));
            System.out.println("last_update: "+ filmTitle.getString(3));

            System.out.println("========================================================================================================");
        }
    }
    public static void afficheCustomer(Connection connection) throws SQLException {
        Statement stmt= connection.createStatement();

        ResultSet filmTitle= stmt.executeQuery("SELECT * FROM customer");

        System.out.println();
        while(filmTitle.next()){
            System.out.println("customer_id: "+ filmTitle.getString(1));
            System.out.println("store_id: "+ filmTitle.getString(2));
            System.out.println("first_name: "+ filmTitle.getString(3));
            System.out.println("last_name: "+ filmTitle.getString(4));
            System.out.println("email: "+ filmTitle.getString(5));
            System.out.println("address_id: "+ filmTitle.getString(6));
            System.out.println("active: "+ filmTitle.getString(7));
            System.out.println("create_date: "+ filmTitle.getString(8));
            System.out.println("last_update: "+ filmTitle.getString(9));


            System.out.println("========================================================================================================");
        }
    }
    public static void afficheCustomerList(Connection connection) throws SQLException {
        Statement stmt= connection.createStatement();

        ResultSet filmTitle= stmt.executeQuery("SELECT * FROM customer_list");

        System.out.println();
        while(filmTitle.next()){
            System.out.println("ID: "+ filmTitle.getString(1));
            System.out.println("name: "+ filmTitle.getString(2));
            System.out.println("address: "+ filmTitle.getString(3));
            System.out.println("zip code: "+ filmTitle.getString(4));
            System.out.println("phone: "+ filmTitle.getString(5));
            System.out.println("city: "+ filmTitle.getString(6));
            System.out.println("country: "+ filmTitle.getString(7));
            System.out.println("notes: "+ filmTitle.getString(8));
            System.out.println("SID: "+ filmTitle.getString(9));


            System.out.println("========================================================================================================");
        }
    }







}
