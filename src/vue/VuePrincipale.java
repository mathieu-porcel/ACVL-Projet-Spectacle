package vue;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.function.Supplier;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public abstract class VuePrincipale extends JPanel {

    private JPanel panel;
    private GridBagConstraints constraints;

    public VuePrincipale() {
        // ScrollPane acceuillant les composnants graphiques
        setLayout(new BorderLayout());
        panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        constraints = new GridBagConstraints();
        add(new JScrollPane(panel), BorderLayout.CENTER);

        // Contraintes du cadrillage des composnants graphiques
        constraints.anchor = GridBagConstraints.WEST;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.insets = new Insets(2, 2, 2, 2);
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.gridwidth = 1;
        constraints.gridheight = 1;
        constraints.fill = GridBagConstraints.BOTH;
    }

    protected JComponent addComposantGraphique(JComponent component) {
        constraints.gridx++;
        panel.add(component, constraints);
        panel.updateUI();
        return component;
    }

    protected void newLigne() {
        constraints.gridx = 0;
        constraints.gridy++;
    }

    protected void clear() {
        constraints.gridx = 0;
        constraints.gridy = 0;
        panel.removeAll();
    }

    protected void addBouton(String texte, Runnable action) {
        JButton bouton = new JButton(texte);
        bouton.addActionListener(e -> action.run());
        addComposantGraphique(bouton);
    }

    protected void addTexte(String texte) {
        addComposantGraphique(new JLabel(texte));
    }

    protected Supplier<String> addChampTexte() {
        JTextField champ = new JTextField(16);
        addComposantGraphique(champ);
        return () -> champ.getText();
    }

    protected Supplier<String> addChampMotDePasse() {
        JPasswordField champ = new JPasswordField(16);
        addComposantGraphique(champ);
        return () -> new String(champ.getPassword());
    }
}
