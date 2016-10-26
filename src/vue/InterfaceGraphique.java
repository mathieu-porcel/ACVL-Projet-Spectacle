package vue;

import java.awt.BorderLayout;

import javax.swing.JFrame;

@SuppressWarnings("serial")
public class InterfaceGraphique extends JFrame {

    private static InterfaceGraphique instance;

    /**
     * Singleton
     */
    public static InterfaceGraphique getInstance() {
        if (instance == null) {
            instance = new InterfaceGraphique();
        }
        return instance;
    }

    /**
     * Initialisation de l'interface graphique
     */
    private InterfaceGraphique() {
        setLayout(new BorderLayout());
        setSize(800, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }

    public void setVueActions(VueActions vue) {
        add(vue, BorderLayout.NORTH);
        vue.updateUI();
    }

    public void setVuePrincipale(VuePrincipale vue) {
        add(vue, BorderLayout.CENTER);
        vue.updateUI();
    }
}
