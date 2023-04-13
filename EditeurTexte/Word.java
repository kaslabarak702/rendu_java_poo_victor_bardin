package EditeurTexte;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileWriter;

public class Word extends JFrame implements ActionListener {

    private JTextArea textArea;
    private JMenuBar menuBar;
    private JMenu fileMenu;
    private JMenuItem nouveauFichier, ouvrirFichier, sauverFichier, exit;
    private JFileChooser fileChooser;
    private String fileName;
    private JButton boutonSauvegarde;

    public Word() {
        // Layout général
        super("Word - logiciel libre de droits");
        setAlwaysOnTop(true);
        setLayout(new BorderLayout());
        setSize(800, 400);

        textArea = new JTextArea();
        JScrollPane scrollPane = new JScrollPane(textArea);
        add(scrollPane, BorderLayout.CENTER);

        menuBar = new JMenuBar();
        fileMenu = new JMenu("Fichier");

        nouveauFichier = new JMenuItem("Nouveau");
        nouveauFichier.addActionListener(this);
        fileMenu.add(nouveauFichier);

        ouvrirFichier = new JMenuItem("Ouvrir");
        ouvrirFichier.addActionListener(this);
        fileMenu.add(ouvrirFichier);

        sauverFichier = new JMenuItem("Enregistrer");
        sauverFichier.addActionListener(this);
        fileMenu.add(sauverFichier);

        exit = new JMenuItem("Quitter");
        exit.addActionListener(this);
        fileMenu.add(exit);

        menuBar.add(fileMenu);
        setJMenuBar(menuBar);

        // assistant fichiers
        fileChooser = new JFileChooser();

        boutonSauvegarde = new JButton("Enregistrer");
        boutonSauvegarde.addActionListener(this);
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.add(boutonSauvegarde);
        add(buttonPanel, BorderLayout.SOUTH);

        // affichage de la fenetre
        setVisible(true);
    }

    public static void launchWord() {
        new Word();
    }
}
