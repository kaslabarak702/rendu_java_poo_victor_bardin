package src.Modules.SimulationBanque;

import java.util.UUID;

public class Client {
    private final UUID id;
    private String nom;
    private String adresse;
    private String email;

    public Client(String nom, String adresse, String email) {
        this.id = UUID.randomUUID();
        this.nom = nom;
        this.adresse = adresse;
        this.email = email;
    }

    public UUID getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append("nom: ").append(nom).append("\n");
        sb.append("    adresse: ").append(adresse).append("\n ");
        sb.append("    email: ").append(email).append("\n ");

        if (this instanceof ClientParticulier) {
            ClientParticulier cp = (ClientParticulier) this;
            sb.append("    prenom: ").append(cp.getPrenom()).append("\n ");
            sb.append("    profession: ").append(cp.getProfession()).append("\n ");
            sb.append("    salaire: ").append(cp.getSalaire());
        } else {
            ClientEntreprise ce = (ClientEntreprise) this;
            sb.append("    numeroEntreprise: ").append(ce.getNumeroEntreprise()).append("\n ");
            sb.append("    secteurActivite: ").append(ce.getSecteurActivite());
        }

        return sb.toString();
    }

    public String toJSON(boolean idOnly) {
        StringBuilder sb = new StringBuilder("{");
        if (!idOnly) {
            sb.append("\"id\": \"").append(id).append("\", ");
            sb.append("\"nom\": \"").append(nom).append("\", ");
            sb.append("\"adresse\": \"").append(adresse).append("\", ");
            sb.append("\"email\": \"").append(email).append("\", ");
            if (this instanceof ClientParticulier) {
                ClientParticulier cp = (ClientParticulier) this;
                sb.append("\"prenom\": \"").append(cp.getPrenom()).append("\", ");
                sb.append("\"profession\": \"").append(cp.getProfession()).append("\", ");
                sb.append("\"salaire\": ").append(cp.getSalaire());
            } else {
                ClientEntreprise ce = (ClientEntreprise) this;
                sb.append("\"numeroEntreprise\": \"").append(ce.getNumeroEntreprise()).append("\", ");
                sb.append("\"secteurActivite\": \"").append(ce.getSecteurActivite()).append("\"");
            }

        } else {
            sb.append("\"id\": \"").append(id).append("\"");
        }
        sb.append("}");
        return sb.toString();
    }

}
