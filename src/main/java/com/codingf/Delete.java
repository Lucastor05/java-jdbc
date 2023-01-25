package com.codingf;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import com.codingf.*;

public class Delete {

    public static void deleteActor(Connection connection) throws SQLException {
        boolean newCountryValide = false;
        while(!newCountryValide) {
            Affiche.afficheActor(connection);
            System.out.print("Entrez l'id de l'acteur que vous souhaitez supprimer : ");
            Scanner newCountry = new Scanner(System.in);

            if (newCountry.hasNextInt()) {
                String name = newCountry.next();
                String query = "SELECT * FROM actor WHERE actor_id = ?";
                PreparedStatement statement = connection.prepareStatement(query);
                statement.setString(1, name);
                ResultSet resultSet = statement.executeQuery();

                if (!resultSet.next()) {
                    System.err.println("\nErreur: Cet acteur n'existe pas !!!");
                } else {


                    query = "DELETE FROM actor WHERE actor_id = ?";
                    statement = connection.prepareStatement(query);
                    statement.setString(1, name);

                    int result = statement.executeUpdate();

                    if (result == 1) {
                        System.out.println("\nLa valeur a été supprimée avec succès dans la table actor");
                    } else {
                        System.out.println("\nErreur lors de la suppression de la valeur dans la table actor");
                    }

                    newCountryValide = true;
                }
            }else{
                System.err.println("\nErreur: Ceci n'est pas un nombre");
            }
        }
    }
    public static void deleteAddress(Connection connection) throws SQLException {
        boolean newCountryValide = false;
        while(!newCountryValide) {
            Affiche.afficheAddress(connection);
            System.out.print("Entrez l'id de l'adresse que vous souhaitez supprimer : ");
            Scanner newCountry = new Scanner(System.in);

            if (newCountry.hasNextInt()) {
                String name = newCountry.next();
                String query = "SELECT * FROM address WHERE address_id = ?";
                PreparedStatement statement = connection.prepareStatement(query);
                statement.setString(1, name);
                ResultSet resultSet = statement.executeQuery();

                if (!resultSet.next()) {
                    System.err.println("\nErreur: Cette adresse n'existe pas !!!");
                } else {


                    query = "DELETE FROM address WHERE address_id = ?";
                    statement = connection.prepareStatement(query);
                    statement.setString(1, name);

                    int result = statement.executeUpdate();

                    if (result == 1) {
                        System.out.println("\nLa valeur a été supprimée avec succès dans la table address");
                    } else {
                        System.out.println("\nErreur lors de la suppression de la valeur dans la table address");
                    }

                    newCountryValide = true;
                }
            }else{
                System.err.println("\nErreur: Ceci n'est pas un nombre");
            }
        }
    }

    public static void deleteCategory(Connection connection) throws SQLException {
        boolean newCountryValide = false;
        while(!newCountryValide) {
            Affiche.afficheCategory(connection);
            System.out.print("Entrez l'id de la categorie que vous souhaitez supprimer : ");
            Scanner newCountry = new Scanner(System.in);

            if (newCountry.hasNextInt()) {
                String name = newCountry.next();
                String query = "SELECT * FROM category WHERE category_id = ?";
                PreparedStatement statement = connection.prepareStatement(query);
                statement.setString(1, name);
                ResultSet resultSet = statement.executeQuery();

                if (!resultSet.next()) {
                    System.err.println("\nErreur: Cette categorie n'existe pas !!!");
                } else {


                    query = "DELETE FROM category WHERE category_id = ?";
                    statement = connection.prepareStatement(query);
                    statement.setString(1, name);

                    int result = statement.executeUpdate();

                    if (result == 1) {
                        System.out.println("\nLa valeur a été supprimée avec succès dans la table category");
                    } else {
                        System.out.println("\nErreur lors de la suppression de la valeur dans la table category");
                    }

                    newCountryValide = true;
                }
            }else{
                System.err.println("\nErreur: Ceci n'est pas un nombre");
            }
        }
    }

    public static void deleteCity(Connection connection) throws SQLException {
        // utiliser un Scanner pour lire l'entrée de l'utilisateur
        Scanner scanner = new Scanner(System.in);
        Affiche.afficheCity(connection);
        // Variable pour stocker l'entrée de l'utilisateur
        int cityID = 0;

        while (true) {
            System.out.print("Entrez l'ID de la ville à supprimer: ");
            if (scanner.hasNextInt()) {
                cityID = scanner.nextInt();
                break;
            } else {
                System.out.println("L'ID doit être un entier. Veuillez réessayer.");
                scanner.next();
            }
        }

        // définir les informations de connexion à la base de données
        String url = "jdbc:mysql://localhost:3306/your_database_name";
        String user = "your_username";
        String password = "your_password";

        // créer une requête préparée pour supprimer la ville de la table
        String sql = "DELETE FROM city WHERE city_id = ?";
        PreparedStatement statement = connection.prepareStatement(sql);

        // définir la valeur pour le paramètre de la requête
        statement.setInt(1, cityID);

        // exécuter la requête
        int rowsDeleted = statement.executeUpdate();
        if (rowsDeleted > 0) {
            System.out.println("La ville a été supprimée avec succès.");
        } else {
            System.out.println("Aucune ville n'a été trouvée avec cet ID.");
        }

    }

    public static void deleteCountry(Connection connection) throws SQLException {
        boolean newCountryValide = false;
        while(!newCountryValide) {
            Affiche.afficheCountry(connection);
            System.out.print("Entrez le nom du pays que vous souhaitez supprimer : ");
            Scanner newCountry = new Scanner(System.in);

            if (!newCountry.hasNextInt()) {
                String name = newCountry.next();
                String query = "SELECT * FROM country WHERE country = ?";
                PreparedStatement statement = connection.prepareStatement(query);
                statement.setString(1, name);
                ResultSet resultSet = statement.executeQuery();

                if (!resultSet.next()) {
                    System.err.println("\nErreur: Le pays n'existe pas !!!");
                } else {


                    query = "DELETE FROM country WHERE country = ?";
                    statement = connection.prepareStatement(query);
                    statement.setString(1, name);

                    int result = statement.executeUpdate();

                    if (result == 1) {
                        System.out.println("\nLa valeur a été supprimée avec succès dans la table country");
                    } else {
                        System.out.println("\nErreur lors de la suppression de la valeur dans la table country");
                    }

                    newCountryValide = true;
                }
            }else{
                System.err.println("\nErreur: Le nom ne peut etre un numéro");
            }
        }
    }

    public static void deleteCustomer(Connection connection) throws SQLException {
        boolean newCountryValide = false;
        while(!newCountryValide) {
            Affiche.afficheCustomer(connection);
            System.out.print("Entrez l'id du client que vous souhaitez supprimer : ");
            Scanner newCountry = new Scanner(System.in);

            if (newCountry.hasNextInt()) {
                String name = newCountry.next();
                String query = "SELECT * FROM customer WHERE customer_id = ?";
                PreparedStatement statement = connection.prepareStatement(query);
                statement.setString(1, name);
                ResultSet resultSet = statement.executeQuery();

                if (!resultSet.next()) {
                    System.err.println("\nErreur: Ce client n'existe pas !!!");
                } else {


                    query = "DELETE FROM customer WHERE customer_id = ?";
                    statement = connection.prepareStatement(query);
                    statement.setString(1, name);

                    int result = statement.executeUpdate();

                    if (result == 1) {
                        System.out.println("\nLa valeur a été supprimée avec succès dans la table customer");
                    } else {
                        System.out.println("\nErreur lors de la suppression de la valeur dans la table customer");
                    }

                    newCountryValide = true;
                }
            }else{
                System.err.println("\nErreur: Ceci n'est pas un nombre");
            }
        }
    }





}
