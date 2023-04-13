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

    // gestion de tous les événements de la fenetres
    public void actionPerformed(ActionEvent event) {
        if (event.getSource() == nouveauFichier) {
            textArea.setText("");
            fileName = null;
        } else if (event.getSource() == ouvrirFichier) {
            int returnVal = fileChooser.showOpenDialog(this);
            if (returnVal == JFileChooser.APPROVE_OPTION) {
                try {
                    java.io.File file = fileChooser.getSelectedFile();
                    java.io.FileReader fr = new java.io.FileReader(file);
                    textArea.read(fr, null);
                    fr.close();
                    fileName = file.getName();
                    setTitle(fileName);
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(this, "Erreur lors du chargement du fichier : " + e.getMessage());
                }
            }
        } else if (event.getSource() == sauverFichier || event.getSource() == boutonSauvegarde) {
            if (fileName == null) {
                // affichage assistant
                int returnVal = fileChooser.showSaveDialog(this);

                if (returnVal == JFileChooser.APPROVE_OPTION) {
                    try {
                        File file = fileChooser.getSelectedFile();
                        FileWriter fw = new FileWriter(file);
                        textArea.write(fw);
                        fw.close();
                        fileName = file.getName();
                        setTitle(fileName);
                    } catch (Exception e) {
                        JOptionPane.showMessageDialog(this,
                                "Erreur lors de l'enregistrement du fichier : " + e.getMessage());
                    }
                }
            } else {
                try {
                    java.io.FileWriter fw = new FileWriter(fileName);
                    textArea.write(fw);
                    fw.close();
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(this,
                            "Erreur lors de l'enregistrement du fichier : " + e.getMessage());
                }
            }
        } else if (event.getSource() == exit) {
            System.exit(0);
        }
    }

    public static void launchWord() {
        new Word();
    }
}
