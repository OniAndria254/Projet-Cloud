package TestJava;
import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class HistoriqueCours {
    private Integer idHistoriqueCours;
    private BigDecimal prix;
    private Date dateEnregistrement;
    private Integer idCryptomonnaie;

    // Getters et Setters
    public Integer getIdHistoriqueCours() {
        return idHistoriqueCours;
    }

    public void setIdHistoriqueCours(Integer idHistoriqueCours) {
        this.idHistoriqueCours = idHistoriqueCours;
    }

    public BigDecimal getPrix() {
        return prix;
    }

    public void setPrix(BigDecimal prix) {
        this.prix = prix;
    }

    public Date getDateEnregistrement() {
        return dateEnregistrement;
    }

    public void setDateEnregistrement(Date dateEnregistrement) {
        this.dateEnregistrement = dateEnregistrement;
    }

    public Integer getIdCryptomonnaie() {
        return idCryptomonnaie;
    }

    public void setIdCryptomonnaie(Integer idCryptomonnaie) {
        this.idCryptomonnaie = idCryptomonnaie;
    }

    // Méthodes CRUD
    public static void insert(HistoriqueCours historique) {
        String query = "INSERT INTO historique_cours (prix, date_enregistrement, id_cryptomonnaie) VALUES (?, ?, ?)";
        try (Connection connection = Connex.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setBigDecimal(1, historique.getPrix());
            statement.setDate(2, historique.getDateEnregistrement());
            statement.setInt(3, historique.getIdCryptomonnaie());
            statement.executeUpdate();
            System.out.println("Historique ajouté avec succès.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static List<HistoriqueCours> getAll() {
        List<HistoriqueCours> historiques = new ArrayList<>();
        String query = "SELECT * FROM historique_cours";
        try (Connection connection = Connex.getConnection();
             PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                HistoriqueCours historique = new HistoriqueCours();
                historique.setIdHistoriqueCours(resultSet.getInt("id_historique_cours"));
                historique.setPrix(resultSet.getBigDecimal("prix"));
                historique.setDateEnregistrement(resultSet.getDate("date_enregistrement"));
                historique.setIdCryptomonnaie(resultSet.getInt("id_cryptomonnaie"));
                historiques.add(historique);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return historiques;
    }

    public static void delete(int id) {
        String query = "DELETE FROM historique_cours WHERE id_historique_cours = ?";
        try (Connection connection = Connex.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);
            statement.executeUpdate();
            System.out.println("Historique supprimé avec succès.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
