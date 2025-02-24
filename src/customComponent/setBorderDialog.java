package customComponent;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class setBorderDialog extends JDialog {
    JPanel basePanel = new JPanel();

    JLabel setBorderTypeLabel = new JLabel("Border Type");
    JLabel setBorderColorLabel = new JLabel("Border Color");

    String[] BorderType = {"None", "Line", "Dot"};
    JComboBox<String> borderTypeBox = new JComboBox<>(BorderType);

    toggleButton setBorderColorButton = new toggleButton("Color");
    basicButton borderApplyButton = new basicButton("Apply");

    Font setBorderDialogFont = new Font("Arial", Font.BOLD, 25);
    Font setBorderTypeBoxFont = new Font("Arial", Font.BOLD, 15);

    Color chooseColor = new Color(0, 0, 0);
    String type = "None";
    boolean apply = false;

    public  setBorderDialog(JFrame parent) {
        super(parent, "Set Border", true);

        setSize(540, 270);
        setResizable(false);
        basePanel.setBackground(new Color(0, 2, 11));
        basePanel.setLayout(new GridBagLayout());

        setBorderTypeLabel.setFont(setBorderDialogFont);
        setBorderTypeLabel.setForeground(new Color(242, 239, 233));

        setBorderColorLabel.setFont(setBorderDialogFont);
        setBorderColorLabel.setForeground(new Color(242, 239, 233));

        borderTypeBox.setFont(setBorderTypeBoxFont);
        borderTypeBox.setPreferredSize(new Dimension(120, 30));

        setBorderColorButton.setPreferredSize(new Dimension(120, 30));
        setBorderColorButton.setForeground(new Color(242, 239, 233));
        setBorderColorButton.setBackground(new Color(0, 2, 11));

        GridBagConstraints c = new GridBagConstraints();

        c.insets = new Insets(0, 20, 30, 15);
        c.gridx = 0;
        c.weightx = 0.0;
        c.weighty = 0;
        c.fill = GridBagConstraints.NONE;
        c.anchor = GridBagConstraints.CENTER;

        c.gridy = 0;
        basePanel.add(setBorderTypeLabel, c);

        c.insets = new Insets(0, 15, 30, 20);
        c.gridx = 1;
        basePanel.add(borderTypeBox, c);

        c.insets = new Insets(0, 20, 30, 15);
        c.gridy = 1;
        c.gridx = 0;
        basePanel.add(setBorderColorLabel, c);

        c.insets = new Insets(0, 15, 30, 20);
        c.gridx = 1;
        basePanel.add(setBorderColorButton, c);

        c.insets = new Insets(10, 20, 0, 20);
        c.gridy = 2;
        c.gridx = 0;
        c.gridwidth = 2;
        basePanel.add(borderApplyButton, c);

        add(basePanel);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(parent);

        borderTypeBox.addActionListener(e -> {
            String selected = (String) borderTypeBox.getSelectedItem();

           if(Objects.requireNonNull(selected).equals("Line") || Objects.requireNonNull(selected).equals("Dot")) {
               setBorderColorButton.setBackground(chooseColor);
               setBorderColorButton.setEnabled(true);

               type = selected;
           }
           else {
               setBorderColorButton.setBackground(new Color(0, 2, 11));
               setBorderColorButton.setEnabled(false);

               type = "None";
           }
        });

        setBorderColorButton.addActionListener(e -> {
            chooseColor = JColorChooser.showDialog(this, "Choose a color", chooseColor);

            if(chooseColor != null) {
                if(chooseColor.getRed() == 0 && chooseColor.getGreen() == 0 && chooseColor.getBlue() == 0) {
                    setBorderColorButton.setForeground(new Color(242, 239, 233));
                }
                else{
                    setBorderColorButton.setForeground(new Color(0, 2, 11));
                }

                setBorderColorButton.setBackground(chooseColor);
            }
            else {
                chooseColor = new Color(0, 0, 0);
                setBorderColorButton.setBackground(chooseColor);
            }
        });

        borderApplyButton.addActionListener(e -> {
            apply = true;
            dispose();
        });
    }

    public boolean isApply() {
        return apply;
    }

    public String getBorderType() {
        return type;
    }

    public Color getBorderColor() {
        return chooseColor;
    }
}
