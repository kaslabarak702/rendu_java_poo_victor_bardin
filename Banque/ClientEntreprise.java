package Banque;

public class ClientEntreprise extends Client {
    private String numeroEntreprise;
    private String secteurActivite;

    public ClientEntreprise(String nom, String adresse, String numeroEntreprise, String secteurActivite, String mail) {
        super(nom, adresse, mail);
        this.numeroEntreprise = numeroEntreprise;
        this.secteurActivite = secteurActivite;
    }

    public String getNumeroEntreprise() {
        return numeroEntreprise;
    }

    public String getSecteurActivite() {
        return secteurActivite;
    }
}
