package src.Modules.SimulationBanque;

import java.util.Arrays;
import java.util.LinkedList;

import src.Utils.Menu;
import src.Utils.StringUtils;

/**
 * 
 * Classe SimulationBanque, responsable de la simulation de gestion de banques.
 * Elle
 * permet à l'utilisateur de créer une nouvelle banque,
 * d'ajouter des clients, de consulter la liste des clients et des banques,
 * d'extraire toutes les données en JSON, de peupler
 * l'environnement de fausses données à des fins de démonstration et de créditer
 * ou débiter un compte.
 * 
 * Elle s'éxécute comme un module de la classe mainToolBox.java
 */
public class SimulationBanque {

    /** conditionne l'arrêt du jeu par l'utilisateur */
    boolean gameOn;

    /** le menu du module SimulationBanque */
    Menu menuPrincipal;
    LinkedList<Banque> banques = new LinkedList<Banque>();
    private LinkedList<String> optionsMenuPrincipal;

    /**
     * Constructeur de la classe AppBanque, affiche le menu et attend une entrée
     * utilisateur
     */
    public SimulationBanque() {
        this.gameOn = true;
        this.menuPrincipal = new Menu("Bienvenue dans cette micro-simulation de gestion de banques :",
                new LinkedList<>());
        this.optionsMenuPrincipal = new LinkedList<String>();
        this.optionsMenuPrincipal.addAll(Arrays.asList("créer une banque",
                "créer un client",
                "créer un compte",
                "consulter la liste des clients d'une banque",
                "consulter la liste des banques",
                "extraire toutes les données en json",
                "peupler l'environnement de fausses données à des fins de démonstration",
                "créditer ou débiter un compte",
                "revenir au menu principal"));
        menuPrincipal.options = this.optionsMenuPrincipal;
        menuBanque(gameOn);

    }

    /**
     * crée le menu de l'option "lancer une simuation de banque"
     * 
     * @param gameOn conditionne l'arret ou non de la simulation
     */
    private void menuBanque(boolean gameOn) {
        if (!gameOn)
            return;
        System.out.print(menuPrincipal);
        switch (menuPrincipal.choix()) {
            case 1:
                BanqueService.ajouterBanque(this);
                finModule();
                return;
            case 2:
                BanqueService.ajouterClient(this);
                finModule();
                return;
            case 3:
                BanqueService.ajouterCompte(this.banques);
                finModule();
                return;
            case 4:
                BanqueService.afficherListeClients(this.banques);
                finModule();
                return;
            case 5:
                BanqueService.afficherListeBanques(this.banques);
                finModule();
                return;
            case 6:
                BanqueService.serializeAllDataToJson(this);
                finModule();
                return;
            case 7:
                BanqueService.demo(this);
                finModule();
                return;
            case 8:
                /*
                 * deux options en une, on détermine ce que l'utilisateur veut faire avant de
                 * lancer la fonction
                 */

                // 1 pour crédit 2 pour débit, -1 par défaut
                int creditDebit = -1;

                while (creditDebit != 1 && creditDebit != 2) {
                    creditDebit = StringUtils.demanderEntierUtilisateur(
                            "Voulez vous créditer (1) ou débiter (2) le compte? ",
                            false);
                }
                BanqueService.creditDebit(this.banques,
                        creditDebit);
                finModule();
                return;
            case 9:
                menuBanque(false);
                return;
            default:
                System.out.println("cette fonction n'est pas implémentée");
                return;
        }

    }

    /**
     * Appelé très souvent, sert à ralentir le défilement console et rediriger
     * l'utilisateur vers le menu ou terminer le programme proprement
     */
    private void finModule() {
        String choix = StringUtils
                .demanderTexteUtilisateur("\nappuyez sur entrer pour continuer, tapez exit pour sortir", false);
        if (choix.equalsIgnoreCase("exit")) {
            gameOn = false;
        }
        menuPrincipal = new Menu("opération effectuée, que voulez vous faire mainteant?", optionsMenuPrincipal);
        menuBanque(gameOn);// retour au menu, le cas échéant
    }

    /**
     * explicite, lance la simulation de banque
     */
    public static void lancerSimulationBanque() {
        new SimulationBanque();
    }

}