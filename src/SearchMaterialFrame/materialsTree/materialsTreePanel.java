package SearchMaterialFrame.materialsTree;

import javax.swing.*;
import javax.swing.event.TreeExpansionEvent;
import javax.swing.event.TreeWillExpandListener;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeNode;
import java.awt.*;
import java.io.File;

public class materialsTreePanel extends JPanel {
    public materialsTreePanel(JTree materialsTree) {
        setLayout(new BorderLayout());

        // The file in the physical location where the program runs is based on
        File rootFile = new File(System.getProperty("user.dir") + "/materialList");

        // Custom Node (Lazy loading Node)
        lazyLoadNode rootNode = new lazyLoadNode(rootFile.getName(), rootFile);
        rootNode.loadChildren();

        DefaultTreeModel treeModel = new DefaultTreeModel(rootNode);
        materialsTree.setModel(treeModel);

        materialsTree.addTreeWillExpandListener(new TreeWillExpandListener() {
            public void treeWillExpand(TreeExpansionEvent event) {
                TreeNode node = (TreeNode) event.getPath().getLastPathComponent();

                if (node instanceof lazyLoadNode) {
                    ((lazyLoadNode) node).loadChildren();
                }
            }

            public void treeWillCollapse(TreeExpansionEvent event) {}
        });

        JScrollPane treeScrollPane = new JScrollPane(materialsTree);
        treeScrollPane.setPreferredSize(new Dimension(300, 600));
        treeScrollPane.setAutoscrolls(true);
        treeScrollPane.setViewportView(materialsTree);

        setPreferredSize(new Dimension(300, 600));
        setMaximumSize(new Dimension(300, 600));

        add(treeScrollPane, BorderLayout.CENTER);

    }
}

