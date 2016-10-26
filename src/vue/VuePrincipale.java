package vue;

import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JComponent;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public abstract class VuePrincipale extends JPanel {

    private JPanel currentLine;

    public VuePrincipale() {
        setLayout(new GridBagLayout());
        newLine();
    }

    protected void newLine() {
        currentLine = new JPanel();
        currentLine.setLayout(new FlowLayout(FlowLayout.CENTER));
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx = 1;
        add(currentLine, constraints);
    }

    protected void addComponent(JComponent component) {
        currentLine.add(component);
        currentLine.updateUI();
    }
}
