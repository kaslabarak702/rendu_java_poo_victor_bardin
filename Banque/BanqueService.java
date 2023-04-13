package Banque;

import java.util.Arrays;
import java.util.LinkedList;

import Utils.Menu;
import Utils.StringUtils;

public class BanqueService {

    /**
     * Ajoute une banque dans une instance de la micro-appli appBanque
     * 
     * @param appBanque
     */
    public static void ajouterBanque(AppBanque appBanque) {
        appBanque.banques.add(new Banque(StringUtils.demanderTexteUtilisateur("un nom de banque", true)));
    }

    /**
     * Affiche la liste des banques
     * 
     * @param banques la liste des banques à afficher
     */
    public static void afficherListeBanques(LinkedList<Banque> banques) {
        if (banques.size() == 0) {
            System.out.println("Il n'y a pas encore de banque à afficher");
        } else {
            for (Banque banque : banques) {
                System.out.println(banque.toString());
            }
        }
    }

    public static void ajouterClient(AppBanque appBanque) {
        if (appBanque.banques.isEmpty()) {
            System.out.print("Il n'y a pas encore de banque. Créez une banque");
            return;
        }
        LinkedList<String> options = new LinkedList<String>();
        for (Banque banque : appBanque.banques) {
            options.add(banque.getNom());
        }
        Menu choixBanque = new Menu("Choisisez la banque à laquelle vous voulez assigner un nouveau client : ",
                options);
        System.out.print(choixBanque);
        int banque = choixBanque.choix() - 1;

        options = new LinkedList<String>();
        options.add("Particulier");
        options.add("Entreprise");
        Menu choixTypeClient = new Menu("Choisissez le type de client",
                options);
        System.out.println(choixTypeClient.toString());
        int client = choixTypeClient.choix();
        Client nouveauClient = null;

        switch (client) {
            case 1:
                nouveauClient = new ClientParticulier(
                        StringUtils.demanderTexteUtilisateur("un nom", true),
                        StringUtils.demanderTexteUtilisateur("un prénom", true),
                        StringUtils.demanderEntierUtilisateur("un age", true),
                        StringUtils.demanderTexteUtilisateur("une adresse", true),
                        StringUtils.demanderTexteUtilisateur("un email", true),
                        StringUtils.demanderTexteUtilisateur("une profession", true),
                        StringUtils.demanderEntierUtilisateur("un salaire", true));
                break;
            case 2:
                nouveauClient = new ClientEntreprise(
                        StringUtils.demanderTexteUtilisateur("le nom de l'entreprise", true),
                        StringUtils.demanderTexteUtilisateur("l'adresse de l'entreprise", true),
                        StringUtils.demanderEntierUtilisateur("le numéro de l'entreprise", true),
                        StringUtils.demanderTexteUtilisateur("le secteur d'activité", true),
                        StringUtils.demanderTexteUtilisateur("le mail de l'entreprise", true));
                break;
        }

        appBanque.banques.get(banque).clients.add(nouveauClient);

    }

    public static boolean serializeAllDataToJsonCSV(AppBanque appBanque) {
        System.out.println("Début de l'extraction json -> csv...");
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = 0; i < appBanque.banques.size(); i++) {
            Banque banque = appBanque.banques.get(i);
            sb.append("{")
                    .append("\"banque\": \"").append(banque.getNom()).append("\",")
                    .append("\"clients\": [");
            for (int j = 0; j < banque.getClients().size(); j++) {
                sb.append(banque.getClients().get(j).toJSON(false));
                if (j < banque.getClients().size() - 1) {
                    sb.append(",");
                }
            }
            sb.append("],")
                    .append("\"comptes\": [");
            for (int j = 0; j < appBanque.banques.get(i).getComptes().size(); j++) {
                sb.append(appBanque.banques.get(i).getComptes().get(j).toJSON());
                if (j < appBanque.banques.get(i).getComptes().size() - 1) {
                    sb.append(",");
                }
            }
            sb.append("]}");
            if (i < appBanque.banques.size() - 1) {
                sb.append(",");
            }
        }
        sb.append("]");
        System.out.print("\nEnregistrement... ");
        StringUtils.sauvegarderTexte(sb.toString(),
                System.getProperty("user.home") + "/Downloads/donnesjson.json");

        return true;
    }

    public static void demo(AppBanque appBanque) {
        System.out.print("\nComptes de démonstration en cours de déploiment...");
        Banque banque1 = new Banque("Bnp Paribas");
        Banque banque2 = new Banque("Crédit du nord");

        Client client1 = new ClientParticulier("Dupont", "Jean", 25, "1 rue des fleurs", "dupont@gmail.com",
                "ingénieur", 3000);
        Client client2 = new ClientParticulier("Martin", "Sophie", 33, "25 avenue des étoiles",
                "sophie.martin@hotmail.com", "architecte", 4000);

        Client client3 = new ClientEntreprise("Google", "1600 Amphitheatre Parkway", 0, "it", "contact@google.com");
        Client client4 = new ClientEntreprise("Amazon", "410 Terry Ave. North", 0, "commerce", "contact@amazon.com");

        banque1.ajouterClient(client1);
        banque1.ajouterClient(client2);
        banque2.ajouterClient(client3);
        banque2.ajouterClient(client4);

        banque1.ouvrirCompte(client1, 1000);
        banque1.ouvrirCompte(client2, 5000);
        banque2.ouvrirCompte(client3, 2000);
        banque2.ouvrirCompte(client4, 0);

        appBanque.banques.addAll(Arrays.asList(banque1, banque2));
        System.out.print(" done.\n");

    }

}
