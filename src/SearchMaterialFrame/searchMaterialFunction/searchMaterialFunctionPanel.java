package SearchMaterialFrame.searchMaterialFunction;

import DTO.inputDTO;
import DTO.searchMaterialDTO;
import customComponent.scrollableNumericField;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class searchMaterialFunctionPanel extends JPanel {
    JLabel purposeLabel = new JLabel("Purpose : ");
    JLabel purposeValueLabel = new JLabel("");
    JLabel nameLabel = new JLabel("Name : ");
    JLabel nameValueLabel = new JLabel("");
    JLabel highValueLabel = new JLabel("High Value : ");
    JLabel lowValueLabel = new JLabel("Low Value : ");

    scrollableNumericField highValueField = new scrollableNumericField(20);
    scrollableNumericField lowValueField = new scrollableNumericField(20);

    Font searchMaterialFunctionPanelFont = new Font("Arial", Font.BOLD, 25);
    Font searchMaterialFunctionValueFont = new Font("맑은 고딕", Font.PLAIN, 25);

    String[] materialType = {"Organic", "Other", "Metal"};
    JComboBox<String> materialTypeBox = new JComboBox<>(materialType);

    public searchMaterialFunctionPanel(JButton chooseColorButton, JButton drawButton) {
        setLayout(new GridBagLayout());
        setBackground(new Color(0, 2, 11));
        setSize(900, 600);
        setMaximumSize(new Dimension(900, 600));

        purposeLabel.setFont(searchMaterialFunctionPanelFont);
        nameLabel.setFont(searchMaterialFunctionPanelFont);
        highValueLabel.setFont(searchMaterialFunctionPanelFont);
        lowValueLabel.setFont(searchMaterialFunctionPanelFont);

        purposeLabel.setForeground(new Color(242, 239, 233));
        nameLabel.setForeground(new Color(242, 239, 233));
        highValueLabel.setForeground(new Color(242, 239, 233));
        lowValueLabel.setForeground(new Color(242, 239, 233));

        purposeValueLabel.setFont(searchMaterialFunctionValueFont);
        nameValueLabel.setFont(searchMaterialFunctionValueFont);

        purposeValueLabel.setForeground(new Color(242, 239, 233));
        nameValueLabel.setForeground(new Color(242, 239, 233));

        highValueField.setFont(searchMaterialFunctionValueFont);
        lowValueField.setFont(searchMaterialFunctionValueFont);

        highValueField.setHorizontalAlignment(JTextField.CENTER);
        lowValueField.setHorizontalAlignment(JTextField.CENTER);

        chooseColorButton.setFont(searchMaterialFunctionPanelFont);
        chooseColorButton.setPreferredSize(new Dimension(200, 50));

        materialTypeBox.setFont(searchMaterialFunctionPanelFont);

        drawButton.setFont(searchMaterialFunctionPanelFont);
        drawButton.setPreferredSize(new Dimension(200, 50));

        GridBagConstraints c = new GridBagConstraints();

        c.insets = new Insets(0, 50, 30, 50);
        c.gridx = 0;
        c.weightx = 0.0;
        c.weighty = 0.0;
        c.fill = GridBagConstraints.NONE;
        c.anchor = GridBagConstraints.CENTER;

        c.gridy = 0;
        add(purposeLabel, c);

        c.gridx = 1;
        add(purposeValueLabel, c);

        c.gridx = 0;
        c.gridy = 1;
        add(nameLabel, c);

        c.gridx = 1;
        add(nameValueLabel, c);

        c.gridx = 0;
        c.gridy = 2;
        add(highValueLabel, c);

        c.gridx = 1;
        add(highValueField, c);

        c.gridx = 0;
        c.gridy = 3;
        add(lowValueLabel, c);

        c.gridx = 1;
        add(lowValueField, c);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.insets = new Insets(60, 50, 0, 50);
        c.gridx = 0;
        c.gridy = 4;
        add(materialTypeBox, c);

        c.gridx = 1;
        add(chooseColorButton, c);

        c.gridwidth = 2;
        c.gridx = 0;
        c.gridy = 5;
        add(drawButton, c);

        materialTypeBox.addActionListener(e -> {
            String SelectedType = Objects.requireNonNull(materialTypeBox.getSelectedItem()).toString();

            lowValueField.setEnabled(!SelectedType.equals("Metal"));
        });
    }

    public inputDTO getInputData() {
        try {
            if(lowValueField.isEnabled()) {
                double highValue = Double.parseDouble(highValueField.getText());
                double lowValue = Double.parseDouble(lowValueField.getText());

                if(!nameValueLabel.getText().trim().isEmpty() && highValue < lowValue) {
                    return new inputDTO(Objects.requireNonNull(materialTypeBox.getSelectedItem()).toString(),
                            nameValueLabel.getText(), highValue, lowValue);
                }
                else {
                    return null;
                }
            }
            else {
                double highValue = Double.parseDouble(highValueField.getText());

                if(!nameValueLabel.getText().trim().isEmpty()) {
                    return new inputDTO(Objects.requireNonNull(materialTypeBox.getSelectedItem()).toString(),
                            nameValueLabel.getText(), highValue, 0.0);
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

    public void setSearchData(searchMaterialDTO searchData) {
        purposeValueLabel.setText(searchData.getPurpose());
        nameValueLabel.setText(searchData.getName());
        highValueField.setText(String.valueOf(searchData.getHighValue()));
        lowValueField.setText(String.valueOf(searchData.getLowValue()));
    }
}
