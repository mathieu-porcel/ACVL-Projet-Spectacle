package vue;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class InterfaceGraphique extends JFrame {

    private VueActions vueActions;
    private VuePrincipale vuePrincipale;

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
        setTitle("Reservation Spectacle");
        setSize(800, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }

    public void setVueActions(VueActions vueActions) {
        this.vueActions = vueActions;
        updateVue();
    }

    public void setVuePrincipale(VuePrincipale vuePrincipale) {
        this.vuePrincipale = vuePrincipale;
        updateVue();
    }

    /**
     * Met à jour la fenêtre en y ajoutant toutes les vues en cours
     */
    private void updateVue() {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        if (vueActions != null) {
            panel.add(vueActions, BorderLayout.NORTH);
        }
        if (vuePrincipale != null) {
            panel.add(vuePrincipale, BorderLayout.CENTER);
        }
        setContentPane(panel);
        repaint();
        revalidate();
    }
}
