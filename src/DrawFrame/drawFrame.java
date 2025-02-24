package DrawFrame;

import DrawFrame.Canvas.canvasPanel;
import DTO.material;
import customComponent.*;
import DrawFrame.moreFunction.moreFunctionPanel;
import customEvent.MaterialManager;
import customEvent.editMaterialSelectionListener;
import DTO.inputDTO;
import DrawFrame.input.inputPanel;
import SearchMaterialFrame.searchMaterialFrame;
import fileUtil.loadUtil;
import fileUtil.saveUtil;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;

public class drawFrame extends JFrame {
    Color chooseColor = new Color(150, 150, 150);

    //customButton (basicButton, toggleButton)
    basicButton drawButton = new basicButton("Draw");
    basicButton chooseColorButton = new basicButton("Select Color");
    toggleButton editButton = new toggleButton("Edit");
    toggleButton copyButton = new toggleButton("Copy");
    toggleButton deleteButton = new toggleButton("Delete");
    basicButton saveButton = new basicButton("Save");
    basicButton loadButton = new basicButton("Load");
    basicButton moreFunctionButton = new basicButton("More Tools");
    basicButton previousButton = new basicButton("Previous");
    toggleButton cutButton = new toggleButton("Cut");
    toggleButton setBorderButton = new toggleButton("Set Border");
    toggleButton moveFrontButton = new toggleButton("Move Front");
    toggleButton moveBackButton = new toggleButton("Move Back");
    toggleButton toggleValueButton = new toggleButton("Toggle Values");
    toggleButton toggleNameButton = new toggleButton("Toggle Name");
    toggleButton invertValueButton = new toggleButton("Invert Values");
    toggleButton invertNameButton = new toggleButton("Invert Name");
    basicButton deleteAllButton = new basicButton("Delete All");
    basicButton searchMaterialButton = new basicButton("Search Material");
    basicButton saveMaterialButton = new basicButton("Save Material");
    toggleButton setTransparencyButton = new toggleButton("Set Transparency");

    JPanel basePanel = new JPanel();
    JPanel functionPanel = new JPanel();
    JPanel cardPanel = new JPanel();

    // Manage the component's events here, centered on this frame
    // UI management, data management, and delivery are done in the custom panel
    canvasPanel canvasPanel = new canvasPanel();
    inputPanel inputPanel = new inputPanel(drawButton, chooseColorButton, editButton, copyButton, deleteButton, saveButton, loadButton, moreFunctionButton);
    moreFunctionPanel moreFunctionPanel = new moreFunctionPanel(previousButton, cutButton, setBorderButton, moveFrontButton, moveBackButton, toggleValueButton,
            toggleNameButton, invertValueButton, invertNameButton, deleteAllButton, searchMaterialButton, saveMaterialButton, setTransparencyButton);

    inputDTO nowData;

    ArrayList<toggleButton> allToggleButton = new ArrayList<>(Arrays.asList(editButton, copyButton, cutButton, setBorderButton, moveFrontButton, moveBackButton, toggleValueButton,
            toggleNameButton, invertValueButton, invertNameButton, setTransparencyButton, deleteButton));
    ArrayList<toggleButton> metalDisabledButton = new ArrayList<>(Arrays.asList(cutButton, setBorderButton, invertNameButton, invertValueButton, setTransparencyButton));
    ArrayList<toggleButton> commonAvailableButton = new ArrayList<>(Arrays.asList(editButton, copyButton, moveFrontButton, moveBackButton, toggleValueButton, toggleNameButton));

    final static Color ACTIVE_COLOR = new Color(150, 150, 150);
    final static Color DELETE_ACTIVE_COLOR = new Color(131, 0, 0);
    final static Color DISABLED_COLOR = new Color(0, 2, 11);

    public drawFrame() {
        super("DrawD");
        basePanel.setLayout(new BorderLayout());

        cardPanel.setLayout(new CardLayout());
        cardPanel.setBackground(new Color(0, 2, 11));
        cardPanel.setPreferredSize(new Dimension(280, 830));
        cardPanel.setMaximumSize(new Dimension(280, 830));

        functionPanel.setBackground(new Color(0, 2, 11));
        functionPanel.setLayout(new BoxLayout(functionPanel, BoxLayout.Y_AXIS));

        cardPanel.add(inputPanel, "inputPanel");
        cardPanel.add(moreFunctionPanel, "moreFunctionPanel");
        functionPanel.add(cardPanel);

        JScrollPane scrollPane = new JScrollPane(canvasPanel);

        basePanel.add(functionPanel, BorderLayout.WEST);
        basePanel.add(scrollPane, BorderLayout.CENTER);

        chooseColorButton.setBackground(chooseColor);

        add(basePanel);
        setSize(1800, 900);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        drawButton.addActionListener(e -> {
            nowData = inputPanel.getNowData();

            if(nowData != null) {
                nowData.setColor(chooseColor);

                canvasPanel.addMaterial(nowData);
                inputPanel.clearData();
            }
            else {
                JOptionPane.showMessageDialog(null, "Invalid values", "Invalid values", JOptionPane.ERROR_MESSAGE);
            }
        });

        chooseColorButton.addActionListener(e -> {
            try {
                chooseColor = JColorChooser.showDialog(null, "Choose Color", chooseColor);

                if(chooseColor != null) {
                    if(chooseColor.getRed() == 0 && chooseColor.getGreen() == 0 && chooseColor.getBlue() == 0) {
                        chooseColorButton.setForeground(new Color(242, 239, 233));
                    }
                    else{
                        chooseColorButton.setForeground(new Color(0, 2, 11));
                    }

                    chooseColorButton.setBackground(chooseColor);
                }
                else {
                    chooseColor = new Color(150, 150, 150);
                    chooseColorButton.setBackground(chooseColor);
                }
            }
            catch (Exception ex) {
                System.out.println(ex.getMessage());

                JOptionPane.showMessageDialog(null, "Error, Contact us", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        editButton.addActionListener(e -> {
            nowData = inputPanel.getNowData();

            if(nowData != null) {
                nowData.setColor(chooseColor);

                canvasPanel.editMaterial(nowData);
                inputPanel.clearData();
            }
            else {
                JOptionPane.showMessageDialog(null, "Invalid values", "Invalid values", JOptionPane.ERROR_MESSAGE);
            }
        });

        copyButton.addActionListener(e -> {
            canvasPanel.copyMaterial();
        });

        deleteButton.addActionListener(e -> {
            int response = JOptionPane.showConfirmDialog(null,"Do you really want to delete it?", "Delete", JOptionPane.YES_NO_OPTION,
                    JOptionPane.WARNING_MESSAGE);

            if(response == JOptionPane.YES_OPTION) {
                canvasPanel.removeMaterial();
                inputPanel.clearData();
            }
        });

        saveButton.addActionListener(e -> {
            String fileName;

            try {
                saveDialog saveDialog =  new saveDialog(this);
                saveDialog.setVisible(true);

                fileName = saveDialog.getFileName();

                if(saveDialog.isSave()) {
                    if(fileName != null && !fileName.trim().isEmpty()) {
                        if(new saveUtil().saveDiagram(canvasPanel.getMaterials(), fileName)) {
                            JOptionPane.showMessageDialog(null, "Save successful", "Success", JOptionPane.INFORMATION_MESSAGE);
                        }
                    }
                    else {
                        JOptionPane.showMessageDialog(null, "Save failed, Enter a valid name", "Invalid values", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
            catch (Exception ex) {
                System.out.println(ex.getMessage());

                JOptionPane.showMessageDialog(null, "Error, Contact us", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        loadButton.addActionListener(e -> {
            try {
                JFileChooser chooser = new JFileChooser();
                FileNameExtensionFilter filter = new FileNameExtensionFilter("JSON Files (*.json)", "json");

                chooser.setCurrentDirectory(new File(System.getProperty("user.dir") + "/save"));
                chooser.setFileFilter(filter);

                int returnVal = chooser.showOpenDialog(null);

                if(returnVal == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = chooser.getSelectedFile();

                    canvasPanel.setLoadData(new loadUtil().loadFile(selectedFile));
                }
            }
            catch (Exception ex) {
                System.out.println(ex.getMessage());

                JOptionPane.showMessageDialog(null, "Error, Contact us", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        moreFunctionButton.addActionListener(e -> {
            CardLayout cardLayout = (CardLayout) cardPanel.getLayout();

            cardLayout.next(cardPanel);
        });

        previousButton.addActionListener(e -> {
            CardLayout cardLayout = (CardLayout) cardPanel.getLayout();

            cardLayout.previous(cardPanel);
        });

        cutButton.addActionListener(e -> {
            canvasPanel.cutMaterial();
        });

        setBorderButton.addActionListener(e -> {
            setBorderDialog setBorderDialog = new setBorderDialog(this);
            setBorderDialog.setVisible(true);

            if(setBorderDialog.isApply()) {
                canvasPanel.setMaterialBorder(setBorderDialog.getBorderType(), setBorderDialog.getBorderColor());
            }
        });

        moveFrontButton.addActionListener(e -> {
            canvasPanel.controlFrontMaterial();
        });

        moveBackButton.addActionListener(e -> {
            canvasPanel.controlBackMaterial();
        });

        toggleValueButton.addActionListener(e -> {
            canvasPanel.showValueMaterial();
        });

        toggleNameButton.addActionListener(e -> {
            canvasPanel.showNameMaterial();
        });

        invertValueButton.addActionListener(e -> {
            canvasPanel.invertValueMaterial();
        });

        invertNameButton.addActionListener(e -> {
            canvasPanel.invertNameMaterial();
        });

        setTransparencyButton.addActionListener(e -> {
            canvasPanel.setTransparent();
        });

        searchMaterialButton.addActionListener(e -> {
            searchMaterialFrame searchMaterialFrame = new searchMaterialFrame();
            searchMaterialFrame.setVisible(true);
        });

        saveMaterialButton.addActionListener(e -> {
            saveMaterialDialog saveMaterialDialog =  new saveMaterialDialog(this);
            saveMaterialDialog.setVisible(true);
        });

        deleteAllButton.addActionListener(e -> {
            int response = JOptionPane.showConfirmDialog(null,"Do you really want to delete them all?", "Delete All", JOptionPane.YES_NO_OPTION,
                    JOptionPane.WARNING_MESSAGE);

            if(response == JOptionPane.YES_OPTION) {
                canvasPanel.removeAllMaterial();
            }
        });

        // Register custom events to update the UI in context
        MaterialManager.getInstance().addListener(editMaterialSelectionListener.class, new editMaterialSelectionListener() {
            @Override
            public void materialSelect(material material) {
                if(material != null) {
                    inputPanel.setSelectedData(material);
                    chooseColor = material.getColor();
                    chooseColorButton.setBackground(material.getColor());

                    if(!material.getType().equals("Metal")) {
                        for(toggleButton button : metalDisabledButton) {
                            button.setBackground(ACTIVE_COLOR);
                            button.setEnabled(true);
                        }
                    }
                    else {
                        for(toggleButton button : metalDisabledButton) {
                            button.setBackground(DISABLED_COLOR);
                            button.setEnabled(false);
                        }
                    }

                    for(toggleButton button : commonAvailableButton) {
                        button.setBackground(ACTIVE_COLOR);
                        button.setEnabled(true);
                    }

                    deleteButton.setBackground(DELETE_ACTIVE_COLOR);
                    deleteButton.setEnabled(true);
                }
                else {
                    for(toggleButton button : allToggleButton) {
                        button.setBackground(DISABLED_COLOR);
                        button.setEnabled(false);
                    }

                    inputPanel.clearData();
                }
            }
        });
    }
}
