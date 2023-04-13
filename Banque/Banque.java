package Banque;

import java.util.ArrayList;

public class Banque {
    private String nom;
    private ArrayList<Client> clients;
    private ArrayList<Compte> comptes;

    public Banque(String nom) {
        this.nom = nom;
        this.clients = new ArrayList<Client>();
        this.comptes = new ArrayList<Compte>();
        this.clients = new ArrayList<>();
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

    public String toString() {
        StringBuilder sb = new StringBuilder();
        int index = 0;
        sb.append("Banque: ")
                .append(nom)
                .append("\n")
                .append("Clients:");
        for (Client client : clients) {
            sb.append(client).append("\n");
            index++;
        }
        if (index == 0) {
            sb.append(" aucun");
        }
        sb.append("\n");
        index = 0;
        sb.append("Comptes:");
        for (Compte compte : comptes) {
            index++;
            sb.append(compte).append("\n");
        }
        if (index == 0) {
            sb.append(" aucun");
        }
        sb.append("\n");
        return sb.toString();
    }
}
