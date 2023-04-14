package src.Modules.SimulationBanque;

import java.util.UUID;

public class Compte {
    private final UUID id;
    private double solde;
    private Client client;

    /**
     * Initialise un nouveau compte bancaire pour un client
     * 
     * @param client Le client pour lequel créer le compte bancaire
     */
    public Compte(Client client) {
        this.id = UUID.randomUUID();
        this.solde = 0.0;
        this.client = client;
    }

    /**
     * Retourne l'identifiant unique de ce compte bancaire
     * 
     * @return L'identifiant unique de ce compte bancaire
     */
    public UUID getId() {
        return id;
    }

    /**
     * Retourne le solde actuel de ce compte bancaire
     * 
     * @return Le solde actuel de ce compte bancaire
     */
    public double getSolde() {
        return solde;
    }

    /**
     * Retourne le client propriétaire de ce compte bancaire
     * 
     * @return Le client propriétaire de ce compte bancaire
     */
    public Client getClient() {
        return client;
    }

    /**
     * Modifie le client propriétaire de ce compte bancaire
     * 
     * @param client Le nouveau client propriétaire de ce compte bancaire
     */
    public void setClient(Client client) {
        this.client = client;
    }

    /**
     * Ajoute un montant donné au solde de ce compte bancaire
     * 
     * @param montant Le montant à ajouter
     */
    public void crediter(double montant) {
        solde += montant;
    }

    /**
     * Retire un montant donné du solde de ce compte bancaire, si possible
     * 
     * @param montant Le montant à retirer
     * @return true si le retrait a été effectué avec succès, false sinon
     */
    public boolean debiter(double montant) {
        if (montant <= solde) {
            solde -= montant;
            return true;
        } else {
            System.out.println("Impossible de passer ce compte à découvert.");
            return false;
        }
    }

    /**
     * Retourne une représentation textuelle de ce compte bancaire, sous la forme
     * d'une chaîne de caractères
     * 
     * @return Une chaîne de caractères représentant ce compte bancaire
     */
    public String toString() {
        return "Compte [solde=" + solde + ", client=" + client.getNom() + "]";
    }

    /**
     * Retourne une représentation JSON de ce compte bancaire, sous la forme d'une
     * chaîne de caractères
     * 
     * @return Une chaîne de caractères représentant ce compte bancaire au format
     *         JSON
     */
    public String toJSON() {
        StringBuilder sb = new StringBuilder("{");
        sb.append("\"id\": \"").append(id).append("\", ");
        sb.append("\"solde\": ").append(solde).append(", ");
        sb.append("\"client\": ").append(client.toJSON(true));
        sb.append("}");
        return sb.toString();
    }
}
