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
                        if(choix == 1){
                            choiceActor(connection);
                        }else if(choix == 2){
                            choiceActorInfo(connection);
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
                            choiceCustomerList(connection);
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






    /*ACTOR*/

    public static void choiceActor(Connection connection) throws SQLException{
        boolean restart = true;

        while(restart) {
            System.out.println("\n1. Ajouter un nouvel acteur\n2. Afficher les acteurs\n3. Mettre à jour les données d'un acteur\n4. Supprimer un acteur\n5. Retour");
            System.out.println("Entrez votre choix :");
            Scanner choice = new Scanner(System.in);
            int choix = choice.nextInt();
            if (choix == 1) {

            }else if(choix == 2) {
                afficheActor(connection);
            }else if(choix == 3) {
            }else if(choix == 4) {
                deleteActor(connection);
            }else if(choix == 5) {
                restart = false;
            }
        }

    }
    public static void afficheActor(Connection connection) throws SQLException {
        Statement stmt= connection.createStatement();

        ResultSet city= stmt.executeQuery("SELECT actor_id, first_name, last_name FROM actor");
        ResultSetMetaData resultMeta = city.getMetaData();

        while(city.next()){
            for(int i = 1; i <= resultMeta.getColumnCount(); i++) {
                if(i == 1){
                    System.out.print(city.getObject(i).toString()+". ");
                }else{
                    System.out.print(city.getObject(i).toString()+" ");
                }

            }
            System.out.println();
        }
    }
    public static void deleteActor(Connection connection) throws SQLException {
        boolean newCountryValide = false;
        while(!newCountryValide) {
            afficheCountry(connection);
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



    /*ACTOR_INFO*/
    public static void choiceActorInfo(Connection connection) throws SQLException{
        boolean restart = true;

        while(restart){

            boolean valide = false;
            while(!valide) {
                System.out.println("\n1. Créer une nouvelle infos d'acteur\n2. Afficher les infos\n3. Mettre à jour une infos\n4. Supprimer une infos\n5. Retour");
                System.out.println("Entrez votre choix :");
                Scanner choice = new Scanner(System.in);
                if (choice.hasNextInt()){
                    int choix = choice.nextInt();
                    if(choix <= 5 || choix >= 1){
                        if(choix == 1){

                            valide = true;
                        }else if(choix == 2){
                            afficheActorInfo(connection);
                            valide = true;
                        }else if(choix == 3){

                            valide = true;
                        }else if(choix == 4){
                            deleteActorInfo(connection);
                            valide = true;
                        }else if(choix == 5){
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
    public static void deleteActorInfo(Connection connection) throws SQLException {
        boolean newCountryValide = false;
        while(!newCountryValide) {
            afficheCountry(connection);
            System.out.print("Entrez l'id de l'info que vous souhaitez supprimer : ");
            Scanner newCountry = new Scanner(System.in);

            if (newCountry.hasNextInt()) {
                String name = newCountry.next();
                String query = "SELECT * FROM actor_info WHERE actor_id = ?";
                PreparedStatement statement = connection.prepareStatement(query);
                statement.setString(1, name);
                ResultSet resultSet = statement.executeQuery();

                if (!resultSet.next()) {
                    System.err.println("\nErreur: Cette info n'existe pas !!!");
                } else {


                    query = "DELETE FROM actor_info WHERE actor_id = ?";
                    statement = connection.prepareStatement(query);
                    statement.setString(1, name);

                    int result = statement.executeUpdate();

                    if (result == 1) {
                        System.out.println("\nLa valeur a été supprimée avec succès dans la table actor_info");
                    } else {
                        System.out.println("\nErreur lors de la suppression de la valeur dans la table actor_info");
                    }

                    newCountryValide = true;
                }
            }else{
                System.err.println("\nErreur: Ceci n'est pas un nombre");
            }
        }
    }




    /*ADRESS*/
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

                            valide = true;
                        }else if(choix == 2){
                            afficheAddress(connection);
                            valide = true;
                        }else if(choix == 3){

                            valide = true;
                        }else if(choix == 4){
                            deleteAddress(connection);
                            valide = true;
                        }else if(choix == 5){
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
    public static void deleteAddress(Connection connection) throws SQLException {
        boolean newCountryValide = false;
        while(!newCountryValide) {
            afficheCountry(connection);
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




    /*CATEGORY*/
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

                            valide = true;
                        }else if(choix == 2){
                            afficheCategory(connection);
                            valide = true;
                        }else if(choix == 3){

                            valide = true;
                        }else if(choix == 4){
                            deleteCategory(connection);
                            valide = true;
                        }else if(choix == 5){
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
    public static void deleteCategory(Connection connection) throws SQLException {
        boolean newCountryValide = false;
        while(!newCountryValide) {
            afficheCountry(connection);
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


    /*CITY*/
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

                            valide = true;
                        }else if(choix == 2){
                            afficheCity(connection);
                            valide = true;
                        }else if(choix == 3){

                            valide = true;
                        }else if(choix == 4){
                            deleteCity(connection);
                            valide = true;
                        }else if(choix == 5){
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
    public static void deleteCity(Connection connection) throws SQLException {
        boolean newCountryValide = false;
        while(!newCountryValide) {
            afficheCountry(connection);
            System.out.print("Entrez l'id de la ville que vous souhaitez supprimer : ");
            Scanner newCountry = new Scanner(System.in);

            if (newCountry.hasNextInt()) {
                String name = newCountry.next();
                String query = "SELECT * FROM city WHERE city_id = ?";
                PreparedStatement statement = connection.prepareStatement(query);
                statement.setString(1, name);
                ResultSet resultSet = statement.executeQuery();

                if (!resultSet.next()) {
                    System.err.println("\nErreur: Cette ville n'existe pas !!!");
                } else {


                    query = "DELETE FROM city WHERE city_id = ?";
                    statement = connection.prepareStatement(query);
                    statement.setString(1, name);

                    int result = statement.executeUpdate();

                    if (result == 1) {
                        System.out.println("\nLa valeur a été supprimée avec succès dans la table city");
                    } else {
                        System.out.println("\nErreur lors de la suppression de la valeur dans la table city");
                    }

                    newCountryValide = true;
                }
            }else{
                System.err.println("\nErreur: Ceci n'est pas un nombre");
            }
        }
    }



    /*COUNTRY*/
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
                            addCountry(connection);
                            valide = true;
                        }else if(choix == 2){
                            afficheCountry(connection);
                            valide = true;
                        }else if(choix == 3){
                            updateCountry(connection);
                            valide = true;
                        }else if(choix == 4){
                            deleteCountry(connection);
                            valide = true;
                        }else if(choix == 5){
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
    public static void deleteCountry(Connection connection) throws SQLException {
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
    public static void updateCountry(Connection connection) throws SQLException {
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


    /*CUSTOMER*/

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

                            valide = true;
                        }else if(choix == 2){
                            afficheCustomer(connection);
                            valide = true;
                        }else if(choix == 3){

                            valide = true;
                        }else if(choix == 4){
                            deleteCustomer(connection);
                            valide = true;
                        }else if(choix == 5){
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
    public static void deleteCustomer(Connection connection) throws SQLException {
        boolean newCountryValide = false;
        while(!newCountryValide) {
            afficheCountry(connection);
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


    /*CUSTOMER_LIST*/
    public static void choiceCustomerList(Connection connection) throws SQLException{
        boolean restart = true;

        while(restart){

            boolean valide = false;
            while(!valide) {
                System.out.println("\n1. Créer un element de la liste\n2. Afficher la liste de client\n3. Mettre à jour la liste des clients\n4. Supprimer la liste des clients\n5. Retour");
                System.out.println("Entrez votre choix :");
                Scanner choice = new Scanner(System.in);
                if (choice.hasNextInt()){
                    int choix = choice.nextInt();
                    if(choix <= 5 || choix >= 1){
                        if(choix == 1){

                            valide = true;
                        }else if(choix == 2){
                            afficheCity(connection);
                            valide = true;
                        }else if(choix == 3){

                            valide = true;
                        }else if(choix == 4){
                            deleteCustomerList(connection);
                            valide = true;
                        }else if(choix == 5){
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
    public static void deleteCustomerList(Connection connection) throws SQLException {
        boolean newCountryValide = false;
        while(!newCountryValide) {
            afficheCountry(connection);
            System.out.print("Entrez l'id dans la liste de client que vous souhaitez supprimer : ");
            Scanner newCountry = new Scanner(System.in);

            if (newCountry.hasNextInt()) {
                String name = newCountry.next();
                String query = "SELECT * FROM customer_list WHERE ID = ?";
                PreparedStatement statement = connection.prepareStatement(query);
                statement.setString(1, name);
                ResultSet resultSet = statement.executeQuery();

                if (!resultSet.next()) {
                    System.err.println("\nErreur: Cette data n'existe pas !!!");
                } else {


                    query = "DELETE FROM customer_list WHERE ID = ?";
                    statement = connection.prepareStatement(query);
                    statement.setString(1, name);

                    int result = statement.executeUpdate();

                    if (result == 1) {
                        System.out.println("\nLa valeur a été supprimée avec succès dans la table customer_list");
                    } else {
                        System.out.println("\nErreur lors de la suppression de la valeur dans la table customer_list");
                    }

                    newCountryValide = true;
                }
            }else{
                System.err.println("\nErreur: Ceci n'est pas un nombre");
            }
        }
    }



}