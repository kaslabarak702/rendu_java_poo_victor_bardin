package Banque;

public class ClientEntreprise extends Client {
    private int numeroEntreprise;
    private String secteurActivite;

    public ClientEntreprise(
            String nom,
            String adresse,
            int numeroEntreprise,
            String secteurActivite,
            String mail) {
        super(nom, adresse, mail);
        this.numeroEntreprise = numeroEntreprise;
        this.secteurActivite = secteurActivite;
    }

    public int getNumeroEntreprise() {
        return numeroEntreprise;
    }

    public String getSecteurActivite() {
        return secteurActivite;
    }
}
