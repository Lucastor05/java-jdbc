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
            System.err.println("probleme chargement sur driver");
            System.exit(-1);
        }

        System.out.println("Le driver est chargÃ© !!!");

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
            System.err.println("erreur de connexion !!!!");
        } else {
            System.err.println("connexion etablie");

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

                System.out.println("\n\n1. actor\n2. actor_info\n3. address\n4. category\n5. city\n6. country\n7. customer\n8. customer_list\n9. film\n10. film_actor\n11. film_category\n12. film_list\n13. film_text\n14. inventory\n15. language\n16. nicer_but_slower_film_list\n17. payment\n18. rental\n19. sales_by_film_category\n20. sales_by_store\n21. staff\n22. staff_list\n23. store\n24. Exit");
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
                        }else if(choix == 24){
                            System.exit(-1);
                        }
                    } else {
                        System.err.println("Erreur: Le nombre choisi ne correspond à aucun choix proposé !!!");
                    }
                } else {
                    System.err.println("Erreur: Ceci n'est pas un nombre !!!");
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

                            valide = true;
                        } else if (choix == 4) {
                            Delete.deleteActor(connection);
                            valide = true;
                        } else if (choix == 5) {
                            restart = false;
                            break;
                        }
                    } else {
                        System.err.println("\nErreur: Le nombre choisi ne correspond à aucun choix proposé !!!");
                    }
                } else {
                    System.err.println("\nErreur: Ceci n'est pas un nombre !!!");
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

                            valide = true;
                        }else if(choix == 4){
                            Delete.deleteAddress(connection);
                            valide = true;
                        }else if(choix == 5){
                            restart = false;
                            break;
                        }
                    }else {
                        System.err.println("\nErreur: Le nombre choisi ne correspond à aucun choix proposé !!!");
                    }

                }else {
                    System.err.println("\nErreur: Ceci n'est pas un nombre !!!");
                }
            }
        }
    }

    public static void choiceCategory(Connection connection) throws SQLException{
        boolean restart = true;

        while(restart){

            boolean valide = false;
            while(!valide) {
                System.out.println("\n1. Créer une nouvelle categorie\n2. Afficher les categories\n3. Mettre à jour une categorie\n4. Supprimer une categorie\n5. Retour");
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

                            valide = true;
                        }else if(choix == 4){
                            Delete.deleteCategory(connection);
                            valide = true;
                        }else if(choix == 5){
                            restart = false;
                            break;
                        }
                    }else {
                        System.err.println("\nErreur: Le nombre choisi ne correspond à aucun choix proposé !!!");
                    }

                }else {
                    System.err.println("\nErreur: Ceci n'est pas un nombre !!!");
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

                            valide = true;
                        }else if(choix == 4){
                            Delete.deleteCity(connection);
                            valide = true;
                        }else if(choix == 5){
                            restart = false;
                            break;
                        }
                    }else {
                        System.err.println("\nErreur: Le nombre choisi ne correspond à aucun choix proposé !!!");
                    }

                }else {
                    System.err.println("\nErreur: Ceci n'est pas un nombre !!!");
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
                        System.err.println("\nErreur: Le nombre choisi ne correspond à aucun choix proposé !!!");
                    }

                }else {
                    System.err.println("\nErreur: Ceci n'est pas un nombre !!!");
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

                            valide = true;
                        }else if(choix == 4){
                            Delete.deleteCustomer(connection);
                            valide = true;
                        }else if(choix == 5){
                            restart = false;
                            break;
                        }
                    }else {
                        System.err.println("\nErreur: Le nombre choisi ne correspond à aucun choix proposé !!!");
                    }

                }else {
                    System.err.println("\nErreur: Ceci n'est pas un nombre !!!");
                }
            }
        }
    }




}