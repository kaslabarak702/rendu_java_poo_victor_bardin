package src.Modules.SimulationBanque;

import java.util.UUID;

/**
 * 
 * Chaque client possède un identifiant unique généré par UUID
 * La classe implémente toJSON(), pour une extraction pattern JSON
 * Cette classe est utilisée par les classes ClientParticulier et
 * ClientEntreprise qui en héritent.
 * 
 * @author victor BARDIN
 */
public class Client {
    /**
     * L'identifiant unique du client.
     */
    private final UUID id;

    /**
     * Le nom du client.
     */
    private String nom;

    /**
     * L'adresse du client.
     */
    private String adresse;

    /**
     * L'adresse email du client.
     */
    private String email;

    /**
     * Construit un nouveau client avec le nom, l'adresse et l'email spécifiés.
     * 
     * @param nom     le nom du client
     * @param adresse l'adresse du client
     * @param email   l'adresse email du client
     */
    public Client(String nom, String adresse, String email) {
        this.id = UUID.randomUUID();
        this.nom = nom;
        this.adresse = adresse;
        this.email = email;
    }

    /**
     * Renvoie l'identifiant unique du client.
     * 
     * @return l'identifiant unique du client
     */
    public UUID getId() {
        return id;
    }

    /**
     * Renvoie le nom du client.
     * 
     * @return le nom du client
     */
    public String getNom() {
        return nom;
    }

    /**
     * Renvoie l'adresse du client.
     * 
     * @return l'adresse du client
     */
    public String getAdresse() {
        return adresse;
    }

    /**
     * Modifie le nom du client.
     * 
     * @param nom le nouveau nom du client
     */
    public void setNom(String nom) {
        this.nom = nom;
    }

    /**
     * Modifie l'adresse du client.
     * 
     * @param adresse la nouvelle adresse du client
     */
    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    /**
     * Renvoie une chaîne de caractères qui décrit le client et ses attributs, y
     * compris les attributs spécifiques aux sous-classes.
     * 
     * @return une chaîne de caractères qui décrit le client et ses attributs
     */
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
        sb.append("\n");
        return sb.toString();
    }

    /**
     * Renvoie une chaîne de caractères qui représente le client et ses attributs au
     * format JSON.
     * 
     * @param idOnly si vrai, ne renvoie que l'identifiant du client en format JSON
     * @return une chaîne de caractères qui représente le client et ses attributs au
     *         format JSON
     */
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
