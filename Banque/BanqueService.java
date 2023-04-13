package Banque;

import java.util.LinkedList;

import Utils.StringUtils;

public class BanqueService {

    public static void ajouterBanque(AppBanque appBanque) {
        appBanque.banques.add(new Banque(StringUtils.demanderTexteUtilisateur("un nom de banque")));
    }

    public static void afficherListeBanques(LinkedList<Banque> banques) {
        if (banques.size() == 0) {
            System.out.println("Il n'y a pas encore de banque à afficher");
        } else {
            for (Banque banque : banques) {
                System.out.println(banque.toString());
            }
        }

    }
}
