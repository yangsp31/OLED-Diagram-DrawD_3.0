package DrawFrame.input;

import DTO.inputDTO;
import DTO.material;
import customComponent.scrollableNumericField;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class inputPanel extends JPanel {
    scrollableNumericField highValueField = new scrollableNumericField(20);
    scrollableNumericField lowValueField = new scrollableNumericField(20);

    JLabel materialTypeLabel = new JLabel("Material Type");
    JLabel nameLabel = new JLabel("Name");
    JLabel highLabel = new JLabel("High");
    JLabel lowLabel = new JLabel("Low");

    JTextField nameField = new JTextField(20);

    Font inputPanelFont = new Font("Arial", Font.BOLD, 15);
    Font inputFieldFont = new Font("Arial", Font.PLAIN, 15);

    String[] materialType = {"Metal", "Organic", "Other"};
    JComboBox<String> materialTypeBox = new JComboBox<>(materialType);

    public inputPanel(JButton drawButton, JButton chooseColorButton, JButton editButton,
                      JButton copyButton, JButton deleteButton, JButton saveButton, JButton loadButton, JButton moreFunctionButton) {
        setLayout(new GridBagLayout());
        setBackground(new Color(0, 2, 11));
        setPreferredSize(new Dimension(280, 830));
        setMaximumSize(new Dimension(280, 830));

        materialTypeLabel.setFont(inputPanelFont);
        nameLabel.setFont(inputPanelFont);
        highLabel.setFont(inputPanelFont);
        lowLabel.setFont(inputPanelFont);

        materialTypeLabel.setForeground(new Color(242, 239, 233));
        nameLabel.setForeground(new Color(242, 239, 233));
        highLabel.setForeground(new Color(242, 239, 233));
        lowLabel.setForeground(new Color(242, 239, 233));

        nameField.setPreferredSize(new Dimension(100, 30));
        nameField.setHorizontalAlignment(JTextField.LEFT);
        nameField.setFont(inputFieldFont);
        highValueField.setFont(inputFieldFont);
        lowValueField.setFont(inputFieldFont);

        lowValueField.setEnabled(false);

        GridBagConstraints c = new GridBagConstraints();

        c.insets = new Insets(0, 5, 10, 5);
        c.gridx = 0;
        c.weightx = 1.0;
        c.weighty = 0;
        c.fill = GridBagConstraints.BOTH;
        c.anchor = GridBagConstraints.WEST;

        c.gridy = 0;
        add(materialTypeLabel, c);

        c.gridy = 1;
        add(materialTypeBox, c);

        c.gridy = 2;
        add(nameLabel, c);

        c.gridy = 3;
        add(nameField, c);

        c.gridy = 4;
        add(highLabel, c);

        c.gridy = 5;
        add(highValueField, c);

        c.gridy = 6;
        add(lowLabel, c);

        c.gridy = 7;
        add(lowValueField, c);

        c.insets = new Insets(10, 5, 10, 5);
        c.gridy = 8;
        add(chooseColorButton, c);

        c.gridy = 9;
        add(drawButton, c);

        c.gridy = 10;
        add(editButton, c);

        c.gridy = 11;
        add(copyButton, c);

        c.gridy = 12;
        add(deleteButton, c);

        c.insets = new Insets(100, 5, 10, 5);
        c.gridy = 13;
        add(saveButton, c);

        c.insets = new Insets(10, 5, 10, 5);
        c.gridy = 14;
        add(loadButton, c);

        c.gridy = 15;
        add(moreFunctionButton, c);

        materialTypeBox.addActionListener(e -> {
            try {
                String SelectedType = Objects.requireNonNull(materialTypeBox.getSelectedItem()).toString();

                lowValueField.setEnabled(!SelectedType.equals("Metal"));
            }
            catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
        });
    }

    public inputDTO getNowData() {
        try {
            if(lowValueField.isEnabled()) {
                double highValue = Double.parseDouble(highValueField.getText());
                double lowValue = Double.parseDouble(lowValueField.getText());

                if(!nameField.getText().trim().isEmpty() && highValue < lowValue) {
                    return new inputDTO(Objects.requireNonNull(materialTypeBox.getSelectedItem()).toString(),
                            nameField.getText(), highValue, lowValue);
                }
                else {
                    return null;
                }
            }
            else {
                double highValue = Double.parseDouble(highValueField.getText());

                if(!nameField.getText().trim().isEmpty()) {
                    return new inputDTO(Objects.requireNonNull(materialTypeBox.getSelectedItem()).toString(),
                            nameField.getText(), highValue, 0.0);
                }
                else {
                    return null;
                }
            }
        }
        catch (Exception ex) {
            System.out.println(ex.getMessage());

            return null;
        }
    }

    public void setSelectedData(material material) {
        materialTypeBox.setSelectedItem(material.getType());
        nameField.setText(material.getName());
        highValueField.setText(String.valueOf(material.getHighValue()));
        lowValueField.setText(String.valueOf(material.getLowValue()));
    }

    public void clearData() {
        nameField.setText("");
        highValueField.setText("");
        lowValueField.setText("");
    }
}
