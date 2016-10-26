package vue;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class VueConnexion extends VuePrincipale {
    public VueConnexion() {
        addComponent(new JLabel("Pseudo"));
        JTextField pseudo = new JTextField(10);
        addComponent(pseudo);
        newLine();

        JPasswordField motDePasse = new JPasswordField(10);
        addComponent(new JLabel("Mot de passe"));
        addComponent(motDePasse);
        newLine();

        JButton ok = new JButton("Ok");
        ok.addActionListener(e -> {
            System.out.println("Connexion: " + pseudo.getText() + " " + new String(motDePasse.getPassword()));
        });
        addComponent(ok);
    }
}
