package com.codingf;

import java.sql.*;
import java.util.Scanner;

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
        // Connect to the database
        Statement stmt = connection.createStatement();

        // Get the customer information from the user
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the store ID: ");
        int store_id = sc.nextInt();
        sc.nextLine();
        System.out.print("Enter the first name: ");
        String first_name = sc.nextLine();
        System.out.print("Enter the last name: ");
        String last_name = sc.nextLine();
        System.out.print("Enter the email address: ");
        String email = sc.nextLine();
        System.out.print("Enter the address ID: ");
        int address_id = sc.nextInt();
        System.out.print("Enter the active status (1 or 0): ");
        int active = sc.nextInt();

        // Validate the input values
        if (store_id < 0 || store_id > 255) {
            System.out.println("Error: Store ID must be between 0 and 255");
            return;
        }
        if (first_name.length() > 45) {
            System.out.println("Error: First name must be 45 characters or less");
            return;
        }
        if (last_name.length() > 45) {
            System.out.println("Error: Last name must be 45 characters or less");
            return;
        }
        if (email.length() > 50) {
            System.out.println("Error: Email must be 50 characters or less");
            return;
        }
        if (address_id < 0 || address_id > 65535) {
            System.out.println("Error: Address ID must be between 0 and 65535");
            return;
        }
        if (active != 0 && active != 1) {
            System.out.println("Error: Active status must be 0 or 1");
            return;
        }

        // Insert the customer into the table
        String sql = "INSERT INTO customer (store_id, first_name, last_name, email, address_id, active, create_date) VALUES (?, ?, ?, ?, ?, ?, NOW())";
        PreparedStatement pstmt = connection.prepareStatement(sql);
        pstmt.setInt(1, store_id);
        pstmt.setString(2, first_name);
        pstmt.setString(3, last_name);
        pstmt.setString(4, email);
        pstmt.setInt(5, address_id);
        pstmt.setInt(6, active);
        pstmt.executeUpdate();


    }


}
