package Banque;

public class AppBanque {
    boolean gameOn;

    public AppBanque() {
        gameOn = true;
        while (gameOn) {
            menuBanque();
        }
    }

    private void menuBanque() {
        StringBuilder menu = new StringBuilder();
        menu.append("Bienvenue dans cette micro-simulation de gestion de banques, vous avez la possibilité de :\n")
                .append("1. créer une banque\n")
                .append("2. créer un client\n")
                .append("3. rattacher un client à une banque\n")
                .append("4. consulter la liste des clients d'une banque\n")
                .append("5. consulter les infos d'un client\n")
        ;
    }

}