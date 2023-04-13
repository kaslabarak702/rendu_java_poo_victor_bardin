package Utils;

import java.util.LinkedList;

public class Menu {
    public LinkedList<String> options;
    private String intro;

    public Menu(String intro, LinkedList<String> options) {
        this.intro = intro;
        this.options = options;
    }

    public String toString() {
        int index = 0;
        StringBuilder sb = new StringBuilder();
        sb.append("\n" + intro + "\n");
        for (String option : options) {
            index++;
            sb.append(index + " -> " + option + " \n");
        }
        sb.append("Entrez le chiffre correspondant à votre choix entre 1 et " + index + ":");
        return sb.toString();
    }
}
