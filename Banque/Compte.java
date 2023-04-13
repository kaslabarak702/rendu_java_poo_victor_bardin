package Banque;

import java.util.UUID;

public class Compte {
    private final UUID id;
    private double solde;
    private Client client;

    public Compte(Client client) {
        this.id = UUID.randomUUID();
        this.solde = 0.0;
        this.client = client;
    }

    public UUID getId() {
        return id;
    }

    public double getSolde() {
        return solde;
    }

    public Client getclient() {
        return client;
    }

    public void setclient(Client client) {
        this.client = client;
    }

    public void crediter(double montant) {
        solde += montant;
    }

    public boolean debiter(double montant) {
        if (montant <= solde) {
            solde -= montant;
            return true;
        } else {
            System.out.println("impossible de passer ce compte à découvert");
            return false;
        }
    }
}
