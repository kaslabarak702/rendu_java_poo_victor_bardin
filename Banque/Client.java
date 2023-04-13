package Banque;

import java.util.UUID;

public abstract class Client {
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
        String toReturn = "id=" + getId() +
                ", nom=" + getNom() +
                ", adresse=" + getAdresse() +
                ", email=" + getEmail();

        if (this instanceof ClientParticulier) {
            toReturn += ", prenom=" + ((ClientParticulier) this).getPrenom() +
                    ", profession=" + ((ClientParticulier) this).getProfession() +
                    ", salaire=" + ((ClientParticulier) this).getSalaire();
        } else {
            toReturn += ", numeroEntreprise=" + ((ClientEntreprise) this).getNumeroEntreprise() +
                    ", secteurActivite=" + ((ClientEntreprise) this).getSecteurActivite();
        }
        return toReturn;
    }

    private String getEmail() {
        return this.email;
    }

}
