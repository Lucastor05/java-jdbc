package com.codingf;

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

        System.out.println("Le driver est chargé !!!");

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
                        if (choix == 6) {
                            choiceCountry(connection);
                            valide = true;
                        }else if (choix == 17) {
                            choicePayment(connection);
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


    public static void choiceCountry(Connection connection) throws SQLException{
        System.out.println("\n1. Créer un nouveau pays\n2. Afficher les pays\n3. Mettre à jour un pays\n4. Supprimer un pays\n5. Retour");
        System.out.println("Entrez votre choix :");
        Scanner choice = new Scanner(System.in);

        boolean valide = false;
        while(!valide) {
            if (choice.hasNextInt()){
                int choix = choice.nextInt();
                if(choix <= 5 || choix >= 1){
                    if(choix == 1){

                    }else if(choix == 2){
                        afficheCountry(connection);
                    }else if(choix == 3){

                    }else if(choix == 4){

                    }else if(choix == 5){
                        valide = true;
                    }
                }else {
                    System.err.println("Erreur: Le nombre choisi ne correspond à aucun choix proposé !!!");
                }

            }else {
                System.err.println("Erreur: Ceci n'est pas un nombre !!!");
            }
        }
    }
    public static void choicePayment(Connection connection) throws SQLException{
        System.out.println("\n1. Créer un nouveau paiement\n2. Afficher les paiements\n3. Mettre à jour un paiement\n4. Supprimer un paiement\n5. Retour");
        System.out.println("Entrez votre choix :");
        Scanner choice = new Scanner(System.in);

        boolean valide = false;
        while(!valide) {
            if (choice.hasNextInt()){
                int choix = choice.nextInt();
                if(choix <= 5 || choix >= 1){
                    if(choix == 1){

                    }else if(choix == 2){
                        affichePayment(connection);
                    }else if(choix == 3){

                    }else if(choix == 4){

                    }else if(choix == 5){
                        valide = true;
                    }
                }else {
                    System.err.println("Erreur: Le nombre choisi ne correspond à aucun choix proposé !!!");
                }

            }else {
                System.err.println("Erreur: Ceci n'est pas un nombre !!!");
            }
        }
    }
    public static void afficheCountry(Connection connection) throws SQLException {
        Statement stmt= connection.createStatement();

        ResultSet pays= stmt.executeQuery("SELECT country FROM country");
        ResultSetMetaData resultMeta = pays.getMetaData();

        while(pays.next()){
            for(int i = 1; i <= resultMeta.getColumnCount(); i++) {
                System.out.println("pays: "+ pays.getObject(i).toString());
            }
        }
    }
    public static void affichePayment(Connection connection) throws SQLException {
        Statement stmt= connection.createStatement();

        ResultSet argent= stmt.executeQuery("SELECT payment_id, amount, payment_date, last_update FROM payment");
        ResultSetMetaData resultMeta = argent.getMetaData();

        while (argent.next()){
            for (int i = 1; i <= resultMeta.getColumnCount(); i++) {
                System.out.println("Id : "+ argent.getObject(i).toString() + ", amount : "+ argent.getObject(i).toString() +", premier paiement : "+ argent.getObject(i).toString() + ", dernier paiement effectué : "+ argent.getObject(i).toString());
            }
        }
    }
    public static void afficheCity(Connection connection) throws SQLException {
        Statement stmt= connection.createStatement();

        ResultSet city= stmt.executeQuery("SELECT city FROM city");
        ResultSetMetaData resultMeta = city.getMetaData();

        while(city.next()){
            for(int i = 1; i <= resultMeta.getColumnCount(); i++) {
                System.out.println("Ville: "+ city.getObject(i).toString());
            }
        }
    }
}