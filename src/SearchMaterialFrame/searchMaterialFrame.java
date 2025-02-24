package SearchMaterialFrame;

import DTO.inputDTO;
import SearchMaterialFrame.materialsTree.lazyLoadNode;
import SearchMaterialFrame.materialsTree.materialsTreePanel;
import SearchMaterialFrame.searchMaterialFunction.searchMaterialFunctionPanel;
import customComponent.basicButton;
import customComponent.materialsTree;
import customEvent.searchMaterialManager;
import fileUtil.readUtil;

import javax.swing.*;
import javax.swing.tree.TreeNode;
import java.awt.*;
import java.io.File;

public class searchMaterialFrame extends JFrame {
    JPanel basePanel = new JPanel();

    basicButton chooseColorButton = new basicButton("Select Color");
    basicButton drawButton = new basicButton("Draw");

    materialsTree materialsTree = new materialsTree();

    // Manage the component's events here, centered on this frame
    // UI management, data management, and delivery are done in the custom panel
    materialsTreePanel materialsTreePanel = new materialsTreePanel(materialsTree);
    searchMaterialFunctionPanel searchMaterialFunctionPanel = new searchMaterialFunctionPanel(chooseColorButton, drawButton);

    Color chooseColor = new Color(150, 150, 150);

    public searchMaterialFrame() {
        super("Search Material");

        basePanel.setLayout(new BorderLayout());
        basePanel.add(materialsTreePanel, BorderLayout.WEST);
        basePanel.add(searchMaterialFunctionPanel, BorderLayout.CENTER);

        add(basePanel);
        setSize(1200, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);

        chooseColorButton.addActionListener(e -> {
            chooseColor = JColorChooser.showDialog(this, "Choose a color", chooseColor);

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
        });

        materialsTree.addTreeSelectionListener(event -> {
            try {
                TreeNode selectNode = (TreeNode) event.getPath().getLastPathComponent();

                // Using custom node (Lazy Loading Node)
                if (selectNode instanceof lazyLoadNode layzNode) {
                    File file = layzNode.getFile();

                    // Suppress extensions for filenames that are held in custom nodes and actually output to the tree
                    if(file.isFile() && file.getName().endsWith(".json")) {
                        searchMaterialFunctionPanel.setSearchData(new readUtil().readFile(file));

                    }
                }
            }
            catch (Exception e) {
                System.out.println(e.getMessage());
            }
        });

        drawButton.addActionListener(e -> {
            inputDTO searchMaterial = searchMaterialFunctionPanel.getInputData();

            if(searchMaterial != null) {
                searchMaterial.setColor(chooseColor);
                searchMaterialManager.getInstance().setSearchMaterial(searchMaterial);
            }
            else {
                JOptionPane.showMessageDialog(null, "Invalid values", "Invalid values", JOptionPane.ERROR_MESSAGE);
            }
        });
    }
}
