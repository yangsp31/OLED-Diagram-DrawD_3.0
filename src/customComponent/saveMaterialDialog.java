package customComponent;

import DTO.searchMaterialDTO;
import fileUtil.saveUtil;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class saveMaterialDialog extends JDialog {
    JPanel basePanel = new JPanel();

    JLabel savePhrases = new JLabel("Enter ingredient information");
    JLabel purposeLabel = new JLabel("Purpose : ");
    JLabel nameLabel = new JLabel("Name : ");
    JLabel highValueLabel = new JLabel("High Value : ");
    JLabel lowValueLabel = new JLabel("Low Value : ");

    JTextField purposeField = new JTextField(20);
    JTextField nameField = new JTextField(20);

    scrollableNumericField highValueField = new scrollableNumericField(20);
    scrollableNumericField lowValueField = new scrollableNumericField(20);

    basicButton saveButton = new basicButton("Save");

    Font saveDialogFont = new Font("Arial", Font.BOLD, 30);
    Font dataFieldFont = new Font("맑은 고딕", Font.PLAIN, 30);      // Fonts for Korean input

    public saveMaterialDialog(JFrame parent) {
        super(parent, "Save Material", true);

        setSize(1200, 600);
        setResizable(false);
        basePanel.setBackground(new Color(0, 2, 11));
        basePanel.setLayout(new GridBagLayout());

        savePhrases.setFont(saveDialogFont);
        purposeLabel.setFont(saveDialogFont);
        nameLabel.setFont(saveDialogFont);
        highValueLabel.setFont(saveDialogFont);
        lowValueLabel.setFont(saveDialogFont);

        savePhrases.setForeground(new Color(242, 239, 233));
        purposeLabel.setForeground(new Color(242, 239, 233));
        nameLabel.setForeground(new Color(242, 239, 233));
        highValueLabel.setForeground(new Color(242, 239, 233));
        lowValueLabel.setForeground(new Color(242, 239, 233));

        purposeField.setFont(dataFieldFont);
        nameField.setFont(dataFieldFont);
        highValueField.setFont(dataFieldFont);
        lowValueField.setFont(dataFieldFont);

        purposeField.setHorizontalAlignment(JTextField.CENTER);
        nameField.setHorizontalAlignment(JTextField.CENTER);
        highValueField.setHorizontalAlignment(JTextField.CENTER);
        lowValueField.setHorizontalAlignment(JTextField.CENTER);

        highValueField.setPreferredSize(purposeField.getPreferredSize());
        lowValueField.setPreferredSize(purposeField.getPreferredSize());

        saveButton.setFont(saveDialogFont);
        saveButton.setPreferredSize(new Dimension(500, 50));

        GridBagConstraints c = new GridBagConstraints();

        c.insets = new Insets(0, 20, 40, 20);
        c.gridx = 0;
        c.weightx = 0.0;
        c.weighty = 0;
        c.fill = GridBagConstraints.NONE;
        c.anchor = GridBagConstraints.CENTER;

        c.gridwidth = 2;
        c.gridy = 0;
        basePanel.add(savePhrases, c);

        c.gridwidth = 1;
        c.gridy = 1;
        c.gridx = 0;
        basePanel.add(purposeLabel, c);

        c.gridx = 1;
        basePanel.add(purposeField, c);

        c.gridy = 2;
        c.gridx = 0;
        basePanel.add(nameLabel, c);

        c.gridx = 1;
        basePanel.add(nameField, c);

        c.gridy = 3;
        c.gridx = 0;
        basePanel.add(highValueLabel, c);

        c.gridx = 1;
        basePanel.add(highValueField, c);

        c.gridy = 4;
        c.gridx = 0;
        basePanel.add(lowValueLabel, c);

        c.gridx = 1;
        basePanel.add(lowValueField, c);

        c.insets = new Insets(30, 20, 0, 20);
        c.gridwidth = 2;
        c.gridy = 5;
        c.gridx = 0;
        basePanel.add(saveButton, c);

        add(basePanel);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(parent);

        saveButton.addActionListener(e ->{
            String purpose = purposeField.getText();
            String name = nameField.getText();
            boolean checkStringField = purpose.trim().isEmpty() || name.trim().isEmpty();

            try {
                if(!checkStringField) {
                    if(lowValueField.getText().trim().isEmpty()) {
                        double highValue = Double.parseDouble(highValueField.getText());

                        if (new saveUtil().saveMaterial(new searchMaterialDTO(purpose, name, highValue, 0.0))) {
                            JOptionPane.showMessageDialog(this, "Save successful", "Success", JOptionPane.INFORMATION_MESSAGE);

                            clearField();
                        }
                    }
                    else {
                        double highValue = Double.parseDouble(highValueField.getText());
                        double lowValue = Double.parseDouble(lowValueField.getText());

                        if(highValue < lowValue) {
                            if(new saveUtil().saveMaterial(new searchMaterialDTO(purpose, name, highValue, lowValue))) {
                                JOptionPane.showMessageDialog(this, "Save successful", "Success", JOptionPane.INFORMATION_MESSAGE);

                                clearField();
                            }
                        }
                        else {
                            throw new IllegalArgumentException("Invalid");
                        }
                    }
                }
                else {
                    JOptionPane.showMessageDialog(this, "Invalid values", "Invalid values", JOptionPane.ERROR_MESSAGE);
                }
            }
            catch (Exception ex) {
                if(ex instanceof IOException) {
                    JOptionPane.showMessageDialog(this, "Error, Contact us", "Error", JOptionPane.ERROR_MESSAGE);
                }
                else {
                    JOptionPane.showMessageDialog(this, "Invalid values", "Invalid values", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }

    private void clearField() {
        nameField.setText("");
        highValueField.setText("");
        lowValueField.setText("");
        purposeField.setText("");
    }
}
