import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Random;
import java.util.Scanner;

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
    final static int NOMBRE_OPTIONS_MENU = 8;

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
        StringBuilder menu = new StringBuilder();
        menu.append("\n")
                .append("Bonjour ! Que souhaitez-vous faire ?\n")

                .append("1 -> Renverser une chaîne de caractères\n")
                .append("2 -> Afficher une pyramide\n")
                .append("3 -> Calculer la somme des nombres de 1 à x\n")
                .append("4 -> Vérifier si un nombre est pair, impair ou premier\n")
                .append("5 -> Jouer au juste nombre (trouver le nombre choisi par l'ordinateur)\n")
                .append("6 -> Compter le nombre de mots dans une phrase\n")
                .append("7 -> Sauvegarder une phrase dans un fichier\n")
                .append("8 -> Lire du texte d'un fichier pour y compter les mots\n")

                .append("Entrez le chiffre correspondant à votre choix entre 1 et " + NOMBRE_OPTIONS_MENU + ":");

        System.out.println(menu.toString());
        int choix = -1;// le choix utilisateur dans le menu
        while (choix < 1 || choix > NOMBRE_OPTIONS_MENU) {
            choix = demanderEntierUtilisateur("une option");
        }

        switch (choix) {
            case 1:
                renverserChaineCaracteres();
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
                compterMotsPhraseModule();
                return;
            case 7:
                sauvegarderTexte();
                return;
            case 8:
                analyseTexte();
                return;
            default:
                System.out.println("Choisissez un nombre valide");
                startMenu(true);
                return;
        }

    }

    /**
     * Ouvre un fichier dont le chemin est précisé par un input console, l'ouvre et
     * lit son contenu, utilise compterMotsPhraseModule pour alanyser le string
     * qu'est le
     * texte, gestion des exceptions incluse
     * 
     * @see compterMotsPhraseModule
     */
    private static void analyseTexte() {
        Scanner scanner = new Scanner(System.in);

        // Demande à l'utilisateur le chemin du fichier à ouvrir
        System.out.println("Entrez le chemin du fichier à analyser :");
        String cheminFichier = scanner.nextLine();

        try {
            // Ouvre le fichier
            File fichier = new File(cheminFichier);
            BufferedReader lecteur = new BufferedReader(new InputStreamReader(new FileInputStream(fichier)));

            // Lit le contenu du fichier et stocke le texte dans une chaîne de caractères
            // via un StringBuilder
            StringBuilder texte = new StringBuilder();
            String ligne = lecteur.readLine();
            while (ligne != null) {
                texte.append(ligne);
                ligne = lecteur.readLine();
            }
            lecteur.close();

            // Analyse le texte en comptant les mots
            compterLesMots(texte.toString());

        } catch (FileNotFoundException e) {
            System.out.println("Fichier introuvable ");
        } catch (IOException e) {
            System.out.println("Erreur de lecture de fichier");
        }
    }

    /**
     * Sauvegarde une entrée de texte utilisateur dans un fichier dans le dossier
     * téléchargements de l'utillisateur
     */
    private static void sauvegarderTexte() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Entrez le texte à sauvegarder : ");
        sauvegarderTexte(scanner.nextLine(), System.getProperty("user.home") + "/Downloads/texte_sauvegarde.txt");
        finModule();
    }

    /**
     * Sauvegarde une entrée de texte utilisateur dans un fichier dans le dossier
     * téléchargements de l'utilisateur
     * 
     * @param texte  le texte à sauvegarder
     * @param chemin le chemin du fichier dans lequel texte est sauvegardé
     */
    private static void sauvegarderTexte(String texte, String chemin) {
        try {
            FileWriter writer = new FileWriter(chemin);
            writer.write(texte);
            writer.close();
            System.out.println("Le texte a été sauvegardé dans le fichier : " + chemin);
        } catch (IOException e) {
            System.out.println("Une erreur est survenue lors de la sauvegarde du texte : " + e.getMessage());
        }
    }

    /**
     * comte le nombre de mots dans une phrase rentrée dans la console
     */
    private static void compterMotsPhraseModule() {
        Scanner sc = new Scanner(System.in);
        System.out.println("\nEntrez le texte dont vous voulez connaitre le nombre de mots : ");
        // on passe par un tableau de string (on délimite les mots comme des chaines
        // séparées par des apostrophes et des espaces)
        compterLesMots(sc.nextLine());
        finModule();
    }

    /**
     * comte le nombre de mots dans une phrase en paramètre
     * 
     * @param texte analysé
     */
    private static void compterLesMots(String texte) {
        System.out.println("Ce texte contient " + texte.split("[\\s']+").length + " mots\n");
    }

    /**
     * implémentation du jeu "nombre juste"
     */
    private static void nombreJuste() {
        int tentative = 0; // le nombre de tentatives effectives
        int nombreEssais = demanderEntierUtilisateur("le nombre d'essais"); // le nombre de tentatives max
        boolean found = false;
        System.out.println("Bienvenue dans Complète Nombre Juste! Vous avez " + nombreEssais + " essais");

        // choix de la borne max
        int max = demanderEntierUtilisateur("la difficulté");
        int nombreAleatoire = (int) (Math.random() * max);

        // début du jeu
        System.out.println("Le nombre à deviner est compris entre 0 et " + max + ".");
        System.out.println("Vous avez " + nombreEssais + " tentatives pour le deviner.");

        while (tentative < nombreEssais && !found) {
            System.out.print("Tentative n°" + (tentative + 1) + ": ");
            int nombreDevine = demanderEntierUtilisateur("un nombre");

            if (nombreDevine == nombreAleatoire) {
                System.out.println("Félicitations, vous avez deviné le nombre!");
                found = true;
            } else if (nombreDevine > nombreAleatoire) {
                System.out.println("Le nombre est plus petit que " + nombreDevine + ".");
            } else {
                System.out.println("Le nombre est plus grand que " + nombreDevine + ".");
            }
            tentative++;
        }

        System.out.println("Désolé, vous n'avez pas deviné le nombre en moins de " + nombreEssais + ". Le nombre était "
                + nombreAleatoire + ".");
        finModule();
    }

    /**
     * Affiche si un nombre entré par l'utilisateur est premier, pair ou impair
     *
     * @see demanderEntierUtilisateur
     */
    private static void pairImpairPremier() {
        int nombre = demanderEntierUtilisateur("un nombre");
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
     * Calcule une somme de 1 à ?
     */
    private static void calculerUneSomme() {
        int borneHaute = demanderEntierUtilisateur("la borne haute");
        int somme = borneHaute * (borneHaute + 1) / 2; // la somme des premiers entiers
        System.out.println("La somme des nombres de 1 à " + borneHaute + " est : " + somme); // résultat
        finModule();
    }

    /**
     * demande un nombre entier a l'utilisateur, vérifie que l'input correspond bien
     * à un entier positif, et redemande un input jusqu'au succès
     * 
     * @see demanderEntierUtilisateur
     * 
     * @param titreValeur la valeur a demander (ex "la hauteur")
     * @return l'entier vérifié
     */
    public static int demanderEntierUtilisateur(String titreValeur) {
        System.out.print("\nChoisissez " + titreValeur + ", valeur entière et positive : ");
        boolean validNumber = false;
        Scanner scanner = new Scanner(System.in);
        int number = -1;

        while (!validNumber && number < 0) {
            try {
                number = Integer.parseInt(scanner.next());
                validNumber = true;
            } catch (Exception e) {
                System.out.println("Entrez un nombre entier valide : "); // gestion des erreurs utilisateur
            }
        }
        return number;
    }

    /**
     * Affiche une pyramide d'une hauteur choisie par l'utilisateur
     */
    private static void afficherPyramide() {
        int hauteur = 1; // hauteur de la pyramide en nombre de lignes
        Random r = new Random(); // on génère aléatoirement des caractères
        System.out.println("Quelle hauteur de pyramide souhaitez vous?");
        hauteur = demanderEntierUtilisateur("une hauteur valide") + 1;
        for (int i = 0; i < hauteur; i++) {
            for (int j = 0; j < (hauteur - i) - 1; j++) {
                System.out.print(" ");
            }
            for (int k = 1; k <= 2 * i - 1; k++) {
                System.out.print((char) (r.nextInt(26) + 'a'));
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
        System.out.println("Continuer? Oui pour retourner au menu, une autre chaine pour terminer le programme");
        Scanner scanner = new Scanner(System.in);
        if (!"oui".equalsIgnoreCase(scanner.next())) {
            gameOn = false; // fin du jeu
        }
    }

    /**
     * Affiche une chaine de caracteres que l'utilisateur rentre, retournee
     */
    private static void renverserChaineCaracteres() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\nRentrez la chaîne de caracteres à retourner (pas d'accent): ");
        String toReverse = scanner.nextLine(); // on lit l'entrée utilisateur
        System.out.println("Voici votre chaîne(" + toReverse + ") retournee : "
                + new StringBuilder(toReverse).reverse().toString() + "\n");
        finModule();
    }
}