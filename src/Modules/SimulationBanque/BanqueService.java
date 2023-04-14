package src.Modules.SimulationBanque;

import java.util.Arrays;
import java.util.LinkedList;

import src.Utils.Menu;
import src.Utils.StringUtils;

public class BanqueService {

    /**
     * Ajoute une banque dans une instance de la micro-appli appBanque
     * 
     * @param appBanque
     */
    public static void ajouterBanque(SimulationBanque appBanque) {
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

    /**
     * Permet d'ajouter un client à une banque précise choisie par l'utilisateur à
     * l'éxécution de la méthode
     * 
     * @param appBanque l'instance de l'application Banque dans laquelle on veut
     *                  ajouter le client
     */
    public static void ajouterClient(SimulationBanque appBanque) {

        Banque banque;
        if (appBanque.banques.isEmpty()) {
            System.out.print("Il n'y a pas encore de banque. Créez une banque");
            return;
        } else {
            banque = choixBanque(appBanque.banques);
        }
        LinkedList<String> options = new LinkedList<String>();

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

        banque.clients.add(nouveauClient);

    }

    public static boolean serializeAllDataToJson(SimulationBanque appBanque) {
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

    public static void demo(SimulationBanque appBanque) {
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

    public static void afficherListeClients(LinkedList<Banque> banques) {
        System.out.println(choixBanque(banques).getClients().toString());
    }

    public static void creditDebit(LinkedList<Banque> banques, int creditDebit) {
        Compte compte = choixCompte(choixBanque(banques));
        switch (creditDebit) {
            case 1:
                compte.crediter(StringUtils.demanderEntierUtilisateur("un montant à créditer", true));
                return;
            case 2:
                compte.debiter(StringUtils.demanderEntierUtilisateur("un montant à débiter", true));
                return;
        }
    }

    /**
     * 
     * @param banques
     * @return la banque choisie par l'utilisateur de la banque dans la liste de
     *         banques passée en paramètre
     */
    public static Banque choixBanque(LinkedList<Banque> banques) {
        LinkedList<String> options = new LinkedList<String>();
        for (Banque banque : banques) {
            options.add(banque.getNom());
        }
        Menu menu = new Menu("Choisissez la banque :", options);
        System.out.print(menu.toString());
        return banques.get(menu.choix() - 1);

    }

    public static Client choixClient(Banque banque) {
        LinkedList<String> options = new LinkedList<>();
        for (Client client : banque.clients) {
            options.add(client.toString());
        }
        Menu choixClient = new Menu("Choisissez un client : ", options);
        System.out.println(choixClient);
        return banque.clients.get(choixClient.choix() - 1);
    }

    public static Compte choixCompte(Banque banque) {
        LinkedList<String> options = new LinkedList<>();
        for (Compte compte : banque.comptes) {
            options.add(compte.toString());
        }
        Menu choixCcomtpe = new Menu("Choisissez un compte : ", options);
        return banque.comptes.get(choixCcomtpe.choix() - 1);
    }

    public static void ajouterCompte(LinkedList<Banque> banques) {
        Banque banque = choixBanque(banques);
        banque.comptes.add(new Compte(choixClient(banque)));
        banque.comptes.getLast().crediter(StringUtils.demanderEntierUtilisateur("le versement initial", true));
        System.out.println("Le client a bien été ajouté");
    }
}
