package vue;

import java.awt.BorderLayout;

import javax.swing.JFrame;

@SuppressWarnings("serial")
public class InterfaceGraphique extends JFrame {

    private static InterfaceGraphique instance;

    public static InterfaceGraphique getInstance() {
        if (instance == null) {
            instance = new InterfaceGraphique();
        }
        return instance;
    }

    private InterfaceGraphique() {
        setLayout(new BorderLayout());
        setSize(800, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }

    public void setVueActions(VueActions vue) {
        add(vue, BorderLayout.WEST);
    }

    public void setVuePrincipale(VuePrincipale vue) {
        add(vue, BorderLayout.CENTER);
    }
}
