package com.codingf;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class Update {

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

}
