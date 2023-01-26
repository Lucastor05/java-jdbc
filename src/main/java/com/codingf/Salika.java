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

                        valide = true;
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

    /*Paiements*/
    public static void affichePayment(Connection connection) throws SQLException {
        Statement stmt= connection.createStatement();

        ResultSet argent= stmt.executeQuery("SELECT * FROM payment");
        System.out.println();

        while (argent.next()) {
            System.out.println("payment_id: " + argent.getString(1));
            System.out.println("customer_id: " + argent.getString(2));
            System.out.println("staff_id: " + argent.getString(3));
            System.out.println("rental_id: " + argent.getString(4));
            System.out.println("amount: " + argent.getString(5));
            System.out.println("payment_date: " + argent.getString(6));
            System.out.println("last_update: " + argent.getString(7));
            System.out.println("========================================================================================================");
        }
    }

    public static void addPayment(Connection connection) throws SQLException {
        affichePayment(connection);
        // utiliser un Scanner pour lire les entrées de l'utilisateur
        Scanner scanner = new Scanner(System.in);

        int customerId = 0;
        int staffId = 0;
        int rentalId = 0;
        float amount = 0;

        boolean isCustomerIdValid = false;
        boolean isStaffIdValid = false;
        boolean isRentalIdValid = false;
        boolean isAmountValid = false;

        while (!isCustomerIdValid) {
            System.out.print("Entrez l'id du client : ");
            Scanner cust = new Scanner(System.in);
            // vérifier si le nom est un nombre
            if (cust.hasNextInt()){
                customerId = cust.nextInt();
                isCustomerIdValid = true;
            } else {
                System.out.println("Vous ne pouvez entrez qu'un nombre entier pour l'id du client.");
            }
        }

        while (!isStaffIdValid) {
            System.out.print("Entrez l'id du staff s'occupant du client : ");
            Scanner staff = new Scanner(System.in);
            // vérifier si le nom est un nombre
            if (staff.hasNextInt()){
                staffId = staff.nextInt();
                isStaffIdValid = true;
            } else {
                System.out.println("Vous ne pouvez entrez qu'un nombre entier pour l'id du staff.");
            }
        }

        while (!isRentalIdValid) {
            System.out.print("Entrez l'id de la location : ");
            Scanner rent = new Scanner(System.in);
            // vérifier si le nom est un nombre
            if (rent.hasNextInt()){
                rentalId = rent.nextInt();
                isRentalIdValid = true;
            } else {
                System.out.println("Vous ne pouvez entrez qu'un nombre entier pour l'id de la location.");
            }
        }

        while (!isAmountValid) {
            System.out.print("Entrez une quantité d'argent : ");
            Scanner mount = new Scanner(System.in);
            // vérifier si le nom est un nombre
            if (mount.hasNextFloat()){
                amount = mount.nextFloat();
                isAmountValid = true;
            } else {
                System.out.println("Vous ne pouvez entrez qu'un nombre décimal pour l'id de la location.");
            }
        }

        // créer une requête préparée pour insérer les données dans la table
        String sql3 = "INSERT INTO payment (customer_id, staff_id, rental_id, amount) VALUES (?, ?, ?, ?)";
        PreparedStatement statement2 = connection.prepareStatement(sql3);

        // définir les valeurs pour les paramètres de la requête
        statement2.setInt(1, customerId);
        statement2.setInt(2, staffId);
        statement2.setInt(3, rentalId);
        statement2.setFloat(4, amount);

        // exécuter la requête
        statement2.executeUpdate();

    }

    /*Locations (argent)*/
    public static void afficheRental(Connection connection) throws SQLException {
        Statement stmt= connection.createStatement();

        ResultSet rent= stmt.executeQuery("SELECT * FROM rental");
        System.out.println();

        while (rent.next()) {
            System.out.println("rental_id: " + rent.getString(1));
            System.out.println("rental_date: " + rent.getString(2));
            System.out.println("inventory_id: " + rent.getString(3));
            System.out.println("customer_id: " + rent.getString(4));
            System.out.println("return_date: " + rent.getString(5));
            System.out.println("staff_id: " + rent.getString(6));
            System.out.println("last_update: " + rent.getString(7));
            System.out.println("========================================================================================================");
        }
    }

    public static void addRental(Connection connection) throws SQLException {
        afficheRental(connection);
        // utiliser un Scanner pour lire les entrées de l'utilisateur
        Scanner scanner = new Scanner(System.in);

        int inventoryId = 0;
        int customerId = 0;
        int staffId = 0;

        boolean isInventoryIdValid = false;
        boolean isCustomerIdValid = false;
        boolean isStaffIdValid = false;

        while (!isInventoryIdValid) {
            System.out.print("Entrez l'id de la location : ");
            Scanner invent = new Scanner(System.in);
            // vérifier si le nom est un nombre
            if (invent.hasNextInt()){
                inventoryId = invent.nextInt();
                isInventoryIdValid = true;
            } else {
                System.out.println("Vous ne pouvez entrez qu'un nombre entier pour l'id de la location.");
            }
        }
        while (!isCustomerIdValid) {
            System.out.print("Entrez l'id de la location : ");
            Scanner cust = new Scanner(System.in);
            // vérifier si le nom est un nombre
            if (cust.hasNextInt()){
                customerId = cust.nextInt();
                isCustomerIdValid = true;
            } else {
                System.out.println("Vous ne pouvez entrez qu'un nombre entier pour l'id de la location.");
            }
        }
        while (!isStaffIdValid) {
            System.out.print("Entrez l'id de la location : ");
            Scanner staffy = new Scanner(System.in);
            // vérifier si le nom est un nombre
            if (staffy.hasNextInt()){
                staffId = staffy.nextInt();
                isStaffIdValid = true;
            } else {
                System.out.println("Vous ne pouvez entrez qu'un nombre entier pour l'id de la location.");
            }
        }

        // créer une requête préparée pour insérer les données dans la table
        String sql3 = "INSERT INTO rental (inventory_id, customer_id, staff_id) VALUES (?, ?, ?)";
        PreparedStatement statement2 = connection.prepareStatement(sql3);

        // définir les valeurs pour les paramètres de la requête
        statement2.setInt(1, inventoryId);
        statement2.setInt(2, customerId);
        statement2.setInt(3, staffId);

        // exécuter la requête
        statement2.executeUpdate();

    }

    /*Ventes par catégories de film*/
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

    /*Ventes par magasin*/
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

    /*Équipe*/
    public static void afficheStaff(Connection connection) throws SQLException {
        Statement stmt= connection.createStatement();

        ResultSet staff= stmt.executeQuery("SELECT * FROM staff");
        System.out.println();

        while (staff.next()) {
            System.out.println("staff_id: " + staff.getString(1));
            System.out.println("first_name: " + staff.getString(2));
            System.out.println("last_name: " + staff.getString(3));
            System.out.println("address_id: " + staff.getString(4));
            System.out.println("email: " + staff.getString(6));
            System.out.println("store_id: " + staff.getString(7));
            System.out.println("active: " + staff.getString(8));
            System.out.println("username: " + staff.getString(9));
            System.out.println("password: " + staff.getString(10));
            System.out.println("last_update: " + staff.getString(11));
            System.out.println("========================================================================================================");
        }
    }

    public static void addStaff(Connection connection) throws SQLException {
        afficheStaff(connection);
        // utiliser un Scanner pour lire les entrées de l'utilisateur
        Scanner scanner = new Scanner(System.in);

        // Variables pour stocker les entrées de l'utilisateur
        String firstName = "";
        String lastName = "";
        int addressId = 0;
        String EMail = "";
        int storeId = 0;
        String username = "";
        String password = "";
        boolean isFirstNameValid = false;
        boolean isLastNameValid = false;
        boolean isAddressIdValid = false;
        boolean isEmailValid = false;
        boolean isStoreIdValid = false;
        boolean isUsernameValid = false;
        boolean isPasswordValid = false;
        boolean isStaffExist = false;

        while (!isFirstNameValid) {
            System.out.print("Entrez le prénom de l'équipier : ");
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
            System.out.print("Entrez son nom : ");
            lastName = scanner.next();
            // vérifier si le nom est un nombre
            try {
                Double.parseDouble(lastName);
                System.out.println("Vous ne pouvez pas entrer un nombre pour le nom.");
            } catch (NumberFormatException e) {
                isLastNameValid = true;
            }
        }

        while (!isAddressIdValid) {
            System.out.print("Entrez le nombre de l'adresse id de la personne : ");
            Scanner idadress = new Scanner(System.in);
            // vérifier si l'id est autre qu'un nombre
            if (idadress.hasNextInt()){
                addressId = idadress.nextInt();
                isAddressIdValid = true;
            } else {
                System.out.println("Vous ne pouvez entrez qu'un nombre entier pour l'adresse id.");
            }
        }

        while (!isEmailValid){
            System.out.print("Entrez son email : ");
            Scanner email = new Scanner(System.in);
            if (email.hasNext()){
                EMail = email.next();
                isEmailValid = true;
            } else {
                System.out.println("L'adresse email ne peut pas être vide.");
            }
        }

        while (!isStoreIdValid) {
            System.out.print("Entrez le nombre du store id de la personne : ");
            Scanner idStore = new Scanner(System.in);
            // vérifier si le nom est un nombre
            if (idStore.hasNextInt()){
                storeId = idStore.nextInt();
                isStoreIdValid = true;
            } else {
                System.out.println("Vous ne pouvez entrez qu'un nombre entier pour l'id du store.");
            }
        }

        while (!isUsernameValid){
            System.out.print("Entrez son pseudonyme : ");
            Scanner User = new Scanner(System.in);
            if (User.hasNext()){
                username = User.next();
                isUsernameValid = true;
            } else {
                System.out.println("Le pseudonyme ne peut pas être vide.");
            }
        }

        while (!isPasswordValid){
            System.out.print("Entrez son mot de passe : ");
            Scanner Pass = new Scanner(System.in);
            if (Pass.hasNext()){
                password = Pass.next();
                isPasswordValid = true;
            } else {
                System.out.println("Le mot de passe ne peut pas être vide.");
            }
        }

        // créer une requête pour vérifier si l'acteur existe déjà dans la table
        String sql = "SELECT * FROM staff WHERE first_name = ? AND last_name = ? AND email = ?";
        PreparedStatement statement = connection.prepareStatement(sql);

        // définir les valeurs pour les paramètres de la requête
        statement.setString(1, firstName);
        statement.setString(2, lastName);
        statement.setString(3, EMail);

        // exécuter la requête
        ResultSet resultSet = statement.executeQuery();

        // vérifier si l'acteur existe déjà dans la table
        while (resultSet.next()) {
            isStaffExist = true;
            break;
        }

        if (isStaffExist) {
            System.out.println("L'acteur existe déjà dans la base de données.");
        } else {
            // créer une requête préparée pour insérer les données dans la table
            String sql2 = "INSERT INTO staff (first_name, last_name, address_id, email, store_id, username, password) VALUES (?, ?, ?, ?, ?, ?, ?)";
            statement = connection.prepareStatement(sql2);

            // définir les valeurs pour les paramètres de la requête
            statement.setString(1, firstName);
            statement.setString(2, lastName);
            statement.setInt(3, addressId);
            statement.setString(4, EMail);
            statement.setInt(5, storeId);
            statement.setString(6, username);
            statement.setString(7, password);

            // exécuter la requête
            statement.executeUpdate();
        }
    }

    /*Personnes dans l'équipe*/
    public static void afficheStaffList(Connection connection) throws SQLException {
        Statement stmt= connection.createStatement();

        ResultSet staffList= stmt.executeQuery("SELECT * FROM staff_list");
        System.out.println();

        while (staffList.next()) {
            System.out.println("ID: " + staffList.getString(1));
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

    /*Magasin*/
    public static void afficheStore(Connection connection) throws SQLException {
        Statement stmt= connection.createStatement();

        ResultSet store= stmt.executeQuery("SELECT * FROM store");
        System.out.println();

        while (store.next()) {
            System.out.println("store_id: " + store.getString(1));
            System.out.println("manager_staff_id: " + store.getString(2));
            System.out.println("adress_id: " + store.getString(3));
            System.out.println("last_update: " + store.getString(4));
            System.out.println("========================================================================================================");
        }
    }

    public static void addStore(Connection connection) throws SQLException{
        afficheStore(connection);
        // utiliser un Scanner pour lire les entrées de l'utilisateur
        Scanner scanner = new Scanner(System.in);

        int managerStaffId = 0;
        int addressId = 0;

        boolean isManagerStaffIdValid = false;
        boolean isAddressIdValid = false;

        while (!isManagerStaffIdValid) {
            System.out.print("Entrez le nombre du store id de la personne : ");
            Scanner manage = new Scanner(System.in);
            // vérifier si le nom est un nombre
            if (manage.hasNextInt()){
                managerStaffId = manage.nextInt();
                isManagerStaffIdValid = true;
            } else {
                System.out.println("Vous ne pouvez entrez qu'un nombre entier pour l'id du store.");
            }
        }

        while (!isAddressIdValid) {
            System.out.print("Entrez le nombre du store id de la personne : ");
            Scanner address = new Scanner(System.in);
            // vérifier si le nom est un nombre
            if (address.hasNextInt()){
                addressId = address.nextInt();
                isAddressIdValid = true;
            } else {
                System.out.println("Vous ne pouvez entrez qu'un nombre entier pour l'id du store.");
            }

            // créer une requête préparée pour insérer les données dans la table
            String sql3 = "INSERT INTO store (manager_store_id, address_id) VALUES (?, ?)";
            PreparedStatement statement2 = connection.prepareStatement(sql3);

            // définir les valeurs pour les paramètres de la requête
            statement2.setInt(1, managerStaffId);
            statement2.setInt(2, addressId);

            // exécuter la requête
            statement2.executeUpdate();
        }

    }

}