package Utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class StringUtils {
    /**
     * Ouvre un fichier dont le chemin est précisé par un input console, l'ouvre et
     * lit son contenu, utilise compterMotsPhraseModule pour alanyser le string
     * qu'est le
     * texte, gestion des exceptions incluse
     * 
     * @see compterMotsPhraseModule
     */
    public static void analyseTexte() {
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
     * comte le nombre de mots dans une phrase en paramètre
     * 
     * @param texte analysé
     */
    public static void compterLesMots(String texte) {
        System.out.println("Ce texte contient " + texte.split("[\\s']+").length + " mots\n");
    }

    /**
     * Sauvegarde une entrée de texte utilisateur dans un fichier dans le dossier
     * téléchargements de l'utilisateur
     * 
     * @param texte  le texte à sauvegarder
     * @param chemin le chemin du fichier dans lequel texte est sauvegardé
     */
    public static void sauvegarderTexte(String texte, String chemin) {
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

    public static String demanderTexteUtilisateur(String titreValeur) {
        System.out.print("\nChoisissez " + titreValeur + " : ");
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    /**
     * Affiche une chaine de caracteres que l'utilisateur rentre, retournee
     */
    public static void renverserChaineCaracteres() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\nRentrez la chaîne de caracteres à retourner (pas d'accent): ");
        String toReverse = scanner.nextLine(); // on lit l'entrée utilisateur
        System.out.println("Voici votre chaîne(" + toReverse + ") retournee : "
                + new StringBuilder(toReverse).reverse().toString() + "\n");
    }

    /**
     * Sauvegarde une entrée de texte utilisateur dans un fichier dans le dossier
     * téléchargements de l'utillisateur
     */
    public static void sauvegarderTexte() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Entrez le texte à sauvegarder : ");
        StringUtils.sauvegarderTexte(scanner.nextLine(),
                System.getProperty("user.home") + "/Downloads/texte_sauvegarde.txt");
    }

    /**
     * comte le nombre de mots dans une phrase rentrée dans la console
     */
    public static void compterMotsPhraseModule() {
        Scanner sc = new Scanner(System.in);
        System.out.println("\nEntrez le texte dont vous voulez connaitre le nombre de mots : ");
        // on passe par un tableau de string (on délimite les mots comme des chaines
        // séparées par des apostrophes et des espaces)
        StringUtils.compterLesMots(sc.nextLine());
    }
}
