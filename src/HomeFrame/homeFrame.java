package HomeFrame;

import DocumentFrame.documentFrame;
import DrawFrame.drawFrame;
import customComponent.basicButton;

import javax.swing.*;
import java.awt.*;

public class homeFrame extends JFrame {
    JPanel basePanel = new JPanel();
    JLabel title = new JLabel("Draw OLED Diagram");
    JLabel sentence = new JLabel("Deus paratos favore prosequitur, si vere est.");
    JLabel versionSentence = new JLabel("Version 3.0   Copyright (c) 2025 Yang Seung-pil.   Licensed under the MIT License.");

    basicButton OLEDButton = new basicButton("OLED Diagram");
    basicButton documentButton = new basicButton("Document");

    Font homeButtonFont = new Font("Arial", Font.BOLD, 30);
    Font sentenceFont = new Font("Arial", Font.BOLD, 15);

    public homeFrame() {
        super("DrawD");

        //apply Button Style base customButton(basicButton)
        OLEDButton.setPreferredSize(new Dimension(400, 60));
        documentButton.setPreferredSize(new Dimension(400, 60));
        OLEDButton.setFont(homeButtonFont);
        documentButton.setFont(homeButtonFont);

        title.setFont(new Font("Arial", Font.BOLD, 100));
        title.setForeground(new Color(242, 239, 233));

        sentence.setFont(sentenceFont);
        versionSentence.setFont(sentenceFont);

        versionSentence.setForeground(new Color(242, 239, 233));

        basePanel.setLayout(new GridBagLayout());
        basePanel.setBackground(new Color(0, 2, 11));

        GridBagConstraints c = new GridBagConstraints();

        c.insets = new Insets(130, 0, 90, 0);
        c.gridx = 0;
        c.anchor = GridBagConstraints.CENTER;
        c.weightx = 1.0;
        c.weighty = 0;
        c.fill = GridBagConstraints.NONE;

        c.gridy = 0;
        basePanel.add(title, c);

        c.insets = new Insets(20, 0, 30, 0);
        c.gridy = 1;
        basePanel.add(OLEDButton, c);

        c.gridy = 2;
        basePanel.add(documentButton, c);

        c.insets = new Insets(120, 0, 5, 0);
        c.gridy = 3;
        basePanel.add(versionSentence, c);

        c.insets = new Insets(10, 0, 10, 0);
        c.gridy = 4;
        basePanel.add(sentence, c);

        OLEDButton.addActionListener(e -> {
            dispose();

            new drawFrame().setVisible(true);
        });

        documentButton.addActionListener(e -> {
            new documentFrame().setVisible(true);
        });

        add(basePanel);
        setSize(1600, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);
    }
}
