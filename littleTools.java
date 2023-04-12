import java.util.Random;
import java.util.Scanner;

/**
 * littleTools est une classe de mini jeux, executez le programme et laissez
 * vous guider
 * 
 * @author Victor BARDIN
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
            return;
        }
        System.out.println("\n"
                + "Bonjour ! Que souhaitez vous faire ?\n\n"
                + "1 -> renverser une chaîne de caracteres\n"
                + "2 -> Afficher une pyramide\n"
                + "3 -> Calculer la somme des nombres de 1 a x\n"
                + "4 -> Verifier si un nombre est pair, impair ou premier\n"
                + "5 -> Jouer au juste nombre (trouvez le nombre choisi par l'ordinateur)\n"
                + "Entrez le chiffre correspondant a votre choix");

        Scanner scanner = new Scanner(System.in); // Scanner pour lire les inputs utilisateurs
        int choix = -1;// le choix utilisateur dans le menu
        while (choix == -1) {
            try {
                choix = Integer.parseInt(scanner.next());
            } catch (Exception e) {
                choix = -1;
                System.out.println("Le nombre n'est pas valide, reessayez");
            }
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
                return;
            case 5:
                return;
            default:
                System.out.println("Choisissez un nombre valide");
                return;
        }

    }

    private static void calculerUneSomme() {
    }

    private static void afficherPyramide() {
        boolean validHeight = false;
        Scanner scanner = new Scanner(System.in);
        int hauteur = 0; // hauteur de la pyramide en nombre de lignes

        Random r = new Random(); // on génère aléatoirement des caractères
        System.out.println("Quelle hauteur de pyramide souhaitez vous?");
        while (!validHeight) {
            try {
                hauteur = Integer.parseInt(scanner.next());
                validHeight = true;
            } catch (Exception e) {
                System.out.println("Entrez une hauteur entière valide : "); // gestion des erreurs utilisateur
            }
        }
        for (int i = 0; i < hauteur; i++) {
            for (int j = 1; j <= hauteur - i; j++) {
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
            System.out.println("Merci d'avoir joué, bonne journée");
        }
    }

    /**
     * Affiche une chaine de caracteres que l'utilisateur rentre, retournee
     */
    private static void renverserChaineCaracteres() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\n\nRentrez la chaîne de caracteres à retourner (pas d'accent): ");
        String toReverse = scanner.nextLine(); // on lit l'entrée utilisateur
        System.out.println("Voici votre chaîne(" + toReverse + ") retournee : "
                + new StringBuilder(toReverse).reverse().toString() + "\n");
        finModule();
    }
}