package vue;

import java.awt.BorderLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;

import Controleur.Controleur;

@SuppressWarnings("serial")
public class InterfaceGraphique extends JFrame {

    private AbstractVueActions vueActions;
    private AbstractVuePrincipale vuePrincipale;

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

        // Sauvegarde du modèle à la fermeture de l'application
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                Controleur.getInstance().saveModele();
            }
        });
    }

    public void setVueActions(AbstractVueActions vueActions) {
        this.vueActions = vueActions;
        updateVue();
    }

    public void setVuePrincipale(AbstractVuePrincipale vuePrincipale) {
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
