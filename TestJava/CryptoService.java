package TestJava;

import java.math.BigDecimal;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class CryptoService {
    private final Random random = new Random();
    private Connection connection; // Connexion unique pour la classe

    // Constructeur pour initialiser la connexion
    public CryptoService() {
        try {
            this.connection = Connex.getConnection(); // Connexion unique initialisée
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Fermer la connexion proprement
    public void closeConnection() {
        if (this.connection != null) {
            try {
                this.connection.close();
                System.out.println("Connexion fermée.");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void insert10secondes() {
        List<Cryptomonnaie> lc = getAllCryptomonnaies();

        for (Cryptomonnaie crypto : lc) {
            BigDecimal dernierPrix = getDernierPrixCrypto(crypto.getIdCryptomonnaie());

            BigDecimal nouveauPrix;
            if (dernierPrix != null) {
                double pourcentage = random.nextDouble() * (20 - 2) + 2; // Entre 2% et 20%
                boolean isPositif = random.nextBoolean();

                if (isPositif) {
                    nouveauPrix = dernierPrix.add(dernierPrix.multiply(BigDecimal.valueOf(pourcentage / 100)));
                } else {
                    nouveauPrix = dernierPrix.subtract(dernierPrix.multiply(BigDecimal.valueOf(pourcentage / 100)));
                }
            } else {
                nouveauPrix = BigDecimal.valueOf(100);
            }

            HistoriqueCours historique = new HistoriqueCours();
            historique.setIdCryptomonnaie(crypto.getIdCryptomonnaie());
            historique.setPrix(nouveauPrix);
            historique.setDateEnregistrement(Date.valueOf(LocalDateTime.now().toLocalDate()));

            insertHistorique(historique);
        }
    }

    private BigDecimal getDernierPrixCrypto(Integer idCryptomonnaie) {
        String query = "SELECT prix FROM historique_cours WHERE id_cryptomonnaie = ? ORDER BY id_historique_cours DESC LIMIT 1";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, idCryptomonnaie);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getBigDecimal("prix");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null; // Aucun historique pour cette cryptomonnaie
    }

    private List<Cryptomonnaie> getAllCryptomonnaies() {
        List<Cryptomonnaie> cryptos = new ArrayList<>();
        String query = "SELECT * FROM cryptomonnaie";
        try (PreparedStatement statement = connection.prepareStatement(query);
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

    private void insertHistorique(HistoriqueCours historique) {
        String query = "INSERT INTO historique_cours (prix, date_enregistrement, id_cryptomonnaie) VALUES (?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setBigDecimal(1, historique.getPrix());
            statement.setDate(2, historique.getDateEnregistrement());
            statement.setInt(3, historique.getIdCryptomonnaie());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<HistoriqueCours> getDerniersHistoriques() {
        List<HistoriqueCours> historiques = new ArrayList<>();
        String query = "SELECT * FROM historique_cours ORDER BY date_enregistrement DESC, id_historique_cours DESC LIMIT ?";
    
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            int n = Cryptomonnaie.getCountCryptomonnaies();
            statement.setInt(1, n); // On fixe le nombre de résultats à retourner
            ResultSet resultSet = statement.executeQuery();
    
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

    public List<HistoriqueCours> graph() throws Exception{
        List<HistoriqueCours> derniersHistoriques = null;
        try{
            insert10secondes();
            derniersHistoriques = getDerniersHistoriques();
        }catch(Exception e){e.printStackTrace();}
        return derniersHistoriques;    
    }
    
}
