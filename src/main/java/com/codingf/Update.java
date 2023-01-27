package com.codingf;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Update {

    public static void updateActor(Connection connection){
        try {
            // Demande à l'utilisateur les informations nécessaires
            Scanner scanner = new Scanner(System.in);
            int actorId = 0;
            boolean isValidId = false;
            while (!isValidId) {
                System.out.print("Entrez l'ID de l'acteur à mettre à jour : ");
                actorId = scanner.nextInt();
                // Vérifie si l'ID de l'acteur existe dans la table
                String sql = "SELECT COUNT(*) FROM actor WHERE actor_id = ?";
                PreparedStatement statement = connection.prepareStatement(sql);
                statement.setInt(1, actorId);
                ResultSet result = statement.executeQuery();
                if (result.next() && result.getInt(1) == 1) {
                    isValidId = true;
                } else {
                    System.out.println("ID de l'acteur invalide, veuillez réessayer");
                }
            }
            System.out.print("Entrez le nouveau prénom de l'acteur : ");
            String firstName = scanner.next();
            System.out.print("Entrez le nouveau nom de l'acteur : ");
            String lastName = scanner.next();

            // Vérifie la validité des entrées de l'utilisateur
            if (firstName.length() > 45) {
                System.out.println("Le prénom est trop long (45 caractères maximum)");
                return;
            }
            if (lastName.length() > 45) {
                System.out.println("Le nom est trop long (45 caractères maximum)");
                return;
            }

            // Prépare la requête SQL
            String sql = "UPDATE actor SET first_name = ?, last_name = ? WHERE actor_id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, firstName);
            statement.setString(2, lastName);
            statement.setInt(3, actorId);

            // Exécute la requête SQL
            statement.executeUpdate();
            System.out.println("Acteur mis à jour avec succès");
        } catch (SQLException e) {
            System.out.println("Erreur lors de la mise à jour de l'acteur : " + e.getMessage());
        }
    }
    public static void updateAddress(Connection connection){
        try {
            // Demande à l'utilisateur les informations nécessaires
            Scanner scanner = new Scanner(System.in);
            int addressId = 0;
            boolean isValidId = false;
            while (!isValidId) {
                System.out.print("Entrez l'ID de l'adresse à mettre à jour : ");
                addressId = scanner.nextInt();
                // Vérifie si l'ID de l'adresse existe dans la table
                String sql = "SELECT COUNT(*) FROM address WHERE address_id = ?";
                PreparedStatement statement = connection.prepareStatement(sql);
                statement.setInt(1, addressId);
                ResultSet result = statement.executeQuery();
                if (result.next() && result.getInt(1) == 1) {
                    isValidId = true;
                } else {
                    System.out.println("ID de l'adresse invalide, veuillez réessayer");
                }
            }
            System.out.print("Entrez la nouvelle adresse : ");
            scanner.nextLine();
            String address = scanner.nextLine();
            System.out.print("Entrez la nouvelle adresse (ligne 2): ");
            String address2 = scanner.nextLine();
            System.out.print("Entrez le nouveau district : ");
            String district = scanner.nextLine();
            int cityId = 0;
            boolean isValidCityId = false;
            while (!isValidCityId) {
                System.out.print("Entrez l'ID de la ville : ");
                cityId = scanner.nextInt();
                // Vérifie si l'ID de la ville existe dans la table
                String sql = "SELECT COUNT(*) FROM city WHERE city_id = ?";
                PreparedStatement statement = connection.prepareStatement(sql);
                statement.setInt(1, cityId);
                ResultSet result = statement.executeQuery();
                if (result.next() && result.getInt(1) == 1) {
                    isValidCityId = true;
                } else {
                    System.out.println("ID de la ville invalide, veuillez réessayer");
                }
            }
            System.out.print("Entrez le nouveau code postal : ");
            scanner.nextLine();
            String postalCode = scanner.nextLine();
            System.out.print("Entrez le nouveau numéro de téléphone : ");
            String phone = scanner.nextLine();
            System.out.print("Entrez la nouvelle localisation, exemple : POINT(12.34 56.78): ");
            String location = scanner.nextLine();
            // Vérifie la longueur des entrées pour les champs adresse, adresse 2, district, code postal et numéro de téléphone
            if (address.length() > 50) {
                System.out.println("L'adresse saisie est trop longue (50 caractères maximum)");
                return;
            }
            if (address2.length() > 50) {
                System.out.println("L'adresse (ligne 2) saisie est trop longue (50 caractères maximum)");
                return;
            }
            if (district.length() > 20) {
                System.out.println("Le district saisi est trop long (20 caractères maximum)");
                return;
            }
            if (postalCode.length() > 10) {
                System.out.println("Le code postal saisi est trop long (10 caractères maximum)");
                return;
            }
            if (phone.length() > 20) {
                System.out.println("Le numéro de téléphone saisi est trop long (20 caractères maximum)");
                return;
            }
            if (!location.matches("^POINT\\(-?\\d+\\.\\d+ -?\\d+\\.\\d+\\)$")) {
                System.out.println("Format de localisation non valide. Veuillez entrer une localisation valide au format POINT(x y) où x et y sont des coordonnées géographiques.");
                return;
            }
            // Exécute la mise à jour de l'adresse dans la table
            String sql = "UPDATE address SET address = ?, address2 = ?, district = ?, city_id = ?, postal_code = ?, phone = ?, location = ST_GeomFromText(?) WHERE address_id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, address);
            statement.setString(2, address2);
            statement.setString(3, district);
            statement.setInt(4, cityId);
            statement.setString(5, postalCode);
            statement.setString(6, phone);
            statement.setString(7, location);
            statement.setInt(8, addressId);
            statement.executeUpdate();
            System.out.println("Adresse mise à jour avec succès");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void updateCategory(Connection connection){
        Scanner scanner = new Scanner(System.in);

        try {
            System.out.print("Entrez l'ID de la catégorie à mettre à jour : ");
            int categoryId = scanner.nextInt();
            scanner.nextLine();

            // Vérifie si l'ID de la catégorie existe dans la table
            String sql = "SELECT COUNT(*) FROM category WHERE category_id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, categoryId);
            ResultSet result = statement.executeQuery();
            if (!result.next() || result.getInt(1) != 1) {
                System.out.println("ID de catégorie non valide, veuillez réessayer.");
                return;
            }

            System.out.print("Entrez le nouveau nom de la catégorie : ");
            String name = scanner.nextLine();

            // Mise à jour de la catégorie dans la table
            String querry = "UPDATE category SET name = ? WHERE category_id = ?";
            statement = connection.prepareStatement(querry);
            statement.setString(1, name);
            statement.setInt(2, categoryId);
            statement.executeUpdate();
            System.out.println("Catégorie mise à jour avec succès");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void updateCity(Connection connection){
        Scanner scanner = new Scanner(System.in);

        try {
            System.out.print("Entrez l'ID de la ville à mettre à jour : ");
            int cityId = scanner.nextInt();
            scanner.nextLine();

            // Vérifie si l'ID de la ville existe dans la table
            String sql = "SELECT COUNT(*) FROM city WHERE city_id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, cityId);
            ResultSet result = statement.executeQuery();
            if (!result.next() || result.getInt(1) != 1) {
                System.out.println("ID de ville non valide, veuillez réessayer.");
                return;
            }

            System.out.print("Entrez le nouveau nom de la ville : ");
            String city = scanner.nextLine();

            System.out.print("Entrez l'ID du pays associé à cette ville : ");
            int countryId = scanner.nextInt();
            scanner.nextLine();

            // Vérifie si l'ID du pays existe dans la table
            sql = "SELECT COUNT(*) FROM country WHERE country_id = ?";
            statement = connection.prepareStatement(sql);
            statement.setInt(1, countryId);
            result = statement.executeQuery();
            if (!result.next() || result.getInt(1) != 1) {
                System.out.println("ID de pays non valide, veuillez réessayer.");
                return;
            }

            //Mise à jour de la ville dans la table
            String querry = "UPDATE city SET city = ?, country_id = ? WHERE city_id = ?";
            statement = connection.prepareStatement(querry);
            statement.setString(1, city);
            statement.setInt(2, countryId);
            statement.setInt(3, cityId);
            statement.executeUpdate();
            System.out.println("Ville mise à jour avec succès");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void updateCountry(Connection connection) throws SQLException {
        boolean newCountryValide = false;
        while(!newCountryValide) {
            Affiche.afficheCountry(connection);

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

    public static void updateCustomer(Connection connection){
        Scanner scanner = new Scanner(System.in);
        try {
            System.out.print("Entrez l'ID du client à mettre à jour : ");
            int customerId = scanner.nextInt();
            scanner.nextLine();

            // Vérifie si l'ID du client existe dans la table
            String sql = "SELECT COUNT(*) FROM customer WHERE customer_id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, customerId);
            ResultSet result = statement.executeQuery();
            if (!result.next() || result.getInt(1) != 1) {
                System.out.println("ID de client non valide, veuillez réessayer.");
                return;
            }

            System.out.print("Entrez le nouveau prénom du client : ");
            String firstName = scanner.nextLine();

            System.out.print("Entrez le nouveau nom de famille du client : ");
            String lastName = scanner.nextLine();

            System.out.print("Entrez le nouvel email du client : ");
            String email = scanner.nextLine();
            System.out.print("Entrez l'ID de l'adresse associée à ce client : ");
            int addressId = scanner.nextInt();
            scanner.nextLine();

            // Vérifie si l'ID de l'adresse existe dans la table
            sql = "SELECT COUNT(*) FROM address WHERE address_id = ?";
            statement = connection.prepareStatement(sql);
            statement.setInt(1, addressId);
            result = statement.executeQuery();
            if (!result.next() || result.getInt(1) != 1) {
                System.out.println("ID d'adresse non valide, veuillez réessayer.");
                return;
            }

            System.out.print("Entrez l'ID de la boutique associée à ce client : ");
            int storeId = scanner.nextInt();
            scanner.nextLine();

            // Vérifie si l'ID de la boutique existe dans la table
            sql = "SELECT COUNT(*) FROM store WHERE store_id = ?";
            statement = connection.prepareStatement(sql);
            statement.setInt(1, storeId);
            result = statement.executeQuery();
            if (!result.next() || result.getInt(1) != 1) {
                System.out.println("ID de boutique non valide, veuillez réessayer.");
                return;
            }

            // Update customer information
            String querry = "UPDATE customer SET first_name = ?, last_name = ?, email = ?, address_id = ?, store_id = ? WHERE customer_id = ?";
            statement = connection.prepareStatement(querry);
            statement.setString(1, firstName);
            statement.setString(2, lastName);
            statement.setString(3, email);
            statement.setInt(4, addressId);
            statement.setInt(5, storeId);
            statement.setInt(6, customerId);
            int rowsAffected = statement.executeUpdate();

            if (rowsAffected == 1) {
                System.out.println("Mise à jour du client effectuée avec succès.");
            } else {
                System.out.println("La mise à jour du client a échoué, veuillez réessayer.");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

    }

    public static void updateFilm(Connection connection) throws SQLException {
        Affiche.afficheFilm(connection);
        boolean newUpdateFilm = false;

        //permet de demander a l'utilisateur si il veut modifier ou quitter
        while (!newUpdateFilm) {
            System.out.println("1. modifier le film\n2.quitter");
            System.out.println("Entrez votre choix :");

            Scanner picks = new Scanner(System.in);

            if (picks.hasNextInt()) {
                int pick = picks.nextInt();

                //si l'utilisateur souhait modifier sinon quitter
                if (pick == 1){

                    //demande l'id du film souhaiter pour modifier
                    System.out.println("id du film: ");
                    Scanner newIdFilm = new Scanner(System.in);

                    if (newIdFilm.hasNextInt()) {
                        int film_id = newIdFilm.nextInt();

                        //l'utilisateur aura plusieurs choix en dependant de ce qu'il veut modifier
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


                            //execute les requetes de sql
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

    public static void updateFilmActor(Connection connection) throws SQLException{
        Affiche.afficheFilmActor(connection);
        boolean newUpdateFilm = false;
        while (!newUpdateFilm) {
            //demande si il veut modifier ou quitter
            System.out.println("1. modifier le film id et actor\n2.quitter");
            System.out.println("Entrez votre choix :");

            Scanner picks = new Scanner(System.in);

            if (picks.hasNextInt()) {
                int pick = picks.nextInt();

                if (pick == 1){

                    //demande l'id d'un actor
                    System.out.println("id de actor: ");
                    Scanner newIdActor = new Scanner(System.in);

                    if (newIdActor.hasNextInt()) {
                        int actor_id = newIdActor.nextInt();

                        boolean loop = false;
                        while (!loop) {

                            //deamnde l'id d'un film
                            System.out.println("id du film a changer: ");
                            Scanner newIdFilm = new Scanner(System.in);

                            if (newIdFilm.hasNextInt()) {
                                int film_id = newIdFilm.nextInt();

                                //check si il existe
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

                                    //si il existe alors on execute le sql
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

    public static void updateFilmCate(Connection connection) throws SQLException{
        Affiche.afficheFilmCate(connection);

        boolean restart = false;
        while (!restart){

            //demande si il veut modifier ou quitter
            System.out.println("1.modifier une category\n2.quitter");
            Scanner choix1 = new Scanner(System.in);

            if (choix1.hasNextInt()){
                int choix1def = choix1.nextInt();

                if (choix1def == 1){
                    int category_id = 0;
                    int film_id = 0;

                    boolean loop2 = false;
                    while (!loop2){
                        //demande l'id du film
                        System.out.println("film id:");
                        Scanner newFilmId = new Scanner(System.in);
                        if (newFilmId.hasNextInt()) {
                            film_id = newFilmId.nextInt();
                            loop2 = true;
                        }
                    }

                    boolean loop = false;
                    while(!loop) {
                        //demande l'id du category
                        System.out.println("category id a changer:");
                        Scanner newCategory = new Scanner(System.in);
                        if (newCategory.hasNextInt()) {
                            category_id = newCategory.nextInt();
                            loop = true;
                        } else {
                            System.err.println("pas un nombre");
                        }
                    }

                    //check si il existe
                    String query = "SELECT * FROM film_category WHERE category_id = ? AND film_id = ?";
                    PreparedStatement statement = connection.prepareStatement(query);
                    statement.setString(1, Integer.toString(category_id));
                    statement.setString(2, Integer.toString(film_id));

                    ResultSet result1 = statement.executeQuery();

                    if (result1.next()){
                        System.err.println("existe deja");

                    } else {

                        //si il existe alors on modifie
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

    public static void updateFilmText(Connection connection) throws SQLException{
        Affiche.afficheFilmText(connection);

        boolean restart = false;
        while (!restart){

            //demande si il veut modifier ou quitter
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

                    //check si il existe
                    String query = "SELECT * FROM film_text WHERE film_id = ? AND title = ? AND description = ?;";
                    PreparedStatement statement = connection.prepareStatement(query);
                    statement.setString(1, Integer.toString(film_id));
                    statement.setString(2, title);
                    statement.setString(3, description);

                    ResultSet result1 = statement.executeQuery();

                    if (result1.next()){
                        System.err.println("existe deja");

                    }else {

                        //si il exite alors on modifie
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

    public static void updateInventory(Connection connection) throws SQLException{
        Affiche.afficheInventory(connection);

        boolean restart = false;
        while (!restart){

            //demande si il veut modifier ou quitter
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

                    //check si il existe
                    String query = "SELECT * FROM inventory WHERE film_id = ? AND store_id = ? AND inventory_id = ?";
                    PreparedStatement statement = connection.prepareStatement(query);
                    statement.setString(1, Integer.toString(film_id));
                    statement.setString(2, Integer.toString(store_id));
                    statement.setString(3, Integer.toString(inventory_id));

                    ResultSet result1 = statement.executeQuery();

                    if (result1.next()){
                        System.err.println("existe deja");
                    } else {

                        //si il existe alors on modifie
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

    public static void updateLanguage(Connection connection) throws SQLException{
        Affiche.afficheLanguage(connection);

        boolean restart = false;
        while (!restart){

            //demande si il veut modifier ou quitter
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

                    //check si il existe
                    String query = "SELECT * FROM language WHERE name = ? AND language_id = ?";
                    PreparedStatement statement = connection.prepareStatement(query);
                    statement.setString(1, name);
                    statement.setString(2, Integer.toString(language_id));


                    ResultSet result1 = statement.executeQuery();

                    if (result1.next()){
                        System.err.println("existe deja");

                    } else {

                        //si il existe on modfie
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

    public static void updatePayment(Connection connection) throws SQLException{
        Affiche.affichePayment(connection);
        Scanner scanner = new Scanner(System.in);

        // Choisir l'id de la table à modifier
        try {
            System.out.print("Entrez l'ID du paiement à mettre à jour : ");
            int categoryId = scanner.nextInt();
            scanner.nextLine();

            // Vérifie si l'id de la catégorie existe dans la table
            String sql = "SELECT COUNT(*) FROM payment WHERE payment_id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, categoryId);
            ResultSet result = statement.executeQuery();
            if (!result.next() || result.getInt(1) != 1) {
                System.out.println("L'id du paiement est non valide, veuillez réessayer.");
                return;
            }

            // Demander à l'utilisateur les informations nécessaires
            System.out.print("Entrez le nouvel id du client : ");
            int customerId = scanner.nextInt();
            System.out.print("Entrez le nouvel id de la personne du staff s'occupant du client : ");
            int staffId = scanner.nextInt();
            System.out.print("Entrez le nouvel id de la location : ");
            int rentalId = scanner.nextInt();
            System.out.println("Entrez la nouvelle quantité d'argent déversé : ");
            float amount = scanner.nextFloat();



            // Mise à jour de la catégorie dans la table
            String querry = "UPDATE payment SET customer_id = ? AND staff_id = ? AND rental_id = ? AND amount = ? WHERE payment_id = ?";
            statement = connection.prepareStatement(querry);
            statement.setInt(1, customerId);
            statement.setInt(2, staffId);
            statement.setInt(3, rentalId);
            statement.setFloat(4, amount);

            statement.executeUpdate();
            System.out.println("Le tableau Payment à été mis à jour avec succès");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void updateRental(Connection connection) throws SQLException{
        Affiche.afficheRental(connection);
        Scanner scanner = new Scanner(System.in);

        try {
            System.out.print("Entrez l'ID de la location à mettre à jour : ");
            int categoryId = scanner.nextInt();
            scanner.nextLine();

            // Vérifie si l'ID de la catégorie existe dans la table
            String sql = "SELECT COUNT(*) FROM rental WHERE rental_id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, categoryId);
            ResultSet result = statement.executeQuery();
            if (!result.next() || result.getInt(1) != 1) {
                System.out.println("L'id de la location est non valide, veuillez réessayer.");
                return;
            }

            System.out.print("Entrez le nouvel id de l'inventaire : ");
            int inventoryId = scanner.nextInt();
            System.out.print("Entrez le nouvel id du client : ");
            int customerId = scanner.nextInt();
            System.out.print("Entrez le nouvel id de la personne du staff s'occupant du client : ");
            int staffId = scanner.nextInt();


            // Mise à jour de la catégorie dans la table
            String querry = "UPDATE rental SET inventory_id = ? AND customer_id = ? AND staff_id = ? WHERE rental_id = ?";
            statement = connection.prepareStatement(querry);
            statement.setInt(1, inventoryId);
            statement.setInt(2, customerId);
            statement.setInt(3, staffId);
            statement.executeUpdate();
            System.out.println("Le tableau Rental à été mis à jour avec succès");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void updateStaff(Connection connection) throws SQLException{
        Affiche.afficheStaff(connection);
        Scanner scanner = new Scanner(System.in);

        try {
            System.out.print("Entrez l'ID de la personne de l'équipe à mettre à jour : ");
            int categoryId = scanner.nextInt();
            scanner.nextLine();

            // Vérifie si l'ID de la catégorie existe dans la table
            String sql = "SELECT COUNT(*) FROM staff WHERE staff_id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, categoryId);
            ResultSet result = statement.executeQuery();
            if (!result.next() || result.getInt(1) != 1) {
                System.out.println("L'id de la personne est non valide, veuillez réessayer.");
                return;
            }

            System.out.print("Entrez le nouveau prénom de la personne : ");
            String firstName = scanner.nextLine();
            System.out.print("Entrez son nouveau nom : ");
            String lastName = scanner.nextLine();
            System.out.print("Entrez le nouvel id de son adresse : ");
            int addressId = scanner.nextInt();
            System.out.print("Entrez son nouvel email : ");
            String email = scanner.nextLine();
            System.out.print("Entrez le nouvel id de son magasin : ");
            int storeId = scanner.nextInt();
            System.out.print("Entrez son nouveau pseudonyme : ");
            String username = scanner.nextLine();
            System.out.print("Entrez son nouveau mot de passe : ");
            String password = scanner.nextLine();

            // Mise à jour de la catégorie dans la table
            String querry = "UPDATE staff SET first_name = ? AND last_name = ? AND address_id = ? AND email = ? AND store_id = ? AND username = ? AND password = ? WHERE staff_id = ?";
            statement = connection.prepareStatement(querry);
            statement.setString(1, firstName);
            statement.setString(2, lastName);
            statement.setInt(3, addressId);
            statement.setString(4, email);
            statement.setInt(5, storeId);
            statement.setString(6, username);
            statement.setString(7, password);
            statement.executeUpdate();
            System.out.println("Le tableau Staff à été mis à jour avec succès");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void updateStore(Connection connection) throws SQLException {
        Affiche.afficheStore(connection);
        Scanner scanner = new Scanner(System.in);

        try {
            System.out.print("Entrez l'ID du magasin à mettre à jour : ");
            int categoryId = scanner.nextInt();
            scanner.nextLine();

            // Vérifie si l'ID de la catégorie existe dans la table
            String sql = "SELECT COUNT(*) FROM store WHERE store_id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, categoryId);
            ResultSet result = statement.executeQuery();
            if (!result.next() || result.getInt(1) != 1) {
                System.out.println("L'id du magasin est non valide, veuillez réessayer.");
                return;
            }

            System.out.print("Entrez le nouvel id de l'équipe de management : ");
            int managerStaffId = scanner.nextInt();
            System.out.print("Entrez le nouvel id de l'adresse : ");
            int addressId = scanner.nextInt();


            // Mise à jour de la catégorie dans la table
            String querry = "UPDATE store SET manager_staff_id = ? AND address_id = ? WHERE store_id = ?";
            statement = connection.prepareStatement(querry);
            statement.setInt(1, managerStaffId);
            statement.setInt(2, addressId);
            statement.setInt(3, categoryId);
            statement.executeUpdate();
            System.out.println("Le tableau Store à été mis à jour avec succès");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
