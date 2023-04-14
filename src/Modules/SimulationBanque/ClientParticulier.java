package src.Modules.SimulationBanque;

public class ClientParticulier extends Client {
    private String profession;
    private double salaire;
    private String prenom;

    /**
     * 
     * @param nom
     * @param prenom
     * @param age
     * @param adresse
     * @param email
     * @param profession
     * @param salaire
     */
    public ClientParticulier(String nom, String prenom, int age, String adresse, String email, String profession,
            double salaire) {
        super(nom, adresse, email);
        this.prenom = prenom;
        this.profession = profession;
        this.salaire = salaire;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public double getSalaire() {
        return salaire;
    }

    public void setSalaire(double salaire) {
        this.salaire = salaire;
    }

    public String getPrenom() {
        return this.prenom;
    }
}
