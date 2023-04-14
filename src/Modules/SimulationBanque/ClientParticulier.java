package src.Modules.SimulationBanque;

/**
 * Représente un client particulier de la banque
 * Hérite de la classe Client
 */
public class ClientParticulier extends Client {

    /**
     * Le prénom du client particulier
     */
    private String prenom;

    /**
     * La profession du client particulier
     */
    private String profession;

    /**
     * Le salaire du client particulier
     */
    private double salaire;

    /**
     * Constructeur de la classe ClientParticulier
     *
     * @param nom        le nom du client particulier
     * @param prenom     le prénom du client particulier
     * @param age        l'âge du client particulier
     * @param adresse    l'adresse du client particulier
     * @param email      l'email du client particulier
     * @param profession la profession du client particulier
     * @param salaire    le salaire du client particulier
     */
    public ClientParticulier(String nom, String prenom, int age, String adresse, String email, String profession,
            double salaire) {
        super(nom, adresse, email);
        this.prenom = prenom;
        this.profession = profession;
        this.salaire = salaire;
    }

    /**
     * Retourne le prénom du client particulier
     *
     * @return le prénom du client particulier
     */
    public String getPrenom() {
        return this.prenom;
    }

    /**
     * Retourne la profession du client particulier
     *
     * @return la profession du client particulier
     */
    public String getProfession() {
        return profession;
    }

    /**
     * Modifie la profession du client particulier
     *
     * @param profession la nouvelle profession du client particulier
     */
    public void setProfession(String profession) {
        this.profession = profession;
    }

    /**
     * Retourne le salaire du client particulier
     *
     * @return le salaire du client particulier
     */
    public double getSalaire() {
        return salaire;
    }

    /**
     * Modifie le salaire du client particulier
     *
     * @param salaire le nouveau salaire du client particulier
     */
    public void setSalaire(double salaire) {
        this.salaire = salaire;
    }
}
