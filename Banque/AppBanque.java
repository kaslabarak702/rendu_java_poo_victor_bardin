package Banque;

import java.util.Arrays;
import java.util.LinkedList;

import Utils.Menu;
import Utils.StringUtils;

public class AppBanque {

    boolean gameOn;
    Menu menu;
    LinkedList<Banque> banques = new LinkedList<Banque>();

    public AppBanque() {
        this.gameOn = true;
        this.menu = new Menu("Bienvenue dans cette micro-simulation de gestion de banques :", new LinkedList<>());
        menu.options.addAll(Arrays.asList("créer une banque",
                "créer un client",
                "consulter la liste des clients d'une banque",
                "consulter les infos d'un client",
                "consulter la liste des banques"));
        while (gameOn) {
            menuBanque();
        }
    }

    private void menuBanque() {

        System.out.println(menu);

        int choix = -1;
        while (choix < 1 || choix > menu.options.size()) {
            choix = StringUtils.demanderEntierUtilisateur("une option");
        }

        switch (choix) {
            case 1:
                BanqueService.ajouterBanque(this);
                return;
            case 6:
                BanqueService.afficherListeBanques(this.banques);
                return;
        }

    }

}