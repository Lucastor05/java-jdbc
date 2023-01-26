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

                        }else if(choix == 9) {
                            choiceFilm(connection);

                        }else if(choix == 10) {
                            choiceFilmActor(connection);

                        }else if(choix == 11) {
                            choiceFilmCate(connection);

                        }else if(choix == 12) {
                            choiceFilmList(connection);

                        }else if(choix == 13) {
                            choiceFilmText(connection);

                        }else if(choix == 14) {
                            choiceInventory(connection);

                        }else if(choix == 15) {
                            choiceLanguage(connection);

                        }else if(choix == 16) {
                            choiceNicerFilm(connection);

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


    /*film*/
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
                        addFilm(connection);
                        System.out.println("\n1. Créer un nouveau film\n2. Afficher les films\n3. Mettre à jour un film\n4. Supprimer un film\n5. Retour");

                    }else if(choix == 2){
                        afficheFilm(connection);
                        System.out.println("\n1. Créer un nouveau film\n2. Afficher les films\n3. Mettre à jour un film\n4. Supprimer un film\n5. Retour");

                    }else if(choix == 3){
                        updateFilm(connection);
                        System.out.println("\n1. Créer un nouveau film\n2. Afficher les films\n3. Mettre à jour un film\n4. Supprimer un film\n5. Retour");

                    }else if(choix == 4){
                        removeFilm(connection);
                        System.out.println("\n1. Créer un nouveau film\n2. Afficher les films\n3. Mettre à jour un film\n4. Supprimer un film\n5. Retour");

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
    public static void afficheFilm(Connection connection) throws SQLException{
        Statement stmt= connection.createStatement();

        ResultSet filmTitle= stmt.executeQuery("SELECT * FROM film");

        System.out.println();
        while(filmTitle.next()){
            System.out.println("film_id: "+ filmTitle.getString(1));
            System.out.println("Title: "+ filmTitle.getString(2));
            System.out.println("Description: "+ filmTitle.getString(3));
            System.out.println("original_language_id: "+ filmTitle.getString(6));
            System.out.println("rental_duration: "+ filmTitle.getString(7));
            System.out.println("rental_rate: "+ filmTitle.getString(8));
            System.out.println("length: "+ filmTitle.getString(9));
            System.out.println("replacement_cost: "+ filmTitle.getString(10));
            System.out.println("rating: "+ filmTitle.getString(11));
            System.out.println("special_features: "+ filmTitle.getString(12));
            System.out.println("last_update: "+ filmTitle.getString(13));
            System.out.println("release_year: "+ filmTitle.getString(4));
            System.out.println("language_id: "+ filmTitle.getString(5));

            System.out.println("========================================================================================================");
        }
    }
    public static void addFilm(Connection connection) throws SQLException {

        String title;
        String description;
        int release_year;
        int language_id;
        int original_language_id;
        float rental_duration;
        float rental_rate;
        int length;
        float replacement_cost;
        String rating;
        String special_features;

        /*loop pour film*/
        boolean newFilmValide = false;
        while(!newFilmValide) {

            /*le title*/
            System.out.println("Entrez le title de votre film: ");
            Scanner newTitle = new Scanner(System.in);

            /*check si il y a un titre et enregistre*/
            if (newTitle.hasNext()) {
                title = newTitle.nextLine().toUpperCase();




                /*loop pour la description*/
                boolean newFilmDescription = false;
                while(!newFilmDescription) {

                    /*la description*/
                    System.out.println("Entrez la description de votre film: ");
                    Scanner newDescription = new Scanner(System.in);

                    /*check si il y a une description et enregistre*/
                    if (newDescription.hasNext()) {
                        description = newDescription.nextLine();




                        /*loop pour le release year*/
                        boolean newFilmReleaseYear = false;
                        while(!newFilmReleaseYear) {

                            /*le release year*/
                            System.out.println("Entrez le release year: ");
                            Scanner newReleaseYear = new Scanner(System.in);

                            /*check si il y a une date et enregistre*/
                            if (newReleaseYear.hasNextInt()) {
                                release_year = newReleaseYear.nextInt();
                                if (release_year > 1800 && release_year < 9999) {



                                    /*loop pour le language id*/
                                    boolean newFilmLanguageId = false;
                                    while (!newFilmLanguageId) {

                                        /*le language id*/
                                        System.out.println("Entrez le language id: ");
                                        Scanner newLanguageId = new Scanner(System.in);

                                        /*check si il y a un language id et enregistre*/
                                        if (newLanguageId.hasNextInt()) {
                                            language_id = newLanguageId.nextInt();



                                            /*loop pour le original language id*/
                                            boolean newFilmOriginalLanguage = false;
                                            while (!newFilmOriginalLanguage) {

                                                /*le original language id*/
                                                System.out.println("Entrez le original language id: ");
                                                Scanner newOriginalLanguageId = new Scanner(System.in);

                                                /*check si il y a un original language id et enregistre*/
                                                if (newOriginalLanguageId.hasNextInt()) {
                                                    original_language_id = newOriginalLanguageId.nextInt();




                                                    /*loop pour le rental duration*/
                                                    boolean newFilmRentalDuration = false;
                                                    while (!newFilmRentalDuration) {

                                                        /*le rental duration*/
                                                        System.out.println("Entrez le rental duration: ");
                                                        Scanner newRentalDuration = new Scanner(System.in);

                                                        /*check si il y a un rental duration et enregistre*/
                                                        if (newRentalDuration.hasNextInt()) {
                                                            rental_duration = newRentalDuration.nextInt();




                                                            //loop pour rental rate
                                                            boolean newFilmRentalRate = false;
                                                            while (!newFilmRentalRate) {

                                                                /*le rental rate*/
                                                                System.out.println("Entrez le rental rate: ");
                                                                Scanner newRentalRate = new Scanner(System.in);

                                                                /*check si il y a un rental rate et enregistre*/
                                                                if (newRentalRate.hasNextFloat()) {
                                                                    rental_rate = newRentalRate.nextFloat();



                                                                    //loop pour length
                                                                    boolean newFilmLength = false;
                                                                    while (!newFilmLength) {

                                                                        /*le length*/
                                                                        System.out.println("Entrez le length: ");
                                                                        Scanner newLength = new Scanner(System.in);

                                                                        /*check si il y a un length et enregistre*/
                                                                        if (newLength.hasNextInt()) {
                                                                            length = newLength.nextInt();




                                                                            //loop pour replacement cost
                                                                            boolean newFilmReplacementCost = false;
                                                                            while (!newFilmReplacementCost) {

                                                                                /*le replacement cost*/
                                                                                System.out.println("Entrez le replacement cost: ");
                                                                                Scanner newReplacementCost = new Scanner(System.in);

                                                                                /*check si il y a un replacement cost et enregistre*/
                                                                                if (newReplacementCost.hasNextFloat()) {
                                                                                    replacement_cost = newReplacementCost.nextFloat();




                                                                                    //loop pour rating
                                                                                    boolean newFilmRating = false;
                                                                                    while (!newFilmRating) {

                                                                                        /*le rating*/
                                                                                        System.out.println("Entrez le rating: ");
                                                                                        Scanner newRating = new Scanner(System.in);

                                                                                        /*check si il y a un rating et enregistre*/
                                                                                        if (newRating.hasNext()) {
                                                                                            rating = newRating.nextLine();




                                                                                            //loop pour speciales features
                                                                                            boolean newFilmSpecialFeats = false;
                                                                                            while (!newFilmSpecialFeats) {

                                                                                                /*le replacement cost*/
                                                                                                System.out.println("Entrez les speciales features: ");
                                                                                                Scanner newSpecialFeats = new Scanner(System.in);

                                                                                                /*check si il y a les speciales features et enregistre*/
                                                                                                if (newSpecialFeats.hasNext()) {
                                                                                                    special_features = newSpecialFeats.nextLine();



                                                                                                    String query = "SELECT * FROM film WHERE title = ?";
                                                                                                    PreparedStatement statement = connection.prepareStatement(query);
                                                                                                    statement.setString(1, title);
                                                                                                    ResultSet resultSet = statement.executeQuery();

                                                                                                    if (resultSet.next()) {
                                                                                                        System.err.println("le film existe deja svp veuillez aller modifier");
                                                                                                    } else {


                                                                                                        query = "INSERT INTO film (title, description, release_year, language_id, original_language_id, rental_duration, rental_rate, length, replacement_cost, rating, special_features) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
                                                                                                        statement = connection.prepareStatement(query);
                                                                                                        statement.setString(1, title);
                                                                                                        statement.setString(2, description);
                                                                                                        statement.setString(3, Integer.toString(release_year));
                                                                                                        statement.setString(4, Integer.toString(language_id));
                                                                                                        statement.setString(5, Integer.toString(original_language_id));
                                                                                                        statement.setString(6, Float.toString(rental_duration));
                                                                                                        statement.setString(7, Float.toString(rental_rate));
                                                                                                        statement.setString(8, Integer.toString(length));
                                                                                                        statement.setString(9, Float.toString(replacement_cost));
                                                                                                        statement.setString(10, rating);
                                                                                                        statement.setString(11, special_features);

                                                                                                        int result = statement.executeUpdate();

                                                                                                        if (result == 1) {
                                                                                                            System.out.println("Les valeurs ont été insérée avec succès dans la table film");
                                                                                                        } else {
                                                                                                            System.err.println("Erreur: lors de l'insertion de les valeurs dans la table film");
                                                                                                        }

                                                                                                        newFilmSpecialFeats = true;
                                                                                                        newFilmRating = true;
                                                                                                        newFilmReplacementCost = true;
                                                                                                        newFilmLength = true;
                                                                                                        newFilmRentalRate = true;
                                                                                                        newFilmRentalDuration = true;
                                                                                                        newFilmOriginalLanguage = true;
                                                                                                        newFilmLanguageId = true;
                                                                                                        newFilmReleaseYear = true;
                                                                                                        newFilmDescription = true;
                                                                                                        newFilmValide = true;
                                                                                                    }
                                                                                                }
                                                                                            }
                                                                                        }
                                                                                    }
                                                                                } else {
                                                                                    System.err.println("Erreur: pas un nombre ");
                                                                                }
                                                                            }
                                                                        }
                                                                    }
                                                                }
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        } else {
                                            System.err.println("Erreur: pas un nombre");
                                        }
                                    }
                                } else {
                                    System.out.println("c'est impossible...");
                                }

                            } else {
                                System.err.println("Erreur: pas un nombre");
                            }
                        }
                    }
                }
            }
        }
    }
    public static void updateFilm(Connection connection) throws SQLException {
        afficheFilm(connection);
        boolean newUpdateFilm = false;
        while (!newUpdateFilm) {
            System.out.println("1. modifier le film\n2.quitter");
            System.out.println("Entrez votre choix :");

            Scanner picks = new Scanner(System.in);

            if (picks.hasNextInt()) {
                int pick = picks.nextInt();

                if (pick == 1){

                    System.out.println("id du film: ");
                    Scanner newIdFilm = new Scanner(System.in);

                    if (newIdFilm.hasNextInt()) {
                        int film_id = newIdFilm.nextInt();

                        System.out.println("\n1. Modifier title\n2. Modifier description\n3. Modifier release year\n4. Modifier language id\n5. Modifier original language id\n6. Modifier rental duration\n7. Modifier rental rate\n8. Modifier length\n9. Modifier remplacement cost\n10. Modifier rating\n11. Modifier special features");
                        System.out.println("Entrez votre choix :");
                        Scanner choices = new Scanner(System.in);

                        if (choices.hasNextInt()) {

                            int choice = choices.nextInt();
                            PreparedStatement statement = null;
                            String sql;


                            if (choice == 1) {
                                System.out.println("titre: ");
                                Scanner newTitle = new Scanner(System.in);
                                if (newTitle.hasNext()) {
                                    String title = newTitle.nextLine();
                                    sql = "UPDATE film SET title = ? WHERE film_id = ?";
                                    statement = connection.prepareStatement(sql);
                                    statement.setString(2, Integer.toString(film_id));
                                    statement.setString(1, title);
                                }

                            } else if (choice == 2) {
                                System.out.println("description: ");
                                Scanner newDescription = new Scanner(System.in);
                                if (newDescription.hasNext()) {
                                    String description = newDescription.nextLine();
                                    sql = "UPDATE film SET description = ? WHERE film_id = ?";
                                    statement = connection.prepareStatement(sql);
                                    statement.setString(2, Integer.toString(film_id));
                                    statement.setString(1, description);
                                }

                            } else if (choice == 3) {
                                System.out.println("release year: ");
                                Scanner newReleaseYear = new Scanner(System.in);
                                if (newReleaseYear.hasNextInt()) {
                                    int release_year = newReleaseYear.nextInt();
                                    if (release_year > 1800 && release_year < 9999) {
                                        sql = "UPDATE film SET release_year = ? WHERE film_id = ?";
                                        statement = connection.prepareStatement(sql);
                                        statement.setString(2, Integer.toString(film_id));
                                        statement.setString(1, Integer.toString(release_year));
                                    } else {
                                        System.err.println("pas possible...");
                                    }
                                } else {
                                    System.err.println("pas un nombre");
                                }

                            } else if (choice == 4) {
                                System.out.println("language id: ");
                                Scanner newLanguageId = new Scanner(System.in);
                                if (newLanguageId.hasNextInt()) {
                                    int language_id = newLanguageId.nextInt();
                                    sql = "UPDATE film SET language_id = ? WHERE film_id = ?";
                                    statement = connection.prepareStatement(sql);
                                    statement.setString(2, Integer.toString(film_id));
                                    statement.setString(1, Integer.toString(language_id));
                                } else {
                                    System.err.println("pas un nombre");
                                }

                            } else if (choice == 5) {
                                System.out.println("original language id: ");
                                Scanner newOriginalLanguageId = new Scanner(System.in);
                                if (newOriginalLanguageId.hasNextInt()) {
                                    int original_language_id = newOriginalLanguageId.nextInt();
                                    sql = "UPDATE film SET original_language_id = ? WHERE film_id = ?";
                                    statement = connection.prepareStatement(sql);
                                    statement.setString(2, Integer.toString(film_id));
                                    statement.setString(1, Integer.toString(original_language_id));
                                } else {
                                    System.err.println("pas un nombre");
                                }

                            } else if (choice == 6) {
                                System.out.println("rental duration: ");
                                Scanner newRentalDuration = new Scanner(System.in);
                                if (newRentalDuration.hasNextInt()) {
                                    int rental_duration = newRentalDuration.nextInt();
                                    sql = "UPDATE film SET rental_duration = ? WHERE film_id = ?";
                                    statement = connection.prepareStatement(sql);
                                    statement.setString(2, Integer.toString(film_id));
                                    statement.setString(1, Integer.toString(rental_duration));
                                } else {
                                    System.err.println("pas un nombre");
                                }

                            } else if (choice == 7) {
                                System.out.println("rental rate: ");
                                Scanner newRentalRate = new Scanner(System.in);
                                if (newRentalRate.hasNextInt()) {
                                    int rental_rate = newRentalRate.nextInt();
                                    sql = "UPDATE film SET rental_rate = ? WHERE film_id = ?";
                                    statement = connection.prepareStatement(sql);
                                    statement.setString(2, Integer.toString(film_id));
                                    statement.setString(1, Float.toString(rental_rate));
                                } else {
                                    System.err.println("pas un nombre");
                                }

                            } else if (choice == 8) {
                                System.out.println("length: ");
                                Scanner newLength = new Scanner(System.in);
                                if (newLength.hasNextInt()) {
                                    int length = newLength.nextInt();
                                    sql = "UPDATE film SET length = ? WHERE film_id = ?";
                                    statement = connection.prepareStatement(sql);
                                    statement.setString(2, Integer.toString(film_id));
                                    statement.setString(1, Integer.toString(length));
                                } else {
                                    System.err.println("pas un nombre");
                                }

                            } else if (choice == 9) {
                                System.out.println("replacement cost: ");
                                Scanner newReplacementCost = new Scanner(System.in);
                                if (newReplacementCost.hasNextInt()) {
                                    int replacement_cost = newReplacementCost.nextInt();
                                    sql = "UPDATE film SET replacement_cost = ? WHERE film_id = ?";
                                    statement = connection.prepareStatement(sql);
                                    statement.setString(2, Integer.toString(film_id));
                                    statement.setString(1, Float.toString(replacement_cost));
                                } else {
                                    System.err.println("pas un nombre");
                                }

                            } else if (choice == 10) {
                                System.out.println("rating: ");
                                Scanner newRating = new Scanner(System.in);
                                if (newRating.hasNext()) {
                                    String rating = newRating.nextLine();
                                    sql = "UPDATE film SET rating = ? WHERE film_id = ?";
                                    statement = connection.prepareStatement(sql);
                                    statement.setString(2, Integer.toString(film_id));
                                    statement.setString(1, rating);
                                }

                            } else if (choice == 11) {
                                System.out.println("special features: ");
                                Scanner newSpecialFeats = new Scanner(System.in);
                                if (newSpecialFeats.hasNext()) {
                                    String special_features = newSpecialFeats.nextLine();
                                    sql = "UPDATE film SET special_features = ? WHERE film_id = ?";
                                    statement = connection.prepareStatement(sql);
                                    statement.setString(2, Integer.toString(film_id));
                                    statement.setString(1, special_features);
                                }
                            }


                            statement.executeUpdate();

                        } else {
                            System.err.println("pas un nombre");
                        }
                    }

                } else if (pick == 2) {
                    System.out.println("Quitter");
                    newUpdateFilm = true;

                } else {
                    System.err.println("pas une option");
                }
            }
        }
    }
    public static void removeFilm(Connection connection) throws SQLException {
        afficheFilm(connection);

        boolean newRemoveFilm = false;
        while(!newRemoveFilm) {
            ResultSet result;
            PreparedStatement statement;

            System.out.println("quel film voulez vous supprimer ?");
            Scanner removeTitle = new Scanner(System.in);
            String title = removeTitle.nextLine();

            String sql = "SELECT * FROM film WHERE title = ?";
            statement = connection.prepareStatement(sql);
            statement.setString(1, title);
            result = statement.executeQuery();


            if (!result.next()) {
                System.err.println("le film existe pas :P");
                newRemoveFilm = true;
            } else {

                sql = "DELETE FROM film WHERE title = ?";
                statement = connection.prepareStatement(sql);

                statement.setString(1, title);

                statement.executeUpdate();

                System.out.println("film a ete supprimer");
                newRemoveFilm = true;
            }
        }
    }





    /*film_actor*/
    public static void choiceFilmActor(Connection connection) throws SQLException{
        System.out.println("\n1. Créer un nouveau film actor\n2. Afficher les films actors\n3. Mettre à jour un film actor\n4. Supprimer un film actor\n5. Retour");
        System.out.println("Entrez votre choix :");
        Scanner choice = new Scanner(System.in);

        boolean valide = false;
        while(!valide) {
            if (choice.hasNextInt()){
                int choix = choice.nextInt();
                if(choix <= 5 || choix >= 1){
                    if(choix == 1){
                        addFilmActor(connection);
                        System.out.println("\n1. Créer un nouveau film actor\n2. Afficher les films actors\n3. Mettre à jour un film actor\n4. Supprimer un film actor\n5. Retour");

                    }else if(choix == 2){
                        afficheFilmActor(connection);
                        System.out.println("\n1. Créer un nouveau film actor\n2. Afficher les films actors\n3. Mettre à jour un film actor\n4. Supprimer un film actor\n5. Retour");

                    }else if(choix == 3){
                        updateFilmActor(connection);
                        System.out.println("\n1. Créer un nouveau film actor\n2. Afficher les films actors\n3. Mettre à jour un film actor\n4. Supprimer un film actor\n5. Retour");

                    }else if(choix == 4){
                        removeFilmActor(connection);
                        System.out.println("\n1. Créer un nouveau film actor\n2. Afficher les films actors\n3. Mettre à jour un film actor\n4. Supprimer un film actor\n5. Retour");

                    }else if(choix == 5){
                        System.out.println("Quitter");
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
    public static void afficheFilmActor(Connection connection) throws SQLException{
        Statement stmt= connection.createStatement();

        ResultSet filmActor= stmt.executeQuery("SELECT * FROM film_actor");

        System.out.println();
        while(filmActor.next()){
            System.out.println("actor_id: "+ filmActor.getString(1));
            System.out.println("film_id: "+ filmActor.getString(2));
            System.out.println("last_update: "+ filmActor.getString(3));
            System.out.println("========================================================================================================");
        }
    }
    public static void addFilmActor(Connection connection) throws SQLException{
        afficheFilmActor(connection);

        boolean restart = false;
        while (!restart){

            System.out.println("1. ajouter un film actor\n2.quitter");
            Scanner choix1 = new Scanner(System.in);

            if (choix1.hasNextInt()){
                int choix1def = choix1.nextInt();

                if (choix1def == 1){
                    int actor_id = 0;
                    int film_id = 0;

                    boolean loop = false;
                    while(!loop) {
                        System.out.println("actor id:");
                        Scanner newActorId = new Scanner(System.in);
                        if (newActorId.hasNextInt()) {
                            actor_id = newActorId.nextInt();
                            loop = true;
                        } else {
                            System.err.println("pas un nombre");
                        }
                    }

                    boolean loop2 = false;
                    while (!loop2){
                        System.out.println("film id:");
                        Scanner newFilmId = new Scanner(System.in);
                        if (newFilmId.hasNextInt()) {
                            film_id = newFilmId.nextInt();
                            loop2 = true;
                        } else {
                            System.err.println("pas un nombre");
                        }
                    }

                    String query = "SELECT * FROM film_actor WHERE actor_id = ? AND film_id = ?;";
                    PreparedStatement statement = connection.prepareStatement(query);
                    statement.setString(1, Integer.toString(actor_id));
                    statement.setString(2, Integer.toString(film_id));

                    ResultSet result1 = statement.executeQuery();

                    if (result1.next()){
                        System.err.println("existe deja");
                        break;
                    }


                    query = "INSERT INTO film_actor (actor_id, film_id) VALUES (?, ?);";
                    statement = connection.prepareStatement(query);
                    statement.setString(1, Integer.toString(actor_id));
                    statement.setString(2, Integer.toString(film_id));

                    int result2 = statement.executeUpdate();

                    if (result2 == 1) {
                        System.out.println("Les valeurs ont été insérée avec succès dans la table film_actor");
                    } else {
                        System.err.println("Erreur: lors de l'insertion de les valeurs dans la table film_actor");
                    }

                    restart = true;




                } else if (choix1def == 2){
                    System.out.println("Quitter :D");

                } else {
                    System.err.println("pas une option");
                }
            }
        }
    }
    public static void updateFilmActor(Connection connection) throws SQLException{
        afficheFilmActor(connection);
        boolean newUpdateFilm = false;
        while (!newUpdateFilm) {
            System.out.println("1. modifier le film id et actor\n2.quitter");
            System.out.println("Entrez votre choix :");

            Scanner picks = new Scanner(System.in);

            if (picks.hasNextInt()) {
                int pick = picks.nextInt();

                if (pick == 1){

                    System.out.println("id de actor: ");
                    Scanner newIdActor = new Scanner(System.in);

                    if (newIdActor.hasNextInt()) {
                        int actor_id = newIdActor.nextInt();

                        boolean loop = false;
                        while (!loop) {
                            System.out.println("id du film a changer: ");
                            Scanner newIdFilm = new Scanner(System.in);

                            if (newIdFilm.hasNextInt()) {
                                int film_id = newIdFilm.nextInt();

                                String sql = "SELECT * FROM film_actor WHERE actor_id = ? AND film_id = ?";
                                PreparedStatement statement = connection.prepareStatement(sql);

                                statement.setString(1, Integer.toString(actor_id));
                                statement.setString(2, Integer.toString(film_id));
                                ResultSet resultSet = statement.executeQuery();

                                boolean isFilmActorExist = false;
                                while (resultSet.next()) {
                                    System.err.println("existe deja");
                                    isFilmActorExist = true;
                                    loop = true;
                                    newUpdateFilm = true;
                                }

                                if (!isFilmActorExist) {
                                    sql = "UPDATE film_actor SET film_id = ? WHERE actor_id = ?";
                                    statement = connection.prepareStatement(sql);
                                    statement.setString(2, Integer.toString(actor_id));
                                    statement.setString(1, Integer.toString(film_id));
                                    statement.executeUpdate();
                                    System.out.println("enregistrer ");
                                    loop = true;
                                    newUpdateFilm = true;

                                }

                            } else {
                                System.err.println("pas un nombre");
                            }
                        }
                    }

                } else if (pick == 2) {
                    System.out.println("Quitter");
                    newUpdateFilm = true;

                } else {
                    System.err.println("pas une option");
                }
            }
        }
    }
    public static void removeFilmActor(Connection connection) throws SQLException{
        afficheFilmActor(connection);

        boolean newRemoveFilm = false;
        while(!newRemoveFilm) {
            ResultSet result;
            PreparedStatement statement;

            System.out.println("quel actor id voulez vous supprimer ?");
            Scanner removeIdActor = new Scanner(System.in);
            int actor_id = removeIdActor.nextInt();

            System.out.println("quel film id voulez vous supprimer ?");
            Scanner removeIdfilm = new Scanner(System.in);
            int film_id = removeIdfilm.nextInt();

            String sql = "SELECT * FROM film_actor WHERE actor_id = ? AND film_id = ?";
            statement = connection.prepareStatement(sql);
            statement.setString(1, Integer.toString(actor_id));
            statement.setString(2, Integer.toString(film_id));
            result = statement.executeQuery();


            if (!result.next()) {
                System.err.println("le film actor existe pas :P");
                newRemoveFilm = true;

            } else {

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





    //Film Category
    public static void choiceFilmCate(Connection connection) throws SQLException{
        System.out.println("\n1. Créer un nouveau film category\n2. Afficher les films categories\n3. Mettre à jour un film category\n4. Supprimer un film category\n5. Retour");
        System.out.println("Entrez votre choix :");
        Scanner choice = new Scanner(System.in);

        boolean valide = false;
        while(!valide) {
            if (choice.hasNextInt()){
                int choix = choice.nextInt();
                if(choix <= 5 || choix >= 1){
                    if(choix == 1){
                        addFilmCate(connection);
                        System.out.println("\n1. Créer un nouveau film category\n2. Afficher les films categories\n3. Mettre à jour un film category\n4. Supprimer un film category\n5. Retour");

                    }else if(choix == 2){
                        afficheFilmCate(connection);
                        System.out.println("\n1. Créer un nouveau film category\n2. Afficher les films categories\n3. Mettre à jour un film category\n4. Supprimer un film category\n5. Retour");

                    }else if(choix == 3){
                        updateFilmCate(connection);
                        System.out.println("\n1. Créer un nouveau film category\n2. Afficher les films categories\n3. Mettre à jour un film category\n4. Supprimer un film category\n5. Retour");

                    }else if(choix == 4){

                        System.out.println("\n1. Créer un nouveau film category\n2. Afficher les films categories\n3. Mettre à jour un film category\n4. Supprimer un film category\n5. Retour");

                    }else if(choix == 5){
                        System.out.println("Quitter");
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
    public static void addFilmCate(Connection connection) throws SQLException{
        afficheFilmCate(connection);

        boolean restart = false;
        while (!restart){

            System.out.println("1. ajouter une category\n2.quitter");
            Scanner choix1 = new Scanner(System.in);

            if (choix1.hasNextInt()){
                int choix1def = choix1.nextInt();

                if (choix1def == 1){
                    int category_id = 0;
                    int film_id = 0;

                    boolean loop = false;
                    while(!loop) {
                        System.out.println("category id:");
                        Scanner newCategory = new Scanner(System.in);
                        if (newCategory.hasNextInt()) {
                            category_id = newCategory.nextInt();
                            loop = true;
                        } else {
                            System.err.println("pas un nombre");
                        }
                    }

                    boolean loop2 = false;
                    while (!loop2){
                        System.out.println("film id:");
                        Scanner newFilmId = new Scanner(System.in);
                        if (newFilmId.hasNextInt()) {
                            film_id = newFilmId.nextInt();
                            loop2 = true;
                        }
                    }

                    String query = "SELECT * FROM film_category WHERE category_id = ? AND film_id = ?;";
                    PreparedStatement statement = connection.prepareStatement(query);
                    statement.setString(1, Integer.toString(category_id));
                    statement.setString(2, Integer.toString(film_id));

                    ResultSet result1 = statement.executeQuery();

                    if (result1.next()){
                        System.err.println("existe deja");

                    } else {


                        query = "INSERT INTO film_category (category_id, film_id) VALUES (?, ?);";
                        statement = connection.prepareStatement(query);
                        statement.setString(1, Integer.toString(category_id));
                        statement.setString(2, Integer.toString(film_id));

                        int result2 = statement.executeUpdate();

                        if (result2 == 1) {
                            System.out.println("Les valeurs ont été insérée avec succès dans la table category");
                        } else {
                            System.err.println("Erreur: lors de l'insertion de les valeurs dans la table category");
                        }

                        restart = true;

                    }

                } else if (choix1def == 2){
                    System.out.println("Quitter :D");
                    restart = true;
                } else {
                    System.err.println("pas une option");
                }
            }
        }
    }
    public static void afficheFilmCate(Connection connection) throws SQLException{
        Statement stmt= connection.createStatement();

        ResultSet filmCate= stmt.executeQuery("SELECT * FROM film_category");

        System.out.println();
        while(filmCate.next()){
            System.out.println("film_id: "+ filmCate.getString(1));
            System.out.println("category_id: "+ filmCate.getString(2));
            System.out.println("last_update: "+ filmCate.getString(3));
            System.out.println("========================================================================================================");
        }
    }
    public static void updateFilmCate(Connection connection) throws SQLException{
        afficheFilmCate(connection);

        boolean restart = false;
        while (!restart){

            System.out.println("1.modifier une category\n2.quitter");
            Scanner choix1 = new Scanner(System.in);

            if (choix1.hasNextInt()){
                int choix1def = choix1.nextInt();

                if (choix1def == 1){
                    int category_id = 0;
                    int film_id = 0;

                    boolean loop2 = false;
                    while (!loop2){
                        System.out.println("film id:");
                        Scanner newFilmId = new Scanner(System.in);
                        if (newFilmId.hasNextInt()) {
                            film_id = newFilmId.nextInt();
                            loop2 = true;
                        }
                    }

                    boolean loop = false;
                    while(!loop) {
                        System.out.println("category id a changer:");
                        Scanner newCategory = new Scanner(System.in);
                        if (newCategory.hasNextInt()) {
                            category_id = newCategory.nextInt();
                            loop = true;
                        } else {
                            System.err.println("pas un nombre");
                        }
                    }

                    String query = "SELECT * FROM film_category WHERE category_id = ? AND film_id = ?";
                    PreparedStatement statement = connection.prepareStatement(query);
                    statement.setString(1, Integer.toString(category_id));
                    statement.setString(2, Integer.toString(film_id));

                    ResultSet result1 = statement.executeQuery();

                    if (result1.next()){
                        System.err.println("existe deja");

                    } else {


                        query = "UPDATE film_category SET category_id = ? WHERE film_id = ?";
                        statement = connection.prepareStatement(query);
                        statement.setString(1, Integer.toString(category_id));
                        statement.setString(2, Integer.toString(film_id));

                        int result2 = statement.executeUpdate();

                        if (result2 == 1) {
                            System.out.println("Les valeurs ont été insérée avec succès dans la table category");
                        } else {
                            System.err.println("Erreur: lors de l'insertion de les valeurs dans la table category");
                        }

                        restart = true;

                    }

                } else if (choix1def == 2){
                    System.out.println("Quitter :D");
                    restart = true;
                } else {
                    System.err.println("pas une option");
                }
            }
        }
    }
    public static void removeFilmCate(Connection connection) throws SQLException{
        afficheFilmActor(connection);

        boolean newRemoveFilm = false;
        while(!newRemoveFilm) {
            ResultSet result;
            PreparedStatement statement;

            System.out.println("quel actor id voulez vous supprimer ?");
            Scanner removeIdActor = new Scanner(System.in);
            int actor_id = removeIdActor.nextInt();

            System.out.println("quel film id voulez vous supprimer ?");
            Scanner removeIdfilm = new Scanner(System.in);
            int film_id = removeIdfilm.nextInt();

            String sql = "SELECT * FROM film_actor WHERE actor_id = ? AND film_id = ?";
            statement = connection.prepareStatement(sql);
            statement.setString(1, Integer.toString(actor_id));
            statement.setString(2, Integer.toString(film_id));
            result = statement.executeQuery();


            if (!result.next()) {
                System.err.println("le film actor existe pas :P");
                newRemoveFilm = true;

            } else {

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


    //Film List affiche only
    public static void choiceFilmList(Connection connection) throws SQLException {

        System.out.println("1. Afficher les films list\n5. Retour");
        System.out.println("Entrez votre choix :");
        Scanner choice = new Scanner(System.in);

        boolean valide = false;
        while (!valide) {
            if (choice.hasNextInt()) {
                int choix = choice.nextInt();
                if (choix == 5 || choix == 1) {
                    if (choix == 1) {
                        afficheFilmList(connection);
                        System.out.println("1. Afficher les films list\n5. Retour");

                    } else if (choix == 5) {
                        System.out.println("Quitter");
                        valide = true;
                    }
                } else {
                    System.err.println("Erreur: Le nombre choisi ne correspond à aucun choix proposé !!!");
                }

            } else {
                System.err.println("Erreur: Ceci n'est pas un nombre !!!");
            }
        }
    }
    public static void afficheFilmList(Connection connection) throws SQLException{
        Statement stmt= connection.createStatement();

        ResultSet filmList= stmt.executeQuery("SELECT * FROM film_list");

        System.out.println();
        while(filmList.next()){
            System.out.println("FID: "+ filmList.getString(1));
            System.out.println("title: "+ filmList.getString(2));
            System.out.println("description: "+ filmList.getString(3));
            System.out.println("category: "+ filmList.getString(4));
            System.out.println("price: "+ filmList.getString(5));
            System.out.println("length: "+ filmList.getString(6));
            System.out.println("rating: "+ filmList.getString(7));
            System.out.println("actors: "+ filmList.getString(8));

            System.out.println("========================================================================================================");
        }
    }


    //Film text
    public static void choiceFilmText(Connection connection) throws SQLException{
    System.out.println("\n1. Créer un nouveau film text\n2. Afficher les films text\n3. Mettre à jour un film text\n4. Supprimer un film text\n5. Retour");
    System.out.println("Entrez votre choix :");
    Scanner choice = new Scanner(System.in);

    boolean valide = false;
    while(!valide) {
        if (choice.hasNextInt()){
            int choix = choice.nextInt();
            if(choix <= 5 || choix >= 1){
                if(choix == 1){
                    addFilmText(connection);
                    System.out.println("\n1. Créer un nouveau film text\n2. Afficher les films text\n3. Mettre à jour un film text\n4. Supprimer un film text\n5. Retour");

                }else if(choix == 2){
                    afficheFilmText(connection);
                    System.out.println("\n1. Créer un nouveau film text\n2. Afficher les films text\n3. Mettre à jour un film text\n4. Supprimer un film text\n5. Retour");

                }else if(choix == 3){
                    updateFilmText(connection);
                    System.out.println("\n1. Créer un nouveau film text\n2. Afficher les films text\n3. Mettre à jour un film text\n4. Supprimer un film text\n5. Retour");

                }else if(choix == 4){

                    System.out.println("\n1. Créer un nouveau film text\n2. Afficher les films text\n3. Mettre à jour un film text\n4. Supprimer un film text\n5. Retour");

                }else if(choix == 5){
                    System.out.println("Quitter");
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
    public static void addFilmText(Connection connection) throws SQLException{
        afficheFilmText(connection);

        boolean restart = false;
        while (!restart){

            System.out.println("1.ajouter un film text\n2.quitter");
            Scanner choix1 = new Scanner(System.in);

            if (choix1.hasNextInt()){
                int choix1def = choix1.nextInt();

                if (choix1def == 1){
                    int film_id = 0;
                    String title = "";
                    String description = "";

                    boolean loop = false;
                    while(!loop) {
                        System.out.println("film id:");
                        Scanner newFilmId = new Scanner(System.in);
                        if (newFilmId.hasNextInt()) {
                            film_id = newFilmId.nextInt();
                            loop = true;
                        } else {
                            System.err.println("pas un nombre");
                        }
                    }

                    boolean loop2 = false;
                    while (!loop2){
                        System.out.println("title:");
                        Scanner newTitle = new Scanner(System.in);
                        if (newTitle.hasNext()) {
                            title = newTitle.next();
                            loop2 = true;
                        }
                    }

                    boolean loop3 = false;
                    while (!loop3){
                        System.out.println("description:");
                        Scanner newDescription = new Scanner(System.in);
                        if (newDescription.hasNext()) {
                            description = newDescription.next();
                            loop3 = true;
                        }
                    }


                    String query = "SELECT * FROM film_text WHERE film_id = ? AND title = ? AND description = ?;";
                    PreparedStatement statement = connection.prepareStatement(query);
                    statement.setString(1, Integer.toString(film_id));
                    statement.setString(2, title);
                    statement.setString(3, description);

                    ResultSet result1 = statement.executeQuery();

                    if (result1.next()){
                        System.err.println("existe deja");

                    }else {


                        query = "INSERT INTO film_text (film_id, title, description) VALUES (?, ?, ?);";
                        statement = connection.prepareStatement(query);
                        statement.setString(1, Integer.toString(film_id));
                        statement.setString(2, title);
                        statement.setString(3, description);

                        int result2 = statement.executeUpdate();

                        if (result2 == 1) {
                            System.out.println("Les valeurs ont été insérée avec succès dans la table film_text");
                        } else {
                            System.err.println("Erreur: lors de l'insertion de les valeurs dans la table film_text");
                        }

                        restart = true;

                    }

                } else if (choix1def == 2){
                    System.out.println("Quitter :D");
                    restart = true;
                } else {
                    System.err.println("pas une option");
                }
            }
        }
    }
    public static void afficheFilmText(Connection connection) throws SQLException{
        Statement stmt= connection.createStatement();

        ResultSet filmText= stmt.executeQuery("SELECT * FROM film_text");

        System.out.println();
        while(filmText.next()){
            System.out.println("film_id: "+ filmText.getString(1));
            System.out.println("title: "+ filmText.getString(2));
            System.out.println("description: "+ filmText.getString(3));

            System.out.println("========================================================================================================");
        }
    }
    public static void updateFilmText(Connection connection) throws SQLException{
        afficheFilmText(connection);

        boolean restart = false;
        while (!restart){

            System.out.println("1.modifier un film text\n2.quitter");
            Scanner choix1 = new Scanner(System.in);

            if (choix1.hasNextInt()){
                int choix1def = choix1.nextInt();

                if (choix1def == 1){
                    int film_id = 0;
                    String title = "";
                    String description = "";

                    boolean loop = false;
                    while(!loop) {
                        System.out.println("film id:");
                        Scanner newFilmId = new Scanner(System.in);
                        if (newFilmId.hasNextInt()) {
                            film_id = newFilmId.nextInt();
                            loop = true;
                        } else {
                            System.err.println("pas un nombre");
                        }
                    }

                    boolean loop2 = false;
                    while (!loop2){
                        System.out.println("title a changer:");
                        Scanner newTitle = new Scanner(System.in);
                        if (newTitle.hasNext()) {
                            title = newTitle.next();
                            loop2 = true;
                        }
                    }

                    boolean loop3 = false;
                    while (!loop3){
                        System.out.println("description a changer:");
                        Scanner newDescription = new Scanner(System.in);
                        if (newDescription.hasNext()) {
                            description = newDescription.next();
                            loop3 = true;
                        }
                    }


                    String query = "SELECT * FROM film_text WHERE film_id = ? AND title = ? AND description = ?;";
                    PreparedStatement statement = connection.prepareStatement(query);
                    statement.setString(1, Integer.toString(film_id));
                    statement.setString(2, title);
                    statement.setString(3, description);

                    ResultSet result1 = statement.executeQuery();

                    if (result1.next()){
                        System.err.println("existe deja");

                    }else {


                        query = "UPDATE film_text SET title = ?, description = ? WHERE film_id = ?";
                        statement = connection.prepareStatement(query);
                        statement.setString(3, Integer.toString(film_id));
                        statement.setString(1, title);
                        statement.setString(2, description);

                        int result2 = statement.executeUpdate();

                        if (result2 == 1) {
                            System.out.println("Les valeurs ont été insérée avec succès dans la table film_text");
                        } else {
                            System.err.println("Erreur: lors de l'insertion de les valeurs dans la table film_text");
                        }

                        restart = true;

                    }

                } else if (choix1def == 2){
                    System.out.println("Quitter :D");
                    restart = true;
                } else {
                    System.err.println("pas une option");
                }
            }
        }
    }


    //Inventory
    public static void choiceInventory(Connection connection) throws SQLException{
        System.out.println("\n1. Créer un nouveau inventory\n2. Afficher les inventories\n3. Mettre à jour un inventory\n4. Supprimer un inventory\n5. Retour");
        System.out.println("Entrez votre choix :");
        Scanner choice = new Scanner(System.in);

        boolean valide = false;
        while(!valide) {
            if (choice.hasNextInt()){
                int choix = choice.nextInt();
                if(choix <= 5 || choix >= 1){
                    if(choix == 1){
                        addInventory(connection);
                        System.out.println("\n1. Créer un nouveau inventory\n2. Afficher les inventories\n3. Mettre à jour un inventory\n4. Supprimer un inventory\n5. Retour");

                    }else if(choix == 2){
                        afficheInventory(connection);
                        System.out.println("\n1. Créer un nouveau inventory\n2. Afficher les inventories\n3. Mettre à jour un inventory\n4. Supprimer un inventory\n5. Retour");

                    }else if(choix == 3){
                        updateInventory(connection);
                        System.out.println("\n1. Créer un nouveau inventory\n2. Afficher les inventories\n3. Mettre à jour un inventory\n4. Supprimer un inventory\n5. Retour");

                    }else if(choix == 4){

                        System.out.println("\n1. Créer un nouveau inventory\n2. Afficher les inventories\n3. Mettre à jour un inventory\n4. Supprimer un inventory\n5. Retour");

                    }else if(choix == 5){
                        System.out.println("Quitter");
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
    public static void addInventory(Connection connection) throws SQLException{
        afficheInventory(connection);

        boolean restart = false;
        while (!restart){

            System.out.println("1. ajouter un inventory\n2.quitter");
            Scanner choix1 = new Scanner(System.in);

            if (choix1.hasNextInt()){
                int choix1def = choix1.nextInt();

                if (choix1def == 1){
                    int film_id = 0;
                    int store_id = 0;

                    boolean loop = false;
                    while(!loop) {
                        System.out.println("film id:");
                        Scanner newFilmId = new Scanner(System.in);
                        if (newFilmId.hasNextInt()) {
                            film_id = newFilmId.nextInt();
                            loop = true;
                        } else {
                            System.err.println("pas un nombre");
                        }
                    }

                    boolean loop2 = false;
                    while (!loop2){
                        System.out.println("store id:");
                        Scanner newStoreId = new Scanner(System.in);
                        if (newStoreId.hasNextInt()) {
                            store_id = newStoreId.nextInt();
                            loop2 = true;
                        } else {
                            System.err.println("pas un nombre");
                        }
                    }

                    String query = "SELECT * FROM inventory WHERE film_id = ? AND store_id = ?;";
                    PreparedStatement statement = connection.prepareStatement(query);
                    statement.setString(1, Integer.toString(film_id));
                    statement.setString(2, Integer.toString(store_id));

                    ResultSet result1 = statement.executeQuery();

                    if (result1.next()){
                        System.err.println("existe deja");
                    } else {


                        query = "INSERT INTO inventory (film_id, store_id) VALUES (?, ?);";
                        statement = connection.prepareStatement(query);
                        statement.setString(1, Integer.toString(film_id));
                        statement.setString(2, Integer.toString(store_id));

                        int result2 = statement.executeUpdate();

                        if (result2 == 1) {
                            System.out.println("Les valeurs ont été insérée avec succès dans la table inventory");
                        } else {
                            System.err.println("Erreur: lors de l'insertion de les valeurs dans la table inventory");
                        }

                        restart = true;
                    }

                } else if (choix1def == 2){
                    restart = true;
                    System.out.println("Quitter :D");

                } else {
                    System.err.println("pas une option");
                }
            }
        }
    }
    public static void afficheInventory(Connection connection) throws SQLException{
        Statement stmt= connection.createStatement();

        ResultSet inventory= stmt.executeQuery("SELECT * FROM inventory");

        System.out.println();
        while(inventory.next()){
            System.out.println("inventory_id: "+ inventory.getString(1));
            System.out.println("film_id: "+ inventory.getString(2));
            System.out.println("store_id: "+ inventory.getString(3));
            System.out.println("last_update: "+ inventory.getString(4));

            System.out.println("========================================================================================================");
        }
    }
    public static void updateInventory(Connection connection) throws SQLException{
        afficheInventory(connection);

        boolean restart = false;
        while (!restart){

            System.out.println("1. modifier un inventory\n2.quitter");
            Scanner choix1 = new Scanner(System.in);

            if (choix1.hasNextInt()){
                int choix1def = choix1.nextInt();

                if (choix1def == 1){
                    int inventory_id = 0;
                    int film_id = 0;
                    int store_id = 0;

                    boolean loop = false;
                    while(!loop) {
                        System.out.println("inventory id:");
                        Scanner newInvId = new Scanner(System.in);
                        if (newInvId.hasNextInt()) {
                            inventory_id = newInvId.nextInt();
                            loop = true;
                        } else {
                            System.err.println("pas un nombre");
                        }
                    }

                    boolean loop2 = false;
                    while(!loop2) {
                        System.out.println("film id a changer:");
                        Scanner newFilmId = new Scanner(System.in);
                        if (newFilmId.hasNextInt()) {
                            film_id = newFilmId.nextInt();
                            loop2 = true;
                        } else {
                            System.err.println("pas un nombre");
                        }
                    }

                    boolean loop3 = false;
                    while (!loop3){
                        System.out.println("store id a changer:");
                        Scanner newStoreId = new Scanner(System.in);
                        if (newStoreId.hasNextInt()) {
                            store_id = newStoreId.nextInt();
                            loop3 = true;
                        } else {
                            System.err.println("pas un nombre");
                        }
                    }

                    String query = "SELECT * FROM inventory WHERE film_id = ? AND store_id = ? AND inventory_id = ?";
                    PreparedStatement statement = connection.prepareStatement(query);
                    statement.setString(1, Integer.toString(film_id));
                    statement.setString(2, Integer.toString(store_id));
                    statement.setString(3, Integer.toString(inventory_id));

                    ResultSet result1 = statement.executeQuery();

                    if (result1.next()){
                        System.err.println("existe deja");
                    } else {


                        query = "UPDATE inventory SET film_id = ?, store_id = ? WHERE inventory_id = ?";
                        statement = connection.prepareStatement(query);
                        statement.setString(1, Integer.toString(film_id));
                        statement.setString(2, Integer.toString(store_id));
                        statement.setString(3, Integer.toString(inventory_id));
                        int result2 = statement.executeUpdate();

                        if (result2 == 1) {
                            System.out.println("Les valeurs ont été insérée avec succès dans la table inventory");
                        } else {
                            System.err.println("Erreur: lors de l'insertion de les valeurs dans la table inventory");
                        }

                        restart = true;
                    }

                } else if (choix1def == 2){
                    restart = true;
                    System.out.println("Quitter :D");

                } else {
                    System.err.println("pas une option");
                }
            }
        }
    }


    //Language
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
                        addLanguage(connection);
                        System.out.println("\n1. Créer un nouveau language\n2. Afficher les languages\n3. Mettre à jour un language\n4. Supprimer un language\n5. Retour");

                    }else if(choix == 2){
                        afficheLanguage(connection);
                        System.out.println("\n1. Créer un nouveau language\n2. Afficher les languages\n3. Mettre à jour un language\n4. Supprimer un language\n5. Retour");

                    }else if(choix == 3){
                        updateLanguage(connection);
                        System.out.println("\n1. Créer un nouveau language\n2. Afficher les languages\n3. Mettre à jour un language\n4. Supprimer un language\n5. Retour");

                    }else if(choix == 4){

                        System.out.println("\n1. Créer un nouveau language\n2. Afficher les languages\n3. Mettre à jour un language\n4. Supprimer un language\n5. Retour");

                    }else if(choix == 5){
                        System.out.println("Quitter");
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
    public static void addLanguage(Connection connection) throws SQLException{
        afficheLanguage(connection);

        boolean restart = false;
        while (!restart){

            System.out.println("1. ajouter un language\n2.quitter");
            Scanner choix1 = new Scanner(System.in);

            if (choix1.hasNextInt()){
                int choix1def = choix1.nextInt();

                if (choix1def == 1){
                    String name = null;


                    boolean loop = false;
                    while(!loop) {
                        System.out.println("language:");
                        Scanner newName = new Scanner(System.in);
                        if (newName.hasNext()) {
                            name = newName.next();
                            loop = true;
                        }
                    }

                    String query = "SELECT * FROM language WHERE name = ?;";
                    PreparedStatement statement = connection.prepareStatement(query);
                    statement.setString(1, name);

                    ResultSet result1 = statement.executeQuery();

                    if (result1.next()){
                        System.err.println("existe deja");

                    } else {


                        query = "INSERT INTO language (name) VALUE (?);";
                        statement = connection.prepareStatement(query);
                        statement.setString(1, name);

                        int result2 = statement.executeUpdate();

                        if (result2 == 1) {
                            System.out.println("Les valeurs ont été insérée avec succès dans la table language");
                        } else {
                            System.err.println("Erreur: lors de l'insertion de les valeurs dans la table language");
                        }

                        restart = true;
                    }


                } else if (choix1def == 2){
                    System.out.println("Quitter :D");
                    restart = true;

                } else {
                    System.err.println("pas une option");
                }
            }
        }
    }
    public static void afficheLanguage(Connection connection) throws SQLException{
        Statement stmt= connection.createStatement();

        ResultSet language= stmt.executeQuery("SELECT * FROM language");

        System.out.println();
        while(language.next()){
            System.out.println("language_id: "+ language.getString(1));
            System.out.println("name: "+ language.getString(2));
            System.out.println("last_update: "+ language.getString(3));

            System.out.println("========================================================================================================");
        }
    }
    public static void updateLanguage(Connection connection) throws SQLException{
        afficheLanguage(connection);

        boolean restart = false;
        while (!restart){

            System.out.println("1.modifier un language\n2.quitter");
            Scanner choix1 = new Scanner(System.in);

            if (choix1.hasNextInt()){
                int choix1def = choix1.nextInt();

                if (choix1def == 1){
                    int language_id = 0;
                    String name = null;

                    boolean loop2 = false;
                    while(!loop2) {
                        System.out.println("language id:");
                        Scanner newName = new Scanner(System.in);
                        if (newName.hasNextInt()) {
                            language_id = newName.nextInt();
                            loop2 = true;
                        }
                    }

                    boolean loop = false;
                    while(!loop) {
                        System.out.println("name a changer:");
                        Scanner newName = new Scanner(System.in);
                        if (newName.hasNext()) {
                            name = newName.next();
                            loop = true;
                        }
                    }

                    String query = "SELECT * FROM language WHERE name = ? AND language_id = ?";
                    PreparedStatement statement = connection.prepareStatement(query);
                    statement.setString(1, name);
                    statement.setString(2, Integer.toString(language_id));


                    ResultSet result1 = statement.executeQuery();

                    if (result1.next()){
                        System.err.println("existe deja");

                    } else {


                        query = "UPDATE language SET name = ? WHERE language_id = ?";
                        statement = connection.prepareStatement(query);
                        statement.setString(1, name);
                        statement.setString(2, Integer.toString(language_id));

                        int result2 = statement.executeUpdate();

                        if (result2 == 1) {
                            System.out.println("Les valeurs ont été insérée avec succès dans la table language");
                        } else {
                            System.err.println("Erreur: lors de l'insertion de les valeurs dans la table language");
                        }

                        restart = true;
                    }


                } else if (choix1def == 2){
                    System.out.println("Quitter :D");
                    restart = true;

                } else {
                    System.err.println("pas une option");
                }
            }
        }
    }


    //Nicer film affiche only
    public static void choiceNicerFilm(Connection connection) throws SQLException {

        System.out.println("1. Afficher les films nicer\n5. Retour");
        System.out.println("Entrez votre choix :");
        Scanner choice = new Scanner(System.in);

        boolean valide = false;
        while (!valide) {
            if (choice.hasNextInt()) {
                int choix = choice.nextInt();
                if (choix == 5 || choix == 1) {
                    if (choix == 1) {
                        afficheNicerFilm(connection);
                        System.out.println("1. Afficher les films nicer\n5. Retour");

                    } else if (choix == 5) {
                        System.out.println("Quitter");
                        valide = true;
                    }
                } else {
                    System.err.println("Erreur: Le nombre choisi ne correspond à aucun choix proposé !!!");
                }

            } else {
                System.err.println("Erreur: Ceci n'est pas un nombre !!!");
            }
        }
    }
    public static void afficheNicerFilm(Connection connection) throws SQLException{
        Statement stmt= connection.createStatement();

        ResultSet NicerFilm= stmt.executeQuery("SELECT * FROM nicer_but_slower_film_list");

        System.out.println();
        while(NicerFilm.next()){
            System.out.println("FID: "+ NicerFilm.getString(1));
            System.out.println("title: "+ NicerFilm.getString(2));
            System.out.println("description: "+ NicerFilm.getString(3));
            System.out.println("category: "+ NicerFilm.getString(4));
            System.out.println("price: "+ NicerFilm.getString(5));
            System.out.println("length: "+ NicerFilm.getString(6));
            System.out.println("rating: "+ NicerFilm.getString(7));
            System.out.println("actors: "+ NicerFilm.getString(8));

            System.out.println("========================================================================================================");
        }
    }

}