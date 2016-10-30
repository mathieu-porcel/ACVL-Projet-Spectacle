package vue;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import modele.Compte;
import modele.TypeCompte;

@SuppressWarnings("serial")
public abstract class AbstractVueActions extends JPanel {

    private JPanel panel;

    public AbstractVueActions(Compte compte, boolean isAdmin) {
        setLayout(new BorderLayout());

        String txt = "Bienvenue";
        if (compte != null && !compte.type.equals(TypeCompte.Admin)) {
            txt += " " + compte.prenom + " " + compte.nom;
        }
        if (isAdmin) {
            txt += " (admin)";
        }
        add(new JLabel(txt), BorderLayout.SOUTH);

        panel = new JPanel();
        panel.setLayout(new FlowLayout());
        add(panel, BorderLayout.CENTER);
    }

    /**
     * Ajoute un bouton a la barre d'action
     */
    protected void addAction(String text, Runnable action) {
        JButton button = new JButton(text);
        button.addActionListener(e -> action.run());
        panel.add(button);
    }
}
