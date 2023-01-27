package com.codingf;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;
import com.codingf.*;

public class Add {

    public static void addActor(Connection connection) throws SQLException {
        // utiliser un Scanner pour lire les entrées de l'utilisateur
        Scanner scanner = new Scanner(System.in);

        // Variables pour stocker les entrées de l'utilisateur
        String firstName = "";
        String lastName = "";
        boolean isFirstNameValid = false;
        boolean isLastNameValid = false;
        boolean isActorExist = false;

        while (!isFirstNameValid) {
            System.out.print("Entrez le prénom de l'acteur : ");
            firstName = scanner.next();
            // vérifier si le prénom est un nombre
            try {
                Double.parseDouble(firstName);
                System.out.println("Vous ne pouvez pas entrer un nombre pour le prénom.");
            } catch (NumberFormatException e) {
                isFirstNameValid = true;
            }
        }

        while (!isLastNameValid) {
            System.out.print("Entrez le nom de l'acteur : ");
            lastName = scanner.next();
            // vérifier si le nom est un nombre
            try {
                Double.parseDouble(lastName);
                System.out.println("Vous ne pouvez pas entrer un nombre pour le nom.");
            } catch (NumberFormatException e) {
                isLastNameValid = true;
            }
        }
        // créer une requête pour vérifier si l'acteur existe déjà dans la table
        String sql = "SELECT * FROM actor WHERE first_name = ? AND last_name = ?";
        PreparedStatement statement = connection.prepareStatement(sql);

        // définir les valeurs pour les paramètres de la requête
        statement.setString(1, firstName);
        statement.setString(2, lastName);

        // exécuter la requête
        ResultSet resultSet = statement.executeQuery();

        // vérifier si l'acteur existe déjà dans la table
        while (resultSet.next()) {
            isActorExist = true;
            break;
        }

        if (isActorExist) {
            System.out.println("L'acteur existe déjà dans la base de données.");
        } else {
            // créer une requête préparée pour insérer les données dans la table
            sql = "INSERT INTO actor (first_name, last_name) VALUES (?, ?)";
            statement = connection.prepareStatement(sql);

            // définir les valeurs pour les paramètres de la requête
            statement.setString(1, firstName);
            statement.setString(2, lastName);

            // exécuter la requête
            statement.executeUpdate();
        }
    }
    public static void addAddress(Connection connection) throws SQLException {
        // utiliser un Scanner pour lire les entrées de l'utilisateur
        Scanner scanner = new Scanner(System.in);

        // Variables pour stocker les entrées de l'utilisateur
        String address = "";
        String address2 = "";
        String district = "";
        int cityID = 0;
        String postalCode = "";
        String phone = "";
        String location = "";

        System.out.print("Entrez l'adresse: ");
        address = scanner.nextLine();

        System.out.print("Entrez l'adresse 2 (facultatif): ");
        address2 = scanner.nextLine();

        System.out.print("Entrez le district: ");
        district = scanner.nextLine();

        System.out.print("Entrez l'ID de la ville: ");
        cityID = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Entrez le code postal (facultatif): ");
        postalCode = scanner.nextLine();

        System.out.print("Entrez le numéro de téléphone: ");
        phone = scanner.nextLine();

        boolean isLocationValid = false;
        while (!isLocationValid) {
            System.out.print("Entrez la localisation, exemple : POINT(12.34 56.78): ");
            location = scanner.nextLine();
            // vérifier si le format de la localisation est valide
            if (!location.matches("^POINT\\(-?\\d+\\.\\d+ -?\\d+\\.\\d+\\)$")) {
                System.out.println("Format de localisation non valide. Veuillez entrer une localisation valide au format POINT(x y) où x et y sont des coordonnées géographiques.");
            } else {
                isLocationValid = true;
            }
        }


        // créer une requête préparée pour insérer les données dans la table
        String sql = "INSERT INTO address (address, address2, district, city_id, postal_code, phone, location) VALUES (?, ?, ?, ?, ?, ?, ST_GeomFromText(?))";
        PreparedStatement statement = connection.prepareStatement(sql);

        // définir les valeurs pour les paramètres de la requête
        statement.setString(1, address);
        statement.setString(2, address2);
        statement.setString(3, district);
        statement.setInt(4, cityID);
        statement.setString(5, postalCode);
        statement.setString(6, phone);
        statement.setString(7, location);


        // exécuter la requête
        statement.executeUpdate();
        System.out.println("L'adresse a été insérée avec succès.");

    }
    public static void addCategory(Connection connection) throws SQLException {
        // utiliser un Scanner pour lire les entrées de l'utilisateur
        Scanner scanner = new Scanner(System.in);

        // Variables pour stocker les entrées de l'utilisateur
        String name = "";

        System.out.print("Entrez le nom de la catégorie: ");
        name = scanner.nextLine();

        String sql = "SELECT COUNT(*) FROM category WHERE name = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, name);
        ResultSet result = statement.executeQuery();
        result.next();
        int count = result.getInt(1);
        if (count > 0) {
            System.out.println("La catégorie existe déjà dans la base de données.");
        } else {

            // créer une requête préparée pour insérer les données dans la table
            sql = "INSERT INTO category (name) VALUES (?)";
            statement = connection.prepareStatement(sql);

            // définir les valeurs pour les paramètres de la requête
            statement.setString(1, name);

            // exécuter la requête
            statement.executeUpdate();
            System.out.println("La catégorie a été insérée avec succès.");
        }
    }
    public static void addCity(Connection connection) throws SQLException {
        // utiliser un Scanner pour lire les entrées de l'utilisateur
        Scanner scanner = new Scanner(System.in);

        // Variables pour stocker les entrées de l'utilisateur
        String city = "";
        int countryID = 0;

        System.out.print("Entrez le nom de la ville: ");
        city = scanner.nextLine();

        System.out.print("Entrez l'ID du pays: ");
        countryID = scanner.nextInt();

        // créer une requête préparée pour insérer les données dans la table
        String sql = "INSERT INTO city (city, country_id) VALUES (?, ?)";
        PreparedStatement statement = connection.prepareStatement(sql);

        // définir les valeurs pour les paramètres de la requête
        statement.setString(1, city);
        statement.setInt(2, countryID);
        // exécuter la requête
        statement.executeUpdate();
        System.out.println("La ville a été insérée avec succès.");

    }
    public static void addCountry(Connection connection) throws SQLException {
        boolean newCountryValide = false;
        while(!newCountryValide) {
            System.out.print("Entrez le nom du pays que vous souhaitez ajouter : ");
            Scanner newCountry = new Scanner(System.in);

            if (!newCountry.hasNextInt()) {
                String name = newCountry.next();
                String query = "SELECT * FROM country WHERE country = ?";
                PreparedStatement statement = connection.prepareStatement(query);
                statement.setString(1, name);
                ResultSet resultSet = statement.executeQuery();

                if (resultSet.next()) {
                    System.err.println("Erreur: Le pays existe déjà !!!");
                } else {

                    query = "INSERT INTO country (country) VALUES (?);";
                    statement = connection.prepareStatement(query);
                    statement.setString(1, name);

                    int result = statement.executeUpdate();

                    if (result == 1) {
                        System.out.println("\nLa valeur a été insérée avec succès dans la table country");
                    } else {
                        System.err.println("\nErreur: lors de l'insertion de la valeur dans la table country");
                    }

                    newCountryValide = true;
                }
            }else{
                System.err.println("\nErreur: Le nom ne peut etre un numéro");
            }
        }
    }
    public static void addCustomer(Connection connection) throws SQLException {
        boolean newCountryValide = false;
        while(!newCountryValide) {
            System.out.print("Entrez le nom du pays que vous souhaitez ajouter : ");
            Scanner newCountry = new Scanner(System.in);

            if (!newCountry.hasNextInt()) {
                String name = newCountry.next();
                String query = "SELECT * FROM country WHERE country = ?";
                PreparedStatement statement = connection.prepareStatement(query);
                statement.setString(1, name);
                ResultSet resultSet = statement.executeQuery();

                if (resultSet.next()) {
                    System.err.println("Erreur: Le pays existe déjà !!!");
                } else {

                    query = "INSERT INTO country (country) VALUES (?);";
                    statement = connection.prepareStatement(query);
                    statement.setString(1, name);

                    int result = statement.executeUpdate();

                    if (result == 1) {
                        System.out.println("\nLa valeur a été insérée avec succès dans la table country");
                    } else {
                        System.err.println("\nErreur: lors de l'insertion de la valeur dans la table country");
                    }

                    newCountryValide = true;
                }
            }else{
                System.err.println("\nErreur: Le nom ne peut etre un numéro");
            }
        }
    }

    public static void addFilm(Connection connection) throws SQLException {

        //creation des  variables
        String title;
        String description;
        int release_year;
        int language_id;
        int original_language_id;
        float rental_duration;
        float rental_rate;
        int length;
        float replacement_cost;
        String rating;
        String special_features;

        /*loop pour film*/
        boolean newFilmValide = false;
        while(!newFilmValide) {

            /*le title*/
            System.out.println("Entrez le title de votre film: ");
            Scanner newTitle = new Scanner(System.in);

            /*check si il y a un titre et enregistre*/
            if (newTitle.hasNext()) {
                title = newTitle.nextLine().toUpperCase();




                /*loop pour la description*/
                boolean newFilmDescription = false;
                while(!newFilmDescription) {

                    /*la description*/
                    System.out.println("Entrez la description de votre film: ");
                    Scanner newDescription = new Scanner(System.in);

                    /*check si il y a une description et enregistre*/
                    if (newDescription.hasNext()) {
                        description = newDescription.nextLine();




                        /*loop pour le release year*/
                        boolean newFilmReleaseYear = false;
                        while(!newFilmReleaseYear) {

                            /*le release year*/
                            System.out.println("Entrez le release year: ");
                            Scanner newReleaseYear = new Scanner(System.in);

                            /*check si il y a une date et enregistre*/
                            if (newReleaseYear.hasNextInt()) {
                                release_year = newReleaseYear.nextInt();
                                if (release_year > 1800 && release_year < 9999) {



                                    /*loop pour le language id*/
                                    boolean newFilmLanguageId = false;
                                    while (!newFilmLanguageId) {

                                        /*le language id*/
                                        System.out.println("Entrez le language id: ");
                                        Scanner newLanguageId = new Scanner(System.in);

                                        /*check si il y a un language id et enregistre*/
                                        if (newLanguageId.hasNextInt()) {
                                            language_id = newLanguageId.nextInt();



                                            /*loop pour le original language id*/
                                            boolean newFilmOriginalLanguage = false;
                                            while (!newFilmOriginalLanguage) {

                                                /*le original language id*/
                                                System.out.println("Entrez le original language id: ");
                                                Scanner newOriginalLanguageId = new Scanner(System.in);

                                                /*check si il y a un original language id et enregistre*/
                                                if (newOriginalLanguageId.hasNextInt()) {
                                                    original_language_id = newOriginalLanguageId.nextInt();




                                                    /*loop pour le rental duration*/
                                                    boolean newFilmRentalDuration = false;
                                                    while (!newFilmRentalDuration) {

                                                        /*le rental duration*/
                                                        System.out.println("Entrez le rental duration: ");
                                                        Scanner newRentalDuration = new Scanner(System.in);

                                                        /*check si il y a un rental duration et enregistre*/
                                                        if (newRentalDuration.hasNextInt()) {
                                                            rental_duration = newRentalDuration.nextInt();




                                                            //loop pour rental rate
                                                            boolean newFilmRentalRate = false;
                                                            while (!newFilmRentalRate) {

                                                                /*le rental rate*/
                                                                System.out.println("Entrez le rental rate: ");
                                                                Scanner newRentalRate = new Scanner(System.in);

                                                                /*check si il y a un rental rate et enregistre*/
                                                                if (newRentalRate.hasNextFloat()) {
                                                                    rental_rate = newRentalRate.nextFloat();



                                                                    //loop pour length
                                                                    boolean newFilmLength = false;
                                                                    while (!newFilmLength) {

                                                                        /*le length*/
                                                                        System.out.println("Entrez le length: ");
                                                                        Scanner newLength = new Scanner(System.in);

                                                                        /*check si il y a un length et enregistre*/
                                                                        if (newLength.hasNextInt()) {
                                                                            length = newLength.nextInt();




                                                                            //loop pour replacement cost
                                                                            boolean newFilmReplacementCost = false;
                                                                            while (!newFilmReplacementCost) {

                                                                                /*le replacement cost*/
                                                                                System.out.println("Entrez le replacement cost: ");
                                                                                Scanner newReplacementCost = new Scanner(System.in);

                                                                                /*check si il y a un replacement cost et enregistre*/
                                                                                if (newReplacementCost.hasNextFloat()) {
                                                                                    replacement_cost = newReplacementCost.nextFloat();




                                                                                    //loop pour rating
                                                                                    boolean newFilmRating = false;
                                                                                    while (!newFilmRating) {

                                                                                        /*le rating*/
                                                                                        System.out.println("Entrez le rating: ");
                                                                                        Scanner newRating = new Scanner(System.in);

                                                                                        /*check si il y a un rating et enregistre*/
                                                                                        if (newRating.hasNext()) {
                                                                                            rating = newRating.nextLine();




                                                                                            //loop pour speciales features
                                                                                            boolean newFilmSpecialFeats = false;
                                                                                            while (!newFilmSpecialFeats) {

                                                                                                /*le replacement cost*/
                                                                                                System.out.println("Entrez les speciales features: ");
                                                                                                Scanner newSpecialFeats = new Scanner(System.in);

                                                                                                /*check si il y a les speciales features et enregistre*/
                                                                                                if (newSpecialFeats.hasNext()) {
                                                                                                    special_features = newSpecialFeats.nextLine();


                                                                                                    //check si il existe deja dans la table
                                                                                                    String query = "SELECT * FROM film WHERE title = ?";
                                                                                                    PreparedStatement statement = connection.prepareStatement(query);
                                                                                                    statement.setString(1, title);
                                                                                                    ResultSet resultSet = statement.executeQuery();

                                                                                                    if (resultSet.next()) {
                                                                                                        System.err.println("le film existe deja svp veuillez aller modifier");
                                                                                                    } else {

                                                                                                        //si il n'existe pas alors inserer dans la table
                                                                                                        query = "INSERT INTO film (title, description, release_year, language_id, original_language_id, rental_duration, rental_rate, length, replacement_cost, rating, special_features) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
                                                                                                        statement = connection.prepareStatement(query);
                                                                                                        statement.setString(1, title);
                                                                                                        statement.setString(2, description);
                                                                                                        statement.setString(3, Integer.toString(release_year));
                                                                                                        statement.setString(4, Integer.toString(language_id));
                                                                                                        statement.setString(5, Integer.toString(original_language_id));
                                                                                                        statement.setString(6, Float.toString(rental_duration));
                                                                                                        statement.setString(7, Float.toString(rental_rate));
                                                                                                        statement.setString(8, Integer.toString(length));
                                                                                                        statement.setString(9, Float.toString(replacement_cost));
                                                                                                        statement.setString(10, rating);
                                                                                                        statement.setString(11, special_features);

                                                                                                        int result = statement.executeUpdate();

                                                                                                        if (result == 1) {
                                                                                                            System.out.println("Les valeurs ont été insérée avec succès dans la table film");
                                                                                                        } else {
                                                                                                            System.err.println("Erreur: lors de l'insertion de les valeurs dans la table film");
                                                                                                        }

                                                                                                        //fermeture des loops
                                                                                                        newFilmSpecialFeats = true;
                                                                                                        newFilmRating = true;
                                                                                                        newFilmReplacementCost = true;
                                                                                                        newFilmLength = true;
                                                                                                        newFilmRentalRate = true;
                                                                                                        newFilmRentalDuration = true;
                                                                                                        newFilmOriginalLanguage = true;
                                                                                                        newFilmLanguageId = true;
                                                                                                        newFilmReleaseYear = true;
                                                                                                        newFilmDescription = true;
                                                                                                        newFilmValide = true;
                                                                                                    }
                                                                                                }
                                                                                            }
                                                                                        }
                                                                                    }
                                                                                } else {
                                                                                    System.err.println("Erreur: pas un nombre ");
                                                                                }
                                                                            }
                                                                        }
                                                                    }
                                                                }
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        } else {
                                            System.err.println("Erreur: pas un nombre");
                                        }
                                    }
                                } else {
                                    System.out.println("c'est impossible...");
                                }

                            } else {
                                System.err.println("Erreur: pas un nombre");
                            }
                        }
                    }
                }
            }
        }
    }

    public static void addFilmActor(Connection connection) throws SQLException{
        Affiche.afficheFilmActor(connection);

        boolean restart = false;
        while (!restart){

            //demande si l'utilisateur veut ajouter ou quitter
            System.out.println("1. ajouter un film actor\n2.quitter");
            Scanner choix1 = new Scanner(System.in);

            if (choix1.hasNextInt()){
                int choix1def = choix1.nextInt();

                if (choix1def == 1){
                    int actor_id = 0;
                    int film_id = 0;

                    boolean loop = false;
                    while(!loop) {

                        //demande un id pour l'actor
                        System.out.println("actor id:");
                        Scanner newActorId = new Scanner(System.in);
                        if (newActorId.hasNextInt()) {
                            actor_id = newActorId.nextInt();
                            loop = true;
                        } else {
                            System.err.println("pas un nombre");
                        }
                    }

                    boolean loop2 = false;
                    while (!loop2){

                        //demande un id pour le film
                        System.out.println("film id:");
                        Scanner newFilmId = new Scanner(System.in);
                        if (newFilmId.hasNextInt()) {
                            film_id = newFilmId.nextInt();
                            loop2 = true;
                        } else {
                            System.err.println("pas un nombre");
                        }
                    }

                    //check si il existe deja dans la table
                    String query = "SELECT * FROM film_actor WHERE actor_id = ? AND film_id = ?;";
                    PreparedStatement statement = connection.prepareStatement(query);
                    statement.setString(1, Integer.toString(actor_id));
                    statement.setString(2, Integer.toString(film_id));

                    ResultSet result1 = statement.executeQuery();

                    if (result1.next()){
                        System.err.println("existe deja");
                        break;
                    }

                    //si il existe pas alors ajouter a la table
                    query = "INSERT INTO film_actor (actor_id, film_id) VALUES (?, ?);";
                    statement = connection.prepareStatement(query);
                    statement.setString(1, Integer.toString(actor_id));
                    statement.setString(2, Integer.toString(film_id));

                    int result2 = statement.executeUpdate();

                    if (result2 == 1) {
                        System.out.println("Les valeurs ont été insérée avec succès dans la table film_actor");
                    } else {
                        System.err.println("Erreur: lors de l'insertion de les valeurs dans la table film_actor");
                    }

                    restart = true;


                } else if (choix1def == 2){
                    System.out.println("Quitter :D");

                } else {
                    System.err.println("pas une option");
                }
            }
        }
    }

    public static void addFilmCate(Connection connection) throws SQLException{
        Affiche.afficheFilmCate(connection);

        boolean restart = false;
        while (!restart){

            //demande si il veut ajouter ou quitter
            System.out.println("1. ajouter une category\n2.quitter");
            Scanner choix1 = new Scanner(System.in);

            if (choix1.hasNextInt()){
                int choix1def = choix1.nextInt();

                if (choix1def == 1){
                    int category_id = 0;
                    int film_id = 0;

                    boolean loop = false;
                    while(!loop) {

                        //demande l'id de category
                        System.out.println("category id:");
                        Scanner newCategory = new Scanner(System.in);
                        if (newCategory.hasNextInt()) {
                            category_id = newCategory.nextInt();
                            loop = true;
                        } else {
                            System.err.println("pas un nombre");
                        }
                    }

                    boolean loop2 = false;
                    while (!loop2){
                        //demande l'id du film
                        System.out.println("film id:");
                        Scanner newFilmId = new Scanner(System.in);
                        if (newFilmId.hasNextInt()) {
                            film_id = newFilmId.nextInt();
                            loop2 = true;
                        }
                    }

                    //si le film existe
                    String query = "SELECT * FROM film_category WHERE category_id = ? AND film_id = ?;";
                    PreparedStatement statement = connection.prepareStatement(query);
                    statement.setString(1, Integer.toString(category_id));
                    statement.setString(2, Integer.toString(film_id));

                    ResultSet result1 = statement.executeQuery();

                    if (result1.next()){
                        System.err.println("existe deja");

                    } else {

                        //si le film existe pas alos on ajoute a la table
                        query = "INSERT INTO film_category (category_id, film_id) VALUES (?, ?);";
                        statement = connection.prepareStatement(query);
                        statement.setString(1, Integer.toString(category_id));
                        statement.setString(2, Integer.toString(film_id));

                        int result2 = statement.executeUpdate();

                        if (result2 == 1) {
                            System.out.println("Les valeurs ont été insérée avec succès dans la table category");
                        } else {
                            System.err.println("Erreur: lors de l'insertion de les valeurs dans la table category");
                        }

                        restart = true;

                    }

                } else if (choix1def == 2){
                    System.out.println("Quitter :D");
                    restart = true;
                } else {
                    System.err.println("pas une option");
                }
            }
        }
    }

    public static void addFilmText(Connection connection) throws SQLException{
        Affiche.afficheFilmText(connection);

        boolean restart = false;
        while (!restart){

            //demande si il veut ajouter ou quitter
            System.out.println("1.ajouter un film text\n2.quitter");
            Scanner choix1 = new Scanner(System.in);

            if (choix1.hasNextInt()){
                int choix1def = choix1.nextInt();

                if (choix1def == 1){
                    int film_id = 0;
                    String title = "";
                    String description = "";

                    boolean loop = false;
                    while(!loop) {
                        System.out.println("film id:");
                        Scanner newFilmId = new Scanner(System.in);
                        if (newFilmId.hasNextInt()) {
                            film_id = newFilmId.nextInt();
                            loop = true;
                        } else {
                            System.err.println("pas un nombre");
                        }
                    }

                    boolean loop2 = false;
                    while (!loop2){
                        System.out.println("title:");
                        Scanner newTitle = new Scanner(System.in);
                        if (newTitle.hasNext()) {
                            title = newTitle.next();
                            loop2 = true;
                        }
                    }

                    boolean loop3 = false;
                    while (!loop3){
                        System.out.println("description:");
                        Scanner newDescription = new Scanner(System.in);
                        if (newDescription.hasNext()) {
                            description = newDescription.next();
                            loop3 = true;
                        }
                    }


                    String query = "SELECT * FROM film_text WHERE film_id = ? AND title = ? AND description = ?;";
                    PreparedStatement statement = connection.prepareStatement(query);
                    statement.setString(1, Integer.toString(film_id));
                    statement.setString(2, title);
                    statement.setString(3, description);

                    ResultSet result1 = statement.executeQuery();

                    if (result1.next()){
                        System.err.println("existe deja");

                    }else {


                        query = "INSERT INTO film_text (film_id, title, description) VALUES (?, ?, ?);";
                        statement = connection.prepareStatement(query);
                        statement.setString(1, Integer.toString(film_id));
                        statement.setString(2, title);
                        statement.setString(3, description);

                        int result2 = statement.executeUpdate();

                        if (result2 == 1) {
                            System.out.println("Les valeurs ont été insérée avec succès dans la table film_text");
                        } else {
                            System.err.println("Erreur: lors de l'insertion de les valeurs dans la table film_text");
                        }

                        restart = true;

                    }

                } else if (choix1def == 2){
                    System.out.println("Quitter :D");
                    restart = true;
                } else {
                    System.err.println("pas une option");
                }
            }
        }
    }

    public static void addInventory(Connection connection) throws SQLException{
        Affiche.afficheInventory(connection);

        boolean restart = false;
        while (!restart){

            //demande si il veut ajouter ou quitter
            System.out.println("1. ajouter un inventory\n2.quitter");
            Scanner choix1 = new Scanner(System.in);

            if (choix1.hasNextInt()){
                int choix1def = choix1.nextInt();

                if (choix1def == 1){
                    int film_id = 0;
                    int store_id = 0;

                    boolean loop = false;
                    while(!loop) {
                        System.out.println("film id:");
                        Scanner newFilmId = new Scanner(System.in);
                        if (newFilmId.hasNextInt()) {
                            film_id = newFilmId.nextInt();
                            loop = true;
                        } else {
                            System.err.println("pas un nombre");
                        }
                    }

                    boolean loop2 = false;
                    while (!loop2){
                        System.out.println("store id:");
                        Scanner newStoreId = new Scanner(System.in);
                        if (newStoreId.hasNextInt()) {
                            store_id = newStoreId.nextInt();
                            loop2 = true;
                        } else {
                            System.err.println("pas un nombre");
                        }
                    }

                    //check si il existe
                    String query = "SELECT * FROM inventory WHERE film_id = ? AND store_id = ?;";
                    PreparedStatement statement = connection.prepareStatement(query);
                    statement.setString(1, Integer.toString(film_id));
                    statement.setString(2, Integer.toString(store_id));

                    ResultSet result1 = statement.executeQuery();

                    if (result1.next()){
                        System.err.println("existe deja");
                    } else {

                        //si il existe pas alors on ajoute
                        query = "INSERT INTO inventory (film_id, store_id) VALUES (?, ?);";
                        statement = connection.prepareStatement(query);
                        statement.setString(1, Integer.toString(film_id));
                        statement.setString(2, Integer.toString(store_id));

                        int result2 = statement.executeUpdate();

                        if (result2 == 1) {
                            System.out.println("Les valeurs ont été insérée avec succès dans la table inventory");
                        } else {
                            System.err.println("Erreur: lors de l'insertion de les valeurs dans la table inventory");
                        }

                        restart = true;
                    }

                } else if (choix1def == 2){
                    restart = true;
                    System.out.println("Quitter :D");

                } else {
                    System.err.println("pas une option");
                }
            }
        }
    }

    public static void addLanguage(Connection connection) throws SQLException{
        Affiche.afficheLanguage(connection);

        boolean restart = false;
        while (!restart){

            //demande si il veut ajouter ou quitter
            System.out.println("1. ajouter un language\n2.quitter");
            Scanner choix1 = new Scanner(System.in);

            if (choix1.hasNextInt()){
                int choix1def = choix1.nextInt();

                if (choix1def == 1){
                    String name = null;


                    boolean loop = false;
                    while(!loop) {
                        System.out.println("language:");
                        Scanner newName = new Scanner(System.in);
                        if (newName.hasNext()) {
                            name = newName.next();
                            loop = true;
                        }
                    }

                    //check si il existe
                    String query = "SELECT * FROM language WHERE name = ?;";
                    PreparedStatement statement = connection.prepareStatement(query);
                    statement.setString(1, name);

                    ResultSet result1 = statement.executeQuery();

                    if (result1.next()){
                        System.err.println("existe deja");

                    } else {

                        //si il existe alors on ajoute
                        query = "INSERT INTO language (name) VALUE (?);";
                        statement = connection.prepareStatement(query);
                        statement.setString(1, name);

                        int result2 = statement.executeUpdate();

                        if (result2 == 1) {
                            System.out.println("Les valeurs ont été insérée avec succès dans la table language");
                        } else {
                            System.err.println("Erreur: lors de l'insertion de les valeurs dans la table language");
                        }

                        restart = true;
                    }


                } else if (choix1def == 2){
                    System.out.println("Quitter :D");
                    restart = true;

                } else {
                    System.err.println("pas une option");
                }
            }
        }
    }

    public static void addPayment(Connection connection) throws SQLException {
        Affiche.affichePayment(connection);
        Scanner scanner = new Scanner(System.in);

        // Initialiser des variables pour enregistrer les données que rentre l'utilisateur
        int customerId = 0;
        int staffId = 0;
        int rentalId = 0;
        float amount = 0;

        // Initialiser des booléens pour les boucles while
        boolean isCustomerIdValid = false;
        boolean isStaffIdValid = false;
        boolean isRentalIdValid = false;
        boolean isAmountValid = false;

        // Demander les informations à l'utilisateur DEBUT
        while (!isCustomerIdValid) {
            System.out.print("Entrez l'id du client : ");
            Scanner cust = new Scanner(System.in);
            if (cust.hasNextInt()){
                customerId = cust.nextInt();
                isCustomerIdValid = true;
            } else {
                System.out.println("Vous ne pouvez entrez qu'un nombre entier pour l'id du client.");
            }
        }

        while (!isStaffIdValid) {
            System.out.print("Entrez l'id du staff s'occupant du client : ");
            Scanner staff = new Scanner(System.in);
            if (staff.hasNextInt()){
                staffId = staff.nextInt();
                isStaffIdValid = true;
            } else {
                System.out.println("Vous ne pouvez entrez qu'un nombre entier pour l'id du staff.");
            }
        }

        while (!isRentalIdValid) {
            System.out.print("Entrez l'id de la location : ");
            Scanner rent = new Scanner(System.in);
            if (rent.hasNextInt()){
                rentalId = rent.nextInt();
                isRentalIdValid = true;
            } else {
                System.out.println("Vous ne pouvez entrez qu'un nombre entier pour l'id de la location.");
            }
        }

        while (!isAmountValid) {
            System.out.print("Entrez une quantité d'argent : ");
            Scanner mount = new Scanner(System.in);
            if (mount.hasNextFloat()){
                amount = mount.nextFloat();
                isAmountValid = true;
            } else {
                System.out.println("Vous ne pouvez entrez qu'un nombre décimal pour l'id de la location.");
            }
        }
        // Demander les informations à l'utilisateur FIN

        // Créer une requête préparée pour insérer les données dans la table
        String sql3 = "INSERT INTO payment (customer_id, staff_id, rental_id, amount) VALUES (?, ?, ?, ?)";
        PreparedStatement statement2 = connection.prepareStatement(sql3);

        // Définir les valeurs pour les paramètres de la requête
        statement2.setInt(1, customerId);
        statement2.setInt(2, staffId);
        statement2.setInt(3, rentalId);
        statement2.setFloat(4, amount);

        // Exécuter la requête
        statement2.executeUpdate();

    }

    public static void addRental(Connection connection) throws SQLException {
        Affiche.afficheRental(connection);
        Scanner scanner = new Scanner(System.in);

        // Initialiser des variables pour enregistrer les données que rentre l'utilisateur
        int inventoryId = 0;
        int customerId = 0;
        int staffId = 0;

        // Initialiser des booléens pour les boucles while
        boolean isInventoryIdValid = false;
        boolean isCustomerIdValid = false;
        boolean isStaffIdValid = false;

        // Demander les informations à l'utilisateur DEBUT
        while (!isInventoryIdValid) {
            System.out.print("Entrez l'id de la location : ");
            Scanner invent = new Scanner(System.in);
            if (invent.hasNextInt()){
                inventoryId = invent.nextInt();
                isInventoryIdValid = true;
            } else {
                System.out.println("Vous ne pouvez entrez qu'un nombre entier pour l'id de la location.");
            }
        }
        while (!isCustomerIdValid) {
            System.out.print("Entrez l'id de la location : ");
            Scanner cust = new Scanner(System.in);
            if (cust.hasNextInt()){
                customerId = cust.nextInt();
                isCustomerIdValid = true;
            } else {
                System.out.println("Vous ne pouvez entrez qu'un nombre entier pour l'id de la location.");
            }
        }
        while (!isStaffIdValid) {
            System.out.print("Entrez l'id de la location : ");
            Scanner staffy = new Scanner(System.in);
            if (staffy.hasNextInt()){
                staffId = staffy.nextInt();
                isStaffIdValid = true;
            } else {
                System.out.println("Vous ne pouvez entrez qu'un nombre entier pour l'id de la location.");
            }
        }
        // Demander les informations à l'utilisateur FIN

        // Créer une requête préparée pour insérer les données dans la table
        String sql3 = "INSERT INTO rental (inventory_id, customer_id, staff_id) VALUES (?, ?, ?)";
        PreparedStatement statement2 = connection.prepareStatement(sql3);

        // Définir les valeurs pour les paramètres de la requête
        statement2.setInt(1, inventoryId);
        statement2.setInt(2, customerId);
        statement2.setInt(3, staffId);

        // Exécuter la requête
        statement2.executeUpdate();

    }

    public static void addStaff(Connection connection) throws SQLException {
        Affiche.afficheStaff(connection);
        // utiliser un Scanner pour lire les entrées de l'utilisateur
        Scanner scanner = new Scanner(System.in);

        // Variables pour stocker les entrées de l'utilisateur
        String firstName = "";
        String lastName = "";
        int addressId = 0;
        String EMail = "";
        int storeId = 0;
        String username = "";
        String password = "";
        boolean isFirstNameValid = false;
        boolean isLastNameValid = false;
        boolean isAddressIdValid = false;
        boolean isEmailValid = false;
        boolean isStoreIdValid = false;
        boolean isUsernameValid = false;
        boolean isPasswordValid = false;
        boolean isStaffExist = false;

        while (!isFirstNameValid) {
            System.out.print("Entrez le prénom de l'équipier : ");
            firstName = scanner.next();
            // vérifier si le prénom est un nombre
            try {
                Double.parseDouble(firstName);
                System.out.println("Vous ne pouvez pas entrer un nombre pour le prénom.");
            } catch (NumberFormatException e) {
                isFirstNameValid = true;
            }
        }

        while (!isLastNameValid) {
            System.out.print("Entrez son nom : ");
            lastName = scanner.next();
            // vérifier si le nom est un nombre
            try {
                Double.parseDouble(lastName);
                System.out.println("Vous ne pouvez pas entrer un nombre pour le nom.");
            } catch (NumberFormatException e) {
                isLastNameValid = true;
            }
        }

        while (!isAddressIdValid) {
            System.out.print("Entrez le nombre de l'adresse id de la personne : ");
            Scanner idadress = new Scanner(System.in);
            // vérifier si l'id est autre qu'un nombre
            if (idadress.hasNextInt()){
                addressId = idadress.nextInt();
                isAddressIdValid = true;
            } else {
                System.out.println("Vous ne pouvez entrez qu'un nombre entier pour l'adresse id.");
            }
        }

        while (!isEmailValid){
            System.out.print("Entrez son email : ");
            Scanner email = new Scanner(System.in);
            if (email.hasNext()){
                EMail = email.next();
                isEmailValid = true;
            } else {
                System.out.println("L'adresse email ne peut pas être vide.");
            }
        }

        while (!isStoreIdValid) {
            System.out.print("Entrez le nombre du store id de la personne : ");
            Scanner idStore = new Scanner(System.in);
            // vérifier si le nom est un nombre
            if (idStore.hasNextInt()){
                storeId = idStore.nextInt();
                isStoreIdValid = true;
            } else {
                System.out.println("Vous ne pouvez entrez qu'un nombre entier pour l'id du store.");
            }
        }

        while (!isUsernameValid){
            System.out.print("Entrez son pseudonyme : ");
            Scanner User = new Scanner(System.in);
            if (User.hasNext()){
                username = User.next();
                isUsernameValid = true;
            } else {
                System.out.println("Le pseudonyme ne peut pas être vide.");
            }
        }

        while (!isPasswordValid){
            System.out.print("Entrez son mot de passe : ");
            Scanner Pass = new Scanner(System.in);
            if (Pass.hasNext()){
                password = Pass.next();
                isPasswordValid = true;
            } else {
                System.out.println("Le mot de passe ne peut pas être vide.");
            }
        }

        // créer une requête pour vérifier si l'acteur existe déjà dans la table
        String sql = "SELECT * FROM staff WHERE first_name = ? AND last_name = ? AND email = ?";
        PreparedStatement statement = connection.prepareStatement(sql);

        // définir les valeurs pour les paramètres de la requête
        statement.setString(1, firstName);
        statement.setString(2, lastName);
        statement.setString(3, EMail);

        // exécuter la requête
        ResultSet resultSet = statement.executeQuery();

        // vérifier si l'acteur existe déjà dans la table
        while (resultSet.next()) {
            isStaffExist = true;
            break;
        }

        if (isStaffExist) {
            System.out.println("L'acteur existe déjà dans la base de données.");
        } else {
            // créer une requête préparée pour insérer les données dans la table
            String sql2 = "INSERT INTO staff (first_name, last_name, address_id, email, store_id, username, password) VALUES (?, ?, ?, ?, ?, ?, ?)";
            statement = connection.prepareStatement(sql2);

            // définir les valeurs pour les paramètres de la requête
            statement.setString(1, firstName);
            statement.setString(2, lastName);
            statement.setInt(3, addressId);
            statement.setString(4, EMail);
            statement.setInt(5, storeId);
            statement.setString(6, username);
            statement.setString(7, password);

            // exécuter la requête
            statement.executeUpdate();
        }
    }

    public static void addStore(Connection connection) throws SQLException{
        Affiche.afficheStore(connection);
        // utiliser un Scanner pour lire les entrées de l'utilisateur
        Scanner scanner = new Scanner(System.in);

        int managerStaffId = 0;
        int addressId = 0;

        boolean isManagerStaffIdValid = false;
        boolean isAddressIdValid = false;

        while (!isManagerStaffIdValid) {
            System.out.print("Entrez le nombre du store id de la personne : ");
            Scanner manage = new Scanner(System.in);
            // vérifier si le nom est un nombre
            if (manage.hasNextInt()){
                managerStaffId = manage.nextInt();
                isManagerStaffIdValid = true;
            } else {
                System.out.println("Vous ne pouvez entrez qu'un nombre entier pour l'id du store.");
            }
        }

        while (!isAddressIdValid) {
            System.out.print("Entrez le nombre du store id de la personne : ");
            Scanner address = new Scanner(System.in);
            // vérifier si le nom est un nombre
            if (address.hasNextInt()){
                addressId = address.nextInt();
                isAddressIdValid = true;
            } else {
                System.out.println("Vous ne pouvez entrez qu'un nombre entier pour l'id du store.");
            }

            // créer une requête préparée pour insérer les données dans la table
            String sql3 = "INSERT INTO store (manager_store_id, address_id) VALUES (?, ?)";
            PreparedStatement statement2 = connection.prepareStatement(sql3);

            // définir les valeurs pour les paramètres de la requête
            statement2.setInt(1, managerStaffId);
            statement2.setInt(2, addressId);

            // exécuter la requête
            statement2.executeUpdate();
        }

    }
}
