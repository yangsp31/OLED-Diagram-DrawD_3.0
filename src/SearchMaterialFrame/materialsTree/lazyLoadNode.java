package SearchMaterialFrame.materialsTree;

import javax.swing.tree.DefaultMutableTreeNode;
import java.io.File;
import java.io.FileFilter;

public class lazyLoadNode extends DefaultMutableTreeNode {
    private File file;
    private boolean loaded;

    public lazyLoadNode(String name, File file) {
        super(name);
        this.file = file;
        this.loaded = false;
    }

    @Override
    public boolean isLeaf() {
        return !file.isDirectory();
    }

    public File getFile() {
        return file;
    }

    // Cache logic to prevent duplicate loading
    public void loadChildren() {
        if (!loaded && file.isDirectory()) {
            File[] files = file.listFiles(new FileFilter() {
                @Override
                public boolean accept(File file) {
                    return !file.isHidden();
                }
            });

            if (files != null) {
                for (File child : files) {
                    String fileName = child.getName();

                    if(fileName.lastIndexOf(".") > 0) {
                        fileName = fileName.substring(0, fileName.lastIndexOf("."));
                    }

                    lazyLoadNode node = new lazyLoadNode(fileName, child);
                    add(node);
                }
            }

            loaded = true;
        }
    }
}
