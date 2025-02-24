package customComponent;

import javax.swing.*;
import java.awt.*;

public class saveDialog extends JDialog {
    JPanel basePanel = new JPanel();

    JLabel savePhrases = new JLabel("Enter a name for the file to save");

    JTextField fileNameField = new JTextField(100);

    basicButton saveFileButton = new basicButton("Save");

    Font saveDialogFont = new Font("Arial", Font.BOLD, 25);
    Font fileNameFieldFont = new Font("맑은 고딕", Font.PLAIN, 25);       // Fonts for Korean input

    String filename;
    boolean save = false;

    public saveDialog(JFrame parent) {
        super(parent, "Save", true);

        setSize(540, 270);
        setResizable(false);
        basePanel.setBackground(new Color(0, 2, 11));
        basePanel.setLayout(new GridBagLayout());

        savePhrases.setFont(saveDialogFont);
        savePhrases.setForeground(new Color(242, 239, 233));

        fileNameField.setFont(fileNameFieldFont);
        fileNameField.setPreferredSize(new Dimension(150, 70));
        fileNameField.setHorizontalAlignment(JTextField.LEFT);

        saveFileButton.setFont(saveDialogFont);

        GridBagConstraints c = new GridBagConstraints();

        c.insets = new Insets(0, 20, 30, 20);
        c.gridx = 0;
        c.weightx = 1.0;
        c.weighty = 0;
        c.fill = GridBagConstraints.NONE;
        c.anchor = GridBagConstraints.CENTER;

        c.gridy = 0;
        basePanel.add(savePhrases, c);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridy = 1;
        basePanel.add(fileNameField, c);

        c.insets = new Insets(15, 20, 0, 20);
        c.fill = GridBagConstraints.NONE;
        c.gridy = 2;
        basePanel.add(saveFileButton, c);

        add(basePanel);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(parent);

        saveFileButton.addActionListener(e -> {
            filename = fileNameField.getText();
            save = true;

            dispose();
        });
    }

    public String getFileName() {
        return fileNameField.getText();
    }

    public boolean isSave() {
        return save;
    }
}
