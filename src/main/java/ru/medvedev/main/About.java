package ru.medvedev.main;

import javax.swing.*;
import java.awt.*;

public class About extends JFrame{
    public About() {
        super("About");
        initCompoments();
    }

    private void initCompoments() {
        this.setBounds(400, 400, 240, 80);
        this.setLayout(new BorderLayout());
        this.setResizable(false);

        JLabel myLabel = new JLabel("Текстовый редактор");
        JLabel myLabel2 = new JLabel("Дмитрий медведев, У-178");
        myLabel.setHorizontalAlignment(SwingConstants.CENTER);
        myLabel2.setHorizontalAlignment(SwingConstants.CENTER);

        add(myLabel2, BorderLayout.CENTER);
        add(myLabel, BorderLayout.NORTH);
    }
}


