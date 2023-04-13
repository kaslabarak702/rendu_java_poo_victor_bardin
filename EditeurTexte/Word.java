package EditeurTexte;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class Word extends JFrame implements ActionListener {

    private JTextArea textArea;
    private JMenuBar menuBar;
    private JMenu fileMenu;
    private JMenuItem nouveauFichier,
            ouvrirFichier,
            sauverFichier;
    private JFileChooser fileChooser;
    private String fileName;

    public Word() {
        // Layout général
        super("Word - Nouveau fichier");
        fileName = "Nouveau fichier";

        setAlwaysOnTop(true); // on garde la fenetre en premier plan
        setLayout(new BorderLayout()); // layout générique sans espace
        setSize(800, 400);

        // On crée la zone de texte dans une zone scrollable
        textArea = new JTextArea();
        JScrollPane scrollPane = new JScrollPane(textArea);
        add(scrollPane, BorderLayout.CENTER);

        // la barre de menus
        menuBar = new JMenuBar();
        fileMenu = new JMenu("Fichier");

        // bouton pour créer un nouvau fichier vierge
        nouveauFichier = new JMenuItem("Nouveau");
        nouveauFichier.addActionListener(this);
        fileMenu.add(nouveauFichier);

        // bouton pour ouvrir un fichier déjà existant
        ouvrirFichier = new JMenuItem("Ouvrir");
        ouvrirFichier.addActionListener(this);
        fileMenu.add(ouvrirFichier);

        // bouton enregistrer le fichier s
        sauverFichier = new JMenuItem("Enregistrer");
        sauverFichier.addActionListener(this);
        fileMenu.add(sauverFichier);

        // On ajoute la barre au menu
        menuBar.add(fileMenu);
        setJMenuBar(menuBar);

        // assistant fichiers
        fileChooser = new JFileChooser();

        // affichage de la fenetre
        setVisible(true);
    }

    // gestion de tous les événements de la fenetres
    public void actionPerformed(ActionEvent event) {
        if (event.getSource() == nouveauFichier) {
            textArea.setText("");
            fileName = "Nouveau fichier";
            setTitle("Word - " + fileName);
        } else if (event.getSource() == ouvrirFichier) {
            int choice = fileChooser.showOpenDialog(this); // on ouvre l'assistant pour ouvrir un fichier
            if (choice == JFileChooser.APPROVE_OPTION) {
                try {
                    File file = fileChooser.getSelectedFile(); // fichier sélectionné dans l'assistant
                    FileReader fileReader = new java.io.FileReader(file);
                    textArea.read(fileReader, null);
                    fileReader.close(); // on vide le cache

                    // On renomme la fenetre comme le titre du document ouvert
                    fileName = file.getName();
                    setTitle("Word - " + fileName);
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(this, "Erreur lors du chargement du fichier : " + e.getMessage());
                }
            }
        } else if (event.getSource() == sauverFichier) {
            try {
                java.io.FileWriter fw = new FileWriter(fileName);
                textArea.write(fw);
                fw.close();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this,
                        "Erreur lors de l'enregistrement du fichier : " + e.getMessage());
            }

        }
    }

    public static void launchWord() {
        new Word();
    }
}
