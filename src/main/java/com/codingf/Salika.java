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
                        if (choix == 6) {
                            choiceCountry(connection);
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
                                            System.out.println("\nErreur lors de l'insertion de la valeur dans la table country");
                                        }

                                        newCountryValide = true;
                                        valide = true;
                                    }
                                }else{
                                    System.err.println("\nErreur: Le nom ne peut etre un numéro");
                                }
                            }
                        }else if(choix == 2){
                            afficheCountry(connection);
                            choiceCountry(connection);
                        }else if(choix == 3){

                            boolean newCountryValide = false;
                            while(!newCountryValide) {
                                afficheCountry(connection);

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
                                            query = "UPDATE country SET country = ? WHERE country_id = ?";
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
                        }else if(choix == 4){
                            boolean newCountryValide = false;
                            while(!newCountryValide) {
                                afficheCountry(connection);
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
                                            System.out.println("La valeur a été supprimée avec succès dans la table country");
                                        } else {
                                            System.out.println("\nErreur lors de la suppression de la valeur dans la table country");
                                        }

                                        newCountryValide = true;
                                    }
                                }else{
                                    System.err.println("\nErreur: Le nom ne peut etre un numéro");
                                }
                            }
                            choiceCountry(connection);
                        }else if(choix == 5){
                            valide = true;
                            restart = false;
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
    public static void afficheCountry(Connection connection) throws SQLException {
        Statement stmt= connection.createStatement();

        ResultSet pays= stmt.executeQuery("SELECT country_id, country FROM country");
        ResultSetMetaData resultMeta = pays.getMetaData();

        while(pays.next()){
            for(int i = 1; i <= resultMeta.getColumnCount(); i++) {
                System.out.print(pays.getObject(i).toString()+". ");
            }
            System.out.println();
        }
    }
    public static void afficheCity(Connection connection) throws SQLException {
        Statement stmt= connection.createStatement();

        ResultSet city= stmt.executeQuery("SELECT city FROM city");
        ResultSetMetaData resultMeta = city.getMetaData();

        while(city.next()){
            for(int i = 1; i <= resultMeta.getColumnCount(); i++) {
                System.out.print(city.getObject(i).toString()+". ");
            }
            System.out.println();
        }
    }
}