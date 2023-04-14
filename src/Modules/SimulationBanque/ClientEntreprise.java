package src.Modules.SimulationBanque;

public class ClientEntreprise extends Client {
    /**
     * Le numéro d'entreprise du client entreprise.
     */
    private int numeroEntreprise;

    /**
     * Le secteur d'activité du client entreprise.
     */ 
    private String secteurActivite;

    /**
     * Cette classe représente un client entreprise de la banque.
     * Elle hérite de la classe Client et ajoute deux attributs : le numéro
     * d'entreprise et le secteur d'activité.
     */
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

    /**
     * Le numéro d'entreprise du client entreprise.
     * 
     * @return le numéro d'entreprise
     */
    public int getNumeroEntreprise() {
        return numeroEntreprise;
    }

    /**
     * Le secteur d'activité du client entreprise.
     * 
     * @return le secteur d'activité
     */
    public String getSecteurActivite() {
        return secteurActivite;
    }
}
