import java.util.Arrays;
import java.util.LinkedList;
import java.util.Random;

import Banque.AppBanque;
import EditeurTexte.Word;
import Utils.Menu;
import Utils.StringUtils;

/**
 * littleTools est une classe de mini outils. Chaque outil est un module, les
 * modules ne s'éxécutent qu'un par un mais peuvent faire appel les uns aux
 * autres. Pour exécuter un module, choisissez le dans le menu, à l'éxécution.
 * 
 * @author Victor BARDIN
 * @version 1.4
 */
public class littleTools {
    private static boolean gameOn = true; // conditionne l'arrêt du programme

    public static void main(String[] args) {
        startMenu(gameOn);
    }

    /**
     * Menu du programme little tools
     */
    private static void startMenu(boolean gameOn) {
        if (!gameOn) {
            System.out.println("Merci d'avoir joué, bonne journée");
            return;
        }

        // affichage du menu
        Menu menu = new Menu("Bonjour ! Que souhaitez-vous faire ?", new LinkedList<>());
        menu.options.addAll(Arrays.asList(
                " Renverser une chaîne de caractères",
                " Afficher une pyramide",
                " Calculer la somme des nombres de 1 à x",
                " Vérifier si un nombre est pair, impair ou premier",
                " Jouer au juste nombre (trouver le nombre choisi par l'ordinateur)",
                " Compter le nombre de mots dans une phrase",
                " Sauvegarder une phrase dans un fichier",
                " Lire du texte d'un fichier pour y compter les mots",
                " Faire une simulation de banque",
                "Ouvrir un éditeur de texte"));

        System.out.println(menu.toString());

        int choix = -1;// le choix utilisateur dans le menu
        while (choix < 1 || choix > menu.options.size()) {
            choix = StringUtils.demanderEntierUtilisateur("une option");
        }

        switch (choix) {
            case 1:
                StringUtils.renverserChaineCaracteres();
                finModule();
                return;
            case 2:
                afficherPyramide();
                return;
            case 3:
                calculerUneSomme();
                return;
            case 4:
                pairImpairPremier();
                return;
            case 5:
                nombreJuste();
                return;
            case 6:
                StringUtils.compterMotsPhraseModule();
                finModule();
                return;
            case 7:
                StringUtils.sauvegarderTexte();
                finModule();
                return;
            case 8:
                StringUtils.analyseTexte();
                finModule();
                return;
            case 9:
                lancerAppBanque();
                return;
            case 10:
                Word.launchWord();
                finModule();
                return;

            default:
                System.out.println("Option non implémentée");
                finModule();
                return;
        }

    }

    private static void lancerAppBanque() {
        AppBanque appBanque = new AppBanque();
        finModule();
    }

    /**
     * implémentation du jeu "nombre juste"
     */
    private static void nombreJuste() {
        int tentative = 0; // le nombre de tentatives effectives
        int nombreEssais = StringUtils.demanderEntierUtilisateur("le nombre d'essais"); // le nombre de tentatives max
        boolean trouve = false;

        // choix de la borne max
        int max = StringUtils.demanderEntierUtilisateur("la difficulté");
        int nombreAleatoire = (int) (Math.random() * max);

        // début du jeu
        System.out.println("Bienvenue dans Complète Nombre Juste! Vous avez " + nombreEssais + " essais");
        System.out.println("Le nombre à deviner est compris entre 0 et " + max);
        System.out.println("Vous avez " + nombreEssais + " tentatives pour le deviner.");

        while (tentative < nombreEssais && !trouve) {
            System.out.print("Tentative n°" + (tentative + 1) + ": ");
            int nombreDevine = StringUtils.demanderEntierUtilisateur("un nombre");

            if (nombreDevine == nombreAleatoire) {
                System.out.println("Félicitations, vous avez trouvé le nombre!");
                trouve = true;
            } else if (nombreDevine > nombreAleatoire) {
                System.out.println("Le nombre est plus petit que " + nombreDevine);
            } else {
                System.out.println("Le nombre est plus grand que " + nombreDevine);
            }
            tentative++;
        }

        if (!trouve)
            System.out.println(
                    "Désolé, vous n'avez pas deviné le nombre en moins de " + nombreEssais + ". Le nombre était "
                            + nombreAleatoire);
        finModule();
    }

    /**
     * Affiche si un nombre entré par l'utilisateur est premier, pair ou impair
     *
     * @see demanderEntierUtilisateur
     */
    private static void pairImpairPremier() {
        int nombre = StringUtils.demanderEntierUtilisateur("un nombre");
        if (nombre % 2 == 0) {
            System.out.println(nombre + " est pair"); // aucun nombre pair n'est premier
        } else {
            System.out.println(nombre + " est impair");

            // un fois qu'on sait que le nombre est impair, on teste s'il est premier
            if (nombre < 2) {
                System.out.println(nombre + " n'est pas premier");
            } else {
                boolean estPremier = true;
                double sqrt = Math.sqrt(nombre); // la racine du nombre teste, au dela de laquelle vérifier s'il est
                                                 // divisible par un entier est inutile
                for (int i = 2; i <= sqrt; i++) {
                    if (nombre % i == 0) {
                        estPremier = false;
                        break;
                    }
                }
                if (estPremier) {
                    System.out.println(nombre + " est premier");
                } else {
                    System.out.println(nombre + " n'est pas premier");
                }
            }
        }
        finModule();
    }

    /**
     * Calcule une somme de 1 à un chiffre rentré par l'utilisateur
     */
    private static void calculerUneSomme() {
        int borneHaute = StringUtils.demanderEntierUtilisateur("la borne haute");
        int somme = borneHaute * (borneHaute + 1) / 2; // la somme des premiers entiers
        System.out.println("La somme des nombres de 1 à " + borneHaute + " est : " + somme); // résultat
        finModule();
    }

    /**
     * Affiche une pyramide d'une hauteur choisie par l'utilisateur
     */
    private static void afficherPyramide() {
        int hauteur = 1; // hauteur de la pyramide en nombre de lignes
        double probaGuirlande = 0.33; // probabilité qu'une guirlande apparaisse
        Random r = new Random(); // on génère aléatoirement des caractères
        System.out.println("Quelle hauteur de pyramide souhaitez vous?");
        hauteur = StringUtils.demanderEntierUtilisateur("une hauteur valide") + 1;
        for (int i = 0; i < hauteur; i++) {
            for (int j = 0; j < (hauteur - i) - 1; j++) {
                System.out.print(" ");
            }
            if (Math.random() > probaGuirlande) {
                for (int k = 1; k <= 2 * i - 1; k++) {
                    System.out.print((char) (r.nextInt(26) + 'a'));
                }
            } else {
                for (int k = 1; k <= 2 * i - 1; k++) {
                    System.out.print("*+");
                }
            }
            System.out.println();
        }
        System.out.println();

        finModule();
    }

    /**
     * factorise deux fonctions, appelé très souvent à chaque fin de jeu
     */
    private static void finModule() {
        continueGame(); // fin du jeu?
        startMenu(gameOn);// retour au menu, le cas échéant
    }

    /**
     * détermine si le jeu continue ou non selon le choix de l'utilisateur ->
     * termine l'éxécution
     */
    public static void continueGame() {
        String choix = StringUtils.demanderTexteUtilisateur("vous de revenir au menu?");
        if (choix.equalsIgnoreCase("")
                || choix.equalsIgnoreCase("o")
                || choix.equalsIgnoreCase("oui")) {
            gameOn = true;
        } else {
            gameOn = false;// fin du jeu
        }
    }
}