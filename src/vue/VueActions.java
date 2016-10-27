package vue;

import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public abstract class VueActions extends JPanel {

    public VueActions() {
        setLayout(new FlowLayout());
    }

    /**
     * Ajoute un bouton a la barre d'action
     */
    protected void addAction(String text, Runnable action) {
        JButton button = new JButton(text);
        button.addActionListener(e -> action.run());
        add(button);
    }
}
