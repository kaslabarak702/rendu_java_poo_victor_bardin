package Utils;

import java.util.LinkedList;

public class Menu {
    public LinkedList<String> options;
    private String intro;

    /**
     * Crée un nouveau menu (compilation d'options numérotées)
     * 
     * @param intro   : présentation du menu
     * @param options : options du menu
     */
    public Menu(String intro, LinkedList<String> options) {
        this.intro = intro;
        this.options = options;
    }

    public String toString() {
        int index = 0;
        StringBuilder sb = new StringBuilder();
        if (!"".equals(intro)) {
            sb.append("\n" + intro + "\n");
        }
        for (String option : options) {
            index++;
            sb.append(index + " -> " + option + " \n");
        }
        sb.append("Entrez le chiffre correspondant à votre choix entre 1 et " + index + " : ");
        return sb.toString();
    }

    /**
     * Demande en boucle une valeur entiere positive dans la range du menu à
     * l'utilisateur, jusqu'à en recevoir une correcte
     * 
     * @return choix : le numéro de l'option dans le menu
     */
    public int choix() {
        int choix = -1;// le choix utilisateur dans le menu
        while (choix < 1 || choix > this.options.size()) {
            choix = StringUtils.demanderEntierUtilisateur("", false);
        }
        return choix;
    }
}
