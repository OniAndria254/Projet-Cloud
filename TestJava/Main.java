// package TestJava;
// import java.util.concurrent.Executors;
// import java.util.concurrent.ScheduledExecutorService;
// import java.util.concurrent.TimeUnit;

// public class Main {
//     public static void main(String[] args) {
//         CryptoService cryptoService = new CryptoService();

//         // Crée un ScheduledExecutorService avec un seul thread
//         ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

//         // Planifie l'exécution périodique de insert10secondes toutes les 10 secondes
//         scheduler.scheduleAtFixedRate(() -> {
//             try {
//                 System.out.println("Insertion des prix...");
//                 cryptoService.insert10secondes();
//                 System.out.println("Opération terminée.");
//             } catch (Exception e) {
//                 e.printStackTrace();
//             }
//         }, 0, 10, TimeUnit.SECONDS);

//         // Garder le programme en vie (simulation d'un serveur en cours d'exécution)
//         try {
//             Thread.sleep(60000); // Arrête le programme après 1 minute pour l'exemple
//         } catch (InterruptedException e) {
//             e.printStackTrace();
//         }

//         // Arrêter le scheduler proprement
//         scheduler.shutdown();
//         System.out.println("Programme terminé.");
//     }
// }


package TestJava;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) {
        CryptoService cryptoService = new CryptoService();

        // Crée un ScheduledExecutorService avec un seul thread
        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

        // Planifie l'exécution périodique de insert10secondes toutes les 10 secondes
        scheduler.scheduleAtFixedRate(() -> {
            try {
                // Récupère le nombre de cryptomonnaies
                // Récupère les derniers historiques de cours
                List<HistoriqueCours> derniersHistoriques = cryptoService.graph();

                // Affiche les derniers historiques
                System.out.println("Derniers historiques de cours :");
                for (HistoriqueCours historique : derniersHistoriques) {
                    System.out.println("ID: " + historique.getIdHistoriqueCours() +
                            ", Cryptomonnaie ID: " + historique.getIdCryptomonnaie() +
                            ", Prix: " + historique.getPrix() +
                            ", Date: " + historique.getDateEnregistrement());
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }, 0, 10, TimeUnit.SECONDS);

        // Garder le programme en vie (simulation d'un serveur en cours d'exécution)
        try {
            Thread.sleep(60000); // Arrête le programme après 1 minute pour l'exemple
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Arrêter le scheduler proprement
        scheduler.shutdown();
        System.out.println("Programme terminé.");
    }
}

