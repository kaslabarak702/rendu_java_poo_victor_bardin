package Banque;

import java.lang.StackWalker.Option;
import java.util.Arrays;
import java.util.LinkedList;

import Utils.Menu;
import Utils.StringUtils;

public class AppBanque {

    boolean gameOn;
    Menu menu;
    LinkedList<Banque> banques = new LinkedList<Banque>();
    private LinkedList<String> options;

    public AppBanque() {
        this.gameOn = true;
        this.menu = new Menu("Bienvenue dans cette micro-simulation de gestion de banques :", new LinkedList<>());
        this.options = new LinkedList<String>();
        this.options.addAll(Arrays.asList("créer une banque",
                "créer un client",
                "consulter la liste des clients d'une banque",
                "consulter les infos d'un client",
                "consulter la liste des banques",
                "extraire toutes les données en json",
                "peupler l'environnement de fausses données à des fins de démonstration"));
        menu.options = this.options;
        menuBanque(gameOn);

    }

    private void menuBanque(boolean gameOn) {
        if (!gameOn)
            return;
        System.out.print(menu);
        switch (menu.choix()) {
            case 1:
                BanqueService.ajouterBanque(this);
                finModule();
                return;
            case 2:
                BanqueService.ajouterClient(this);
                finModule();
                return;
            case 5:
                BanqueService.afficherListeBanques(this.banques);
                finModule();
                return;
            case 6:
                BanqueService.serializeAllDataToJsonCSV(this);
                finModule();
                return;
            case 7:
                BanqueService.demo(this
                );
                finModule();
                return;
        }

    }

    private void finModule() {
        String choix = StringUtils
                .demanderTexteUtilisateur("\nappuyez sur entrer pour continuer, tapez exit pour sortir\n", false);
        if (choix.equalsIgnoreCase("exit")) {
            gameOn = false;
        }
        menu = new Menu("opération effectuée, que voulez vous faire mainteant?", options);
        menuBanque(gameOn);// retour au menu, le cas échéant
    }

}