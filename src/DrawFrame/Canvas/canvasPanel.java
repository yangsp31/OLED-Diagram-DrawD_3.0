package DrawFrame.Canvas;

import DTO.material;
import customEvent.MaterialManager;
import DTO.inputDTO;
import customEvent.searchMaterialListener;
import customEvent.searchMaterialManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;
import java.util.Comparator;

public class canvasPanel extends JPanel implements MouseListener, MouseMotionListener, MouseWheelListener {
    private static final int LEFT = 0;
    private static final int RIGHT = 1;
    private final int xOffset = 300;
    private final int yOffset = 90;
    private int lastX = 0;
    private float[] dashPattern = {5, 5};
    private Point canvasDragStart;
    private Point InternalDragStart;
    public material selectedMaterial = null;
    private ArrayList<material> materials = new ArrayList<>();
    private boolean resizing = false;
    private boolean moving = false;
    private boolean canvasDrag = false;
    private boolean editZIndex = false;
    private double canvasScale = 1.0;

    public canvasPanel() {
        setPreferredSize(new Dimension(15000, 2500));
        setBackground(Color.white);

        addMouseListener(this);
        addMouseMotionListener(this);
        addMouseWheelListener(this);

        // Register a custom event to update the U.I with retrieved data
        searchMaterialManager.getInstance().addListener(searchMaterialListener.class, new searchMaterialListener() {
            @Override
            public void searchMaterial(inputDTO inputDTO) {
                addMaterial(inputDTO);
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Color originalColor;
        Font nameFont = new Font("Arial", Font.BOLD, 25);
        Font valueFont = new Font("Arial", Font.BOLD, 21);
        int centerX;
        int centerY;
        int nameWidth;
        int nameHeight;
        FontMetrics fontMetrics;
        AffineTransform originalTransform;

        Graphics2D g2d = (Graphics2D) g;

        g2d.scale(canvasScale, canvasScale);

        if(editZIndex) {
            materials.sort((Comparator.comparingInt(material::getzIndex)));

            editZIndex = false;
        }

        for(material material : materials) {
            if(material.getType().equals("Metal")) {
                if(selectedMaterial == material) {
                    g2d.setStroke(new BasicStroke(3, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 0, dashPattern, 0));
                }
                else {
                    g2d.setStroke(new BasicStroke(3));
                }

                g2d.setColor(material.getColor());

                g2d.drawLine((int)material.getX(), (int)Math.round(material.getHighValue()  * yOffset) + 1,
                        (int)material.getX() + (int)material.getWidth(), (int)Math.round(material.getHighValue()  * yOffset) + 1);

                g2d.drawLine((int)material.getX(), (int)Math.round(material.getHighValue()  * yOffset) + 7,
                        (int)material.getX() + (int)material.getWidth(), (int)Math.round(material.getHighValue()  * yOffset) + 7);

                g2d.setColor(Color.black);

                if(material.isShowName()) {
                    g2d.setFont(nameFont);
                    fontMetrics = g2d.getFontMetrics();

                    g2d.drawString(material.getName(),
                            (int)Math.round(material.getX() + material.getWidth() / 2 - (double) fontMetrics.stringWidth(material.getName()) / 2),
                            (int)Math.round(material.getHighValue() * yOffset - (double) fontMetrics.getHeight() / 2 - 1));
                }

                if(material.isShowValue()) {
                    g2d.setFont(valueFont);
                    fontMetrics = g2d.getFontMetrics();

                    g2d.drawString(material.getHighValue() + "eV",
                            (int)Math.round(material.getX() + material.getWidth() / 2 - (double) fontMetrics.stringWidth(material.getHighValue() + "eV") / 2),
                            (int)Math.round(material.getHighValue() * yOffset + (double) fontMetrics.getHeight() / 2 + 23));
                }
            }
            else {
                if(material.isCut()) {
                    g2d.setColor(Color.WHITE);

                    g2d.fillRect((int)material.getX(), (int)Math.round(material.getHighValue() * yOffset) - 1000, (int)material.getWidth(), 100000);
                }

                originalColor = material.getColor();
                g2d.setColor(originalColor);

                if(material.getTransparency() < 1.0f) {
                    g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, material.getTransparency()));

                    g2d.fillRect((int)material.getX(), (int)Math.round(material.getHighValue() * yOffset), (int)material.getWidth(),
                            (int)Math.round((material.getLowValue() - material.getHighValue()) * yOffset));

                    g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1.0f));
                }
                else {
                    g2d.fillRect((int)material.getX(), (int)Math.round(material.getHighValue() * yOffset), (int)material.getWidth(),
                            (int)Math.round((material.getLowValue() - material.getHighValue()) * yOffset));
                }

                if(selectedMaterial == material) {
                    if(originalColor.getRed() == 255 && originalColor.getGreen() == 255 && originalColor.getBlue() == 255) {
                        g2d.setColor(Color.black);
                    }
                    else {
                        g2d.setColor(new Color(Math.max((int)(originalColor.getRed() * 0.8f), 0),
                                Math.max((int)(originalColor.getGreen() * 0.8f), 0), Math.max((int)(originalColor.getBlue() * 0.8f), 0)));
                    }

                    g2d.setStroke(new BasicStroke(3, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 0, dashPattern, 0));

                    g2d.drawRect((int)material.getX(), (int)Math.round(material.getHighValue() * yOffset), (int)material.getWidth(),
                            (int)Math.round((material.getLowValue() - material.getHighValue()) * yOffset));
                }
                else if(material.getBorerType().equals("Line")) {
                    g2d.setColor(material.getBorerColor());
                    g2d.setStroke(new BasicStroke(2));

                    g2d.drawRect((int)material.getX(), (int)Math.round(material.getHighValue() * yOffset), (int)material.getWidth(),
                            (int)Math.round((material.getLowValue() - material.getHighValue()) * yOffset));
                }
                else if(material.getBorerType().equals("Dot")) {
                    g2d.setColor(material.getBorerColor());
                    g2d.setStroke(new BasicStroke(2, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 0, dashPattern, 0));

                    g2d.drawRect((int)material.getX(), (int)Math.round(material.getHighValue() * yOffset), (int)material.getWidth(),
                            (int)Math.round((material.getLowValue() - material.getHighValue()) * yOffset));
                }

                g2d.setColor(Color.black);

                if(material.isShowName()) {
                    if(material.isInvertName()) {
                        centerX = getInvertCenterX(material);
                    }
                    else {
                        centerX = getCenterX(material);
                    }

                    g2d.setFont(nameFont);
                    fontMetrics = g2d.getFontMetrics();
                    centerY = (int)Math.round(material.getHighValue() * yOffset + ((material.getLowValue() - material.getHighValue()) * yOffset) / 2);
                    nameWidth = fontMetrics.stringWidth(material.getName());
                    nameHeight = fontMetrics.getHeight();

                    originalTransform = g2d.getTransform();
                    g2d.translate(centerX, centerY);
                    g2d.rotate(Math.PI / 2);

                    g2d.drawString(material.getName(),  -nameWidth / 2, nameHeight / 6);

                    g2d.setTransform(originalTransform);
                }

                if(material.isShowValue()) {
                    if(material.isInvertValue()) {
                        centerX = getInvertCenterX(material);
                    }
                    else {
                        centerX = getCenterX(material);
                    }

                    g2d.setFont(valueFont);
                    fontMetrics = g2d.getFontMetrics();

                    g2d.drawString(material.getHighValue() + "eV",
                            centerX - fontMetrics.stringWidth(material.getHighValue() + "eV") / 2,
                            (int)(material.getHighValue() * yOffset - 7));

                    g2d.drawString(material.getLowValue() + "eV",
                            centerX - fontMetrics.stringWidth(material.getLowValue() + "eV") / 2,
                            (int)(material.getLowValue() * yOffset + 23));
                }
            }
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        for(int i = materials.size() - 1; i >= 0; i--) {
            if(isContain(e.getPoint(), materials.get(i))) {
                selectedMaterial = materials.get(i);
                MaterialManager.getInstance().setSelectedMaterial(selectedMaterial);       // Custom event triggers

                SwingUtilities.invokeLater(this::repaint);

                return;
            }
        }

        selectedMaterial = null;
        MaterialManager.getInstance().setSelectedMaterial(selectedMaterial);    // Custom event triggers

        SwingUtilities.invokeLater(this::repaint);
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if(selectedMaterial != null && isOnEdge(e.getPoint(), selectedMaterial)) {
            resizing = true;
        }
        else if(selectedMaterial != null && isOnInternal(e.getPoint(), selectedMaterial)) {
            moving = true;
            InternalDragStart = e.getPoint();
        }
        else {
            resizing = false;
            moving = false;
            canvasDragStart = e.getPoint();
            canvasDrag = true;
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        resizing = false;
        moving = false;
        canvasDrag = false;
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {
        if(selectedMaterial != null && resizing) {
            double minWidth;

            minWidth = 40;

            if(getResizeDirection(e.getPoint(), selectedMaterial) == LEFT) {
                double newX = e.getX() / canvasScale;
                double newWidth = selectedMaterial.getWidth() + (selectedMaterial.getX() - newX);

                if(newWidth > minWidth) {
                    selectedMaterial.setX(newX);
                    selectedMaterial.setWidth(newWidth);
                }
            }
            else if(getResizeDirection(e.getPoint(), selectedMaterial) == RIGHT) {
                double newWidth = e.getX() / canvasScale - selectedMaterial.getX();

                if(newWidth > minWidth) {
                    selectedMaterial.setWidth(newWidth);
                }
            }

            SwingUtilities.invokeLater(this::repaint);
        }
        else if(selectedMaterial != null && moving) {
            int dx = (int)(e.getX() / canvasScale - InternalDragStart.x / canvasScale);
            double newX = selectedMaterial.getX() + dx;
            selectedMaterial.setX(newX);

            InternalDragStart = e.getPoint();

            SwingUtilities.invokeLater(this::repaint);
        }
        else if(canvasDrag) {
            int dx = e.getX() - canvasDragStart.x;
            int dy = e.getY() - canvasDragStart.y;

            canvasDragStart = e.getPoint();
            JViewport viewport = (JViewport)SwingUtilities.getAncestorOfClass(JViewport.class, this);

            if(viewport != null) {
                Point viewPosition = viewport.getViewPosition();
                viewPosition.translate(-dx, -dy);

                int maxX = getWidth() - viewport.getWidth();
                int maxY = getHeight() - viewport.getHeight();

                viewPosition.x = Math.max(0, Math.min(maxX, viewPosition.x));
                viewPosition.y = Math.max(0, Math.min(maxY, viewPosition.y));

                viewport.setViewPosition(viewPosition);
            }
        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        if(selectedMaterial != null && isOnEdge(e.getPoint(), selectedMaterial)) {
            setCursor(new Cursor(Cursor.E_RESIZE_CURSOR));
        }
        else if(selectedMaterial != null && isOnInternal(e.getPoint(), selectedMaterial)) {
            setCursor(new Cursor(Cursor.MOVE_CURSOR));
        }
        else {
            setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
        }
    }

    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {
        int WheelRate = e.getWheelRotation();

        if(WheelRate < 0) {
            canvasScale *= 1.1;
        }
        else {
            canvasScale /= 1.1;
        }

        SwingUtilities.invokeLater(this::repaint);
    }

    private int getInvertCenterX (material material) {
        if(material.getType().equals("Organic")) {
            return material.getWidth() < 150 ?
                    (int)Math.round((material.getX() + material.getWidth()) - material.getWidth() / 2.0) :
                    (int)Math.round((material.getX() + material.getWidth()) - (double) 150 / 2.0);
        }
        else {
             return material.getWidth() < 70 ?
                    (int)Math.round((material.getX() + material.getWidth()) - material.getWidth() / 2.0) :
                    (int)Math.round((material.getX() + material.getWidth()) - (double) 70 / 2.0);
        }
    }

    private int getCenterX (material material) {
        if(material.getType().equals("Organic")) {
            return material.getWidth() < 150 ?
                    (int)Math.round(material.getX() + material.getWidth() / 2.0) :
                    (int)Math.round(material.getX() + (double) 150 / 2.0);
        }
        else {
            return material.getWidth() < 70 ?
                    (int)Math.round(material.getX() + material.getWidth() / 2.0) :
                    (int)Math.round(material.getX() + (double) 70 / 2.0);
        }
    }

    private boolean isContain(Point point, material material) {
        if(material.getType().equals("Metal")) {
            return (point.x / canvasScale >= material.getX() && point.x / canvasScale <= material.getX() + material.getWidth()) &&
                    (point.y / canvasScale >= material.getHighValue()  * yOffset && point.y / canvasScale <= material.getHighValue() * yOffset + 6);
        }
        else {
            return (point.x / canvasScale >= material.getX() && point.x / canvasScale <= material.getX() + material.getWidth()) &&
                    (point.y / canvasScale >= material.getHighValue() * yOffset && point.y / canvasScale <= material.getLowValue() * yOffset);
        }
    }

    private boolean isOnEdge(Point point, material material) {
        int edgeThickness = 5;

        if(material.getType().equals("Metal")) {
            return ((Math.abs(point.x / canvasScale - (material.getX())) <= edgeThickness) ||
                    (Math.abs(point.x / canvasScale - (material.getX() + material.getWidth())) <= edgeThickness)) &&
                    (point.y / canvasScale >= material.getHighValue() * yOffset && point.y / canvasScale <= material.getHighValue() * yOffset + 6);
        }
        else {
            return ((Math.abs(point.x / canvasScale - (material.getX())) <= edgeThickness) ||
                    (Math.abs(point.x / canvasScale - (material.getX() + material.getWidth())) <= edgeThickness)) &&
                    (point.y / canvasScale >= material.getHighValue() * yOffset && point.y / canvasScale <= material.getLowValue() * yOffset);
        }
    }

    private boolean isOnInternal(Point point, material material) {
        int edgeThickness = 5;

        if(material.getType().equals("Metal")) {
            return ((point.x / canvasScale >= material.getX() + edgeThickness) &&
                    (point.x / canvasScale <= material.getX() + material.getWidth() - 5)) &&
                    (point.y / canvasScale >= material.getHighValue() * yOffset && point.y / canvasScale <= material.getHighValue() * yOffset + 6);
        }
        return ((point.x / canvasScale < (material.getX() + material.getWidth() - 5)) &&
                (point.x / canvasScale > material.getX() + edgeThickness)) &&
                (point.y / canvasScale >= material.getHighValue() * yOffset && point.y / canvasScale <= material.getLowValue() * yOffset);
    }

    private int getResizeDirection(Point point, material material) {
        int edgeThickness = (int)(yOffset / canvasScale / 2) + 5;

        if(Math.abs(point.x / canvasScale - material.getX()) < edgeThickness) {
            return LEFT;
        }
        else if(Math.abs(point.x / canvasScale - (material.getX() + material.getWidth())) < edgeThickness) {
            return RIGHT;
        }

        return -1;
    }

    public void addMaterial(inputDTO data) {
        if(materials.isEmpty()) {
            lastX = 100 + xOffset;
        }
        else {
            lastX = (int)Math.round(materials.getLast().getX()) + (int)Math.round(materials.getLast().getWidth());
        }

        switch (data.getMaterialType()) {
            case "Metal" -> {
                material newMaterial = new material(data.getName(), data.getColor(),
                        data.getMaterialType(), lastX, 150, 0, data.getHighValue(), 0);

                materials.add(newMaterial);
            }
            case "Organic" -> {
                material newMaterial = new material(data.getName(), data.getColor(), data.getMaterialType(), lastX,
                        150, data.getLowValue(), data.getHighValue(), 0);

                materials.add(newMaterial);
            }
            case "Other" -> {
                material newMaterial = new material(data.getName(), data.getColor(), data.getMaterialType(), lastX,
                        70, data.getLowValue(), data.getHighValue(), 0);

                materials.add(newMaterial);
            }
        }

        SwingUtilities.invokeLater(this::repaint);
    }

    public void removeMaterial() {
        materials.remove(selectedMaterial);
        selectedMaterial = null;

        MaterialManager.getInstance().setSelectedMaterial(selectedMaterial);     // Custom event triggers

        SwingUtilities.invokeLater(this::repaint);
    }

    public void removeAllMaterial() {
        materials.clear();
        selectedMaterial = null;

        MaterialManager.getInstance().setSelectedMaterial(selectedMaterial);      // Custom event triggers

        SwingUtilities.invokeLater(this::repaint);
    }

    public ArrayList<material> getMaterials() {
        return materials;
    }

    public void editMaterial(inputDTO data) {
        if(data.getMaterialType().equals("Metal") || data.getMaterialType().equals("Organic")) {
            selectedMaterial.setWidth(150);
        }
        else {
            selectedMaterial.setWidth(70);
        }

        selectedMaterial.setType(data.getMaterialType());
        selectedMaterial.setName(data.getName());
        selectedMaterial.setHighValue(data.getHighValue());
        selectedMaterial.setLowValue(data.getLowValue());
        selectedMaterial.setColor(data.getColor());

        SwingUtilities.invokeLater(this::repaint);
    }

    public void cutMaterial() {
        selectedMaterial.setCut(!selectedMaterial.isCut());

        SwingUtilities.invokeLater(this::repaint);
    }

    public void showValueMaterial() {
        selectedMaterial.setShowValue(!selectedMaterial.isShowValue());

        SwingUtilities.invokeLater(this::repaint);
    }

    public void showNameMaterial() {
        selectedMaterial.setShowName(!selectedMaterial.isShowName());

        SwingUtilities.invokeLater(this::repaint);
    }

    public void invertValueMaterial() {
        selectedMaterial.setInvertValue(!selectedMaterial.isInvertValue());

        SwingUtilities.invokeLater(this::repaint);
    }

    public void invertNameMaterial() {
        selectedMaterial.setInvertName(!selectedMaterial.isInvertName());

        SwingUtilities.invokeLater(this::repaint);
    }

    public void controlFrontMaterial() {
        selectedMaterial.setZIndex(selectedMaterial.getzIndex() + 1);
        editZIndex = true;

        SwingUtilities.invokeLater(this::repaint);
    }

    public void controlBackMaterial() {
        selectedMaterial.setZIndex(selectedMaterial.getzIndex() - 1);
        editZIndex = true;

        SwingUtilities.invokeLater(this::repaint);
    }

    public void copyMaterial() {
        material newMaterial = new material(selectedMaterial);
        double newX = (int)Math.round(materials.getLast().getX()) + (int)Math.round(materials.getLast().getWidth());

        newMaterial.setX(newX);
        materials.add(newMaterial);

        SwingUtilities.invokeLater(this::repaint);
    }

    public void setLoadData(ArrayList<material> loadMaterials) {
        materials = loadMaterials;

        SwingUtilities.invokeLater(this::repaint);
    }

    public void setMaterialBorder(String type, Color color) {
        selectedMaterial.setBorerType(type);
        selectedMaterial.setBorerColor(color);

        SwingUtilities.invokeLater(this::repaint);
    }

    // Use integer operations to avoid floating-point errors caused by float operations
    public void setTransparent() {
        if(selectedMaterial.getTransparency() == 1.0f) {   // True only in simple assignment, so no tolerance is considered
            selectedMaterial.setTransparency((100f - 20f) / 100f);
        }
        else {
            float tempValue = selectedMaterial.getTransparency() * 100f - 30f;

            if(tempValue <= 0) {
                selectedMaterial.setTransparency(1.0f);
            }
            else {
                selectedMaterial.setTransparency(tempValue / 100f);
            }
        }

        SwingUtilities.invokeLater(this::repaint);
    }
}
