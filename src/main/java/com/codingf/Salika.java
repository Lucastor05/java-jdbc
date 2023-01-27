package com.codingf;

import com.mysql.cj.jdbc.exceptions.SQLError;
import com.codingf.*;

import java.sql.*;
import java.util.ResourceBundle;
import java.util.Scanner;

public class Salika {

    public static void main(String[] args) throws SQLException {

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e){
            System.err.println("Problème de chargement sur le driver.");
            System.exit(-1);
        }

        System.out.println("Le driver est chargé.");

        ResourceBundle bundle = ResourceBundle.getBundle("db");

        // url de connexion
        String host = bundle.getString("db.host");
        String dbName = bundle.getString("db.dbase");
        String port = bundle.getString("db.port");
        String URL ="jdbc:mysql://"+host +":"+port +"/"+dbName;
        //
        String username = bundle.getString("db.user");
        String password = bundle.getString("db.pass");
        //
        Connection connection =
                DriverManager.getConnection(URL,username,password);

        if (connection == null) {
            System.err.println("Une erreur de connexion est survenue.");
        } else {
            System.err.println("La connexion a été établie.");

        }

        DatabaseMetaData dbMetaData=connection.getMetaData();
        String productName=dbMetaData.getDatabaseProductName();
        System.out.println("Database: "+productName);
        String productVersion=dbMetaData.getDatabaseProductVersion();
        System.out.println("Version: "+productVersion);
        boolean restart = true;

        while(restart) {
            boolean valide = false;
            while (!valide) {

                System.out.println("\n\n1. actor\n2. actor_info\n3. address\n4. category\n5. city\n6. country\n7. customer\n8. customer_list\n9. film\n10. film_actor\n11. film_category\n12. film_list\n13. film_text\n14. inventory\n15. language\n16. nicer_but_slower_film_list\n17. payment\n18. rental\n19. sales_by_film_category\n20. sales_by_store\n21. staff\n22. staff_list\n23. store\n24. exit");
                System.out.println("Entrez votre choix :");
                Scanner choice = new Scanner(System.in);

                if (choice.hasNextInt()) {
                    int choix = choice.nextInt();
                    if (choix <= 24 || choix >= 1) {
                        if(choix == 1){
                            choiceActor(connection);
                        }else if(choix == 2){
                            Affiche.afficheActorInfo(connection);
                            valide = true;
                        }else if(choix == 3){
                            choiceAddress(connection);
                            valide = true;
                        }else if(choix == 4){
                            choiceCategory(connection);
                            valide = true;
                        }else if(choix == 5){
                            choiceCity(connection);
                            valide = true;
                        }else if (choix == 6) {
                            choiceCountry(connection);
                            valide = true;
                        }else if (choix == 7) {
                            choiceCustomer(connection);
                            valide = true;
                        }else if (choix == 8) {
                            Affiche.afficheCustomerList(connection);
                            valide = true;

                        }else if (choix == 9) {
                            choiceFilm(connection);
                            valide = true;

                        }else if (choix == 10) {
                            choiceFilmActor(connection);
                            valide = true;

                        }else if (choix == 11) {
                            choiceFilmCate(connection);
                            valide = true;

                        }else if (choix == 12) {
                            Affiche.afficheFilmList(connection);
                            valide = true;

                        }else if (choix == 13) {
                            choiceFilmText(connection);
                            valide = true;

                        }else if (choix == 14) {
                            choiceInventory(connection);
                            valide = true;

                        }else if (choix == 15) {
                            choiceLanguage(connection);
                            valide = true;

                        }else if (choix == 16) {
                            Affiche.afficheNicerFilm(connection);
                            valide = true;

                        }else if (choix == 17) {
                            choicePayement(connection);
                            valide = true;

                        }else if (choix == 18) {
                            choiceRental(connection);
                            valide = true;

                        }else if (choix == 19) {
                            Affiche.afficheSalesCategory(connection);
                            valide = true;

                        }else if (choix == 20) {
                            Affiche.afficheSalesStore(connection);
                            valide = true;

                        }else if (choix == 21) {
                            choiceStaff(connection);
                            valide = true;

                        }else if (choix == 22) {
                            Affiche.afficheStaffList(connection);
                            valide = true;

                        }else if (choix == 23) {
                            choiceStore(connection);
                            valide = true;

                        }else if(choix == 24){
                            System.exit(-1);
                        }
                    } else {
                        System.err.println("Erreur : le nombre choisi ne correspond à aucun choix proposé.");
                    }
                } else {
                    System.err.println("Erreur : vous n'avez pas tapé de nombre.");
                }
            }
        }
    }

    public static void choiceActor(Connection connection) throws SQLException{
        boolean restart = true;

        while(restart) {
            boolean valide = false;

            while(!valide) {
                System.out.println("\n1. Ajouter un nouvel acteur\n2. Afficher les acteurs\n3. Mettre à jour les données d'un acteur\n4. Supprimer un acteur\n5. Retour");
                System.out.println("Entrez votre choix :");
                Scanner choice = new Scanner(System.in);
                if (choice.hasNextInt()) {
                    int choix = choice.nextInt();
                    if (choix <= 5 || choix >= 1) {
                        if (choix == 1) {
                            Add.addActor(connection);
                            valide = true;
                        } else if (choix == 2) {
                            Affiche.afficheActor(connection);
                            valide = true;
                        } else if (choix == 3) {
                            Update.updateActor(connection);
                            valide = true;
                        } else if (choix == 4) {
                            Delete.deleteActor(connection);
                            valide = true;
                        } else if (choix == 5) {
                            restart = false;
                            break;
                        }
                    } else {
                        System.err.println("\nErreur : le nombre choisi ne correspond à aucun choix proposé.");
                    }
                } else {
                    System.err.println("\nErreur : vous n'avez pas tapé de nombre.");
                }
            }
        }

    }

    public static void choiceAddress(Connection connection) throws SQLException{
        boolean restart = true;

        while(restart){

            boolean valide = false;
            while(!valide) {
                System.out.println("\n1. Créer une nouvelle adresse\n2. Afficher les adresses\n3. Mettre à jour une adresse\n4. Supprimer une adresse\n5. Retour");
                System.out.println("Entrez votre choix :");
                Scanner choice = new Scanner(System.in);
                if (choice.hasNextInt()){
                    int choix = choice.nextInt();
                    if(choix <= 5 || choix >= 1){
                        if(choix == 1){
                            Add.addAddress(connection);
                            valide = true;
                        }else if(choix == 2){
                            Affiche.afficheAddress(connection);
                            valide = true;
                        }else if(choix == 3){
                            Update.updateAddress(connection);
                            valide = true;
                        }else if(choix == 4){
                            Delete.deleteAddress(connection);
                            valide = true;
                        }else if(choix == 5){
                            restart = false;
                            break;
                        }
                    }else {
                        System.err.println("\nErreur : le nombre choisi ne correspond à aucun choix proposé.");
                    }

                }else {
                    System.err.println("\nErreur : vous n'avez pas tapé de nombre.");
                }
            }
        }
    }

    public static void choiceCategory(Connection connection) throws SQLException{
        boolean restart = true;

        while(restart){

            boolean valide = false;
            while(!valide) {
                System.out.println("\n1. Créer une nouvelle catégorie\n2. Afficher les catégories\n3. Mettre à jour une catégorie\n4. Supprimer une catégorie\n5. Retour");
                System.out.println("Entrez votre choix :");
                Scanner choice = new Scanner(System.in);
                if (choice.hasNextInt()){
                    int choix = choice.nextInt();
                    if(choix <= 5 || choix >= 1){
                        if(choix == 1){
                            Add.addCategory(connection);
                            valide = true;
                        }else if(choix == 2){
                            Affiche.afficheCategory(connection);
                            valide = true;
                        }else if(choix == 3){
                            Update.updateCategory(connection);
                            valide = true;
                        }else if(choix == 4){
                            Delete.deleteCategory(connection);
                            valide = true;
                        }else if(choix == 5){
                            restart = false;
                            break;
                        }
                    }else {
                        System.err.println("\nErreur : le nombre choisi ne correspond à aucun choix proposé.");
                    }

                }else {
                    System.err.println("\nErreur : vous n'avez pas tapé de nombre.");
                }
            }
        }
    }

    public static void choiceCity(Connection connection) throws SQLException{
        boolean restart = true;

        while(restart){

            boolean valide = false;
            while(!valide) {
                System.out.println("\n1. Créer une nouvelle ville\n2. Afficher les villes\n3. Mettre à jour une ville\n4. Supprimer une ville\n5. Retour");
                System.out.println("Entrez votre choix :");
                Scanner choice = new Scanner(System.in);
                if (choice.hasNextInt()){
                    int choix = choice.nextInt();
                    if(choix <= 5 || choix >= 1){
                        if(choix == 1){
                            Add.addCity(connection);
                            valide = true;
                        }else if(choix == 2){
                            Affiche.afficheCity(connection);
                            valide = true;
                        }else if(choix == 3){
                            Update.updateCity(connection);
                            valide = true;
                        }else if(choix == 4){
                            Delete.deleteCity(connection);
                            valide = true;
                        }else if(choix == 5){
                            restart = false;
                            break;
                        }
                    }else {
                        System.err.println("\nErreur : le nombre choisi ne correspond à aucun choix proposé.");
                    }

                }else {
                    System.err.println("\nErreur : vous n'avez pas tapé de nombre.");
                }
            }
        }
    }

    public static void choiceCountry(Connection connection) throws SQLException{
        boolean restart = true;

        while(restart){

            boolean valide = false;
            while(!valide) {
                System.out.println("\n1. Créer un nouveau pays\n2. Afficher les pays\n3. Mettre à jour un pays\n4. Supprimer un pays\n5. Retour");
                System.out.println("Entrez votre choix :");
                Scanner choice = new Scanner(System.in);
                if (choice.hasNextInt()){
                    int choix = choice.nextInt();
                    if(choix <= 5 || choix >= 1){
                        if(choix == 1){
                            Add.addCountry(connection);
                            valide = true;
                        }else if(choix == 2){
                            Affiche.afficheCountry(connection);
                            valide = true;
                        }else if(choix == 3){
                            Update.updateCountry(connection);
                            valide = true;
                        }else if(choix == 4){
                            Delete.deleteCountry(connection);
                            valide = true;
                        }else if(choix == 5){
                            restart = false;
                            break;
                        }
                    }else {
                        System.err.println("\nErreur : le nombre choisi ne correspond à aucun choix proposé.");
                    }

                }else {
                    System.err.println("\nErreur : vous n'avez pas tapé de nombre.");
                }
            }
        }
    }

    public static void choiceCustomer(Connection connection) throws SQLException{
        boolean restart = true;

        while(restart){

            boolean valide = false;
            while(!valide) {
                System.out.println("\n1. Créer un nouveau client\n2. Afficher les clients\n3. Mettre à jour un client\n4. Supprimer un client\n5. Retour");
                System.out.println("Entrez votre choix :");
                Scanner choice = new Scanner(System.in);
                if (choice.hasNextInt()){
                    int choix = choice.nextInt();
                    if(choix <= 5 || choix >= 1){
                        if(choix == 1){
                            Add.addCustomer(connection);
                            valide = true;
                        }else if(choix == 2){
                            Affiche.afficheCustomer(connection);
                            valide = true;
                        }else if(choix == 3){
                            Update.updateCustomer(connection);
                            valide = true;
                        }else if(choix == 4){
                            Delete.deleteCustomer(connection);
                            valide = true;
                        }else if(choix == 5){
                            restart = false;
                            break;
                        }
                    }else {
                        System.err.println("\nErreur : le nombre choisi ne correspond à aucun choix proposé.");
                    }

                }else {
                    System.err.println("\nErreur : vous n'avez pas tapé de nombre.");
                }
            }
        }
    }

    public static void choiceFilm(Connection connection) throws SQLException{
        System.out.println("\n1. Créer un nouveau film\n2. Afficher les films\n3. Mettre à jour un film\n4. Supprimer un film\n5. Retour");
        System.out.println("Entrez votre choix :");
        Scanner choice = new Scanner(System.in);

        boolean valide = false;
        while(!valide) {
            if (choice.hasNextInt()){
                int choix = choice.nextInt();
                if(choix <= 5 || choix >= 1){
                    if(choix == 1){
                        Add.addFilm(connection);
                        System.out.println("\n1. Créer un nouveau film\n2. Afficher les films\n3. Mettre à jour un film\n4. Supprimer un film\n5. Retour");

                    }else if(choix == 2){
                        Affiche.afficheFilm(connection);
                        System.out.println("\n1. Créer un nouveau film\n2. Afficher les films\n3. Mettre à jour un film\n4. Supprimer un film\n5. Retour");

                    }else if(choix == 3){
                        Update.updateFilm(connection);
                        System.out.println("\n1. Créer un nouveau film\n2. Afficher les films\n3. Mettre à jour un film\n4. Supprimer un film\n5. Retour");

                    }else if(choix == 4){
                        Delete.removeFilm(connection);
                        System.out.println("\n1. Créer un nouveau film\n2. Afficher les films\n3. Mettre à jour un film\n4. Supprimer un film\n5. Retour");

                    }else if(choix == 5){
                        valide = true;
                    }
                }else {
                    System.err.println("Erreur : le nombre choisi ne correspond à aucun choix proposé.");
                }

            }else {
                System.err.println("Erreur : vous n'avez pas tapé de nombre.");
            }
        }
    }

    public static void choiceFilmActor(Connection connection) throws SQLException{
        System.out.println("\n1. Créer un nouvelle liaison entre les films et les acteurs\n2. Afficher les liaisons\n3. Mettre à jour une liaison\n4. Supprimer une liaison\n5. Retour");
        System.out.println("Entrez votre choix :");
        Scanner choice = new Scanner(System.in);

        boolean valide = false;
        while(!valide) {
            if (choice.hasNextInt()){
                int choix = choice.nextInt();
                if(choix <= 5 || choix >= 1){
                    if(choix == 1){
                        Add.addFilmActor(connection);
                        System.out.println("\n1. Créer un nouvelle liaison entre les films et les acteurs\n2. Afficher les liaisons\n3. Mettre à jour une liaison\n4. Supprimer une liaison\n5. Retour");

                    }else if(choix == 2){
                        Affiche.afficheFilmActor(connection);
                        System.out.println("\n1. Créer un nouvelle liaison entre les films et les acteurs\n2. Afficher les liaisons\n3. Mettre à jour une liaison\n4. Supprimer une liaison\n5. Retour");

                    }else if(choix == 3){
                        Update.updateFilmActor(connection);
                        System.out.println("\n1. Créer un nouvelle liaison entre les films et les acteurs\n2. Afficher les liaisons\n3. Mettre à jour une liaison\n4. Supprimer une liaison\n5. Retour");

                    }else if(choix == 4){
                        Delete.removeFilmActor(connection);
                        System.out.println("\n1. Créer un nouvelle liaison entre les films et les acteurs\n2. Afficher les liaisons\n3. Mettre à jour une liaison\n4. Supprimer une liaison\n5. Retour");

                    }else if(choix == 5){
                        System.out.println("Quitter");
                        valide = true;
                    }
                }else {
                    System.err.println("Erreur : le nombre choisi ne correspond à aucun choix proposé.");
                }

            }else {
                System.err.println("Erreur : vous n'avez pas tapé de nombre.");
            }
        }
    }

    public static void choiceFilmCate(Connection connection) throws SQLException{
        System.out.println("\n1. Créer un nouvelle liaison entre les films et les catégories\n2. Afficher les liaisons\n3. Mettre à jour une liaison\n4. Supprimer une liaison\n5. Retour");
        System.out.println("Entrez votre choix :");
        Scanner choice = new Scanner(System.in);

        boolean valide = false;
        while(!valide) {
            if (choice.hasNextInt()){
                int choix = choice.nextInt();
                if(choix <= 5 || choix >= 1){
                    if(choix == 1){
                        Add.addFilmCate(connection);
                        System.out.println("\n1. Créer un nouvelle liaison entre les films et les catégories\n2. Afficher les liaisons\n3. Mettre à jour une liaison\n4. Supprimer une liaison\n5. Retour");

                    }else if(choix == 2){
                        Affiche.afficheFilmCate(connection);
                        System.out.println("\n1. Créer un nouvelle liaison entre les films et les catégories\n2. Afficher les liaisons\n3. Mettre à jour une liaison\n4. Supprimer une liaison\n5. Retour");

                    }else if(choix == 3){
                        Update.updateFilmCate(connection);
                        System.out.println("\n1. Créer un nouvelle liaison entre les films et les catégories\n2. Afficher les liaisons\n3. Mettre à jour une liaison\n4. Supprimer une liaison\n5. Retour");

                    }else if(choix == 4){
                        Delete.removeFilmCate(connection);
                        System.out.println("\n1. Créer un nouvelle liaison entre les films et les catégories\n2. Afficher les liaisons\n3. Mettre à jour une liaison\n4. Supprimer une liaison\n5. Retour");

                    }else if(choix == 5){
                        System.out.println("Quitter");
                        valide = true;
                    }
                }else {
                    System.err.println("Erreur : le nombre choisi ne correspond à aucun choix proposé.");
                }

            }else {
                System.err.println("Erreur : vous n'avez pas tapé de nombre.");
            }
        }
    }

    public static void choiceFilmText(Connection connection) throws SQLException {
        System.out.println("\n1. Créer une nouvelle description du film\n2. Afficher les descriptions\n3. Mettre à jour une description\n4. Supprimer une description\n5. Retour");
        System.out.println("Entrez votre choix :");
        Scanner choice = new Scanner(System.in);

        boolean valide = false;
        while (!valide) {
            if (choice.hasNextInt()) {
                int choix = choice.nextInt();
                if (choix <= 5 || choix >= 1) {
                    if (choix == 1) {
                        Add.addFilmText(connection);
                        System.out.println("\n1. Créer une nouvelle description du film\n2. Afficher les descriptions\n3. Mettre à jour une description\n4. Supprimer une description\n5. Retour");

                    } else if (choix == 2) {
                        Affiche.afficheFilmText(connection);
                        System.out.println("\n1. Créer une nouvelle description du film\n2. Afficher les descriptions\n3. Mettre à jour une description\n4. Supprimer une description\n5. Retour");

                    } else if (choix == 3) {
                        Update.updateFilmText(connection);
                        System.out.println("\n1. Créer une nouvelle description du film\n2. Afficher les descriptions\n3. Mettre à jour une description\n4. Supprimer une description\n5. Retour");

                    } else if (choix == 4) {
                        Delete.removeFilmText(connection);
                        System.out.println("\n1. Créer une nouvelle description du film\n2. Afficher les descriptions\n3. Mettre à jour une description\n4. Supprimer une description\n5. Retour");

                    } else if (choix == 5) {
                        System.out.println("Quitter");
                        valide = true;
                    }
                } else {
                    System.err.println("Erreur : le nombre choisi ne correspond à aucun choix proposé.");
                }

            } else {
                System.err.println("Erreur : vous n'avez pas tapé de nombre.");
            }
        }
    }

    public static void choiceInventory(Connection connection) throws SQLException{
        System.out.println("\n1. Créer un nouvel inventaire\n2. Afficher les inventaires\n3. Mettre à jour un inventaire\n4. Supprimer un inventaire\n5. Retour");
        System.out.println("Entrez votre choix :");
        Scanner choice = new Scanner(System.in);

        boolean valide = false;
        while(!valide) {
            if (choice.hasNextInt()){
                int choix = choice.nextInt();
                if(choix <= 5 || choix >= 1){
                    if(choix == 1){
                        Add.addInventory(connection);
                        System.out.println("\n1. Créer un nouvel inventaire\n2. Afficher les inventaires\n3. Mettre à jour un inventaire\n4. Supprimer un inventaire\n5. Retour");

                    }else if(choix == 2){
                        Affiche.afficheInventory(connection);
                        System.out.println("\n1. Créer un nouvel inventaire\n2. Afficher les inventaires\n3. Mettre à jour un inventaire\n4. Supprimer un inventaire\n5. Retour");

                    }else if(choix == 3){
                        Update.updateInventory(connection);
                        System.out.println("\n1. Créer un nouvel inventaire\n2. Afficher les inventaires\n3. Mettre à jour un inventaire\n4. Supprimer un inventaire\n5. Retour");

                    }else if(choix == 4){
                        Delete.removeInventory(connection);
                        System.out.println("\n1. Créer un nouvel inventaire\n2. Afficher les inventaires\n3. Mettre à jour un inventaire\n4. Supprimer un inventaire\n5. Retour");

                    }else if(choix == 5){
                        System.out.println("Quitter");
                        valide = true;
                    }
                }else {
                    System.err.println("Erreur : le nombre choisi ne correspond à aucun choix proposé.");
                }

            }else {
                System.err.println("Erreur : vous n'avez pas tapé de nombre.");
            }
        }
    }

    public static void choiceLanguage(Connection connection) throws SQLException{
        System.out.println("\n1. Créer un nouveau language\n2. Afficher les languages\n3. Mettre à jour un language\n4. Supprimer un language\n5. Retour");
        System.out.println("Entrez votre choix :");
        Scanner choice = new Scanner(System.in);

        boolean valide = false;
        while(!valide) {
            if (choice.hasNextInt()){
                int choix = choice.nextInt();
                if(choix <= 5 || choix >= 1){
                    if(choix == 1){
                        Add.addLanguage(connection);
                        System.out.println("\n1. Créer un nouveau language\n2. Afficher les languages\n3. Mettre à jour un language\n4. Supprimer un language\n5. Retour");

                    }else if(choix == 2){
                        Affiche.afficheLanguage(connection);
                        System.out.println("\n1. Créer un nouveau language\n2. Afficher les languages\n3. Mettre à jour un language\n4. Supprimer un language\n5. Retour");

                    }else if(choix == 3){
                        Update.updateLanguage(connection);
                        System.out.println("\n1. Créer un nouveau language\n2. Afficher les languages\n3. Mettre à jour un language\n4. Supprimer un language\n5. Retour");

                    }else if(choix == 4){
                        Delete.removeLanguage(connection);
                        System.out.println("\n1. Créer un nouveau language\n2. Afficher les languages\n3. Mettre à jour un language\n4. Supprimer un language\n5. Retour");

                    }else if(choix == 5){
                        System.out.println("Quitter");
                        valide = true;
                    }
                }else {
                    System.err.println("Erreur : le nombre choisi ne correspond à aucun choix proposé.");
                }

            }else {
                System.err.println("Erreur : vous n'avez pas tapé de nombre.");
            }
        }
    }

    public static void choicePayement(Connection connection) throws SQLException{
        System.out.println("\n1. Créer un nouveau paiement\n2. Afficher les paiements\n3. Mettre à jour un paiement\n4. Supprimer un paiement\n5. Retour");
        System.out.println("Entrez votre choix :");
        Scanner choice = new Scanner(System.in);

        boolean valide = false;
        while(!valide) {
            if (choice.hasNextInt()){
                int choix = choice.nextInt();
                if(choix <= 5 || choix >= 1){
                    if(choix == 1){
                        Add.addPayment(connection);
                        System.out.println("\n1. Créer un nouveau paiement\n2. Afficher les paiements\n3. Mettre à jour un paiement\n4. Supprimer un paiement\n5. Retour");

                    }else if(choix == 2){
                        Affiche.affichePayment(connection);
                        System.out.println("\n1. Créer un nouveau paiement\n2. Afficher les paiements\n3. Mettre à jour un paiement\n4. Supprimer un paiement\n5. Retour");

                    }else if(choix == 3){
                        Update.updatePayment(connection);
                        System.out.println("\n1. Créer un nouveau paiement\n2. Afficher les paiements\n3. Mettre à jour un paiement\n4. Supprimer un paiement\n5. Retour");

                    }else if(choix == 4){
                        Delete.deletePayment(connection);
                        System.out.println("\n1. Créer un nouveau paiement\n2. Afficher les paiements\n3. Mettre à jour un paiement\n4. Supprimer un paiement\n5. Retour");

                    }else if(choix == 5){
                        System.out.println("Quitter");
                        valide = true;
                    }
                }else {
                    System.err.println("Erreur : le nombre choisi ne correspond à aucun choix proposé.");
                }

            }else {
                System.err.println("Erreur : vous n'avez pas tapé de nombre.");
            }
        }
    }

    public static void choiceRental(Connection connection) throws SQLException{
        System.out.println("\n1. Créer un nouvelle location\n2. Afficher les locations\n3. Mettre à jour une location\n4. Supprimer une location\n5. Retour");
        System.out.println("Entrez votre choix :");
        Scanner choice = new Scanner(System.in);

        boolean valide = false;
        while(!valide) {
            if (choice.hasNextInt()){
                int choix = choice.nextInt();
                if(choix <= 5 || choix >= 1){
                    if(choix == 1){
                        Add.addRental(connection);
                        System.out.println("\n1. Créer un nouvelle location\n2. Afficher les locations\n3. Mettre à jour une location\n4. Supprimer une location\n5. Retour");

                    }else if(choix == 2){
                        Affiche.afficheRental(connection);
                        System.out.println("\n1. Créer un nouvelle location\n2. Afficher les locations\n3. Mettre à jour une location\n4. Supprimer une location\n5. Retour");

                    }else if(choix == 3){
                        Update.updateRental(connection);
                        System.out.println("\n1. Créer un nouvelle location\n2. Afficher les locations\n3. Mettre à jour une location\n4. Supprimer une location\n5. Retour");

                    }else if(choix == 4){
                        Delete.deleteRental(connection);
                        System.out.println("\n1. Créer un nouvelle location\n2. Afficher les locations\n3. Mettre à jour une location\n4. Supprimer une location\n5. Retour");

                    }else if(choix == 5){
                        System.out.println("Quitter");
                        valide = true;
                    }
                }else {
                    System.err.println("Erreur : le nombre choisi ne correspond à aucun choix proposé.");
                }

            }else {
                System.err.println("Erreur : vous n'avez pas tapé de nombre.");
            }
        }
    }

    public static void choiceStaff(Connection connection) throws SQLException{
        System.out.println("\n1. Créer un nouveau membre du staff\n2. Afficher les membres\n3. Mettre à jour un membre\n4. Supprimer un membre\n5. Retour");
        System.out.println("Entrez votre choix :");
        Scanner choice = new Scanner(System.in);

        boolean valide = false;
        while(!valide) {
            if (choice.hasNextInt()){
                int choix = choice.nextInt();
                if(choix <= 5 || choix >= 1){
                    if(choix == 1){
                        Add.addStaff(connection);
                        System.out.println("\n1. Créer un nouveau membre du staff\n2. Afficher les membres\n3. Mettre à jour un membre\n4. Supprimer un membre\n5. Retour");

                    }else if(choix == 2){
                        Affiche.afficheStaff(connection);
                        System.out.println("\n1. Créer un nouveau membre du staff\n2. Afficher les membres\n3. Mettre à jour un membre\n4. Supprimer un membre\n5. Retour");

                    }else if(choix == 3){
                        Update.updateStaff(connection);
                        System.out.println("\n1. Créer un nouveau membre du staff\n2. Afficher les membres\n3. Mettre à jour un membre\n4. Supprimer un membre\n5. Retour");

                    }else if(choix == 4){
                        Delete.deleteStaff(connection);
                        System.out.println("\n1. Créer un nouveau membre du staff\n2. Afficher les membres\n3. Mettre à jour un membre\n4. Supprimer un membre\n5. Retour");

                    }else if(choix == 5){
                        System.out.println("Quitter");
                        valide = true;
                    }
                }else {
                    System.err.println("Erreur : le nombre choisi ne correspond à aucun choix proposé.");
                }

            }else {
                System.err.println("Erreur : vous n'avez pas tapé de nombre.");
            }
        }
    }

    public static void choiceStore(Connection connection) throws SQLException{
        System.out.println("\n1. Créer un nouveau magasin\n2. Afficher les magasins\n3. Mettre à jour un magasin\n4. Supprimer un magasin\n5. Retour");
        System.out.println("Entrez votre choix :");
        Scanner choice = new Scanner(System.in);

        boolean valide = false;
        while(!valide) {
            if (choice.hasNextInt()){
                int choix = choice.nextInt();
                if(choix <= 5 || choix >= 1){
                    if(choix == 1){
                        Add.addStore(connection);
                        System.out.println("\n1. Créer un nouveau magasin\n2. Afficher les magasins\n3. Mettre à jour un magasin\n4. Supprimer un magasin\n5. Retour");

                    }else if(choix == 2){
                        Affiche.afficheStore(connection);
                        System.out.println("\n1. Créer un nouveau magasin\n2. Afficher les magasins\n3. Mettre à jour un magasin\n4. Supprimer un magasin\n5. Retour");

                    }else if(choix == 3){
                        Update.updateStore(connection);
                        System.out.println("\n1. Créer un nouveau magasin\n2. Afficher les magasins\n3. Mettre à jour un magasin\n4. Supprimer un magasin\n5. Retour");

                    }else if(choix == 4){
                        Delete.deleteStore(connection);
                        System.out.println("\n1. Créer un nouveau magasin\n2. Afficher les magasins\n3. Mettre à jour un magasin\n4. Supprimer un magasin\n5. Retour");

                    }else if(choix == 5){
                        System.out.println("Quitter");
                        valide = true;
                    }
                }else {
                    System.err.println("Erreur : le nombre choisi ne correspond à aucun choix proposé.");
                }

            }else {
                System.err.println("Erreur : vous n'avez pas tapé de nombre.");
            }
        }
    }

}