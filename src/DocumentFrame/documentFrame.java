package DocumentFrame;

import LicenseFrame.licenseFrame;
import customComponent.basicButton;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.net.URI;

public class documentFrame extends JFrame {
    JPanel basePanel = new JPanel();

    basicButton onlineDocumentButton = new basicButton("Online Document");
    basicButton localDocumentButton = new basicButton("Local Document");
    basicButton licenseButton = new basicButton("License");

    JLabel documentLabel = new JLabel("Document");
    JLabel notificationLabel = new JLabel("DrawD does not collect any of your data and personal information and does not maliciously invade your PC.");

    Font documentButtonFont = new Font("Arial", Font.BOLD, 30);
    Font documentLabelFont = new Font("Arial", Font.BOLD, 100);
    Font notificationFont = new Font("Arial", Font.BOLD, 15);

    public documentFrame() {
        super("Document");

        basePanel.setLayout(new GridBagLayout());
        basePanel.setBackground(new Color(0, 2, 11));

        onlineDocumentButton.setPreferredSize(new Dimension(400, 60));
        localDocumentButton.setPreferredSize(new Dimension(400, 60));
        licenseButton.setPreferredSize(new Dimension(400, 60));

        onlineDocumentButton.setFont(documentButtonFont);
        localDocumentButton.setFont(documentButtonFont);
        licenseButton.setFont(documentButtonFont);

        documentLabel.setFont(documentLabelFont);
        documentLabel.setForeground(new Color(242, 239, 233));

        notificationLabel.setFont(notificationFont);
        notificationLabel.setForeground(new Color(242, 239, 233));

        GridBagConstraints c = new GridBagConstraints();

        c.insets = new Insets(0, 0, 90, 0);
        c.gridx = 0;
        c.anchor = GridBagConstraints.CENTER;
        c.weightx = 1.0;
        c.weighty = 0;
        c.fill = GridBagConstraints.NONE;

        c.gridy = 0;
        basePanel.add(documentLabel, c);

        c.insets = new Insets(20, 0, 30, 0);
        c.gridy = 1;
        basePanel.add(onlineDocumentButton, c);

        c.gridy = 2;
        basePanel.add(localDocumentButton, c);

        c.gridy = 3;
        basePanel.add(licenseButton, c);

        c.insets = new Insets(60, 0, 0, 0);
        c.gridy = 4;
        basePanel.add(notificationLabel, c);

        add(basePanel);

        setSize(1600, 800);
        setResizable(false);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        onlineDocumentButton.addActionListener(e -> {
            if(Desktop.isDesktopSupported()) {
                try {
                    Desktop desktop = Desktop.getDesktop();
                    URI uri = new URI("https://velog.io/@yang_seongp31/series/drawd-document");

                    desktop.browse(uri);

                    dispose();
                }
                catch(Exception ex) {
                    System.out.println(ex.getMessage());

                    JOptionPane.showMessageDialog(null, "Check your internet connection", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
            else {
                JOptionPane.showMessageDialog(null, "Desktop API not available", "Unavailable", JOptionPane.ERROR_MESSAGE);
            }
        });

        localDocumentButton.addActionListener(e -> {
            Desktop desktop = Desktop.getDesktop();

            try {
                File folder = new File(System.getProperty("user.dir") + "/document");

                if(folder.exists() && folder.isDirectory()) {
                    desktop.open(folder);

                    dispose();
                }
                else {
                    throw new Exception("error");
                }
            }
            catch (Exception ex) {
                System.out.println(ex.getMessage());

                JOptionPane.showMessageDialog(null, "The file is missing or corrupt", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        licenseButton.addActionListener(e -> {
            new licenseFrame().setVisible(true);
        });
    }
}
