package Banque;

import java.util.ArrayList;
import java.util.LinkedList;

public class Banque {
    private String nom;
    ArrayList<Client> clients;
    private ArrayList<Compte> comptes;

    public Banque(String nom) {
        this.nom = nom;
        this.clients = new ArrayList<Client>();
        this.comptes = new ArrayList<Compte>();
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void ajouterClient(Client client) {
        this.clients.add(client);
    }

    public void supprimerClient(Client client) {
        this.clients.remove(client);
    }

    public ArrayList<Client> getClients() {
        return clients;
    }

    public void ouvrirCompte(Client client, double solde) {
        Compte compte = new Compte(client);
        compte.crediter(solde);
        this.comptes.add(compte);
    }

    public void fermerCompte(Client client, Compte compte) {
        comptes.remove(compte);
        System.out.println("Le compte " + compte.getId() + " a été supprimé.");
    }

    public void afficherClients() {
        System.out.println("Liste des clients de la banque " + nom + ":");
        for (Client client : clients) {
            System.out.println(client);
        }
    }

    public ArrayList<Compte> getComptes() {
        return comptes;
    }

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
