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

    public static void afficheFilm(Connection connection) throws SQLException{
        Statement stmt= connection.createStatement();

        ResultSet filmTitle= stmt.executeQuery("SELECT * FROM film");

        //loop qui permet d'afficher tout les informations sur la table film
        System.out.println();
        while(filmTitle.next()){
            System.out.println("film_id: "+ filmTitle.getString(1));
            System.out.println("Title: "+ filmTitle.getString(2));
            System.out.println("Description: "+ filmTitle.getString(3));
            System.out.println("original_language_id: "+ filmTitle.getString(6));
            System.out.println("rental_duration: "+ filmTitle.getString(7));
            System.out.println("rental_rate: "+ filmTitle.getString(8));
            System.out.println("length: "+ filmTitle.getString(9));
            System.out.println("replacement_cost: "+ filmTitle.getString(10));
            System.out.println("rating: "+ filmTitle.getString(11));
            System.out.println("special_features: "+ filmTitle.getString(12));
            System.out.println("last_update: "+ filmTitle.getString(13));
            System.out.println("release_year: "+ filmTitle.getString(4));
            System.out.println("language_id: "+ filmTitle.getString(5));

            System.out.println("========================================================================================================");
        }
    }

    public static void afficheFilmCate(Connection connection) throws SQLException{
        Statement stmt= connection.createStatement();

        //print la table film category
        ResultSet filmCate= stmt.executeQuery("SELECT * FROM film_category");

        System.out.println();
        while(filmCate.next()){
            System.out.println("film_id: "+ filmCate.getString(1));
            System.out.println("category_id: "+ filmCate.getString(2));
            System.out.println("last_update: "+ filmCate.getString(3));
            System.out.println("========================================================================================================");
        }
    }

    public static void afficheFilmList(Connection connection) throws SQLException{
        Statement stmt= connection.createStatement();

        //print la table film list
        ResultSet filmList= stmt.executeQuery("SELECT * FROM film_list");

        System.out.println();
        while(filmList.next()){
            System.out.println("FID: "+ filmList.getString(1));
            System.out.println("title: "+ filmList.getString(2));
            System.out.println("description: "+ filmList.getString(3));
            System.out.println("category: "+ filmList.getString(4));
            System.out.println("price: "+ filmList.getString(5));
            System.out.println("length: "+ filmList.getString(6));
            System.out.println("rating: "+ filmList.getString(7));
            System.out.println("actors: "+ filmList.getString(8));

            System.out.println("========================================================================================================");
        }
    }

    public static void afficheFilmText(Connection connection) throws SQLException{
        Statement stmt= connection.createStatement();

        //print la table film text
        ResultSet filmText= stmt.executeQuery("SELECT * FROM film_text");

        System.out.println();
        while(filmText.next()){
            System.out.println("film_id: "+ filmText.getString(1));
            System.out.println("title: "+ filmText.getString(2));
            System.out.println("description: "+ filmText.getString(3));

            System.out.println("========================================================================================================");
        }
    }

    public static void afficheInventory(Connection connection) throws SQLException{
        Statement stmt= connection.createStatement();

        //print la table inventory
        ResultSet inventory= stmt.executeQuery("SELECT * FROM inventory");

        System.out.println();
        while(inventory.next()){
            System.out.println("inventory_id: "+ inventory.getString(1));
            System.out.println("film_id: "+ inventory.getString(2));
            System.out.println("store_id: "+ inventory.getString(3));
            System.out.println("last_update: "+ inventory.getString(4));

            System.out.println("========================================================================================================");
        }
    }

    public static void afficheLanguage(Connection connection) throws SQLException{
        Statement stmt= connection.createStatement();

        //print la table language
        ResultSet language= stmt.executeQuery("SELECT * FROM language");

        System.out.println();
        while(language.next()){
            System.out.println("language_id: "+ language.getString(1));
            System.out.println("name: "+ language.getString(2));
            System.out.println("last_update: "+ language.getString(3));

            System.out.println("========================================================================================================");
        }
    }

    public static void afficheNicerFilm(Connection connection) throws SQLException{
        Statement stmt= connection.createStatement();

        //print la table nicer_but_slower_film_list
        ResultSet NicerFilm= stmt.executeQuery("SELECT * FROM nicer_but_slower_film_list");

        System.out.println();
        while(NicerFilm.next()){
            System.out.println("FID: "+ NicerFilm.getString(1));
            System.out.println("title: "+ NicerFilm.getString(2));
            System.out.println("description: "+ NicerFilm.getString(3));
            System.out.println("category: "+ NicerFilm.getString(4));
            System.out.println("price: "+ NicerFilm.getString(5));
            System.out.println("length: "+ NicerFilm.getString(6));
            System.out.println("rating: "+ NicerFilm.getString(7));
            System.out.println("actors: "+ NicerFilm.getString(8));

            System.out.println("========================================================================================================");
        }
    }

    public static void affichePayment(Connection connection) throws SQLException {
        Statement stmt= connection.createStatement();

        // Chercher les informations dans la base de données
        ResultSet argent= stmt.executeQuery("SELECT * FROM payment");
        System.out.println();

        // Afficher ces informations pour l'utilisateur
        while (argent.next()) {
            System.out.println("payment_id: " + argent.getString(1));
            System.out.println("customer_id: " + argent.getString(2));
            System.out.println("staff_id: " + argent.getString(3));
            System.out.println("rental_id: " + argent.getString(4));
            System.out.println("amount: " + argent.getString(5));
            System.out.println("payment_date: " + argent.getString(6));
            System.out.println("last_update: " + argent.getString(7));
            System.out.println("========================================================================================================");
        }
    }

    public static void afficheRental(Connection connection) throws SQLException {
        Statement stmt= connection.createStatement();

        // Chercher les informations dans la base de données
        ResultSet rent= stmt.executeQuery("SELECT * FROM rental");
        System.out.println();

        // Afficher ces informations pour l'utilisateur
        while (rent.next()) {
            System.out.println("rental_id: " + rent.getString(1));
            System.out.println("rental_date: " + rent.getString(2));
            System.out.println("inventory_id: " + rent.getString(3));
            System.out.println("customer_id: " + rent.getString(4));
            System.out.println("return_date: " + rent.getString(5));
            System.out.println("staff_id: " + rent.getString(6));
            System.out.println("last_update: " + rent.getString(7));
            System.out.println("========================================================================================================");
        }
    }

    public static void afficheSalesCategory(Connection connection) throws SQLException {
        Statement stmt= connection.createStatement();

        ResultSet salesCat= stmt.executeQuery("SELECT * FROM sales_by_film_category");
        System.out.println();

        while (salesCat.next()) {
            System.out.println("category: " + salesCat.getString(1));
            System.out.println("total_sales: " + salesCat.getString(2));
            System.out.println("========================================================================================================");
        }
    }

    public static void afficheSalesStore(Connection connection) throws SQLException {
        Statement stmt= connection.createStatement();

        ResultSet salesStore= stmt.executeQuery("SELECT * FROM sales_by_store");
        System.out.println();

        while (salesStore.next()) {
            System.out.println("store: " + salesStore.getString(1));
            System.out.println("manager: " + salesStore.getString(2));
            System.out.println("total_sales: " + salesStore.getString(3));
            System.out.println("========================================================================================================");
        }
    }

    public static void afficheStaff(Connection connection) throws SQLException {
        Statement stmt= connection.createStatement();

        ResultSet staff= stmt.executeQuery("SELECT * FROM staff");
        System.out.println();

        while (staff.next()) {
            System.out.println("staff_id: " + staff.getString(1));
            System.out.println("first_name: " + staff.getString(2));
            System.out.println("last_name: " + staff.getString(3));
            System.out.println("address_id: " + staff.getString(4));
            System.out.println("email: " + staff.getString(6));
            System.out.println("store_id: " + staff.getString(7));
            System.out.println("active: " + staff.getString(8));
            System.out.println("username: " + staff.getString(9));
            System.out.println("password: " + staff.getString(10));
            System.out.println("last_update: " + staff.getString(11));
            System.out.println("========================================================================================================");
        }
    }

    public static void afficheStaffList(Connection connection) throws SQLException {
        Statement stmt= connection.createStatement();

        ResultSet staffList= stmt.executeQuery("SELECT * FROM staff_list");
        System.out.println();

        while (staffList.next()) {
            System.out.println("ID: " + staffList.getString(1));
            System.out.println("name: " + staffList.getString(2));
            System.out.println("adress: " + staffList.getString(3));
            System.out.println("zip code: " + staffList.getString(4));
            System.out.println("phone: " + staffList.getString(5));
            System.out.println("city: " + staffList.getString(6));
            System.out.println("country: " + staffList.getString(7));
            System.out.println("SID: " + staffList.getString(8));
            System.out.println("========================================================================================================");
        }
    }

    public static void afficheStore(Connection connection) throws SQLException {
        Statement stmt= connection.createStatement();

        ResultSet store= stmt.executeQuery("SELECT * FROM store");
        System.out.println();

        while (store.next()) {
            System.out.println("store_id: " + store.getString(1));
            System.out.println("manager_staff_id: " + store.getString(2));
            System.out.println("adress_id: " + store.getString(3));
            System.out.println("last_update: " + store.getString(4));
            System.out.println("========================================================================================================");
        }
    }

    public static void afficheFilmActor(Connection connection) throws SQLException{
        Statement stmt= connection.createStatement();

        //print la table film actor
        ResultSet filmActor= stmt.executeQuery("SELECT * FROM film_actor");

        System.out.println();
        while(filmActor.next()){
            System.out.println("actor_id: "+ filmActor.getString(1));
            System.out.println("film_id: "+ filmActor.getString(2));
            System.out.println("last_update: "+ filmActor.getString(3));
            System.out.println("========================================================================================================");
        }
    }

}
