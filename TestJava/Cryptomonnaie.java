package TestJava;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import TestJava.Connex;

public class Cryptomonnaie {
    private Integer idCryptomonnaie;
    private String nom;
    private String symbole;
    private Date dateCreation;

    // Getters et Setters
    public Integer getIdCryptomonnaie() {
        return idCryptomonnaie;
    }

    public void setIdCryptomonnaie(Integer idCryptomonnaie) {
        this.idCryptomonnaie = idCryptomonnaie;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getSymbole() {
        return symbole;
    }

    public void setSymbole(String symbole) {
        this.symbole = symbole;
    }

    public Date getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(Date dateCreation) {
        this.dateCreation = dateCreation;
    }

    // Méthodes CRUD
    public static void insert(Cryptomonnaie crypto) {
        String query = "INSERT INTO cryptomonnaie (nom, symbole, date_creation) VALUES (?, ?, ?)";
        try (Connection connection = Connex.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, crypto.getNom());
            statement.setString(2, crypto.getSymbole());
            statement.setDate(3, crypto.getDateCreation());
            statement.executeUpdate();
            System.out.println("Cryptomonnaie insérée avec succès.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static List<Cryptomonnaie> getAll() {
        List<Cryptomonnaie> cryptos = new ArrayList<>();
        String query = "SELECT * FROM cryptomonnaie";
        try (Connection connection = Connex.getConnection();
             PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                Cryptomonnaie crypto = new Cryptomonnaie();
                crypto.setIdCryptomonnaie(resultSet.getInt("id_cryptomonnaie"));
                crypto.setNom(resultSet.getString("nom"));
                crypto.setSymbole(resultSet.getString("symbole"));
                crypto.setDateCreation(resultSet.getDate("date_creation"));
                cryptos.add(crypto);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cryptos;
    }

    public static void update(Cryptomonnaie crypto) {
        String query = "UPDATE cryptomonnaie SET nom = ?, symbole = ?, date_creation = ? WHERE id_cryptomonnaie = ?";
        try (Connection connection = Connex.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, crypto.getNom());
            statement.setString(2, crypto.getSymbole());
            statement.setDate(3, crypto.getDateCreation());
            statement.setInt(4, crypto.getIdCryptomonnaie());
            statement.executeUpdate();
            System.out.println("Cryptomonnaie mise à jour avec succès.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void delete(int id) {
        String query = "DELETE FROM cryptomonnaie WHERE id_cryptomonnaie = ?";
        try (Connection connection = Connex.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);
            statement.executeUpdate();
            System.out.println("Cryptomonnaie supprimée avec succès.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static int getCountCryptomonnaies() {
        String query = "SELECT COUNT(DISTINCT nom) AS nombre_noms_distincts FROM cryptomonnaie";
        int count = 0;

        try (Connection connection = Connex.getConnection();
            PreparedStatement statement = connection.prepareStatement(query)) {
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                count = resultSet.getInt(1); // Récupère le résultat du COUNT(*)
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return count;
    }
}
