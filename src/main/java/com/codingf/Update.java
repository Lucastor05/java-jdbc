package com.codingf;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Update {

    public static void updateActor(Connection connection){
        try {
            // Demande à l'utilisateur les informations nécessaires
            Scanner scanner = new Scanner(System.in);
            int actorId = 0;
            boolean isValidId = false;
            while (!isValidId) {
                System.out.print("Entrez l'ID de l'acteur à mettre à jour : ");
                actorId = scanner.nextInt();
                // Vérifie si l'ID de l'acteur existe dans la table
                String sql = "SELECT COUNT(*) FROM actor WHERE actor_id = ?";
                PreparedStatement statement = connection.prepareStatement(sql);
                statement.setInt(1, actorId);
                ResultSet result = statement.executeQuery();
                if (result.next() && result.getInt(1) == 1) {
                    isValidId = true;
                } else {
                    System.out.println("ID de l'acteur invalide, veuillez réessayer");
                }
            }
            System.out.print("Entrez le nouveau prénom de l'acteur : ");
            String firstName = scanner.next();
            System.out.print("Entrez le nouveau nom de l'acteur : ");
            String lastName = scanner.next();

            // Vérifie la validité des entrées de l'utilisateur
            if (firstName.length() > 45) {
                System.out.println("Le prénom est trop long (45 caractères maximum)");
                return;
            }
            if (lastName.length() > 45) {
                System.out.println("Le nom est trop long (45 caractères maximum)");
                return;
            }

            // Prépare la requête SQL
            String sql = "UPDATE actor SET first_name = ?, last_name = ? WHERE actor_id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, firstName);
            statement.setString(2, lastName);
            statement.setInt(3, actorId);

            // Exécute la requête SQL
            statement.executeUpdate();
            System.out.println("Acteur mis à jour avec succès");
        } catch (SQLException e) {
            System.out.println("Erreur lors de la mise à jour de l'acteur : " + e.getMessage());
        }
    }
    public static void updateAddress(Connection connection){
        try {
            // Demande à l'utilisateur les informations nécessaires
            Scanner scanner = new Scanner(System.in);
            int addressId = 0;
            boolean isValidId = false;
            while (!isValidId) {
                System.out.print("Entrez l'ID de l'adresse à mettre à jour : ");
                addressId = scanner.nextInt();
                // Vérifie si l'ID de l'adresse existe dans la table
                String sql = "SELECT COUNT(*) FROM address WHERE address_id = ?";
                PreparedStatement statement = connection.prepareStatement(sql);
                statement.setInt(1, addressId);
                ResultSet result = statement.executeQuery();
                if (result.next() && result.getInt(1) == 1) {
                    isValidId = true;
                } else {
                    System.out.println("ID de l'adresse invalide, veuillez réessayer");
                }
            }
            System.out.print("Entrez la nouvelle adresse : ");
            scanner.nextLine();
            String address = scanner.nextLine();
            System.out.print("Entrez la nouvelle adresse (ligne 2): ");
            String address2 = scanner.nextLine();
            System.out.print("Entrez le nouveau district : ");
            String district = scanner.nextLine();
            int cityId = 0;
            boolean isValidCityId = false;
            while (!isValidCityId) {
                System.out.print("Entrez l'ID de la ville : ");
                cityId = scanner.nextInt();
                // Vérifie si l'ID de la ville existe dans la table
                String sql = "SELECT COUNT(*) FROM city WHERE city_id = ?";
                PreparedStatement statement = connection.prepareStatement(sql);
                statement.setInt(1, cityId);
                ResultSet result = statement.executeQuery();
                if (result.next() && result.getInt(1) == 1) {
                    isValidCityId = true;
                } else {
                    System.out.println("ID de la ville invalide, veuillez réessayer");
                }
            }
            System.out.print("Entrez le nouveau code postal : ");
            scanner.nextLine();
            String postalCode = scanner.nextLine();
            System.out.print("Entrez le nouveau numéro de téléphone : ");
            String phone = scanner.nextLine();
            System.out.print("Entrez la nouvelle localisation, exemple : POINT(12.34 56.78): ");
            String location = scanner.nextLine();
            // Vérifie la longueur des entrées pour les champs adresse, adresse 2, district, code postal et numéro de téléphone
            if (address.length() > 50) {
                System.out.println("L'adresse saisie est trop longue (50 caractères maximum)");
                return;
            }
            if (address2.length() > 50) {
                System.out.println("L'adresse (ligne 2) saisie est trop longue (50 caractères maximum)");
                return;
            }
            if (district.length() > 20) {
                System.out.println("Le district saisi est trop long (20 caractères maximum)");
                return;
            }
            if (postalCode.length() > 10) {
                System.out.println("Le code postal saisi est trop long (10 caractères maximum)");
                return;
            }
            if (phone.length() > 20) {
                System.out.println("Le numéro de téléphone saisi est trop long (20 caractères maximum)");
                return;
            }
            if (!location.matches("^POINT\\(-?\\d+\\.\\d+ -?\\d+\\.\\d+\\)$")) {
                System.out.println("Format de localisation non valide. Veuillez entrer une localisation valide au format POINT(x y) où x et y sont des coordonnées géographiques.");
                return;
            }
            // Exécute la mise à jour de l'adresse dans la table
            String sql = "UPDATE address SET address = ?, address2 = ?, district = ?, city_id = ?, postal_code = ?, phone = ?, location = ST_GeomFromText(?) WHERE address_id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, address);
            statement.setString(2, address2);
            statement.setString(3, district);
            statement.setInt(4, cityId);
            statement.setString(5, postalCode);
            statement.setString(6, phone);
            statement.setString(7, location);
            statement.setInt(8, addressId);
            statement.executeUpdate();
            System.out.println("Adresse mise à jour avec succès");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void updateCategory(Connection connection){
        Scanner scanner = new Scanner(System.in);

        try {
            System.out.print("Entrez l'ID de la catégorie à mettre à jour : ");
            int categoryId = scanner.nextInt();
            scanner.nextLine();

            // Vérifie si l'ID de la catégorie existe dans la table
            String sql = "SELECT COUNT(*) FROM category WHERE category_id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, categoryId);
            ResultSet result = statement.executeQuery();
            if (!result.next() || result.getInt(1) != 1) {
                System.out.println("ID de catégorie non valide, veuillez réessayer.");
                return;
            }

            System.out.print("Entrez le nouveau nom de la catégorie : ");
            String name = scanner.nextLine();

            // Mise à jour de la catégorie dans la table
            String querry = "UPDATE category SET name = ? WHERE category_id = ?";
            statement = connection.prepareStatement(querry);
            statement.setString(1, name);
            statement.setInt(2, categoryId);
            statement.executeUpdate();
            System.out.println("Catégorie mise à jour avec succès");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void updateCity(Connection connection){
        Scanner scanner = new Scanner(System.in);

        try {
            System.out.print("Entrez l'ID de la ville à mettre à jour : ");
            int cityId = scanner.nextInt();
            scanner.nextLine();

            // Vérifie si l'ID de la ville existe dans la table
            String sql = "SELECT COUNT(*) FROM city WHERE city_id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, cityId);
            ResultSet result = statement.executeQuery();
            if (!result.next() || result.getInt(1) != 1) {
                System.out.println("ID de ville non valide, veuillez réessayer.");
                return;
            }

            System.out.print("Entrez le nouveau nom de la ville : ");
            String city = scanner.nextLine();

            System.out.print("Entrez l'ID du pays associé à cette ville : ");
            int countryId = scanner.nextInt();
            scanner.nextLine();

            // Vérifie si l'ID du pays existe dans la table
            sql = "SELECT COUNT(*) FROM country WHERE country_id = ?";
            statement = connection.prepareStatement(sql);
            statement.setInt(1, countryId);
            result = statement.executeQuery();
            if (!result.next() || result.getInt(1) != 1) {
                System.out.println("ID de pays non valide, veuillez réessayer.");
                return;
            }

            //Mise à jour de la ville dans la table
            String querry = "UPDATE city SET city = ?, country_id = ? WHERE city_id = ?";
            statement = connection.prepareStatement(querry);
            statement.setString(1, city);
            statement.setInt(2, countryId);
            statement.setInt(3, cityId);
            statement.executeUpdate();
            System.out.println("Ville mise à jour avec succès");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void updateCountry(Connection connection) throws SQLException {
        boolean newCountryValide = false;
        while(!newCountryValide) {
            Affiche.afficheCountry(connection);

            System.out.print("\nEntrez le numéro du pays que vous souhaitez modifier : ");
            Scanner newCountry = new Scanner(System.in);

            if (newCountry.hasNextInt()) {
                int name = newCountry.nextInt();
                String query;
                PreparedStatement statement;
                boolean nameCountryValide = false;

                while(!nameCountryValide) {
                    System.out.print("Entrez le nouveau nom du pays : ");
                    Scanner newCountryName = new Scanner(System.in);

                    if (!newCountryName.hasNextInt()) {
                        String newName = newCountryName.next();
                        query = "UPDATE country SET country = ? AND last_update = NOW() WHERE country_id = ?";
                        statement = connection.prepareStatement(query);
                        statement.setString(1, newName);
                        statement.setInt(2, name);

                        int result = statement.executeUpdate();

                        if (result == 1) {
                            System.out.println("\nLa valeur a été modifiée avec succès dans la table country");
                        } else {
                            System.out.println("\nErreur lors de la modification de la valeur dans la table country");
                        }
                        nameCountryValide = true;
                    }else{
                        System.err.println("\nErreur: Le nom ne peut etre un numéro");
                    }
                }

                newCountryValide = true;

            }else{
                System.err.println("\nErreur: Le nom ne peut etre un numéro");
            }
        }
    }

    public static void updateCustomer(Connection connection){
        Scanner scanner = new Scanner(System.in);
        try {
            System.out.print("Entrez l'ID du client à mettre à jour : ");
            int customerId = scanner.nextInt();
            scanner.nextLine();

            // Vérifie si l'ID du client existe dans la table
            String sql = "SELECT COUNT(*) FROM customer WHERE customer_id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, customerId);
            ResultSet result = statement.executeQuery();
            if (!result.next() || result.getInt(1) != 1) {
                System.out.println("ID de client non valide, veuillez réessayer.");
                return;
            }

            System.out.print("Entrez le nouveau prénom du client : ");
            String firstName = scanner.nextLine();

            System.out.print("Entrez le nouveau nom de famille du client : ");
            String lastName = scanner.nextLine();

            System.out.print("Entrez le nouvel email du client : ");
            String email = scanner.nextLine();
            System.out.print("Entrez l'ID de l'adresse associée à ce client : ");
            int addressId = scanner.nextInt();
            scanner.nextLine();

            // Vérifie si l'ID de l'adresse existe dans la table
            sql = "SELECT COUNT(*) FROM address WHERE address_id = ?";
            statement = connection.prepareStatement(sql);
            statement.setInt(1, addressId);
            result = statement.executeQuery();
            if (!result.next() || result.getInt(1) != 1) {
                System.out.println("ID d'adresse non valide, veuillez réessayer.");
                return;
            }

            System.out.print("Entrez l'ID de la boutique associée à ce client : ");
            int storeId = scanner.nextInt();
            scanner.nextLine();

            // Vérifie si l'ID de la boutique existe dans la table
            sql = "SELECT COUNT(*) FROM store WHERE store_id = ?";
            statement = connection.prepareStatement(sql);
            statement.setInt(1, storeId);
            result = statement.executeQuery();
            if (!result.next() || result.getInt(1) != 1) {
                System.out.println("ID de boutique non valide, veuillez réessayer.");
                return;
            }

            // Update customer information
            String querry = "UPDATE customer SET first_name = ?, last_name = ?, email = ?, address_id = ?, store_id = ? WHERE customer_id = ?";
            statement = connection.prepareStatement(querry);
            statement.setString(1, firstName);
            statement.setString(2, lastName);
            statement.setString(3, email);
            statement.setInt(4, addressId);
            statement.setInt(5, storeId);
            statement.setInt(6, customerId);
            int rowsAffected = statement.executeUpdate();

            if (rowsAffected == 1) {
                System.out.println("Mise à jour du client effectuée avec succès.");
            } else {
                System.out.println("La mise à jour du client a échoué, veuillez réessayer.");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

    }





}
