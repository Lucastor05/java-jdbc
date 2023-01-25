package com.codingf;

import java.sql.*;
import java.util.ResourceBundle;
import java.util.Scanner;

public class Salika {
    public static void main(String[] args) throws SQLException {

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e){
            System.err.println("Un problème est survenu sur le chargement sur driver");
            System.exit(-1);
        }

        System.out.println("Le driver est chargé !");

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
            System.err.println("Erreur de connexion.");
        } else {
            System.err.println("Connexion établie");

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
                        System.err.println("Erreur: Le nombre choisi ne correspond à aucun choix proposé.");
                    }
                } else {
                    System.err.println("Erreur: Ceci n'est pas un nombre.");
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
                    System.err.println("Erreur: Le nombre choisi ne correspond à aucun choix proposé.");
                }

            }else {
                System.err.println("Erreur: Ceci n'est pas un nombre.");
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
    public static void affichePayment(Connection connection) throws SQLException {
        Statement stmt= connection.createStatement();

        ResultSet argent= stmt.executeQuery("SELECT * FROM payment");
        System.out.println();

        while (argent.next()) {
            System.out.println("payment_id: " + argent.getString(1));
            System.out.println("amount: " + argent.getString(5));
            System.out.println("payment_date: " + argent.getString(6));
            System.out.println("last_update: " + argent.getString(7));
            System.out.println("========================================================================================================");
        }
    }
    public static void afficheRental(Connection connection) throws SQLException {
        Statement stmt= connection.createStatement();

        ResultSet rent= stmt.executeQuery("SELECT * FROM rental");
        System.out.println();

        while (rent.next()) {
            System.out.println("rental_id: " + rent.getString(1));
            System.out.println("rental_date: " + rent.getString(2));
            System.out.println("return_date: " + rent.getString(5));
            System.out.println("last_update: " + rent.getString(7));
            System.out.println("========================================================================================================");
        }
    }
    public static void afficheSalesCategory(Connection connection) throws SQLException {
        Statement stmt= connection.createStatement();

        ResultSet salesCat= stmt.executeQuery("SELECT * FROM sales_by_film_category");
        System.out.println();

        while (salesCat.next()) {
            System.out.println("rental_id: " + salesCat.getString(1));
            System.out.println("rental_date: " + salesCat.getString(2));
            System.out.println("========================================================================================================");
        }
    }
    public static void afficheSalesStore(Connection connection) throws SQLException {
        Statement stmt= connection.createStatement();

        ResultSet salesStore= stmt.executeQuery("SELECT * FROM sales_by_store");
        System.out.println();

        while (salesStore.next()) {
            System.out.println("store: " + salesStore.getString(1));
            System.out.println("manager: " + salesStore.getString(2));
            System.out.println("total_sales: " + salesStore.getString(3));
            System.out.println("========================================================================================================");
        }
    }
    public static void afficheStaff(Connection connection) throws SQLException {
        Statement stmt= connection.createStatement();

        ResultSet staff= stmt.executeQuery("SELECT * FROM staff");
        System.out.println();

        while (staff.next()) {
            System.out.println("first_name: " + staff.getString(2));
            System.out.println("last_name: " + staff.getString(3));
            System.out.println("email: " + staff.getString(6));
            System.out.println("active: " + staff.getString(8));
            System.out.println("username: " + staff.getString(9));
            System.out.println("password: " + staff.getString(10));
            System.out.println("last_update: " + staff.getString(11));
            System.out.println("========================================================================================================");
        }
    }
    public static void afficheStaffList(Connection connection) throws SQLException {
        Statement stmt= connection.createStatement();

        ResultSet staffList= stmt.executeQuery("SELECT * FROM staff_list");
        System.out.println();

        while (staffList.next()) {
            System.out.println("name: " + staffList.getString(2));
            System.out.println("adress: " + staffList.getString(3));
            System.out.println("zip code: " + staffList.getString(4));
            System.out.println("phone: " + staffList.getString(5));
            System.out.println("city: " + staffList.getString(6));
            System.out.println("country: " + staffList.getString(7));
            System.out.println("SID: " + staffList.getString(8));
            System.out.println("========================================================================================================");
        }
    }
    public static void afficheStore(Connection connection) throws SQLException {
        Statement stmt= connection.createStatement();

        ResultSet store= stmt.executeQuery("SELECT * FROM store");
        System.out.println();

        while (store.next()) {
            System.out.println("store_id: " + store.getString(1));
            System.out.println("last_update: " + store.getString(4));
            System.out.println("========================================================================================================");
        }
    }
}