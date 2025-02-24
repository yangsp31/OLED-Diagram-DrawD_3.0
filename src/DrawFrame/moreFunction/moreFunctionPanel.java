package DrawFrame.moreFunction;

import javax.swing.*;
import java.awt.*;

public class moreFunctionPanel extends JPanel {
    public moreFunctionPanel(JButton previousButton, JButton cutButton, JButton setBorderButton, JButton moveFrontButton, JButton moveBackButton, JButton toggleValuesButton,
                             JButton toggleNameButton, JButton invertValueButton, JButton invertNameButton, JButton deleteAllButton, JButton searchMaterialButton,
                             JButton saveMaterialButton, JButton setTransparencyButton) {
        setLayout(new GridBagLayout());
        setBackground(new Color(0, 2, 11));
        setPreferredSize(new Dimension(280, 900));
        setMaximumSize(new Dimension(280, 900));

        deleteAllButton.setBackground(new Color(131, 0, 0));

        GridBagConstraints c = new GridBagConstraints();

        c.insets = new Insets(40, 5, 50, 5);
        c.gridx = 0;
        c.weightx = 1.0;
        c.weighty = 0;
        c.fill = GridBagConstraints.BOTH;
        c.anchor = GridBagConstraints.WEST;

        c.gridy = 0;
        add(previousButton, c);

        c.insets = new Insets(0, 5, 20, 5);
        c.gridy = 1;
        add(cutButton, c);

        c.gridy = 2;
        add(setBorderButton, c);

        c.gridy = 3;
        add(moveFrontButton, c);

        c.gridy = 4;
        add(moveBackButton, c);

        c.gridy = 5;
        add(toggleValuesButton, c);

        c.gridy = 6;
        add(toggleNameButton, c);

        c.gridy = 7;
        add(invertValueButton, c);

        c.gridy = 8;
        add(invertNameButton, c);

        c.gridy = 9;
        add(setTransparencyButton, c);

        c.gridy = 10;
        add(searchMaterialButton, c);

        c.gridy = 11;
        add(saveMaterialButton, c);

        c.insets = new Insets(60, 5, 0, 5);
        c.gridy = 12;
        add(deleteAllButton, c);

    }
}
