package src.Modules.SimulationBanque;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Classe représentant une banque qui gère des clients et des comptes.
 */
public class Banque {
    /**
     * Nom de la banque
     */
    private String nom;
    /**
     * Liste des clients de la banque
     */
    ArrayList<Client> clients;
    /**
     * Liste des comptes de la banque
     */
    LinkedList<Compte> comptes;

    /**
     * Constructeur de la classe Banque
     * 
     * @param nom Le nom de la banque
     */
    public Banque(String nom) {
        this.nom = nom;
        this.clients = new ArrayList<Client>();
        this.comptes = new LinkedList<Compte>();
    }

    /**
     * Retourne le nom de la banque
     * 
     * @return Le nom de la banque
     */
    public String getNom() {
        return nom;
    }

    /**
     * Modifie le nom de la banque
     * 
     * @param nom Le nouveau nom de la banque
     */
    public void setNom(String nom) {
        this.nom = nom;
    }

    /**
     * Ajoute un client à la banque
     * 
     * @param client Le client à ajouter
     */
    public void ajouterClient(Client client) {
        this.clients.add(client);
    }

    /**
     * Supprime un client de la banque
     * 
     * @param client Le client à supprimer
     */
    public void supprimerClient(Client client) {
        this.clients.remove(client);
    }

    /**
     * Retourne la liste des clients de la banque
     * 
     * @return La liste des clients de la banque
     */
    public ArrayList<Client> getClients() {
        return clients;
    }

    /**
     * Ouvre un compte pour un client et y dépose un solde initial
     * 
     * @param client Le client pour lequel ouvrir un compte
     * @param solde  Le solde initial à déposer sur le compte
     */
    public void ouvrirCompte(Client client, double solde) {
        Compte compte = new Compte(client);
        compte.crediter(solde);
        this.comptes.add(compte);
    }

    /**
     * Ferme un compte pour un client
     * 
     * @param client Le client pour lequel fermer un compte
     * @param compte Le compte à fermer
     */
    public void fermerCompte(Client client, Compte compte) {
        comptes.remove(compte);
        System.out.println("Le compte " + compte.getId() + " a été supprimé.");
    }

    /**
     * Affiche la liste des clients de la banque
     */
    public void afficherClients() {
        System.out.println("Liste des clients de la banque " + nom + ":");
        for (Client client : clients) {
            System.out.println(client);
        }
    }

    /**
     * Retourne la liste des comptes de la banque
     * 
     * @return La liste des comptes de la banqu
     */
    public LinkedList<Compte> getComptes() {
        return comptes;
    }

    /**
     * Retourne une description de la banque
     * 
     * @return description de la banque
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("\n" + nom).append("\n\n");
        for (Client client : clients) {
            sb.append("    ").append(client.toString()).append("\n");
        }
        sb.append("\n");
        for (Compte compte : comptes) {
            sb.append("    ").append(compte.toString()).append("\n");
        }
        return sb.toString();
    }

}
