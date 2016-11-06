package vue;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.Calendar;
import java.util.Collection;
import java.util.function.Supplier;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public abstract class AbstractVuePrincipale extends JPanel {

    private JPanel panel;
    private GridBagConstraints constraints;

    public AbstractVuePrincipale() {
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

    protected void addBouton(String texte, Runnable action) {
        JButton bouton = new JButton(texte);
        bouton.addActionListener(e -> action.run());
        addComposantGraphique(bouton);
    }

    protected void addTexte(String texte) {
        addComposantGraphique(new JLabel(texte));
    }

    protected void addTitre(String texte) {
        JLabel label = new JLabel(texte);
        label.setFont(new Font(label.getFont().getName(), label.getFont().getStyle(), 16));
        addComposantGraphique(label);
    }

    protected Supplier<String> addChampDate() {
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());

        // Jours
        String[] jours = new String[31];
        for (int i = 0; i < jours.length; i++) {
            jours[i] = (i + 1) + "";
        }
        JComboBox<String> jour = new JComboBox<>(jours);
        panel.add(jour);

        // Mois
        JComboBox<String> mois = new JComboBox<>(
                new String[] { "Janvier", "Fï¿½vrier", "Mars", "Avril", "Mai", "Juin", "Juillet", "Aout", "Septembre", "Octobre", "Novembre", "Decembre" });
        panel.add(mois);

        // Années
        String[] annes = new String[10];
        for (int i = 0; i < annes.length; i++) {
            annes[i] = (Calendar.getInstance().get(Calendar.YEAR) + i) + "";
        }
        JComboBox<String> annee = new JComboBox<>(annes);
        panel.add(annee);

        // Heures
        String[] heures = new String[24];
        for (int i = 0; i < heures.length; i++) {
            heures[i] = i + "H";
        }
        JComboBox<String> heure = new JComboBox<>(heures);
        panel.add(heure);

        addComposantGraphique(panel);
        return () -> (jour.getSelectedItem() + "/" + (mois.getSelectedIndex() + 1) + "/" + annee.getSelectedItem() + " " + heure.getSelectedIndex());
    }

    protected Supplier<?> addListe(Collection<?> liste) {
        JComboBox<Object> comboBox = new JComboBox<>(liste.toArray());
        addComposantGraphique(comboBox);
        return () -> comboBox.getSelectedItem();
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

    protected Supplier<Boolean> addCheckbox() {
        JCheckBox checkbox = new JCheckBox();
        addComposantGraphique(checkbox);
        return () -> checkbox.isSelected();
    }

    protected void addCheckboxLock() {
        JCheckBox checkbox = new JCheckBox();
        addComposantGraphique(checkbox);
        checkbox.setEnabled(false);
    }
}
