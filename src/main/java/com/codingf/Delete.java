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

    public static void removeFilm(Connection connection) throws SQLException {
        Affiche.afficheFilm(connection);

        boolean newRemoveFilm = false;
        while(!newRemoveFilm) {
            ResultSet result;
            PreparedStatement statement;

            //demande a l'utilisateur quel film il veut supprimer
            System.out.println("quel film id voulez vous supprimer ?");
            Scanner removeFilmId = new Scanner(System.in);
            int film_id = removeFilmId.nextInt();

            //verifie si le film existe
            String sql = "SELECT * FROM film WHERE film_id = ?";
            statement = connection.prepareStatement(sql);
            statement.setString(1, Integer.toString(film_id));
            result = statement.executeQuery();


            if (!result.next()) {
                System.err.println("le film existe pas :P");
                newRemoveFilm = true;

            } else {

                //si le film existe alors on le supprime
                sql = "DELETE FROM film WHERE film_id = ?";
                statement = connection.prepareStatement(sql);

                statement.setString(1, Integer.toString(film_id));

                statement.executeUpdate();

                System.out.println("film a ete supprimer");
                newRemoveFilm = true;
            }
        }
    }

    public static void removeFilmActor(Connection connection) throws SQLException{
        Affiche.afficheFilmActor(connection);

        boolean newRemoveFilm = false;
        while(!newRemoveFilm) {
            ResultSet result;
            PreparedStatement statement;

            //demande l'id dans actor
            System.out.println("quel actor id voulez vous supprimer ?");
            Scanner removeIdActor = new Scanner(System.in);
            int actor_id = removeIdActor.nextInt();

            //demande l'id d'en film
            System.out.println("quel film id voulez vous supprimer ?");
            Scanner removeIdfilm = new Scanner(System.in);
            int film_id = removeIdfilm.nextInt();

            //check si le film et actor existe
            String sql = "SELECT * FROM film_actor WHERE actor_id = ? AND film_id = ?";
            statement = connection.prepareStatement(sql);
            statement.setString(1, Integer.toString(actor_id));
            statement.setString(2, Integer.toString(film_id));
            result = statement.executeQuery();


            if (!result.next()) {
                System.err.println("le film actor existe pas :P");
                newRemoveFilm = true;

            } else {

                //si le film et actor existe alors on supprime
                sql = "DELETE FROM film_actor WHERE actor_id = ? AND film_id = ?";
                statement = connection.prepareStatement(sql);

                statement.setString(1, Integer.toString(actor_id));
                statement.setString(2, Integer.toString(film_id));
                statement.executeUpdate();

                System.out.println("film actor a ete supprimer");
                newRemoveFilm = true;
            }
        }
    }

    public static void removeFilmCate(Connection connection) throws SQLException{
        Affiche.afficheFilmCate(connection);

        boolean newRemoveFilm = false;
        while(!newRemoveFilm) {
            ResultSet result;
            PreparedStatement statement;

            //demande l'id du film
            System.out.println("quel film id voulez vous supprimer ?");
            Scanner removeFilmId = new Scanner(System.in);
            int film_id = removeFilmId.nextInt();

            //demande l'id du category
            System.out.println("quel category id voulez vous supprimer ?");
            Scanner removeCateId = new Scanner(System.in);
            int cate_id = removeCateId.nextInt();

            //check si il existe
            String sql = "SELECT * FROM film_category WHERE category_id = ? AND film_id = ?";
            statement = connection.prepareStatement(sql);
            statement.setString(1, Integer.toString(cate_id));
            statement.setString(2, Integer.toString(film_id));
            result = statement.executeQuery();


            if (!result.next()) {
                System.err.println("le film category existe pas :P");
                newRemoveFilm = true;

            } else {

                //si il existe alors on supprime
                sql = "DELETE FROM film_category WHERE category_id = ? AND film_id = ?";
                statement = connection.prepareStatement(sql);

                statement.setString(1, Integer.toString(cate_id));
                statement.setString(2, Integer.toString(film_id));
                statement.executeUpdate();

                System.out.println("film category a ete supprimer");
                newRemoveFilm = true;
            }
        }
    }

    public static void removeFilmText(Connection connection) throws SQLException{
        Affiche.afficheFilmText(connection);

        boolean newRemoveFilm = false;
        while(!newRemoveFilm) {
            ResultSet result;
            PreparedStatement statement;

            //demande l'id du film
            System.out.println("quel film id voulez vous supprimer ?");
            Scanner removeFilmId = new Scanner(System.in);
            int film_id = removeFilmId.nextInt();

            //check si il existe
            String sql = "SELECT * FROM film_text WHERE film_id = ?";
            statement = connection.prepareStatement(sql);
            statement.setString(1, Integer.toString(film_id));
            result = statement.executeQuery();


            if (!result.next()) {
                System.err.println("le film text existe pas :P");
                newRemoveFilm = true;

            } else {

                //si il existe alors on supprime
                sql = "DELETE FROM film_text WHERE film_id = ?";
                statement = connection.prepareStatement(sql);

                statement.setString(1, Integer.toString(film_id));
                statement.executeUpdate();

                System.out.println("film text a ete supprimer");
                newRemoveFilm = true;
            }
        }
    }

    public static void removeInventory(Connection connection) throws SQLException{
        Affiche.afficheInventory(connection);

        boolean newRemoveFilm = false;
        while(!newRemoveFilm) {
            ResultSet result;
            PreparedStatement statement;

            //demande l'id du inventory
            System.out.println("quel inventory id voulez vous supprimer ?");
            Scanner removeInvId = new Scanner(System.in);
            int inventory_id = removeInvId.nextInt();

            String sql = "SELECT * FROM inventory WHERE inventory_id = ?";
            statement = connection.prepareStatement(sql);
            statement.setString(1, Integer.toString(inventory_id));
            result = statement.executeQuery();


            if (!result.next()) {
                System.err.println("le film text existe pas :P");
                newRemoveFilm = true;

            } else {

                //si il existe alors on suppprime
                sql = "DELETE FROM inventory WHERE inventory_id = ?";
                statement = connection.prepareStatement(sql);

                statement.setString(1, Integer.toString(inventory_id));
                statement.executeUpdate();

                System.out.println("inventory a ete supprimer");
                newRemoveFilm = true;
            }
        }
    }

    public static void removeLanguage(Connection connection) throws SQLException{
        Affiche.afficheLanguage(connection);

        boolean newRemoveFilm = false;
        while(!newRemoveFilm) {
            ResultSet result;
            PreparedStatement statement;

            //demande l'id de language
            System.out.println("quel language id voulez vous supprimer ?");
            Scanner removeLangId = new Scanner(System.in);
            int language_id = removeLangId.nextInt();

            //check si il existe
            String sql = "SELECT * FROM language WHERE language_id = ?";
            statement = connection.prepareStatement(sql);
            statement.setString(1, Integer.toString(language_id));
            result = statement.executeQuery();


            if (!result.next()) {
                System.err.println("le language existe pas :P");
                newRemoveFilm = true;

            } else {

                //si il existe alors on supprime
                sql = "DELETE FROM language WHERE language_id = ?";
                statement = connection.prepareStatement(sql);

                statement.setString(1, Integer.toString(language_id));
                statement.executeUpdate();

                System.out.println("language a ete supprimer");
                newRemoveFilm = true;
            }
        }
    }

    public static void deletePayment(Connection connection) throws SQLException {
        // Initialiser un booléens pour la boucle while
        boolean newPaymentValide = false;
        while(!newPaymentValide) {
            Affiche.affichePayment(connection);
            System.out.print("Entrez l'id du paiement que vous souhaitez supprimer : ");
            Scanner newPayment = new Scanner(System.in);

            // Vérifier si l'id existe dans la table
            if (newPayment.hasNextInt()) {
                String name = newPayment.next();
                String query = "SELECT * FROM payment WHERE payment_id = ?";
                PreparedStatement statement = connection.prepareStatement(query);
                statement.setString(1, name);
                ResultSet resultSet = statement.executeQuery();

                // Supprimer s'il n'y a pas d'erreurs
                if (!resultSet.next()) {
                    System.err.println("\nErreur: l'id de ce paiement n'existe pas.");
                } else {
                    query = "DELETE FROM payment WHERE payment_id = ?";
                    statement = connection.prepareStatement(query);
                    statement.setString(1, name);

                    int result = statement.executeUpdate();

                    if (result == 1) {
                        System.out.println("\nLes informations de l'id entré ont été supprimées avec succès dans la table payment.");
                    } else {
                        System.out.println("\nErreur lors de la suppression des informations dans la table payment.");
                    }

                    newPaymentValide = true;
                }
            }else{
                System.err.println("\nErreur: ceci n'est pas un nombre.");
            }
        }
    }

    public static void deleteRental(Connection connection) throws SQLException {
        boolean newRentalValide = false;
        while(!newRentalValide) {
            Affiche.afficheRental(connection);
            System.out.print("Entrez l'id de la location que vous souhaitez supprimer : ");
            Scanner newRental = new Scanner(System.in);

            if (newRental.hasNextInt()) {
                String name = newRental.next();
                String query = "SELECT * FROM rental WHERE rental_id = ?";
                PreparedStatement statement = connection.prepareStatement(query);
                statement.setString(1, name);
                ResultSet resultSet = statement.executeQuery();

                if (!resultSet.next()) {
                    System.err.println("\nErreur: l'id de cette location n'existe pas.");
                } else {
                    query = "DELETE FROM rental WHERE rental_id = ?";
                    statement = connection.prepareStatement(query);
                    statement.setString(1, name);

                    int result = statement.executeUpdate();

                    if (result == 1) {
                        System.out.println("\nLes informations de l'id entré ont été supprimées avec succès dans la table rental.");
                    } else {
                        System.out.println("\nErreur lors de la suppression des informations dans la table rental.");
                    }

                    newRentalValide = true;
                }
            }else{
                System.err.println("\nErreur: ceci n'est pas un nombre.");
            }
        }
    }

    public static void deleteStaff(Connection connection) throws SQLException {
        boolean newStaffValide = false;
        while(!newStaffValide) {
            Affiche.afficheStaff(connection);
            System.out.print("Entrez l'id de la personne dans l'équipe que vous souhaitez supprimer : ");
            Scanner newStaff = new Scanner(System.in);

            if (newStaff.hasNextInt()) {
                String name = newStaff.next();
                String query = "SELECT * FROM staff WHERE staff_id = ?";
                PreparedStatement statement = connection.prepareStatement(query);
                statement.setString(1, name);
                ResultSet resultSet = statement.executeQuery();

                if (!resultSet.next()) {
                    System.err.println("\nErreur: l'id de cette personne de l'équipe n'existe pas.");
                } else {
                    query = "DELETE FROM staff WHERE staff_id = ?";
                    statement = connection.prepareStatement(query);
                    statement.setString(1, name);

                    int result = statement.executeUpdate();

                    if (result == 1) {
                        System.out.println("\nLes informations de l'id entré ont été supprimées avec succès dans la table staff.");
                    } else {
                        System.out.println("\nErreur lors de la suppression des informations dans la table staff.");
                    }

                    newStaffValide = true;
                }
            }else{
                System.err.println("\nErreur: ceci n'est pas un nombre.");
            }
        }
    }

    public static void deleteStore(Connection connection) throws SQLException {
        boolean newStoreValide = false;
        while(!newStoreValide) {
            Affiche.afficheStore(connection);
            System.out.print("Entrez l'id du magasin que vous souhaitez supprimer : ");
            Scanner newStore = new Scanner(System.in);

            if (newStore.hasNextInt()) {
                String name = newStore.next();
                String query = "SELECT * FROM store WHERE store_id = ?";
                PreparedStatement statement = connection.prepareStatement(query);
                statement.setString(1, name);
                ResultSet resultSet = statement.executeQuery();

                if (!resultSet.next()) {
                    System.err.println("\nErreur: l'id de ce magasin n'existe pas.");
                } else {
                    query = "DELETE FROM store WHERE store_id = ?";
                    statement = connection.prepareStatement(query);
                    statement.setString(1, name);

                    int result = statement.executeUpdate();

                    if (result == 1) {
                        System.out.println("\nLes informations de l'id entré ont été supprimées avec succès dans la table store.");
                    } else {
                        System.out.println("\nErreur lors de la suppression des informations dans la table store.");
                    }

                    newStoreValide = true;
                }
            }else{
                System.err.println("\nErreur: ceci n'est pas un nombre.");
            }
        }
    }

}
