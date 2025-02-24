package customComponent;

import javax.swing.*;
import java.awt.*;

public class basicButton extends JButton {
    Font basicButtonFont = new Font("Arial", Font.BOLD, 15);

    public basicButton(String text) {
        super(text);

        setPreferredSize(new Dimension(100, 35));
        setBackground(new Color(150, 150, 150));
        setForeground(new Color(242, 239, 233));
        setFont(basicButtonFont);
    }
}
