package customComponent;

import javax.swing.*;
import java.awt.*;

public class toggleButton extends JButton {
    Font toggleButtonFont = new Font("Arial", Font.BOLD, 15);

    public toggleButton(String text) {
        super(text);

        setPreferredSize(new Dimension(100, 35));
        setBackground(new Color(0, 2, 11));
        setForeground(new Color(242, 239, 233));
        setFont(toggleButtonFont);
        setEnabled(false);
    }
}
